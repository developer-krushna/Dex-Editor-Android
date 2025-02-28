/*
 * Copyright 2016, Google LLC
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following disclaimer
 * in the documentation and/or other materials provided with the
 * distribution.
 * Neither the name of Google LLC nor the names of its
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

package com.android.tools.smali.baksmali;

import com.beust.jcommander.Parameter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.android.tools.smali.dexlib2.VersionMap;
import com.android.tools.smali.dexlib2.analysis.ClassPath;
import com.android.tools.smali.dexlib2.analysis.ClassPathResolver;
import com.android.tools.smali.dexlib2.dexbacked.DexBackedDexFile;
import com.android.tools.smali.dexlib2.dexbacked.OatFile;
import com.android.tools.smali.dexlib2.iface.MultiDexContainer;
import com.android.tools.smali.util.jcommander.ColonParameterSplitter;
import com.android.tools.smali.util.jcommander.ExtendedParameter;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.android.tools.smali.dexlib2.analysis.ClassPath.NOT_SPECIFIED;

public class AnalysisArguments {
    @Parameter(names = {"-b", "--bootclasspath", "--bcp"},
            description = "A colon separated list of the files to include in the bootclasspath when analyzing the " +
                    "dex file. If not specified, baksmali will attempt to choose an " +
                    "appropriate default. When analyzing oat files, this can simply be the path to the device's " +
                    "boot.oat file. A single empty string can be used to specify that an empty bootclasspath should " +
                    "be used. (e.g. --bootclasspath \"\") See baksmali help classpath for more information.",
            splitter = ColonParameterSplitter.class)
    @ExtendedParameter(argumentNames = "classpath")
    public List<String> bootClassPath = null;

    @Parameter(names = {"-c", "--classpath", "--cp"},
            description = "A colon separated list of additional files to include in the classpath when analyzing the " +
                    "dex file. These will be added to the classpath after any bootclasspath entries.",
            splitter = ColonParameterSplitter.class)
    @ExtendedParameter(argumentNames = "classpath")
    public List<String> classPath = Lists.newArrayList();

    @Parameter(names = {"-d", "--classpath-dir", "--cpd", "--dir"},
            description = "A directory to search for classpath files. This option can be used multiple times to " +
                    "specify multiple directories to search. They will be searched in the order they are provided.")
    @ExtendedParameter(argumentNames = "dir")
    public List<String> classPathDirectories = null;

    public static class CheckPackagePrivateArgument {
        @Parameter(names = {"--check-package-private-access", "--package-private", "--checkpp", "--pp"},
                description = "Use the package-private access check when calculating vtable indexes. This is enabled " +
                        "by default for oat files. For odex files, this is only needed for odexes from 4.2.0. It " +
                        "was reverted in 4.2.1.")
        public boolean checkPackagePrivateAccess = false;
    }

    @Nonnull
    public ClassPath loadClassPathForDexFile(@Nonnull File dexFileDir,
                                             @Nonnull MultiDexContainer.DexEntry<? extends DexBackedDexFile> dexEntry,
                                             boolean checkPackagePrivateAccess) throws IOException {
        return loadClassPathForDexFile(dexFileDir, dexEntry, checkPackagePrivateAccess, NOT_SPECIFIED);
    }

    @Nonnull
    public ClassPath loadClassPathForDexFile(@Nonnull File dexFileDir,
                                             @Nonnull MultiDexContainer.DexEntry<? extends DexBackedDexFile> dexEntry,
                                             boolean checkPackagePrivateAccess, int oatVersion)
            throws IOException {
        ClassPathResolver resolver;

        MultiDexContainer<? extends DexBackedDexFile> container = dexEntry.getContainer();

        if (oatVersion == NOT_SPECIFIED) {
            if (container instanceof OatFile) {
                checkPackagePrivateAccess = true;
                oatVersion = ((OatFile) container).getOatVersion();
            } else {
                oatVersion = VersionMap.mapApiToArtVersion(dexEntry.getDexFile().getOpcodes().api);
            }
        } else {
            // this should always be true for ART
            checkPackagePrivateAccess = true;
        }

        if (classPathDirectories == null || classPathDirectories.size() == 0) {
            classPathDirectories = Lists.newArrayList(dexFileDir.getPath());
        }

        List<String> filteredClassPathDirectories = Lists.newArrayList();
        if (classPathDirectories != null) {
            for (String dir: classPathDirectories) {
                File file = new File(dir);
                if (!file.exists()) {
                    System.err.println(String.format("Warning: directory %s does not exist. Ignoring.", dir));
                } else if (!file.isDirectory()) {
                    System.err.println(String.format("Warning: %s is not a directory. Ignoring.", dir));
                } else {
                    filteredClassPathDirectories.add(dir);
                }
            }
        }

        if (bootClassPath == null) {
            // TODO: we should be able to get the api from the Opcodes object associated with the dexFile..
            // except that the oat version -> api mapping doesn't fully work yet
            resolver = new ClassPathResolver(filteredClassPathDirectories, classPath, dexEntry);
        }  else if (bootClassPath.size() == 1 && bootClassPath.get(0).length() == 0) {
            // --bootclasspath "" is a special case, denoting that no bootclasspath should be used
            resolver = new ClassPathResolver(
                    ImmutableList.<String>of(), ImmutableList.<String>of(), classPath, dexEntry);
        } else {
            resolver = new ClassPathResolver(filteredClassPathDirectories, bootClassPath, classPath, dexEntry);
        }

        if (oatVersion == 0 && container instanceof OatFile) {
            oatVersion = ((OatFile) container).getOatVersion();
        }
        return new ClassPath(resolver.getResolvedClassProviders(), checkPackagePrivateAccess, oatVersion);
    }
}
