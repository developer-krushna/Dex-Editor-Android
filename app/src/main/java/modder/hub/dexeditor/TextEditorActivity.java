
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

import android.animation.*;
import android.app.*;
import android.content.*;
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
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.blogspot.atifsoftwares.animatoolib.*;
import com.google.android.material.appbar.AppBarLayout;
import io.github.rosemoe.sora.*;
import io.github.rosemoe.sora.langs.java.*;
import io.github.rosemoe.sora.langs.textmate.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import com.android.tools.smali.baksmali.Adaptors.ClassDefinition;
import com.android.tools.smali.baksmali.BaksmaliOptions;
import com.android.tools.smali.dexlib2.iface.ClassDef;
import com.android.tools.smali.util.IndentingWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import com.android.tools.smali.dexlib2.iface.ClassDef;
import com.android.tools.smali.dexlib2.iface.Method;
import com.android.tools.smali.dexlib2.writer.builder.DexBuilder;
import com.android.tools.smali.dexlib2.writer.io.MemoryDataStore;
import com.android.tools.smali.smali.*;
import com.android.tools.smali.dexlib2.Opcodes;
import com.android.tools.smali.smali2.Smali;
import org.apache.commons.io.IOUtils;
import java.nio.charset.StandardCharsets;
import io.github.rosemoe.sora.lang.Language;
import io.github.rosemoe.sora.lang.EmptyLanguage;
import io.github.rosemoe.sora.lang.Language;
import io.github.rosemoe.sora.langs.textmate.TextMateColorScheme;
import io.github.rosemoe.sora.langs.textmate.TextMateLanguage;
import io.github.rosemoe.sora.widget.CodeEditor;
import java.io.InputStreamReader;
import org.eclipse.tm4e.core.registry.IThemeSourceMT;
import org.eclipse.tm4e.core.registry.IGrammarSourceMT;
import org.eclipse.tm4e.core.registry.IThemeSource;
import dalvik.system.*;
import android.provider.Settings;
import io.github.rosemoe.sora.event.PublishSearchResultEvent;
import io.github.rosemoe.sora.event.SelectionChangeEvent;
import io.github.rosemoe.sora.text.Cursor;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.EditorSearcher;
import java.util.regex.PatternSyntaxException;

import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.component.Magnifier;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import io.github.rosemoe.sora.widget.schemes.SchemeDarcula;
import io.github.rosemoe.sora.widget.schemes.SchemeEclipse;
import io.github.rosemoe.sora.event.ContentChangeEvent;
import io.github.rosemoe.sora.widget.schemes.SchemeGitHub;
import io.github.rosemoe.sora.widget.schemes.SchemeNotepadXX;
import io.github.rosemoe.sora.widget.schemes.SchemeVS2019;
import android.view.inputmethod.InputMethodManager;

import modder.hub.dexeditor.fragment.SmaliMethodListFragment.DialogLineNumberListener;
import modder.hub.dexeditor.fragment.SmaliMethodListFragment;


import modder.hub.dexeditor.smali.*;

/*
Author @developer-krushna
*/

public class TextEditorActivity extends AppCompatActivity implements  DialogLineNumberListener {
	
	private Timer _timer = new Timer();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String saveOnstart = "";
	private boolean isEdit = false;
	public static  ClassTree classTree;
	private String saveSubscribeEvent = "";
	private Menu menu;
	private MenuItem undoMenu;
	private MenuItem redoMenu;
	private String Title;
	private CodeEditor smali_editor;
	private TimerTask t;
    private Intent ii = new Intent();
	
