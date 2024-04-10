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


package modder.hub.dexeditor.fragment;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.content.*;
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


import androidx.annotation.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;

import org.json.*;
import org.apache.commons.io.FilenameUtils;


import android.app.AlertDialog.Builder;
import android.widget.RadioGroup.LayoutParams;

import modder.hub.dexeditor.R;
import modder.hub.dexeditor.FileUtil;
import modder.hub.dexeditor.Notify_MT;
import modder.hub.dexeditor.SketchwareUtil;
import modder.hub.dexeditor.RadialProgressView;
import modder.hub.dexeditor.GraphDot.DrawFlowDiagram;
import modder.hub.dexeditor.GraphDot.Method;
import modder.hub.dexeditor.smali.*;

/*
Author @developer-krushna
*/

public class SmaliMethodListFragment extends DialogFragment {
	
	private String save = "";
	private double map_number = 0;
	private double n = 0;
	private String name = "";
	private String charSeq = "";
	private String methodName = "";
	private String parameters = "";
	private String smaliFilePath = "";
	private String className = "";
	private ArrayList<HashMap<String, Object>> methodInfo = new ArrayList<>();
	private ProgressDialog coreprog;
	private ProgressDialog coreprog2;
	private LinearLayout linear1;
	private Toolbar toolbar;
	private ListView listview1;
	private String lineNumber;
	
