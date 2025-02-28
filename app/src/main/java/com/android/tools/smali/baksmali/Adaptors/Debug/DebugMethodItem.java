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

package com.android.tools.smali.baksmali.Adaptors.Debug;

import com.android.tools.smali.dexlib2.iface.debug.DebugItem;
import com.android.tools.smali.dexlib2.iface.debug.EndLocal;
import com.android.tools.smali.dexlib2.iface.debug.LineNumber;
import com.android.tools.smali.dexlib2.iface.debug.RestartLocal;
import com.android.tools.smali.dexlib2.iface.debug.SetSourceFile;
import com.android.tools.smali.dexlib2.iface.debug.StartLocal;
import com.android.tools.smali.baksmali.Adaptors.ClassDefinition;
import com.android.tools.smali.baksmali.Adaptors.MethodItem;
import com.android.tools.smali.baksmali.Adaptors.RegisterFormatter;
import com.android.tools.smali.dexlib2.DebugItemType;
import com.android.tools.smali.util.ExceptionWithContext;

public abstract class DebugMethodItem extends MethodItem {
    private final int sortOrder;

    protected DebugMethodItem(int codeAddress, int sortOrder) {
        super(codeAddress);
        this.sortOrder = sortOrder;
    }

    @Override public double getSortOrder() { return sortOrder; }

    public static DebugMethodItem build(
            ClassDefinition classDef, RegisterFormatter registerFormatter, DebugItem debugItem) {

        int codeAddress = debugItem.getCodeAddress();
        switch (debugItem.getDebugItemType()) {
            case DebugItemType.START_LOCAL:
                return new StartLocalMethodItem(classDef, codeAddress, -1, registerFormatter, (StartLocal)debugItem);
            case DebugItemType.END_LOCAL:
                return new EndLocalMethodItem(codeAddress, -1, registerFormatter, (EndLocal)debugItem);
            case DebugItemType.RESTART_LOCAL:
                return new RestartLocalMethodItem(
                        classDef, codeAddress, -1, registerFormatter, (RestartLocal)debugItem);
            case DebugItemType.EPILOGUE_BEGIN:
                return new BeginEpilogueMethodItem(codeAddress, -4);
            case DebugItemType.PROLOGUE_END:
                return new EndPrologueMethodItem(codeAddress, -4);
            case DebugItemType.SET_SOURCE_FILE:
                return new SetSourceFileMethodItem(codeAddress, -3, (SetSourceFile)debugItem);
            case DebugItemType.LINE_NUMBER:
                return new LineNumberMethodItem(codeAddress, -2, (LineNumber)debugItem);
            default:
                throw new ExceptionWithContext("Invalid debug item type: %d", debugItem.getDebugItemType());
        }
    }
}
