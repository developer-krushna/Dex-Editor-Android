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


package modder.hub.dexeditor.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.android.tools.smali.dexlib2.Opcodes;
import com.android.tools.smali.dexlib2.dexbacked.DexBackedDexFile;
import com.android.tools.smali.dexlib2.iface.ClassDef;
import com.android.tools.smali.dexlib2.writer.builder.DexBuilder;
import com.android.tools.smali.dexlib2.writer.io.MemoryDataStore;
import com.android.tools.smali.dexlib2.util.DexUtil;
import modder.hub.dexeditor.views.*;
import modder.hub.dexeditor.utils.*;
import modder.hub.dexeditor.activity.*;
import modder.hub.dexeditor.*;
 
 
 /*
Author @developer-krushna
Code fixed by ChatGPT
*/


public class ClassTree {
	
	public Tree tree;
	public HashMap<String, ClassDef> classMap;
	public List<DexBackedDexFile> dexFiles;  // Changed to List for multiple DEX files
    public DexBackedDexFile dexFile;
    public String Path;
    
	public ClassDef curClassDef;
	public int dep;
	public Stack<String> path;
	public String curFile;
	public List<ClassDef> classDefList = new ArrayList<>();
	public int mod;
	public List<String> paths;  
	public int dexVersion; 
    
    byte[] data;
	byte[] input;
	
	public ClassTree(String mPath) throws Exception {
		this.Path = mPath;
		initDex();
	}
	
	// Initialize the DEX file
	private void initDex() throws Exception {
		byte[] read = read(this.Path);
		this.input = read;
		int verifyDexHeader = DexUtil.verifyDexHeader(read, 0);
		this.dexVersion = verifyDexHeader;
		dexFile = DexBackedDexFile.fromInputStream(Opcodes.forDexVersion(verifyDexHeader), new ByteArrayInputStream(input));
		classDefList.addAll(dexFile.getClasses());
		initClassMap();
	}
	
	// Constructor updated to take a list of paths
	public ClassTree(List<String> mPaths) throws Exception {
		this.paths = mPaths;
		initMultiDex();
	}
	
	// Initialize multiple DEX files
	private void initMultiDex() throws Exception {
		dexFiles = new ArrayList<>();
		classDefList.clear();
		
		for (String path : paths) {
			byte[] read = read(path);
			int verifyDexHeader = DexUtil.verifyDexHeader(read, 0);
			this.dexVersion = verifyDexHeader; 
			DexBackedDexFile dexFile = DexBackedDexFile.fromInputStream(
			Opcodes.forDexVersion(verifyDexHeader), new ByteArrayInputStream(read));
			dexFiles.add(dexFile);
			classDefList.addAll(dexFile.getClasses());
		}
		
		initClassMap();
	}
	
	public int getOpenedDexVersion() {
		return this.dexVersion;
	}
	
