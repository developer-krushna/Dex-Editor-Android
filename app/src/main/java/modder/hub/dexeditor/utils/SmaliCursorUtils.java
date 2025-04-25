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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class SmaliCursorUtils {
	/*
	Author @developer-krushna
	Code fixed/regex/comments by ChatGPT
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
	
	
	public static List<String> extractAllLabelLines(CharSequence text, int cursorLine) {
		List<String> labelLines = new ArrayList<>();
		String[] lines = text.toString().split("\n");
		
		MethodBoundary method = findMethodBoundary(lines, cursorLine);
		if (method == null) return labelLines;
		
		// List of individual patterns for each label type
		String[] patterns = {
			// Conditional branches
			"if-(?:eq|ne|lt|gt|le|ge|eqz|nez|ltz|gtz|lez|gez)\\b.*?:(\\w+)",
			// Unconditional branches
			"goto\\s+:([^\\s,]+)",
			// Switch cases
			"(?:packed|sparse)-switch\\b.*?:(\\w+)",
			// Exception handlers
			"(?:catch|catchall).*?\\{:(\\w+)\\s+\\.\\.\\s+:(\\w+)\\}.*?:(\\w+)",
			// Try blocks
			"\\.try_(?:start|end)_(\\w+)",
			// Switch case entries (0x... -> :label)
			"0x[0-9a-f]+\\s*->\\s*:(\\w+)",
			// Plain labels
			"^\\s*:(\\w+)"
		};
		
		for (int lineNum = method.startLine; lineNum <= method.endLine; lineNum++) {
			String line = lines[lineNum].trim();
			if (line.isEmpty() || line.startsWith("#") || line.startsWith(".field")) {
				continue;
			}
			
			// Check against each pattern individually
			for (String pattern : patterns) {
				if (Pattern.matches(".*" + pattern + ".*", line)) {
					labelLines.add(String.format("[%d] %s", lineNum + 1, line));
					break; // Found a match, move to next line
				}
			}
		}
		return labelLines;
	}
	
	private static class MethodBoundary {
		int startLine;
		int endLine;
	}
	
	private static MethodBoundary findMethodBoundary(String[] lines, int cursorLine) {
		MethodBoundary boundary = new MethodBoundary();
		boundary.startLine = -1;
		
		for (int i = Math.min(cursorLine, lines.length - 1); i >= 0; i--) {
			if (lines[i].trim().startsWith(".method ")) {
				boundary.startLine = i;
				break;
			}
		}
		
		if (boundary.startLine == -1) return null;
		
		for (int i = boundary.startLine + 1; i < lines.length; i++) {
			if (lines[i].trim().startsWith(".end method")) {
				boundary.endLine = i;
				return boundary;
			}
		}
		return null;
	}
}
