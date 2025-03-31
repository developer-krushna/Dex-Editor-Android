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

public class SmaliFieldAccessParser {
	
	// Map of primitive type codes to their corresponding suffixes for smali instructions
	private static final Map<String, String> TYPE_SUFFIXES = new HashMap<String, String>() {{
			put("Z", "boolean");  // boolean type
			put("B", "byte");     // byte type
			put("S", "short");    // short type
			put("C", "char");     // char type
			put("J", "wide");      // long type (uses wide suffix)
			put("F", "float");     // float type
			put("D", "wide");     // double type (uses wide suffix)
		}};
	
	private String className;  // The class name that contains the fields
	
	public SmaliFieldAccessParser(String className) {
		this.className = className;
	}
	
	// Generates smali code for getting a field value
	public String generateGetCode(String fieldSignature) {
		FieldInfo fieldInfo = parseFieldSignature(fieldSignature);
		return generateGetCode(fieldInfo);
	}
	
	// Generates smali code for setting a field value
	public String generatePutCode(String fieldSignature) {
		FieldInfo fieldInfo = parseFieldSignature(fieldSignature);
		return generatePutCode(fieldInfo);
	}
	
	// Generates the actual get instruction based on field info
	private String generateGetCode(FieldInfo fieldInfo) {
		String prefix = fieldInfo.isStatic ? "s" : "i";  // 's' for static, 'i' for instance
		String suffix = getTypeSuffix(fieldInfo.fieldType);  // Gets the type suffix
		
		// Formats the smali get instruction:
		// For static: "sget-type v0, Class->field:type"
		// For instance: "iget-type v0, v1, Class->field:type"
		return String.format("%sget%s v0%s, %s->%s:%s",
		prefix,
		suffix,
		fieldInfo.isStatic ? "" : ", v1",  // instance fields need source register
		className,
		fieldInfo.fieldName,
		fieldInfo.fieldType
		);
	}
	
	// Generates the actual put instruction based on field info
	private String generatePutCode(FieldInfo fieldInfo) {
		String prefix = fieldInfo.isStatic ? "s" : "i";  // 's' for static, 'i' for instance
		String suffix = getTypeSuffix(fieldInfo.fieldType);  // Gets the type suffix
		
		// Formats the smali put instruction similar to get
		return String.format("%sput%s v0%s, %s->%s:%s",
		prefix,
		suffix,
		fieldInfo.isStatic ? "" : ", v1",  // instance fields need destination register
		className,
		fieldInfo.fieldName,
		fieldInfo.fieldType
		);
	}
	
	// Gets the appropriate type suffix for smali instructions
	private static String getTypeSuffix(String fieldType) {
		if (TYPE_SUFFIXES.containsKey(fieldType)) {
			return "-" + TYPE_SUFFIXES.get(fieldType);  // Primitive types have specific suffixes
		}
		// Arrays and objects use "-object" suffix
		return fieldType.startsWith("[") || fieldType.startsWith("L") ? "-object" : "";
	}
	
	// Parses a field signature string into FieldInfo object
	private FieldInfo parseFieldSignature(String signature) {
		FieldInfo info = new FieldInfo();
		// Regex to extract field name and type from smali field declaration
		Matcher matcher = Pattern.compile(
		"\\.field\\s+(?:[^\\s:]+\\s+)*(?<name>[^\\s:]+):(?<type>\\S+)")
		.matcher(signature);
		
		if (matcher.find()) {
			info.isStatic = signature.contains(" static ");  // Check if field is static
			info.fieldName = matcher.group("name");        // Extract field name
			info.fieldType = matcher.group("type");        // Extract field type
		}
		return info;
	}
	
	// Simple container class for field information
	static class FieldInfo {
		boolean isStatic;    // Whether the field is static
		String fieldName;    // Name of the field
		String fieldType;    // Type descriptor of the field
	}
}
