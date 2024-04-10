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


package modder.hub.dexeditor.GraphDot;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
Author @developer-krushna
Special thanks to @Timscriptov for enhancing and fixing some parts of the codes
*/

public class ArgumentParser {
    private String[] args;
    private Map<String, String> argumentMap = new HashMap<>();
    private Map<String, String> argumentHelp = new HashMap<>();

    public ArgumentParser(String[] args) {
        this.args = args;
    }

    public void addArgument(String argName, String help) {
        argumentHelp.put(argName, help);
    }

    public void parseArgs() {
        String currentArg = null;
        int index = 0;
        while (index < args.length) {
            String arg = args[index];
            if (arg.startsWith("-")) {
                currentArg = arg;
                argumentMap.put(currentArg, "");
                index++;
            } else {
                if (currentArg != null) {
                    if (argumentMap.get(currentArg).isEmpty()) {
                        argumentMap.put(currentArg, arg);
                    } else {
                        argumentMap.put(currentArg, argumentMap.get(currentArg) + " " + arg);
                    }
                }
                index++;
            }
        }
    }

    public String getString(String argName) {
        return argumentMap.get(argName);
    }

    public Integer getInt(String argName) {
        String argValue = argumentMap.get(argName);
        if (argValue != null) {
            try {
                return Integer.parseInt(argValue);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    public boolean getBoolean(String argName) {
        String argValue = argumentMap.get(argName);
        return argValue != null && Boolean.parseBoolean(argValue);
    }

    public List<String> getList(String argName) {
        String argValue = argumentMap.get(argName);
        if (argValue != null) {
            return List.of(argValue.split(" "));
        }
        return null;
    }

    public void printHelp() {
        System.out.println("Usage:");
        for (Map.Entry<String, String> entry : argumentHelp.entrySet()) {
            System.out.println("\t" + entry.getKey() + ": " + entry.getValue());
        }
    }
}