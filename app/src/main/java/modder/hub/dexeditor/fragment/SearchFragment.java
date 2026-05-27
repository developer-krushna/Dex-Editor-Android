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
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
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
import androidx.recyclerview.widget.ConcatAdapter;
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
import java.util.Set;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.android.tools.smali.dexlib2.iface.*;
import com.android.tools.smali.dexlib2.iface.instruction.*;
import com.android.tools.smali.dexlib2.iface.reference.*;
import com.android.tools.smali.dexlib2.iface.value.*;
import com.android.tools.smali.dexlib2.dexbacked.*;

import modder.hub.dexeditor.R;
import modder.hub.dexeditor.activity.DexEditorActivity;
import modder.hub.dexeditor.adapter.TreeAdapter;
import modder.hub.dexeditor.model.TreeNode;
import modder.hub.dexeditor.utils.Notify_MT;
import modder.hub.dexeditor.utils.SketchwareUtil;
import modder.hub.dexeditor.utils.TreeHelper;
import modder.hub.dexeditor.utils.UIHelper;
import modder.hub.dexeditor.views.AlertProgress;
import modder.hub.dexeditor.views.FastScrollerRecyclerView;

// Author : @developer-krushna
// Got some LOGICS from AI but thought , idea other resources copied from mt manager interface
// Thanks @Bin

/**
 * SearchFragment: Handles all search and replace operations in the DEX editor.
 * It supports Smali code, class names, methods, fields, and even raw integers/hex.
 */
public class SearchFragment extends Fragment {

    private LinearLayout btnSearchInResults, btnReplaceInResults, btnClearResults, layoutSearchInfo;
    private TextView tvSearchInfo;
    private List<TreeNode> searchResults = new ArrayList<>();
    private TreeAdapter adapter;
    private String currentQuery;

    private String lastSearchQuery = "", lastSearchPath = "/", lastReplaceWith = "", lastSearchType = "Smali";
    private boolean lastSearchSubfolders = true, lastMatchCase = false, lastIsRegex = false, lastExactlyMatch = false, lastIsHex = false, lastUseExcludeList = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        FastScrollerRecyclerView recyclerView = view.findViewById(R.id.search_results_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);

        View headerView = inflater.inflate(R.layout.search_header, recyclerView, false);
        LinearLayout btnNewSearch = headerView.findViewById(R.id.btn_new_search);
        btnSearchInResults = headerView.findViewById(R.id.btn_search_in_results);
        btnReplaceInResults = headerView.findViewById(R.id.btn_replace_in_results);
        btnClearResults = headerView.findViewById(R.id.btn_clear_results);
        layoutSearchInfo = headerView.findViewById(R.id.layout_search_info);
        tvSearchInfo = headerView.findViewById(R.id.tv_search_results_info);
        ImageView treeOptionsButton = headerView.findViewById(R.id.btn_treeview_options);

        DexEditorActivity activity = (DexEditorActivity) getActivity();
        if (activity != null) {
            searchResults = activity.searchNodes;
            adapter = new TreeAdapter(getContext(), searchResults, new TreeAdapter.OnNodeClickListener() {
                @Override
                public void onNodeClick(TreeNode node) {
                    if (node.isSnippet()) {
                        activity.openClassAtLine(node.getFullName(), node.getLineNumber(), currentQuery);
                    } else if (!node.isDirectory()) {
                        activity.openClass(node.getFullName());
                    }
                }

                @Override
                public void onNodeDeleted(TreeNode node) {
                    if (node.isSnippet()) {
                        TreeNode parent = node.getParent();
                        if (parent != null && !parent.isDirectory()) {
                            removeNodeFromSearchResults(parent);
                        } else {
                            removeNodeFromSearchResults(node);
                        }
                    } else {
                        removeNodeFromSearchResults(node);
                    }
                }

                @Override public void onSelectionChanged(int count) {}
                @Override public void onLocate(TreeNode node) {
                    activity.locateClass(node.getFullName());
                }
                @Override public void onCopyName(TreeNode node) {
                    UIHelper.copyToClipboard(requireContext(), node.getName());
                }
            }, false);
            adapter.setSearchList(true, currentQuery);
            recyclerView.setAdapter(new ConcatAdapter(new HeaderViewAdapter(headerView), adapter));
        }

