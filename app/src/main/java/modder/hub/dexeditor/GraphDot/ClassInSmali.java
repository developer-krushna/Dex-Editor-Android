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
import java.util.Map;

/*
Author @developer-krushna
Special thanks to @Timscriptov for enhancing and fixing some parts of the codes

*/
public class ClassInSmali {
    public String className;
    public Map<String, Method> methodDict = new HashMap<>();

    public ClassInSmali(String className) {
        this.className = className;
    }

    public ClassInSmali() {
    }
	
	public Map<String, Method> getMethodDict() {
        return methodDict;
    }

    public void setClassName(String className) throws Exception {
        if (this.className != null) {
            throw new Exception("More than one class in smali file!");
        }
        this.className = className;
    }

    public void addMethod(String methodName) throws Exception {
        if (methodDict.containsKey(methodName)) {
            throw new Exception("There are methods with the same name\nmethod name = " + methodName);
        }
        methodDict.put(methodName, new Method(methodName));
    }

    public void addMethodIns(String methodName, String ins, int lineNum) {
        if (!methodDict.containsKey(methodName)) {
            System.out.println("method->" + methodName + " does not exist");
        }
        Method method = methodDict.get(methodName);
        if (method != null) {
            method.addIns(ins, lineNum);
        }
    }
}