    private ProgressDialog coreprog;
    
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.text_editor);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		DexEditorActivity.classTree = classTree;
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
		smali_editor = findViewById(R.id.smali_editor);
	}
	
	private void initializeLogic() {
		setTitle("Smali Editor");
		isEdit = false;
		smali_editor.setTextSize(15);
		smali_editor.setLineSpacing(2f, 1.1f);
		smali_editor.subscribeEvent(ContentChangeEvent.class, ((event, unsubscribe) -> {
			_undo_redo();
			if (!saveSubscribeEvent.equals("")) {
				isEdit = true;
			}
			saveSubscribeEvent = "Saved";
		}));
		_handleJava2SmaliIntent();
	}
	
	
	@Override
	public void onStart() {
		super.onStart();
		if (saveOnstart.equals("")) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					SystemClock.sleep(200);
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_loadTheme();
						}
					});
				}
			}).start();
			saveOnstart = "Saved";
		}
	}
	
	@Override
	public void onBackPressed() {
		if (isEdit) {
			final AlertDialog.Builder d_build = new AlertDialog.Builder(TextEditorActivity.this);
			d_build.setTitle("Info");
			d_build.setMessage("Do you want to save the file ?");
			d_build.setPositiveButton("ok", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface _dialog, int _which) {
					_saveDef(true);
				}
			});
			d_build.setNegativeButton("cancel ", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface _dialog, int _which) {
					finish();
					com.blogspot.atifsoftwares.animatoolib.Animatoo.animateSlideLeft(TextEditorActivity.this);
				}
			});
			Notify_MT.Dlg_Style(d_build);
		}
		else {
			finish();
			com.blogspot.atifsoftwares.animatoolib.Animatoo.animateSlideLeft(TextEditorActivity.this);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.menu = menu;
		MenuItem menuitem3 = menu.add(Menu.NONE, 1, Menu.NONE, "Navigation");
		menuitem3.setIcon(R.drawable.ic_navigation);
		menuitem3.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
		undoMenu = menu.add(Menu.NONE, 2, Menu.NONE, "Undo");
		undoMenu.setIcon(R.drawable.ic_undo_grey);
		undoMenu.setEnabled(false);
		undoMenu.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);


		redoMenu = menu.add(Menu.NONE, 3, Menu.NONE, "Redo");
		redoMenu.setIcon(R.drawable.ic_redo_grey);
		redoMenu.setEnabled(false);
		redoMenu.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
		MenuItem menuitem1 = menu.add(Menu.NONE, 4, Menu.NONE, "Save");
		menuitem1.setIcon(R.drawable.ic_save);
		menuitem1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
		MenuItem menuitem2 = menu.add(Menu.NONE, 5, Menu.NONE, "Smali2Java");
		menuitem2.setIcon(R.drawable.ic_java_mt);
		menuitem2.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		final int _id = item.getItemId();
		final String _title = (String) item.getTitle();
		if (_id == 3) {
			smali_editor.redo();
			_undo_redo();
		}
		if (_id == 2) {
			smali_editor.undo();
			_undo_redo();
		}
		if (_id == 4) {
			if (isEdit) {
				_saveDef(false);
			}
		}
		if (_id == 1) {
			try{
				String tmpSmali = getFilesDir() + "/tmp.smali";
				FileUtil.writeFile(tmpSmali, smali_editor.getText().toString());
				t = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								SmaliMethodListFragment method_list = new SmaliMethodListFragment();
								method_list.show(getSupportFragmentManager(), " ");
								if (method_list != null) {
									method_list.updateUi(tmpSmali, Title.replace(".smali", ""));
								}
							}
						});
					}
				};
				_timer.schedule(t, (int)(200));
				
			} catch (Exception e) {
				Notify_MT.Notify(this, getResources().getString(R.string.error_title), "An error occurred while processing;)\n\n---StackTrace---\n\n" + e.getMessage(), getResources().getString(R.string.close_btn));
			}
			
		}
		
		if (_id == 5) {
			_processing_dlg(true);
			final Handler mHandler = new Handler() {
				public void handleMessage(Message msg) {
					_processing_dlg(false);
				} 
			};
			new Thread() {
				public void run() {
					Looper.prepare();
					try {
						if (smali_editor.getText().toString().equals("")) {
							SketchwareUtil.showMessage(getApplicationContext(), "Editor is null !");
						}
						else {
							String path  = getFilesDir() + "/Smali2Java.java";
							FileUtil.writeFile(path, Smali2Java.translate(smali_editor.getText().toString()));
							ii.setClass(getApplicationContext(), JavaViewActivity.class);
							ii.putExtra("Smali2Java", path);
							ii.putExtra("Smali2JavaName", Title.replace(".smali", "").concat(".java"));
							startActivity(ii);
							SketchwareUtil.showMessage(getApplicationContext(), "Success");
						}
					} catch (Exception e) {
						try {
							String input = e.getMessage();
							Pattern pattern = Pattern.compile("\\[(\\d+),(\\d+)\\]");
							Matcher matcher = pattern.matcher(input);
							if (matcher.find()) {
								int firstNumber = Integer.parseInt(matcher.group(1));
								int secondNumber = Integer.parseInt(matcher.group(2));
								smali_editor.setSelection(firstNumber - 1, secondNumber);
							} else {}
						} catch (Exception e2){}
						Notify_MT.Notify(TextEditorActivity.this, getResources().getString(R.string.error_title), e.getMessage(), getResources().getString(R.string.close_btn));
					}
					mHandler.sendEmptyMessage(0);
					Looper.loop();
				} }.start();
		}
		
		return super.onOptionsItemSelected(item);
	}
	
    // You should rename the IThemSourceMT to it's actual name IThemSource
	private TextMateColorScheme getColorScheme(String themeName) {
		try {
			IThemeSource themeSource = IThemeSourceMT.fromInputStream(
			getAssets().open("themes/"+themeName),
			themeName,
			null
			);
			
			return TextMateColorScheme.create(themeSource);
		} catch (Exception e) {
			
		}
		return null;
	}
    
    
	/* Here also rename the IGrammarSourceMT to actual name IGrammarSource
    
    *It was happened with me because of D8 Dexer
    
    */
	private Language getSmaliLanguage(String themeName) {
		try {
			return TextMateLanguage.create(
			IGrammarSourceMT.fromInputStream(
			getAssets().open("smali/syntaxes/smali.tmLanguage.json"),
			"smali.tmLanguage.json",
			null
			),
			new InputStreamReader(
			getAssets().open("smali/language-configuration.json")),
			getColorScheme(themeName).getThemeSource());
		} catch (IOException e) {
			return new EmptyLanguage();
		}
		
	}
	
	
	public void _loadTheme() {
		smali_editor.setColorScheme(getColorScheme("light.json"));
		smali_editor.setEditorLanguage(getSmaliLanguage("light.json"));
	}
	
	
	public void _processing_dlg(final boolean _ifShow) {
		if (_ifShow) {
			if (coreprog == null) {
				coreprog = new ProgressDialog(this);
				coreprog.setCancelable(false);
				coreprog.setCanceledOnTouchOutside(false);
				coreprog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
				coreprog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
			}
			coreprog.setMessage(null);
			try { 
				runOnUiThread(new Runnable(){
					@Override
					public void run() {
						coreprog.show();
					}
				});
			} catch (WindowManager.BadTokenException e){ }
			coreprog.setContentView(R.layout.loading);
            
            //Initialisation of views
			LinearLayout linear2 = (LinearLayout)coreprog.findViewById(R.id.linear2);
			LinearLayout background = (LinearLayout)coreprog.findViewById(R.id.background);
			LinearLayout layout_progress = (LinearLayout)coreprog.findViewById(R.id.layout_progress);
            // Background
			GradientDrawable gd = new GradientDrawable();
			gd.setColor(Color.parseColor("#E0E0E0"));
			gd.setCornerRadius(40);
			gd.setStroke(0, Color.WHITE); 
			linear2.setBackground(gd);
			RadialProgressView progress = new RadialProgressView(this);
			layout_progress.addView(progress);
		}
		else {
			if (coreprog != null) {
				runOnUiThread(new Runnable(){
					@Override
					public void run() {
						coreprog.dismiss();
					} 
                    });
			}
		}
	}
	
	public void _handleJava2SmaliIntent() {
		_loadTheme();
		if (getIntent().hasExtra("Smali")) {
			Title = getIntent().getStringExtra("ClassName") + ".smali";
			_processing_dlg(true);
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					try {
						byte[] input = getIntent().getByteArrayExtra("Smali");
						String content = new String(input, "UTF-8");
						getSupportActionBar().setSubtitle(Title);
						smali_editor.setText(content);
						_processing_dlg(false);
					} catch (Exception e) {
						_processing_dlg(false);
						SketchwareUtil.showMessage(getApplicationContext(), e.toString());
					}
				}
			});
		}
	}
	
	
	public void _saveDef(final boolean _isOnBackPressed) {
		_processing_dlg(true);
		new Thread() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						try {
							DexBuilder dexBuilder = new DexBuilder(Opcodes.getDefault());
							dexBuilder.setIgnoreMethodAndFieldError(true);
							ClassDef classDef = Smali.assemble(smali_editor.getText().toString(), new SmaliOptions());
							classTree.saveClassDef(classDef);
							_processing_dlg(false);
							isEdit = false;
							SketchwareUtil.showMessage(getApplicationContext(), "Saved successfully");
							if (_isOnBackPressed) {
								finish();
								com.blogspot.atifsoftwares.animatoolib.Animatoo.animateSlideLeft(TextEditorActivity.this);
							}
						} catch (Exception e) {
							_processing_dlg(false);
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									try {
										String input = e.getMessage();
										Pattern pattern = Pattern.compile("\\[(\\d+),(\\d+)\\]");
										Matcher matcher = pattern.matcher(input);
										if (matcher.find()) {
											int firstNumber = Integer.parseInt(matcher.group(1));
											int secondNumber = Integer.parseInt(matcher.group(2));
											smali_editor.setSelection(firstNumber - 1, secondNumber);
										}
										else {
											
										}
									} catch (Exception e2){}
									Notify_MT.Notify(TextEditorActivity.this, "Error", e.getMessage(), "Cancel");
								}
							});
						}
					}
				});
				_processing_dlg(false);
			} }.start();
	}
	
	
	public void _undo_redo() {
		if (menu != null) {
			if (smali_editor.canUndo()) {
				undoMenu.setIcon(R.drawable.ic_undo_mt);
				undoMenu.setEnabled(true);
			}
			else {
				undoMenu.setIcon(R.drawable.ic_undo_grey);
				isEdit = false;
				undoMenu.setEnabled(false);
			}
			if (smali_editor.canRedo()) {
				redoMenu.setIcon(R.drawable.ic_redo_mt);
				redoMenu.setEnabled(true);
			}
			else {
				redoMenu.setIcon(R.drawable.ic_redo_grey);
				redoMenu.setEnabled(false);
			}
		}
	}
	
	public void _updateEditorLineNumber(final String _lineNo) {
		try {
			smali_editor.jumpToLine(Integer.parseInt(_lineNo));
		} catch (Exception e) {
			SketchwareUtil.showMessage(getApplicationContext(), "Value is out of range.");
		}
	}
	
}
