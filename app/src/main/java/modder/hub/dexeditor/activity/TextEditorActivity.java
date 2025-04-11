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


package modder.hub.dexeditor.activity;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.method.LinkMovementMethod;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.android.tools.smali.baksmali.Adaptors.ClassDefinition;
import com.android.tools.smali.baksmali.BaksmaliOptions;
import com.android.tools.smali.baksmali.formatter.BaksmaliWriter;
import com.android.tools.smali.dexlib2.iface.ClassDef;
import com.android.tools.smali.smali.SmaliOptions;
import com.android.tools.smali.smali2.Smali2;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.textfield.TextInputLayout;

import io.github.rosemoe.sora.event.ContentChangeEvent;
import io.github.rosemoe.sora.event.*;
import io.github.rosemoe.sora.event.Event;
import io.github.rosemoe.sora.event.EventReceiver;
import io.github.rosemoe.sora.event.Unsubscribe;
import io.github.rosemoe.sora.lang.EmptyLanguage;
import io.github.rosemoe.sora.lang.Language;
import io.github.rosemoe.sora.langs.textmate.TextMateColorScheme;
import io.github.rosemoe.sora.langs.textmate.TextMateLanguage;
import io.github.rosemoe.sora.langs.textmate.registry.ThemeRegistry;
import io.github.rosemoe.sora.text.Cursor;
import io.github.rosemoe.sora.text.Content;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.component.EditorTextActionWindow;
import io.github.rosemoe.sora.widget.component.EditorBuiltinComponent;
import io.github.rosemoe.sora.text.Content;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modder.hub.dexeditor.fragment.*;
import modder.hub.dexeditor.smali.Smali2Java;
import modder.hub.dexeditor.views.*;
import modder.hub.dexeditor.utils.*;
import modder.hub.dexeditor.R;


import org.eclipse.tm4e.core.registry.IGrammarSourceMT;
import org.eclipse.tm4e.core.registry.IThemeSourceMT;


/*
Author @developer-krushna
Code fixed comments by ChatGPT
*/


public class TextEditorActivity extends AppCompatActivity implements SmaliMethodListFragment.DialogLineNumberListener {
	public static ClassTree classTree;
	private String currentTitle;
	private AppBarLayout appBarLayout;
	private CoordinatorLayout coordinatorLayout;
	private Toolbar toolbar;
	private ProgressDialog progressDialog;
	private Cursor cursor;
	private int dexVersion;
	private Menu menu;
	private PackageManager packageManager;
	private SharedPreferences.Editor preferencesEditor;
	private MenuItem redoMenuItem;
	private SharedPreferences sharedPreferences;
	
	private CodeEditor smaliEditor;
	private LinearLayout linearHeader;
	private LinearLayout linearLeft;
	private TextView textviewLeft;
	private LinearLayout linearRight;
	private TextView textviewLineNo;
	private TextView methodName;
	
	private TimerTask timerTask;
	private MenuItem undoMenuItem;
	private Timer timer = new Timer();
	private boolean isEditMode = false;
	private String isFileCreated = "";
	private String saveEvent = "";
	private String saveOnStart = "";
	private String tempSmaliPath = "";
	private String intentClassName = "";
	private String saveCompileError = "";
	private Intent intent = new Intent();
	
	public static SmaliMethodListFragment smaliMethodsStringsFragment = null;
	public static Parcelable methodRecyclerViewState = null;
	public static Parcelable stringsRecyclerViewState = null;
	public static boolean wasStringsVisible = false;
	public static String lastSmaliFilePath = "";
	public static long lastModifiedTime = -1;
	
	
	private SharedPreferences dexPref;
	private SharedPreferences editorPrefs;
	
	
	private String savedFont = "normal";
	private boolean isReload = false;
	
	
	public interface FileSaveCallback {
		void onFileSaved(String filePath);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_editor);
		
		// Reset state if activity is recreated
		if (savedInstanceState == null) {
			smaliMethodsStringsFragment = null;
			methodRecyclerViewState = null;
			stringsRecyclerViewState = null;
			wasStringsVisible = false;
			lastSmaliFilePath = "";
			lastModifiedTime = -1;
		}
		
