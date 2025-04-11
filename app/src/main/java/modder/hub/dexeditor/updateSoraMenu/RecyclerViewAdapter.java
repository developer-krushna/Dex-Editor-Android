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


package modder.hub.dexeditor.updateSoraMenu;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import modder.hub.dexeditor.R;

/*
Author @developer-krushna
Thanks to GeeksForGeeks.com
*/

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements ItemMoveCallback.ItemTouchHelperContract {
	
	private ArrayList<HashMap<String, Object>> data;
	private final StartDragListener mStartDragListener;
	private Context context;
	private SharedPreferences prefs;
	
	private final Map<String, Integer> buttonIcons = new HashMap<String, Integer>() {{
			put("panel_btn_select_all", R.drawable.ic_selectall_mt);
			put("panel_btn_copy", R.drawable.ic_copy_mt);
			put("panel_btn_paste", R.drawable.ic_paste_mt);
			put("goto_btn", R.drawable.ic_goto_mt);
			put("translate_btn", R.drawable.ic_translate_mt);
			put("panel_btn_cut", R.drawable.ic_cut_mt);
			put("comment_btn", R.drawable.ic_hash_mt);
			put("openLink_btn", R.drawable.ic_link_mt);
			put("share_btn", R.drawable.ic_share_mt);
			put("panel_btn_long_select", R.drawable.ic_text_select_start_mt);
			put("delete_btn", R.drawable.ic_delete_mt);
			put("customize_btn", R.drawable.ic_setting_mt);
		}};
	
	public class MyViewHolder extends RecyclerView.ViewHolder {
		TextView mTitle;
		View rowView;
		ImageView imageView;
		RelativeLayout relativeLayout;
		Switch disableSwitch;
		
		public MyViewHolder(View itemView) {
			super(itemView);
			rowView = itemView;
			mTitle = itemView.findViewById(R.id.txtTitle);
			imageView = itemView.findViewById(R.id.imageView);
			relativeLayout = itemView.findViewById(R.id.relativeLayout);
			disableSwitch = itemView.findViewById(R.id.disableSwitch);
		}
	}
	
	public RecyclerViewAdapter(ArrayList<HashMap<String, Object>> data, StartDragListener startDragListener, Context context) {
		this.data = data;
		mStartDragListener = startDragListener;
		this.context = context;
		prefs = context.getSharedPreferences("editor_prefs", Context.MODE_PRIVATE);
	}
	
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.floating_menu_items, parent, false);
		return new MyViewHolder(itemView);
	}
	
	@Override
	public void onBindViewHolder(final MyViewHolder holder, final int position) {
		HashMap<String, Object> item = data.get(position);
		String id = (String) item.get("id");
		String title = (String) item.get("title");
		boolean disabled = item.containsKey("disabled") && (boolean) item.get("disabled");
		
		holder.mTitle.setText(title);
		
		if (buttonIcons.containsKey(id)) {
			holder.imageView.setImageResource(buttonIcons.get(id));
		}
		
		holder.disableSwitch.setChecked(!disabled);
		
		holder.disableSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				item.put("disabled", !isChecked);
				updateJson();
			}
		});
		
		// Drag handling
		holder.imageView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					mStartDragListener.requestDrag(holder);
				}
				return false;
			}
		});
	}
	
	@Override
	public int getItemCount() {
		return data.size();
	}
	
	@Override
	public void onRowMoved(int fromPosition, int toPosition) {
		if (fromPosition < toPosition) {
			for (int i = fromPosition; i < toPosition; i++) {
				Collections.swap(data, i, i + 1);
			}
		} else {
			for (int i = fromPosition; i > toPosition; i--) {
				Collections.swap(data, i, i - 1);
			}
		}
		notifyItemMoved(fromPosition, toPosition);
		updateJson();
	}
	
	private void updateJson() {
		try {
			String jsonContent = new Gson().toJson(data);
			prefs.edit().putString("menu_order", jsonContent).apply();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onRowSelected(MyViewHolder myViewHolder) {
	}
	
	@Override
	public void onRowClear(MyViewHolder myViewHolder) {
	}
}
