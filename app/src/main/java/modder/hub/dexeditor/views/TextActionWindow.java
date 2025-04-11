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

import android.content.Context;
import android.graphics.Color;
import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.*;
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
import android.webkit.*;
import android.widget.*;
import android.content.res.*;
import android.graphics.*;
import android.content.*;
import android.graphics.drawable.*;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.TooltipCompat;
import io.github.rosemoe.sora.event.*;
import io.github.rosemoe.sora.text.Content;
import io.github.rosemoe.sora.text.Cursor;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.EditorTouchEventHandler;
import io.github.rosemoe.sora.widget.component.EditorTextActionWindow;
import io.github.rosemoe.sora.text.CharPosition;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modder.hub.dexeditor.R;
import modder.hub.dexeditor.activity.*;

/*
Author @developer-krushna
Code fixed/enhancement/some hidden ideas/comments by ChatGPT
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
	private ImageButton commentButton;
	private ImageButton shareButton;
	private ImageButton openLinkButton;
	private ImageButton customizeButton;
	private ImageButton deleteButton;
	
	private Map<String, ImageButton> buttonMap = new HashMap<>();
	private ArrayList<String> menuItems = new ArrayList<>();
	private static final Set<String> VALID_TLDS = new HashSet<>();
	
	static {
		VALID_TLDS.add("com");
		VALID_TLDS.add("org");
		VALID_TLDS.add("net");
		VALID_TLDS.add("in");
		VALID_TLDS.add("cn");
		VALID_TLDS.add("io");
		VALID_TLDS.add("co");
		VALID_TLDS.add("ai");
	}
	
	
	public interface ItemClickCallBack {
		void onClickGoTo(View view, String text);
		void onClickTranslate(View view, String text);
		void onLongClickTranslate(View view);
	}
	
	public TextActionWindow(CodeEditor codeEditor) {
		this(codeEditor, null);
	}
	
	public TextActionWindow(final CodeEditor codeEditor, ItemClickCallBack actionCallback) {
		super(codeEditor);
		this.isEnabled = true;
		this.extractedTextWithLAndSemiColon = "";
		this.selectedText = null;
		this.codeEditor = codeEditor;
		this.actionCallback = actionCallback;
		this.touchEventHandler = codeEditor.getEventHandler();
		
		// Create root container
		FrameLayout rootContainer = new FrameLayout(codeEditor.getContext());
		this.rootView = rootContainer;
		
		// Load menu items from JSON
		loadMenuItemsFromJson();
		
		// Initialize buttons
		initializeButtons(rootContainer);
		
		// Set background
		GradientDrawable backgroundDrawable = new GradientDrawable();
		backgroundDrawable.setCornerRadius(codeEditor.getDpUnit() * 5.0f);
		backgroundDrawable.setColor(-1);
		rootContainer.setBackground(backgroundDrawable);
		
		setContentView(rootContainer);
		setSize(0, (int) (this.codeEditor.getDpUnit() * 48.0f));
		
		updateButtonStates();
		
		// Event subscriptions
		codeEditor.subscribeEvent(SelectionChangeEvent.class, new EventReceiver<SelectionChangeEvent>() {
			@Override
			public void onReceive(SelectionChangeEvent event, Unsubscribe unsubscribe) {
				onSelectionChanged(codeEditor, event, unsubscribe);
			}
		});
		
		codeEditor.subscribeEvent(ScrollEvent.class, new EventReceiver<ScrollEvent>() {
			@Override
			public void onReceive(ScrollEvent event, Unsubscribe unsubscribe) {
				onScrollEvent(event, unsubscribe);
			}
		});
		
		codeEditor.subscribeEvent(HandleStateChangeEvent.class, new EventReceiver<HandleStateChangeEvent>() {
			@Override
			public void onReceive(HandleStateChangeEvent event, Unsubscribe unsubscribe) {
				onHandleStateChanged(event, unsubscribe);
			}
		});
		
		codeEditor.subscribeEvent(LongPressEvent.class, new EventReceiver<LongPressEvent>() {
			@Override
			public void onReceive(LongPressEvent event, Unsubscribe unsubscribe) {
				onLongPressEvent(codeEditor, event, unsubscribe);
			}
		});
		
		getPopup().setAnimationStyle(R.style.text_action_popup_animation);
	}
	
	private void loadMenuItemsFromJson() {
		// 1. Initialize SharedPreferences
		SharedPreferences prefs = codeEditor.getContext()
		.getSharedPreferences("editor_prefs", Context.MODE_PRIVATE);
		
		// 2. Check if config exists in SharedPreferences
		String jsonConfig = prefs.getString("menu_order", null);
		
		// 3. If not exists, save default config first
		if (jsonConfig == null) {
			jsonConfig = 
			"[" +
			"{\"id\":\"panel_btn_select_all\",\"title\":\"Select All\",\"disabled\":false}," +
			"{\"id\":\"panel_btn_copy\",\"title\":\"Copy\",\"disabled\":false}," +
			"{\"id\":\"panel_btn_paste\",\"title\":\"Paste\",\"disabled\":false}," +
			"{\"id\":\"goto_btn\",\"title\":\"Go To\",\"disabled\":false}," +
			"{\"id\":\"translate_btn\",\"title\":\"Translate\",\"disabled\":false}," +
			"{\"id\":\"panel_btn_cut\",\"title\":\"Cut\",\"disabled\":false}," +
			"{\"id\":\"comment_btn\",\"title\":\"Toggle comment\",\"disabled\":false}," +
			"{\"id\":\"openLink_btn\",\"title\":\"Open link\",\"disabled\":false}," +
			"{\"id\":\"share_btn\",\"title\":\"Share\",\"disabled\":false}," +
			"{\"id\":\"panel_btn_long_select\",\"title\":\"Long Select\",\"disabled\":false}," +
			"{\"id\":\"delete_btn\",\"title\":\"Delete\",\"disabled\":false}," +
			"{\"id\":\"customize_btn\",\"title\":\"Customize\",\"disabled\":false}" +
			"]";
			prefs.edit().putString("menu_order", jsonConfig).apply();
		}
		
		// 4. Parse the JSON (only using IDs as before)
		try {
			JSONArray jsonArray = new JSONArray(jsonConfig);
			menuItems.clear();
			
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject item = jsonArray.getJSONObject(i);
				// Only add if not disabled
				if (!item.optBoolean("disabled", false)) {
					menuItems.add(item.getString("id"));
				}
			}
		} catch (Exception e) {
			// Fallback to default order (without titles)
			menuItems.clear();
			menuItems.add("panel_btn_select_all");
			menuItems.add("panel_btn_copy");
			menuItems.add("panel_btn_paste");
			menuItems.add("goto_btn");
			menuItems.add("translate_btn");
			menuItems.add("panel_btn_cut");
			menuItems.add("comment_btn");
			menuItems.add("openLink_btn");
			menuItems.add("share_btn");
			menuItems.add("panel_btn_long_select");
			menuItems.add("delete_btn");
		}
	}
	
	private void initializeButtons(ViewGroup parent) {
		parent.removeAllViews();
		Context context = codeEditor.getContext();
		
		HorizontalScrollView scrollView = new HorizontalScrollView(context);
		LinearLayout container = new LinearLayout(context);
		container.setOrientation(LinearLayout.HORIZONTAL);
		container.setGravity(Gravity.CENTER_VERTICAL);
		scrollView.addView(container);
		parent.addView(scrollView);
		
		// Get selectable background
		TypedValue outValue = new TypedValue();
		context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
		
		int buttonSize = (int) (45 * codeEditor.getDpUnit());
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(buttonSize, buttonSize);
		
		// Define all possible buttons with their resources
		Map<String, ButtonConfig> allButtons = new HashMap<>();
		allButtons.put("panel_btn_select_all", new ButtonConfig(R.drawable.ic_selectall_mt, R.string.select_all));
		allButtons.put("panel_btn_copy", new ButtonConfig(R.drawable.ic_copy_mt, R.string.copy));
		allButtons.put("panel_btn_paste", new ButtonConfig(R.drawable.ic_paste_mt, R.string.paste));
		allButtons.put("goto_btn", new ButtonConfig(R.drawable.ic_goto_mt, R.string.go_to));
		allButtons.put("translate_btn", new ButtonConfig(R.drawable.ic_translate_mt, R.string.translate));
		allButtons.put("panel_btn_cut", new ButtonConfig(R.drawable.ic_cut_mt, R.string.cut));
		allButtons.put("comment_btn", new ButtonConfig(R.drawable.ic_hash_mt, R.string.comment));
		allButtons.put("openLink_btn", new ButtonConfig(R.drawable.ic_link_mt, R.string.link));
		allButtons.put("share_btn", new ButtonConfig(R.drawable.ic_share_mt, R.string.share));
		allButtons.put("panel_btn_long_select", new ButtonConfig(R.drawable.ic_text_select_start_mt, R.string.long_select));
		allButtons.put("delete_btn", new ButtonConfig(R.drawable.ic_delete_mt, R.string.delete));
		allButtons.put("customize_btn", new ButtonConfig(R.drawable.ic_setting_mt, R.string.customize));
		
		// Create buttons in JSON-defined order
		for (String buttonId : menuItems) {
			ButtonConfig config = allButtons.get(buttonId);
			if (config == null) {
				continue; // Skip unknown button IDs
			}
			
			ImageButton button = new ImageButton(context);
			button.setTag(buttonId);
			button.setLayoutParams(params);
			button.setBackgroundResource(outValue.resourceId);
			button.setImageResource(config.iconRes);
			button.setOnClickListener(this);
			
			if ("translate_btn".equals(buttonId)) {
				button.setOnLongClickListener(this);
			}
			
			container.addView(button);
			buttonMap.put(buttonId, button);
			
			setTooltipText(button, context.getString(config.tooltipRes));
		}
		
		// Initialize all button variables
		this.selectAllButton = buttonMap.get("panel_btn_select_all");
		this.copyButton = buttonMap.get("panel_btn_copy");
		this.pasteButton = buttonMap.get("panel_btn_paste"); 
		this.gotoButton = buttonMap.get("goto_btn");
		this.translateButton = buttonMap.get("translate_btn");
		this.cutButton = buttonMap.get("panel_btn_cut");
		this.longSelectButton = buttonMap.get("panel_btn_long_select");
		this.commentButton = buttonMap.get("comment_btn");
		this.openLinkButton = buttonMap.get("openLink_btn");
		this.shareButton = buttonMap.get("share_btn");
		this.deleteButton = buttonMap.get("delete_btn");
		this.customizeButton = buttonMap.get("customize_btn");
	}
	
	private static class ButtonConfig {
		final int iconRes;
		final int tooltipRes;
		
		ButtonConfig(int iconRes, int tooltipRes) {
			this.iconRes = iconRes;
			this.tooltipRes = tooltipRes;
		}
	}
	
	// Updated setTooltipText with null check
	public void setTooltipText(View view, String tooltipText) {
		if (view != null && Build.VERSION.SDK_INT >= 26) {
			TooltipCompat.setTooltipText(view, tooltipText);
		}
	}
	
	
	@Override
	public void onClick(View view) {
		String buttonId = (String) view.getTag();
		Cursor cursor = this.codeEditor.getCursor();
		if (buttonId == null) return;
		
		switch (buttonId) {
			case "panel_btn_select_all":
			this.codeEditor.selectAll();
			break;
			case "panel_btn_cut":
			if (cursor.isSelected()) {
				this.codeEditor.cutText();
			}
			break;
			case "panel_btn_copy":
			this.codeEditor.copyText();
			this.codeEditor.setSelection(cursor.getRightLine(), cursor.getRightColumn());
			break;
			case "panel_btn_paste":
			this.codeEditor.pasteText();
			this.codeEditor.setSelection(cursor.getRightLine(), cursor.getRightColumn());
			break;
			case "panel_btn_long_select":
			this.codeEditor.beginLongSelect();
			break;
			case "goto_btn":
			this.actionCallback.onClickGoTo(view, this.extractedTextWithLAndSemiColon);
			break;
			case "comment_btn" :
			toggleComment();
			break;
			case "delete_btn" :
			this.codeEditor.deleteText();
			break;
			case "customize_btn" :
			Intent intent = new Intent(codeEditor.getContext(), EditFloatingMenusActivity.class);
			codeEditor.getContext().startActivity(intent);
			break;
			case "share_btn" :
			if (cursor.isSelected()) {
				String selectedText = getSelectedText(this.codeEditor.getText(), cursor.getLeft(), cursor.getRight());
				this.selectedText = selectedText;
				if (selectedText != null) {
					try{
						Intent shareIntent = new Intent(Intent.ACTION_SEND);
						shareIntent.setType("text/plain");
						shareIntent.putExtra(Intent.EXTRA_TEXT, selectedText);
						codeEditor.getContext().startActivity(Intent.createChooser(shareIntent, "Share Text"));
					}catch(Exception e){}
				}
			}
			break;
			case "openLink_btn" :
			if (cursor.isSelected()) {
				String selectedText = getSelectedText(this.codeEditor.getText(), cursor.getLeft(), cursor.getRight());
				this.selectedText = selectedText;
				if (selectedText != null) {
					openLinkInBrowser(codeEditor.getContext(), selectedText);
				}
			}
			break;
			case "translate_btn":
			if (cursor.isSelected()) {
				String selectedText = getSelectedText(this.codeEditor.getText(), cursor.getLeft(), cursor.getRight());
				this.selectedText = selectedText;
				if (selectedText != null) {
					this.actionCallback.onClickTranslate(view, selectedText);
				}
			}
			break;
		}
		dismiss();
	}
	
	@Override
	public boolean onLongClick(View view) {
		String buttonId = (String) view.getTag();
		if (buttonId != null && buttonId.equals("translate_btn")) {
			this.actionCallback.onLongClickTranslate(view);
			dismiss();
			return true;
		}
		return false;
	}
	
	
	private void onSelectionChanged(CodeEditor codeEditor, SelectionChangeEvent selectionChangeEvent, Unsubscribe unsubscribe) {
		updateButtonStates();
		String extractedText = extractTextWithLAndSemiColon(codeEditor.getText().getLine(codeEditor.getCursor().getLeftLine()).toString(), codeEditor.getCursor().getLeftColumn());
		this.extractedTextWithLAndSemiColon = extractedText;
		if(gotoButton != null){
			if (extractedText.equals("NotAvailable")) {
				this.gotoButton.setVisibility(View.GONE);
			} else {
				this.gotoButton.setVisibility(View.VISIBLE);
			}
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
	
	
	private void updateButtonStates() {
		updatePasteButtonState();
		updateCopyButtonVisibility();
		updateTranslateButtonVisibility();
		updatePasteButtonVisibility();
		updateCutButtonVisibility();
		updateLongSelectButtonVisibility();
		updateDeleteButtonVisibility();
		updateShareButtonVisibility();
		updateOpenLinkButtonVisibility();
        updateCommentButtonVisibility();
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
		if(copyButton != null){
			this.copyButton.setVisibility(visibility);
		}
	}
	
	private void updateOpenLinkButtonVisibility() {
		int visibility;
		Cursor cursor = this.codeEditor.getCursor();
		
		if (cursor.isSelected()) {
			String selectedText = getSelectedText(this.codeEditor.getText(), cursor.getLeft(), cursor.getRight());
			if(isLink(selectedText)){
				visibility = View.VISIBLE;
			} else {
				visibility = View.GONE;
			}
		} else {
			visibility = View.GONE;
		}
		if(openLinkButton != null){
			this.openLinkButton.setVisibility(visibility);
		}
	}
	
	private void updateDeleteButtonVisibility() {
		int visibility;
		if (this.codeEditor.getCursor().isSelected() && this.codeEditor.isEditable()) {
			visibility = View.VISIBLE;
		} else {
			visibility = View.GONE;
		}
		if(deleteButton != null){
			this.deleteButton.setVisibility(visibility);
		}
	}
    
    private void updateCommentButtonVisibility(){
        int visibility;
		if (this.codeEditor.isEditable()) {
			visibility = View.VISIBLE;
		} else {
			visibility = View.GONE;
		}
		if(commentButton != null){
			this.commentButton.setVisibility(visibility);
		}
    }
	
	private void updateShareButtonVisibility() {
		int visibility;
		if (this.codeEditor.getCursor().isSelected()) {
			visibility = View.VISIBLE;
		} else {
			visibility = View.GONE;
		}
		if(shareButton != null){
			this.shareButton.setVisibility(visibility);
		}
	}
	
	private void updateTranslateButtonVisibility() {
		int visibility;
		if (this.codeEditor.getCursor().isSelected()) {
			visibility = View.VISIBLE;
		} else {
			visibility = View.GONE;
		}
		if(translateButton != null){
			this.translateButton.setVisibility(visibility);
		}
	}
	
	private void updatePasteButtonVisibility() {
		int visibility;
		if (this.codeEditor.isEditable()) {
			visibility = View.VISIBLE;
		} else {
			visibility = View.GONE;
		}
		if(pasteButton != null){
			this.pasteButton.setVisibility(visibility);
		}
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
		if(longSelectButton != null){
			if (this.codeEditor.getCursor().isSelected()) {
				this.longSelectButton.setVisibility(View.GONE);
			} else {
				this.longSelectButton.setVisibility(View.VISIBLE);
			}
		}
	}
	
	public void toggleComment() {
		final CodeEditor editor = this.codeEditor;
		final Content text = editor.getText();
		final Cursor cursor = editor.getCursor();
		
		text.beginBatchEdit();
		try {
			if (cursor.isSelected()) {
				int startLine = cursor.getLeftLine();
				int endLine = cursor.getRightLine();
				
				// Phase 1: Check comment state (single pass)
				boolean allCommented = true;
				int[] firstCharPositions = new int[endLine - startLine + 1];
				
				for (int i = 0, line = startLine; line <= endLine; line++, i++) {
					String lineStr = text.getLineString(line);
					firstCharPositions[i] = getFirstNonWhitespace(lineStr);
					
					if (firstCharPositions[i] < lineStr.length() && 
					lineStr.charAt(firstCharPositions[i]) != '#') {
						allCommented = false;
					}
				}
				
				// Phase 2: Apply changes (single pass)
				for (int i = 0, line = startLine; line <= endLine; line++, i++) {
					int firstCharPos = firstCharPositions[i];
					String lineStr = text.getLineString(line);
					
					if (firstCharPos >= lineStr.length()) continue;
					
					if (allCommented) {
						if (lineStr.charAt(firstCharPos) == '#') {
							int endPos = firstCharPos + 1;
							if (endPos < lineStr.length() && lineStr.charAt(endPos) == ' ') {
								endPos++;
							}
							text.delete(line, firstCharPos, line, endPos);
						}
					} else {
						if (lineStr.charAt(firstCharPos) != '#') {
							text.insert(line, firstCharPos, "# ");
						}
					}
				}
				
				// Restore selection
				editor.setSelectionRegion(startLine, 0, endLine, text.getColumnCount(endLine));
			} else {
				// Optimized single line version
				int line = cursor.getLeftLine();
				String lineStr = text.getLineString(line);
				int firstCharPos = getFirstNonWhitespace(lineStr);
				
				if (firstCharPos < lineStr.length()) {
					if (lineStr.charAt(firstCharPos) == '#') {
						int endPos = firstCharPos + 1;
						if (endPos < lineStr.length() && lineStr.charAt(endPos) == ' ') {
							endPos++;
						}
						text.delete(line, firstCharPos, line, endPos);
					} else {
						text.insert(line, firstCharPos, "# ");
					}
				}
			}
		} finally {
			text.endBatchEdit();
		}
	}
	
	private int getFirstNonWhitespace(String line) {
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if (c != ' ' && c != '\t') {
				return i;
			}
		}
		return line.length();
	}
	
	
	public static void openLinkInBrowser(Context context, String url) {
		if (context == null || TextUtils.isEmpty(url)) {
			return;
		}
		String normalizedUrl = normalizeUrl(url);
		if (normalizedUrl == null) {
			return;
		}
		try {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse(normalizedUrl));
			context.startActivity(intent);
		} catch (Exception e) {
			Toast.makeText(context, "No browser available", Toast.LENGTH_SHORT).show();
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
	
	public static boolean isLink(String text) {
		if (TextUtils.isEmpty(text)) {
			return false;
		}
		
		// Check for full URLs with protocol
		String urlRegex = "^(https?|ftp)://[\\w.-]+(\\.[a-zA-Z]{2,})+[/\\w.-]*$";
		
		// Check for simple domains (without protocol)
		String domainRegex = "^([\\w-]+\\.)+([a-zA-Z]{2,})$";
		
		Pattern urlPattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
		Pattern domainPattern = Pattern.compile(domainRegex, Pattern.CASE_INSENSITIVE);
		
		if (urlPattern.matcher(text).matches()) {
			return true;
		}
		
		if (domainPattern.matcher(text).matches()) {
			String tld = text.substring(text.lastIndexOf('.') + 1).toLowerCase();
			return VALID_TLDS.contains(tld);
		}
		
		if (text.toLowerCase().startsWith("www.")) {
			String rest = text.substring(4);
			if (domainPattern.matcher(rest).matches()) {
				String tld = rest.substring(rest.lastIndexOf('.') + 1).toLowerCase();
				return VALID_TLDS.contains(tld);
			}
		}
		
		return false;
	}
	
	public static String normalizeUrl(String text) {
		if (TextUtils.isEmpty(text)) {
			return null;
		}
		
		// If already starts with http:// or https://
		if (text.toLowerCase().startsWith("http://") || 
		text.toLowerCase().startsWith("https://")) {
			return text;
		}
		
		// If starts with www.
		if (text.toLowerCase().startsWith("www.")) {
			return "http://" + text;
		}
		
		// If it's a valid domain without prefix
		if (isLink(text) && text.contains(".")) {
			return "http://" + text;
		}
		
		return null;
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
