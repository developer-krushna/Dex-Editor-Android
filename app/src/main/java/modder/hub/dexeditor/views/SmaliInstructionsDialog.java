/*
 * Dex-Editor-Android an Advanced Dex Editor for Android
 * Copyright 2024-2026, developer-krushna
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

package modder.hub.dexeditor.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import modder.hub.dexeditor.R;

public class SmaliInstructionsDialog {
	private final Context context;
	private final String assetFileName;
	private String currentQuery;
	private InstructionsAdapter adapter;
	private List<InstructionItem> originalItems = new ArrayList<>();

	public SmaliInstructionsDialog(@NonNull Context context, String assetFileName) {
		this(context, assetFileName, "");
	}

	public SmaliInstructionsDialog(@NonNull Context context, String assetFileName, String initialQuery) {
		this.context = context;
		this.assetFileName = assetFileName;
		this.currentQuery = initialQuery != null ? initialQuery : "";
	}

	public void show() {
		View view = LayoutInflater.from(context).inflate(R.layout.dialog_instructions, null);
		Toolbar toolbar = view.findViewById(R.id.toolbar);
		toolbar.inflateMenu(R.menu.menu_search);
		toolbar.setTitle("Smali Instructions");

		MenuItem searchItem = toolbar.getMenu().findItem(R.id.search);
		SearchView searchView = (SearchView) searchItem.getActionView();
		if (searchView != null) {
			searchView.setMaxWidth(Integer.MAX_VALUE);
			if (!TextUtils.isEmpty(currentQuery)) {
				searchView.setIconified(false);
				searchItem.expandActionView();
				searchView.setQuery(currentQuery, false);
				searchView.clearFocus();
			}
			searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
				@Override public boolean onQueryTextSubmit(String query) { return false; }
				@Override public boolean onQueryTextChange(String newText) {
					currentQuery = newText;
					filterInstructions(newText);
					return true;
				}
			});
		}

		originalItems = parseInstructions(loadFromAssets());
		adapter = new InstructionsAdapter(originalItems);

		FastScrollerRecyclerView recyclerView = view.findViewById(R.id.instructions_recycler);
		recyclerView.setLayoutManager(new LinearLayoutManager(context));
		recyclerView.setAdapter(adapter);

		if (!TextUtils.isEmpty(currentQuery)) filterInstructions(currentQuery);

		new MaterialAlertDialogBuilder(context)
				.setView(view)
				.setPositiveButton("OK", null)
				.show();
	}

	private void filterInstructions(String query) {
		if (TextUtils.isEmpty(query)) {
			adapter.updateItems(originalItems, "");
			return;
		}
		List<InstructionItem> filteredList = new ArrayList<>();
		String searchQuery = query.toLowerCase();
		for (InstructionItem item : originalItems) {
			boolean headerMatches = item.header.toLowerCase().contains(searchQuery);
			List<String> matchingContent = new ArrayList<>();
			for (String line : item.content) {
				if (line.toLowerCase().contains(searchQuery)) matchingContent.add(line);
			}
			if (headerMatches || !matchingContent.isEmpty()) {
				filteredList.add(new InstructionItem(item.header, matchingContent.isEmpty() ? item.content : matchingContent));
			}
		}
		adapter.updateItems(filteredList, query);
	}

	private String loadFromAssets() {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(assetFileName)))) {
			String line;
			while ((line = reader.readLine()) != null) sb.append(line).append("\n");
		} catch (IOException e) { e.printStackTrace(); }
		return sb.toString();
	}

	private List<InstructionItem> parseInstructions(String text) {
		List<InstructionItem> items = new ArrayList<>();
		for (String section : text.split("\n\n")) {
			String[] lines = section.trim().split("\n");
			if (lines.length > 0 && !lines[0].isEmpty()) {
				items.add(new InstructionItem(lines[0], new ArrayList<>(Arrays.asList(lines).subList(1, lines.length))));
			}
		}
		return items;
	}

	private static class InstructionItem {
		final String header;
		final List<String> content;
		InstructionItem(String header, List<String> content) {
			this.header = header;
			this.content = content;
		}
	}

	private static class InstructionsAdapter extends RecyclerView.Adapter<InstructionsAdapter.ViewHolder> {
		private List<InstructionItem> items;
		private String highlightQuery = "";

		InstructionsAdapter(List<InstructionItem> items) { this.items = items; }

		@SuppressLint("NotifyDataSetChanged")
		void updateItems(List<InstructionItem> newItems, String query) {
			this.items = newItems;
			this.highlightQuery = query;
			notifyDataSetChanged();
		}

		@NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_instruction, parent, false));
		}

		@Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
			InstructionItem item = items.get(position);
			StringBuilder sb = new StringBuilder();
			for (String line : item.content) sb.append(line).append("\n");
			String contentString = sb.toString().trim();

			if (!TextUtils.isEmpty(highlightQuery)) {
				holder.headerText.setText(getHighlightedText(item.header, highlightQuery));
				holder.contentText.setText(getHighlightedText(contentString, highlightQuery));
			} else {
				holder.headerText.setText(item.header);
				holder.contentText.setText(contentString);
			}
		}

		private SpannableString getHighlightedText(String text, String query) {
			SpannableString spannable = new SpannableString(text);
			String textLower = text.toLowerCase(), queryLower = query.toLowerCase();
			int index = textLower.indexOf(queryLower);
			while (index >= 0) {
				spannable.setSpan(new StyleSpan(Typeface.BOLD), index, index + query.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
				index = textLower.indexOf(queryLower, index + query.length());
			}
			return spannable;
		}

		@Override public int getItemCount() { return items.size(); }

		static class ViewHolder extends RecyclerView.ViewHolder {
			TextView headerText, contentText;
			ViewHolder(View view) {
				super(view);
				headerText = view.findViewById(R.id.header_text);
				contentText = view.findViewById(R.id.content_text);
			}
		}
	}
}