		initialize(savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle savedInstanceState) {
		DexEditorActivity.classTree = classTree;
		appBarLayout = findViewById(R.id._app_bar);
		coordinatorLayout = findViewById(R.id._coordinator);
		toolbar = findViewById(R.id._toolbar);
		
		linearHeader = findViewById(R.id.linear_header);
		linearLeft = findViewById(R.id.linear_left);
		textviewLeft = findViewById(R.id.textview_left);
		linearRight = findViewById(R.id.linear_right);
		textviewLineNo = findViewById(R.id.textview_lineNo);
		methodName = findViewById(R.id.methodName);
		
		
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onBackPressed();
			}
		});
		smaliEditor = findViewById(R.id.smali_editor);
		sharedPreferences = getSharedPreferences("SelectedTranslationPackageName", 0);
		preferencesEditor = sharedPreferences.edit();
		packageManager = getPackageManager();
		
		dexPref = getSharedPreferences("dexPref", Activity.MODE_PRIVATE);
		editorPrefs = getSharedPreferences("editor_prefs", Context.MODE_PRIVATE);
		
		linearLeft.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				PopupMenu popupMenu = new PopupMenu(TextEditorActivity.this, view);
				Menu menu = popupMenu.getMenu();
				menu.add(1, 1, 1, currentTitle);
				menu.add(2, 2, 2, Smali2JavaName2(intentClassName));
				menu.add(3, 3, 3, intentClassName);
				menu.add(4, 4, 4, "L" + intentClassName + ";");
				
				popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem menuItem) {
						copiedToClipboard(menuItem.getTitle().toString());
						return true;
					}
				});
				
				popupMenu.show();
			}
		});
		
		linearRight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				runOnUiThread(new MethodListRunnable());
			}
		}); 
	}
	
	private void initializeLogic() {
		setTitle("");
		tempSmaliPath = getFilesDir() + "/tmp.smali";
		savedFont  = SettingsFragment.getFontType(this);
		cursor = smaliEditor.getCursor();
		
		SmaliInstructionHelper.init(getApplicationContext());
		
		loadEditorSettings(true);
		loadTheme();
		handleSmaliIntent();
		
		smaliEditor.subscribeEvent(ContentChangeEvent.class, new EventReceiver<ContentChangeEvent>() {
			@Override
			public void onReceive(ContentChangeEvent event, Unsubscribe unsubscribe) {
				handleUndoRedo();
				if(!isReload){
					Cursor cursor = smaliEditor.getCursor();
					int position = cursor.getLeftLine();
					editorPrefs.edit().putInt("lineNo", position).commit();
				}
				if (!saveEvent.isEmpty()) {
					isEditMode = true;
					isFileCreated = "";
				}
				saveEvent = "Saved";
				isReload = false;
			}
		});
		
		smaliEditor.subscribeEvent(SelectionChangeEvent.class, new EventReceiver<SelectionChangeEvent>() {
			@Override
			public void onReceive(SelectionChangeEvent event, Unsubscribe unsubscribe) {
				Cursor cursor = smaliEditor.getCursor();
				CharSequence text = smaliEditor.getText();
				
				int line = cursor.getLeftLine() + 1;
				int column = cursor.getLeftColumn() + 1;
				
				StringBuilder positionText = new StringBuilder();
				positionText.append(String.format("%d:%d", line, column));
				
				if (cursor.isSelected()) {
					String selectedText = text.subSequence(
					cursor.getLeft(), 
					cursor.getRight()).toString();
					positionText.append(" (").append(selectedText.length()).append(")");
				}
				
				textviewLineNo.setText(positionText.toString());
				
				String currentElement = SmaliCursorUtils.getCurrentMethodOrFieldName(text, cursor.getLeftLine());
				methodName.setText(currentElement != null ? currentElement : "...");
			}
		});
	}
	
	
	private void showSmaliNavigation(String tempSmaliPath, String currentTitle, int lineNo) {
		File smaliFile = new File(tempSmaliPath);
		boolean fileChanged = !tempSmaliPath.equals(lastSmaliFilePath) || 
		(smaliFile.exists() && smaliFile.lastModified() != lastModifiedTime);
		
		if (smaliMethodsStringsFragment == null || fileChanged) {
			// Create a new instance if none exists or file has changed
			smaliMethodsStringsFragment = new SmaliMethodListFragment();
			smaliMethodsStringsFragment.show(getSupportFragmentManager(), " ");
			smaliMethodsStringsFragment.updateUi(tempSmaliPath, currentTitle.replace(".smali", ""), lineNo, dexVersion);
			
			// Update tracking variables
			lastSmaliFilePath = tempSmaliPath;
			lastModifiedTime = smaliFile.exists() ? smaliFile.lastModified() : -1;
			
			// Reset visibility and scroll states since it's a fresh load
			wasStringsVisible = false;
			methodRecyclerViewState = null;
			stringsRecyclerViewState = null;
		} else {
			// Reuse the existing fragment instance
			smaliMethodsStringsFragment.show(getSupportFragmentManager(), " ");
			smaliMethodsStringsFragment.restorePreviousState(methodRecyclerViewState, stringsRecyclerViewState, wasStringsVisible);
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Clear the fragment instance on activity destruction
		smaliMethodsStringsFragment = null;
	}
	
	
	@Override
	public void onStart() {
		super.onStart();
		if (saveOnStart.equals("")) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					SystemClock.sleep(200);
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							loadTheme();
						}
					});
				}
			}).start();
			saveOnStart = "Saved";
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		loadEditorSettings(false);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.menu = menu;
		getMenuInflater().inflate(R.menu.editor_menu, menu);
		undoMenuItem = menu.findItem(R.id.undo);
		redoMenuItem = menu.findItem(R.id.redo);
		menu.findItem(R.id.wrap_text).setCheckable(true).setChecked(editorPrefs.getBoolean("wrap_text", false));
		menu.findItem(R.id.edit_menu);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				View saveView = findViewById(R.id.save);
				if (saveView != null) {
					saveView.setOnLongClickListener(new View.OnLongClickListener() {
						@Override
						public boolean onLongClick(View view) {
							if (saveCompileError.isEmpty()) {
								return true;
							}
							showPreviousErrorDlg(saveCompileError);
							return true;
						}
					});
				} else {
				}
			}
		}, 100); // 100ms delay
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Cursor cursor = smaliEditor.getCursor();
		switch (item.getItemId()) {
			case R.id.redo:
			smaliEditor.redo();
			handleUndoRedo();
			dismissEditorWindow();
			break;
			case R.id.undo:
			smaliEditor.undo();
			handleUndoRedo();
			dismissEditorWindow();
			break;
			case R.id.save:
			if (isEditMode) {
				saveFile(false);
			}
			break;
			case R.id.navigation:
			runOnUiThread(new MethodListRunnable());
			break;
			case R.id.smali2java:
			runOnUiThread(new SmaliToJavaRunnable());
			break;
			case R.id.preference:
			startActivity(new Intent(TextEditorActivity.this, SettingsActivity.class));
			break;
			case R.id.jumpToLine:
			jumpToLine();
			break;
			case R.id.close:
			onBackPressed();
			break;
			case R.id.wrap_text:
			if (item.isChecked()) {
				item.setChecked(false);
				editorPrefs.edit().putBoolean("wrap_text", false).commit();
				smaliEditor.setWordwrap(false);
			}
			else {
				item.setChecked(true);
				editorPrefs.edit().putBoolean("wrap_text", true).commit();
				smaliEditor.setWordwrap(true);
			}
			return true;
			case R.id.read_only:
			if (item.isChecked()) {
				item.setChecked(false);
				smaliEditor.setEditable(true);
				menu.findItem(R.id.edit_menu).setEnabled(true);
			}
			else {
				item.setChecked(true);
				smaliEditor.setEditable(false);
				menu.findItem(R.id.edit_menu).setEnabled(false);
			}
			return true;
			
			case R.id.delete_line:
			deleteLine();
			dismissEditorWindow();
			return true;
			
			case R.id.empty_line:
			emptyLine();
			dismissEditorWindow();
			return true;
			
			case R.id.replace_line:
			replaceLine();
			dismissEditorWindow();
			return true;
			
			case R.id.duplicate_line:
			duplicateLine();
			dismissEditorWindow();
			return true;
			
			case R.id.toggle_comment:
			toggleComment();
			return true;
			
			case R.id.copy_line:
			_copyLine();
			dismissEditorWindow();
			return true;
			
			case R.id.cut_line:
			smaliEditor.cutLine();
			dismissEditorWindow();
			return true;
			
			case R.id.convert_uppercase:
			convertUpperLowerCase(true);
			dismissEditorWindow();
			return true;
			
			case R.id.convert_lowercase:
			convertUpperLowerCase(false);
			dismissEditorWindow();
			return true;
			
			case R.id.increase_indent:
			increaseIndent();
			dismissEditorWindow();
			return true;
			
			case R.id.decrease_indent:
			decreaseIndent();
			dismissEditorWindow();
			return true;
			
			case R.id.smali_instruction:
			String instruction = getCurrentLineInstruction();
			if (instruction != null) {
				SmaliInstructionsDialog dialog = new SmaliInstructionsDialog(this, "smali_instructions.txt", instruction);
				dialog.show();
			} else {
				SmaliInstructionsDialog dialog = new SmaliInstructionsDialog(this, "smali_instructions.txt");
				dialog.show();
			}
			
			return true;
			
			default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
	
	
	public void loadEditorSettings(boolean loadTypeface){
		smaliEditor.setTextSize(SettingsFragment.getFontSize(this));
		smaliEditor.setLineNumberEnabled(SettingsFragment.showLineNumbers(this));
		
		smaliEditor.setLineSpacing(2.0f, 1.1f);
		smaliEditor.setLineNumberMarginLeft(2f);
		
		smaliEditor.setWordwrap(editorPrefs.getBoolean("wrap_text", false));
		if(loadTypeface){
			Typeface typeface = savedFont.equals("normal") 
			? Typeface.DEFAULT : Typeface.MONOSPACE;
			smaliEditor.setTypefaceText(typeface);
			smaliEditor.setTypefaceLineNumber(typeface);
		} else {
			if(!savedFont.equals(SettingsFragment.getFontType(this))){
				Typeface typeface = savedFont.equals("normal") 
				? Typeface.MONOSPACE : Typeface.DEFAULT;
				smaliEditor.setTypefaceText(typeface);
				smaliEditor.setTypefaceLineNumber(typeface);
				
				savedFont  = SettingsFragment.getFontType(this);
				isReload = true;
				reloadText();
			}
		}
		
		smaliEditor.replaceComponent(EditorTextActionWindow.class, new TextActionWindow(smaliEditor, new TextActionCallback(Smali2JavaName2(intentClassName))));
	}
	
	
	private class TextActionCallback implements TextActionWindow.ItemClickCallBack {
		private final String currentClassName;
		
		TextActionCallback(String className) {
			this.currentClassName = className;
		}
		
		@Override
		public void onClickGoTo(View view, final String text) {
			runOnUiThread(new GoToRunnable(text, currentClassName));
		}
		
		@Override
		public void onClickTranslate(View view, final String text) {
			runOnUiThread(new Runnable() {
				private final String mText = text;
				
				@Override
				public void run() {
					if (!sharedPreferences.contains("selectedPackage")) {
						SketchwareUtil.showMessage(getApplicationContext(), "Select a translation app first");
						showAvailableTranslationDlg();
						return;
					}
					try {
						String packageName = sharedPreferences.getString("selectedPackage", "");
						packageManager.getPackageInfo(packageName, 0);
						Intent intent = new Intent("android.intent.action.PROCESS_TEXT");
						intent.setType("text/plain");
						intent.putExtra("android.intent.extra.PROCESS_TEXT", mText);
						intent.putExtra("android.intent.extra.PROCESS_TEXT_READONLY", true);
						intent.setPackage(packageName);
						startActivity(intent);
					} catch (PackageManager.NameNotFoundException e) {
						preferencesEditor.remove("selectedPackage");
						preferencesEditor.apply();
						showAvailableTranslationDlg();
					}
				}
			});
		}
		
		@Override
		public void onLongClickTranslate(View view) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					showAvailableTranslationDlg();
				}
			});
			
		}
	}
	
	private class GoToRunnable implements Runnable {
		private final String text;
		private final String currentClassName;
		
		GoToRunnable(String text, String currentClassName) {
			this.text = text;
			this.currentClassName = currentClassName;
		}
		
		@Override
		public void run() {
			if (!text.contains(";->")) {
				if (Smali2JavaName(text).equals(currentClassName)) {
					SketchwareUtil.showMessage(getApplicationContext(), "You are already in this class");
					return;
				} else if (classTree.classMap.get(smali2OnlySlash(text)) == null) {
					ErrorDlg("Class not found: " + Smali2JavaName(text));
					return;
				} else {
					Intent intent = new Intent(TextEditorActivity.this, TextEditorActivity.class);
					intent.putExtra("ClassName", smali2OnlySlash(text));
					startActivity(intent);
					Animatoo.animateSlideLeft(TextEditorActivity.this);
					return;
				}
			}
			String[] splitText = splitText(text);
			if (splitText.length == 2) {
				String className = splitText[0];
				final String methodName = splitText[1];
				String javaClassName = Smali2JavaName(className);
				if (javaClassName.equals(currentClassName)) {
					try {
						if (isFileCreated.isEmpty()) {
							saveSmaliCodeToFile(smaliEditor.getText().toString(), tempSmaliPath, new FileSaveCallback() {
								@Override
								public void onFileSaved(String filePath) {
									try {
										isFileCreated = "true";
										extractInfo(filePath, methodName);
									} catch (Exception e) {
										ErrorDlg(e.toString());
									}
								}
							});
						} else if (!FileUtil.isExistFile(tempSmaliPath)) {
							saveSmaliCodeToFile(smaliEditor.getText().toString(), tempSmaliPath, new FileSaveCallback() {
								@Override
								public void onFileSaved(String filePath) {
									try {
										isFileCreated = "true";
										extractInfo(filePath, methodName);
									} catch (Exception e) {
										ErrorDlg(e.toString());
									}
								}
							});
						} else {
							extractInfo(tempSmaliPath, methodName);
						}
					} catch (Exception e) {
						ErrorDlg(e.toString());
					}
				} else if (classTree.classMap.get(smali2OnlySlash(className)) == null) {
					ErrorDlg("Class not found : " + javaClassName);
				} else {
					Intent intent = new Intent(TextEditorActivity.this, TextEditorActivity.class);
					intent.putExtra("ClassName", smali2OnlySlash(className));
					intent.putExtra("MethodName", methodName);
					startActivity(intent);
					Animatoo.animateSlideLeft(TextEditorActivity.this);
				}
			}
		}
	}
	
	
	
	@Override
	public void onBackPressed() {
		FileUtil.deleteFile(tempSmaliPath);
		if (isEditMode) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Info");
			builder.setMessage("Do you want to save the file ?");
			builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					saveFile(true);
				}
			});
			builder.setNegativeButton("cancel ", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
					Animatoo.animateSlideRight(TextEditorActivity.this);
				}
			});
			Notify_MT.Dlg_Style(builder);
			return;
		}
		finish();
		Animatoo.animateSlideRight(this);
	}
	
	
	private void handleSmaliIntent() {
		showProgressDialog(true);
		if (getIntent().hasExtra("ClassName")) {
			intentClassName = getIntent().getStringExtra("ClassName");
			currentTitle = extractSubstringAfterLastSlash(intentClassName);
			
			try {
				dexVersion = classTree.getOpenedDexVersion();
				SharedPreferences.Editor editor = dexPref.edit();
				editor.putInt("dexVer", dexVersion);
				editor.apply();
			} catch (Exception e) {
				dexVersion = dexPref.getInt("dexVer", 35);
			}
			
			final Handler handler = new Handler(Looper.getMainLooper()) {
				@Override
				public void handleMessage(Message msg) {
					showProgressDialog(false);
					try {
						if (getIntent().hasExtra("MethodName")) {
							saveSmaliCodeToFile(smaliEditor.getText().toString(), tempSmaliPath, new FileSaveCallback() {
								@Override
								public void onFileSaved(String filePath) {
									try {
										isFileCreated = "true";
										extractInfo(filePath, getIntent().getStringExtra("MethodName"));
									} catch (Exception e) {
										ErrorDlg(customException(e));
									}
								}
							});
						}
					} catch (Exception e) {
						ErrorDlg(customException(e));
					}
				}
			};
			
			new Thread() {
				@Override
				public void run() {
					try {
						if (classTree == null) {
							finish();
						}
						String smaliCode = new String(getSmaliByType(classTree.classMap.get(intentClassName)).getBytes(), "UTF-8");
						
						// Update UI on the main thread
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								textviewLeft.setText(currentTitle);
								smaliEditor.setText(smaliCode);
								showProgressDialog(false);
							}
						});
						
					} catch (Exception e) {
						// Handle exceptions on the main thread
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								showProgressDialog(false);
								ErrorDlg(customException(e));
							}
						});
					}
					handler.sendEmptyMessage(0); // Notify completion
				}
			}.start();
		}
	}
	
	private void reloadText(){
		showProgressDialog(true);
		final Handler handler = new Handler(Looper.getMainLooper()) {
			@Override
			public void handleMessage(Message msg) {
				showProgressDialog(false);
				try {
					reloadEditorPosition(editorPrefs.getInt("lineNo", 0));
				} catch (Exception e) {
					ErrorDlg(customException(e));
				}
			}
		};
		
		new Thread() {
			@Override
			public void run() {
				try {
					String smaliCode = smaliEditor.getText().toString();
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							smaliEditor.setText(smaliCode);
						}
					});
					
				} catch (Exception e) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							showProgressDialog(false);
							ErrorDlg(customException(e));
						}
					});
				}
				handler.sendEmptyMessage(0);
			}
		}.start();
	}
	
	private void handleUndoRedo() {
		if (menu != null) {
			if (smaliEditor.canUndo()) {
				undoMenuItem.setIcon(R.drawable.ic_undo_mt);
				undoMenuItem.setEnabled(true);
			} else {
				undoMenuItem.setIcon(R.drawable.ic_undo_grey);
				isEditMode = false;
				undoMenuItem.setEnabled(false);
			}
			if (smaliEditor.canRedo()) {
				redoMenuItem.setIcon(R.drawable.ic_redo_mt);
				redoMenuItem.setEnabled(true);
			} else {
				redoMenuItem.setIcon(R.drawable.ic_redo_grey);
				redoMenuItem.setEnabled(false);
			}
		}
	}
	
	@Override
	public void _updateEditorLineNumber(String lineNumber) {
		try {
			int LineNum = (int) Math.floor(Double.parseDouble(lineNumber));
			smaliEditor.jumpToLine(LineNum);
			String getLineText = smaliEditor.getText().getLine(LineNum).toString();
			if(getLineText.contains("const-string")){
				int[] positions = getOuterQuotePositions(getLineText);
				smaliEditor.setSelectionRegion(LineNum, (positions[0] + 1), LineNum, positions[1]);
				dismissEditorWindow();
			} 
		} catch (Exception e) {
			SketchwareUtil.showMessage(getApplicationContext(), "Value is out of range." + lineNumber);
		}
	}
	
	private void showAvailableTranslationDlg() {
		Intent intent = new Intent("android.intent.action.PROCESS_TEXT");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.setType("text/plain");
		final List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent, 0);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Available system translations");
		String[] appNames = new String[resolveInfoList.size()];
		final String[] selectedPackage = {""};
		int selectedIndex = -1;
		for (int i = 0; i < resolveInfoList.size(); i++) {
			ResolveInfo resolveInfo = resolveInfoList.get(i);
			String appName = resolveInfo.activityInfo.applicationInfo.loadLabel(packageManager).toString();
			String packageName = resolveInfo.activityInfo.packageName;
			String activityName = resolveInfo.loadLabel(packageManager).toString();
			appNames[i] = appName + " - " + activityName;
			if (packageName.equals(sharedPreferences.getString("selectedPackage", ""))) {
				selectedPackage[0] = packageName;
				selectedIndex = i;
			}
		}
		builder.setSingleChoiceItems(appNames, selectedIndex, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				selectedPackage[0] = resolveInfoList.get(which).activityInfo.packageName;
			}
		});
		builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (selectedPackage[0].isEmpty()) {
					Toast.makeText(getApplication(), "No app selected", Toast.LENGTH_SHORT).show();
					return;
				}
				preferencesEditor.putString("selectedPackage", selectedPackage[0]);
				preferencesEditor.apply();
				Toast.makeText(getApplication(), "You can further change your translation app by pressing long in the translation menu item", Toast.LENGTH_SHORT).show();
			}
		});
		AlertDialog dialog = builder.create();
		dialog.getWindow().setBackgroundDrawable(Notify_MT.createDrawable(20, Color.WHITE));
		dialog.show();
		dialog.getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				String packageName = resolveInfoList.get(position).activityInfo.packageName;
				if (packageName.isEmpty()) {
					return true;
				}
				Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
				intent.setData(Uri.parse("package:" + packageName));
				startActivity(intent);
				return true;
			}
		});
		if (selectedPackage[0].isEmpty()) {
			return;
		}
		try {
			packageManager.getPackageInfo(selectedPackage[0], 0);
		} catch (PackageManager.NameNotFoundException e) {
			preferencesEditor.remove("selectedPackage");
			preferencesEditor.apply();
		}
	}
	
	private TextLocation findMethodLocation(String filePath, String methodName) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		int lineNumber = 0;
		while (true) {
			String line = reader.readLine();
			if (line != null) {
				lineNumber++;
				String trimmedLine = line.trim();
				if (!trimmedLine.isEmpty()) {
					String[] parts = trimmedLine.split(" ");
					if (".method".equals(parts[0]) && parts[parts.length - 1].equals(methodName)) {
						int startIndex = trimmedLine.indexOf(methodName);
						TextLocation location = new TextLocation(lineNumber, startIndex, _getTextBefore(methodName, "(").length() + startIndex);
						reader.close();
						return location;
					}
				}
			} else {
				reader.close();
				return null;
			}
		}
	}
	
	private TextLocation findFieldLocation(String filePath, String fieldName) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		int lineNumber = 0;
		while (true) {
			String line = reader.readLine();
			if (line != null) {
				lineNumber++;
				if (line.trim().startsWith(".field") && line.contains(fieldName)) {
					int startIndex = line.indexOf(fieldName);
					TextLocation location = new TextLocation(lineNumber, startIndex, _getTextBefore(fieldName, ":").length() + startIndex);
					reader.close();
					return location;
				}
			} else {
				reader.close();
				return null;
			}
		}
	}
	
	private void saveSmaliCodeToFile(String content, String filePath, FileSaveCallback callback) throws Exception {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
		writer.write(content);
		if (callback != null) {
			callback.onFileSaved(filePath);
		}
		writer.close();
	}
	
	private void extractInfo(final String filePath, final String target) {
		new AsyncTask<Void, Void, TextLocation>() {
			@Override
			protected TextLocation doInBackground(Void... voids) {
				try {
					if (target.contains(":")) {
						return findFieldLocation(filePath, target);
					} else {
						return findMethodLocation(filePath, target);
					}
				} catch (Exception e) {
					ErrorDlg(e.toString());
					return null;
				}
			}
			
			@Override
			protected void onPostExecute(TextLocation location) {
				if (location != null) {
					int lineNumber = location.lineNumber - 1;
					try {
						smaliEditor.jumpToLine(lineNumber);
						new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
							@Override
							public void run() {
								try {
									smaliEditor.setSelectionRegion(lineNumber, location.startColumn, lineNumber, location.endColumn);
									dismissEditorWindow();
								} catch (Exception e) {
									ErrorDlg(e.toString());
								}
							}
						}, 100);
					} catch (Exception e) {
						ErrorDlg(e.toString());
					}
				} else {
					smaliEditor.jumpToLine(0);
					System.out.println("Method or field not found.");
				}
			}
		}.execute();
	}
	
	public void ErrorDlg(String errorMessage) {
		Notify_MT.Notify(this, getResources().getString(R.string.error_title), errorMessage, getResources().getString(R.string.close_btn));
	}
	
	private class TextLocation {
		int lineNumber;
		int startColumn;
		int endColumn;
		
		public TextLocation(int lineNumber, int startColumn, int endColumn) {
			this.lineNumber = lineNumber;
			this.startColumn = startColumn;
			this.endColumn = endColumn;
		}
	}
	
	private class MethodListRunnable implements Runnable {
		Cursor cursor = smaliEditor.getCursor();
		@Override
		public void run() {
			try {
				if (isFileCreated.isEmpty()) {
					saveSmaliCodeToFile(smaliEditor.getText().toString(), tempSmaliPath, new FileSaveCallback() {
						@Override
						public void onFileSaved(String filePath) {
							try {
								isFileCreated = "true";
								showSmaliNavigation(tempSmaliPath, currentTitle.replace(".smali", ""), cursor.getLeftLine());
								
							} catch (Exception e) {
								ErrorDlg(e.toString());
							}
						}
					});
				} else if (!FileUtil.isExistFile(tempSmaliPath)) {
					saveSmaliCodeToFile(smaliEditor.getText().toString(), tempSmaliPath, new FileSaveCallback() {
						@Override
						public void onFileSaved(String filePath) {
							try {
								isFileCreated = "true";
								showSmaliNavigation(tempSmaliPath, currentTitle.replace(".smali", ""), cursor.getLeftLine());
								
							} catch (Exception e) {
								ErrorDlg(e.toString());
							}
						}
					});
				} else {
					SmaliMethodListFragment fragment = new SmaliMethodListFragment();
					fragment.show(getSupportFragmentManager(), " ");
					fragment.updateUi(tempSmaliPath, currentTitle.replace(".smali", ""), cursor.getLeftLine(), dexVersion);
				}
			} catch (Exception e) {
				ErrorDlg(e.toString());
			}
		}
	}
	
	private class SmaliToJavaRunnable implements Runnable {
		@Override
		public void run() {
			showProgressDialog(true);
			final Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					showProgressDialog(false);
				}
			};
			new Thread() {
				@Override
				public void run() {
					Looper.prepare();
					try {
						if (smaliEditor.getText().toString().isEmpty()) {
							SketchwareUtil.showMessage(getApplicationContext(), "Editor is empty!");
						} else {
							String javaFilePath = getFilesDir() + "/Smali2Java.java";
							FileUtil.writeFile(javaFilePath, Smali2Java.translate(smaliEditor.getText().toString(), dexVersion));
							intent.setClass(getApplicationContext(), JavaViewActivity.class);
							intent.putExtra("Smali2Java", javaFilePath);
							intent.putExtra("Smali2JavaName", currentTitle.replace(".smali", "").concat(".java"));
							startActivity(intent);
							SketchwareUtil.showMessage(getApplicationContext(), "Success");
						}
					} catch (Exception e) {
						try {
							Matcher matcher = Pattern.compile("\\[(\\d+),(\\d+)\\]").matcher(e.getMessage());
							if (matcher.find()) {
								smaliEditor.setSelection(Integer.parseInt(matcher.group(1)) - 1, Integer.parseInt(matcher.group(2)));
							}
						} catch (Exception ignored) {
						}
						ErrorDlg(e.getMessage());
					}
					handler.sendEmptyMessage(0);
					Looper.loop();
				}
			}.start();
		}
	}
	
	
	private void saveFile(boolean isOnBackPressed) {
		showProgressDialog(true);
		new SaveFileTask(isOnBackPressed).start();
	}
	
	private class SaveFileTask extends Thread {
		private final boolean isOnBackPressed;
		
		SaveFileTask(boolean isOnBackPressed) {
			this.isOnBackPressed = isOnBackPressed;
		}
		
		@Override
		public void run() {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					showProgressDialog(true);
					final Handler handler = new Handler() {
						@Override
						public void handleMessage(Message msg) {
							showProgressDialog(false);
						}
					};
					new Thread() {
						@Override
						public void run() {
							Looper.prepare();
							try {
								classTree.saveClassDef(Smali2.assemble(smaliEditor.getText().toString(), new SmaliOptions(), dexVersion));
								showProgressDialog(false);
								isEditMode = false;
								SketchwareUtil.showMessage(getApplicationContext(), "Saved successfully");
								if (isOnBackPressed) {
									finish();
									Animatoo.animateSlideLeft(TextEditorActivity.this);
								}
							} catch (Exception e) {
								showProgressDialog(false);
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										showProgressDialog(false);
										runOnUiThread(new ErrorRunnable(e));
									}
								});
							}
							handler.sendEmptyMessage(0);
							Looper.loop();
						}
					}.start();
				}
			});
		}
	}
	
	private class ErrorRunnable implements Runnable {
		private final Exception exception;
		
		ErrorRunnable(Exception exception) {
			this.exception = exception;
		}
		
		@Override
		public void run() {
			try {
				saveCompileError = exception.getMessage();
				Matcher matcher = Pattern.compile("\\[(\\d+),(\\d+)\\]").matcher(saveCompileError);
				if (!matcher.find()) {
					String javaClassName = Smali2JavaName2(intentClassName);
					String[] splitText = splitText(saveCompileError);
					if (splitText.length == 2) {
						String className = splitText[0];
						final String methodName = splitText[1];
						if (Smali2JavaName(className).equals(javaClassName)) {
							try {
								if (isFileCreated.isEmpty()) {
									saveSmaliCodeToFile(smaliEditor.getText().toString(), tempSmaliPath, new FileSaveCallback() {
										@Override
										public void onFileSaved(String filePath) {
											try {
												isFileCreated = "true";
												extractInfo(filePath, methodName);
											} catch (Exception e) {
												ErrorDlg(e.toString());
											}
										}
									});
								} else if (!FileUtil.isExistFile(tempSmaliPath)) {
									saveSmaliCodeToFile(smaliEditor.getText().toString(), tempSmaliPath, new FileSaveCallback() {
										@Override
										public void onFileSaved(String filePath) {
											try {
												isFileCreated = "true";
												extractInfo(filePath, methodName);
											} catch (Exception e) {
												ErrorDlg(e.toString());
											}
										}
									});
								} else {
									extractInfo(tempSmaliPath, methodName);
								}
							} catch (Exception e) {
								ErrorDlg(e.toString());
							}
						}
					}
				} else {
					int lineNumber = Integer.parseInt(matcher.group(1));
					smaliEditor.setSelection(lineNumber - 1, Integer.parseInt(matcher.group(2)));
				}
			} catch (Exception e) {
			}
			ErrorDlg(exception.getMessage());
		}
	}
	
	public static String getSmaliByType(ClassDef classDef) throws Exception {
		StringWriter stringWriter = new StringWriter();
		BaksmaliWriter baksmaliWriter = new BaksmaliWriter(stringWriter);
		new ClassDefinition(new BaksmaliOptions(), classDef).writeTo(baksmaliWriter);
		baksmaliWriter.close();
		return stringWriter.toString();
	}
	
	public void showPreviousErrorDlg(String errorMessage) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Error"); 
		builder.setMessage(""); 
		builder.setPositiveButton("Cancel", (DialogInterface.OnClickListener) null);
		
		final AlertDialog dialog = builder.create();
		dialog.getWindow().setBackgroundDrawable(Notify_MT.createDrawable(20, -1));
		dialog.show(); 
		
		TextView textView = (TextView) dialog.findViewById(android.R.id.message);
		textView.setClickable(true); // Make the text clickable
		
		SpannableString spannableString = new SpannableString(errorMessage);
		
		Matcher matcher = Pattern.compile("\\[(.*?)\\]").matcher(errorMessage);
		while (matcher.find()) {
			final String match = matcher.group(1);
			spannableString.setSpan(new ClickableSpan() {
				@Override
				public void onClick(View view) {
					try {
						Pattern pattern = Pattern.compile("\\[(\\d+),(\\d+)\\]");
						Matcher lineColumnMatcher = pattern.matcher("[" + match + "]");
						if (lineColumnMatcher.find()) {
							int lineNumber = Integer.parseInt(lineColumnMatcher.group(1));
							int columnNumber = Integer.parseInt(lineColumnMatcher.group(2));
							
							smaliEditor.setSelection(lineNumber - 1, columnNumber);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					dialog.dismiss();
				}
				
				@Override
				public void updateDrawState(TextPaint textPaint) {
					super.updateDrawState(textPaint);
					textPaint.setUnderlineText(false);
					textPaint.setColor(textPaint.linkColor); 
				}
			}, matcher.start(), matcher.end(), 33);
		}
		
		// Set the SpannableString to the TextView
		textView.setText(spannableString);
		textView.setMovementMethod(LinkMovementMethod.getInstance()); // Enable clickable links
		textView.setHighlightColor(0); // Disable text highlighting
	}
	
	private String customException(Exception e) {
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}
	
	
	private void loadTheme() {
		smaliEditor.setColorScheme(getColorScheme("light.json"));
		smaliEditor.setEditorLanguage(getSmaliLanguage("light.json"));
		try {
			TextMateColorScheme colorScheme = TextMateColorScheme.create(ThemeRegistry.getInstance());
			smaliEditor.setColorScheme(colorScheme);
			colorScheme.setColor(1, Color.parseColor("#E0E0E0")); // Background color
			colorScheme.setColor(3, Color.parseColor("#F0F0F0")); // Line number background
			colorScheme.setColor(17, Color.parseColor("#B0B0B0")); // Line number text
		} catch (Exception ignored) {
		}
	}
	
	private TextMateColorScheme getColorScheme(String themeName) {
		try {
			AssetManager assets = getAssets();
			return TextMateColorScheme.create(IThemeSourceMT.fromInputStream(assets.open("themes/" + themeName), themeName, null));
		} catch (Exception ignored) {
			return null;
		}
	}
	
	private Language getSmaliLanguage(String themeName) {
		try {
			return TextMateLanguage.create(
			IGrammarSourceMT.fromInputStream(getAssets().open("smali/syntaxes/smali.tmLanguage.json"), "smali.tmLanguage.json", null),
			new InputStreamReader(getAssets().open("smali/language-configuration.json")),
			getColorScheme(themeName).getThemeSource()
			);
		} catch (IOException ignored) {
			return new EmptyLanguage();
		}
	}
	
	public void _copyLine() {
		Cursor cursor = smaliEditor.getCursor();
		if (cursor.isSelected()) {
			smaliEditor.copyText();
			return;
		}
		int i = cursor.left().line;
		smaliEditor.setSelectionRegion(i, 0, i, smaliEditor.getText().getColumnCount(i));
		smaliEditor.copyText(false);
	}
	
	
	public void toggleComment() {
		Cursor cursor = smaliEditor.getCursor();
		Content text = smaliEditor.getText();
		
		if (cursor.isSelected()) {
			int startLine = cursor.getLeftLine();
			int endLine = cursor.getRightLine();
			
			boolean allCommented = true;
			for (int line = startLine; line <= endLine; line++) {
				String lineStr = text.getLineString(line);
				int firstCharPos = getFirstNonWhitespace(lineStr);
				
				if (firstCharPos < lineStr.length() && lineStr.charAt(firstCharPos) != '#') {
					allCommented = false;
					break;
				}
			}
			
			for (int line = startLine; line <= endLine; line++) {
				String lineStr = text.getLineString(line);
				int firstCharPos = getFirstNonWhitespace(lineStr);
				
				if (firstCharPos >= lineStr.length()) {
					continue;
				}
				
				if (allCommented) {
					if (lineStr.charAt(firstCharPos) == '#') {
						int endPos = firstCharPos + 1;
						if (endPos < lineStr.length() && lineStr.charAt(endPos) == ' ') {
							endPos++;
						}
						smaliEditor.setSelectionRegion(line, firstCharPos, line, endPos);
						smaliEditor.deleteText();
					}
				} else {
					if (lineStr.charAt(firstCharPos) != '#') {
						smaliEditor.setSelection(line, firstCharPos);
						smaliEditor.commitText("# ");
					}
				}
			}
			
			smaliEditor.setSelectionRegion(startLine, 0, endLine, text.getColumnCount(endLine));
		} else {
			int line = cursor.getLeftLine();
			String lineStr = text.getLineString(line);
			int firstCharPos = getFirstNonWhitespace(lineStr);
			
			if (firstCharPos < lineStr.length()) {
				if (lineStr.charAt(firstCharPos) == '#') {
					int endPos = firstCharPos + 1;
					if (endPos < lineStr.length() && lineStr.charAt(endPos) == ' ') {
						endPos++;
					}
					smaliEditor.setSelectionRegion(line, firstCharPos, line, endPos);
					smaliEditor.deleteText();
				} else {
					smaliEditor.setSelection(line, firstCharPos);
					smaliEditor.commitText("# ");
				}
			}
		}
	}
	
	private int getFirstNonWhitespace(String line) {
		for (int i = 0; i < line.length(); i++) {
			if (!Character.isWhitespace(line.charAt(i))) {
				return i;
			}
		}
		return line.length();
	}
	
	public void deleteLine() {
		Cursor cursor = smaliEditor.getCursor();
		if (cursor.isSelected()) {
			smaliEditor.deleteText();
			return;
		}
		
		io.github.rosemoe.sora.text.CharPosition pos = cursor.left();
		int currentLine = pos.getLine();
		int nextLine = currentLine + 1;
		
		if (nextLine == smaliEditor.getLineCount()) {
			smaliEditor.setSelectionRegion(
			currentLine, 0, 
			currentLine, smaliEditor.getText().getColumnCount(currentLine)
			);
		} else {
			smaliEditor.setSelectionRegion(
			currentLine, 0, 
			nextLine, 0
			);
		}
		smaliEditor.deleteText();
	}
	
	public void emptyLine() {
		final CodeEditor editor = smaliEditor;
		final Content content = editor.getText();
		final Cursor cur = editor.getCursor();
		
		final int startLine = cur.getLeftLine();
		final int endLine = cur.isSelected() ? cur.getRightLine() : startLine;
		
		content.beginBatchEdit();
		try {
			for (int line = endLine; line >= startLine; line--) {
				final int lineEnd = content.getColumnCount(line);
				if (lineEnd > 0) {
					content.delete(line, 0, line, lineEnd);
				}
			}
		} finally {
			content.endBatchEdit();
		}
		
		editor.setSelection(startLine, 0);
	}
	
	private void replaceLine() {
		final CodeEditor editor = smaliEditor;
		final Content content = editor.getText();
		final Cursor cursor = editor.getCursor();
		
		content.beginBatchEdit();
		try {
			if (cursor.isSelected()) {
				int startLine = cursor.getLeftLine();
				int endLine = cursor.getRightLine();
				int endCol = content.getColumnCount(endLine);
				
				content.delete(startLine, 0, endLine, endCol);
				editor.pasteText();
				editor.setSelection(startLine, 0);
			} else {
				int line = cursor.getLeftLine();
				int lineLength = content.getColumnCount(line);
				
				content.delete(line, 0, line, lineLength);
				editor.pasteText();
				
				editor.setSelection(line, 0);
			}
		} finally {
			content.endBatchEdit();
		}
	}
	
	private void duplicateLine() {
		final CodeEditor editor = smaliEditor;
		final Content content = editor.getText();
		final Cursor cursor = editor.getCursor();
		
		content.beginBatchEdit();
		try {
			if (cursor.isSelected()) {
				int startLine = cursor.getLeftLine();
				int endLine = cursor.getRightLine();
				
				StringBuilder duplicateContent = new StringBuilder("\n");
				for (int i = startLine; i <= endLine; i++) {
					duplicateContent.append(content.getLineString(i)).append(i < endLine ? "\n" : "");
				}
				
				content.insert(
				endLine, content.getColumnCount(endLine),
				duplicateContent.toString()
				);
				
				int newStartLine = endLine + 1;
				int newEndLine = endLine + 1 + (endLine - startLine);
				editor.setSelectionRegion(
				newStartLine, 0,
				newEndLine, content.getColumnCount(newEndLine)
				);
			} else {
				int line = cursor.getLeftLine();
				int column = cursor.getLeftColumn();
				String lineContent = content.getLineString(line);
				
				content.insert(
				line, content.getColumnCount(line), "\n" + lineContent);
				
				editor.setSelection(line, column);
			}
		} finally {
			
			content.endBatchEdit();
		}
	}
	
	public void convertUpperLowerCase(boolean toUpper) {
		final CodeEditor editor = smaliEditor;
		final Content content = editor.getText();
		final Cursor cursor = editor.getCursor();
		
		if (!cursor.isSelected()) {
			SketchwareUtil.showMessage(this, "Please select the text to be converted first");
			return; 
		}
		
		content.beginBatchEdit();
		try {
			int startLine = cursor.getLeftLine();
			int startCol = cursor.getLeftColumn();
			int endLine = cursor.getRightLine();
			int endCol = cursor.getRightColumn();
			
			String selectedText = content.subContent(startLine, startCol, endLine, endCol).toString();
			String convertedText = toUpper ? selectedText.toUpperCase() : selectedText.toLowerCase();
			content.replace(startLine, startCol, endLine, endCol, convertedText);
			editor.setSelectionRegion(startLine, startCol, endLine, startCol + convertedText.length());
		} finally {
			content.endBatchEdit();
		}
	}
	
	public void increaseIndent() {
		final CodeEditor editor = smaliEditor;
		final Content content = editor.getText();
		final Cursor cursor = editor.getCursor();
		final int tabWidth = editor.getTabWidth();
		final String tabSpaces = String.format("%" + tabWidth + "s", "");
		
		content.beginBatchEdit();
		try {
			if (cursor.isSelected()) {
				int startLine = cursor.getLeftLine();
				int endLine = cursor.getRightLine();
				
				for (int line = startLine; line <= endLine; line++) {
					content.insert(line, 0, tabSpaces);
				}
			} else {
				int line = cursor.getLeftLine();
				int column = cursor.getLeftColumn();
				content.insert(line, column, tabSpaces);
				editor.setSelection(line, column + tabWidth);
			}
		} finally {
			content.endBatchEdit();
		}
	}
	
	public void decreaseIndent() {
		final CodeEditor editor = smaliEditor;
		final Content content = editor.getText();
		final Cursor cursor = editor.getCursor();
		final int tabWidth = editor.getTabWidth();
		
		content.beginBatchEdit();
		try {
			int startLine = cursor.isSelected() ? cursor.getLeftLine() : cursor.getLeftLine();
			int endLine = cursor.isSelected() ? cursor.getRightLine() : cursor.getLeftLine();
			
			for (int line = startLine; line <= endLine; line++) {
				String lineText = content.getLineString(line);
				int spacesToRemove = 0;
				
				while (spacesToRemove < lineText.length() && 
				lineText.charAt(spacesToRemove) == ' ') {
					spacesToRemove++;
				}
				
				if (spacesToRemove > 0) {
					int removeCount = spacesToRemove >= tabWidth ? tabWidth : spacesToRemove;
					content.delete(line, 0, line, removeCount);
				}
			}
		} finally {
			content.endBatchEdit();
		}
	}
	
	
	private void reloadEditorPosition(int position) {
		new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
			@Override
			public void run() {
				try {
					smaliEditor.post(new Runnable() {
						@Override
						public void run() {
							smaliEditor.jumpToLine(position);
						}
					});
				} catch (Exception e) {
					ErrorDlg(e.toString());
				}
			}
		}, 200);
	}
	
	
	
	public void jumpToLine() {
		View view = LayoutInflater.from(this).inflate(R.layout.dialog_jump_to_line, null);
		TextInputLayout textInputLayout = view.findViewById(R.id.textInputLayout);
		EditText editText = view.findViewById(R.id.editText);
		
		// Set dynamic hint
		String hint = "Line number [1-" + smaliEditor.getLineCount() + "]";
		textInputLayout.setHint(hint);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this)
		.setTitle("Jump to line")
		.setView(view)
		.setPositiveButton("OK", null)
		.setNegativeButton("Cancel", null);
		
		AlertDialog dialog_mt = builder.create();
		
		
		dialog_mt.getWindow().setBackgroundDrawable(Notify_MT.createDrawable(20, Color.parseColor("#FFFFFF")));
		dialog_mt.show();
		dialog_mt.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v){
				
				if (editText.getText().toString().equals("")) {
					textInputLayout.setError("Enter something !");
				}
				else {
					try {
						smaliEditor.jumpToLine(Integer.parseInt(editText.getText().toString()) - 1);
						dialog_mt.dismiss();
					} catch (Exception e) {
						textInputLayout.setError("Value is out of range.");
					}
				}
				
			}
		});
	}
	
	public void dismissEditorWindow(){
		new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
			@Override
			public void run() {
				try {
					smaliEditor.hideEditorWindows();
				} catch (Exception e) {
					ErrorDlg(e.toString());
				}
			}
		}, 50);
	}
	
	private void showProgressDialog(boolean show) {
		if (show) {
			if (progressDialog == null) {
				progressDialog = new ProgressDialog(this);
				progressDialog.setCancelable(false);
				progressDialog.setCanceledOnTouchOutside(false);
				progressDialog.requestWindowFeature(1);
				progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
			}
			progressDialog.setMessage(null);
			try {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						progressDialog.show();
					}
				});
			} catch (WindowManager.BadTokenException e) {
				// Ignore
			}
			progressDialog.setContentView(R.layout.loading);
			LinearLayout backgroundLayout = progressDialog.findViewById(R.id.background);
			GradientDrawable gradientDrawable = new GradientDrawable();
			gradientDrawable.setColor(Color.parseColor("#E0E0E0"));
			gradientDrawable.setCornerRadius(40.0f);
			gradientDrawable.setStroke(0, Color.WHITE);
			progressDialog.findViewById(R.id.linear2).setBackground(gradientDrawable);
			((LinearLayout) progressDialog.findViewById(R.id.layout_progress)).addView(new RadialProgressView(this));
		} else if (progressDialog != null) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					progressDialog.dismiss();
				}
			});
		}
	}
	
	public String getCurrentLineInstruction() {
		CodeEditor editor = smaliEditor;
		Cursor cursor = editor.getCursor();
		Content content = editor.getText();
		
		int line = cursor.getLeftLine();
		String lineText = content.getLineString(line);
		String trimmed = lineText.trim();
		
		if (trimmed.isEmpty()) {
			return null;
		}
		
		
		int endOfFirstWord = 0;
		while (endOfFirstWord < trimmed.length()) {
			char c = trimmed.charAt(endOfFirstWord);
			if (Character.isWhitespace(c)) break;  // Fixed syntax here
			if (c == '{' || c == '}' || c == ';') break;
			endOfFirstWord++;
		}
		
		String firstWord = trimmed.substring(0, endOfFirstWord);
		return SmaliInstructionHelper.isSmaliInstruction(firstWord) ? firstWord : null;
	}
	
	public String Smali2JavaName(String smaliName) {
		return smaliName.substring(1, smaliName.length() - 1).replace('/', '.');
	}
	
	public String Smali2JavaName2(String smaliName) {
		return smaliName.replace('/', '.');
	}
	
	public String smali2OnlySlash(String smaliName) {
		return smaliName.substring(1, smaliName.length() - 1);
	}
	
	public static String extractSubstringAfterLastSlash(String str) {
		int lastSlashIndex = str.lastIndexOf('/');
		return lastSlashIndex != -1 ? str.substring(lastSlashIndex + 1) : str;
	}
	
	public String[] splitText(String text) {
		return text.split("->");
	}
	
	private String _getTextBefore(String text, String delimiter) {
		int index = text.indexOf(delimiter);
		return index != -1 ? text.substring(0, index) : "";
	}
	
	public static int[] getOuterQuotePositions(String input) {
		int startQuote = -1;
		int endQuote = -1;
		boolean insideString = false;
		boolean escapeNext = false;
		
		for (int i = 0; i < input.length(); i++) {
			char currentChar = input.charAt(i);
			if (currentChar == '\\') {
				escapeNext = true;
			} else if (currentChar == '"' && !escapeNext) {
				if (!insideString) {
					startQuote = i;
					insideString = true;
				} else {
					endQuote = i;
					break; 
				}
			} else {
				escapeNext = false;
			}
		}
		
		return new int[]{startQuote, endQuote};
	}
	
	public void copiedToClipboard(String text) {
		((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", text));
		SketchwareUtil.showMessage(this, "Text has been copied to clipboard");
	}
	
	
}
