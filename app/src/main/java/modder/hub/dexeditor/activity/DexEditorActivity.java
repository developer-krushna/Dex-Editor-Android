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

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.view.animation.DecelerateInterpolator;
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
import androidx.appcompat.widget.PopupMenu;
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

import com.android.tools.smali.dexlib2.iface.ClassDef;
import com.android.tools.smali.smali.SmaliOptions;
import com.android.tools.smali.smali2.Smali;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.github.rosemoe.sora.text.Content;
import io.github.rosemoe.sora.text.Cursor;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.component.EditorTextActionWindow;
import modder.hub.dexeditor.R;
import modder.hub.dexeditor.adapter.HeaderAdapter;
import modder.hub.dexeditor.adapter.StringAdapter;
import modder.hub.dexeditor.adapter.TreeAdapter;
import modder.hub.dexeditor.fragment.EditorFragment;
import modder.hub.dexeditor.fragment.SearchFragment;
import modder.hub.dexeditor.fragment.SmaliMethodFieldListFragment;
import modder.hub.dexeditor.model.TreeNode;
import modder.hub.dexeditor.smali.SmaliHelper;
import modder.hub.dexeditor.smali.SmaliInstructionHelper;
import modder.hub.dexeditor.utils.ClassTree;
import modder.hub.dexeditor.utils.EditorHelper;
import modder.hub.dexeditor.utils.EditorPositionManager;
import modder.hub.dexeditor.utils.Notify_MT;
import modder.hub.dexeditor.utils.SketchwareUtil;
import modder.hub.dexeditor.utils.UIHelper;
import modder.hub.dexeditor.views.AlertCircularProgress;
import modder.hub.dexeditor.views.AlertProgress;
import modder.hub.dexeditor.views.FastScrollerRecyclerView;
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
    // Legacy static fields for SmaliMethodFieldListFragment state
    public static SmaliMethodFieldListFragment smaliMethodsFieldsStringsFragment = null;
    public static android.os.Parcelable methodRecyclerViewState = null;
    public static android.os.Parcelable stringsRecyclerViewState = null;
    public static boolean wasStringsVisible = false;
    public static String lastSmaliFilePath = "";
    private static int currentTabIndex = -1;
    private final List<TreeNode> historyNodes = new ArrayList<>();
    private final List<String> stringList = new ArrayList<>();
    private final java.util.Stack<Integer> tabNavigationHistory = new java.util.Stack<>();
    private final ClassTree.CompilationOptions sessionOptions = new ClassTree.CompilationOptions();
    private final List<TreeNode> treeRoots = new ArrayList<>();
    private final List<TreeNode> modifiedNodes = new ArrayList<>();
    // --- Member Fields ---
    public int dexVersion;
    public List<TreeNode> searchNodes = new ArrayList<>();
    public String pendingSearchPath = null;
    public String pendingStringSearchQuery = null;
    public TabsAdapter tabsAdapter;
    private boolean needsModifiedTreeRebuild = true;
    private boolean needsExplorerRefresh = false;
    private long lastBackPressTime = 0;
    private SharedPreferences dexPref;
    private Menu optionsMenu;
    // --- UI Components ---
    private DrawerLayout drawerLayout;
    private Toolbar drawerToolbar;
    private ActionBarDrawerToggle drawerToggle;
    private ViewPager2 viewPager;
    private TabAdapter tabAdapter;
    private FastScrollerRecyclerView tabsRecyclerView;
    private View classListContainer;
    private ViewPager2 explorerViewPager;
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
            if (viewPager.getTranslationX() != 0) return; // Ignore back while animating
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

    private void initialize(Bundle ignoredSavedInstanceState) {
        drawerLayout = findViewById(R.id.drawer_layout);
        classListContainer = findViewById(R.id.class_list_container);
        viewPager = findViewById(R.id.view_pager);
        tabsRecyclerView = findViewById(R.id.tabs_recycler_view);
        Toolbar toolbar = findViewById(R.id._toolbar);
        setSupportActionBar(toolbar);

        drawerToolbar = findViewById(R.id.drawer_toolbar);
        drawerToolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.ic_more_mt));
        if (drawerToolbar.getOverflowIcon() != null) {
            androidx.core.graphics.drawable.DrawableCompat.setTint(drawerToolbar.getOverflowIcon(), Color.WHITE);
        }
        setupDrawerToolbar();

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                // Ensure the "Home" item highlight and menu states are up to date
                if (tabsAdapter != null) {
                    tabsAdapter.notifyItemChanged(0);
                }
                updateDrawerMenuState();

                if (viewPager.getVisibility() == View.VISIBLE && currentTabIndex != -1) {
                    tabsRecyclerView.scrollToPosition(currentTabIndex + 1);
                }
            }
        });
        drawerToggle.syncState();

        tabAdapter = new TabAdapter(this);
        viewPager.setAdapter(tabAdapter);
        viewPager.setOffscreenPageLimit(10);
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

        tabsRecyclerView.setTrackVisible(false);
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
        TabLayout explorerTabLayout = findViewById(R.id.explorer_tab_layout);
        explorerViewPager = findViewById(R.id.explorer_view_pager);

        ExplorerTabAdapter explorerTabAdapter = new ExplorerTabAdapter(this);
        explorerViewPager.setAdapter(explorerTabAdapter);
        explorerViewPager.setOffscreenPageLimit(1);

        // Optimize drag sensitivity for smoother tab switching
        reduceDragSensitivity(explorerViewPager);

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
                refreshExplorerPage(position);
            }
        });
    }

    private void initializeLogic() {
        // Pre-initialize heavy components in background to avoid lag on first editor open
        new Thread(new Runnable() {
            @Override
            public void run() {
                SmaliInstructionHelper.init(getApplicationContext());
                EditorFragment.ensureLanguageInitialized(getApplicationContext());
            }
        }).start();

        tabs.clear();
        tabNavigationHistory.clear();
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

        List<String> dexPaths = getIntent().getStringArrayListExtra("SelectedDexFiles");
        setTitle("Dex Editor Plus");
        showProcessingProgress(true);
        fabDelete.setBackgroundTintList(ColorStateList.valueOf(0xFFF44336));
        fabDelete.setImageTintList(ColorStateList.valueOf(0xFFFFFFFF));
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

    public void loadStrings() {
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
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            }

            @Override
            public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return 0;
            }
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
        if (getSupportActionBar() == null) return;
        if (viewPager.getVisibility() != View.VISIBLE) {
            getSupportActionBar().setSubtitle((subtitle == null || subtitle.isEmpty()) ? "Temporary project" : subtitle.replace("/", "."));
        } else {
            getSupportActionBar().setSubtitle(subtitle);
        }
    }

    public void setToolbarTitle(String title) {
        if (getSupportActionBar() == null) return;
        getSupportActionBar().setTitle(title);
    }

    public void toggleDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
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
            boolean isGraph = tab.type == 2;

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

            MenuItem jumpToLine = menu.findItem(R.id.jumpToLine);
            if (jumpToLine != null) jumpToLine.setVisible(!isGraph);

            MenuItem readOnlyItem = menu.findItem(R.id.read_only);
            readOnlyItem.setVisible(isSmali);
            readOnlyItem.setChecked(tab.isReadOnly);// preserve the read-only feature for targeted tab only

            MenuItem wrapItem = menu.findItem(R.id.wrap_text);
            if (wrapItem != null) {
                wrapItem.setVisible(!isGraph);
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
            EditorTab tab = tabs.get(currentTabIndex);

            if (id == R.id.close) {
                closeTabWithPrompt(currentTabIndex);
                return true;
            } else if (id == R.id.preference) {
                startActivity(new Intent(DexEditorActivity.this, SettingsActivity.class));
                return true;
            }

            if (tab.type == 2) { // Graph
                return super.onOptionsItemSelected(item);
            }

            EditorFragment editorFragment = getCurrentFragment();
            if (editorFragment == null) return super.onOptionsItemSelected(item);

            CodeEditor editor = editorFragment.getEditor();

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
                // but I have plan to replace the sora editor with my custom MH-Texteditor
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
                if (instruction != null) {
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
            } else if (id == R.id.preference) {
                startActivity(new Intent(DexEditorActivity.this, SettingsActivity.class));
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    // get the smali instruction from cursor position in editor
    public String getCurrentLineSmaliInstruction(EditorFragment fragment) {
        CodeEditor editor = fragment.getEditor();
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
            if (Character.isWhitespace(c)) break;
            if (c == '{' || c == '}' || c == ';') break;
            endOfFirstWord++;
        }

        String firstWord = trimmed.substring(0, endOfFirstWord);
        return SmaliInstructionHelper.isSmaliInstruction(firstWord) ? firstWord : null;
    }

    // when closing the editor fragment tab if the class is edited then there will be a prompt for
    private void closeTabWithPrompt(int index) {
        if (index < 0 || index >= tabs.size()) return;
        final EditorTab tab = tabs.get(index);
        final String className = tab.className;

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
                            saveTab(tab, new Runnable() {
                                @Override
                                public void run() {
                                    clearPositionSaving(className);
                                    removeTab(tab);
                                }
                            });
                        }
                    })
                    .setNeutralButton("Don't Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface d, int w) {
                            clearPositionSaving(className);
                            removeTab(tab);
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        } else {
            clearPositionSaving(className);
            removeTab(tab);
        }
    }

    private void clearPositionSaving(String className) {
        EditorPositionManager.getInstance(this).removePosition(className);
    }

    // smali2java
    private void smali2java(EditorFragment fragment) {
        String code = fragment.getCode();
        String className = fragment.getClassName();
        String title = SmaliHelper.extractSimpleName(className) + ".java";

        // Check if java tab already exists for this class
        for (int i = 0; i < tabs.size(); i++) {
            if (tabs.get(i).className.equals(className) && tabs.get(i).type == 1 && tabs.get(i).title.equals(title)) {
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
                            addTab(className, title, java, 1); // adding the java item in the recent opened classes list
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
        EditText editText = view.findViewById(R.id.editText);
        CodeEditor smaliEditor = fragment.getEditor();

        // set dynamic hint
        String hint = "1⋯" + smaliEditor.getLineCount();
        editText.setHint(hint);

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this)
                .setTitle("Jump to line")
                .setView(view)
                .setPositiveButton("OK", null)
                .setNegativeButton("Cancel", null);

        AlertDialog dialog_mt = builder.create();
        dialog_mt.show();
        dialog_mt.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText.getText().toString().isEmpty()) {
                    editText.setError("Enter something !");
                } else {
                    try {
                        smaliEditor.jumpToLine(Integer.parseInt(editText.getText().toString()) - 1);
                        dialog_mt.dismiss();
                    } catch (Exception e) {
                        editText.setError("Value is out of range.");
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

    public void refreshExplorerPage(int position) {
        if (position == 1 && classTree != null && needsModifiedTreeRebuild) {
            modifiedNodes.clear();
            modifiedNodes.addAll(classTree.buildEditedFullTree());
            needsModifiedTreeRebuild = false;
        }

        if (position == 0 && !needsExplorerRefresh) {
             // Avoid heavy refresh during swipe if not needed
             return;
        }

        Fragment fragment = getSupportFragmentManager().findFragmentByTag("f" + (2000 + position));
        if (fragment instanceof ExplorerPageFragment) {
            final ExplorerPageFragment explorerFrag = (ExplorerPageFragment) fragment;
            if (explorerFrag.rv != null) {
                explorerFrag.rv.post(new Runnable() {
                    @Override
                    public void run() {
                        explorerFrag.updateUI();
                        if (position == 0) needsExplorerRefresh = false;
                    }
                });
            }
        } else if (fragment instanceof SearchFragment) {
            ((SearchFragment) fragment).refreshUI();
        }
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

        // Add tab with content if it's already in pendingSmaliMap or if it's already in another tab
        String content = null;
        if (classTree.getPendingSmaliMap().containsKey(className))
            content = classTree.getPendingSmaliMap().get(className);

        addTab(className, SmaliHelper.extractSimpleName(className), content, 0);
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

    @SuppressLint("NotifyDataSetChanged")
    public void addTab(String className, String title, String code, int type) {
        addTab(className, title, null, code, type);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addTab(String className, String title, String subtitle, String code, int type) {
        // Check if tab already exists for this class and title (avoid duplicates)
        for (int i = 0; i < tabs.size(); i++) {
            EditorTab existingTab = tabs.get(i);
            if (existingTab.className.equals(className) && existingTab.title.equals(title) &&
                    (subtitle == null || subtitle.equals(existingTab.subtitle)) && existingTab.type == type) {
                showEditor(i);
                return;
            }
        }

        EditorTab tab = new EditorTab(className, title, subtitle, code, type);
        if (type == 1 || type == 2) { // Java or Graph
            tab.isReadOnly = true;
        }
        tabs.add(tab);
        tabAdapter.notifyItemInserted(tabs.size() - 1);
        tabsAdapter.notifyDataSetChanged();
        showEditor(tabs.size() - 1);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void showEditor(int index) {
        if (index < 0 || index >= tabs.size()) return;

        int oldIndex = currentTabIndex;
        boolean wasHidden = viewPager.getVisibility() != View.VISIBLE;

        if (wasHidden) {
            tabNavigationHistory.clear();
            viewPager.setCurrentItem(index, false);

            float width = (float) getResources().getDisplayMetrics().widthPixels;

            viewPager.animate().cancel();
            classListContainer.animate().cancel();

            viewPager.setTranslationX(width);
            viewPager.setVisibility(View.VISIBLE);
            classListContainer.setVisibility(View.VISIBLE);

            viewPager.animate()
                    .translationX(0)
                    .setDuration(280)
                    .setInterpolator(new DecelerateInterpolator(1.1f))
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            classListContainer.setVisibility(View.GONE);
                            classListContainer.setTranslationX(0);
                        }
                    })
                    .start();

            classListContainer.animate()
                    .translationX(-width * 0.2f)
                    .setDuration(280)
                    .setInterpolator(new DecelerateInterpolator(1.1f))
                    .start();

            currentTabIndex = index;
            // Targeted updates instead of notifyDataSetChanged
            tabsAdapter.notifyItemChanged(0);
            tabsAdapter.notifyItemChanged(index + 1);
            updateToolbar();
        } else if (oldIndex != index) {
            tabNavigationHistory.push(oldIndex);
            viewPager.setCurrentItem(index, true);
        }

        fabDelete.hide();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void hideEditor() {
        if (viewPager.getVisibility() != View.VISIBLE || viewPager.getTranslationX() != 0) return;

        int oldIndex = currentTabIndex;
        float width = (float) getResources().getDisplayMetrics().widthPixels;

        viewPager.animate().cancel();
        classListContainer.animate().cancel();

        classListContainer.setVisibility(View.VISIBLE);
        classListContainer.setTranslationX(-width * 0.2f);

        viewPager.animate()
                .translationX(width)
                .setDuration(280)
                .setInterpolator(new DecelerateInterpolator(1.1f))
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        viewPager.setVisibility(View.GONE);
                        viewPager.setTranslationX(0);
                        updateToolbar();

                        // Notify adapter AFTER visibility changes to GONE
                        tabsAdapter.notifyItemChanged(0);
                        if (oldIndex != -1) {
                            tabsAdapter.notifyItemChanged(oldIndex + 1);
                        }
                    }
                })
                .start();

        classListContainer.animate()
                .translationX(0)
                .setDuration(280)
                .setInterpolator(new DecelerateInterpolator(1.1f))
                .start();
    }

    public EditorTab getTabForClassName(String className) {
        for (EditorTab tab : tabs) {
            if (tab.className.equals(className)) return tab;
        }
        return null;
    }

    public void onContentModified(String className) {
        needsModifiedTreeRebuild = true;
        for (int i = 0; i < tabs.size(); i++) {
            EditorTab tab = tabs.get(i);
            if (tab.className.equals(className)) {
                EditorFragment fragment = getFragmentAtIndex(i);
                if (fragment != null && fragment.getEditor() != null) {
                    String currentText = fragment.getEditor().getText().toString();
                    tab.content = currentText; // Update current content to preserve it on recreation
                    
                    boolean actuallyModified = !currentText.equals(tab.originalContent);

                    if (tab.isModified != actuallyModified) {
                        tab.isModified = actuallyModified;
                        tabsAdapter.notifyItemChanged(i + 1); // Fixed index: tabs start at position 1
                        invalidateOptionsMenu();
                    }
                }
                break;
            }
        }
    }

    private void saveCurrentTab() {
        final int index = viewPager.getCurrentItem();
        if (index < 0 || index >= tabs.size()) return;
        final EditorTab tab = tabs.get(index);
        saveTab(tab, new Runnable() {
            @Override
            public void run() {
                SketchwareUtil.showMessage(DexEditorActivity.this, "Saved " + tab.title);
            }
        });
    }

    // Compilation options when pressed the dex preference
    // I know the smali libry support version 40 and 41 api
    // but this smali library was newly lauched when I used never knew that there is  bugs releted to api
    private void showCompilationOptionsDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_compilation_options, null);
        Spinner spinnerDexVersion = dialogView.findViewById(R.id.spinner_dex_version);
        SwitchCompat swRemoveAllDebug = dialogView.findViewById(R.id.sw_remove_all_debug);
        SwitchCompat swRemoveDebugSource = dialogView.findViewById(R.id.sw_remove_debug_source);
        SwitchCompat swRemoveDebugLine = dialogView.findViewById(R.id.sw_remove_debug_line);
        SwitchCompat swRemoveDebugParam = dialogView.findViewById(R.id.sw_remove_debug_param);
        SwitchCompat swRemoveDebugPrologue = dialogView.findViewById(R.id.sw_remove_debug_prologue);
        SwitchCompat swRemoveDebugLocal = dialogView.findViewById(R.id.sw_remove_debug_local);

        String[] versions = {"Keep the same", "35", "37", "38", "39"}; // upto 39 is okk
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
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
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

    public void showClassNotfound(String targetClass) {
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

    public void removeTabsForClass(String name, boolean isDirectory) {
        for (int i = tabs.size() - 1; i >= 0; i--) {
            String tabClass = tabs.get(i).className;
            if (isDirectory) {
                if (tabClass.startsWith(name)) {
                    removeTab(i);
                }
            } else {
                if (tabClass.equals(name)) {
                    removeTab(i);
                }
            }
        }
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
                final EditorTab tab = modifiedTabs.get(i);
                saveTab(tab, new Runnable() { // save one after one
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
    // @tab the tab to be saved, @onSaved used for proper task to detect if the saving completed or not
    private void saveTab(final EditorTab tab, final Runnable onSaved) {
        if (tab == null) {
            if (onSaved != null) onSaved.run();
            return;
        }

        final EditorFragment fragment = (EditorFragment) getSupportFragmentManager().findFragmentByTag("f" + tab.id);
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
                            tab.isModified = false; 
                            tab.content = code;
                            tab.originalContent = code;
                            int currentIndex = tabs.indexOf(tab);
                            if (currentIndex != -1) {
                                tabsAdapter.notifyItemChanged(currentIndex + 1); // Fixed index: home is at 0
                            }
                            isChanged = true;
                            needsModifiedTreeRebuild = true;
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

        // clear all tab data
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

    // multiple fabs for slection of tree nodes
    private void initializeFab() {
        @SuppressLint("InflateParams") View root = getLayoutInflater().inflate(R.layout.multiple_fabs, null);
        LinearLayout fabLayout = root.findViewById(R.id.linear_bg);
        fabBackground = fabLayout;
        FloatingActionButton fabInvertSelect = fabLayout.findViewById(R.id.fab_select_rest);
        FloatingActionButton fabClear = fabLayout.findViewById(R.id.fab_clear);

        // Remove fabLayout from its parent (the root of multiple_fabs.xml) before adding to fabDelete's parent
        ((ViewGroup) fabLayout.getParent()).removeView(fabLayout);
        ((ViewGroup) fabDelete.getParent()).addView(fabLayout);
        fabClear.setBackgroundTintList(ColorStateList.valueOf(0xFFBEBEC3));
        fabClear.setImageTintList(ColorStateList.valueOf(0xFFFFFFFF));
        fabInvertSelect.setBackgroundTintList(ColorStateList.valueOf(0xFFBEBEC3));
        fabInvertSelect.setImageTintList(ColorStateList.valueOf(0XFFFFFFFF));
        fabInvertSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment currentFragment = getSupportFragmentManager().findFragmentByTag("f" + (2000 + explorerViewPager.getCurrentItem()));
                if (currentFragment instanceof ExplorerPageFragment) {
                    RecyclerView rv = ((ExplorerPageFragment) currentFragment).rv;
                    if (rv != null && rv.getAdapter() instanceof TreeAdapter) {
                        ((TreeAdapter) rv.getAdapter()).invertSelection();
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

    private void removeTab(EditorTab tab) {
        int index = tabs.indexOf(tab);
        if (index != -1) {
            removeTab(index);
        }
    }

    private void reduceDragSensitivity(ViewPager2 viewPager) {
        try {
            java.lang.reflect.Field recyclerViewField = ViewPager2.class.getDeclaredField("mRecyclerView");
            recyclerViewField.setAccessible(true);
            RecyclerView recyclerView = (RecyclerView) recyclerViewField.get(viewPager);

            java.lang.reflect.Field touchSlopField = RecyclerView.class.getDeclaredField("mTouchSlop");
            touchSlopField.setAccessible(true);
            int touchSlop = (int) touchSlopField.get(recyclerView);
            touchSlopField.set(recyclerView, touchSlop * 2);
        } catch (Exception ignored) {
        }
    }

    private void setupDrawerToolbar() {
        drawerToolbar.getMenu().add(0, 0, 0, "Close other");
        drawerToolbar.getMenu().add(0, 1, 0, "Close all");
        drawerToolbar.getMenu().add(0, 2, 0, "Close unmodified");
        drawerToolbar.getMenu().add(0, 3, 0, "Close above");
        drawerToolbar.getMenu().add(0, 4, 0, "Close below");

        drawerToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int index = currentTabIndex;
                switch (item.getItemId()) {
                    case 0: // Close other
                        if (index != -1) tabsAdapter.closeOtherTabs(index);
                        break;
                    case 1: // Close all
                        tabsAdapter.closeAllTabs();
                        break;
                    case 2: // Close unmodified
                        tabsAdapter.closeUnmodifiedTabs();
                        break;
                    case 3: // Close above
                        if (index != -1) tabsAdapter.closeTabsAbove(index);
                        break;
                    case 4: // Close below
                        if (index != -1) tabsAdapter.closeTabsBelow(index);
                        break;
                }
                return true;
            }
        });
    }

    private void updateDrawerMenuState() {
        Menu menu = drawerToolbar.getMenu();
        int index = currentTabIndex;
        int count = tabs.size();

        menu.findItem(0).setEnabled(index != -1 && count > 1);
        menu.findItem(1).setEnabled(count > 0);
        menu.findItem(2).setEnabled(count > 0);

        MenuItem closeAbove = menu.findItem(3);
        closeAbove.setEnabled(index > 0);
        UIHelper.setMenuItemColor(closeAbove, closeAbove.isEnabled() ? Color.BLACK : Color.GRAY);

        MenuItem closeBelow = menu.findItem(4);
        closeBelow.setEnabled(index != -1 && index < count - 1);
        UIHelper.setMenuItemColor(closeBelow, closeBelow.isEnabled() ? Color.BLACK : Color.GRAY);

        // Also update other items color if needed
        UIHelper.setMenuItemColor(menu.findItem(0), menu.findItem(0).isEnabled() ? Color.BLACK : Color.GRAY);
        UIHelper.setMenuItemColor(menu.findItem(1), menu.findItem(1).isEnabled() ? Color.BLACK : Color.GRAY);
        UIHelper.setMenuItemColor(menu.findItem(2), menu.findItem(2).isEnabled() ? Color.BLACK : Color.GRAY);
    }

    public static class EditorTab {
        private static long idCounter = 1;
        public long id;
        public String className;
        public String title;
        public String subtitle;
        public String content;
        public String originalContent;
        public int type; // 0: Smali, 1: Java, 2: Graph
        public boolean isModified;
        public boolean isReadOnly;

        // Pending navigation
        public int pendingLine = -1;
        public int pendingColumn = -1;
        public String pendingQuery = null;

        EditorTab(String className, String title, String subtitle, String content, int type) {
            this.id = idCounter++;
            this.className = className;
            this.title = title;
            this.subtitle = subtitle;
            this.content = content;
            this.originalContent = content;
            this.type = type;
            this.isModified = false;
            this.isReadOnly = false;
        }
    }

    public static class ExplorerPageFragment extends Fragment {
        FastScrollerRecyclerView rv;
        private int position;
        private RecyclerView.Adapter<?> currentAdapter;

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
            rv = new FastScrollerRecyclerView(requireContext());
            rv.setTrackVisible(false);
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

        @SuppressLint("NotifyDataSetChanged")
        public void updateUI() {
            if (rv == null) return;
            DexEditorActivity activity = (DexEditorActivity) getActivity();
            if (activity == null) return;

            // Reuse existing adapters for all tabs to avoid frame drops
            if (currentAdapter != null && position != 1) {
                if (currentAdapter instanceof TreeAdapter) {
                    ((TreeAdapter) currentAdapter).refreshVisibleNodes();
                } else if (currentAdapter instanceof StringAdapter) {
                    currentAdapter.notifyDataSetChanged();
                } else if (currentAdapter instanceof ConcatAdapter) {
                    // For History/Modified tab, we need to be careful.
                    // If we use ConcatAdapter, we might need to update sub-adapters.
                    // But for now, let's just fall through if position is 1.

                    // Get string adapter from ConcatAdapter of strings tab
                    List<? extends RecyclerView.Adapter<?>> adapters = ((ConcatAdapter) currentAdapter).getAdapters();
                    if(adapters.size() > 1) {
                        RecyclerView.Adapter<?> adapter = adapters.get(1);
                        if (adapter instanceof StringAdapter) adapter.notifyDataSetChanged();
                    }
                } else {
                    currentAdapter.notifyDataSetChanged();
                }
                if (position != 1) return;
            }

            switch (position) {
                case 0:
                    currentAdapter = new TreeAdapter(getContext(), activity.treeRoots, new TreeAdapter.OnNodeClickListener() {
                        @Override
                        public void onNodeClick(TreeNode node) {
                            activity.openClass(node.getFullName());
                        }

                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onNodeDeleted(TreeNode node) {
                            activity.clearPositionSaving(node.getFullName());
                            activity.removeTabsForClass(node.getFullName(), node.isDirectory());
                            classTree.removeClass(node.getFullName());
                            DexEditorActivity.isChanged = true;
                            activity.needsModifiedTreeRebuild = true;
                            activity.needsExplorerRefresh = true;
                            activity.refreshExplorerPage(1);
                            activity.tabsAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onSelectionChanged(int count) {
                            activity.showMultipleFabs(count > 0);
                            if (count > 0) activity.fabDelete.show();
                            else activity.fabDelete.hide();
                        }

                        @Override
                        public void onSearch(TreeNode node) {
                            activity.pendingSearchPath = node.getFullName() + "/";
                            activity.explorerViewPager.setCurrentItem(2);
                        }
                    }, false);
                    break;
                case 1:
                    // For History/Modified, we use ConcatAdapter which is slightly harder to reuse,
                    // but we can at least avoid rebuilding if not needed.
                    ConcatAdapter concatAdapter = new ConcatAdapter();
                    boolean hasRecently = !activity.historyNodes.isEmpty();
                    boolean hasModified = !activity.modifiedNodes.isEmpty();

                    if (hasRecently) {
                        concatAdapter.addAdapter(new HeaderAdapter("Recently", new HeaderAdapter.OnMenuClickListener() {
                            @Override
                            public void onMenuClick(View view) {
                                PopupMenu popup = new PopupMenu(requireContext(), view);
                                popup.getMenu().add("Clear all");
                                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                    @Override
                                    public boolean onMenuItemClick(MenuItem item) {
                                        if ("Clear all".contentEquals(Objects.requireNonNull(item.getTitle()))) {
                                            activity.historyNodes.clear();
                                            activity.refreshExplorerPage(1);
                                            return true;
                                        }
                                        return false;
                                    }
                                });
                                popup.show();
                            }
                        }));
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

                            @Override
                            public void onSearch(TreeNode node) {
                                activity.pendingSearchPath = node.getFullName() + "/";
                                activity.explorerViewPager.setCurrentItem(2);
                            }
                        }, true));
                    }

                    if (hasModified) {
                        concatAdapter.addAdapter(new HeaderAdapter("Modified"));
                        concatAdapter.addAdapter(new TreeAdapter(requireContext(), activity.modifiedNodes, new TreeAdapter.OnNodeClickListener() {
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
                            public void onSearch(TreeNode node) {
                                activity.pendingSearchPath = node.getFullName() + "/";
                                activity.explorerViewPager.setCurrentItem(2);
                            }

                            @Override
                            public void onCompare(TreeNode node) {
                                // TODO: Implement compare the difference
                            }
                        }, false, true));
                    }
                    currentAdapter = concatAdapter;
                    break;
                case 2:
                    currentAdapter = new TreeAdapter(getContext(), activity.searchNodes, new TreeAdapter.OnNodeClickListener() {
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
                    }, true);
                    break;
                case 3:
                    View header = LayoutInflater.from(getContext()).inflate(R.layout.strings_header, rv, false);

                    // holder trick so the click listener can reference the adapter it belongs to
                    final StringAdapter[] holder = new StringAdapter[1];
                    StringAdapter stringAdapter = new StringAdapter(activity.stringList, text -> activity.showStringEditDialog(holder[0], header.<TextView>findViewById(R.id.btn_strings_apply), text));
                    holder[0] = stringAdapter;
                    currentAdapter = new ConcatAdapter(new SearchFragment.HeaderViewAdapter(header), stringAdapter);

                    /*header.findViewById(R.id.btn_strings_reload).setOnClickListener(v -> {
                        stringAdapter.clearModifications();
                        stringAdapter.setFilter(null);
                        btnApply.setVisibility(View.GONE);
                        activity.loadStrings();
                    });*/ // It doesnt look like reload working maybe whole loadStrings implementation would need to change to get it to work
                    header.findViewById(R.id.btn_strings_filter).setOnClickListener(v -> activity.showStringFilterDialog(stringAdapter));
                    header.findViewById(R.id.btn_strings_replace).setOnClickListener(v -> activity.showStringReplaceAllDialog());
                    header.findViewById(R.id.btn_strings_apply).setOnClickListener(v -> activity.applyStringChanges(stringAdapter, header.<TextView>findViewById(R.id.btn_strings_apply)));
                    break;
            }
            if (currentAdapter != null) {
                rv.setAdapter(currentAdapter);
            }
        }
    }

    public void showStringEditDialog(final StringAdapter adapter, final View btnApply, final String original) {
        final EditText editText = new EditText(this);
        editText.setText(adapter.getPendingValue(original));
        editText.setSelection(editText.getText().length());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        editText.setLayoutParams(params);
        editText.setGravity(android.view.Gravity.TOP);

        int pad = (int) getDip(16);
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setPadding(pad, pad, pad, pad);
        container.addView(editText);
        container.setLayoutParams(params);

        final AlertDialog dialog = new MaterialAlertDialogBuilder(this)
                .setTitle("Edit string")
                .setView(container)
                .setPositiveButton("OK", null)
                .setNeutralButton("Search", null)
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();

        // Custom click listeners so "Search" doesn't auto-dismiss before we can read the field.
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            String newValue = editText.getText().toString();
            adapter.markModified(original, newValue);
            btnApply.setVisibility(adapter.hasModifications() ? View.VISIBLE : View.GONE);
            dialog.dismiss();
        });
        dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener(v -> {
            dialog.dismiss();
            searchStringInClasses(original);
        });
    }

    public void showStringFilterDialog(final StringAdapter adapter) {
        final EditText editText = new EditText(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        editText.setLayoutParams(params);
        editText.setHint("Type to filter...");
        if (adapter.getCurrentFilter() != null) editText.setText(adapter.getCurrentFilter());

        int pad = (int) getDip(16);
        LinearLayout container = new LinearLayout(this);
        container.setPadding(pad, pad, pad, pad);
        container.setLayoutParams(params);
        container.addView(editText);

        new MaterialAlertDialogBuilder(this)
                .setTitle("Filter strings")
                .setView(container)
                .setPositiveButton("Apply", (d, w) -> adapter.setFilter(editText.getText().toString()))
                .setNegativeButton("Clear", (d, w) -> adapter.setFilter(null))
                .show();
    }

    public void showStringReplaceAllDialog() {
        View container = LayoutInflater.from(this).inflate(R.layout.string_replace_all_dialog, null);
        TextView etFind = container.findViewById(R.id.etFind), etReplace = container.findViewById(R.id.etReplace);
        MaterialCheckBox swMatchCase = container.findViewById(R.id.swMatchCase);

        new MaterialAlertDialogBuilder(this)
                .setTitle("Replace in all strings")
                .setView(container)
                .setPositiveButton("Replace", (d, w) -> {
                    String find = etFind.getText().toString();
                    String replace = etReplace.getText().toString();
                    if (find.isEmpty()) return;
                    new StringBatchTask(this, null, find, replace, swMatchCase.isChecked(), null).start();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    public void applyStringChanges(final StringAdapter adapter, final View btnApply) {
        final Map<String, String> changes = new LinkedHashMap<>(adapter.getModifiedStrings());
        if (changes.isEmpty()) return;
        new StringBatchTask(this, changes, null, null, true, () -> {
            adapter.clearModifications();
            btnApply.setVisibility(View.GONE);
            loadStrings();
        }).start();
    }

    public void searchStringInClasses(String query) {
        if (explorerViewPager == null) return;
        explorerViewPager.setCurrentItem(2, true);
        Fragment f = getSupportFragmentManager().findFragmentByTag("f2002");
        if (f instanceof SearchFragment) {
            ((SearchFragment) f).runStringSearch(query);
        } else {
            // Fragment not created/attached yet - SearchFragment.onResume() will pick this up.
            pendingStringSearchQuery = query;
        }
    }

    private static class ExplorerTabAdapter extends FragmentStateAdapter {
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
                            treeRoots.clear();
                            treeRoots.addAll(roots);
                            modifiedNodes.clear();
                            modifiedNodes.addAll(modified);
                            needsModifiedTreeRebuild = false;
                            needsExplorerRefresh = true;
                            // update toolbar
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
            builder.setTitle(getString(R.string.error));
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

    private static class StringBatchTask {
        private final java.lang.ref.WeakReference<DexEditorActivity> activityRef;
        private final Map<String, String> exactReplacements;
        private final String findSubstring;
        private final String replaceSubstring;
        private final boolean matchCase;
        private final Runnable onDone;
        private final Handler mainHandler = new Handler(Looper.getMainLooper());
        private AlertProgress progressDialog;
        private volatile boolean isStopped = false;

        StringBatchTask(DexEditorActivity activity, Map<String, String> exactReplacements,
                        String findSubstring, String replaceSubstring, boolean matchCase, Runnable onDone) {
            this.activityRef = new java.lang.ref.WeakReference<>(activity);
            this.exactReplacements = exactReplacements;
            this.findSubstring = findSubstring;
            this.replaceSubstring = replaceSubstring;
            this.matchCase = matchCase;
            this.onDone = onDone;
        }

        void start() {
            final DexEditorActivity activity = activityRef.get();
            if (activity == null || classTree == null) return;

            progressDialog = new AlertProgress(activity);
            progressDialog.setTitle("Applying changes...");
            progressDialog.setCancelable(false);
            progressDialog.setOnCancelListener(() -> isStopped = true);
            progressDialog.show();

            final Map<String, String> openTabsContent = new HashMap<>();
            for (int i = 0; i < tabs.size(); i++) {
                EditorTab tab = tabs.get(i);
                if (tab.type == 0) {
                    EditorFragment ef = activity.getFragmentAtIndex(i);
                    if (ef != null && ef.getEditor() != null) {
                        openTabsContent.put(tab.className, ef.getEditor().getText().toString());
                    } else {
                        openTabsContent.put(tab.className, tab.content);
                    }
                }
            }

            new Thread(() -> {
                List<ClassDef> classes = new ArrayList<>(classTree.classMap.values());
                int total = classes.size();
                int processed = 0, replacedCount = 0, affectedClasses = 0;
                final Map<String, String> updatedTabs = new HashMap<>();
                Pattern literalPattern = Pattern.compile("\"((?:\\\\.|[^\"\\\\])*)\"");

                for (ClassDef classDef : classes) {
                    if (isStopped) break;
                    String fullType = classDef.getType();
                    String className = fullType.substring(1, fullType.length() - 1);
                    try {
                        String original = openTabsContent.containsKey(className) ? openTabsContent.get(className) : classTree.getSmaliByType(classDef);

                        Matcher m = literalPattern.matcher(original);
                        StringBuffer sb = new StringBuffer();
                        int countInClass = 0;
                        while (m.find()) {
                            String literal = m.group(1);
                            String replaced = applyRules(literal);
                            if (!replaced.equals(literal)) countInClass++;
                            m.appendReplacement(sb, Matcher.quoteReplacement("\"" + replaced + "\""));
                        }
                        m.appendTail(sb);

                        if (countInClass > 0) {
                            String modified = sb.toString();
                            try {
                                ClassDef newDef = Smali.assemble(modified, new SmaliOptions(), activity.dexVersion);
                                classTree.saveClassDef(newDef);
                            } catch (Exception e) {
                                classTree.saveSmali(className, modified);
                            }
                            replacedCount += countInClass;
                            affectedClasses++;
                            if (openTabsContent.containsKey(className)) updatedTabs.put(className, modified);
                        }
                    } catch (Exception ignored) {
                    }

                    processed++;
                    final int fp = processed, ft = total;
                    mainHandler.post(() -> {
                        if (progressDialog != null && progressDialog.isShowing()) progressDialog.setProgress(fp, ft);
                    });
                }

                final int finalReplaced = replacedCount, finalAffected = affectedClasses;
                mainHandler.post(() -> {
                    if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
                    if (!updatedTabs.isEmpty()) {
                        for (Map.Entry<String, String> e : updatedTabs.entrySet()) {
                            for (int i = 0; i < tabs.size(); i++) {
                                EditorTab tab = tabs.get(i);
                                if (tab.className.equals(e.getKey()) && tab.type == 0) {
                                    tab.content = e.getValue();
                                    EditorFragment ef = activity.getFragmentAtIndex(i);
                                    if (ef != null && ef.getEditor() != null) ef.getEditor().setText(e.getValue());
                                }
                            }
                        }
                    }
                    Notify_MT.Notify(activity, "Info", "Replaced " + finalReplaced + " occurrence(s) in " + finalAffected + " class(es).", "Close");
                    isChanged = true;
                    activity.needsModifiedTreeRebuild = true;
                    activity.refreshExplorerPage(1);
                    if (onDone != null) onDone.run();
                });
            }).start();
        }

        private String applyRules(String literal) {
            if (exactReplacements != null) {
                String rep = exactReplacements.get(literal);
                if (rep != null) return rep; // exact whole-literal match (used by "Apply changes")
            }
            if (findSubstring != null && !findSubstring.isEmpty()) {
                if (matchCase) {
                    return literal.replace(findSubstring, replaceSubstring);
                } else {
                    Pattern p = Pattern.compile(Pattern.quote(findSubstring), Pattern.CASE_INSENSITIVE);
                    return p.matcher(literal).replaceAll(Matcher.quoteReplacement(replaceSubstring));
                }
            }
            return literal;
        }
    }

    private class SaveAndExitClickListener implements DialogInterface.OnClickListener {
        private volatile boolean isStopped = false;

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            final AlertProgress alertProgress = new AlertProgress(DexEditorActivity.this);
            alertProgress.setTitle("Processing...");
            alertProgress.setMessage("...");
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
                                java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\[(\\d+),(\\d+)]").matcher(error);
                                if (matcher.find()) {
                                    line = Integer.parseInt(Objects.requireNonNull(matcher.group(1))) - 1; // 0-based
                                    column = Integer.parseInt(Objects.requireNonNull(matcher.group(2))) - 1; // 0-based
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
                                    final String nameForTabRemoval = fullName;
                                    final boolean isDirForTabRemoval = node.isDirectory();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            removeTabsForClass(nameForTabRemoval, isDirForTabRemoval);
                                        }
                                    });
                                }
                                classTree.removeClasses(namesToDelete);
                                runOnUiThread(new Runnable() {
                                    @SuppressLint("NotifyDataSetChanged")
                                    @Override
                                    public void run() {
                                        adapter.removeSelectedNodes();
                                        adapter.setSelectionMode(false);
                                        showMultipleFabs(false);
                                        fabDelete.hide();
                                        isChanged = true;
                                        needsModifiedTreeRebuild = true;
                                        refreshExplorerPage(1);
                                        tabsAdapter.notifyDataSetChanged();
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

    private static class TabAdapter extends FragmentStateAdapter {
        public TabAdapter(@NonNull AppCompatActivity activity) {
            super(activity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            EditorTab tab = tabs.get(position);
            if (tab.type == 2) {
                return modder.hub.dexeditor.fragment.GraphFragment.newInstance(tab.className, tab.title, tab.subtitle, tab.content);
            }
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
                        drawerLayout.closeDrawer(GravityCompat.START);
                        v.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                hideEditor();
                            }
                        }, 250);
                    }
                });
                holder.menuView.setVisibility(View.GONE);
                holder.mainView.setOnTouchListener(null);
            } else {
                int tabIndex = position - 1;
                EditorTab tab = tabs.get(tabIndex);
                isSelected = (viewPager.getVisibility() == View.VISIBLE && tabIndex == currentTabIndex);
                holder.title.setText((tab.isModified ? "*" : "") + tab.title); // highlight the spcific edited classes with star
                holder.title.setTextColor(isSelected ? skyColor : Color.BLACK);

                holder.path.setVisibility(View.VISIBLE);
                holder.path.setText(tab.className);
                if (tab.type == 1) {
                    holder.icon.setImageResource(R.drawable.ic_java_mt);
                } else if (tab.type == 2) {
                    holder.icon.setImageResource(R.drawable.ic_flow_diagram);
                } else {
                    holder.icon.setImageResource(R.drawable.ic_edit_mt);
                }
                holder.icon.setImageTintList(ColorStateList.valueOf(isSelected ? skyColor : Color.BLACK));

                holder.mainView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int currentPos = holder.getBindingAdapterPosition();
                        if (currentPos == RecyclerView.NO_POSITION) return;

                        if (v.getTranslationX() != 0) {
                            v.animate().translationX(0).setDuration(150).start();
                            swipedPosition = -1;
                            return;
                        }

                        // Close drawer first for smoother animation
                        drawerLayout.closeDrawer(GravityCompat.START);

                        // Use accurate tabIndex from current holder position
                        final int targetTabIndex = currentPos - 1;
                        v.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                showEditor(targetTabIndex);
                            }
                        }, 200);
                    }
                });

                holder.mainView.setOnTouchListener(new View.OnTouchListener() {
                    private final int touchSlop = android.view.ViewConfiguration.get(getApplicationContext()).getScaledTouchSlop();
                    private float startX;
                    private float initialX;
                    private boolean isDragging = false;

                    @SuppressLint("ClickableViewAccessibility")
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
                                if (Math.abs(diff) > touchSlop || isDragging) {
                                    isDragging = true;
                                    v.setTranslationX(Math.max(0, Math.min(startX + diff, swipeWidth)));
                                    v.getParent().requestDisallowInterceptTouchEvent(true);
                                    return true;
                                }
                                break;
                            case MotionEvent.ACTION_UP:
                            case MotionEvent.ACTION_CANCEL:
                                if (isDragging) {
                                    float target = v.getTranslationX() > swipeWidth / 3 ? swipeWidth : 0;
                                    v.animate().translationX(target).setDuration(150).start();
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
                        int currentPos = holder.getBindingAdapterPosition();
                        if (currentPos != RecyclerView.NO_POSITION) {
                            swipedPosition = -1;
                            holder.mainView.setTranslationX(0);
                            closeTab(currentPos - 1);
                        }
                    }
                });
                holder.menuLocate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int currentPos = holder.getBindingAdapterPosition();
                        if (currentPos != RecyclerView.NO_POSITION) {
                            swipedPosition = -1;
                            holder.mainView.animate().translationX(0).setDuration(200).start();
                            locateClass(tabs.get(currentPos - 1).className);
                            drawerLayout.closeDrawers();
                        }
                    }
                });
            }
            holder.mainView.setBackgroundColor(isSelected ? Color.parseColor("#E1F5FE") : Color.WHITE);
        }

        public void closeOtherTabs(int index) {
            for (int i = tabs.size() - 1; i >= 0; i--) {
                if (i != index) {
                    closeTab(i);
                }
            }
            updateDrawerMenuState();
        }

        public void closeAllTabs() {
            for (int i = tabs.size() - 1; i >= 0; i--) {
                closeTab(i);
            }
            updateDrawerMenuState();
        }

        public void closeUnmodifiedTabs() {
            for (int i = tabs.size() - 1; i >= 0; i--) {
                if (!tabs.get(i).isModified) {
                    closeTab(i);
                }
            }
            updateDrawerMenuState();
        }

        public void closeTabsAbove(int index) {
            for (int i = index - 1; i >= 0; i--) {
                closeTab(i);
            }
            updateDrawerMenuState();
        }

        public void closeTabsBelow(int index) {
            for (int i = tabs.size() - 1; i > index; i--) {
                closeTab(i);
            }
            updateDrawerMenuState();
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
