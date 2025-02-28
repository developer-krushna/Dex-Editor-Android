/*
* Dex-Editor-Android an Advanced Dex Editor for Android 
* Copyright 2024, developer-krushna
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

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.TooltipCompat;
import io.github.rosemoe.sora.event.Event;
import io.github.rosemoe.sora.event.EventReceiver;
import io.github.rosemoe.sora.event.HandleStateChangeEvent;
import io.github.rosemoe.sora.event.LongPressEvent;
import io.github.rosemoe.sora.event.ScrollEvent;
import io.github.rosemoe.sora.event.SelectionChangeEvent;
import io.github.rosemoe.sora.event.Unsubscribe;
import io.github.rosemoe.sora.text.Content;
import io.github.rosemoe.sora.text.Cursor;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.EditorTouchEventHandler;
import io.github.rosemoe.sora.widget.component.EditorTextActionWindow;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import modder.hub.dexeditor.R;
/*
Author @developer-krushna
Code fixed comments by ChatGPT
Special thanks to @AndroidPrime for
peoviding instructions about sora editor functinalities
*/
public class TextActionWindow extends EditorTextActionWindow implements View.OnLongClickListener {
	private static final long DISPLAY_DELAY = 200;
	private ItemClickCallBack actionCallback;
	private ImageButton copyButton;
	private ImageButton cutButton;
	private CodeEditor codeEditor;
	private boolean isEnabled;
	private String extractedTextWithLAndSemiColon;
	private ImageButton gotoButton;
	private EditorTouchEventHandler touchEventHandler;
	private int lastEventCause;
	private int lastCursorPosition;
	private long lastScrollTime;
	private ImageButton longSelectButton;
	private ImageButton pasteButton;
	private View rootView;
	private ImageButton selectAllButton;
	private String selectedText;
	private ImageButton translateButton;
	
	public interface ItemClickCallBack {
		void onClickGoTo(View view, String text);
		void onClickTranslate(View view, String text);
		void onLongClickTranslate(View view);
	}
	
	public TextActionWindow(CodeEditor codeEditor) {
		super(codeEditor);
		this.isEnabled = true;
		this.extractedTextWithLAndSemiColon = "";
		this.selectedText = null;
		this.codeEditor = codeEditor;
	}
	
