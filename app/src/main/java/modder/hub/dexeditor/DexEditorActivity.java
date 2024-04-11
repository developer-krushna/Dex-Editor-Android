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



package modder.hub.dexeditor;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

import com.android.tools.smali.baksmali.Adaptors.ClassDefinition;
import com.android.tools.smali.baksmali.BaksmaliOptions;
import com.android.tools.smali.dexlib2.iface.ClassDef;
import com.android.tools.smali.util.IndentingWriter;
import com.android.tools.smali.baksmali.formatter.BaksmaliWriter;

/*
Author @developer-krushna
*/

public class DexEditorActivity extends AppCompatActivity {
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private FloatingActionButton fab_delete;
	
	private HashMap<String, Object> map = new HashMap<>();
	
	private String listData = "";
	private String dex_path = "";
	
	public static ClassTree classTree;
	
	private LinearLayout fab_bg;
	
	private  SparseBooleanArray selectedItems = new SparseBooleanArray();
	
	public static boolean isChanged = false;
	public static boolean isSaved = false;
	private  boolean isBetweenSelectionActive = false;
	private  boolean isSelectionMode = false;
	
	private ArrayList<String> classList = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> classMap = new ArrayList<>();
	
	private RecyclerView recyclerview1;
	
	private AlertDialog.Builder d;
	
	
	private ProgressDialog prog;
	private ProgressDialog coreprog;
	
