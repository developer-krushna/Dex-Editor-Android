/*
* Dex-Editor-Android an Advanced Dex Editor for Android 
* Copyright 2024-25, developer-krushna
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are
* met:
*
*     * Redistributions of source code must retain the above copyright
* notice, this list of conditions and the following disclaimer.
*     * Redistributions in binary form must reproduce the above
* copyright notice, this list of conditions and the following disclaimer
* in the documentation and/or other materials provided with the
* distribution.
*     * Neither the name of developer-krushna nor the names of its
* contributors may be used to endorse or promote products derived from
* this software without specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
* "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
* LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
* A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
* OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
* SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
* LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
* DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
* THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
* (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
* OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.


*     Please contact Krushna by email mt.modder.hub@gmail.com if you need
*     additional information or have any questions
*/


package modder.hub.dexeditor.utils;

import android.text.TextUtils;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class SmaliCursorUtils {
	/*
	Author @developer-krushna
	Code fixed/comments by ChatGPT
	*/
	
	public static String getCurrentMethodOrFieldName(CharSequence text, int cursorLine) {
		String[] lines = text.toString().split("\n");
		
		// First check methods
		String methodInfo = getCurrentMethodName(lines, cursorLine);
		if (methodInfo != null) return methodInfo + "()";
		
		// Then check fields
		return getCurrentFieldName(lines, cursorLine);
	}
	
	private static String getCurrentMethodName(String[] lines, int cursorLine) {
		int methodStart = -1;
		String methodName = null;
		
		// Search upwards for .method
		for (int i = Math.min(cursorLine, lines.length - 1); i >= 0; i--) {
			String line = lines[i].trim();
			if (line.startsWith(".method ")) {
				methodStart = i;
				String[] tokens = line.split("\\s+");
				methodName = tokens[tokens.length - 1]; // Get last token
				methodName = methodName.substring(0, methodName.indexOf('(')); // Get text before "("
				break;
			}
		}
		
		if (methodStart == -1) return null;
		
		// Check if cursor is within method bounds
		for (int i = methodStart + 1; i < lines.length; i++) {
			if (lines[i].trim().startsWith(".end method")) {
				if (cursorLine >= methodStart && cursorLine <= i) {
					return String.format("[%d-%d] : %s", 
					methodStart + 1, i + 1, methodName);
				}
				break;
			}
		}
		return null;
	}
	
	private static String getCurrentFieldName(String[] lines, int cursorLine) {
		for (int i = Math.min(cursorLine, lines.length - 1); i >= 0; i--) {
			String line = lines[i].trim();
			if (line.startsWith(".field ") && i == cursorLine) {
				String[] parts = line.split("\\s+");
				for (String part : parts) {
					if (part.contains(":")) {
						return String.format("[%d] : %s", 
						i + 1, part.substring(0, part.indexOf(':')));
					}
				}
			}
		}
		return null;
	}
}