	private Intent i = new Intent();
	private Intent i2 = new Intent();
	private SharedPreferences pref;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.list_method_layout, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		toolbar = _view.findViewById(R.id.toolbar);
		listview1 = _view.findViewById(R.id.listview1);
		pref = getContext().getSharedPreferences("pref", Activity.MODE_PRIVATE);
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				lineNumber = methodInfo.get((int)_position).get("LineNumber").toString();
				DialogLineNumberListener activity = (DialogLineNumberListener) getActivity();
				activity._updateEditorLineNumber(lineNumber);
				dismiss();
				
			}
		});
		
		/* I am really fool if you are feeling */
		listview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				PopupMenu popup = new PopupMenu(getActivity(), _param2);
				Menu menu = popup.getMenu();
				menu.add("View flowchart");
				menu.add("Smali to Java");
				popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
					public boolean onMenuItemClick(MenuItem item) {
						switch (item.getTitle().toString()) {
							case "View flowchart":
							methodFlowChart(methodInfo.get((int)_position).get("MethodName").toString());
							return true;
							case "Smali to Java":
							smali2Java(methodInfo.get((int)_position).get("MethodName").toString());
							return true;
							default: return false;
						}
					}
				});
				popup.show();
				return true;
			}
		});
		
	}
	/*
	Author @developer-krushna
	*/
	private void initializeLogic() {
		if (getDialog() != null && getDialog().getWindow() != null) {
			getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); 
			getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		}
		toolbar.setTitle("Navigation");
		toolbar.inflateMenu(R.menu.close_menu);
		Menu menu = toolbar.getMenu();
		MenuItem searchItem = menu.findItem(R.id.search);
		MenuItem closeItem = menu.findItem(R.id.close);
		Drawable iconDrawable = closeItem.getIcon();
		iconDrawable.setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorBlack), PorterDuff.Mode.SRC_IN);
		searchItem.setVisible(true);
		androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) menu.findItem(R.id.search).getActionView();
		searchView.setQueryHint("Search");
		searchView.setMaxWidth(Integer.MAX_VALUE);
		searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
			@Override public boolean onQueryTextSubmit(String query){ 
				charSeq = query; 
				_Search(charSeq);
				return false; } 
			@Override public boolean onQueryTextChange(String query){
				charSeq = query; 
				_Search(charSeq);
				return true;
			}});
		
		final Handler handler = new Handler(Looper.getMainLooper());
		final int LINES_PER_ITERATION = 100; // Adjust this value based on your needs
		
		final Runnable updateList = new Runnable() {
			@Override
			public void run() {
				new AsyncTask<Void, Void, List<HashMap<String, Object>>>() {
					@Override
					protected List<HashMap<String, Object>> doInBackground(Void... voids) {
						List<HashMap<String, Object>> tempMethodInfo = new ArrayList<>();
						
						try {
							BufferedReader smaliFile = new BufferedReader(new FileReader(smaliFilePath));
							String line;
							int lineIndex = 0;
							
							while ((line = smaliFile.readLine()) != null) {
								String trimLine = line.trim();
								if (!trimLine.isEmpty()) {
									String[] splitLine = trimLine.split(" ");
									if (".method".equals(splitLine[0])) {
										String curMethodName = splitLine[splitLine.length - 1];
										HashMap<String, Object> _item = new HashMap<>();
										_item.put("MethodName", curMethodName);
										_item.put("LineNumber", String.valueOf(lineIndex));
										tempMethodInfo.add(_item);
									}
								}
								
								lineIndex++;
							}
						} catch (Exception e) {
							
						}
						
						return tempMethodInfo;
					}
					
					@Override
					protected void onPostExecute(List<HashMap<String, Object>> result) {
						// Update UI on the main thread after the background task is complete
						if (result != null && !result.isEmpty()) {
							try{
								methodInfo.addAll(result);
								save = new Gson().toJson(methodInfo);
								listview1.setFastScrollEnabled(true);
								listview1.setAdapter(new Listview1Adapter(methodInfo));
								((BaseAdapter) listview1.getAdapter()).notifyDataSetChanged();
							} catch (final Exception e){
								getActivity().runOnUiThread(new Runnable(){
									@Override
									public void run(){
										showExceptionDlg(e);
									}
								});
								
							}
						}
					}
				}.execute();
			}
		};
		handler.postDelayed(updateList, 200);
		
		// Close menu click listener
		toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()) {
					case R.id.close:
					// Cancel the background task if the close menu is clicked
					handler.removeCallbacks(updateList);
					dismiss();
					break;
				}
				return true;
			}
		});
		
		
		
	}
	
	public String _getTextBefore(final String _inputText, final String _targetText) {
		int indexOfOpeningParenthesis = _inputText.indexOf(_targetText);
		if (indexOfOpeningParenthesis != -1) {
			String textBeforeParenthesis = _inputText.substring(0, indexOfOpeningParenthesis);
			return (textBeforeParenthesis);
		}
		return ("");
	}
	
	
	public String _getTextAfter(final String _inputText, final String _targetText) {
		int indexOfOpeningParenthesis = _inputText.indexOf(_targetText);
		if (indexOfOpeningParenthesis != -1) {
			String textAfterParenthesis = _inputText.substring(indexOfOpeningParenthesis + 1);
			return ("(".concat(textAfterParenthesis));
		}
		return ("");
	}
	
	public void methodFlowChart(String methodName){
		_processing_dlg(true);
		new Thread() {
			@Override
			public void run() {
				try {
					String pictureFormat = "png";
					List<String> methodsToDraw = new ArrayList<>();
					methodsToDraw.add(methodName);
					String[] methodsToDrawArray = methodsToDraw != null ? methodsToDraw.toArray(new String[0]) : null;
					final DrawFlowDiagram drawFlowDiagram = new DrawFlowDiagram(smaliFilePath, pictureFormat, methodsToDrawArray, "", "");
					drawFlowDiagram.run();
					getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							for (Method method : drawFlowDiagram.getClassInSmali().getMethodDict().values()) {
								final String dotSourceCode = drawFlowDiagram.drawMethodFlowDiagram(method);
								_processing_dlg(false);
								_f(true);
								new Thread(new Runnable() {
									@Override
									public void run(){
										try {
											String url = "https://timscriptov.ru/graphviz/index.php"; //Api by @Timscriptov
											java.net.HttpURLConnection connection = (java.net.HttpURLConnection) new java.net.URL(url).openConnection();
											connection.setRequestMethod("POST");
											connection.setDoOutput(true);
											String formData = "dot_diagram=" + dotSourceCode;
											byte[] postData = formData.getBytes("UTF-8");
											connection.getOutputStream().write(postData);
											
											int responseCode = connection.getResponseCode();
											if (responseCode == java.net.HttpURLConnection.HTTP_OK) {
												File pngFile = new File(getActivity().getFilesDir() + "/", "SmaliFlowChart.png");
												writeToFile(pngFile, connection.getInputStream());
												_f(false);
												i.setClass(getContext().getApplicationContext(), modder.hub.dexeditor.ImageViewerActivity.class);
												i.putExtra("Title", className + "." + _getTextBefore(methodName, "("));
												i.putExtra("Subtitle", _getTextAfter(methodName, "("));
												startActivity(i);
											}
										} catch (final Exception e) {
											_f(false);
											getActivity().runOnUiThread(new Runnable(){
												@Override
												public void run(){
													showExceptionDlg(e);
												}
											});
										}
									}
								}).start();
							}
						}
					});
				} catch (final Exception e) {
					_processing_dlg(false);
					getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							showExceptionDlg(e);
						}
					});
				}
			}
		}.start();
	}
	
	public void smali2Java(final String methodName) {
		final Handler handler = new Handler(Looper.getMainLooper());
		final Runnable updateList = new Runnable() {
			@Override
			public void run() {
				_processing_dlg(true);
				new AsyncTask<Void, Void, String>() {
					@Override
					protected String doInBackground(Void... voids) {
						try {
							String[] methodsToDraw = {methodName};
							SmaliMethodBody smaliParser = new SmaliMethodBody(smaliFilePath, methodsToDraw);
							return Smali2Java.translate(smaliParser.parseClassInSmali());
							
						} catch (final Exception e) {
							// Handle exceptions or show an error dialog
							_processing_dlg(false);
							getActivity().runOnUiThread(new Runnable(){
								@Override
								public void run(){
									showExceptionDlg(e);
								}
							});
							return null;
						}
					}
					
					@Override
					protected void onPostExecute(String extractedCode) {
						// Update UI on the main thread after the background task is complete
						_processing_dlg(false);
						if (extractedCode != null) {
							try {
								String path = getActivity().getFilesDir() + "/Method2Java.java";
								FileUtil.writeFile(path, extractedCode);
								i2.setClass(getContext().getApplicationContext(), modder.hub.dexeditor.JavaViewActivity.class);
								i2.putExtra("Method2JavaTitle", className + "." + _getTextBefore(methodName, "("));
								i2.putExtra("Method2JavaSubtitle", _getTextAfter(methodName, "("));
								i2.putExtra("Method2JavaPath", path);
								startActivity(i2);
								
							} catch (Exception e) {
								// Handle exceptions or show an error dialog
								getActivity().runOnUiThread(new Runnable(){
									@Override
									public void run(){
										showExceptionDlg(e);
									}
								});
							}
						}
					}
				}.execute();
			}
		};
		handler.postDelayed(updateList, 200);
	}
	
	public void _processing_dlg(final boolean _ifShow) {
		if (_ifShow) {
			if (coreprog == null){
				coreprog = new ProgressDialog(getActivity());
				coreprog.setCancelable(false);
				coreprog.setCanceledOnTouchOutside(false);
				
				coreprog.requestWindowFeature(Window.FEATURE_NO_TITLE);  coreprog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
				
			}
			coreprog.setMessage(null);
			getActivity().runOnUiThread(new Runnable(){
				@Override
				public void run() {
					
					coreprog.show();
				}
			});
			// custom view name here 
			coreprog.setContentView(R.layout.loading);
			LinearLayout linear2 = (LinearLayout)coreprog.findViewById(R.id.linear2);
			LinearLayout background = (LinearLayout)coreprog.findViewById(R.id.background);
			LinearLayout layout_progress = (LinearLayout)coreprog.findViewById(R.id.layout_progress);
			
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(); 
			gd.setColor(Color.parseColor("#E0E0E0")); /* color */
			gd.setCornerRadius(40); /* radius */
			gd.setStroke(0, Color.WHITE); /* stroke heigth and color */
			linear2.setBackground(gd);
			
			RadialProgressView progress = new RadialProgressView(getActivity());
			layout_progress.addView(progress);
			
		}
		else {
			if (coreprog != null){
				getActivity().runOnUiThread(new Runnable(){
					@Override
					public void run() {
						
						coreprog.dismiss();
					}
				});
			}
		}
	}
	
	public void _f(final boolean _ifShow) {
		if (_ifShow) {
			if (coreprog2 == null) {
				coreprog2 = new ProgressDialog(getActivity());
				coreprog2.setCancelable(false);
				coreprog2.setCanceledOnTouchOutside(false);
				coreprog2.requestWindowFeature(Window.FEATURE_NO_TITLE); 
				coreprog2.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
			}
			coreprog2.setMessage(null);
			try { 
				coreprog2.show();
			} catch (WindowManager.BadTokenException e){
				
			}
			coreprog2.setContentView(R.layout.loading);
			LinearLayout linear2 = (LinearLayout)coreprog2.findViewById(R.id.linear2);
			
			LinearLayout background = (LinearLayout)coreprog2.findViewById(R.id.background);
			
			LinearLayout layout_progress = (LinearLayout)coreprog2.findViewById(R.id.layout_progress);
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor("#E0E0E0"));
			gd.setCornerRadius(40);
			gd.setStroke(0, Color.WHITE); 
			linear2.setBackground(gd);
			RadialProgressView progress = new RadialProgressView(getActivity());
			layout_progress.addView(progress);
		}
		else {
			if (coreprog2 != null) {
				getActivity().runOnUiThread(new Runnable(){
					@Override
					public void run() {
						
						coreprog2.dismiss();
					}
				});
			}
		}
	}
	
	
	
	public void updateUi(final String _smaliFilePath, final String _className) {
		smaliFilePath = _smaliFilePath;
		className = _className;
	}
	
	public interface DialogLineNumberListener {
		
		void _updateEditorLineNumber(String line);
	}
	
	private void writeToFile(File file, InputStream inputStream) throws Exception {
		byte[] buffer = new byte[4096];
		int bytesRead;
		FileOutputStream outputStream = new FileOutputStream(file);
		
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}
		
		outputStream.close();
		inputStream.close();
		
	}
	
	public void showDlg(String title, String message){
		getActivity().runOnUiThread(new Runnable(){
			@Override
			public void run(){
				Notify_MT.Notify(getActivity(), title, message, getResources().getString(R.string.close_btn));
				
			}
		});
	}
	
	public void showExceptionDlg(Exception e){
		getActivity().runOnUiThread(new Runnable(){
			@Override
			public void run(){
				Notify_MT.Notify(getActivity(), "Error", e.getMessage(), getResources().getString(R.string.close_btn));
				
			}
		});
	}
	
	
	
	
	
	public void _Search(final String _charSeq) {
		try{
			methodInfo.clear();
			methodInfo = new Gson().fromJson(save, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			map_number = methodInfo.size();
			n = map_number - 1;
			for(int i = 0; i < (int)(map_number); i++) {
				name = methodInfo.get((int)n).get("MethodName").toString();
				if (!(_charSeq.length() > name.length()) && name.toLowerCase().contains(_charSeq.toLowerCase())) {
					
				}
				else {
					methodInfo.remove((int)(n));
				}
				n--;
			}
			listview1.setAdapter(new Listview1Adapter(methodInfo));
			((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
			
		}catch(java.lang.NullPointerException e){}
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.method_list, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final TextView textview3 = _view.findViewById(R.id.textview3);
			final TextView method_name = _view.findViewById(R.id.method_name);
			final TextView return_type = _view.findViewById(R.id.return_type);
			
			linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)50, (int)3, 0xFFE53935, 0xFFE53935));
			textview3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)50, (int)3, 0xFFE53935, 0xFFE53935));
			methodName = _getTextBefore(_data.get((int)_position).get("MethodName").toString(), "(");
			parameters = _getTextAfter(_data.get((int)_position).get("MethodName").toString(), "(");
			
			lineNumber = _data.get((int)_position).get("LineNumber").toString();
			method_name.setText(methodName);
			return_type.setText(parameters);
			
			return _view;
		}
	}
}
