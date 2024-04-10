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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
Author @developer-krushna
Special thanks to @Timscriptov for enhancing and fixing some parts of the codes

*/
public class Method {
    public String methodName;
    public List<Instruction> instructions = new ArrayList<>();
    public Map<String, Instruction> labelDict = new HashMap<>();
    public Map<String, List<Instruction>> jumpToLabelDict = new HashMap<>();

    public Method(String methodName) {
        this.methodName = methodName;
    }
	
	public String getMethodName() {
        return methodName;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void addIns(String ins, int lineNum) {
        Instruction instruction = new Instruction(ins, lineNum);
        switch (instruction.type) {
            case InstructionType.GOTO_LABEL:
            case InstructionType.CON_LABEL:
                if (labelDict.containsKey(ins)) {
                    throw new RuntimeException("There are the same label: " + ins + " in method: " + methodName);
                }
                if (jumpToLabelDict.containsKey(ins)) {
                    for (Instruction eachNode : jumpToLabelDict.get(ins)) {
                        eachNode.addChild(instruction);
                        instruction.addParent(eachNode);
                    }
                    jumpToLabelDict.remove(ins);
                }
                labelDict.put(ins, instruction);
                instructions.add(instruction);
                break;

            case InstructionType.GOTO:
            case InstructionType.CON_JUMP:
                String label = ins.substring(ins.indexOf(":"));
                if (labelDict.containsKey(label)) {
                    instruction.addChild(labelDict.get(label));
                    labelDict.get(label).addParent(instruction);
                } else {
                    if (jumpToLabelDict.containsKey(label)) {
                        jumpToLabelDict.get(label).add(instruction);
                    } else {
                        jumpToLabelDict.put(label, new ArrayList<>(List.of(instruction)));
                    }
                }
                instructions.add(instruction);
                break;

            case InstructionType.RETURN:
                instructions.add(instruction);
                break;
        }
    }
}