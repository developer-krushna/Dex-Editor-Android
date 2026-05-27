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

package modder.hub.dexeditor.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import modder.hub.dexeditor.R;
import modder.hub.dexeditor.activity.DexEditorActivity;
import modder.hub.dexeditor.model.TreeNode;
import modder.hub.dexeditor.utils.TreeHelper;
import modder.hub.dexeditor.utils.UIHelper;

// Author ; @developer-krushna
// Code fixed and some commments and improvements are done by AI

/**
 * TreeAdapter: Manages the hierarchical display of classes and packages.
 * It transforms a nested TreeNode structure into a flat list for RecyclerView,
 * handling expansion, selection, and context menus.
 */
public class TreeAdapter extends RecyclerView.Adapter<TreeAdapter.ViewHolder> {

    // visibleNodes is the "flattened" version of the tree used by the adapter
    private final List<TreeNode> visibleNodes = new ArrayList<>();
    private final List<TreeNode> rootNodes;
    private final OnNodeClickListener listener;
    private final Context context;
    private final float density;

    private static GradientDrawable folderDrawable;
    private static GradientDrawable classDrawable;

    // UI state flags
    private boolean isSelectionMode = false;
    private int initialLongPressPosition = -1; // Used for range selection
    private boolean isHistory = false;
    private boolean isModifiedList = false;
    private String highlightedFullName = null;
    private String searchQuery = null;
    private boolean isSearchList = false;

    public TreeAdapter(Context context, List<TreeNode> rootNodes, OnNodeClickListener listener) {
        this(context, rootNodes, listener, false, false);
    }

    public TreeAdapter(Context context, List<TreeNode> rootNodes, OnNodeClickListener listener, boolean isHistory) {
        this(context, rootNodes, listener, isHistory, false);
    }

    private static final int TYPE_DIRECTORY = 0;
    private static final int TYPE_CLASS = 1;
    private static final int TYPE_SNIPPET = 2;

