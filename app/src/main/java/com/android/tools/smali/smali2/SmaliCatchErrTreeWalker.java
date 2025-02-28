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
 
package com.android.tools.smali.smali2;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import com.android.tools.smali.smali.smaliTreeWalker;

import java.util.ArrayList;
import java.util.List;
/*
Original Author @MikeAndrson (https://github.com/MikeAndrson)
Re-modification done by @developer-krushna
*/
public class SmaliCatchErrTreeWalker extends smaliTreeWalker {
	
	private StringBuilder errors = new StringBuilder();
	private List<SyntaxError> syntaxErrors = new ArrayList<>();
	
	public SmaliCatchErrTreeWalker(CommonTreeNodeStream treeStream) {
		super(treeStream);
	}
	
	@Override
	public void emitErrorMessage(String msg) {
		errors.append(msg);
		errors.append("\n");
	}
	
	public String getErrorsString() {
		return errors.toString();
	}
	
	@Override
	public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
		syntaxErrors.add(new SyntaxError(
		e.line,
		e.charPositionInLine,
		e.line,
		e.charPositionInLine + (e.token != null ? e.token.getText().length() : 1),
		getErrorMessage(e, tokenNames)
		));
		super.displayRecognitionError(tokenNames, e);
	}
	
	public List<SyntaxError> getErrors() {
		return syntaxErrors;
	}
}
