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

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.github.rosemoe.sora.text.Content;

public class SmaliCursorUtils {
    // Author @developer-krushna
    // Optimized by Assistant to avoid expensive string splitting and fix crashes

    public static String getCurrentMethodOrFieldName(CharSequence text, int cursorLine) {
        // First check methods
        String methodInfo = getCurrentMethodName(text, cursorLine);
        if (methodInfo != null) return methodInfo + "()";
        
        // Then check fields
        return getCurrentFieldName(text, cursorLine);
    }
    
    @SuppressLint("DefaultLocale")
    private static String getCurrentMethodName(CharSequence text, int cursorLine) {
        int methodStart = -1;
        String methodName = null;
        
        // Search upwards for .method
        for (int i = cursorLine; i >= 0; i--) {
            String line = getLine(text, i);
            if (line == null) break;
            line = line.trim();
            if (line.startsWith(".method ")) {
                methodStart = i;
                String[] tokens = line.split("\\s+");
                if (tokens.length > 0) {
                    methodName = tokens[tokens.length - 1]; // Get last token
                    int pIndex = methodName.indexOf('(');
                    if (pIndex != -1) {
                        methodName = methodName.substring(0, pIndex);
                    }
                }
                break;
            }
        }
        
        if (methodStart == -1 || methodName == null) return null;
        
        // Check if cursor is within method bounds
        for (int i = methodStart + 1; ; i++) {
            String line = getLine(text, i);
            if (line == null) break;
            String trimmed = line.trim();
            if (trimmed.startsWith(".end method")) {
                if (cursorLine >= methodStart && cursorLine <= i) {
                    return String.format("[%d-%d] : %s", 
                    methodStart + 1, i + 1, methodName);
                }
                break;
            }
            // Safety break if it's taking too long or we reached next method without .end method
            if (trimmed.startsWith(".method ") && i > methodStart) break;
        }
        return null;
    }
    
    @SuppressLint("DefaultLocale")
    private static String getCurrentFieldName(CharSequence text, int cursorLine) {
        String line = getLine(text, cursorLine);
        if (line == null) return null;
        String trimmed = line.trim();
        if (trimmed.startsWith(".field ")) {
            String[] parts = trimmed.split("\\s+");
            for (String part : parts) {
                if (part.contains(":")) {
                    return String.format("[%d] : %s", 
                    cursorLine + 1, part.substring(0, part.indexOf(':')));
                }
            }
        }
        return null;
    }

    private static String getLine(CharSequence text, int index) {
        if (text instanceof Content) {
            Content content = (Content) text;
            if (index < 0 || index >= content.getLineCount()) return null;
            return content.getLineString(index);
        } else {
            // Fallback for non-Content CharSequence (less efficient but safer)
            String[] lines = text.toString().split("\n");
            if (index < 0 || index >= lines.length) return null;
            return lines[index];
        }
    }
    
    @SuppressLint("DefaultLocale")
    public static List<String> extractAllLabelLines(CharSequence text, int cursorLine) {
        List<String> labelLines = new ArrayList<>();
        
        MethodBoundary method = findMethodBoundary(text, cursorLine);
        if (method == null) return labelLines;
        
        // List of patterns
        Pattern[] patterns = {
            Pattern.compile("if-(?:eq|ne|lt|gt|le|ge|eqz|nez|ltz|gtz|lez|gez)\\b.*?:(\\w+)"),
            Pattern.compile("goto\\s+:([^\\s,]+)"),
            Pattern.compile("(?:packed|sparse)-switch\\b.*?:(\\w+)"),
            Pattern.compile("(?:catch|catchall).*?\\{:(\\w+)\\s+\\.\\.\\s+:(\\w+)\\}.*?:(\\w+)"),
            Pattern.compile("\\.try_(?:start|end)_(\\w+)"),
            Pattern.compile("0x[0-9a-f]+\\s*->\\s*:(\\w+)"),
            Pattern.compile("^\\s*:(\\w+)")
        };
        
        for (int lineNum = method.startLine; lineNum <= method.endLine; lineNum++) {
            String line = getLine(text, lineNum);
            if (line == null) break;
            String trimmed = line.trim();
            if (trimmed.isEmpty() || trimmed.startsWith("#") || trimmed.startsWith(".field")) {
                continue;
            }
            
            for (Pattern p : patterns) {
                if (p.matcher(trimmed).find()) {
                    labelLines.add(String.format("[%d] %s", lineNum + 1, trimmed));
                    break;
                }
            }
        }
        return labelLines;
    }
    
    private static class MethodBoundary {
        int startLine;
        int endLine;
    }
    
    private static MethodBoundary findMethodBoundary(CharSequence text, int cursorLine) {
        MethodBoundary boundary = new MethodBoundary();
        boundary.startLine = -1;
        
        for (int i = cursorLine; i >= 0; i--) {
            String line = getLine(text, i);
            if (line == null) break;
            if (line.trim().startsWith(".method ")) {
                boundary.startLine = i;
                break;
            }
        }
        
        if (boundary.startLine == -1) return null;
        
        for (int i = boundary.startLine + 1; ; i++) {
            String line = getLine(text, i);
            if (line == null) break;
            if (line.trim().startsWith(".end method")) {
                boundary.endLine = i;
                return boundary;
            }
            if (line.trim().startsWith(".method ")) break;
        }
        return null;
    }
}
