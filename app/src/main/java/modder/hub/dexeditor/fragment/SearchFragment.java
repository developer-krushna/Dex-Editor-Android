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
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tools.smali.baksmali.BaksmaliOptions;
import com.android.tools.smali.baksmali.formatter.BaksmaliWriter;
import com.android.tools.smali.dexlib2.AccessFlags;
import com.android.tools.smali.dexlib2.iface.ClassDef;
import com.android.tools.smali.smali.SmaliOptions;
import com.android.tools.smali.smali2.Smali;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modder.hub.dexeditor.R;
import modder.hub.dexeditor.activity.DexEditorActivity;
import modder.hub.dexeditor.adapter.TreeAdapter;
import modder.hub.dexeditor.model.TreeNode;
import modder.hub.dexeditor.utils.Notify_MT;
import modder.hub.dexeditor.utils.SketchwareUtil;
import modder.hub.dexeditor.views.AlertProgress;

// Author : @developer-krushna
// Got some LOGICS from AI but thought , idea other resources copied from mt manager interface
// Thanks @Bin

/**
 * SearchFragment: Handles all search and replace operations in the DEX editor.
 * It supports Smali code, class names, methods, fields, and even raw integers/hex.
 */
public class SearchFragment extends Fragment {

    // UI components for the search result list and control panel
    private RecyclerView recyclerView;
    private LinearLayout btnNewSearch;
    private LinearLayout btnSearchInResults;
    private LinearLayout btnReplaceInResults;
    private LinearLayout btnClearResults;
    private LinearLayout layoutSearchInfo;
    private TextView tvSearchInfo;
    private List<TreeNode> searchResults = new ArrayList<>();
    private TreeAdapter adapter;
    private String currentQuery;
    private ImageView btnSearchMenu;

    // We store the last search settings to provide a better user experience (stickiness)
    private String lastSearchQuery = "";
    private String lastSearchPath = "/";
    private String lastReplaceWith = "";
    private String lastSearchType = "Smali";
    private boolean lastSearchSubfolders = true;
    private boolean lastMatchCase = false;
    private boolean lastIsRegex = false;
    private boolean lastExactlyMatch = false;
    private boolean lastIsHex = false;
    private boolean lastUseExcludeList = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        btnNewSearch = view.findViewById(R.id.btn_new_search);
        btnSearchInResults = view.findViewById(R.id.btn_search_in_results);
        btnReplaceInResults = view.findViewById(R.id.btn_replace_in_results);
        btnClearResults = view.findViewById(R.id.btn_clear_results);
        layoutSearchInfo = view.findViewById(R.id.layout_search_info);
        tvSearchInfo = view.findViewById(R.id.tv_search_results_info);
        btnSearchMenu = view.findViewById(R.id.btn_search_menu);
        recyclerView = view.findViewById(R.id.search_results_rv);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Set up the results list with a custom TreeAdapter
        DexEditorActivity activity = (DexEditorActivity) getActivity();
        if (activity != null) {
            searchResults = activity.searchNodes;
            adapter = new TreeAdapter(getContext(), searchResults, new TreeAdapter.OnNodeClickListener() {
                @Override
                public void onNodeClick(TreeNode node) {
                    // Navigate to the class or specific snippet line when clicked
                    if (node.isSnippet()) {
                        activity.openClassAtLine(node.getFullName(), node.getLineNumber(), currentQuery);
                    } else if (!node.isDirectory()) {
                        activity.openClass(node.getFullName());
                    }
                }

                @Override
                public void onNodeDeleted(TreeNode node) {
                    removeNodeFromSearchResults(node);
                }

                @Override
                public void onSelectionChanged(int count) {
                }

                @Override
                public void onLocate(TreeNode node) {
                    activity.locateClass(node.getFullName());
                }

                @Override
                public void onCopyName(TreeNode node) {
                    activity.copiedToClipboard(node.getName());
                }
            }, false);
            adapter.setSearchList(true, currentQuery);
            recyclerView.setAdapter(adapter);
        }

        btnNewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUnsavedAndShowWarning(new Runnable() {
                    @Override
                    public void run() {
                        showSearchDialog(false);
                    }
                });
            }
        });
        btnSearchInResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSearchDialog(true);
            }
        });
        btnReplaceInResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReplaceDialog();
            }
        });
        btnClearResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearResults();
            }
        });
        btnSearchMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSearchMenu(v);
            }
        });

        updateUIState();

        return view;
    }

    /**
     * Checks if there are any unsaved changes in open tabs before performing a search.
     * This prevents potential data loss or search inconsistencies.
     */
    private void checkUnsavedAndShowWarning(Runnable onProceed) {
        List<DexEditorActivity.EditorTab> modifiedTabs = new ArrayList<>();
        DexEditorActivity activity = (DexEditorActivity) getActivity();
        if (activity != null) {
            for (DexEditorActivity.EditorTab tab : DexEditorActivity.tabs) {
                if (tab.isModified) {
                    modifiedTabs.add(tab);
                }
            }
        }

        if (!modifiedTabs.isEmpty()) {
            new MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Info")
                    .setMessage("You need to save all the codes before proceeding. Do you want to continue ?")
                    .setPositiveButton("Save and Continue", new android.content.DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(android.content.DialogInterface dialog, int which) {
                            boolean[] checked = new boolean[modifiedTabs.size()];
                            Arrays.fill(checked, true);
                            if (activity != null) {
                                activity.saveMultipleTabs(modifiedTabs, checked, onProceed);
                            } else {
                                onProceed.run();
                            }
                        }
                    })
                    .setNeutralButton("Cancel", null)
                    .show();
        } else {
            onProceed.run();
        }
    }

    private void updateUIState() {
        boolean hasResults = !searchResults.isEmpty();
        boolean hasQuery = currentQuery != null;
        int visibility = hasResults ? View.VISIBLE : View.GONE;
        btnSearchInResults.setVisibility(visibility);
        btnReplaceInResults.setVisibility(visibility);
        btnClearResults.setVisibility(visibility);
        layoutSearchInfo.setVisibility(hasQuery || hasResults ? View.VISIBLE : View.GONE);
    }

    private void removeNodeFromSearchResults(TreeNode node) {
        if (node.getParent() != null) {
            node.getParent().getChildren().remove(node);
            if (node.getParent().getChildren().isEmpty()) {
                removeNodeFromSearchResults(node.getParent());
            }
        } else {
            searchResults.remove(node);
        }
        updateSearchInfoBar();
        updateUIState();
        adapter.refreshVisibleNodes();
    }

    private void clearResults() {
        searchResults.clear();
        currentQuery = null;
        updateSearchInfoBar();
        updateUIState();
        adapter.setSearchList(true, null);
        adapter.refreshVisibleNodes();
    }

    private void showSearchMenu(View v) {
        PopupMenu popup = new PopupMenu(requireContext(), v);
        popup.getMenu().add(0, 1, 0, "Collapse all");
        popup.getMenu().add(0, 2, 0, "Expand all");
        popup.getMenu().add(0, 3, 0, "Only expand packages");
        popup.getMenu().add(0, 4, 0, "Copy class names");

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(android.view.MenuItem item) {
                switch (item.getItemId()) {
                    case 1:
                        collapseAll(searchResults);
                        adapter.refreshVisibleNodes();
                        return true;
                    case 2:
                        expandAll(searchResults);
                        adapter.refreshVisibleNodes();
                        return true;
                    case 3:
                        onlyExpandPackages(searchResults);
                        adapter.refreshVisibleNodes();
                        return true;
                    case 4:
                        copyClassNames();
                        return true;
                }
                return false;
            }
        });
        popup.show();
    }

    private void collapseAll(List<TreeNode> nodes) {
        for (TreeNode node : nodes) {
            node.setExpanded(false);
            collapseAll(node.getChildren());
        }
    }

    private void expandAll(List<TreeNode> nodes) {
        for (TreeNode node : nodes) {
            if (!node.getChildren().isEmpty()) {
                node.setExpanded(true);
                expandAll(node.getChildren());
            }
        }
    }

    private void onlyExpandPackages(List<TreeNode> nodes) {
        for (TreeNode node : nodes) {
            if (node.isDirectory()) {
                node.setExpanded(true);
                onlyExpandPackages(node.getChildren());
            } else {
                node.setExpanded(false);
            }
        }
    }

    private void copyClassNames() {
        StringBuilder sb = new StringBuilder();
        collectClassNamesRecursive(searchResults, sb);
        if (sb.length() > 0) {
            DexEditorActivity activity = (DexEditorActivity) getActivity();
            if (activity != null) {
                activity.copiedToClipboard(sb.toString().trim());
            }
        }
    }

    private void collectClassNamesRecursive(List<TreeNode> nodes, StringBuilder sb) {
        for (TreeNode node : nodes) {
            if (!node.isDirectory() && !node.isSnippet()) {
                sb.append(node.getFullName().replace('/', '.')).append("\n");
            }
            collectClassNamesRecursive(node.getChildren(), sb);
        }
    }

    @SuppressLint("SetTextI18n")
    private void updateSearchInfoBar() {
        boolean hasResults = !searchResults.isEmpty();
        boolean hasQuery = currentQuery != null;
        if (!hasQuery && !hasResults) {
            layoutSearchInfo.setVisibility(View.GONE);
        } else {
            layoutSearchInfo.setVisibility(View.VISIBLE);
            int count = countTotalSnippets(searchResults);
            tvSearchInfo.setText("search results (" + count + ") - " + (currentQuery != null ? currentQuery : ""));
        }
    }

    private int countTotalSnippets(List<TreeNode> nodes) {
        int count = 0;
        for (TreeNode node : nodes) {
            if (node.isSnippet()) {
                count++;
            } else if (!node.isDirectory() && node.getChildren().isEmpty()) {
                count++;
            }
            count += countTotalSnippets(node.getChildren());
        }
        return count;
    }

    private void showExcludeListDialog() {
        android.content.SharedPreferences prefs = requireContext().getSharedPreferences("search_prefs", android.content.Context.MODE_PRIVATE);
        String savedExcludes = prefs.getString("exclude_list", "");

        LinearLayout layout = new LinearLayout(requireContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 20, 50, 20);

        EditText etExcludes = new EditText(requireContext());
        etExcludes.setText(savedExcludes);
        etExcludes.setHint("com/gms/\nandroidx/");
        etExcludes.setMinLines(3);
        etExcludes.setGravity(android.view.Gravity.TOP);

        TextView tvExplanation = new TextView(requireContext());
        tvExplanation.setText("Set a list of paths that need to be excluded from the search range, one per line, for example:\ncom/gms/\nandroidx/\nAttention! The exclusion list only takes effect when the search path is empty or \"/\"!");
        tvExplanation.setTextSize(14);
        tvExplanation.setPadding(0, 20, 0, 0);

        layout.addView(etExcludes);
        layout.addView(tvExplanation);

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        builder.setTitle("Exclude list");
        builder.setView(layout);
        builder.setPositiveButton("OK", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(android.content.DialogInterface dialog, int which) {
                prefs.edit().putString("exclude_list", etExcludes.getText().toString()).apply();
            }
        });
        builder.setNegativeButton("CANCEL", null);
        builder.show();
    }

    /**
     * Configures and displays the search configuration dialog.
     * @param searchInResults If true, filters the existing results instead of searching all files.
     */
    private void showSearchDialog(boolean searchInResults) {
        View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_search_dex, null);
        AutoCompleteTextView etFind = dialogView.findViewById(R.id.et_find);
        EditText etPath = dialogView.findViewById(R.id.et_path);
        Spinner spinnerSearchType = dialogView.findViewById(R.id.spinner_search_type);
        CheckBox cbSearchSubfolders = dialogView.findViewById(R.id.cb_search_subfolders);
        CheckBox cbMatchCase = dialogView.findViewById(R.id.cb_match_case);
        CheckBox cbRegex = dialogView.findViewById(R.id.cb_regex);
        CheckBox cbExactlyMatch = dialogView.findViewById(R.id.cb_exactly_match);
        CheckBox cbHex = dialogView.findViewById(R.id.cb_hex);
        CheckBox cbUseExcludeList = dialogView.findViewById(R.id.cb_use_exclude_list);
        TextView tvExcludeList = dialogView.findViewById(R.id.tv_exclude_list);

        android.content.SharedPreferences prefs = requireContext().getSharedPreferences("search_prefs", android.content.Context.MODE_PRIVATE);
        lastUseExcludeList = prefs.getBoolean("use_exclude_list", false);

        etFind.setText(lastSearchQuery);
        if (searchInResults) {
            etPath.setText("");
            etPath.setHint("<Current search results>");
            etPath.setEnabled(false);
            tvExcludeList.setEnabled(false);
            cbUseExcludeList.setEnabled(false);
            cbSearchSubfolders.setVisibility(View.GONE);
        } else {
            etPath.setText(lastSearchPath);
            etPath.setEnabled(true);
            tvExcludeList.setEnabled(true);
            cbUseExcludeList.setEnabled(true);
            cbSearchSubfolders.setVisibility(View.VISIBLE);
            cbSearchSubfolders.setChecked(lastSearchSubfolders);
        }

        cbMatchCase.setChecked(lastMatchCase);
        cbRegex.setChecked(lastIsRegex);
        cbExactlyMatch.setChecked(lastExactlyMatch);
        cbHex.setChecked(lastIsHex);
        cbUseExcludeList.setChecked(lastUseExcludeList);

        tvExcludeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExcludeListDialog();
            }
        });

        String[] searchTypes = {"Smali", "Class name", "Field name", "Method name", "String", "Integer"};
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, searchTypes);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSearchType.setAdapter(typeAdapter);

        int typePos = 0;
        for (int i = 0; i < searchTypes.length; i++) {
            if (searchTypes[i].equals(lastSearchType)) {
                typePos = i;
                break;
            }
        }
        spinnerSearchType.setSelection(typePos);

        spinnerSearchType.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                String selected = searchTypes[position];
                if (!searchInResults) cbSearchSubfolders.setVisibility(View.VISIBLE);
                cbMatchCase.setVisibility(View.VISIBLE);
                cbRegex.setVisibility(View.VISIBLE);
                cbExactlyMatch.setVisibility(View.VISIBLE);
                cbHex.setVisibility(View.GONE);

                if (selected.equals("Smali")) {
                    cbExactlyMatch.setVisibility(View.GONE);
                } else if (selected.equals("Integer")) {
                    cbMatchCase.setVisibility(View.GONE);
                    cbRegex.setVisibility(View.GONE);
                    cbExactlyMatch.setVisibility(View.GONE);
                    cbHex.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
            }
        });

        AlertDialog dialog = new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Search")
                .setView(dialogView)
                .setPositiveButton("OK", null)
                .setNegativeButton("Cancel", null)
                .create();

        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = etFind.getText().toString().trim();
                String path = searchInResults ? "" : etPath.getText().toString();
                String type = spinnerSearchType.getSelectedItem().toString();
                boolean searchSubfolders = cbSearchSubfolders.isChecked();
                boolean matchCase = cbMatchCase.isChecked();
                boolean isRegex = cbRegex.isChecked();
                boolean exactlyMatch = cbExactlyMatch.isChecked();
                boolean isHex = cbHex.isChecked();
                boolean useExcludeList = cbUseExcludeList.isChecked();

                if (type.equals("Integer")) {
                    try {
                        String q = query;
                        if (isHex) {
                            if (q.startsWith("0x")) q = q.substring(2);
                            Long.parseLong(q, 16);
                        } else {
                            if (q.startsWith("0x")) {
                                throw new NumberFormatException();
                            }
                            Long.parseLong(q);
                        }
                    } catch (Exception e) {
                        modder.hub.dexeditor.utils.SketchwareUtil.showMessage(requireActivity(), "Value format error");
                        return;
                    }
                }

                lastSearchQuery = query;
                if (!searchInResults) {
                    lastSearchPath = path;
                    lastSearchSubfolders = searchSubfolders;
                    lastUseExcludeList = useExcludeList;
                    prefs.edit().putBoolean("use_exclude_list", useExcludeList).apply();
                }
                lastSearchType = type;
                lastMatchCase = matchCase;
                lastIsRegex = isRegex;
                lastExactlyMatch = exactlyMatch;
                lastIsHex = isHex;

                List<String> scopeClasses = null;
                if (searchInResults) {
                    scopeClasses = new ArrayList<>();
                    collectClassesRecursive(searchResults, scopeClasses);
                }

                searchResults.clear();
                currentQuery = null;
                adapter.refreshVisibleNodes();
                updateUIState();

                new SearchTask(SearchFragment.this, query, path, type, searchSubfolders, matchCase, isRegex, exactlyMatch, isHex, scopeClasses, useExcludeList).start();
                dialog.dismiss();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void showReplaceDialog() {
        View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_search_dex, null);
        AutoCompleteTextView etFind = dialogView.findViewById(R.id.et_find);
        EditText etReplaceWith = dialogView.findViewById(R.id.et_path);
        Spinner spinnerSearchType = dialogView.findViewById(R.id.spinner_search_type);
        CheckBox cbSearchSubfolders = dialogView.findViewById(R.id.cb_search_subfolders);
        CheckBox cbMatchCase = dialogView.findViewById(R.id.cb_match_case);
        CheckBox cbRegex = dialogView.findViewById(R.id.cb_regex);
        CheckBox cbExactlyMatch = dialogView.findViewById(R.id.cb_exactly_match);
        CheckBox cbHex = dialogView.findViewById(R.id.cb_hex);
        TextView pathTitleText = dialogView.findViewById(R.id.pathTitle);
        View layoutExcludeList = dialogView.findViewById(R.id.layout_exclude_list);

        pathTitleText.setText("Replace with");
        layoutExcludeList.setVisibility(View.GONE);
        cbSearchSubfolders.setVisibility(View.GONE);
        cbHex.setVisibility(View.GONE);

        etFind.setText(lastSearchQuery);
        etReplaceWith.setText(lastReplaceWith);
        cbMatchCase.setChecked(lastMatchCase);
        cbRegex.setChecked(lastIsRegex);
        cbExactlyMatch.setChecked(lastExactlyMatch);

        String[] replaceTypes = {"Smali", "String"};
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, replaceTypes);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSearchType.setAdapter(typeAdapter);

        spinnerSearchType.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                String selected = replaceTypes[position];
                cbExactlyMatch.setVisibility(selected.equals("String") ? View.VISIBLE : View.GONE);
                cbRegex.setVisibility(selected.equals("Smali") || selected.equals("String") ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
            }
        });

        // Setting up the replace dialog with options like regex and match case
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Replace")
                .setView(dialogView)
                .setPositiveButton("OK", new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(android.content.DialogInterface dialog, int which) {
                        String find = etFind.getText().toString();
                        String replace = etReplaceWith.getText().toString();
                        String type = spinnerSearchType.getSelectedItem().toString();
                        boolean matchCase = cbMatchCase.isChecked();
                        boolean isRegex = cbRegex.isChecked();
                        boolean exactlyMatch = cbExactlyMatch.isChecked();

                        lastSearchQuery = find;
                        lastReplaceWith = replace;
                        lastMatchCase = matchCase;
                        lastIsRegex = isRegex;
                        lastExactlyMatch = exactlyMatch;

                        List<String> scopeClasses;
                        scopeClasses = new ArrayList<>();
                        collectClassesRecursive(searchResults, scopeClasses);

                        new ReplaceTask(SearchFragment.this, find, replace, type, matchCase, isRegex, exactlyMatch, scopeClasses).start();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void collectClassesRecursive(List<TreeNode> nodes, List<String> classes) {
        for (TreeNode node : nodes) {
            if (!node.isDirectory() && !node.isSnippet()) {
                classes.add(node.getFullName());
            }
            collectClassesRecursive(node.getChildren(), classes);
        }
    }

    /**
     * ReplaceTask: Handles global string/smali replacements.
     * This is a sensitive operation as it involves re-assembling classes.
     * It still carries some bugs which I left open people around
     */
    private static class ReplaceTask {
        private final WeakReference<SearchFragment> fragmentRef;
        private final String findQuery;
        private final String replaceWith;
        private final String type;
        private final boolean matchCase;
        private final boolean isRegex;
        private final boolean exactlyMatch;
        private final List<String> scopeClasses;
        private final Pattern compiledPattern;
        private final List<String> errorClasses = Collections.synchronizedList(new ArrayList<>());
        private final AtomicInteger replacedCount = new AtomicInteger(0);
        private final AtomicInteger affectedClasses = new AtomicInteger(0);
        private final AtomicInteger processedCount = new AtomicInteger(0);
        private final com.android.tools.smali.baksmali.BaksmaliOptions baksmaliOptions = new com.android.tools.smali.baksmali.BaksmaliOptions();
        private final Map<String, String> openTabsContent = new HashMap<>();
        private final Map<String, String> replacedTabsContent = new ConcurrentHashMap<>();
        private final Handler mainHandler = new Handler(Looper.getMainLooper());
        private String firstErrorClass = null;
        private int firstErrorLine = -1;
        private int firstErrorColumn = -1;
        private volatile boolean isStopped = false;
        private AlertProgress progressDialog;

        ReplaceTask(SearchFragment fragment, String findQuery, String replaceWith, String type, boolean matchCase, boolean isRegex, boolean exactlyMatch, List<String> scopeClasses) {
            this.fragmentRef = new WeakReference<>(fragment);
            this.findQuery = findQuery;
            this.replaceWith = replaceWith;
            this.type = type;
            this.matchCase = matchCase;
            this.isRegex = isRegex;
            this.exactlyMatch = exactlyMatch;
            this.scopeClasses = scopeClasses;

            if (isRegex) {
                int flags = matchCase ? 0 : Pattern.CASE_INSENSITIVE;
                this.compiledPattern = Pattern.compile(findQuery, flags);
            } else {
                this.compiledPattern = null;
            }
        }

        /* This process is really critical though I made it possible, but I see it unable to replace if
           any classes are being opend by user before using the replace feature, mean if you search and explore some highligted
           results in a class then you thought that I have to replace the result your new value then it will only replace the explored class only.
           I tried my best to fix this, but it's not working.
         */
        void start() {
            SearchFragment fragment = fragmentRef.get();
            if (fragment == null) return;
            DexEditorActivity activity = (DexEditorActivity) fragment.getActivity();
            if (activity == null) return;

            progressDialog = new AlertProgress(activity);
            progressDialog.setTitle("Processing...");
            progressDialog.setCancelable(false);
            progressDialog.setOnCancelListener(new AlertProgress.OnCancelListener() {
                @Override
                public void onCancel() {
                    isStopped = true;
                }
            });
            progressDialog.show();

            // Collect open tabs content (must be on UI thread)
            for (int i = 0; i < DexEditorActivity.tabs.size(); i++) {
                DexEditorActivity.EditorTab tab = DexEditorActivity.tabs.get(i);
                if (tab.type == 0) { // Smali
                    EditorFragment editorFrag = activity.getFragmentAtIndex(i);
                    if (editorFrag != null && editorFrag.getEditor() != null) {
                        openTabsContent.put(tab.className, editorFrag.getEditor().getText().toString()); 
                    } else {
                        openTabsContent.put(tab.className, tab.content);
                    }
                }
            }

            int numThreads = Math.max(1, Runtime.getRuntime().availableProcessors());
            ExecutorService executor = Executors.newFixedThreadPool(numThreads);
            
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (DexEditorActivity.classTree == null) {
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                            }
                        });
                        return;
                    }

                    List<String> classesToProcess = new ArrayList<>();
                    if (scopeClasses != null) {
                        classesToProcess.addAll(scopeClasses);
                    } else {
                        for (ClassDef classDef : DexEditorActivity.classTree.classDefList) {
                            String fullName = classDef.getType();
                            classesToProcess.add(fullName.substring(1, fullName.length() - 1));
                        }
                    }

                    final int total = classesToProcess.size();
                    final int dexVersion = activity.dexVersion;

                    for (final String className : classesToProcess) {
                        if (isStopped) break;
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                if (isStopped) return;

                                try {
                                    String originalText = null;
                                    if (openTabsContent.containsKey(className)) {
                                        originalText = openTabsContent.get(className);
                                    } else {
                                        ClassDef classDef = DexEditorActivity.classTree.classMap.get(className);
                                        if (classDef != null) {
                                            originalText = generateSmali(classDef);
                                        }
                                    }

                                    if (originalText != null) {
                                        String modifiedText;
                                        int countInClass = 0;

                                        if (type.equals("String")) {
                                            String findPattern = exactlyMatch ? "\"" + Pattern.quote(findQuery) + "\"" : Pattern.quote(findQuery);
                                            String replacePattern = exactlyMatch ? "\"" + java.util.regex.Matcher.quoteReplacement(replaceWith) + "\"" : java.util.regex.Matcher.quoteReplacement(replaceWith);
                                            Pattern p = Pattern.compile(findPattern, matchCase ? 0 : Pattern.CASE_INSENSITIVE);
                                            java.util.regex.Matcher m = p.matcher(originalText);
                                            StringBuffer sb = new StringBuffer();
                                            while (m.find()) {
                                                countInClass++;
                                                m.appendReplacement(sb, replacePattern);
                                            }
                                            m.appendTail(sb);
                                            modifiedText = sb.toString();
                                        } else {
                                            if (isRegex) {
                                                Matcher m = compiledPattern.matcher(originalText);
                                                StringBuffer sb = new StringBuffer();
                                                while (m.find()) {
                                                    countInClass++;
                                                    m.appendReplacement(sb, Matcher.quoteReplacement(replaceWith));
                                                }
                                                m.appendTail(sb);
                                                modifiedText = sb.toString();
                                            } else {
                                                String search = findQuery;
                                                String replacement = replaceWith;
                                                if (matchCase) {
                                                    countInClass = countOccurrences(originalText, search);
                                                    modifiedText = originalText.replace(search, replacement);
                                                } else {
                                                    Pattern p = Pattern.compile(Pattern.quote(search), Pattern.CASE_INSENSITIVE);
                                                   Matcher m = p.matcher(originalText);
                                                    StringBuffer sb = new StringBuffer();
                                                    while (m.find()) {
                                                        countInClass++;
                                                        m.appendReplacement(sb, Matcher.quoteReplacement(replacement));
                                                    }
                                                    m.appendTail(sb);
                                                    modifiedText = sb.toString();
                                                }
                                            }
                                        }

                                        if (countInClass > 0) {
                                            try {
                                                ClassDef newDef = Smali.assemble(modifiedText, new SmaliOptions(), dexVersion);
                                                DexEditorActivity.classTree.saveClassDef(newDef);
                                            } catch (Exception e) {
                                                DexEditorActivity.classTree.saveSmali(className, modifiedText);
                                                String errorMsg = e.getMessage();
                                                errorClasses.add(className + " (Saved as raw): " + errorMsg);
                                                synchronized (ReplaceTask.this) {
                                                    if (firstErrorClass == null) {
                                                        firstErrorClass = className;
                                                        try {
                                                            Matcher matcher = null;
                                                            if (errorMsg != null) {
                                                                matcher = Pattern.compile("\\[(\\d+),(\\d+)]").matcher(errorMsg);
                                                            }
                                                            if (matcher != null && matcher.find()) {
                                                                firstErrorLine = Integer.parseInt(Objects.requireNonNull(matcher.group(1))) - 1;
                                                                firstErrorColumn = Integer.parseInt(Objects.requireNonNull(matcher.group(2))) - 1;
                                                            }
                                                        } catch (Exception ignored) {
                                                        }
                                                    }
                                                }
                                            }
                                            replacedCount.addAndGet(countInClass);
                                            affectedClasses.incrementAndGet();
                                            if (openTabsContent.containsKey(className)) {
                                                replacedTabsContent.put(className, modifiedText);
                                            }
                                        }
                                    }
                                } catch (Exception ignored) {
                                }

                                final int processed = processedCount.incrementAndGet();
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressDialog.setMax(total);
                                        progressDialog.setProgress(processed);
                                    }
                                });
                            }
                        });
                    }

                    executor.shutdown();
                    try {
                        executor.awaitTermination(1, TimeUnit.HOURS);
                    } catch (InterruptedException ignored) {
                    }

                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onPostExecute(activity);
                        }
                    });
                }
            }).start();
        }

        private int countOccurrences(String text, String find) {
            int count = 0;
            int index = 0;
            while ((index = text.indexOf(find, index)) != -1) {
                count++;
                index += find.length();
            }
            return count;
        }

        private String generateSmali(ClassDef classDef) throws Exception {
            if (DexEditorActivity.classTree != null) {
                return DexEditorActivity.classTree.getSmaliByType(classDef);
            }
            StringWriter stringWriter = new StringWriter();
            BaksmaliWriter baksmaliWriter = new BaksmaliWriter(stringWriter);
            new com.android.tools.smali.baksmali.Adaptors.ClassDefinition(baksmaliOptions, classDef).writeTo(baksmaliWriter);
            baksmaliWriter.close();
            return stringWriter.toString();
        }

        @SuppressLint("NotifyDataSetChanged")
        private void onPostExecute(DexEditorActivity activity) {
            if (progressDialog.isShowing()) progressDialog.dismiss();
            Notify_MT.Notify(activity, "Info", "Total replaced " + replacedCount.get() + "times in " + affectedClasses.get() + "classes." , "Close");
            if (!errorClasses.isEmpty()) {
                showErrorDialog(activity);
            }

            if (!replacedTabsContent.isEmpty()) {
                for (Map.Entry<String, String> entry : replacedTabsContent.entrySet()) {
                    String className = entry.getKey();
                    String newText = entry.getValue();

                    for (int i = 0; i < DexEditorActivity.tabs.size(); i++) {
                        DexEditorActivity.EditorTab tab = DexEditorActivity.tabs.get(i);
                        if (tab.className.equals(className) && tab.type == 0) {
                            tab.content = newText;
                            tab.isModified = false;
                            EditorFragment fragment = activity.getFragmentAtIndex(i);
                            if (fragment != null && fragment.getEditor() != null) {
                                fragment.getEditor().setText(newText);
                            }
                        }
                    }
                }
                if (activity.tabsAdapter != null) activity.tabsAdapter.notifyDataSetChanged();
            }

            activity.refreshExplorerPage(1);
            DexEditorActivity.isChanged = true;
        }

        private void showErrorDialog(DexEditorActivity activity) {
            StringBuilder sb = new StringBuilder("The following classes could not be assembled after replacement:\n\n");
            synchronized (errorClasses) {
                for (int i = 0; i < Math.min(errorClasses.size(), 20); i++) {
                    sb.append(errorClasses.get(i)).append("\n");
                }
                if (errorClasses.size() > 20) {
                    sb.append("\n... and ").append(errorClasses.size() - 20).append(" more.");
                }
            }

            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(activity)
                    .setTitle("Replacement Errors")
                    .setMessage(sb.toString())
                    .setNegativeButton("Close", null);

            if (firstErrorClass != null) {
                builder.setPositiveButton("Fix First", new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(android.content.DialogInterface d, int w) {
                        if (firstErrorLine != -1) {
                            activity.openClassAtLine(firstErrorClass, firstErrorLine, firstErrorColumn, null);
                        } else {
                            activity.openClass(firstErrorClass);
                        }
                    }
                });
            }
            builder.show();
        }
    }

    /**
     * SearchTask: A multithreaded task that scans the DEX files for the given query.
     * It handles different search modes and manages UI progress updates.
     * The search will stop when back press twice, and even it will stop when search result reach 250k+
     */
    private static class SearchTask {
        private final WeakReference<SearchFragment> fragmentRef;
        private final String query;
        private final String path;
        private final String type;
        private final boolean searchSubfolders;
        private final boolean matchCase;
        private final boolean isRegex;
        private final boolean exactlyMatch;
        private final List<String> scopeClasses;
        private final Pattern compiledPattern;
        private final long finalTargetValue;
        private final boolean finalIsNumberValid;
        private final String highlightQuery;
        private final AtomicInteger foundCount = new AtomicInteger(0);
        private final AtomicInteger processedCount = new AtomicInteger(0);
        private final List<String> excludeList = new ArrayList<>();
        private final Handler mainHandler = new Handler(Looper.getMainLooper());
        private final Map<String, String> openEditorsContent = new HashMap<>();
        private final BaksmaliOptions baksmaliOptions = new BaksmaliOptions();
        private AlertProgress progressDialog;
        private volatile boolean isStopped = false;
        private volatile boolean warningShown = false;
        private volatile boolean hasConfirmedLargeSearch = false;
        private long lastProgressUpdateTime = 0;
        private ExecutorService executor;

        SearchTask(SearchFragment fragment, String query, String path, String type, boolean searchSubfolders, boolean matchCase, boolean isRegex, boolean exactlyMatch, boolean isHex, List<String> scopeClasses, boolean useExcludeList) {
            this.fragmentRef = new WeakReference<>(fragment);
            this.query = query;
            this.path = path.startsWith("/") ? path : "/" + path;
            this.type = type;
            this.searchSubfolders = searchSubfolders;
            this.matchCase = matchCase;
            this.isRegex = isRegex;
            this.exactlyMatch = exactlyMatch;
            this.scopeClasses = scopeClasses;

            long targetValue = 0;
            boolean isNumberValid = false;
            String hq = query;
            if (type.equals("Integer")) {
                try {
                    String cleanQuery = query.trim();
                    if (isHex) {
                        if (cleanQuery.startsWith("0x")) cleanQuery = cleanQuery.substring(2);
                        targetValue = Long.parseLong(cleanQuery, 16);
                        hq = "0x" + cleanQuery.toLowerCase();
                    } else {
                        targetValue = Long.parseLong(cleanQuery);
                        if (targetValue >= Integer.MIN_VALUE && targetValue <= Integer.MAX_VALUE) {
                            hq = "0x" + Integer.toHexString((int) targetValue).toLowerCase();
                        } else {
                            hq = "0x" + Long.toHexString(targetValue).toLowerCase();
                        }
                    }
                    isNumberValid = true;
                } catch (Exception ignored) {
                }
            }
            this.finalTargetValue = targetValue;
            this.finalIsNumberValid = isNumberValid;
            this.highlightQuery = hq;

            SearchFragment frag = fragmentRef.get();
            if (frag != null && useExcludeList && (this.path.equals("/") || this.path.isEmpty())) {
                android.content.SharedPreferences prefs = frag.requireContext().getSharedPreferences("search_prefs", android.content.Context.MODE_PRIVATE);
                String savedExcludes = prefs.getString("exclude_list", "");
                if (!savedExcludes.isEmpty()) {
                    for (String s : savedExcludes.split("\n")) {
                        String trimmed = s.trim();
                        if (!trimmed.isEmpty()) excludeList.add(trimmed);
                    }
                }
            }

            if (isRegex) {
                int flags = matchCase ? 0 : Pattern.CASE_INSENSITIVE;
                Pattern p;
                try {
                    p = Pattern.compile(query, flags);
                } catch (Exception e) {
                    p = null;
                }
                this.compiledPattern = p;
            } else {
                this.compiledPattern = null;
            }
        }

        void start() {
            SearchFragment fragment = fragmentRef.get();
            if (fragment == null) return;
            DexEditorActivity activity = (DexEditorActivity) fragment.getActivity();
            if (activity == null) return;

            // Prepare the progress dialog - search can take time on large DEX files
            progressDialog = new AlertProgress(activity);
            progressDialog.setTitle("Searching...");
            progressDialog.setMessage("Found: 0");
            progressDialog.setCancelable(false);
            progressDialog.setOnCancelListener(new AlertProgress.OnCancelListener() {
                @Override
                public void onCancel() {
                    isStopped = true;
                    if (executor != null) executor.shutdownNow();
                }
            });
            progressDialog.show();

            // Cache the content of open editors to search unsaved changes too
            for (int i = 0; i < DexEditorActivity.tabs.size(); i++) {
                DexEditorActivity.EditorTab tab = DexEditorActivity.tabs.get(i);
                if (tab.type == 0) {
                    EditorFragment editorFrag = activity.getFragmentAtIndex(i);
                    if (editorFrag != null && editorFrag.getEditor() != null) {
                        openEditorsContent.put(tab.className, editorFrag.getEditor().getText().toString());
                    } else if (tab.isModified) {
                        openEditorsContent.put(tab.className, tab.content);
                    }
                }
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    final List<TreeNode> results = Collections.synchronizedList(new ArrayList<>());
                    if (DexEditorActivity.classTree == null) {
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                onPostExecute(results);
                            }
                        });
                        return;
                    }

                    // Building the list of classes to search based on the scope
                    List<ClassDef> classesToSearch = new ArrayList<>();
                    if (scopeClasses != null) {
                        for (String className : scopeClasses) {
                            synchronized (DexEditorActivity.classTree) {
                                ClassDef classDef = DexEditorActivity.classTree.classMap.get(className);
                                if (classDef != null) classesToSearch.add(classDef);
                            }
                        }
                    } else {
                        synchronized (DexEditorActivity.classTree) {
                            classesToSearch.addAll(DexEditorActivity.classTree.classMap.values());
                        }
                    }

                    int total = classesToSearch.size();
                    String tempFilterPath = path.isEmpty() ? "" : path.substring(1);
                    if (tempFilterPath.endsWith("/"))
                        tempFilterPath = tempFilterPath.substring(0, tempFilterPath.length() - 1);
                    final String filterPath = tempFilterPath;

                    int numThreads = Math.max(1, Runtime.getRuntime().availableProcessors() - 1);
                    executor = Executors.newFixedThreadPool(numThreads);
                    final int finalTotal = total;

                    for (final ClassDef classDef : classesToSearch) {
                        if (isStopped) break;
                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
                                if (isStopped || Thread.currentThread().isInterrupted()) return;
                                if (type.equals("Integer") && !finalIsNumberValid) {
                                    updateProgress(processedCount.incrementAndGet(), finalTotal);
                                    return;
                                }

                                if (foundCount.get() >= 250000) {
                                    stopSearchWithLimit(activity);
                                    return;
                                }

                                if (foundCount.get() >= 1000 && !hasConfirmedLargeSearch) {
                                    synchronized (SearchTask.this) {
                                        if (!hasConfirmedLargeSearch) {
                                            if (!warningShown) {
                                                warningShown = true;
                                                mainHandler.post(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        showWarningDialog();
                                                    }
                                                });
                                            }
                                            while (warningShown && !isStopped) {
                                                try {
                                                    SearchTask.this.wait();
                                                } catch (InterruptedException e) {
                                                    isStopped = true;
                                                }
                                            }
                                        }
                                    }
                                }
                                if (isStopped) return;

                                String fullType = classDef.getType();
                                String className = fullType.substring(1, fullType.length() - 1);

                                if (!excludeList.isEmpty()) {
                                    for (String excludePath : excludeList) {
                                        if (className.startsWith(excludePath)) {
                                            updateProgress(processedCount.incrementAndGet(), finalTotal);
                                            return;
                                        }
                                    }
                                }

                                if (scopeClasses == null && !filterPath.isEmpty()) {
                                    boolean inPath = false;
                                    if (searchSubfolders) {
                                        if (className.startsWith(filterPath)) inPath = true;
                                    } else {
                                        String parentPath = className.contains("/") ? className.substring(0, className.lastIndexOf('/')) : "";
                                        if (parentPath.equals(filterPath)) inPath = true;
                                    }
                                    if (!inPath) {
                                        updateProgress(processedCount.incrementAndGet(), finalTotal);
                                        return;
                                    }
                                }

                                if (type.equals("Class name")) {
                                    String clsNamePart = className.contains("/") ? className.substring(className.lastIndexOf('/') + 1) : className;
                                    boolean match = false;
                                    if (query.contains(".") || query.contains("/")) {
                                        if (checkMatch(className) || checkMatch(className.replace('/', '.')))
                                            match = true;
                                    } else {
                                        if (checkMatch(clsNamePart)) match = true;
                                    }
                                    if (match) {
                                        if (foundCount.incrementAndGet() >= 250000) {
                                            stopSearchWithLimit(activity);
                                            return;
                                        }
                                        results.add(new TreeNode(clsNamePart, className, 0, false));
                                    }
                                    updateProgress(processedCount.incrementAndGet(), finalTotal);
                                    return;
                                }

                                List<TreeNode> snippets = new ArrayList<>();
                                boolean match = false;
                                String smali = null;

                                switch (type) {
                                    case "Smali":
                                        try {
                                            String openContent = openEditorsContent.get(className);
                                            boolean hasPending = DexEditorActivity.classTree.getPendingSmaliMap().containsKey(className);

                                            if (openContent != null) {
                                                smali = openContent;
                                            } else if (hasPending) {
                                                smali = DexEditorActivity.classTree.getSmaliByType(classDef);
                                            } else {
                                                smali = generateSmali(classDef);
                                            }

                                            if (smali != null) {
                                                if (isRegex || query.contains("\n")) {
                                                    Pattern pattern = isRegex ? compiledPattern : Pattern.compile(Pattern.quote(query), matchCase ? 0 : Pattern.CASE_INSENSITIVE);
                                                    java.util.regex.Matcher matcher = pattern.matcher(smali);
                                                    while (matcher.find()) {
                                                        if (foundCount.incrementAndGet() >= 250000) {
                                                            stopSearchWithLimit(activity);
                                                            return;
                                                        }
                                                        int startPos = matcher.start();
                                                        int lineStart = smali.lastIndexOf('\n', startPos) + 1;
                                                        int lineEnd = smali.indexOf('\n', startPos);
                                                        if (lineEnd == -1) lineEnd = smali.length();
                                                        String lineText = smali.substring(lineStart, lineEnd);
                                                        int lineIdx = countLinesBefore(smali, lineStart);
                                                        TreeNode snippet = new TreeNode(lineText.trim(), className, 0, false);
                                                        snippet.setSnippet(true);
                                                        snippet.setLineNumber(lineIdx);
                                                        snippets.add(snippet);
                                                        match = true;
                                                    }
                                                } else {
                                                    int start = 0, lineIdx = 0, end;
                                                    while ((end = smali.indexOf('\n', start)) != -1) {
                                                        if (checkMatchInRange(smali, start, end)) {
                                                            if (foundCount.incrementAndGet() >= 250000) {
                                                                stopSearchWithLimit(activity);
                                                                return;
                                                            }
                                                            String lineText = smali.substring(start, end);
                                                            TreeNode snippet = new TreeNode(lineText.trim(), className, 0, false);
                                                            snippet.setSnippet(true);
                                                            snippet.setLineNumber(lineIdx);
                                                            snippets.add(snippet);
                                                            match = true;
                                                        }
                                                        start = end + 1;
                                                        lineIdx++;
                                                    }
                                                    if (start < smali.length() && checkMatchInRange(smali, start, smali.length())) {
                                                        if (foundCount.incrementAndGet() >= 250000) {
                                                            stopSearchWithLimit(activity);
                                                            return;
                                                        }
                                                        String lineText = smali.substring(start);
                                                        TreeNode snippet = new TreeNode(lineText.trim(), className, 0, false);
                                                        snippet.setSnippet(true);
                                                        snippet.setLineNumber(lineIdx);
                                                        snippets.add(snippet);
                                                        match = true;
                                                    }
                                                }
                                            }
                                        } catch (Exception ignored) {
                                        }
                                        break;
                                    case "Field name":
                                        for (com.android.tools.smali.dexlib2.iface.Field field : classDef.getFields()) {
                                            if (checkMatch(field.getName())) {
                                                if (foundCount.incrementAndGet() >= 250000) {
                                                    stopSearchWithLimit(activity);
                                                    return;
                                                }
                                                if (smali == null)
                                                    smali = generateSmaliSafe(classDef);
                                                String snippetText = ".field " + AccessFlags.formatAccessFlagsForField(field.getAccessFlags()) + " " + field.getName() + ":" + field.getType();
                                                int lineIdx = findLineOfText(smali, field.getName());
                                                TreeNode snippet = new TreeNode(snippetText, className, 0, false);
                                                snippet.setSnippet(true);
                                                snippet.setLineNumber(lineIdx != -1 ? lineIdx : 0);
                                                snippets.add(snippet);
                                                match = true;
                                            }
                                        }
                                        break;
                                    case "Method name":
                                        for (com.android.tools.smali.dexlib2.iface.Method method : classDef.getMethods()) {
                                            if (checkMatch(method.getName())) {
                                                if (foundCount.incrementAndGet() >= 250000) {
                                                    stopSearchWithLimit(activity);
                                                    return;
                                                }
                                                if (smali == null)
                                                    smali = generateSmaliSafe(classDef);
                                                StringBuilder desc = new StringBuilder("(");
                                                for (com.android.tools.smali.dexlib2.iface.MethodParameter param : method.getParameters())
                                                    desc.append(param.getType());
                                                desc.append(")").append(method.getReturnType());
                                                String snippetText = ".method " + AccessFlags.formatAccessFlagsForMethod(method.getAccessFlags()) + " " + method.getName() + desc;
                                                int lineIdx = findLineOfText(smali, ".method " + AccessFlags.formatAccessFlagsForMethod(method.getAccessFlags()) + " " + method.getName());
                                                TreeNode snippet = new TreeNode(snippetText, className, 0, false);
                                                snippet.setSnippet(true);
                                                snippet.setLineNumber(lineIdx != -1 ? lineIdx : 0);
                                                snippets.add(snippet);
                                                match = true;
                                            }
                                        }
                                        break;
                                    case "String":
                                        String openContentStr = openEditorsContent.get(className);
                                        boolean hasPendingStr = DexEditorActivity.classTree.getPendingSmaliMap().containsKey(className);
                                        if (openContentStr != null || hasPendingStr) {
                                            try {
                                                smali = openContentStr != null ? openContentStr : DexEditorActivity.classTree.getSmaliByType(classDef);
                                                int start = 0, end;
                                                while ((end = smali.indexOf('\n', start)) != -1) {
                                                    String lineText = smali.substring(start, end);
                                                    if (lineText.contains("\"") && checkMatch(lineText)) {
                                                        if (foundCount.incrementAndGet() >= 250000) {
                                                            stopSearchWithLimit(activity);
                                                            return;
                                                        }
                                                    }
                                                }
                                            } catch (Exception ignored) {
                                            }
                                        } else {
                                            for (com.android.tools.smali.dexlib2.iface.Method method : classDef.getMethods()) {
                                                com.android.tools.smali.dexlib2.iface.MethodImplementation impl = method.getImplementation();
                                                if (impl != null) {
                                                    for (com.android.tools.smali.dexlib2.iface.instruction.Instruction inst : impl.getInstructions()) {
                                                        if (inst instanceof com.android.tools.smali.dexlib2.iface.instruction.ReferenceInstruction) {
                                                            com.android.tools.smali.dexlib2.iface.reference.Reference ref = ((com.android.tools.smali.dexlib2.iface.instruction.ReferenceInstruction) inst).getReference();
                                                            if (ref instanceof com.android.tools.smali.dexlib2.iface.reference.StringReference) {
                                                                String str = ((com.android.tools.smali.dexlib2.iface.reference.StringReference) ref).getString();
                                                                if (checkMatch(str)) {
                                                                    if (foundCount.incrementAndGet() >= 250000) {
                                                                        stopSearchWithLimit(activity);
                                                                        return;
                                                                    }
                                                                    if (smali == null)
                                                                        smali = generateSmaliSafe(classDef);
                                                                    String snippetText = inst.getOpcode().name + " ..., \"" + str + "\"";
                                                                    int lineIdx = findLineOfText(smali, "\"" + str + "\"");
                                                                    TreeNode snippet = new TreeNode(snippetText, className, 0, false);
                                                                    snippet.setSnippet(true);
                                                                    snippet.setLineNumber(lineIdx != -1 ? lineIdx : 0);
                                                                    snippets.add(snippet);
                                                                    match = true;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            for (com.android.tools.smali.dexlib2.iface.Field field : classDef.getFields()) {
                                                com.android.tools.smali.dexlib2.iface.value.EncodedValue initialValue = field.getInitialValue();
                                                if (initialValue instanceof com.android.tools.smali.dexlib2.iface.value.StringEncodedValue) {
                                                    String str = ((com.android.tools.smali.dexlib2.iface.value.StringEncodedValue) initialValue).getValue();
                                                    if (checkMatch(str)) {
                                                        if (foundCount.incrementAndGet() >= 250000) {
                                                            stopSearchWithLimit(activity);
                                                            return;
                                                        }
                                                        if (smali == null)
                                                            smali = generateSmaliSafe(classDef);
                                                        String snippetText = ".field ... " + field.getName() + " = \"" + str + "\"";
                                                        int lineIdx = findLineOfText(smali, field.getName());
                                                        TreeNode snippet = new TreeNode(snippetText, className, 0, false);
                                                        snippet.setSnippet(true);
                                                        snippet.setLineNumber(lineIdx != -1 ? lineIdx : 0);
                                                        snippets.add(snippet);
                                                        match = true;
                                                    }
                                                }
                                            }
                                            if (searchInAnnotations(classDef.getAnnotations(), className, snippets))
                                                match = true;
                                        }
                                        break;
                                    case "Integer":
                                        boolean hasMatched = false;
                                        for (com.android.tools.smali.dexlib2.iface.Method method : classDef.getMethods()) {
                                            com.android.tools.smali.dexlib2.iface.MethodImplementation impl = method.getImplementation();
                                            if (impl != null) {
                                                for (com.android.tools.smali.dexlib2.iface.instruction.Instruction inst : impl.getInstructions()) {
                                                    if (inst instanceof com.android.tools.smali.dexlib2.iface.instruction.NarrowLiteralInstruction) {
                                                        if (((com.android.tools.smali.dexlib2.iface.instruction.NarrowLiteralInstruction) inst).getNarrowLiteral() == (int) finalTargetValue) {
                                                            hasMatched = true;
                                                            break;
                                                        }
                                                    } else if (inst instanceof com.android.tools.smali.dexlib2.iface.instruction.WideLiteralInstruction) {
                                                        if (((com.android.tools.smali.dexlib2.iface.instruction.WideLiteralInstruction) inst).getWideLiteral() == finalTargetValue) {
                                                            hasMatched = true;
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                            if (hasMatched) break;
                                        }
                                        if (!hasMatched) {
                                            for (com.android.tools.smali.dexlib2.iface.Field field : classDef.getFields()) {
                                                com.android.tools.smali.dexlib2.iface.value.EncodedValue initialValue = field.getInitialValue();
                                                if (initialValue instanceof com.android.tools.smali.dexlib2.iface.value.IntEncodedValue) {
                                                    if (((com.android.tools.smali.dexlib2.iface.value.IntEncodedValue) initialValue).getValue() == (int) finalTargetValue) {
                                                        hasMatched = true;
                                                        break;
                                                    }
                                                } else if (initialValue instanceof com.android.tools.smali.dexlib2.iface.value.LongEncodedValue) {
                                                    if (((com.android.tools.smali.dexlib2.iface.value.LongEncodedValue) initialValue).getValue() == finalTargetValue) {
                                                        hasMatched = true;
                                                        break;
                                                    }
                                                }
                                            }
                                        }

                                        if (hasMatched) {
                                            try {
                                                smali = generateSmali(classDef);
                                                String[] lines = smali.split("\n");
                                                String pattern = highlightQuery;
                                                for (int i = 0; i < lines.length; i++) {
                                                    String line = lines[i];
                                                    if (line.toLowerCase().contains(pattern)) {
                                                        int idx = line.toLowerCase().indexOf(pattern);
                                                        char next = (idx + pattern.length() < line.length()) ? line.charAt(idx + pattern.length()) : ' ';
                                                        if (Character.isLetterOrDigit(next))
                                                            continue;
                                                        if (foundCount.incrementAndGet() >= 250000) {
                                                            stopSearchWithLimit(activity);
                                                            return;
                                                        }
                                                        TreeNode snippet = new TreeNode(line.trim(), className, 0, false);
                                                        snippet.setSnippet(true);
                                                        snippet.setLineNumber(i);
                                                        snippets.add(snippet);
                                                        match = true;
                                                    }
                                                }
                                            } catch (Exception ignored) {
                                            }
                                        }
                                        break;
                                }

                                if (match) {
                                    TreeNode classNode = new TreeNode(className.substring(className.lastIndexOf('/') + 1), className, 0, false);
                                    if (!snippets.isEmpty()) {
                                        classNode.setChildren(snippets);
                                        classNode.setExpanded(true);
                                    }
                                    results.add(classNode);
                                }
                                updateProgress(processedCount.incrementAndGet(), finalTotal);
                            }
                        });
                    }

                    executor.shutdown();
                    try {
                        executor.awaitTermination(1, TimeUnit.HOURS);
                    } catch (InterruptedException ignored) {
                    }

                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onPostExecute(results);
                        }
                    });
                }
            }).start();
        }

        private void stopSearchWithLimit(final DexEditorActivity activity) {
            if (!isStopped) {
                isStopped = true;
                if (executor != null) executor.shutdownNow();
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();
                        SketchwareUtil.showMessage(activity, "Search cancelled: Too many results (> 250,000)");
                    }
                });
            }
        }

        private void updateProgress(int processed, int total) {
            long now = System.currentTimeMillis();
            if (now - lastProgressUpdateTime > 100 || processed == total || isStopped) {
                lastProgressUpdateTime = now;
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.setMax(total);
                            progressDialog.setProgress(processed);
                            progressDialog.setMessage("Found: " + foundCount.get());
                        }
                    }
                });
            }
        }

        // match the highlited region
        private boolean checkMatchInRange(String text, int start, int end) {
            if (isRegex) {
                if (compiledPattern == null) return false;
                return compiledPattern.matcher(text.substring(start, end)).find();
            }
            if (matchCase) {
                if (exactlyMatch)
                    return (end - start == query.length()) && text.regionMatches(false, start, query, 0, query.length());
                for (int i = start; i <= end - query.length(); i++)
                    if (text.regionMatches(false, i, query, 0, query.length())) return true;
                return false;
            } else {
                if (exactlyMatch)
                    return (end - start == query.length()) && text.regionMatches(true, start, query, 0, query.length());
                for (int i = start; i <= end - query.length(); i++)
                    if (text.regionMatches(true, i, query, 0, query.length())) return true;
                return false;
            }
        }

        private boolean checkMatch(String text) {
            if (isRegex) {
                if (compiledPattern == null) return false;
                return compiledPattern.matcher(text).find();
            }
            if (matchCase) {
                if (exactlyMatch) return text.equals(query);
                return text.contains(query);
            } else {
                if (exactlyMatch) return text.equalsIgnoreCase(query);
                return containsIgnoreCase(text, query);
            }
        }

        private String generateSmali(ClassDef classDef) throws Exception {
            if (DexEditorActivity.classTree != null) {
                return DexEditorActivity.classTree.getSmaliByType(classDef);
            }
            StringWriter stringWriter = new StringWriter();
            com.android.tools.smali.baksmali.formatter.BaksmaliWriter baksmaliWriter = new com.android.tools.smali.baksmali.formatter.BaksmaliWriter(stringWriter);
            new com.android.tools.smali.baksmali.Adaptors.ClassDefinition(baksmaliOptions, classDef).writeTo(baksmaliWriter);
            baksmaliWriter.close();
            return stringWriter.toString();
        }

        private String generateSmaliSafe(ClassDef classDef) {
            try {
                return generateSmali(classDef);
            } catch (Exception e) {
                return "";
            }
        }

        private int countLinesBefore(String text, int index) {
            int count = 0;
            for (int i = 0; i < index && i < text.length(); i++)
                if (text.charAt(i) == '\n') count++;
            return count;
        }

        // get the line of the target text
        private int findLineOfText(String smali, String searchText) {
            if (smali == null || searchText == null || smali.isEmpty()) return -1;
            int pos = matchCase ? smali.indexOf(searchText) : smali.toLowerCase().indexOf(searchText.toLowerCase());
            if (pos == -1) return -1;
            return countLinesBefore(smali, pos);
        }

        private boolean searchInAnnotations(java.util.Set<? extends com.android.tools.smali.dexlib2.iface.Annotation> annotations, String className, List<TreeNode> snippets) {
            if (annotations == null) return false;
            boolean matched = false;
            for (com.android.tools.smali.dexlib2.iface.Annotation annotation : annotations) {
                for (com.android.tools.smali.dexlib2.iface.AnnotationElement element : annotation.getElements()) {
                    if (collectAnnotationMatches(element.getName(), element.getValue(), className, snippets))
                        matched = true;
                }
            }
            return matched;
        }

        private boolean collectAnnotationMatches(String name, com.android.tools.smali.dexlib2.iface.value.EncodedValue value, String className, List<TreeNode> snippets) {
            boolean matched = false;
            if (value instanceof com.android.tools.smali.dexlib2.iface.value.StringEncodedValue) {
                String str = ((com.android.tools.smali.dexlib2.iface.value.StringEncodedValue) value).getValue();
                if (checkMatch(str)) {
                    if (foundCount.incrementAndGet() >= 250000) return true;
                    TreeNode snippet = new TreeNode("Annotation: " + name + " = \"" + str + "\"", className, 0, false);
                    snippet.setSnippet(true);
                    snippet.setLineNumber(1);
                    snippets.add(snippet);
                    matched = true;
                }
            } else if (value instanceof com.android.tools.smali.dexlib2.iface.value.AnnotationEncodedValue) {
                for (com.android.tools.smali.dexlib2.iface.AnnotationElement element : ((com.android.tools.smali.dexlib2.iface.value.AnnotationEncodedValue) value).getElements()) {
                    if (collectAnnotationMatches(element.getName(), element.getValue(), className, snippets))
                        matched = true;
                }
            } else if (value instanceof com.android.tools.smali.dexlib2.iface.value.ArrayEncodedValue) {
                for (com.android.tools.smali.dexlib2.iface.value.EncodedValue subValue : ((com.android.tools.smali.dexlib2.iface.value.ArrayEncodedValue) value).getValue()) {
                    if (collectAnnotationMatches(name, subValue, className, snippets))
                        matched = true;
                }
            }
            return matched;
        }

        private boolean containsIgnoreCase(String str, String searchStr) {
            if (str == null || searchStr == null) return false;
            final int length = searchStr.length();
            if (length == 0) return true;
            for (int i = str.length() - length; i >= 0; i--)
                if (str.regionMatches(true, i, searchStr, 0, length)) return true;
            return false;
        }

        private void showWarningDialog() {
            SearchFragment fragment = fragmentRef.get();
            if (fragment == null) return;
            new MaterialAlertDialogBuilder(fragment.requireContext())
                    .setTitle("Warning")
                    .setMessage("1000+ results found so far. Are you sure you wish to continue?")
                    .setPositiveButton("CONTINUE", new android.content.DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(android.content.DialogInterface dialog, int which) {
                            synchronized (SearchTask.this) {
                                hasConfirmedLargeSearch = true;
                                warningShown = false;
                                SearchTask.this.notifyAll();
                            }
                        }
                    })
                    .setNegativeButton("STOP", new android.content.DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(android.content.DialogInterface dialog, int which) {
                            isStopped = true;
                            synchronized (SearchTask.this) {
                                warningShown = false;
                                SearchTask.this.notifyAll();
                            }
                        }
                    })
                    .setCancelable(false)
                    .show();
        }

        private void onPostExecute(final List<TreeNode> results) {
            if (progressDialog.isShowing()) progressDialog.dismiss();
            SearchFragment fragment = fragmentRef.get();
            if (fragment == null) return;
            fragment.currentQuery = query;
            final DexEditorActivity activity = (DexEditorActivity) fragment.getActivity();
            if (activity != null) {
                activity.searchNodes.clear();
                results.sort(new Comparator<TreeNode>() {
                    @Override
                    public int compare(TreeNode n1, TreeNode n2) {
                        return n1.getFullName().compareTo(n2.getFullName());
                    }
                });
                List<TreeNode> tree = buildTreeStructure(results);
                tree.sort(new Comparator<TreeNode>() {
                    @Override
                    public int compare(TreeNode n1, TreeNode n2) {
                        if (n1.isDirectory() != n2.isDirectory()) return n1.isDirectory() ? -1 : 1;
                        return n1.getName().compareTo(n2.getName());
                    }
                });
                activity.searchNodes.addAll(tree);
                fragment.updateSearchInfoBar();
                fragment.updateUIState();
                if (fragment.adapter != null) {
                    fragment.adapter.setSearchList(true, highlightQuery);
                    fragment.adapter.refreshVisibleNodes();
                }
            }
        }

        /**
         * Rebuilds the search result list into a tree-like package structure
         * to make it easier for users to navigate results.
         */
        private List<TreeNode> buildTreeStructure(List<TreeNode> classNodes) {
            Map<String, TreeNode> packageMap = new HashMap<>();
            List<TreeNode> roots = new ArrayList<>();
            for (TreeNode classNode : classNodes) {
                String fullName = classNode.getFullName();
                String pkgName = fullName.contains("/") ? fullName.substring(0, fullName.lastIndexOf('/')) : "";
                if (pkgName.isEmpty()) {
                    roots.add(classNode);
                } else {
                    TreeNode pkgNode = packageMap.get(pkgName);
                    if (pkgNode == null) {
                        pkgNode = new TreeNode(pkgName.replace('/', '.'), pkgName, 0, true);
                        pkgNode.setExpanded(true);
                        packageMap.put(pkgName, pkgNode);
                        roots.add(pkgNode);
                    }
                    pkgNode.addChild(classNode);
                }
            }
            for (TreeNode root : roots) sortChildrenRecursive(root);
            return roots;
        }

        private void sortChildrenRecursive(TreeNode node) {
            if (node.isDirectory()) {
                node.getChildren().sort(new Comparator<TreeNode>() {
                    @Override
                    public int compare(TreeNode n1, TreeNode n2) {
                        if (n1.isDirectory() != n2.isDirectory()) return n1.isDirectory() ? -1 : 1;
                        return n1.getName().compareTo(n2.getName());
                    }
                });
                for (TreeNode child : node.getChildren()) sortChildrenRecursive(child);
            }
        }
    }
}
