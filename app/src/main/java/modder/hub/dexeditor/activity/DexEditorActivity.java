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

package modder.hub.dexeditor.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.android.tools.smali.smali.SmaliOptions;
import com.android.tools.smali.smali2.Smali;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import io.github.rosemoe.sora.text.Content;
import io.github.rosemoe.sora.text.Cursor;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.component.EditorTextActionWindow;
import me.zhanghai.android.fastscroll.FastScrollerBuilder;
import modder.hub.dexeditor.R;
import modder.hub.dexeditor.adapter.HeaderAdapter;
import modder.hub.dexeditor.adapter.TreeAdapter;
import modder.hub.dexeditor.fragment.EditorFragment;
import modder.hub.dexeditor.fragment.SearchFragment;
import modder.hub.dexeditor.fragment.SmaliMethodFieldListFragment;
import modder.hub.dexeditor.model.TreeNode;
import modder.hub.dexeditor.utils.ClassTree;
import modder.hub.dexeditor.utils.EditorHelper;
import modder.hub.dexeditor.utils.EditorPositionManager;
import modder.hub.dexeditor.utils.Notify_MT;
import modder.hub.dexeditor.utils.SketchwareUtil;
import modder.hub.dexeditor.utils.SmaliInstructionHelper;
import modder.hub.dexeditor.utils.SmaliHelper;
import modder.hub.dexeditor.utils.TreeHelper;
import modder.hub.dexeditor.utils.UIHelper;
import modder.hub.dexeditor.views.AlertCircularProgress;
import modder.hub.dexeditor.views.AlertProgress;
import modder.hub.dexeditor.views.SmaliInstructionsDialog;
import modder.hub.dexeditor.views.TextActionWindow;

/**
 * DexEditorActivity: The main entry point for the DEX editor.
 * Re-sequenced and refactored for better clarity and utility.
 * Author: @developer-krushna
 */
public class DexEditorActivity extends AppCompatActivity {

    // --- Constants and Static State ---
    private static final long DOUBLE_PRESS_INTERVAL = 2000;
    public static ClassTree classTree;
    public static boolean isChanged;
    public static boolean isSaved;
    public static List<EditorTab> tabs = new ArrayList<>();
    private static int currentTabIndex = -1;

    // Legacy static fields for SmaliMethodFieldListFragment state
    public static SmaliMethodFieldListFragment smaliMethodsFieldsStringsFragment = null;
    public static android.os.Parcelable methodRecyclerViewState = null;
    public static android.os.Parcelable stringsRecyclerViewState = null;
    public static boolean wasStringsVisible = false;
    public static String lastSmaliFilePath = "";

    // --- Member Fields ---
    public int dexVersion;
    private List<String> dexPaths;
    public List<TreeNode> searchNodes = new ArrayList<>();
    private List<TreeNode> treeRoots = new ArrayList<>();
    private final List<TreeNode> historyNodes = new ArrayList<>();
    private List<TreeNode> modifiedNodes = new ArrayList<>();
    private final List<String> stringList = new ArrayList<>();
    private final java.util.Stack<Integer> tabNavigationHistory = new java.util.Stack<>();
    private final ClassTree.CompilationOptions sessionOptions = new ClassTree.CompilationOptions();
    private long lastBackPressTime = 0;
    private SharedPreferences dexPref;
    private Menu optionsMenu;

    // --- UI Components ---
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private ViewPager2 viewPager;
    private TabAdapter tabAdapter;
    private RecyclerView tabsRecyclerView;
    public TabsAdapter tabsAdapter;
    private View classListContainer;
    private TabLayout explorerTabLayout;
    private ViewPager2 explorerViewPager;
    private ExplorerTabAdapter explorerTabAdapter;
    private FloatingActionButton fabDelete;
    private LinearLayout fabBackground;
    private AlertCircularProgress coreProgressDialog;
    private AlertProgress progressDialog;