	// Initialize the class map
	private void initClassMap() {
		if (classMap == null) {
			classMap = new HashMap<String, ClassDef>();
		} else {
			classMap.clear();
		}
		for (int i = 0; i < classDefList.size(); i++) {
			ClassDef classDef = classDefList.get(i);
			String type = classDef.getType();
			type = type.substring(1, type.length() - 1);
			classMap.put(type, classDef);
		}
		tree = null;
		tree = new Tree(classMap);
	}
	
	
	// Remove a class
	public void removeClass(String className) {
		{
			Iterator classIterator = classMap.keySet().iterator();
			while (classIterator.hasNext()) {
				ClassDef classDef = classMap.get(classIterator.next());
				String type = classDef.getType();
				type = type.substring(1, type.length() - 1);
				
				if (tree.isDirectory(className)) {
					if (type.startsWith(className)) {
						classIterator.remove();
					}
				} else {
					if (type.equals(className)) {
						classIterator.remove();
					}
				}
			}
		}
		
		{
			Iterator classIterator = classDefList.iterator();
			while (classIterator.hasNext()) {
				ClassDef classDef = (ClassDef) classIterator.next();
				String type = classDef.getType();
				type = type.substring(1, type.length() - 1);
				
				if (tree.isDirectory(className)) {
					if (type.startsWith(className)) {
						classIterator.remove();
					}
				} else {
					if (type.equals(className)) {
						classIterator.remove();
					}
				}
			}
			
		}
		
		initClassMap();
		
	}
	
	
	// Get the list of classes in a directory
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
			dir = dir + "/";
		}
		
		tree.push(dir);
		return tree.list();
	}
	
	// Set the current class
	public void setCurrnetClass(String className) {
		curClassDef = classMap.get(className);
	}
	
	// Clear all data
	public void clearAll() {
		if (classMap != null)
		classMap.clear();
		classMap = null;
		path = null;
		dexFile = null;
		curClassDef = null;
		tree = null;
		curFile = null;
		System.gc();
	}
	
	
	// Tree class
	public class Tree {
		private List<Map<String, String>> node;
		private Comparator<String> sortByType = new Comparator<String>() {
			public int compare(String a, String b) {
				if (isDirectory(a) && !isDirectory(b)) return -1;
				if (!isDirectory(a) && isDirectory(b)) return 1;
				return a.toLowerCase().compareTo(b.toLowerCase());
			}
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
					if (i >= node.size()) {
						Map<String, String> map = new HashMap<>();
						if (classMap.containsKey(tmp + value) && i + 1 == token.length) {
							map.put(tmp + value, tmp);
						} else {
							map.put(tmp + value + "/", tmp);
						}
						node.add(map);
						tmp += value + "/";
					} else {
						Map<String, String> map = node.get(i);
						if (classMap.containsKey(tmp + value) && i + 1 == token.length) {
							map.put(tmp + value, tmp);
						} else {
							map.put(tmp + value + "/", tmp);
						}
						tmp += value + "/";
					}
				}
			}
		}
		
		public ArrayList<String> list(String parent) {
			Map<String, String> map = null;
			ArrayList<String> str = new ArrayList<>();
			while (dep >= 0 && node.size() > 0) {
				map = node.get(dep);
				if (map != null) break;
				pop();
			}
			if (map == null) return str;
			
			for (String key : map.keySet()) {
				if (parent.equals(map.get(key))) {
					int index = key.endsWith("/") ? key.lastIndexOf("/", key.length() - 2) : key.lastIndexOf("/");
					if (index != -1) key = key.substring(index + 1);
					str.add(key);
				}
			}
			Collections.sort(str, sortByType);
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
			StringBuilder sb = new StringBuilder("");
			for (String s : stack) sb.append(s);
			return sb.toString();
		}
	}
	
	// Save a class definition
	public void saveClassDef(ClassDef classDef) throws Exception {
		DexBuilder dexBuilder = new DexBuilder(Opcodes.forDexVersion(this.dexVersion));
		dexBuilder.setIgnoreMethodAndFieldError(true);
		dexBuilder.internClassDef(classDef);
		
		String type = classDef.getType().substring(1, classDef.getType().length() - 1);
		classMap.remove(type);
		classMap.put(type, classDef);
		DexEditorActivity.isChanged = true;
		DexEditorActivity.isSaved = false;
	}
	
	// Save final DEX file 
	public void saveDexFile(String destPath, DexSaveProgress dexSaveProgress) {
		DexBuilder dexBuilder = new DexBuilder(Opcodes.forDexVersion(this.dexVersion));
		dexBuilder.setIgnoreMethodAndFieldError(true);
		try {
			int index = 0;
			for (String type : classMap.keySet()) {
				ClassDef classDef = classMap.get(type);
				dexBuilder.internClassDef(classDef);
				index++;
				dexSaveProgress.onProgress(index, classMap.size());
			}
			dexSaveProgress.onMessage("Writing file...");
			
			MemoryDataStore memoryDataStore = new MemoryDataStore();
			dexBuilder.writeTo(memoryDataStore);
			byte[] result = Arrays.copyOf(memoryDataStore.getBuffer(), memoryDataStore.getSize());
			File file = new File(destPath);
			File bak = new File(destPath + ".bak");
			if (file.renameTo(bak)) {
				file. delete();
				saveFile(result, destPath);
			} else {
				
			}
			data = result;
			DexEditorActivity.isChanged = false;
			DexEditorActivity.isSaved = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// Save as file
	public void saveFile(byte[] bfile, String filePath) throws Exception {
		File file = new File(filePath);
		File dir = file.getParentFile();
		if (dir != null && !dir.exists()) dir.mkdirs();
		
		try (FileOutputStream fos = new FileOutputStream(file);
		BufferedOutputStream bos = new BufferedOutputStream(fos)) {
			bos.write(bfile);
		}
	}
	
	// Read the file and convert it to bytes
	public byte[] read(String fileName) throws IOException {
		try (InputStream is = new FileInputStream(fileName);
		ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			byte[] buffer = new byte[is.available()];
			int n;
			while ((n = is.read(buffer)) != -1) bos.write(buffer, 0, n);
			return bos.toByteArray();
		}
	}
	
	// Static interface for getting DEX saving progress
	public static interface DexSaveProgress {
		void onProgress(int progress, int total);
		void onMessage(String name);
		void onTitle(String title);
	}
}
