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


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/*
Author @developer-krushna
Thanks to GeeksForGeeks.com
*/

public class ItemMoveCallback extends ItemTouchHelper.Callback {
	
	private final ItemTouchHelperContract mAdapter;
	
	public ItemMoveCallback(ItemTouchHelperContract adapter) {
		mAdapter = adapter;
	}
	
	@Override
	public boolean isLongPressDragEnabled() {
		return false;
	}
	
	@Override
	public boolean isItemViewSwipeEnabled() {
		return false;
	}
	
	@Override
	public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
		
	}
	
	@Override
	public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
		int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
		return makeMovementFlags(dragFlags, 0);
	}
	
	@Override
	public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
	RecyclerView.ViewHolder target) {
		mAdapter.onRowMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
		return true;
	}
	
	@Override
	public void onSelectedChanged(RecyclerView.ViewHolder viewHolder,
	int actionState) {
		
		if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
			if (viewHolder instanceof RecyclerViewAdapter.MyViewHolder) {
				RecyclerViewAdapter.MyViewHolder myViewHolder =
				(RecyclerViewAdapter.MyViewHolder) viewHolder;
				mAdapter.onRowSelected(myViewHolder);
			}
		}
		
		super.onSelectedChanged(viewHolder, actionState);
	}
	
	@Override
	public void clearView(RecyclerView recyclerView,
	RecyclerView.ViewHolder viewHolder) {
		super.clearView(recyclerView, viewHolder);
		
		if (viewHolder instanceof RecyclerViewAdapter.MyViewHolder) {
			RecyclerViewAdapter.MyViewHolder myViewHolder =
			(RecyclerViewAdapter.MyViewHolder) viewHolder;
			mAdapter.onRowClear(myViewHolder);
		}
	}
	
	public interface ItemTouchHelperContract {
		
		void onRowMoved(int fromPosition, int toPosition);
		
		void onRowSelected(RecyclerViewAdapter.MyViewHolder myViewHolder);
		
		void onRowClear(RecyclerViewAdapter.MyViewHolder myViewHolder);
	}
}
