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


*     Please contact Krushna by email mt.modder.hub@gmail.com if you need
*     additional information or have any questions
*/

package modder.hub.dexeditor.utils;

import android.os.Bundle;

import androidx.annotation.NonNull;

import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.component.EditorAutoCompletion;
import org.eclipse.tm4e.languageconfiguration.internal.model.IndentationRules;

import io.github.rosemoe.sora.lang.EmptyLanguage;
import io.github.rosemoe.sora.lang.Language;
import io.github.rosemoe.sora.lang.analysis.AnalyzeManager;
import io.github.rosemoe.sora.lang.completion.CompletionPublisher;
import io.github.rosemoe.sora.lang.smartEnter.NewlineHandleResult;
import io.github.rosemoe.sora.lang.smartEnter.NewlineHandler;
import io.github.rosemoe.sora.lang.styling.Styles;
import io.github.rosemoe.sora.langs.textmate.TextMateLanguage;
import io.github.rosemoe.sora.langs.textmate.TextMateSymbolPairMatch;
import io.github.rosemoe.sora.langs.textmate.registry.GrammarRegistry;
import org.eclipse.tm4e.core.grammar.IGrammar;
import java.util.List;
import java.util.Objects;

import io.github.rosemoe.sora.text.CharPosition;
import io.github.rosemoe.sora.text.Content;
import io.github.rosemoe.sora.text.ContentReference;
import io.github.rosemoe.sora.widget.SymbolPairMatch;

import modder.hub.dexeditor.adapter.*;
import modder.hub.dexeditor.views.*;

/*
Author @developer-krushna
Thanks to @AndroidPrime
*/

public class CustomAutoComplete extends EmptyLanguage {
	private final Language mLanguage;
	private final CodeEditor mEditor;
	private final IndentationRules mIndentationRules;
	
	public CustomAutoComplete(CodeEditor editor, String[] gotoItems, Language language) {
		mEditor = editor;
		mLanguage = language;
		editor.getComponent(EditorAutoCompletion.class).setMaxHeight(100);
		editor.getComponent(EditorAutoCompletion.class).setLayout(new CustomCompletionLayout());
		editor.getComponent(EditorAutoCompletion.class).setAdapter(new CustomCompletionItemAdapter());
		
		IndentationRules rules = null;
		if (language instanceof TextMateLanguage) {
			try {
				rules = Objects.requireNonNull(GrammarRegistry.getInstance().findLanguageConfiguration("source.smali")).getIndentationRules();
			} catch (Exception e) {
				// Ignore
			}
		}
		mIndentationRules = rules;
		
		if (mLanguage instanceof TextMateLanguage) {
			TextMateLanguage tm = (TextMateLanguage) mLanguage;
			tm.setAutoCompleteEnabled(true);
			tm.setTabSize(editor.getTabWidth());
			if (tm.getSymbolPairs() instanceof TextMateSymbolPairMatch) {
				((TextMateSymbolPairMatch)tm.getSymbolPairs()).setEnabled(true);
			}
		}
		
		String[] ks = {
			".class", ".super", ".source", ".implements", ".field", ".method", ".end method",
			".registers", ".locals", ".param", ".prologue", ".line", ".end local", ".restart local",
			"invoke-virtual", "invoke-direct", "invoke-static", "invoke-interface", "invoke-super",
			"move-result", "move-result-object", "move-result-wide", "move", "move-object", "move-wide",
			"const", "const/4", "const/16", "const/high16", "const-string", "const-class", "const-wide", "const-wide/16",
			"return", "return-void", "return-object", "return-wide",
			"iget", "iput", "iget-object", "iput-object", "iget-boolean", "iput-boolean",
			"sget", "sput", "sget-object", "sput-object",
			"if-eq", "if-ne", "if-lt", "if-ge", "if-gt", "if-le",
			"if-eqz", "if-nez", "if-ltz", "if-gez", "if-gtz", "if-lez",
			"goto", "goto/16", "goto/32",
			"new-instance", "new-array", "filled-new-array", "array-length",
			"check-cast", "instance-of",
			"throw", "monitor-enter", "monitor-exit",
			"add-int", "sub-int", "mul-int", "div-int", "rem-int",
			"and-int", "or-int", "xor-int", "shl-int", "shr-int", "ushr-int",
			"add-float", "sub-float", "mul-float", "div-float", "rem-float",
			"cmp-long", "cmpg-float", "cmpl-float", "cmpg-double", "cmpl-double",
			"packed-switch", "sparse-switch", "fill-array-data",
			"nop", "return-void", "throw", "move-exception",
			".array-data", ".end array-data", ".packed-switch", ".end packed-switch",
			".sparse-switch", ".end sparse-switch"
		};
		
		if(mLanguage instanceof TextMateLanguage) {
			TextMateLanguage tm = (TextMateLanguage) mLanguage;
			if(gotoItems == null){
				tm.setCompleterKeywords(ks);
			}else {
				// Merge ks and gotoItems
				java.util.Set<String> allKeywords = new java.util.HashSet<>(java.util.Arrays.asList(ks));
				allKeywords.addAll(java.util.Arrays.asList(gotoItems));
				tm.setCompleterKeywords(allKeywords.toArray(new String[0]));
			}
		}
	}
	
