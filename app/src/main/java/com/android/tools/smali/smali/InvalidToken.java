package com.android.tools.smali.smali;

import org.antlr.runtime.CommonToken;

public class InvalidToken extends CommonToken {
    private final String message;

    public InvalidToken(String message) {
        super(smaliParser.INVALID_TOKEN);
        this.message = message;
        this.channel = smaliParser.ERROR_CHANNEL;
    }

    public InvalidToken(String message, String text) {
        super(smaliParser.INVALID_TOKEN, text);
        this.message = message;
        this.channel = smaliParser.ERROR_CHANNEL;
    }

    public String getMessage() {
        return message;
    }
}