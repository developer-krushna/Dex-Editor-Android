/*
 * Dex-Editor-Android an Advanced Dex Editor for Android
 * Copyright 2024-26, developer-krushna
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

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

// Author - @developer-krushna
public class EditorPositionManager {
    private static final String FILE_NAME = "editor_positions.json";
    private static EditorPositionManager instance;
    private final File file;
    private Map<String, Position> positions = new HashMap<>();
    private final Gson gson = new Gson();

    public static class Position {
        public int lineno;
        public int column;

        public Position(int lineno, int column) {
            this.lineno = lineno;
            this.column = column;
        }
    }

    private EditorPositionManager(Context context) {
        file = new File(context.getFilesDir(), FILE_NAME);
        load();
    }

    public static synchronized EditorPositionManager getInstance(Context context) {
        if (instance == null) {
            instance = new EditorPositionManager(context.getApplicationContext());
        }
        return instance;
    }

    private void load() {
        if (file.exists()) {
            String json = FileUtil.readFile(file.getAbsolutePath());
            if (json != null && !json.isEmpty()) {
                try {
                    Type type = new TypeToken<Map<String, Position>>() {}.getType();
                    Map<String, Position> loaded = gson.fromJson(json, type);
                    if (loaded != null) {
                        positions = loaded;
                    }
                } catch (Exception e) {
                    positions = new HashMap<>();
                }
            }
        }
    }

    public void savePosition(String className, int lineno, int column) {
        positions.put(className, new Position(lineno, column));
        persist();
    }

    private void persist() {
        String json = gson.toJson(positions);
        FileUtil.writeFile(file.getAbsolutePath(), json);
    }

    public Position getPosition(String className) {
        return positions.get(className);
    }

    public void removePosition(String className) {
        if (positions.containsKey(className)) {
            positions.remove(className);
            persist();
        }
    }

    public void clear() {
        if (file.exists()) {
            file.delete();
        }
        positions.clear();
    }
}
