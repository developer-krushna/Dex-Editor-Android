// $ANTLR 3.5.2 /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g 2023-05-04 15:21:24

package com.android.tools.smali.smali;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.*;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.TreeNodeStream;
import org.antlr.runtime.tree.TreeParser;
import org.antlr.runtime.tree.TreeRuleReturnScope;
import com.android.tools.smali.dexlib2.*;
import com.android.tools.smali.dexlib2.builder.Label;
import com.android.tools.smali.dexlib2.builder.MethodImplementationBuilder;
import com.android.tools.smali.dexlib2.builder.SwitchLabelElement;
import com.android.tools.smali.dexlib2.builder.instruction.*;
import com.android.tools.smali.dexlib2.iface.Annotation;
import com.android.tools.smali.dexlib2.iface.AnnotationElement;
import com.android.tools.smali.dexlib2.iface.ClassDef;
import com.android.tools.smali.dexlib2.iface.MethodImplementation;
import com.android.tools.smali.dexlib2.iface.reference.FieldReference;
import com.android.tools.smali.dexlib2.iface.reference.MethodReference;
import com.android.tools.smali.dexlib2.iface.value.EncodedValue;
import com.android.tools.smali.dexlib2.immutable.ImmutableAnnotation;
import com.android.tools.smali.dexlib2.immutable.ImmutableAnnotationElement;
import com.android.tools.smali.dexlib2.immutable.reference.ImmutableCallSiteReference;
import com.android.tools.smali.dexlib2.immutable.reference.ImmutableFieldReference;
import com.android.tools.smali.dexlib2.immutable.reference.ImmutableMethodHandleReference;
import com.android.tools.smali.dexlib2.immutable.reference.ImmutableMethodReference;
import com.android.tools.smali.dexlib2.immutable.reference.ImmutableMethodProtoReference;
import com.android.tools.smali.dexlib2.immutable.reference.ImmutableReference;
import com.android.tools.smali.dexlib2.immutable.reference.ImmutableTypeReference;
import com.android.tools.smali.dexlib2.immutable.value.*;
import com.android.tools.smali.dexlib2.util.MethodUtil;
import com.android.tools.smali.dexlib2.writer.InstructionFactory;
import com.android.tools.smali.dexlib2.writer.builder.*;
import com.android.tools.smali.util.LinearSearch;

