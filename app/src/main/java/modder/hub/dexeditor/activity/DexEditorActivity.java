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


package modder.hub.dexeditor.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.tools.smali.baksmali.Adaptors.ClassDefinition;
import com.android.tools.smali.baksmali.BaksmaliOptions;
import com.android.tools.smali.baksmali.formatter.BaksmaliWriter;
import com.android.tools.smali.dexlib2.iface.ClassDef;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import me.zhanghai.android.fastscroll.*;
import modder.hub.dexeditor.views.*;
import modder.hub.dexeditor.utils.*;
import modder.hub.dexeditor.R;

/*
Author @developer-krushna
Code fixed comments by ChatGPT
*/


public class DexEditorActivity extends AppCompatActivity {
	public static ClassTree classTree;
	public static boolean isChanged;
	public static boolean isSaved;
	
	private AppBarLayout appBarLayout;
	private CoordinatorLayout coordinatorLayout;
	private Toolbar toolbar;
	private ProgressDialog coreProgressDialog;
	private AlertDialog.Builder alertDialogBuilder;
	private LinearLayout fabBackground;
	private FloatingActionButton fabDelete;
	private ImageView listItemIcon;
	private ProgressDialog progressDialog;
	private RecyclerView recyclerView;
	private HashMap<String, Object> classMapItem = new HashMap<>();
	private String listData = "";
	private String dexPath = "";
	private SparseBooleanArray selectedItems = new SparseBooleanArray();
	private boolean isBetweenSelectionActive = false;
	private boolean isSelectionMode = false;
	private ArrayList<String> classList = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> classMap = new ArrayList<>();
	private int STORAGE_REQUEST_CODE = 1000;
	private int initialLongPressPosition = -1;
	private int startPosition = Integer.MAX_VALUE;
	private int itemCount = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dex_editor);
		initialize(savedInstanceState);
		
		// Check for storage permissions
		if (ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == -1) {
			ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, STORAGE_REQUEST_CODE);
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
	
	// Initialize UI components
	private void initialize(Bundle savedInstanceState) {
		appBarLayout = findViewById(R.id._app_bar);
		coordinatorLayout = findViewById(R.id._coordinator);
		toolbar = findViewById(R.id._toolbar);
		
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onBackPressed();
			}
		});
		
		fabDelete = findViewById(R.id.fab_delete);
		recyclerView = findViewById(R.id.recyclerview1);
		alertDialogBuilder = new AlertDialog.Builder(this);
		fabDelete.setOnClickListener(new DeleteButtonClickListener());
	}
	
	// Initialize logic after permissions are granted
	private void initializeLogic() {
		dexPath = getIntent().getStringExtra("Path");
		setTitle("DexEditor");
		showProcessingProgress(true);
		new FastScrollerBuilder(recyclerView).build();
		initializeFab();
		recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_slide_from_bottom));
		fabDelete.setBackgroundTintList(ColorStateList.valueOf(0xFFF44336));
		fabDelete.hide();
		new LoadDexThread().start();
	}
	
	// Thread to load DEX file
	private class LoadDexThread extends Thread {
		@Override
		public void run() {
			try {
				classTree = new ClassTree(dexPath);
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						classList = classTree.getList("/");
						getSupportActionBar().setSubtitle("/".concat(classTree.tree.getCurPath()));
						for (int i = 0; i < classList.size(); i++) {
							classMapItem = new HashMap<>();
							classMapItem.put("ClassList", classList.get(i));
							classMap.add(classMapItem);
							refreshRecyclerView();
							listData = new Gson().toJson(classMap);
						}
					}
				});
			} catch (Exception e) {
				showProcessingProgress(false);
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						AlertDialog.Builder builder = new AlertDialog.Builder(DexEditorActivity.this);
						builder.setTitle("Error");
						builder.setMessage("An error occurred while processing dex\n\n---StackTrace---\n\n" + e.getMessage());
						builder.setPositiveButton("Go back", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int i) {
								finish();
							}
						});
						builder.setCancelable(false);
						Notify_MT.Dlg_Style(builder);
					}
				});
			}
			showProcessingProgress(false);
		}
	}
	
	// Handle back button press
	@Override
	public void onBackPressed() {
		if (classTree.tree.getCurPath().equals("")) {
			if (isChanged) {
				alertDialogBuilder.setTitle("Info");
				alertDialogBuilder.setMessage("Do you want to compile and save the file ?");
				alertDialogBuilder.setPositiveButton("Save and Exit", new SaveAndExitClickListener());
				alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						alertDialogBuilder.create().dismiss();
					}
				});
				alertDialogBuilder.setNeutralButton("Exit Directly", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						isChanged = false;
						isSaved = false;
						classTree.clearAll();
						finish();
						alertDialogBuilder.create().dismiss();
					}
				});
				Notify_MT.Dlg_Style(alertDialogBuilder);
				return;
			}
			classMap.clear();
			classTree.clearAll();
			isSaved = false;
			isChanged = false;
			finish();
			return;
		}
		classMap.clear();
		classList = classTree.getList("../");
		clearChoosed(false);
		for (int i = 0; i < classList.size(); i++) {
			HashMap<String, Object> hashMap = new HashMap<>();
			classMapItem = hashMap;
			hashMap.put("ClassList", classList.get(i));
			classMap.add(classMapItem);
			refreshRecyclerView();
			ActionBar supportActionBar = getSupportActionBar();
			supportActionBar.setSubtitle("/" + classTree.tree.getCurPath());
		}
	}
	
	// Refresh the RecyclerView
	private void refreshRecyclerView() {
		recyclerView.setAdapter(new RecyclerViewAdapter(classMap));
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
	}
	
	// Show or hide the processing progress dialog
	private void showProcessingProgress(boolean show) {
		if (show) {
			if (coreProgressDialog == null) {
				coreProgressDialog = new ProgressDialog(this);
				coreProgressDialog.setCancelable(false);
				coreProgressDialog.setCanceledOnTouchOutside(false);
				coreProgressDialog.requestWindowFeature(1);
				coreProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
			}
			coreProgressDialog.setMessage(null);
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					coreProgressDialog.show();
				}
			});
			coreProgressDialog.setContentView(R.layout.loading);
			LinearLayout linearLayout = (LinearLayout) coreProgressDialog.findViewById(R.id.background);
			((LinearLayout) coreProgressDialog.findViewById(R.id.linear2)).setBackground(createDrawable(40, Color.parseColor("#E0E0E0")));
			((LinearLayout) coreProgressDialog.findViewById(R.id.layout_progress)).addView(new RadialProgressView(this));
		} else if (coreProgressDialog != null) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					coreProgressDialog.dismiss();
				}
			});
		}
	}
	
	// Create a gradient drawable with rounded corners
	private GradientDrawable createDrawable(int cornerRadius, int color) {
		GradientDrawable gradientDrawable = new GradientDrawable();
		gradientDrawable.setCornerRadius(cornerRadius);
		gradientDrawable.setColor(color);
		return gradientDrawable;
	}
	
	// RecyclerView Adapter
	public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
		ArrayList<HashMap<String, Object>> data;
		
		public RecyclerViewAdapter(ArrayList<HashMap<String, Object>> data) {
			this.data = data;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = getLayoutInflater().inflate(R.layout.class_holder, parent, false);
			view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			return new ViewHolder(view);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder holder, final int position) {
			View view = holder.itemView;
			final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linear_bg);
			LinearLayout iconBackground = (LinearLayout) view.findViewById(R.id.icon_background);
			listItemIcon = (ImageView) view.findViewById(R.id.imageview1);
			TextView smaliSymbol = (TextView) view.findViewById(R.id.smaliSymbol);
			smaliSymbol.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/mono.ttf"), 0);
			final String className = data.get(position).get("ClassList").toString();
			((TextView) view.findViewById(R.id.name)).setText(className);
			
			if (classTree != null && classTree.tree != null) {
				if (classTree.tree.isDirectory(className)) {
					listItemIcon.setVisibility(View.VISIBLE);
					smaliSymbol.setVisibility(View.GONE);
					listItemIcon.setImageResource(R.drawable.ic_folder_mt);
					iconBackground.setBackground(createDrawable(8,0xFF252525));
				} else {
					listItemIcon.setVisibility(View.GONE);
					smaliSymbol.setVisibility(View.VISIBLE);
					iconBackground.setBackground(createDrawable(100, 0xFF3860AF));
				}
			}
			
			if (selectedItems.get(position, false)) {
				linearLayout.setBackground(createDrawable(13, 0xFF42A5F5));
			} else {
				linearLayout.setBackground(ContextCompat.getDrawable(DexEditorActivity.this, R.drawable.rounded_corner_ripple));
			}
			
			linearLayout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (isSelectionMode) {
						isBetweenSelectionActive = false;
						initialLongPressPosition = -1;
						toggleSelection(position, linearLayout);
						return;
					}
					if (classTree.tree.isDirectory(className)) {
						classMap.clear();
						classList = classTree.getList(className);
						for (int i = 0; i < classList.size(); i++) {
							classMapItem = new HashMap<>();
							classMapItem.put("ClassList", classList.get(i));
							classMap.add(classMapItem);
							getSupportActionBar().setSubtitle("/".concat(classTree.tree.getCurPath()));
							notifyDataSetChanged();
							refreshRecyclerView();
						}
						return;
					}
					try {
						TextEditorActivity.classTree = classTree;
						Intent intent = new Intent(DexEditorActivity.this, TextEditorActivity.class);
						intent.putExtra("ClassName", String.valueOf(classTree.tree.getCurPath()) + className);
						startActivity(intent);
						Animatoo.animateSlideLeft(DexEditorActivity.this);
					} catch (Exception e) {
						Notify_MT.Notify(DexEditorActivity.this, "Error", customException(e), "Close");
					}
				}
			});
			
			linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View view) {
					if (!isSelectionMode) {
						isSelectionMode = true;
					}
					toggleSelection(position, linearLayout);
					if (!isBetweenSelectionActive) {
						if (initialLongPressPosition == -1) {
							initialLongPressPosition = position;
						} else {
							isBetweenSelectionActive = true;
							int max = Math.max(initialLongPressPosition, position);
							notifyDataSetChanged();
							for (int min = Math.min(initialLongPressPosition, position); min <= max; min++) {
								selectedItems.put(min, true);
							}
							initialLongPressPosition = -1;
							isBetweenSelectionActive = false;
						}
					} else {
						toggleSelection(position, linearLayout);
					}
					return true;
				}
			});
		}
		
		@Override
		public int getItemCount() {
			return data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			public ViewHolder(View view) {
				super(view);
			}
		}
	}
	
	// Toggle selection of items in the RecyclerView
	private void toggleSelection(int position, View view) {
		if (selectedItems.get(position, false)) {
			selectedItems.delete(position);
			view.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_corner_ripple));
		} else {
			selectedItems.put(position, true);
			fabDelete.show();
			view.setBackground(createDrawable(13, 0xFF42A5F5));
			showMultipleFabs(true);
		}
		if (selectedItems.size() == 0) {
			showMultipleFabs(false);
			fabDelete.hide();
			isSelectionMode = false;
		}
	}
	
	// Show or hide multiple FABs (Floating Action Buttons)
	private void showMultipleFabs(boolean show) {
		if (show) {
			fabBackground.setVisibility(View.VISIBLE);
			fabBackground.setTranslationY(getDip(50));
			fabBackground.setAlpha(0.0f);
			fabBackground.animate().setDuration(200L).alpha(1.0f).translationY(0.0f);
		} else {
			fabBackground.setVisibility(View.GONE);
		}
	}
	
	// Initialize Floating Action Buttons (FABs)
	private void initializeFab() {
		LinearLayout fabLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.multiple_fabs, (ViewGroup) null).findViewById(R.id.linear_bg);
		fabBackground = fabLayout;
		FloatingActionButton fabSelectRest = fabLayout.findViewById(R.id.fab_select_rest);
		FloatingActionButton fabClear = fabLayout.findViewById(R.id.fab_clear);
		
		((ViewGroup) fabLayout.getParent()).removeView(fabLayout);
		((ViewGroup) fabDelete.getParent()).addView(fabLayout);
		
		fabClear.setBackgroundTintList(ColorStateList.valueOf(0xFFBEBEC3));
		fabSelectRest.setBackgroundTintList(ColorStateList.valueOf(0xFFBEBEC3));
		
		fabSelectRest.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				for (int i = 0; i < classMap.size(); i++) {
					if (selectedItems.get(i, false)) {
						selectedItems.delete(i);
					} else {
						selectedItems.put(i, true);
					}
				}
				recyclerView.getAdapter().notifyDataSetChanged();
			}
		});
		
		fabClear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				clearChoosed(true);
			}
		});
		
		showMultipleFabs(false);
	}
	
	// Clear selected items
	private void clearChoosed(boolean clearSelection) {
		if (isSelectionMode) {
			isSelectionMode = false;
			initialLongPressPosition = -1;
			if (clearSelection) {
				for (int i = 0; i < recyclerView.getChildCount(); i++) {
					View child = recyclerView.getChildAt(i);
					int position = recyclerView.getChildLayoutPosition(child);
					LinearLayout linearLayout = (LinearLayout) child.findViewById(R.id.linear_bg);
					if (selectedItems.get(position, false)) {
						linearLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_corner_ripple));
					}
				}
			}
			selectedItems.clear();
			showMultipleFabs(false);
			fabDelete.hide();
			if (clearSelection) {
				recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
					@Override
					public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
						return false;
					}
					
					@Override
					public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
					}
					
					@Override
					public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
					}
				});
			}
		}
	}
	
	// Convert exception to string for error handling
	private String customException(Exception exception) {
		StringWriter stringWriter = new StringWriter();
		exception.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}
	
	// Convert dip to pixels
	@Deprecated
	public float getDip(int dip) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
	}
	
	// Show error dialog
	private void showErrorDialog(String errorMessage) {
		Notify_MT.Notify(this, getResources().getString(R.string.error_title), errorMessage, getResources().getString(R.string.close_btn));
	}
	
	// Handle save and exit click
	private class SaveAndExitClickListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialogInterface, int i) {
			final AlertProgress alertProgress = new AlertProgress(DexEditorActivity.this);
			alertProgress.setTitle(String.valueOf(Uri.parse(dexPath).getLastPathSegment()) + " (1/1)");
			alertProgress.setMessage("Compiling...");
			alertProgress.show();
			
			final Handler handler = new Handler() {
				@Override
				public void handleMessage(Message message) {
					alertProgress.dismiss();
				}
			};
			
			new Thread() {
				@Override
				public void run() {
					Looper.prepare();
					try {
						classTree.saveDexFile(dexPath, new ClassTree.DexSaveProgress() {
							@Override
							public void onProgress(final int progress, final int total) {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										alertProgress.setProgress(progress, total);
									}
								});
							}
							
							@Override
							public void onTitle(final String title) {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										//	alertProgress.setTitle(title);
									}
								});
							}
							
							@Override
							public void onMessage(final String message) {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										alertProgress.setMessage(message);
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
						showErrorDialog("An error occurred while processing dex\n\n---StackTrace---\n\n" + e.toString());
					}
					handler.sendEmptyMessage(0);
					Looper.loop();
				}
			}.start();
		}
	}
	
	// Handle delete button click
	private class DeleteButtonClickListener implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			progressDialog = new ProgressDialog(DexEditorActivity.this);
			progressDialog.setCancelable(false);
			progressDialog.setCanceledOnTouchOutside(false);
			progressDialog.getWindow().setBackgroundDrawable(createDrawable(20, Color.parseColor("#FFFFFF")));
			progressDialog.setMessage("Deleting classes...");
			progressDialog.show();
			getWindow().addFlags(128);
			
			final Handler handler = new Handler(Looper.getMainLooper());
			new Thread() {
				@Override
				public void run() {
					Looper.prepare();
					try {
						ArrayList<Integer> itemsToDelete = new ArrayList<>();
						for (int i = 0; i < classMap.size(); i++) {
							if (selectedItems.get(i)) {
								itemsToDelete.add(i);
							}
						}
						for (int j = itemsToDelete.size() - 1; j >= 0; j--) {
							final int position = itemsToDelete.get(j);
							HashMap<String, Object> item = classMap.get(position);
							final String className = item.get("ClassList").toString();
							classMap.remove(item);
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									classTree.removeClass(String.valueOf(classTree.tree.getCurPath()) + className);
									recyclerView.getAdapter().notifyItemRemoved(position);
									listData = new Gson().toJson(classMap);
								}
							});
						}
						itemsToDelete.clear();
						selectedItems.clear();
						isChanged = true;
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								clearChoosed(false);
							}
						});
					} catch (Exception e) {
						handler.post(new Runnable() {
							@Override
							public void run() {
								showErrorDialog(e.toString());
							}
						});
					}
					handler.post(new Runnable() {
						@Override
						public void run() {
							getWindow().clearFlags(128);
							progressDialog.dismiss();
						}
					});
				}
			}.start();
		}
	}
}