	public TextActionWindow(final CodeEditor codeEditor, ItemClickCallBack actionCallback) {
		super(codeEditor);
		this.isEnabled = true;
		this.extractedTextWithLAndSemiColon = "";
		this.selectedText = null;
		this.codeEditor = codeEditor;
		this.actionCallback = actionCallback;
		this.touchEventHandler = codeEditor.getEventHandler();
		
		View inflatedView = LayoutInflater.from(codeEditor.getContext()).inflate(R.layout.text_action_panel, (ViewGroup) null);
		
		this.selectAllButton = (ImageButton) inflatedView.findViewById(R.id.panel_btn_select_all);
		this.cutButton = (ImageButton) inflatedView.findViewById(R.id.panel_btn_cut);
		this.copyButton = (ImageButton) inflatedView.findViewById(R.id.panel_btn_copy);
		this.longSelectButton = (ImageButton) inflatedView.findViewById(R.id.panel_btn_long_select);
		this.pasteButton = (ImageButton) inflatedView.findViewById(R.id.panel_btn_paste);
		this.gotoButton = (ImageButton) inflatedView.findViewById(R.id.goto_btn);
		this.translateButton = (ImageButton) inflatedView.findViewById(R.id.translate_btn);
		
		this.selectAllButton.setOnClickListener(this);
		this.cutButton.setOnClickListener(this);
		this.copyButton.setOnClickListener(this);
		this.pasteButton.setOnClickListener(this);
		this.longSelectButton.setOnClickListener(this);
		this.gotoButton.setOnClickListener(this);
		this.translateButton.setOnClickListener(this);
		this.translateButton.setOnLongClickListener(this);
		
		/* Tooltip text */
		setTooltipText(this.copyButton, codeEditor.getContext().getString(R.string.copy));
		setTooltipText(this.pasteButton, codeEditor.getContext().getString(R.string.paste));
		setTooltipText(this.translateButton, codeEditor.getContext().getString(R.string.translate));
		setTooltipText(this.gotoButton, codeEditor.getContext().getString(R.string.go_to));
		setTooltipText(this.longSelectButton, codeEditor.getContext().getString(R.string.long_select));
		setTooltipText(this.cutButton, codeEditor.getContext().getString(R.string.cut));
		setTooltipText(this.selectAllButton, codeEditor.getContext().getString(R.string.select_all));
		
		
		GradientDrawable backgroundDrawable = new GradientDrawable();
		backgroundDrawable.setCornerRadius(codeEditor.getDpUnit() * 5.0f);
		backgroundDrawable.setColor(-1);
		inflatedView.setBackground(backgroundDrawable);
		setContentView(inflatedView);
		setSize(0, (int) (this.codeEditor.getDpUnit() * 48.0f));
		this.rootView = inflatedView;
		updateButtonStates();
		codeEditor.subscribeEvent(SelectionChangeEvent.class, new EventReceiver() {
			public final void onReceive(Event event, Unsubscribe unsubscribe) {
				TextActionWindow.this.onSelectionChanged(codeEditor, (SelectionChangeEvent) event, unsubscribe);
			}
		});
		codeEditor.subscribeEvent(ScrollEvent.class, new EventReceiver() {
			public final void onReceive(Event event, Unsubscribe unsubscribe) {
				TextActionWindow.this.onScrollEvent((ScrollEvent) event, unsubscribe);
			}
		});
		codeEditor.subscribeEvent(HandleStateChangeEvent.class, new EventReceiver() {
			public final void onReceive(Event event, Unsubscribe unsubscribe) {
				TextActionWindow.this.onHandleStateChanged((HandleStateChangeEvent) event, unsubscribe);
			}
		});
		codeEditor.subscribeEvent(LongPressEvent.class, new EventReceiver() {
			public final void onReceive(Event event, Unsubscribe unsubscribe) {
				TextActionWindow.this.onLongPressEvent(codeEditor, (LongPressEvent) event, unsubscribe);
			}
		});
		codeEditor.subscribeEvent(HandleStateChangeEvent.class, new EventReceiver() {
			public final void onReceive(Event event, Unsubscribe unsubscribe) {
				TextActionWindow.this.onHandleStateChangedFinal(codeEditor, (HandleStateChangeEvent) event, unsubscribe);
			}
		});
		getPopup().setAnimationStyle(R.style.text_action_popup_animation);
	}
	
	private void onSelectionChanged(CodeEditor codeEditor, SelectionChangeEvent selectionChangeEvent, Unsubscribe unsubscribe) {
		updateButtonStates();
		String extractedText = extractTextWithLAndSemiColon(codeEditor.getText().getLine(codeEditor.getCursor().getLeftLine()).toString(), codeEditor.getCursor().getLeftColumn());
		this.extractedTextWithLAndSemiColon = extractedText;
		if (extractedText.equals("NotAvailable")) {
			this.gotoButton.setVisibility(View.GONE);
		} else {
			this.gotoButton.setVisibility(View.VISIBLE);
		}
	}
	
	private void onScrollEvent(ScrollEvent scrollEvent, Unsubscribe unsubscribe) {
		long lastScrollTime = this.lastScrollTime;
		long currentTime = System.currentTimeMillis();
		this.lastScrollTime = currentTime;
		if (currentTime - lastScrollTime >= DISPLAY_DELAY || this.lastEventCause == 6) {
			return;
		}
		postDisplayWindow();
	}
	
	private void onHandleStateChanged(HandleStateChangeEvent handleStateChangeEvent, Unsubscribe unsubscribe) {
		if (handleStateChangeEvent.isHeld()) {
			postDisplayWindow();
		}
	}
	