        btnNewSearch.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                checkUnsavedAndShowWarning(new Runnable() {
                    @Override public void run() {
                        showSearchDialog(false);
                    }
                });
            }
        });
        btnSearchInResults.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                showSearchDialog(true);
            }
        });
        btnReplaceInResults.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                showReplaceDialog();
            }
        });
        btnClearResults.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                clearResults();
            }
        });
        treeOptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                showTreeOptionsMenu(v);
            }
        });

        updateUIState();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        DexEditorActivity activity = (DexEditorActivity) getActivity();
        if (activity != null && activity.pendingSearchPath != null) {
            String path = activity.pendingSearchPath;
            activity.pendingSearchPath = null;
            showSearchDialogWithPath(path);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refreshUI() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
            updateSearchInfoBar();
        }
    }

    public void showSearchDialogWithPath(String path) {
        showSearchDialog(false, path);
    }

    private static class HeaderViewAdapter extends RecyclerView.Adapter<HeaderViewAdapter.ViewHolder> {
        private final View view;
        HeaderViewAdapter(View view) {
            this.view = view;
        }
        @NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(view);
        }
        @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {}
        @Override public int getItemCount() {
            return 1;
        }
        static class ViewHolder extends RecyclerView.ViewHolder {
            ViewHolder(View view) {
                super(view);
            }
        }
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

    private void showTreeOptionsMenu(View v) {
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
                        TreeHelper.collapseAll(searchResults);
                        adapter.refreshVisibleNodes();
                        return true;
                    case 2:
                        TreeHelper.expandAll(searchResults);
                        adapter.refreshVisibleNodes();
                        return true;
                    case 3:
                        TreeHelper.onlyExpandPackages(searchResults);
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

    private void copyClassNames() {
        List<String> names = new ArrayList<>();
        collectClassFullNames(searchResults, names);
        StringBuilder sb = new StringBuilder();
        for (String name : names) sb.append(name.replace('/', '.')).append("\n");
        if (sb.length() > 0) UIHelper.copyToClipboard(requireContext(), sb.toString().trim());
    }

    private void collectClassFullNames(List<TreeNode> nodes, List<String> out) {
        for (TreeNode node : nodes) {
            if (!node.isDirectory() && !node.isSnippet()) out.add(node.getFullName());
            collectClassFullNames(node.getChildren(), out);
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

    // count total serach result
    // snippets are basically the tota;l number of highligted region exluding the folder
    private int countTotalSnippets(List<TreeNode> nodes) {
        int count = 0;
        for (TreeNode node : nodes) {
            if (node.isSnippet() || (!node.isDirectory() && node.getChildren().isEmpty())) count++;
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
        showSearchDialog(searchInResults, null);
    }

    private void showSearchDialog(boolean searchInResults, String initialPath) {
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
            etPath.setText(initialPath != null ? initialPath : lastSearchPath);
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
                String query = etFind.getText().toString().trim(), type = spinnerSearchType.getSelectedItem().toString(), path = searchInResults ? "" : etPath.getText().toString();
                boolean isHex = cbHex.isChecked(), useExcludeList = cbUseExcludeList.isChecked();
                if (type.equals("Integer")) {
                    try {
                        String q = query;
                        if (isHex) { if (q.startsWith("0x")) q = q.substring(2); Long.parseLong(q, 16); }
                        else { if (q.startsWith("0x")) throw new NumberFormatException(); Long.parseLong(q); }
                    } catch (Exception e) { SketchwareUtil.showMessage(requireActivity(), "Value format error"); return; }
                }
                lastSearchQuery = query; lastSearchType = type;
                if (!searchInResults) { lastSearchPath = path; lastSearchSubfolders = cbSearchSubfolders.isChecked(); lastUseExcludeList = useExcludeList; prefs.edit().putBoolean("use_exclude_list", useExcludeList).apply(); }
                lastMatchCase = cbMatchCase.isChecked(); lastIsRegex = cbRegex.isChecked(); lastExactlyMatch = cbExactlyMatch.isChecked(); lastIsHex = isHex;
                List<String> scopeClasses = null;
                if (searchInResults) { scopeClasses = new ArrayList<>(); collectClassFullNames(searchResults, scopeClasses); }
                searchResults.clear(); currentQuery = null; adapter.refreshVisibleNodes(); updateUIState();
                new SearchTask(SearchFragment.this, query, path, type, lastSearchSubfolders, lastMatchCase, lastIsRegex, lastExactlyMatch, isHex, scopeClasses, useExcludeList).start();
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
                        String find = etFind.getText().toString(), replace = etReplaceWith.getText().toString(), type = spinnerSearchType.getSelectedItem().toString();
                        lastSearchQuery = find; lastReplaceWith = replace; lastMatchCase = cbMatchCase.isChecked(); lastIsRegex = cbRegex.isChecked(); lastExactlyMatch = cbExactlyMatch.isChecked();
                        List<String> scopeClasses = new ArrayList<>();
                        collectClassFullNames(searchResults, scopeClasses);
                        new ReplaceTask(SearchFragment.this, find, replace, type, lastMatchCase, lastIsRegex, lastExactlyMatch, scopeClasses).start();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
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
        private final BaksmaliOptions baksmaliOptions = new BaksmaliOptions();
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
            if (DexEditorActivity.classTree != null) return DexEditorActivity.classTree.getSmaliByType(classDef);
            StringWriter sw = new StringWriter();
            BaksmaliWriter bw = new BaksmaliWriter(sw);
            new com.android.tools.smali.baksmali.Adaptors.ClassDefinition(baksmaliOptions, classDef).writeTo(bw);
            bw.close();
            return sw.toString();
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

        // there is some problem with replacement , i have to review this part next time but serach metod you should try
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
     *
     * ENHANCED FOR ULTRA SPEED (MT Manager Style):
     * 1. DEX Pool Filtering: Skips classes instantly if query isn't in the DEX string pool.
     * 2. Object Reuse: Uses ThreadLocal for BaksmaliOptions and String buffers.
     * 3. Efficient Matching: Case-insensitive search without new string allocations.
     */
    private static class SearchTask {
        private static final ThreadLocal<BaksmaliOptions> OPTIONS_THREAD_LOCAL = new ThreadLocal<BaksmaliOptions>() {
            @Override protected BaksmaliOptions initialValue() { return new BaksmaliOptions(); }
        };
        private static final ThreadLocal<StringBuilder> BUFFER_THREAD_LOCAL = new ThreadLocal<StringBuilder>() {
            @Override protected StringBuilder initialValue() { return new StringBuilder(64 * 1024); }
        };

        private final WeakReference<SearchFragment> fragmentRef;
        private final String query, path, type, highlightQuery;
        private final boolean searchSubfolders, matchCase, isRegex, exactlyMatch, finalIsNumberValid;
        private final List<String> scopeClasses, excludeList = new ArrayList<>();
        private final Pattern compiledPattern;
        private final long finalTargetValue;
        private final AtomicInteger foundCount = new AtomicInteger(0), processedCount = new AtomicInteger(0);
        private final Handler mainHandler = new Handler(Looper.getMainLooper());
        private final Map<String, String> openEditorsContent = new HashMap<>();
        private final Map<Integer, Boolean> dexMatchCache = new ConcurrentHashMap<>();
        private final Set<String> smaliKeywords = new java.util.HashSet<>();
        private AlertProgress progressDialog;
        private volatile boolean isStopped = false, warningShown = false, hasConfirmedLargeSearch = false;
        private final AtomicBoolean isFinalized = new AtomicBoolean(false);
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

            initSmaliKeywords();

            long targetValue = 0;
            boolean isNumberValid = false;
            String hq = query;
            if (type.equals("Integer")) {
                try {
                    String q = query.trim();
                    if (isHex) {
                        if (q.startsWith("0x")) q = q.substring(2);
                        targetValue = Long.parseLong(q, 16);
                        hq = "0x" + q.toLowerCase();
                    } else {
                        targetValue = Long.parseLong(q);
                        hq = "0x" + (targetValue >= Integer.MIN_VALUE && targetValue <= Integer.MAX_VALUE ? Integer.toHexString((int) targetValue) : Long.toHexString(targetValue)).toLowerCase();
                    }
                    isNumberValid = true;
                } catch (Exception ignored) {}
            }
            this.finalTargetValue = targetValue;
            this.finalIsNumberValid = isNumberValid;
            this.highlightQuery = hq;

            SearchFragment frag = fragmentRef.get();
            if (frag != null && useExcludeList && (this.path.equals("/") || this.path.isEmpty())) {
                String savedExcludes = frag.requireContext().getSharedPreferences("search_prefs", android.content.Context.MODE_PRIVATE).getString("exclude_list", "");
                if (!savedExcludes.isEmpty()) {
                    for (String s : savedExcludes.split("\n")) {
                        String t = s.trim();
                        if (!t.isEmpty()) excludeList.add(t);
                    }
                }
            }
            this.compiledPattern = isRegex ? Pattern.compile(query, matchCase ? 0 : Pattern.CASE_INSENSITIVE) : null;
        }

        private void initSmaliKeywords() {
            // Directives, registers, and opcode segments
            String[] words = {
                ".class", ".super", ".implements", ".source", ".field", ".method", ".end", ".registers", ".locals",
                ".array-data", ".packed-switch", ".sparse-switch", ".catch", ".catchall", ".line", ".local", ".prologue",
                ".epilogue", ".param", ".annotation", "move", "return", "const", "monitor", "check-cast", "instance-of",
                "array-length", "new-instance", "new-array", "filled-new-array", "fill-array-data", "throw", "goto",
                "cmpl", "cmpg", "cmp", "if", "aget", "aput", "sget", "sput", "iget", "iput", "invoke", "v0", "v1", "v2",
                "v3", "v4", "v5", "p0", "p1", "p2"
            };
            Collections.addAll(smaliKeywords, words);
        }

        void start() {
            SearchFragment fragment = fragmentRef.get();
            if (fragment == null) return;
            DexEditorActivity activity = (DexEditorActivity) fragment.getActivity();
            if (activity == null) return;

            final List<TreeNode> results = Collections.synchronizedList(new ArrayList<>());

            // Prepare the progress dialog
            progressDialog = new AlertProgress(activity);
            progressDialog.setTitle("Searching...");
            progressDialog.setMessage("Found: 0");
            progressDialog.setCancelable(false);
            progressDialog.setOnCancelListener(new AlertProgress.OnCancelListener() {
                @Override
                public void onCancel() {
                    if (isFinalized.compareAndSet(false, true)) {
                        isStopped = true;
                        if (executor != null) executor.shutdownNow();
                        // On cancel/back press, show what was found so far
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                finalizeResults(results);
                            }
                        }).start();
                    }
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
                    if (DexEditorActivity.classTree == null) {
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                onPostExecute(new ArrayList<>());
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

                                // Class name is simple search
                                // only search the class node
                                // so its always faster
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

                                            if (openContent != null) {
                                                smali = openContent;
                                            } else {
                                                // POOL-BASED PRE-FILTERING (Ultra Fast Optimization)
                                                // If query isn't in the DEX string pool and isn't a Smali keyword, skip the class.
                                                // Look we're using pre-filtering method to detect the search result for smali whoich may affect serach speed when there is large classes or small classes
                                                if (!isRegex && query.length() >= 3 && !isCommonSmaliWord(query)) {
                                                    if (classDef instanceof DexBackedClassDef) {
                                                        DexBackedDexFile dex = ((DexBackedClassDef) classDef).dexFile;
                                                        if (!checkDexPool(dex, query, matchCase)) {
                                                            updateProgress(processedCount.incrementAndGet(), finalTotal);
                                                            return;
                                                        }
                                                    }
                                                }
                                                smali = generateSmali(classDef);
                                            }

                                            if (!isRegex && !query.contains("\n")) {
                                                if (!checkMatch(smali)) {
                                                    updateProgress(processedCount.incrementAndGet(), finalTotal);
                                                    return;
                                                }
                                            }

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
                                        } catch (Exception ignored) {}
                                        break;
                                    case "Field name":
                                        for (Field field : classDef.getFields()) {
                                            if (checkMatch(field.getName())) {
                                                if (foundCount.incrementAndGet() >= 250000) { stopSearchWithLimit(activity); return; }
                                                if (smali == null) smali = generateSmaliSafe(classDef);
                                                String snippetText = ".field " + AccessFlags.formatAccessFlagsForField(field.getAccessFlags()) + " " + field.getName() + ":" + field.getType();
                                                int lineIdx = findLineOfText(smali, field.getName());
                                                TreeNode snippet = new TreeNode(snippetText, className, 0, false);
                                                snippet.setSnippet(true);
                                                snippet.setLineNumber(lineIdx != -1 ? lineIdx : 0);
                                                preHighlightSnippet(snippet, highlightQuery);
                                                snippets.add(snippet);
                                                match = true;
                                            }
                                        }
                                        break;
                                    case "Method name":
                                        for (Method method : classDef.getMethods()) {
                                            if (checkMatch(method.getName())) {
                                                if (foundCount.incrementAndGet() >= 250000) { stopSearchWithLimit(activity); return; }
                                                if (smali == null) smali = generateSmaliSafe(classDef);
                                                StringBuilder desc = new StringBuilder("(");
                                                for (MethodParameter param : method.getParameters()) desc.append(param.getType());
                                                desc.append(")").append(method.getReturnType());
                                                String snippetText = ".method " + AccessFlags.formatAccessFlagsForMethod(method.getAccessFlags()) + " " + method.getName() + desc;
                                                int lineIdx = findLineOfText(smali, ".method " + AccessFlags.formatAccessFlagsForMethod(method.getAccessFlags()) + " " + method.getName());
                                                TreeNode snippet = new TreeNode(snippetText, className, 0, false);
                                                snippet.setSnippet(true);
                                                snippet.setLineNumber(lineIdx != -1 ? lineIdx : 0);
                                                preHighlightSnippet(snippet, highlightQuery);
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
                                                        TreeNode snippet = new TreeNode(lineText.trim(), className, 0, false);
                                                        snippet.setSnippet(true);
                                                        snippet.setLineNumber(countLinesBefore(smali, start));
                                                        preHighlightSnippet(snippet, highlightQuery);
                                                        snippets.add(snippet);
                                                        match = true;
                                                    }
                                                    start = end + 1;
                                                }
                                            } catch (Exception ignored) {
                                            }
                                        } else {
                                            for (Method method : classDef.getMethods()) {
                                                MethodImplementation impl = method.getImplementation();
                                                if (impl != null) {
                                                    for (Instruction inst : impl.getInstructions()) {
                                                        if (inst instanceof ReferenceInstruction) {
                                                            Reference ref = ((ReferenceInstruction) inst).getReference();
                                                            if (ref instanceof StringReference) {
                                                                String str = ((StringReference) ref).getString();
                                                                if (checkMatch(str)) {
                                                                    if (foundCount.incrementAndGet() >= 250000) { stopSearchWithLimit(activity); return; }
                                                                    if (smali == null) smali = generateSmaliSafe(classDef);
                                                                    String snippetText = inst.getOpcode().name + " ..., \"" + str + "\"";
                                                                    int lineIdx = findLineOfText(smali, "\"" + str + "\"");
                                                                    TreeNode snippet = new TreeNode(snippetText, className, 0, false);
                                                                    snippet.setSnippet(true);
                                                                    snippet.setLineNumber(lineIdx != -1 ? lineIdx : 0);
                                                                    preHighlightSnippet(snippet, highlightQuery);
                                                                    snippets.add(snippet);
                                                                    match = true;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            for (Field field : classDef.getFields()) {
                                                EncodedValue initialValue = field.getInitialValue();
                                                if (initialValue instanceof StringEncodedValue) {
                                                    String str = ((StringEncodedValue) initialValue).getValue();
                                                    if (checkMatch(str)) {
                                                        if (foundCount.incrementAndGet() >= 250000) { stopSearchWithLimit(activity); return; }
                                                        if (smali == null) smali = generateSmaliSafe(classDef);
                                                        String snippetText = ".field ... " + field.getName() + " = \"" + str + "\"";
                                                        int lineIdx = findLineOfText(smali, field.getName());
                                                        TreeNode snippet = new TreeNode(snippetText, className, 0, false);
                                                        snippet.setSnippet(true);
                                                        snippet.setLineNumber(lineIdx != -1 ? lineIdx : 0);
                                                        preHighlightSnippet(snippet, highlightQuery);
                                                        snippets.add(snippet);
                                                        match = true;
                                                    }
                                                }
                                            }
                                            if (searchInAnnotations(classDef.getAnnotations(), className, snippets)) match = true;
                                        }
                                        break;
                                    case "Integer":
                                        boolean hasMatched = false;
                                        for (Method method : classDef.getMethods()) {
                                            MethodImplementation impl = method.getImplementation();
                                            if (impl != null) {
                                                for (Instruction inst : impl.getInstructions()) {
                                                    if (inst instanceof NarrowLiteralInstruction) {
                                                        if (((NarrowLiteralInstruction) inst).getNarrowLiteral() == (int) finalTargetValue) { hasMatched = true; break; }
                                                    } else if (inst instanceof WideLiteralInstruction) {
                                                        if (((WideLiteralInstruction) inst).getWideLiteral() == finalTargetValue) { hasMatched = true; break; }
                                                    }
                                                }
                                            }
                                            if (hasMatched) break;
                                        }
                                        if (!hasMatched) {
                                            for (Field field : classDef.getFields()) {
                                                EncodedValue initialValue = field.getInitialValue();
                                                if (initialValue instanceof IntEncodedValue) {
                                                    if (((IntEncodedValue) initialValue).getValue() == (int) finalTargetValue) { hasMatched = true; break; }
                                                } else if (initialValue instanceof LongEncodedValue) {
                                                    if (((LongEncodedValue) initialValue).getValue() == finalTargetValue) { hasMatched = true; break; }
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
                                                        preHighlightSnippet(snippet, highlightQuery);
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

                    if (isFinalized.compareAndSet(false, true)) {
                        finalizeResults(results);
                    }
                }
            }).start();
        }

        private void finalizeResults(List<TreeNode> results) {
            // Sort and build tree in background
            List<TreeNode> tree;
            if (results.isEmpty()) {
                tree = new ArrayList<>();
            } else {
                // To avoid ConcurrentModificationException if a thread is still finishing
                List<TreeNode> snapshot;
                synchronized (Collections.unmodifiableList(results)) {
                    snapshot = new ArrayList<>(results);
                }
                
                snapshot.sort(new Comparator<TreeNode>() {
                    @Override
                    public int compare(TreeNode n1, TreeNode n2) {
                        return n1.getFullName().compareTo(n2.getFullName());
                    }
                });

                tree = buildTreeStructure(snapshot);
                tree.sort(new Comparator<TreeNode>() {
                    @Override
                    public int compare(TreeNode n1, TreeNode n2) {
                        if (n1.isDirectory() != n2.isDirectory()) return n1.isDirectory() ? -1 : 1;
                        return n1.getName().compareTo(n2.getName());
                    }
                });
            }

            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    onPostExecute(tree);
                }
            });
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
                        SketchwareUtil.showMessage(activity, "The number of serach results exceeds the limit, the search has been stopped");
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

        private boolean checkMatchInRange(String text, int start, int end) {
            if (isRegex) return compiledPattern != null && compiledPattern.matcher(text.substring(start, end)).find();
            boolean ignoreCase = !matchCase;
            if (exactlyMatch) return (end - start == query.length()) && text.regionMatches(ignoreCase, start, query, 0, query.length());
            for (int i = start; i <= end - query.length(); i++)
                if (text.regionMatches(ignoreCase, i, query, 0, query.length())) return true;
            return false;
        }

        private boolean checkMatch(String text) {
            if (isRegex) return compiledPattern != null && compiledPattern.matcher(text).find();
            if (matchCase) return exactlyMatch ? text.equals(query) : text.contains(query);
            return exactlyMatch ? text.equalsIgnoreCase(query) : containsIgnoreCase(text, query);
        }

        private boolean isCommonSmaliWord(String query) {
            if (query.contains(" ") || query.contains(",") || query.contains("{")) return true;
            String lower = query.toLowerCase();
            if (smaliKeywords.contains(lower)) return true;
            // Literals or register ranges
            char first = query.charAt(0);
            return !Character.isLetter(first) && first != 'L';
        }

        private boolean checkDexPool(com.android.tools.smali.dexlib2.dexbacked.DexBackedDexFile dex, String query, boolean matchCase) {
            int dexId = System.identityHashCode(dex);
            Boolean cached = dexMatchCache.get(dexId);
            if (cached != null) return cached;

            boolean found = false;
            java.util.List<String> pool = dex.getStringSection();
            int count = pool.size();
            for (int i = 0; i < count; i++) {
                if (contains(pool.get(i), query, matchCase)) {
                    found = true;
                    break;
                }
            }
            dexMatchCache.put(dexId, found);
            return found;
        }

        private boolean contains(String text, String query, boolean matchCase) {
            return text != null && (matchCase ? text.contains(query) : containsIgnoreCase(text, query));
        }

        private String generateSmaliOptimized(ClassDef classDef) throws Exception {
            StringBuilder sb = BUFFER_THREAD_LOCAL.get();
            if (sb == null) return "";
            sb.setLength(0);

            // Consistent with ClassTree.getSmaliByType header
            String dexFileName = "unknown.dex";
            if (DexEditorActivity.classTree != null) {
                dexFileName = DexEditorActivity.classTree.findDexFileNameForClass(classDef);
            }
            sb.append("# ").append(dexFileName).append("\n\n");

            java.io.Writer writer = new java.io.Writer() {
                @Override public void write(@NonNull char[] c, int o, int l) { sb.append(c, o, l); }
                @Override public void flush() {}
                @Override public void close() {}
            };

            BaksmaliWriter bw = new BaksmaliWriter(writer);
            BaksmaliOptions options = OPTIONS_THREAD_LOCAL.get();
            if (options == null) options = new BaksmaliOptions();

            new com.android.tools.smali.baksmali.Adaptors.ClassDefinition(options, classDef).writeTo(bw);
            bw.close();
            return sb.toString();
        }

        private String generateSmali(ClassDef classDef) throws Exception {
            if (DexEditorActivity.classTree != null) {
                String type = classDef.getType();
                String typeKey = type.substring(1, type.length() - 1);
                String pending = DexEditorActivity.classTree.getPendingSmaliMap().get(typeKey);
                if (pending != null) {
                    // Ensure header consistency even for pending smali
                    if (pending.trim().startsWith("# ")) {
                        pending = pending.replaceFirst("(?s)^#.*?\\n\\n", "");
                    }
                    String dexFileName = DexEditorActivity.classTree.findDexFileNameForClass(classDef);
                    return "# " + dexFileName + "\n\n" + pending;
                }
            }
            return generateSmaliOptimized(classDef);
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
                    preHighlightSnippet(snippet, highlightQuery);
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

        private void preHighlightSnippet(TreeNode node, String query) {
            if (query == null || query.isEmpty()) return;
            String text = node.getName();
            String lowerText = text.toLowerCase();
            String lowerQuery = query.toLowerCase();
            int firstMatch = lowerText.indexOf(lowerQuery);

            if (firstMatch != -1) {
                // Priority to highlighted part: center it in the snippet
                int contextBefore = 40;
                int contextAfter = 60;
                int startLimit = Math.max(0, firstMatch - contextBefore);
                int endLimit = Math.min(text.length(), firstMatch + query.length() + contextAfter);

                String prefix = (startLimit > 0) ? "..." : "";
                String suffix = (endLimit < text.length()) ? "..." : "";
                String displayText = prefix + text.substring(startLimit, endLimit).replace("\n", " ").replace("\r", " ") + suffix;
                
                SpannableString spannable = new SpannableString(displayText);
                String lowerDisplay = displayText.toLowerCase();
                int s = 0;
                while ((s = lowerDisplay.indexOf(lowerQuery, s)) != -1) {
                    spannable.setSpan(new BackgroundColorSpan(0xFFB3E5FC), s, s + query.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    s += query.length();
                }
                node.setCachedSpannedName(spannable);
            } else {
                String cleanText = text.replace("\n", " ").replace("\r", " ");
                node.setCachedSpannedName(cleanText);
            }
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

        private void onPostExecute(final List<TreeNode> tree) {
            if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
            SearchFragment fragment = fragmentRef.get();
            if (fragment == null) return;
            fragment.currentQuery = query;
            final DexEditorActivity activity = (DexEditorActivity) fragment.getActivity();
            if (activity != null) {
                activity.searchNodes.clear();
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
