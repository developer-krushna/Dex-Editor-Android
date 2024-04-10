/*
 * Dex-Editor-Android an Advanced Dex Editor for Android 
 * Copyright 2024, developer-krushna
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
 
package modder.hub.dexeditor.smali;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/*
Author @developer-krushna
*/
public class SmaliMethodBody {
	
	private String smaliFilePath;
	private String[] methodsToDraw;
	
	public SmaliMethodBody(String smaliFilePath, String[] methodsToDraw) {
		this.smaliFilePath = smaliFilePath;
		this.methodsToDraw = methodsToDraw;
	}
	
	public String parseClassInSmali() {
		StringBuilder extractedCode = new StringBuilder();
		
		try (BufferedReader smaliFile = new BufferedReader(new FileReader(smaliFilePath))) {
			String line;
			StringBuilder methodBlock = new StringBuilder();
			boolean inMethod = false;
			
			while ((line = smaliFile.readLine()) != null) {
				String trimLine = line.trim();
				
				if (!trimLine.isEmpty()) {
					// Print the .class and .source lines
					if (".class".equals(trimLine.split(" ")[0]) || ".super".equals(trimLine.split(" ")[0])) {
						extractedCode.append(line).append("\n");
					}
					
					// Print the first two lines of each method block
					if (".method".equals(trimLine.split(" ")[0])) {
						inMethod = true;
						methodBlock.setLength(0); // Reset methodBlock for a new method
						methodBlock.append(line).append("\n");
					} else if (inMethod) {
						methodBlock.append(line).append("\n");
					}
					
					// Check for ".end method" to mark the end of the method block
					if (inMethod && ".end method".equals(trimLine)) {
						inMethod = false;
						
						// Check if this is one of the target methods
						String methodName = extractMethodName(methodBlock.toString());
						if (containsMethod(methodsToDraw, methodName)) {
							extractedCode.append(methodBlock);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return extractedCode.toString();
	}
	
	private String extractMethodName(String methodBlock) {
		// Extract the method name from the method block
		String[] lines = methodBlock.split("\\n");
		if (lines.length > 0) {
			String firstLine = lines[0].trim();
			String[] splitLine = firstLine.split(" ");
			if (splitLine.length > 1 && ".method".equals(splitLine[0])) {
				return splitLine[splitLine.length - 1];
			}
		}
		return "";
	}
	
	private boolean containsMethod(String[] methodsToDraw, String methodName) {
		// Check if the given array contains the target method name
		for (String method : methodsToDraw) {
			if (methodName.endsWith(method)) {
				return true;
			}
		}
		return false;
	}
	
}