import java.util.*;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class smaliTreeWalker extends TreeParser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ACCESS_SPEC", "ANNOTATION_DIRECTIVE",
		"ANNOTATION_VISIBILITY", "ARRAY_DATA_DIRECTIVE", "ARRAY_TYPE_PREFIX",
		"ARROW", "AT", "BOOL_LITERAL", "BYTE_LITERAL", "CATCHALL_DIRECTIVE", "CATCH_DIRECTIVE",
		"CHAR_LITERAL", "CLASS_DESCRIPTOR", "CLASS_DIRECTIVE", "CLOSE_BRACE",
		"CLOSE_PAREN", "COLON", "COMMA", "DOTDOT", "DOUBLE_LITERAL", "DOUBLE_LITERAL_OR_ID",
		"END_ANNOTATION_DIRECTIVE", "END_ARRAY_DATA_DIRECTIVE", "END_FIELD_DIRECTIVE",
		"END_LOCAL_DIRECTIVE", "END_METHOD_DIRECTIVE", "END_PACKED_SWITCH_DIRECTIVE",
		"END_PARAMETER_DIRECTIVE", "END_SPARSE_SWITCH_DIRECTIVE", "END_SUBANNOTATION_DIRECTIVE",
		"ENUM_DIRECTIVE", "EPILOGUE_DIRECTIVE", "EQUAL", "FIELD_DIRECTIVE", "FIELD_OFFSET",
		"FLOAT_LITERAL", "FLOAT_LITERAL_OR_ID", "HIDDENAPI_RESTRICTION", "IMPLEMENTS_DIRECTIVE",
		"INLINE_INDEX", "INSTRUCTION_FORMAT10t", "INSTRUCTION_FORMAT10x", "INSTRUCTION_FORMAT10x_ODEX",
		"INSTRUCTION_FORMAT11n", "INSTRUCTION_FORMAT11x", "INSTRUCTION_FORMAT12x",
		"INSTRUCTION_FORMAT12x_OR_ID", "INSTRUCTION_FORMAT20bc", "INSTRUCTION_FORMAT20t",
		"INSTRUCTION_FORMAT21c_FIELD", "INSTRUCTION_FORMAT21c_FIELD_ODEX", "INSTRUCTION_FORMAT21c_METHOD_HANDLE",
		"INSTRUCTION_FORMAT21c_METHOD_TYPE", "INSTRUCTION_FORMAT21c_STRING", "INSTRUCTION_FORMAT21c_TYPE",
		"INSTRUCTION_FORMAT21ih", "INSTRUCTION_FORMAT21lh", "INSTRUCTION_FORMAT21s",
		"INSTRUCTION_FORMAT21t", "INSTRUCTION_FORMAT22b", "INSTRUCTION_FORMAT22c_FIELD",
		"INSTRUCTION_FORMAT22c_FIELD_ODEX", "INSTRUCTION_FORMAT22c_TYPE", "INSTRUCTION_FORMAT22cs_FIELD",
		"INSTRUCTION_FORMAT22s", "INSTRUCTION_FORMAT22s_OR_ID", "INSTRUCTION_FORMAT22t",
		"INSTRUCTION_FORMAT22x", "INSTRUCTION_FORMAT23x", "INSTRUCTION_FORMAT30t",
		"INSTRUCTION_FORMAT31c", "INSTRUCTION_FORMAT31i", "INSTRUCTION_FORMAT31i_OR_ID",
		"INSTRUCTION_FORMAT31t", "INSTRUCTION_FORMAT32x", "INSTRUCTION_FORMAT35c_CALL_SITE",
		"INSTRUCTION_FORMAT35c_METHOD", "INSTRUCTION_FORMAT35c_METHOD_ODEX", "INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE",
		"INSTRUCTION_FORMAT35c_TYPE", "INSTRUCTION_FORMAT35mi_METHOD", "INSTRUCTION_FORMAT35ms_METHOD",
		"INSTRUCTION_FORMAT3rc_CALL_SITE", "INSTRUCTION_FORMAT3rc_METHOD", "INSTRUCTION_FORMAT3rc_METHOD_ODEX",
		"INSTRUCTION_FORMAT3rc_TYPE", "INSTRUCTION_FORMAT3rmi_METHOD", "INSTRUCTION_FORMAT3rms_METHOD",
		"INSTRUCTION_FORMAT45cc_METHOD", "INSTRUCTION_FORMAT4rcc_METHOD", "INSTRUCTION_FORMAT51l",
		"INTEGER_LITERAL", "INVALID_TOKEN", "I_ACCESS_LIST", "I_ACCESS_OR_RESTRICTION_LIST",
		"I_ANNOTATION", "I_ANNOTATIONS", "I_ANNOTATION_ELEMENT", "I_ARRAY_ELEMENTS",
		"I_ARRAY_ELEMENT_SIZE", "I_CALL_SITE_EXTRA_ARGUMENTS", "I_CALL_SITE_REFERENCE",
		"I_CATCH", "I_CATCHALL", "I_CATCHES", "I_CLASS_DEF", "I_ENCODED_ARRAY",
		"I_ENCODED_ENUM", "I_ENCODED_FIELD", "I_ENCODED_METHOD", "I_ENCODED_METHOD_HANDLE",
		"I_END_LOCAL", "I_EPILOGUE", "I_FIELD", "I_FIELDS", "I_FIELD_INITIAL_VALUE",
		"I_FIELD_TYPE", "I_IMPLEMENTS", "I_LABEL", "I_LINE", "I_LOCAL", "I_LOCALS",
		"I_METHOD", "I_METHODS", "I_METHOD_PROTOTYPE", "I_METHOD_RETURN_TYPE",
		"I_ORDERED_METHOD_ITEMS", "I_PACKED_SWITCH_ELEMENTS", "I_PACKED_SWITCH_START_KEY",
		"I_PARAMETER", "I_PARAMETERS", "I_PARAMETER_NOT_SPECIFIED", "I_PROLOGUE",
		"I_REGISTERS", "I_REGISTER_LIST", "I_REGISTER_RANGE", "I_RESTART_LOCAL",
		"I_SOURCE", "I_SPARSE_SWITCH_ELEMENTS", "I_STATEMENT_ARRAY_DATA", "I_STATEMENT_FORMAT10t",
		"I_STATEMENT_FORMAT10x", "I_STATEMENT_FORMAT11n", "I_STATEMENT_FORMAT11x",
		"I_STATEMENT_FORMAT12x", "I_STATEMENT_FORMAT20bc", "I_STATEMENT_FORMAT20t",
		"I_STATEMENT_FORMAT21c_FIELD", "I_STATEMENT_FORMAT21c_METHOD_HANDLE",
		"I_STATEMENT_FORMAT21c_METHOD_TYPE", "I_STATEMENT_FORMAT21c_STRING", "I_STATEMENT_FORMAT21c_TYPE",
		"I_STATEMENT_FORMAT21ih", "I_STATEMENT_FORMAT21lh", "I_STATEMENT_FORMAT21s",
		"I_STATEMENT_FORMAT21t", "I_STATEMENT_FORMAT22b", "I_STATEMENT_FORMAT22c_FIELD",
		"I_STATEMENT_FORMAT22c_TYPE", "I_STATEMENT_FORMAT22s", "I_STATEMENT_FORMAT22t",
		"I_STATEMENT_FORMAT22x", "I_STATEMENT_FORMAT23x", "I_STATEMENT_FORMAT30t",
		"I_STATEMENT_FORMAT31c", "I_STATEMENT_FORMAT31i", "I_STATEMENT_FORMAT31t",
		"I_STATEMENT_FORMAT32x", "I_STATEMENT_FORMAT35c_CALL_SITE", "I_STATEMENT_FORMAT35c_METHOD",
		"I_STATEMENT_FORMAT35c_TYPE", "I_STATEMENT_FORMAT3rc_CALL_SITE", "I_STATEMENT_FORMAT3rc_METHOD",
		"I_STATEMENT_FORMAT3rc_TYPE", "I_STATEMENT_FORMAT45cc_METHOD", "I_STATEMENT_FORMAT4rcc_METHOD",
		"I_STATEMENT_FORMAT51l", "I_STATEMENT_PACKED_SWITCH", "I_STATEMENT_SPARSE_SWITCH",
		"I_SUBANNOTATION", "I_SUPER", "LINE_COMMENT", "LINE_DIRECTIVE", "LOCALS_DIRECTIVE",
		"LOCAL_DIRECTIVE", "LONG_LITERAL", "MEMBER_NAME", "METHOD_DIRECTIVE",
		"METHOD_HANDLE_TYPE_FIELD", "METHOD_HANDLE_TYPE_METHOD", "NEGATIVE_INTEGER_LITERAL",
		"NULL_LITERAL", "OPEN_BRACE", "OPEN_PAREN", "PACKED_SWITCH_DIRECTIVE",
		"PARAMETER_DIRECTIVE", "PARAM_LIST_OR_ID_PRIMITIVE_TYPE", "POSITIVE_INTEGER_LITERAL",
		"PRIMITIVE_TYPE", "PROLOGUE_DIRECTIVE", "REGISTER", "REGISTERS_DIRECTIVE",
		"RESTART_LOCAL_DIRECTIVE", "SHORT_LITERAL", "SIMPLE_NAME", "SOURCE_DIRECTIVE",
		"SPARSE_SWITCH_DIRECTIVE", "STRING_LITERAL", "SUBANNOTATION_DIRECTIVE",
		"SUPER_DIRECTIVE", "VERIFICATION_ERROR_TYPE", "VOID_TYPE", "VTABLE_INDEX",
		"WHITE_SPACE"
	};
	public static final int EOF=-1;
	public static final int ACCESS_SPEC=4;
	public static final int ANNOTATION_DIRECTIVE=5;
	public static final int ANNOTATION_VISIBILITY=6;
	public static final int ARRAY_DATA_DIRECTIVE=7;
	public static final int ARRAY_TYPE_PREFIX=8;
	public static final int ARROW=9;
	public static final int AT=10;
	public static final int BOOL_LITERAL=11;
	public static final int BYTE_LITERAL=12;
	public static final int CATCHALL_DIRECTIVE=13;
	public static final int CATCH_DIRECTIVE=14;
	public static final int CHAR_LITERAL=15;
	public static final int CLASS_DESCRIPTOR=16;
	public static final int CLASS_DIRECTIVE=17;
	public static final int CLOSE_BRACE=18;
	public static final int CLOSE_PAREN=19;
	public static final int COLON=20;
	public static final int COMMA=21;
	public static final int DOTDOT=22;
	public static final int DOUBLE_LITERAL=23;
	public static final int DOUBLE_LITERAL_OR_ID=24;
	public static final int END_ANNOTATION_DIRECTIVE=25;
	public static final int END_ARRAY_DATA_DIRECTIVE=26;
	public static final int END_FIELD_DIRECTIVE=27;
	public static final int END_LOCAL_DIRECTIVE=28;
	public static final int END_METHOD_DIRECTIVE=29;
	public static final int END_PACKED_SWITCH_DIRECTIVE=30;
	public static final int END_PARAMETER_DIRECTIVE=31;
	public static final int END_SPARSE_SWITCH_DIRECTIVE=32;
	public static final int END_SUBANNOTATION_DIRECTIVE=33;
	public static final int ENUM_DIRECTIVE=34;
	public static final int EPILOGUE_DIRECTIVE=35;
	public static final int EQUAL=36;
	public static final int FIELD_DIRECTIVE=37;
	public static final int FIELD_OFFSET=38;
	public static final int FLOAT_LITERAL=39;
	public static final int FLOAT_LITERAL_OR_ID=40;
	public static final int HIDDENAPI_RESTRICTION=41;
	public static final int IMPLEMENTS_DIRECTIVE=42;
	public static final int INLINE_INDEX=43;
	public static final int INSTRUCTION_FORMAT10t=44;
	public static final int INSTRUCTION_FORMAT10x=45;
	public static final int INSTRUCTION_FORMAT10x_ODEX=46;
	public static final int INSTRUCTION_FORMAT11n=47;
	public static final int INSTRUCTION_FORMAT11x=48;
	public static final int INSTRUCTION_FORMAT12x=49;
	public static final int INSTRUCTION_FORMAT12x_OR_ID=50;
	public static final int INSTRUCTION_FORMAT20bc=51;
	public static final int INSTRUCTION_FORMAT20t=52;
	public static final int INSTRUCTION_FORMAT21c_FIELD=53;
	public static final int INSTRUCTION_FORMAT21c_FIELD_ODEX=54;
	public static final int INSTRUCTION_FORMAT21c_METHOD_HANDLE=55;
	public static final int INSTRUCTION_FORMAT21c_METHOD_TYPE=56;
	public static final int INSTRUCTION_FORMAT21c_STRING=57;
	public static final int INSTRUCTION_FORMAT21c_TYPE=58;
	public static final int INSTRUCTION_FORMAT21ih=59;
	public static final int INSTRUCTION_FORMAT21lh=60;
	public static final int INSTRUCTION_FORMAT21s=61;
	public static final int INSTRUCTION_FORMAT21t=62;
	public static final int INSTRUCTION_FORMAT22b=63;
	public static final int INSTRUCTION_FORMAT22c_FIELD=64;
	public static final int INSTRUCTION_FORMAT22c_FIELD_ODEX=65;
	public static final int INSTRUCTION_FORMAT22c_TYPE=66;
	public static final int INSTRUCTION_FORMAT22cs_FIELD=67;
	public static final int INSTRUCTION_FORMAT22s=68;
	public static final int INSTRUCTION_FORMAT22s_OR_ID=69;
	public static final int INSTRUCTION_FORMAT22t=70;
	public static final int INSTRUCTION_FORMAT22x=71;
	public static final int INSTRUCTION_FORMAT23x=72;
	public static final int INSTRUCTION_FORMAT30t=73;
	public static final int INSTRUCTION_FORMAT31c=74;
	public static final int INSTRUCTION_FORMAT31i=75;
	public static final int INSTRUCTION_FORMAT31i_OR_ID=76;
	public static final int INSTRUCTION_FORMAT31t=77;
	public static final int INSTRUCTION_FORMAT32x=78;
	public static final int INSTRUCTION_FORMAT35c_CALL_SITE=79;
	public static final int INSTRUCTION_FORMAT35c_METHOD=80;
	public static final int INSTRUCTION_FORMAT35c_METHOD_ODEX=81;
	public static final int INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE=82;
	public static final int INSTRUCTION_FORMAT35c_TYPE=83;
	public static final int INSTRUCTION_FORMAT35mi_METHOD=84;
	public static final int INSTRUCTION_FORMAT35ms_METHOD=85;
	public static final int INSTRUCTION_FORMAT3rc_CALL_SITE=86;
	public static final int INSTRUCTION_FORMAT3rc_METHOD=87;
	public static final int INSTRUCTION_FORMAT3rc_METHOD_ODEX=88;
	public static final int INSTRUCTION_FORMAT3rc_TYPE=89;
	public static final int INSTRUCTION_FORMAT3rmi_METHOD=90;
	public static final int INSTRUCTION_FORMAT3rms_METHOD=91;
	public static final int INSTRUCTION_FORMAT45cc_METHOD=92;
	public static final int INSTRUCTION_FORMAT4rcc_METHOD=93;
	public static final int INSTRUCTION_FORMAT51l=94;
	public static final int INTEGER_LITERAL=95;
	public static final int INVALID_TOKEN=96;
	public static final int I_ACCESS_LIST=97;
	public static final int I_ACCESS_OR_RESTRICTION_LIST=98;
	public static final int I_ANNOTATION=99;
	public static final int I_ANNOTATIONS=100;
	public static final int I_ANNOTATION_ELEMENT=101;
	public static final int I_ARRAY_ELEMENTS=102;
	public static final int I_ARRAY_ELEMENT_SIZE=103;
	public static final int I_CALL_SITE_EXTRA_ARGUMENTS=104;
	public static final int I_CALL_SITE_REFERENCE=105;
	public static final int I_CATCH=106;
	public static final int I_CATCHALL=107;
	public static final int I_CATCHES=108;
	public static final int I_CLASS_DEF=109;
	public static final int I_ENCODED_ARRAY=110;
	public static final int I_ENCODED_ENUM=111;
	public static final int I_ENCODED_FIELD=112;
	public static final int I_ENCODED_METHOD=113;
	public static final int I_ENCODED_METHOD_HANDLE=114;
	public static final int I_END_LOCAL=115;
	public static final int I_EPILOGUE=116;
	public static final int I_FIELD=117;
	public static final int I_FIELDS=118;
	public static final int I_FIELD_INITIAL_VALUE=119;
	public static final int I_FIELD_TYPE=120;
	public static final int I_IMPLEMENTS=121;
	public static final int I_LABEL=122;
	public static final int I_LINE=123;
	public static final int I_LOCAL=124;
	public static final int I_LOCALS=125;
	public static final int I_METHOD=126;
	public static final int I_METHODS=127;
	public static final int I_METHOD_PROTOTYPE=128;
	public static final int I_METHOD_RETURN_TYPE=129;
	public static final int I_ORDERED_METHOD_ITEMS=130;
	public static final int I_PACKED_SWITCH_ELEMENTS=131;
	public static final int I_PACKED_SWITCH_START_KEY=132;
	public static final int I_PARAMETER=133;
	public static final int I_PARAMETERS=134;
	public static final int I_PARAMETER_NOT_SPECIFIED=135;
	public static final int I_PROLOGUE=136;
	public static final int I_REGISTERS=137;
	public static final int I_REGISTER_LIST=138;
	public static final int I_REGISTER_RANGE=139;
	public static final int I_RESTART_LOCAL=140;
	public static final int I_SOURCE=141;
	public static final int I_SPARSE_SWITCH_ELEMENTS=142;
	public static final int I_STATEMENT_ARRAY_DATA=143;
	public static final int I_STATEMENT_FORMAT10t=144;
	public static final int I_STATEMENT_FORMAT10x=145;
	public static final int I_STATEMENT_FORMAT11n=146;
	public static final int I_STATEMENT_FORMAT11x=147;
	public static final int I_STATEMENT_FORMAT12x=148;
	public static final int I_STATEMENT_FORMAT20bc=149;
	public static final int I_STATEMENT_FORMAT20t=150;
	public static final int I_STATEMENT_FORMAT21c_FIELD=151;
	public static final int I_STATEMENT_FORMAT21c_METHOD_HANDLE=152;
	public static final int I_STATEMENT_FORMAT21c_METHOD_TYPE=153;
	public static final int I_STATEMENT_FORMAT21c_STRING=154;
	public static final int I_STATEMENT_FORMAT21c_TYPE=155;
	public static final int I_STATEMENT_FORMAT21ih=156;
	public static final int I_STATEMENT_FORMAT21lh=157;
	public static final int I_STATEMENT_FORMAT21s=158;
	public static final int I_STATEMENT_FORMAT21t=159;
	public static final int I_STATEMENT_FORMAT22b=160;
	public static final int I_STATEMENT_FORMAT22c_FIELD=161;
	public static final int I_STATEMENT_FORMAT22c_TYPE=162;
	public static final int I_STATEMENT_FORMAT22s=163;
	public static final int I_STATEMENT_FORMAT22t=164;
	public static final int I_STATEMENT_FORMAT22x=165;
	public static final int I_STATEMENT_FORMAT23x=166;
	public static final int I_STATEMENT_FORMAT30t=167;
	public static final int I_STATEMENT_FORMAT31c=168;
	public static final int I_STATEMENT_FORMAT31i=169;
	public static final int I_STATEMENT_FORMAT31t=170;
	public static final int I_STATEMENT_FORMAT32x=171;
	public static final int I_STATEMENT_FORMAT35c_CALL_SITE=172;
	public static final int I_STATEMENT_FORMAT35c_METHOD=173;
	public static final int I_STATEMENT_FORMAT35c_TYPE=174;
	public static final int I_STATEMENT_FORMAT3rc_CALL_SITE=175;
	public static final int I_STATEMENT_FORMAT3rc_METHOD=176;
	public static final int I_STATEMENT_FORMAT3rc_TYPE=177;
	public static final int I_STATEMENT_FORMAT45cc_METHOD=178;
	public static final int I_STATEMENT_FORMAT4rcc_METHOD=179;
	public static final int I_STATEMENT_FORMAT51l=180;
	public static final int I_STATEMENT_PACKED_SWITCH=181;
	public static final int I_STATEMENT_SPARSE_SWITCH=182;
	public static final int I_SUBANNOTATION=183;
	public static final int I_SUPER=184;
	public static final int LINE_COMMENT=185;
	public static final int LINE_DIRECTIVE=186;
	public static final int LOCALS_DIRECTIVE=187;
	public static final int LOCAL_DIRECTIVE=188;
	public static final int LONG_LITERAL=189;
	public static final int MEMBER_NAME=190;
	public static final int METHOD_DIRECTIVE=191;
	public static final int METHOD_HANDLE_TYPE_FIELD=192;
	public static final int METHOD_HANDLE_TYPE_METHOD=193;
	public static final int NEGATIVE_INTEGER_LITERAL=194;
	public static final int NULL_LITERAL=195;
	public static final int OPEN_BRACE=196;
	public static final int OPEN_PAREN=197;
	public static final int PACKED_SWITCH_DIRECTIVE=198;
	public static final int PARAMETER_DIRECTIVE=199;
	public static final int PARAM_LIST_OR_ID_PRIMITIVE_TYPE=200;
	public static final int POSITIVE_INTEGER_LITERAL=201;
	public static final int PRIMITIVE_TYPE=202;
	public static final int PROLOGUE_DIRECTIVE=203;
	public static final int REGISTER=204;
	public static final int REGISTERS_DIRECTIVE=205;
	public static final int RESTART_LOCAL_DIRECTIVE=206;
	public static final int SHORT_LITERAL=207;
	public static final int SIMPLE_NAME=208;
	public static final int SOURCE_DIRECTIVE=209;
	public static final int SPARSE_SWITCH_DIRECTIVE=210;
	public static final int STRING_LITERAL=211;
	public static final int SUBANNOTATION_DIRECTIVE=212;
	public static final int SUPER_DIRECTIVE=213;
	public static final int VERIFICATION_ERROR_TYPE=214;
	public static final int VOID_TYPE=215;
	public static final int VTABLE_INDEX=216;
	public static final int WHITE_SPACE=217;

	// delegates
	public TreeParser[] getDelegates() {
		return new TreeParser[] {};
	}

	// delegators


	public smaliTreeWalker(TreeNodeStream input) {
		this(input, new RecognizerSharedState());
	}
	public smaliTreeWalker(TreeNodeStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return smaliTreeWalker.tokenNames; }
	@Override public String getGrammarFileName() { return "/usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g"; }


	  public String classType;
	  private boolean verboseErrors = false;
	  private int apiLevel = 15;
	  private Opcodes opcodes = Opcodes.forApi(apiLevel);
	  private DexBuilder dexBuilder;
	  private int callSiteNameIndex = 0;

	  public void setDexBuilder(DexBuilder dexBuilder) {
	      this.dexBuilder = dexBuilder;
	  }

	  public void setApiLevel(int apiLevel) {
	      this.opcodes = Opcodes.forApi(apiLevel);
	      this.apiLevel = apiLevel;
	  }

	  public void setVerboseErrors(boolean verboseErrors) {
	    this.verboseErrors = verboseErrors;
	  }

	  private byte parseRegister_nibble(String register)
	      throws SemanticException {
	    int totalMethodRegisters = method_stack.peek().totalMethodRegisters;
	    int methodParameterRegisters = method_stack.peek().methodParameterRegisters;

	    //register should be in the format "v12"
	    int val = Byte.parseByte(register.substring(1));
	    if (register.charAt(0) == 'p') {
	      val = totalMethodRegisters - methodParameterRegisters + val;
	    }
	    if (val >= 2<<4) {
	      throw new SemanticException(input, "The maximum allowed register in this context is list of registers is v15");
	    }
	    //the parser wouldn't have accepted a negative register, i.e. v-1, so we don't have to check for val<0;
	    return (byte)val;
	  }

	  //return a short, because java's byte is signed
	  private short parseRegister_byte(String register)
	      throws SemanticException {
	    int totalMethodRegisters = method_stack.peek().totalMethodRegisters;
	    int methodParameterRegisters = method_stack.peek().methodParameterRegisters;
	    //register should be in the format "v123"
	    int val = Short.parseShort(register.substring(1));
	    if (register.charAt(0) == 'p') {
	      val = totalMethodRegisters - methodParameterRegisters + val;
	    }
	    if (val >= 2<<8) {
	      throw new SemanticException(input, "The maximum allowed register in this context is v255");
	    }
	    return (short)val;
	  }

	  //return an int because java's short is signed
	  private int parseRegister_short(String register)
	      throws SemanticException {
	    int totalMethodRegisters = method_stack.peek().totalMethodRegisters;
	    int methodParameterRegisters = method_stack.peek().methodParameterRegisters;
	    //register should be in the format "v12345"
	    int val = Integer.parseInt(register.substring(1));
	    if (register.charAt(0) == 'p') {
	      val = totalMethodRegisters - methodParameterRegisters + val;
	    }
	    if (val >= 2<<16) {
	      throw new SemanticException(input, "The maximum allowed register in this context is v65535");
	    }
	    //the parser wouldn't accept a negative register, i.e. v-1, so we don't have to check for val<0;
	    return val;
	  }

	  public String getErrorMessage(RecognitionException e, String[] tokenNames) {
	    if ( e instanceof SemanticException ) {
	      return e.getMessage();
	    } else {
	      return super.getErrorMessage(e, tokenNames);
	    }
	  }

	  public String getErrorHeader(RecognitionException e) {
	    return getSourceName()+"["+ e.line+","+e.charPositionInLine+"]";
	  }



	// $ANTLR start "smali_file"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:162:1: smali_file returns [ClassDef classDef] : ^( I_CLASS_DEF header methods fields annotations ) ;
	public final ClassDef smali_file() throws RecognitionException {
		ClassDef classDef = null;


		TreeRuleReturnScope header1 =null;
		Set<Annotation> annotations2 =null;
		List<BuilderField> fields3 =null;
		List<BuilderMethod> methods4 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:163:3: ( ^( I_CLASS_DEF header methods fields annotations ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:163:5: ^( I_CLASS_DEF header methods fields annotations )
			{
			match(input,I_CLASS_DEF,FOLLOW_I_CLASS_DEF_in_smali_file52);
			match(input, Token.DOWN, null);
			pushFollow(FOLLOW_header_in_smali_file54);
			header1=header();
			state._fsp--;

			pushFollow(FOLLOW_methods_in_smali_file56);
			methods4=methods();
			state._fsp--;

			pushFollow(FOLLOW_fields_in_smali_file58);
			fields3=fields();
			state._fsp--;

			pushFollow(FOLLOW_annotations_in_smali_file60);
			annotations2=annotations();
			state._fsp--;

			match(input, Token.UP, null);


			    classDef = dexBuilder.internClassDef((header1!=null?((smaliTreeWalker.header_return)header1).classType:null), (header1!=null?((smaliTreeWalker.header_return)header1).accessFlags:0), (header1!=null?((smaliTreeWalker.header_return)header1).superType:null),
			            (header1!=null?((smaliTreeWalker.header_return)header1).implementsList:null), (header1!=null?((smaliTreeWalker.header_return)header1).sourceSpec:null), annotations2, fields3, methods4);
			
			}

		}
		catch (Exception ex) {

			    if (verboseErrors) {
			      ex.printStackTrace(System.err);
			    }
			    reportError(new SemanticException(input, ex));
			
		}

		finally {
			// do for sure before leaving
		}
		return classDef;
	}
	// $ANTLR end "smali_file"


	public static class header_return extends TreeRuleReturnScope {
		public String classType;
		public int accessFlags;
		public String superType;
		public List<String> implementsList;
		public String sourceSpec;
	};


	// $ANTLR start "header"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:176:1: header returns [String classType, int accessFlags, String superType, List<String> implementsList, String sourceSpec] : class_spec ( super_spec )? implements_list source_spec ;
	public final smaliTreeWalker.header_return header() throws RecognitionException {
		smaliTreeWalker.header_return retval = new smaliTreeWalker.header_return();
		retval.start = input.LT(1);

		TreeRuleReturnScope class_spec5 =null;
		String super_spec6 =null;
		List<String> implements_list7 =null;
		String source_spec8 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:177:3: ( class_spec ( super_spec )? implements_list source_spec )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:177:3: class_spec ( super_spec )? implements_list source_spec
			{
			pushFollow(FOLLOW_class_spec_in_header85);
			class_spec5=class_spec();
			state._fsp--;

			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:177:14: ( super_spec )?
			int alt1=2;
			int LA1_0 = input.LA(1);
			if ( (LA1_0==I_SUPER) ) {
				alt1=1;
			}
			switch (alt1) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:177:14: super_spec
					{
					pushFollow(FOLLOW_super_spec_in_header87);
					super_spec6=super_spec();
					state._fsp--;

					}
					break;

			}

			pushFollow(FOLLOW_implements_list_in_header90);
			implements_list7=implements_list();
			state._fsp--;

			pushFollow(FOLLOW_source_spec_in_header92);
			source_spec8=source_spec();
			state._fsp--;


			    classType = (class_spec5!=null?((smaliTreeWalker.class_spec_return)class_spec5).type:null);
			    retval.classType = classType;
			    retval.accessFlags = (class_spec5!=null?((smaliTreeWalker.class_spec_return)class_spec5).accessFlags:0);
			    retval.superType = super_spec6;
			    retval.implementsList = implements_list7;
			    retval.sourceSpec = source_spec8;
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "header"


	public static class class_spec_return extends TreeRuleReturnScope {
		public String type;
		public int accessFlags;
	};


	// $ANTLR start "class_spec"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:188:1: class_spec returns [String type, int accessFlags] : CLASS_DESCRIPTOR access_list ;
	public final smaliTreeWalker.class_spec_return class_spec() throws RecognitionException {
		smaliTreeWalker.class_spec_return retval = new smaliTreeWalker.class_spec_return();
		retval.start = input.LT(1);

		CommonTree CLASS_DESCRIPTOR9=null;
		int access_list10 =0;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:189:3: ( CLASS_DESCRIPTOR access_list )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:189:5: CLASS_DESCRIPTOR access_list
			{
			CLASS_DESCRIPTOR9=(CommonTree)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_class_spec110);
			pushFollow(FOLLOW_access_list_in_class_spec112);
			access_list10=access_list();
			state._fsp--;


			    retval.type = (CLASS_DESCRIPTOR9!=null?CLASS_DESCRIPTOR9.getText():null);
			    retval.accessFlags = access_list10;
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "class_spec"



	// $ANTLR start "super_spec"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:195:1: super_spec returns [String type] : ^( I_SUPER CLASS_DESCRIPTOR ) ;
	public final String super_spec() throws RecognitionException {
		String type = null;


		CommonTree CLASS_DESCRIPTOR11=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:196:3: ( ^( I_SUPER CLASS_DESCRIPTOR ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:196:5: ^( I_SUPER CLASS_DESCRIPTOR )
			{
			match(input,I_SUPER,FOLLOW_I_SUPER_in_super_spec130);
			match(input, Token.DOWN, null);
			CLASS_DESCRIPTOR11=(CommonTree)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_super_spec132);
			match(input, Token.UP, null);


			    type = (CLASS_DESCRIPTOR11!=null?CLASS_DESCRIPTOR11.getText():null);
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return type;
	}
	// $ANTLR end "super_spec"



	// $ANTLR start "implements_spec"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:202:1: implements_spec returns [String type] : ^( I_IMPLEMENTS CLASS_DESCRIPTOR ) ;
	public final String implements_spec() throws RecognitionException {
		String type = null;


		CommonTree CLASS_DESCRIPTOR12=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:203:3: ( ^( I_IMPLEMENTS CLASS_DESCRIPTOR ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:203:5: ^( I_IMPLEMENTS CLASS_DESCRIPTOR )
			{
			match(input,I_IMPLEMENTS,FOLLOW_I_IMPLEMENTS_in_implements_spec152);
			match(input, Token.DOWN, null);
			CLASS_DESCRIPTOR12=(CommonTree)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_implements_spec154);
			match(input, Token.UP, null);


			    type = (CLASS_DESCRIPTOR12!=null?CLASS_DESCRIPTOR12.getText():null);
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return type;
	}
	// $ANTLR end "implements_spec"



	// $ANTLR start "implements_list"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:208:1: implements_list returns [List<String> implementsList] : ( implements_spec )* ;
	public final List<String> implements_list() throws RecognitionException {
		List<String> implementsList = null;


		String implements_spec13 =null;

		 List<String> typeList;
		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:210:3: ( ( implements_spec )* )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:210:5: ( implements_spec )*
			{
			typeList = Lists.newArrayList();
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:211:5: ( implements_spec )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==I_IMPLEMENTS) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:211:6: implements_spec
					{
					pushFollow(FOLLOW_implements_spec_in_implements_list184);
					implements_spec13=implements_spec();
					state._fsp--;

					typeList.add(implements_spec13);
					}
					break;

				default :
					break loop2;
				}
			}


			    if (typeList.size() > 0) {
			      implementsList = typeList;
			    } else {
			      implementsList = null;
			    }
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return implementsList;
	}
	// $ANTLR end "implements_list"



	// $ANTLR start "source_spec"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:220:1: source_spec returns [String source] : ( ^( I_SOURCE string_literal ) |);
	public final String source_spec() throws RecognitionException {
		String source = null;


		String string_literal14 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:221:3: ( ^( I_SOURCE string_literal ) |)
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==I_SOURCE) ) {
				alt3=1;
			}
			else if ( (LA3_0==I_METHODS) ) {
				alt3=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}

			switch (alt3) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:221:5: ^( I_SOURCE string_literal )
					{
					source = null;
					match(input,I_SOURCE,FOLLOW_I_SOURCE_in_source_spec213);
					match(input, Token.DOWN, null);
					pushFollow(FOLLOW_string_literal_in_source_spec215);
					string_literal14=string_literal();
					state._fsp--;

					source = string_literal14;
					match(input, Token.UP, null);

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:223:16:
					{
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return source;
	}
	// $ANTLR end "source_spec"



	// $ANTLR start "access_list"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:225:1: access_list returns [int value] : ^( I_ACCESS_LIST ( ACCESS_SPEC )* ) ;
	public final int access_list() throws RecognitionException {
		int value = 0;


		CommonTree ACCESS_SPEC15=null;


		    value = 0;
		
		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:230:3: ( ^( I_ACCESS_LIST ( ACCESS_SPEC )* ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:230:5: ^( I_ACCESS_LIST ( ACCESS_SPEC )* )
			{
			match(input,I_ACCESS_LIST,FOLLOW_I_ACCESS_LIST_in_access_list247);
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:231:7: ( ACCESS_SPEC )*
				loop4:
				while (true) {
					int alt4=2;
					int LA4_0 = input.LA(1);
					if ( (LA4_0==ACCESS_SPEC) ) {
						alt4=1;
					}

					switch (alt4) {
					case 1 :
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:232:9: ACCESS_SPEC
						{
						ACCESS_SPEC15=(CommonTree)match(input,ACCESS_SPEC,FOLLOW_ACCESS_SPEC_in_access_list265);

						          value |= AccessFlags.getAccessFlag(ACCESS_SPEC15.getText()).getValue();
						
						}
						break;

					default :
						break loop4;
					}
				}

				match(input, Token.UP, null);
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "access_list"


	public static class access_or_restriction_list_return extends TreeRuleReturnScope {
		public int value;
		public Set<HiddenApiRestriction> hiddenApiRestrictions;
	};


	// $ANTLR start "access_or_restriction_list"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:238:1: access_or_restriction_list returns [int value, Set<HiddenApiRestriction> hiddenApiRestrictions] : ^( I_ACCESS_OR_RESTRICTION_LIST ( ACCESS_SPEC | HIDDENAPI_RESTRICTION )* ) ;
	public final smaliTreeWalker.access_or_restriction_list_return access_or_restriction_list() throws RecognitionException {
		smaliTreeWalker.access_or_restriction_list_return retval = new smaliTreeWalker.access_or_restriction_list_return();
		retval.start = input.LT(1);

		CommonTree ACCESS_SPEC16=null;
		CommonTree HIDDENAPI_RESTRICTION17=null;


		    retval.value = 0;
		    HiddenApiRestriction hiddenApiRestriction = null;
		    List<HiddenApiRestriction> domainSpecificApiRestrictions = new ArrayList<>();
		
		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:245:3: ( ^( I_ACCESS_OR_RESTRICTION_LIST ( ACCESS_SPEC | HIDDENAPI_RESTRICTION )* ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:245:5: ^( I_ACCESS_OR_RESTRICTION_LIST ( ACCESS_SPEC | HIDDENAPI_RESTRICTION )* )
			{
			match(input,I_ACCESS_OR_RESTRICTION_LIST,FOLLOW_I_ACCESS_OR_RESTRICTION_LIST_in_access_or_restriction_list308);
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:246:7: ( ACCESS_SPEC | HIDDENAPI_RESTRICTION )*
				loop5:
				while (true) {
					int alt5=3;
					int LA5_0 = input.LA(1);
					if ( (LA5_0==ACCESS_SPEC) ) {
						alt5=1;
					}
					else if ( (LA5_0==HIDDENAPI_RESTRICTION) ) {
						alt5=2;
					}

					switch (alt5) {
					case 1 :
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:247:9: ACCESS_SPEC
						{
						ACCESS_SPEC16=(CommonTree)match(input,ACCESS_SPEC,FOLLOW_ACCESS_SPEC_in_access_or_restriction_list326);

						          retval.value |= AccessFlags.getAccessFlag(ACCESS_SPEC16.getText()).getValue();
						
						}
						break;
					case 2 :
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:252:9: HIDDENAPI_RESTRICTION
						{
						HIDDENAPI_RESTRICTION17=(CommonTree)match(input,HIDDENAPI_RESTRICTION,FOLLOW_HIDDENAPI_RESTRICTION_in_access_or_restriction_list356);

						          if (opcodes.api < 29) {
						              throw new SemanticException(input, HIDDENAPI_RESTRICTION17, "Hidden API restrictions are only supported on api 29 and above.");
						          }

						          HiddenApiRestriction restriction = HiddenApiRestriction.forName(HIDDENAPI_RESTRICTION17.getText());
						          if (restriction.isDomainSpecificApiFlag()) {
						             domainSpecificApiRestrictions.add(restriction);
						          } else {
						            if (hiddenApiRestriction != null) {
						              throw new SemanticException(input, HIDDENAPI_RESTRICTION17, "Only one hidden api restriction may be specified.");
						            }
						            hiddenApiRestriction = restriction;
						          }
						
						}
						break;

					default :
						break loop5;
					}
				}

				match(input, Token.UP, null);
			}


			        ImmutableSet.Builder builder = ImmutableSet.builder();
			        if (hiddenApiRestriction != null) {
			          builder.add(hiddenApiRestriction);
			        }
			        builder.addAll(domainSpecificApiRestrictions);
			        retval.hiddenApiRestrictions = builder.build();
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "access_or_restriction_list"



	// $ANTLR start "fields"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:278:1: fields returns [List<BuilderField> fields] : ^( I_FIELDS ( field )* ) ;
	public final List<BuilderField> fields() throws RecognitionException {
		List<BuilderField> fields = null;


		BuilderField field18 =null;

		fields = Lists.newArrayList();
		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:280:3: ( ^( I_FIELDS ( field )* ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:280:5: ^( I_FIELDS ( field )* )
			{
			match(input,I_FIELDS,FOLLOW_I_FIELDS_in_fields405);
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:281:7: ( field )*
				loop6:
				while (true) {
					int alt6=2;
					int LA6_0 = input.LA(1);
					if ( (LA6_0==I_FIELD) ) {
						alt6=1;
					}

					switch (alt6) {
					case 1 :
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:281:8: field
						{
						pushFollow(FOLLOW_field_in_fields414);
						field18=field();
						state._fsp--;


						        fields.add(field18);
						
						}
						break;

					default :
						break loop6;
					}
				}

				match(input, Token.UP, null);
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return fields;
	}
	// $ANTLR end "fields"



	// $ANTLR start "methods"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:286:1: methods returns [List<BuilderMethod> methods] : ^( I_METHODS ( method )* ) ;
	public final List<BuilderMethod> methods() throws RecognitionException {
		List<BuilderMethod> methods = null;


		BuilderMethod method19 =null;

		methods = Lists.newArrayList();
		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:288:3: ( ^( I_METHODS ( method )* ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:288:5: ^( I_METHODS ( method )* )
			{
			match(input,I_METHODS,FOLLOW_I_METHODS_in_methods446);
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:289:7: ( method )*
				loop7:
				while (true) {
					int alt7=2;
					int LA7_0 = input.LA(1);
					if ( (LA7_0==I_METHOD) ) {
						alt7=1;
					}

					switch (alt7) {
					case 1 :
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:289:8: method
						{
						pushFollow(FOLLOW_method_in_methods455);
						method19=method();
						state._fsp--;


						        methods.add(method19);
						
						}
						break;

					default :
						break loop7;
					}
				}

				match(input, Token.UP, null);
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return methods;
	}
	// $ANTLR end "methods"



	// $ANTLR start "field"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:294:1: field returns [BuilderField field] : ^( I_FIELD SIMPLE_NAME access_or_restriction_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) field_initial_value ( annotations )? ) ;
	public final BuilderField field() throws RecognitionException {
		BuilderField field = null;


		CommonTree SIMPLE_NAME22=null;
		TreeRuleReturnScope access_or_restriction_list20 =null;
		EncodedValue field_initial_value21 =null;
		TreeRuleReturnScope nonvoid_type_descriptor23 =null;
		Set<Annotation> annotations24 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:295:3: ( ^( I_FIELD SIMPLE_NAME access_or_restriction_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) field_initial_value ( annotations )? ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:295:4: ^( I_FIELD SIMPLE_NAME access_or_restriction_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) field_initial_value ( annotations )? )
			{
			match(input,I_FIELD,FOLLOW_I_FIELD_in_field480);
			match(input, Token.DOWN, null);
			SIMPLE_NAME22=(CommonTree)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_field482);
			pushFollow(FOLLOW_access_or_restriction_list_in_field484);
			access_or_restriction_list20=access_or_restriction_list();
			state._fsp--;

			match(input,I_FIELD_TYPE,FOLLOW_I_FIELD_TYPE_in_field487);
			match(input, Token.DOWN, null);
			pushFollow(FOLLOW_nonvoid_type_descriptor_in_field489);
			nonvoid_type_descriptor23=nonvoid_type_descriptor();
			state._fsp--;

			match(input, Token.UP, null);

			pushFollow(FOLLOW_field_initial_value_in_field492);
			field_initial_value21=field_initial_value();
			state._fsp--;

			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:295:113: ( annotations )?
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==I_ANNOTATIONS) ) {
				alt8=1;
			}
			switch (alt8) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:295:113: annotations
					{
					pushFollow(FOLLOW_annotations_in_field494);
					annotations24=annotations();
					state._fsp--;

					}
					break;

			}

			match(input, Token.UP, null);


			    int accessFlags = (access_or_restriction_list20!=null?((smaliTreeWalker.access_or_restriction_list_return)access_or_restriction_list20).value:0);
			    Set<HiddenApiRestriction> hiddenApiRestrictions = (access_or_restriction_list20!=null?((smaliTreeWalker.access_or_restriction_list_return)access_or_restriction_list20).hiddenApiRestrictions:null);

			    if (!AccessFlags.STATIC.isSet(accessFlags) && field_initial_value21 != null) {
			        throw new SemanticException(input, "Initial field values can only be specified for static fields.");
			    }

			    field = dexBuilder.internField(classType, (SIMPLE_NAME22!=null?SIMPLE_NAME22.getText():null), (nonvoid_type_descriptor23!=null?((smaliTreeWalker.nonvoid_type_descriptor_return)nonvoid_type_descriptor23).type:null), accessFlags,
			            field_initial_value21, annotations24, hiddenApiRestrictions);
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return field;
	}
	// $ANTLR end "field"



	// $ANTLR start "field_initial_value"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:309:1: field_initial_value returns [EncodedValue encodedValue] : ( ^( I_FIELD_INITIAL_VALUE literal ) |);
	public final EncodedValue field_initial_value() throws RecognitionException {
		EncodedValue encodedValue = null;


		ImmutableEncodedValue literal25 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:310:3: ( ^( I_FIELD_INITIAL_VALUE literal ) |)
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==I_FIELD_INITIAL_VALUE) ) {
				alt9=1;
			}
			else if ( (LA9_0==UP||LA9_0==I_ANNOTATIONS) ) {
				alt9=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:310:5: ^( I_FIELD_INITIAL_VALUE literal )
					{
					match(input,I_FIELD_INITIAL_VALUE,FOLLOW_I_FIELD_INITIAL_VALUE_in_field_initial_value515);
					match(input, Token.DOWN, null);
					pushFollow(FOLLOW_literal_in_field_initial_value517);
					literal25=literal();
					state._fsp--;

					match(input, Token.UP, null);

					encodedValue = literal25;
					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:311:16:
					{
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return encodedValue;
	}
	// $ANTLR end "field_initial_value"



	// $ANTLR start "literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:313:1: literal returns [ImmutableEncodedValue encodedValue] : ( integer_literal | long_literal | short_literal | byte_literal | float_literal | double_literal | char_literal | string_literal | bool_literal | NULL_LITERAL | type_descriptor | array_literal | subannotation | field_literal | method_literal | enum_literal | method_handle_literal | method_prototype );
	public final ImmutableEncodedValue literal() throws RecognitionException {
		ImmutableEncodedValue encodedValue = null;


		int integer_literal26 =0;
		long long_literal27 =0;
		short short_literal28 =0;
		byte byte_literal29 =0;
		float float_literal30 =0.0f;
		double double_literal31 =0.0;
		char char_literal32 =0;
		String string_literal33 =null;
		boolean bool_literal34 =false;
		String type_descriptor35 =null;
		List<EncodedValue> array_literal36 =null;
		TreeRuleReturnScope subannotation37 =null;
		ImmutableFieldReference field_literal38 =null;
		ImmutableMethodReference method_literal39 =null;
		ImmutableFieldReference enum_literal40 =null;
		ImmutableMethodHandleReference method_handle_literal41 =null;
		ImmutableMethodProtoReference method_prototype42 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:314:3: ( integer_literal | long_literal | short_literal | byte_literal | float_literal | double_literal | char_literal | string_literal | bool_literal | NULL_LITERAL | type_descriptor | array_literal | subannotation | field_literal | method_literal | enum_literal | method_handle_literal | method_prototype )
			int alt10=18;
			switch ( input.LA(1) ) {
			case INTEGER_LITERAL:
				{
				alt10=1;
				}
				break;
			case LONG_LITERAL:
				{
				alt10=2;
				}
				break;
			case SHORT_LITERAL:
				{
				alt10=3;
				}
				break;
			case BYTE_LITERAL:
				{
				alt10=4;
				}
				break;
			case FLOAT_LITERAL:
				{
				alt10=5;
				}
				break;
			case DOUBLE_LITERAL:
				{
				alt10=6;
				}
				break;
			case CHAR_LITERAL:
				{
				alt10=7;
				}
				break;
			case STRING_LITERAL:
				{
				alt10=8;
				}
				break;
			case BOOL_LITERAL:
				{
				alt10=9;
				}
				break;
			case NULL_LITERAL:
				{
				alt10=10;
				}
				break;
			case ARRAY_TYPE_PREFIX:
			case CLASS_DESCRIPTOR:
			case PRIMITIVE_TYPE:
			case VOID_TYPE:
				{
				alt10=11;
				}
				break;
			case I_ENCODED_ARRAY:
				{
				alt10=12;
				}
				break;
			case I_SUBANNOTATION:
				{
				alt10=13;
				}
				break;
			case I_ENCODED_FIELD:
				{
				alt10=14;
				}
				break;
			case I_ENCODED_METHOD:
				{
				alt10=15;
				}
				break;
			case I_ENCODED_ENUM:
				{
				alt10=16;
				}
				break;
			case I_ENCODED_METHOD_HANDLE:
				{
				alt10=17;
				}
				break;
			case I_METHOD_PROTOTYPE:
				{
				alt10=18;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}
			switch (alt10) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:314:5: integer_literal
					{
					pushFollow(FOLLOW_integer_literal_in_literal539);
					integer_literal26=integer_literal();
					state._fsp--;

					 encodedValue = new ImmutableIntEncodedValue(integer_literal26);
					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:315:5: long_literal
					{
					pushFollow(FOLLOW_long_literal_in_literal547);
					long_literal27=long_literal();
					state._fsp--;

					 encodedValue = new ImmutableLongEncodedValue(long_literal27);
					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:316:5: short_literal
					{
					pushFollow(FOLLOW_short_literal_in_literal555);
					short_literal28=short_literal();
					state._fsp--;

					 encodedValue = new ImmutableShortEncodedValue(short_literal28);
					}
					break;
				case 4 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:317:5: byte_literal
					{
					pushFollow(FOLLOW_byte_literal_in_literal563);
					byte_literal29=byte_literal();
					state._fsp--;

					 encodedValue = new ImmutableByteEncodedValue(byte_literal29);
					}
					break;
				case 5 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:318:5: float_literal
					{
					pushFollow(FOLLOW_float_literal_in_literal571);
					float_literal30=float_literal();
					state._fsp--;

					 encodedValue = new ImmutableFloatEncodedValue(float_literal30);
					}
					break;
				case 6 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:319:5: double_literal
					{
					pushFollow(FOLLOW_double_literal_in_literal579);
					double_literal31=double_literal();
					state._fsp--;

					 encodedValue = new ImmutableDoubleEncodedValue(double_literal31);
					}
					break;
				case 7 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:320:5: char_literal
					{
					pushFollow(FOLLOW_char_literal_in_literal587);
					char_literal32=char_literal();
					state._fsp--;

					 encodedValue = new ImmutableCharEncodedValue(char_literal32);
					}
					break;
				case 8 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:321:5: string_literal
					{
					pushFollow(FOLLOW_string_literal_in_literal595);
					string_literal33=string_literal();
					state._fsp--;

					 encodedValue = new ImmutableStringEncodedValue(string_literal33);
					}
					break;
				case 9 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:322:5: bool_literal
					{
					pushFollow(FOLLOW_bool_literal_in_literal603);
					bool_literal34=bool_literal();
					state._fsp--;

					 encodedValue = ImmutableBooleanEncodedValue.forBoolean(bool_literal34);
					}
					break;
				case 10 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:323:5: NULL_LITERAL
					{
					match(input,NULL_LITERAL,FOLLOW_NULL_LITERAL_in_literal611);
					 encodedValue = ImmutableNullEncodedValue.INSTANCE;
					}
					break;
				case 11 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:324:5: type_descriptor
					{
					pushFollow(FOLLOW_type_descriptor_in_literal619);
					type_descriptor35=type_descriptor();
					state._fsp--;

					 encodedValue = new ImmutableTypeEncodedValue(type_descriptor35);
					}
					break;
				case 12 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:325:5: array_literal
					{
					pushFollow(FOLLOW_array_literal_in_literal627);
					array_literal36=array_literal();
					state._fsp--;

					 encodedValue = new ImmutableArrayEncodedValue(array_literal36);
					}
					break;
				case 13 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:326:5: subannotation
					{
					pushFollow(FOLLOW_subannotation_in_literal635);
					subannotation37=subannotation();
					state._fsp--;

					 encodedValue = new ImmutableAnnotationEncodedValue((subannotation37!=null?((smaliTreeWalker.subannotation_return)subannotation37).annotationType:null), (subannotation37!=null?((smaliTreeWalker.subannotation_return)subannotation37).elements:null));
					}
					break;
				case 14 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:327:5: field_literal
					{
					pushFollow(FOLLOW_field_literal_in_literal643);
					field_literal38=field_literal();
					state._fsp--;

					 encodedValue = new ImmutableFieldEncodedValue(field_literal38);
					}
					break;
				case 15 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:328:5: method_literal
					{
					pushFollow(FOLLOW_method_literal_in_literal651);
					method_literal39=method_literal();
					state._fsp--;

					 encodedValue = new ImmutableMethodEncodedValue(method_literal39);
					}
					break;
				case 16 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:329:5: enum_literal
					{
					pushFollow(FOLLOW_enum_literal_in_literal659);
					enum_literal40=enum_literal();
					state._fsp--;

					 encodedValue = new ImmutableEnumEncodedValue(enum_literal40);
					}
					break;
				case 17 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:330:5: method_handle_literal
					{
					pushFollow(FOLLOW_method_handle_literal_in_literal667);
					method_handle_literal41=method_handle_literal();
					state._fsp--;

					 encodedValue = new ImmutableMethodHandleEncodedValue(method_handle_literal41);
					}
					break;
				case 18 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:331:5: method_prototype
					{
					pushFollow(FOLLOW_method_prototype_in_literal675);
					method_prototype42=method_prototype();
					state._fsp--;

					 encodedValue = new ImmutableMethodTypeEncodedValue(method_prototype42);
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return encodedValue;
	}
	// $ANTLR end "literal"



	// $ANTLR start "fixed_64bit_literal_number"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:334:1: fixed_64bit_literal_number returns [Number value] : ( integer_literal | long_literal | short_literal | byte_literal | float_literal | double_literal | char_literal | bool_literal );
	public final Number fixed_64bit_literal_number() throws RecognitionException {
		Number value = null;


		int integer_literal43 =0;
		long long_literal44 =0;
		short short_literal45 =0;
		byte byte_literal46 =0;
		float float_literal47 =0.0f;
		double double_literal48 =0.0;
		char char_literal49 =0;
		boolean bool_literal50 =false;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:335:3: ( integer_literal | long_literal | short_literal | byte_literal | float_literal | double_literal | char_literal | bool_literal )
			int alt11=8;
			switch ( input.LA(1) ) {
			case INTEGER_LITERAL:
				{
				alt11=1;
				}
				break;
			case LONG_LITERAL:
				{
				alt11=2;
				}
				break;
			case SHORT_LITERAL:
				{
				alt11=3;
				}
				break;
			case BYTE_LITERAL:
				{
				alt11=4;
				}
				break;
			case FLOAT_LITERAL:
				{
				alt11=5;
				}
				break;
			case DOUBLE_LITERAL:
				{
				alt11=6;
				}
				break;
			case CHAR_LITERAL:
				{
				alt11=7;
				}
				break;
			case BOOL_LITERAL:
				{
				alt11=8;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}
			switch (alt11) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:335:5: integer_literal
					{
					pushFollow(FOLLOW_integer_literal_in_fixed_64bit_literal_number691);
					integer_literal43=integer_literal();
					state._fsp--;

					 value = integer_literal43;
					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:336:5: long_literal
					{
					pushFollow(FOLLOW_long_literal_in_fixed_64bit_literal_number699);
					long_literal44=long_literal();
					state._fsp--;

					 value = long_literal44;
					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:337:5: short_literal
					{
					pushFollow(FOLLOW_short_literal_in_fixed_64bit_literal_number707);
					short_literal45=short_literal();
					state._fsp--;

					 value = short_literal45;
					}
					break;
				case 4 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:338:5: byte_literal
					{
					pushFollow(FOLLOW_byte_literal_in_fixed_64bit_literal_number715);
					byte_literal46=byte_literal();
					state._fsp--;

					 value = byte_literal46;
					}
					break;
				case 5 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:339:5: float_literal
					{
					pushFollow(FOLLOW_float_literal_in_fixed_64bit_literal_number723);
					float_literal47=float_literal();
					state._fsp--;

					 value = Float.floatToRawIntBits(float_literal47);
					}
					break;
				case 6 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:340:5: double_literal
					{
					pushFollow(FOLLOW_double_literal_in_fixed_64bit_literal_number731);
					double_literal48=double_literal();
					state._fsp--;

					 value = Double.doubleToRawLongBits(double_literal48);
					}
					break;
				case 7 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:341:5: char_literal
					{
					pushFollow(FOLLOW_char_literal_in_fixed_64bit_literal_number739);
					char_literal49=char_literal();
					state._fsp--;

					 value = (int)char_literal49;
					}
					break;
				case 8 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:342:5: bool_literal
					{
					pushFollow(FOLLOW_bool_literal_in_fixed_64bit_literal_number747);
					bool_literal50=bool_literal();
					state._fsp--;

					 value = bool_literal50?1:0;
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "fixed_64bit_literal_number"



	// $ANTLR start "fixed_64bit_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:344:1: fixed_64bit_literal returns [long value] : ( integer_literal | long_literal | short_literal | byte_literal | float_literal | double_literal | char_literal | bool_literal );
	public final long fixed_64bit_literal() throws RecognitionException {
		long value = 0;


		int integer_literal51 =0;
		long long_literal52 =0;
		short short_literal53 =0;
		byte byte_literal54 =0;
		float float_literal55 =0.0f;
		double double_literal56 =0.0;
		char char_literal57 =0;
		boolean bool_literal58 =false;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:345:3: ( integer_literal | long_literal | short_literal | byte_literal | float_literal | double_literal | char_literal | bool_literal )
			int alt12=8;
			switch ( input.LA(1) ) {
			case INTEGER_LITERAL:
				{
				alt12=1;
				}
				break;
			case LONG_LITERAL:
				{
				alt12=2;
				}
				break;
			case SHORT_LITERAL:
				{
				alt12=3;
				}
				break;
			case BYTE_LITERAL:
				{
				alt12=4;
				}
				break;
			case FLOAT_LITERAL:
				{
				alt12=5;
				}
				break;
			case DOUBLE_LITERAL:
				{
				alt12=6;
				}
				break;
			case CHAR_LITERAL:
				{
				alt12=7;
				}
				break;
			case BOOL_LITERAL:
				{
				alt12=8;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 12, 0, input);
				throw nvae;
			}
			switch (alt12) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:345:5: integer_literal
					{
					pushFollow(FOLLOW_integer_literal_in_fixed_64bit_literal762);
					integer_literal51=integer_literal();
					state._fsp--;

					 value = integer_literal51;
					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:346:5: long_literal
					{
					pushFollow(FOLLOW_long_literal_in_fixed_64bit_literal770);
					long_literal52=long_literal();
					state._fsp--;

					 value = long_literal52;
					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:347:5: short_literal
					{
					pushFollow(FOLLOW_short_literal_in_fixed_64bit_literal778);
					short_literal53=short_literal();
					state._fsp--;

					 value = short_literal53;
					}
					break;
				case 4 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:348:5: byte_literal
					{
					pushFollow(FOLLOW_byte_literal_in_fixed_64bit_literal786);
					byte_literal54=byte_literal();
					state._fsp--;

					 value = byte_literal54;
					}
					break;
				case 5 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:349:5: float_literal
					{
					pushFollow(FOLLOW_float_literal_in_fixed_64bit_literal794);
					float_literal55=float_literal();
					state._fsp--;

					 value = Float.floatToRawIntBits(float_literal55);
					}
					break;
				case 6 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:350:5: double_literal
					{
					pushFollow(FOLLOW_double_literal_in_fixed_64bit_literal802);
					double_literal56=double_literal();
					state._fsp--;

					 value = Double.doubleToRawLongBits(double_literal56);
					}
					break;
				case 7 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:351:5: char_literal
					{
					pushFollow(FOLLOW_char_literal_in_fixed_64bit_literal810);
					char_literal57=char_literal();
					state._fsp--;

					 value = char_literal57;
					}
					break;
				case 8 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:352:5: bool_literal
					{
					pushFollow(FOLLOW_bool_literal_in_fixed_64bit_literal818);
					bool_literal58=bool_literal();
					state._fsp--;

					 value = bool_literal58?1:0;
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "fixed_64bit_literal"



	// $ANTLR start "fixed_32bit_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:356:1: fixed_32bit_literal returns [int value] : ( integer_literal | long_literal | short_literal | byte_literal | float_literal | char_literal | bool_literal );
	public final int fixed_32bit_literal() throws RecognitionException {
		int value = 0;


		int integer_literal59 =0;
		long long_literal60 =0;
		short short_literal61 =0;
		byte byte_literal62 =0;
		float float_literal63 =0.0f;
		char char_literal64 =0;
		boolean bool_literal65 =false;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:357:3: ( integer_literal | long_literal | short_literal | byte_literal | float_literal | char_literal | bool_literal )
			int alt13=7;
			switch ( input.LA(1) ) {
			case INTEGER_LITERAL:
				{
				alt13=1;
				}
				break;
			case LONG_LITERAL:
				{
				alt13=2;
				}
				break;
			case SHORT_LITERAL:
				{
				alt13=3;
				}
				break;
			case BYTE_LITERAL:
				{
				alt13=4;
				}
				break;
			case FLOAT_LITERAL:
				{
				alt13=5;
				}
				break;
			case CHAR_LITERAL:
				{
				alt13=6;
				}
				break;
			case BOOL_LITERAL:
				{
				alt13=7;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 13, 0, input);
				throw nvae;
			}
			switch (alt13) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:357:5: integer_literal
					{
					pushFollow(FOLLOW_integer_literal_in_fixed_32bit_literal835);
					integer_literal59=integer_literal();
					state._fsp--;

					 value = integer_literal59;
					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:358:5: long_literal
					{
					pushFollow(FOLLOW_long_literal_in_fixed_32bit_literal843);
					long_literal60=long_literal();
					state._fsp--;

					 LiteralTools.checkInt(long_literal60); value = (int)long_literal60;
					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:359:5: short_literal
					{
					pushFollow(FOLLOW_short_literal_in_fixed_32bit_literal851);
					short_literal61=short_literal();
					state._fsp--;

					 value = short_literal61;
					}
					break;
				case 4 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:360:5: byte_literal
					{
					pushFollow(FOLLOW_byte_literal_in_fixed_32bit_literal859);
					byte_literal62=byte_literal();
					state._fsp--;

					 value = byte_literal62;
					}
					break;
				case 5 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:361:5: float_literal
					{
					pushFollow(FOLLOW_float_literal_in_fixed_32bit_literal867);
					float_literal63=float_literal();
					state._fsp--;

					 value = Float.floatToRawIntBits(float_literal63);
					}
					break;
				case 6 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:362:5: char_literal
					{
					pushFollow(FOLLOW_char_literal_in_fixed_32bit_literal875);
					char_literal64=char_literal();
					state._fsp--;

					 value = char_literal64;
					}
					break;
				case 7 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:363:5: bool_literal
					{
					pushFollow(FOLLOW_bool_literal_in_fixed_32bit_literal883);
					bool_literal65=bool_literal();
					state._fsp--;

					 value = bool_literal65?1:0;
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "fixed_32bit_literal"



	// $ANTLR start "array_elements"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:365:1: array_elements returns [List<Number> elements] : ^( I_ARRAY_ELEMENTS ( fixed_64bit_literal_number )* ) ;
	public final List<Number> array_elements() throws RecognitionException {
		List<Number> elements = null;


		Number fixed_64bit_literal_number66 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:366:3: ( ^( I_ARRAY_ELEMENTS ( fixed_64bit_literal_number )* ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:366:5: ^( I_ARRAY_ELEMENTS ( fixed_64bit_literal_number )* )
			{
			elements = Lists.newArrayList();
			match(input,I_ARRAY_ELEMENTS,FOLLOW_I_ARRAY_ELEMENTS_in_array_elements905);
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:368:7: ( fixed_64bit_literal_number )*
				loop14:
				while (true) {
					int alt14=2;
					int LA14_0 = input.LA(1);
					if ( ((LA14_0 >= BOOL_LITERAL && LA14_0 <= BYTE_LITERAL)||LA14_0==CHAR_LITERAL||LA14_0==DOUBLE_LITERAL||LA14_0==FLOAT_LITERAL||LA14_0==INTEGER_LITERAL||LA14_0==LONG_LITERAL||LA14_0==SHORT_LITERAL) ) {
						alt14=1;
					}

					switch (alt14) {
					case 1 :
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:368:8: fixed_64bit_literal_number
						{
						pushFollow(FOLLOW_fixed_64bit_literal_number_in_array_elements914);
						fixed_64bit_literal_number66=fixed_64bit_literal_number();
						state._fsp--;


						        elements.add(fixed_64bit_literal_number66);
						
						}
						break;

					default :
						break loop14;
					}
				}

				match(input, Token.UP, null);
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return elements;
	}
	// $ANTLR end "array_elements"



	// $ANTLR start "packed_switch_elements"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:373:1: packed_switch_elements returns [List<Label> elements] : ^( I_PACKED_SWITCH_ELEMENTS ( label_ref )* ) ;
	public final List<Label> packed_switch_elements() throws RecognitionException {
		List<Label> elements = null;


		Label label_ref67 =null;

		elements = Lists.newArrayList();
		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:375:3: ( ^( I_PACKED_SWITCH_ELEMENTS ( label_ref )* ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:376:5: ^( I_PACKED_SWITCH_ELEMENTS ( label_ref )* )
			{
			match(input,I_PACKED_SWITCH_ELEMENTS,FOLLOW_I_PACKED_SWITCH_ELEMENTS_in_packed_switch_elements950);
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:377:7: ( label_ref )*
				loop15:
				while (true) {
					int alt15=2;
					int LA15_0 = input.LA(1);
					if ( (LA15_0==SIMPLE_NAME) ) {
						alt15=1;
					}

					switch (alt15) {
					case 1 :
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:377:8: label_ref
						{
						pushFollow(FOLLOW_label_ref_in_packed_switch_elements959);
						label_ref67=label_ref();
						state._fsp--;

						 elements.add(label_ref67);
						}
						break;

					default :
						break loop15;
					}
				}

				match(input, Token.UP, null);
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return elements;
	}
	// $ANTLR end "packed_switch_elements"



	// $ANTLR start "sparse_switch_elements"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:380:1: sparse_switch_elements returns [List<SwitchLabelElement> elements] : ^( I_SPARSE_SWITCH_ELEMENTS ( fixed_32bit_literal label_ref )* ) ;
	public final List<SwitchLabelElement> sparse_switch_elements() throws RecognitionException {
		List<SwitchLabelElement> elements = null;


		int fixed_32bit_literal68 =0;
		Label label_ref69 =null;

		elements = Lists.newArrayList();
		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:382:3: ( ^( I_SPARSE_SWITCH_ELEMENTS ( fixed_32bit_literal label_ref )* ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:383:5: ^( I_SPARSE_SWITCH_ELEMENTS ( fixed_32bit_literal label_ref )* )
			{
			match(input,I_SPARSE_SWITCH_ELEMENTS,FOLLOW_I_SPARSE_SWITCH_ELEMENTS_in_sparse_switch_elements994);
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:384:8: ( fixed_32bit_literal label_ref )*
				loop16:
				while (true) {
					int alt16=2;
					int LA16_0 = input.LA(1);
					if ( ((LA16_0 >= BOOL_LITERAL && LA16_0 <= BYTE_LITERAL)||LA16_0==CHAR_LITERAL||LA16_0==FLOAT_LITERAL||LA16_0==INTEGER_LITERAL||LA16_0==LONG_LITERAL||LA16_0==SHORT_LITERAL) ) {
						alt16=1;
					}

					switch (alt16) {
					case 1 :
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:384:9: fixed_32bit_literal label_ref
						{
						pushFollow(FOLLOW_fixed_32bit_literal_in_sparse_switch_elements1004);
						fixed_32bit_literal68=fixed_32bit_literal();
						state._fsp--;

						pushFollow(FOLLOW_label_ref_in_sparse_switch_elements1006);
						label_ref69=label_ref();
						state._fsp--;


						         elements.add(new SwitchLabelElement(fixed_32bit_literal68, label_ref69));
						
						}
						break;

					default :
						break loop16;
					}
				}

				match(input, Token.UP, null);
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return elements;
	}
	// $ANTLR end "sparse_switch_elements"


	protected static class method_scope {
		boolean isStatic;
		int totalMethodRegisters;
		int methodParameterRegisters;
		MethodImplementationBuilder methodBuilder;
	}
	protected Stack<method_scope> method_stack = new Stack<method_scope>();


	// $ANTLR start "method"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:390:1: method returns [BuilderMethod ret] : ^( I_METHOD method_name_and_prototype access_or_restriction_list ( ( registers_directive ) |) ordered_method_items catches parameters[$method_name_and_prototype.parameters] annotations ) ;
	public final BuilderMethod method() throws RecognitionException {
		method_stack.push(new method_scope());
		BuilderMethod ret = null;


		CommonTree I_METHOD74=null;
		TreeRuleReturnScope access_or_restriction_list70 =null;
		TreeRuleReturnScope method_name_and_prototype71 =null;
		TreeRuleReturnScope registers_directive72 =null;
		List<BuilderTryBlock> catches73 =null;
		Set<Annotation> annotations75 =null;


		    method_stack.peek().totalMethodRegisters = 0;
		    method_stack.peek().methodParameterRegisters = 0;
		    int accessFlags = 0;
		    method_stack.peek().isStatic = false;
		    Set<HiddenApiRestriction> hiddenApiRestrictions = null;
		
		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:406:3: ( ^( I_METHOD method_name_and_prototype access_or_restriction_list ( ( registers_directive ) |) ordered_method_items catches parameters[$method_name_and_prototype.parameters] annotations ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:407:5: ^( I_METHOD method_name_and_prototype access_or_restriction_list ( ( registers_directive ) |) ordered_method_items catches parameters[$method_name_and_prototype.parameters] annotations )
			{
			I_METHOD74=(CommonTree)match(input,I_METHOD,FOLLOW_I_METHOD_in_method1058);
			match(input, Token.DOWN, null);
			pushFollow(FOLLOW_method_name_and_prototype_in_method1066);
			method_name_and_prototype71=method_name_and_prototype();
			state._fsp--;

			pushFollow(FOLLOW_access_or_restriction_list_in_method1074);
			access_or_restriction_list70=access_or_restriction_list();
			state._fsp--;


			        accessFlags = (access_or_restriction_list70!=null?((smaliTreeWalker.access_or_restriction_list_return)access_or_restriction_list70).value:0);
			        hiddenApiRestrictions = (access_or_restriction_list70!=null?((smaliTreeWalker.access_or_restriction_list_return)access_or_restriction_list70).hiddenApiRestrictions:null);
			        method_stack.peek().isStatic = AccessFlags.STATIC.isSet(accessFlags);
			        method_stack.peek().methodParameterRegisters =
			                MethodUtil.getParameterRegisterCount((method_name_and_prototype71!=null?((smaliTreeWalker.method_name_and_prototype_return)method_name_and_prototype71).parameters:null), method_stack.peek().isStatic);
			
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:417:7: ( ( registers_directive ) |)
			int alt17=2;
			int LA17_0 = input.LA(1);
			if ( (LA17_0==I_LOCALS||LA17_0==I_REGISTERS) ) {
				alt17=1;
			}
			else if ( (LA17_0==I_ORDERED_METHOD_ITEMS) ) {
				alt17=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 17, 0, input);
				throw nvae;
			}

			switch (alt17) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:418:9: ( registers_directive )
					{
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:418:9: ( registers_directive )
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:418:10: registers_directive
					{
					pushFollow(FOLLOW_registers_directive_in_method1101);
					registers_directive72=registers_directive();
					state._fsp--;


					          if ((registers_directive72!=null?((smaliTreeWalker.registers_directive_return)registers_directive72).isLocalsDirective:false)) {
					            method_stack.peek().totalMethodRegisters = (registers_directive72!=null?((smaliTreeWalker.registers_directive_return)registers_directive72).registers:0) + method_stack.peek().methodParameterRegisters;
					          } else {
					            method_stack.peek().totalMethodRegisters = (registers_directive72!=null?((smaliTreeWalker.registers_directive_return)registers_directive72).registers:0);
					          }

					          method_stack.peek().methodBuilder = new MethodImplementationBuilder(method_stack.peek().totalMethodRegisters);

					
					}

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:431:9:
					{

					          method_stack.peek().methodBuilder = new MethodImplementationBuilder(0);
					
					}
					break;

			}

			pushFollow(FOLLOW_ordered_method_items_in_method1158);
			ordered_method_items();
			state._fsp--;

			pushFollow(FOLLOW_catches_in_method1166);
			catches73=catches();
			state._fsp--;

			pushFollow(FOLLOW_parameters_in_method1174);
			parameters((method_name_and_prototype71!=null?((smaliTreeWalker.method_name_and_prototype_return)method_name_and_prototype71).parameters:null));
			state._fsp--;

			pushFollow(FOLLOW_annotations_in_method1183);
			annotations75=annotations();
			state._fsp--;

			match(input, Token.UP, null);


			    MethodImplementation methodImplementation = null;
			    List<BuilderTryBlock> tryBlocks = catches73;

			    boolean isAbstract = false;
			    boolean isNative = false;

			    if ((accessFlags & AccessFlags.ABSTRACT.getValue()) != 0) {
			      isAbstract = true;
			    } else if ((accessFlags & AccessFlags.NATIVE.getValue()) != 0) {
			      isNative = true;
			    }

			    methodImplementation = method_stack.peek().methodBuilder.getMethodImplementation();

			    if (Iterables.isEmpty(methodImplementation.getInstructions())) {
			      if (!isAbstract && !isNative) {
			        throw new SemanticException(input, I_METHOD74, "A non-abstract/non-native method must have at least 1 instruction");
			      }

			      String methodType;
			      if (isAbstract) {
			        methodType = "an abstract";
			      } else {
			        methodType = "a native";
			      }

			      if ((registers_directive72!=null?((CommonTree)registers_directive72.start):null) != null) {
			        if ((registers_directive72!=null?((smaliTreeWalker.registers_directive_return)registers_directive72).isLocalsDirective:false)) {
			          throw new SemanticException(input, (registers_directive72!=null?((CommonTree)registers_directive72.start):null), "A .locals directive is not valid in %s method", methodType);
			        } else {
			          throw new SemanticException(input, (registers_directive72!=null?((CommonTree)registers_directive72.start):null), "A .registers directive is not valid in %s method", methodType);
			        }
			      }

			      if (methodImplementation.getTryBlocks().size() > 0) {
			        throw new SemanticException(input, I_METHOD74, "try/catch blocks cannot be present in %s method", methodType);
			      }

			      if (!Iterables.isEmpty(methodImplementation.getDebugItems())) {
			        throw new SemanticException(input, I_METHOD74, "debug directives cannot be present in %s method", methodType);
			      }

			      methodImplementation = null;
			    } else {
			      if (isAbstract) {
			        throw new SemanticException(input, I_METHOD74, "An abstract method cannot have any instructions");
			      }
			      if (isNative) {
			        throw new SemanticException(input, I_METHOD74, "A native method cannot have any instructions");
			      }

			      if ((registers_directive72!=null?((CommonTree)registers_directive72.start):null) == null) {
			        throw new SemanticException(input, I_METHOD74, "A .registers or .locals directive must be present for a non-abstract/non-final method");
			      }

			      if (method_stack.peek().totalMethodRegisters < method_stack.peek().methodParameterRegisters) {
			        throw new SemanticException(input, (registers_directive72!=null?((CommonTree)registers_directive72.start):null), "This method requires at least " +
			                Integer.toString(method_stack.peek().methodParameterRegisters) +
			                " registers, for the method parameters");
			      }
			    }

			    ret = dexBuilder.internMethod(
			            classType,
			            (method_name_and_prototype71!=null?((smaliTreeWalker.method_name_and_prototype_return)method_name_and_prototype71).name:null),
			            (method_name_and_prototype71!=null?((smaliTreeWalker.method_name_and_prototype_return)method_name_and_prototype71).parameters:null),
			            (method_name_and_prototype71!=null?((smaliTreeWalker.method_name_and_prototype_return)method_name_and_prototype71).returnType:null),
			            accessFlags,
			            annotations75,
			            hiddenApiRestrictions,
			            methodImplementation);
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
			method_stack.pop();
		}
		return ret;
	}
	// $ANTLR end "method"



	// $ANTLR start "method_prototype"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:514:1: method_prototype returns [ImmutableMethodProtoReference proto] : ^( I_METHOD_PROTOTYPE ^( I_METHOD_RETURN_TYPE type_descriptor ) method_type_list ) ;
	public final ImmutableMethodProtoReference method_prototype() throws RecognitionException {
		ImmutableMethodProtoReference proto = null;


		String type_descriptor76 =null;
		List<String> method_type_list77 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:515:3: ( ^( I_METHOD_PROTOTYPE ^( I_METHOD_RETURN_TYPE type_descriptor ) method_type_list ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:515:5: ^( I_METHOD_PROTOTYPE ^( I_METHOD_RETURN_TYPE type_descriptor ) method_type_list )
			{
			match(input,I_METHOD_PROTOTYPE,FOLLOW_I_METHOD_PROTOTYPE_in_method_prototype1207);
			match(input, Token.DOWN, null);
			match(input,I_METHOD_RETURN_TYPE,FOLLOW_I_METHOD_RETURN_TYPE_in_method_prototype1210);
			match(input, Token.DOWN, null);
			pushFollow(FOLLOW_type_descriptor_in_method_prototype1212);
			type_descriptor76=type_descriptor();
			state._fsp--;

			match(input, Token.UP, null);

			pushFollow(FOLLOW_method_type_list_in_method_prototype1215);
			method_type_list77=method_type_list();
			state._fsp--;

			match(input, Token.UP, null);


			    String returnType = type_descriptor76;
			    List<String> parameters = method_type_list77;
			    proto = new ImmutableMethodProtoReference(parameters, returnType);
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return proto;
	}
	// $ANTLR end "method_prototype"


	public static class method_name_and_prototype_return extends TreeRuleReturnScope {
		public String name;
		public List<SmaliMethodParameter> parameters;
		public String returnType;
	};


	// $ANTLR start "method_name_and_prototype"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:522:1: method_name_and_prototype returns [String name, List<SmaliMethodParameter> parameters, String returnType] : SIMPLE_NAME method_prototype ;
	public final smaliTreeWalker.method_name_and_prototype_return method_name_and_prototype() throws RecognitionException {
		smaliTreeWalker.method_name_and_prototype_return retval = new smaliTreeWalker.method_name_and_prototype_return();
		retval.start = input.LT(1);

		CommonTree SIMPLE_NAME78=null;
		ImmutableMethodProtoReference method_prototype79 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:523:3: ( SIMPLE_NAME method_prototype )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:523:5: SIMPLE_NAME method_prototype
			{
			SIMPLE_NAME78=(CommonTree)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_method_name_and_prototype1233);
			pushFollow(FOLLOW_method_prototype_in_method_name_and_prototype1235);
			method_prototype79=method_prototype();
			state._fsp--;


			    retval.name = (SIMPLE_NAME78!=null?SIMPLE_NAME78.getText():null);
			    retval.parameters = Lists.newArrayList();

			    int paramRegister = 0;
			    for (CharSequence type: method_prototype79.getParameterTypes()) {
			        retval.parameters.add(new SmaliMethodParameter(paramRegister++, type.toString()));
			        char c = type.charAt(0);
			        if (c == 'D' || c == 'J') {
			            paramRegister++;
			        }
			    }
			    retval.returnType = method_prototype79.getReturnType();
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "method_name_and_prototype"



	// $ANTLR start "method_type_list"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:539:1: method_type_list returns [List<String> types] : ( nonvoid_type_descriptor )* ;
	public final List<String> method_type_list() throws RecognitionException {
		List<String> types = null;


		TreeRuleReturnScope nonvoid_type_descriptor80 =null;


		    types = Lists.newArrayList();
		
		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:544:3: ( ( nonvoid_type_descriptor )* )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:544:5: ( nonvoid_type_descriptor )*
			{
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:544:5: ( nonvoid_type_descriptor )*
			loop18:
			while (true) {
				int alt18=2;
				int LA18_0 = input.LA(1);
				if ( (LA18_0==ARRAY_TYPE_PREFIX||LA18_0==CLASS_DESCRIPTOR||LA18_0==PRIMITIVE_TYPE) ) {
					alt18=1;
				}

				switch (alt18) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:545:7: nonvoid_type_descriptor
					{
					pushFollow(FOLLOW_nonvoid_type_descriptor_in_method_type_list1269);
					nonvoid_type_descriptor80=nonvoid_type_descriptor();
					state._fsp--;


					        types.add((nonvoid_type_descriptor80!=null?((smaliTreeWalker.nonvoid_type_descriptor_return)nonvoid_type_descriptor80).type:null));
					
					}
					break;

				default :
					break loop18;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return types;
	}
	// $ANTLR end "method_type_list"



	// $ANTLR start "call_site_reference"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:551:1: call_site_reference returns [ImmutableCallSiteReference callSiteReference] : ^( I_CALL_SITE_REFERENCE call_site_name= SIMPLE_NAME method_name= string_literal method_prototype call_site_extra_arguments method_reference ) ;
	public final ImmutableCallSiteReference call_site_reference() throws RecognitionException {
		ImmutableCallSiteReference callSiteReference = null;


		CommonTree call_site_name=null;
		String method_name =null;
		ImmutableMethodReference method_reference81 =null;
		ImmutableMethodProtoReference method_prototype82 =null;
		List<ImmutableEncodedValue> call_site_extra_arguments83 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:552:3: ( ^( I_CALL_SITE_REFERENCE call_site_name= SIMPLE_NAME method_name= string_literal method_prototype call_site_extra_arguments method_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:553:3: ^( I_CALL_SITE_REFERENCE call_site_name= SIMPLE_NAME method_name= string_literal method_prototype call_site_extra_arguments method_reference )
			{
			match(input,I_CALL_SITE_REFERENCE,FOLLOW_I_CALL_SITE_REFERENCE_in_call_site_reference1300);
			match(input, Token.DOWN, null);
			call_site_name=(CommonTree)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_call_site_reference1304);
			pushFollow(FOLLOW_string_literal_in_call_site_reference1308);
			method_name=string_literal();
			state._fsp--;

			pushFollow(FOLLOW_method_prototype_in_call_site_reference1310);
			method_prototype82=method_prototype();
			state._fsp--;

			pushFollow(FOLLOW_call_site_extra_arguments_in_call_site_reference1320);
			call_site_extra_arguments83=call_site_extra_arguments();
			state._fsp--;

			pushFollow(FOLLOW_method_reference_in_call_site_reference1322);
			method_reference81=method_reference();
			state._fsp--;

			match(input, Token.UP, null);


			        String callSiteName = (call_site_name!=null?call_site_name.getText():null);
			        ImmutableMethodHandleReference methodHandleReference =
			            new ImmutableMethodHandleReference(MethodHandleType.INVOKE_STATIC,
			                method_reference81);
			        callSiteReference = new ImmutableCallSiteReference(
			            callSiteName, methodHandleReference, method_name, method_prototype82,
			            call_site_extra_arguments83);
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return callSiteReference;
	}
	// $ANTLR end "call_site_reference"


	public static class method_handle_type_return extends TreeRuleReturnScope {
		public int methodHandleType;
	};


	// $ANTLR start "method_handle_type"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:565:1: method_handle_type returns [int methodHandleType] : ( METHOD_HANDLE_TYPE_FIELD | METHOD_HANDLE_TYPE_METHOD | INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE ) ;
	public final smaliTreeWalker.method_handle_type_return method_handle_type() throws RecognitionException {
		smaliTreeWalker.method_handle_type_return retval = new smaliTreeWalker.method_handle_type_return();
		retval.start = input.LT(1);

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:566:3: ( ( METHOD_HANDLE_TYPE_FIELD | METHOD_HANDLE_TYPE_METHOD | INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:566:5: ( METHOD_HANDLE_TYPE_FIELD | METHOD_HANDLE_TYPE_METHOD | INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE )
			{
			if ( input.LA(1)==INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE||(input.LA(1) >= METHOD_HANDLE_TYPE_FIELD && input.LA(1) <= METHOD_HANDLE_TYPE_METHOD) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}

			    retval.methodHandleType = MethodHandleType.getMethodHandleType(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(retval.start),input.getTreeAdaptor().getTokenStopIndex(retval.start)));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "method_handle_type"



	// $ANTLR start "method_handle_reference"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:570:1: method_handle_reference returns [ImmutableMethodHandleReference methodHandle] : method_handle_type ( field_reference | method_reference ) ;
	public final ImmutableMethodHandleReference method_handle_reference() throws RecognitionException {
		ImmutableMethodHandleReference methodHandle = null;


		TreeRuleReturnScope field_reference84 =null;
		ImmutableMethodReference method_reference85 =null;
		TreeRuleReturnScope method_handle_type86 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:571:3: ( method_handle_type ( field_reference | method_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:571:5: method_handle_type ( field_reference | method_reference )
			{
			pushFollow(FOLLOW_method_handle_type_in_method_handle_reference1367);
			method_handle_type86=method_handle_type();
			state._fsp--;

			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:571:24: ( field_reference | method_reference )
			int alt19=2;
			switch ( input.LA(1) ) {
			case CLASS_DESCRIPTOR:
				{
				int LA19_1 = input.LA(2);
				if ( (LA19_1==SIMPLE_NAME) ) {
					int LA19_3 = input.LA(3);
					if ( (LA19_3==ARRAY_TYPE_PREFIX||LA19_3==CLASS_DESCRIPTOR||LA19_3==PRIMITIVE_TYPE) ) {
						alt19=1;
					}
					else if ( (LA19_3==I_METHOD_PROTOTYPE) ) {
						alt19=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 19, 3, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 19, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case ARRAY_TYPE_PREFIX:
				{
				int LA19_2 = input.LA(2);
				if ( (LA19_2==PRIMITIVE_TYPE) ) {
					int LA19_4 = input.LA(3);
					if ( (LA19_4==SIMPLE_NAME) ) {
						int LA19_3 = input.LA(4);
						if ( (LA19_3==ARRAY_TYPE_PREFIX||LA19_3==CLASS_DESCRIPTOR||LA19_3==PRIMITIVE_TYPE) ) {
							alt19=1;
						}
						else if ( (LA19_3==I_METHOD_PROTOTYPE) ) {
							alt19=2;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 19, 3, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 19, 4, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}
				else if ( (LA19_2==CLASS_DESCRIPTOR) ) {
					int LA19_5 = input.LA(3);
					if ( (LA19_5==SIMPLE_NAME) ) {
						int LA19_3 = input.LA(4);
						if ( (LA19_3==ARRAY_TYPE_PREFIX||LA19_3==CLASS_DESCRIPTOR||LA19_3==PRIMITIVE_TYPE) ) {
							alt19=1;
						}
						else if ( (LA19_3==I_METHOD_PROTOTYPE) ) {
							alt19=2;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 19, 3, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 19, 5, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 19, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case SIMPLE_NAME:
				{
				int LA19_3 = input.LA(2);
				if ( (LA19_3==ARRAY_TYPE_PREFIX||LA19_3==CLASS_DESCRIPTOR||LA19_3==PRIMITIVE_TYPE) ) {
					alt19=1;
				}
				else if ( (LA19_3==I_METHOD_PROTOTYPE) ) {
					alt19=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 19, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 19, 0, input);
				throw nvae;
			}
			switch (alt19) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:571:25: field_reference
					{
					pushFollow(FOLLOW_field_reference_in_method_handle_reference1370);
					field_reference84=field_reference();
					state._fsp--;

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:571:43: method_reference
					{
					pushFollow(FOLLOW_method_reference_in_method_handle_reference1374);
					method_reference85=method_reference();
					state._fsp--;

					}
					break;

			}


			    ImmutableReference reference;
			    if ((field_reference84!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(field_reference84.start),input.getTreeAdaptor().getTokenStopIndex(field_reference84.start))):null) != null) {
			        reference = (field_reference84!=null?((smaliTreeWalker.field_reference_return)field_reference84).fieldReference:null);
			    } else {
			        reference = method_reference85;
			    }
			    methodHandle = new ImmutableMethodHandleReference((method_handle_type86!=null?((smaliTreeWalker.method_handle_type_return)method_handle_type86).methodHandleType:0), reference);
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return methodHandle;
	}
	// $ANTLR end "method_handle_reference"



	// $ANTLR start "method_handle_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:581:1: method_handle_literal returns [ImmutableMethodHandleReference value] : ^( I_ENCODED_METHOD_HANDLE method_handle_reference ) ;
	public final ImmutableMethodHandleReference method_handle_literal() throws RecognitionException {
		ImmutableMethodHandleReference value = null;


		ImmutableMethodHandleReference method_handle_reference87 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:582:3: ( ^( I_ENCODED_METHOD_HANDLE method_handle_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:582:5: ^( I_ENCODED_METHOD_HANDLE method_handle_reference )
			{
			match(input,I_ENCODED_METHOD_HANDLE,FOLLOW_I_ENCODED_METHOD_HANDLE_in_method_handle_literal1391);
			match(input, Token.DOWN, null);
			pushFollow(FOLLOW_method_handle_reference_in_method_handle_literal1393);
			method_handle_reference87=method_handle_reference();
			state._fsp--;

			match(input, Token.UP, null);


			    value = method_handle_reference87;
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "method_handle_literal"



	// $ANTLR start "method_reference"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:586:1: method_reference returns [ImmutableMethodReference methodReference] : ( reference_type_descriptor )? SIMPLE_NAME method_prototype ;
	public final ImmutableMethodReference method_reference() throws RecognitionException {
		ImmutableMethodReference methodReference = null;


		CommonTree SIMPLE_NAME89=null;
		TreeRuleReturnScope reference_type_descriptor88 =null;
		ImmutableMethodProtoReference method_prototype90 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:587:3: ( ( reference_type_descriptor )? SIMPLE_NAME method_prototype )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:587:5: ( reference_type_descriptor )? SIMPLE_NAME method_prototype
			{
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:587:5: ( reference_type_descriptor )?
			int alt20=2;
			int LA20_0 = input.LA(1);
			if ( (LA20_0==ARRAY_TYPE_PREFIX||LA20_0==CLASS_DESCRIPTOR) ) {
				alt20=1;
			}
			switch (alt20) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:587:5: reference_type_descriptor
					{
					pushFollow(FOLLOW_reference_type_descriptor_in_method_reference1409);
					reference_type_descriptor88=reference_type_descriptor();
					state._fsp--;

					}
					break;

			}

			SIMPLE_NAME89=(CommonTree)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_method_reference1412);
			pushFollow(FOLLOW_method_prototype_in_method_reference1414);
			method_prototype90=method_prototype();
			state._fsp--;


			    String type;
			    if ((reference_type_descriptor88!=null?((smaliTreeWalker.reference_type_descriptor_return)reference_type_descriptor88).type:null) == null) {
			        type = classType;
			    } else {
			        type = (reference_type_descriptor88!=null?((smaliTreeWalker.reference_type_descriptor_return)reference_type_descriptor88).type:null);
			    }
			    methodReference = new ImmutableMethodReference(type, (SIMPLE_NAME89!=null?SIMPLE_NAME89.getText():null),
			             method_prototype90.getParameterTypes(), method_prototype90.getReturnType());
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return methodReference;
	}
	// $ANTLR end "method_reference"


	public static class field_reference_return extends TreeRuleReturnScope {
		public ImmutableFieldReference fieldReference;
	};


	// $ANTLR start "field_reference"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:599:1: field_reference returns [ImmutableFieldReference fieldReference] : ( reference_type_descriptor )? SIMPLE_NAME nonvoid_type_descriptor ;
	public final smaliTreeWalker.field_reference_return field_reference() throws RecognitionException {
		smaliTreeWalker.field_reference_return retval = new smaliTreeWalker.field_reference_return();
		retval.start = input.LT(1);

		CommonTree SIMPLE_NAME92=null;
		TreeRuleReturnScope reference_type_descriptor91 =null;
		TreeRuleReturnScope nonvoid_type_descriptor93 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:600:3: ( ( reference_type_descriptor )? SIMPLE_NAME nonvoid_type_descriptor )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:600:5: ( reference_type_descriptor )? SIMPLE_NAME nonvoid_type_descriptor
			{
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:600:5: ( reference_type_descriptor )?
			int alt21=2;
			int LA21_0 = input.LA(1);
			if ( (LA21_0==ARRAY_TYPE_PREFIX||LA21_0==CLASS_DESCRIPTOR) ) {
				alt21=1;
			}
			switch (alt21) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:600:5: reference_type_descriptor
					{
					pushFollow(FOLLOW_reference_type_descriptor_in_field_reference1431);
					reference_type_descriptor91=reference_type_descriptor();
					state._fsp--;

					}
					break;

			}

			SIMPLE_NAME92=(CommonTree)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_field_reference1434);
			pushFollow(FOLLOW_nonvoid_type_descriptor_in_field_reference1436);
			nonvoid_type_descriptor93=nonvoid_type_descriptor();
			state._fsp--;


			    String type;
			    if ((reference_type_descriptor91!=null?((smaliTreeWalker.reference_type_descriptor_return)reference_type_descriptor91).type:null) == null) {
			        type = classType;
			    } else {
			        type = (reference_type_descriptor91!=null?((smaliTreeWalker.reference_type_descriptor_return)reference_type_descriptor91).type:null);
			    }
			    retval.fieldReference = new ImmutableFieldReference(type, (SIMPLE_NAME92!=null?SIMPLE_NAME92.getText():null),
			            (nonvoid_type_descriptor93!=null?((smaliTreeWalker.nonvoid_type_descriptor_return)nonvoid_type_descriptor93).type:null));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "field_reference"


	public static class registers_directive_return extends TreeRuleReturnScope {
		public boolean isLocalsDirective;
		public int registers;
	};


	// $ANTLR start "registers_directive"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:612:1: registers_directive returns [boolean isLocalsDirective, int registers] : ^( ( I_REGISTERS | I_LOCALS ) short_integral_literal ) ;
	public final smaliTreeWalker.registers_directive_return registers_directive() throws RecognitionException {
		smaliTreeWalker.registers_directive_return retval = new smaliTreeWalker.registers_directive_return();
		retval.start = input.LT(1);

		short short_integral_literal94 =0;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:613:3: ( ^( ( I_REGISTERS | I_LOCALS ) short_integral_literal ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:613:5: ^( ( I_REGISTERS | I_LOCALS ) short_integral_literal )
			{
			retval.registers = 0;
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:614:7: ( I_REGISTERS | I_LOCALS )
			int alt22=2;
			int LA22_0 = input.LA(1);
			if ( (LA22_0==I_REGISTERS) ) {
				alt22=1;
			}
			else if ( (LA22_0==I_LOCALS) ) {
				alt22=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 22, 0, input);
				throw nvae;
			}

			switch (alt22) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:614:9: I_REGISTERS
					{
					match(input,I_REGISTERS,FOLLOW_I_REGISTERS_in_registers_directive1462);
					retval.isLocalsDirective = false;
					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:615:9: I_LOCALS
					{
					match(input,I_LOCALS,FOLLOW_I_LOCALS_in_registers_directive1474);
					retval.isLocalsDirective = true;
					}
					break;

			}

			match(input, Token.DOWN, null);
			pushFollow(FOLLOW_short_integral_literal_in_registers_directive1492);
			short_integral_literal94=short_integral_literal();
			state._fsp--;

			retval.registers = short_integral_literal94 & 0xFFFF;
			match(input, Token.UP, null);

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "registers_directive"



	// $ANTLR start "label_def"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:620:1: label_def : ^( I_LABEL SIMPLE_NAME ) ;
	public final void label_def() throws RecognitionException {
		CommonTree SIMPLE_NAME95=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:621:3: ( ^( I_LABEL SIMPLE_NAME ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:621:5: ^( I_LABEL SIMPLE_NAME )
			{
			match(input,I_LABEL,FOLLOW_I_LABEL_in_label_def1512);
			match(input, Token.DOWN, null);
			SIMPLE_NAME95=(CommonTree)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_label_def1514);
			match(input, Token.UP, null);


			    method_stack.peek().methodBuilder.addLabel((SIMPLE_NAME95!=null?SIMPLE_NAME95.getText():null));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "label_def"



	// $ANTLR start "catches"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:626:1: catches returns [List<BuilderTryBlock> tryBlocks] : ^( I_CATCHES ( catch_directive )* ( catchall_directive )* ) ;
	public final List<BuilderTryBlock> catches() throws RecognitionException {
		List<BuilderTryBlock> tryBlocks = null;


		tryBlocks = Lists.newArrayList();
		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:628:3: ( ^( I_CATCHES ( catch_directive )* ( catchall_directive )* ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:628:5: ^( I_CATCHES ( catch_directive )* ( catchall_directive )* )
			{
			match(input,I_CATCHES,FOLLOW_I_CATCHES_in_catches1540);
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:628:17: ( catch_directive )*
				loop23:
				while (true) {
					int alt23=2;
					int LA23_0 = input.LA(1);
					if ( (LA23_0==I_CATCH) ) {
						alt23=1;
					}

					switch (alt23) {
					case 1 :
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:628:17: catch_directive
						{
						pushFollow(FOLLOW_catch_directive_in_catches1542);
						catch_directive();
						state._fsp--;

						}
						break;

					default :
						break loop23;
					}
				}

				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:628:34: ( catchall_directive )*
				loop24:
				while (true) {
					int alt24=2;
					int LA24_0 = input.LA(1);
					if ( (LA24_0==I_CATCHALL) ) {
						alt24=1;
					}

					switch (alt24) {
					case 1 :
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:628:34: catchall_directive
						{
						pushFollow(FOLLOW_catchall_directive_in_catches1545);
						catchall_directive();
						state._fsp--;

						}
						break;

					default :
						break loop24;
					}
				}

				match(input, Token.UP, null);
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return tryBlocks;
	}
	// $ANTLR end "catches"



	// $ANTLR start "catch_directive"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:630:1: catch_directive : ^( I_CATCH nonvoid_type_descriptor from= label_ref to= label_ref using= label_ref ) ;
	public final void catch_directive() throws RecognitionException {
		Label from =null;
		Label to =null;
		Label using =null;
		TreeRuleReturnScope nonvoid_type_descriptor96 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:631:3: ( ^( I_CATCH nonvoid_type_descriptor from= label_ref to= label_ref using= label_ref ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:631:5: ^( I_CATCH nonvoid_type_descriptor from= label_ref to= label_ref using= label_ref )
			{
			match(input,I_CATCH,FOLLOW_I_CATCH_in_catch_directive1558);
			match(input, Token.DOWN, null);
			pushFollow(FOLLOW_nonvoid_type_descriptor_in_catch_directive1560);
			nonvoid_type_descriptor96=nonvoid_type_descriptor();
			state._fsp--;

			pushFollow(FOLLOW_label_ref_in_catch_directive1564);
			from=label_ref();
			state._fsp--;

			pushFollow(FOLLOW_label_ref_in_catch_directive1568);
			to=label_ref();
			state._fsp--;

			pushFollow(FOLLOW_label_ref_in_catch_directive1572);
			using=label_ref();
			state._fsp--;

			match(input, Token.UP, null);


			    method_stack.peek().methodBuilder.addCatch(dexBuilder.internTypeReference((nonvoid_type_descriptor96!=null?((smaliTreeWalker.nonvoid_type_descriptor_return)nonvoid_type_descriptor96).type:null)),
			        from, to, using);
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "catch_directive"



	// $ANTLR start "catchall_directive"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:637:1: catchall_directive : ^( I_CATCHALL from= label_ref to= label_ref using= label_ref ) ;
	public final void catchall_directive() throws RecognitionException {
		Label from =null;
		Label to =null;
		Label using =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:638:3: ( ^( I_CATCHALL from= label_ref to= label_ref using= label_ref ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:638:5: ^( I_CATCHALL from= label_ref to= label_ref using= label_ref )
			{
			match(input,I_CATCHALL,FOLLOW_I_CATCHALL_in_catchall_directive1588);
			match(input, Token.DOWN, null);
			pushFollow(FOLLOW_label_ref_in_catchall_directive1592);
			from=label_ref();
			state._fsp--;

			pushFollow(FOLLOW_label_ref_in_catchall_directive1596);
			to=label_ref();
			state._fsp--;

			pushFollow(FOLLOW_label_ref_in_catchall_directive1600);
			using=label_ref();
			state._fsp--;

			match(input, Token.UP, null);


			    method_stack.peek().methodBuilder.addCatch(from, to, using);
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "catchall_directive"



	// $ANTLR start "parameters"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:643:1: parameters[List<SmaliMethodParameter> parameters] : ^( I_PARAMETERS ( parameter[parameters] )* ) ;
	public final void parameters(List<SmaliMethodParameter> parameters) throws RecognitionException {
		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:644:3: ( ^( I_PARAMETERS ( parameter[parameters] )* ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:644:5: ^( I_PARAMETERS ( parameter[parameters] )* )
			{
			match(input,I_PARAMETERS,FOLLOW_I_PARAMETERS_in_parameters1617);
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:644:20: ( parameter[parameters] )*
				loop25:
				while (true) {
					int alt25=2;
					int LA25_0 = input.LA(1);
					if ( (LA25_0==I_PARAMETER) ) {
						alt25=1;
					}

					switch (alt25) {
					case 1 :
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:644:21: parameter[parameters]
						{
						pushFollow(FOLLOW_parameter_in_parameters1620);
						parameter(parameters);
						state._fsp--;

						}
						break;

					default :
						break loop25;
					}
				}

				match(input, Token.UP, null);
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "parameters"



	// $ANTLR start "parameter"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:646:1: parameter[List<SmaliMethodParameter> parameters] : ^( I_PARAMETER REGISTER ( string_literal )? annotations ) ;
	public final void parameter(List<SmaliMethodParameter> parameters) throws RecognitionException {
		CommonTree REGISTER97=null;
		CommonTree I_PARAMETER98=null;
		String string_literal99 =null;
		Set<Annotation> annotations100 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:647:3: ( ^( I_PARAMETER REGISTER ( string_literal )? annotations ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:647:5: ^( I_PARAMETER REGISTER ( string_literal )? annotations )
			{
			I_PARAMETER98=(CommonTree)match(input,I_PARAMETER,FOLLOW_I_PARAMETER_in_parameter1636);
			match(input, Token.DOWN, null);
			REGISTER97=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_parameter1638);
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:647:28: ( string_literal )?
			int alt26=2;
			int LA26_0 = input.LA(1);
			if ( (LA26_0==STRING_LITERAL) ) {
				alt26=1;
			}
			switch (alt26) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:647:28: string_literal
					{
					pushFollow(FOLLOW_string_literal_in_parameter1640);
					string_literal99=string_literal();
					state._fsp--;

					}
					break;

			}

			pushFollow(FOLLOW_annotations_in_parameter1643);
			annotations100=annotations();
			state._fsp--;

			match(input, Token.UP, null);


			        final int registerNumber = parseRegister_short((REGISTER97!=null?REGISTER97.getText():null));
			        int totalMethodRegisters = method_stack.peek().totalMethodRegisters;
			        int methodParameterRegisters = method_stack.peek().methodParameterRegisters;

			        if (registerNumber >= totalMethodRegisters) {
			            throw new SemanticException(input, I_PARAMETER98, "Register %s is larger than the maximum register v%d " +
			                    "for this method", (REGISTER97!=null?REGISTER97.getText():null), totalMethodRegisters-1);
			        }
			        final int indexGuess = registerNumber - (totalMethodRegisters - methodParameterRegisters) - (method_stack.peek().isStatic?0:1);

			        if (indexGuess < 0) {
			            throw new SemanticException(input, I_PARAMETER98, "Register %s is not a parameter register.",
			                    (REGISTER97!=null?REGISTER97.getText():null));
			        }

			        int parameterIndex = LinearSearch.linearSearch(parameters, SmaliMethodParameter.COMPARATOR,
			            new WithRegister() { public int getRegister() { return indexGuess; } },
			                indexGuess);

			        if (parameterIndex < 0) {
			            throw new SemanticException(input, I_PARAMETER98, "Register %s is the second half of a wide parameter.",
			                                (REGISTER97!=null?REGISTER97.getText():null));
			        }

			        SmaliMethodParameter methodParameter = parameters.get(parameterIndex);
			        methodParameter.name = string_literal99;
			        if (annotations100 != null && annotations100.size() > 0) {
			            methodParameter.annotations = annotations100;
			        }
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "parameter"



	// $ANTLR start "debug_directive"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:680:1: debug_directive : ( line | local | end_local | restart_local | prologue | epilogue | source );
	public final void debug_directive() throws RecognitionException {
		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:681:3: ( line | local | end_local | restart_local | prologue | epilogue | source )
			int alt27=7;
			switch ( input.LA(1) ) {
			case I_LINE:
				{
				alt27=1;
				}
				break;
			case I_LOCAL:
				{
				alt27=2;
				}
				break;
			case I_END_LOCAL:
				{
				alt27=3;
				}
				break;
			case I_RESTART_LOCAL:
				{
				alt27=4;
				}
				break;
			case I_PROLOGUE:
				{
				alt27=5;
				}
				break;
			case I_EPILOGUE:
				{
				alt27=6;
				}
				break;
			case I_SOURCE:
				{
				alt27=7;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 27, 0, input);
				throw nvae;
			}
			switch (alt27) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:681:5: line
					{
					pushFollow(FOLLOW_line_in_debug_directive1660);
					line();
					state._fsp--;

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:682:5: local
					{
					pushFollow(FOLLOW_local_in_debug_directive1666);
					local();
					state._fsp--;

					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:683:5: end_local
					{
					pushFollow(FOLLOW_end_local_in_debug_directive1672);
					end_local();
					state._fsp--;

					}
					break;
				case 4 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:684:5: restart_local
					{
					pushFollow(FOLLOW_restart_local_in_debug_directive1678);
					restart_local();
					state._fsp--;

					}
					break;
				case 5 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:685:5: prologue
					{
					pushFollow(FOLLOW_prologue_in_debug_directive1684);
					prologue();
					state._fsp--;

					}
					break;
				case 6 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:686:5: epilogue
					{
					pushFollow(FOLLOW_epilogue_in_debug_directive1690);
					epilogue();
					state._fsp--;

					}
					break;
				case 7 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:687:5: source
					{
					pushFollow(FOLLOW_source_in_debug_directive1696);
					source();
					state._fsp--;

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "debug_directive"



	// $ANTLR start "line"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:689:1: line : ^( I_LINE integral_literal ) ;
	public final void line() throws RecognitionException {
		int integral_literal101 =0;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:690:3: ( ^( I_LINE integral_literal ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:690:5: ^( I_LINE integral_literal )
			{
			match(input,I_LINE,FOLLOW_I_LINE_in_line1707);
			match(input, Token.DOWN, null);
			pushFollow(FOLLOW_integral_literal_in_line1709);
			integral_literal101=integral_literal();
			state._fsp--;

			match(input, Token.UP, null);


			        method_stack.peek().methodBuilder.addLineNumber(integral_literal101);
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "line"



	// $ANTLR start "local"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:695:1: local : ^( I_LOCAL REGISTER ( ( NULL_LITERAL |name= string_literal ) ( nonvoid_type_descriptor )? (signature= string_literal )? )? ) ;
	public final void local() throws RecognitionException {
		CommonTree REGISTER102=null;
		String name =null;
		String signature =null;
		TreeRuleReturnScope nonvoid_type_descriptor103 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:696:3: ( ^( I_LOCAL REGISTER ( ( NULL_LITERAL |name= string_literal ) ( nonvoid_type_descriptor )? (signature= string_literal )? )? ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:696:5: ^( I_LOCAL REGISTER ( ( NULL_LITERAL |name= string_literal ) ( nonvoid_type_descriptor )? (signature= string_literal )? )? )
			{
			match(input,I_LOCAL,FOLLOW_I_LOCAL_in_local1727);
			match(input, Token.DOWN, null);
			REGISTER102=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_local1729);
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:696:24: ( ( NULL_LITERAL |name= string_literal ) ( nonvoid_type_descriptor )? (signature= string_literal )? )?
			int alt31=2;
			int LA31_0 = input.LA(1);
			if ( (LA31_0==NULL_LITERAL||LA31_0==STRING_LITERAL) ) {
				alt31=1;
			}
			switch (alt31) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:696:25: ( NULL_LITERAL |name= string_literal ) ( nonvoid_type_descriptor )? (signature= string_literal )?
					{
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:696:25: ( NULL_LITERAL |name= string_literal )
					int alt28=2;
					int LA28_0 = input.LA(1);
					if ( (LA28_0==NULL_LITERAL) ) {
						alt28=1;
					}
					else if ( (LA28_0==STRING_LITERAL) ) {
						alt28=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 28, 0, input);
						throw nvae;
					}

					switch (alt28) {
						case 1 :
							// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:696:26: NULL_LITERAL
							{
							match(input,NULL_LITERAL,FOLLOW_NULL_LITERAL_in_local1733);
							}
							break;
						case 2 :
							// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:696:41: name= string_literal
							{
							pushFollow(FOLLOW_string_literal_in_local1739);
							name=string_literal();
							state._fsp--;

							}
							break;

					}

					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:696:62: ( nonvoid_type_descriptor )?
					int alt29=2;
					int LA29_0 = input.LA(1);
					if ( (LA29_0==ARRAY_TYPE_PREFIX||LA29_0==CLASS_DESCRIPTOR||LA29_0==PRIMITIVE_TYPE) ) {
						alt29=1;
					}
					switch (alt29) {
						case 1 :
							// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:696:62: nonvoid_type_descriptor
							{
							pushFollow(FOLLOW_nonvoid_type_descriptor_in_local1742);
							nonvoid_type_descriptor103=nonvoid_type_descriptor();
							state._fsp--;

							}
							break;

					}

					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:696:96: (signature= string_literal )?
					int alt30=2;
					int LA30_0 = input.LA(1);
					if ( (LA30_0==STRING_LITERAL) ) {
						alt30=1;
					}
					switch (alt30) {
						case 1 :
							// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:696:96: signature= string_literal
							{
							pushFollow(FOLLOW_string_literal_in_local1747);
							signature=string_literal();
							state._fsp--;

							}
							break;

					}

					}
					break;

			}

			match(input, Token.UP, null);


			      int registerNumber = parseRegister_short((REGISTER102!=null?REGISTER102.getText():null));
			      method_stack.peek().methodBuilder.addStartLocal(registerNumber,
			              dexBuilder.internNullableStringReference(name),
			              dexBuilder.internNullableTypeReference((nonvoid_type_descriptor103!=null?((smaliTreeWalker.nonvoid_type_descriptor_return)nonvoid_type_descriptor103).type:null)),
			              dexBuilder.internNullableStringReference(signature));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "local"



	// $ANTLR start "end_local"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:705:1: end_local : ^( I_END_LOCAL REGISTER ) ;
	public final void end_local() throws RecognitionException {
		CommonTree REGISTER104=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:706:3: ( ^( I_END_LOCAL REGISTER ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:706:5: ^( I_END_LOCAL REGISTER )
			{
			match(input,I_END_LOCAL,FOLLOW_I_END_LOCAL_in_end_local1768);
			match(input, Token.DOWN, null);
			REGISTER104=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_end_local1770);
			match(input, Token.UP, null);


			      int registerNumber = parseRegister_short((REGISTER104!=null?REGISTER104.getText():null));
			      method_stack.peek().methodBuilder.addEndLocal(registerNumber);
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "end_local"



	// $ANTLR start "restart_local"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:712:1: restart_local : ^( I_RESTART_LOCAL REGISTER ) ;
	public final void restart_local() throws RecognitionException {
		CommonTree REGISTER105=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:713:3: ( ^( I_RESTART_LOCAL REGISTER ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:713:5: ^( I_RESTART_LOCAL REGISTER )
			{
			match(input,I_RESTART_LOCAL,FOLLOW_I_RESTART_LOCAL_in_restart_local1788);
			match(input, Token.DOWN, null);
			REGISTER105=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_restart_local1790);
			match(input, Token.UP, null);


			      int registerNumber = parseRegister_short((REGISTER105!=null?REGISTER105.getText():null));
			      method_stack.peek().methodBuilder.addRestartLocal(registerNumber);
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "restart_local"



	// $ANTLR start "prologue"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:719:1: prologue : I_PROLOGUE ;
	public final void prologue() throws RecognitionException {
		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:720:3: ( I_PROLOGUE )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:720:5: I_PROLOGUE
			{
			match(input,I_PROLOGUE,FOLLOW_I_PROLOGUE_in_prologue1807);

			      method_stack.peek().methodBuilder.addPrologue();
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "prologue"



	// $ANTLR start "epilogue"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:725:1: epilogue : I_EPILOGUE ;
	public final void epilogue() throws RecognitionException {
		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:726:3: ( I_EPILOGUE )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:726:5: I_EPILOGUE
			{
			match(input,I_EPILOGUE,FOLLOW_I_EPILOGUE_in_epilogue1823);

			      method_stack.peek().methodBuilder.addEpilogue();
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "epilogue"



	// $ANTLR start "source"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:731:1: source : ^( I_SOURCE ( string_literal )? ) ;
	public final void source() throws RecognitionException {
		String string_literal106 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:732:3: ( ^( I_SOURCE ( string_literal )? ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:732:5: ^( I_SOURCE ( string_literal )? )
			{
			match(input,I_SOURCE,FOLLOW_I_SOURCE_in_source1840);
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:732:16: ( string_literal )?
				int alt32=2;
				int LA32_0 = input.LA(1);
				if ( (LA32_0==STRING_LITERAL) ) {
					alt32=1;
				}
				switch (alt32) {
					case 1 :
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:732:16: string_literal
						{
						pushFollow(FOLLOW_string_literal_in_source1842);
						string_literal106=string_literal();
						state._fsp--;

						}
						break;

				}

				match(input, Token.UP, null);
			}


			      method_stack.peek().methodBuilder.addSetSourceFile(dexBuilder.internNullableStringReference(string_literal106));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "source"



	// $ANTLR start "call_site_extra_arguments"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:737:1: call_site_extra_arguments returns [List<ImmutableEncodedValue> extraArguments] : ^( I_CALL_SITE_EXTRA_ARGUMENTS ( literal )* ) ;
	public final List<ImmutableEncodedValue> call_site_extra_arguments() throws RecognitionException {
		List<ImmutableEncodedValue> extraArguments = null;


		ImmutableEncodedValue literal107 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:738:3: ( ^( I_CALL_SITE_EXTRA_ARGUMENTS ( literal )* ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:738:5: ^( I_CALL_SITE_EXTRA_ARGUMENTS ( literal )* )
			{
			 extraArguments = Lists.newArrayList();
			match(input,I_CALL_SITE_EXTRA_ARGUMENTS,FOLLOW_I_CALL_SITE_EXTRA_ARGUMENTS_in_call_site_extra_arguments1868);
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:739:33: ( literal )*
				loop33:
				while (true) {
					int alt33=2;
					int LA33_0 = input.LA(1);
					if ( (LA33_0==ARRAY_TYPE_PREFIX||(LA33_0 >= BOOL_LITERAL && LA33_0 <= BYTE_LITERAL)||(LA33_0 >= CHAR_LITERAL && LA33_0 <= CLASS_DESCRIPTOR)||LA33_0==DOUBLE_LITERAL||LA33_0==FLOAT_LITERAL||LA33_0==INTEGER_LITERAL||(LA33_0 >= I_ENCODED_ARRAY && LA33_0 <= I_ENCODED_METHOD_HANDLE)||LA33_0==I_METHOD_PROTOTYPE||LA33_0==I_SUBANNOTATION||LA33_0==LONG_LITERAL||LA33_0==NULL_LITERAL||LA33_0==PRIMITIVE_TYPE||LA33_0==SHORT_LITERAL||LA33_0==STRING_LITERAL||LA33_0==VOID_TYPE) ) {
						alt33=1;
					}

					switch (alt33) {
					case 1 :
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:739:34: literal
						{
						pushFollow(FOLLOW_literal_in_call_site_extra_arguments1871);
						literal107=literal();
						state._fsp--;

						 extraArguments.add(literal107);
						}
						break;

					default :
						break loop33;
					}
				}

				match(input, Token.UP, null);
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return extraArguments;
	}
	// $ANTLR end "call_site_extra_arguments"



	// $ANTLR start "ordered_method_items"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:741:1: ordered_method_items : ^( I_ORDERED_METHOD_ITEMS ( label_def | instruction | debug_directive )* ) ;
	public final void ordered_method_items() throws RecognitionException {
		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:742:3: ( ^( I_ORDERED_METHOD_ITEMS ( label_def | instruction | debug_directive )* ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:742:5: ^( I_ORDERED_METHOD_ITEMS ( label_def | instruction | debug_directive )* )
			{
			match(input,I_ORDERED_METHOD_ITEMS,FOLLOW_I_ORDERED_METHOD_ITEMS_in_ordered_method_items1887);
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:742:30: ( label_def | instruction | debug_directive )*
				loop34:
				while (true) {
					int alt34=4;
					switch ( input.LA(1) ) {
					case I_LABEL:
						{
						alt34=1;
						}
						break;
					case I_STATEMENT_ARRAY_DATA:
					case I_STATEMENT_FORMAT10t:
					case I_STATEMENT_FORMAT10x:
					case I_STATEMENT_FORMAT11n:
					case I_STATEMENT_FORMAT11x:
					case I_STATEMENT_FORMAT12x:
					case I_STATEMENT_FORMAT20bc:
					case I_STATEMENT_FORMAT20t:
					case I_STATEMENT_FORMAT21c_FIELD:
					case I_STATEMENT_FORMAT21c_METHOD_HANDLE:
					case I_STATEMENT_FORMAT21c_METHOD_TYPE:
					case I_STATEMENT_FORMAT21c_STRING:
					case I_STATEMENT_FORMAT21c_TYPE:
					case I_STATEMENT_FORMAT21ih:
					case I_STATEMENT_FORMAT21lh:
					case I_STATEMENT_FORMAT21s:
					case I_STATEMENT_FORMAT21t:
					case I_STATEMENT_FORMAT22b:
					case I_STATEMENT_FORMAT22c_FIELD:
					case I_STATEMENT_FORMAT22c_TYPE:
					case I_STATEMENT_FORMAT22s:
					case I_STATEMENT_FORMAT22t:
					case I_STATEMENT_FORMAT22x:
					case I_STATEMENT_FORMAT23x:
					case I_STATEMENT_FORMAT30t:
					case I_STATEMENT_FORMAT31c:
					case I_STATEMENT_FORMAT31i:
					case I_STATEMENT_FORMAT31t:
					case I_STATEMENT_FORMAT32x:
					case I_STATEMENT_FORMAT35c_CALL_SITE:
					case I_STATEMENT_FORMAT35c_METHOD:
					case I_STATEMENT_FORMAT35c_TYPE:
					case I_STATEMENT_FORMAT3rc_CALL_SITE:
					case I_STATEMENT_FORMAT3rc_METHOD:
					case I_STATEMENT_FORMAT3rc_TYPE:
					case I_STATEMENT_FORMAT45cc_METHOD:
					case I_STATEMENT_FORMAT4rcc_METHOD:
					case I_STATEMENT_FORMAT51l:
					case I_STATEMENT_PACKED_SWITCH:
					case I_STATEMENT_SPARSE_SWITCH:
						{
						alt34=2;
						}
						break;
					case I_END_LOCAL:
					case I_EPILOGUE:
					case I_LINE:
					case I_LOCAL:
					case I_PROLOGUE:
					case I_RESTART_LOCAL:
					case I_SOURCE:
						{
						alt34=3;
						}
						break;
					}
					switch (alt34) {
					case 1 :
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:742:31: label_def
						{
						pushFollow(FOLLOW_label_def_in_ordered_method_items1890);
						label_def();
						state._fsp--;

						}
						break;
					case 2 :
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:742:43: instruction
						{
						pushFollow(FOLLOW_instruction_in_ordered_method_items1894);
						instruction();
						state._fsp--;

						}
						break;
					case 3 :
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:742:57: debug_directive
						{
						pushFollow(FOLLOW_debug_directive_in_ordered_method_items1898);
						debug_directive();
						state._fsp--;

						}
						break;

					default :
						break loop34;
					}
				}

				match(input, Token.UP, null);
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ordered_method_items"



	// $ANTLR start "label_ref"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:744:1: label_ref returns [Label label] : SIMPLE_NAME ;
	public final Label label_ref() throws RecognitionException {
		Label label = null;


		CommonTree SIMPLE_NAME108=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:745:3: ( SIMPLE_NAME )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:745:5: SIMPLE_NAME
			{
			SIMPLE_NAME108=(CommonTree)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_label_ref1914);
			 label = method_stack.peek().methodBuilder.getLabel((SIMPLE_NAME108!=null?SIMPLE_NAME108.getText():null));
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return label;
	}
	// $ANTLR end "label_ref"


	public static class register_list_return extends TreeRuleReturnScope {
		public byte[] registers;
		public byte registerCount;
	};


	// $ANTLR start "register_list"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:747:1: register_list returns [byte[] registers, byte registerCount] : ^( I_REGISTER_LIST ( REGISTER )* ) ;
	public final smaliTreeWalker.register_list_return register_list() throws RecognitionException {
		smaliTreeWalker.register_list_return retval = new smaliTreeWalker.register_list_return();
		retval.start = input.LT(1);

		CommonTree I_REGISTER_LIST109=null;
		CommonTree REGISTER110=null;


		    retval.registers = new byte[5];
		    retval.registerCount = 0;
		
		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:753:3: ( ^( I_REGISTER_LIST ( REGISTER )* ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:753:5: ^( I_REGISTER_LIST ( REGISTER )* )
			{
			I_REGISTER_LIST109=(CommonTree)match(input,I_REGISTER_LIST,FOLLOW_I_REGISTER_LIST_in_register_list1939);
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:754:7: ( REGISTER )*
				loop35:
				while (true) {
					int alt35=2;
					int LA35_0 = input.LA(1);
					if ( (LA35_0==REGISTER) ) {
						alt35=1;
					}

					switch (alt35) {
					case 1 :
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:754:8: REGISTER
						{
						REGISTER110=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_register_list1948);

						        if (retval.registerCount == 5) {
						          throw new SemanticException(input, I_REGISTER_LIST109, "A list of registers can only have a maximum of 5 " +
						                  "registers. Use the <op>/range alternate opcode instead.");
						        }
						        retval.registers[retval.registerCount++] = parseRegister_nibble((REGISTER110!=null?REGISTER110.getText():null));
						
						}
						break;

					default :
						break loop35;
					}
				}

				match(input, Token.UP, null);
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "register_list"


	public static class register_range_return extends TreeRuleReturnScope {
		public int startRegister;
		public int endRegister;
	};


	// $ANTLR start "register_range"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:763:1: register_range returns [int startRegister, int endRegister] : ^( I_REGISTER_RANGE (startReg= REGISTER (endReg= REGISTER )? )? ) ;
	public final smaliTreeWalker.register_range_return register_range() throws RecognitionException {
		smaliTreeWalker.register_range_return retval = new smaliTreeWalker.register_range_return();
		retval.start = input.LT(1);

		CommonTree startReg=null;
		CommonTree endReg=null;
		CommonTree I_REGISTER_RANGE111=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:764:3: ( ^( I_REGISTER_RANGE (startReg= REGISTER (endReg= REGISTER )? )? ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:764:5: ^( I_REGISTER_RANGE (startReg= REGISTER (endReg= REGISTER )? )? )
			{
			I_REGISTER_RANGE111=(CommonTree)match(input,I_REGISTER_RANGE,FOLLOW_I_REGISTER_RANGE_in_register_range1973);
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:764:24: (startReg= REGISTER (endReg= REGISTER )? )?
				int alt37=2;
				int LA37_0 = input.LA(1);
				if ( (LA37_0==REGISTER) ) {
					alt37=1;
				}
				switch (alt37) {
					case 1 :
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:764:25: startReg= REGISTER (endReg= REGISTER )?
						{
						startReg=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_register_range1978);
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:764:49: (endReg= REGISTER )?
						int alt36=2;
						int LA36_0 = input.LA(1);
						if ( (LA36_0==REGISTER) ) {
							alt36=1;
						}
						switch (alt36) {
							case 1 :
								// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:764:49: endReg= REGISTER
								{
								endReg=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_register_range1982);
								}
								break;

						}

						}
						break;

				}

				match(input, Token.UP, null);
			}


			        if (startReg == null) {
			            retval.startRegister = 0;
			            retval.endRegister = -1;
			        } else {
			                retval.startRegister = parseRegister_short((startReg!=null?startReg.getText():null));
			                if (endReg == null) {
			                    retval.endRegister = retval.startRegister;
			                } else {
			                    retval.endRegister = parseRegister_short((endReg!=null?endReg.getText():null));
			                }

			                int registerCount = retval.endRegister-retval.startRegister+1;
			                if (registerCount < 1) {
			                    throw new SemanticException(input, I_REGISTER_RANGE111, "A register range must have the lower register listed first");
			                }
			            }
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "register_range"



	// $ANTLR start "verification_error_reference"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:784:1: verification_error_reference returns [ImmutableReference reference] : ( CLASS_DESCRIPTOR | field_reference | method_reference );
	public final ImmutableReference verification_error_reference() throws RecognitionException {
		ImmutableReference reference = null;


		CommonTree CLASS_DESCRIPTOR112=null;
		TreeRuleReturnScope field_reference113 =null;
		ImmutableMethodReference method_reference114 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:785:3: ( CLASS_DESCRIPTOR | field_reference | method_reference )
			int alt38=3;
			switch ( input.LA(1) ) {
			case CLASS_DESCRIPTOR:
				{
				int LA38_1 = input.LA(2);
				if ( (LA38_1==UP) ) {
					alt38=1;
				}
				else if ( (LA38_1==SIMPLE_NAME) ) {
					int LA38_3 = input.LA(3);
					if ( (LA38_3==ARRAY_TYPE_PREFIX||LA38_3==CLASS_DESCRIPTOR||LA38_3==PRIMITIVE_TYPE) ) {
						alt38=2;
					}
					else if ( (LA38_3==I_METHOD_PROTOTYPE) ) {
						alt38=3;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 38, 3, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 38, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case ARRAY_TYPE_PREFIX:
				{
				int LA38_2 = input.LA(2);
				if ( (LA38_2==PRIMITIVE_TYPE) ) {
					int LA38_5 = input.LA(3);
					if ( (LA38_5==SIMPLE_NAME) ) {
						int LA38_3 = input.LA(4);
						if ( (LA38_3==ARRAY_TYPE_PREFIX||LA38_3==CLASS_DESCRIPTOR||LA38_3==PRIMITIVE_TYPE) ) {
							alt38=2;
						}
						else if ( (LA38_3==I_METHOD_PROTOTYPE) ) {
							alt38=3;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 38, 3, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 38, 5, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}
				else if ( (LA38_2==CLASS_DESCRIPTOR) ) {
					int LA38_6 = input.LA(3);
					if ( (LA38_6==SIMPLE_NAME) ) {
						int LA38_3 = input.LA(4);
						if ( (LA38_3==ARRAY_TYPE_PREFIX||LA38_3==CLASS_DESCRIPTOR||LA38_3==PRIMITIVE_TYPE) ) {
							alt38=2;
						}
						else if ( (LA38_3==I_METHOD_PROTOTYPE) ) {
							alt38=3;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 38, 3, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 38, 6, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 38, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case SIMPLE_NAME:
				{
				int LA38_3 = input.LA(2);
				if ( (LA38_3==ARRAY_TYPE_PREFIX||LA38_3==CLASS_DESCRIPTOR||LA38_3==PRIMITIVE_TYPE) ) {
					alt38=2;
				}
				else if ( (LA38_3==I_METHOD_PROTOTYPE) ) {
					alt38=3;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 38, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 38, 0, input);
				throw nvae;
			}
			switch (alt38) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:785:5: CLASS_DESCRIPTOR
					{
					CLASS_DESCRIPTOR112=(CommonTree)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_verification_error_reference2005);

					    reference = new ImmutableTypeReference((CLASS_DESCRIPTOR112!=null?CLASS_DESCRIPTOR112.getText():null));
					
					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:789:5: field_reference
					{
					pushFollow(FOLLOW_field_reference_in_verification_error_reference2015);
					field_reference113=field_reference();
					state._fsp--;


					    reference = (field_reference113!=null?((smaliTreeWalker.field_reference_return)field_reference113).fieldReference:null);
					
					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:793:5: method_reference
					{
					pushFollow(FOLLOW_method_reference_in_verification_error_reference2025);
					method_reference114=method_reference();
					state._fsp--;


					    reference = method_reference114;
					
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return reference;
	}
	// $ANTLR end "verification_error_reference"



	// $ANTLR start "verification_error_type"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:798:1: verification_error_type returns [int verificationError] : VERIFICATION_ERROR_TYPE ;
	public final int verification_error_type() throws RecognitionException {
		int verificationError = 0;


		CommonTree VERIFICATION_ERROR_TYPE115=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:799:3: ( VERIFICATION_ERROR_TYPE )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:799:5: VERIFICATION_ERROR_TYPE
			{
			VERIFICATION_ERROR_TYPE115=(CommonTree)match(input,VERIFICATION_ERROR_TYPE,FOLLOW_VERIFICATION_ERROR_TYPE_in_verification_error_type2042);

			    verificationError = VerificationError.getVerificationError((VERIFICATION_ERROR_TYPE115!=null?VERIFICATION_ERROR_TYPE115.getText():null));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return verificationError;
	}
	// $ANTLR end "verification_error_type"


	public static class instruction_return extends TreeRuleReturnScope {
	};


	// $ANTLR start "instruction"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:804:1: instruction : ( insn_format10t | insn_format10x | insn_format11n | insn_format11x | insn_format12x | insn_format20bc | insn_format20t | insn_format21c_field | insn_format21c_method_handle | insn_format21c_method_type | insn_format21c_string | insn_format21c_type | insn_format21ih | insn_format21lh | insn_format21s | insn_format21t | insn_format22b | insn_format22c_field | insn_format22c_type | insn_format22s | insn_format22t | insn_format22x | insn_format23x | insn_format30t | insn_format31c | insn_format31i | insn_format31t | insn_format32x | insn_format35c_call_site | insn_format35c_method | insn_format35c_type | insn_format3rc_call_site | insn_format3rc_method | insn_format3rc_type | insn_format45cc_method | insn_format4rcc_method | insn_format51l_type | insn_array_data_directive | insn_packed_switch_directive | insn_sparse_switch_directive );
	public final smaliTreeWalker.instruction_return instruction() throws RecognitionException {
		smaliTreeWalker.instruction_return retval = new smaliTreeWalker.instruction_return();
		retval.start = input.LT(1);

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:805:3: ( insn_format10t | insn_format10x | insn_format11n | insn_format11x | insn_format12x | insn_format20bc | insn_format20t | insn_format21c_field | insn_format21c_method_handle | insn_format21c_method_type | insn_format21c_string | insn_format21c_type | insn_format21ih | insn_format21lh | insn_format21s | insn_format21t | insn_format22b | insn_format22c_field | insn_format22c_type | insn_format22s | insn_format22t | insn_format22x | insn_format23x | insn_format30t | insn_format31c | insn_format31i | insn_format31t | insn_format32x | insn_format35c_call_site | insn_format35c_method | insn_format35c_type | insn_format3rc_call_site | insn_format3rc_method | insn_format3rc_type | insn_format45cc_method | insn_format4rcc_method | insn_format51l_type | insn_array_data_directive | insn_packed_switch_directive | insn_sparse_switch_directive )
			int alt39=40;
			switch ( input.LA(1) ) {
			case I_STATEMENT_FORMAT10t:
				{
				alt39=1;
				}
				break;
			case I_STATEMENT_FORMAT10x:
				{
				alt39=2;
				}
				break;
			case I_STATEMENT_FORMAT11n:
				{
				alt39=3;
				}
				break;
			case I_STATEMENT_FORMAT11x:
				{
				alt39=4;
				}
				break;
			case I_STATEMENT_FORMAT12x:
				{
				alt39=5;
				}
				break;
			case I_STATEMENT_FORMAT20bc:
				{
				alt39=6;
				}
				break;
			case I_STATEMENT_FORMAT20t:
				{
				alt39=7;
				}
				break;
			case I_STATEMENT_FORMAT21c_FIELD:
				{
				alt39=8;
				}
				break;
			case I_STATEMENT_FORMAT21c_METHOD_HANDLE:
				{
				alt39=9;
				}
				break;
			case I_STATEMENT_FORMAT21c_METHOD_TYPE:
				{
				alt39=10;
				}
				break;
			case I_STATEMENT_FORMAT21c_STRING:
				{
				alt39=11;
				}
				break;
			case I_STATEMENT_FORMAT21c_TYPE:
				{
				alt39=12;
				}
				break;
			case I_STATEMENT_FORMAT21ih:
				{
				alt39=13;
				}
				break;
			case I_STATEMENT_FORMAT21lh:
				{
				alt39=14;
				}
				break;
			case I_STATEMENT_FORMAT21s:
				{
				alt39=15;
				}
				break;
			case I_STATEMENT_FORMAT21t:
				{
				alt39=16;
				}
				break;
			case I_STATEMENT_FORMAT22b:
				{
				alt39=17;
				}
				break;
			case I_STATEMENT_FORMAT22c_FIELD:
				{
				alt39=18;
				}
				break;
			case I_STATEMENT_FORMAT22c_TYPE:
				{
				alt39=19;
				}
				break;
			case I_STATEMENT_FORMAT22s:
				{
				alt39=20;
				}
				break;
			case I_STATEMENT_FORMAT22t:
				{
				alt39=21;
				}
				break;
			case I_STATEMENT_FORMAT22x:
				{
				alt39=22;
				}
				break;
			case I_STATEMENT_FORMAT23x:
				{
				alt39=23;
				}
				break;
			case I_STATEMENT_FORMAT30t:
				{
				alt39=24;
				}
				break;
			case I_STATEMENT_FORMAT31c:
				{
				alt39=25;
				}
				break;
			case I_STATEMENT_FORMAT31i:
				{
				alt39=26;
				}
				break;
			case I_STATEMENT_FORMAT31t:
				{
				alt39=27;
				}
				break;
			case I_STATEMENT_FORMAT32x:
				{
				alt39=28;
				}
				break;
			case I_STATEMENT_FORMAT35c_CALL_SITE:
				{
				alt39=29;
				}
				break;
			case I_STATEMENT_FORMAT35c_METHOD:
				{
				alt39=30;
				}
				break;
			case I_STATEMENT_FORMAT35c_TYPE:
				{
				alt39=31;
				}
				break;
			case I_STATEMENT_FORMAT3rc_CALL_SITE:
				{
				alt39=32;
				}
				break;
			case I_STATEMENT_FORMAT3rc_METHOD:
				{
				alt39=33;
				}
				break;
			case I_STATEMENT_FORMAT3rc_TYPE:
				{
				alt39=34;
				}
				break;
			case I_STATEMENT_FORMAT45cc_METHOD:
				{
				alt39=35;
				}
				break;
			case I_STATEMENT_FORMAT4rcc_METHOD:
				{
				alt39=36;
				}
				break;
			case I_STATEMENT_FORMAT51l:
				{
				alt39=37;
				}
				break;
			case I_STATEMENT_ARRAY_DATA:
				{
				alt39=38;
				}
				break;
			case I_STATEMENT_PACKED_SWITCH:
				{
				alt39=39;
				}
				break;
			case I_STATEMENT_SPARSE_SWITCH:
				{
				alt39=40;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 39, 0, input);
				throw nvae;
			}
			switch (alt39) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:805:5: insn_format10t
					{
					pushFollow(FOLLOW_insn_format10t_in_instruction2056);
					insn_format10t();
					state._fsp--;

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:806:5: insn_format10x
					{
					pushFollow(FOLLOW_insn_format10x_in_instruction2062);
					insn_format10x();
					state._fsp--;

					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:807:5: insn_format11n
					{
					pushFollow(FOLLOW_insn_format11n_in_instruction2068);
					insn_format11n();
					state._fsp--;

					}
					break;
				case 4 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:808:5: insn_format11x
					{
					pushFollow(FOLLOW_insn_format11x_in_instruction2074);
					insn_format11x();
					state._fsp--;

					}
					break;
				case 5 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:809:5: insn_format12x
					{
					pushFollow(FOLLOW_insn_format12x_in_instruction2080);
					insn_format12x();
					state._fsp--;

					}
					break;
				case 6 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:810:5: insn_format20bc
					{
					pushFollow(FOLLOW_insn_format20bc_in_instruction2086);
					insn_format20bc();
					state._fsp--;

					}
					break;
				case 7 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:811:5: insn_format20t
					{
					pushFollow(FOLLOW_insn_format20t_in_instruction2092);
					insn_format20t();
					state._fsp--;

					}
					break;
				case 8 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:812:5: insn_format21c_field
					{
					pushFollow(FOLLOW_insn_format21c_field_in_instruction2098);
					insn_format21c_field();
					state._fsp--;

					}
					break;
				case 9 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:813:5: insn_format21c_method_handle
					{
					pushFollow(FOLLOW_insn_format21c_method_handle_in_instruction2104);
					insn_format21c_method_handle();
					state._fsp--;

					}
					break;
				case 10 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:814:5: insn_format21c_method_type
					{
					pushFollow(FOLLOW_insn_format21c_method_type_in_instruction2110);
					insn_format21c_method_type();
					state._fsp--;

					}
					break;
				case 11 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:815:5: insn_format21c_string
					{
					pushFollow(FOLLOW_insn_format21c_string_in_instruction2116);
					insn_format21c_string();
					state._fsp--;

					}
					break;
				case 12 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:816:5: insn_format21c_type
					{
					pushFollow(FOLLOW_insn_format21c_type_in_instruction2122);
					insn_format21c_type();
					state._fsp--;

					}
					break;
				case 13 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:817:5: insn_format21ih
					{
					pushFollow(FOLLOW_insn_format21ih_in_instruction2128);
					insn_format21ih();
					state._fsp--;

					}
					break;
				case 14 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:818:5: insn_format21lh
					{
					pushFollow(FOLLOW_insn_format21lh_in_instruction2134);
					insn_format21lh();
					state._fsp--;

					}
					break;
				case 15 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:819:5: insn_format21s
					{
					pushFollow(FOLLOW_insn_format21s_in_instruction2140);
					insn_format21s();
					state._fsp--;

					}
					break;
				case 16 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:820:5: insn_format21t
					{
					pushFollow(FOLLOW_insn_format21t_in_instruction2146);
					insn_format21t();
					state._fsp--;

					}
					break;
				case 17 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:821:5: insn_format22b
					{
					pushFollow(FOLLOW_insn_format22b_in_instruction2152);
					insn_format22b();
					state._fsp--;

					}
					break;
				case 18 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:822:5: insn_format22c_field
					{
					pushFollow(FOLLOW_insn_format22c_field_in_instruction2158);
					insn_format22c_field();
					state._fsp--;

					}
					break;
				case 19 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:823:5: insn_format22c_type
					{
					pushFollow(FOLLOW_insn_format22c_type_in_instruction2164);
					insn_format22c_type();
					state._fsp--;

					}
					break;
				case 20 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:824:5: insn_format22s
					{
					pushFollow(FOLLOW_insn_format22s_in_instruction2170);
					insn_format22s();
					state._fsp--;

					}
					break;
				case 21 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:825:5: insn_format22t
					{
					pushFollow(FOLLOW_insn_format22t_in_instruction2176);
					insn_format22t();
					state._fsp--;

					}
					break;
				case 22 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:826:5: insn_format22x
					{
					pushFollow(FOLLOW_insn_format22x_in_instruction2182);
					insn_format22x();
					state._fsp--;

					}
					break;
				case 23 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:827:5: insn_format23x
					{
					pushFollow(FOLLOW_insn_format23x_in_instruction2188);
					insn_format23x();
					state._fsp--;

					}
					break;
				case 24 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:828:5: insn_format30t
					{
					pushFollow(FOLLOW_insn_format30t_in_instruction2194);
					insn_format30t();
					state._fsp--;

					}
					break;
				case 25 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:829:5: insn_format31c
					{
					pushFollow(FOLLOW_insn_format31c_in_instruction2200);
					insn_format31c();
					state._fsp--;

					}
					break;
				case 26 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:830:5: insn_format31i
					{
					pushFollow(FOLLOW_insn_format31i_in_instruction2206);
					insn_format31i();
					state._fsp--;

					}
					break;
				case 27 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:831:5: insn_format31t
					{
					pushFollow(FOLLOW_insn_format31t_in_instruction2212);
					insn_format31t();
					state._fsp--;

					}
					break;
				case 28 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:832:5: insn_format32x
					{
					pushFollow(FOLLOW_insn_format32x_in_instruction2218);
					insn_format32x();
					state._fsp--;

					}
					break;
				case 29 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:833:5: insn_format35c_call_site
					{
					pushFollow(FOLLOW_insn_format35c_call_site_in_instruction2224);
					insn_format35c_call_site();
					state._fsp--;

					}
					break;
				case 30 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:834:5: insn_format35c_method
					{
					pushFollow(FOLLOW_insn_format35c_method_in_instruction2230);
					insn_format35c_method();
					state._fsp--;

					}
					break;
				case 31 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:835:5: insn_format35c_type
					{
					pushFollow(FOLLOW_insn_format35c_type_in_instruction2236);
					insn_format35c_type();
					state._fsp--;

					}
					break;
				case 32 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:836:5: insn_format3rc_call_site
					{
					pushFollow(FOLLOW_insn_format3rc_call_site_in_instruction2242);
					insn_format3rc_call_site();
					state._fsp--;

					}
					break;
				case 33 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:837:5: insn_format3rc_method
					{
					pushFollow(FOLLOW_insn_format3rc_method_in_instruction2248);
					insn_format3rc_method();
					state._fsp--;

					}
					break;
				case 34 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:838:5: insn_format3rc_type
					{
					pushFollow(FOLLOW_insn_format3rc_type_in_instruction2254);
					insn_format3rc_type();
					state._fsp--;

					}
					break;
				case 35 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:839:5: insn_format45cc_method
					{
					pushFollow(FOLLOW_insn_format45cc_method_in_instruction2260);
					insn_format45cc_method();
					state._fsp--;

					}
					break;
				case 36 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:840:5: insn_format4rcc_method
					{
					pushFollow(FOLLOW_insn_format4rcc_method_in_instruction2266);
					insn_format4rcc_method();
					state._fsp--;

					}
					break;
				case 37 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:841:5: insn_format51l_type
					{
					pushFollow(FOLLOW_insn_format51l_type_in_instruction2272);
					insn_format51l_type();
					state._fsp--;

					}
					break;
				case 38 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:842:5: insn_array_data_directive
					{
					pushFollow(FOLLOW_insn_array_data_directive_in_instruction2278);
					insn_array_data_directive();
					state._fsp--;

					}
					break;
				case 39 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:843:5: insn_packed_switch_directive
					{
					pushFollow(FOLLOW_insn_packed_switch_directive_in_instruction2284);
					insn_packed_switch_directive();
					state._fsp--;

					}
					break;
				case 40 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:844:5: insn_sparse_switch_directive
					{
					pushFollow(FOLLOW_insn_sparse_switch_directive_in_instruction2290);
					insn_sparse_switch_directive();
					state._fsp--;

					}
					break;

			}
		}
		catch (Exception ex) {

			    reportError(new SemanticException(input, ((CommonTree)retval.start), ex.getMessage()));
			    recover(input, null);
			
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "instruction"



	// $ANTLR start "insn_format10t"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:850:1: insn_format10t : ^( I_STATEMENT_FORMAT10t INSTRUCTION_FORMAT10t label_ref ) ;
	public final void insn_format10t() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT10t116=null;
		Label label_ref117 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:851:3: ( ^( I_STATEMENT_FORMAT10t INSTRUCTION_FORMAT10t label_ref ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:852:5: ^( I_STATEMENT_FORMAT10t INSTRUCTION_FORMAT10t label_ref )
			{
			match(input,I_STATEMENT_FORMAT10t,FOLLOW_I_STATEMENT_FORMAT10t_in_insn_format10t2314);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT10t116=(CommonTree)match(input,INSTRUCTION_FORMAT10t,FOLLOW_INSTRUCTION_FORMAT10t_in_insn_format10t2316);
			pushFollow(FOLLOW_label_ref_in_insn_format10t2318);
			label_ref117=label_ref();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT10t116!=null?INSTRUCTION_FORMAT10t116.getText():null));
			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction10t(opcode, label_ref117));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format10t"



	// $ANTLR start "insn_format10x"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:858:1: insn_format10x : ^( I_STATEMENT_FORMAT10x INSTRUCTION_FORMAT10x ) ;
	public final void insn_format10x() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT10x118=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:859:3: ( ^( I_STATEMENT_FORMAT10x INSTRUCTION_FORMAT10x ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:860:5: ^( I_STATEMENT_FORMAT10x INSTRUCTION_FORMAT10x )
			{
			match(input,I_STATEMENT_FORMAT10x,FOLLOW_I_STATEMENT_FORMAT10x_in_insn_format10x2341);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT10x118=(CommonTree)match(input,INSTRUCTION_FORMAT10x,FOLLOW_INSTRUCTION_FORMAT10x_in_insn_format10x2343);
			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT10x118!=null?INSTRUCTION_FORMAT10x118.getText():null));
			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction10x(opcode));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format10x"



	// $ANTLR start "insn_format11n"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:866:1: insn_format11n : ^( I_STATEMENT_FORMAT11n INSTRUCTION_FORMAT11n REGISTER short_integral_literal ) ;
	public final void insn_format11n() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT11n119=null;
		CommonTree REGISTER120=null;
		short short_integral_literal121 =0;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:867:3: ( ^( I_STATEMENT_FORMAT11n INSTRUCTION_FORMAT11n REGISTER short_integral_literal ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:868:5: ^( I_STATEMENT_FORMAT11n INSTRUCTION_FORMAT11n REGISTER short_integral_literal )
			{
			match(input,I_STATEMENT_FORMAT11n,FOLLOW_I_STATEMENT_FORMAT11n_in_insn_format11n2366);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT11n119=(CommonTree)match(input,INSTRUCTION_FORMAT11n,FOLLOW_INSTRUCTION_FORMAT11n_in_insn_format11n2368);
			REGISTER120=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format11n2370);
			pushFollow(FOLLOW_short_integral_literal_in_insn_format11n2372);
			short_integral_literal121=short_integral_literal();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT11n119!=null?INSTRUCTION_FORMAT11n119.getText():null));
			      byte regA = parseRegister_nibble((REGISTER120!=null?REGISTER120.getText():null));

			      short litB = short_integral_literal121;
			      LiteralTools.checkNibble(litB);

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction11n(opcode, regA, litB));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format11n"



	// $ANTLR start "insn_format11x"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:879:1: insn_format11x : ^( I_STATEMENT_FORMAT11x INSTRUCTION_FORMAT11x REGISTER ) ;
	public final void insn_format11x() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT11x122=null;
		CommonTree REGISTER123=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:880:3: ( ^( I_STATEMENT_FORMAT11x INSTRUCTION_FORMAT11x REGISTER ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:881:5: ^( I_STATEMENT_FORMAT11x INSTRUCTION_FORMAT11x REGISTER )
			{
			match(input,I_STATEMENT_FORMAT11x,FOLLOW_I_STATEMENT_FORMAT11x_in_insn_format11x2395);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT11x122=(CommonTree)match(input,INSTRUCTION_FORMAT11x,FOLLOW_INSTRUCTION_FORMAT11x_in_insn_format11x2397);
			REGISTER123=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format11x2399);
			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT11x122!=null?INSTRUCTION_FORMAT11x122.getText():null));
			      short regA = parseRegister_byte((REGISTER123!=null?REGISTER123.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction11x(opcode, regA));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format11x"



	// $ANTLR start "insn_format12x"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:889:1: insn_format12x : ^( I_STATEMENT_FORMAT12x INSTRUCTION_FORMAT12x registerA= REGISTER registerB= REGISTER ) ;
	public final void insn_format12x() throws RecognitionException {
		CommonTree registerA=null;
		CommonTree registerB=null;
		CommonTree INSTRUCTION_FORMAT12x124=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:890:3: ( ^( I_STATEMENT_FORMAT12x INSTRUCTION_FORMAT12x registerA= REGISTER registerB= REGISTER ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:891:5: ^( I_STATEMENT_FORMAT12x INSTRUCTION_FORMAT12x registerA= REGISTER registerB= REGISTER )
			{
			match(input,I_STATEMENT_FORMAT12x,FOLLOW_I_STATEMENT_FORMAT12x_in_insn_format12x2422);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT12x124=(CommonTree)match(input,INSTRUCTION_FORMAT12x,FOLLOW_INSTRUCTION_FORMAT12x_in_insn_format12x2424);
			registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format12x2428);
			registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format12x2432);
			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT12x124!=null?INSTRUCTION_FORMAT12x124.getText():null));
			      byte regA = parseRegister_nibble((registerA!=null?registerA.getText():null));
			      byte regB = parseRegister_nibble((registerB!=null?registerB.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction12x(opcode, regA, regB));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format12x"



	// $ANTLR start "insn_format20bc"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:900:1: insn_format20bc : ^( I_STATEMENT_FORMAT20bc INSTRUCTION_FORMAT20bc verification_error_type verification_error_reference ) ;
	public final void insn_format20bc() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT20bc125=null;
		int verification_error_type126 =0;
		ImmutableReference verification_error_reference127 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:901:3: ( ^( I_STATEMENT_FORMAT20bc INSTRUCTION_FORMAT20bc verification_error_type verification_error_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:902:5: ^( I_STATEMENT_FORMAT20bc INSTRUCTION_FORMAT20bc verification_error_type verification_error_reference )
			{
			match(input,I_STATEMENT_FORMAT20bc,FOLLOW_I_STATEMENT_FORMAT20bc_in_insn_format20bc2455);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT20bc125=(CommonTree)match(input,INSTRUCTION_FORMAT20bc,FOLLOW_INSTRUCTION_FORMAT20bc_in_insn_format20bc2457);
			pushFollow(FOLLOW_verification_error_type_in_insn_format20bc2459);
			verification_error_type126=verification_error_type();
			state._fsp--;

			pushFollow(FOLLOW_verification_error_reference_in_insn_format20bc2461);
			verification_error_reference127=verification_error_reference();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT20bc125!=null?INSTRUCTION_FORMAT20bc125.getText():null));

			      int verificationError = verification_error_type126;
			      ImmutableReference referencedItem = verification_error_reference127;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction20bc(opcode, verificationError,
			              dexBuilder.internReference(referencedItem)));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format20bc"



	// $ANTLR start "insn_format20t"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:913:1: insn_format20t : ^( I_STATEMENT_FORMAT20t INSTRUCTION_FORMAT20t label_ref ) ;
	public final void insn_format20t() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT20t128=null;
		Label label_ref129 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:914:3: ( ^( I_STATEMENT_FORMAT20t INSTRUCTION_FORMAT20t label_ref ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:915:5: ^( I_STATEMENT_FORMAT20t INSTRUCTION_FORMAT20t label_ref )
			{
			match(input,I_STATEMENT_FORMAT20t,FOLLOW_I_STATEMENT_FORMAT20t_in_insn_format20t2484);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT20t128=(CommonTree)match(input,INSTRUCTION_FORMAT20t,FOLLOW_INSTRUCTION_FORMAT20t_in_insn_format20t2486);
			pushFollow(FOLLOW_label_ref_in_insn_format20t2488);
			label_ref129=label_ref();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT20t128!=null?INSTRUCTION_FORMAT20t128.getText():null));
			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction20t(opcode, label_ref129));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format20t"



	// $ANTLR start "insn_format21c_field"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:921:1: insn_format21c_field : ^( I_STATEMENT_FORMAT21c_FIELD inst= ( INSTRUCTION_FORMAT21c_FIELD | INSTRUCTION_FORMAT21c_FIELD_ODEX ) REGISTER field_reference ) ;
	public final void insn_format21c_field() throws RecognitionException {
		CommonTree inst=null;
		CommonTree REGISTER130=null;
		TreeRuleReturnScope field_reference131 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:922:3: ( ^( I_STATEMENT_FORMAT21c_FIELD inst= ( INSTRUCTION_FORMAT21c_FIELD | INSTRUCTION_FORMAT21c_FIELD_ODEX ) REGISTER field_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:923:5: ^( I_STATEMENT_FORMAT21c_FIELD inst= ( INSTRUCTION_FORMAT21c_FIELD | INSTRUCTION_FORMAT21c_FIELD_ODEX ) REGISTER field_reference )
			{
			match(input,I_STATEMENT_FORMAT21c_FIELD,FOLLOW_I_STATEMENT_FORMAT21c_FIELD_in_insn_format21c_field2511);
			match(input, Token.DOWN, null);
			inst=(CommonTree)input.LT(1);
			if ( (input.LA(1) >= INSTRUCTION_FORMAT21c_FIELD && input.LA(1) <= INSTRUCTION_FORMAT21c_FIELD_ODEX) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			REGISTER130=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21c_field2523);
			pushFollow(FOLLOW_field_reference_in_insn_format21c_field2525);
			field_reference131=field_reference();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((inst!=null?inst.getText():null));
			      short regA = parseRegister_byte((REGISTER130!=null?REGISTER130.getText():null));

			      ImmutableFieldReference fieldReference = (field_reference131!=null?((smaliTreeWalker.field_reference_return)field_reference131).fieldReference:null);

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction21c(opcode, regA,
			              dexBuilder.internFieldReference(fieldReference)));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format21c_field"



	// $ANTLR start "insn_format21c_method_handle"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:934:1: insn_format21c_method_handle : ^( I_STATEMENT_FORMAT21c_METHOD_HANDLE inst= ( INSTRUCTION_FORMAT21c_METHOD_HANDLE ) REGISTER method_handle_reference ) ;
	public final void insn_format21c_method_handle() throws RecognitionException {
		CommonTree inst=null;
		CommonTree REGISTER132=null;
		ImmutableMethodHandleReference method_handle_reference133 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:935:3: ( ^( I_STATEMENT_FORMAT21c_METHOD_HANDLE inst= ( INSTRUCTION_FORMAT21c_METHOD_HANDLE ) REGISTER method_handle_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:936:5: ^( I_STATEMENT_FORMAT21c_METHOD_HANDLE inst= ( INSTRUCTION_FORMAT21c_METHOD_HANDLE ) REGISTER method_handle_reference )
			{
			match(input,I_STATEMENT_FORMAT21c_METHOD_HANDLE,FOLLOW_I_STATEMENT_FORMAT21c_METHOD_HANDLE_in_insn_format21c_method_handle2548);
			match(input, Token.DOWN, null);
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:936:48: ( INSTRUCTION_FORMAT21c_METHOD_HANDLE )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:936:49: INSTRUCTION_FORMAT21c_METHOD_HANDLE
			{
			inst=(CommonTree)match(input,INSTRUCTION_FORMAT21c_METHOD_HANDLE,FOLLOW_INSTRUCTION_FORMAT21c_METHOD_HANDLE_in_insn_format21c_method_handle2553);
			}

			REGISTER132=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21c_method_handle2556);
			pushFollow(FOLLOW_method_handle_reference_in_insn_format21c_method_handle2558);
			method_handle_reference133=method_handle_reference();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((inst!=null?inst.getText():null));
			      short regA = parseRegister_byte((REGISTER132!=null?REGISTER132.getText():null));

			      ImmutableMethodHandleReference methodHandleReference = method_handle_reference133;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction21c(opcode, regA,
			              dexBuilder.internMethodHandle(methodHandleReference)));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format21c_method_handle"



	// $ANTLR start "insn_format21c_method_type"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:947:1: insn_format21c_method_type : ^( I_STATEMENT_FORMAT21c_METHOD_TYPE inst= ( INSTRUCTION_FORMAT21c_METHOD_TYPE ) REGISTER method_prototype ) ;
	public final void insn_format21c_method_type() throws RecognitionException {
		CommonTree inst=null;
		CommonTree REGISTER134=null;
		ImmutableMethodProtoReference method_prototype135 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:948:3: ( ^( I_STATEMENT_FORMAT21c_METHOD_TYPE inst= ( INSTRUCTION_FORMAT21c_METHOD_TYPE ) REGISTER method_prototype ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:949:5: ^( I_STATEMENT_FORMAT21c_METHOD_TYPE inst= ( INSTRUCTION_FORMAT21c_METHOD_TYPE ) REGISTER method_prototype )
			{
			match(input,I_STATEMENT_FORMAT21c_METHOD_TYPE,FOLLOW_I_STATEMENT_FORMAT21c_METHOD_TYPE_in_insn_format21c_method_type2581);
			match(input, Token.DOWN, null);
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:949:46: ( INSTRUCTION_FORMAT21c_METHOD_TYPE )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:949:47: INSTRUCTION_FORMAT21c_METHOD_TYPE
			{
			inst=(CommonTree)match(input,INSTRUCTION_FORMAT21c_METHOD_TYPE,FOLLOW_INSTRUCTION_FORMAT21c_METHOD_TYPE_in_insn_format21c_method_type2586);
			}

			REGISTER134=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21c_method_type2589);
			pushFollow(FOLLOW_method_prototype_in_insn_format21c_method_type2591);
			method_prototype135=method_prototype();
			state._fsp--;

			match(input, Token.UP, null);


			        Opcode opcode = opcodes.getOpcodeByName((inst!=null?inst.getText():null));
			        short regA = parseRegister_byte((REGISTER134!=null?REGISTER134.getText():null));

			        ImmutableMethodProtoReference methodProtoReference = method_prototype135;

			        method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction21c(opcode, regA,
			                dexBuilder.internMethodProtoReference(methodProtoReference)));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format21c_method_type"



	// $ANTLR start "insn_format21c_string"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:960:1: insn_format21c_string : ^( I_STATEMENT_FORMAT21c_STRING INSTRUCTION_FORMAT21c_STRING REGISTER string_literal ) ;
	public final void insn_format21c_string() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT21c_STRING136=null;
		CommonTree REGISTER137=null;
		String string_literal138 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:961:3: ( ^( I_STATEMENT_FORMAT21c_STRING INSTRUCTION_FORMAT21c_STRING REGISTER string_literal ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:962:5: ^( I_STATEMENT_FORMAT21c_STRING INSTRUCTION_FORMAT21c_STRING REGISTER string_literal )
			{
			match(input,I_STATEMENT_FORMAT21c_STRING,FOLLOW_I_STATEMENT_FORMAT21c_STRING_in_insn_format21c_string2614);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT21c_STRING136=(CommonTree)match(input,INSTRUCTION_FORMAT21c_STRING,FOLLOW_INSTRUCTION_FORMAT21c_STRING_in_insn_format21c_string2616);
			REGISTER137=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21c_string2618);
			pushFollow(FOLLOW_string_literal_in_insn_format21c_string2620);
			string_literal138=string_literal();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT21c_STRING136!=null?INSTRUCTION_FORMAT21c_STRING136.getText():null));
			      short regA = parseRegister_byte((REGISTER137!=null?REGISTER137.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction21c(opcode, regA,
			              dexBuilder.internStringReference(string_literal138)));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format21c_string"



	// $ANTLR start "insn_format21c_type"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:971:1: insn_format21c_type : ^( I_STATEMENT_FORMAT21c_TYPE INSTRUCTION_FORMAT21c_TYPE REGISTER nonvoid_type_descriptor ) ;
	public final void insn_format21c_type() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT21c_TYPE139=null;
		CommonTree REGISTER140=null;
		TreeRuleReturnScope nonvoid_type_descriptor141 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:972:3: ( ^( I_STATEMENT_FORMAT21c_TYPE INSTRUCTION_FORMAT21c_TYPE REGISTER nonvoid_type_descriptor ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:973:5: ^( I_STATEMENT_FORMAT21c_TYPE INSTRUCTION_FORMAT21c_TYPE REGISTER nonvoid_type_descriptor )
			{
			match(input,I_STATEMENT_FORMAT21c_TYPE,FOLLOW_I_STATEMENT_FORMAT21c_TYPE_in_insn_format21c_type2643);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT21c_TYPE139=(CommonTree)match(input,INSTRUCTION_FORMAT21c_TYPE,FOLLOW_INSTRUCTION_FORMAT21c_TYPE_in_insn_format21c_type2645);
			REGISTER140=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21c_type2647);
			pushFollow(FOLLOW_nonvoid_type_descriptor_in_insn_format21c_type2649);
			nonvoid_type_descriptor141=nonvoid_type_descriptor();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT21c_TYPE139!=null?INSTRUCTION_FORMAT21c_TYPE139.getText():null));
			      short regA = parseRegister_byte((REGISTER140!=null?REGISTER140.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction21c(opcode, regA,
			              dexBuilder.internTypeReference((nonvoid_type_descriptor141!=null?((smaliTreeWalker.nonvoid_type_descriptor_return)nonvoid_type_descriptor141).type:null))));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format21c_type"



	// $ANTLR start "insn_format21ih"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:982:1: insn_format21ih : ^( I_STATEMENT_FORMAT21ih INSTRUCTION_FORMAT21ih REGISTER fixed_32bit_literal ) ;
	public final void insn_format21ih() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT21ih142=null;
		CommonTree REGISTER143=null;
		int fixed_32bit_literal144 =0;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:983:3: ( ^( I_STATEMENT_FORMAT21ih INSTRUCTION_FORMAT21ih REGISTER fixed_32bit_literal ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:984:5: ^( I_STATEMENT_FORMAT21ih INSTRUCTION_FORMAT21ih REGISTER fixed_32bit_literal )
			{
			match(input,I_STATEMENT_FORMAT21ih,FOLLOW_I_STATEMENT_FORMAT21ih_in_insn_format21ih2672);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT21ih142=(CommonTree)match(input,INSTRUCTION_FORMAT21ih,FOLLOW_INSTRUCTION_FORMAT21ih_in_insn_format21ih2674);
			REGISTER143=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21ih2676);
			pushFollow(FOLLOW_fixed_32bit_literal_in_insn_format21ih2678);
			fixed_32bit_literal144=fixed_32bit_literal();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT21ih142!=null?INSTRUCTION_FORMAT21ih142.getText():null));
			      short regA = parseRegister_byte((REGISTER143!=null?REGISTER143.getText():null));

			      int litB = fixed_32bit_literal144;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction21ih(opcode, regA, litB));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format21ih"



	// $ANTLR start "insn_format21lh"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:994:1: insn_format21lh : ^( I_STATEMENT_FORMAT21lh INSTRUCTION_FORMAT21lh REGISTER fixed_64bit_literal ) ;
	public final void insn_format21lh() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT21lh145=null;
		CommonTree REGISTER146=null;
		long fixed_64bit_literal147 =0;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:995:3: ( ^( I_STATEMENT_FORMAT21lh INSTRUCTION_FORMAT21lh REGISTER fixed_64bit_literal ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:996:5: ^( I_STATEMENT_FORMAT21lh INSTRUCTION_FORMAT21lh REGISTER fixed_64bit_literal )
			{
			match(input,I_STATEMENT_FORMAT21lh,FOLLOW_I_STATEMENT_FORMAT21lh_in_insn_format21lh2701);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT21lh145=(CommonTree)match(input,INSTRUCTION_FORMAT21lh,FOLLOW_INSTRUCTION_FORMAT21lh_in_insn_format21lh2703);
			REGISTER146=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21lh2705);
			pushFollow(FOLLOW_fixed_64bit_literal_in_insn_format21lh2707);
			fixed_64bit_literal147=fixed_64bit_literal();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT21lh145!=null?INSTRUCTION_FORMAT21lh145.getText():null));
			      short regA = parseRegister_byte((REGISTER146!=null?REGISTER146.getText():null));

			      long litB = fixed_64bit_literal147;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction21lh(opcode, regA, litB));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format21lh"



	// $ANTLR start "insn_format21s"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1006:1: insn_format21s : ^( I_STATEMENT_FORMAT21s INSTRUCTION_FORMAT21s REGISTER short_integral_literal ) ;
	public final void insn_format21s() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT21s148=null;
		CommonTree REGISTER149=null;
		short short_integral_literal150 =0;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1007:3: ( ^( I_STATEMENT_FORMAT21s INSTRUCTION_FORMAT21s REGISTER short_integral_literal ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1008:5: ^( I_STATEMENT_FORMAT21s INSTRUCTION_FORMAT21s REGISTER short_integral_literal )
			{
			match(input,I_STATEMENT_FORMAT21s,FOLLOW_I_STATEMENT_FORMAT21s_in_insn_format21s2730);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT21s148=(CommonTree)match(input,INSTRUCTION_FORMAT21s,FOLLOW_INSTRUCTION_FORMAT21s_in_insn_format21s2732);
			REGISTER149=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21s2734);
			pushFollow(FOLLOW_short_integral_literal_in_insn_format21s2736);
			short_integral_literal150=short_integral_literal();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT21s148!=null?INSTRUCTION_FORMAT21s148.getText():null));
			      short regA = parseRegister_byte((REGISTER149!=null?REGISTER149.getText():null));

			      short litB = short_integral_literal150;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction21s(opcode, regA, litB));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format21s"



	// $ANTLR start "insn_format21t"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1018:1: insn_format21t : ^( I_STATEMENT_FORMAT21t INSTRUCTION_FORMAT21t REGISTER label_ref ) ;
	public final void insn_format21t() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT21t151=null;
		CommonTree REGISTER152=null;
		Label label_ref153 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1019:3: ( ^( I_STATEMENT_FORMAT21t INSTRUCTION_FORMAT21t REGISTER label_ref ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1020:5: ^( I_STATEMENT_FORMAT21t INSTRUCTION_FORMAT21t REGISTER label_ref )
			{
			match(input,I_STATEMENT_FORMAT21t,FOLLOW_I_STATEMENT_FORMAT21t_in_insn_format21t2759);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT21t151=(CommonTree)match(input,INSTRUCTION_FORMAT21t,FOLLOW_INSTRUCTION_FORMAT21t_in_insn_format21t2761);
			REGISTER152=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21t2763);
			pushFollow(FOLLOW_label_ref_in_insn_format21t2765);
			label_ref153=label_ref();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT21t151!=null?INSTRUCTION_FORMAT21t151.getText():null));
			      short regA = parseRegister_byte((REGISTER152!=null?REGISTER152.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction21t(opcode, regA, label_ref153));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format21t"



	// $ANTLR start "insn_format22b"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1028:1: insn_format22b : ^( I_STATEMENT_FORMAT22b INSTRUCTION_FORMAT22b registerA= REGISTER registerB= REGISTER short_integral_literal ) ;
	public final void insn_format22b() throws RecognitionException {
		CommonTree registerA=null;
		CommonTree registerB=null;
		CommonTree INSTRUCTION_FORMAT22b154=null;
		short short_integral_literal155 =0;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1029:3: ( ^( I_STATEMENT_FORMAT22b INSTRUCTION_FORMAT22b registerA= REGISTER registerB= REGISTER short_integral_literal ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1030:5: ^( I_STATEMENT_FORMAT22b INSTRUCTION_FORMAT22b registerA= REGISTER registerB= REGISTER short_integral_literal )
			{
			match(input,I_STATEMENT_FORMAT22b,FOLLOW_I_STATEMENT_FORMAT22b_in_insn_format22b2788);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT22b154=(CommonTree)match(input,INSTRUCTION_FORMAT22b,FOLLOW_INSTRUCTION_FORMAT22b_in_insn_format22b2790);
			registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22b2794);
			registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22b2798);
			pushFollow(FOLLOW_short_integral_literal_in_insn_format22b2800);
			short_integral_literal155=short_integral_literal();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT22b154!=null?INSTRUCTION_FORMAT22b154.getText():null));
			      short regA = parseRegister_byte((registerA!=null?registerA.getText():null));
			      short regB = parseRegister_byte((registerB!=null?registerB.getText():null));

			      short litC = short_integral_literal155;
			      LiteralTools.checkByte(litC);

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction22b(opcode, regA, regB, litC));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format22b"



	// $ANTLR start "insn_format22c_field"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1042:1: insn_format22c_field : ^( I_STATEMENT_FORMAT22c_FIELD inst= ( INSTRUCTION_FORMAT22c_FIELD | INSTRUCTION_FORMAT22c_FIELD_ODEX ) registerA= REGISTER registerB= REGISTER field_reference ) ;
	public final void insn_format22c_field() throws RecognitionException {
		CommonTree inst=null;
		CommonTree registerA=null;
		CommonTree registerB=null;
		TreeRuleReturnScope field_reference156 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1043:3: ( ^( I_STATEMENT_FORMAT22c_FIELD inst= ( INSTRUCTION_FORMAT22c_FIELD | INSTRUCTION_FORMAT22c_FIELD_ODEX ) registerA= REGISTER registerB= REGISTER field_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1044:5: ^( I_STATEMENT_FORMAT22c_FIELD inst= ( INSTRUCTION_FORMAT22c_FIELD | INSTRUCTION_FORMAT22c_FIELD_ODEX ) registerA= REGISTER registerB= REGISTER field_reference )
			{
			match(input,I_STATEMENT_FORMAT22c_FIELD,FOLLOW_I_STATEMENT_FORMAT22c_FIELD_in_insn_format22c_field2823);
			match(input, Token.DOWN, null);
			inst=(CommonTree)input.LT(1);
			if ( (input.LA(1) >= INSTRUCTION_FORMAT22c_FIELD && input.LA(1) <= INSTRUCTION_FORMAT22c_FIELD_ODEX) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_field2837);
			registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_field2841);
			pushFollow(FOLLOW_field_reference_in_insn_format22c_field2843);
			field_reference156=field_reference();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((inst!=null?inst.getText():null));
			      byte regA = parseRegister_nibble((registerA!=null?registerA.getText():null));
			      byte regB = parseRegister_nibble((registerB!=null?registerB.getText():null));

			      ImmutableFieldReference fieldReference = (field_reference156!=null?((smaliTreeWalker.field_reference_return)field_reference156).fieldReference:null);

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction22c(opcode, regA, regB,
			              dexBuilder.internFieldReference(fieldReference)));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format22c_field"



	// $ANTLR start "insn_format22c_type"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1056:1: insn_format22c_type : ^( I_STATEMENT_FORMAT22c_TYPE INSTRUCTION_FORMAT22c_TYPE registerA= REGISTER registerB= REGISTER nonvoid_type_descriptor ) ;
	public final void insn_format22c_type() throws RecognitionException {
		CommonTree registerA=null;
		CommonTree registerB=null;
		CommonTree INSTRUCTION_FORMAT22c_TYPE157=null;
		TreeRuleReturnScope nonvoid_type_descriptor158 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1057:3: ( ^( I_STATEMENT_FORMAT22c_TYPE INSTRUCTION_FORMAT22c_TYPE registerA= REGISTER registerB= REGISTER nonvoid_type_descriptor ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1058:5: ^( I_STATEMENT_FORMAT22c_TYPE INSTRUCTION_FORMAT22c_TYPE registerA= REGISTER registerB= REGISTER nonvoid_type_descriptor )
			{
			match(input,I_STATEMENT_FORMAT22c_TYPE,FOLLOW_I_STATEMENT_FORMAT22c_TYPE_in_insn_format22c_type2866);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT22c_TYPE157=(CommonTree)match(input,INSTRUCTION_FORMAT22c_TYPE,FOLLOW_INSTRUCTION_FORMAT22c_TYPE_in_insn_format22c_type2868);
			registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_type2872);
			registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_type2876);
			pushFollow(FOLLOW_nonvoid_type_descriptor_in_insn_format22c_type2878);
			nonvoid_type_descriptor158=nonvoid_type_descriptor();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT22c_TYPE157!=null?INSTRUCTION_FORMAT22c_TYPE157.getText():null));
			      byte regA = parseRegister_nibble((registerA!=null?registerA.getText():null));
			      byte regB = parseRegister_nibble((registerB!=null?registerB.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction22c(opcode, regA, regB,
			              dexBuilder.internTypeReference((nonvoid_type_descriptor158!=null?((smaliTreeWalker.nonvoid_type_descriptor_return)nonvoid_type_descriptor158).type:null))));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format22c_type"



	// $ANTLR start "insn_format22s"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1068:1: insn_format22s : ^( I_STATEMENT_FORMAT22s INSTRUCTION_FORMAT22s registerA= REGISTER registerB= REGISTER short_integral_literal ) ;
	public final void insn_format22s() throws RecognitionException {
		CommonTree registerA=null;
		CommonTree registerB=null;
		CommonTree INSTRUCTION_FORMAT22s159=null;
		short short_integral_literal160 =0;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1069:3: ( ^( I_STATEMENT_FORMAT22s INSTRUCTION_FORMAT22s registerA= REGISTER registerB= REGISTER short_integral_literal ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1070:5: ^( I_STATEMENT_FORMAT22s INSTRUCTION_FORMAT22s registerA= REGISTER registerB= REGISTER short_integral_literal )
			{
			match(input,I_STATEMENT_FORMAT22s,FOLLOW_I_STATEMENT_FORMAT22s_in_insn_format22s2901);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT22s159=(CommonTree)match(input,INSTRUCTION_FORMAT22s,FOLLOW_INSTRUCTION_FORMAT22s_in_insn_format22s2903);
			registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22s2907);
			registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22s2911);
			pushFollow(FOLLOW_short_integral_literal_in_insn_format22s2913);
			short_integral_literal160=short_integral_literal();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT22s159!=null?INSTRUCTION_FORMAT22s159.getText():null));
			      byte regA = parseRegister_nibble((registerA!=null?registerA.getText():null));
			      byte regB = parseRegister_nibble((registerB!=null?registerB.getText():null));

			      short litC = short_integral_literal160;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction22s(opcode, regA, regB, litC));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format22s"



	// $ANTLR start "insn_format22t"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1081:1: insn_format22t : ^( I_STATEMENT_FORMAT22t INSTRUCTION_FORMAT22t registerA= REGISTER registerB= REGISTER label_ref ) ;
	public final void insn_format22t() throws RecognitionException {
		CommonTree registerA=null;
		CommonTree registerB=null;
		CommonTree INSTRUCTION_FORMAT22t161=null;
		Label label_ref162 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1082:3: ( ^( I_STATEMENT_FORMAT22t INSTRUCTION_FORMAT22t registerA= REGISTER registerB= REGISTER label_ref ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1083:5: ^( I_STATEMENT_FORMAT22t INSTRUCTION_FORMAT22t registerA= REGISTER registerB= REGISTER label_ref )
			{
			match(input,I_STATEMENT_FORMAT22t,FOLLOW_I_STATEMENT_FORMAT22t_in_insn_format22t2936);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT22t161=(CommonTree)match(input,INSTRUCTION_FORMAT22t,FOLLOW_INSTRUCTION_FORMAT22t_in_insn_format22t2938);
			registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22t2942);
			registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22t2946);
			pushFollow(FOLLOW_label_ref_in_insn_format22t2948);
			label_ref162=label_ref();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT22t161!=null?INSTRUCTION_FORMAT22t161.getText():null));
			      byte regA = parseRegister_nibble((registerA!=null?registerA.getText():null));
			      byte regB = parseRegister_nibble((registerB!=null?registerB.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction22t(opcode, regA, regB, label_ref162));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format22t"



	// $ANTLR start "insn_format22x"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1092:1: insn_format22x : ^( I_STATEMENT_FORMAT22x INSTRUCTION_FORMAT22x registerA= REGISTER registerB= REGISTER ) ;
	public final void insn_format22x() throws RecognitionException {
		CommonTree registerA=null;
		CommonTree registerB=null;
		CommonTree INSTRUCTION_FORMAT22x163=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1093:3: ( ^( I_STATEMENT_FORMAT22x INSTRUCTION_FORMAT22x registerA= REGISTER registerB= REGISTER ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1094:5: ^( I_STATEMENT_FORMAT22x INSTRUCTION_FORMAT22x registerA= REGISTER registerB= REGISTER )
			{
			match(input,I_STATEMENT_FORMAT22x,FOLLOW_I_STATEMENT_FORMAT22x_in_insn_format22x2971);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT22x163=(CommonTree)match(input,INSTRUCTION_FORMAT22x,FOLLOW_INSTRUCTION_FORMAT22x_in_insn_format22x2973);
			registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22x2977);
			registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22x2981);
			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT22x163!=null?INSTRUCTION_FORMAT22x163.getText():null));
			      short regA = parseRegister_byte((registerA!=null?registerA.getText():null));
			      int regB = parseRegister_short((registerB!=null?registerB.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction22x(opcode, regA, regB));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format22x"



	// $ANTLR start "insn_format23x"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1103:1: insn_format23x : ^( I_STATEMENT_FORMAT23x INSTRUCTION_FORMAT23x registerA= REGISTER registerB= REGISTER registerC= REGISTER ) ;
	public final void insn_format23x() throws RecognitionException {
		CommonTree registerA=null;
		CommonTree registerB=null;
		CommonTree registerC=null;
		CommonTree INSTRUCTION_FORMAT23x164=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1104:3: ( ^( I_STATEMENT_FORMAT23x INSTRUCTION_FORMAT23x registerA= REGISTER registerB= REGISTER registerC= REGISTER ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1105:5: ^( I_STATEMENT_FORMAT23x INSTRUCTION_FORMAT23x registerA= REGISTER registerB= REGISTER registerC= REGISTER )
			{
			match(input,I_STATEMENT_FORMAT23x,FOLLOW_I_STATEMENT_FORMAT23x_in_insn_format23x3004);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT23x164=(CommonTree)match(input,INSTRUCTION_FORMAT23x,FOLLOW_INSTRUCTION_FORMAT23x_in_insn_format23x3006);
			registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format23x3010);
			registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format23x3014);
			registerC=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format23x3018);
			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT23x164!=null?INSTRUCTION_FORMAT23x164.getText():null));
			      short regA = parseRegister_byte((registerA!=null?registerA.getText():null));
			      short regB = parseRegister_byte((registerB!=null?registerB.getText():null));
			      short regC = parseRegister_byte((registerC!=null?registerC.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction23x(opcode, regA, regB, regC));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format23x"



	// $ANTLR start "insn_format30t"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1115:1: insn_format30t : ^( I_STATEMENT_FORMAT30t INSTRUCTION_FORMAT30t label_ref ) ;
	public final void insn_format30t() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT30t165=null;
		Label label_ref166 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1116:3: ( ^( I_STATEMENT_FORMAT30t INSTRUCTION_FORMAT30t label_ref ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1117:5: ^( I_STATEMENT_FORMAT30t INSTRUCTION_FORMAT30t label_ref )
			{
			match(input,I_STATEMENT_FORMAT30t,FOLLOW_I_STATEMENT_FORMAT30t_in_insn_format30t3041);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT30t165=(CommonTree)match(input,INSTRUCTION_FORMAT30t,FOLLOW_INSTRUCTION_FORMAT30t_in_insn_format30t3043);
			pushFollow(FOLLOW_label_ref_in_insn_format30t3045);
			label_ref166=label_ref();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT30t165!=null?INSTRUCTION_FORMAT30t165.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction30t(opcode, label_ref166));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format30t"



	// $ANTLR start "insn_format31c"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1124:1: insn_format31c : ^( I_STATEMENT_FORMAT31c INSTRUCTION_FORMAT31c REGISTER string_literal ) ;
	public final void insn_format31c() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT31c167=null;
		CommonTree REGISTER168=null;
		String string_literal169 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1125:3: ( ^( I_STATEMENT_FORMAT31c INSTRUCTION_FORMAT31c REGISTER string_literal ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1126:5: ^( I_STATEMENT_FORMAT31c INSTRUCTION_FORMAT31c REGISTER string_literal )
			{
			match(input,I_STATEMENT_FORMAT31c,FOLLOW_I_STATEMENT_FORMAT31c_in_insn_format31c3068);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT31c167=(CommonTree)match(input,INSTRUCTION_FORMAT31c,FOLLOW_INSTRUCTION_FORMAT31c_in_insn_format31c3070);
			REGISTER168=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format31c3072);
			pushFollow(FOLLOW_string_literal_in_insn_format31c3074);
			string_literal169=string_literal();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT31c167!=null?INSTRUCTION_FORMAT31c167.getText():null));
			      short regA = parseRegister_byte((REGISTER168!=null?REGISTER168.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction31c(opcode, regA,
			              dexBuilder.internStringReference(string_literal169)));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format31c"



	// $ANTLR start "insn_format31i"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1135:1: insn_format31i : ^( I_STATEMENT_FORMAT31i INSTRUCTION_FORMAT31i REGISTER fixed_32bit_literal ) ;
	public final void insn_format31i() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT31i170=null;
		CommonTree REGISTER171=null;
		int fixed_32bit_literal172 =0;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1136:3: ( ^( I_STATEMENT_FORMAT31i INSTRUCTION_FORMAT31i REGISTER fixed_32bit_literal ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1137:5: ^( I_STATEMENT_FORMAT31i INSTRUCTION_FORMAT31i REGISTER fixed_32bit_literal )
			{
			match(input,I_STATEMENT_FORMAT31i,FOLLOW_I_STATEMENT_FORMAT31i_in_insn_format31i3097);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT31i170=(CommonTree)match(input,INSTRUCTION_FORMAT31i,FOLLOW_INSTRUCTION_FORMAT31i_in_insn_format31i3099);
			REGISTER171=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format31i3101);
			pushFollow(FOLLOW_fixed_32bit_literal_in_insn_format31i3103);
			fixed_32bit_literal172=fixed_32bit_literal();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT31i170!=null?INSTRUCTION_FORMAT31i170.getText():null));
			      short regA = parseRegister_byte((REGISTER171!=null?REGISTER171.getText():null));

			      int litB = fixed_32bit_literal172;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction31i(opcode, regA, litB));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format31i"



	// $ANTLR start "insn_format31t"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1147:1: insn_format31t : ^( I_STATEMENT_FORMAT31t INSTRUCTION_FORMAT31t REGISTER label_ref ) ;
	public final void insn_format31t() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT31t173=null;
		CommonTree REGISTER174=null;
		Label label_ref175 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1148:3: ( ^( I_STATEMENT_FORMAT31t INSTRUCTION_FORMAT31t REGISTER label_ref ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1149:5: ^( I_STATEMENT_FORMAT31t INSTRUCTION_FORMAT31t REGISTER label_ref )
			{
			match(input,I_STATEMENT_FORMAT31t,FOLLOW_I_STATEMENT_FORMAT31t_in_insn_format31t3126);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT31t173=(CommonTree)match(input,INSTRUCTION_FORMAT31t,FOLLOW_INSTRUCTION_FORMAT31t_in_insn_format31t3128);
			REGISTER174=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format31t3130);
			pushFollow(FOLLOW_label_ref_in_insn_format31t3132);
			label_ref175=label_ref();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT31t173!=null?INSTRUCTION_FORMAT31t173.getText():null));

			      short regA = parseRegister_byte((REGISTER174!=null?REGISTER174.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction31t(opcode, regA, label_ref175));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format31t"



	// $ANTLR start "insn_format32x"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1158:1: insn_format32x : ^( I_STATEMENT_FORMAT32x INSTRUCTION_FORMAT32x registerA= REGISTER registerB= REGISTER ) ;
	public final void insn_format32x() throws RecognitionException {
		CommonTree registerA=null;
		CommonTree registerB=null;
		CommonTree INSTRUCTION_FORMAT32x176=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1159:3: ( ^( I_STATEMENT_FORMAT32x INSTRUCTION_FORMAT32x registerA= REGISTER registerB= REGISTER ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1160:5: ^( I_STATEMENT_FORMAT32x INSTRUCTION_FORMAT32x registerA= REGISTER registerB= REGISTER )
			{
			match(input,I_STATEMENT_FORMAT32x,FOLLOW_I_STATEMENT_FORMAT32x_in_insn_format32x3155);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT32x176=(CommonTree)match(input,INSTRUCTION_FORMAT32x,FOLLOW_INSTRUCTION_FORMAT32x_in_insn_format32x3157);
			registerA=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format32x3161);
			registerB=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format32x3165);
			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT32x176!=null?INSTRUCTION_FORMAT32x176.getText():null));
			      int regA = parseRegister_short((registerA!=null?registerA.getText():null));
			      int regB = parseRegister_short((registerB!=null?registerB.getText():null));

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction32x(opcode, regA, regB));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format32x"



	// $ANTLR start "insn_format35c_call_site"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1169:1: insn_format35c_call_site : ^( I_STATEMENT_FORMAT35c_CALL_SITE INSTRUCTION_FORMAT35c_CALL_SITE register_list call_site_reference ) ;
	public final void insn_format35c_call_site() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT35c_CALL_SITE177=null;
		TreeRuleReturnScope register_list178 =null;
		ImmutableCallSiteReference call_site_reference179 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1170:3: ( ^( I_STATEMENT_FORMAT35c_CALL_SITE INSTRUCTION_FORMAT35c_CALL_SITE register_list call_site_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1172:5: ^( I_STATEMENT_FORMAT35c_CALL_SITE INSTRUCTION_FORMAT35c_CALL_SITE register_list call_site_reference )
			{
			match(input,I_STATEMENT_FORMAT35c_CALL_SITE,FOLLOW_I_STATEMENT_FORMAT35c_CALL_SITE_in_insn_format35c_call_site3193);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT35c_CALL_SITE177=(CommonTree)match(input,INSTRUCTION_FORMAT35c_CALL_SITE,FOLLOW_INSTRUCTION_FORMAT35c_CALL_SITE_in_insn_format35c_call_site3195);
			pushFollow(FOLLOW_register_list_in_insn_format35c_call_site3197);
			register_list178=register_list();
			state._fsp--;

			pushFollow(FOLLOW_call_site_reference_in_insn_format35c_call_site3199);
			call_site_reference179=call_site_reference();
			state._fsp--;

			match(input, Token.UP, null);


			        Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT35c_CALL_SITE177!=null?INSTRUCTION_FORMAT35c_CALL_SITE177.getText():null));

			        //this depends on the fact that register_list returns a byte[5]
			        byte[] registers = (register_list178!=null?((smaliTreeWalker.register_list_return)register_list178).registers:null);
			        byte registerCount = (register_list178!=null?((smaliTreeWalker.register_list_return)register_list178).registerCount:0);

			        ImmutableCallSiteReference callSiteReference = call_site_reference179;

			        method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction35c(opcode, registerCount, registers[0],
			                registers[1], registers[2], registers[3], registers[4], dexBuilder.internCallSite(callSiteReference)));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format35c_call_site"



	// $ANTLR start "insn_format35c_method"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1186:1: insn_format35c_method : ^( I_STATEMENT_FORMAT35c_METHOD INSTRUCTION_FORMAT35c_METHOD register_list method_reference ) ;
	public final void insn_format35c_method() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT35c_METHOD180=null;
		TreeRuleReturnScope register_list181 =null;
		ImmutableMethodReference method_reference182 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1187:3: ( ^( I_STATEMENT_FORMAT35c_METHOD INSTRUCTION_FORMAT35c_METHOD register_list method_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1188:5: ^( I_STATEMENT_FORMAT35c_METHOD INSTRUCTION_FORMAT35c_METHOD register_list method_reference )
			{
			match(input,I_STATEMENT_FORMAT35c_METHOD,FOLLOW_I_STATEMENT_FORMAT35c_METHOD_in_insn_format35c_method3222);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT35c_METHOD180=(CommonTree)match(input,INSTRUCTION_FORMAT35c_METHOD,FOLLOW_INSTRUCTION_FORMAT35c_METHOD_in_insn_format35c_method3224);
			pushFollow(FOLLOW_register_list_in_insn_format35c_method3226);
			register_list181=register_list();
			state._fsp--;

			pushFollow(FOLLOW_method_reference_in_insn_format35c_method3228);
			method_reference182=method_reference();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT35c_METHOD180!=null?INSTRUCTION_FORMAT35c_METHOD180.getText():null));

			      //this depends on the fact that register_list returns a byte[5]
			      byte[] registers = (register_list181!=null?((smaliTreeWalker.register_list_return)register_list181).registers:null);
			      byte registerCount = (register_list181!=null?((smaliTreeWalker.register_list_return)register_list181).registerCount:0);

			      ImmutableMethodReference methodReference = method_reference182;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction35c(opcode, registerCount, registers[0], registers[1],
			              registers[2], registers[3], registers[4], dexBuilder.internMethodReference(methodReference)));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format35c_method"



	// $ANTLR start "insn_format35c_type"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1202:1: insn_format35c_type : ^( I_STATEMENT_FORMAT35c_TYPE INSTRUCTION_FORMAT35c_TYPE register_list nonvoid_type_descriptor ) ;
	public final void insn_format35c_type() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT35c_TYPE183=null;
		TreeRuleReturnScope register_list184 =null;
		TreeRuleReturnScope nonvoid_type_descriptor185 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1203:3: ( ^( I_STATEMENT_FORMAT35c_TYPE INSTRUCTION_FORMAT35c_TYPE register_list nonvoid_type_descriptor ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1204:5: ^( I_STATEMENT_FORMAT35c_TYPE INSTRUCTION_FORMAT35c_TYPE register_list nonvoid_type_descriptor )
			{
			match(input,I_STATEMENT_FORMAT35c_TYPE,FOLLOW_I_STATEMENT_FORMAT35c_TYPE_in_insn_format35c_type3251);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT35c_TYPE183=(CommonTree)match(input,INSTRUCTION_FORMAT35c_TYPE,FOLLOW_INSTRUCTION_FORMAT35c_TYPE_in_insn_format35c_type3253);
			pushFollow(FOLLOW_register_list_in_insn_format35c_type3255);
			register_list184=register_list();
			state._fsp--;

			pushFollow(FOLLOW_nonvoid_type_descriptor_in_insn_format35c_type3257);
			nonvoid_type_descriptor185=nonvoid_type_descriptor();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT35c_TYPE183!=null?INSTRUCTION_FORMAT35c_TYPE183.getText():null));

			      //this depends on the fact that register_list returns a byte[5]
			      byte[] registers = (register_list184!=null?((smaliTreeWalker.register_list_return)register_list184).registers:null);
			      byte registerCount = (register_list184!=null?((smaliTreeWalker.register_list_return)register_list184).registerCount:0);

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction35c(opcode, registerCount, registers[0], registers[1],
			              registers[2], registers[3], registers[4], dexBuilder.internTypeReference((nonvoid_type_descriptor185!=null?((smaliTreeWalker.nonvoid_type_descriptor_return)nonvoid_type_descriptor185).type:null))));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format35c_type"



	// $ANTLR start "insn_format3rc_call_site"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1216:1: insn_format3rc_call_site : ^( I_STATEMENT_FORMAT3rc_CALL_SITE INSTRUCTION_FORMAT3rc_CALL_SITE register_range call_site_reference ) ;
	public final void insn_format3rc_call_site() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT3rc_CALL_SITE186=null;
		TreeRuleReturnScope register_range187 =null;
		ImmutableCallSiteReference call_site_reference188 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1217:3: ( ^( I_STATEMENT_FORMAT3rc_CALL_SITE INSTRUCTION_FORMAT3rc_CALL_SITE register_range call_site_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1219:5: ^( I_STATEMENT_FORMAT3rc_CALL_SITE INSTRUCTION_FORMAT3rc_CALL_SITE register_range call_site_reference )
			{
			match(input,I_STATEMENT_FORMAT3rc_CALL_SITE,FOLLOW_I_STATEMENT_FORMAT3rc_CALL_SITE_in_insn_format3rc_call_site3285);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT3rc_CALL_SITE186=(CommonTree)match(input,INSTRUCTION_FORMAT3rc_CALL_SITE,FOLLOW_INSTRUCTION_FORMAT3rc_CALL_SITE_in_insn_format3rc_call_site3287);
			pushFollow(FOLLOW_register_range_in_insn_format3rc_call_site3289);
			register_range187=register_range();
			state._fsp--;

			pushFollow(FOLLOW_call_site_reference_in_insn_format3rc_call_site3291);
			call_site_reference188=call_site_reference();
			state._fsp--;

			match(input, Token.UP, null);


			        Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT3rc_CALL_SITE186!=null?INSTRUCTION_FORMAT3rc_CALL_SITE186.getText():null));
			        int startRegister = (register_range187!=null?((smaliTreeWalker.register_range_return)register_range187).startRegister:0);
			        int endRegister = (register_range187!=null?((smaliTreeWalker.register_range_return)register_range187).endRegister:0);

			        int registerCount = endRegister - startRegister + 1;

			        ImmutableCallSiteReference callSiteReference = call_site_reference188;

			        method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction3rc(opcode, startRegister, registerCount,
			                dexBuilder.internCallSite(callSiteReference)));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format3rc_call_site"



	// $ANTLR start "insn_format3rc_method"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1233:1: insn_format3rc_method : ^( I_STATEMENT_FORMAT3rc_METHOD INSTRUCTION_FORMAT3rc_METHOD register_range method_reference ) ;
	public final void insn_format3rc_method() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT3rc_METHOD189=null;
		TreeRuleReturnScope register_range190 =null;
		ImmutableMethodReference method_reference191 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1234:3: ( ^( I_STATEMENT_FORMAT3rc_METHOD INSTRUCTION_FORMAT3rc_METHOD register_range method_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1235:5: ^( I_STATEMENT_FORMAT3rc_METHOD INSTRUCTION_FORMAT3rc_METHOD register_range method_reference )
			{
			match(input,I_STATEMENT_FORMAT3rc_METHOD,FOLLOW_I_STATEMENT_FORMAT3rc_METHOD_in_insn_format3rc_method3314);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT3rc_METHOD189=(CommonTree)match(input,INSTRUCTION_FORMAT3rc_METHOD,FOLLOW_INSTRUCTION_FORMAT3rc_METHOD_in_insn_format3rc_method3316);
			pushFollow(FOLLOW_register_range_in_insn_format3rc_method3318);
			register_range190=register_range();
			state._fsp--;

			pushFollow(FOLLOW_method_reference_in_insn_format3rc_method3320);
			method_reference191=method_reference();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT3rc_METHOD189!=null?INSTRUCTION_FORMAT3rc_METHOD189.getText():null));
			      int startRegister = (register_range190!=null?((smaliTreeWalker.register_range_return)register_range190).startRegister:0);
			      int endRegister = (register_range190!=null?((smaliTreeWalker.register_range_return)register_range190).endRegister:0);

			      int registerCount = endRegister-startRegister+1;

			      ImmutableMethodReference methodReference = method_reference191;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction3rc(opcode, startRegister, registerCount,
			              dexBuilder.internMethodReference(methodReference)));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format3rc_method"



	// $ANTLR start "insn_format3rc_type"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1249:1: insn_format3rc_type : ^( I_STATEMENT_FORMAT3rc_TYPE INSTRUCTION_FORMAT3rc_TYPE register_range nonvoid_type_descriptor ) ;
	public final void insn_format3rc_type() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT3rc_TYPE192=null;
		TreeRuleReturnScope register_range193 =null;
		TreeRuleReturnScope nonvoid_type_descriptor194 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1250:3: ( ^( I_STATEMENT_FORMAT3rc_TYPE INSTRUCTION_FORMAT3rc_TYPE register_range nonvoid_type_descriptor ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1251:5: ^( I_STATEMENT_FORMAT3rc_TYPE INSTRUCTION_FORMAT3rc_TYPE register_range nonvoid_type_descriptor )
			{
			match(input,I_STATEMENT_FORMAT3rc_TYPE,FOLLOW_I_STATEMENT_FORMAT3rc_TYPE_in_insn_format3rc_type3343);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT3rc_TYPE192=(CommonTree)match(input,INSTRUCTION_FORMAT3rc_TYPE,FOLLOW_INSTRUCTION_FORMAT3rc_TYPE_in_insn_format3rc_type3345);
			pushFollow(FOLLOW_register_range_in_insn_format3rc_type3347);
			register_range193=register_range();
			state._fsp--;

			pushFollow(FOLLOW_nonvoid_type_descriptor_in_insn_format3rc_type3349);
			nonvoid_type_descriptor194=nonvoid_type_descriptor();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT3rc_TYPE192!=null?INSTRUCTION_FORMAT3rc_TYPE192.getText():null));
			      int startRegister = (register_range193!=null?((smaliTreeWalker.register_range_return)register_range193).startRegister:0);
			      int endRegister = (register_range193!=null?((smaliTreeWalker.register_range_return)register_range193).endRegister:0);

			      int registerCount = endRegister-startRegister+1;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction3rc(opcode, startRegister, registerCount,
			              dexBuilder.internTypeReference((nonvoid_type_descriptor194!=null?((smaliTreeWalker.nonvoid_type_descriptor_return)nonvoid_type_descriptor194).type:null))));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format3rc_type"



	// $ANTLR start "insn_format45cc_method"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1263:1: insn_format45cc_method : ^( I_STATEMENT_FORMAT45cc_METHOD INSTRUCTION_FORMAT45cc_METHOD register_list method_reference method_prototype ) ;
	public final void insn_format45cc_method() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT45cc_METHOD195=null;
		TreeRuleReturnScope register_list196 =null;
		ImmutableMethodReference method_reference197 =null;
		ImmutableMethodProtoReference method_prototype198 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1264:3: ( ^( I_STATEMENT_FORMAT45cc_METHOD INSTRUCTION_FORMAT45cc_METHOD register_list method_reference method_prototype ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1265:5: ^( I_STATEMENT_FORMAT45cc_METHOD INSTRUCTION_FORMAT45cc_METHOD register_list method_reference method_prototype )
			{
			match(input,I_STATEMENT_FORMAT45cc_METHOD,FOLLOW_I_STATEMENT_FORMAT45cc_METHOD_in_insn_format45cc_method3372);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT45cc_METHOD195=(CommonTree)match(input,INSTRUCTION_FORMAT45cc_METHOD,FOLLOW_INSTRUCTION_FORMAT45cc_METHOD_in_insn_format45cc_method3374);
			pushFollow(FOLLOW_register_list_in_insn_format45cc_method3376);
			register_list196=register_list();
			state._fsp--;

			pushFollow(FOLLOW_method_reference_in_insn_format45cc_method3378);
			method_reference197=method_reference();
			state._fsp--;

			pushFollow(FOLLOW_method_prototype_in_insn_format45cc_method3380);
			method_prototype198=method_prototype();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT45cc_METHOD195!=null?INSTRUCTION_FORMAT45cc_METHOD195.getText():null));

			      //this depends on the fact that register_list returns a byte[5]
			      byte[] registers = (register_list196!=null?((smaliTreeWalker.register_list_return)register_list196).registers:null);
			      byte registerCount = (register_list196!=null?((smaliTreeWalker.register_list_return)register_list196).registerCount:0);

			      ImmutableMethodReference methodReference = method_reference197;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction45cc(opcode, registerCount, registers[0], registers[1],
			              registers[2], registers[3], registers[4],
			              dexBuilder.internMethodReference(methodReference),
			              dexBuilder.internMethodProtoReference(method_prototype198)));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format45cc_method"



	// $ANTLR start "insn_format4rcc_method"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1281:1: insn_format4rcc_method : ^( I_STATEMENT_FORMAT4rcc_METHOD INSTRUCTION_FORMAT4rcc_METHOD register_range method_reference method_prototype ) ;
	public final void insn_format4rcc_method() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT4rcc_METHOD199=null;
		TreeRuleReturnScope register_range200 =null;
		ImmutableMethodReference method_reference201 =null;
		ImmutableMethodProtoReference method_prototype202 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1282:3: ( ^( I_STATEMENT_FORMAT4rcc_METHOD INSTRUCTION_FORMAT4rcc_METHOD register_range method_reference method_prototype ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1283:5: ^( I_STATEMENT_FORMAT4rcc_METHOD INSTRUCTION_FORMAT4rcc_METHOD register_range method_reference method_prototype )
			{
			match(input,I_STATEMENT_FORMAT4rcc_METHOD,FOLLOW_I_STATEMENT_FORMAT4rcc_METHOD_in_insn_format4rcc_method3403);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT4rcc_METHOD199=(CommonTree)match(input,INSTRUCTION_FORMAT4rcc_METHOD,FOLLOW_INSTRUCTION_FORMAT4rcc_METHOD_in_insn_format4rcc_method3405);
			pushFollow(FOLLOW_register_range_in_insn_format4rcc_method3407);
			register_range200=register_range();
			state._fsp--;

			pushFollow(FOLLOW_method_reference_in_insn_format4rcc_method3409);
			method_reference201=method_reference();
			state._fsp--;

			pushFollow(FOLLOW_method_prototype_in_insn_format4rcc_method3411);
			method_prototype202=method_prototype();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT4rcc_METHOD199!=null?INSTRUCTION_FORMAT4rcc_METHOD199.getText():null));
			      int startRegister = (register_range200!=null?((smaliTreeWalker.register_range_return)register_range200).startRegister:0);
			      int endRegister = (register_range200!=null?((smaliTreeWalker.register_range_return)register_range200).endRegister:0);

			      int registerCount = endRegister-startRegister+1;

			      ImmutableMethodReference methodReference = method_reference201;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction4rcc(opcode, startRegister, registerCount,
			              dexBuilder.internMethodReference(methodReference),
			              dexBuilder.internMethodProtoReference(method_prototype202)));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format4rcc_method"



	// $ANTLR start "insn_format51l_type"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1298:1: insn_format51l_type : ^( I_STATEMENT_FORMAT51l INSTRUCTION_FORMAT51l REGISTER fixed_64bit_literal ) ;
	public final void insn_format51l_type() throws RecognitionException {
		CommonTree INSTRUCTION_FORMAT51l203=null;
		CommonTree REGISTER204=null;
		long fixed_64bit_literal205 =0;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1299:3: ( ^( I_STATEMENT_FORMAT51l INSTRUCTION_FORMAT51l REGISTER fixed_64bit_literal ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1300:5: ^( I_STATEMENT_FORMAT51l INSTRUCTION_FORMAT51l REGISTER fixed_64bit_literal )
			{
			match(input,I_STATEMENT_FORMAT51l,FOLLOW_I_STATEMENT_FORMAT51l_in_insn_format51l_type3434);
			match(input, Token.DOWN, null);
			INSTRUCTION_FORMAT51l203=(CommonTree)match(input,INSTRUCTION_FORMAT51l,FOLLOW_INSTRUCTION_FORMAT51l_in_insn_format51l_type3436);
			REGISTER204=(CommonTree)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format51l_type3438);
			pushFollow(FOLLOW_fixed_64bit_literal_in_insn_format51l_type3440);
			fixed_64bit_literal205=fixed_64bit_literal();
			state._fsp--;

			match(input, Token.UP, null);


			      Opcode opcode = opcodes.getOpcodeByName((INSTRUCTION_FORMAT51l203!=null?INSTRUCTION_FORMAT51l203.getText():null));
			      short regA = parseRegister_byte((REGISTER204!=null?REGISTER204.getText():null));

			      long litB = fixed_64bit_literal205;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderInstruction51l(opcode, regA, litB));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_format51l_type"



	// $ANTLR start "insn_array_data_directive"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1310:1: insn_array_data_directive : ^( I_STATEMENT_ARRAY_DATA ^( I_ARRAY_ELEMENT_SIZE short_integral_literal ) array_elements ) ;
	public final void insn_array_data_directive() throws RecognitionException {
		short short_integral_literal206 =0;
		List<Number> array_elements207 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1311:3: ( ^( I_STATEMENT_ARRAY_DATA ^( I_ARRAY_ELEMENT_SIZE short_integral_literal ) array_elements ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1312:5: ^( I_STATEMENT_ARRAY_DATA ^( I_ARRAY_ELEMENT_SIZE short_integral_literal ) array_elements )
			{
			match(input,I_STATEMENT_ARRAY_DATA,FOLLOW_I_STATEMENT_ARRAY_DATA_in_insn_array_data_directive3463);
			match(input, Token.DOWN, null);
			match(input,I_ARRAY_ELEMENT_SIZE,FOLLOW_I_ARRAY_ELEMENT_SIZE_in_insn_array_data_directive3466);
			match(input, Token.DOWN, null);
			pushFollow(FOLLOW_short_integral_literal_in_insn_array_data_directive3468);
			short_integral_literal206=short_integral_literal();
			state._fsp--;

			match(input, Token.UP, null);

			pushFollow(FOLLOW_array_elements_in_insn_array_data_directive3471);
			array_elements207=array_elements();
			state._fsp--;

			match(input, Token.UP, null);


			      int elementWidth = short_integral_literal206;
			      List<Number> elements = array_elements207;

			      method_stack.peek().methodBuilder.addInstruction(new BuilderArrayPayload(elementWidth, array_elements207));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_array_data_directive"



	// $ANTLR start "insn_packed_switch_directive"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1320:1: insn_packed_switch_directive : ^( I_STATEMENT_PACKED_SWITCH ^( I_PACKED_SWITCH_START_KEY fixed_32bit_literal ) packed_switch_elements ) ;
	public final void insn_packed_switch_directive() throws RecognitionException {
		int fixed_32bit_literal208 =0;
		List<Label> packed_switch_elements209 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1321:3: ( ^( I_STATEMENT_PACKED_SWITCH ^( I_PACKED_SWITCH_START_KEY fixed_32bit_literal ) packed_switch_elements ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1322:5: ^( I_STATEMENT_PACKED_SWITCH ^( I_PACKED_SWITCH_START_KEY fixed_32bit_literal ) packed_switch_elements )
			{
			match(input,I_STATEMENT_PACKED_SWITCH,FOLLOW_I_STATEMENT_PACKED_SWITCH_in_insn_packed_switch_directive3493);
			match(input, Token.DOWN, null);
			match(input,I_PACKED_SWITCH_START_KEY,FOLLOW_I_PACKED_SWITCH_START_KEY_in_insn_packed_switch_directive3496);
			match(input, Token.DOWN, null);
			pushFollow(FOLLOW_fixed_32bit_literal_in_insn_packed_switch_directive3498);
			fixed_32bit_literal208=fixed_32bit_literal();
			state._fsp--;

			match(input, Token.UP, null);

			pushFollow(FOLLOW_packed_switch_elements_in_insn_packed_switch_directive3501);
			packed_switch_elements209=packed_switch_elements();
			state._fsp--;

			match(input, Token.UP, null);


			        int startKey = fixed_32bit_literal208;
			        method_stack.peek().methodBuilder.addInstruction(new BuilderPackedSwitchPayload(startKey,
			            packed_switch_elements209));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_packed_switch_directive"



	// $ANTLR start "insn_sparse_switch_directive"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1329:1: insn_sparse_switch_directive : ^( I_STATEMENT_SPARSE_SWITCH sparse_switch_elements ) ;
	public final void insn_sparse_switch_directive() throws RecognitionException {
		List<SwitchLabelElement> sparse_switch_elements210 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1330:3: ( ^( I_STATEMENT_SPARSE_SWITCH sparse_switch_elements ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1331:5: ^( I_STATEMENT_SPARSE_SWITCH sparse_switch_elements )
			{
			match(input,I_STATEMENT_SPARSE_SWITCH,FOLLOW_I_STATEMENT_SPARSE_SWITCH_in_insn_sparse_switch_directive3525);
			match(input, Token.DOWN, null);
			pushFollow(FOLLOW_sparse_switch_elements_in_insn_sparse_switch_directive3527);
			sparse_switch_elements210=sparse_switch_elements();
			state._fsp--;

			match(input, Token.UP, null);


			      method_stack.peek().methodBuilder.addInstruction(new BuilderSparseSwitchPayload(sparse_switch_elements210));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "insn_sparse_switch_directive"



	// $ANTLR start "array_descriptor"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1336:1: array_descriptor returns [String type] : ARRAY_TYPE_PREFIX ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR ) ;
	public final String array_descriptor() throws RecognitionException {
		String type = null;


		CommonTree ARRAY_TYPE_PREFIX211=null;
		CommonTree PRIMITIVE_TYPE212=null;
		CommonTree CLASS_DESCRIPTOR213=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1337:3: ( ARRAY_TYPE_PREFIX ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1337:5: ARRAY_TYPE_PREFIX ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR )
			{
			ARRAY_TYPE_PREFIX211=(CommonTree)match(input,ARRAY_TYPE_PREFIX,FOLLOW_ARRAY_TYPE_PREFIX_in_array_descriptor3548);
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1337:23: ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR )
			int alt40=2;
			int LA40_0 = input.LA(1);
			if ( (LA40_0==PRIMITIVE_TYPE) ) {
				alt40=1;
			}
			else if ( (LA40_0==CLASS_DESCRIPTOR) ) {
				alt40=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 40, 0, input);
				throw nvae;
			}

			switch (alt40) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1337:25: PRIMITIVE_TYPE
					{
					PRIMITIVE_TYPE212=(CommonTree)match(input,PRIMITIVE_TYPE,FOLLOW_PRIMITIVE_TYPE_in_array_descriptor3552);
					 type = (ARRAY_TYPE_PREFIX211!=null?ARRAY_TYPE_PREFIX211.getText():null) + (PRIMITIVE_TYPE212!=null?PRIMITIVE_TYPE212.getText():null);
					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1338:25: CLASS_DESCRIPTOR
					{
					CLASS_DESCRIPTOR213=(CommonTree)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_array_descriptor3580);
					 type = (ARRAY_TYPE_PREFIX211!=null?ARRAY_TYPE_PREFIX211.getText():null) + (CLASS_DESCRIPTOR213!=null?CLASS_DESCRIPTOR213.getText():null);
					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return type;
	}
	// $ANTLR end "array_descriptor"


	public static class nonvoid_type_descriptor_return extends TreeRuleReturnScope {
		public String type;
	};


	// $ANTLR start "nonvoid_type_descriptor"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1340:1: nonvoid_type_descriptor returns [String type] : ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR | array_descriptor ) ;
	public final smaliTreeWalker.nonvoid_type_descriptor_return nonvoid_type_descriptor() throws RecognitionException {
		smaliTreeWalker.nonvoid_type_descriptor_return retval = new smaliTreeWalker.nonvoid_type_descriptor_return();
		retval.start = input.LT(1);

		String array_descriptor214 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1341:3: ( ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR | array_descriptor ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1341:5: ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR | array_descriptor )
			{
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1341:5: ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR | array_descriptor )
			int alt41=3;
			switch ( input.LA(1) ) {
			case PRIMITIVE_TYPE:
				{
				alt41=1;
				}
				break;
			case CLASS_DESCRIPTOR:
				{
				alt41=2;
				}
				break;
			case ARRAY_TYPE_PREFIX:
				{
				alt41=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 41, 0, input);
				throw nvae;
			}
			switch (alt41) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1341:6: PRIMITIVE_TYPE
					{
					match(input,PRIMITIVE_TYPE,FOLLOW_PRIMITIVE_TYPE_in_nonvoid_type_descriptor3598);
					 retval.type = input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(retval.start),input.getTreeAdaptor().getTokenStopIndex(retval.start));
					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1342:5: CLASS_DESCRIPTOR
					{
					match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_nonvoid_type_descriptor3606);
					 retval.type = input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(retval.start),input.getTreeAdaptor().getTokenStopIndex(retval.start));
					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1343:5: array_descriptor
					{
					pushFollow(FOLLOW_array_descriptor_in_nonvoid_type_descriptor3614);
					array_descriptor214=array_descriptor();
					state._fsp--;

					 retval.type = array_descriptor214;
					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "nonvoid_type_descriptor"


	public static class reference_type_descriptor_return extends TreeRuleReturnScope {
		public String type;
	};


	// $ANTLR start "reference_type_descriptor"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1346:1: reference_type_descriptor returns [String type] : ( CLASS_DESCRIPTOR | array_descriptor ) ;
	public final smaliTreeWalker.reference_type_descriptor_return reference_type_descriptor() throws RecognitionException {
		smaliTreeWalker.reference_type_descriptor_return retval = new smaliTreeWalker.reference_type_descriptor_return();
		retval.start = input.LT(1);

		String array_descriptor215 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1347:3: ( ( CLASS_DESCRIPTOR | array_descriptor ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1347:5: ( CLASS_DESCRIPTOR | array_descriptor )
			{
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1347:5: ( CLASS_DESCRIPTOR | array_descriptor )
			int alt42=2;
			int LA42_0 = input.LA(1);
			if ( (LA42_0==CLASS_DESCRIPTOR) ) {
				alt42=1;
			}
			else if ( (LA42_0==ARRAY_TYPE_PREFIX) ) {
				alt42=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 42, 0, input);
				throw nvae;
			}

			switch (alt42) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1347:6: CLASS_DESCRIPTOR
					{
					match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_reference_type_descriptor3635);
					 retval.type = input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(retval.start),input.getTreeAdaptor().getTokenStopIndex(retval.start));
					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1348:5: array_descriptor
					{
					pushFollow(FOLLOW_array_descriptor_in_reference_type_descriptor3643);
					array_descriptor215=array_descriptor();
					state._fsp--;

					 retval.type = array_descriptor215;
					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "reference_type_descriptor"



	// $ANTLR start "type_descriptor"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1351:1: type_descriptor returns [String type] : ( VOID_TYPE | nonvoid_type_descriptor );
	public final String type_descriptor() throws RecognitionException {
		String type = null;


		TreeRuleReturnScope nonvoid_type_descriptor216 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1352:3: ( VOID_TYPE | nonvoid_type_descriptor )
			int alt43=2;
			int LA43_0 = input.LA(1);
			if ( (LA43_0==VOID_TYPE) ) {
				alt43=1;
			}
			else if ( (LA43_0==ARRAY_TYPE_PREFIX||LA43_0==CLASS_DESCRIPTOR||LA43_0==PRIMITIVE_TYPE) ) {
				alt43=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 43, 0, input);
				throw nvae;
			}

			switch (alt43) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1352:5: VOID_TYPE
					{
					match(input,VOID_TYPE,FOLLOW_VOID_TYPE_in_type_descriptor3663);
					type = "V";
					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1353:5: nonvoid_type_descriptor
					{
					pushFollow(FOLLOW_nonvoid_type_descriptor_in_type_descriptor3671);
					nonvoid_type_descriptor216=nonvoid_type_descriptor();
					state._fsp--;

					type = (nonvoid_type_descriptor216!=null?((smaliTreeWalker.nonvoid_type_descriptor_return)nonvoid_type_descriptor216).type:null);
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return type;
	}
	// $ANTLR end "type_descriptor"



	// $ANTLR start "short_integral_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1356:1: short_integral_literal returns [short value] : ( long_literal | integer_literal | short_literal | char_literal | byte_literal );
	public final short short_integral_literal() throws RecognitionException {
		short value = 0;


		long long_literal217 =0;
		int integer_literal218 =0;
		short short_literal219 =0;
		char char_literal220 =0;
		byte byte_literal221 =0;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1357:3: ( long_literal | integer_literal | short_literal | char_literal | byte_literal )
			int alt44=5;
			switch ( input.LA(1) ) {
			case LONG_LITERAL:
				{
				alt44=1;
				}
				break;
			case INTEGER_LITERAL:
				{
				alt44=2;
				}
				break;
			case SHORT_LITERAL:
				{
				alt44=3;
				}
				break;
			case CHAR_LITERAL:
				{
				alt44=4;
				}
				break;
			case BYTE_LITERAL:
				{
				alt44=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 44, 0, input);
				throw nvae;
			}
			switch (alt44) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1357:5: long_literal
					{
					pushFollow(FOLLOW_long_literal_in_short_integral_literal3689);
					long_literal217=long_literal();
					state._fsp--;


					      LiteralTools.checkShort(long_literal217);
					      value = (short)long_literal217;
					
					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1362:5: integer_literal
					{
					pushFollow(FOLLOW_integer_literal_in_short_integral_literal3701);
					integer_literal218=integer_literal();
					state._fsp--;


					      LiteralTools.checkShort(integer_literal218);
					      value = (short)integer_literal218;
					
					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1367:5: short_literal
					{
					pushFollow(FOLLOW_short_literal_in_short_integral_literal3713);
					short_literal219=short_literal();
					state._fsp--;

					value = short_literal219;
					}
					break;
				case 4 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1368:5: char_literal
					{
					pushFollow(FOLLOW_char_literal_in_short_integral_literal3721);
					char_literal220=char_literal();
					state._fsp--;

					value = (short)char_literal220;
					}
					break;
				case 5 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1369:5: byte_literal
					{
					pushFollow(FOLLOW_byte_literal_in_short_integral_literal3729);
					byte_literal221=byte_literal();
					state._fsp--;

					value = byte_literal221;
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "short_integral_literal"



	// $ANTLR start "integral_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1371:1: integral_literal returns [int value] : ( long_literal | integer_literal | short_literal | byte_literal );
	public final int integral_literal() throws RecognitionException {
		int value = 0;


		long long_literal222 =0;
		int integer_literal223 =0;
		short short_literal224 =0;
		byte byte_literal225 =0;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1372:3: ( long_literal | integer_literal | short_literal | byte_literal )
			int alt45=4;
			switch ( input.LA(1) ) {
			case LONG_LITERAL:
				{
				alt45=1;
				}
				break;
			case INTEGER_LITERAL:
				{
				alt45=2;
				}
				break;
			case SHORT_LITERAL:
				{
				alt45=3;
				}
				break;
			case BYTE_LITERAL:
				{
				alt45=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 45, 0, input);
				throw nvae;
			}
			switch (alt45) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1372:5: long_literal
					{
					pushFollow(FOLLOW_long_literal_in_integral_literal3744);
					long_literal222=long_literal();
					state._fsp--;


					      LiteralTools.checkInt(long_literal222);
					      value = (int)long_literal222;
					
					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1377:5: integer_literal
					{
					pushFollow(FOLLOW_integer_literal_in_integral_literal3756);
					integer_literal223=integer_literal();
					state._fsp--;

					value = integer_literal223;
					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1378:5: short_literal
					{
					pushFollow(FOLLOW_short_literal_in_integral_literal3764);
					short_literal224=short_literal();
					state._fsp--;

					value = short_literal224;
					}
					break;
				case 4 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1379:5: byte_literal
					{
					pushFollow(FOLLOW_byte_literal_in_integral_literal3772);
					byte_literal225=byte_literal();
					state._fsp--;

					value = byte_literal225;
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "integral_literal"



	// $ANTLR start "integer_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1382:1: integer_literal returns [int value] : INTEGER_LITERAL ;
	public final int integer_literal() throws RecognitionException {
		int value = 0;


		CommonTree INTEGER_LITERAL226=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1383:3: ( INTEGER_LITERAL )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1383:5: INTEGER_LITERAL
			{
			INTEGER_LITERAL226=(CommonTree)match(input,INTEGER_LITERAL,FOLLOW_INTEGER_LITERAL_in_integer_literal3788);
			 value = LiteralTools.parseInt((INTEGER_LITERAL226!=null?INTEGER_LITERAL226.getText():null));
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "integer_literal"



	// $ANTLR start "long_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1385:1: long_literal returns [long value] : LONG_LITERAL ;
	public final long long_literal() throws RecognitionException {
		long value = 0;


		CommonTree LONG_LITERAL227=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1386:3: ( LONG_LITERAL )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1386:5: LONG_LITERAL
			{
			LONG_LITERAL227=(CommonTree)match(input,LONG_LITERAL,FOLLOW_LONG_LITERAL_in_long_literal3803);
			 value = LiteralTools.parseLong((LONG_LITERAL227!=null?LONG_LITERAL227.getText():null));
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "long_literal"



	// $ANTLR start "short_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1388:1: short_literal returns [short value] : SHORT_LITERAL ;
	public final short short_literal() throws RecognitionException {
		short value = 0;


		CommonTree SHORT_LITERAL228=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1389:3: ( SHORT_LITERAL )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1389:5: SHORT_LITERAL
			{
			SHORT_LITERAL228=(CommonTree)match(input,SHORT_LITERAL,FOLLOW_SHORT_LITERAL_in_short_literal3818);
			 value = LiteralTools.parseShort((SHORT_LITERAL228!=null?SHORT_LITERAL228.getText():null));
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "short_literal"



	// $ANTLR start "byte_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1391:1: byte_literal returns [byte value] : BYTE_LITERAL ;
	public final byte byte_literal() throws RecognitionException {
		byte value = 0;


		CommonTree BYTE_LITERAL229=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1392:3: ( BYTE_LITERAL )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1392:5: BYTE_LITERAL
			{
			BYTE_LITERAL229=(CommonTree)match(input,BYTE_LITERAL,FOLLOW_BYTE_LITERAL_in_byte_literal3833);
			 value = LiteralTools.parseByte((BYTE_LITERAL229!=null?BYTE_LITERAL229.getText():null));
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "byte_literal"



	// $ANTLR start "float_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1394:1: float_literal returns [float value] : FLOAT_LITERAL ;
	public final float float_literal() throws RecognitionException {
		float value = 0.0f;


		CommonTree FLOAT_LITERAL230=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1395:3: ( FLOAT_LITERAL )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1395:5: FLOAT_LITERAL
			{
			FLOAT_LITERAL230=(CommonTree)match(input,FLOAT_LITERAL,FOLLOW_FLOAT_LITERAL_in_float_literal3848);
			 value = LiteralTools.parseFloat((FLOAT_LITERAL230!=null?FLOAT_LITERAL230.getText():null));
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "float_literal"



	// $ANTLR start "double_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1397:1: double_literal returns [double value] : DOUBLE_LITERAL ;
	public final double double_literal() throws RecognitionException {
		double value = 0.0;


		CommonTree DOUBLE_LITERAL231=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1398:3: ( DOUBLE_LITERAL )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1398:5: DOUBLE_LITERAL
			{
			DOUBLE_LITERAL231=(CommonTree)match(input,DOUBLE_LITERAL,FOLLOW_DOUBLE_LITERAL_in_double_literal3863);
			 value = LiteralTools.parseDouble((DOUBLE_LITERAL231!=null?DOUBLE_LITERAL231.getText():null));
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "double_literal"



	// $ANTLR start "char_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1400:1: char_literal returns [char value] : CHAR_LITERAL ;
	public final char char_literal() throws RecognitionException {
		char value = 0;


		CommonTree CHAR_LITERAL232=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1401:3: ( CHAR_LITERAL )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1401:5: CHAR_LITERAL
			{
			CHAR_LITERAL232=(CommonTree)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_char_literal3878);
			 value = (CHAR_LITERAL232!=null?CHAR_LITERAL232.getText():null).charAt(1);
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "char_literal"



	// $ANTLR start "string_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1403:1: string_literal returns [String value] : STRING_LITERAL ;
	public final String string_literal() throws RecognitionException {
		String value = null;


		CommonTree STRING_LITERAL233=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1404:3: ( STRING_LITERAL )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1404:5: STRING_LITERAL
			{
			STRING_LITERAL233=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_string_literal3893);

			      value = (STRING_LITERAL233!=null?STRING_LITERAL233.getText():null);
			      value = value.substring(1,value.length()-1);
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "string_literal"



	// $ANTLR start "bool_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1410:1: bool_literal returns [boolean value] : BOOL_LITERAL ;
	public final boolean bool_literal() throws RecognitionException {
		boolean value = false;


		CommonTree BOOL_LITERAL234=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1411:3: ( BOOL_LITERAL )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1411:5: BOOL_LITERAL
			{
			BOOL_LITERAL234=(CommonTree)match(input,BOOL_LITERAL,FOLLOW_BOOL_LITERAL_in_bool_literal3912);
			 value = Boolean.parseBoolean((BOOL_LITERAL234!=null?BOOL_LITERAL234.getText():null));
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "bool_literal"



	// $ANTLR start "array_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1413:1: array_literal returns [List<EncodedValue> elements] : ^( I_ENCODED_ARRAY ( literal )* ) ;
	public final List<EncodedValue> array_literal() throws RecognitionException {
		List<EncodedValue> elements = null;


		ImmutableEncodedValue literal235 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1414:3: ( ^( I_ENCODED_ARRAY ( literal )* ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1414:5: ^( I_ENCODED_ARRAY ( literal )* )
			{
			elements = Lists.newArrayList();
			match(input,I_ENCODED_ARRAY,FOLLOW_I_ENCODED_ARRAY_in_array_literal3934);
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1415:23: ( literal )*
				loop46:
				while (true) {
					int alt46=2;
					int LA46_0 = input.LA(1);
					if ( (LA46_0==ARRAY_TYPE_PREFIX||(LA46_0 >= BOOL_LITERAL && LA46_0 <= BYTE_LITERAL)||(LA46_0 >= CHAR_LITERAL && LA46_0 <= CLASS_DESCRIPTOR)||LA46_0==DOUBLE_LITERAL||LA46_0==FLOAT_LITERAL||LA46_0==INTEGER_LITERAL||(LA46_0 >= I_ENCODED_ARRAY && LA46_0 <= I_ENCODED_METHOD_HANDLE)||LA46_0==I_METHOD_PROTOTYPE||LA46_0==I_SUBANNOTATION||LA46_0==LONG_LITERAL||LA46_0==NULL_LITERAL||LA46_0==PRIMITIVE_TYPE||LA46_0==SHORT_LITERAL||LA46_0==STRING_LITERAL||LA46_0==VOID_TYPE) ) {
						alt46=1;
					}

					switch (alt46) {
					case 1 :
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1415:24: literal
						{
						pushFollow(FOLLOW_literal_in_array_literal3937);
						literal235=literal();
						state._fsp--;

						elements.add(literal235);
						}
						break;

					default :
						break loop46;
					}
				}

				match(input, Token.UP, null);
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return elements;
	}
	// $ANTLR end "array_literal"



	// $ANTLR start "annotations"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1417:1: annotations returns [Set<Annotation> annotations] : ^( I_ANNOTATIONS ( annotation )* ) ;
	public final Set<Annotation> annotations() throws RecognitionException {
		Set<Annotation> annotations = null;


		Annotation annotation236 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1418:3: ( ^( I_ANNOTATIONS ( annotation )* ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1418:5: ^( I_ANNOTATIONS ( annotation )* )
			{
			HashMap<String, Annotation> annotationMap = Maps.newHashMap();
			match(input,I_ANNOTATIONS,FOLLOW_I_ANNOTATIONS_in_annotations3962);
			if ( input.LA(1)==Token.DOWN ) {
				match(input, Token.DOWN, null);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1419:21: ( annotation )*
				loop47:
				while (true) {
					int alt47=2;
					int LA47_0 = input.LA(1);
					if ( (LA47_0==I_ANNOTATION) ) {
						alt47=1;
					}

					switch (alt47) {
					case 1 :
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1419:22: annotation
						{
						pushFollow(FOLLOW_annotation_in_annotations3965);
						annotation236=annotation();
						state._fsp--;


						        Annotation anno = annotation236;
						        Annotation old = annotationMap.put(anno.getType(), anno);
						        if (old != null) {
						            throw new SemanticException(input, "Multiple annotations of type %s", anno.getType());
						        }
						
						}
						break;

					default :
						break loop47;
					}
				}

				match(input, Token.UP, null);
			}


			        annotations = ImmutableSet.copyOf(annotationMap.values());
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return annotations;
	}
	// $ANTLR end "annotations"



	// $ANTLR start "annotation"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1431:1: annotation returns [Annotation annotation] : ^( I_ANNOTATION ANNOTATION_VISIBILITY subannotation ) ;
	public final Annotation annotation() throws RecognitionException {
		Annotation annotation = null;


		CommonTree ANNOTATION_VISIBILITY237=null;
		TreeRuleReturnScope subannotation238 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1432:3: ( ^( I_ANNOTATION ANNOTATION_VISIBILITY subannotation ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1432:5: ^( I_ANNOTATION ANNOTATION_VISIBILITY subannotation )
			{
			match(input,I_ANNOTATION,FOLLOW_I_ANNOTATION_in_annotation3994);
			match(input, Token.DOWN, null);
			ANNOTATION_VISIBILITY237=(CommonTree)match(input,ANNOTATION_VISIBILITY,FOLLOW_ANNOTATION_VISIBILITY_in_annotation3996);
			pushFollow(FOLLOW_subannotation_in_annotation3998);
			subannotation238=subannotation();
			state._fsp--;

			match(input, Token.UP, null);


			      int visibility = AnnotationVisibility.getVisibility((ANNOTATION_VISIBILITY237!=null?ANNOTATION_VISIBILITY237.getText():null));
			      annotation = new ImmutableAnnotation(visibility, (subannotation238!=null?((smaliTreeWalker.subannotation_return)subannotation238).annotationType:null), (subannotation238!=null?((smaliTreeWalker.subannotation_return)subannotation238).elements:null));
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return annotation;
	}
	// $ANTLR end "annotation"



	// $ANTLR start "annotation_element"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1438:1: annotation_element returns [AnnotationElement element] : ^( I_ANNOTATION_ELEMENT SIMPLE_NAME literal ) ;
	public final AnnotationElement annotation_element() throws RecognitionException {
		AnnotationElement element = null;


		CommonTree SIMPLE_NAME239=null;
		ImmutableEncodedValue literal240 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1439:3: ( ^( I_ANNOTATION_ELEMENT SIMPLE_NAME literal ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1439:5: ^( I_ANNOTATION_ELEMENT SIMPLE_NAME literal )
			{
			match(input,I_ANNOTATION_ELEMENT,FOLLOW_I_ANNOTATION_ELEMENT_in_annotation_element4019);
			match(input, Token.DOWN, null);
			SIMPLE_NAME239=(CommonTree)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_annotation_element4021);
			pushFollow(FOLLOW_literal_in_annotation_element4023);
			literal240=literal();
			state._fsp--;

			match(input, Token.UP, null);


			      element = new ImmutableAnnotationElement((SIMPLE_NAME239!=null?SIMPLE_NAME239.getText():null), literal240);
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return element;
	}
	// $ANTLR end "annotation_element"


	public static class subannotation_return extends TreeRuleReturnScope {
		public String annotationType;
		public List<AnnotationElement> elements;
	};


	// $ANTLR start "subannotation"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1444:1: subannotation returns [String annotationType, List<AnnotationElement> elements] : ^( I_SUBANNOTATION CLASS_DESCRIPTOR ( annotation_element )* ) ;
	public final smaliTreeWalker.subannotation_return subannotation() throws RecognitionException {
		smaliTreeWalker.subannotation_return retval = new smaliTreeWalker.subannotation_return();
		retval.start = input.LT(1);

		CommonTree CLASS_DESCRIPTOR242=null;
		AnnotationElement annotation_element241 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1445:3: ( ^( I_SUBANNOTATION CLASS_DESCRIPTOR ( annotation_element )* ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1445:5: ^( I_SUBANNOTATION CLASS_DESCRIPTOR ( annotation_element )* )
			{
			ArrayList<AnnotationElement> elements = Lists.newArrayList();
			match(input,I_SUBANNOTATION,FOLLOW_I_SUBANNOTATION_in_subannotation4050);
			match(input, Token.DOWN, null);
			CLASS_DESCRIPTOR242=(CommonTree)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_subannotation4060);
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1448:9: ( annotation_element )*
			loop48:
			while (true) {
				int alt48=2;
				int LA48_0 = input.LA(1);
				if ( (LA48_0==I_ANNOTATION_ELEMENT) ) {
					alt48=1;
				}

				switch (alt48) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1448:10: annotation_element
					{
					pushFollow(FOLLOW_annotation_element_in_subannotation4071);
					annotation_element241=annotation_element();
					state._fsp--;


					           elements.add(annotation_element241);
					
					}
					break;

				default :
					break loop48;
				}
			}

			match(input, Token.UP, null);


			      retval.annotationType = (CLASS_DESCRIPTOR242!=null?CLASS_DESCRIPTOR242.getText():null);
			      retval.elements = elements;
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "subannotation"



	// $ANTLR start "field_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1458:1: field_literal returns [ImmutableFieldReference value] : ^( I_ENCODED_FIELD field_reference ) ;
	public final ImmutableFieldReference field_literal() throws RecognitionException {
		ImmutableFieldReference value = null;


		TreeRuleReturnScope field_reference243 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1459:3: ( ^( I_ENCODED_FIELD field_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1459:5: ^( I_ENCODED_FIELD field_reference )
			{
			match(input,I_ENCODED_FIELD,FOLLOW_I_ENCODED_FIELD_in_field_literal4110);
			match(input, Token.DOWN, null);
			pushFollow(FOLLOW_field_reference_in_field_literal4112);
			field_reference243=field_reference();
			state._fsp--;

			match(input, Token.UP, null);


			      value = (field_reference243!=null?((smaliTreeWalker.field_reference_return)field_reference243).fieldReference:null);
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "field_literal"



	// $ANTLR start "method_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1464:1: method_literal returns [ImmutableMethodReference value] : ^( I_ENCODED_METHOD method_reference ) ;
	public final ImmutableMethodReference method_literal() throws RecognitionException {
		ImmutableMethodReference value = null;


		ImmutableMethodReference method_reference244 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1465:3: ( ^( I_ENCODED_METHOD method_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1465:5: ^( I_ENCODED_METHOD method_reference )
			{
			match(input,I_ENCODED_METHOD,FOLLOW_I_ENCODED_METHOD_in_method_literal4133);
			match(input, Token.DOWN, null);
			pushFollow(FOLLOW_method_reference_in_method_literal4135);
			method_reference244=method_reference();
			state._fsp--;

			match(input, Token.UP, null);


			      value = method_reference244;
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "method_literal"



	// $ANTLR start "enum_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1470:1: enum_literal returns [ImmutableFieldReference value] : ^( I_ENCODED_ENUM field_reference ) ;
	public final ImmutableFieldReference enum_literal() throws RecognitionException {
		ImmutableFieldReference value = null;


		TreeRuleReturnScope field_reference245 =null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1471:3: ( ^( I_ENCODED_ENUM field_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliTreeWalker.g:1471:5: ^( I_ENCODED_ENUM field_reference )
			{
			match(input,I_ENCODED_ENUM,FOLLOW_I_ENCODED_ENUM_in_enum_literal4156);
			match(input, Token.DOWN, null);
			pushFollow(FOLLOW_field_reference_in_enum_literal4158);
			field_reference245=field_reference();
			state._fsp--;

			match(input, Token.UP, null);


			      value = (field_reference245!=null?((smaliTreeWalker.field_reference_return)field_reference245).fieldReference:null);
			
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return value;
	}
	// $ANTLR end "enum_literal"

	// Delegated rules



	public static final BitSet FOLLOW_I_CLASS_DEF_in_smali_file52 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_header_in_smali_file54 = new BitSet(new long[]{0x0000000000000000L,0x8000000000000000L});
	public static final BitSet FOLLOW_methods_in_smali_file56 = new BitSet(new long[]{0x0000000000000000L,0x0040000000000000L});
	public static final BitSet FOLLOW_fields_in_smali_file58 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_annotations_in_smali_file60 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_class_spec_in_header85 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000000L,0x0100000000002000L});
	public static final BitSet FOLLOW_super_spec_in_header87 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_implements_list_in_header90 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000002000L});
	public static final BitSet FOLLOW_source_spec_in_header92 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_class_spec110 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
	public static final BitSet FOLLOW_access_list_in_class_spec112 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_I_SUPER_in_super_spec130 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_super_spec132 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_IMPLEMENTS_in_implements_spec152 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_implements_spec154 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_implements_spec_in_implements_list184 = new BitSet(new long[]{0x0000000000000002L,0x0200000000000000L});
	public static final BitSet FOLLOW_I_SOURCE_in_source_spec213 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_string_literal_in_source_spec215 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_ACCESS_LIST_in_access_list247 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ACCESS_SPEC_in_access_list265 = new BitSet(new long[]{0x0000000000000018L});
	public static final BitSet FOLLOW_I_ACCESS_OR_RESTRICTION_LIST_in_access_or_restriction_list308 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ACCESS_SPEC_in_access_or_restriction_list326 = new BitSet(new long[]{0x0000020000000018L});
	public static final BitSet FOLLOW_HIDDENAPI_RESTRICTION_in_access_or_restriction_list356 = new BitSet(new long[]{0x0000020000000018L});
	public static final BitSet FOLLOW_I_FIELDS_in_fields405 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_field_in_fields414 = new BitSet(new long[]{0x0000000000000008L,0x0020000000000000L});
	public static final BitSet FOLLOW_I_METHODS_in_methods446 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_method_in_methods455 = new BitSet(new long[]{0x0000000000000008L,0x4000000000000000L});
	public static final BitSet FOLLOW_I_FIELD_in_field480 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_SIMPLE_NAME_in_field482 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_access_or_restriction_list_in_field484 = new BitSet(new long[]{0x0000000000000000L,0x0100000000000000L});
	public static final BitSet FOLLOW_I_FIELD_TYPE_in_field487 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_field489 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_field_initial_value_in_field492 = new BitSet(new long[]{0x0000000000000008L,0x0000001000000000L});
	public static final BitSet FOLLOW_annotations_in_field494 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_FIELD_INITIAL_VALUE_in_field_initial_value515 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_literal_in_field_initial_value517 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_integer_literal_in_literal539 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_long_literal_in_literal547 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_short_literal_in_literal555 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_byte_literal_in_literal563 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_float_literal_in_literal571 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_double_literal_in_literal579 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_char_literal_in_literal587 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_string_literal_in_literal595 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_bool_literal_in_literal603 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NULL_LITERAL_in_literal611 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_type_descriptor_in_literal619 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_array_literal_in_literal627 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_subannotation_in_literal635 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_field_literal_in_literal643 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_method_literal_in_literal651 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_enum_literal_in_literal659 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_method_handle_literal_in_literal667 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_method_prototype_in_literal675 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_integer_literal_in_fixed_64bit_literal_number691 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_long_literal_in_fixed_64bit_literal_number699 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_short_literal_in_fixed_64bit_literal_number707 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_byte_literal_in_fixed_64bit_literal_number715 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_float_literal_in_fixed_64bit_literal_number723 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_double_literal_in_fixed_64bit_literal_number731 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_char_literal_in_fixed_64bit_literal_number739 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_bool_literal_in_fixed_64bit_literal_number747 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_integer_literal_in_fixed_64bit_literal762 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_long_literal_in_fixed_64bit_literal770 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_short_literal_in_fixed_64bit_literal778 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_byte_literal_in_fixed_64bit_literal786 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_float_literal_in_fixed_64bit_literal794 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_double_literal_in_fixed_64bit_literal802 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_char_literal_in_fixed_64bit_literal810 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_bool_literal_in_fixed_64bit_literal818 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_integer_literal_in_fixed_32bit_literal835 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_long_literal_in_fixed_32bit_literal843 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_short_literal_in_fixed_32bit_literal851 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_byte_literal_in_fixed_32bit_literal859 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_float_literal_in_fixed_32bit_literal867 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_char_literal_in_fixed_32bit_literal875 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_bool_literal_in_fixed_32bit_literal883 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_I_ARRAY_ELEMENTS_in_array_elements905 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_fixed_64bit_literal_number_in_array_elements914 = new BitSet(new long[]{0x0000008000809808L,0x0000000080000000L,0x2000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_I_PACKED_SWITCH_ELEMENTS_in_packed_switch_elements950 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_label_ref_in_packed_switch_elements959 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_I_SPARSE_SWITCH_ELEMENTS_in_sparse_switch_elements994 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_fixed_32bit_literal_in_sparse_switch_elements1004 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_label_ref_in_sparse_switch_elements1006 = new BitSet(new long[]{0x0000008000009808L,0x0000000080000000L,0x2000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_I_METHOD_in_method1058 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_method_name_and_prototype_in_method1066 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
	public static final BitSet FOLLOW_access_or_restriction_list_in_method1074 = new BitSet(new long[]{0x0000000000000000L,0x2000000000000000L,0x0000000000000204L});
	public static final BitSet FOLLOW_registers_directive_in_method1101 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_ordered_method_items_in_method1158 = new BitSet(new long[]{0x0000000000000000L,0x0000100000000000L});
	public static final BitSet FOLLOW_catches_in_method1166 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_parameters_in_method1174 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_annotations_in_method1183 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_METHOD_PROTOTYPE_in_method_prototype1207 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_I_METHOD_RETURN_TYPE_in_method_prototype1210 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_type_descriptor_in_method_prototype1212 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_method_type_list_in_method_prototype1215 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SIMPLE_NAME_in_method_name_and_prototype1233 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_method_prototype_in_method_name_and_prototype1235 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_method_type_list1269 = new BitSet(new long[]{0x0000000000010102L,0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_I_CALL_SITE_REFERENCE_in_call_site_reference1300 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_SIMPLE_NAME_in_call_site_reference1304 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_string_literal_in_call_site_reference1308 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_method_prototype_in_call_site_reference1310 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_call_site_extra_arguments_in_call_site_reference1320 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_method_reference_in_call_site_reference1322 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_set_in_method_handle_type1342 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_method_handle_type_in_method_handle_reference1367 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_field_reference_in_method_handle_reference1370 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_method_reference_in_method_handle_reference1374 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_I_ENCODED_METHOD_HANDLE_in_method_handle_literal1391 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_method_handle_reference_in_method_handle_literal1393 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_reference_type_descriptor_in_method_reference1409 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_SIMPLE_NAME_in_method_reference1412 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_method_prototype_in_method_reference1414 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_reference_type_descriptor_in_field_reference1431 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_SIMPLE_NAME_in_field_reference1434 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_field_reference1436 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_I_REGISTERS_in_registers_directive1462 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_I_LOCALS_in_registers_directive1474 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_short_integral_literal_in_registers_directive1492 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_LABEL_in_label_def1512 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_SIMPLE_NAME_in_label_def1514 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_CATCHES_in_catches1540 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_catch_directive_in_catches1542 = new BitSet(new long[]{0x0000000000000008L,0x00000C0000000000L});
	public static final BitSet FOLLOW_catchall_directive_in_catches1545 = new BitSet(new long[]{0x0000000000000008L,0x0000080000000000L});
	public static final BitSet FOLLOW_I_CATCH_in_catch_directive1558 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_catch_directive1560 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_label_ref_in_catch_directive1564 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_label_ref_in_catch_directive1568 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_label_ref_in_catch_directive1572 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_CATCHALL_in_catchall_directive1588 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_label_ref_in_catchall_directive1592 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_label_ref_in_catchall_directive1596 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_label_ref_in_catchall_directive1600 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_PARAMETERS_in_parameters1617 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_parameter_in_parameters1620 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_I_PARAMETER_in_parameter1636 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_parameter1638 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L,0x0000000000000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_string_literal_in_parameter1640 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
	public static final BitSet FOLLOW_annotations_in_parameter1643 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_line_in_debug_directive1660 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_local_in_debug_directive1666 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_end_local_in_debug_directive1672 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_restart_local_in_debug_directive1678 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_prologue_in_debug_directive1684 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_epilogue_in_debug_directive1690 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_source_in_debug_directive1696 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_I_LINE_in_line1707 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_integral_literal_in_line1709 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_LOCAL_in_local1727 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_local1729 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000080008L});
	public static final BitSet FOLLOW_NULL_LITERAL_in_local1733 = new BitSet(new long[]{0x0000000000010108L,0x0000000000000000L,0x0000000000000000L,0x0000000000080400L});
	public static final BitSet FOLLOW_string_literal_in_local1739 = new BitSet(new long[]{0x0000000000010108L,0x0000000000000000L,0x0000000000000000L,0x0000000000080400L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_local1742 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_string_literal_in_local1747 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_END_LOCAL_in_end_local1768 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_end_local1770 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_RESTART_LOCAL_in_restart_local1788 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_restart_local1790 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_PROLOGUE_in_prologue1807 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_I_EPILOGUE_in_epilogue1823 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_I_SOURCE_in_source1840 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_string_literal_in_source1842 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_CALL_SITE_EXTRA_ARGUMENTS_in_call_site_extra_arguments1868 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_literal_in_call_site_extra_arguments1871 = new BitSet(new long[]{0x0000008000819908L,0x0007C00080000000L,0x2080000000000001L,0x0000000000888408L});
	public static final BitSet FOLLOW_I_ORDERED_METHOD_ITEMS_in_ordered_method_items1887 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_label_def_in_ordered_method_items1890 = new BitSet(new long[]{0x0000000000000008L,0x1C18000000000000L,0x007FFFFFFFFFB100L});
	public static final BitSet FOLLOW_instruction_in_ordered_method_items1894 = new BitSet(new long[]{0x0000000000000008L,0x1C18000000000000L,0x007FFFFFFFFFB100L});
	public static final BitSet FOLLOW_debug_directive_in_ordered_method_items1898 = new BitSet(new long[]{0x0000000000000008L,0x1C18000000000000L,0x007FFFFFFFFFB100L});
	public static final BitSet FOLLOW_SIMPLE_NAME_in_label_ref1914 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_I_REGISTER_LIST_in_register_list1939 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_register_list1948 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_I_REGISTER_RANGE_in_register_range1973 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_REGISTER_in_register_range1978 = new BitSet(new long[]{0x0000000000000008L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_register_range1982 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_verification_error_reference2005 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_field_reference_in_verification_error_reference2015 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_method_reference_in_verification_error_reference2025 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VERIFICATION_ERROR_TYPE_in_verification_error_type2042 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format10t_in_instruction2056 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format10x_in_instruction2062 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format11n_in_instruction2068 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format11x_in_instruction2074 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format12x_in_instruction2080 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format20bc_in_instruction2086 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format20t_in_instruction2092 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21c_field_in_instruction2098 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21c_method_handle_in_instruction2104 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21c_method_type_in_instruction2110 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21c_string_in_instruction2116 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21c_type_in_instruction2122 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21ih_in_instruction2128 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21lh_in_instruction2134 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21s_in_instruction2140 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21t_in_instruction2146 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22b_in_instruction2152 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22c_field_in_instruction2158 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22c_type_in_instruction2164 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22s_in_instruction2170 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22t_in_instruction2176 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22x_in_instruction2182 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format23x_in_instruction2188 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format30t_in_instruction2194 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format31c_in_instruction2200 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format31i_in_instruction2206 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format31t_in_instruction2212 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format32x_in_instruction2218 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format35c_call_site_in_instruction2224 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format35c_method_in_instruction2230 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format35c_type_in_instruction2236 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format3rc_call_site_in_instruction2242 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format3rc_method_in_instruction2248 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format3rc_type_in_instruction2254 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format45cc_method_in_instruction2260 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format4rcc_method_in_instruction2266 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format51l_type_in_instruction2272 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_array_data_directive_in_instruction2278 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_packed_switch_directive_in_instruction2284 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_sparse_switch_directive_in_instruction2290 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT10t_in_insn_format10t2314 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT10t_in_insn_format10t2316 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_label_ref_in_insn_format10t2318 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT10x_in_insn_format10x2341 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT10x_in_insn_format10x2343 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT11n_in_insn_format11n2366 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT11n_in_insn_format11n2368 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format11n2370 = new BitSet(new long[]{0x0000000000009000L,0x0000000080000000L,0x2000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_short_integral_literal_in_insn_format11n2372 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT11x_in_insn_format11x2395 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT11x_in_insn_format11x2397 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format11x2399 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT12x_in_insn_format12x2422 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT12x_in_insn_format12x2424 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format12x2428 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format12x2432 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT20bc_in_insn_format20bc2455 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT20bc_in_insn_format20bc2457 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_verification_error_type_in_insn_format20bc2459 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_verification_error_reference_in_insn_format20bc2461 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT20t_in_insn_format20t2484 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT20t_in_insn_format20t2486 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_label_ref_in_insn_format20t2488 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT21c_FIELD_in_insn_format21c_field2511 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_set_in_insn_format21c_field2515 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21c_field2523 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_field_reference_in_insn_format21c_field2525 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT21c_METHOD_HANDLE_in_insn_format21c_method_handle2548 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_METHOD_HANDLE_in_insn_format21c_method_handle2553 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21c_method_handle2556 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L,0x0000000000000000L,0x0000000000000003L});
	public static final BitSet FOLLOW_method_handle_reference_in_insn_format21c_method_handle2558 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT21c_METHOD_TYPE_in_insn_format21c_method_type2581 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_METHOD_TYPE_in_insn_format21c_method_type2586 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21c_method_type2589 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_method_prototype_in_insn_format21c_method_type2591 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT21c_STRING_in_insn_format21c_string2614 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_STRING_in_insn_format21c_string2616 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21c_string2618 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_string_literal_in_insn_format21c_string2620 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT21c_TYPE_in_insn_format21c_type2643 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_TYPE_in_insn_format21c_type2645 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21c_type2647 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_insn_format21c_type2649 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT21ih_in_insn_format21ih2672 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21ih_in_insn_format21ih2674 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21ih2676 = new BitSet(new long[]{0x0000008000009800L,0x0000000080000000L,0x2000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_fixed_32bit_literal_in_insn_format21ih2678 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT21lh_in_insn_format21lh2701 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21lh_in_insn_format21lh2703 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21lh2705 = new BitSet(new long[]{0x0000008000809800L,0x0000000080000000L,0x2000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_fixed_64bit_literal_in_insn_format21lh2707 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT21s_in_insn_format21s2730 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21s_in_insn_format21s2732 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21s2734 = new BitSet(new long[]{0x0000000000009000L,0x0000000080000000L,0x2000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_short_integral_literal_in_insn_format21s2736 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT21t_in_insn_format21t2759 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21t_in_insn_format21t2761 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21t2763 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_label_ref_in_insn_format21t2765 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT22b_in_insn_format22b2788 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22b_in_insn_format22b2790 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22b2794 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22b2798 = new BitSet(new long[]{0x0000000000009000L,0x0000000080000000L,0x2000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_short_integral_literal_in_insn_format22b2800 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT22c_FIELD_in_insn_format22c_field2823 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_set_in_insn_format22c_field2827 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_field2837 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_field2841 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_field_reference_in_insn_format22c_field2843 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT22c_TYPE_in_insn_format22c_type2866 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_TYPE_in_insn_format22c_type2868 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_type2872 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_type2876 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_insn_format22c_type2878 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT22s_in_insn_format22s2901 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22s_in_insn_format22s2903 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22s2907 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22s2911 = new BitSet(new long[]{0x0000000000009000L,0x0000000080000000L,0x2000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_short_integral_literal_in_insn_format22s2913 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT22t_in_insn_format22t2936 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22t_in_insn_format22t2938 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22t2942 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22t2946 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_label_ref_in_insn_format22t2948 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT22x_in_insn_format22x2971 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22x_in_insn_format22x2973 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22x2977 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22x2981 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT23x_in_insn_format23x3004 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT23x_in_insn_format23x3006 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format23x3010 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format23x3014 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format23x3018 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT30t_in_insn_format30t3041 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT30t_in_insn_format30t3043 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_label_ref_in_insn_format30t3045 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT31c_in_insn_format31c3068 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT31c_in_insn_format31c3070 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format31c3072 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_string_literal_in_insn_format31c3074 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT31i_in_insn_format31i3097 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT31i_in_insn_format31i3099 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format31i3101 = new BitSet(new long[]{0x0000008000009800L,0x0000000080000000L,0x2000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_fixed_32bit_literal_in_insn_format31i3103 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT31t_in_insn_format31t3126 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT31t_in_insn_format31t3128 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format31t3130 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_label_ref_in_insn_format31t3132 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT32x_in_insn_format32x3155 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT32x_in_insn_format32x3157 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format32x3161 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format32x3165 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT35c_CALL_SITE_in_insn_format35c_call_site3193 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_CALL_SITE_in_insn_format35c_call_site3195 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_register_list_in_insn_format35c_call_site3197 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_call_site_reference_in_insn_format35c_call_site3199 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT35c_METHOD_in_insn_format35c_method3222 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_METHOD_in_insn_format35c_method3224 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_register_list_in_insn_format35c_method3226 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_method_reference_in_insn_format35c_method3228 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT35c_TYPE_in_insn_format35c_type3251 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_TYPE_in_insn_format35c_type3253 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_register_list_in_insn_format35c_type3255 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_insn_format35c_type3257 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT3rc_CALL_SITE_in_insn_format3rc_call_site3285 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT3rc_CALL_SITE_in_insn_format3rc_call_site3287 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_register_range_in_insn_format3rc_call_site3289 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
	public static final BitSet FOLLOW_call_site_reference_in_insn_format3rc_call_site3291 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT3rc_METHOD_in_insn_format3rc_method3314 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT3rc_METHOD_in_insn_format3rc_method3316 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_register_range_in_insn_format3rc_method3318 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_method_reference_in_insn_format3rc_method3320 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT3rc_TYPE_in_insn_format3rc_type3343 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT3rc_TYPE_in_insn_format3rc_type3345 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_register_range_in_insn_format3rc_type3347 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_insn_format3rc_type3349 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT45cc_METHOD_in_insn_format45cc_method3372 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT45cc_METHOD_in_insn_format45cc_method3374 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_register_list_in_insn_format45cc_method3376 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_method_reference_in_insn_format45cc_method3378 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_method_prototype_in_insn_format45cc_method3380 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT4rcc_METHOD_in_insn_format4rcc_method3403 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT4rcc_METHOD_in_insn_format4rcc_method3405 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_register_range_in_insn_format4rcc_method3407 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000010000L});
	public static final BitSet FOLLOW_method_reference_in_insn_format4rcc_method3409 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_method_prototype_in_insn_format4rcc_method3411 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_FORMAT51l_in_insn_format51l_type3434 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT51l_in_insn_format51l_type3436 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format51l_type3438 = new BitSet(new long[]{0x0000008000809800L,0x0000000080000000L,0x2000000000000000L,0x0000000000008000L});
	public static final BitSet FOLLOW_fixed_64bit_literal_in_insn_format51l_type3440 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_ARRAY_DATA_in_insn_array_data_directive3463 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_I_ARRAY_ELEMENT_SIZE_in_insn_array_data_directive3466 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_short_integral_literal_in_insn_array_data_directive3468 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_array_elements_in_insn_array_data_directive3471 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_PACKED_SWITCH_in_insn_packed_switch_directive3493 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_I_PACKED_SWITCH_START_KEY_in_insn_packed_switch_directive3496 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_fixed_32bit_literal_in_insn_packed_switch_directive3498 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_packed_switch_elements_in_insn_packed_switch_directive3501 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_STATEMENT_SPARSE_SWITCH_in_insn_sparse_switch_directive3525 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_sparse_switch_elements_in_insn_sparse_switch_directive3527 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_ARRAY_TYPE_PREFIX_in_array_descriptor3548 = new BitSet(new long[]{0x0000000000010000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_PRIMITIVE_TYPE_in_array_descriptor3552 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_array_descriptor3580 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRIMITIVE_TYPE_in_nonvoid_type_descriptor3598 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_nonvoid_type_descriptor3606 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_array_descriptor_in_nonvoid_type_descriptor3614 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_reference_type_descriptor3635 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_array_descriptor_in_reference_type_descriptor3643 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VOID_TYPE_in_type_descriptor3663 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_type_descriptor3671 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_long_literal_in_short_integral_literal3689 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_integer_literal_in_short_integral_literal3701 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_short_literal_in_short_integral_literal3713 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_char_literal_in_short_integral_literal3721 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_byte_literal_in_short_integral_literal3729 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_long_literal_in_integral_literal3744 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_integer_literal_in_integral_literal3756 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_short_literal_in_integral_literal3764 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_byte_literal_in_integral_literal3772 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTEGER_LITERAL_in_integer_literal3788 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LONG_LITERAL_in_long_literal3803 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SHORT_LITERAL_in_short_literal3818 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BYTE_LITERAL_in_byte_literal3833 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FLOAT_LITERAL_in_float_literal3848 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLE_LITERAL_in_double_literal3863 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CHAR_LITERAL_in_char_literal3878 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_string_literal3893 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BOOL_LITERAL_in_bool_literal3912 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_I_ENCODED_ARRAY_in_array_literal3934 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_literal_in_array_literal3937 = new BitSet(new long[]{0x0000008000819908L,0x0007C00080000000L,0x2080000000000001L,0x0000000000888408L});
	public static final BitSet FOLLOW_I_ANNOTATIONS_in_annotations3962 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_annotation_in_annotations3965 = new BitSet(new long[]{0x0000000000000008L,0x0000000800000000L});
	public static final BitSet FOLLOW_I_ANNOTATION_in_annotation3994 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_ANNOTATION_VISIBILITY_in_annotation3996 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0080000000000000L});
	public static final BitSet FOLLOW_subannotation_in_annotation3998 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_ANNOTATION_ELEMENT_in_annotation_element4019 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_SIMPLE_NAME_in_annotation_element4021 = new BitSet(new long[]{0x0000008000819900L,0x0007C00080000000L,0x2080000000000001L,0x0000000000888408L});
	public static final BitSet FOLLOW_literal_in_annotation_element4023 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_SUBANNOTATION_in_subannotation4050 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_subannotation4060 = new BitSet(new long[]{0x0000000000000008L,0x0000002000000000L});
	public static final BitSet FOLLOW_annotation_element_in_subannotation4071 = new BitSet(new long[]{0x0000000000000008L,0x0000002000000000L});
	public static final BitSet FOLLOW_I_ENCODED_FIELD_in_field_literal4110 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_field_reference_in_field_literal4112 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_ENCODED_METHOD_in_method_literal4133 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_method_reference_in_method_literal4135 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_I_ENCODED_ENUM_in_enum_literal4156 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_field_reference_in_enum_literal4158 = new BitSet(new long[]{0x0000000000000008L});
}
