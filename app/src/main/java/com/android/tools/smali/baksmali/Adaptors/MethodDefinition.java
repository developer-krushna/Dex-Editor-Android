/*
 * [The "BSD licence"]
 * Copyright (c) 2010 Ben Gruver (JesusFreke)
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.android.tools.smali.baksmali.Adaptors;

import com.android.tools.smali.baksmali.Adaptors.Format.InstructionMethodItemFactory;
import com.android.tools.smali.dexlib2.AccessFlags;
import com.android.tools.smali.dexlib2.Format;
import com.android.tools.smali.dexlib2.HiddenApiRestriction;
import com.android.tools.smali.dexlib2.Opcode;
import com.android.tools.smali.dexlib2.ReferenceType;
import com.android.tools.smali.dexlib2.iface.Annotation;
import com.android.tools.smali.dexlib2.iface.ExceptionHandler;
import com.android.tools.smali.dexlib2.iface.Method;
import com.android.tools.smali.dexlib2.iface.MethodImplementation;
import com.android.tools.smali.dexlib2.iface.MethodParameter;
import com.android.tools.smali.dexlib2.iface.TryBlock;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.android.tools.smali.baksmali.Adaptors.Debug.DebugMethodItem;
import com.android.tools.smali.baksmali.formatter.BaksmaliWriter;
import com.android.tools.smali.dexlib2.analysis.AnalysisException;
import com.android.tools.smali.dexlib2.analysis.AnalyzedInstruction;
import com.android.tools.smali.dexlib2.analysis.MethodAnalyzer;
import com.android.tools.smali.dexlib2.iface.debug.DebugItem;
import com.android.tools.smali.dexlib2.iface.instruction.Instruction;
import com.android.tools.smali.dexlib2.iface.instruction.OffsetInstruction;
import com.android.tools.smali.dexlib2.iface.instruction.ReferenceInstruction;
import com.android.tools.smali.dexlib2.iface.instruction.formats.Instruction31t;
import com.android.tools.smali.dexlib2.iface.reference.MethodReference;
import com.android.tools.smali.dexlib2.iface.reference.Reference;
import com.android.tools.smali.dexlib2.immutable.instruction.ImmutableInstruction31t;
import com.android.tools.smali.dexlib2.util.InstructionOffsetMap;
import com.android.tools.smali.dexlib2.util.InstructionOffsetMap.InvalidInstructionOffset;
import com.android.tools.smali.dexlib2.util.SyntheticAccessorResolver;
import com.android.tools.smali.dexlib2.util.SyntheticAccessorResolver.AccessedMember;
import com.android.tools.smali.dexlib2.util.TypeUtils;
import com.android.tools.smali.util.ExceptionWithContext;
import com.android.tools.smali.util.SparseIntArray;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;
import java.io.IOException;

public class MethodDefinition {
    @Nonnull public final ClassDefinition classDef;
    @Nonnull public final Method method;
    @Nonnull public final MethodImplementation methodImpl;
    @Nonnull public final ImmutableList<Instruction> instructions;
    @Nonnull public final List<Instruction> effectiveInstructions;

    @Nonnull public final ImmutableList<MethodParameter> methodParameters;
    public RegisterFormatter registerFormatter;

    @Nonnull private final LabelCache labelCache = new LabelCache();

    @Nonnull private final SparseIntArray packedSwitchMap;
    @Nonnull private final SparseIntArray sparseSwitchMap;
    @Nonnull private final InstructionOffsetMap instructionOffsetMap;

    public MethodDefinition(@Nonnull ClassDefinition classDef, @Nonnull Method method,
                            @Nonnull MethodImplementation methodImpl) {
        this.classDef = classDef;
        this.method = method;
        this.methodImpl = methodImpl;

        try {
            //TODO: what about try/catch blocks inside the dead code? those will need to be commented out too. ugh.

            instructions = ImmutableList.copyOf(methodImpl.getInstructions());
            methodParameters = ImmutableList.copyOf(method.getParameters());

            effectiveInstructions = Lists.newArrayList(instructions);

            packedSwitchMap = new SparseIntArray(0);
            sparseSwitchMap = new SparseIntArray(0);
            instructionOffsetMap = new InstructionOffsetMap(instructions);

            int endOffset = instructionOffsetMap.getInstructionCodeOffset(instructions.size()-1) +
                    instructions.get(instructions.size()-1).getCodeUnits();

            for (int i=0; i<instructions.size(); i++) {
                Instruction instruction = instructions.get(i);

                Opcode opcode = instruction.getOpcode();
                if (opcode == Opcode.PACKED_SWITCH) {
                    boolean valid = true;
                    int codeOffset = instructionOffsetMap.getInstructionCodeOffset(i);
                    int targetOffset = codeOffset + ((OffsetInstruction)instruction).getCodeOffset();
                    try {
                        targetOffset = findPayloadOffset(targetOffset, Opcode.PACKED_SWITCH_PAYLOAD);
                    } catch (InvalidSwitchPayload ex) {
                        valid = false;
                    }
                    if (valid) {
                        if (packedSwitchMap.get(targetOffset, -1) != -1) {
                            Instruction payloadInstruction =
                                    findSwitchPayload(targetOffset, Opcode.PACKED_SWITCH_PAYLOAD);
                            targetOffset = endOffset;
                            effectiveInstructions.set(i, new ImmutableInstruction31t(opcode,
                                    ((Instruction31t)instruction).getRegisterA(), targetOffset-codeOffset));
                            effectiveInstructions.add(payloadInstruction);
                            endOffset += payloadInstruction.getCodeUnits();
                        }
                        packedSwitchMap.append(targetOffset, codeOffset);
                    }
                } else if (opcode == Opcode.SPARSE_SWITCH) {
                    boolean valid = true;
                    int codeOffset = instructionOffsetMap.getInstructionCodeOffset(i);
                    int targetOffset = codeOffset + ((OffsetInstruction)instruction).getCodeOffset();
                    try {
                        targetOffset = findPayloadOffset(targetOffset, Opcode.SPARSE_SWITCH_PAYLOAD);
                    } catch (InvalidSwitchPayload ex) {
                        valid = false;
                        // The offset to the payload instruction was invalid. Nothing to do, except that we won't
                        // add this instruction to the map.
                    }
                    if (valid) {
                        if (sparseSwitchMap.get(targetOffset, -1) != -1) {
                            Instruction payloadInstruction =
                                    findSwitchPayload(targetOffset, Opcode.SPARSE_SWITCH_PAYLOAD);
                            targetOffset = endOffset;
                            effectiveInstructions.set(i, new ImmutableInstruction31t(opcode,
                                    ((Instruction31t)instruction).getRegisterA(), targetOffset-codeOffset));
                            effectiveInstructions.add(payloadInstruction);
                            endOffset += payloadInstruction.getCodeUnits();
                        }
                        sparseSwitchMap.append(targetOffset, codeOffset);
                    }
                }
            }
        } catch (Exception ex) {
            String methodString;
            try {
                methodString = classDef.getFormatter().getMethodDescriptor(method);
            } catch (Exception ex2) {
                throw ExceptionWithContext.withContext(ex, "Error while processing method");
            }
            throw ExceptionWithContext.withContext(ex, "Error while processing method %s", methodString);
        }
    }

    public static void writeEmptyMethodTo(BaksmaliWriter writer, Method method,
                                          ClassDefinition classDef) throws IOException {
        writer.write(".method ");
        writeAccessFlagsAndRestrictions(writer, method.getAccessFlags(), method.getHiddenApiRestrictions());
        writer.write(method.getName());
        writer.write("(");
        ImmutableList<MethodParameter> methodParameters = ImmutableList.copyOf(method.getParameters());
        for (MethodParameter parameter: methodParameters) {
            writer.writeType(parameter.getType());
        }
        writer.write(")");
        writer.write(method.getReturnType());
        writer.write('\n');

        writer.indent(4);
        writeParameters(classDef, writer, method, methodParameters);

        AnnotationFormatter.writeTo(writer, method.getAnnotations());

        writer.deindent(4);
        writer.write(".end method\n");
    }

    public void writeTo(BaksmaliWriter writer) throws IOException {
        int parameterRegisterCount = 0;
        if (!AccessFlags.STATIC.isSet(method.getAccessFlags())) {
            parameterRegisterCount++;
        }

        writer.write(".method ");
        writeAccessFlagsAndRestrictions(writer, method.getAccessFlags(), method.getHiddenApiRestrictions());
        writer.writeSimpleName(method.getName());
        writer.write("(");
        for (MethodParameter parameter: methodParameters) {
            String type = parameter.getType();
            writer.writeType(type);
            parameterRegisterCount++;
            if (TypeUtils.isWideType(type)) {
                parameterRegisterCount++;
            }
        }
        writer.write(")");
        writer.writeType(method.getReturnType());
        writer.write('\n');

        writer.indent(4);
        if (classDef.options.localsDirective) {
            writer.write(".locals ");
            writer.writeSignedIntAsDec(methodImpl.getRegisterCount() - parameterRegisterCount);
        } else {
            writer.write(".registers ");
            writer.writeSignedIntAsDec(methodImpl.getRegisterCount());
        }
        writer.write('\n');
        writeParameters(classDef, writer, method, methodParameters);

        if (registerFormatter == null) {
            registerFormatter = new RegisterFormatter(classDef.options, methodImpl.getRegisterCount(),
                    parameterRegisterCount);
        }

        AnnotationFormatter.writeTo(writer, method.getAnnotations());

        writer.write('\n');

        List<MethodItem> methodItems = getMethodItems();
        for (MethodItem methodItem: methodItems) {
            if (methodItem.writeTo(writer)) {
                writer.write('\n');
            }
        }
        writer.deindent(4);
        writer.write(".end method\n");
    }

    public Instruction findSwitchPayload(int targetOffset, Opcode type) {
        int targetIndex;
        try {
            targetIndex = instructionOffsetMap.getInstructionIndexAtCodeOffset(targetOffset);
        } catch (InvalidInstructionOffset ex) {
            throw new InvalidSwitchPayload(targetOffset);
        }

        //TODO: does dalvik let you pad with multiple nops?
        //TODO: does dalvik let a switch instruction point to a non-payload instruction?

        Instruction instruction = instructions.get(targetIndex);
        if (instruction.getOpcode() != type) {
            // maybe it's pointing to a NOP padding instruction. Look at the next instruction
            if (instruction.getOpcode() == Opcode.NOP) {
                targetIndex += 1;
                if (targetIndex < instructions.size()) {
                    instruction = instructions.get(targetIndex);
                    if (instruction.getOpcode() == type) {
                        return instruction;
                    }
                }
            }
            throw new InvalidSwitchPayload(targetOffset);
        } else {
            return instruction;
        }
    }

    public int findPayloadOffset(int targetOffset, Opcode type) {
        int targetIndex;
        try {
            targetIndex = instructionOffsetMap.getInstructionIndexAtCodeOffset(targetOffset);
        } catch (InvalidInstructionOffset ex) {
            throw new InvalidSwitchPayload(targetOffset);
        }

        //TODO: does dalvik let you pad with multiple nops?
        //TODO: does dalvik let a switch instruction point to a non-payload instruction?

        Instruction instruction = instructions.get(targetIndex);
        if (instruction.getOpcode() != type) {
            // maybe it's pointing to a NOP padding instruction. Look at the next instruction
            if (instruction.getOpcode() == Opcode.NOP) {
                targetIndex += 1;
                if (targetIndex < instructions.size()) {
                    instruction = instructions.get(targetIndex);
                    if (instruction.getOpcode() == type) {
                        return instructionOffsetMap.getInstructionCodeOffset(targetIndex);
                    }
                }
            }
            throw new InvalidSwitchPayload(targetOffset);
        } else {
            return targetOffset;
        }
    }

    private static void writeAccessFlagsAndRestrictions(
            BaksmaliWriter writer, int accessFlags, Set<HiddenApiRestriction> hiddenApiRestrictions)
            throws IOException {
        for (AccessFlags accessFlag: AccessFlags.getAccessFlagsForMethod(accessFlags)) {
            writer.write(accessFlag.toString());
            writer.write(' ');
        }
        for (HiddenApiRestriction hiddenApiRestriction : hiddenApiRestrictions) {
            writer.write(hiddenApiRestriction.toString());
            writer.write(' ');
        }
    }

    private static void writeParameters(ClassDefinition classDef, BaksmaliWriter writer, Method method,
                                        List<? extends MethodParameter> parameters) throws IOException {
        boolean isStatic = AccessFlags.STATIC.isSet(method.getAccessFlags());
        int registerNumber = isStatic?0:1;

        for (MethodParameter parameter: parameters) {
            String parameterType = parameter.getType();
            String parameterName = parameter.getName();
            Collection<? extends Annotation> annotations = parameter.getAnnotations();
            if ((classDef.options.debugInfo && parameterName != null) || annotations.size() != 0) {
                writer.write(".param p");
                writer.writeSignedIntAsDec(registerNumber);

                if (parameterName != null && classDef.options.debugInfo) {
                    writer.write(", ");
                    writer.writeQuotedString(parameterName);
                }
                writer.write("    # ");

                writer.writeType(parameterType);
                writer.write("\n");
                if (annotations.size() > 0) {
                    writer.indent(4);
                    AnnotationFormatter.writeTo(writer, annotations);
                    writer.deindent(4);
                    writer.write(".end param\n");
                }
            }

            registerNumber++;
            if (TypeUtils.isWideType(parameterType)) {
                registerNumber++;
            }
        }
    }

    @Nonnull public LabelCache getLabelCache() {
        return labelCache;
    }

    public int getPackedSwitchBaseAddress(int packedSwitchPayloadCodeOffset) {
        return packedSwitchMap.get(packedSwitchPayloadCodeOffset, -1);
    }

    public int getSparseSwitchBaseAddress(int sparseSwitchPayloadCodeOffset) {
        return sparseSwitchMap.get(sparseSwitchPayloadCodeOffset, -1);
    }

    private List<MethodItem> getMethodItems() {
        ArrayList<MethodItem> methodItems = new ArrayList<MethodItem>();

        if ((classDef.options.registerInfo != 0) || (classDef.options.normalizeVirtualMethods) ||
                (classDef.options.deodex && needsAnalyzed())) {
            addAnalyzedInstructionMethodItems(methodItems);
        } else {
            addInstructionMethodItems(methodItems);
        }

        addTries(methodItems);
        if (classDef.options.debugInfo) {
            addDebugInfo(methodItems);
        }

        if (classDef.options.sequentialLabels) {
            setLabelSequentialNumbers();
        }

        for (LabelMethodItem labelMethodItem: labelCache.getLabels()) {
            methodItems.add(labelMethodItem);
        }

        Collections.sort(methodItems);

        return methodItems;
    }

    private boolean needsAnalyzed() {
        for (Instruction instruction: methodImpl.getInstructions()) {
            if (instruction.getOpcode().odexOnly()) {
                return true;
            }
        }
        return false;
    }

    private void addInstructionMethodItems(List<MethodItem> methodItems) {
        int currentCodeAddress = 0;

        for (int i=0; i<effectiveInstructions.size(); i++) {
            Instruction instruction = effectiveInstructions.get(i);

            MethodItem methodItem = InstructionMethodItemFactory.makeInstructionFormatMethodItem(this,
                    currentCodeAddress, instruction);

            methodItems.add(methodItem);

            if (i != effectiveInstructions.size() - 1) {
                methodItems.add(new BlankMethodItem(currentCodeAddress));
            }

            if (classDef.options.codeOffsets) {
                methodItems.add(new MethodItem(currentCodeAddress) {

                    @Override
                    public double getSortOrder() {
                        return -1000;
                    }

                    @Override
                    public boolean writeTo(BaksmaliWriter writer) throws IOException {
                        writer.write("#@");
                        writer.writeUnsignedLongAsHex(codeAddress & 0xFFFFFFFFL);
                        return true;
                    }
                });
            }

            if (classDef.options.accessorComments && classDef.options.syntheticAccessorResolver != null &&
                    (instruction instanceof ReferenceInstruction)) {
                Opcode opcode = instruction.getOpcode();

                if (opcode.referenceType == ReferenceType.METHOD) {
                    MethodReference methodReference =
                            (MethodReference)((ReferenceInstruction)instruction).getReference();

                    try {
                        methodReference.validateReference();

                        if (SyntheticAccessorResolver.looksLikeSyntheticAccessor(methodReference.getName())) {
                            AccessedMember accessedMember =
                                    classDef.options.syntheticAccessorResolver.getAccessedMember(methodReference);
                            if (accessedMember != null) {
                                methodItems.add(new SyntheticAccessCommentMethodItem(
                                        classDef, accessedMember, currentCodeAddress));
                            }
                        }
                    } catch (Reference.InvalidReferenceException e) {
                        // Just ignore for now. We'll deal with it when processing the instruction
                    }
                }
            }

            currentCodeAddress += instruction.getCodeUnits();
        }
    }

    private void addAnalyzedInstructionMethodItems(List<MethodItem> methodItems) {
        MethodAnalyzer methodAnalyzer = new MethodAnalyzer(classDef.options.classPath, method,
                classDef.options.inlineResolver, classDef.options.normalizeVirtualMethods);

        AnalysisException analysisException = methodAnalyzer.getAnalysisException();
        if (analysisException != null) {
            // TODO: need to keep track of whether any errors occurred, so we can exit with a non-zero result
            methodItems.add(new CommentMethodItem(
                    String.format("AnalysisException: %s", analysisException.getMessage()),
                    analysisException.codeAddress, Integer.MIN_VALUE));
            analysisException.printStackTrace(System.err);
        }

        List<AnalyzedInstruction> instructions = methodAnalyzer.getAnalyzedInstructions();

        int currentCodeAddress = 0;
        for (int i=0; i<instructions.size(); i++) {
            AnalyzedInstruction instruction = instructions.get(i);

            MethodItem methodItem = InstructionMethodItemFactory.makeInstructionFormatMethodItem(
                    this, currentCodeAddress, instruction.getInstruction());

            methodItems.add(methodItem);

            if (instruction.getInstruction().getOpcode().format == Format.UnresolvedOdexInstruction) {
                methodItems.add(new CommentedOutMethodItem(
                        InstructionMethodItemFactory.makeInstructionFormatMethodItem(
                                this, currentCodeAddress, instruction.getOriginalInstruction())));
            }

            if (i != instructions.size() - 1) {
                methodItems.add(new BlankMethodItem(currentCodeAddress));
            }

            if (classDef.options.codeOffsets) {
                methodItems.add(new MethodItem(currentCodeAddress) {

                    @Override
                    public double getSortOrder() {
                        return -1000;
                    }

                    @Override
                    public boolean writeTo(BaksmaliWriter writer) throws IOException {
                        writer.write("#@");
                        writer.writeUnsignedLongAsHex(codeAddress & 0xFFFFFFFFL);
                        return true;
                    }
                });
            }

            if (classDef.options.registerInfo != 0 &&
                    !instruction.getInstruction().getOpcode().format.isPayloadFormat) {
                methodItems.add(
                        new PreInstructionRegisterInfoMethodItem(classDef.options.registerInfo,
                                methodAnalyzer, registerFormatter, instruction, currentCodeAddress));

                methodItems.add(
                        new PostInstructionRegisterInfoMethodItem(registerFormatter, instruction, currentCodeAddress));
            }

            currentCodeAddress += instruction.getInstruction().getCodeUnits();
        }
    }

    private void addTries(List<MethodItem> methodItems) {
        List<? extends TryBlock<? extends ExceptionHandler>> tryBlocks = methodImpl.getTryBlocks();
        if (tryBlocks.size() == 0) {
            return;
        }

        int lastInstructionAddress = instructionOffsetMap.getInstructionCodeOffset(instructions.size() - 1);
        int codeSize = lastInstructionAddress + instructions.get(instructions.size() - 1).getCodeUnits();

        for (TryBlock<? extends ExceptionHandler> tryBlock: tryBlocks) {
            int startAddress = tryBlock.getStartCodeAddress();
            int endAddress = startAddress + tryBlock.getCodeUnitCount();

            if (startAddress >= codeSize) {
                throw new RuntimeException(String.format("Try start offset %d is past the end of the code block.",
                        startAddress));
            }
            // Note: not >=. endAddress == codeSize is valid, when the try covers the last instruction
            if (endAddress > codeSize) {
                throw new RuntimeException(String.format("Try end offset %d is past the end of the code block.",
                        endAddress));
            }

            /**
             * The end address points to the address immediately after the end of the last
             * instruction that the try block covers. We want the .catch directive and end_try
             * label to be associated with the last covered instruction, so we need to get
             * the address for that instruction
             */

            int lastCoveredIndex = instructionOffsetMap.getInstructionIndexAtCodeOffset(endAddress - 1, false);
            int lastCoveredAddress = instructionOffsetMap.getInstructionCodeOffset(lastCoveredIndex);

            for (ExceptionHandler handler: tryBlock.getExceptionHandlers()) {
                int handlerAddress = handler.getHandlerCodeAddress();
                if (handlerAddress >= codeSize) {
                    throw new ExceptionWithContext(
                            "Exception handler offset %d is past the end of the code block.", handlerAddress);
                }

                //use the address from the last covered instruction
                CatchMethodItem catchMethodItem = new CatchMethodItem(classDef.options, labelCache, lastCoveredAddress,
                        handler.getExceptionType(), startAddress, endAddress, handlerAddress);
                methodItems.add(catchMethodItem);
            }
        }
    }

    private void addDebugInfo(final List<MethodItem> methodItems) {
        for (DebugItem debugItem: methodImpl.getDebugItems()) {
            methodItems.add(DebugMethodItem.build(classDef, registerFormatter, debugItem));
        }
    }

    private void setLabelSequentialNumbers() {
        HashMap<String, Integer> nextLabelSequenceByType = new HashMap<String, Integer>();
        ArrayList<LabelMethodItem> sortedLabels = new ArrayList<LabelMethodItem>(labelCache.getLabels());

        //sort the labels by their location in the method
        Collections.sort(sortedLabels);

        for (LabelMethodItem labelMethodItem: sortedLabels) {
            Integer labelSequence = nextLabelSequenceByType.get(labelMethodItem.getLabelPrefix());
            if (labelSequence == null) {
                labelSequence = 0;
            }
            labelMethodItem.setLabelSequence(labelSequence);
            nextLabelSequenceByType.put(labelMethodItem.getLabelPrefix(), labelSequence + 1);
        }
    }

    public static class LabelCache {
        protected HashMap<LabelMethodItem, LabelMethodItem> labels = new HashMap<LabelMethodItem, LabelMethodItem>();

        public LabelCache() {
        }

        public LabelMethodItem internLabel(LabelMethodItem labelMethodItem) {
            LabelMethodItem internedLabelMethodItem = labels.get(labelMethodItem);
            if (internedLabelMethodItem != null) {
                return internedLabelMethodItem;
            }
            labels.put(labelMethodItem, labelMethodItem);
            return labelMethodItem;
        }


        public Collection<LabelMethodItem> getLabels() {
            return labels.values();
        }
    }

    public static class InvalidSwitchPayload extends ExceptionWithContext {
        private final int payloadOffset;

        public InvalidSwitchPayload(int payloadOffset) {
            super("No switch payload at offset: %d", payloadOffset);
            this.payloadOffset = payloadOffset;
        }

        public int getPayloadOffset() {
            return payloadOffset;
        }
    }
}
