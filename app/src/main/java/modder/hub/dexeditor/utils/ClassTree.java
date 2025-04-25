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

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import com.android.tools.smali.dexlib2.Opcodes;
import com.android.tools.smali.dexlib2.dexbacked.DexBackedDexFile;
import com.android.tools.smali.dexlib2.iface.ClassDef;
import com.android.tools.smali.dexlib2.writer.builder.DexBuilder;
import com.android.tools.smali.dexlib2.writer.io.MemoryDataStore;
import com.android.tools.smali.dexlib2.util.DexUtil;

import com.android.tools.smali.baksmali.Adaptors.ClassDefinition;
import com.android.tools.smali.baksmali.BaksmaliOptions;
import com.android.tools.smali.baksmali.formatter.BaksmaliWriter;
import com.android.tools.smali.dexlib2.iface.ClassDef;
import com.android.tools.smali.smali.SmaliOptions;
import com.android.tools.smali.smali2.Smali2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.GsonBuilder;

import modder.hub.dexeditor.activity.DexEditorActivity;

public class ClassTree {
	
	/*
	Author @developer-krushna
	Orginally replicate from Flying-Yu AE Manager on github
	*/
	
	
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
	public int mod;
	public List<String> paths;
	public int dexVersion;
	Map<String, List<String>> dexClassMap = new LinkedHashMap<>();
	private final Map<String, List<String>> removedClassMap = new HashMap<>();
	private final Map<String, List<String>> editedClassMap = new HashMap<>();
	
	private static final String ALL_CLASSES_JSON = "/sdcard/Android/data/modder.hub.dexeditor/allclasses.json";
	private static final String DELETED_CLASSES_JSON = "/sdcard/Android/data/modder.hub.dexeditor/deletedclasses.json";
	private static final String EDITED_CLASSES_JSON = "/sdcard/Android/data/modder.hub.dexeditor/editedclasses.json";
	
	byte[] data;
	byte[] input;
	
	private Map<String, List<String>> deletedClassJson = new HashMap<>();
	
	public ClassTree(String mPath) throws Exception {
		this.Path = mPath;
		initDex();
		loadDeletedClasses();
	}
	
	public ClassTree(List<String> mPaths) throws Exception {
		this.paths = mPaths;
		initMultiDex();
		loadDeletedClasses();
	}
	
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
		
		for (int i = 0; i < paths.size(); i++) {
			String path = paths.get(i);
			byte[] read = read(path);
			int verifyDexHeader = DexUtil.verifyDexHeader(read, 0);
			this.dexVersion = verifyDexHeader;
			DexBackedDexFile file = DexBackedDexFile.fromInputStream(Opcodes.forDexVersion(verifyDexHeader), new ByteArrayInputStream(read));
			
			dexFiles.add(file);
			classDefList.addAll(file.getClasses());
			
			List<String> classNames = new ArrayList<>();
			for (ClassDef classDef : file.getClasses()) {
				classNames.add(classDef.getType());
			}
			
			String fileName = new File(path).getName();
			dexClassMap.put(fileName, classNames);
		}
		