    // ==========================================
    // Lifecycle Methods
    // ==========================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dex_editor);

        ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                new androidx.activity.result.ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean isGranted) {
                        if (isGranted) {
                            initializeLogic();
                        } else {
                            Notify_MT.Notify(DexEditorActivity.this, "Permission Denied", "Storage permission is required to edit DEX files.", "Go Back");
                            finish();
                        }
                    }
                }
        );

        initialize(savedInstanceState);

        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == -1) {
            requestPermissionLauncher.launch("android.permission.READ_EXTERNAL_STORAGE");
        } else {
            initializeLogic();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EditorFragment.clearCache();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }

        if (isSelectionModeActive()) {
            cancelSelectionMode();
            return;
        }

        if (viewPager.getVisibility() == View.VISIBLE) {
            if (!tabNavigationHistory.isEmpty()) {
                int lastIndex = tabNavigationHistory.pop();
                if (lastIndex >= 0 && lastIndex < tabs.size()) {
                    viewPager.setCurrentItem(lastIndex, true);
                    return;
                }
            }
            hideEditor();
            return;
        }

        boolean anyModified = false;
        for (EditorTab tab : tabs) {
            if (tab.isModified) {
                anyModified = true;
                break;
            }
        }

        if (anyModified || isChanged || isCompilationOptionsActive()) {
            showExitConfirmation();
            return;
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastBackPressTime < DOUBLE_PRESS_INTERVAL) {
            exitActivity();
        } else {
            lastBackPressTime = currentTime;
            SketchwareUtil.showMessage(this, "Press back again to exit");
        }
    }


    // ==========================================
    // Initialization & UI Setup
    // ==========================================

    private void initialize(Bundle savedInstanceState) {
        drawerLayout = findViewById(R.id.drawer_layout);
        classListContainer = findViewById(R.id.class_list_container);
        viewPager = findViewById(R.id.view_pager);
        tabsRecyclerView = findViewById(R.id.tabs_recycler_view);
        toolbar = findViewById(R.id._toolbar);
        setSupportActionBar(toolbar);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                if (viewPager.getVisibility() == View.VISIBLE && currentTabIndex != -1) {
                    tabsRecyclerView.scrollToPosition(currentTabIndex + 1);
                }
            }
        });
        drawerToggle.syncState();

        tabAdapter = new TabAdapter(this);
        viewPager.setAdapter(tabAdapter);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setUserInputEnabled(false);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                int previousIndex = currentTabIndex;
                currentTabIndex = position;

                // Targeted updates to reduce lag
                if (previousIndex != -1) {
                    tabsAdapter.notifyItemChanged(previousIndex + 1);
                }
                tabsAdapter.notifyItemChanged(position + 1);
                tabsAdapter.notifyItemChanged(0); // Update Home item selection state

                updateToolbar();
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    tabsRecyclerView.scrollToPosition(position + 1);
                }
            }
        });

        tabsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tabsAdapter = new TabsAdapter();
        tabsRecyclerView.setAdapter(tabsAdapter);
        setupTabsTouchHelper();

        fabDelete = findViewById(R.id.fab_delete);
        fabDelete.setOnClickListener(new DeleteButtonClickListener());

        initializeExplorerTabs();
        initializeFab();
        dexPref = getSharedPreferences("dexPref", Activity.MODE_PRIVATE);
    }

    private void initializeExplorerTabs() {
        explorerTabLayout = findViewById(R.id.explorer_tab_layout);
        explorerViewPager = findViewById(R.id.explorer_view_pager);
        explorerTabAdapter = new ExplorerTabAdapter(this);
        explorerViewPager.setAdapter(explorerTabAdapter);

        new TabLayoutMediator(explorerTabLayout, explorerViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                String[] titles = {"Explorer", "History", "Search", "Strings"};
                tab.setText(titles[position]);
            }
        }).attach();

        explorerTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Default behavior handled by ViewPager2
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("f" + (2000 + position));
                if (fragment != null && fragment.getView() != null) {
                    RecyclerView rv = null;
                    if (fragment instanceof ExplorerPageFragment) {
                        rv = ((ExplorerPageFragment) fragment).rv;
                    } else if (fragment instanceof SearchFragment) {
                        rv = fragment.getView().findViewById(R.id.search_results_rv);
                    }

                    if (rv != null) {
                        rv.scrollToPosition(0);
                    }
                }
            }
        });

        explorerViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (position == 3 && stringList.isEmpty()) {
                    loadStrings();
                }
            }
        });
    }

    private void initializeLogic() {
        tabs.clear();
        currentTabIndex = -1;
        isChanged = false;
        isSaved = false;

        EditorPositionManager.getInstance(this).clear();

        File[] cacheFiles = getCacheDir().listFiles();
        if (cacheFiles != null) {
            for (File file : cacheFiles) {
                if (file.isDirectory() && file.getName().startsWith("dex_editor_")) {
                    deleteRecursive(file);
                }
            }
        }

        dexPaths = getIntent().getStringArrayListExtra("SelectedDexFiles");
        setTitle("Dex Editor Plus");
        showProcessingProgress(true);
        fabDelete.setBackgroundTintList(ColorStateList.valueOf(0xFFF44336));
        fabDelete.hide();

        String uniqueId = (System.currentTimeMillis() % 1000000) + "_" + (new java.util.Random().nextInt(9000) + 1000);
        File cacheDir = new File(getCacheDir(), "dex_editor_" + uniqueId);

        if (dexPaths != null && !dexPaths.isEmpty()) {
            new LoadDexThread(dexPaths, cacheDir.getAbsolutePath()).start();
        } else {
            showErrorDialog("No DEX files provided");
            finish();
        }
    }

    private void loadStrings() {
        if (classTree == null) return;
        showProcessingProgress(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final List<String> strings = classTree.getAllStrings();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            stringList.clear();
                            stringList.addAll(strings);
                            refreshExplorerPage(3);
                            showProcessingProgress(false);
                        }
                    });
                } catch (Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showProcessingProgress(false);
                            showErrorDialog("Failed to load strings: " + e.getMessage());
                        }
                    });
                }
            }
        }).start();
    }

    private void setupTabsTouchHelper() {
        androidx.recyclerview.widget.ItemTouchHelper.Callback callback = new androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback(
                androidx.recyclerview.widget.ItemTouchHelper.UP | androidx.recyclerview.widget.ItemTouchHelper.DOWN, 0) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int fromPos = viewHolder.getBindingAdapterPosition();
                int toPos = target.getBindingAdapterPosition();
                if (fromPos == 0 || toPos == 0) return false;

                int fromTab = fromPos - 1;
                int toTab = toPos - 1;

                EditorTab movedTab = tabs.remove(fromTab);
                tabs.add(toTab, movedTab);

                if (currentTabIndex == fromTab) currentTabIndex = toTab;
                else if (fromTab < currentTabIndex && toTab >= currentTabIndex) currentTabIndex--;
                else if (fromTab > currentTabIndex && toTab <= currentTabIndex) currentTabIndex++;

                tabAdapter.notifyItemMoved(fromTab, toTab);
                tabsAdapter.notifyItemMoved(fromPos, toPos);
                return true;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {}
            @Override
            public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) { return 0; }
        };
        new androidx.recyclerview.widget.ItemTouchHelper(callback).attachToRecyclerView(tabsRecyclerView);
    }

    // ==========================================
    // Toolbar & Menu Management
    // ==========================================

    private void updateToolbar() {
        if (getSupportActionBar() == null) return;

        if (viewPager.getVisibility() == View.VISIBLE && currentTabIndex != -1 && currentTabIndex < tabs.size()) {
            getSupportActionBar().setTitle(tabs.get(currentTabIndex).title);
            getSupportActionBar().setSubtitle(null);
        } else {
            getSupportActionBar().setTitle("Dex Editor Plus");
            setToolbarSubtitle(null);
        }

        drawerToggle.setDrawerIndicatorEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();
        invalidateOptionsMenu();
    }

    public void setToolbarSubtitle(String subtitle) {
        if (getSupportActionBar() == null || viewPager.getVisibility() == View.VISIBLE) return;
        getSupportActionBar().setSubtitle((subtitle == null || subtitle.isEmpty()) ? "Temporary project" : subtitle.replace("/", "."));
    }

    public void toggleDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) drawerLayout.closeDrawer(GravityCompat.START);
        else drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.optionsMenu = menu;
        getMenuInflater().inflate(viewPager.getVisibility() == View.VISIBLE ? R.menu.editor_menu : R.menu.dex_editor_main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (viewPager.getVisibility() == View.VISIBLE && currentTabIndex != -1 && currentTabIndex < tabs.size()) {
            EditorTab tab = tabs.get(currentTabIndex);
            boolean isSmali = tab.type == 0;

            menu.findItem(R.id.undo).setVisible(isSmali);
            menu.findItem(R.id.redo).setVisible(isSmali);

            MenuItem saveItem = menu.findItem(R.id.save);
            saveItem.setVisible(isSmali);
            saveItem.setEnabled(tab.isModified);
            if (saveItem.getIcon() != null) saveItem.getIcon().setAlpha(tab.isModified ? 255 : 100);

            menu.findItem(R.id.navigation).setVisible(isSmali);
            menu.findItem(R.id.edit_menu).setVisible(isSmali);

            // Sub-items in "More"
            menu.findItem(R.id.smali2java).setVisible(isSmali);
            menu.findItem(R.id.smali_instruction).setVisible(isSmali);
            MenuItem readOnlyItem = menu.findItem(R.id.read_only);
            readOnlyItem.setVisible(isSmali);
            readOnlyItem.setChecked(tab.isReadOnly);// preserve the read-only feature for targeted tab only

            MenuItem wrapItem = menu.findItem(R.id.wrap_text);
            if (wrapItem != null) {
                wrapItem.setChecked(getSharedPreferences("editor_prefs", MODE_PRIVATE).getBoolean("wrap_text", false));
            }
            handleUndoRedo();
        }
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();

        if (id == R.id.action_compile) {
            new SaveAndExitClickListener().onClick(null, 0);
            return true;
        } else if (id == R.id.action_preferences) {
            showCompilationOptionsDialog();
            return true;
        } else if (id == R.id.action_exit) {
            onBackPressed();
            return true;
        }

        if (viewPager.getVisibility() == View.VISIBLE && currentTabIndex != -1 && currentTabIndex < tabs.size()) {
            EditorFragment editorFragment = getCurrentFragment();
            if (editorFragment == null) return super.onOptionsItemSelected(item);

            CodeEditor editor = editorFragment.getEditor();
            EditorTab tab = tabs.get(currentTabIndex);

            if (id == R.id.undo) {
                editor.undo();
                handleUndoRedo();
                return true;
            } else if (id == R.id.redo) {
                editor.redo();
                handleUndoRedo();
                return true;
            } else if (id == R.id.save) {
                saveCurrentTab();
                return true;
            } else if (id == R.id.search) {
                // testing , i willl add a search bar like my modder hub app have.
                // but i have plan to replace the sora editor with my custom MH-Texteditor
                try {
                    java.lang.reflect.Method method = editor.getSearcher().getClass().getMethod("showSearchPanel");
                    method.invoke(editor.getSearcher());
                } catch (Exception ignored) {
                    // ignored
                }
                return true;
            } else if (id == R.id.navigation) {
                editorFragment.showMethodFieldList();
                return true;
            } else if (id == R.id.copy_line) {
                EditorHelper.copyLine(editor);
                return true;
            } else if (id == R.id.cut_line) {
                EditorHelper.cutLine(editor);
                return true;
            } else if (id == R.id.delete_line) {
                EditorHelper.deleteLine(editor);
                return true;
            } else if (id == R.id.empty_line) {
                EditorHelper.emptyLine(editor);
                return true;
            } else if (id == R.id.duplicate_line) {
                EditorHelper.duplicateLine(editor);
                return true;
            } else if (id == R.id.convert_uppercase) {
                EditorHelper.convertSelectedTextCase(editor, true);
                return true;
            } else if (id == R.id.convert_lowercase) {
                EditorHelper.convertSelectedTextCase(editor, false);
                return true;
            } else if (id == R.id.increase_indent) {
                EditorHelper.indent(editor, true);
                return true;
            } else if (id == R.id.decrease_indent) {
                EditorHelper.indent(editor, false);
                return true;
            } else if (id == R.id.toggle_comment) {
                toggleComment(editorFragment);
                return true;
            } else if (id == R.id.jumpToLine) {
                showJumpToLineDialog(editorFragment);
                return true;
            } else if (id == R.id.wrap_text) {
                item.setChecked(!item.isChecked());
                editor.setWordwrap(item.isChecked());
                getSharedPreferences("editor_prefs", MODE_PRIVATE).edit().putBoolean("wrap_text", item.isChecked()).apply();
                return true;
            } else if (id == R.id.read_only) {
                tab.isReadOnly = !tab.isReadOnly;
                item.setChecked(tab.isReadOnly);
                editor.setEditable(!tab.isReadOnly);
                return true;
            } else if (id == R.id.smali_instruction) {
                String instruction = getCurrentLineSmaliInstruction(editorFragment);
                if (instruction != null){
                    new SmaliInstructionsDialog(this, "smali_instructions.txt", instruction).show();
                } else {
                    new SmaliInstructionsDialog(this, "smali_instructions.txt").show();
                }
                return true;
            } else if (id == R.id.smali2java) {
                smali2java(editorFragment);
                return true;
            } else if (id == R.id.close) {
                closeTabWithPrompt(currentTabIndex);
                return true;
            } else if (id == R.id.preference){
                startActivity(new Intent(DexEditorActivity.this, SettingsActivity.class));
            }
        }
        return super.onOptionsItemSelected(item);
    }

    // get the smali instruction from cursor position in editor
    public String getCurrentLineSmaliInstruction(EditorFragment fragment){
        CodeEditor editor = fragment.getEditor();
        Cursor cursor = editor.getCursor();
        Content content = editor.getText();

        int line = cursor.getLeftLine();
        String lineText = content.getLineString(line);
        String trimmed = lineText.trim();

        if(trimmed.isEmpty()){
            return null;
        }

        int endOfFirstWord = 0;
        while (endOfFirstWord < trimmed.length()){
            char c = trimmed.charAt(endOfFirstWord);
            if (Character.isWhitespace(c)) break;
            if (c == '{' || c == '}' || c ==  ';') break;
            endOfFirstWord++;
        }

        String firstWord = trimmed.substring(0, endOfFirstWord);
        return SmaliInstructionHelper.isSmaliInstruction(firstWord) ? firstWord : null;
    }

    // when closing the editor fragment tab if the class is edited then there will be a prompt for
    private void closeTabWithPrompt(int index) {
        if (index < 0 || index >= tabs.size()) return;
        EditorTab tab = tabs.get(index);
        String className = tab.className;

        EditorFragment fragment = getFragmentAtIndex(index);
        if (fragment != null) {
            fragment.setClosing(true);
        }

        if (tab.isModified) {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Warning")
                    .setMessage("Class '" + tab.title + "' has been modified. Save the code ?")
                    .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface d, int w) {
                            saveTab(index, new Runnable() {
                                @Override
                                public void run() {
                                    clearPositionSaving(className);
                                    removeTab(index);
                                }
                            });
                        }
                    })
                    .setNeutralButton("Don't Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface d, int w) {
                            clearPositionSaving(className);
                            removeTab(index);
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        } else {
            clearPositionSaving(className);
            removeTab(index);
        }
    }

    private void clearPositionSaving(String className) {
        EditorPositionManager.getInstance(this).removePosition(className);
    }

    // smali2java
    private void smali2java(EditorFragment fragment) {
        String code = fragment.getCode();
        String className = fragment.getClassName();

        // Check if java tab already exists for this class
        for (int i = 0; i < tabs.size(); i++) {
            if (tabs.get(i).className.equals(className) && tabs.get(i).type == 1) {
                showEditor(i);
                return;
            }
        }

        AlertCircularProgress pd = new AlertCircularProgress(this);
        pd.setMessage("Decompiling...");
        pd.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final String java = modder.hub.dexeditor.smali.Smali2Java.translate(code, dexVersion);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pd.dismiss();
                            addTab(className, SmaliHelper.extractSimpleName(className) + ".java", java, 1); // adding the java item in the recent opened classes list
                        }
                    });
                } catch (final Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pd.dismiss();
                            Notify_MT.Notify(DexEditorActivity.this, getString(R.string.error), e.toString(), getString(R.string.close));
                        }
                    });
                }
            }
        }).start();
    }

    // smali toggle comment
    private void toggleComment(EditorFragment fragment) {
        try {
            TextActionWindow window = (TextActionWindow) fragment.getEditor().getComponent(EditorTextActionWindow.class);
            window.toggleComment();
        } catch (Exception ignored) {
        }
    }
    // jump to line
    private void showJumpToLineDialog(EditorFragment fragment) {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_jump_to_line, null);
        TextInputLayout textInputLayout = view.findViewById(R.id.textInputLayout);
        EditText editText = view.findViewById(R.id.editText);
        CodeEditor smaliEditor = fragment.getEditor();

        // set dynamic hint
        String hint = "Line number [1-" + smaliEditor.getLineCount() + "]";
        textInputLayout.setHint(hint);

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this)
                .setTitle("Jump to line")
                .setView(view)
                .setPositiveButton("OK", null)
                .setNegativeButton("Cancel", null);

        AlertDialog dialog_mt = builder.create();
        dialog_mt.show();
        dialog_mt.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if (editText.getText().toString().isEmpty()) {
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



    public void handleUndoRedo() {
        if (optionsMenu == null || viewPager.getVisibility() != View.VISIBLE) return;
        EditorFragment fragment = getCurrentFragment();
        if (fragment != null && fragment.getEditor() != null) {
            MenuItem undo = optionsMenu.findItem(R.id.undo);
            MenuItem redo = optionsMenu.findItem(R.id.redo);
            MenuItem save = optionsMenu.findItem(R.id.save);

            boolean isModified = false;
            for (EditorTab tab : tabs) {
                if (tab.className.equals(fragment.getClassName())) {
                    isModified = tab.isModified;
                    break;
                }
            }

            if (save != null) {
                save.setEnabled(isModified);
                if (save.getIcon() != null) {
                    save.getIcon().setAlpha(isModified ? 255 : 100);
                }
            }

            if (undo != null) {
                undo.setEnabled(fragment.getEditor().canUndo());
                if (undo.getIcon() != null) {
                    androidx.core.graphics.drawable.DrawableCompat.setTint(undo.getIcon(), Color.WHITE);
                    undo.getIcon().setAlpha(undo.isEnabled() ? 255 : 100);
                }
            }
            if (redo != null) {
                redo.setEnabled(fragment.getEditor().canRedo());
                if (redo.getIcon() != null) {
                    androidx.core.graphics.drawable.DrawableCompat.setTint(redo.getIcon(), Color.WHITE);
                    redo.getIcon().setAlpha(redo.isEnabled() ? 255 : 100);
                }
            }
        }
    }

    public EditorFragment getCurrentFragment() {
        return getFragmentAtIndex(viewPager.getCurrentItem());
    }

    public EditorFragment getFragmentAtIndex(int index) {
        if (index < 0 || index >= tabs.size()) return null;
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("f" + tabs.get(index).id);
        if (fragment instanceof EditorFragment) {
            return (EditorFragment) fragment;
        }
        return null;
    }

    private void showTreeView() {
        if (explorerViewPager != null) {
            explorerViewPager.setCurrentItem(0);
        }
    }

    // this is for updating the modifed section in the 'History' tab
    public void refreshExplorerPage(int position) {
        if (position == 1 && classTree != null) {
            modifiedNodes = classTree.buildEditedFullTree(); // a better tree structure like mt manager for the modifed classes
        }
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("f" + (2000 + position));
        if (fragment instanceof ExplorerPageFragment) {
            ((ExplorerPageFragment) fragment).updateUI();
        }
    }

    public List<TreeNode> getSearchNodes() {
        return searchNodes;
    }

    // locate the class in the main treeview in the EXPLORER Tab
    public void locateClass(final String className) {
        if (viewPager == null || explorerViewPager == null) return;

        // Ensure we are not in editor mode
        if (viewPager.getVisibility() == View.VISIBLE) {
            hideEditor();
        }

        // Close navigation drawer if it's open
        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        }

        // Switch to the first tab (EXPLORER)
        explorerViewPager.setCurrentItem(0, true);

        // Robust locate with retry mechanism to ensure fragment is ready
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            private int retryCount = 0;
            @Override
            public void run() {
                Fragment f = getSupportFragmentManager().findFragmentByTag("f2000");
                if (f instanceof ExplorerPageFragment && f.isAdded() && f.getView() != null) {
                    ((ExplorerPageFragment) f).locateNode(className);
                } else if (retryCount < 10) { // Retry for up to 1 second
                    retryCount++;
                    handler.postDelayed(this, 100);
                }
            }
        });
    }

    // open the class from the treenodes
    public void openClass(String className) {
        // Add/Update history
        TreeNode existingNode = null;
        for (TreeNode node : historyNodes) {
            if (node.getFullName().equals(className)) {
                existingNode = node;
                break;
            }
        }
        if (existingNode != null) {
            historyNodes.remove(existingNode);
            historyNodes.add(0, existingNode);
        } else {
            historyNodes.add(0, new TreeNode(SmaliHelper.extractSimpleName(className), className, 0, false));
        }

        refreshExplorerPage(1);

        for (int i = 0; i < tabs.size(); i++) {
            if (tabs.get(i).className.equals(className) && tabs.get(i).type == 0) {
                showEditor(i);
                return;
            }
        }

        try {
            dexVersion = classTree.getOpenedDexVersion();
        } catch (Exception e) {
            dexVersion = dexPref.getInt("dexVer", 35);
        }

        // Add tab with null content, the fragment will handle the loading
        addTab(className, SmaliHelper.extractSimpleName(className), null, 0);
    }

    // method reposnsible for opening the editor tab according to the search reasult , line number and class name
    public void openClassAtLine(String className, int lineNumber, String query) {
        openClassAtLine(className, lineNumber, -1, query);
    }

    public void openClassAtLine(String className, int lineNumber, int column, String query) {
        openClass(className);

        // Find the tab we just opened or that was already open
        EditorTab targetTab = null;
        int tabIndex = -1;
        for (int i = 0; i < tabs.size(); i++) {
            if (tabs.get(i).className.equals(className) && tabs.get(i).type == 0) {
                targetTab = tabs.get(i);
                tabIndex = i;
                break;
            }
        }

        if (targetTab != null) {
            // Set pending navigation info
            targetTab.pendingLine = lineNumber;
            targetTab.pendingColumn = column;
            targetTab.pendingQuery = query;

            // If fragment is already ready, navigate now
            EditorFragment fragment = getFragmentAtIndex(tabIndex);
            if (fragment != null && fragment.getEditor() != null && fragment.getEditor().getText().getLineCount() > lineNumber) {
                fragment.navigateTo(lineNumber, column, query);
                // Clear pending once navigated
                targetTab.pendingLine = -1;
                targetTab.pendingColumn = -1;
                targetTab.pendingQuery = null;
            }
        }
    }

    public void copiedToClipboard(String text) {
        UIHelper.copyToClipboard(this, text);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void addTab(String className, String title, String code, int type) {
        tabs.add(new EditorTab(className, title, code, type));
        tabAdapter.notifyItemInserted(tabs.size() - 1);
        tabsAdapter.notifyDataSetChanged();
        showEditor(tabs.size() - 1);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void showEditor(int index) {
        if (index < 0 || index >= tabs.size()) return;

        boolean wasHidden = viewPager.getVisibility() != View.VISIBLE;
        int oldIndex = viewPager.getCurrentItem();

        if (wasHidden) {
            // Instantly sync state so fragments can be prepared correctly before showing
            viewPager.setCurrentItem(index, false);

            classListContainer.setVisibility(View.GONE);
            viewPager.setVisibility(View.VISIBLE);

            currentTabIndex = index;
            tabsAdapter.notifyDataSetChanged();
            updateToolbar();
        } else if (oldIndex != index) {
            tabNavigationHistory.push(oldIndex);
            // Animate for reasonably small jumps, pop for very distant ones
            boolean animate = Math.abs(oldIndex - index) <= 5;
            viewPager.setCurrentItem(index, animate);
        }

        // Delay drawer closing slightly to let the ViewPager transition take priority
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                drawerLayout.closeDrawers();
            }
        }, 100);

        fabDelete.hide();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void hideEditor() {
        int oldIndex = currentTabIndex;
        viewPager.setVisibility(View.GONE);
        classListContainer.setVisibility(View.VISIBLE);
        updateToolbar();
        
        // Targeted updates for better performance
        tabsAdapter.notifyItemChanged(0); // Update Home item selection
        if (oldIndex != -1) {
            tabsAdapter.notifyItemChanged(oldIndex + 1);
        }
    }

    public EditorTab getTabForClassName(String className) {
        for (EditorTab tab : tabs) {
            if (tab.className.equals(className)) return tab;
        }
        return null;
    }

    public void onContentModified(String className) {
        for (int i = 0; i < tabs.size(); i++) {
            if (tabs.get(i).className.equals(className)) {
                EditorFragment fragment = getFragmentAtIndex(i);
                boolean actuallyModified = true;
                if (fragment != null && fragment.getEditor() != null) {
                    String currentText = fragment.getEditor().getText().toString();
                    String originalText = tabs.get(i).content;
                    actuallyModified = originalText == null || !currentText.equals(originalText);
                }

                if (tabs.get(i).isModified != actuallyModified) {
                    tabs.get(i).isModified = actuallyModified;
                    tabsAdapter.notifyItemChanged(i + 1); // Fixed index: tabs start at position 1
                    invalidateOptionsMenu();
                }
                break;
            }
        }
    }

    private void saveCurrentTab() {
        final int index = viewPager.getCurrentItem();
        saveTab(index, new Runnable() {
            @Override
            public void run() {
                SketchwareUtil.showMessage(DexEditorActivity.this, "Saved " + tabs.get(index).title);
            }
        });
    }

    private void showCompilationOptionsDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_compilation_options, null);
        Spinner spinnerDexVersion = dialogView.findViewById(R.id.spinner_dex_version);
        SwitchCompat swRemoveAllDebug = dialogView.findViewById(R.id.sw_remove_all_debug);
        SwitchCompat swRemoveDebugSource = dialogView.findViewById(R.id.sw_remove_debug_source);
        SwitchCompat swRemoveDebugLine = dialogView.findViewById(R.id.sw_remove_debug_line);
        SwitchCompat swRemoveDebugParam = dialogView.findViewById(R.id.sw_remove_debug_param);
        SwitchCompat swRemoveDebugPrologue = dialogView.findViewById(R.id.sw_remove_debug_prologue);
        SwitchCompat swRemoveDebugLocal = dialogView.findViewById(R.id.sw_remove_debug_local);

        String[] versions = {"Keep the same", "35", "37", "38", "39"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, versions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDexVersion.setAdapter(adapter);

        // Load session values
        for (int i = 0; i < versions.length; i++) {
            if (versions[i].equals(sessionOptions.dexVersion)) {
                spinnerDexVersion.setSelection(i);
                break;
            }
        }

        swRemoveAllDebug.setChecked(sessionOptions.removeAllDebug);
        swRemoveDebugSource.setChecked(sessionOptions.removeDebugSource);
        swRemoveDebugLine.setChecked(sessionOptions.removeDebugLine);
        swRemoveDebugParam.setChecked(sessionOptions.removeDebugParam);
        swRemoveDebugPrologue.setChecked(sessionOptions.removeDebugPrologue);
        swRemoveDebugLocal.setChecked(sessionOptions.removeDebugLocal);

        swRemoveAllDebug.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                swRemoveDebugSource.setEnabled(!isChecked);
                swRemoveDebugLine.setEnabled(!isChecked);
                swRemoveDebugParam.setEnabled(!isChecked);
                swRemoveDebugPrologue.setEnabled(!isChecked);
                swRemoveDebugLocal.setEnabled(!isChecked);
                if (isChecked) {
                    swRemoveDebugSource.setChecked(true);
                    swRemoveDebugLine.setChecked(true);
                    swRemoveDebugParam.setChecked(true);
                    swRemoveDebugPrologue.setChecked(true);
                    swRemoveDebugLocal.setChecked(true);
                }
            }
        });

        if (sessionOptions.removeAllDebug) {
            swRemoveDebugSource.setEnabled(false);
            swRemoveDebugLine.setEnabled(false);
            swRemoveDebugParam.setEnabled(false);
            swRemoveDebugPrologue.setEnabled(false);
            swRemoveDebugLocal.setEnabled(false);
        }

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setView(dialogView);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sessionOptions.dexVersion = spinnerDexVersion.getSelectedItem().toString();
                sessionOptions.removeAllDebug = swRemoveAllDebug.isChecked();
                sessionOptions.removeDebugSource = swRemoveDebugSource.isChecked();
                sessionOptions.removeDebugLine = swRemoveDebugLine.isChecked();
                sessionOptions.removeDebugParam = swRemoveDebugParam.isChecked();
                sessionOptions.removeDebugPrologue = swRemoveDebugPrologue.isChecked();
                sessionOptions.removeDebugLocal = swRemoveDebugLocal.isChecked();
            }
        });
        builder.setNegativeButton("CANCEL", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showSmaliNavigation(String tempSmaliPath, String title, int lineNo) {
        lastSmaliFilePath = tempSmaliPath;
        if (smaliMethodsFieldsStringsFragment == null) {
            smaliMethodsFieldsStringsFragment = new SmaliMethodFieldListFragment();
        }
        if (!smaliMethodsFieldsStringsFragment.isAdded()) {
            smaliMethodsFieldsStringsFragment.show(getSupportFragmentManager(), "navigation");
        }
        smaliMethodsFieldsStringsFragment.updateUi(tempSmaliPath, title, lineNo, dexVersion);
    }

    public void goTo(String text, String currentClassName) {
        if (!text.contains(";->")) {
            String targetClass = SmaliHelper.smali2OnlySlash(text);
            if (targetClass.equals(currentClassName)) {
                SketchwareUtil.showMessage(this, " You are already in this class");
            } else if (classTree.classMap.get(targetClass) != null) {
                openClass(targetClass);
            } else {
                showClassNotfound(targetClass);
            }
        } else {
            String[] split = text.split("->");
            String className = SmaliHelper.smali2OnlySlash(split[0]);
            String methodName = split[1];
            if (className.equals(currentClassName)) {
                EditorFragment fragment = getCurrentFragment();
                if (fragment != null) {
                    fragment.extractMethodFieldInfo(methodName);
                }
            } else if (classTree.classMap.get(className) != null) {
                openClassWithMethod(className, methodName);
            } else {
                showClassNotfound(className);
            }
        }
    }

    public void showClassNotfound(String targetClass){
        Notify_MT.Notify(this, getString(R.string.error), "Class not found: " + targetClass, "Close");
    }

    private void openClassWithMethod(String className, String methodName) {
        openClass(className);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                EditorFragment fragment = getCurrentFragment();
                if (fragment != null) {
                    fragment.extractMethodFieldInfo(methodName);
                }
            }
        }, 500);
    }

    private String smali2OnlySlash(String smaliName) {
        return SmaliHelper.smali2OnlySlash(smaliName);
    }


    private boolean isCompilationOptionsActive() {
        return sessionOptions.removeAllDebug || sessionOptions.removeDebugSource ||
                sessionOptions.removeDebugLine || sessionOptions.removeDebugParam ||
                sessionOptions.removeDebugPrologue || sessionOptions.removeDebugLocal ||
                !sessionOptions.dexVersion.equals("Keep the same");
    }


    private void showExitConfirmation() {
        List<EditorTab> modifiedTabs = new ArrayList<>();
        for (EditorTab tab : tabs) {
            if (tab.isModified) modifiedTabs.add(tab);
        }

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Unsaved changes");

        if (!modifiedTabs.isEmpty()) {
            final String[] titles = new String[modifiedTabs.size()];
            final boolean[] checked = new boolean[modifiedTabs.size()];
            for (int i = 0; i < modifiedTabs.size(); i++) {
                titles[i] = modifiedTabs.get(i).title;
                checked[i] = true;
            }
            builder.setMultiChoiceItems(titles, checked, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    checked[which] = isChecked;
                }
            });
            builder.setPositiveButton("Save and Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    saveMultipleTabs(modifiedTabs, checked, new Runnable() {
                        @Override
                        public void run() {
                            new SaveAndExitClickListener().onClick(null, 0);
                        }
                    });
                }
            });
        } else {
            builder.setMessage("Do you want to compile and save the dex files?");
            builder.setPositiveButton("Save and Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    new SaveAndExitClickListener().onClick(null, 0);
                }
            });
        }

        builder.setNegativeButton("Cancel", null);
        builder.setNeutralButton("Exit Directly", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                exitActivity();
            }
        });
       builder.show();
    }

    // method for saviing multiple tabs
    // it will save tabs one by one
    // look the next method fo details
    public void saveMultipleTabs(final List<EditorTab> modifiedTabs, final boolean[] checked, final Runnable onProceed) {
        int totalToSave = 0;
        for (boolean b : checked) if (b) totalToSave++;
        if (totalToSave == 0) {
            onProceed.run();
            return;
        }

        final int[] count = {0};
        final int finalTotalToSave = totalToSave;
        for (int i = 0; i < modifiedTabs.size(); i++) {
            if (checked[i]) {
                final int indexInTabs = tabs.indexOf(modifiedTabs.get(i)); // get the modifed tab from map
                saveTab(indexInTabs, new Runnable() { // save one after one
                    @Override
                    public void run() {
                        synchronized (count) {
                            count[0]++;
                            if (count[0] == finalTotalToSave) {
                                runOnUiThread(onProceed);
                            }
                        }
                    }
                });
            }
        }
    }

    // main method for saving tabs
    // @index for the position of the tab , @onSaved used for proper task to detect if the savig completed or not
    private void saveTab(final int index, final Runnable onSaved) {
        final EditorTab tab = tabs.get(index);
        final EditorFragment fragment = getFragmentAtIndex(index);
        if (fragment == null) {
            if (onSaved != null) onSaved.run();
            return;
        }

        final AlertCircularProgress pd = new AlertCircularProgress(this);
        pd.setMessage("Saving " + tab.title + "...");
        pd.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final String code = fragment.getEditor().getText().toString();
                    classTree.saveClassDef(Smali.assemble(code, new SmaliOptions(), dexVersion));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pd.dismiss();
                            tab.isModified = false; // make false because we have saved it, but user again try to edit the calss thn this will again true
                            tab.content = code;
                            tabsAdapter.notifyItemChanged(index);
                            isChanged = true;
                            refreshExplorerPage(1);
                            handleUndoRedo();
                            if (onSaved != null) onSaved.run();
                        }
                    });
                } catch (final Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pd.dismiss();
                            Notify_MT.Notify(DexEditorActivity.this, "Error saving " + tab.title, e.getMessage(), "Close");
                            if (onSaved != null) onSaved.run();
                        }
                    });
                }
            }
        }).start();
    }

    private void exitActivity() {
        tabs.clear();
        tabNavigationHistory.clear();
        currentTabIndex = -1;
        if (classTree != null) {
            classTree.clearAll();
            classTree = null;
        }

        // Clear navigation statics to prevent state leakage
        smaliMethodsFieldsStringsFragment = null;
        lastSmaliFilePath = "";
        methodRecyclerViewState = null;
        stringsRecyclerViewState = null;

        treeRoots.clear();
        historyNodes.clear();
        modifiedNodes.clear();
        searchNodes.clear();
        stringList.clear();
        isSaved = false;
        isChanged = false;
        finish();
    }

    private boolean isSelectionModeActive() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentByTag("f" + (2000 + explorerViewPager.getCurrentItem()));
        if (currentFragment instanceof ExplorerPageFragment) {
            RecyclerView rv = ((ExplorerPageFragment) currentFragment).rv;
            if (rv != null && rv.getAdapter() instanceof TreeAdapter) {
                return ((TreeAdapter) rv.getAdapter()).isSelectionMode();
            }
        }
        return false;
    }

    // cancel the batch selection
    private void cancelSelectionMode() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentByTag("f" + (2000 + explorerViewPager.getCurrentItem()));
        if (currentFragment instanceof ExplorerPageFragment) {
            RecyclerView rv = ((ExplorerPageFragment) currentFragment).rv;
            if (rv != null && rv.getAdapter() instanceof TreeAdapter) {
                TreeAdapter adapter = (TreeAdapter) rv.getAdapter();
                adapter.setSelectionMode(false);
                showMultipleFabs(false);
                fabDelete.hide();
            }
        }
    }

    private void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            File[] children = fileOrDirectory.listFiles();
            if (children != null) {
                for (File child : children) {
                    deleteRecursive(child);
                }
            }
        }
        fileOrDirectory.delete();
    }

    // show the progress dialog
    private void showProcessingProgress(boolean show) {
        if (show) {
            if (coreProgressDialog == null) {
                coreProgressDialog = new AlertCircularProgress(this);
            }
            coreProgressDialog.setTitle(null);
            coreProgressDialog.setMessage("Loading...");
            coreProgressDialog.show();
        } else if (coreProgressDialog != null) {
            coreProgressDialog.dismiss();
        }
    }

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

    private void initializeFab() {
        @SuppressLint("InflateParams") LinearLayout fabLayout = getLayoutInflater().inflate(R.layout.multiple_fabs, null).findViewById(R.id.linear_bg);
        fabBackground = fabLayout;
        FloatingActionButton fabSelectRest = fabLayout.findViewById(R.id.fab_select_rest);
        FloatingActionButton fabClear = fabLayout.findViewById(R.id.fab_clear);
        ((ViewGroup) fabLayout.getParent()).removeView(fabLayout);
        ((ViewGroup) fabDelete.getParent()).addView(fabLayout);
        fabClear.setBackgroundTintList(ColorStateList.valueOf(0xFFBEBEC3));
        fabSelectRest.setBackgroundTintList(ColorStateList.valueOf(0xFFBEBEC3));
        fabSelectRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment currentFragment = getSupportFragmentManager().findFragmentByTag("f" + (2000 + explorerViewPager.getCurrentItem()));
                if (currentFragment instanceof ExplorerPageFragment) {
                    RecyclerView rv = ((ExplorerPageFragment) currentFragment).rv;
                    if (rv != null && rv.getAdapter() instanceof TreeAdapter) {
                        ((TreeAdapter) rv.getAdapter()).selectAllRest();
                    }
                }
            }
        });
        fabClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment currentFragment = getSupportFragmentManager().findFragmentByTag("f" + (2000 + explorerViewPager.getCurrentItem()));
                if (currentFragment instanceof ExplorerPageFragment) {
                    RecyclerView rv = ((ExplorerPageFragment) currentFragment).rv;
                    if (rv != null && rv.getAdapter() instanceof TreeAdapter) {
                        ((TreeAdapter) rv.getAdapter()).clearAllSelection();
                        showMultipleFabs(false);
                        fabDelete.hide();
                        ((TreeAdapter) rv.getAdapter()).setSelectionMode(false);
                    }
                }
            }
        });
        showMultipleFabs(false);
    }

    private String customException(Exception exception) {
        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    @Deprecated
    public float getDip(int dip) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
    }

    private void showErrorDialog(String errorMessage) {
        Notify_MT.Notify(this, getResources().getString(R.string.error), errorMessage, getResources().getString(R.string.close));
    }

    private void closeTab(int index) {
        closeTabWithPrompt(index);
    }


    @SuppressLint("NotifyDataSetChanged")
    private void removeTab(int index) {
        tabs.remove(index);
        tabAdapter.notifyItemRemoved(index);
        tabsAdapter.notifyDataSetChanged();
        if (tabs.isEmpty()) {
            hideEditor();
        } else {
            int nextIndex = Math.max(0, index - 1);
            viewPager.setCurrentItem(nextIndex, true);
        }
    }

    public static class EditorTab {
        private static long idCounter = 1;
        public long id;
        public String className;
        public String title;
        public String content;
        public int type; // 0: Smali, 1: Java
        public boolean isModified;
        public boolean isReadOnly;

        // Pending navigation
        public int pendingLine = -1;
        public int pendingColumn = -1;
        public String pendingQuery = null;

        EditorTab(String className, String title, String content, int type) {
            this.id = idCounter++;
            this.className = className;
            this.title = title;
            this.content = content;
            this.type = type;
            this.isModified = false;
            this.isReadOnly = false;
        }
    }

    public static class ExplorerPageFragment extends Fragment {
        RecyclerView rv;
        private int position;

        public static ExplorerPageFragment newInstance(int position) {
            ExplorerPageFragment fragment = new ExplorerPageFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) position = getArguments().getInt("position");
        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            rv = new RecyclerView(requireContext());
            rv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            rv.setBackgroundColor(Color.WHITE);

            if (position == 0) {
                rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        updateSubtitle();
                    }
                });
            }

            updateUI();
            return rv;
        }

        // update the subtitle according the folder opened in the treeview
        private void updateSubtitle() {
            if (rv == null || !(rv.getLayoutManager() instanceof LinearLayoutManager) || !(rv.getAdapter() instanceof TreeAdapter))
                return;
            DexEditorActivity activity = (DexEditorActivity) getActivity();
            if (activity == null) return;

            LinearLayoutManager lm = (LinearLayoutManager) rv.getLayoutManager();
            int firstPos = lm.findFirstVisibleItemPosition();
            if (firstPos == RecyclerView.NO_POSITION) return;

            TreeAdapter adapter = (TreeAdapter) rv.getAdapter();
            List<TreeNode> nodes = adapter.getVisibleNodes();
            if (firstPos >= nodes.size()) return;

            TreeNode node = nodes.get(firstPos);
            View v = lm.findViewByPosition(firstPos);

            if (v != null) {
                // MT Manager style: Show the path of the current expanded folder level
                TreeNode parent = node.getParent();
                if (parent != null && parent.isExpanded()) {
                    activity.setToolbarSubtitle(parent.getFullName());
                } else if (node.isDirectory() && node.isExpanded() && v.getTop() < 0) {
                    activity.setToolbarSubtitle(node.getFullName().replace("/", "."));
                } else {
                    activity.setToolbarSubtitle(null);
                }
            }
        }

        // class node loacter in the main treeview
        public void locateNode(String className) {
            if (rv == null || rv.getAdapter() == null) return;
            TreeAdapter adapter = (TreeAdapter) rv.getAdapter();
            TreeNode target = findNodeRecursive(adapter.getRootNodes(), className);
            if (target != null) {
                for (TreeNode node = target.getParent(); node != null; node = node.getParent()) {
                    node.setExpanded(true);
                }
                adapter.refreshVisibleNodes();
                int pos = adapter.getPosition(target);
                if (pos != -1) {
                    rv.scrollToPosition(pos);
                    adapter.setHighlightedFullName(className);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            adapter.setHighlightedFullName(null);
                        }
                    }, 2000);
                }
            }
        }

        private TreeNode findNodeRecursive(List<TreeNode> nodes, String fullName) {
            for (TreeNode node : nodes) {
                if (node.getFullName().equals(fullName)) return node;
                TreeNode found = findNodeRecursive(node.getChildren(), fullName);
                if (found != null) return found;
            }
            return null;
        }

        public void updateUI() {
            if (rv == null) return;
            DexEditorActivity activity = (DexEditorActivity) getActivity();
            if (activity == null) return;

            switch (position) {
                case 0:
                    rv.setAdapter(new TreeAdapter(getContext(), activity.treeRoots, new TreeAdapter.OnNodeClickListener() {
                        @Override
                        public void onNodeClick(TreeNode node) {
                            activity.openClass(node.getFullName());
                        }

                        @Override
                        public void onNodeDeleted(TreeNode node) {
                            activity.clearPositionSaving(node.getFullName());
                            classTree.removeClass(node.getFullName());
                            DexEditorActivity.isChanged = true;
                            activity.refreshExplorerPage(1);
                        }

                        @Override
                        public void onSelectionChanged(int count) {
                            activity.showMultipleFabs(count > 0);
                            if (count > 0) activity.fabDelete.show();
                            else activity.fabDelete.hide();
                        }
                    }, false));
                    break;
                case 1:
                    ConcatAdapter concatAdapter = new ConcatAdapter();
                    if (!activity.historyNodes.isEmpty()) {
                        concatAdapter.addAdapter(new HeaderAdapter("Recently"));
                        concatAdapter.addAdapter(new TreeAdapter(getContext(), activity.historyNodes, new TreeAdapter.OnNodeClickListener() {
                            @Override
                            public void onNodeClick(TreeNode node) {
                                activity.openClass(node.getFullName());
                            }

                            @Override
                            public void onNodeDeleted(TreeNode node) {
                                activity.historyNodes.remove(node);
                                activity.refreshExplorerPage(1);
                            }

                            @Override
                            public void onSelectionChanged(int count) {
                            }

                            @Override
                            public void onLocate(TreeNode node) {
                                activity.locateClass(node.getFullName());
                            }
                        }, true));
                    }

                    if (!activity.modifiedNodes.isEmpty()) {
                        concatAdapter.addAdapter(new HeaderAdapter("Modified"));
                        concatAdapter.addAdapter(new TreeAdapter(getContext(), activity.modifiedNodes, new TreeAdapter.OnNodeClickListener() {
                            @Override
                            public void onNodeClick(TreeNode node) {
                                activity.openClass(node.getFullName());
                            }

                            @Override
                            public void onNodeDeleted(TreeNode node) { /* Modified nodes are from ClassTree */ }

                            @Override
                            public void onSelectionChanged(int count) {
                            }

                            @Override
                            public void onLocate(TreeNode node) {
                                activity.locateClass(node.getFullName());
                            }

                            @Override
                            public void onCompare(TreeNode node) {
                                // TODO: Implement compare the difference
                            }
                        }, false, true));
                    }
                    rv.setAdapter(concatAdapter);
                    break;
                case 2:
                    rv.setAdapter(new TreeAdapter(getContext(), activity.searchNodes, new TreeAdapter.OnNodeClickListener() {
                        @Override
                        public void onNodeClick(TreeNode node) {
                            activity.openClass(node.getFullName());
                        }

                        @Override
                        public void onNodeDeleted(TreeNode node) {
                            activity.searchNodes.remove(node);
                        }

                        @Override
                        public void onSelectionChanged(int count) {
                        }

                        @Override
                        public void onLocate(TreeNode node) {
                            activity.locateClass(node.getFullName());
                        }
                    }, true));
                    break;
                case 3:
                    rv.setAdapter(new modder.hub.dexeditor.adapter.StringAdapter(activity.stringList, new modder.hub.dexeditor.adapter.StringAdapter.OnStringClickListener() {
                        @Override
                        public void onStringClick(String text) {
                            // TO-DO
                        }
                    }));
                    break;
            }
            new FastScrollerBuilder(rv).build();
        }
    }

    private class ExplorerTabAdapter extends FragmentStateAdapter {
        public ExplorerTabAdapter(@NonNull AppCompatActivity activity) {
            super(activity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 2) return new SearchFragment();
            return ExplorerPageFragment.newInstance(position);
        }

        @Override
        public int getItemCount() {
            return 4;
        }

        @Override
        public long getItemId(int position) {
            return 2000 + position;
        }

        @Override
        public boolean containsItem(long itemId) {
            return itemId >= 2000 && itemId <= 2003;
        }
    }

    // Thread to load DEX file
    private class LoadDexThread extends Thread {
        private final List<String> paths;
        private final String cachePath;

        public LoadDexThread(List<String> paths, String cachePath) {
            this.paths = paths;
            this.cachePath = cachePath;
        }

        @Override
        public void run() {
            try {
                classTree = new ClassTree(paths, cachePath);
                // Pre-build trees in background to avoid UI lag
                final List<TreeNode> roots = classTree.buildFullTree();
                final List<TreeNode> modified = classTree.buildEditedFullTree();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            treeRoots = roots;
                            modifiedNodes = modified;
                            updateToolbar();
                            refreshExplorerPage(0);
                            showTreeView();
                        } catch (Exception e) {
                            handleUiThreadError(e);
                        }
                    }
                });
            } catch (final Exception e) {
                showProcessingProgress(false);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showErrorDialog(e);
                    }
                });
            } finally {
                showProcessingProgress(false);
            }
        }

        private void handleUiThreadError(Exception e) {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(DexEditorActivity.this);
            builder.setTitle(getString(R.string.error));
            builder.setMessage("UI update failed: " + e.getMessage());
            builder.setPositiveButton("OK", null);
            Notify_MT.Dlg_Style(builder);
        }

        private void showErrorDialog(final Exception e) {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(DexEditorActivity.this);
            builder.setTitle("DEX Loading Error");
            builder.setMessage("Failed to process DEX files:\n\n" + e.getMessage());
            builder.setPositiveButton("Go Back", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setCancelable(false);
            Notify_MT.Dlg_Style(builder);
        }
    }

    private class SaveAndExitClickListener implements DialogInterface.OnClickListener {
        private volatile boolean isStopped = false;

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            final AlertProgress alertProgress = new AlertProgress(DexEditorActivity.this);
            alertProgress.setTitle("Processing...");
            alertProgress.setMessage("Compiling...");
            alertProgress.setOnCancelListener(new modder.hub.dexeditor.views.AlertProgress.OnCancelListener() {
                @Override
                public void onCancel() {
                    isStopped = true;
                }
            });
            alertProgress.show();

            // Set compilation options
            classTree.setCompilationOptions(sessionOptions);

            new Thread() {
                @Override
                public void run() {
                    Looper.prepare();
                    try {
                        classTree.saveAllDexFiles(new ClassTree.DexSaveProgress() {
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
                                        alertProgress.setTitle(title);
                                    }
                                });
                            }

                            @Override
                            public void onMessage(final String message) {
                                if (isStopped) throw new RuntimeException("CANCELLED");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        alertProgress.setMessage(message);
                                    }
                                });
                            }
                        });
                        if (isStopped) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    SketchwareUtil.showMessage(getApplicationContext(), "Cancelled");
                                }
                            });
                            alertProgress.dismiss();
                            return;
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Clear editor positions JSON on success
                                EditorPositionManager.getInstance(DexEditorActivity.this).clear();
                                SketchwareUtil.showMessage(getApplicationContext(), "Success");
                                finish();
                            }
                        });
                    } catch (final Exception e) {
                        if ("CANCELLED".equals(e.getMessage())) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    SketchwareUtil.showMessage(getApplicationContext(), "Operation Cancelled");
                                }
                            });
                            alertProgress.dismiss();
                            return;
                        }
                        final String msg = e.getMessage();
                        if (msg != null && msg.startsWith("COMPILE_ERROR:")) {
                            String[] parts = msg.split(":", 3);
                            final String faultyClass = parts[1];
                            final String error = parts[2];

                            // Parse line and column info [line,column]
                            int line = -1;
                            int column = -1;
                            try {
                                java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\[(\\d+),(\\d+)\\]").matcher(error);
                                if (matcher.find()) {
                                    line = Integer.parseInt(matcher.group(1)) - 1; // 0-based
                                    column = Integer.parseInt(matcher.group(2)) - 1; // 0-based
                                }
                            } catch (Exception ignored) {
                            }

                            final int finalLine = line;
                            final int finalColumn = column;

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // detect compile error from spceific classes and navigate to the class for fix
                                    new AlertDialog.Builder(DexEditorActivity.this)
                                            .setTitle("Compile Error")
                                            .setMessage("Class: " + faultyClass + "\n\n" + error)
                                            .setPositiveButton("Fix", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface d, int w) {
                                                    if (finalLine != -1) {
                                                        openClassAtLine(faultyClass, finalLine, finalColumn, null);
                                                    } else {
                                                        openClass(faultyClass);
                                                    }
                                                }
                                            })
                                            .setNegativeButton("Close", null)
                                            .show();
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showErrorDialog("An error occurred while processing dex\n\n---StackTrace---\n\n" + e);
                                }
                            });
                        }
                    }
                    alertProgress.dismiss();
                    Looper.loop();
                }
            }.start();
        }
    }

    // batch class deletion function
    private class DeleteButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            progressDialog = new AlertProgress(DexEditorActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("Deleting classes...");
            progressDialog.show();
            new Thread() {
                @Override
                public void run() {
                    try {
                        Fragment currentFragment = getSupportFragmentManager().findFragmentByTag("f" + (2000 + explorerViewPager.getCurrentItem()));
                        if (currentFragment instanceof ExplorerPageFragment) {
                            RecyclerView rv = ((ExplorerPageFragment) currentFragment).rv;
                            if (rv != null && rv.getAdapter() instanceof TreeAdapter) {
                                TreeAdapter adapter = (TreeAdapter) rv.getAdapter();
                                List<TreeNode> selected = adapter.getSelectedNodes();
                                List<String> namesToDelete = new ArrayList<>();
                                EditorPositionManager posManager = EditorPositionManager.getInstance(DexEditorActivity.this);
                                for (TreeNode node : selected) {
                                    String fullName = node.getFullName();
                                    namesToDelete.add(fullName + (node.isDirectory() ? "/" : ""));
                                    posManager.removePosition(fullName);
                                }
                                classTree.removeClasses(namesToDelete);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        adapter.removeSelectedNodes();
                                        adapter.setSelectionMode(false);
                                        showMultipleFabs(false);
                                        fabDelete.hide();
                                        isChanged = true;
                                        refreshExplorerPage(1);
                                    }
                                });
                            }
                        }
                    } catch (Exception e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showErrorDialog(e.toString());
                            }
                        });
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                        }
                    });
                }
            }.start();
        }
    }

    private class TabAdapter extends FragmentStateAdapter {
        public TabAdapter(@NonNull AppCompatActivity activity) {
            super(activity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            EditorTab tab = tabs.get(position);
            return EditorFragment.newInstance(tab.className, tab.title, tab.content, tab.type);
        }

        @Override
        public int getItemCount() {
            return tabs.size();
        }

        @Override
        public long getItemId(int position) {
            return tabs.get(position).id;
        }

        @Override
        public boolean containsItem(long itemId) {
            for (EditorTab tab : tabs) {
                if (tab.id == itemId) return true;
            }
            return false;
        }
    }

    public class TabsAdapter extends RecyclerView.Adapter<TabsAdapter.ViewHolder> {
        private int swipedPosition = -1;

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_item, parent, false);
            return new ViewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            boolean isSelected;
            int skyColor = Color.parseColor("#00B0FF");
            float swipeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, getResources().getDisplayMetrics());

            // Reset sticky state
            holder.mainView.animate().cancel();
            holder.mainView.setTranslationX(position == swipedPosition ? swipeWidth : 0);

            if (position == 0) {
                isSelected = viewPager.getVisibility() != View.VISIBLE;
                holder.title.setText("Dex Editor Plus");
                holder.title.setTextColor(isSelected ? skyColor : Color.BLACK);
                holder.path.setVisibility(View.GONE);
                holder.icon.setImageResource(R.drawable.ic_home);
                holder.icon.setImageTintList(ColorStateList.valueOf(isSelected ? skyColor : Color.BLACK));
                holder.mainView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawerLayout.closeDrawers();
                        v.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                hideEditor();
                            }
                        }, 200);
                    }
                });
                holder.menuView.setVisibility(View.GONE);
                holder.mainView.setOnTouchListener(null);
            } else {
                int tabIndex = position - 1;
                EditorTab tab = tabs.get(tabIndex);
                isSelected = (viewPager.getVisibility() == View.VISIBLE && tabIndex == currentTabIndex);
                holder.title.setText(tab.title + (tab.isModified ? " *" : "")); // highlight the spcific edited classes with star
                holder.title.setTextColor(isSelected ? skyColor : Color.BLACK);
                holder.path.setVisibility(View.VISIBLE);
                holder.path.setText(tab.className);
                holder.icon.setImageResource(R.drawable.ic_edit_mt);
                holder.icon.setImageTintList(ColorStateList.valueOf(isSelected ? skyColor : Color.BLACK));

                holder.mainView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (v.getTranslationX() != 0) {
                            v.animate().translationX(0).setDuration(200).start();
                            swipedPosition = -1;
                            return;
                        }
                        drawerLayout.closeDrawers();
                        v.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                showEditor(tabIndex);
                            }
                        }, 200);
                    }
                });

                holder.mainView.setOnTouchListener(new View.OnTouchListener() {
                    private float startX;
                    private float initialX;
                    private boolean isDragging = false;

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                initialX = event.getRawX();
                                startX = v.getTranslationX();
                                isDragging = false;
                                break;
                            case MotionEvent.ACTION_MOVE:
                                float diff = event.getRawX() - initialX;
                                if (Math.abs(diff) > 10 || isDragging) {
                                    isDragging = true;
                                    v.setTranslationX(Math.max(0, Math.min(startX + diff, swipeWidth)));
                                    v.getParent().requestDisallowInterceptTouchEvent(true);
                                    return true;
                                }
                                break;
                            case MotionEvent.ACTION_UP:
                            case MotionEvent.ACTION_CANCEL:
                                if (isDragging) {
                                    float target = v.getTranslationX() > swipeWidth / 2 ? swipeWidth : 0;
                                    v.animate().translationX(target).setDuration(200).start();
                                    int oldSwiped = swipedPosition;
                                    swipedPosition = target > 0 ? holder.getBindingAdapterPosition() : -1;
                                    if (oldSwiped != -1 && oldSwiped != swipedPosition)
                                        notifyItemChanged(oldSwiped);
                                    return true;
                                }
                                break;
                        }
                        return false;
                    }
                });

                holder.menuView.setVisibility(View.VISIBLE);
                holder.menuClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        swipedPosition = -1;
                        holder.mainView.setTranslationX(0);
                        closeTab(tabIndex);
                    }
                });
                holder.menuLocate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        swipedPosition = -1;
                        holder.mainView.animate().translationX(0).setDuration(200).start();
                        locateClass(tab.className);
                        drawerLayout.closeDrawers();
                    }
                });
            }
            holder.mainView.setBackgroundColor(isSelected ? Color.parseColor("#E1F5FE") : Color.WHITE);
        }

        @Override
        public int getItemCount() {
            return tabs.size() + 1;
        }

        private void hideEditor() {
            DexEditorActivity.this.hideEditor();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            TextView path;
            ImageView icon;
            View mainView;
            View menuView;
            ImageView menuClose;
            ImageView menuLocate;

            public ViewHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.tab_title);
                path = itemView.findViewById(R.id.tab_path);
                icon = itemView.findViewById(R.id.tab_icon);
                mainView = itemView.findViewById(R.id.main_view);
                menuView = itemView.findViewById(R.id.menu_view);
                menuClose = itemView.findViewById(R.id.menu_close);
                menuLocate = itemView.findViewById(R.id.menu_locate);
            }
        }
    }
}
