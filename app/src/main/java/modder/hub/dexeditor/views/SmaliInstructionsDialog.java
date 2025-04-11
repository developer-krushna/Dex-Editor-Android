
/*
* Dex-Editor-Android an Advanced Dex Editor for Android 
* Copyright 2024-2025, developer-krushna
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


package modder.hub.dexeditor.views;

import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import me.zhanghai.android.fastscroll.FastScrollerBuilder;
import modder.hub.dexeditor.R;


/*
Author @developer-krushna
Code fixed/enhancement/some hidden ideas/comments by ChatGPT
*/


public class SmaliInstructionsDialog extends Dialog {
	
	private Context context;
	private String assetFileName;
	private String currentQuery = "";
	private InstructionsAdapter adapter;
	private List<InstructionItem> originalItems = new ArrayList<>();
	
	public SmaliInstructionsDialog(@NonNull Context context, String assetFileName) {
		this(context, assetFileName, "");
	}
	
	public SmaliInstructionsDialog(@NonNull Context context, String assetFileName, String initialQuery) {
		super(context);
		this.context = context;
		this.assetFileName = assetFileName;
		this.currentQuery = initialQuery != null ? initialQuery : "";
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_instructions);
		
		Window window = getWindow();
		if (window != null) {
			window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
		}
		
		Toolbar toolbar = findViewById(R.id.toolbar);
		toolbar.inflateMenu(R.menu.menu_search);
		toolbar.setTitle("Smali Instructions");
		toolbar.setNavigationIcon(null);
		
		MenuItem searchItem = toolbar.getMenu().findItem(R.id.search);
		SearchView searchView = (SearchView) searchItem.getActionView();
		searchView.setMaxWidth(Integer.MAX_VALUE);
		searchView.setQueryHint("");
		
		if (!TextUtils.isEmpty(currentQuery)) {
			searchView.setIconified(false);
			searchItem.expandActionView();
			searchView.setQuery(currentQuery, false);
			searchView.clearFocus();
		}
		
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				return false;
			}
			
			@Override
			public boolean onQueryTextChange(String newText) {
				currentQuery = newText;
				filterInstructions(newText);
				return true;
			}
		});
		
		String instructionsText = loadFromAssets();
		originalItems = parseInstructions(instructionsText);
		
		RecyclerView recyclerView = findViewById(R.id.instructions_recycler);
		new FastScrollerBuilder(recyclerView).build();
		recyclerView.setLayoutManager(new LinearLayoutManager(context));
		adapter = new InstructionsAdapter(originalItems);
		recyclerView.setAdapter(adapter);
		
		if (!TextUtils.isEmpty(currentQuery)) {
			filterInstructions(currentQuery);
		}
		
		Button positiveButton = findViewById(R.id.positive_button);
		positiveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	}
	
	private void filterInstructions(String query) {
		List<InstructionItem> filteredList = new ArrayList<>();
		if (TextUtils.isEmpty(query)) {
			// Return all items without highlighting when query is empty
			filteredList.addAll(originalItems);
		} else {
			String searchQuery = query.toLowerCase();
			for (InstructionItem originalItem : originalItems) {
				boolean headerMatches = originalItem.getHeader().toLowerCase().contains(searchQuery);
				List<String> matchingContent = new ArrayList<>();
				
				for (String line : originalItem.getContent()) {
					if (line.toLowerCase().contains(searchQuery)) {
						matchingContent.add(line);
					}
				}
				
				if (headerMatches || !matchingContent.isEmpty()) {
					filteredList.add(new InstructionItem(
					originalItem.getHeader(),
					matchingContent.isEmpty() ? originalItem.getContent() : matchingContent
					));
				}
			}
		}
		adapter.updateItems(filteredList, query);
	}
	
	private String loadFromAssets() {
		StringBuilder stringBuilder = new StringBuilder();
		AssetManager assetManager = context.getAssets();
		try {
			InputStream inputStream = assetManager.open(assetFileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line).append("\n");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}
	
	private List<InstructionItem> parseInstructions(String text) {
		List<InstructionItem> items = new ArrayList<>();
		String[] sections = text.split("\n\n");
		
		for (String section : sections) {
			if (section.trim().isEmpty()) continue;
			
			String[] lines = section.split("\n");
			if (lines.length > 0) {
				String header = lines[0];
				List<String> content = new ArrayList<>();
				
				for (int i = 1; i < lines.length; i++) {
					content.add(lines[i]);
				}
				
				items.add(new InstructionItem(header, content));
			}
		}
		return items;
	}
	
	private static class InstructionItem {
		private String header;
		private List<String> content;
		
		public InstructionItem(String header, List<String> content) {
			this.header = header;
			this.content = content;
		}
		
		public String getHeader() {
			return header;
		}
		
		public List<String> getContent() {
			return content;
		}
	}
	
	private class InstructionsAdapter extends RecyclerView.Adapter<InstructionsAdapter.ViewHolder> {
		private List<InstructionItem> items;
		private String currentHighlightQuery = "";
		
		public InstructionsAdapter(List<InstructionItem> items) {
			this.items = items;
		}
		
		public void updateItems(List<InstructionItem> newItems, String query) {
			this.items = newItems;
			this.currentHighlightQuery = query;
			notifyDataSetChanged();
		}
		
		@NonNull
		@Override
		public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.item_instruction, parent, false);
			
			return new ViewHolder(view);
		}
		
		@Override
		public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
			InstructionItem item = items.get(position);
			
			if (!TextUtils.isEmpty(currentHighlightQuery)) {
				holder.headerText.setText(getHighlightedText(item.getHeader(), currentHighlightQuery));
				
				StringBuilder contentBuilder = new StringBuilder();
				for (String line : item.getContent()) {
					contentBuilder.append(line).append("\n");
				}
				holder.contentText.setText(getHighlightedText(
				contentBuilder.toString().trim(), 
				currentHighlightQuery));
			} else {
				holder.headerText.setText(item.getHeader());
				
				StringBuilder contentBuilder = new StringBuilder();
				for (String line : item.getContent()) {
					contentBuilder.append(line).append("\n");
				}
				holder.contentText.setText(contentBuilder.toString().trim());
			}
		}
		
		private SpannableString getHighlightedText(String text, String query) {
			SpannableString spannable = new SpannableString(text);
			if (TextUtils.isEmpty(query)) {
				return spannable;
			}
			
			String textLower = text.toLowerCase();
			String queryLower = query.toLowerCase();
			int index = textLower.indexOf(queryLower);
			
			while (index >= 0) {
				spannable.setSpan(new StyleSpan(Typeface.BOLD), 
				index, index + query.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
				index = textLower.indexOf(queryLower, index + query.length());
			}
			
			return spannable;
		}
		
		@Override
		public int getItemCount() {
			return items.size();
		}
		
		class ViewHolder extends RecyclerView.ViewHolder {
			TextView headerText;
			TextView contentText;
			
			public ViewHolder(View view) {
				super(view);
				headerText = view.findViewById(R.id.header_text);
				contentText = view.findViewById(R.id.content_text);
				
				view.setClickable(true);
				view.setFocusable(true);
			}
		}
	}
}