	private int STORAGE_REQUEST_CODE = 1000;
	private  int initialLongPressPosition = RecyclerView.NO_POSITION;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.dex_editor);
		initialize(_savedInstanceState);
		
		// Check Storage permission 
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_REQUEST_CODE);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == STORAGE_REQUEST_CODE) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		
		//ToolBar navigation auto-gererated code by Sketchware Pro
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		
		fab_delete = findViewById(R.id.fab_delete);
		
		recyclerview1 = findViewById(R.id.recyclerview1);
		d = new AlertDialog.Builder(this);
		
		
		fab_delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				prog = new ProgressDialog(DexEditorActivity.this);
				prog.setCancelable(false);
				prog.setCanceledOnTouchOutside(false);
				prog.getWindow().setBackgroundDrawable(createDrawable(20, Color.parseColor("#FFFFFF")));
				prog.show();
				//Keep screen on during processing
				getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
				// UI Handler
				final Handler uiHandler = new Handler(Looper.getMainLooper());
				new Thread() {
					public void run() {
						Looper.prepare();
						try {
							ArrayList<Integer> selectedPositions = new ArrayList<>();
							for (int i = 0; i < (int)(classMap.size()); i++) {
								if (selectedItems.get(i)) {
									//add selected
									selectedPositions.add(i);
								}
							}
							for (int i = ((int) selectedPositions.size() - 1); i > -1; i--) {
								int position = selectedPositions.get(i);
								HashMap<String, Object> selectedItem = classMap.get(position);
								String classListItem = selectedItem.get("ClassList").toString();
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										classTree.removeClass(classTree.tree.getCurPath() + classListItem);
										prog.setMessage(classListItem);
									}
								});
								classMap.remove(position);
							}
							selectedItems.clear();
							isChanged = true;
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									_clearChoosed();
									recyclerview1.getAdapter().notifyDataSetChanged();
									_refreshRecyclerView();
								}
							});
						} catch (final Exception e) {
							uiHandler.post(new Runnable() {
								@Override
								public void run() {
									ErrorDlg(e.toString());
								}
							});
						}
						uiHandler.post(new Runnable() {
							@Override
							public void run() {
								// Stop screen on
								getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
								// Progress dismiss
								prog.dismiss();
							}
						});
					}
				}.start();
			}
		});
	}
	
	private void initializeLogic() {
		dex_path = getIntent().getStringExtra("Path");
		setTitle("DexEditor");
		_showProcessingProgress(true);
		_fab_Initialization();
		// Delete fab colour changed to red color
		fab_delete.setBackgroundTintList(ColorStateList.valueOf(0xFFF44336));
		fab_delete.hide();
		new Thread() {
			@Override
			public void run() {
				try {
					classTree = new ClassTree(dex_path);
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							classList = classTree.getList("/");
							getSupportActionBar().setSubtitle("/".concat(classTree.tree.getCurPath()));
							for (int i = 0; i < (int)(classList.size()); i++) {
								map = new HashMap<>();
								map.put("ClassList", classList.get((int)(i)));
								classMap.add(map);
								//first refresh RecyclerView
								_refreshRecyclerView();
								// then load listData
								listData = new Gson().toJson(classMap);
							}
						}
					});
				} catch (Exception e) {
					_showProcessingProgress(false);
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							final AlertDialog.Builder d_build = new AlertDialog.Builder(DexEditorActivity.this);
							d_build.setTitle("Error");
							d_build.setMessage("An error occurred while processing dex\n\n---StackTrace---\n\n" + e.getMessage());
							d_build.setPositiveButton("Go back", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									finish();
								}
							});
							d_build.setCancelable(false);
							// Dialog Background Animation 
							Notify_MT.Dlg_Style(d_build);
						}
					});
				}
				// Cancel processing progress
				_showProcessingProgress(false);
			} }.start();
	}
	
	@Override
	public void onBackPressed() {
		if (classTree.tree.getCurPath().equals("")) {
			if (isChanged) {
				d.setTitle("Info");
				d.setMessage("Do you want to compile and save the file ?");
				d.setPositiveButton("Save and Exit", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						final AlertProgress progressDialog = new AlertProgress(DexEditorActivity.this);
						progressDialog.setTitle(Uri.parse(dex_path).getLastPathSegment() + " " + "(1/1)");
						progressDialog.setMessage("Compiling...");
						progressDialog.show();
						final Handler mHandler = new Handler() {
							public void handleMessage(Message msg) {
								// Dismiss the progress after successful or failure 
								progressDialog.dismiss();
							} 
						};
						new Thread() {
							public void run() {
								Looper.prepare();
								try {
									classTree.saveDexFile(dex_path, new ClassTree.DexSaveProgress() {
										@Override
										public void onProgress(int progress, int total) {
											runOnUiThread(new Runnable() {
												@Override
												public void run() {
													progressDialog.setProgress(progress, total);
												}
											});
										}
										
										public void onMessage(String name) {
											runOnUiThread(new Runnable() {
												@Override
												public void run() {
													progressDialog.setMessage(name);
												}
											});
										}
										
									});
									
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											SketchwareUtil.showMessage(getApplicationContext(), "Success");
											finish();
										}
									});
									
								} catch (Exception e) {
									ErrorDlg("An error occurred while processing dex\n\n---StackTrace---\n\n" + e.toString());
								}
								mHandler.sendEmptyMessage(0);
								Looper.loop();
							} }.start();
					}
				});
				d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						d.create().dismiss();
					}
				});
				d.setNeutralButton("Exit Directly", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						isChanged = false;
						isSaved = false;
						classTree.clearAll();
						finish();
						d.create().dismiss();
					}
				});
				Notify_MT.Dlg_Style(d);
			}
			else {
				classMap.clear();
				classTree.clearAll();
				isSaved = false;
				isChanged= false;
				finish();
			}
		}
		else {
			classMap.clear();
			classList = classTree.getList("../");
			_clearChoosed();
			for (int i = 0; i < (int)(classList.size()); i++) {
				map = new HashMap<>();
				map.put("ClassList", classList.get((int)(i)));
				classMap.add(map);
				_refreshRecyclerView();
				getSupportActionBar().setSubtitle("/" + classTree.tree.getCurPath());
			}
		}
	}
	public void _refresh() {
		String cuPath = classTree.tree.getCurPath();
		classMap.clear();
		classList = classTree.tree.list(cuPath);
		for (int i = 0; i < (int)(classList.size()); i++) {
			map = new HashMap<>();
			map.put("ClassList", classList.get((int)(i)));
			classMap.add(map);
			recyclerview1.setAdapter(new Recyclerview1Adapter(classMap));
			recyclerview1.setLayoutManager(new LinearLayoutManager(this));
			listData = new Gson().toJson(classMap);
		}
	}
	
	public void ErrorDlg(String msg){
		Notify_MT.Notify(this, getResources().getString(R.string.error_title), msg, getResources().getString(R.string.close_btn));
	}
	
	
	public void _clearChoosed() {
		if (isSelectionMode) {
			isSelectionMode = false;
			initialLongPressPosition = RecyclerView.NO_POSITION;
			selectedItems.clear();
			_show_multiple_fabs(false);
			fab_delete.hide();
			recyclerview1.getAdapter().notifyDataSetChanged();
			recyclerview1.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
				@Override
				public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
					return false;
				}
				
				@Override
				public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
				}
				
				@Override
				public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
				}
			});
		}
	}
	
	
	public void _showProcessingProgress(final boolean _ifShow) {
		if (_ifShow) {
			if (coreprog == null){
				coreprog = new ProgressDialog(this);
				coreprog.setCancelable(false);
				coreprog.setCanceledOnTouchOutside(false);
				coreprog.requestWindowFeature(Window.FEATURE_NO_TITLE);  
				coreprog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				
			}
			coreprog.setMessage(null);
			runOnUiThread(new Runnable(){
				@Override
				public void run() {
					coreprog.show();
				}
			});
			// custom view name here 
			coreprog.setContentView(R.layout.loading);
			//Initialisation of views
			LinearLayout linear2 = (LinearLayout)coreprog.findViewById(R.id.linear2);
			LinearLayout background = (LinearLayout)coreprog.findViewById(R.id.background);
			LinearLayout layout_progress = (LinearLayout)coreprog.findViewById(R.id.layout_progress);
			
			linear2.setBackground(createDrawable(40, Color.parseColor("#E0E0E0")));
			
			//RadialProgressView
			RadialProgressView progress = new RadialProgressView(this);
			layout_progress.addView(progress);
			
		}
		else {
			if (coreprog != null){
				runOnUiThread(new Runnable(){
					@Override
					public void run() {
						coreprog.dismiss();
					}
				});
			}
		}
	}
	
	
	
	public void _toggleSelection(final double _position, final View _view) {
		if (selectedItems.get((int)_position, false)) {
			selectedItems.delete((int)_position);
			_view.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_corner_ripple));
		}
		else {
			selectedItems.put((int)_position, true);
			fab_delete.show();
			_view.setBackground(createDrawable(13, 0xFF42A5F5));
			_show_multiple_fabs(true);
		}
		if (selectedItems.size() == 0) {
			_show_multiple_fabs(false);
			fab_delete.hide();
			isSelectionMode = false;
		}
	}
	
	
	public void _refreshRecyclerView() {
		recyclerview1.setAdapter(new Recyclerview1Adapter(classMap));
		recyclerview1.setLayoutManager(new LinearLayoutManager(this));
	}
	
	
	public void _show_multiple_fabs(final boolean show) {
		if (show) {
			fab_bg.setVisibility(View.VISIBLE);
			fab_bg.setTranslationY((float)(getDip(50)));
			fab_bg.setAlpha((float)(0));
			fab_bg.animate().setDuration(200).alpha(1f).translationY(0);
		}
		else {
			fab_bg.setVisibility(View.GONE);
		}
	}
	
	
	public void _fab_Initialization() {
        //load and inflate multiple_fabs layout
		View cv = getLayoutInflater().inflate(R.layout.multiple_fabs, null);
		
		// Initialisation of views 
		fab_bg = (LinearLayout) cv.findViewById(R.id.linear_bg);
		FloatingActionButton fab_select_rest = (FloatingActionButton) fab_bg.findViewById(R.id.fab_select_rest);
		FloatingActionButton fab_clear = (FloatingActionButton) fab_bg.findViewById(R.id.fab_clear);
        
		//First remove
		((ViewGroup)fab_bg.getParent()).removeView(fab_bg);
		
		// Then add fab_bg as a parent view of fab_delete
		((ViewGroup)fab_delete.getParent()).addView(fab_bg);
		
		// fab background color tint
		fab_clear.setBackgroundTintList(ColorStateList.valueOf(0xFFBEBEC3));
		fab_select_rest.setBackgroundTintList(ColorStateList.valueOf(0xFFBEBEC3));
		
		
		//Click listener
		fab_select_rest.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				for (int i = 0; i < (int)(classMap.size()); i++) {
					if (selectedItems.get(i, false)) {
						selectedItems.delete(i);
					}
					else {
						selectedItems.put(i, true);
					}
				}
				recyclerview1.getAdapter().notifyDataSetChanged();
			}
		});
		
		//fab_clear click listener
		fab_clear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_clearChoosed();
			}
		});
		_show_multiple_fabs(false);
	}
	
	public static String getSmaliByType(ClassDef classDef, String Type) throws Exception {
		StringWriter stringWriter = new StringWriter();
		BaksmaliWriter writer = new BaksmaliWriter(stringWriter);
		ClassDefinition classDefinition = new ClassDefinition(new BaksmaliOptions(), classDef);
		classDefinition.writeTo(writer);
		writer.close();
		String code = stringWriter.toString();
		return code;
	}
	
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
		
	}
	
	private GradientDrawable createDrawable(int cornerRadius, int color) {
		GradientDrawable drawable = new GradientDrawable();
		drawable.setCornerRadius(cornerRadius);
		drawable.setColor(color);
		return drawable;
	}
	
	public String customException(Exception e){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String exceptionDetails = sw.toString();
		return exceptionDetails;
		
	}
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.class_holder, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			// Initialize views within the item layout
			final LinearLayout linear_bg = _view.findViewById(R.id.linear_bg);
			final LinearLayout icon_background = _view.findViewById(R.id.icon_background);
			final TextView name = _view.findViewById(R.id.name);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			
			// Retrieve class name from the data list based on current position
			String className = _data.get((int)_position).get("ClassList").toString();
			
			// Set class name as text for the name TextView
			name.setText(className);
			
			// Check if classTree and classTree.tree are not null
			if (classTree != null && classTree.tree != null) {
				// Check if the class is a directory or not, and set appropriate icon and linear_bg color
				if (classTree.tree.isDirectory(className)) {
					imageview1.setImageResource(R.drawable.ic_folder_mt);
					icon_background.setBackground(createDrawable(8, 0xFF252525));
				} else {
					icon_background.setBackground(createDrawable(100, 0xFF3860AF));
					imageview1.setImageResource(R.drawable.smali_icon_mt);
				}
			}
			
			// Set linear_bg color based on selection state of the item
			if (selectedItems.get((int)_position, false)) {
				linear_bg.setBackground(createDrawable(13, 0xFF42A5F5));
			}
			else {
				linear_bg.setBackground(ContextCompat.getDrawable(DexEditorActivity.this, R.drawable.rounded_corner_ripple));
			}
			
			// Set click listener on the item linear_bg
			linear_bg.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (isSelectionMode) {
						isBetweenSelectionActive = false;
						initialLongPressPosition = RecyclerView.NO_POSITION;
						_toggleSelection(_position, linear_bg);
					}
					else {
						String curFile = className;
						if (classTree.tree.isDirectory(curFile)) {
							// Update RecyclerView with list of classes within the directory
							classMap.clear();
							classList = classTree.getList(curFile);
							for (int i = 0; i < (int)(classList.size()); i++) {
								// Convert class list map to HashMap
								map = new HashMap<>();
								map.put("ClassList", classList.get((int)(i)));
								// Add to classMap
								classMap.add(map);
								getSupportActionBar().setSubtitle("/".concat(classTree.tree.getCurPath()));
								notifyDataSetChanged();
								_refreshRecyclerView();
							}
							return;
						} else {
							try{
								// Get Smali data for classDef
								String smali = getSmaliByType(classTree.classMap.get(classTree.tree.getCurPath() + curFile), "L" + (classTree.tree.getCurPath() + curFile) + ";");
								byte[] b = smali.getBytes();
								
								// Initialize TextEditorActivity 
								TextEditorActivity editor = new TextEditorActivity();
								editor.classTree = classTree;
								// Start activity 
								Intent i = new Intent (DexEditorActivity.this, editor.getClass());
								i.putExtra("Smali", b);
								i.putExtra("ClassName", className);
								startActivity(i);
								com.blogspot.atifsoftwares.animatoolib.Animatoo.animateSlideRight(DexEditorActivity.this);
								return;
							} catch (Exception e){
								Notify_MT.Notify(DexEditorActivity.this, "Error", customException(e), "Close");
							}
						}
					}
				}
			});
			
			// Set long click listener on the item linear_bg
			linear_bg.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View _view) {
					if (!isSelectionMode) {
						isSelectionMode = true;
					}
					_toggleSelection(_position, linear_bg);
					if (!isBetweenSelectionActive) {
						if (initialLongPressPosition == RecyclerView.NO_POSITION) {
							initialLongPressPosition = _position;
						}
						else {
							isBetweenSelectionActive = true;
							int start = Math.min(initialLongPressPosition, _position);
							int end = Math.max(initialLongPressPosition, _position);
							notifyDataSetChanged();
							for (int i = start; i <= end; i++) {
								selectedItems.put(i, true);
							}
							initialLongPressPosition = RecyclerView.NO_POSITION;
							isBetweenSelectionActive = false;
						}
					}
					else {
						_toggleSelection(_position, linear_bg);
					}
					return true;
				}
			});
		}
		
		@Override
		public int getItemCount() {
			return _data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			public ViewHolder(View v) {
				super(v);
			}
		}
	}
}
