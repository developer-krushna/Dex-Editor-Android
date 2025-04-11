
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

package modder.hub.dexeditor.fragment;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import modder.hub.dexeditor.GraphDot.*;
import modder.hub.dexeditor.utils.*;
import modder.hub.dexeditor.R;
import modder.hub.dexeditor.views.*;
import modder.hub.dexeditor.activity.*;
import modder.hub.dexeditor.smali.*;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import me.zhanghai.android.fastscroll.*;

/*
Author @developer-krushna
Code fixed comments by ChatGPT
*/


public class SmaliMethodListFragment extends DialogFragment {
	private ProgressDialog progressDialog;
	private ProgressDialog secondaryProgressDialog;
	private int dexVersion;
	private int editorLineNumber;
	private String lineNumber;
	private LinearLayout linearLayout;
	private RecyclerView methodRecyclerView;
	private RecyclerView stringsRecyclerView;
	private SharedPreferences sharedPreferences;
	private Toolbar toolbar;
	private String savedMethodData = "";
	private String savedStringsData = "";
	private String methodName = "";
	private String searchQuery = "";
	private String methodSignature = "";
	private String methodParameters = "";
	private String smaliFilePath = "";
	private String className = "";
	private List<HashMap<String, Object>> methodOrFieldInfo = new ArrayList<>();
	private List<HashMap<String, Object>>  stringListInfo = new ArrayList<>();
	private Intent javaViewIntent = new Intent();
	private Intent imageViewerIntent = new Intent();
	private String fullClassName = "???";
	private String smaliCallSyntax = "->";
	
	private boolean isFirstLoad = true;
	private boolean wasStringsVisible = false;
	
	
	public interface DialogLineNumberListener {
		void _updateEditorLineNumber(String lineNumber);
	}
	
	// Update the UI with the smali file path, class name, editor line number, and dex version
	public void updateUi(String smaliFilePath, String className, int editorLineNumber, int dexVersion) {
		this.smaliFilePath = smaliFilePath;
		this.className = className;
		this.editorLineNumber = editorLineNumber;
		this.dexVersion = dexVersion;
	}
	
