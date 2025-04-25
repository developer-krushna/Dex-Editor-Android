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

package modder.hub.dexeditor.views;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.drawable.GradientDrawable;
import android.os.SystemClock;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import io.github.rosemoe.sora.widget.component.EditorAutoCompletion;
import io.github.rosemoe.sora.widget.component.CompletionLayout;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
/*
Author @developer-krushna
Thanks to @AndroidPrime
*/
public class CustomCompletionLayout implements CompletionLayout {
	
	private ListView listView;
	private LinearLayout rootView;
	private EditorAutoCompletion editorAutoCompletion;
	
	private boolean enabledAnimation = false;
	
	@Override
	public void setEditorCompletion(@NonNull EditorAutoCompletion completion) {
		editorAutoCompletion = completion;
	}
	
	@Override
	public void setEnabledAnimation(boolean enabledAnimation) {
		this.enabledAnimation = enabledAnimation;
		
		if (enabledAnimation) {
			LayoutTransition transition = new LayoutTransition();
			transition.enableTransitionType(LayoutTransition.CHANGING);
			transition.enableTransitionType(LayoutTransition.APPEARING);
			transition.enableTransitionType(LayoutTransition.DISAPPEARING);
			transition.enableTransitionType(LayoutTransition.CHANGE_APPEARING);
			transition.enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING);
			transition.addTransitionListener(new LayoutTransition.TransitionListener() {
				@Override
				public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
					// No implementation needed
				}
				
				@Override
				public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
					if (view == listView) {
						view.requestLayout();
					}
				}
			});
			rootView.setLayoutTransition(transition);
			listView.setLayoutTransition(transition);
		} else {
			rootView.setLayoutTransition(null);
			listView.setLayoutTransition(null);
		}
	}
	
	@NonNull
	@Override
	public View inflate(@NonNull Context context) {
		LinearLayout rootLayout = new LinearLayout(context);
		rootView = rootLayout;
		listView = new ListView(context);
		
		rootLayout.setOrientation(LinearLayout.VERTICAL);
		setEnabledAnimation(false);
		
		rootLayout.addView(listView, new LinearLayout.LayoutParams(-1, -1));
		
		// Setup background
		GradientDrawable gd = new GradientDrawable();
		gd.setCornerRadius(0);
		gd.setColor(Color.WHITE);
		rootLayout.setBackground(gd);
		
		setRootViewOutlineProvider(rootView);
		
		listView.setDividerHeight(2);
		
		// Traditional click listener instead of lambda
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				try {
					editorAutoCompletion.select(position);
				} catch (Exception e) {
					e.printStackTrace(System.err);
					Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		return rootLayout;
	}
	
	@Override
	public void onApplyColorScheme(@NonNull EditorColorScheme colorScheme) {
		GradientDrawable gd = new GradientDrawable();
		gd.setCornerRadius(0);
		gd.setStroke(1, colorScheme.getColor(EditorColorScheme.COMPLETION_WND_CORNER));
		gd.setColor(Color.WHITE);
		rootView.setBackground(gd);
		
		setRootViewOutlineProvider(rootView);
	}
	
	@Override
	public void setLoading(boolean state) {
		// No progress bar implementation
	}
	
	@NonNull
	@Override
	public ListView getCompletionList() {
		return listView;
	}
	
	private void performScrollList(int offset) {
		ListView adpView = getCompletionList();        
		long down = SystemClock.uptimeMillis();
		
		MotionEvent ev = MotionEvent.obtain(down, down, MotionEvent.ACTION_DOWN, 0, 0, 0);
		adpView.onTouchEvent(ev);
		ev.recycle();
		
		ev = MotionEvent.obtain(down, down, MotionEvent.ACTION_MOVE, 0, offset, 0);
		adpView.onTouchEvent(ev);
		ev.recycle();
		
		ev = MotionEvent.obtain(down, down, MotionEvent.ACTION_CANCEL, 0, offset, 0);
		adpView.onTouchEvent(ev);
		ev.recycle();
	}
	
	private void setRootViewOutlineProvider(View rootView) {
		rootView.setOutlineProvider(new ViewOutlineProvider() {
			@Override
			public void getOutline(View view, Outline outline) {
				outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 0);
			}
		});
		rootView.setClipToOutline(true);
	}
	
	@Override
	public void ensureListPositionVisible(int position, int increment) {
		listView.post(new Runnable() {
			@Override
			public void run() {
				while (listView.getFirstVisiblePosition() + 1 > position && listView.canScrollList(-1)) {
					performScrollList(increment / 2);
				}
				while (listView.getLastVisiblePosition() - 1 < position && listView.canScrollList(1)) {
					performScrollList(-increment / 2);
				}
			}
		});
	}
}