		saveAllClassesJson();
		initClassMap();
	}
	
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
	
	private void loadDeletedClasses() {
		try {
			File f = new File(DELETED_CLASSES_JSON);
			if (!f.exists()) return;
			
			String json = new String(read(DELETED_CLASSES_JSON));
			deletedClassJson = new Gson().fromJson(json, new TypeToken<Map<String, List<String>>>() {}.getType());
		} catch (Exception e) {
			deletedClassJson = new HashMap<>();
		}
	}
	
	private void saveDeletedClasses() {
		try {
			File file = new File(DELETED_CLASSES_JSON);
			if (file.exists()) {
				file.delete();
			}
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(deletedClassJson);
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
	
	private boolean isClassDeleted(String type) {
		for (Map.Entry<String, List<String>> entry : deletedClassJson.entrySet()) {
			if (entry.getValue().contains(type)) return true;
		}
		return false;
	}
	
	public void removeClass(String className) {
		// Remove from classMap
		Iterator<String> classMapIterator = classMap.keySet().iterator();
		while (classMapIterator.hasNext()) {
			String key = classMapIterator.next();
			if (tree.isDirectory(className)) {
				if (key.startsWith(className)) {
					classMapIterator.remove();
					recordRemovedClass(key);
				}
			} else {
				if (key.equals(className)) {
					classMapIterator.remove();
					recordRemovedClass(key);
				}
			}
		}
		
		// Remove from classDefList
		Iterator<ClassDef> classDefIterator = classDefList.iterator();
		while (classDefIterator.hasNext()) {
			ClassDef classDef = classDefIterator.next();
			String type = classDef.getType();
			type = type.substring(1, type.length() - 1);
			if (tree.isDirectory(className)) {
				if (type.startsWith(className)) {
					classDefIterator.remove();
				}
			} else {
				if (type.equals(className)) {
					classDefIterator.remove();
				}
			}
		}
		
		initClassMap();
		saveDeletedClasses();
	}
	
	private void recordRemovedClass(String type) {
		for (Entry<String, List<String>> entry : dexClassMap.entrySet()) {
			if (entry.getValue().contains("L" + type + ";")) {
				deletedClassJson.computeIfAbsent(entry.getKey(), k -> new ArrayList<>()).add(type);
				break;
			}
		}
	}
	
	public void saveClassDef(ClassDef classDef) throws Exception {
		String type = classDef.getType().substring(1, classDef.getType().length() - 1);
		
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
		StringWriter stringWriter = new StringWriter();
		BaksmaliWriter baksmaliWriter = new BaksmaliWriter(stringWriter);
		new ClassDefinition(new BaksmaliOptions(), classDef).writeTo(baksmaliWriter);
		baksmaliWriter.close();
		
		String dexFileName = findDexFileNameForClass(classDef);
		
		return "# " + dexFileName + "\n\n" + stringWriter.toString();
	}
	
	private String findDexFileNameForClass(ClassDef classDef) {
		String classType = classDef.getType();
		for (Entry<String, List<String>> entry : dexClassMap.entrySet()) {
			if (entry.getValue().contains(classType)) {
				return entry.getKey();
			}
		}
		
		return "unknown.dex";
	}
	
	private void recordEditedClass(String type) {
		for (Entry<String, List<String>> entry : dexClassMap.entrySet()) {
			if (entry.getValue().contains("L" + type + ";")) {
				editedClassMap.computeIfAbsent(entry.getKey(), k -> new ArrayList<>()).add(type);
				break;
			}
		}
	}
	
	public void saveAllDexFiles(DexSaveProgress dexSaveProgress) {
		if (dexClassMap == null || dexClassMap.isEmpty()) return;
		
		int total = dexClassMap.size();
		int current = 1;
		
		for (Entry<String, List<String>> entry : dexClassMap.entrySet()) {
			String fileName = entry.getKey();
			dexSaveProgress.onTitle(fileName + " (" + current + "/" + total + ")");
			
			boolean isTouched = deletedClassJson.containsKey(fileName) || editedClassMap.containsKey(fileName);
			if (!isTouched) {
				current++;
				continue;
			}
			
			DexBuilder dexBuilder = new DexBuilder(Opcodes.forDexVersion(this.dexVersion));
			dexBuilder.setIgnoreMethodAndFieldError(true);
			
			List<String> classNames = entry.getValue();
			int classCount = classNames.size();
			
			for (int i = 0; i < classCount; i++) {
				String rawType = classNames.get(i);
				String type = rawType.substring(1, rawType.length() - 1);
				
				if (deletedClassJson.containsKey(fileName) && deletedClassJson.get(fileName).contains(type)) {
					dexSaveProgress.onProgress(i + 1, classCount);
					continue;
				}
				
				ClassDef classDef = classMap.get(type);
				if (classDef != null) {
					dexSaveProgress.onMessage("Compiling...");
					dexBuilder.internClassDef(classDef);
				}
				dexSaveProgress.onProgress(i + 1, classCount);
			}
			
			dexSaveProgress.onMessage("Writing file...");
			try {
				MemoryDataStore memoryDataStore = new MemoryDataStore();
				dexBuilder.writeTo(memoryDataStore);
				byte[] result = Arrays.copyOf(memoryDataStore.getBuffer(), memoryDataStore.getSize());
				
				String outputDir = (paths != null && !paths.isEmpty()) ? new File(paths.get(0)).getParent() : "/sdcard";
				File outFile = new File(outputDir, fileName);
				File bakFile = new File(outFile.getAbsolutePath() + ".bak");
				
				if (outFile.exists()) {
					// Create backup
					InputStream in = new FileInputStream(outFile);
					OutputStream out = new FileOutputStream(bakFile);
					byte[] buffer = new byte[1024];
					int length;
					while ((length = in.read(buffer)) > 0) {
						out.write(buffer, 0, length);
					}
					in.close();
					out.close();
					outFile.delete();
				}
				
				// Write new file
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
	
	public void clearAll() {
		if (classMap != null) classMap.clear();
		classMap = null;
		path = null;
		dexFile = null;
		curClassDef = null;
		tree = null;
		curFile = null;
		System.gc();
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
		if (dir != null && !dir.exists()) dir.mkdirs();
		
		FileOutputStream fos = new FileOutputStream(file);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		bos.write(bfile);
		bos.close();
	}
	
	public int getOpenedDexVersion() {
		return this.dexVersion;
	}
	
	public class Tree {
		private List<Map<String, String>> node;
		private Comparator<String> sortByType = (a, b) -> {
			if (isDirectory(a) && !isDirectory(b)) return -1;
			if (!isDirectory(a) && isDirectory(b)) return 1;
			return a.toLowerCase().compareTo(b.toLowerCase());
		};
		
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
			str.sort(sortByType);
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
	
	public static interface DexSaveProgress {
		void onProgress(int progress, int total);
		void onMessage(String name);
		void onTitle(String title);
	}
}