	@NonNull
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.list_method_layout, container, false);
		initialize(savedInstanceState, view);
		initializeLogic();
		return view;
	}
	
	private void initialize(Bundle savedInstanceState, View view) {
		linearLayout = view.findViewById(R.id.linear1);
		toolbar = view.findViewById(R.id.toolbar);
		methodRecyclerView = view.findViewById(R.id.recyclerview_method_list); 
		methodRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		
		stringsRecyclerView = view.findViewById(R.id.recyclerview_strings_list); 
		stringsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		sharedPreferences = getContext().getSharedPreferences("pref", 0);
		
	}
	
	private void initializeLogic() {
		if (getDialog() != null && getDialog().getWindow() != null) {
			getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
			getDialog().getWindow().requestFeature(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		}
		
		new FastScrollerBuilder(methodRecyclerView).build();
		new FastScrollerBuilder(stringsRecyclerView).build();
		ViewAnimationHelper.enableSwipeViewToggle(methodRecyclerView, stringsRecyclerView);
		
		toolbar.setTitle("Navigation");
		toolbar.inflateMenu(R.menu.smali_navigation_menu);
		
		Menu menu = toolbar.getMenu();
		MenuItem searchItem = menu.findItem(R.id.search);
		menu.findItem(R.id.close);
		menu.findItem(R.id.strings_list);
		searchItem.setVisible(true);
		
		SearchView searchView = (SearchView) searchItem.getActionView();
		searchView.setQueryHint("Search");
		searchView.setMaxWidth(Integer.MAX_VALUE);
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				searchQuery = query;
				if (stringsRecyclerView.getVisibility() == View.VISIBLE) {
					performStringsSearch(searchQuery);
				} else {
					performSearch(searchQuery);
				}
				return false;
			}
			
			@Override
			public boolean onQueryTextChange(String newText) {
				searchQuery = newText;
				if (stringsRecyclerView.getVisibility() == View.VISIBLE) {
					performStringsSearch(searchQuery);
				} else {
					performSearch(searchQuery);
				}
				return true;
			}
		});
		
		if (isFirstLoad) {
			final Handler handler = new Handler(Looper.getMainLooper());
			final Runnable loadDataRunnable = new LoadDataRunnable();
			handler.postDelayed(loadDataRunnable, 200L);
			isFirstLoad = false;
		} else {
			// Restore adapters without reloading
			methodRecyclerView.setAdapter(new MethodListAdapter(methodOrFieldInfo));
			stringsRecyclerView.setAdapter(new StringListAdapter(stringListInfo));
			restoreRecyclerViewState();
		}
		
		toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				if (item.getItemId() == R.id.close) {
					saveCurrentState(); // Save state before dismissing
					dismiss();
					return true;
				}
				if (item.getItemId() == R.id.strings_list) {
					if(stringListInfo.size() != 0){
						if (stringsRecyclerView.getVisibility() == View.VISIBLE) {
							ViewAnimationHelper.hideViewAndShowViewWithAnimation(stringsRecyclerView, methodRecyclerView);
							item.setTitle("Show Strings");
							wasStringsVisible = false;
						} else {
							ViewAnimationHelper.hideViewAndShowViewWithAnimation(methodRecyclerView, stringsRecyclerView);
							item.setTitle("Show Methods");
							wasStringsVisible = true;
						}
					} else {
						SketchwareUtil.showMessage(getActivity(), "No strings found");
					}
					return true;
				}
				return false;
			}
		});
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Dialog dialog = getDialog();
		if (dialog != null) {
			// Get screen width
			DisplayMetrics displayMetrics = new DisplayMetrics();
			getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
			
			// Set fixed width (80% of screen width)
			int dialogWidth = (int) (displayMetrics.widthPixels * 0.8);
			
			// Height will WRAP_CONTENT automatically
			dialog.getWindow().setLayout(dialogWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
			dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		}
	}
	
	private void saveCurrentState() {
		// Save RecyclerView scroll states
		TextEditorActivity.methodRecyclerViewState = methodRecyclerView.getLayoutManager().onSaveInstanceState();
		TextEditorActivity.stringsRecyclerViewState = stringsRecyclerView.getLayoutManager().onSaveInstanceState();
		TextEditorActivity.wasStringsVisible = stringsRecyclerView.getVisibility() == View.VISIBLE;
	}
	
	public void restorePreviousState(Parcelable methodState, Parcelable stringsState, boolean wasStringsVisible) {
		// Restore visibility
		if (wasStringsVisible) {
			methodRecyclerView.setVisibility(View.GONE);
			stringsRecyclerView.setVisibility(View.VISIBLE);
			toolbar.getMenu().findItem(R.id.strings_list).setTitle("Show Methods");
		} else {
			methodRecyclerView.setVisibility(View.VISIBLE);
			stringsRecyclerView.setVisibility(View.GONE);
			toolbar.getMenu().findItem(R.id.strings_list).setTitle("Show Strings");
		}
		
		// Restore scroll positions
		if (methodState != null) {
			methodRecyclerView.getLayoutManager().onRestoreInstanceState(methodState);
		}
		if (stringsState != null) {
			stringsRecyclerView.getLayoutManager().onRestoreInstanceState(stringsState);
		}
	}
	
	private void restoreRecyclerViewState() {
		restorePreviousState(TextEditorActivity.methodRecyclerViewState, TextEditorActivity.stringsRecyclerViewState, TextEditorActivity.wasStringsVisible);
	}
	
	private class LoadDataRunnable implements Runnable {
		@Override
		public void run() {
			new LoadDataTask().execute();
		}
	}
	
	private class LoadDataTask extends AsyncTask<Void, Void, Map<String, List<HashMap<String, Object>>>> {
		@Override
		protected Map<String, List<HashMap<String, Object>>> doInBackground(Void... voids) {
			Map<String, List<HashMap<String, Object>>> parsedDataMap = new HashMap<>();
			List<HashMap<String, Object>> methodInfoList = new ArrayList<>();
			List<HashMap<String, Object>> fieldInfoList = new ArrayList<>();
			List<HashMap<String, Object>> classInfoList = new ArrayList<>();
			List<HashMap<String, Object>> stringList = new ArrayList<>();
			
			BufferedReader smaliFileReader = null;
			try {
				// Open the smali file for reading
				smaliFileReader = new BufferedReader(new FileReader(smaliFilePath));
				String currentLine;
				int currentLineNumber = 0;
				boolean isInsideMethod = false;
				String currentMethodName = "";
				String currentFullMethodSignature = "";
				int methodStartLine = -1;
				
				// Read the smali file line by line
				while ((currentLine = smaliFileReader.readLine()) != null) {
					currentLineNumber++;
					String trimmedLine = currentLine.trim();
					
					// Skip empty lines
					if (trimmedLine.isEmpty()) {
						continue;
					}
					
					// Extract strings
					if (trimmedLine.startsWith("const-string") || trimmedLine.startsWith("const-string/jumbo")) {
						int startIndex = trimmedLine.indexOf("\"");
						int endIndex = trimmedLine.lastIndexOf("\"");
						if (startIndex != -1 && endIndex != -1) {
							String extractedString = trimmedLine.substring(startIndex + 1, endIndex);
							
							HashMap<String, Object> stringInfo = new HashMap<>();
							stringInfo.put("StringName", extractedString);
							stringInfo.put("StartLineNumber", currentLineNumber);
							stringList.add(stringInfo);
						}
					}
					
					// Split the line into tokens for easier parsing
					String[] tokens = trimmedLine.split("\\s+");
					
					// Check if the line defines a method
					if (tokens[0].equals(".method")) {
						isInsideMethod = true;
						currentMethodName = tokens[tokens.length - 1]; // Last token is the method name
						currentFullMethodSignature = currentLine.trim(); // Store full method signature
						methodStartLine = currentLineNumber;
					}
					// Check if the line ends a method
					else if (tokens[0].equals(".end") && tokens[1].equals("method")) {
						if (isInsideMethod && methodStartLine != -1) {
							// Create a method info entry
							HashMap<String, Object> methodInfo = new HashMap<>();
							methodInfo.put("MethodOrFieldName", currentMethodName); // Original behavior
							methodInfo.put("FullMethodOrField", currentFullMethodSignature); // New full signature
							methodInfo.put("StartLineNumber", methodStartLine);
							methodInfo.put("EndLineNumber", currentLineNumber);
							methodInfoList.add(methodInfo);
							
							// Reset method tracking variables
							isInsideMethod = false;
							currentMethodName = "";
							currentFullMethodSignature = "";
							methodStartLine = -1;
						}
					}
					// Check if the line defines a field
					else if (tokens[0].equals(".field")) {
						String fieldSignature = trimmedLine.substring(trimmedLine.indexOf(".field") + 7).trim();
						int colonIndex = fieldSignature.indexOf(58);
						if (colonIndex != -1) {
							String fieldName = fieldSignature.substring(0, colonIndex).trim();
							
							HashMap<String, Object> fieldInfo = new HashMap<>();
							// Original behavior
							fieldInfo.put("MethodOrFieldName", 
							String.valueOf(fieldName.substring(fieldName.lastIndexOf(32) + 1)) + 
							":" + fieldSignature.substring(colonIndex + 1).trim());
							// New full signature
							fieldInfo.put("FullMethodOrField", currentLine.trim());
							fieldInfo.put("StartLineNumber", currentLineNumber);
							fieldInfoList.add(fieldInfo);
						}
					}
					
					// Check if the line defines a class
					else if (tokens[0].equals(".class") && currentLineNumber == 1 && trimmedLine.endsWith(";")) {
						String className = tokens[tokens.length - 1]; // Last token is the class name
						fullClassName = className;
						// Read the next line to check for the superclass
						String nextLine = smaliFileReader.readLine();
						if (nextLine != null && nextLine.trim().startsWith(".super")) {
							String superClassName = nextLine.trim().substring(nextLine.indexOf(".super") + 7).trim();
							
							// Create a class info entry
							HashMap<String, Object> classInfo = new HashMap<>();
							classInfo.put("MethodOrFieldName", className);
							classInfo.put("StartLineNumber", (currentLineNumber - 1));
							classInfo.put("SuperClass", superClassName);
							classInfoList.add(classInfo);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// Close the file reader
				if (smaliFileReader != null) {
					try {
						smaliFileReader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			// Add all parsed data to the map
			parsedDataMap.put("MethodInfo", methodInfoList);
			parsedDataMap.put("FieldInfo", fieldInfoList);
			parsedDataMap.put("ClassInfo", classInfoList);
			
			// Add the string list to the parsed data map
			parsedDataMap.put("StringInfo", stringList);
			return parsedDataMap;
		}
		
		
		@Override
		protected void onPostExecute(Map<String, List<HashMap<String, Object>>> parsedDataMap) {
			if (parsedDataMap != null && !parsedDataMap.isEmpty()) {
				methodOrFieldInfo.clear();
				stringListInfo.clear();
				
				methodOrFieldInfo.addAll(parsedDataMap.get("ClassInfo"));
				methodOrFieldInfo.addAll(parsedDataMap.get("FieldInfo"));
				methodOrFieldInfo.addAll(parsedDataMap.get("MethodInfo"));
				stringListInfo.addAll(parsedDataMap.get("StringInfo"));
				
				savedMethodData = new Gson().toJson(methodOrFieldInfo);
				
				savedStringsData = new Gson().toJson(stringListInfo);
				
				methodRecyclerView.setAdapter(new MethodListAdapter(methodOrFieldInfo));
				stringsRecyclerView.setAdapter(new StringListAdapter(stringListInfo));
				
				
				int methodPositionToScroll = -1;
				int stringPositionToScroll = -1;
				
				// Step 1: Find position in methodOrFieldInfo
				for (int i = 0; i < methodOrFieldInfo.size(); i++) {
					Map<String, Object> item = methodOrFieldInfo.get(i);
					String startLineNumber = item.get("StartLineNumber").toString();
					int startLine = (int) Math.floor(Double.parseDouble(startLineNumber));
					
					if (item.containsKey("EndLineNumber")) {
						// This is a method - check line range
						String endLineNumber = item.get("EndLineNumber").toString();
						int endLine = (int) Math.floor(Double.parseDouble(endLineNumber));
						if (editorLineNumber >= startLine && editorLineNumber <= endLine) {
							methodPositionToScroll = i;
							break;
						}
					} else {
						if (editorLineNumber == startLine) {
							methodPositionToScroll = i;
							break;
						}
					}
					
				}
				
				// Step 2: Find position in stringListInfo
				for (int i = 0; i < stringListInfo.size(); i++) {
					String startLineNumber = stringListInfo.get(i).get("StartLineNumber").toString();
					int startLine = (int) Math.floor(Double.parseDouble(startLineNumber));
					if (editorLineNumber == startLine) {
						stringPositionToScroll = i;
						break;
					}
				}
				
				// Step 3: Update adapters for both RecyclerViews
				methodRecyclerView.getAdapter().notifyDataSetChanged();
				stringsRecyclerView.getAdapter().notifyDataSetChanged();
				
				// Step 4: Scroll the appropriate RecyclerView based on the found position
				if (methodPositionToScroll != -1) {
					// Scroll methodRecyclerView and ensure it's visible
					methodRecyclerView.scrollToPosition(methodPositionToScroll); // Immediate scroll
				} else if (stringPositionToScroll != -1) {
					// Scroll stringsRecyclerView and ensure it's visible
					stringsRecyclerView.scrollToPosition(stringPositionToScroll); // Immediate scroll
					
				}
				
				// Restore state after silent reload
				restoreRecyclerViewState();
			}
		}
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		saveCurrentState(); // Save state when dialog is dismissed
	}
	
	public void copiedToClipboard(String text) {
		ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(FragmentActivity.CLIPBOARD_SERVICE);
		clipboard.setPrimaryClip(ClipData.newPlainText("clipboard", text));
		SketchwareUtil.showMessage(getActivity(), "Text has been copied to clipboard");
	}
	
	public void performSearch(final String _charSeq) {
		try {
			methodOrFieldInfo.clear();
			methodOrFieldInfo = new Gson().fromJson(savedMethodData, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			int mapNumber = methodOrFieldInfo.size();
			int currentIndex = mapNumber - 1;
			for (int i = 0; i < (int)(mapNumber); i++) {
				methodName = methodOrFieldInfo.get((int)currentIndex).get("MethodOrFieldName").toString();
				if (!(_charSeq.length() > methodName.length()) && methodName.toLowerCase().contains(_charSeq.toLowerCase())) {
					
				} else {
					methodOrFieldInfo.remove((int)(currentIndex));
				}
				currentIndex--;
			}
			methodRecyclerView.setAdapter(new MethodListAdapter(methodOrFieldInfo));
			methodRecyclerView.getAdapter().notifyDataSetChanged();
			
		} catch (java.lang.NullPointerException e) {}
	}
	
	public void performStringsSearch(final String _charSeq) {
		try {
			stringListInfo.clear();
			stringListInfo = new Gson().fromJson(savedStringsData, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			int mapNumber = stringListInfo.size();
			int currentIndex = mapNumber - 1;
			for (int i = 0; i < (int)(mapNumber); i++) {
				String stringName = stringListInfo.get((int)currentIndex).get("StringName").toString();
				if (!(_charSeq.length() > stringName.length()) && stringName.toLowerCase().contains(_charSeq.toLowerCase())) {
					
				} else {
					stringListInfo.remove((int)(currentIndex));
				}
				currentIndex--;
			}
			stringsRecyclerView.setAdapter(new StringListAdapter(stringListInfo));
			stringsRecyclerView.getAdapter().notifyDataSetChanged();
			
		} catch (java.lang.NullPointerException e) {}
	}
	
	public class MethodListAdapter extends RecyclerView.Adapter<MethodListAdapter.ViewHolder> {
		List<HashMap<String, Object>> data;
		
		public MethodListAdapter(List<HashMap<String, Object>> data) {
			this.data = data;
		}
		
		@NonNull
		@Override
		public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.method_list, parent, false);
			return new ViewHolder(view);
		}
		
		@Override
		public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
			HashMap<String, Object> item = data.get(position);
			String methodOrFieldName = item.get("MethodOrFieldName").toString();
			String startLineNumber = item.get("StartLineNumber").toString();
			
			holder.indexNameTextView.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/mono.ttf"), Typeface.NORMAL);
			
			LayerDrawable layerDrawable = (LayerDrawable) holder.backgroundLayout.getBackground();
			GradientDrawable dynamicBackground = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.dynamic_background);
			
			if (methodOrFieldName.startsWith("L") && methodOrFieldName.endsWith(";")) {
				holder.indexNameContainer.setBackground(createDrawable(50, Color.parseColor("#3860AF")));
				holder.indexNameTextView.setBackground(createDrawable(50, Color.parseColor("#3860AF")));
				holder.indexNameTextView.setText("C");
				fullClassName = methodOrFieldName;
				holder.methodNameTextView.setText(extractSubstringAfterLastSlash(methodOrFieldName));
				holder.returnTypeTextView.setText(methodOrFieldName);
				
				if (editorLineNumber == ((int) Math.floor(Double.parseDouble(startLineNumber)))) {
					dynamicBackground.setColor(Color.parseColor("#67C1DF"));
				} else {
					dynamicBackground.setColor(Color.TRANSPARENT);
				}
			} else if (methodOrFieldName.contains(":")) {
				holder.indexNameContainer.setBackground(createDrawable(50, Color.parseColor("#FB8C00")));
				holder.indexNameTextView.setBackground(createDrawable(50, Color.parseColor("#FB8C00")));
				holder.indexNameTextView.setText("F");
				holder.methodNameTextView.setText(_getTextBefore(methodOrFieldName, ":"));
				holder.returnTypeTextView.setText(_getTextAfter(methodOrFieldName, ":"));
				
				if (editorLineNumber == ((int) Math.floor(Double.parseDouble(startLineNumber)))) {
					dynamicBackground.setColor(Color.parseColor("#67C1DF"));
				} else {
					dynamicBackground.setColor(Color.TRANSPARENT);
				}
			} else {
				holder.indexNameContainer.setBackground(createDrawable(50, Color.parseColor("#E53935")));
				holder.indexNameTextView.setBackground(createDrawable(50, Color.parseColor("#E53935")));
				holder.indexNameTextView.setText("M");
				String methodName = _getTextBefore(methodOrFieldName, "(");
				String parameters = "(" + _getTextAfter(methodOrFieldName, "(");
				int startLine = (int) Math.floor(Double.parseDouble(startLineNumber));
				int endLine = (int) Math.floor(Double.parseDouble(item.get("EndLineNumber").toString()));
				
				holder.methodNameTextView.setText(methodName);
				holder.returnTypeTextView.setText(parameters);
				
				if (editorLineNumber >= startLine && editorLineNumber <= endLine) {
					dynamicBackground.setColor(Color.parseColor("#67C1DF"));
				} else {
					dynamicBackground.setColor(Color.TRANSPARENT);
				}
			}
			
			holder.backgroundLayout.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View _view) {
					// Get the method or field name from the clicked position
					final String methodOrFieldName = methodOrFieldInfo.get(position).get("MethodOrFieldName").toString();
					
					// Create a popup menu attached to the clicked view
					PopupMenu popupMenu = new PopupMenu(getActivity(), _view);
					Menu menu = popupMenu.getMenu();
					
					// Check if this is a class signature (starts with L and ends with ;)
					if (methodOrFieldName.startsWith("L") && methodOrFieldName.endsWith(";")) {
						menu.add(1, 1, 1, "Copy class signature");
						menu.add(2, 2, 2, "Copy subclass signature");
					}
					
					// Check if this is a field (contains :)
					if (methodOrFieldName.contains(":")) {
						menu.add(3, 3, 3, "Copy field signature");
						menu.add(9, 9, 9, "Copy field get code");  // Generate smali get instruction
						menu.add(10, 10, 10, "Copy field put code"); // Generate smali put instruction
					}
					
					// Check if this is a method (contains ( but not :)
					if (methodOrFieldName.contains("(") && !methodOrFieldName.contains(":")) {
						menu.add(4, 4, 4, "Copy method signature");
						menu.add(5, 5, 5, "Copy method code");      // Get full method body
						menu.add(6, 6, 6, "Copy method invoke code"); // Generate invoke instruction
						menu.add(7, 7, 7, "View flowchart");       // Show method flowchart
						menu.add(8, 8, 8, "Smali to Java");        // Convert smali to Java
						menu.add(11, 11, 11, "AI Explanation");
					}
					
					// Set click listener for popup menu items
					popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
						@Override
						public boolean onMenuItemClick(MenuItem menuItem) {
							switch (menuItem.getItemId()) {
								case 1:  // Copy class signature
								copiedToClipboard(methodOrFieldName);
								return true;
								
								case 2:  // Copy subclass signature
								copiedToClipboard(methodOrFieldInfo.get(position).get("SuperClass").toString());
								return true;
								
								case 3:  // Copy field signature
								String fieldSignature = fullClassName + smaliCallSyntax + methodOrFieldName;
								// Clean up the signature by removing extra parts after space
								int spaceIndex = fieldSignature.indexOf(" ");
								if (spaceIndex != -1) {
									fieldSignature = fieldSignature.substring(0, spaceIndex);
								}
								copiedToClipboard(fieldSignature);
								return true;
								
								case 4:  // Copy method signature
								copiedToClipboard(fullClassName + smaliCallSyntax + methodOrFieldName);
								return true;
								
								case 5:  // Copy method code
								// Parse and copy the full method body from smali file
								SmaliMethodBody smaliMethodBody = new SmaliMethodBody(
								smaliFilePath, 
								new String[]{methodOrFieldInfo.get(position).get("MethodOrFieldName").toString()}, 
								false
								);
								copiedToClipboard(smaliMethodBody.parseClassInSmali());
								return true;
								
								case 6:  // Copy method invoke code
								// Generate and copy smali invoke instruction
								SmaliMethodInvokeParser parser = new SmaliMethodInvokeParser(fullClassName);
								copiedToClipboard(parser.generateInvokeCode(
								item.get("FullMethodOrField").toString(), 
								"v0"  // Using register v0
								));
								return true;
								
								case 7:  // View flowchart
								methodFlowChart(methodOrFieldInfo.get(position).get("MethodOrFieldName").toString());
								return true;
								
								case 8:  // Smali to Java
								smali2Java(methodOrFieldInfo.get(position).get("MethodOrFieldName").toString());
								return true;
								
								case 9:  // Copy field get code
								// Generate and copy smali get instruction for field
								SmaliFieldAccessParser parser2 = new SmaliFieldAccessParser(fullClassName);
								copiedToClipboard(parser2.generateGetCode(item.get("FullMethodOrField").toString()));
								return true;
								
								case 10:  // Copy field put code
								// Generate and copy smali put instruction for field
								SmaliFieldAccessParser parser3 = new SmaliFieldAccessParser(fullClassName);
								copiedToClipboard(parser3.generatePutCode(item.get("FullMethodOrField").toString()));
								return true;
								
								case 11:  // AI Explanation 
								SmaliMethodBody smaliMethodBody2 = new SmaliMethodBody(smaliFilePath, new String[]{methodOrFieldInfo.get(position).get("MethodOrFieldName").toString()}, false);
								Intent intent = new Intent(getContext().getApplicationContext(), AIOverViewActivity.class);
								intent.putExtra("smali", smaliMethodBody2.parseClassInSmali());
								startActivity(intent);
								return true;
								
								default:
								return false;
							}
						}
					});
					
					// Show the popup menu
					popupMenu.show();
					return true;  // Consume the long click event
				}
			});
			
			holder.backgroundLayout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					lineNumber = methodOrFieldInfo.get(position).get("StartLineNumber").toString();
					updateEditorLineNumber(lineNumber);
					dismiss();
				}
			});
			
		}
		
		@Override
		public int getItemCount() {
			return data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			LinearLayout backgroundLayout;
			LinearLayout indexNameContainer;
			TextView indexNameTextView;
			TextView methodNameTextView;
			TextView returnTypeTextView;
			
			public ViewHolder(@NonNull View itemView) {
				super(itemView);
				backgroundLayout = itemView.findViewById(R.id.linear_bg);
				indexNameContainer = itemView.findViewById(R.id.indexName_container);
				indexNameTextView = itemView.findViewById(R.id.indexName);
				methodNameTextView = itemView.findViewById(R.id.method_name);
				returnTypeTextView = itemView.findViewById(R.id.return_type);
			}
		}
	}
	
	private GradientDrawable createDrawable(int cornerRadius, int color) {
		GradientDrawable drawable = new GradientDrawable();
		drawable.setCornerRadius(cornerRadius);
		drawable.setColor(color);
		return drawable;
	}
	
	public static String extractSubstringAfterLastSlash(String str) {
		int lastSlashIndex = str.lastIndexOf('/');
		return lastSlashIndex != -1 ? str.substring(lastSlashIndex + 1, str.length() - 1) : str;
	}
	
	// Update the editor line number in the parent activity
	public void updateEditorLineNumber(String lineNumber) {
		if (getActivity() instanceof DialogLineNumberListener) {
			((DialogLineNumberListener) getActivity())._updateEditorLineNumber(lineNumber);
		}
	}
	
	// Generate and display a flowchart for the given method
	public void methodFlowChart(String methodName) {
		showProgressDialog(true); // Show progress dialog
		new MethodFlowChartTask(methodName).start(); // Start the flowchart generation task
	}
	
	// Helper method to get the text before a specific delimiter
	public String _getTextBefore(String text, String delimiter) {
		int index = text.indexOf(delimiter);
		return index != -1 ? text.substring(0, index) : "";
	}
	
	// Helper method to get the text after a specific delimiter
	public String _getTextAfter(String text, String delimiter) {
		int index = text.indexOf(delimiter);
		return index != -1 ? text.substring(index + delimiter.length()) : "";
	}
	
	// Task to generate a method flowchart
	private class MethodFlowChartTask extends Thread {
		private final String methodName;
		
		public MethodFlowChartTask(String methodName) {
			this.methodName = methodName;
		}
		
		@Override
		public void run() {
			try {
				ArrayList<String> methodList = new ArrayList<>();
				methodList.add(methodName);
				DrawFlowDiagram drawFlowDiagram = new DrawFlowDiagram(smaliFilePath, "png", methodList.toArray(new String[0]), "", "");
				drawFlowDiagram.run();
				
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						for (Method method : drawFlowDiagram.getClassInSmali().getMethodDict().values()) {
							final String dotDiagram = drawFlowDiagram.drawMethodFlowDiagram(method);
							showProgressDialog(false); // Hide progress dialog
							showSecondaryProgressDialog(true); // Show secondary progress dialog
							
							new Thread(new Runnable() {
								@Override
								public void run() {
									try {
										Response response = new OkHttpClient().newCall(
										new Request.Builder()
										.url("https://timscriptov.ru/graphviz/index.php")
										.post(new FormBody.Builder()
										.add("dot_diagram", dotDiagram)
										.build())
										.build()
										).execute();
										
										if (!response.isSuccessful()) {
											showSecondaryProgressDialog(false);
											showExceptionDlg(new IOException("Unsuccessful response: " + response));
										} else {
											FileOutputStream fileOutputStream = new FileOutputStream(
											new File(getActivity().getFilesDir(), "SmaliFlowChart.png")
											);
											fileOutputStream.write(response.body().bytes());
											fileOutputStream.close();
											showSecondaryProgressDialog(false);
											
											Intent intent = new Intent(getContext().getApplicationContext(), ImageViewerActivity.class);
											intent.putExtra("Title", className + "." + _getTextBefore(methodName, "("));
											intent.putExtra("Subtitle", "(" + _getTextAfter(methodName, "("));
											startActivity(intent);
										}
									} catch (IOException e) {
										showSecondaryProgressDialog(false);
										showExceptionDlg(e);
									}
								}
							}).start();
						}
					}
				});
			} catch (Exception e) {
				showProgressDialog(false);
				showExceptionDlg(e);
			}
		}
	}
	
	// Convert Smali code to Java code for the given method
	public void smali2Java(String methodName) {
		new Handler(Looper.getMainLooper()).postDelayed(new SmaliToJavaTask(methodName), 200L);
	}
	
	// Task to convert Smali code to Java code
	private class SmaliToJavaTask implements Runnable {
		private final String methodName;
		
		public SmaliToJavaTask(String methodName) {
			this.methodName = methodName;
		}
		
		@Override
		public void run() {
			showProgressDialog(true); // Show progress dialog
			new AsyncTask<Void, Void, String>() {
				@Override
				protected String doInBackground(Void... voids) {
					try {
						// Parse the Smali method and convert it to Java
						SmaliMethodBody smaliMethodBody = new SmaliMethodBody(smaliFilePath, new String[]{methodName}, true);
						return Smali2Java.translate(smaliMethodBody.parseClassInSmali(), dexVersion);
					} catch (Exception e) {
						showProgressDialog(false); // Hide progress dialog on error
						getActivity().runOnUiThread(new Runnable() {
							@Override
							public void run() {
								showExceptionDlg(e); // Show exception dialog
							}
						});
						return null;
					}
				}
				
				@Override
				protected void onPostExecute(String javaCode) {
					showProgressDialog(false); // Hide progress dialog
					if (javaCode != null) {
						try {
							// Save the converted Java code to a file
							String javaFilePath = getActivity().getFilesDir() + "/Method2Java.java";
							FileUtil.writeFile(javaFilePath, javaCode);
							
							// Launch the JavaViewActivity to display the Java code
							javaViewIntent.setClass(getContext().getApplicationContext(), JavaViewActivity.class);
							javaViewIntent.putExtra("Method2JavaTitle", className + "." + _getTextBefore(methodName, "("));
							javaViewIntent.putExtra("Method2JavaSubtitle", "(" + _getTextAfter(methodName, "("));
							javaViewIntent.putExtra("Method2JavaPath", javaFilePath);
							startActivity(javaViewIntent);
						} catch (Exception e) {
							getActivity().runOnUiThread(new Runnable() {
								@Override
								public void run() {
									showExceptionDlg(e); // Show exception dialog
								}
							});
						}
					}
				}
			}.execute();
		}
	}
	
	// Show or hide the primary progress dialog
	public void showProgressDialog(boolean show) {
		if (show) {
			if (progressDialog == null) {
				progressDialog = new ProgressDialog(getActivity());
				progressDialog.setCancelable(false);
				progressDialog.setCanceledOnTouchOutside(false);
				progressDialog.requestWindowFeature(1);
				progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
			}
			progressDialog.setMessage(null);
			progressDialog.show();
			progressDialog.setContentView(R.layout.loading);
			LinearLayout background = progressDialog.findViewById(R.id.background);
			GradientDrawable gradientDrawable = new GradientDrawable();
			gradientDrawable.setColor(Color.parseColor("#E0E0E0"));
			gradientDrawable.setCornerRadius(40.0f);
			gradientDrawable.setStroke(0, Color.TRANSPARENT);
			progressDialog.findViewById(R.id.linear2).setBackground(gradientDrawable);
			((LinearLayout)progressDialog.findViewById(R.id.layout_progress)).addView(new RadialProgressView(getActivity()));
		} else if (progressDialog != null) {
			progressDialog.dismiss();
		}
	}
	
	// Show or hide the secondary progress dialog
	public void showSecondaryProgressDialog(boolean show) {
		if (show) {
			if (secondaryProgressDialog == null) {
				secondaryProgressDialog = new ProgressDialog(getActivity());
				secondaryProgressDialog.setCancelable(false);
				secondaryProgressDialog.setCanceledOnTouchOutside(false);
				secondaryProgressDialog.requestWindowFeature(1);
				secondaryProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
			}
			secondaryProgressDialog.setMessage(null);
			secondaryProgressDialog.show();
			secondaryProgressDialog.setContentView(R.layout.loading);
			LinearLayout background = secondaryProgressDialog.findViewById(R.id.background);
			GradientDrawable gradientDrawable = new GradientDrawable();
			gradientDrawable.setColor(Color.parseColor("#E0E0E0"));
			gradientDrawable.setCornerRadius(40.0f);
			gradientDrawable.setStroke(0, Color.TRANSPARENT);
			secondaryProgressDialog.findViewById(R.id.linear2).setBackground(gradientDrawable);
			((LinearLayout) secondaryProgressDialog.findViewById(R.id.layout_progress)).addView(new RadialProgressView(getActivity()));
		} else if (secondaryProgressDialog != null) {
			secondaryProgressDialog.dismiss();
		}
	}
	
	// Show an exception dialog with the given exception
	public void showExceptionDlg(final Exception e) {
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Notify_MT.Notify(getActivity(), getResources().getString(R.string.error_title), e.getMessage(), getResources().getString(R.string.close_btn));
			}
		});
	}
	
	private class StringListAdapter extends RecyclerView.Adapter<StringListAdapter.ViewHolder> {
		List<HashMap<String, Object>> data;
		
		public StringListAdapter(List<HashMap<String, Object>> data) {
			this.data = data;
		}
		
		@NonNull
		@Override
		public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.string_list, parent, false);
			return new ViewHolder(view);
		}
		
		@Override
		public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
			HashMap<String, Object> item = data.get(position);
			String stringName = item.get("StringName").toString();
			String startLineNumber = item.get("StartLineNumber").toString();
			holder.indexNameTextView.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/mono.ttf"), Typeface.NORMAL);
			holder.indexNameContainer.setBackground(createDrawable(50, Color.parseColor("#40AD3E")));
			holder.indexNameTextView.setBackground(createDrawable(50, Color.parseColor("#40AD3E")));
			holder.stringTextView.setText(stringName);
			
			LayerDrawable layerDrawable = (LayerDrawable) holder.backgroundLayout.getBackground();
			GradientDrawable dynamicBackground = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.dynamic_background);
			
			if (editorLineNumber == ((int) Math.floor(Double.parseDouble(startLineNumber)))) {
				dynamicBackground.setColor(Color.parseColor("#67C1DF"));
			} else {
				dynamicBackground.setColor(Color.TRANSPARENT);
			}
			
			holder.backgroundLayout.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View _view) {
					PopupMenu popupMenu = new PopupMenu(getActivity(), _view);
					Menu menu = popupMenu.getMenu();
					menu.add(1, 1, 1, "Copy");
					popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
						@Override
						public boolean onMenuItemClick(MenuItem item) {
							switch (item.getItemId()) {
								case 1:
								copiedToClipboard(stringName);
								return true;
								default:
								return false;
							}
						}
					});
					popupMenu.show();
					return true;
				}
			});
			
			holder.backgroundLayout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					updateEditorLineNumber(startLineNumber);
					dismiss();
				}
			});
		}
		
		@Override
		public int getItemCount() {
			return data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			TextView stringTextView;
			LinearLayout backgroundLayout;
			LinearLayout indexNameContainer;
			TextView indexNameTextView;
			
			public ViewHolder(@NonNull View itemView) {
				super(itemView);
				backgroundLayout = itemView.findViewById(R.id.linear_bg);
				stringTextView = itemView.findViewById(R.id.string_name);
				indexNameContainer = itemView.findViewById(R.id.indexName_container);
				indexNameTextView = itemView.findViewById(R.id.indexName);
			}
		}
	}
	
}