	private void onLongPressEvent(CodeEditor codeEditor, LongPressEvent longPressEvent, Unsubscribe unsubscribe) {
		if (codeEditor.getCursor().isSelected() && this.lastEventCause == 6) {
			int index = longPressEvent.getIndex();
			if (index >= codeEditor.getCursor().getLeft() && index <= codeEditor.getCursor().getRight()) {
				this.lastEventCause = 0;
				displayWindow();
			}
			longPressEvent.intercept(2);
		}
	}
	
	private void onHandleStateChangedFinal(final CodeEditor codeEditor, HandleStateChangeEvent handleStateChangeEvent, Unsubscribe unsubscribe) {
		if (handleStateChangeEvent.getEditor().getCursor().isSelected() || handleStateChangeEvent.getHandleType() != 0 || handleStateChangeEvent.isHeld()) {
			return;
		}
		displayWindow();
		codeEditor.postDelayedInLifecycle(new Runnable() {
			@Override
			public void run() {
				if (!codeEditor.getEventHandler().shouldDrawInsertHandle() && !codeEditor.getCursor().isSelected()) {
					TextActionWindow.this.dismiss();
				} else if (codeEditor.getCursor().isSelected()) {
				} else {
					codeEditor.postDelayedInLifecycle(this, 100L);
				}
			}
		}, 100L);
	}
	
	public void onClick(View view) {
		int id = view.getId();
		if (id == R.id.panel_btn_select_all) {
			this.codeEditor.selectAll();
			return;
		}
		if (id == R.id.panel_btn_cut) {
			if (this.codeEditor.getCursor().isSelected()) {
				this.codeEditor.cutText();
			}
		} else if (id == R.id.panel_btn_paste) {
			this.codeEditor.pasteText();
			CodeEditor codeEditor = this.codeEditor;
			codeEditor.setSelection(codeEditor.getCursor().getRightLine(), this.codeEditor.getCursor().getRightColumn());
		} else if (id == R.id.panel_btn_copy) {
			this.codeEditor.copyText();
			CodeEditor codeEditor2 = this.codeEditor;
			codeEditor2.setSelection(codeEditor2.getCursor().getRightLine(), this.codeEditor.getCursor().getRightColumn());
		} else if (id == R.id.panel_btn_long_select) {
			this.codeEditor.beginLongSelect();
		} else if (id == R.id.goto_btn) {
			this.actionCallback.onClickGoTo(view, this.extractedTextWithLAndSemiColon);
		} else if (id == R.id.translate_btn) {
			Cursor cursor = this.codeEditor.getCursor();
			if (cursor.isSelected()) {
				String selectedText = getSelectedText(this.codeEditor.getText(), cursor.getLeft(), cursor.getRight());
				this.selectedText = selectedText;
				if (selectedText != null) {
					this.actionCallback.onClickTranslate(view, selectedText);
				}
			}
		}
		dismiss();
	}
	
	@Override
	public boolean onLongClick(View view) {
		if (view.getId() == R.id.translate_btn) {
			this.actionCallback.onLongClickTranslate(view);
		}
		dismiss();
		return true;
	}
	
	public void setTooltipText(View view, String tooltipText) {
		if (Build.VERSION.SDK_INT >= 26) {
			TooltipCompat.setTooltipText(view, tooltipText);
		}
	}
	
	private void updateButtonStates() {
		updatePasteButtonState();
		updateCopyButtonVisibility();
		updateTranslateButtonVisibility();
		updatePasteButtonVisibility();
		updateCutButtonVisibility();
		updateLongSelectButtonVisibility();
	}
	
	private void updatePasteButtonState() {
		this.pasteButton.setEnabled(this.codeEditor.hasClip());
	}
	
	private void updateCopyButtonVisibility() {
		int visibility;
		if (this.codeEditor.getCursor().isSelected()) {
			visibility = View.VISIBLE;
		} else {
			visibility = View.GONE;
		}
		this.copyButton.setVisibility(visibility);
	}
	
	private void updateTranslateButtonVisibility() {
		int visibility;
		if (this.codeEditor.getCursor().isSelected()) {
			visibility = View.VISIBLE;
		} else {
			visibility = View.GONE;
		}
		this.translateButton.setVisibility(visibility);
	}
	