	@Override
	public int getIndentAdvance(@NonNull ContentReference content, int line, int column) {
		return getIndentAdvance(content.getLine(line).substring(0, column));
	}
	
	public int getIndentAdvance(String line) {
		if (mIndentationRules == null || mIndentationRules.increaseIndentPattern == null) {
			return 0;
		}
		return line.matches(mIndentationRules.increaseIndentPattern.pattern()) ? mEditor.getTabWidth() : 0;
	}
	
	private final NewlineHandler[] mNewlineHandlers = new NewlineHandler[]{new EndwiseNewlineHandler()};
	
	@Override
	public NewlineHandler[] getNewlineHandlers() {
		return mNewlineHandlers;
	}
	
	@Override
	public SymbolPairMatch getSymbolPairs() {
		return mLanguage != null ? mLanguage.getSymbolPairs() : super.getSymbolPairs();
	}
	
	@Override
	@NonNull
	public AnalyzeManager getAnalyzeManager() {
		return mLanguage != null ? mLanguage.getAnalyzeManager() : super.getAnalyzeManager();
	}
	
	@Override
	public void requireAutoComplete(@NonNull ContentReference content, @NonNull CharPosition position, @NonNull CompletionPublisher publisher, @NonNull Bundle extraArguments) {		
		if (mLanguage != null) {
			mLanguage.requireAutoComplete(content, position, publisher, extraArguments);
		}
	}
	
	public class EndwiseNewlineHandler implements NewlineHandler {
		private static final String ENDWISE_PATTERN = "^((?!(--)).)*(\\b(else|function|then|do|repeat)\\b((?!\\b(end|until)\\b).)*)$";
		
		@Override
		public boolean matchesRequirement(@NonNull Content text, @NonNull CharPosition position, Styles style) {
			String line = text.getLineString(position.line);
			String beforeText = line.substring(0, position.column);
			
			return beforeText.matches(ENDWISE_PATTERN);
		}
		
		@NonNull
		@Override
		public NewlineHandleResult handleNewline(@NonNull Content text, @NonNull CharPosition position, Styles style, int tabSize) {
			return new NewlineHandleResult("",0);
		}
		
	}
	
	public static String repeat(String str, int count) {
		if (str == null || str.isEmpty() || count <= 0) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder(str.length() * count);
		for (int i = 0; i < count; i++) {
			sb.append(str);
		}
		
		return sb.toString();
	}
	
	public static boolean isOnlySpaces(String str) {
		if (str == null || str.isEmpty()) {
			return false;
		}
		
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		
		return true;
	}
	
	public static int leadingSpaceCount(String str) {
		int count = 0;
		int index = 0;
		
		while (index < str.length() && str.charAt(index) == ' ') {
			count++;
			index++;
		}
		
		return count;
	}
	
}
