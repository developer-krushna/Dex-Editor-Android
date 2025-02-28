
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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import android.content.*;
import android.app.FragmentManager;

/*
Author @developer-krushna
Special thanks to @Timscriptov for enhancing and fixing some parts of the codes

*/
public class DrawFlowDiagram {
	private String smaliFilePath;
	private String pictureFormat;
	private String[] methodsToDraw;
	private String outputDir;
	private String dotFilePath;
	private ClassInSmali classInSmali;
	private String curMethodName;
	
	public DrawFlowDiagram(String smaliFilePath, String pictureFormat, String[] methodsToDraw, String outputDir, String dotFilePath) {
		this.smaliFilePath = smaliFilePath;
		this.pictureFormat = pictureFormat;
		this.methodsToDraw = methodsToDraw;
		this.outputDir = outputDir;
		this.dotFilePath = dotFilePath;
		this.classInSmali = new ClassInSmali();
		this.curMethodName = null;
	}
	
	public void run() {
		parseClassInSmali();
		draw();
	}
	
	public ClassInSmali getClassInSmali() {
		return classInSmali;
	}
	
	private void parseClassInSmali() {
		try  {
			
			BufferedReader smaliFile = new BufferedReader(new FileReader(smaliFilePath));
			String line;
			int lineIndex = 0;
			while ((line = smaliFile.readLine()) != null) {
				String trimLine = line.trim();
				if (!trimLine.isEmpty()) {
					String[] splitLine = trimLine.split(" ");
					if (".class".equals(splitLine[0])) {
						classInSmali.setClassName(splitLine[splitLine.length - 1]);
					} else if (".method".equals(splitLine[0])) {
						curMethodName = splitLine[splitLine.length - 1];
						if (methodsToDraw == null || containsMethod(curMethodName)) {
							classInSmali.addMethod(curMethodName);
						} else {
							curMethodName = null;
						}
					} else if (".end method".equals(splitLine[0])) {
						curMethodName = null;
					} else {
						addMethodIns(trimLine, lineIndex + 1);
					}
				}
				lineIndex++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean containsMethod(String methodName) {
		if (methodsToDraw != null) {
			for (String method : methodsToDraw) {
				if (method.equals(methodName) || method.equals(methodName.substring(0, methodName.indexOf("(")))) {
					return true;
				}
			}
		}
		return false;
	}
	
	private void addMethodIns(String trimLine, int lineIndex) {
		if (curMethodName != null) {
			classInSmali.addMethodIns(curMethodName, trimLine, lineIndex);
		}
	}
	
	private void draw() {
		for (Method method : classInSmali.getMethodDict().values()) {
			try {
				drawMethodFlowDiagram(method);
			} catch (Exception ex) {
				System.out.println("Draw method flow diagram error!\nerror message:" + ex.getMessage() + "\nmethod name:" + method.getMethodName());
			}
		}
	}
	
	public String drawMethodFlowDiagram(Method method) {
		String methodName = method.getMethodName();
		StringBuilder dotStr = new StringBuilder("digraph G{\n\tstart[label=\"start\"]\n");
		for (int index = 0; index < method.getInstructions().size(); index++) {
			Instruction ins = method.getInstructions().get(index);
			if (index == 0) {
				dotStr.append("\tnode[shape=record];\n");
				dotStr.append(getDotStrForNode(ins));
				dotStr.append("\tstart->node_").append(ins.getLineNum()).append("\n");
			} else {
				dotStr.append(getDotStrForNode(ins));
				
				String edgeColor = "black";
				String lastInsType = method.getInstructions().get(index - 1).getType();
				if (lastInsType.equals(InstructionType.CON_JUMP)) {
					edgeColor = "red";
				} else if (lastInsType.equals(InstructionType.GOTO) || lastInsType.equals(InstructionType.RETURN)) {
					edgeColor = "white";
				}
				dotStr.append(getDotStrForEdge(method.getInstructions().get(index - 1).getLineNum(), ins.getLineNum(), edgeColor));
				for (Instruction child : ins.getChildrenAbove()) {
					if (ins.getType() == InstructionType.CON_JUMP) {
						dotStr.append(getDotStrForEdge(ins.getLineNum(), child.getLineNum(), "green"));
					} else if (ins.getType() == InstructionType.GOTO) {
						dotStr.append(getDotStrForEdge(ins.getLineNum(), child.getLineNum(), "orange"));
					}
				}
				for (Instruction parent : ins.getParentAbove()) {
					if (parent.getType() == InstructionType.CON_JUMP) {
						dotStr.append(getDotStrForEdge(parent.getLineNum(), ins.getLineNum(), "green"));
					} else if (parent.getType() == InstructionType.GOTO) {
						dotStr.append(getDotStrForEdge(parent.getLineNum(), ins.getLineNum(), "orange"));
					}
				}
			}
		}
		dotStr.append("}");
		
		return dotStr.toString();
		
	}
	
	
	
	
	private String getDotStrForEdge(int fromLineNum, int toLineNum, String edgeColor) {
		return "\tedge[color=" + edgeColor + "]\n" +
		"\tnode_" + fromLineNum + "->node_" + toLineNum + "\n" +
		"\tedge[color=black]\n";
	}
	
	private String getDotStrForNode(Instruction instruction) {
		String label = instruction.getIns().startsWith("return") ? "style=filled,fillcolor=yellow" : "";
		return "\tnode_" + instruction.getLineNum() + " [label=\"<f0>" + instruction.getLineNum() + "|<f1>" + instruction.getIns() + "\"" + label + "];\n";
	}
}
