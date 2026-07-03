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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import modder.hub.dexeditor.R;

/*
 * Author - @developer-krushna
 * this class responsible for listing strings from dex files
 */
public class StringAdapter extends RecyclerView.Adapter<StringAdapter.ViewHolder> {

    private static final int COLOR_MODIFIED = 0xFF2E7D32; // green
    private static final int COLOR_NORMAL = 0xFF000000;

    // Full, unfiltered dataset - kept as a reference to the activity's backing list
    private final List<String> allStrings;
    // Subset currently shown (equals allStrings when no filter is active)
    private List<String> displayedStrings;
    // original string -> pending new value (not yet applied to the dex classes)
    private final Map<String, String> modifiedStrings = new LinkedHashMap<>();

    private final OnStringClickListener listener;
    private String currentFilter = null;

    public interface OnStringClickListener {
        void onStringClick(String text);
    }

    public StringAdapter(List<String> strings, OnStringClickListener listener) {
        this.allStrings = strings;
        this.displayedStrings = strings;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.string_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String original = displayedStrings.get(position);
        final boolean isModified = modifiedStrings.containsKey(original);
        final String displayText = isModified ? modifiedStrings.get(original) : original;

        holder.stringText.setText(displayText);
        holder.stringText.setTextColor(isModified ? COLOR_MODIFIED : COLOR_NORMAL);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.onStringClick(original);
            }
        });
    }

    @Override
    public int getItemCount() {
        return displayedStrings.size();
    }

    public void refresh() {
        applyFilter(currentFilter);
    }

    public void setFilter(String query) {
        currentFilter = (query == null || query.trim().isEmpty()) ? null : query.trim();
        applyFilter(currentFilter);
    }

    public String getCurrentFilter() {
        return currentFilter;
    }

    private void applyFilter(String query) {
        if (query == null) {
            displayedStrings = allStrings;
        } else {
            String lower = query.toLowerCase();
            List<String> filtered = new ArrayList<>();
            for (String s : allStrings) {
                if (s != null && s.toLowerCase().contains(lower)) filtered.add(s);
            }
            displayedStrings = filtered;
        }
        notifyDataSetChanged();
    }

    public void markModified(String original, String newValue) {
        if (newValue == null || newValue.equals(original)) {
            modifiedStrings.remove(original);
        } else {
            modifiedStrings.put(original, newValue);
        }
        notifyDataSetChanged();
    }

    public String getPendingValue(String original) {
        String v = modifiedStrings.get(original);
        return v != null ? v : original;
    }

    public boolean hasModifications() {
        return !modifiedStrings.isEmpty();
    }

    public Map<String, String> getModifiedStrings() {
        return modifiedStrings;
    }

    public void clearModifications() {
        modifiedStrings.clear();
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView stringText;

        ViewHolder(View itemView) {
            super(itemView);
            stringText = itemView.findViewById(R.id.string_text);
        }
    }
}