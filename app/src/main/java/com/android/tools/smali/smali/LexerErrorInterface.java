package com.android.tools.smali.smali;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognizerSharedState;

public interface LexerErrorInterface {
    public int getNumberOfSyntaxErrors();

    //ANTLR doesn't provide any way to add interfaces to the lexer class directly, so this is an intermediate
    //class that implements LexerErrorInterface that we can have the ANTLR parser extend 
    public abstract static class ANTLRLexerWithErrorInterface extends Lexer implements LexerErrorInterface {
        public ANTLRLexerWithErrorInterface() {
        }

        public ANTLRLexerWithErrorInterface(CharStream input, RecognizerSharedState state) {
            super(input, state);
        }
    }
}