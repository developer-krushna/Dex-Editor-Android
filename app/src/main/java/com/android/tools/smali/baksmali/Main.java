/*
 * Copyright 2016, Google LLC
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

package com.android.tools.smali.baksmali;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.common.collect.Lists;
import com.android.tools.smali.baksmali.HelpCommand.HlepCommand;
import com.android.tools.smali.util.jcommander.Command;
import com.android.tools.smali.util.jcommander.ExtendedCommands;
import com.android.tools.smali.util.jcommander.ExtendedParameters;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

@ExtendedParameters(
        includeParametersInUsage = true,
        commandName = "baksmali",
        postfixDescription = "See baksmali help <command> for more information about a specific command")
public class Main extends Command {
    public static final String VERSION = loadVersion();

    @Parameter(names = {"--help", "-h", "-?"}, help = true,
            description = "Show usage information")
    private boolean help;

    @Parameter(names = {"--version", "-v"}, help = true,
            description = "Print the version of baksmali and then exit")
    public boolean version;

    private JCommander jc;

    public Main() {
        super(Lists.<JCommander>newArrayList());
    }

    @Override public void run() {
    }

    @Override protected JCommander getJCommander() {
        return jc;
    }

    public static void main(String[] args) {
        Main main = new Main();

        JCommander jc = new JCommander(main);
        main.jc = jc;
        jc.setProgramName("baksmali");
        List<JCommander> commandHierarchy = main.getCommandHierarchy();

        ExtendedCommands.addExtendedCommand(jc, new DisassembleCommand(commandHierarchy));
        ExtendedCommands.addExtendedCommand(jc, new DeodexCommand(commandHierarchy));
        ExtendedCommands.addExtendedCommand(jc, new DumpCommand(commandHierarchy));
        ExtendedCommands.addExtendedCommand(jc, new HelpCommand(commandHierarchy));
        ExtendedCommands.addExtendedCommand(jc, new HlepCommand(commandHierarchy));
        ExtendedCommands.addExtendedCommand(jc, new ListCommand(commandHierarchy));

        jc.parse(args);

        if (main.version) {
            version();
        }

        if (jc.getParsedCommand() == null || main.help) {
            main.usage();
            return;
        }

        Command command = (Command)jc.getCommands().get(jc.getParsedCommand()).getObjects().get(0);
        command.run();
    }

    protected static void version() {
        System.out.println("baksmali " + VERSION + " (http://smali.org)");
        System.out.println("Copyright (C) 2010 Ben Gruver (JesusFreke@JesusFreke.com)");
        System.out.println("BSD license (http://www.opensource.org/licenses/bsd-license.php)");
        System.exit(0);
    }

    private static String loadVersion() {
        InputStream propertiesStream = Baksmali.class.getClassLoader().getResourceAsStream("baksmali.properties");
        String version = "[unknown version]";
        if (propertiesStream != null) {
            Properties properties = new Properties();
            try {
                properties.load(propertiesStream);
                version = properties.getProperty("application.version");
            } catch (IOException ex) {
                // ignore
            }
        }
        return version;
    }
}
