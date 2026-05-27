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

package modder.hub.dexeditor.smali;

import android.annotation.SuppressLint;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import io.github.rosemoe.sora.text.Content;

public class SmaliCursorUtils {
    // Author @developer-krushna
    // Optimized by Assistant to avoid expensive string splitting and fix crashes

    // Comprehensive patterns for labels and jumps
    private static final Pattern[] LABEL_PATTERNS = {
        Pattern.compile("^\\s*:([\\w.]+)"),                                    // Label declarations: :label_name
        Pattern.compile("(?:if-|goto|packed-switch|sparse-switch|fill-array-data|catch|catchall).*?:([\\w.]+)"), // Jumps/Catches/Data
        Pattern.compile("\\.try_(?:start|end)_([\\w.]+)"),                      // Try block boundaries
        Pattern.compile("0x[0-9a-f]+\\s*->\\s*:([\\w.]+)"),                     // Switch data targets
        Pattern.compile("\\.param\\s+.*?\\s*:([\\w.]+)")                       // Parameters with labels
    };

    public static class MethodInfo {
        public int startLine = -1;
        public int endLine = -1;
        public String name;
        public String signature;

        public String getDisplayName() {
            if (name == null) return null;
            return String.format(Locale.US, "[%d-%d] : %s", startLine + 1, endLine + 1, name);
        }
    }

    public static String getCurrentMethodOrFieldName(CharSequence text, int cursorLine) {
        MethodInfo method = getMethodInfo(text, cursorLine);
        if (method != null && method.name != null) {
            return method.getDisplayName() + "()";
        }
        
        // Then check fields
        return getCurrentFieldName(text, cursorLine);
    }

    public static MethodInfo getMethodInfo(CharSequence text, int cursorLine) {
        MethodInfo info = new MethodInfo();
        int lineCount = getLineCount(text);
        if (cursorLine < 0 || cursorLine >= lineCount) return null;

        // Search upwards for .method
        for (int i = cursorLine; i >= 0; i--) {
            String line = getLine(text, i);
            if (line == null) break;
            String trimmed = line.trim();
            if (trimmed.startsWith(".method ")) {
                info.startLine = i;
                info.signature = trimmed;
                
                String[] tokens = trimmed.split("\\s+");
                if (tokens.length > 0) {
                    String methodNamePart = tokens[tokens.length - 1];
                    int pIndex = methodNamePart.indexOf('(');
                    if (pIndex != -1) {
                        info.name = methodNamePart.substring(0, pIndex);
                    } else {
                        info.name = methodNamePart;
                    }
                }
                break;
            }
            // If we hit another .end method while going up, we are not inside a method
            if (i < cursorLine && trimmed.startsWith(".end method")) {
                return null;
            }
        }
        
        if (info.startLine == -1) return null;
        
        // Search downwards for .end method
        for (int j = info.startLine + 1; j < lineCount; j++) {
            String line = getLine(text, j);
            if (line == null) break;
            String trimmed = line.trim();
            if (trimmed.startsWith(".end method")) {
                info.endLine = j;
                // Cursor must be within [startLine, endLine]
                if (cursorLine <= j) {
                    return info;
                }
                break;
            }
            // Safety: if we hit another .method while going down, the method was never closed
            if (trimmed.startsWith(".method ")) break;
        }
        
        return null;
    }
    
    @SuppressLint("DefaultLocale")
    private static String getCurrentFieldName(CharSequence text, int cursorLine) {
        String line = getLine(text, cursorLine);
        if (line == null) return null;
        String trimmed = line.trim();
        if (trimmed.startsWith(".field ")) {
            int colonIndex = trimmed.indexOf(':');
            if (colonIndex != -1) {
                String fieldPart = trimmed.substring(0, colonIndex);
                String[] parts = fieldPart.split("\\s+");
                if (parts.length > 0) {
                    return String.format(Locale.US, "[%d] : %s", cursorLine + 1, parts[parts.length - 1]);
                }
            }
        }
        return null;
    }

    private static int getLineCount(CharSequence text) {
        if (text instanceof Content) {
            return ((Content) text).getLineCount();
        }
        int count = 1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\n') count++;
        }
        return count;
    }

    private static String getLine(CharSequence text, int index) {
        if (text instanceof Content) {
            Content content = (Content) text;
            if (index < 0 || index >= content.getLineCount()) return null;
            return content.getLineString(index);
        } else {
            // Efficient line extraction for any CharSequence
            int start = 0;
            int count = 0;
            while (count < index) {
                int next = -1;
                for (int i = start; i < text.length(); i++) {
                    if (text.charAt(i) == '\n') {
                        next = i;
                        break;
                    }
                }
                if (next == -1) return null;
                start = next + 1;
                count++;
            }
            int end = -1;
            for (int i = start; i < text.length(); i++) {
                if (text.charAt(i) == '\n') {
                    end = i;
                    break;
                }
            }
            if (end == -1) end = text.length();
            return text.subSequence(start, end).toString();
        }
    }
    
    @SuppressLint("DefaultLocale")
    public static List<String> extractAllLabelLines(CharSequence text, int cursorLine) {
        return extractAllLabelLines(text, getMethodInfo(text, cursorLine));
    }

    @SuppressLint("DefaultLocale")
    public static List<String> extractAllLabelLines(CharSequence text, MethodInfo method) {
        List<String> labelLines = new ArrayList<>();
        if (method == null) return labelLines;
        
        for (int lineNum = method.startLine; lineNum <= method.endLine; lineNum++) {
            String line = getLine(text, lineNum);
            if (line == null) break;
            String trimmed = line.trim();
            if (trimmed.isEmpty() || trimmed.startsWith("#") || trimmed.startsWith(".field") || trimmed.startsWith(".method")) {
                continue;
            }
            
            for (Pattern p : LABEL_PATTERNS) {
                if (p.matcher(trimmed).find()) {
                    labelLines.add(String.format(Locale.US, "[%d] %s", lineNum + 1, trimmed));
                    break;
                }
            }
        }
        return labelLines;
    }
}
