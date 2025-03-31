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

import java.util.*;
import java.util.regex.*;

/*
Author @developer-krushna
 Code comment and improvement by ChatGPT
*/

// This is an exprimental project . Error may show while generating 

public class SmaliMethodInvokeParser {
	
	private static final Set<String> PRIMITIVE_TYPES = new HashSet<>(Arrays.asList(
	"Z", "B", "S", "C", "I", "J", "F", "D", "V"
	));
	
	private String className;
	
	public SmaliMethodInvokeParser(String className) {
		this.className = className;
	}
	
	public String generateInvokeCode(String methodSignature, String resultRegister) {
		MethodInfo methodInfo = parseMethodSignature(methodSignature);
		return buildInvokeInstruction(methodInfo, resultRegister);
	}
	
	private String buildInvokeInstruction(MethodInfo methodInfo, String resultRegister) {
		String invokeType = determineInvokeType(methodInfo);
		
		// Build register list
		String registers = buildRegisterList(methodInfo, resultRegister);
		
		// Build parameter types
		String paramTypes = buildParameterTypes(methodInfo);
		
		// Build full instruction
		String instruction = invokeType + " {" + registers + "}, " + 
		className + "->" + methodInfo.methodName + 
		"(" + paramTypes + ")" + methodInfo.returnType;
		
		// Add move-result if needed
		if (!methodInfo.returnType.equals("V")) {
			String moveResult = getMoveResultInstruction(methodInfo.returnType, resultRegister);
			instruction += "\n" + moveResult;
		}
		
		return instruction;
	}
	
	private String buildRegisterList(MethodInfo methodInfo, String resultRegister) {
		StringBuilder sb = new StringBuilder();
		
		// For non-static methods, include the object register
		if (!methodInfo.isStatic) {
			sb.append(resultRegister);
			if (methodInfo.parameterTypes.size() > 0) {
				sb.append(", ");
			}
		}
		
		// Add parameter registers
		for (int i = 0; i < methodInfo.parameterTypes.size(); i++) {
			if (i > 0) {
				sb.append(", ");
			}
			sb.append("v").append(i + 1); // Parameters start from v1
		}
		
		return sb.toString();
	}
	
	private String buildParameterTypes(MethodInfo methodInfo) {
		StringBuilder sb = new StringBuilder();
		for (String param : methodInfo.parameterTypes) {
			sb.append(param);
		}
		return sb.toString();
	}
	
	private String getMoveResultInstruction(String returnType, String register) {
		if (isPrimitive(returnType)) {
			if (returnType.equals("J") || returnType.equals("D")) {
				return "move-result-wide " + register;
			}
			return "move-result " + register;
		}
		return "move-result-object " + register;
	}
	
	private boolean isPrimitive(String type) {
		return PRIMITIVE_TYPES.contains(type) || 
		(type.startsWith("[") && PRIMITIVE_TYPES.contains(type.substring(1)));
	}
	
	private String determineInvokeType(MethodInfo methodInfo) {
		if (methodInfo.methodName.equals("<init>")) {
			return "invoke-direct";
		} else if (methodInfo.isStatic) {
			return "invoke-static";
		} else if (methodInfo.isPrivate || methodInfo.isConstructor) {
			return "invoke-direct";
		} else {
			return "invoke-virtual";
		}
	}
	
	private MethodInfo parseMethodSignature(String signature) {
		MethodInfo info = new MethodInfo();
		
		// Improved regex to better capture all modifiers
		Pattern pattern = Pattern.compile(
		"\\.method\\s+(?<modifiers>(?:[a-z]+\\s+)*)(?<name>[^\\s(]+)\\((?<params>[^)]*)\\)(?<return>\\S+)");
		Matcher matcher = pattern.matcher(signature);
		
		if (matcher.find()) {
			// Parse modifiers
			String modifiers = matcher.group("modifiers");
			if (modifiers != null) {
				String[] modifierParts = modifiers.trim().split("\\s+");
				for (String modifier : modifierParts) {
					switch (modifier) {
						case "static":
						info.isStatic = true;
						break;
						case "protected":
						info.isProtected = true;
						break;
						case "private":
						info.isPrivate = true;
						break;
						case "constructor":
						info.isConstructor = true;
						break;
						case "native":
						info.isNative = true;
						break;
					}
				}
			}
			
			// Parse method name
			info.methodName = matcher.group("name");
			
			// Parse parameters
			String params = matcher.group("params");
			if (!params.isEmpty()) {
				info.parameterTypes = parseParameterTypes(params);
			}
			
			// Parse return type
			info.returnType = matcher.group("return");
		}
		
		return info;
	}
	
	private List<String> parseParameterTypes(String params) {
		List<String> types = new ArrayList<>();
		int pos = 0;
		while (pos < params.length()) {
			char c = params.charAt(pos);
			if (c == 'L') {
				// Object type
				int end = params.indexOf(';', pos);
				if (end == -1) end = params.length();
				types.add(params.substring(pos, end + 1));
				pos = end + 1;
			} else if (c == '[') {
				// Array type
				int baseStart = pos;
				while (pos < params.length() && params.charAt(pos) == '[') {
					pos++;
				}
				if (pos < params.length() && params.charAt(pos) == 'L') {
					int end = params.indexOf(';', pos);
					if (end == -1) end = params.length();
					types.add(params.substring(baseStart, end + 1));
					pos = end + 1;
				} else if (pos < params.length()) {
					types.add(params.substring(baseStart, pos + 1));
					pos++;
				}
			} else {
				// Primitive type
				types.add(String.valueOf(c));
				pos++;
			}
		}
		return types;
	}
	
	static class MethodInfo {
		boolean isStatic;
		boolean isProtected;
		boolean isPrivate;
		boolean isConstructor;
		boolean isNative;
		String methodName;
		List<String> parameterTypes = new ArrayList<>();
		String returnType;
	}
	
}
