
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

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
/*
Author @developer-krushna
*/


public class DexFileSelector {
	private Context context;
	private File folder;
	private List<String> dexFiles;
	private boolean[] selectedItems;
	private AlertDialog dialog;
	private OnFilesSelectedListener listener;
	private String initialDexPath;
	
	public interface OnFilesSelectedListener {
		void onFilesSelected(List<String> selectedFilePaths);
	}
	
	public DexFileSelector(Context context, String dexFilePath) {
		this.context = context;
		this.initialDexPath = dexFilePath;
		File dexFile = new File(dexFilePath);
		this.folder = dexFile.getParentFile();
		this.dexFiles = new ArrayList<String>();
		loadDexFiles(dexFile);
	}
	
	public void setOnFilesSelectedListener(OnFilesSelectedListener listener) {
		this.listener = listener;
	}
	
	private void loadDexFiles(File initialDexFile) {
		if (folder.exists() && folder.isDirectory()) {
			File[] files = folder.listFiles(new java.io.FileFilter() {
				public boolean accept(File file) {
					return file.getName().endsWith(".dex");
				}
			});
			
			if (files != null && files.length > 0) {
				for (File file : files) {
					dexFiles.add(file.getAbsolutePath());
				}
				if (!dexFiles.contains(initialDexFile.getAbsolutePath())) {
					dexFiles.add(initialDexFile.getAbsolutePath());
				}
				
				Collections.sort(dexFiles, new NaturalOrderComparator());
			} else {
				dexFiles.add(initialDexFile.getAbsolutePath());
			}
		}
	}
	
	public void showDialog() {
		if (dexFiles.isEmpty()) {
			Toast.makeText(context, "No .dex files found in the folder.", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if (dexFiles.size() == 1) {
			if (listener != null) {
				List<String> singleFileList = new ArrayList<String>();
				singleFileList.add(dexFiles.get(0));
				listener.onFilesSelected(singleFileList);
			}
			return;
		}
		
		String[] fileNames = new String[dexFiles.size()];
		for (int i = 0; i < dexFiles.size(); i++) {
			fileNames[i] = new File(dexFiles.get(i)).getName();
		}
		
		selectedItems = new boolean[dexFiles.size()];
		int initialIndex = dexFiles.indexOf(initialDexPath);
		if (initialIndex != -1) {
			selectedItems[initialIndex] = true;
		}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("MultiDex");
		builder.setMultiChoiceItems(fileNames, selectedItems, new DialogInterface.OnMultiChoiceClickListener() {
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				selectedItems[which] = isChecked;
			}
		});
		
		builder.setNeutralButton("Select All", null);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				List<String> selectedPaths = new ArrayList<String>();
				for (int i = 0; i < selectedItems.length; i++) {
					if (selectedItems[i]) {
						selectedPaths.add(dexFiles.get(i));
					}
				}
				if (listener != null) {
					listener.onFilesSelected(selectedPaths);
				}
			}
		});
		builder.setNegativeButton("Cancel", null);
		
		dialog = builder.create();
		
		dialog.setOnShowListener(new DialogInterface.OnShowListener() {
			public void onShow(DialogInterface dialogInterface) {
				Button invertButton = dialog.getButton(AlertDialog.BUTTON_NEUTRAL);
				invertButton.setOnClickListener(new android.view.View.OnClickListener() {
					public void onClick(android.view.View v) {
						String buttonText = invertButton.getText().toString();
						AlertDialog alertDialog = (AlertDialog) dialog;
						
						if (buttonText.equals("Select All")) {
							// First click: select all
							for (int i = 0; i < selectedItems.length; i++) {
								selectedItems[i] = true;
								alertDialog.getListView().setItemChecked(i, true);
							}
							invertButton.setText("Invert Selection");
						} else {
							// Subsequent clicks: invert selection
							for (int i = 0; i < selectedItems.length; i++) {
								selectedItems[i] = !selectedItems[i];
								alertDialog.getListView().setItemChecked(i, selectedItems[i]);
							}
						}
					}
				});
			}
		});
		
		dialog.show();
	}
	
	public class NaturalOrderComparator implements Comparator<String> {
		@Override
		public int compare(String a, String b) {
			int aLength = a.length();
			int bLength = b.length();
			int aIndex = 0;
			int bIndex = 0;
			
			while (aIndex < aLength && bIndex < bLength) {
				char aChar = a.charAt(aIndex);
				char bChar = b.charAt(bIndex);
				
				if (Character.isDigit(aChar) && Character.isDigit(bChar)) {
					// Compare numbers
					int aNumber = 0;
					while (aIndex < aLength && Character.isDigit(a.charAt(aIndex))) {
						aNumber = aNumber * 10 + (a.charAt(aIndex) - '0');
						aIndex++;
					}
					
					int bNumber = 0;
					while (bIndex < bLength && Character.isDigit(b.charAt(bIndex))) {
						bNumber = bNumber * 10 + (b.charAt(bIndex) - '0');
						bIndex++;
					}
					
					if (aNumber != bNumber) {
						return Integer.compare(aNumber, bNumber);
					}
				} else {
					// Compare characters
					if (aChar != bChar) {
						return Character.compare(aChar, bChar);
					}
					aIndex++;
					bIndex++;
				}
			}
			
			return aLength - bLength;
		}
	}
}