    public TreeAdapter(Context context, List<TreeNode> rootNodes, OnNodeClickListener listener, boolean isHistory, boolean isModifiedList) {
        this.context = context;
        this.listener = listener;
        this.rootNodes = rootNodes;
        this.visibleNodes.addAll(rootNodes);
        this.isHistory = isHistory;
        this.isModifiedList = isModifiedList;
        this.density = context.getResources().getDisplayMetrics().density;

        if (folderDrawable == null) {
            folderDrawable = new GradientDrawable();
            folderDrawable.setCornerRadius(5 * density); // Minimal radius for folder (Standard)
            folderDrawable.setColor(0xFF252525);
        }
        if (classDrawable == null) {
            classDrawable = new GradientDrawable();
            classDrawable.setCornerRadius(100 * density); // Fully circular for Class
            classDrawable.setColor(0xFF3860AF);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setHighlightedFullName(String fullName) {
        this.highlightedFullName = fullName;
        notifyDataSetChanged();
    }

    public void setSearchList(boolean searchList, String query) {
        this.isSearchList = searchList;
        if (query == null || !query.equals(this.searchQuery)) {
            clearCachedSpannedNamesRecursive(rootNodes);
        }
        this.searchQuery = query;
    }

    private void clearCachedSpannedNamesRecursive(List<TreeNode> nodes) {
        for (TreeNode node : nodes) {
            node.setCachedSpannedName(null);
            if (!node.getChildren().isEmpty()) {
                clearCachedSpannedNamesRecursive(node.getChildren());
            }
        }
    }

    public List<TreeNode> getRootNodes() {
        return rootNodes;
    }

    /**
     * Flattens the current tree structure based on which nodes are expanded.
     * Call this whenever the tree structure changes significantly.
     */
    @SuppressLint("NotifyDataSetChanged")
    public void refreshVisibleNodes() {
        visibleNodes.clear();
        addVisibleNodesRecursive(rootNodes, 0);
        notifyDataSetChanged();
    }

    private void addVisibleNodesRecursive(List<TreeNode> nodes, int depth) {
        if (nodes == null) return;
        for (TreeNode node : nodes) {
            node.setDepth(depth);
            visibleNodes.add(node);
            if (node.isExpanded() && node.getChildren() != null && !node.getChildren().isEmpty()) {
                addVisibleNodesRecursive(node.getChildren(), depth + 1);
            }
        }
    }

    public int getPosition(TreeNode node) {
        return visibleNodes.indexOf(node);
    }

    public List<TreeNode> getVisibleNodes() {
        return visibleNodes;
    }

    @Override
    public int getItemViewType(int position) {
        TreeNode node = visibleNodes.get(position);
        if (node.isDirectory()) return TYPE_DIRECTORY;
        if (node.isSnippet()) return TYPE_SNIPPET;
        return TYPE_CLASS;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_holder, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        
        holder.arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getBindingAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) toggleNode(visibleNodes.get(pos), pos);
            }
        });

        holder.itemContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getBindingAdapterPosition();
                if (pos == RecyclerView.NO_POSITION) return;
                TreeNode node = visibleNodes.get(pos);
                if (node.isDirectory() && !isHistory) {
                    toggleNode(node, pos);
                } else if (node.isSnippet()) {
                    if (isSearchList) {
                        boolean isDeleted = DexEditorActivity.classTree != null && !DexEditorActivity.classTree.classMap.containsKey(node.getFullName());
                        if (isDeleted) {
                            showDeletedPrompt(node, pos);
                            return;
                        }
                    }
                    if (listener != null) listener.onNodeClick(node);
                } else {
                    if (!isSelectionMode) {
                        if (isHistory && !node.isDirectory()) {
                            boolean isDeleted = DexEditorActivity.classTree != null && !DexEditorActivity.classTree.classMap.containsKey(node.getFullName());
                            if (isDeleted) {
                                showDeletedPrompt(node, pos);
                                return;
                            }
                        }

                        if (isSearchList) {
                            // Toggle expansion for classes in search results instead of opening editor
                            toggleNode(node, pos);
                        } else if (listener != null) {
                            listener.onNodeClick(node);
                        }
                    } else {
                        toggleChecked(node, pos);
                    }
                }
            }
        });

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getBindingAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) toggleChecked(visibleNodes.get(pos), pos);
            }
        });

        holder.itemContent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int pos = holder.getBindingAdapterPosition();
                if (pos == RecyclerView.NO_POSITION) return true;
                handleLongClick(v, visibleNodes.get(pos), pos);
                return true;
            }
        });

        return holder;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TreeNode node = visibleNodes.get(position);
        int type = getItemViewType(position);

        // 1. Icons and Fixed States based on type
        switch (type) {
            case TYPE_DIRECTORY:
                if (!isHistory) {
                    if (holder.arrow.getVisibility() != View.VISIBLE) {
                        holder.arrow.setVisibility(View.VISIBLE);
                    }
                    holder.arrow.setRotation(node.isExpanded() ? 45 : 0);
                } else {
                    if (holder.arrow.getVisibility() != View.GONE) {
                        holder.arrow.setVisibility(View.GONE);
                    }
                }
                if (holder.icon.getVisibility() != View.VISIBLE) {
                    holder.icon.setVisibility(View.VISIBLE);
                }
                if (holder.smaliSymbol.getVisibility() != View.GONE) {
                    holder.smaliSymbol.setVisibility(View.GONE);
                }
                if (holder.iconBackground.getVisibility() != View.VISIBLE) {
                    holder.iconBackground.setVisibility(View.VISIBLE);
                }
                holder.icon.setImageResource(R.drawable.ic_folder_mt);
                holder.iconBackground.setBackground(folderDrawable);
                holder.iconBackground.setElevation(5);
                break;
            case TYPE_SNIPPET:
                if (holder.arrow.getVisibility() != View.GONE) {
                    holder.arrow.setVisibility(View.GONE);
                }
                if (holder.icon.getVisibility() != View.GONE) {
                    holder.icon.setVisibility(View.GONE);
                }
                if (holder.smaliSymbol.getVisibility() != View.GONE) {
                    holder.smaliSymbol.setVisibility(View.GONE);
                }
                if (holder.iconBackground.getVisibility() != View.GONE) {
                    holder.iconBackground.setVisibility(View.GONE);
                }
                holder.iconBackground.setBackground(null);
                break;
            case TYPE_CLASS:
                if (holder.arrow.getVisibility() != View.GONE) {
                    holder.arrow.setVisibility(View.GONE);
                }
                if (holder.icon.getVisibility() != View.GONE) {
                    holder.icon.setVisibility(View.GONE);
                }
                if (holder.smaliSymbol.getVisibility() != View.VISIBLE) {
                    holder.smaliSymbol.setVisibility(View.VISIBLE);
                }
                if (holder.iconBackground.getVisibility() != View.VISIBLE) {
                    holder.iconBackground.setVisibility(View.VISIBLE);
                }
                holder.smaliSymbol.setText("C");
                holder.smaliSymbol.setTypeface(Typeface.MONOSPACE);
                holder.iconBackground.setBackground(classDrawable);
                holder.iconBackground.setElevation(5);
                break;
        }

        // 2. Text
        if (type == TYPE_SNIPPET) {
            holder.name.setSingleLine(true);
            CharSequence cached = node.getCachedSpannedName();
            if (cached != null) {
                holder.name.setText(cached);
            } else {
                highlightSnippet(holder.name, node, searchQuery);
            }
        } else {
            holder.name.setText(node.getName());
            holder.name.setSingleLine(false);
        }

        // 3. Subtitle (History)
        if (isHistory && type == TYPE_CLASS) {
            if (holder.subtitle.getVisibility() != View.VISIBLE) {
                holder.subtitle.setVisibility(View.VISIBLE);
            }
            holder.subtitle.setText(node.getFullName());
        } else {
            if (holder.subtitle.getVisibility() != View.GONE) {
                holder.subtitle.setVisibility(View.GONE);
            }
        }

        // Apply strike-through for deleted classes in history and search
        if ((isHistory || isSearchList) && (type == TYPE_CLASS || type == TYPE_SNIPPET)) {
            boolean isDeleted = DexEditorActivity.classTree != null && !DexEditorActivity.classTree.classMap.containsKey(node.getFullName());
            if (isDeleted) {
                holder.name.setPaintFlags(holder.name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.subtitle.setPaintFlags(holder.subtitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.name.setPaintFlags(holder.name.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                holder.subtitle.setPaintFlags(holder.subtitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
        } else {
            holder.name.setPaintFlags(holder.name.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        // 4. Background
        if (type == TYPE_SNIPPET) {
            holder.itemContent.setBackgroundResource(R.drawable.item_background);
        } else {
            if (highlightedFullName != null && highlightedFullName.equals(node.getFullName())) {
                holder.itemContent.setBackgroundColor(0xFFE1F5FE);
            } else {
                android.util.TypedValue outValue = new android.util.TypedValue();
                context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
                holder.itemContent.setBackgroundResource(outValue.resourceId);
            }
        }

        // 5. Indentation
        int arrowWidth = (int) (14 * density);
        int itemPaddingLeft = (int) (4 * density);
        int checkboxWidth = (isSelectionMode ? (int) (28 * density) : 0);
        int iconWidth = (int) (28 * density); // 20dp + 4dp margin each side
        int textPadding = (int) (6 * density);

        // Slightly reduced indent to balance alignment
        int indentPerLevel = (int) (24 * density);

        int depth = node.getDepth();
        int indent;

        if (isSearchList) {
            if (type == TYPE_CLASS) {
                // Indent search results by one full level to separate from root folders
                indent = indentPerLevel;
            } else if (type == TYPE_SNIPPET) {
                // Align Snippet text with Class text
                // Class text starts at: Indent(24) + Checkbox + IconWidth(28) + Padding(6)
                indent = indentPerLevel + checkboxWidth + iconWidth;
            } else {
                indent = 0;
            }
        } else if (isHistory) {
            indent = 0;
        } else {
            // Main Tree
            indent = depth * indentPerLevel;
            if (type != TYPE_DIRECTORY) {
                indent += arrowWidth;
            }
        }

        int topBottomPadding = (type == TYPE_SNIPPET) ? 4 : 2;
        if (holder.lastIndent != indent || holder.lastTopBottomPadding != topBottomPadding) {
            ViewGroup.LayoutParams lp = holder.indentSpacer.getLayoutParams();
            lp.width = indent;
            holder.indentSpacer.setLayoutParams(lp);

            holder.itemContent.setPadding(itemPaddingLeft, topBottomPadding, (int) (24 * density), topBottomPadding);
            holder.lastIndent = indent;
            holder.lastTopBottomPadding = topBottomPadding;
        }

        // Divider visibility logic
        boolean showDivider = false;
        if (isSearchList) {
            if (type == TYPE_CLASS || type == TYPE_SNIPPET) {
                showDivider = true;
                // If next node exists and is a directory, hide divider to avoid "divider before folder"
                if (position + 1 < visibleNodes.size()) {
                    if (visibleNodes.get(position + 1).isDirectory()) {
                        showDivider = false;
                    }
                }
            }
        }
        holder.divider.setVisibility(showDivider ? View.VISIBLE : View.GONE);

        // Align divider with text
        if (showDivider) {
             LinearLayout.LayoutParams dlp = (LinearLayout.LayoutParams) holder.divider.getLayoutParams();
             // Text starts after: checkbox + icon + textPadding
             int textStartOffset = checkboxWidth + (type != TYPE_SNIPPET ? iconWidth : 0) + textPadding + itemPaddingLeft;
             dlp.leftMargin = textStartOffset;
             dlp.rightMargin = (int) (16 * density);
             holder.divider.setLayoutParams(dlp);
        }

        // 6. Checkbox
        if (isSelectionMode) {
            if (holder.checkBox.getVisibility() != View.VISIBLE) holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setOnCheckedChangeListener(null);
            if (type == TYPE_DIRECTORY) {
                CheckState state = getCheckState(node);
                if (state == CheckState.PARTIAL) {
                    holder.checkBox.setButtonDrawable(R.drawable.ic_checkbox_partial);
                    holder.checkBox.setChecked(true);
                } else {
                    holder.checkBox.setButtonDrawable(holder.defaultCheckBoxDrawable);
                    holder.checkBox.setChecked(state == CheckState.CHECKED);
                }
            } else {
                holder.checkBox.setButtonDrawable(holder.defaultCheckBoxDrawable);
                holder.checkBox.setChecked(node.isChecked());
            }
        } else {
            if (holder.checkBox.getVisibility() != View.GONE) holder.checkBox.setVisibility(View.GONE);
        }
    }

    private void highlightSnippet(TextView textView, TreeNode node, String query) {
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

            android.text.SpannableString spannable = new android.text.SpannableString(displayText);
            String lowerDisplay = displayText.toLowerCase();
            int s = 0;
            while ((s = lowerDisplay.indexOf(lowerQuery, s)) != -1) {
                spannable.setSpan(new android.text.style.BackgroundColorSpan(0xFFB3E5FC), s, s + query.length(), android.text.Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                s += query.length();
            }
            node.setCachedSpannedName(spannable);
            textView.setText(spannable);
        } else {
            String cleanText = text.replace("\n", " ").replace("\r", " ");
            node.setCachedSpannedName(cleanText);
            textView.setText(cleanText);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void handleLongClick(View v, TreeNode node, int pos) {
        if (isSelectionMode) {
            if (initialLongPressPosition == -1) {
                initialLongPressPosition = pos;
                TreeHelper.setCheckedRecursive(node, true);
                notifyDataSetChanged();
            } else {
                int start = Math.min(initialLongPressPosition, pos);
                int end = Math.max(initialLongPressPosition, pos);
                for (int i = start; i <= end; i++) {
                    TreeHelper.setCheckedRecursive(visibleNodes.get(i), true);
                }
                initialLongPressPosition = -1;
                notifyDataSetChanged();
            }
            if (listener != null) {
                listener.onSelectionChanged(getSelectedNodes().size());
            }
        } else {
            if (isHistory) {
                showHistoryPopupMenu(v, node, pos);
            } else if (isModifiedList) {
                if (!node.isDirectory()) showModifiedPopupMenu(v, node, pos);
            } else if (isSearchList) {
                if (!node.isDirectory() && !node.isSnippet()) showSearchPopupMenu(v, node, pos);
            } else {
                showPopupMenu(v, node, pos);
            }
        }
    }

    private boolean isNodeDeleted(TreeNode node) {
        return DexEditorActivity.classTree != null && !DexEditorActivity.classTree.classMap.containsKey(node.getFullName());
    }

    private void showSearchPopupMenu(View view, TreeNode node, int pos) {
        boolean isDeleted = isNodeDeleted(node);
        PopupMenu popup = new PopupMenu(context, view);
        popup.getMenu().add("Copy");
        if (!isDeleted) {
            popup.getMenu().add("Locate");
        }
        popup.getMenu().add("Remove from search result");
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(android.view.MenuItem item) {
                switch (Objects.requireNonNull(item.getTitle()).toString()) {
                    case "Copy":
                       showCopySubMenu(view, node);
                        break;
                    case "Locate":
                        if (listener != null) listener.onLocate(node);
                        break;
                    case "Remove from search result":
                        if (listener != null) listener.onNodeDeleted(node);
                        break;
                }
                return true;
            }
        });
        popup.show();
    }

    private void showDeletedPrompt(TreeNode node, int position) {
        new MaterialAlertDialogBuilder(context)
                .setTitle("Class deleted")
                .setMessage("This class has been deleted. Do you want to remove it from " + (isHistory ? "history" : "search results") + "?")
                .setPositiveButton("Remove", new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(android.content.DialogInterface dialog, int which) {
                        visibleNodes.remove(position);
                        rootNodes.remove(node);
                        notifyItemRemoved(position);
                        if (listener != null) listener.onNodeDeleted(node);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    /**
     * Determines the check state of a node.
     * For folders, it looks at the children to decide if it's checked, unchecked, or partially checked.
     */
    private CheckState getCheckState(TreeNode node) {
        if (!node.isDirectory()) {
            return node.isChecked() ? CheckState.CHECKED : CheckState.UNCHECKED;
        }

        List<TreeNode> children = node.getChildren();
        if (children.isEmpty()) {
            return node.isChecked() ? CheckState.CHECKED : CheckState.UNCHECKED;
        }

        boolean hasChecked = false;
        boolean hasUnchecked = false;
        boolean hasPartial = false;

        for (TreeNode child : children) {
            CheckState state = getCheckState(child);
            if (state == CheckState.PARTIAL) {
                hasPartial = true;
                break;
            }
            if (state == CheckState.CHECKED) hasChecked = true;
            else if (state == CheckState.UNCHECKED) hasUnchecked = true;
        }

        if (hasPartial || (hasChecked && hasUnchecked)) {
            return CheckState.PARTIAL;
        }
        if (hasChecked) return CheckState.CHECKED;
        return CheckState.UNCHECKED;
    }

    @SuppressLint("NotifyDataSetChanged")
    private void toggleChecked(TreeNode node, int position) {
        boolean newState;
        CheckState currentState = getCheckState(node);
        if (node.isDirectory()) {
            newState = currentState != CheckState.CHECKED;
        } else {
            newState = !node.isChecked();
        }
        TreeHelper.setCheckedRecursive(node, newState);

        // Update parent states if needed
        updateParentCheckState(node);

        notifyDataSetChanged();

        int selectedCount = getSelectedNodes().size();
        if (listener != null) {
            listener.onSelectionChanged(selectedCount);
        }

        // Auto-cancel selection mode if nothing is selected
        if (isSelectionMode && selectedCount == 0) {
            setSelectionMode(false);
        }
    }

    private void updateParentCheckState(TreeNode node) {
        TreeNode parent = node.getParent();
        if (parent == null) return;

        // Note: The UI check state for folders is calculated dynamically in onBindViewHolder
        // via getCheckState(), so we don't strictly need to set isChecked on folder nodes,
        // but it's good practice for consistency.
        updateParentCheckState(parent);
    }

    public List<TreeNode> getSelectedNodes() {
        List<TreeNode> selected = new ArrayList<>();
        findSelectedNodesRecursive(rootNodes, selected);
        return selected;
    }

    private void findSelectedNodesRecursive(List<TreeNode> nodes, List<TreeNode> result) {
        for (TreeNode node : nodes) {
            CheckState state = getCheckState(node);
            if (state == CheckState.CHECKED) {
                result.add(node);
            } else if (state == CheckState.PARTIAL) {
                findSelectedNodesRecursive(node.getChildren(), result);
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void invertSelection() {
        invertSelectionRecursive(rootNodes);
        notifyDataSetChanged();
        if (listener != null) {
            listener.onSelectionChanged(getSelectedNodes().size());
        }
    }

    private void invertSelectionRecursive(List<TreeNode> nodes) {
        for (TreeNode node : nodes) {
            node.setChecked(!node.isChecked());
            if (!node.getChildren().isEmpty()) {
                invertSelectionRecursive(node.getChildren());
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void clearAllSelection() {
        TreeHelper.clearSelectionRecursive(rootNodes);
        notifyDataSetChanged();
        if (listener != null) {
            listener.onSelectionChanged(0);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void showModifiedPopupMenu(View view, TreeNode node, int position) {
        boolean isDeleted = isNodeDeleted(node);
        PopupMenu popup = new PopupMenu(context, view);
        popup.getMenu().add(0, 0, 0, "Copy");
        if (!isDeleted) {
            popup.getMenu().add(0, 1, 0, "Locate");
            popup.getMenu().add(0, 2, 0, "Compare the difference");
        }

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(android.view.MenuItem item) {
                int id = item.getItemId();
                if (id == 0) {
                    showCopySubMenu(view, node);
                } else if (id == 1) {
                    if (listener != null) listener.onLocate(node);
                } else if (id == 2) {
                    if (listener != null) listener.onCompare(node);
                }
                return true;
            }
        });
        popup.show();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void showHistoryPopupMenu(View view, TreeNode node, int position) {
        boolean isDeleted = isNodeDeleted(node);
        PopupMenu popup = new PopupMenu(context, view);
        popup.getMenu().add(0, 0, 0, "Copy").setActionView(null);
        if (!isDeleted) {
            popup.getMenu().add(0, 1, 0, "Locate");
        }
        popup.getMenu().add(0, 2, 0, "Delete");

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(android.view.MenuItem item) {
                int id = item.getItemId();
                if (id == 0) {
                    showCopySubMenu(view, node);
                } else if (id == 1) {
                    if (listener != null) listener.onLocate(node);
                } else if (id == 2) {
                    visibleNodes.remove(position);
                    rootNodes.remove(node);
                    notifyItemRemoved(position);
                    if (listener != null) listener.onNodeDeleted(node);
                }
                return true;
            }
        });
        popup.show();
    }

    private void showCopySubMenu(View view, TreeNode node) {
        String full = node.getFullName(); // com/hello/MainActivity
        String name = node.getName(); // MainActivity
        PopupMenu sub = new PopupMenu(context, view);
        sub.getMenu().add(name);
        sub.getMenu().add(full.replace("/", "."));
        sub.getMenu().add(full);
        sub.getMenu().add("L" + full + ";");

        sub.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(android.view.MenuItem item) {
                UIHelper.copyToClipboard(context, Objects.requireNonNull(item.getTitle()).toString());
                return true;
            }
        });
        sub.show();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void showPopupMenu(View view, TreeNode node, int position) {
        PopupMenu popup = new PopupMenu(context, view);
        if (node.isDirectory()) {
            popup.getMenu().add("Search");
        } else {
            popup.getMenu().add("Copy");
        }
        popup.getMenu().add("Add");
        popup.getMenu().add("Import");
        popup.getMenu().add("Delete");
        popup.getMenu().add("Rename");
        popup.getMenu().add("Batch operations");

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(android.view.MenuItem item) {
                String title = Objects.requireNonNull(item.getTitle()).toString();
                switch (title) {
                    case "Search":
                        if (listener != null) listener.onSearch(node);
                        break;
                    case "Copy":
                        showCopySubMenu(view, node);
                        break;
                    case "Delete":
                        showDeleteDialog(node, position);
                        break;
                    case "Batch operations":
                        isSelectionMode = true;
                        TreeHelper.setCheckedRecursive(node, true);
                        notifyDataSetChanged();
                        if (listener != null) {
                            listener.onSelectionChanged(getSelectedNodes().size());
                        }
                        break;
                    default:
                        Toast.makeText(context, title + " not implemented yet", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
        popup.show();
    }

    // Show delete dialog
    private void showDeleteDialog(TreeNode node, int position) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure you want to delete " + (node.isDirectory() ? "folder" : "class") + ": " + node.getName() + "?");
        builder.setPositiveButton("Delete", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(android.content.DialogInterface dialog, int which) {
                if (listener != null) {
                    listener.onNodeDeleted(node);
                    removeItem(node, position);
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void removeItem(TreeNode node, int position) {
        TreeNode parent = node.getParent();
        if (parent != null) {
            parent.getChildren().remove(node);
            removeEmptyParentsRecursive(parent);
        } else {
            rootNodes.remove(node);
        }
        refreshVisibleNodes();
    }

    private void removeEmptyParentsRecursive(TreeNode node) {
        if (node.isDirectory() && node.getChildren().isEmpty()) {
            TreeNode parent = node.getParent();
            if (parent != null) {
                parent.getChildren().remove(node);
                removeEmptyParentsRecursive(parent);
            } else {
                rootNodes.remove(node);
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void removeSelectedNodes() {
        // Remove from rootNodes recursively
        removeSelectedRecursive(rootNodes);
        refreshVisibleNodes();
    }

    private void removeSelectedRecursive(List<TreeNode> nodes) {
        Iterator<TreeNode> it = nodes.iterator();
        while (it.hasNext()) {
            TreeNode node = it.next();
            CheckState state = getCheckState(node);
            if (state == CheckState.CHECKED) {
                it.remove();
            } else if (state == CheckState.PARTIAL) {
                if (!node.getChildren().isEmpty()) {
                    removeSelectedRecursive(node.getChildren());
                    // After cleaning children, if directory is empty, remove it
                    if (node.getChildren().isEmpty()) {
                        it.remove();
                    }
                }
            }
        }
    }

    public boolean isSelectionMode() {
        return isSelectionMode;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setSelectionMode(boolean selectionMode) {
        isSelectionMode = selectionMode;
        initialLongPressPosition = -1;
        if (!selectionMode) {
            TreeHelper.clearSelectionRecursive(rootNodes);
        }
        notifyDataSetChanged();
    }

    private void toggleNode(TreeNode node, int position) {
        if (node.isExpanded()) {
            collapseNode(node, position);
        } else {
            expandNode(node, position);
        }
        notifyItemChanged(position);
    }

    /**
     * Expands a node and inserts its children into the visible list.
     */
    private void expandNode(TreeNode node, int position) {
        node.setExpanded(true);
        List<TreeNode> toAdd = new ArrayList<>();
        getChildrenRecursive(node, toAdd, node.getDepth() + 1);
        if (!toAdd.isEmpty()) {
            visibleNodes.addAll(position + 1, toAdd);
            notifyItemRangeInserted(position + 1, toAdd.size());
        }
    }

    private void getChildrenRecursive(TreeNode parent, List<TreeNode> result, int currentDepth) {
        if (parent.getChildren() == null) return;
        for (TreeNode child : parent.getChildren()) {
            child.setDepth(currentDepth);
            result.add(child);
            if (child.isExpanded()) {
                getChildrenRecursive(child, result, currentDepth + 1);
            }
        }
    }

    /**
     * Collapses a node and removes all its descendants from the visible list.
     */
    private void collapseNode(TreeNode node, int position) {
        node.setExpanded(false);
        int nextPos = position + 1;
        int count = 0;
        while (nextPos < visibleNodes.size() && isDescendant(node, visibleNodes.get(nextPos))) {
            visibleNodes.remove(nextPos);
            count++;
        }

        if (count > 0) {
            notifyItemRangeRemoved(position + 1, count);
        }
    }

    private boolean isDescendant(TreeNode parent, TreeNode node) {
        TreeNode p = node.getParent();
        while (p != null) {
            if (p == parent) return true;
            p = p.getParent();
        }
        return false;
    }

    @Override
    public int getItemCount() {
        return visibleNodes.size();
    }

    private GradientDrawable createDrawable(int cornerRadius, int color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(cornerRadius);
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }

    private enum CheckState {
        CHECKED, UNCHECKED, PARTIAL
    }

    public interface OnNodeClickListener {
        void onNodeClick(TreeNode node);

        void onNodeDeleted(TreeNode node);

        void onSelectionChanged(int count);

        default void onLocate(TreeNode node) {
        }

        default void onCompare(TreeNode node) {
        }

        default void onCopyName(TreeNode node) {
        }

        default void onSearch(TreeNode node) {
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView subtitle;
        TextView smaliSymbol;
        ImageView icon;
        ImageView arrow;
        CheckBox checkBox;
        FrameLayout iconBackground;
        LinearLayout itemContent;
        View divider;
        View indentSpacer;
        Drawable defaultCheckBoxDrawable;

        // Cache last values to avoid redundant layout triggers
        int lastIndent = -1;
        int lastTopBottomPadding = -1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemContent = itemView.findViewById(R.id.item_content);
            name = itemView.findViewById(R.id.name);
            subtitle = itemView.findViewById(R.id.subtitle);
            smaliSymbol = itemView.findViewById(R.id.smaliSymbol);
            icon = itemView.findViewById(R.id.imageview1);
            arrow = itemView.findViewById(R.id.arrow);
            checkBox = itemView.findViewById(R.id.checkbox);
            iconBackground = itemView.findViewById(R.id.icon_background);
            divider = itemView.findViewById(R.id.divider);
            indentSpacer = itemView.findViewById(R.id.indent_spacer);
            defaultCheckBoxDrawable = androidx.core.widget.CompoundButtonCompat.getButtonDrawable(checkBox);
        }
    }
}
