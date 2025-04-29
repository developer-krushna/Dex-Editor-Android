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

import com.android.tools.smali.dexlib2.iface.ClassDef;
import com.android.tools.smali.dexlib2.writer.builder.DexBuilder;
import com.android.tools.smali.smali.SmaliOptions;
import com.android.tools.smali.smali.smaliFlexLexer;
import com.android.tools.smali.smali.*;
import com.android.tools.smali.dexlib2.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

/*
Original Author @MikeAndrson (https://github.com/MikeAndrson)
Re-modification done by @developer-krushna
*/
public class Smali {

    public static ClassDef assemble(String smaliCode, SmaliOptions options, int dexVer) throws Exception {
        DexBuilder dexBuilder = new DexBuilder(Opcodes.forDexVersion(dexVer));

        SmaliCatchErrFlexLexer lexer = new SmaliCatchErrFlexLexer(new StringReader(smaliCode), options.apiLevel);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        SmaliCatchErrParser parser = new SmaliCatchErrParser(tokens);
        parser.setVerboseErrors(options.verboseErrors);
        parser.setAllowOdex(options.allowOdexOpcodes);
        parser.setApiLevel(dexVer);

        SmaliCatchErrParser.smali_file_return result = parser.smali_file();

        if (lexer.getNumberOfSyntaxErrors() > 0) {
            throw new Exception(lexer.getErrorsString());
        }

        if (parser.getNumberOfSyntaxErrors() > 0) {
            throw new Exception(parser.getErrorsString());
        }
        
        CommonTree t = result.getTree();

        CommonTreeNodeStream treeStream = new CommonTreeNodeStream(t);
        treeStream.setTokenStream(tokens);

        SmaliCatchErrTreeWalker treeWalker = new SmaliCatchErrTreeWalker(treeStream);
        treeWalker.setApiLevel(dexVer);
        treeWalker.setVerboseErrors(options.verboseErrors);
        treeWalker.setDexBuilder(dexBuilder);

        ClassDef classDef = treeWalker.smali_file();

        if (treeWalker.getNumberOfSyntaxErrors() > 0) {
            throw new Exception(treeWalker.getErrorsString());
        }

        return classDef;
    }
}

