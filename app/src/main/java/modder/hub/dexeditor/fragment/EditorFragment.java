/*
 * Dex-Editor-Android an Advanced Dex Editor for Android
 * Copyright 2024-26, developer-krushna
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
 */

package modder.hub.dexeditor.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.eclipse.tm4e.core.registry.IGrammarSource;
import org.eclipse.tm4e.core.registry.IThemeSource;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;

import io.github.rosemoe.sora.event.ContentChangeEvent;
import io.github.rosemoe.sora.event.SelectionChangeEvent;
import io.github.rosemoe.sora.lang.EmptyLanguage;
import io.github.rosemoe.sora.lang.Language;
import io.github.rosemoe.sora.langs.java.JavaLanguage;
import io.github.rosemoe.sora.langs.textmate.TextMateColorScheme;
import io.github.rosemoe.sora.langs.textmate.TextMateLanguage;
import io.github.rosemoe.sora.langs.textmate.registry.FileProviderRegistry;
import io.github.rosemoe.sora.langs.textmate.registry.ThemeRegistry;
import io.github.rosemoe.sora.langs.textmate.registry.provider.AssetsFileResolver;
import io.github.rosemoe.sora.text.Content;
import io.github.rosemoe.sora.text.Cursor;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.SymbolInputView;
import io.github.rosemoe.sora.widget.component.EditorTextActionWindow;
import modder.hub.dexeditor.R;
import modder.hub.dexeditor.activity.DexEditorActivity;
import modder.hub.dexeditor.smali.SmaliInstructionHelper;
import modder.hub.dexeditor.utils.CustomAutoComplete;
import modder.hub.dexeditor.utils.SmaliLabelDialog;
import modder.hub.dexeditor.utils.Notify_MT;
import modder.hub.dexeditor.utils.SketchwareUtil;
import modder.hub.dexeditor.smali.SmaliCursorUtils;
import modder.hub.dexeditor.utils.EditorPositionManager;
import modder.hub.dexeditor.smali.SmaliHelper;
import modder.hub.dexeditor.utils.UIHelper;
import modder.hub.dexeditor.views.TextActionWindow;


// Author - @developer-krushna (Krushna Chandra developer of Modder Hub)
// Took help of AI for implementinhg the EditorFragment idea for smooth navigating without loosing editor position
// needs some optimization for large number of class editing and its possible , the advance level of fragmnent and tab management still needs to be study
// and I tried my best to make it possible

// This support for opening and listing both java and smali ( no extention for .smali class)
// Not implemented the smali method body to java and smali flow chart fragment (it still uses the activity) in the recent drawer list

public class EditorFragment extends Fragment implements SmaliMethodFieldListFragment.DialogLineNumberListener {

    private String className;
    private String title;
    private int type; // 0: Smali, 1: Java
    private String initialContentText;

    private CodeEditor smaliEditor;
    private ProgressBar loadingProgress;
    private TextView textviewLeft;
    private TextView textviewLineNo;
    private TextView methodName;
    private SymbolInputView symbol_input;
    private LinearLayout linearLeft;
    private LinearLayout linearRight;

    private EditorPositionManager positionManager;
    private SharedPreferences editor_prefs;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor preferencesEditor;
    private PackageManager packageManager;
    private String savedFont = "normal";
    private boolean isClosing = false;
    private boolean isReload = false;
    private boolean isInitializing = true;
    private String saveEvent = "";
    private String isFileCreated = "";
    private String tempSmaliPath;

    private SmaliCursorUtils.MethodInfo currentMethodInfo;

    private static boolean tmRegistered = false;

    public static final String[] SYMBOLS = new String[] {
            "->", "{", "}", "(", ")",
            ",", ".", ";", "\"", "?",
            "+", "-", "*", "/", "<",
            ">", "[", "]", ":"
    };

    public static final String[] SYMBOL_INSERT_TEXT = new String[] {
            "\t", "{}", "}", "(", ")",
            ",", ".", ";", "\"", "?",
            "+", "-", "*", "/", "<",
            ">", "[", "]", ":"
    };

    public interface FileSaveCallback {
        void onFileSaved(String filePath);
    }

