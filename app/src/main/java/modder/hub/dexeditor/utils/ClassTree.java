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

import android.annotation.SuppressLint;

import com.android.tools.smali.baksmali.Adaptors.ClassDefinition;
import com.android.tools.smali.baksmali.BaksmaliOptions;
import com.android.tools.smali.baksmali.formatter.BaksmaliWriter;
import com.android.tools.smali.dexlib2.Opcodes;
import com.android.tools.smali.dexlib2.dexbacked.DexBackedDexFile;
import com.android.tools.smali.dexlib2.iface.Annotation;
import com.android.tools.smali.dexlib2.iface.AnnotationElement;
import com.android.tools.smali.dexlib2.iface.ClassDef;
import com.android.tools.smali.dexlib2.iface.Field;
import com.android.tools.smali.dexlib2.iface.Method;
import com.android.tools.smali.dexlib2.iface.MethodImplementation;
import com.android.tools.smali.dexlib2.iface.instruction.Instruction;
import com.android.tools.smali.dexlib2.iface.instruction.ReferenceInstruction;
import com.android.tools.smali.dexlib2.iface.reference.Reference;
import com.android.tools.smali.dexlib2.iface.reference.StringReference;
import com.android.tools.smali.dexlib2.iface.value.AnnotationEncodedValue;
import com.android.tools.smali.dexlib2.iface.value.ArrayEncodedValue;
import com.android.tools.smali.dexlib2.iface.value.EncodedValue;
import com.android.tools.smali.dexlib2.iface.value.StringEncodedValue;
import com.android.tools.smali.smali.SmaliOptions;
import com.android.tools.smali.smali2.Smali;
import com.android.tools.smali.dexlib2.util.DexUtil;
import com.android.tools.smali.dexlib2.writer.builder.DexBuilder;
import com.android.tools.smali.dexlib2.writer.io.MemoryDataStore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import modder.hub.dexeditor.activity.DexEditorActivity;
import modder.hub.dexeditor.model.TreeNode;

public class ClassTree {
	
	/*
	Author @developer-krushna
	Orginally replicate from Flying-Yu AE Manager on github
	*/

    /*
     * There are some major advancement and enhancement are made by me. From loading of multi dex to
     faster batch class deletion and even really  fatser dex compilatin .

     * There is so many usefull tricks for advancing your smali assembly and disaembly knowledge
     * Here i have made significant improvement in loading of dexes
     */


    private String ALL_CLASSES_JSON;
    private String DELETED_CLASSES_JSON;
    private String EDITED_CLASSES_JSON;
    private String workDir;
    private final Map<String, List<String>> removedClassMap = new HashMap<>();
    private final Map<String, java.util.HashSet<String>> editedClassMap = new HashMap<>();
    private final Map<String, String> pendingSmaliMap = new HashMap<>();
    public Tree tree;
    public HashMap<String, ClassDef> classMap;
    public List<DexBackedDexFile> dexFiles;
    public DexBackedDexFile dexFile;
    public String Path;
    public ClassDef curClassDef;
    public int dep;
    public Stack<String> path;
    public String curFile;
    public List<ClassDef> classDefList = new ArrayList<ClassDef>();
    public List<String> paths;
    public int dexVersion;
    Map<String, List<String>> dexClassMap = new LinkedHashMap<>();
    byte[] data;
    byte[] input;

    private Map<String, java.util.HashSet<String>> deletedClassJson = new HashMap<>();
    private final Map<String, String> typeToDexMap = new HashMap<>();

    public static class CompilationOptions {
        public String dexVersion = "Keep the same";
        public boolean removeAllDebug = false;
        public boolean removeDebugSource = false;
        public boolean removeDebugLine = false;
        public boolean removeDebugParam = false;
        public boolean removeDebugPrologue = false;
        public boolean removeDebugLocal = false;
    }

    private CompilationOptions compilationOptions = new CompilationOptions();

    public void setCompilationOptions(CompilationOptions options) {
        this.compilationOptions = options;
    }

    public ClassTree(List<String> mPaths, String cacheDir) throws Exception {
        this.paths = mPaths;
        this.workDir = cacheDir;
        initPaths();
        initMultiDex();
        loadDeletedClasses();
    }

