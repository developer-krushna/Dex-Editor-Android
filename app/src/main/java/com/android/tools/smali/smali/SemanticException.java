package com.android.tools.smali.smali;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

public class SemanticException extends RecognitionException {
	private String errorMessage;
	
	
	SemanticException(IntStream input, String errorMessage, Object... messageArguments) {
		super(input);
		this.errorMessage = String.format(errorMessage, messageArguments);
	}
	
	SemanticException(IntStream input, Exception ex) {
		super(input);
		this.errorMessage = ex.getMessage();
	}
	
	SemanticException(IntStream input, CommonTree tree, String errorMessage, Object... messageArguments) {
		super();
		this.input = input;
		this.token = tree.getToken();
		this.index = tree.getTokenStartIndex();
		this.line = token.getLine();
		this.charPositionInLine = token.getCharPositionInLine();
		this.errorMessage = String.format(errorMessage, messageArguments);
	}
	
	SemanticException(IntStream input, Token token, String errorMessage, Object... messageArguments) {
		super();
		this.input = input;
		this.token = token;
		this.index = ((CommonToken)token).getStartIndex();
		this.line = token.getLine();
		this.charPositionInLine = token.getCharPositionInLine();
		this.errorMessage = String.format(errorMessage, messageArguments);
	}
	
	public String getMessage() {
		return errorMessage;
	}
}
