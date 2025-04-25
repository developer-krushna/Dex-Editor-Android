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

package modder.hub.dexeditor.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import modder.hub.dexeditor.R;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;

/*
Author @developer-krushna
Code fixed and enhancement by ChatGPT
*/

public class ListDialog extends Dialog {
	private final RecyclerView recyclerView;
	private final LabelAdapter adapter;
	private final List<String> originalItems;
	private final int highlightLineNumber;
	private final int highlightColor = 0xFF42A5F5;
	private OnLabelClickListener labelClickListener;
	
	private String currentQuery = "";
	
	
	public ListDialog(Context context, List<String> items, String query, int editorLineNumber) {
		super(context);
		this.originalItems = new ArrayList<>(items);
		this.highlightLineNumber = editorLineNumber + 1;
		
		recyclerView = new RecyclerView(context);
		recyclerView.setLayoutParams(new ViewGroup.LayoutParams(
		ViewGroup.LayoutParams.MATCH_PARENT,
		ViewGroup.LayoutParams.WRAP_CONTENT
		));
		recyclerView.setLayoutManager(new LinearLayoutManager(context));
		
		adapter = new LabelAdapter();
		recyclerView.setAdapter(adapter);
		
		updateFilter(query);
		setContentView(recyclerView);
	}
	
	private class LabelAdapter extends RecyclerView.Adapter<LabelAdapter.ViewHolder> {
		private List<String> filteredItems = new ArrayList<>();
		
		class ViewHolder extends RecyclerView.ViewHolder {
			TextView textView;
			LinearLayout container;
			
			ViewHolder(View itemView) {
				super(itemView);
				textView = itemView.findViewById(R.id.result_item_label);
				container = itemView.findViewById(R.id.item_container);
				
				container.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (labelClickListener != null) {
							labelClickListener.onLabelClick(filteredItems.get(getAdapterPosition()));
						}
					}
				});
			}
		}
		
		@NonNull
		@Override
		public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.item_goto_text, parent, false);
			return new ViewHolder(view);
		}
		
		@Override
		public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
			String item = filteredItems.get(position);
			int lineNumber = extractLineNumber(item);
			
			// Create spannable string for partial formatting
			SpannableString spannable = new SpannableString(item);
			
			// Apply line number highlighting
			if (lineNumber == highlightLineNumber) {
				spannable.setSpan(new ForegroundColorSpan(highlightColor), 
				0, item.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
			
			// Apply query text bolding if query exists
			if (!TextUtils.isEmpty(currentQuery)) {
				String lowerItem = item.toLowerCase();
				String lowerQuery = currentQuery.toLowerCase();
				int index = lowerItem.indexOf(lowerQuery);
				
				while (index >= 0) {
					spannable.setSpan(new StyleSpan(Typeface.BOLD), 
					index, index + currentQuery.length(), 
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					index = lowerItem.indexOf(lowerQuery, index + currentQuery.length());
				}
			}
			
			holder.textView.setText(spannable);
		}
		
		
		@Override
		public int getItemCount() {
			return filteredItems.size();
		}
		
		void updateItems(List<String> items) {
			filteredItems = new ArrayList<>(items);
			notifyDataSetChanged();
		}
	}
	
	private int extractLineNumber(String item) {
		try {
			return Integer.parseInt(item.substring(1, item.indexOf(']')));
		} catch (Exception e) {
			return -1;
		}
	}
	
	public void updateFilter(String query) {
		this.currentQuery = query != null ? query : "";
		List<String> filtered = new ArrayList<>();
		if (TextUtils.isEmpty(query)) {
			filtered.addAll(originalItems);
		} else {
			for (String item : originalItems) {
				if (item.toLowerCase().contains(query.toLowerCase())) {
					filtered.add(item);
				}
			}
		}
		adapter.updateItems(filtered);
	}
	
	public void setOnLabelClickListener(OnLabelClickListener listener) {
		this.labelClickListener = listener;
	}
	
	public interface OnLabelClickListener {
		void onLabelClick(String selectedLabel);
	}
}
