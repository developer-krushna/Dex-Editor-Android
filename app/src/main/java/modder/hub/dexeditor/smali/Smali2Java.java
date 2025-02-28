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
 

package modder.hub.dexeditor.smali;


import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;

import jadx.api.JadxArgs;
import jadx.api.JadxDecompiler;
import jadx.api.JavaClass;

import com.google.common.collect.ImmutableList;

import com.android.tools.smali.dexlib2.Opcodes;
import com.android.tools.smali.dexlib2.iface.ClassDef;
import com.android.tools.smali.dexlib2.writer.builder.DexBuilder;
import com.android.tools.smali.dexlib2.writer.io.FileDataStore;
import com.android.tools.smali.dexlib2.writer.pool.DexPool;
import com.android.tools.smali.smali2.Smali;
import com.android.tools.smali.smali.SmaliOptions;

import modder.hub.dexeditor.FileUtil;
/*
Author @developer-krushna
*/
public class Smali2Java {
	
	
	public static String translate(String smali, int version) throws Exception {
		File tmp = File.createTempFile("test", ".dex");
		try {
			DexBuilder dexBuilder = new DexBuilder(Opcodes.forDexVersion(version));
			dexBuilder.setIgnoreMethodAndFieldError(true);
			
			ClassDef classDef = Smali.assemble(smali, new SmaliOptions());
			
			DexPool pool = new DexPool(Opcodes.getDefault());
			pool.internClass(classDef);
			pool.writeTo(new FileDataStore(tmp));
			JadxArgs args = new JadxArgs();
			args.setSkipResources(true);
			args.setShowInconsistentCode(true);
			args.setInputFiles(ImmutableList.of(tmp));
			JadxDecompiler decompiler = new JadxDecompiler(args);
			decompiler.load();
			JavaClass jcls = decompiler.getClasses().iterator().next();
			jcls.decompile();
			return jcls.getCode();
		} finally {
			tmp.delete();
		}
	}
	
}