    private void initPaths() {
        File dir = new File(workDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        ALL_CLASSES_JSON = new File(dir, "allclasses.json").getAbsolutePath();
        DELETED_CLASSES_JSON = new File(dir, "deletedclasses.json").getAbsolutePath();
        EDITED_CLASSES_JSON = new File(dir, "editedclasses.json").getAbsolutePath();
    }

    @Deprecated
    private void initDex() throws Exception {
        byte[] read = read(this.Path);
        this.input = read;
        int verifyDexHeader = DexUtil.verifyDexHeader(read, 0);
        this.dexVersion = verifyDexHeader;
        dexFile = DexBackedDexFile.fromInputStream(Opcodes.forDexVersion(verifyDexHeader), new ByteArrayInputStream(input));
        classDefList.addAll(dexFile.getClasses());
        initClassMap();
    }

    private void initMultiDex() throws Exception {
        dexFiles = new ArrayList<>();
        classDefList.clear();
        typeToDexMap.clear();

        for (int i = 0; i < paths.size(); i++) {
            String path = paths.get(i);
            byte[] read = read(path);
            int verifyDexHeader = DexUtil.verifyDexHeader(read, 0);
            this.dexVersion = verifyDexHeader;
            DexBackedDexFile file = DexBackedDexFile.fromInputStream(Opcodes.forDexVersion(verifyDexHeader), new ByteArrayInputStream(read));

            dexFiles.add(file);
            classDefList.addAll(file.getClasses());

            List<String> classNames = new ArrayList<>();
            String fileName = new File(path).getName();
            for (ClassDef classDef : file.getClasses()) {
                String type = classDef.getType();
                classNames.add(type);
                typeToDexMap.put(type, fileName);
            }

            dexClassMap.put(fileName, classNames);
        }

        saveAllClassesJson(); // save all classes as json
        initClassMap();
    }

    // save all classes during loading of initial dexes
    private void saveAllClassesJson() throws IOException {
        File file = new File(ALL_CLASSES_JSON);
        if (file.exists()) {
            file.delete();
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(dexClassMap);
        FileWriter writer = new FileWriter(ALL_CLASSES_JSON);
        writer.write(json);
        writer.close();
    }

    // loading the deleted classes from JSON list
    private void loadDeletedClasses() {
        try {
            File file = new File(DELETED_CLASSES_JSON);
            if (file.exists()) {
                String json = new String(read(DELETED_CLASSES_JSON));
                Map<String, List<String>> loaded = new Gson().fromJson(json, new TypeToken<Map<String, List<String>>>() {}.getType());
                deletedClassJson.clear();
                for (Entry<String, List<String>> entry : loaded.entrySet()) {
                    deletedClassJson.put(entry.getKey(), new java.util.HashSet<>(entry.getValue()));
                }
            } else {
                deletedClassJson = new HashMap<>();
            }
        } catch (Exception e) {
            deletedClassJson = new HashMap<>();
        }
    }

    // saving deleted classes as JSON so that will be excluded during the dex compilation
    private void saveDeletedClasses() {
        try {
            File file = new File(DELETED_CLASSES_JSON);
            if (file.exists()) {
                file.delete();
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            // Convert HashSet to List for JSON
            Map<String, List<String>> toSave = new HashMap<>();
            for (Entry<String, java.util.HashSet<String>> entry : deletedClassJson.entrySet()) {
                toSave.put(entry.getKey(), new ArrayList<>(entry.getValue()));
            }
            String json = gson.toJson(toSave);
            FileWriter writer = new FileWriter(DELETED_CLASSES_JSON);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initClassMap() {
        if (classMap == null) classMap = new HashMap<>();
        else classMap.clear();

        for (ClassDef classDef : classDefList) {
            String type = classDef.getType();
            type = type.substring(1, type.length() - 1);
            if (!isClassDeleted(type)) {
                classMap.put(type, classDef);
            }
        }
        tree = new Tree(classMap);
    }

    // checking if the class is beigh deleted usefull in case of searching
    private boolean isClassDeleted(String type) {
        String fileName = typeToDexMap.get("L" + type + ";");
        if (fileName != null) {
            java.util.HashSet<String> deleted = deletedClassJson.get(fileName);
            return deleted != null && deleted.contains(type);
        }
        return false;
    }

    // removal of batch or single classes from tree node
    public void removeClasses(List<String> classNames) {
        if (classNames == null || classNames.isEmpty()) return;

        for (String name : classNames) {
            if (name.endsWith("/")) {
                // It's a folder, remove all classes starting with this path
                Iterator<String> it = classMap.keySet().iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    if (key.startsWith(name)) {
                        recordRemovedClass(key);
                        it.remove();
                    }
                }
            } else {
                // It's a single class
                if (classMap.remove(name) != null) {
                    recordRemovedClass(name);
                }
            }
        }

        tree = new Tree(classMap);
        saveDeletedClasses();
    }

    // remove single class only
    public void removeClass(String className) {
        removeClasses(Collections.singletonList(className));
    }

    // the remove classes will be recorded during batch
    private void recordRemovedClass(String type) {
        String fileName = typeToDexMap.get("L" + type + ";");
        if (fileName != null) {
            HashSet<String> deleted = deletedClassJson.get(fileName);
            if (deleted == null) {
                deleted = new HashSet<>();
                deletedClassJson.put(fileName, deleted);
            }
            deleted.add(type);
        }
    }

    // save smali according to the class and its content
    // its just a mapping way preserve smali data but it's not a good way and mt manager never do like this
    public void saveSmali(String type, String smali) {
        pendingSmaliMap.put(type, smali);
        recordEditedClass(type);
        DexEditorActivity.isChanged = true;
        DexEditorActivity.isSaved = false;
    }

    // save class def to the main node o the dex and record the changes classes
    public void saveClassDef(ClassDef classDef) throws Exception {
        String type = classDef.getType().substring(1, classDef.getType().length() - 1);
        pendingSmaliMap.remove(type);

        // Update classMap
        classMap.put(type, classDef);

        // Update classDefList
        for (int i = 0; i < classDefList.size(); i++) {
            ClassDef existingDef = classDefList.get(i);
            String existingType = existingDef.getType().substring(1, existingDef.getType().length() - 1);
            if (existingType.equals(type)) {
                classDefList.set(i, classDef);
                break;
            }
        }

        recordEditedClass(type);
        DexEditorActivity.isChanged = true;
        DexEditorActivity.isSaved = false;
    }

    public String getSmaliByType(ClassDef classDef) throws Exception {
        String type = classDef.getType();
        String typeKey = type.substring(1, type.length() - 1);
        String smali;
        
        // Check if we have unsaved smali in memory
        // MEMEORY WORKS are really not good especially for android. MT Manager never do this it save all as files and retrive them from
        // files only and there will be IO exception and even background kill
        if (pendingSmaliMap.containsKey(typeKey)) {
            smali = pendingSmaliMap.get(typeKey);
        } else {
            smali = getPureSmaliFromClassDef(classDef);
        }

        // We strip any existing header to avoid "# classes.dex" appearing multiple times
        // This usually happens during search and replace operations
        if (smali != null && smali.trim().startsWith("# ")) {
            smali = smali.replaceFirst("(?s)^#.*?\\n\\n", "");
        }

        String dexFileName = findDexFileNameForClass(classDef);
        return "# " + dexFileName + "\n\n" + smali;
    }

    // This returns the smali code without any informative headers
    public String getPureSmaliFromClassDef(ClassDef classDef) throws Exception {
        StringWriter stringWriter = new StringWriter();
        BaksmaliWriter baksmaliWriter = new BaksmaliWriter(stringWriter);
        new ClassDefinition(new BaksmaliOptions(), classDef).writeTo(baksmaliWriter);
        baksmaliWriter.close();
        return stringWriter.toString();
    }

    public String getSmaliFromClassDef(ClassDef classDef) throws Exception {
        return getSmaliByType(classDef);
    }

    private String findDexFileNameForClass(ClassDef classDef) {
        String fileName = typeToDexMap.get(classDef.getType());
        return fileName != null ? fileName : "unknown.dex";
    }

    private void recordEditedClass(String type) {
        String fileName = typeToDexMap.get("L" + type + ";");
        if (fileName != null) {
            java.util.HashSet<String> edited = editedClassMap.get(fileName);
            if (edited == null) {
                edited = new java.util.HashSet<>();
                editedClassMap.put(fileName, edited);
            }
            edited.add(type);
        }
    }

    private class DebugInfoStripper implements com.android.tools.smali.dexlib2.iface.ClassDef {
        private final ClassDef delegate;
        private final CompilationOptions options;

        public DebugInfoStripper(ClassDef delegate, CompilationOptions options) {
            this.delegate = delegate;
            this.options = options;
        }

        @Override @javax.annotation.Nonnull public String getType() { return delegate.getType(); }
        @Override public int getAccessFlags() { return delegate.getAccessFlags(); }
        @Override @javax.annotation.Nullable public String getSuperclass() { return delegate.getSuperclass(); }
        @Override @javax.annotation.Nonnull public List<String> getInterfaces() { return delegate.getInterfaces(); }
        @Override @javax.annotation.Nullable public String getSourceFile() { return options.removeDebugSource || options.removeAllDebug ? null : delegate.getSourceFile(); }
        @Override @javax.annotation.Nonnull public Set<? extends com.android.tools.smali.dexlib2.iface.Annotation> getAnnotations() { return delegate.getAnnotations(); }
        @Override @javax.annotation.Nonnull public Iterable<? extends com.android.tools.smali.dexlib2.iface.Field> getStaticFields() { return delegate.getStaticFields(); }
        @Override @javax.annotation.Nonnull public Iterable<? extends com.android.tools.smali.dexlib2.iface.Field> getInstanceFields() { return delegate.getInstanceFields(); }
        @Override @javax.annotation.Nonnull public Iterable<? extends com.android.tools.smali.dexlib2.iface.Field> getFields() { return delegate.getFields(); }

        @Override @javax.annotation.Nonnull public Iterable<? extends com.android.tools.smali.dexlib2.iface.Method> getDirectMethods() {
            return wrapMethods(delegate.getDirectMethods());
        }

        @Override @javax.annotation.Nonnull public Iterable<? extends com.android.tools.smali.dexlib2.iface.Method> getVirtualMethods() {
            return wrapMethods(delegate.getVirtualMethods());
        }

        @Override @javax.annotation.Nonnull public Iterable<? extends com.android.tools.smali.dexlib2.iface.Method> getMethods() {
            return wrapMethods(delegate.getMethods());
        }

        @Override public int compareTo(@javax.annotation.Nonnull CharSequence o) { return delegate.compareTo(o); }
        @Override public void validateReference() throws com.android.tools.smali.dexlib2.iface.reference.Reference.InvalidReferenceException { delegate.validateReference(); }
        @Override public int length() { return delegate.length(); }
        @Override public char charAt(int index) { return delegate.charAt(index); }
        @Override public CharSequence subSequence(int start, int end) { return delegate.subSequence(start, end); }
        @Override @javax.annotation.Nonnull public String toString() { return delegate.toString(); }

        private Iterable<? extends com.android.tools.smali.dexlib2.iface.Method> wrapMethods(Iterable<? extends com.android.tools.smali.dexlib2.iface.Method> methods) {
            List<com.android.tools.smali.dexlib2.iface.Method> wrapped = new ArrayList<>();
            for (com.android.tools.smali.dexlib2.iface.Method method : methods) {
                wrapped.add(new MethodStripper(method, options));
            }
            return wrapped;
        }
    }

    private class MethodStripper implements com.android.tools.smali.dexlib2.iface.Method {
        private final com.android.tools.smali.dexlib2.iface.Method delegate;
        private final CompilationOptions options;

        public MethodStripper(com.android.tools.smali.dexlib2.iface.Method delegate, CompilationOptions options) {
            this.delegate = delegate;
            this.options = options;
        }

        @Override @javax.annotation.Nonnull public String getDefiningClass() { return delegate.getDefiningClass(); }
        @Override @javax.annotation.Nonnull public String getName() { return delegate.getName(); }
        @Override @javax.annotation.Nonnull public List<? extends com.android.tools.smali.dexlib2.iface.MethodParameter> getParameters() { 
            if (options.removeDebugParam || options.removeAllDebug) {
                List<com.android.tools.smali.dexlib2.iface.MethodParameter> params = new ArrayList<>();
                for (com.android.tools.smali.dexlib2.iface.MethodParameter p : delegate.getParameters()) {
                    params.add(new com.android.tools.smali.dexlib2.immutable.ImmutableMethodParameter(p.getType(), null, null));
                }
                return params;
            }
            return delegate.getParameters(); 
        }
        @Override @javax.annotation.Nonnull public List<? extends CharSequence> getParameterTypes() { return delegate.getParameterTypes(); }
        @Override @javax.annotation.Nonnull public String getReturnType() { return delegate.getReturnType(); }
        @Override public int getAccessFlags() { return delegate.getAccessFlags(); }
        @Override @javax.annotation.Nonnull public Set<? extends com.android.tools.smali.dexlib2.iface.Annotation> getAnnotations() { return delegate.getAnnotations(); }
        @Override @javax.annotation.Nonnull public Set<com.android.tools.smali.dexlib2.HiddenApiRestriction> getHiddenApiRestrictions() { return delegate.getHiddenApiRestrictions(); }
        @Override @javax.annotation.Nullable public com.android.tools.smali.dexlib2.iface.MethodImplementation getImplementation() {
            com.android.tools.smali.dexlib2.iface.MethodImplementation impl = delegate.getImplementation();
            if (impl == null) return null;
            return new MethodImplementationStripper(impl, options);
        }
        @Override public int compareTo(@javax.annotation.Nonnull com.android.tools.smali.dexlib2.iface.reference.MethodReference o) { return delegate.compareTo(o); }
        @Override public void validateReference() throws com.android.tools.smali.dexlib2.iface.reference.Reference.InvalidReferenceException { delegate.validateReference(); }
    }

    private class MethodImplementationStripper implements com.android.tools.smali.dexlib2.iface.MethodImplementation {
        private final com.android.tools.smali.dexlib2.iface.MethodImplementation delegate;
        private final CompilationOptions options;

        public MethodImplementationStripper(com.android.tools.smali.dexlib2.iface.MethodImplementation delegate, CompilationOptions options) {
            this.delegate = delegate;
            this.options = options;
        }

        @Override public int getRegisterCount() { return delegate.getRegisterCount(); }
        @Override public Iterable<? extends com.android.tools.smali.dexlib2.iface.instruction.Instruction> getInstructions() { return delegate.getInstructions(); }
        @Override public List<? extends com.android.tools.smali.dexlib2.iface.TryBlock<? extends com.android.tools.smali.dexlib2.iface.ExceptionHandler>> getTryBlocks() { return delegate.getTryBlocks(); }

        @Override public Iterable<? extends com.android.tools.smali.dexlib2.iface.debug.DebugItem> getDebugItems() {
            if (options.removeAllDebug) return new ArrayList<>();
            List<com.android.tools.smali.dexlib2.iface.debug.DebugItem> filtered = new ArrayList<>();
            for (com.android.tools.smali.dexlib2.iface.debug.DebugItem item : delegate.getDebugItems()) {
                boolean remove = false;
                switch (item.getDebugItemType()) {
                    case com.android.tools.smali.dexlib2.DebugItemType.SET_SOURCE_FILE:
                        if (options.removeDebugSource) remove = true;
                        break;
                    case com.android.tools.smali.dexlib2.DebugItemType.LINE_NUMBER:
                        if (options.removeDebugLine) remove = true;
                        break;
                    case com.android.tools.smali.dexlib2.DebugItemType.PROLOGUE_END:
                        if (options.removeDebugPrologue) remove = true;
                        break;
                    case com.android.tools.smali.dexlib2.DebugItemType.EPILOGUE_BEGIN:
                        // Prologue option usually covers epilogue too in some tools, or we can map it
                        if (options.removeDebugPrologue) remove = true;
                        break;
                    case com.android.tools.smali.dexlib2.DebugItemType.START_LOCAL:
                    case com.android.tools.smali.dexlib2.DebugItemType.END_LOCAL:
                    case com.android.tools.smali.dexlib2.DebugItemType.RESTART_LOCAL:
                    case com.android.tools.smali.dexlib2.DebugItemType.START_LOCAL_EXTENDED:
                        if (options.removeDebugLocal) remove = true;
                        break;
                }
                if (!remove) filtered.add(item);
            }
            // Param debug info is usually handled via method parameters if we want to strip names,
            // but in debug_info_item they are also present. 
            // dexlib2 doesn't easily expose the parameter names list from debug_info_item here.
            return filtered;
        }
    }

    public void saveAllDexFiles(DexSaveProgress dexSaveProgress) throws Exception {
        if (dexClassMap == null || dexClassMap.isEmpty()) return;

        int total = dexClassMap.size();
        int current = 1;

        int targetDexVersion = this.dexVersion;
        if (!compilationOptions.dexVersion.equals("Keep the same")) {
            try {
                targetDexVersion = Integer.parseInt(compilationOptions.dexVersion);
            } catch (Exception ignored) {}
        }

        final int finalTargetDexVersion = targetDexVersion;
        Opcodes opcodes = Opcodes.forDexVersion(targetDexVersion);

        boolean forceCompileAll = compilationOptions.removeAllDebug || compilationOptions.removeDebugSource || 
                                 compilationOptions.removeDebugLine || compilationOptions.removeDebugParam || 
                                 compilationOptions.removeDebugPrologue || compilationOptions.removeDebugLocal ||
                                 !compilationOptions.dexVersion.equals("Keep the same");

        int numThreads = Runtime.getRuntime().availableProcessors();

        for (Entry<String, List<String>> entry : dexClassMap.entrySet()) {
            String fileName = entry.getKey();
            dexSaveProgress.onTitle(fileName + " (" + current + "/" + total + ")");

            boolean isTouched = forceCompileAll || deletedClassJson.containsKey(fileName) || editedClassMap.containsKey(fileName);
            if (!isTouched) {
                current++;
                continue;
            }

            DexBuilder dexBuilder = new DexBuilder(opcodes);
            dexBuilder.setIgnoreMethodAndFieldError(true);

            List<String> classNames = entry.getValue();
            int classCount = classNames.size();
            AtomicInteger processed = new AtomicInteger(0);
            ExecutorService executor = Executors.newFixedThreadPool(numThreads);
            
            final Exception[] threadException = {null};

            for (String rawType : classNames) {
                if (threadException[0] != null) break;
                
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final String type = rawType.substring(1, rawType.length() - 1);

                            if (deletedClassJson.containsKey(fileName) && deletedClassJson.get(fileName).contains(type)) {
                                int p = processed.incrementAndGet();
                                if (p % 100 == 0 || p == classCount) dexSaveProgress.onProgress(p, classCount);
                                return;
                            }

                            if (pendingSmaliMap.containsKey(type)) {
                                // Message update is tricky in parallel, but we can do it occasionally
                                if (processed.get() % 50 == 0) dexSaveProgress.onMessage("Assembling " + type + "...");
                                
                                try {
                                    final ClassDef assembledDef = Smali.assemble(pendingSmaliMap.get(type), new SmaliOptions(), finalTargetDexVersion);
                                    synchronized (classMap) {
                                        classMap.put(type, assembledDef);
                                    }
                                    synchronized (pendingSmaliMap) {
                                        pendingSmaliMap.remove(type);
                                    }
                                    
                                    // Update classDefList (sequential or atomic)
                                    synchronized (classDefList) {
                                        for (int j = 0; j < classDefList.size(); j++) {
                                            if (classDefList.get(j).getType().equals(rawType)) {
                                                classDefList.set(j, assembledDef);
                                                break;
                                            }
                                        }
                                    }
                                } catch (final Exception e) {
                                    synchronized (threadException) { threadException[0] = new Exception("COMPILE_ERROR:" + type + ":" + e.getMessage()); }
                                }
                            }

                            if (threadException[0] != null) return;

                            final ClassDef classDef;
                            synchronized (classMap) {
                                classDef = classMap.get(type);
                            }
                            
                            if (classDef != null) {
                                // Apply compilation options
                                ClassDef strippedDef = classDef;
                                if (compilationOptions.removeAllDebug || compilationOptions.removeDebugSource || 
                                    compilationOptions.removeDebugLine || compilationOptions.removeDebugParam || 
                                    compilationOptions.removeDebugPrologue || compilationOptions.removeDebugLocal) {
                                    strippedDef = new DebugInfoStripper(classDef, compilationOptions);
                                }
                                
                                dexBuilder.internClassDef(strippedDef);
                            }
                            
                            int p = processed.incrementAndGet();
                            if (p % 100 == 0 || p == classCount) dexSaveProgress.onProgress(p, classCount);
                        } catch (final Exception e) {
                            synchronized (threadException) { threadException[0] = e; }
                        }
                    }
                });
            }

            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.HOURS);
            
            if (threadException[0] != null) {
                throw threadException[0];
            }

            dexSaveProgress.onMessage("Writing file...");
            try {
                // Estimate size for buffer to avoid repeated allocations
                MemoryDataStore memoryDataStore = new MemoryDataStore(classCount * 512); 
                dexBuilder.writeTo(memoryDataStore);
                byte[] result = Arrays.copyOf(memoryDataStore.getBuffer(), memoryDataStore.getSize());

                String outputDir = (paths != null && !paths.isEmpty()) ? new File(paths.get(0)).getParent() : "/sdcard";
                File outFile = new File(outputDir, fileName);
                File bakFile = new File(outFile.getAbsolutePath() + ".bak");

                if (outFile.exists()) {
                    // Fast backup using NIO or simple copy
                    FileUtil.copyFile(outFile.getAbsolutePath(), bakFile.getAbsolutePath());
                    outFile.delete();
                }

                // Fast write
                FileOutputStream fos = new FileOutputStream(outFile);
                fos.write(result);
                fos.close();
                data = result;
            } catch (Exception e) {
                e.printStackTrace();
            }

            current++;
        }

