/*
 * Copyright 2012, Google LLC
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
 *     * Neither the name of Google LLC nor the names of its
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
 */

package com.android.tools.smali.dexlib2.immutable.debug;

import com.android.tools.smali.dexlib2.DebugItemType;
import com.android.tools.smali.dexlib2.base.reference.BaseStringReference;
import com.android.tools.smali.dexlib2.iface.UpdateReference;
import com.android.tools.smali.dexlib2.iface.debug.SetSourceFile;
import com.android.tools.smali.dexlib2.iface.reference.StringReference;
import com.android.tools.smali.dexlib2.writer.builder.DexBuilder;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ImmutableSetSourceFile extends ImmutableDebugItem implements SetSourceFile, UpdateReference {
    @Nullable protected final String sourceFile;

	@Nullable
    private StringReference sourceFileRef;

    public ImmutableSetSourceFile(int codeAddress,
                                  @Nullable String sourceFile) {
        super(codeAddress);
        this.sourceFile = sourceFile;
    }

    @Nonnull
    public static ImmutableSetSourceFile of(@Nonnull SetSourceFile setSourceFile) {
        if (setSourceFile instanceof ImmutableSetSourceFile) {
            return (ImmutableSetSourceFile)setSourceFile;
        }
        return new ImmutableSetSourceFile(
			setSourceFile.getCodeAddress(),
			setSourceFile.getSourceFile());
    }

    @Nullable
	@Override public String getSourceFile() { 
		return sourceFile;
	}

    @Nullable @Override public StringReference getSourceFileReference() {

		if (sourceFileRef != null)
            return sourceFileRef;

        return sourceFile == null ?null: new BaseStringReference() {
            @Nonnull @Override public String getString() {
                return sourceFile;
            }
        };
    }


    @Override public int getDebugItemType() { 
		return DebugItemType.SET_SOURCE_FILE;
	}

	@Override
    public void updateReference(DexBuilder dexBuilder) {
        sourceFileRef = dexBuilder.internNullableStringReference(sourceFile);
    }
}