	private void updatePasteButtonVisibility() {
		int visibility;
		if (this.codeEditor.isEditable()) {
			visibility = View.VISIBLE;
		} else {
			visibility = View.GONE;
		}
		this.pasteButton.setVisibility(visibility);
	}
	
	
	private void updateCutButtonVisibility() {
		int visibility;
		boolean isSelected = this.codeEditor.getCursor().isSelected();
		boolean isEditable = this.codeEditor.isEditable();
		
		if (isSelected && isEditable) {
			visibility = View.VISIBLE;
		} else {
			visibility = View.GONE;
		}
		this.cutButton.setVisibility(visibility);
	}
	
	
	private void updateLongSelectButtonVisibility() {
		// If the text is selected then gone the long select button else show
		if (this.codeEditor.getCursor().isSelected()) {
			this.longSelectButton.setVisibility(View.GONE);
		} else {
			this.longSelectButton.setVisibility(View.VISIBLE);
		}
	}
	
	protected String getSelectedText(@NonNull CharSequence charSequence, int start, int end) {
		if (end < start) {
			return null;
		}
		return charSequence instanceof Content ? ((Content) charSequence).substring(start, end) : charSequence.subSequence(start, end).toString();
	}
	
	private void postDisplayWindow() {
		if (isShowing()) {
			dismiss();
			if (this.codeEditor.getCursor().isSelected()) {
				this.codeEditor.postDelayedInLifecycle(new Runnable() {
					@Override
					public void run() {
						if (!TextActionWindow.this.touchEventHandler.hasAnyHeldHandle() && !TextActionWindow.this.codeEditor.getSnippetController().isInSnippet() && System.currentTimeMillis() - TextActionWindow.this.lastScrollTime > DISPLAY_DELAY && TextActionWindow.this.codeEditor.getScroller().isFinished()) {
							TextActionWindow.this.displayWindow();
						} else {
							TextActionWindow.this.codeEditor.postDelayedInLifecycle(this, DISPLAY_DELAY);
						}
					}
				}, DISPLAY_DELAY);
			}
		}
	}
	
	public String extractTextWithLAndSemiColon(String text, int position) {
		Matcher matcher = Pattern.compile("(L[^;]+;)").matcher(text);
		while (matcher.find()) {
			if (matcher.start() <= position && position < matcher.end()) {
				return matcher.group();
			}
		}
		Matcher matcher2 = Pattern.compile(";->([^:()]+):|;->([^()]+)\\(([^)]*)\\)").matcher(text);
		if (matcher2.find()) {
			if ((matcher2.start(1) == -1 || matcher2.start(1) - 2 > position || position >= matcher2.end(1)) && (matcher2.start(2) == -1 || matcher2.start(2) - 2 > position || position >= matcher2.end(2))) {
				return (matcher2.start(3) == -1 || text.charAt(matcher2.start(3) - 1) == '(' || text.charAt(matcher2.start(3) - 1) == ':' || position != matcher2.start(3) - 1) ? "NotAvailable" : matcher2.group(3);
			}
			int lastSpaceIndex = text.lastIndexOf(32, Math.max(matcher2.start(1), matcher2.start(2)) - 2);
			return lastSpaceIndex != -1 ? text.substring(lastSpaceIndex + 1) : "NotAvailable";
		}
		return "NotAvailable";
	}
	
	public static void applyRippleEffect(View view, String backgroundColor, String strokeColor, double cornerRadius, double strokeWidth, String rippleColor) {
		GradientDrawable gradientDrawable = new GradientDrawable();
		gradientDrawable.setColor(Color.parseColor(backgroundColor));
		gradientDrawable.setCornerRadius((float) cornerRadius);
		gradientDrawable.setStroke((int) strokeWidth, Color.parseColor("#" + rippleColor.replace("#", "")));
		view.setBackground(new RippleDrawable(new ColorStateList(new int[][]{new int[0]}, new int[]{Color.parseColor("#FF757575")}), gradientDrawable, null));
		view.setElevation(5.0f);
	}
}