        DexEditorActivity.isChanged = false;
        DexEditorActivity.isSaved = true;
    }

    public ArrayList<String> getList(String dir) {
        if (dir.equals("/")) {
            initClassMap();
            return tree.list();
        }

        if (dir.equals("../")) {
            tree.pop();
            return tree.list();
        }

        if (!dir.endsWith("/") && !dir.equals("/")) {
            dir += "/";
        }

        tree.push(dir);
        return tree.list();
    }

    public void setCurrnetClass(String className) {
        curClassDef = classMap.get(className);
    }

    public List<TreeNode> buildFullTree() {
        Map<String, TreeNode> allNodes = new HashMap<>();
        List<TreeNode> roots = new ArrayList<>();

        List<String> sortedKeys = new ArrayList<>(classMap.keySet());
        java.util.Collections.sort(sortedKeys);

        for (String type : sortedKeys) {
            String[] parts = type.split("/");
            TreeNode parent = null;
            String pathStr = "";

            for (int i = 0; i < parts.length; i++) {
                String part = parts[i];
                pathStr = pathStr.isEmpty() ? part : pathStr + "/" + part;
                boolean isLast = (i == parts.length - 1);

                TreeNode node = allNodes.get(pathStr);
                if (node == null) {
                    node = new TreeNode(part, pathStr, i, !isLast);
                    allNodes.put(pathStr, node);
                    if (parent == null) {
                        roots.add(node);
                    } else {
                        parent.addChild(node);
                    }
                } else {
                    if (!isLast) {
                        node.setDirectory(true);
                    }
                }
                parent = node;
            }
        }
        sortNodes(roots);
        compactTree(roots);
        return roots;
    }

    public Map<String, String> getPendingSmaliMap() {
        return pendingSmaliMap;
    }

    public Map<String, java.util.HashSet<String>> getEditedClassMap() {
        return editedClassMap;
    }

    public List<TreeNode> buildEditedFullTree() {
        Map<String, TreeNode> allNodes = new HashMap<>();
        List<TreeNode> roots = new ArrayList<>();

        List<String> editedClasses = new ArrayList<>();
        for (java.util.HashSet<String> classes : editedClassMap.values()) {
            editedClasses.addAll(classes);
        }
        java.util.Collections.sort(editedClasses);

        for (String type : editedClasses) {
            String[] parts = type.split("/");
            TreeNode parent = null;
            String pathStr = "";

            for (int i = 0; i < parts.length; i++) {
                String part = parts[i];
                pathStr = pathStr.isEmpty() ? part : pathStr + "/" + part;
                boolean isLast = (i == parts.length - 1);

                TreeNode node = allNodes.get(pathStr);
                if (node == null) {
                    node = new TreeNode(part, pathStr, i, !isLast);
                    allNodes.put(pathStr, node);
                    if (parent == null) {
                        roots.add(node);
                    } else {
                        parent.addChild(node);
                    }
                } else {
                    if (!isLast) {
                        node.setDirectory(true);
                    }
                }
                parent = node;
            }
        }
        sortNodes(roots);
        compactTree(roots);
        return roots;
    }

    private void sortNodes(List<TreeNode> nodes) {
        java.util.Collections.sort(nodes, new Comparator<TreeNode>() {
            @Override
            public int compare(TreeNode a, TreeNode b) {
                if (a.isDirectory() != b.isDirectory()) {
                    return a.isDirectory() ? -1 : 1;
                }
                return a.getName().compareToIgnoreCase(b.getName());
            }
        });
        for (TreeNode node : nodes) {
            if (!node.getChildren().isEmpty()) {
                sortNodes(node.getChildren());
            }
        }
    }

    private void compactTree(List<TreeNode> nodes) {
        for (TreeNode node : nodes) {
            if (node.isDirectory()) {
                List<TreeNode> children = node.getChildren();
                while (children.size() == 1 && children.get(0).isDirectory()) {
                    TreeNode singleChild = children.get(0);
                    node.setName(node.getName() + "." + singleChild.getName());
                    node.setFullName(singleChild.getFullName());
                    node.setChildren(singleChild.getChildren());
                    children = node.getChildren();
                }
                compactTree(children);
            }
        }
    }

    public void clearAll() {
        if (classMap != null) classMap.clear();
        classMap = null;
        path = null;
        dexFile = null;
        curClassDef = null;
        tree = null;
        curFile = null;
        
        // Clean up cache directory
        if (workDir != null) {
            deleteRecursive(new File(workDir));
        }
        
        System.gc();
    }

    private void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteRecursive(child);
            }
        }
        fileOrDirectory.delete();
    }

    public byte[] read(String fileName) throws IOException {
        InputStream is = new FileInputStream(fileName);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n;
        while ((n = is.read(buffer)) != -1) {
            bos.write(buffer, 0, n);
        }
        is.close();
        return bos.toByteArray();
    }

    public void saveFile(byte[] bfile, String filePath) throws Exception {
        File file = new File(filePath);
        File dir = file.getParentFile();
        if (dir != null && !dir.exists()) {
            dir.mkdirs();
        }

        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bos.write(bfile);
        bos.close();
    }

    public int getOpenedDexVersion() {
        return this.dexVersion;
    }

    public List<String> getAllStrings() {
        HashSet<String> allStrings = new HashSet<>();
        
        synchronized (classMap) {
            for (ClassDef classDef : classMap.values()) {
                // From Fields
                for (Field field : classDef.getFields()) {
                    EncodedValue initialValue = field.getInitialValue();
                    if (initialValue instanceof StringEncodedValue) {
                        allStrings.add(((StringEncodedValue) initialValue).getValue());
                    }
                }
                
                // From Methods
                for (Method method : classDef.getMethods()) {
                    MethodImplementation impl = method.getImplementation();
                    if (impl != null) {
                        for (Instruction inst : impl.getInstructions()) {
                            if (inst instanceof ReferenceInstruction) {
                                Reference ref = ((ReferenceInstruction) inst).getReference();
                                if (ref instanceof StringReference) {
                                    allStrings.add(((StringReference) ref).getString());
                                }
                            }
                        }
                    }
                }
                
                // From Annotations
                collectStringsFromAnnotations(classDef.getAnnotations(), allStrings);
            }
        }
        
        List<String> result = new ArrayList<>(allStrings);
        Collections.sort(result);
        return result;
    }

    private void collectStringsFromAnnotations(Set<? extends Annotation> annotations, Set<String> allStrings) {
        if (annotations == null) return;
        for (Annotation annotation : annotations) {
            for (AnnotationElement element : annotation.getElements()) {
                collectStringsFromEncodedValue(element.getValue(), allStrings);
            }
        }
    }

    private void collectStringsFromEncodedValue(EncodedValue value, Set<String> allStrings) {
        if (value instanceof StringEncodedValue) {
            allStrings.add(((StringEncodedValue) value).getValue());
        } else if (value instanceof AnnotationEncodedValue) {
            for (AnnotationElement element : ((AnnotationEncodedValue) value).getElements()) {
                collectStringsFromEncodedValue(element.getValue(), allStrings);
            }
        } else if (value instanceof ArrayEncodedValue) {
            for (EncodedValue subValue : ((ArrayEncodedValue) value).getValue()) {
                collectStringsFromEncodedValue(subValue, allStrings);
            }
        }
    }

    public interface DexSaveProgress {
        void onProgress(int progress, int total);

        void onMessage(String name);

        void onTitle(String title);
    }

    public class Tree {
        private final List<Map<String, String>> node;
        private final Comparator<String> sortByType = new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                if (isDirectory(a) && !isDirectory(b)) return -1;
                if (!isDirectory(a) && isDirectory(b)) return 1;
                return a.toLowerCase().compareTo(b.toLowerCase());
            }
        };

        @SuppressLint("SuspiciousIndentation")
        public Tree(HashMap<String, ClassDef> classMap) {
            if (path == null) {
                path = new Stack<>();
                dep = 0;
            }
            Set<String> names = classMap.keySet();
            node = new ArrayList<>();

            for (String name : names) {
                String[] token = name.split("/");
                String tmp = "";
                for (int i = 0; i < token.length; i++) {
                    String value = token[i];
                    if (i >= node.size()) node.add(new HashMap<>());
                    Map<String, String> map = node.get(i);
                    if (classMap.containsKey(tmp + value) && i + 1 == token.length)
                        map.put(tmp + value, tmp);
                    else
                        map.put(tmp + value + "/", tmp);
                    tmp += value + "/";
                }
            }
        }

        public ArrayList<String> list(String parent) {
            ArrayList<String> str = new ArrayList<>();
            while (dep >= 0 && node.size() > 0) {
                Map<String, String> map = node.get(dep);
                if (map != null) {
                    for (String key : map.keySet()) {
                        if (parent.equals(map.get(key))) {
                            int index = key.endsWith("/") ? key.lastIndexOf("/", key.length() - 2) : key.lastIndexOf("/");
                            str.add(index != -1 ? key.substring(index + 1) : key);
                        }
                    }
                    break;
                }
                pop();
            }
            java.util.Collections.sort(str, sortByType);
            return str;
        }

        public ArrayList<String> list() {
            return list(getCurPath());
        }

        private void push(String name) {
            dep++;
            path.push(name);
        }

        private String pop() {
            if (dep > 0) {
                dep--;
                return path.pop();
            }
            return null;
        }

        public String getCurPath() {
            return join(path, "/");
        }

        public boolean isDirectory(String name) {
            return name.endsWith("/");
        }

        private String join(Stack<String> stack, String d) {
            StringBuilder sb = new StringBuilder();
            for (String s : stack) sb.append(s);
            return sb.toString();
        }
    }
}
