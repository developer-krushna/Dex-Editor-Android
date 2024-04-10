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
	private FloatingActionButton _fab;
	private HashMap<String, Object> map = new HashMap<>();
	private String listData = "";
	private String dex_path = "";
	public static ClassTree classTree;
	private LinearLayout fab_bg;
	private  int initialLongPressPosition = RecyclerView.NO_POSITION;
	private  boolean isBetweenSelectionActive = false;
	private  boolean isSelectionMode = false;
	private  SparseBooleanArray selectedItems = new SparseBooleanArray();
	public static boolean isChanged = false;
	public static boolean isSaved = false;
	
	private ArrayList<String> classList = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> File_Map = new ArrayList<>();
	
	private RecyclerView recyclerview1;
	
	private AlertDialog.Builder d;
	private ProgressDialog prog;
	private ProgressDialog coreprog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.dex_editor);
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
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
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		_fab = findViewById(R.id._fab);
		
		recyclerview1 = findViewById(R.id.recyclerview1);
		d = new AlertDialog.Builder(this);
		
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				prog = new ProgressDialog(DexEditorActivity.this);
				prog.setCancelable(false);
				prog.setCanceledOnTouchOutside(false);
				int cornerRadius = 20;
				android.graphics.drawable.GradientDrawable ରାଧେ = new android.graphics.drawable.GradientDrawable();
				ରାଧେ.setColor(Color.parseColor("#FFFFFF"));
				ରାଧେ.setCornerRadius(cornerRadius);
				prog.getWindow().setBackgroundDrawable(ରାଧେ);
				prog.show();
				getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
				final Handler uiHandler = new Handler(Looper.getMainLooper());
				new Thread() {
					public void run() {
						Looper.prepare();
						try {
							ArrayList<Integer> selectedPositions = new ArrayList<>();
							for (int i = 0; i < (int)(File_Map.size()); i++) {
								if (selectedItems.get(i)) {
									selectedPositions.add(i);
								}
							}
							for (int i = ((int) selectedPositions.size() - 1); i > -1; i--) {
								int position = selectedPositions.get(i);
								HashMap<String, Object> selectedItem = File_Map.get(position);
								String classListItem = selectedItem.get("ClassList").toString();
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										classTree.removeClass(classTree.tree.getCurPath() + classListItem);
										prog.setMessage(classListItem);
									}
								});
								File_Map.remove(position);
							}
							selectedItems.clear();
							isChanged = true;
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									_clearChoosed();
									recyclerview1.getAdapter().notifyDataSetChanged();
									_refreshRecycler();
								}
							});
						} catch (final Exception e) {
							uiHandler.post(new Runnable() {
								@Override
								public void run() {
									Notify_MT.Notify(DexEditorActivity.this, "Error", e.toString(), "close");
								}
							});
						}
						uiHandler.post(new Runnable() {
							@Override
							public void run() {
								getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
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
		_processing_dlg(true);
		_fab_Initialization();
		_fab.hide();
		new Thread() {
			@Override
			public void run() {
				try {
					classTree = new ClassTree(getIntent().getStringExtra("Path"));
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							classList = classTree.getList("/");
							getSupportActionBar().setSubtitle("/".concat(classTree.tree.getCurPath()));
							for (int i = 0; i < (int)(classList.size()); i++) {
								map = new HashMap<>();
								map.put("ClassList", classList.get((int)(i)));
								File_Map.add(map);
								_refreshRecycler();
								listData = new Gson().toJson(File_Map);
							}
						}
					});
				} catch (Exception e) {
					_processing_dlg(false);
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
							d_build.create().show();
						}
					});
				}
				_processing_dlg(false);
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
											DexEditorActivity.this.runOnUiThread(new Runnable() {
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
									Notify_MT.Notify(DexEditorActivity.this, "Error", "An error occurred while processing dex\n\n---StackTrace---\n\n" + e.toString(), "Close");
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
				File_Map.clear();
				classTree.clearAll();
				isSaved = false;
				isChanged= false;
				finish();
			}
		}
		else {
			File_Map.clear();
			classList = classTree.getList("../");
			_clearChoosed();
			for (int i = 0; i < (int)(classList.size()); i++) {
				map = new HashMap<>();
				map.put("ClassList", classList.get((int)(i)));
				File_Map.add(map);
				_refreshRecycler();
				getSupportActionBar().setSubtitle("/" + classTree.tree.getCurPath());
			}
		}
	}
	public void _refresh() {
		String cuPath = classTree.tree.getCurPath();
		File_Map.clear();
		classList = classTree.tree.list(cuPath);
		for (int i = 0; i < (int)(classList.size()); i++) {
			map = new HashMap<>();
			map.put("ClassList", classList.get((int)(i)));
			File_Map.add(map);
			recyclerview1.setAdapter(new Recyclerview1Adapter(File_Map));
			recyclerview1.setLayoutManager(new LinearLayoutManager(this));
			listData = new Gson().toJson(File_Map);
		}
	}
	
	
	public void _clearChoosed() {
		if (isSelectionMode) {
			isSelectionMode = false;
			initialLongPressPosition = RecyclerView.NO_POSITION;
			selectedItems.clear();
			_Show(false);
			_fab.hide();
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
	
	
	public void _processing_dlg(final boolean _ifShow) {
		if (_ifShow) {
			if (coreprog == null){
				coreprog = new ProgressDialog(this);
				coreprog.setCancelable(false);
				coreprog.setCanceledOnTouchOutside(false);
				coreprog.requestWindowFeature(Window.FEATURE_NO_TITLE);  coreprog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
				
			}
			coreprog.setMessage(null);
			runOnUiThread(new Runnable(){
				@Override
				public void run()
				{
					
					coreprog.show();
				}
			});
			// custom view name here 
			coreprog.setContentView(R.layout.loading);
			//Initialisation 
			LinearLayout linear2 = (LinearLayout)coreprog.findViewById(R.id.linear2);
			LinearLayout background = (LinearLayout)coreprog.findViewById(R.id.background);
			LinearLayout layout_progress = (LinearLayout)coreprog.findViewById(R.id.layout_progress);
			
			//background 
			GradientDrawable gd = new GradientDrawable(); 
			gd.setColor(Color.parseColor("#E0E0E0")); /* color */
			gd.setCornerRadius(40); /* radius */
			gd.setStroke(0, Color.WHITE); /* stroke heigth and color */
			linear2.setBackground(gd);
			
			//RadialProgressView
			RadialProgressView progress = new RadialProgressView(this);
			layout_progress.addView(progress);
			
		}
		else {
			if (coreprog != null){
				runOnUiThread(new Runnable(){
					@Override
					public void run()
					{
						
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
			_fab.show();
			_view.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)13, 0xFF42A5F5));
			_Show(true);
		}
		if (selectedItems.size() == 0) {
			_Show(false);
			_fab.hide();
			isSelectionMode = false;
		}
	}
	
	
	public void _refreshRecycler() {
		recyclerview1.setAdapter(new Recyclerview1Adapter(File_Map));
		recyclerview1.setLayoutManager(new LinearLayoutManager(this));
	}
	
	
	public void _SetRadiusToView(final View _view, final double _radius, final String _Colour) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(); gd.setColor(Color.parseColor(_Colour)); gd.setCornerRadius((int)_radius); _view.setBackground(gd);
	}
	
	
	public void _Show(final boolean _Show) {
		if (_Show) {
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
		View cv = getLayoutInflater().inflate(R.layout.multiple_fabs, null);
		fab_bg = (LinearLayout)cv.findViewById(R.id.linear1);
		FloatingActionButton fab1 = (FloatingActionButton)fab_bg.findViewById(R.id.linear_fab1);
		FloatingActionButton fab2 = (FloatingActionButton)fab_bg.findViewById(R.id.linear_fab2);
		_Remove(fab_bg);
		((ViewGroup)_fab.getParent()).addView(fab_bg);
		fab1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				for (int i = 0; i < (int)(File_Map.size()); i++) {
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
		fab2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (isSelectionMode) {
					isSelectionMode = false;
					initialLongPressPosition = RecyclerView.NO_POSITION;
					selectedItems.clear();
					_Show(false);
					_fab.hide();
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
		});
		_Show(false);
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
	
	
	public void _Remove(final View _view) {
		//By Solo Studio
		((ViewGroup)_view.getParent()).removeView(_view);
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
		
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
			
			final LinearLayout background = _view.findViewById(R.id.background);
			final LinearLayout icon_background = _view.findViewById(R.id.icon_background);
			final TextView name = _view.findViewById(R.id.name);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			
			String className = _data.get((int)_position).get("ClassList").toString();
			name.setText(className);
			
			if (classTree != null && classTree.tree != null) {
				if (classTree.tree.isDirectory(_data.get((int)_position).get("ClassList").toString())) {
					imageview1.setImageResource(R.drawable.ic_folder_mt);
					icon_background.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)6, 0xFF252525));
				}
				else {
					icon_background.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFF3860AF));
					imageview1.setImageResource(R.drawable.smali_icon_mt);
					name.setText(className);
				}
			}
			if (selectedItems.get((int)_position, false)) {
				background.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)13, 0xFF42A5F5));
			}
			else {
				background.setBackground(ContextCompat.getDrawable(DexEditorActivity.this, R.drawable.rounded_corner_ripple));
			}
			background.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (isSelectionMode) {
						isBetweenSelectionActive = false;
						initialLongPressPosition = RecyclerView.NO_POSITION;
						_toggleSelection(_position, background);
					}
					else {
						String curFile = File_Map.get((int)_position).get("ClassList").toString();
						if (classTree.tree.isDirectory(curFile)) {
							File_Map.clear();
							classList = classTree.getList(curFile);
							for (int i = 0; i < (int)(classList.size()); i++) {
								map = new HashMap<>();
								map.put("ClassList", classList.get((int)(i)));
								File_Map.add(map);
								getSupportActionBar().setSubtitle("/".concat(classTree.tree.getCurPath()));
								notifyDataSetChanged();
								_refreshRecycler();
							}
							return;
						} else {
							try{
								String smali = getSmaliByType(classTree.classMap.get(classTree.tree.getCurPath() + curFile), "L" + (classTree.tree.getCurPath() + curFile) + ";");
								byte[] b = smali.getBytes();
								TextEditorActivity editor = new TextEditorActivity();
								editor.classTree = classTree;
								Intent i = new Intent (DexEditorActivity.this, editor.getClass());
								i.putExtra("Smali", b);
								i.putExtra("ClassName", className);
								startActivity(i);
								com.blogspot.atifsoftwares.animatoolib.Animatoo.animateSlideRight(DexEditorActivity.this);
								return;
							}catch (Exception e){
								Notify_MT.Notify(DexEditorActivity.this, "Error", customException(e), "Close");
							}
						}
					}
				}
			});
			background.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View _view) {
					if (!isSelectionMode) {
						isSelectionMode = true;
					}
					_toggleSelection(_position, background);
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
						_toggleSelection(_position, background);
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