    public static EditorFragment newInstance(String className, String title, String content, int type) {
        EditorFragment fragment = new EditorFragment();
        Bundle args = new Bundle();
        args.putString("className", className);
        args.putString("title", title);
        args.putString("content", content);
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            className = getArguments().getString("className");
            title = getArguments().getString("title");
            initialContentText = getArguments().getString("content");
            type = getArguments().getInt("type");
        }
        tempSmaliPath = requireContext().getFilesDir() + "/tmp_" + className.hashCode() + ".smali";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.editor_fragment, container, false);
        initViews(view);
        initializeLogic();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Activity activity = getActivity();
        if (activity instanceof DexEditorActivity) {
            DexEditorActivity.EditorTab tab = ((DexEditorActivity) activity).getTabForClassName(className);
            if (tab != null && smaliEditor != null) {
                if (type == 1) { // Java
                    smaliEditor.setEditable(false); // set false
                } else {
                    smaliEditor.setEditable(!tab.isReadOnly);
                }
            }
        }
    }

    // Initializing views and setting up listeners for the editor interface
    private void initViews(View view) {
        smaliEditor = view.findViewById(R.id.smali_editor);
        loadingProgress = view.findViewById(R.id.loading_progress);
        textviewLeft = view.findViewById(R.id.textview_left);
        textviewLineNo = view.findViewById(R.id.textview_lineNo);
        methodName = view.findViewById(R.id.methodName);
        symbol_input = view.findViewById(R.id.symbol_input);
        linearLeft = view.findViewById(R.id.linear_left);
        linearRight = view.findViewById(R.id.linear_right);

        View.OnLongClickListener selectMethodListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (type == 0 && currentMethodInfo != null && currentMethodInfo.startLine != -1 && currentMethodInfo.endLine != -1) {
                    smaliEditor.setSelectionRegion(currentMethodInfo.startLine, 0, currentMethodInfo.endLine, smaliEditor.getText().getColumnCount(currentMethodInfo.endLine));
                    return true;
                }
                return false;
            }
        };
        textviewLineNo.setOnLongClickListener(selectMethodListener);
        linearRight.setOnLongClickListener(selectMethodListener);

        positionManager = EditorPositionManager.getInstance(requireContext());
        editor_prefs = requireContext().getSharedPreferences("editor_prefs", Context.MODE_PRIVATE);
        sharedPreferences = requireContext().getSharedPreferences("SelectedTranslationPackageName", 0);
        preferencesEditor = sharedPreferences.edit();
        packageManager = requireContext().getPackageManager();

        textviewLeft.setText(title);

        linearLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() instanceof DexEditorActivity) {
                    ((DexEditorActivity) getActivity()).toggleDrawer();
                }
            }
        });

        linearLeft.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(requireContext(), v);
                Menu menu = popupMenu.getMenu();
                menu.add(1, 1, 1, title);
                menu.add(2, 2, 2, className.replace('/', '.'));
                menu.add(3, 3, 3, className);
                menu.add(4, 4, 4, "L" + className + ";");
                menu.add(5,5, 5, "Locate");

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        if (id == 5) { // Locate
                            Activity activity = getActivity();
                            if (activity instanceof DexEditorActivity) {
                                ((DexEditorActivity) activity).locateClass(className);
                            }
                        } else {
                            UIHelper.copyToClipboard(requireContext(), Objects.requireNonNull(menuItem.getTitle()).toString());
                        }
                        return true;
                    }
                });

                popupMenu.show();
                return true;
            }
        });

        linearRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new MethodFieldListRunnable());
            }
        });
    }

    @SuppressLint("DefaultLocale")
    private void initializeLogic() {
        savedFont = SettingsFragment.getFontType(requireContext());
        
        // Setup line numbers early for a better skeleton feel
        smaliEditor.setLineNumberEnabled(SettingsFragment.showLineNumbers(requireContext()));
        
        // Postpone heavy language initialization to allow transition animation to start smoothly
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (!isAdded()) return;
                updateEditorUI();
                loadEditorSettings(true);
                symbol_input.bindEditor(smaliEditor);
                symbol_input.addSymbols(SYMBOLS, SYMBOL_INSERT_TEXT);

                if (initialContentText != null) {
                    smaliEditor.setText(initialContentText);
                    postInitialize(false);
                } else {
                    loadSmaliInBackground();
                }
            }
        });

        smaliEditor.subscribeEvent(ContentChangeEvent.class, new io.github.rosemoe.sora.event.EventReceiver<ContentChangeEvent>() {
            @Override
            public void onReceive(@NonNull ContentChangeEvent event, @NonNull io.github.rosemoe.sora.event.Unsubscribe unsubscribe) {
                if (isInitializing) return;
                Activity act = getActivity();
                if (act instanceof DexEditorActivity) {
                    ((DexEditorActivity) act).onContentModified(className);
                    ((DexEditorActivity) act).handleUndoRedo();
                }
                if (!isReload && !isClosing) {
                    int position = smaliEditor.getCursor().getLeftLine();
                    positionManager.savePosition(className, position, smaliEditor.getCursor().getLeftColumn());
                }
                if (!saveEvent.isEmpty()) {
                    isFileCreated = "";
                }
                saveEvent = "Saved";
                isReload = false;
            }
        });

        smaliEditor.subscribeEvent(SelectionChangeEvent.class, new io.github.rosemoe.sora.event.EventReceiver<SelectionChangeEvent>() {
            @Override
            public void onReceive(@NonNull SelectionChangeEvent event, @NonNull io.github.rosemoe.sora.event.Unsubscribe unsubscribe) {
                Cursor cursor = smaliEditor.getCursor();
                Content text = smaliEditor.getText();
                int line = cursor.getLeftLine() + 1;
                int column = cursor.getLeftColumn() + 1;

                if (!isReload && !isInitializing && !isClosing) {
                    positionManager.savePosition(className, cursor.getLeftLine(), cursor.getLeftColumn());
                }

                currentMethodInfo = (type == 0) ? SmaliCursorUtils.getMethodInfo(text, cursor.getLeftLine()) : null;

                StringBuilder positionText = new StringBuilder();
                positionText.append(String.format("%d:%d", line, column));

                if (currentMethodInfo != null && currentMethodInfo.startLine != -1 && currentMethodInfo.endLine != -1) {
                    positionText.append(" [").append(currentMethodInfo.startLine + 1).append("-").append(currentMethodInfo.endLine + 1).append("]");
                }

                if (cursor.isSelected()) {
                    String selectedText = text.subSequence(cursor.getLeft(), cursor.getRight()).toString();
                    positionText.append(" (").append(selectedText.length()).append(")");
                }
                textviewLineNo.setText(positionText.toString());

                String currentElement;
                if (currentMethodInfo != null && currentMethodInfo.name != null) {
                    currentElement = currentMethodInfo.getDisplayName() + "()";
                } else {
                    currentElement = SmaliCursorUtils.getCurrentMethodOrFieldName(text, cursor.getLeftLine());
                }
                methodName.setText(currentElement != null ? currentElement : "...");
            }
        });
    }

    // loading the smali code in the editor fragment
    private void loadSmaliInBackground() {
        if (loadingProgress != null) loadingProgress.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Activity activity = getActivity();
                    if (activity instanceof DexEditorActivity) {
                        final DexEditorActivity dexActivity = (DexEditorActivity) activity;
                        final String smaliCode = DexEditorActivity.classTree.getSmaliByType(Objects.requireNonNull(DexEditorActivity.classTree.classMap.get(className)));

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (loadingProgress != null) loadingProgress.setVisibility(View.GONE);
                                
                                initialContentText = smaliCode;
                                smaliEditor.setText(smaliCode);

                                final DexEditorActivity.EditorTab tab = dexActivity.getTabForClassName(className);
                                if (tab != null) {
                                    tab.content = smaliCode;
                                }
                                boolean hasPending = tab != null && tab.pendingLine != -1;

                                postInitialize(hasPending);

                                // Handle pending navigation
                                if (hasPending) {
                                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            smaliEditor.requestFocus();
                                            navigateTo(tab.pendingLine, tab.pendingColumn, tab.pendingQuery);
                                            tab.pendingLine = -1;
                                            tab.pendingColumn = -1;
                                            tab.pendingQuery = null;
                                        }
                                    }, 200); // Increased delay for first-time layout
                                }
                            }
                        });
                    }
                } catch (final Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (loadingProgress != null) loadingProgress.setVisibility(View.GONE);
                            Notify_MT.Notify(getContext(), getString(R.string.error), e.toString(), getString(R.string.close));
                        }
                    });
                }
            }
        }).start();
    }

    private void postInitialize(boolean skipRestorePosition) {
        Activity activity = getActivity();
        if (activity instanceof DexEditorActivity) {
            DexEditorActivity.EditorTab tab = ((DexEditorActivity) activity).getTabForClassName(className);
            if (tab != null) {
                if (type == 1) { // Java
                    smaliEditor.setEditable(false);
                } else {
                    smaliEditor.setEditable(!tab.isReadOnly);
                }
            }
        }

        // Reset initializing flag after text is set and events are dispatched
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                isInitializing = false;
            }
        });

        if (!skipRestorePosition) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        EditorPositionManager.Position pos = positionManager.getPosition(className);
                        if (pos != null) {
                            int line = pos.lineno;
                            int column = pos.column;
                            if (line >= 0 && line < smaliEditor.getText().getLineCount()) {
                                smaliEditor.jumpToLine(line);
                                // Set cursor column if possible
                                smaliEditor.getCursor().set(line, column);
                            }
                        }
                    } catch (Exception ignored) {}
                }
            }, 100);
        }
    }

    private static Language cachedSmaliLanguage;
    private static TextMateColorScheme cachedColorScheme;
    private static String[] cachedInstructions;

    public static void clearCache() {
        cachedSmaliLanguage = null;
        cachedColorScheme = null;
        cachedInstructions = null;
    }

    // sora editor theme
    public static synchronized void ensureLanguageInitialized(Context context) {
        if (cachedSmaliLanguage != null) return;
        try {
            initTMStatic(context);
            ThemeRegistry registry = ThemeRegistry.getInstance();
            String themeName = "light.json";
            IThemeSource themeSource = null;
            try {
                themeSource = IThemeSource.fromInputStream(context.getAssets().open("themes/light.json"), themeName, null);
                registry.loadTheme(themeSource);
                registry.setTheme(themeName);
            } catch (Exception e) {
                Log.e("EditorFragment", "Theme load error", e);
            }

            try {
                cachedSmaliLanguage = TextMateLanguage.create(
                        IGrammarSource.fromInputStream(context.getAssets().open("smali/syntaxes/smali.tmLanguage.json"), "smali.tmLanguage.json", null),
                        new InputStreamReader(context.getAssets().open("smali/language-configuration.json")),
                        themeSource
                );
            } catch (Exception e) {
                Log.e("EditorFragment", "Smali language load error", e);
            }
            cachedColorScheme = TextMateColorScheme.create(registry);
            cachedInstructions = SmaliInstructionHelper.getAllSmaliInstructions();
        } catch (Exception e) {
            Log.e("EditorFragment", "Static init error", e);
        }
    }

    private static void initTMStatic(Context context) {
        if (tmRegistered) return;
        try {
            FileProviderRegistry.getInstance().addFileProvider(new AssetsFileResolver(context.getAssets()));
            tmRegistered = true;
        } catch (Exception ignored) {}
    }

    // Configuring the sora editor theme and language support for Smali or Java
    private void updateEditorUI() {
        View view = getView();
        if (type == 0) {
            try {
                ensureLanguageInitialized(requireContext().getApplicationContext());
                smaliEditor.setEditorLanguage(new CustomAutoComplete(smaliEditor, cachedInstructions, cachedSmaliLanguage));
                smaliEditor.setColorScheme(cachedColorScheme);
            } catch (Exception e) {
                Log.e("EditorFragment", "Error setting smali language/scheme", e);
                smaliEditor.setEditorLanguage(new EmptyLanguage());
            }

            symbol_input.setVisibility(View.VISIBLE);
            if (symbol_input.getParent() instanceof View) {
                ((View) symbol_input.getParent()).setVisibility(View.VISIBLE);
            }
            smaliEditor.setEditable(true);
            if (view != null) {
                view.findViewById(R.id.linear_header).setVisibility(View.VISIBLE);
            }
        } else {
            smaliEditor.setEditorLanguage(new JavaLanguage());
            symbol_input.setVisibility(View.GONE);
            if (symbol_input.getParent() instanceof View) {
                ((View) symbol_input.getParent()).setVisibility(View.GONE);
            }
            smaliEditor.setEditable(false);
            if (view != null) {
                view.findViewById(R.id.linear_header).setVisibility(View.GONE);
            }
        }
    }

    // Settings for editor font, line numbers, and word wrap based on preferences
    public void loadEditorSettings(boolean loadTypeface) {
        smaliEditor.setTextSize(SettingsFragment.getFontSize(requireContext()));
        smaliEditor.setLineNumberEnabled(SettingsFragment.showLineNumbers(requireContext()));
        smaliEditor.setLineSpacing(2.0f, 1.1f);
        smaliEditor.setLineNumberMarginLeft(2f);
        smaliEditor.setWordwrap(editor_prefs.getBoolean("wrap_text", false));
        if (loadTypeface) {
            Typeface typeface = savedFont.equals("normal") ? Typeface.DEFAULT : Typeface.MONOSPACE;
            smaliEditor.setTypefaceText(typeface);
            smaliEditor.setTypefaceLineNumber(typeface);
        } else {
            if (!savedFont.equals(SettingsFragment.getFontType(requireContext()))) {
                Typeface typeface = savedFont.equals("normal") ? Typeface.MONOSPACE : Typeface.DEFAULT;
                smaliEditor.setTypefaceText(typeface);
                smaliEditor.setTypefaceLineNumber(typeface);
                savedFont = SettingsFragment.getFontType(requireContext());
                isReload = true;
                reloadText();
            }
        }
        smaliEditor.replaceComponent(EditorTextActionWindow.class, new TextActionWindow(smaliEditor, new TextActionCallback(className)));
    }

    // reload text after closing the fragment
    private void reloadText() {
        String smaliCode = smaliEditor.getText().toString();
        smaliEditor.setText(smaliCode);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    EditorPositionManager.Position pos = positionManager.getPosition(className);
                    if (pos != null) {
                        smaliEditor.jumpToLine(pos.lineno);
                    }
                } catch (Exception ignored) {}
            }
        }, 200);
    }

    private void runOnUiThread(Runnable runnable) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(runnable);
        }
    }

    public void setClosing(boolean closing) {
        this.isClosing = closing;
    }

    public CodeEditor getEditor() {
        return smaliEditor;
    }

    public String getCode() {
        return smaliEditor.getText().toString();
    }


    public String getClassName() {
        return className;
    }

    public boolean isModified() {
        // left for something
        return false;
    }

    @Override
    public void _updateEditorLineNumber(String lineNumber) {
        if (lineNumber == null || lineNumber.isEmpty()) return;
        try {
            int lineNum = (int) Math.floor(Double.parseDouble(lineNumber));
            navigateTo(lineNum, null);
        } catch (Exception e) {
            SketchwareUtil.showMessage(requireContext(), "Invalid line number: " + lineNumber);
        }
    }

    public void navigateTo(int lineNum, String query) {
        navigateTo(lineNum, -1, query);
    }

    public void navigateTo(final int lineNum, final int column, final String query) {
        if (smaliEditor == null) return;

        // If text is not loaded yet or line count is low, retry after a short delay
        if (smaliEditor.getText().getLineCount() <= lineNum) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    navigateTo(lineNum, column, query);
                }
            }, 100);
            return;
        }

        try {
            if (lineNum >= 0 && lineNum < smaliEditor.getText().getLineCount()) {
                smaliEditor.jumpToLine(lineNum);

                if (column >= 0 && column < smaliEditor.getText().getColumnCount(lineNum)) {
                    smaliEditor.getCursor().set(lineNum, column);
                }

                String getLineText = smaliEditor.getText().getLineString(lineNum);

                if (query != null && !query.isEmpty() && !query.contains("\n")) {
                    int start = getLineText.toLowerCase().indexOf(query.toLowerCase());
                    if (start != -1) {
                        smaliEditor.setSelectionRegion(lineNum, start, lineNum, start + query.length());
                        dismissEditorWindow(smaliEditor);
                        return;
                    }
                }

                if (getLineText.contains("const-string")) {
                    int[] positions = SmaliHelper.getOuterQuotePositions(getLineText);
                    if (positions[0] != -1 && positions[1] != -1) {
                        smaliEditor.setSelectionRegion(lineNum, (positions[0] + 1), lineNum, positions[1]);
                        dismissEditorWindow(smaliEditor);
                    }
                }
            }
        } catch (Exception ignored) {}
    }



    // get the action from the editor menu
    // mainly for the goto (jump to class name with method or field name)
    private class TextActionCallback implements TextActionWindow.ItemClickCallBack {
        private final String currentClassName;

        TextActionCallback(String className) {
            this.currentClassName = className;
        }

        @Override
        public void onClickGoTo(View view, final String text) {
            if (text.startsWith(":")) {
                showLabelsCompletion(text.replace("}", ""));
            } else {
                Activity activity = getActivity();
                if (activity instanceof DexEditorActivity) {
                    ((DexEditorActivity) activity).goTo(text, currentClassName);
                }
            }
        }

        @Override
        public void onClickTranslate(View view, final String text) {
            if (!sharedPreferences.contains("selectedPackage")) {
                SketchwareUtil.showMessage(requireContext(), "Select a translation app first");
                showAvailableTranslationDlg();
                return;
            }
            try {
                String packageName = sharedPreferences.getString("selectedPackage", "");
                packageManager.getPackageInfo(packageName, 0);
                Intent intent = new Intent("android.intent.action.PROCESS_TEXT");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.PROCESS_TEXT", text);
                intent.putExtra("android.intent.extra.PROCESS_TEXT_READONLY", true);
                intent.setPackage(packageName);
                startActivity(intent);
            } catch (PackageManager.NameNotFoundException e) {
                preferencesEditor.remove("selectedPackage");
                preferencesEditor.apply();
                showAvailableTranslationDlg();
            }
        }

        @Override
        public void onLongClickTranslate(View view) {
            showAvailableTranslationDlg();
        }
    }

    // show smali labels cond, try catch, goto etc
    // sora editor doesn't support like the mt manager so i used the list dialog
    private void showLabelsCompletion(final String query) {
        int editorLineNumber = smaliEditor.getCursor().getLeftLine();
        List<String> labelList = SmaliCursorUtils.extractAllLabelLines(smaliEditor.getText(), currentMethodInfo);
        if (labelList.isEmpty()) {
            labelList = SmaliCursorUtils.extractAllLabelLines(smaliEditor.getText(), editorLineNumber);
        }
        final SmaliLabelDialog dialog = new SmaliLabelDialog(requireContext(), labelList, query, editorLineNumber);
        dialog.setOnLabelClickListener(new SmaliLabelDialog.OnLabelClickListener() {
            @Override
            public void onLabelClick(String selectedLabel) {
                int lineNumber = Integer.parseInt(selectedLabel.substring(1, selectedLabel.indexOf(']'))) - 1;
                String lineContent = smaliEditor.getText().getLineString(lineNumber);
                int columnPos = lineContent.indexOf(query);
                if (columnPos >= 0) {
                    smaliEditor.setSelection(lineNumber, columnPos);
                    smaliEditor.ensurePositionVisible(lineNumber, columnPos);
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    // get system process text action based apps list
    // idea got from mt manager (decompoiled code)
    private void showAvailableTranslationDlg() {
        Intent intent = new Intent("android.intent.action.PROCESS_TEXT");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("text/plain");
        final List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent, 0);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
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
        builder.setSingleChoiceItems(appNames, selectedIndex, new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(android.content.DialogInterface dialog, int which) {
                selectedPackage[0] = resolveInfoList.get(which).activityInfo.packageName;
            }
        });
        builder.setPositiveButton("Save", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(android.content.DialogInterface dialog, int which) {
                if (selectedPackage[0].isEmpty()) {
                    Toast.makeText(requireContext(), "No app selected", Toast.LENGTH_SHORT).show();
                    return;
                }
                preferencesEditor.putString("selectedPackage", selectedPackage[0]);
                preferencesEditor.apply();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String pkgName = resolveInfoList.get(i).activityInfo.packageName;
                if(pkgName.isEmpty()) {
                    return true;
                }
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.parse("package:" + pkgName));
                startActivity(intent);
                return true;
            }
        });

    }

    public void showMethodFieldList() {
        runOnUiThread(new MethodFieldListRunnable());
    }

    // smali naviagtion runnable for better ui thread and silent update of the list
    private class MethodFieldListRunnable implements Runnable {
        @Override
        public void run() {
            try {
                // Always save to ensure real-time data in navigation
                saveSmaliCodeToFile(smaliEditor.getText().toString(), tempSmaliPath, new FileSaveCallback() {
                    @Override
                    public void onFileSaved(String filePath) {
                        isFileCreated = "true";
                        showSmaliNavigation(filePath, title, smaliEditor.getCursor().getLeftLine());
                    }
                });
            } catch (Exception e) {
                Notify_MT.Notify(requireContext(), requireActivity().getString(R.string.error), e.toString(), requireActivity().getString(R.string.close));
            }
        }
    }


    private void saveSmaliCodeToFile(String content, String filePath, FileSaveCallback callback) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(content);
        writer.close();
        if (callback != null) {
            callback.onFileSaved(filePath);
        }
    }

    // send line number so that the navigator will find the perfect method body
    private void showSmaliNavigation(String tempSmaliPath, String currentTitle, int lineNo) {
        if (getActivity() instanceof DexEditorActivity) {
            ((DexEditorActivity) getActivity()).showSmaliNavigation(tempSmaliPath, currentTitle, lineNo);
        }
    }

    public void extractMethodFieldInfo(final String target) {
        new ExtractMethodFieldInfoTask(this, target).execute();
    }

    // this method is really facinating thing
    // both me and AI got confused during making of this lol,
    // but I give the propere idea and AI implement it accordingly
    private static class ExtractMethodFieldInfoTask {
        private final WeakReference<EditorFragment> fragmentRef;
        private final String target;
        private final Content text;
        private final Handler mainHandler = new Handler(Looper.getMainLooper());

        ExtractMethodFieldInfoTask(EditorFragment fragment, String target) {
            this.fragmentRef = new WeakReference<>(fragment);
            this.target = target;
            this.text = fragment.smaliEditor.getText();
        }

        void execute() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final TextLocation location = doInBackground();
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onPostExecute(location);
                        }
                    });
                }
            }).start();
        }

        protected TextLocation doInBackground() {
            try {
                if (target.contains(":")) {
                    return findFieldLocation(text, target);
                } else {
                    return findMethodLocation(text, target);
                }
            } catch (Exception e) {
                return null;
            }
        }

        protected void onPostExecute(TextLocation location) {
            EditorFragment fragment = fragmentRef.get();
            if (fragment != null && fragment.isAdded() && location != null) {
                int lineNumber = location.lineNumber - 1;
                fragment.smaliEditor.jumpToLine(lineNumber); // fisrt jump to line number
                mainHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            fragment.smaliEditor.setSelectionRegion(lineNumber, location.startColumn, lineNumber, location.endColumn); // then select the text according to the line & colum(start, end)
                            dismissEditorWindow(fragment.smaliEditor); // dismiss the selection window
                        } catch (Exception ignored) {}
                    }
                }, 100);
            }
        }
    }

    // dismiss the editor selection window
    public static void dismissEditorWindow(final CodeEditor smaliEditor){
        if (smaliEditor == null) return;
        smaliEditor.postDelayedInLifecycle(new Runnable() {
            @Override
            public void run() {
                try {
                    smaliEditor.hideEditorWindows();
                } catch (Exception ignored) {
                }
            }
        }, 50);
    }

    // get the method location
    // @Content is the total text , method name (extracted from the selcted line text)
    private static TextLocation findMethodLocation(Content text, String methodName) {
        for (int i = 0; i < text.getLineCount(); i++) {
            String line = text.getLineString(i);
            String trimmedLine = line.trim();
            if (!trimmedLine.isEmpty()) {
                String[] parts = trimmedLine.split(" ");
                if (parts.length > 0 && ".method".equals(parts[0]) && parts[parts.length - 1].equals(methodName)) {
                    int startIndex = line.indexOf(methodName);
                    int endIndex = (methodName.contains("(") ? methodName.indexOf("(") : methodName.length()) + startIndex;
                    return new TextLocation(i + 1, startIndex, endIndex);
                }
            }
        }
        return null;
    }

    // get field location, The line starts with field and contains ':'
    private static TextLocation findFieldLocation(Content text, String fieldName) {
        for (int i = 0; i < text.getLineCount(); i++) {
            String line = text.getLineString(i);
            if (line.trim().startsWith(".field") && line.contains(fieldName)) {
                int startIndex = line.indexOf(fieldName);
                int endIndex = (fieldName.contains(":") ? fieldName.indexOf(":") : fieldName.length()) + startIndex;
                return new TextLocation(i + 1, startIndex, endIndex);
            }
        }
        return null;
    }

    private static class TextLocation {
        int lineNumber;
        int startColumn;
        int endColumn;

        public TextLocation(int lineNumber, int startColumn, int endColumn) {
            this.lineNumber = lineNumber;
            this.startColumn = startColumn;
            this.endColumn = endColumn;
        }
    }

}
