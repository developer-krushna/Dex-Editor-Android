// $ANTLR 3.5.2 /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g 2023-05-04 15:21:24

package com.android.tools.smali.smali;

import com.android.tools.smali.dexlib2.Opcode;
import com.android.tools.smali.dexlib2.Opcodes;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class smaliParser extends Parser {
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
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public smaliParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public smaliParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return smaliParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g"; }


	  public static final int ERROR_CHANNEL = 100;

	  private boolean verboseErrors = false;
	  private boolean allowOdex = false;
	  private int apiLevel = 15;
	  private Opcodes opcodes = Opcodes.forApi(apiLevel);

	  public void setVerboseErrors(boolean verboseErrors) {
	    this.verboseErrors = verboseErrors;
	  }

	  public void setAllowOdex(boolean allowOdex) {
	      this.allowOdex = allowOdex;
	  }

	  public void setApiLevel(int apiLevel) {
	      this.opcodes = Opcodes.forApi(apiLevel);
	      this.apiLevel = apiLevel;
	  }

	  public String getErrorMessage(RecognitionException e,
	    String[] tokenNames) {

	    if (verboseErrors) {
	      List stack = getRuleInvocationStack(e, this.getClass().getName());
	      String msg = null;

	      if (e instanceof NoViableAltException) {
	        NoViableAltException nvae = (NoViableAltException)e;
	        msg = " no viable alt; token="+getTokenErrorDisplay(e.token)+
	        " (decision="+nvae.decisionNumber+
	        " state "+nvae.stateNumber+")"+
	        " decision=<<"+nvae.grammarDecisionDescription+">>";
	      } else {
	        msg = super.getErrorMessage(e, tokenNames);
	      }

	      return stack + " " + msg;
	    } else {
	      return super.getErrorMessage(e, tokenNames);
	    }
	  }

	  public String getTokenErrorDisplay(Token t) {
	    if (!verboseErrors) {
	      String s = t.getText();
	      if ( s==null ) {
	        if ( t.getType()==Token.EOF ) {
	          s = "<EOF>";
	        }
	        else {
	          s = "<"+tokenNames[t.getType()]+">";
	        }
	      }
	      s = s.replaceAll("\n","\\\\n");
	      s = s.replaceAll("\r","\\\\r");
	      s = s.replaceAll("\t","\\\\t");
	      return "'"+s+"'";
	    }

	    CommonToken ct = (CommonToken)t;

	    String channelStr = "";
	    if (t.getChannel()>0) {
	      channelStr=",channel="+t.getChannel();
	    }
	    String txt = t.getText();
	    if ( txt!=null ) {
	      txt = txt.replaceAll("\n","\\\\n");
	      txt = txt.replaceAll("\r","\\\\r");
	      txt = txt.replaceAll("\t","\\\\t");
	    }
	    else {
	      txt = "<no text>";
	    }
	    return "[@"+t.getTokenIndex()+","+ct.getStartIndex()+":"+ct.getStopIndex()+"='"+txt+"',<"+tokenNames[t.getType()]+">"+channelStr+","+t.getLine()+":"+t.getCharPositionInLine()+"]";
	  }

	  public String getErrorHeader(RecognitionException e) {
	    return getSourceName()+"["+ e.line+","+e.charPositionInLine+"]";
	  }

	  private CommonTree buildTree(int type, String text, List<CommonTree> children) {
	    CommonTree root = new CommonTree(new CommonToken(type, text));
	    for (CommonTree child: children) {
	      root.addChild(child);
	    }
	    return root;
	  }

	  private CommonToken getParamListSubToken(CommonToken baseToken, String str, int typeStartIndex) {
	    CommonToken token = new CommonToken(baseToken);
	    token.setStartIndex(baseToken.getStartIndex() + typeStartIndex);

	    switch (str.charAt(typeStartIndex)) {
	      case 'Z':
	      case 'B':
	      case 'S':
	      case 'C':
	      case 'I':
	      case 'J':
	      case 'F':
	      case 'D':
	      {
	        token.setType(PRIMITIVE_TYPE);
	        token.setText(str.substring(typeStartIndex, typeStartIndex+1));
	        token.setStopIndex(baseToken.getStartIndex() + typeStartIndex);
	        break;
	      }
	      case 'L':
	      {
	        int i = typeStartIndex;
	        while (str.charAt(++i) != ';');

	        token.setType(CLASS_DESCRIPTOR);
	        token.setText(str.substring(typeStartIndex, i + 1));
	        token.setStopIndex(baseToken.getStartIndex() + i);
	        break;
	      }
	      case '[':
	      {
	        int i = typeStartIndex;
	        while (str.charAt(++i) == '[');

	        token.setType(ARRAY_TYPE_PREFIX);
	        token.setText(str.substring(typeStartIndex, i));
	        token.setStopIndex(baseToken.getStartIndex() + i - 1);
	        break;
	      }
	      default:
	        throw new RuntimeException(String.format("Invalid character '%c' in param list \"%s\" at position %d", str.charAt(typeStartIndex), str, typeStartIndex));
	    }

	    return token;
	  }

	  private CommonTree parseParamList(CommonToken paramListToken) {
	    String paramList = paramListToken.getText();
	    CommonTree root = new CommonTree();

	    int startIndex = paramListToken.getStartIndex();

	    int i=0;
	    while (i<paramList.length()) {
	      CommonToken token = getParamListSubToken(paramListToken, paramList, i);
	      root.addChild(new CommonTree(token));
	      i += token.getText().length();
	    }

	    if (root.getChildCount() == 0) {
	      return null;
	    }
	    return root;
	  }

	  private void throwOdexedInstructionException(IntStream input, String odexedInstruction)
	      throws OdexedInstructionException {
	    /*this has to be done in a separate method, otherwise java will complain about the
	    auto-generated code in the rule after the throw not being reachable*/
	    throw new OdexedInstructionException(input, odexedInstruction);
	  }


	protected static class smali_file_scope {
		boolean hasClassSpec;
		boolean hasSuperSpec;
		boolean hasSourceSpec;
		List<CommonTree> classAnnotations;
	}
	protected Stack<smali_file_scope> smali_file_stack = new Stack<smali_file_scope>();

	public static class smali_file_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "smali_file"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:428:1: smali_file : ({...}? => class_spec |{...}? => super_spec | implements_spec |{...}? => source_spec | method | field | annotation )+ EOF -> ^( I_CLASS_DEF class_spec ( super_spec )? ( implements_spec )* ( source_spec )? ^( I_METHODS ( method )* ) ^( I_FIELDS ( field )* ) ) ;
	public final smaliParser.smali_file_return smali_file() throws RecognitionException {
		smali_file_stack.push(new smali_file_scope());
		smaliParser.smali_file_return retval = new smaliParser.smali_file_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token EOF8=null;
		ParserRuleReturnScope class_spec1 =null;
		ParserRuleReturnScope super_spec2 =null;
		ParserRuleReturnScope implements_spec3 =null;
		ParserRuleReturnScope source_spec4 =null;
		ParserRuleReturnScope method5 =null;
		ParserRuleReturnScope field6 =null;
		ParserRuleReturnScope annotation7 =null;

		CommonTree EOF8_tree=null;
		RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
		RewriteRuleSubtreeStream stream_class_spec=new RewriteRuleSubtreeStream(adaptor,"rule class_spec");
		RewriteRuleSubtreeStream stream_annotation=new RewriteRuleSubtreeStream(adaptor,"rule annotation");
		RewriteRuleSubtreeStream stream_method=new RewriteRuleSubtreeStream(adaptor,"rule method");
		RewriteRuleSubtreeStream stream_field=new RewriteRuleSubtreeStream(adaptor,"rule field");
		RewriteRuleSubtreeStream stream_super_spec=new RewriteRuleSubtreeStream(adaptor,"rule super_spec");
		RewriteRuleSubtreeStream stream_implements_spec=new RewriteRuleSubtreeStream(adaptor,"rule implements_spec");
		RewriteRuleSubtreeStream stream_source_spec=new RewriteRuleSubtreeStream(adaptor,"rule source_spec");

		 smali_file_stack.peek().hasClassSpec = smali_file_stack.peek().hasSuperSpec = smali_file_stack.peek().hasSourceSpec = false;
		    smali_file_stack.peek().classAnnotations = new ArrayList<CommonTree>();
		
		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:440:3: ( ({...}? => class_spec |{...}? => super_spec | implements_spec |{...}? => source_spec | method | field | annotation )+ EOF -> ^( I_CLASS_DEF class_spec ( super_spec )? ( implements_spec )* ( source_spec )? ^( I_METHODS ( method )* ) ^( I_FIELDS ( field )* ) ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:441:3: ({...}? => class_spec |{...}? => super_spec | implements_spec |{...}? => source_spec | method | field | annotation )+ EOF
			{
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:441:3: ({...}? => class_spec |{...}? => super_spec | implements_spec |{...}? => source_spec | method | field | annotation )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=8;
				int LA1_0 = input.LA(1);
				if ( (LA1_0==CLASS_DIRECTIVE) && ((!smali_file_stack.peek().hasClassSpec))) {
					alt1=1;
				}
				else if ( (LA1_0==SUPER_DIRECTIVE) && ((!smali_file_stack.peek().hasSuperSpec))) {
					alt1=2;
				}
				else if ( (LA1_0==IMPLEMENTS_DIRECTIVE) ) {
					alt1=3;
				}
				else if ( (LA1_0==SOURCE_DIRECTIVE) && ((!smali_file_stack.peek().hasSourceSpec))) {
					alt1=4;
				}
				else if ( (LA1_0==METHOD_DIRECTIVE) ) {
					alt1=5;
				}
				else if ( (LA1_0==FIELD_DIRECTIVE) ) {
					alt1=6;
				}
				else if ( (LA1_0==ANNOTATION_DIRECTIVE) ) {
					alt1=7;
				}

				switch (alt1) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:441:5: {...}? => class_spec
					{
					if ( !((!smali_file_stack.peek().hasClassSpec)) ) {
						throw new FailedPredicateException(input, "smali_file", "!$smali_file::hasClassSpec");
					}
					pushFollow(FOLLOW_class_spec_in_smali_file1150);
					class_spec1=class_spec();
					state._fsp--;

					stream_class_spec.add(class_spec1.getTree());
					smali_file_stack.peek().hasClassSpec = true;
					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:442:5: {...}? => super_spec
					{
					if ( !((!smali_file_stack.peek().hasSuperSpec)) ) {
						throw new FailedPredicateException(input, "smali_file", "!$smali_file::hasSuperSpec");
					}
					pushFollow(FOLLOW_super_spec_in_smali_file1161);
					super_spec2=super_spec();
					state._fsp--;

					stream_super_spec.add(super_spec2.getTree());
					smali_file_stack.peek().hasSuperSpec = true;
					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:443:5: implements_spec
					{
					pushFollow(FOLLOW_implements_spec_in_smali_file1169);
					implements_spec3=implements_spec();
					state._fsp--;

					stream_implements_spec.add(implements_spec3.getTree());
					}
					break;
				case 4 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:444:5: {...}? => source_spec
					{
					if ( !((!smali_file_stack.peek().hasSourceSpec)) ) {
						throw new FailedPredicateException(input, "smali_file", "!$smali_file::hasSourceSpec");
					}
					pushFollow(FOLLOW_source_spec_in_smali_file1178);
					source_spec4=source_spec();
					state._fsp--;

					stream_source_spec.add(source_spec4.getTree());
					smali_file_stack.peek().hasSourceSpec = true;
					}
					break;
				case 5 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:445:5: method
					{
					pushFollow(FOLLOW_method_in_smali_file1186);
					method5=method();
					state._fsp--;

					stream_method.add(method5.getTree());
					}
					break;
				case 6 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:446:5: field
					{
					pushFollow(FOLLOW_field_in_smali_file1192);
					field6=field();
					state._fsp--;

					stream_field.add(field6.getTree());
					}
					break;
				case 7 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:447:5: annotation
					{
					pushFollow(FOLLOW_annotation_in_smali_file1198);
					annotation7=annotation();
					state._fsp--;

					stream_annotation.add(annotation7.getTree());
					smali_file_stack.peek().classAnnotations.add((annotation7!=null?((CommonTree)annotation7.getTree()):null));
					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			EOF8=(Token)match(input,EOF,FOLLOW_EOF_in_smali_file1209);
			stream_EOF.add(EOF8);


			    if (!smali_file_stack.peek().hasClassSpec) {
			      throw new SemanticException(input, "The file must contain a .class directive");
			    }

			    if (!smali_file_stack.peek().hasSuperSpec) {
			      if (!(class_spec1!=null?((smaliParser.class_spec_return)class_spec1).className:null).equals("Ljava/lang/Object;")) {
			        throw new SemanticException(input, "The file must contain a .super directive");
			      }
			    }
			
			// AST REWRITE
			// elements: field, method, source_spec, super_spec, class_spec, implements_spec
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 461:3: -> ^( I_CLASS_DEF class_spec ( super_spec )? ( implements_spec )* ( source_spec )? ^( I_METHODS ( method )* ) ^( I_FIELDS ( field )* ) )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:461:6: ^( I_CLASS_DEF class_spec ( super_spec )? ( implements_spec )* ( source_spec )? ^( I_METHODS ( method )* ) ^( I_FIELDS ( field )* ) )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_CLASS_DEF, "I_CLASS_DEF"), root_1);
				adaptor.addChild(root_1, stream_class_spec.nextTree());
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:463:8: ( super_spec )?
				if ( stream_super_spec.hasNext() ) {
					adaptor.addChild(root_1, stream_super_spec.nextTree());
				}
				stream_super_spec.reset();

				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:464:8: ( implements_spec )*
				while ( stream_implements_spec.hasNext() ) {
					adaptor.addChild(root_1, stream_implements_spec.nextTree());
				}
				stream_implements_spec.reset();

				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:465:8: ( source_spec )?
				if ( stream_source_spec.hasNext() ) {
					adaptor.addChild(root_1, stream_source_spec.nextTree());
				}
				stream_source_spec.reset();

				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:466:8: ^( I_METHODS ( method )* )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_METHODS, "I_METHODS"), root_2);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:466:20: ( method )*
				while ( stream_method.hasNext() ) {
					adaptor.addChild(root_2, stream_method.nextTree());
				}
				stream_method.reset();

				adaptor.addChild(root_1, root_2);
				}

				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:466:29: ^( I_FIELDS ( field )* )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_FIELDS, "I_FIELDS"), root_2);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:466:40: ( field )*
				while ( stream_field.hasNext() ) {
					adaptor.addChild(root_2, stream_field.nextTree());
				}
				stream_field.reset();

				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_1, buildTree(I_ANNOTATIONS, "I_ANNOTATIONS", smali_file_stack.peek().classAnnotations));
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			smali_file_stack.pop();
		}
		return retval;
	}
	// $ANTLR end "smali_file"


	public static class class_spec_return extends ParserRuleReturnScope {
		public String className;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "class_spec"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:468:1: class_spec returns [String className] : CLASS_DIRECTIVE access_list CLASS_DESCRIPTOR -> CLASS_DESCRIPTOR access_list ;
	public final smaliParser.class_spec_return class_spec() throws RecognitionException {
		smaliParser.class_spec_return retval = new smaliParser.class_spec_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token CLASS_DIRECTIVE9=null;
		Token CLASS_DESCRIPTOR11=null;
		ParserRuleReturnScope access_list10 =null;

		CommonTree CLASS_DIRECTIVE9_tree=null;
		CommonTree CLASS_DESCRIPTOR11_tree=null;
		RewriteRuleTokenStream stream_CLASS_DESCRIPTOR=new RewriteRuleTokenStream(adaptor,"token CLASS_DESCRIPTOR");
		RewriteRuleTokenStream stream_CLASS_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token CLASS_DIRECTIVE");
		RewriteRuleSubtreeStream stream_access_list=new RewriteRuleSubtreeStream(adaptor,"rule access_list");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:469:3: ( CLASS_DIRECTIVE access_list CLASS_DESCRIPTOR -> CLASS_DESCRIPTOR access_list )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:469:5: CLASS_DIRECTIVE access_list CLASS_DESCRIPTOR
			{
			CLASS_DIRECTIVE9=(Token)match(input,CLASS_DIRECTIVE,FOLLOW_CLASS_DIRECTIVE_in_class_spec1296);
			stream_CLASS_DIRECTIVE.add(CLASS_DIRECTIVE9);

			pushFollow(FOLLOW_access_list_in_class_spec1298);
			access_list10=access_list();
			state._fsp--;

			stream_access_list.add(access_list10.getTree());
			CLASS_DESCRIPTOR11=(Token)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_class_spec1300);
			stream_CLASS_DESCRIPTOR.add(CLASS_DESCRIPTOR11);

			retval.className = (CLASS_DESCRIPTOR11!=null?CLASS_DESCRIPTOR11.getText():null);
			// AST REWRITE
			// elements: access_list, CLASS_DESCRIPTOR
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 469:89: -> CLASS_DESCRIPTOR access_list
			{
				adaptor.addChild(root_0, stream_CLASS_DESCRIPTOR.nextNode());
				adaptor.addChild(root_0, stream_access_list.nextTree());
			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "class_spec"


	public static class super_spec_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "super_spec"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:471:1: super_spec : SUPER_DIRECTIVE CLASS_DESCRIPTOR -> ^( I_SUPER[$start, \"I_SUPER\"] CLASS_DESCRIPTOR ) ;
	public final smaliParser.super_spec_return super_spec() throws RecognitionException {
		smaliParser.super_spec_return retval = new smaliParser.super_spec_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token SUPER_DIRECTIVE12=null;
		Token CLASS_DESCRIPTOR13=null;

		CommonTree SUPER_DIRECTIVE12_tree=null;
		CommonTree CLASS_DESCRIPTOR13_tree=null;
		RewriteRuleTokenStream stream_CLASS_DESCRIPTOR=new RewriteRuleTokenStream(adaptor,"token CLASS_DESCRIPTOR");
		RewriteRuleTokenStream stream_SUPER_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token SUPER_DIRECTIVE");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:472:3: ( SUPER_DIRECTIVE CLASS_DESCRIPTOR -> ^( I_SUPER[$start, \"I_SUPER\"] CLASS_DESCRIPTOR ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:472:5: SUPER_DIRECTIVE CLASS_DESCRIPTOR
			{
			SUPER_DIRECTIVE12=(Token)match(input,SUPER_DIRECTIVE,FOLLOW_SUPER_DIRECTIVE_in_super_spec1318);
			stream_SUPER_DIRECTIVE.add(SUPER_DIRECTIVE12);

			CLASS_DESCRIPTOR13=(Token)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_super_spec1320);
			stream_CLASS_DESCRIPTOR.add(CLASS_DESCRIPTOR13);

			// AST REWRITE
			// elements: CLASS_DESCRIPTOR
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 472:38: -> ^( I_SUPER[$start, \"I_SUPER\"] CLASS_DESCRIPTOR )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:472:41: ^( I_SUPER[$start, \"I_SUPER\"] CLASS_DESCRIPTOR )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_SUPER, (retval.start), "I_SUPER"), root_1);
				adaptor.addChild(root_1, stream_CLASS_DESCRIPTOR.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "super_spec"


	public static class implements_spec_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "implements_spec"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:474:1: implements_spec : IMPLEMENTS_DIRECTIVE CLASS_DESCRIPTOR -> ^( I_IMPLEMENTS[$start, \"I_IMPLEMENTS\"] CLASS_DESCRIPTOR ) ;
	public final smaliParser.implements_spec_return implements_spec() throws RecognitionException {
		smaliParser.implements_spec_return retval = new smaliParser.implements_spec_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token IMPLEMENTS_DIRECTIVE14=null;
		Token CLASS_DESCRIPTOR15=null;

		CommonTree IMPLEMENTS_DIRECTIVE14_tree=null;
		CommonTree CLASS_DESCRIPTOR15_tree=null;
		RewriteRuleTokenStream stream_IMPLEMENTS_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token IMPLEMENTS_DIRECTIVE");
		RewriteRuleTokenStream stream_CLASS_DESCRIPTOR=new RewriteRuleTokenStream(adaptor,"token CLASS_DESCRIPTOR");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:475:3: ( IMPLEMENTS_DIRECTIVE CLASS_DESCRIPTOR -> ^( I_IMPLEMENTS[$start, \"I_IMPLEMENTS\"] CLASS_DESCRIPTOR ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:475:5: IMPLEMENTS_DIRECTIVE CLASS_DESCRIPTOR
			{
			IMPLEMENTS_DIRECTIVE14=(Token)match(input,IMPLEMENTS_DIRECTIVE,FOLLOW_IMPLEMENTS_DIRECTIVE_in_implements_spec1339);
			stream_IMPLEMENTS_DIRECTIVE.add(IMPLEMENTS_DIRECTIVE14);

			CLASS_DESCRIPTOR15=(Token)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_implements_spec1341);
			stream_CLASS_DESCRIPTOR.add(CLASS_DESCRIPTOR15);

			// AST REWRITE
			// elements: CLASS_DESCRIPTOR
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 475:43: -> ^( I_IMPLEMENTS[$start, \"I_IMPLEMENTS\"] CLASS_DESCRIPTOR )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:475:46: ^( I_IMPLEMENTS[$start, \"I_IMPLEMENTS\"] CLASS_DESCRIPTOR )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_IMPLEMENTS, (retval.start), "I_IMPLEMENTS"), root_1);
				adaptor.addChild(root_1, stream_CLASS_DESCRIPTOR.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "implements_spec"


	public static class source_spec_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "source_spec"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:477:1: source_spec : SOURCE_DIRECTIVE STRING_LITERAL -> ^( I_SOURCE[$start, \"I_SOURCE\"] STRING_LITERAL ) ;
	public final smaliParser.source_spec_return source_spec() throws RecognitionException {
		smaliParser.source_spec_return retval = new smaliParser.source_spec_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token SOURCE_DIRECTIVE16=null;
		Token STRING_LITERAL17=null;

		CommonTree SOURCE_DIRECTIVE16_tree=null;
		CommonTree STRING_LITERAL17_tree=null;
		RewriteRuleTokenStream stream_SOURCE_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token SOURCE_DIRECTIVE");
		RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:478:3: ( SOURCE_DIRECTIVE STRING_LITERAL -> ^( I_SOURCE[$start, \"I_SOURCE\"] STRING_LITERAL ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:478:5: SOURCE_DIRECTIVE STRING_LITERAL
			{
			SOURCE_DIRECTIVE16=(Token)match(input,SOURCE_DIRECTIVE,FOLLOW_SOURCE_DIRECTIVE_in_source_spec1360);
			stream_SOURCE_DIRECTIVE.add(SOURCE_DIRECTIVE16);

			STRING_LITERAL17=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_source_spec1362);
			stream_STRING_LITERAL.add(STRING_LITERAL17);

			// AST REWRITE
			// elements: STRING_LITERAL
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 478:37: -> ^( I_SOURCE[$start, \"I_SOURCE\"] STRING_LITERAL )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:478:40: ^( I_SOURCE[$start, \"I_SOURCE\"] STRING_LITERAL )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_SOURCE, (retval.start), "I_SOURCE"), root_1);
				adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "source_spec"


	public static class access_list_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "access_list"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:480:1: access_list : ( ACCESS_SPEC )* -> ^( I_ACCESS_LIST[$start,\"I_ACCESS_LIST\"] ( ACCESS_SPEC )* ) ;
	public final smaliParser.access_list_return access_list() throws RecognitionException {
		smaliParser.access_list_return retval = new smaliParser.access_list_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ACCESS_SPEC18=null;

		CommonTree ACCESS_SPEC18_tree=null;
		RewriteRuleTokenStream stream_ACCESS_SPEC=new RewriteRuleTokenStream(adaptor,"token ACCESS_SPEC");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:481:3: ( ( ACCESS_SPEC )* -> ^( I_ACCESS_LIST[$start,\"I_ACCESS_LIST\"] ( ACCESS_SPEC )* ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:481:5: ( ACCESS_SPEC )*
			{
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:481:5: ( ACCESS_SPEC )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==ACCESS_SPEC) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:481:5: ACCESS_SPEC
					{
					ACCESS_SPEC18=(Token)match(input,ACCESS_SPEC,FOLLOW_ACCESS_SPEC_in_access_list1381);
					stream_ACCESS_SPEC.add(ACCESS_SPEC18);

					}
					break;

				default :
					break loop2;
				}
			}

			// AST REWRITE
			// elements: ACCESS_SPEC
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 481:18: -> ^( I_ACCESS_LIST[$start,\"I_ACCESS_LIST\"] ( ACCESS_SPEC )* )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:481:21: ^( I_ACCESS_LIST[$start,\"I_ACCESS_LIST\"] ( ACCESS_SPEC )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ACCESS_LIST, (retval.start), "I_ACCESS_LIST"), root_1);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:481:61: ( ACCESS_SPEC )*
				while ( stream_ACCESS_SPEC.hasNext() ) {
					adaptor.addChild(root_1, stream_ACCESS_SPEC.nextNode());
				}
				stream_ACCESS_SPEC.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "access_list"


	public static class access_or_restriction_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "access_or_restriction"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:483:1: access_or_restriction : ( ACCESS_SPEC | HIDDENAPI_RESTRICTION );
	public final smaliParser.access_or_restriction_return access_or_restriction() throws RecognitionException {
		smaliParser.access_or_restriction_return retval = new smaliParser.access_or_restriction_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token set19=null;

		CommonTree set19_tree=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:484:3: ( ACCESS_SPEC | HIDDENAPI_RESTRICTION )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:
			{
			root_0 = (CommonTree)adaptor.nil();


			set19=input.LT(1);
			if ( input.LA(1)==ACCESS_SPEC||input.LA(1)==HIDDENAPI_RESTRICTION ) {
				input.consume();
				adaptor.addChild(root_0, (CommonTree)adaptor.create(set19));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "access_or_restriction"


	public static class access_or_restriction_list_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "access_or_restriction_list"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:486:1: access_or_restriction_list : ( access_or_restriction )* -> ^( I_ACCESS_OR_RESTRICTION_LIST[$start,\"I_ACCESS_AND_RESTRICTION_LIST\"] ( access_or_restriction )* ) ;
	public final smaliParser.access_or_restriction_list_return access_or_restriction_list() throws RecognitionException {
		smaliParser.access_or_restriction_list_return retval = new smaliParser.access_or_restriction_list_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope access_or_restriction20 =null;

		RewriteRuleSubtreeStream stream_access_or_restriction=new RewriteRuleSubtreeStream(adaptor,"rule access_or_restriction");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:487:3: ( ( access_or_restriction )* -> ^( I_ACCESS_OR_RESTRICTION_LIST[$start,\"I_ACCESS_AND_RESTRICTION_LIST\"] ( access_or_restriction )* ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:487:5: ( access_or_restriction )*
			{
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:487:5: ( access_or_restriction )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( (LA3_0==ACCESS_SPEC) ) {
					int LA3_2 = input.LA(2);
					if ( (LA3_2==ACCESS_SPEC||LA3_2==ANNOTATION_VISIBILITY||LA3_2==BOOL_LITERAL||LA3_2==DOUBLE_LITERAL_OR_ID||(LA3_2 >= FLOAT_LITERAL_OR_ID && LA3_2 <= HIDDENAPI_RESTRICTION)||(LA3_2 >= INSTRUCTION_FORMAT10t && LA3_2 <= INSTRUCTION_FORMAT10x_ODEX)||LA3_2==INSTRUCTION_FORMAT11x||LA3_2==INSTRUCTION_FORMAT12x_OR_ID||(LA3_2 >= INSTRUCTION_FORMAT21c_FIELD && LA3_2 <= INSTRUCTION_FORMAT21c_TYPE)||LA3_2==INSTRUCTION_FORMAT21t||(LA3_2 >= INSTRUCTION_FORMAT22c_FIELD && LA3_2 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA3_2 >= INSTRUCTION_FORMAT22s_OR_ID && LA3_2 <= INSTRUCTION_FORMAT22t)||LA3_2==INSTRUCTION_FORMAT23x||(LA3_2 >= INSTRUCTION_FORMAT31i_OR_ID && LA3_2 <= INSTRUCTION_FORMAT31t)||(LA3_2 >= INSTRUCTION_FORMAT35c_CALL_SITE && LA3_2 <= INSTRUCTION_FORMAT35ms_METHOD)||(LA3_2 >= INSTRUCTION_FORMAT45cc_METHOD && LA3_2 <= INSTRUCTION_FORMAT51l)||LA3_2==MEMBER_NAME||(LA3_2 >= METHOD_HANDLE_TYPE_FIELD && LA3_2 <= NULL_LITERAL)||(LA3_2 >= PARAM_LIST_OR_ID_PRIMITIVE_TYPE && LA3_2 <= PRIMITIVE_TYPE)||LA3_2==REGISTER||LA3_2==SIMPLE_NAME||(LA3_2 >= VERIFICATION_ERROR_TYPE && LA3_2 <= VOID_TYPE)) ) {
						alt3=1;
					}

				}
				else if ( (LA3_0==HIDDENAPI_RESTRICTION) ) {
					int LA3_3 = input.LA(2);
					if ( (LA3_3==ACCESS_SPEC||LA3_3==ANNOTATION_VISIBILITY||LA3_3==BOOL_LITERAL||LA3_3==DOUBLE_LITERAL_OR_ID||(LA3_3 >= FLOAT_LITERAL_OR_ID && LA3_3 <= HIDDENAPI_RESTRICTION)||(LA3_3 >= INSTRUCTION_FORMAT10t && LA3_3 <= INSTRUCTION_FORMAT10x_ODEX)||LA3_3==INSTRUCTION_FORMAT11x||LA3_3==INSTRUCTION_FORMAT12x_OR_ID||(LA3_3 >= INSTRUCTION_FORMAT21c_FIELD && LA3_3 <= INSTRUCTION_FORMAT21c_TYPE)||LA3_3==INSTRUCTION_FORMAT21t||(LA3_3 >= INSTRUCTION_FORMAT22c_FIELD && LA3_3 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA3_3 >= INSTRUCTION_FORMAT22s_OR_ID && LA3_3 <= INSTRUCTION_FORMAT22t)||LA3_3==INSTRUCTION_FORMAT23x||(LA3_3 >= INSTRUCTION_FORMAT31i_OR_ID && LA3_3 <= INSTRUCTION_FORMAT31t)||(LA3_3 >= INSTRUCTION_FORMAT35c_CALL_SITE && LA3_3 <= INSTRUCTION_FORMAT35ms_METHOD)||(LA3_3 >= INSTRUCTION_FORMAT45cc_METHOD && LA3_3 <= INSTRUCTION_FORMAT51l)||LA3_3==MEMBER_NAME||(LA3_3 >= METHOD_HANDLE_TYPE_FIELD && LA3_3 <= NULL_LITERAL)||(LA3_3 >= PARAM_LIST_OR_ID_PRIMITIVE_TYPE && LA3_3 <= PRIMITIVE_TYPE)||LA3_3==REGISTER||LA3_3==SIMPLE_NAME||(LA3_3 >= VERIFICATION_ERROR_TYPE && LA3_3 <= VOID_TYPE)) ) {
						alt3=1;
					}

				}

				switch (alt3) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:487:5: access_or_restriction
					{
					pushFollow(FOLLOW_access_or_restriction_in_access_or_restriction_list1416);
					access_or_restriction20=access_or_restriction();
					state._fsp--;

					stream_access_or_restriction.add(access_or_restriction20.getTree());
					}
					break;

				default :
					break loop3;
				}
			}

			// AST REWRITE
			// elements: access_or_restriction
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 488:3: -> ^( I_ACCESS_OR_RESTRICTION_LIST[$start,\"I_ACCESS_AND_RESTRICTION_LIST\"] ( access_or_restriction )* )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:488:6: ^( I_ACCESS_OR_RESTRICTION_LIST[$start,\"I_ACCESS_AND_RESTRICTION_LIST\"] ( access_or_restriction )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ACCESS_OR_RESTRICTION_LIST, (retval.start), "I_ACCESS_AND_RESTRICTION_LIST"), root_1);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:488:77: ( access_or_restriction )*
				while ( stream_access_or_restriction.hasNext() ) {
					adaptor.addChild(root_1, stream_access_or_restriction.nextTree());
				}
				stream_access_or_restriction.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "access_or_restriction_list"


	public static class field_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "field"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:495:1: field : FIELD_DIRECTIVE access_or_restriction_list member_name COLON nonvoid_type_descriptor ( EQUAL literal )? ( ({...}? annotation )* ( END_FIELD_DIRECTIVE -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_or_restriction_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ( annotation )* ) ) | -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_or_restriction_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ) ) ) ) ;
	public final smaliParser.field_return field() throws RecognitionException {
		smaliParser.field_return retval = new smaliParser.field_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token FIELD_DIRECTIVE21=null;
		Token COLON24=null;
		Token EQUAL26=null;
		Token END_FIELD_DIRECTIVE29=null;
		ParserRuleReturnScope access_or_restriction_list22 =null;
		ParserRuleReturnScope member_name23 =null;
		ParserRuleReturnScope nonvoid_type_descriptor25 =null;
		ParserRuleReturnScope literal27 =null;
		ParserRuleReturnScope annotation28 =null;

		CommonTree FIELD_DIRECTIVE21_tree=null;
		CommonTree COLON24_tree=null;
		CommonTree EQUAL26_tree=null;
		CommonTree END_FIELD_DIRECTIVE29_tree=null;
		RewriteRuleTokenStream stream_END_FIELD_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token END_FIELD_DIRECTIVE");
		RewriteRuleTokenStream stream_EQUAL=new RewriteRuleTokenStream(adaptor,"token EQUAL");
		RewriteRuleTokenStream stream_FIELD_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token FIELD_DIRECTIVE");
		RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
		RewriteRuleSubtreeStream stream_annotation=new RewriteRuleSubtreeStream(adaptor,"rule annotation");
		RewriteRuleSubtreeStream stream_access_or_restriction_list=new RewriteRuleSubtreeStream(adaptor,"rule access_or_restriction_list");
		RewriteRuleSubtreeStream stream_nonvoid_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule nonvoid_type_descriptor");
		RewriteRuleSubtreeStream stream_member_name=new RewriteRuleSubtreeStream(adaptor,"rule member_name");
		RewriteRuleSubtreeStream stream_literal=new RewriteRuleSubtreeStream(adaptor,"rule literal");

		List<CommonTree> annotations = new ArrayList<CommonTree>();
		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:497:3: ( FIELD_DIRECTIVE access_or_restriction_list member_name COLON nonvoid_type_descriptor ( EQUAL literal )? ( ({...}? annotation )* ( END_FIELD_DIRECTIVE -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_or_restriction_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ( annotation )* ) ) | -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_or_restriction_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ) ) ) ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:497:5: FIELD_DIRECTIVE access_or_restriction_list member_name COLON nonvoid_type_descriptor ( EQUAL literal )? ( ({...}? annotation )* ( END_FIELD_DIRECTIVE -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_or_restriction_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ( annotation )* ) ) | -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_or_restriction_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ) ) ) )
			{
			FIELD_DIRECTIVE21=(Token)match(input,FIELD_DIRECTIVE,FOLLOW_FIELD_DIRECTIVE_in_field1449);
			stream_FIELD_DIRECTIVE.add(FIELD_DIRECTIVE21);

			pushFollow(FOLLOW_access_or_restriction_list_in_field1451);
			access_or_restriction_list22=access_or_restriction_list();
			state._fsp--;

			stream_access_or_restriction_list.add(access_or_restriction_list22.getTree());
			pushFollow(FOLLOW_member_name_in_field1453);
			member_name23=member_name();
			state._fsp--;

			stream_member_name.add(member_name23.getTree());
			COLON24=(Token)match(input,COLON,FOLLOW_COLON_in_field1455);
			stream_COLON.add(COLON24);

			pushFollow(FOLLOW_nonvoid_type_descriptor_in_field1457);
			nonvoid_type_descriptor25=nonvoid_type_descriptor();
			state._fsp--;

			stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor25.getTree());
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:497:90: ( EQUAL literal )?
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==EQUAL) ) {
				alt4=1;
			}
			switch (alt4) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:497:91: EQUAL literal
					{
					EQUAL26=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_field1460);
					stream_EQUAL.add(EQUAL26);

					pushFollow(FOLLOW_literal_in_field1462);
					literal27=literal();
					state._fsp--;

					stream_literal.add(literal27.getTree());
					}
					break;

			}

			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:498:5: ( ({...}? annotation )* ( END_FIELD_DIRECTIVE -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_or_restriction_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ( annotation )* ) ) | -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_or_restriction_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ) ) ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:498:7: ({...}? annotation )* ( END_FIELD_DIRECTIVE -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_or_restriction_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ( annotation )* ) ) | -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_or_restriction_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ) ) )
			{
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:498:7: ({...}? annotation )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==ANNOTATION_DIRECTIVE) ) {
					int LA5_9 = input.LA(2);
					if ( ((input.LA(1) == ANNOTATION_DIRECTIVE)) ) {
						alt5=1;
					}

				}

				switch (alt5) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:498:8: {...}? annotation
					{
					if ( !((input.LA(1) == ANNOTATION_DIRECTIVE)) ) {
						throw new FailedPredicateException(input, "field", "input.LA(1) == ANNOTATION_DIRECTIVE");
					}
					pushFollow(FOLLOW_annotation_in_field1475);
					annotation28=annotation();
					state._fsp--;

					stream_annotation.add(annotation28.getTree());
					annotations.add((annotation28!=null?((CommonTree)annotation28.getTree()):null));
					}
					break;

				default :
					break loop5;
				}
			}

			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:499:7: ( END_FIELD_DIRECTIVE -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_or_restriction_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ( annotation )* ) ) | -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_or_restriction_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ) ) )
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( (LA6_0==END_FIELD_DIRECTIVE) ) {
				alt6=1;
			}
			else if ( (LA6_0==EOF||LA6_0==ANNOTATION_DIRECTIVE||LA6_0==CLASS_DIRECTIVE||LA6_0==FIELD_DIRECTIVE||LA6_0==IMPLEMENTS_DIRECTIVE||LA6_0==METHOD_DIRECTIVE||LA6_0==SOURCE_DIRECTIVE||LA6_0==SUPER_DIRECTIVE) ) {
				alt6=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				throw nvae;
			}

			switch (alt6) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:499:9: END_FIELD_DIRECTIVE
					{
					END_FIELD_DIRECTIVE29=(Token)match(input,END_FIELD_DIRECTIVE,FOLLOW_END_FIELD_DIRECTIVE_in_field1489);
					stream_END_FIELD_DIRECTIVE.add(END_FIELD_DIRECTIVE29);

					// AST REWRITE
					// elements: literal, access_or_restriction_list, nonvoid_type_descriptor, member_name, annotation
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 500:9: -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_or_restriction_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ( annotation )* ) )
					{
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:500:12: ^( I_FIELD[$start, \"I_FIELD\"] member_name access_or_restriction_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ( annotation )* ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_FIELD, (retval.start), "I_FIELD"), root_1);
						adaptor.addChild(root_1, stream_member_name.nextTree());
						adaptor.addChild(root_1, stream_access_or_restriction_list.nextTree());
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:500:80: ^( I_FIELD_TYPE nonvoid_type_descriptor )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_FIELD_TYPE, "I_FIELD_TYPE"), root_2);
						adaptor.addChild(root_2, stream_nonvoid_type_descriptor.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:500:120: ( ^( I_FIELD_INITIAL_VALUE literal ) )?
						if ( stream_literal.hasNext() ) {
							// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:500:120: ^( I_FIELD_INITIAL_VALUE literal )
							{
							CommonTree root_2 = (CommonTree)adaptor.nil();
							root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_FIELD_INITIAL_VALUE, "I_FIELD_INITIAL_VALUE"), root_2);
							adaptor.addChild(root_2, stream_literal.nextTree());
							adaptor.addChild(root_1, root_2);
							}

						}
						stream_literal.reset();

						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:500:154: ^( I_ANNOTATIONS ( annotation )* )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ANNOTATIONS, "I_ANNOTATIONS"), root_2);
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:500:170: ( annotation )*
						while ( stream_annotation.hasNext() ) {
							adaptor.addChild(root_2, stream_annotation.nextTree());
						}
						stream_annotation.reset();

						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:501:21:
					{
					smali_file_stack.peek().classAnnotations.addAll(annotations);
					// AST REWRITE
					// elements: literal, nonvoid_type_descriptor, access_or_restriction_list, member_name
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 502:9: -> ^( I_FIELD[$start, \"I_FIELD\"] member_name access_or_restriction_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ) )
					{
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:502:12: ^( I_FIELD[$start, \"I_FIELD\"] member_name access_or_restriction_list ^( I_FIELD_TYPE nonvoid_type_descriptor ) ( ^( I_FIELD_INITIAL_VALUE literal ) )? ^( I_ANNOTATIONS ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_FIELD, (retval.start), "I_FIELD"), root_1);
						adaptor.addChild(root_1, stream_member_name.nextTree());
						adaptor.addChild(root_1, stream_access_or_restriction_list.nextTree());
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:502:80: ^( I_FIELD_TYPE nonvoid_type_descriptor )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_FIELD_TYPE, "I_FIELD_TYPE"), root_2);
						adaptor.addChild(root_2, stream_nonvoid_type_descriptor.nextTree());
						adaptor.addChild(root_1, root_2);
						}

						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:502:120: ( ^( I_FIELD_INITIAL_VALUE literal ) )?
						if ( stream_literal.hasNext() ) {
							// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:502:120: ^( I_FIELD_INITIAL_VALUE literal )
							{
							CommonTree root_2 = (CommonTree)adaptor.nil();
							root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_FIELD_INITIAL_VALUE, "I_FIELD_INITIAL_VALUE"), root_2);
							adaptor.addChild(root_2, stream_literal.nextTree());
							adaptor.addChild(root_1, root_2);
							}

						}
						stream_literal.reset();

						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:502:154: ^( I_ANNOTATIONS )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ANNOTATIONS, "I_ANNOTATIONS"), root_2);
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "field"


	public static class method_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "method"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:506:1: method : METHOD_DIRECTIVE access_or_restriction_list member_name method_prototype statements_and_directives END_METHOD_DIRECTIVE -> ^( I_METHOD[$start, \"I_METHOD\"] member_name method_prototype access_or_restriction_list statements_and_directives ) ;
	public final smaliParser.method_return method() throws RecognitionException {
		smaliParser.method_return retval = new smaliParser.method_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token METHOD_DIRECTIVE30=null;
		Token END_METHOD_DIRECTIVE35=null;
		ParserRuleReturnScope access_or_restriction_list31 =null;
		ParserRuleReturnScope member_name32 =null;
		ParserRuleReturnScope method_prototype33 =null;
		ParserRuleReturnScope statements_and_directives34 =null;

		CommonTree METHOD_DIRECTIVE30_tree=null;
		CommonTree END_METHOD_DIRECTIVE35_tree=null;
		RewriteRuleTokenStream stream_END_METHOD_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token END_METHOD_DIRECTIVE");
		RewriteRuleTokenStream stream_METHOD_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token METHOD_DIRECTIVE");
		RewriteRuleSubtreeStream stream_access_or_restriction_list=new RewriteRuleSubtreeStream(adaptor,"rule access_or_restriction_list");
		RewriteRuleSubtreeStream stream_method_prototype=new RewriteRuleSubtreeStream(adaptor,"rule method_prototype");
		RewriteRuleSubtreeStream stream_member_name=new RewriteRuleSubtreeStream(adaptor,"rule member_name");
		RewriteRuleSubtreeStream stream_statements_and_directives=new RewriteRuleSubtreeStream(adaptor,"rule statements_and_directives");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:507:3: ( METHOD_DIRECTIVE access_or_restriction_list member_name method_prototype statements_and_directives END_METHOD_DIRECTIVE -> ^( I_METHOD[$start, \"I_METHOD\"] member_name method_prototype access_or_restriction_list statements_and_directives ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:507:5: METHOD_DIRECTIVE access_or_restriction_list member_name method_prototype statements_and_directives END_METHOD_DIRECTIVE
			{
			METHOD_DIRECTIVE30=(Token)match(input,METHOD_DIRECTIVE,FOLLOW_METHOD_DIRECTIVE_in_method1600);
			stream_METHOD_DIRECTIVE.add(METHOD_DIRECTIVE30);

			pushFollow(FOLLOW_access_or_restriction_list_in_method1602);
			access_or_restriction_list31=access_or_restriction_list();
			state._fsp--;

			stream_access_or_restriction_list.add(access_or_restriction_list31.getTree());
			pushFollow(FOLLOW_member_name_in_method1604);
			member_name32=member_name();
			state._fsp--;

			stream_member_name.add(member_name32.getTree());
			pushFollow(FOLLOW_method_prototype_in_method1606);
			method_prototype33=method_prototype();
			state._fsp--;

			stream_method_prototype.add(method_prototype33.getTree());
			pushFollow(FOLLOW_statements_and_directives_in_method1608);
			statements_and_directives34=statements_and_directives();
			state._fsp--;

			stream_statements_and_directives.add(statements_and_directives34.getTree());
			END_METHOD_DIRECTIVE35=(Token)match(input,END_METHOD_DIRECTIVE,FOLLOW_END_METHOD_DIRECTIVE_in_method1614);
			stream_END_METHOD_DIRECTIVE.add(END_METHOD_DIRECTIVE35);

			// AST REWRITE
			// elements: statements_and_directives, method_prototype, member_name, access_or_restriction_list
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 509:5: -> ^( I_METHOD[$start, \"I_METHOD\"] member_name method_prototype access_or_restriction_list statements_and_directives )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:509:8: ^( I_METHOD[$start, \"I_METHOD\"] member_name method_prototype access_or_restriction_list statements_and_directives )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_METHOD, (retval.start), "I_METHOD"), root_1);
				adaptor.addChild(root_1, stream_member_name.nextTree());
				adaptor.addChild(root_1, stream_method_prototype.nextTree());
				adaptor.addChild(root_1, stream_access_or_restriction_list.nextTree());
				adaptor.addChild(root_1, stream_statements_and_directives.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "method"


	protected static class statements_and_directives_scope {
		boolean hasRegistersDirective;
		List<CommonTree> methodAnnotations;
	}
	protected Stack<statements_and_directives_scope> statements_and_directives_stack = new Stack<statements_and_directives_scope>();

	public static class statements_and_directives_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "statements_and_directives"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:511:1: statements_and_directives : ( ordered_method_item | registers_directive | catch_directive | catchall_directive | parameter_directive | annotation )* -> ( registers_directive )? ^( I_ORDERED_METHOD_ITEMS ( ordered_method_item )* ) ^( I_CATCHES ( catch_directive )* ( catchall_directive )* ) ^( I_PARAMETERS ( parameter_directive )* ) ;
	public final smaliParser.statements_and_directives_return statements_and_directives() throws RecognitionException {
		statements_and_directives_stack.push(new statements_and_directives_scope());
		smaliParser.statements_and_directives_return retval = new smaliParser.statements_and_directives_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope ordered_method_item36 =null;
		ParserRuleReturnScope registers_directive37 =null;
		ParserRuleReturnScope catch_directive38 =null;
		ParserRuleReturnScope catchall_directive39 =null;
		ParserRuleReturnScope parameter_directive40 =null;
		ParserRuleReturnScope annotation41 =null;

		RewriteRuleSubtreeStream stream_annotation=new RewriteRuleSubtreeStream(adaptor,"rule annotation");
		RewriteRuleSubtreeStream stream_catchall_directive=new RewriteRuleSubtreeStream(adaptor,"rule catchall_directive");
		RewriteRuleSubtreeStream stream_registers_directive=new RewriteRuleSubtreeStream(adaptor,"rule registers_directive");
		RewriteRuleSubtreeStream stream_catch_directive=new RewriteRuleSubtreeStream(adaptor,"rule catch_directive");
		RewriteRuleSubtreeStream stream_ordered_method_item=new RewriteRuleSubtreeStream(adaptor,"rule ordered_method_item");
		RewriteRuleSubtreeStream stream_parameter_directive=new RewriteRuleSubtreeStream(adaptor,"rule parameter_directive");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:517:3: ( ( ordered_method_item | registers_directive | catch_directive | catchall_directive | parameter_directive | annotation )* -> ( registers_directive )? ^( I_ORDERED_METHOD_ITEMS ( ordered_method_item )* ) ^( I_CATCHES ( catch_directive )* ( catchall_directive )* ) ^( I_PARAMETERS ( parameter_directive )* ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:517:5: ( ordered_method_item | registers_directive | catch_directive | catchall_directive | parameter_directive | annotation )*
			{

			      statements_and_directives_stack.peek().hasRegistersDirective = false;
			      statements_and_directives_stack.peek().methodAnnotations = new ArrayList<CommonTree>();
			
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:521:5: ( ordered_method_item | registers_directive | catch_directive | catchall_directive | parameter_directive | annotation )*
			loop7:
			while (true) {
				int alt7=7;
				switch ( input.LA(1) ) {
				case ARRAY_DATA_DIRECTIVE:
				case COLON:
				case END_LOCAL_DIRECTIVE:
				case EPILOGUE_DIRECTIVE:
				case INSTRUCTION_FORMAT10t:
				case INSTRUCTION_FORMAT10x:
				case INSTRUCTION_FORMAT10x_ODEX:
				case INSTRUCTION_FORMAT11n:
				case INSTRUCTION_FORMAT11x:
				case INSTRUCTION_FORMAT12x:
				case INSTRUCTION_FORMAT12x_OR_ID:
				case INSTRUCTION_FORMAT20bc:
				case INSTRUCTION_FORMAT20t:
				case INSTRUCTION_FORMAT21c_FIELD:
				case INSTRUCTION_FORMAT21c_FIELD_ODEX:
				case INSTRUCTION_FORMAT21c_METHOD_HANDLE:
				case INSTRUCTION_FORMAT21c_METHOD_TYPE:
				case INSTRUCTION_FORMAT21c_STRING:
				case INSTRUCTION_FORMAT21c_TYPE:
				case INSTRUCTION_FORMAT21ih:
				case INSTRUCTION_FORMAT21lh:
				case INSTRUCTION_FORMAT21s:
				case INSTRUCTION_FORMAT21t:
				case INSTRUCTION_FORMAT22b:
				case INSTRUCTION_FORMAT22c_FIELD:
				case INSTRUCTION_FORMAT22c_FIELD_ODEX:
				case INSTRUCTION_FORMAT22c_TYPE:
				case INSTRUCTION_FORMAT22cs_FIELD:
				case INSTRUCTION_FORMAT22s:
				case INSTRUCTION_FORMAT22s_OR_ID:
				case INSTRUCTION_FORMAT22t:
				case INSTRUCTION_FORMAT22x:
				case INSTRUCTION_FORMAT23x:
				case INSTRUCTION_FORMAT30t:
				case INSTRUCTION_FORMAT31c:
				case INSTRUCTION_FORMAT31i:
				case INSTRUCTION_FORMAT31i_OR_ID:
				case INSTRUCTION_FORMAT31t:
				case INSTRUCTION_FORMAT32x:
				case INSTRUCTION_FORMAT35c_CALL_SITE:
				case INSTRUCTION_FORMAT35c_METHOD:
				case INSTRUCTION_FORMAT35c_METHOD_ODEX:
				case INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE:
				case INSTRUCTION_FORMAT35c_TYPE:
				case INSTRUCTION_FORMAT35mi_METHOD:
				case INSTRUCTION_FORMAT35ms_METHOD:
				case INSTRUCTION_FORMAT3rc_CALL_SITE:
				case INSTRUCTION_FORMAT3rc_METHOD:
				case INSTRUCTION_FORMAT3rc_METHOD_ODEX:
				case INSTRUCTION_FORMAT3rc_TYPE:
				case INSTRUCTION_FORMAT3rmi_METHOD:
				case INSTRUCTION_FORMAT3rms_METHOD:
				case INSTRUCTION_FORMAT45cc_METHOD:
				case INSTRUCTION_FORMAT4rcc_METHOD:
				case INSTRUCTION_FORMAT51l:
				case LINE_DIRECTIVE:
				case LOCAL_DIRECTIVE:
				case PACKED_SWITCH_DIRECTIVE:
				case PROLOGUE_DIRECTIVE:
				case RESTART_LOCAL_DIRECTIVE:
				case SOURCE_DIRECTIVE:
				case SPARSE_SWITCH_DIRECTIVE:
					{
					alt7=1;
					}
					break;
				case LOCALS_DIRECTIVE:
				case REGISTERS_DIRECTIVE:
					{
					alt7=2;
					}
					break;
				case CATCH_DIRECTIVE:
					{
					alt7=3;
					}
					break;
				case CATCHALL_DIRECTIVE:
					{
					alt7=4;
					}
					break;
				case PARAMETER_DIRECTIVE:
					{
					alt7=5;
					}
					break;
				case ANNOTATION_DIRECTIVE:
					{
					alt7=6;
					}
					break;
				}
				switch (alt7) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:521:7: ordered_method_item
					{
					pushFollow(FOLLOW_ordered_method_item_in_statements_and_directives1659);
					ordered_method_item36=ordered_method_item();
					state._fsp--;

					stream_ordered_method_item.add(ordered_method_item36.getTree());
					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:522:7: registers_directive
					{
					pushFollow(FOLLOW_registers_directive_in_statements_and_directives1667);
					registers_directive37=registers_directive();
					state._fsp--;

					stream_registers_directive.add(registers_directive37.getTree());
					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:523:7: catch_directive
					{
					pushFollow(FOLLOW_catch_directive_in_statements_and_directives1675);
					catch_directive38=catch_directive();
					state._fsp--;

					stream_catch_directive.add(catch_directive38.getTree());
					}
					break;
				case 4 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:524:7: catchall_directive
					{
					pushFollow(FOLLOW_catchall_directive_in_statements_and_directives1683);
					catchall_directive39=catchall_directive();
					state._fsp--;

					stream_catchall_directive.add(catchall_directive39.getTree());
					}
					break;
				case 5 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:525:7: parameter_directive
					{
					pushFollow(FOLLOW_parameter_directive_in_statements_and_directives1691);
					parameter_directive40=parameter_directive();
					state._fsp--;

					stream_parameter_directive.add(parameter_directive40.getTree());
					}
					break;
				case 6 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:526:7: annotation
					{
					pushFollow(FOLLOW_annotation_in_statements_and_directives1699);
					annotation41=annotation();
					state._fsp--;

					stream_annotation.add(annotation41.getTree());
					statements_and_directives_stack.peek().methodAnnotations.add((annotation41!=null?((CommonTree)annotation41.getTree()):null));
					}
					break;

				default :
					break loop7;
				}
			}

			// AST REWRITE
			// elements: registers_directive, ordered_method_item, parameter_directive, catchall_directive, catch_directive
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 528:5: -> ( registers_directive )? ^( I_ORDERED_METHOD_ITEMS ( ordered_method_item )* ) ^( I_CATCHES ( catch_directive )* ( catchall_directive )* ) ^( I_PARAMETERS ( parameter_directive )* )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:528:8: ( registers_directive )?
				if ( stream_registers_directive.hasNext() ) {
					adaptor.addChild(root_0, stream_registers_directive.nextTree());
				}
				stream_registers_directive.reset();

				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:529:8: ^( I_ORDERED_METHOD_ITEMS ( ordered_method_item )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ORDERED_METHOD_ITEMS, "I_ORDERED_METHOD_ITEMS"), root_1);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:529:33: ( ordered_method_item )*
				while ( stream_ordered_method_item.hasNext() ) {
					adaptor.addChild(root_1, stream_ordered_method_item.nextTree());
				}
				stream_ordered_method_item.reset();

				adaptor.addChild(root_0, root_1);
				}

				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:530:8: ^( I_CATCHES ( catch_directive )* ( catchall_directive )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_CATCHES, "I_CATCHES"), root_1);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:530:20: ( catch_directive )*
				while ( stream_catch_directive.hasNext() ) {
					adaptor.addChild(root_1, stream_catch_directive.nextTree());
				}
				stream_catch_directive.reset();

				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:530:37: ( catchall_directive )*
				while ( stream_catchall_directive.hasNext() ) {
					adaptor.addChild(root_1, stream_catchall_directive.nextTree());
				}
				stream_catchall_directive.reset();

				adaptor.addChild(root_0, root_1);
				}

				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:531:8: ^( I_PARAMETERS ( parameter_directive )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_PARAMETERS, "I_PARAMETERS"), root_1);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:531:23: ( parameter_directive )*
				while ( stream_parameter_directive.hasNext() ) {
					adaptor.addChild(root_1, stream_parameter_directive.nextTree());
				}
				stream_parameter_directive.reset();

				adaptor.addChild(root_0, root_1);
				}

				adaptor.addChild(root_0, buildTree(I_ANNOTATIONS, "I_ANNOTATIONS", statements_and_directives_stack.peek().methodAnnotations));
			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
			statements_and_directives_stack.pop();
		}
		return retval;
	}
	// $ANTLR end "statements_and_directives"


	public static class ordered_method_item_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "ordered_method_item"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:535:1: ordered_method_item : ( label | instruction | debug_directive );
	public final smaliParser.ordered_method_item_return ordered_method_item() throws RecognitionException {
		smaliParser.ordered_method_item_return retval = new smaliParser.ordered_method_item_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope label42 =null;
		ParserRuleReturnScope instruction43 =null;
		ParserRuleReturnScope debug_directive44 =null;


		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:536:3: ( label | instruction | debug_directive )
			int alt8=3;
			switch ( input.LA(1) ) {
			case COLON:
				{
				alt8=1;
				}
				break;
			case ARRAY_DATA_DIRECTIVE:
			case INSTRUCTION_FORMAT10t:
			case INSTRUCTION_FORMAT10x:
			case INSTRUCTION_FORMAT10x_ODEX:
			case INSTRUCTION_FORMAT11n:
			case INSTRUCTION_FORMAT11x:
			case INSTRUCTION_FORMAT12x:
			case INSTRUCTION_FORMAT12x_OR_ID:
			case INSTRUCTION_FORMAT20bc:
			case INSTRUCTION_FORMAT20t:
			case INSTRUCTION_FORMAT21c_FIELD:
			case INSTRUCTION_FORMAT21c_FIELD_ODEX:
			case INSTRUCTION_FORMAT21c_METHOD_HANDLE:
			case INSTRUCTION_FORMAT21c_METHOD_TYPE:
			case INSTRUCTION_FORMAT21c_STRING:
			case INSTRUCTION_FORMAT21c_TYPE:
			case INSTRUCTION_FORMAT21ih:
			case INSTRUCTION_FORMAT21lh:
			case INSTRUCTION_FORMAT21s:
			case INSTRUCTION_FORMAT21t:
			case INSTRUCTION_FORMAT22b:
			case INSTRUCTION_FORMAT22c_FIELD:
			case INSTRUCTION_FORMAT22c_FIELD_ODEX:
			case INSTRUCTION_FORMAT22c_TYPE:
			case INSTRUCTION_FORMAT22cs_FIELD:
			case INSTRUCTION_FORMAT22s:
			case INSTRUCTION_FORMAT22s_OR_ID:
			case INSTRUCTION_FORMAT22t:
			case INSTRUCTION_FORMAT22x:
			case INSTRUCTION_FORMAT23x:
			case INSTRUCTION_FORMAT30t:
			case INSTRUCTION_FORMAT31c:
			case INSTRUCTION_FORMAT31i:
			case INSTRUCTION_FORMAT31i_OR_ID:
			case INSTRUCTION_FORMAT31t:
			case INSTRUCTION_FORMAT32x:
			case INSTRUCTION_FORMAT35c_CALL_SITE:
			case INSTRUCTION_FORMAT35c_METHOD:
			case INSTRUCTION_FORMAT35c_METHOD_ODEX:
			case INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE:
			case INSTRUCTION_FORMAT35c_TYPE:
			case INSTRUCTION_FORMAT35mi_METHOD:
			case INSTRUCTION_FORMAT35ms_METHOD:
			case INSTRUCTION_FORMAT3rc_CALL_SITE:
			case INSTRUCTION_FORMAT3rc_METHOD:
			case INSTRUCTION_FORMAT3rc_METHOD_ODEX:
			case INSTRUCTION_FORMAT3rc_TYPE:
			case INSTRUCTION_FORMAT3rmi_METHOD:
			case INSTRUCTION_FORMAT3rms_METHOD:
			case INSTRUCTION_FORMAT45cc_METHOD:
			case INSTRUCTION_FORMAT4rcc_METHOD:
			case INSTRUCTION_FORMAT51l:
			case PACKED_SWITCH_DIRECTIVE:
			case SPARSE_SWITCH_DIRECTIVE:
				{
				alt8=2;
				}
				break;
			case END_LOCAL_DIRECTIVE:
			case EPILOGUE_DIRECTIVE:
			case LINE_DIRECTIVE:
			case LOCAL_DIRECTIVE:
			case PROLOGUE_DIRECTIVE:
			case RESTART_LOCAL_DIRECTIVE:
			case SOURCE_DIRECTIVE:
				{
				alt8=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 8, 0, input);
				throw nvae;
			}
			switch (alt8) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:536:5: label
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_label_in_ordered_method_item1784);
					label42=label();
					state._fsp--;

					adaptor.addChild(root_0, label42.getTree());

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:537:5: instruction
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_instruction_in_ordered_method_item1790);
					instruction43=instruction();
					state._fsp--;

					adaptor.addChild(root_0, instruction43.getTree());

					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:538:5: debug_directive
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_debug_directive_in_ordered_method_item1796);
					debug_directive44=debug_directive();
					state._fsp--;

					adaptor.addChild(root_0, debug_directive44.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "ordered_method_item"


	public static class registers_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "registers_directive"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:540:1: registers_directive : (directive= REGISTERS_DIRECTIVE regCount= integral_literal -> ^( I_REGISTERS[$REGISTERS_DIRECTIVE, \"I_REGISTERS\"] $regCount) |directive= LOCALS_DIRECTIVE regCount2= integral_literal -> ^( I_LOCALS[$LOCALS_DIRECTIVE, \"I_LOCALS\"] $regCount2) ) ;
	public final smaliParser.registers_directive_return registers_directive() throws RecognitionException {
		smaliParser.registers_directive_return retval = new smaliParser.registers_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token directive=null;
		ParserRuleReturnScope regCount =null;
		ParserRuleReturnScope regCount2 =null;

		CommonTree directive_tree=null;
		RewriteRuleTokenStream stream_LOCALS_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token LOCALS_DIRECTIVE");
		RewriteRuleTokenStream stream_REGISTERS_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token REGISTERS_DIRECTIVE");
		RewriteRuleSubtreeStream stream_integral_literal=new RewriteRuleSubtreeStream(adaptor,"rule integral_literal");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:541:3: ( (directive= REGISTERS_DIRECTIVE regCount= integral_literal -> ^( I_REGISTERS[$REGISTERS_DIRECTIVE, \"I_REGISTERS\"] $regCount) |directive= LOCALS_DIRECTIVE regCount2= integral_literal -> ^( I_LOCALS[$LOCALS_DIRECTIVE, \"I_LOCALS\"] $regCount2) ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:541:5: (directive= REGISTERS_DIRECTIVE regCount= integral_literal -> ^( I_REGISTERS[$REGISTERS_DIRECTIVE, \"I_REGISTERS\"] $regCount) |directive= LOCALS_DIRECTIVE regCount2= integral_literal -> ^( I_LOCALS[$LOCALS_DIRECTIVE, \"I_LOCALS\"] $regCount2) )
			{
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:541:5: (directive= REGISTERS_DIRECTIVE regCount= integral_literal -> ^( I_REGISTERS[$REGISTERS_DIRECTIVE, \"I_REGISTERS\"] $regCount) |directive= LOCALS_DIRECTIVE regCount2= integral_literal -> ^( I_LOCALS[$LOCALS_DIRECTIVE, \"I_LOCALS\"] $regCount2) )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==REGISTERS_DIRECTIVE) ) {
				alt9=1;
			}
			else if ( (LA9_0==LOCALS_DIRECTIVE) ) {
				alt9=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:542:7: directive= REGISTERS_DIRECTIVE regCount= integral_literal
					{
					directive=(Token)match(input,REGISTERS_DIRECTIVE,FOLLOW_REGISTERS_DIRECTIVE_in_registers_directive1816);
					stream_REGISTERS_DIRECTIVE.add(directive);

					pushFollow(FOLLOW_integral_literal_in_registers_directive1820);
					regCount=integral_literal();
					state._fsp--;

					stream_integral_literal.add(regCount.getTree());
					// AST REWRITE
					// elements: regCount
					// token labels:
					// rule labels: regCount, retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_regCount=new RewriteRuleSubtreeStream(adaptor,"rule regCount",regCount!=null?regCount.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 542:63: -> ^( I_REGISTERS[$REGISTERS_DIRECTIVE, \"I_REGISTERS\"] $regCount)
					{
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:542:66: ^( I_REGISTERS[$REGISTERS_DIRECTIVE, \"I_REGISTERS\"] $regCount)
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_REGISTERS, directive, "I_REGISTERS"), root_1);
						adaptor.addChild(root_1, stream_regCount.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:543:7: directive= LOCALS_DIRECTIVE regCount2= integral_literal
					{
					directive=(Token)match(input,LOCALS_DIRECTIVE,FOLLOW_LOCALS_DIRECTIVE_in_registers_directive1840);
					stream_LOCALS_DIRECTIVE.add(directive);

					pushFollow(FOLLOW_integral_literal_in_registers_directive1844);
					regCount2=integral_literal();
					state._fsp--;

					stream_integral_literal.add(regCount2.getTree());
					// AST REWRITE
					// elements: regCount2
					// token labels:
					// rule labels: regCount2, retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_regCount2=new RewriteRuleSubtreeStream(adaptor,"rule regCount2",regCount2!=null?regCount2.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 543:61: -> ^( I_LOCALS[$LOCALS_DIRECTIVE, \"I_LOCALS\"] $regCount2)
					{
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:543:64: ^( I_LOCALS[$LOCALS_DIRECTIVE, \"I_LOCALS\"] $regCount2)
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_LOCALS, directive, "I_LOCALS"), root_1);
						adaptor.addChild(root_1, stream_regCount2.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}


			      if (statements_and_directives_stack.peek().hasRegistersDirective) {
			        throw new SemanticException(input, directive, "There can only be a single .registers or .locals directive in a method");
			      }
			      statements_and_directives_stack.peek().hasRegistersDirective =true;
			
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "registers_directive"


	public static class param_list_or_id_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "param_list_or_id"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:552:1: param_list_or_id : ( PARAM_LIST_OR_ID_PRIMITIVE_TYPE )+ ;
	public final smaliParser.param_list_or_id_return param_list_or_id() throws RecognitionException {
		smaliParser.param_list_or_id_return retval = new smaliParser.param_list_or_id_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token PARAM_LIST_OR_ID_PRIMITIVE_TYPE45=null;

		CommonTree PARAM_LIST_OR_ID_PRIMITIVE_TYPE45_tree=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:553:3: ( ( PARAM_LIST_OR_ID_PRIMITIVE_TYPE )+ )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:553:5: ( PARAM_LIST_OR_ID_PRIMITIVE_TYPE )+
			{
			root_0 = (CommonTree)adaptor.nil();


			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:553:5: ( PARAM_LIST_OR_ID_PRIMITIVE_TYPE )+
			int cnt10=0;
			loop10:
			while (true) {
				int alt10=2;
				int LA10_0 = input.LA(1);
				if ( (LA10_0==PARAM_LIST_OR_ID_PRIMITIVE_TYPE) ) {
					alt10=1;
				}

				switch (alt10) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:553:5: PARAM_LIST_OR_ID_PRIMITIVE_TYPE
					{
					PARAM_LIST_OR_ID_PRIMITIVE_TYPE45=(Token)match(input,PARAM_LIST_OR_ID_PRIMITIVE_TYPE,FOLLOW_PARAM_LIST_OR_ID_PRIMITIVE_TYPE_in_param_list_or_id1876);
					PARAM_LIST_OR_ID_PRIMITIVE_TYPE45_tree = (CommonTree)adaptor.create(PARAM_LIST_OR_ID_PRIMITIVE_TYPE45);
					adaptor.addChild(root_0, PARAM_LIST_OR_ID_PRIMITIVE_TYPE45_tree);

					}
					break;

				default :
					if ( cnt10 >= 1 ) break loop10;
					EarlyExitException eee = new EarlyExitException(10, input);
					throw eee;
				}
				cnt10++;
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "param_list_or_id"


	public static class simple_name_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "simple_name"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:557:1: simple_name : ( SIMPLE_NAME | ACCESS_SPEC -> SIMPLE_NAME[$ACCESS_SPEC] | HIDDENAPI_RESTRICTION -> SIMPLE_NAME[$HIDDENAPI_RESTRICTION] | VERIFICATION_ERROR_TYPE -> SIMPLE_NAME[$VERIFICATION_ERROR_TYPE] | POSITIVE_INTEGER_LITERAL -> SIMPLE_NAME[$POSITIVE_INTEGER_LITERAL] | NEGATIVE_INTEGER_LITERAL -> SIMPLE_NAME[$NEGATIVE_INTEGER_LITERAL] | FLOAT_LITERAL_OR_ID -> SIMPLE_NAME[$FLOAT_LITERAL_OR_ID] | DOUBLE_LITERAL_OR_ID -> SIMPLE_NAME[$DOUBLE_LITERAL_OR_ID] | BOOL_LITERAL -> SIMPLE_NAME[$BOOL_LITERAL] | NULL_LITERAL -> SIMPLE_NAME[$NULL_LITERAL] | REGISTER -> SIMPLE_NAME[$REGISTER] | param_list_or_id ->| PRIMITIVE_TYPE -> SIMPLE_NAME[$PRIMITIVE_TYPE] | VOID_TYPE -> SIMPLE_NAME[$VOID_TYPE] | ANNOTATION_VISIBILITY -> SIMPLE_NAME[$ANNOTATION_VISIBILITY] | METHOD_HANDLE_TYPE_FIELD | METHOD_HANDLE_TYPE_METHOD | INSTRUCTION_FORMAT10t -> SIMPLE_NAME[$INSTRUCTION_FORMAT10t] | INSTRUCTION_FORMAT10x -> SIMPLE_NAME[$INSTRUCTION_FORMAT10x] | INSTRUCTION_FORMAT10x_ODEX -> SIMPLE_NAME[$INSTRUCTION_FORMAT10x_ODEX] | INSTRUCTION_FORMAT11x -> SIMPLE_NAME[$INSTRUCTION_FORMAT11x] | INSTRUCTION_FORMAT12x_OR_ID -> SIMPLE_NAME[$INSTRUCTION_FORMAT12x_OR_ID] | INSTRUCTION_FORMAT21c_FIELD -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_FIELD] | INSTRUCTION_FORMAT21c_FIELD_ODEX -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_FIELD_ODEX] | INSTRUCTION_FORMAT21c_METHOD_HANDLE -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_METHOD_HANDLE] | INSTRUCTION_FORMAT21c_METHOD_TYPE -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_METHOD_TYPE] | INSTRUCTION_FORMAT21c_STRING -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_STRING] | INSTRUCTION_FORMAT21c_TYPE -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_TYPE] | INSTRUCTION_FORMAT21t -> SIMPLE_NAME[$INSTRUCTION_FORMAT21t] | INSTRUCTION_FORMAT22c_FIELD -> SIMPLE_NAME[$INSTRUCTION_FORMAT22c_FIELD] | INSTRUCTION_FORMAT22c_FIELD_ODEX -> SIMPLE_NAME[$INSTRUCTION_FORMAT22c_FIELD_ODEX] | INSTRUCTION_FORMAT22c_TYPE -> SIMPLE_NAME[$INSTRUCTION_FORMAT22c_TYPE] | INSTRUCTION_FORMAT22cs_FIELD -> SIMPLE_NAME[$INSTRUCTION_FORMAT22cs_FIELD] | INSTRUCTION_FORMAT22s_OR_ID -> SIMPLE_NAME[$INSTRUCTION_FORMAT22s_OR_ID] | INSTRUCTION_FORMAT22t -> SIMPLE_NAME[$INSTRUCTION_FORMAT22t] | INSTRUCTION_FORMAT23x -> SIMPLE_NAME[$INSTRUCTION_FORMAT23x] | INSTRUCTION_FORMAT31i_OR_ID -> SIMPLE_NAME[$INSTRUCTION_FORMAT31i_OR_ID] | INSTRUCTION_FORMAT31t -> SIMPLE_NAME[$INSTRUCTION_FORMAT31t] | INSTRUCTION_FORMAT35c_CALL_SITE -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_CALL_SITE] | INSTRUCTION_FORMAT35c_METHOD -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_METHOD] | INSTRUCTION_FORMAT35c_METHOD_ODEX -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_METHOD_ODEX] | INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE] | INSTRUCTION_FORMAT35c_TYPE -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_TYPE] | INSTRUCTION_FORMAT35mi_METHOD -> SIMPLE_NAME[$INSTRUCTION_FORMAT35mi_METHOD] | INSTRUCTION_FORMAT35ms_METHOD -> SIMPLE_NAME[$INSTRUCTION_FORMAT35ms_METHOD] | INSTRUCTION_FORMAT45cc_METHOD -> SIMPLE_NAME[$INSTRUCTION_FORMAT45cc_METHOD] | INSTRUCTION_FORMAT4rcc_METHOD -> SIMPLE_NAME[$INSTRUCTION_FORMAT4rcc_METHOD] | INSTRUCTION_FORMAT51l -> SIMPLE_NAME[$INSTRUCTION_FORMAT51l] );
	public final smaliParser.simple_name_return simple_name() throws RecognitionException {
		smaliParser.simple_name_return retval = new smaliParser.simple_name_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token SIMPLE_NAME46=null;
		Token ACCESS_SPEC47=null;
		Token HIDDENAPI_RESTRICTION48=null;
		Token VERIFICATION_ERROR_TYPE49=null;
		Token POSITIVE_INTEGER_LITERAL50=null;
		Token NEGATIVE_INTEGER_LITERAL51=null;
		Token FLOAT_LITERAL_OR_ID52=null;
		Token DOUBLE_LITERAL_OR_ID53=null;
		Token BOOL_LITERAL54=null;
		Token NULL_LITERAL55=null;
		Token REGISTER56=null;
		Token PRIMITIVE_TYPE58=null;
		Token VOID_TYPE59=null;
		Token ANNOTATION_VISIBILITY60=null;
		Token METHOD_HANDLE_TYPE_FIELD61=null;
		Token METHOD_HANDLE_TYPE_METHOD62=null;
		Token INSTRUCTION_FORMAT10t63=null;
		Token INSTRUCTION_FORMAT10x64=null;
		Token INSTRUCTION_FORMAT10x_ODEX65=null;
		Token INSTRUCTION_FORMAT11x66=null;
		Token INSTRUCTION_FORMAT12x_OR_ID67=null;
		Token INSTRUCTION_FORMAT21c_FIELD68=null;
		Token INSTRUCTION_FORMAT21c_FIELD_ODEX69=null;
		Token INSTRUCTION_FORMAT21c_METHOD_HANDLE70=null;
		Token INSTRUCTION_FORMAT21c_METHOD_TYPE71=null;
		Token INSTRUCTION_FORMAT21c_STRING72=null;
		Token INSTRUCTION_FORMAT21c_TYPE73=null;
		Token INSTRUCTION_FORMAT21t74=null;
		Token INSTRUCTION_FORMAT22c_FIELD75=null;
		Token INSTRUCTION_FORMAT22c_FIELD_ODEX76=null;
		Token INSTRUCTION_FORMAT22c_TYPE77=null;
		Token INSTRUCTION_FORMAT22cs_FIELD78=null;
		Token INSTRUCTION_FORMAT22s_OR_ID79=null;
		Token INSTRUCTION_FORMAT22t80=null;
		Token INSTRUCTION_FORMAT23x81=null;
		Token INSTRUCTION_FORMAT31i_OR_ID82=null;
		Token INSTRUCTION_FORMAT31t83=null;
		Token INSTRUCTION_FORMAT35c_CALL_SITE84=null;
		Token INSTRUCTION_FORMAT35c_METHOD85=null;
		Token INSTRUCTION_FORMAT35c_METHOD_ODEX86=null;
		Token INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE87=null;
		Token INSTRUCTION_FORMAT35c_TYPE88=null;
		Token INSTRUCTION_FORMAT35mi_METHOD89=null;
		Token INSTRUCTION_FORMAT35ms_METHOD90=null;
		Token INSTRUCTION_FORMAT45cc_METHOD91=null;
		Token INSTRUCTION_FORMAT4rcc_METHOD92=null;
		Token INSTRUCTION_FORMAT51l93=null;
		ParserRuleReturnScope param_list_or_id57 =null;

		CommonTree SIMPLE_NAME46_tree=null;
		CommonTree ACCESS_SPEC47_tree=null;
		CommonTree HIDDENAPI_RESTRICTION48_tree=null;
		CommonTree VERIFICATION_ERROR_TYPE49_tree=null;
		CommonTree POSITIVE_INTEGER_LITERAL50_tree=null;
		CommonTree NEGATIVE_INTEGER_LITERAL51_tree=null;
		CommonTree FLOAT_LITERAL_OR_ID52_tree=null;
		CommonTree DOUBLE_LITERAL_OR_ID53_tree=null;
		CommonTree BOOL_LITERAL54_tree=null;
		CommonTree NULL_LITERAL55_tree=null;
		CommonTree REGISTER56_tree=null;
		CommonTree PRIMITIVE_TYPE58_tree=null;
		CommonTree VOID_TYPE59_tree=null;
		CommonTree ANNOTATION_VISIBILITY60_tree=null;
		CommonTree METHOD_HANDLE_TYPE_FIELD61_tree=null;
		CommonTree METHOD_HANDLE_TYPE_METHOD62_tree=null;
		CommonTree INSTRUCTION_FORMAT10t63_tree=null;
		CommonTree INSTRUCTION_FORMAT10x64_tree=null;
		CommonTree INSTRUCTION_FORMAT10x_ODEX65_tree=null;
		CommonTree INSTRUCTION_FORMAT11x66_tree=null;
		CommonTree INSTRUCTION_FORMAT12x_OR_ID67_tree=null;
		CommonTree INSTRUCTION_FORMAT21c_FIELD68_tree=null;
		CommonTree INSTRUCTION_FORMAT21c_FIELD_ODEX69_tree=null;
		CommonTree INSTRUCTION_FORMAT21c_METHOD_HANDLE70_tree=null;
		CommonTree INSTRUCTION_FORMAT21c_METHOD_TYPE71_tree=null;
		CommonTree INSTRUCTION_FORMAT21c_STRING72_tree=null;
		CommonTree INSTRUCTION_FORMAT21c_TYPE73_tree=null;
		CommonTree INSTRUCTION_FORMAT21t74_tree=null;
		CommonTree INSTRUCTION_FORMAT22c_FIELD75_tree=null;
		CommonTree INSTRUCTION_FORMAT22c_FIELD_ODEX76_tree=null;
		CommonTree INSTRUCTION_FORMAT22c_TYPE77_tree=null;
		CommonTree INSTRUCTION_FORMAT22cs_FIELD78_tree=null;
		CommonTree INSTRUCTION_FORMAT22s_OR_ID79_tree=null;
		CommonTree INSTRUCTION_FORMAT22t80_tree=null;
		CommonTree INSTRUCTION_FORMAT23x81_tree=null;
		CommonTree INSTRUCTION_FORMAT31i_OR_ID82_tree=null;
		CommonTree INSTRUCTION_FORMAT31t83_tree=null;
		CommonTree INSTRUCTION_FORMAT35c_CALL_SITE84_tree=null;
		CommonTree INSTRUCTION_FORMAT35c_METHOD85_tree=null;
		CommonTree INSTRUCTION_FORMAT35c_METHOD_ODEX86_tree=null;
		CommonTree INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE87_tree=null;
		CommonTree INSTRUCTION_FORMAT35c_TYPE88_tree=null;
		CommonTree INSTRUCTION_FORMAT35mi_METHOD89_tree=null;
		CommonTree INSTRUCTION_FORMAT35ms_METHOD90_tree=null;
		CommonTree INSTRUCTION_FORMAT45cc_METHOD91_tree=null;
		CommonTree INSTRUCTION_FORMAT4rcc_METHOD92_tree=null;
		CommonTree INSTRUCTION_FORMAT51l93_tree=null;
		RewriteRuleTokenStream stream_HIDDENAPI_RESTRICTION=new RewriteRuleTokenStream(adaptor,"token HIDDENAPI_RESTRICTION");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE");
		RewriteRuleTokenStream stream_ANNOTATION_VISIBILITY=new RewriteRuleTokenStream(adaptor,"token ANNOTATION_VISIBILITY");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_TYPE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_TYPE");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22t=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22t");
		RewriteRuleTokenStream stream_VOID_TYPE=new RewriteRuleTokenStream(adaptor,"token VOID_TYPE");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT10t=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT10t");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35mi_METHOD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT35mi_METHOD");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22s_OR_ID=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22s_OR_ID");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22cs_FIELD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22cs_FIELD");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT12x_OR_ID=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT12x_OR_ID");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35ms_METHOD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT35ms_METHOD");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35c_METHOD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT35c_METHOD");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT45cc_METHOD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT45cc_METHOD");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35c_TYPE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT35c_TYPE");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT10x=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT10x");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_METHOD_HANDLE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_METHOD_HANDLE");
		RewriteRuleTokenStream stream_FLOAT_LITERAL_OR_ID=new RewriteRuleTokenStream(adaptor,"token FLOAT_LITERAL_OR_ID");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22c_TYPE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22c_TYPE");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_STRING=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_STRING");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35c_METHOD_ODEX=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT35c_METHOD_ODEX");
		RewriteRuleTokenStream stream_NEGATIVE_INTEGER_LITERAL=new RewriteRuleTokenStream(adaptor,"token NEGATIVE_INTEGER_LITERAL");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22c_FIELD_ODEX=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22c_FIELD_ODEX");
		RewriteRuleTokenStream stream_DOUBLE_LITERAL_OR_ID=new RewriteRuleTokenStream(adaptor,"token DOUBLE_LITERAL_OR_ID");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT31i_OR_ID=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT31i_OR_ID");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21t=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21t");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT31t=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT31t");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT23x=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT23x");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35c_CALL_SITE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT35c_CALL_SITE");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT51l=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT51l");
		RewriteRuleTokenStream stream_POSITIVE_INTEGER_LITERAL=new RewriteRuleTokenStream(adaptor,"token POSITIVE_INTEGER_LITERAL");
		RewriteRuleTokenStream stream_BOOL_LITERAL=new RewriteRuleTokenStream(adaptor,"token BOOL_LITERAL");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT10x_ODEX=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT10x_ODEX");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_FIELD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_FIELD");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22c_FIELD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22c_FIELD");
		RewriteRuleTokenStream stream_VERIFICATION_ERROR_TYPE=new RewriteRuleTokenStream(adaptor,"token VERIFICATION_ERROR_TYPE");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT11x=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT11x");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_METHOD_TYPE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_METHOD_TYPE");
		RewriteRuleTokenStream stream_ACCESS_SPEC=new RewriteRuleTokenStream(adaptor,"token ACCESS_SPEC");
		RewriteRuleTokenStream stream_NULL_LITERAL=new RewriteRuleTokenStream(adaptor,"token NULL_LITERAL");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT4rcc_METHOD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT4rcc_METHOD");
		RewriteRuleTokenStream stream_PRIMITIVE_TYPE=new RewriteRuleTokenStream(adaptor,"token PRIMITIVE_TYPE");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_FIELD_ODEX=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_FIELD_ODEX");
		RewriteRuleSubtreeStream stream_param_list_or_id=new RewriteRuleSubtreeStream(adaptor,"rule param_list_or_id");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:558:3: ( SIMPLE_NAME | ACCESS_SPEC -> SIMPLE_NAME[$ACCESS_SPEC] | HIDDENAPI_RESTRICTION -> SIMPLE_NAME[$HIDDENAPI_RESTRICTION] | VERIFICATION_ERROR_TYPE -> SIMPLE_NAME[$VERIFICATION_ERROR_TYPE] | POSITIVE_INTEGER_LITERAL -> SIMPLE_NAME[$POSITIVE_INTEGER_LITERAL] | NEGATIVE_INTEGER_LITERAL -> SIMPLE_NAME[$NEGATIVE_INTEGER_LITERAL] | FLOAT_LITERAL_OR_ID -> SIMPLE_NAME[$FLOAT_LITERAL_OR_ID] | DOUBLE_LITERAL_OR_ID -> SIMPLE_NAME[$DOUBLE_LITERAL_OR_ID] | BOOL_LITERAL -> SIMPLE_NAME[$BOOL_LITERAL] | NULL_LITERAL -> SIMPLE_NAME[$NULL_LITERAL] | REGISTER -> SIMPLE_NAME[$REGISTER] | param_list_or_id ->| PRIMITIVE_TYPE -> SIMPLE_NAME[$PRIMITIVE_TYPE] | VOID_TYPE -> SIMPLE_NAME[$VOID_TYPE] | ANNOTATION_VISIBILITY -> SIMPLE_NAME[$ANNOTATION_VISIBILITY] | METHOD_HANDLE_TYPE_FIELD | METHOD_HANDLE_TYPE_METHOD | INSTRUCTION_FORMAT10t -> SIMPLE_NAME[$INSTRUCTION_FORMAT10t] | INSTRUCTION_FORMAT10x -> SIMPLE_NAME[$INSTRUCTION_FORMAT10x] | INSTRUCTION_FORMAT10x_ODEX -> SIMPLE_NAME[$INSTRUCTION_FORMAT10x_ODEX] | INSTRUCTION_FORMAT11x -> SIMPLE_NAME[$INSTRUCTION_FORMAT11x] | INSTRUCTION_FORMAT12x_OR_ID -> SIMPLE_NAME[$INSTRUCTION_FORMAT12x_OR_ID] | INSTRUCTION_FORMAT21c_FIELD -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_FIELD] | INSTRUCTION_FORMAT21c_FIELD_ODEX -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_FIELD_ODEX] | INSTRUCTION_FORMAT21c_METHOD_HANDLE -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_METHOD_HANDLE] | INSTRUCTION_FORMAT21c_METHOD_TYPE -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_METHOD_TYPE] | INSTRUCTION_FORMAT21c_STRING -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_STRING] | INSTRUCTION_FORMAT21c_TYPE -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_TYPE] | INSTRUCTION_FORMAT21t -> SIMPLE_NAME[$INSTRUCTION_FORMAT21t] | INSTRUCTION_FORMAT22c_FIELD -> SIMPLE_NAME[$INSTRUCTION_FORMAT22c_FIELD] | INSTRUCTION_FORMAT22c_FIELD_ODEX -> SIMPLE_NAME[$INSTRUCTION_FORMAT22c_FIELD_ODEX] | INSTRUCTION_FORMAT22c_TYPE -> SIMPLE_NAME[$INSTRUCTION_FORMAT22c_TYPE] | INSTRUCTION_FORMAT22cs_FIELD -> SIMPLE_NAME[$INSTRUCTION_FORMAT22cs_FIELD] | INSTRUCTION_FORMAT22s_OR_ID -> SIMPLE_NAME[$INSTRUCTION_FORMAT22s_OR_ID] | INSTRUCTION_FORMAT22t -> SIMPLE_NAME[$INSTRUCTION_FORMAT22t] | INSTRUCTION_FORMAT23x -> SIMPLE_NAME[$INSTRUCTION_FORMAT23x] | INSTRUCTION_FORMAT31i_OR_ID -> SIMPLE_NAME[$INSTRUCTION_FORMAT31i_OR_ID] | INSTRUCTION_FORMAT31t -> SIMPLE_NAME[$INSTRUCTION_FORMAT31t] | INSTRUCTION_FORMAT35c_CALL_SITE -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_CALL_SITE] | INSTRUCTION_FORMAT35c_METHOD -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_METHOD] | INSTRUCTION_FORMAT35c_METHOD_ODEX -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_METHOD_ODEX] | INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE] | INSTRUCTION_FORMAT35c_TYPE -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_TYPE] | INSTRUCTION_FORMAT35mi_METHOD -> SIMPLE_NAME[$INSTRUCTION_FORMAT35mi_METHOD] | INSTRUCTION_FORMAT35ms_METHOD -> SIMPLE_NAME[$INSTRUCTION_FORMAT35ms_METHOD] | INSTRUCTION_FORMAT45cc_METHOD -> SIMPLE_NAME[$INSTRUCTION_FORMAT45cc_METHOD] | INSTRUCTION_FORMAT4rcc_METHOD -> SIMPLE_NAME[$INSTRUCTION_FORMAT4rcc_METHOD] | INSTRUCTION_FORMAT51l -> SIMPLE_NAME[$INSTRUCTION_FORMAT51l] )
			int alt11=48;
			switch ( input.LA(1) ) {
			case SIMPLE_NAME:
				{
				alt11=1;
				}
				break;
			case ACCESS_SPEC:
				{
				alt11=2;
				}
				break;
			case HIDDENAPI_RESTRICTION:
				{
				alt11=3;
				}
				break;
			case VERIFICATION_ERROR_TYPE:
				{
				alt11=4;
				}
				break;
			case POSITIVE_INTEGER_LITERAL:
				{
				alt11=5;
				}
				break;
			case NEGATIVE_INTEGER_LITERAL:
				{
				alt11=6;
				}
				break;
			case FLOAT_LITERAL_OR_ID:
				{
				alt11=7;
				}
				break;
			case DOUBLE_LITERAL_OR_ID:
				{
				alt11=8;
				}
				break;
			case BOOL_LITERAL:
				{
				alt11=9;
				}
				break;
			case NULL_LITERAL:
				{
				alt11=10;
				}
				break;
			case REGISTER:
				{
				alt11=11;
				}
				break;
			case PARAM_LIST_OR_ID_PRIMITIVE_TYPE:
				{
				alt11=12;
				}
				break;
			case PRIMITIVE_TYPE:
				{
				alt11=13;
				}
				break;
			case VOID_TYPE:
				{
				alt11=14;
				}
				break;
			case ANNOTATION_VISIBILITY:
				{
				alt11=15;
				}
				break;
			case METHOD_HANDLE_TYPE_FIELD:
				{
				alt11=16;
				}
				break;
			case METHOD_HANDLE_TYPE_METHOD:
				{
				alt11=17;
				}
				break;
			case INSTRUCTION_FORMAT10t:
				{
				alt11=18;
				}
				break;
			case INSTRUCTION_FORMAT10x:
				{
				alt11=19;
				}
				break;
			case INSTRUCTION_FORMAT10x_ODEX:
				{
				alt11=20;
				}
				break;
			case INSTRUCTION_FORMAT11x:
				{
				alt11=21;
				}
				break;
			case INSTRUCTION_FORMAT12x_OR_ID:
				{
				alt11=22;
				}
				break;
			case INSTRUCTION_FORMAT21c_FIELD:
				{
				alt11=23;
				}
				break;
			case INSTRUCTION_FORMAT21c_FIELD_ODEX:
				{
				alt11=24;
				}
				break;
			case INSTRUCTION_FORMAT21c_METHOD_HANDLE:
				{
				alt11=25;
				}
				break;
			case INSTRUCTION_FORMAT21c_METHOD_TYPE:
				{
				alt11=26;
				}
				break;
			case INSTRUCTION_FORMAT21c_STRING:
				{
				alt11=27;
				}
				break;
			case INSTRUCTION_FORMAT21c_TYPE:
				{
				alt11=28;
				}
				break;
			case INSTRUCTION_FORMAT21t:
				{
				alt11=29;
				}
				break;
			case INSTRUCTION_FORMAT22c_FIELD:
				{
				alt11=30;
				}
				break;
			case INSTRUCTION_FORMAT22c_FIELD_ODEX:
				{
				alt11=31;
				}
				break;
			case INSTRUCTION_FORMAT22c_TYPE:
				{
				alt11=32;
				}
				break;
			case INSTRUCTION_FORMAT22cs_FIELD:
				{
				alt11=33;
				}
				break;
			case INSTRUCTION_FORMAT22s_OR_ID:
				{
				alt11=34;
				}
				break;
			case INSTRUCTION_FORMAT22t:
				{
				alt11=35;
				}
				break;
			case INSTRUCTION_FORMAT23x:
				{
				alt11=36;
				}
				break;
			case INSTRUCTION_FORMAT31i_OR_ID:
				{
				alt11=37;
				}
				break;
			case INSTRUCTION_FORMAT31t:
				{
				alt11=38;
				}
				break;
			case INSTRUCTION_FORMAT35c_CALL_SITE:
				{
				alt11=39;
				}
				break;
			case INSTRUCTION_FORMAT35c_METHOD:
				{
				alt11=40;
				}
				break;
			case INSTRUCTION_FORMAT35c_METHOD_ODEX:
				{
				alt11=41;
				}
				break;
			case INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE:
				{
				alt11=42;
				}
				break;
			case INSTRUCTION_FORMAT35c_TYPE:
				{
				alt11=43;
				}
				break;
			case INSTRUCTION_FORMAT35mi_METHOD:
				{
				alt11=44;
				}
				break;
			case INSTRUCTION_FORMAT35ms_METHOD:
				{
				alt11=45;
				}
				break;
			case INSTRUCTION_FORMAT45cc_METHOD:
				{
				alt11=46;
				}
				break;
			case INSTRUCTION_FORMAT4rcc_METHOD:
				{
				alt11=47;
				}
				break;
			case INSTRUCTION_FORMAT51l:
				{
				alt11=48;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}
			switch (alt11) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:558:5: SIMPLE_NAME
					{
					root_0 = (CommonTree)adaptor.nil();


					SIMPLE_NAME46=(Token)match(input,SIMPLE_NAME,FOLLOW_SIMPLE_NAME_in_simple_name1889);
					SIMPLE_NAME46_tree = (CommonTree)adaptor.create(SIMPLE_NAME46);
					adaptor.addChild(root_0, SIMPLE_NAME46_tree);

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:559:5: ACCESS_SPEC
					{
					ACCESS_SPEC47=(Token)match(input,ACCESS_SPEC,FOLLOW_ACCESS_SPEC_in_simple_name1895);
					stream_ACCESS_SPEC.add(ACCESS_SPEC47);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 559:17: -> SIMPLE_NAME[$ACCESS_SPEC]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, ACCESS_SPEC47));
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:560:5: HIDDENAPI_RESTRICTION
					{
					HIDDENAPI_RESTRICTION48=(Token)match(input,HIDDENAPI_RESTRICTION,FOLLOW_HIDDENAPI_RESTRICTION_in_simple_name1906);
					stream_HIDDENAPI_RESTRICTION.add(HIDDENAPI_RESTRICTION48);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 560:27: -> SIMPLE_NAME[$HIDDENAPI_RESTRICTION]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, HIDDENAPI_RESTRICTION48));
					}


					retval.tree = root_0;

					}
					break;
				case 4 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:561:5: VERIFICATION_ERROR_TYPE
					{
					VERIFICATION_ERROR_TYPE49=(Token)match(input,VERIFICATION_ERROR_TYPE,FOLLOW_VERIFICATION_ERROR_TYPE_in_simple_name1917);
					stream_VERIFICATION_ERROR_TYPE.add(VERIFICATION_ERROR_TYPE49);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 561:29: -> SIMPLE_NAME[$VERIFICATION_ERROR_TYPE]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, VERIFICATION_ERROR_TYPE49));
					}


					retval.tree = root_0;

					}
					break;
				case 5 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:562:5: POSITIVE_INTEGER_LITERAL
					{
					POSITIVE_INTEGER_LITERAL50=(Token)match(input,POSITIVE_INTEGER_LITERAL,FOLLOW_POSITIVE_INTEGER_LITERAL_in_simple_name1928);
					stream_POSITIVE_INTEGER_LITERAL.add(POSITIVE_INTEGER_LITERAL50);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 562:30: -> SIMPLE_NAME[$POSITIVE_INTEGER_LITERAL]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, POSITIVE_INTEGER_LITERAL50));
					}


					retval.tree = root_0;

					}
					break;
				case 6 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:563:5: NEGATIVE_INTEGER_LITERAL
					{
					NEGATIVE_INTEGER_LITERAL51=(Token)match(input,NEGATIVE_INTEGER_LITERAL,FOLLOW_NEGATIVE_INTEGER_LITERAL_in_simple_name1939);
					stream_NEGATIVE_INTEGER_LITERAL.add(NEGATIVE_INTEGER_LITERAL51);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 563:30: -> SIMPLE_NAME[$NEGATIVE_INTEGER_LITERAL]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, NEGATIVE_INTEGER_LITERAL51));
					}


					retval.tree = root_0;

					}
					break;
				case 7 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:564:5: FLOAT_LITERAL_OR_ID
					{
					FLOAT_LITERAL_OR_ID52=(Token)match(input,FLOAT_LITERAL_OR_ID,FOLLOW_FLOAT_LITERAL_OR_ID_in_simple_name1950);
					stream_FLOAT_LITERAL_OR_ID.add(FLOAT_LITERAL_OR_ID52);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 564:25: -> SIMPLE_NAME[$FLOAT_LITERAL_OR_ID]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, FLOAT_LITERAL_OR_ID52));
					}


					retval.tree = root_0;

					}
					break;
				case 8 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:565:5: DOUBLE_LITERAL_OR_ID
					{
					DOUBLE_LITERAL_OR_ID53=(Token)match(input,DOUBLE_LITERAL_OR_ID,FOLLOW_DOUBLE_LITERAL_OR_ID_in_simple_name1961);
					stream_DOUBLE_LITERAL_OR_ID.add(DOUBLE_LITERAL_OR_ID53);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 565:26: -> SIMPLE_NAME[$DOUBLE_LITERAL_OR_ID]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, DOUBLE_LITERAL_OR_ID53));
					}


					retval.tree = root_0;

					}
					break;
				case 9 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:566:5: BOOL_LITERAL
					{
					BOOL_LITERAL54=(Token)match(input,BOOL_LITERAL,FOLLOW_BOOL_LITERAL_in_simple_name1972);
					stream_BOOL_LITERAL.add(BOOL_LITERAL54);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 566:18: -> SIMPLE_NAME[$BOOL_LITERAL]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, BOOL_LITERAL54));
					}


					retval.tree = root_0;

					}
					break;
				case 10 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:567:5: NULL_LITERAL
					{
					NULL_LITERAL55=(Token)match(input,NULL_LITERAL,FOLLOW_NULL_LITERAL_in_simple_name1983);
					stream_NULL_LITERAL.add(NULL_LITERAL55);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 567:18: -> SIMPLE_NAME[$NULL_LITERAL]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, NULL_LITERAL55));
					}


					retval.tree = root_0;

					}
					break;
				case 11 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:568:5: REGISTER
					{
					REGISTER56=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_simple_name1994);
					stream_REGISTER.add(REGISTER56);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 568:14: -> SIMPLE_NAME[$REGISTER]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, REGISTER56));
					}


					retval.tree = root_0;

					}
					break;
				case 12 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:569:5: param_list_or_id
					{
					pushFollow(FOLLOW_param_list_or_id_in_simple_name2005);
					param_list_or_id57=param_list_or_id();
					state._fsp--;

					stream_param_list_or_id.add(param_list_or_id57.getTree());
					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 569:22: ->
					{
						adaptor.addChild(root_0,  adaptor.create(SIMPLE_NAME, (param_list_or_id57!=null?input.toString(param_list_or_id57.start,param_list_or_id57.stop):null)) );
					}


					retval.tree = root_0;

					}
					break;
				case 13 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:570:5: PRIMITIVE_TYPE
					{
					PRIMITIVE_TYPE58=(Token)match(input,PRIMITIVE_TYPE,FOLLOW_PRIMITIVE_TYPE_in_simple_name2015);
					stream_PRIMITIVE_TYPE.add(PRIMITIVE_TYPE58);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 570:20: -> SIMPLE_NAME[$PRIMITIVE_TYPE]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, PRIMITIVE_TYPE58));
					}


					retval.tree = root_0;

					}
					break;
				case 14 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:571:5: VOID_TYPE
					{
					VOID_TYPE59=(Token)match(input,VOID_TYPE,FOLLOW_VOID_TYPE_in_simple_name2026);
					stream_VOID_TYPE.add(VOID_TYPE59);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 571:15: -> SIMPLE_NAME[$VOID_TYPE]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, VOID_TYPE59));
					}


					retval.tree = root_0;

					}
					break;
				case 15 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:572:5: ANNOTATION_VISIBILITY
					{
					ANNOTATION_VISIBILITY60=(Token)match(input,ANNOTATION_VISIBILITY,FOLLOW_ANNOTATION_VISIBILITY_in_simple_name2037);
					stream_ANNOTATION_VISIBILITY.add(ANNOTATION_VISIBILITY60);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 572:27: -> SIMPLE_NAME[$ANNOTATION_VISIBILITY]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, ANNOTATION_VISIBILITY60));
					}


					retval.tree = root_0;

					}
					break;
				case 16 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:573:5: METHOD_HANDLE_TYPE_FIELD
					{
					root_0 = (CommonTree)adaptor.nil();


					METHOD_HANDLE_TYPE_FIELD61=(Token)match(input,METHOD_HANDLE_TYPE_FIELD,FOLLOW_METHOD_HANDLE_TYPE_FIELD_in_simple_name2048);
					METHOD_HANDLE_TYPE_FIELD61_tree = (CommonTree)adaptor.create(METHOD_HANDLE_TYPE_FIELD61);
					adaptor.addChild(root_0, METHOD_HANDLE_TYPE_FIELD61_tree);

					}
					break;
				case 17 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:574:5: METHOD_HANDLE_TYPE_METHOD
					{
					root_0 = (CommonTree)adaptor.nil();


					METHOD_HANDLE_TYPE_METHOD62=(Token)match(input,METHOD_HANDLE_TYPE_METHOD,FOLLOW_METHOD_HANDLE_TYPE_METHOD_in_simple_name2054);
					METHOD_HANDLE_TYPE_METHOD62_tree = (CommonTree)adaptor.create(METHOD_HANDLE_TYPE_METHOD62);
					adaptor.addChild(root_0, METHOD_HANDLE_TYPE_METHOD62_tree);

					}
					break;
				case 18 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:575:5: INSTRUCTION_FORMAT10t
					{
					INSTRUCTION_FORMAT10t63=(Token)match(input,INSTRUCTION_FORMAT10t,FOLLOW_INSTRUCTION_FORMAT10t_in_simple_name2060);
					stream_INSTRUCTION_FORMAT10t.add(INSTRUCTION_FORMAT10t63);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 575:27: -> SIMPLE_NAME[$INSTRUCTION_FORMAT10t]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT10t63));
					}


					retval.tree = root_0;

					}
					break;
				case 19 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:576:5: INSTRUCTION_FORMAT10x
					{
					INSTRUCTION_FORMAT10x64=(Token)match(input,INSTRUCTION_FORMAT10x,FOLLOW_INSTRUCTION_FORMAT10x_in_simple_name2071);
					stream_INSTRUCTION_FORMAT10x.add(INSTRUCTION_FORMAT10x64);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 576:27: -> SIMPLE_NAME[$INSTRUCTION_FORMAT10x]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT10x64));
					}


					retval.tree = root_0;

					}
					break;
				case 20 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:577:5: INSTRUCTION_FORMAT10x_ODEX
					{
					INSTRUCTION_FORMAT10x_ODEX65=(Token)match(input,INSTRUCTION_FORMAT10x_ODEX,FOLLOW_INSTRUCTION_FORMAT10x_ODEX_in_simple_name2082);
					stream_INSTRUCTION_FORMAT10x_ODEX.add(INSTRUCTION_FORMAT10x_ODEX65);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 577:32: -> SIMPLE_NAME[$INSTRUCTION_FORMAT10x_ODEX]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT10x_ODEX65));
					}


					retval.tree = root_0;

					}
					break;
				case 21 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:578:5: INSTRUCTION_FORMAT11x
					{
					INSTRUCTION_FORMAT11x66=(Token)match(input,INSTRUCTION_FORMAT11x,FOLLOW_INSTRUCTION_FORMAT11x_in_simple_name2093);
					stream_INSTRUCTION_FORMAT11x.add(INSTRUCTION_FORMAT11x66);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 578:27: -> SIMPLE_NAME[$INSTRUCTION_FORMAT11x]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT11x66));
					}


					retval.tree = root_0;

					}
					break;
				case 22 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:579:5: INSTRUCTION_FORMAT12x_OR_ID
					{
					INSTRUCTION_FORMAT12x_OR_ID67=(Token)match(input,INSTRUCTION_FORMAT12x_OR_ID,FOLLOW_INSTRUCTION_FORMAT12x_OR_ID_in_simple_name2104);
					stream_INSTRUCTION_FORMAT12x_OR_ID.add(INSTRUCTION_FORMAT12x_OR_ID67);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 579:33: -> SIMPLE_NAME[$INSTRUCTION_FORMAT12x_OR_ID]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT12x_OR_ID67));
					}


					retval.tree = root_0;

					}
					break;
				case 23 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:580:5: INSTRUCTION_FORMAT21c_FIELD
					{
					INSTRUCTION_FORMAT21c_FIELD68=(Token)match(input,INSTRUCTION_FORMAT21c_FIELD,FOLLOW_INSTRUCTION_FORMAT21c_FIELD_in_simple_name2115);
					stream_INSTRUCTION_FORMAT21c_FIELD.add(INSTRUCTION_FORMAT21c_FIELD68);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 580:33: -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_FIELD]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT21c_FIELD68));
					}


					retval.tree = root_0;

					}
					break;
				case 24 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:581:5: INSTRUCTION_FORMAT21c_FIELD_ODEX
					{
					INSTRUCTION_FORMAT21c_FIELD_ODEX69=(Token)match(input,INSTRUCTION_FORMAT21c_FIELD_ODEX,FOLLOW_INSTRUCTION_FORMAT21c_FIELD_ODEX_in_simple_name2126);
					stream_INSTRUCTION_FORMAT21c_FIELD_ODEX.add(INSTRUCTION_FORMAT21c_FIELD_ODEX69);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 581:38: -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_FIELD_ODEX]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT21c_FIELD_ODEX69));
					}


					retval.tree = root_0;

					}
					break;
				case 25 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:582:5: INSTRUCTION_FORMAT21c_METHOD_HANDLE
					{
					INSTRUCTION_FORMAT21c_METHOD_HANDLE70=(Token)match(input,INSTRUCTION_FORMAT21c_METHOD_HANDLE,FOLLOW_INSTRUCTION_FORMAT21c_METHOD_HANDLE_in_simple_name2137);
					stream_INSTRUCTION_FORMAT21c_METHOD_HANDLE.add(INSTRUCTION_FORMAT21c_METHOD_HANDLE70);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 582:41: -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_METHOD_HANDLE]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT21c_METHOD_HANDLE70));
					}


					retval.tree = root_0;

					}
					break;
				case 26 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:583:5: INSTRUCTION_FORMAT21c_METHOD_TYPE
					{
					INSTRUCTION_FORMAT21c_METHOD_TYPE71=(Token)match(input,INSTRUCTION_FORMAT21c_METHOD_TYPE,FOLLOW_INSTRUCTION_FORMAT21c_METHOD_TYPE_in_simple_name2148);
					stream_INSTRUCTION_FORMAT21c_METHOD_TYPE.add(INSTRUCTION_FORMAT21c_METHOD_TYPE71);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 583:39: -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_METHOD_TYPE]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT21c_METHOD_TYPE71));
					}


					retval.tree = root_0;

					}
					break;
				case 27 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:584:5: INSTRUCTION_FORMAT21c_STRING
					{
					INSTRUCTION_FORMAT21c_STRING72=(Token)match(input,INSTRUCTION_FORMAT21c_STRING,FOLLOW_INSTRUCTION_FORMAT21c_STRING_in_simple_name2159);
					stream_INSTRUCTION_FORMAT21c_STRING.add(INSTRUCTION_FORMAT21c_STRING72);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 584:34: -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_STRING]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT21c_STRING72));
					}


					retval.tree = root_0;

					}
					break;
				case 28 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:585:5: INSTRUCTION_FORMAT21c_TYPE
					{
					INSTRUCTION_FORMAT21c_TYPE73=(Token)match(input,INSTRUCTION_FORMAT21c_TYPE,FOLLOW_INSTRUCTION_FORMAT21c_TYPE_in_simple_name2170);
					stream_INSTRUCTION_FORMAT21c_TYPE.add(INSTRUCTION_FORMAT21c_TYPE73);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 585:32: -> SIMPLE_NAME[$INSTRUCTION_FORMAT21c_TYPE]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT21c_TYPE73));
					}


					retval.tree = root_0;

					}
					break;
				case 29 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:586:5: INSTRUCTION_FORMAT21t
					{
					INSTRUCTION_FORMAT21t74=(Token)match(input,INSTRUCTION_FORMAT21t,FOLLOW_INSTRUCTION_FORMAT21t_in_simple_name2181);
					stream_INSTRUCTION_FORMAT21t.add(INSTRUCTION_FORMAT21t74);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 586:27: -> SIMPLE_NAME[$INSTRUCTION_FORMAT21t]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT21t74));
					}


					retval.tree = root_0;

					}
					break;
				case 30 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:587:5: INSTRUCTION_FORMAT22c_FIELD
					{
					INSTRUCTION_FORMAT22c_FIELD75=(Token)match(input,INSTRUCTION_FORMAT22c_FIELD,FOLLOW_INSTRUCTION_FORMAT22c_FIELD_in_simple_name2192);
					stream_INSTRUCTION_FORMAT22c_FIELD.add(INSTRUCTION_FORMAT22c_FIELD75);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 587:33: -> SIMPLE_NAME[$INSTRUCTION_FORMAT22c_FIELD]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT22c_FIELD75));
					}


					retval.tree = root_0;

					}
					break;
				case 31 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:588:5: INSTRUCTION_FORMAT22c_FIELD_ODEX
					{
					INSTRUCTION_FORMAT22c_FIELD_ODEX76=(Token)match(input,INSTRUCTION_FORMAT22c_FIELD_ODEX,FOLLOW_INSTRUCTION_FORMAT22c_FIELD_ODEX_in_simple_name2203);
					stream_INSTRUCTION_FORMAT22c_FIELD_ODEX.add(INSTRUCTION_FORMAT22c_FIELD_ODEX76);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 588:38: -> SIMPLE_NAME[$INSTRUCTION_FORMAT22c_FIELD_ODEX]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT22c_FIELD_ODEX76));
					}


					retval.tree = root_0;

					}
					break;
				case 32 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:589:5: INSTRUCTION_FORMAT22c_TYPE
					{
					INSTRUCTION_FORMAT22c_TYPE77=(Token)match(input,INSTRUCTION_FORMAT22c_TYPE,FOLLOW_INSTRUCTION_FORMAT22c_TYPE_in_simple_name2214);
					stream_INSTRUCTION_FORMAT22c_TYPE.add(INSTRUCTION_FORMAT22c_TYPE77);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 589:32: -> SIMPLE_NAME[$INSTRUCTION_FORMAT22c_TYPE]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT22c_TYPE77));
					}


					retval.tree = root_0;

					}
					break;
				case 33 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:590:5: INSTRUCTION_FORMAT22cs_FIELD
					{
					INSTRUCTION_FORMAT22cs_FIELD78=(Token)match(input,INSTRUCTION_FORMAT22cs_FIELD,FOLLOW_INSTRUCTION_FORMAT22cs_FIELD_in_simple_name2225);
					stream_INSTRUCTION_FORMAT22cs_FIELD.add(INSTRUCTION_FORMAT22cs_FIELD78);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 590:34: -> SIMPLE_NAME[$INSTRUCTION_FORMAT22cs_FIELD]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT22cs_FIELD78));
					}


					retval.tree = root_0;

					}
					break;
				case 34 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:591:5: INSTRUCTION_FORMAT22s_OR_ID
					{
					INSTRUCTION_FORMAT22s_OR_ID79=(Token)match(input,INSTRUCTION_FORMAT22s_OR_ID,FOLLOW_INSTRUCTION_FORMAT22s_OR_ID_in_simple_name2236);
					stream_INSTRUCTION_FORMAT22s_OR_ID.add(INSTRUCTION_FORMAT22s_OR_ID79);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 591:33: -> SIMPLE_NAME[$INSTRUCTION_FORMAT22s_OR_ID]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT22s_OR_ID79));
					}


					retval.tree = root_0;

					}
					break;
				case 35 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:592:5: INSTRUCTION_FORMAT22t
					{
					INSTRUCTION_FORMAT22t80=(Token)match(input,INSTRUCTION_FORMAT22t,FOLLOW_INSTRUCTION_FORMAT22t_in_simple_name2247);
					stream_INSTRUCTION_FORMAT22t.add(INSTRUCTION_FORMAT22t80);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 592:27: -> SIMPLE_NAME[$INSTRUCTION_FORMAT22t]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT22t80));
					}


					retval.tree = root_0;

					}
					break;
				case 36 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:593:5: INSTRUCTION_FORMAT23x
					{
					INSTRUCTION_FORMAT23x81=(Token)match(input,INSTRUCTION_FORMAT23x,FOLLOW_INSTRUCTION_FORMAT23x_in_simple_name2258);
					stream_INSTRUCTION_FORMAT23x.add(INSTRUCTION_FORMAT23x81);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 593:27: -> SIMPLE_NAME[$INSTRUCTION_FORMAT23x]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT23x81));
					}


					retval.tree = root_0;

					}
					break;
				case 37 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:594:5: INSTRUCTION_FORMAT31i_OR_ID
					{
					INSTRUCTION_FORMAT31i_OR_ID82=(Token)match(input,INSTRUCTION_FORMAT31i_OR_ID,FOLLOW_INSTRUCTION_FORMAT31i_OR_ID_in_simple_name2269);
					stream_INSTRUCTION_FORMAT31i_OR_ID.add(INSTRUCTION_FORMAT31i_OR_ID82);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 594:33: -> SIMPLE_NAME[$INSTRUCTION_FORMAT31i_OR_ID]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT31i_OR_ID82));
					}


					retval.tree = root_0;

					}
					break;
				case 38 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:595:5: INSTRUCTION_FORMAT31t
					{
					INSTRUCTION_FORMAT31t83=(Token)match(input,INSTRUCTION_FORMAT31t,FOLLOW_INSTRUCTION_FORMAT31t_in_simple_name2280);
					stream_INSTRUCTION_FORMAT31t.add(INSTRUCTION_FORMAT31t83);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 595:27: -> SIMPLE_NAME[$INSTRUCTION_FORMAT31t]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT31t83));
					}


					retval.tree = root_0;

					}
					break;
				case 39 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:596:5: INSTRUCTION_FORMAT35c_CALL_SITE
					{
					INSTRUCTION_FORMAT35c_CALL_SITE84=(Token)match(input,INSTRUCTION_FORMAT35c_CALL_SITE,FOLLOW_INSTRUCTION_FORMAT35c_CALL_SITE_in_simple_name2291);
					stream_INSTRUCTION_FORMAT35c_CALL_SITE.add(INSTRUCTION_FORMAT35c_CALL_SITE84);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 596:37: -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_CALL_SITE]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT35c_CALL_SITE84));
					}


					retval.tree = root_0;

					}
					break;
				case 40 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:597:5: INSTRUCTION_FORMAT35c_METHOD
					{
					INSTRUCTION_FORMAT35c_METHOD85=(Token)match(input,INSTRUCTION_FORMAT35c_METHOD,FOLLOW_INSTRUCTION_FORMAT35c_METHOD_in_simple_name2302);
					stream_INSTRUCTION_FORMAT35c_METHOD.add(INSTRUCTION_FORMAT35c_METHOD85);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 597:34: -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_METHOD]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT35c_METHOD85));
					}


					retval.tree = root_0;

					}
					break;
				case 41 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:598:5: INSTRUCTION_FORMAT35c_METHOD_ODEX
					{
					INSTRUCTION_FORMAT35c_METHOD_ODEX86=(Token)match(input,INSTRUCTION_FORMAT35c_METHOD_ODEX,FOLLOW_INSTRUCTION_FORMAT35c_METHOD_ODEX_in_simple_name2313);
					stream_INSTRUCTION_FORMAT35c_METHOD_ODEX.add(INSTRUCTION_FORMAT35c_METHOD_ODEX86);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 598:39: -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_METHOD_ODEX]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT35c_METHOD_ODEX86));
					}


					retval.tree = root_0;

					}
					break;
				case 42 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:599:5: INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE
					{
					INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE87=(Token)match(input,INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE,FOLLOW_INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE_in_simple_name2324);
					stream_INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE.add(INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE87);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 599:56: -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE87));
					}


					retval.tree = root_0;

					}
					break;
				case 43 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:600:5: INSTRUCTION_FORMAT35c_TYPE
					{
					INSTRUCTION_FORMAT35c_TYPE88=(Token)match(input,INSTRUCTION_FORMAT35c_TYPE,FOLLOW_INSTRUCTION_FORMAT35c_TYPE_in_simple_name2335);
					stream_INSTRUCTION_FORMAT35c_TYPE.add(INSTRUCTION_FORMAT35c_TYPE88);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 600:32: -> SIMPLE_NAME[$INSTRUCTION_FORMAT35c_TYPE]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT35c_TYPE88));
					}


					retval.tree = root_0;

					}
					break;
				case 44 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:601:5: INSTRUCTION_FORMAT35mi_METHOD
					{
					INSTRUCTION_FORMAT35mi_METHOD89=(Token)match(input,INSTRUCTION_FORMAT35mi_METHOD,FOLLOW_INSTRUCTION_FORMAT35mi_METHOD_in_simple_name2346);
					stream_INSTRUCTION_FORMAT35mi_METHOD.add(INSTRUCTION_FORMAT35mi_METHOD89);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 601:35: -> SIMPLE_NAME[$INSTRUCTION_FORMAT35mi_METHOD]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT35mi_METHOD89));
					}


					retval.tree = root_0;

					}
					break;
				case 45 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:602:5: INSTRUCTION_FORMAT35ms_METHOD
					{
					INSTRUCTION_FORMAT35ms_METHOD90=(Token)match(input,INSTRUCTION_FORMAT35ms_METHOD,FOLLOW_INSTRUCTION_FORMAT35ms_METHOD_in_simple_name2357);
					stream_INSTRUCTION_FORMAT35ms_METHOD.add(INSTRUCTION_FORMAT35ms_METHOD90);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 602:35: -> SIMPLE_NAME[$INSTRUCTION_FORMAT35ms_METHOD]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT35ms_METHOD90));
					}


					retval.tree = root_0;

					}
					break;
				case 46 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:603:5: INSTRUCTION_FORMAT45cc_METHOD
					{
					INSTRUCTION_FORMAT45cc_METHOD91=(Token)match(input,INSTRUCTION_FORMAT45cc_METHOD,FOLLOW_INSTRUCTION_FORMAT45cc_METHOD_in_simple_name2368);
					stream_INSTRUCTION_FORMAT45cc_METHOD.add(INSTRUCTION_FORMAT45cc_METHOD91);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 603:35: -> SIMPLE_NAME[$INSTRUCTION_FORMAT45cc_METHOD]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT45cc_METHOD91));
					}


					retval.tree = root_0;

					}
					break;
				case 47 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:604:5: INSTRUCTION_FORMAT4rcc_METHOD
					{
					INSTRUCTION_FORMAT4rcc_METHOD92=(Token)match(input,INSTRUCTION_FORMAT4rcc_METHOD,FOLLOW_INSTRUCTION_FORMAT4rcc_METHOD_in_simple_name2379);
					stream_INSTRUCTION_FORMAT4rcc_METHOD.add(INSTRUCTION_FORMAT4rcc_METHOD92);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 604:35: -> SIMPLE_NAME[$INSTRUCTION_FORMAT4rcc_METHOD]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT4rcc_METHOD92));
					}


					retval.tree = root_0;

					}
					break;
				case 48 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:605:5: INSTRUCTION_FORMAT51l
					{
					INSTRUCTION_FORMAT51l93=(Token)match(input,INSTRUCTION_FORMAT51l,FOLLOW_INSTRUCTION_FORMAT51l_in_simple_name2390);
					stream_INSTRUCTION_FORMAT51l.add(INSTRUCTION_FORMAT51l93);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 605:27: -> SIMPLE_NAME[$INSTRUCTION_FORMAT51l]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, INSTRUCTION_FORMAT51l93));
					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "simple_name"


	public static class member_name_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "member_name"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:607:1: member_name : ( simple_name | MEMBER_NAME -> SIMPLE_NAME[$MEMBER_NAME] );
	public final smaliParser.member_name_return member_name() throws RecognitionException {
		smaliParser.member_name_return retval = new smaliParser.member_name_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token MEMBER_NAME95=null;
		ParserRuleReturnScope simple_name94 =null;

		CommonTree MEMBER_NAME95_tree=null;
		RewriteRuleTokenStream stream_MEMBER_NAME=new RewriteRuleTokenStream(adaptor,"token MEMBER_NAME");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:608:3: ( simple_name | MEMBER_NAME -> SIMPLE_NAME[$MEMBER_NAME] )
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0==ACCESS_SPEC||LA12_0==ANNOTATION_VISIBILITY||LA12_0==BOOL_LITERAL||LA12_0==DOUBLE_LITERAL_OR_ID||(LA12_0 >= FLOAT_LITERAL_OR_ID && LA12_0 <= HIDDENAPI_RESTRICTION)||(LA12_0 >= INSTRUCTION_FORMAT10t && LA12_0 <= INSTRUCTION_FORMAT10x_ODEX)||LA12_0==INSTRUCTION_FORMAT11x||LA12_0==INSTRUCTION_FORMAT12x_OR_ID||(LA12_0 >= INSTRUCTION_FORMAT21c_FIELD && LA12_0 <= INSTRUCTION_FORMAT21c_TYPE)||LA12_0==INSTRUCTION_FORMAT21t||(LA12_0 >= INSTRUCTION_FORMAT22c_FIELD && LA12_0 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA12_0 >= INSTRUCTION_FORMAT22s_OR_ID && LA12_0 <= INSTRUCTION_FORMAT22t)||LA12_0==INSTRUCTION_FORMAT23x||(LA12_0 >= INSTRUCTION_FORMAT31i_OR_ID && LA12_0 <= INSTRUCTION_FORMAT31t)||(LA12_0 >= INSTRUCTION_FORMAT35c_CALL_SITE && LA12_0 <= INSTRUCTION_FORMAT35ms_METHOD)||(LA12_0 >= INSTRUCTION_FORMAT45cc_METHOD && LA12_0 <= INSTRUCTION_FORMAT51l)||(LA12_0 >= METHOD_HANDLE_TYPE_FIELD && LA12_0 <= NULL_LITERAL)||(LA12_0 >= PARAM_LIST_OR_ID_PRIMITIVE_TYPE && LA12_0 <= PRIMITIVE_TYPE)||LA12_0==REGISTER||LA12_0==SIMPLE_NAME||(LA12_0 >= VERIFICATION_ERROR_TYPE && LA12_0 <= VOID_TYPE)) ) {
				alt12=1;
			}
			else if ( (LA12_0==MEMBER_NAME) ) {
				alt12=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 12, 0, input);
				throw nvae;
			}

			switch (alt12) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:608:5: simple_name
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_simple_name_in_member_name2405);
					simple_name94=simple_name();
					state._fsp--;

					adaptor.addChild(root_0, simple_name94.getTree());

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:609:5: MEMBER_NAME
					{
					MEMBER_NAME95=(Token)match(input,MEMBER_NAME,FOLLOW_MEMBER_NAME_in_member_name2411);
					stream_MEMBER_NAME.add(MEMBER_NAME95);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 609:17: -> SIMPLE_NAME[$MEMBER_NAME]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(SIMPLE_NAME, MEMBER_NAME95));
					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "member_name"


	public static class method_prototype_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "method_prototype"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:611:1: method_prototype : OPEN_PAREN param_list CLOSE_PAREN type_descriptor -> ^( I_METHOD_PROTOTYPE[$start, \"I_METHOD_PROTOTYPE\"] ^( I_METHOD_RETURN_TYPE type_descriptor ) ( param_list )? ) ;
	public final smaliParser.method_prototype_return method_prototype() throws RecognitionException {
		smaliParser.method_prototype_return retval = new smaliParser.method_prototype_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token OPEN_PAREN96=null;
		Token CLOSE_PAREN98=null;
		ParserRuleReturnScope param_list97 =null;
		ParserRuleReturnScope type_descriptor99 =null;

		CommonTree OPEN_PAREN96_tree=null;
		CommonTree CLOSE_PAREN98_tree=null;
		RewriteRuleTokenStream stream_OPEN_PAREN=new RewriteRuleTokenStream(adaptor,"token OPEN_PAREN");
		RewriteRuleTokenStream stream_CLOSE_PAREN=new RewriteRuleTokenStream(adaptor,"token CLOSE_PAREN");
		RewriteRuleSubtreeStream stream_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule type_descriptor");
		RewriteRuleSubtreeStream stream_param_list=new RewriteRuleSubtreeStream(adaptor,"rule param_list");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:612:3: ( OPEN_PAREN param_list CLOSE_PAREN type_descriptor -> ^( I_METHOD_PROTOTYPE[$start, \"I_METHOD_PROTOTYPE\"] ^( I_METHOD_RETURN_TYPE type_descriptor ) ( param_list )? ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:612:5: OPEN_PAREN param_list CLOSE_PAREN type_descriptor
			{
			OPEN_PAREN96=(Token)match(input,OPEN_PAREN,FOLLOW_OPEN_PAREN_in_method_prototype2426);
			stream_OPEN_PAREN.add(OPEN_PAREN96);

			pushFollow(FOLLOW_param_list_in_method_prototype2428);
			param_list97=param_list();
			state._fsp--;

			stream_param_list.add(param_list97.getTree());
			CLOSE_PAREN98=(Token)match(input,CLOSE_PAREN,FOLLOW_CLOSE_PAREN_in_method_prototype2430);
			stream_CLOSE_PAREN.add(CLOSE_PAREN98);

			pushFollow(FOLLOW_type_descriptor_in_method_prototype2432);
			type_descriptor99=type_descriptor();
			state._fsp--;

			stream_type_descriptor.add(type_descriptor99.getTree());
			// AST REWRITE
			// elements: param_list, type_descriptor
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 613:5: -> ^( I_METHOD_PROTOTYPE[$start, \"I_METHOD_PROTOTYPE\"] ^( I_METHOD_RETURN_TYPE type_descriptor ) ( param_list )? )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:613:8: ^( I_METHOD_PROTOTYPE[$start, \"I_METHOD_PROTOTYPE\"] ^( I_METHOD_RETURN_TYPE type_descriptor ) ( param_list )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_METHOD_PROTOTYPE, (retval.start), "I_METHOD_PROTOTYPE"), root_1);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:613:59: ^( I_METHOD_RETURN_TYPE type_descriptor )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_METHOD_RETURN_TYPE, "I_METHOD_RETURN_TYPE"), root_2);
				adaptor.addChild(root_2, stream_type_descriptor.nextTree());
				adaptor.addChild(root_1, root_2);
				}

				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:613:99: ( param_list )?
				if ( stream_param_list.hasNext() ) {
					adaptor.addChild(root_1, stream_param_list.nextTree());
				}
				stream_param_list.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "method_prototype"


	public static class param_list_or_id_primitive_type_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "param_list_or_id_primitive_type"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:615:1: param_list_or_id_primitive_type : PARAM_LIST_OR_ID_PRIMITIVE_TYPE -> PRIMITIVE_TYPE[$PARAM_LIST_OR_ID_PRIMITIVE_TYPE] ;
	public final smaliParser.param_list_or_id_primitive_type_return param_list_or_id_primitive_type() throws RecognitionException {
		smaliParser.param_list_or_id_primitive_type_return retval = new smaliParser.param_list_or_id_primitive_type_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token PARAM_LIST_OR_ID_PRIMITIVE_TYPE100=null;

		CommonTree PARAM_LIST_OR_ID_PRIMITIVE_TYPE100_tree=null;
		RewriteRuleTokenStream stream_PARAM_LIST_OR_ID_PRIMITIVE_TYPE=new RewriteRuleTokenStream(adaptor,"token PARAM_LIST_OR_ID_PRIMITIVE_TYPE");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:616:3: ( PARAM_LIST_OR_ID_PRIMITIVE_TYPE -> PRIMITIVE_TYPE[$PARAM_LIST_OR_ID_PRIMITIVE_TYPE] )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:616:5: PARAM_LIST_OR_ID_PRIMITIVE_TYPE
			{
			PARAM_LIST_OR_ID_PRIMITIVE_TYPE100=(Token)match(input,PARAM_LIST_OR_ID_PRIMITIVE_TYPE,FOLLOW_PARAM_LIST_OR_ID_PRIMITIVE_TYPE_in_param_list_or_id_primitive_type2462);
			stream_PARAM_LIST_OR_ID_PRIMITIVE_TYPE.add(PARAM_LIST_OR_ID_PRIMITIVE_TYPE100);

			// AST REWRITE
			// elements:
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 616:37: -> PRIMITIVE_TYPE[$PARAM_LIST_OR_ID_PRIMITIVE_TYPE]
			{
				adaptor.addChild(root_0, (CommonTree)adaptor.create(PRIMITIVE_TYPE, PARAM_LIST_OR_ID_PRIMITIVE_TYPE100));
			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "param_list_or_id_primitive_type"


	public static class param_list_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "param_list"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:618:1: param_list : ( ( param_list_or_id_primitive_type )+ | ( nonvoid_type_descriptor )* );
	public final smaliParser.param_list_return param_list() throws RecognitionException {
		smaliParser.param_list_return retval = new smaliParser.param_list_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope param_list_or_id_primitive_type101 =null;
		ParserRuleReturnScope nonvoid_type_descriptor102 =null;


		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:619:3: ( ( param_list_or_id_primitive_type )+ | ( nonvoid_type_descriptor )* )
			int alt15=2;
			int LA15_0 = input.LA(1);
			if ( (LA15_0==PARAM_LIST_OR_ID_PRIMITIVE_TYPE) ) {
				alt15=1;
			}
			else if ( (LA15_0==ARRAY_TYPE_PREFIX||LA15_0==CLASS_DESCRIPTOR||LA15_0==CLOSE_PAREN||LA15_0==PRIMITIVE_TYPE) ) {
				alt15=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 15, 0, input);
				throw nvae;
			}

			switch (alt15) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:619:5: ( param_list_or_id_primitive_type )+
					{
					root_0 = (CommonTree)adaptor.nil();


					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:619:5: ( param_list_or_id_primitive_type )+
					int cnt13=0;
					loop13:
					while (true) {
						int alt13=2;
						int LA13_0 = input.LA(1);
						if ( (LA13_0==PARAM_LIST_OR_ID_PRIMITIVE_TYPE) ) {
							alt13=1;
						}

						switch (alt13) {
						case 1 :
							// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:619:5: param_list_or_id_primitive_type
							{
							pushFollow(FOLLOW_param_list_or_id_primitive_type_in_param_list2477);
							param_list_or_id_primitive_type101=param_list_or_id_primitive_type();
							state._fsp--;

							adaptor.addChild(root_0, param_list_or_id_primitive_type101.getTree());

							}
							break;

						default :
							if ( cnt13 >= 1 ) break loop13;
							EarlyExitException eee = new EarlyExitException(13, input);
							throw eee;
						}
						cnt13++;
					}

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:620:5: ( nonvoid_type_descriptor )*
					{
					root_0 = (CommonTree)adaptor.nil();


					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:620:5: ( nonvoid_type_descriptor )*
					loop14:
					while (true) {
						int alt14=2;
						int LA14_0 = input.LA(1);
						if ( (LA14_0==ARRAY_TYPE_PREFIX||LA14_0==CLASS_DESCRIPTOR||LA14_0==PRIMITIVE_TYPE) ) {
							alt14=1;
						}

						switch (alt14) {
						case 1 :
							// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:620:5: nonvoid_type_descriptor
							{
							pushFollow(FOLLOW_nonvoid_type_descriptor_in_param_list2484);
							nonvoid_type_descriptor102=nonvoid_type_descriptor();
							state._fsp--;

							adaptor.addChild(root_0, nonvoid_type_descriptor102.getTree());

							}
							break;

						default :
							break loop14;
						}
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "param_list"


	public static class array_descriptor_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "array_descriptor"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:622:1: array_descriptor : ARRAY_TYPE_PREFIX ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR ) ;
	public final smaliParser.array_descriptor_return array_descriptor() throws RecognitionException {
		smaliParser.array_descriptor_return retval = new smaliParser.array_descriptor_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ARRAY_TYPE_PREFIX103=null;
		Token set104=null;

		CommonTree ARRAY_TYPE_PREFIX103_tree=null;
		CommonTree set104_tree=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:623:3: ( ARRAY_TYPE_PREFIX ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:623:5: ARRAY_TYPE_PREFIX ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR )
			{
			root_0 = (CommonTree)adaptor.nil();


			ARRAY_TYPE_PREFIX103=(Token)match(input,ARRAY_TYPE_PREFIX,FOLLOW_ARRAY_TYPE_PREFIX_in_array_descriptor2495);
			ARRAY_TYPE_PREFIX103_tree = (CommonTree)adaptor.create(ARRAY_TYPE_PREFIX103);
			adaptor.addChild(root_0, ARRAY_TYPE_PREFIX103_tree);

			set104=input.LT(1);
			if ( input.LA(1)==CLASS_DESCRIPTOR||input.LA(1)==PRIMITIVE_TYPE ) {
				input.consume();
				adaptor.addChild(root_0, (CommonTree)adaptor.create(set104));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "array_descriptor"


	public static class type_descriptor_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "type_descriptor"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:625:1: type_descriptor : ( VOID_TYPE | PRIMITIVE_TYPE | CLASS_DESCRIPTOR | array_descriptor );
	public final smaliParser.type_descriptor_return type_descriptor() throws RecognitionException {
		smaliParser.type_descriptor_return retval = new smaliParser.type_descriptor_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token VOID_TYPE105=null;
		Token PRIMITIVE_TYPE106=null;
		Token CLASS_DESCRIPTOR107=null;
		ParserRuleReturnScope array_descriptor108 =null;

		CommonTree VOID_TYPE105_tree=null;
		CommonTree PRIMITIVE_TYPE106_tree=null;
		CommonTree CLASS_DESCRIPTOR107_tree=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:626:3: ( VOID_TYPE | PRIMITIVE_TYPE | CLASS_DESCRIPTOR | array_descriptor )
			int alt16=4;
			switch ( input.LA(1) ) {
			case VOID_TYPE:
				{
				alt16=1;
				}
				break;
			case PRIMITIVE_TYPE:
				{
				alt16=2;
				}
				break;
			case CLASS_DESCRIPTOR:
				{
				alt16=3;
				}
				break;
			case ARRAY_TYPE_PREFIX:
				{
				alt16=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 16, 0, input);
				throw nvae;
			}
			switch (alt16) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:626:5: VOID_TYPE
					{
					root_0 = (CommonTree)adaptor.nil();


					VOID_TYPE105=(Token)match(input,VOID_TYPE,FOLLOW_VOID_TYPE_in_type_descriptor2513);
					VOID_TYPE105_tree = (CommonTree)adaptor.create(VOID_TYPE105);
					adaptor.addChild(root_0, VOID_TYPE105_tree);

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:627:5: PRIMITIVE_TYPE
					{
					root_0 = (CommonTree)adaptor.nil();


					PRIMITIVE_TYPE106=(Token)match(input,PRIMITIVE_TYPE,FOLLOW_PRIMITIVE_TYPE_in_type_descriptor2519);
					PRIMITIVE_TYPE106_tree = (CommonTree)adaptor.create(PRIMITIVE_TYPE106);
					adaptor.addChild(root_0, PRIMITIVE_TYPE106_tree);

					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:628:5: CLASS_DESCRIPTOR
					{
					root_0 = (CommonTree)adaptor.nil();


					CLASS_DESCRIPTOR107=(Token)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_type_descriptor2525);
					CLASS_DESCRIPTOR107_tree = (CommonTree)adaptor.create(CLASS_DESCRIPTOR107);
					adaptor.addChild(root_0, CLASS_DESCRIPTOR107_tree);

					}
					break;
				case 4 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:629:5: array_descriptor
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_array_descriptor_in_type_descriptor2531);
					array_descriptor108=array_descriptor();
					state._fsp--;

					adaptor.addChild(root_0, array_descriptor108.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "type_descriptor"


	public static class nonvoid_type_descriptor_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "nonvoid_type_descriptor"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:631:1: nonvoid_type_descriptor : ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR | array_descriptor );
	public final smaliParser.nonvoid_type_descriptor_return nonvoid_type_descriptor() throws RecognitionException {
		smaliParser.nonvoid_type_descriptor_return retval = new smaliParser.nonvoid_type_descriptor_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token PRIMITIVE_TYPE109=null;
		Token CLASS_DESCRIPTOR110=null;
		ParserRuleReturnScope array_descriptor111 =null;

		CommonTree PRIMITIVE_TYPE109_tree=null;
		CommonTree CLASS_DESCRIPTOR110_tree=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:632:3: ( PRIMITIVE_TYPE | CLASS_DESCRIPTOR | array_descriptor )
			int alt17=3;
			switch ( input.LA(1) ) {
			case PRIMITIVE_TYPE:
				{
				alt17=1;
				}
				break;
			case CLASS_DESCRIPTOR:
				{
				alt17=2;
				}
				break;
			case ARRAY_TYPE_PREFIX:
				{
				alt17=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 17, 0, input);
				throw nvae;
			}
			switch (alt17) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:632:5: PRIMITIVE_TYPE
					{
					root_0 = (CommonTree)adaptor.nil();


					PRIMITIVE_TYPE109=(Token)match(input,PRIMITIVE_TYPE,FOLLOW_PRIMITIVE_TYPE_in_nonvoid_type_descriptor2541);
					PRIMITIVE_TYPE109_tree = (CommonTree)adaptor.create(PRIMITIVE_TYPE109);
					adaptor.addChild(root_0, PRIMITIVE_TYPE109_tree);

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:633:5: CLASS_DESCRIPTOR
					{
					root_0 = (CommonTree)adaptor.nil();


					CLASS_DESCRIPTOR110=(Token)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_nonvoid_type_descriptor2547);
					CLASS_DESCRIPTOR110_tree = (CommonTree)adaptor.create(CLASS_DESCRIPTOR110);
					adaptor.addChild(root_0, CLASS_DESCRIPTOR110_tree);

					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:634:5: array_descriptor
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_array_descriptor_in_nonvoid_type_descriptor2553);
					array_descriptor111=array_descriptor();
					state._fsp--;

					adaptor.addChild(root_0, array_descriptor111.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "nonvoid_type_descriptor"


	public static class reference_type_descriptor_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "reference_type_descriptor"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:636:1: reference_type_descriptor : ( CLASS_DESCRIPTOR | array_descriptor );
	public final smaliParser.reference_type_descriptor_return reference_type_descriptor() throws RecognitionException {
		smaliParser.reference_type_descriptor_return retval = new smaliParser.reference_type_descriptor_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token CLASS_DESCRIPTOR112=null;
		ParserRuleReturnScope array_descriptor113 =null;

		CommonTree CLASS_DESCRIPTOR112_tree=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:637:3: ( CLASS_DESCRIPTOR | array_descriptor )
			int alt18=2;
			int LA18_0 = input.LA(1);
			if ( (LA18_0==CLASS_DESCRIPTOR) ) {
				alt18=1;
			}
			else if ( (LA18_0==ARRAY_TYPE_PREFIX) ) {
				alt18=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 18, 0, input);
				throw nvae;
			}

			switch (alt18) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:637:5: CLASS_DESCRIPTOR
					{
					root_0 = (CommonTree)adaptor.nil();


					CLASS_DESCRIPTOR112=(Token)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_reference_type_descriptor2563);
					CLASS_DESCRIPTOR112_tree = (CommonTree)adaptor.create(CLASS_DESCRIPTOR112);
					adaptor.addChild(root_0, CLASS_DESCRIPTOR112_tree);

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:638:5: array_descriptor
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_array_descriptor_in_reference_type_descriptor2569);
					array_descriptor113=array_descriptor();
					state._fsp--;

					adaptor.addChild(root_0, array_descriptor113.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "reference_type_descriptor"


	public static class integer_literal_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "integer_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:640:1: integer_literal : ( POSITIVE_INTEGER_LITERAL -> INTEGER_LITERAL[$POSITIVE_INTEGER_LITERAL] | NEGATIVE_INTEGER_LITERAL -> INTEGER_LITERAL[$NEGATIVE_INTEGER_LITERAL] );
	public final smaliParser.integer_literal_return integer_literal() throws RecognitionException {
		smaliParser.integer_literal_return retval = new smaliParser.integer_literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token POSITIVE_INTEGER_LITERAL114=null;
		Token NEGATIVE_INTEGER_LITERAL115=null;

		CommonTree POSITIVE_INTEGER_LITERAL114_tree=null;
		CommonTree NEGATIVE_INTEGER_LITERAL115_tree=null;
		RewriteRuleTokenStream stream_NEGATIVE_INTEGER_LITERAL=new RewriteRuleTokenStream(adaptor,"token NEGATIVE_INTEGER_LITERAL");
		RewriteRuleTokenStream stream_POSITIVE_INTEGER_LITERAL=new RewriteRuleTokenStream(adaptor,"token POSITIVE_INTEGER_LITERAL");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:641:3: ( POSITIVE_INTEGER_LITERAL -> INTEGER_LITERAL[$POSITIVE_INTEGER_LITERAL] | NEGATIVE_INTEGER_LITERAL -> INTEGER_LITERAL[$NEGATIVE_INTEGER_LITERAL] )
			int alt19=2;
			int LA19_0 = input.LA(1);
			if ( (LA19_0==POSITIVE_INTEGER_LITERAL) ) {
				alt19=1;
			}
			else if ( (LA19_0==NEGATIVE_INTEGER_LITERAL) ) {
				alt19=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 19, 0, input);
				throw nvae;
			}

			switch (alt19) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:641:5: POSITIVE_INTEGER_LITERAL
					{
					POSITIVE_INTEGER_LITERAL114=(Token)match(input,POSITIVE_INTEGER_LITERAL,FOLLOW_POSITIVE_INTEGER_LITERAL_in_integer_literal2579);
					stream_POSITIVE_INTEGER_LITERAL.add(POSITIVE_INTEGER_LITERAL114);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 641:30: -> INTEGER_LITERAL[$POSITIVE_INTEGER_LITERAL]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(INTEGER_LITERAL, POSITIVE_INTEGER_LITERAL114));
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:642:5: NEGATIVE_INTEGER_LITERAL
					{
					NEGATIVE_INTEGER_LITERAL115=(Token)match(input,NEGATIVE_INTEGER_LITERAL,FOLLOW_NEGATIVE_INTEGER_LITERAL_in_integer_literal2590);
					stream_NEGATIVE_INTEGER_LITERAL.add(NEGATIVE_INTEGER_LITERAL115);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 642:30: -> INTEGER_LITERAL[$NEGATIVE_INTEGER_LITERAL]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(INTEGER_LITERAL, NEGATIVE_INTEGER_LITERAL115));
					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "integer_literal"


	public static class float_literal_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "float_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:644:1: float_literal : ( FLOAT_LITERAL_OR_ID -> FLOAT_LITERAL[$FLOAT_LITERAL_OR_ID] | FLOAT_LITERAL );
	public final smaliParser.float_literal_return float_literal() throws RecognitionException {
		smaliParser.float_literal_return retval = new smaliParser.float_literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token FLOAT_LITERAL_OR_ID116=null;
		Token FLOAT_LITERAL117=null;

		CommonTree FLOAT_LITERAL_OR_ID116_tree=null;
		CommonTree FLOAT_LITERAL117_tree=null;
		RewriteRuleTokenStream stream_FLOAT_LITERAL_OR_ID=new RewriteRuleTokenStream(adaptor,"token FLOAT_LITERAL_OR_ID");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:645:3: ( FLOAT_LITERAL_OR_ID -> FLOAT_LITERAL[$FLOAT_LITERAL_OR_ID] | FLOAT_LITERAL )
			int alt20=2;
			int LA20_0 = input.LA(1);
			if ( (LA20_0==FLOAT_LITERAL_OR_ID) ) {
				alt20=1;
			}
			else if ( (LA20_0==FLOAT_LITERAL) ) {
				alt20=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 20, 0, input);
				throw nvae;
			}

			switch (alt20) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:645:5: FLOAT_LITERAL_OR_ID
					{
					FLOAT_LITERAL_OR_ID116=(Token)match(input,FLOAT_LITERAL_OR_ID,FOLLOW_FLOAT_LITERAL_OR_ID_in_float_literal2605);
					stream_FLOAT_LITERAL_OR_ID.add(FLOAT_LITERAL_OR_ID116);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 645:25: -> FLOAT_LITERAL[$FLOAT_LITERAL_OR_ID]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(FLOAT_LITERAL, FLOAT_LITERAL_OR_ID116));
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:646:5: FLOAT_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					FLOAT_LITERAL117=(Token)match(input,FLOAT_LITERAL,FOLLOW_FLOAT_LITERAL_in_float_literal2616);
					FLOAT_LITERAL117_tree = (CommonTree)adaptor.create(FLOAT_LITERAL117);
					adaptor.addChild(root_0, FLOAT_LITERAL117_tree);

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "float_literal"


	public static class double_literal_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "double_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:648:1: double_literal : ( DOUBLE_LITERAL_OR_ID -> DOUBLE_LITERAL[$DOUBLE_LITERAL_OR_ID] | DOUBLE_LITERAL );
	public final smaliParser.double_literal_return double_literal() throws RecognitionException {
		smaliParser.double_literal_return retval = new smaliParser.double_literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token DOUBLE_LITERAL_OR_ID118=null;
		Token DOUBLE_LITERAL119=null;

		CommonTree DOUBLE_LITERAL_OR_ID118_tree=null;
		CommonTree DOUBLE_LITERAL119_tree=null;
		RewriteRuleTokenStream stream_DOUBLE_LITERAL_OR_ID=new RewriteRuleTokenStream(adaptor,"token DOUBLE_LITERAL_OR_ID");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:649:3: ( DOUBLE_LITERAL_OR_ID -> DOUBLE_LITERAL[$DOUBLE_LITERAL_OR_ID] | DOUBLE_LITERAL )
			int alt21=2;
			int LA21_0 = input.LA(1);
			if ( (LA21_0==DOUBLE_LITERAL_OR_ID) ) {
				alt21=1;
			}
			else if ( (LA21_0==DOUBLE_LITERAL) ) {
				alt21=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 21, 0, input);
				throw nvae;
			}

			switch (alt21) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:649:5: DOUBLE_LITERAL_OR_ID
					{
					DOUBLE_LITERAL_OR_ID118=(Token)match(input,DOUBLE_LITERAL_OR_ID,FOLLOW_DOUBLE_LITERAL_OR_ID_in_double_literal2626);
					stream_DOUBLE_LITERAL_OR_ID.add(DOUBLE_LITERAL_OR_ID118);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 649:26: -> DOUBLE_LITERAL[$DOUBLE_LITERAL_OR_ID]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(DOUBLE_LITERAL, DOUBLE_LITERAL_OR_ID118));
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:650:5: DOUBLE_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					DOUBLE_LITERAL119=(Token)match(input,DOUBLE_LITERAL,FOLLOW_DOUBLE_LITERAL_in_double_literal2637);
					DOUBLE_LITERAL119_tree = (CommonTree)adaptor.create(DOUBLE_LITERAL119);
					adaptor.addChild(root_0, DOUBLE_LITERAL119_tree);

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "double_literal"


	public static class literal_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:652:1: literal : ( LONG_LITERAL | integer_literal | SHORT_LITERAL | BYTE_LITERAL | float_literal | double_literal | CHAR_LITERAL | STRING_LITERAL | BOOL_LITERAL | NULL_LITERAL | array_literal | subannotation | type_field_method_literal | enum_literal | method_handle_literal | method_prototype );
	public final smaliParser.literal_return literal() throws RecognitionException {
		smaliParser.literal_return retval = new smaliParser.literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token LONG_LITERAL120=null;
		Token SHORT_LITERAL122=null;
		Token BYTE_LITERAL123=null;
		Token CHAR_LITERAL126=null;
		Token STRING_LITERAL127=null;
		Token BOOL_LITERAL128=null;
		Token NULL_LITERAL129=null;
		ParserRuleReturnScope integer_literal121 =null;
		ParserRuleReturnScope float_literal124 =null;
		ParserRuleReturnScope double_literal125 =null;
		ParserRuleReturnScope array_literal130 =null;
		ParserRuleReturnScope subannotation131 =null;
		ParserRuleReturnScope type_field_method_literal132 =null;
		ParserRuleReturnScope enum_literal133 =null;
		ParserRuleReturnScope method_handle_literal134 =null;
		ParserRuleReturnScope method_prototype135 =null;

		CommonTree LONG_LITERAL120_tree=null;
		CommonTree SHORT_LITERAL122_tree=null;
		CommonTree BYTE_LITERAL123_tree=null;
		CommonTree CHAR_LITERAL126_tree=null;
		CommonTree STRING_LITERAL127_tree=null;
		CommonTree BOOL_LITERAL128_tree=null;
		CommonTree NULL_LITERAL129_tree=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:653:3: ( LONG_LITERAL | integer_literal | SHORT_LITERAL | BYTE_LITERAL | float_literal | double_literal | CHAR_LITERAL | STRING_LITERAL | BOOL_LITERAL | NULL_LITERAL | array_literal | subannotation | type_field_method_literal | enum_literal | method_handle_literal | method_prototype )
			int alt22=16;
			switch ( input.LA(1) ) {
			case LONG_LITERAL:
				{
				alt22=1;
				}
				break;
			case POSITIVE_INTEGER_LITERAL:
				{
				int LA22_2 = input.LA(2);
				if ( (LA22_2==EOF||(LA22_2 >= ACCESS_SPEC && LA22_2 <= ANNOTATION_VISIBILITY)||LA22_2==BOOL_LITERAL||(LA22_2 >= CLASS_DIRECTIVE && LA22_2 <= CLOSE_PAREN)||LA22_2==COMMA||(LA22_2 >= DOUBLE_LITERAL_OR_ID && LA22_2 <= END_ANNOTATION_DIRECTIVE)||LA22_2==END_FIELD_DIRECTIVE||LA22_2==END_SUBANNOTATION_DIRECTIVE||LA22_2==FIELD_DIRECTIVE||(LA22_2 >= FLOAT_LITERAL_OR_ID && LA22_2 <= IMPLEMENTS_DIRECTIVE)||(LA22_2 >= INSTRUCTION_FORMAT10t && LA22_2 <= INSTRUCTION_FORMAT10x_ODEX)||LA22_2==INSTRUCTION_FORMAT11x||LA22_2==INSTRUCTION_FORMAT12x_OR_ID||(LA22_2 >= INSTRUCTION_FORMAT21c_FIELD && LA22_2 <= INSTRUCTION_FORMAT21c_TYPE)||LA22_2==INSTRUCTION_FORMAT21t||(LA22_2 >= INSTRUCTION_FORMAT22c_FIELD && LA22_2 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA22_2 >= INSTRUCTION_FORMAT22s_OR_ID && LA22_2 <= INSTRUCTION_FORMAT22t)||LA22_2==INSTRUCTION_FORMAT23x||(LA22_2 >= INSTRUCTION_FORMAT31i_OR_ID && LA22_2 <= INSTRUCTION_FORMAT31t)||(LA22_2 >= INSTRUCTION_FORMAT35c_CALL_SITE && LA22_2 <= INSTRUCTION_FORMAT35ms_METHOD)||(LA22_2 >= INSTRUCTION_FORMAT45cc_METHOD && LA22_2 <= INSTRUCTION_FORMAT51l)||(LA22_2 >= METHOD_DIRECTIVE && LA22_2 <= NULL_LITERAL)||(LA22_2 >= PARAM_LIST_OR_ID_PRIMITIVE_TYPE && LA22_2 <= PRIMITIVE_TYPE)||LA22_2==REGISTER||(LA22_2 >= SIMPLE_NAME && LA22_2 <= SOURCE_DIRECTIVE)||(LA22_2 >= SUPER_DIRECTIVE && LA22_2 <= VOID_TYPE)) ) {
					alt22=2;
				}
				else if ( (LA22_2==COLON||LA22_2==OPEN_PAREN) ) {
					alt22=13;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 22, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case NEGATIVE_INTEGER_LITERAL:
				{
				int LA22_3 = input.LA(2);
				if ( (LA22_3==EOF||(LA22_3 >= ACCESS_SPEC && LA22_3 <= ANNOTATION_VISIBILITY)||LA22_3==BOOL_LITERAL||(LA22_3 >= CLASS_DIRECTIVE && LA22_3 <= CLOSE_PAREN)||LA22_3==COMMA||(LA22_3 >= DOUBLE_LITERAL_OR_ID && LA22_3 <= END_ANNOTATION_DIRECTIVE)||LA22_3==END_FIELD_DIRECTIVE||LA22_3==END_SUBANNOTATION_DIRECTIVE||LA22_3==FIELD_DIRECTIVE||(LA22_3 >= FLOAT_LITERAL_OR_ID && LA22_3 <= IMPLEMENTS_DIRECTIVE)||(LA22_3 >= INSTRUCTION_FORMAT10t && LA22_3 <= INSTRUCTION_FORMAT10x_ODEX)||LA22_3==INSTRUCTION_FORMAT11x||LA22_3==INSTRUCTION_FORMAT12x_OR_ID||(LA22_3 >= INSTRUCTION_FORMAT21c_FIELD && LA22_3 <= INSTRUCTION_FORMAT21c_TYPE)||LA22_3==INSTRUCTION_FORMAT21t||(LA22_3 >= INSTRUCTION_FORMAT22c_FIELD && LA22_3 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA22_3 >= INSTRUCTION_FORMAT22s_OR_ID && LA22_3 <= INSTRUCTION_FORMAT22t)||LA22_3==INSTRUCTION_FORMAT23x||(LA22_3 >= INSTRUCTION_FORMAT31i_OR_ID && LA22_3 <= INSTRUCTION_FORMAT31t)||(LA22_3 >= INSTRUCTION_FORMAT35c_CALL_SITE && LA22_3 <= INSTRUCTION_FORMAT35ms_METHOD)||(LA22_3 >= INSTRUCTION_FORMAT45cc_METHOD && LA22_3 <= INSTRUCTION_FORMAT51l)||(LA22_3 >= METHOD_DIRECTIVE && LA22_3 <= NULL_LITERAL)||(LA22_3 >= PARAM_LIST_OR_ID_PRIMITIVE_TYPE && LA22_3 <= PRIMITIVE_TYPE)||LA22_3==REGISTER||(LA22_3 >= SIMPLE_NAME && LA22_3 <= SOURCE_DIRECTIVE)||(LA22_3 >= SUPER_DIRECTIVE && LA22_3 <= VOID_TYPE)) ) {
					alt22=2;
				}
				else if ( (LA22_3==COLON||LA22_3==OPEN_PAREN) ) {
					alt22=13;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 22, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case SHORT_LITERAL:
				{
				alt22=3;
				}
				break;
			case BYTE_LITERAL:
				{
				alt22=4;
				}
				break;
			case FLOAT_LITERAL_OR_ID:
				{
				int LA22_6 = input.LA(2);
				if ( (LA22_6==EOF||(LA22_6 >= ACCESS_SPEC && LA22_6 <= ANNOTATION_VISIBILITY)||LA22_6==BOOL_LITERAL||(LA22_6 >= CLASS_DIRECTIVE && LA22_6 <= CLOSE_PAREN)||LA22_6==COMMA||(LA22_6 >= DOUBLE_LITERAL_OR_ID && LA22_6 <= END_ANNOTATION_DIRECTIVE)||LA22_6==END_FIELD_DIRECTIVE||LA22_6==END_SUBANNOTATION_DIRECTIVE||LA22_6==FIELD_DIRECTIVE||(LA22_6 >= FLOAT_LITERAL_OR_ID && LA22_6 <= IMPLEMENTS_DIRECTIVE)||(LA22_6 >= INSTRUCTION_FORMAT10t && LA22_6 <= INSTRUCTION_FORMAT10x_ODEX)||LA22_6==INSTRUCTION_FORMAT11x||LA22_6==INSTRUCTION_FORMAT12x_OR_ID||(LA22_6 >= INSTRUCTION_FORMAT21c_FIELD && LA22_6 <= INSTRUCTION_FORMAT21c_TYPE)||LA22_6==INSTRUCTION_FORMAT21t||(LA22_6 >= INSTRUCTION_FORMAT22c_FIELD && LA22_6 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA22_6 >= INSTRUCTION_FORMAT22s_OR_ID && LA22_6 <= INSTRUCTION_FORMAT22t)||LA22_6==INSTRUCTION_FORMAT23x||(LA22_6 >= INSTRUCTION_FORMAT31i_OR_ID && LA22_6 <= INSTRUCTION_FORMAT31t)||(LA22_6 >= INSTRUCTION_FORMAT35c_CALL_SITE && LA22_6 <= INSTRUCTION_FORMAT35ms_METHOD)||(LA22_6 >= INSTRUCTION_FORMAT45cc_METHOD && LA22_6 <= INSTRUCTION_FORMAT51l)||(LA22_6 >= METHOD_DIRECTIVE && LA22_6 <= NULL_LITERAL)||(LA22_6 >= PARAM_LIST_OR_ID_PRIMITIVE_TYPE && LA22_6 <= PRIMITIVE_TYPE)||LA22_6==REGISTER||(LA22_6 >= SIMPLE_NAME && LA22_6 <= SOURCE_DIRECTIVE)||(LA22_6 >= SUPER_DIRECTIVE && LA22_6 <= VOID_TYPE)) ) {
					alt22=5;
				}
				else if ( (LA22_6==COLON||LA22_6==OPEN_PAREN) ) {
					alt22=13;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 22, 6, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case FLOAT_LITERAL:
				{
				alt22=5;
				}
				break;
			case DOUBLE_LITERAL_OR_ID:
				{
				int LA22_8 = input.LA(2);
				if ( (LA22_8==EOF||(LA22_8 >= ACCESS_SPEC && LA22_8 <= ANNOTATION_VISIBILITY)||LA22_8==BOOL_LITERAL||(LA22_8 >= CLASS_DIRECTIVE && LA22_8 <= CLOSE_PAREN)||LA22_8==COMMA||(LA22_8 >= DOUBLE_LITERAL_OR_ID && LA22_8 <= END_ANNOTATION_DIRECTIVE)||LA22_8==END_FIELD_DIRECTIVE||LA22_8==END_SUBANNOTATION_DIRECTIVE||LA22_8==FIELD_DIRECTIVE||(LA22_8 >= FLOAT_LITERAL_OR_ID && LA22_8 <= IMPLEMENTS_DIRECTIVE)||(LA22_8 >= INSTRUCTION_FORMAT10t && LA22_8 <= INSTRUCTION_FORMAT10x_ODEX)||LA22_8==INSTRUCTION_FORMAT11x||LA22_8==INSTRUCTION_FORMAT12x_OR_ID||(LA22_8 >= INSTRUCTION_FORMAT21c_FIELD && LA22_8 <= INSTRUCTION_FORMAT21c_TYPE)||LA22_8==INSTRUCTION_FORMAT21t||(LA22_8 >= INSTRUCTION_FORMAT22c_FIELD && LA22_8 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA22_8 >= INSTRUCTION_FORMAT22s_OR_ID && LA22_8 <= INSTRUCTION_FORMAT22t)||LA22_8==INSTRUCTION_FORMAT23x||(LA22_8 >= INSTRUCTION_FORMAT31i_OR_ID && LA22_8 <= INSTRUCTION_FORMAT31t)||(LA22_8 >= INSTRUCTION_FORMAT35c_CALL_SITE && LA22_8 <= INSTRUCTION_FORMAT35ms_METHOD)||(LA22_8 >= INSTRUCTION_FORMAT45cc_METHOD && LA22_8 <= INSTRUCTION_FORMAT51l)||(LA22_8 >= METHOD_DIRECTIVE && LA22_8 <= NULL_LITERAL)||(LA22_8 >= PARAM_LIST_OR_ID_PRIMITIVE_TYPE && LA22_8 <= PRIMITIVE_TYPE)||LA22_8==REGISTER||(LA22_8 >= SIMPLE_NAME && LA22_8 <= SOURCE_DIRECTIVE)||(LA22_8 >= SUPER_DIRECTIVE && LA22_8 <= VOID_TYPE)) ) {
					alt22=6;
				}
				else if ( (LA22_8==COLON||LA22_8==OPEN_PAREN) ) {
					alt22=13;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 22, 8, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case DOUBLE_LITERAL:
				{
				alt22=6;
				}
				break;
			case CHAR_LITERAL:
				{
				alt22=7;
				}
				break;
			case STRING_LITERAL:
				{
				alt22=8;
				}
				break;
			case BOOL_LITERAL:
				{
				int LA22_12 = input.LA(2);
				if ( (LA22_12==EOF||(LA22_12 >= ACCESS_SPEC && LA22_12 <= ANNOTATION_VISIBILITY)||LA22_12==BOOL_LITERAL||(LA22_12 >= CLASS_DIRECTIVE && LA22_12 <= CLOSE_PAREN)||LA22_12==COMMA||(LA22_12 >= DOUBLE_LITERAL_OR_ID && LA22_12 <= END_ANNOTATION_DIRECTIVE)||LA22_12==END_FIELD_DIRECTIVE||LA22_12==END_SUBANNOTATION_DIRECTIVE||LA22_12==FIELD_DIRECTIVE||(LA22_12 >= FLOAT_LITERAL_OR_ID && LA22_12 <= IMPLEMENTS_DIRECTIVE)||(LA22_12 >= INSTRUCTION_FORMAT10t && LA22_12 <= INSTRUCTION_FORMAT10x_ODEX)||LA22_12==INSTRUCTION_FORMAT11x||LA22_12==INSTRUCTION_FORMAT12x_OR_ID||(LA22_12 >= INSTRUCTION_FORMAT21c_FIELD && LA22_12 <= INSTRUCTION_FORMAT21c_TYPE)||LA22_12==INSTRUCTION_FORMAT21t||(LA22_12 >= INSTRUCTION_FORMAT22c_FIELD && LA22_12 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA22_12 >= INSTRUCTION_FORMAT22s_OR_ID && LA22_12 <= INSTRUCTION_FORMAT22t)||LA22_12==INSTRUCTION_FORMAT23x||(LA22_12 >= INSTRUCTION_FORMAT31i_OR_ID && LA22_12 <= INSTRUCTION_FORMAT31t)||(LA22_12 >= INSTRUCTION_FORMAT35c_CALL_SITE && LA22_12 <= INSTRUCTION_FORMAT35ms_METHOD)||(LA22_12 >= INSTRUCTION_FORMAT45cc_METHOD && LA22_12 <= INSTRUCTION_FORMAT51l)||(LA22_12 >= METHOD_DIRECTIVE && LA22_12 <= NULL_LITERAL)||(LA22_12 >= PARAM_LIST_OR_ID_PRIMITIVE_TYPE && LA22_12 <= PRIMITIVE_TYPE)||LA22_12==REGISTER||(LA22_12 >= SIMPLE_NAME && LA22_12 <= SOURCE_DIRECTIVE)||(LA22_12 >= SUPER_DIRECTIVE && LA22_12 <= VOID_TYPE)) ) {
					alt22=9;
				}
				else if ( (LA22_12==COLON||LA22_12==OPEN_PAREN) ) {
					alt22=13;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 22, 12, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case NULL_LITERAL:
				{
				int LA22_13 = input.LA(2);
				if ( (LA22_13==EOF||(LA22_13 >= ACCESS_SPEC && LA22_13 <= ANNOTATION_VISIBILITY)||LA22_13==BOOL_LITERAL||(LA22_13 >= CLASS_DIRECTIVE && LA22_13 <= CLOSE_PAREN)||LA22_13==COMMA||(LA22_13 >= DOUBLE_LITERAL_OR_ID && LA22_13 <= END_ANNOTATION_DIRECTIVE)||LA22_13==END_FIELD_DIRECTIVE||LA22_13==END_SUBANNOTATION_DIRECTIVE||LA22_13==FIELD_DIRECTIVE||(LA22_13 >= FLOAT_LITERAL_OR_ID && LA22_13 <= IMPLEMENTS_DIRECTIVE)||(LA22_13 >= INSTRUCTION_FORMAT10t && LA22_13 <= INSTRUCTION_FORMAT10x_ODEX)||LA22_13==INSTRUCTION_FORMAT11x||LA22_13==INSTRUCTION_FORMAT12x_OR_ID||(LA22_13 >= INSTRUCTION_FORMAT21c_FIELD && LA22_13 <= INSTRUCTION_FORMAT21c_TYPE)||LA22_13==INSTRUCTION_FORMAT21t||(LA22_13 >= INSTRUCTION_FORMAT22c_FIELD && LA22_13 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA22_13 >= INSTRUCTION_FORMAT22s_OR_ID && LA22_13 <= INSTRUCTION_FORMAT22t)||LA22_13==INSTRUCTION_FORMAT23x||(LA22_13 >= INSTRUCTION_FORMAT31i_OR_ID && LA22_13 <= INSTRUCTION_FORMAT31t)||(LA22_13 >= INSTRUCTION_FORMAT35c_CALL_SITE && LA22_13 <= INSTRUCTION_FORMAT35ms_METHOD)||(LA22_13 >= INSTRUCTION_FORMAT45cc_METHOD && LA22_13 <= INSTRUCTION_FORMAT51l)||(LA22_13 >= METHOD_DIRECTIVE && LA22_13 <= NULL_LITERAL)||(LA22_13 >= PARAM_LIST_OR_ID_PRIMITIVE_TYPE && LA22_13 <= PRIMITIVE_TYPE)||LA22_13==REGISTER||(LA22_13 >= SIMPLE_NAME && LA22_13 <= SOURCE_DIRECTIVE)||(LA22_13 >= SUPER_DIRECTIVE && LA22_13 <= VOID_TYPE)) ) {
					alt22=10;
				}
				else if ( (LA22_13==COLON||LA22_13==OPEN_PAREN) ) {
					alt22=13;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 22, 13, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case OPEN_BRACE:
				{
				alt22=11;
				}
				break;
			case SUBANNOTATION_DIRECTIVE:
				{
				alt22=12;
				}
				break;
			case ACCESS_SPEC:
			case ANNOTATION_VISIBILITY:
			case ARRAY_TYPE_PREFIX:
			case CLASS_DESCRIPTOR:
			case HIDDENAPI_RESTRICTION:
			case INSTRUCTION_FORMAT10t:
			case INSTRUCTION_FORMAT10x:
			case INSTRUCTION_FORMAT10x_ODEX:
			case INSTRUCTION_FORMAT11x:
			case INSTRUCTION_FORMAT12x_OR_ID:
			case INSTRUCTION_FORMAT21c_FIELD:
			case INSTRUCTION_FORMAT21c_FIELD_ODEX:
			case INSTRUCTION_FORMAT21c_METHOD_HANDLE:
			case INSTRUCTION_FORMAT21c_METHOD_TYPE:
			case INSTRUCTION_FORMAT21c_STRING:
			case INSTRUCTION_FORMAT21c_TYPE:
			case INSTRUCTION_FORMAT21t:
			case INSTRUCTION_FORMAT22c_FIELD:
			case INSTRUCTION_FORMAT22c_FIELD_ODEX:
			case INSTRUCTION_FORMAT22c_TYPE:
			case INSTRUCTION_FORMAT22cs_FIELD:
			case INSTRUCTION_FORMAT22s_OR_ID:
			case INSTRUCTION_FORMAT22t:
			case INSTRUCTION_FORMAT23x:
			case INSTRUCTION_FORMAT31i_OR_ID:
			case INSTRUCTION_FORMAT31t:
			case INSTRUCTION_FORMAT35c_CALL_SITE:
			case INSTRUCTION_FORMAT35c_METHOD:
			case INSTRUCTION_FORMAT35c_METHOD_ODEX:
			case INSTRUCTION_FORMAT35c_TYPE:
			case INSTRUCTION_FORMAT35mi_METHOD:
			case INSTRUCTION_FORMAT35ms_METHOD:
			case INSTRUCTION_FORMAT45cc_METHOD:
			case INSTRUCTION_FORMAT4rcc_METHOD:
			case INSTRUCTION_FORMAT51l:
			case MEMBER_NAME:
			case PARAM_LIST_OR_ID_PRIMITIVE_TYPE:
			case PRIMITIVE_TYPE:
			case REGISTER:
			case SIMPLE_NAME:
			case VERIFICATION_ERROR_TYPE:
			case VOID_TYPE:
				{
				alt22=13;
				}
				break;
			case METHOD_HANDLE_TYPE_FIELD:
				{
				int LA22_17 = input.LA(2);
				if ( (LA22_17==AT) ) {
					alt22=15;
				}
				else if ( (LA22_17==COLON||LA22_17==OPEN_PAREN) ) {
					alt22=13;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 22, 17, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case METHOD_HANDLE_TYPE_METHOD:
				{
				int LA22_18 = input.LA(2);
				if ( (LA22_18==AT) ) {
					alt22=15;
				}
				else if ( (LA22_18==COLON||LA22_18==OPEN_PAREN) ) {
					alt22=13;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 22, 18, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE:
				{
				int LA22_19 = input.LA(2);
				if ( (LA22_19==AT) ) {
					alt22=15;
				}
				else if ( (LA22_19==COLON||LA22_19==OPEN_PAREN) ) {
					alt22=13;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 22, 19, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case ENUM_DIRECTIVE:
				{
				alt22=14;
				}
				break;
			case OPEN_PAREN:
				{
				alt22=16;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 22, 0, input);
				throw nvae;
			}
			switch (alt22) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:653:5: LONG_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					LONG_LITERAL120=(Token)match(input,LONG_LITERAL,FOLLOW_LONG_LITERAL_in_literal2647);
					LONG_LITERAL120_tree = (CommonTree)adaptor.create(LONG_LITERAL120);
					adaptor.addChild(root_0, LONG_LITERAL120_tree);

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:654:5: integer_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_integer_literal_in_literal2653);
					integer_literal121=integer_literal();
					state._fsp--;

					adaptor.addChild(root_0, integer_literal121.getTree());

					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:655:5: SHORT_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					SHORT_LITERAL122=(Token)match(input,SHORT_LITERAL,FOLLOW_SHORT_LITERAL_in_literal2659);
					SHORT_LITERAL122_tree = (CommonTree)adaptor.create(SHORT_LITERAL122);
					adaptor.addChild(root_0, SHORT_LITERAL122_tree);

					}
					break;
				case 4 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:656:5: BYTE_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					BYTE_LITERAL123=(Token)match(input,BYTE_LITERAL,FOLLOW_BYTE_LITERAL_in_literal2665);
					BYTE_LITERAL123_tree = (CommonTree)adaptor.create(BYTE_LITERAL123);
					adaptor.addChild(root_0, BYTE_LITERAL123_tree);

					}
					break;
				case 5 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:657:5: float_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_float_literal_in_literal2671);
					float_literal124=float_literal();
					state._fsp--;

					adaptor.addChild(root_0, float_literal124.getTree());

					}
					break;
				case 6 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:658:5: double_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_double_literal_in_literal2677);
					double_literal125=double_literal();
					state._fsp--;

					adaptor.addChild(root_0, double_literal125.getTree());

					}
					break;
				case 7 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:659:5: CHAR_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					CHAR_LITERAL126=(Token)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_literal2683);
					CHAR_LITERAL126_tree = (CommonTree)adaptor.create(CHAR_LITERAL126);
					adaptor.addChild(root_0, CHAR_LITERAL126_tree);

					}
					break;
				case 8 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:660:5: STRING_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					STRING_LITERAL127=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_literal2689);
					STRING_LITERAL127_tree = (CommonTree)adaptor.create(STRING_LITERAL127);
					adaptor.addChild(root_0, STRING_LITERAL127_tree);

					}
					break;
				case 9 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:661:5: BOOL_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					BOOL_LITERAL128=(Token)match(input,BOOL_LITERAL,FOLLOW_BOOL_LITERAL_in_literal2695);
					BOOL_LITERAL128_tree = (CommonTree)adaptor.create(BOOL_LITERAL128);
					adaptor.addChild(root_0, BOOL_LITERAL128_tree);

					}
					break;
				case 10 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:662:5: NULL_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					NULL_LITERAL129=(Token)match(input,NULL_LITERAL,FOLLOW_NULL_LITERAL_in_literal2701);
					NULL_LITERAL129_tree = (CommonTree)adaptor.create(NULL_LITERAL129);
					adaptor.addChild(root_0, NULL_LITERAL129_tree);

					}
					break;
				case 11 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:663:5: array_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_array_literal_in_literal2707);
					array_literal130=array_literal();
					state._fsp--;

					adaptor.addChild(root_0, array_literal130.getTree());

					}
					break;
				case 12 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:664:5: subannotation
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_subannotation_in_literal2713);
					subannotation131=subannotation();
					state._fsp--;

					adaptor.addChild(root_0, subannotation131.getTree());

					}
					break;
				case 13 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:665:5: type_field_method_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_type_field_method_literal_in_literal2719);
					type_field_method_literal132=type_field_method_literal();
					state._fsp--;

					adaptor.addChild(root_0, type_field_method_literal132.getTree());

					}
					break;
				case 14 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:666:5: enum_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_enum_literal_in_literal2725);
					enum_literal133=enum_literal();
					state._fsp--;

					adaptor.addChild(root_0, enum_literal133.getTree());

					}
					break;
				case 15 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:667:5: method_handle_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_method_handle_literal_in_literal2731);
					method_handle_literal134=method_handle_literal();
					state._fsp--;

					adaptor.addChild(root_0, method_handle_literal134.getTree());

					}
					break;
				case 16 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:668:5: method_prototype
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_method_prototype_in_literal2737);
					method_prototype135=method_prototype();
					state._fsp--;

					adaptor.addChild(root_0, method_prototype135.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "literal"


	public static class parsed_integer_literal_return extends ParserRuleReturnScope {
		public int value;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "parsed_integer_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:670:1: parsed_integer_literal returns [int value] : integer_literal ;
	public final smaliParser.parsed_integer_literal_return parsed_integer_literal() throws RecognitionException {
		smaliParser.parsed_integer_literal_return retval = new smaliParser.parsed_integer_literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope integer_literal136 =null;


		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:671:3: ( integer_literal )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:671:5: integer_literal
			{
			root_0 = (CommonTree)adaptor.nil();


			pushFollow(FOLLOW_integer_literal_in_parsed_integer_literal2750);
			integer_literal136=integer_literal();
			state._fsp--;

			adaptor.addChild(root_0, integer_literal136.getTree());

			 retval.value = LiteralTools.parseInt((integer_literal136!=null?input.toString(integer_literal136.start,integer_literal136.stop):null));
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "parsed_integer_literal"


	public static class integral_literal_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "integral_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:673:1: integral_literal : ( LONG_LITERAL | integer_literal | SHORT_LITERAL | CHAR_LITERAL | BYTE_LITERAL );
	public final smaliParser.integral_literal_return integral_literal() throws RecognitionException {
		smaliParser.integral_literal_return retval = new smaliParser.integral_literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token LONG_LITERAL137=null;
		Token SHORT_LITERAL139=null;
		Token CHAR_LITERAL140=null;
		Token BYTE_LITERAL141=null;
		ParserRuleReturnScope integer_literal138 =null;

		CommonTree LONG_LITERAL137_tree=null;
		CommonTree SHORT_LITERAL139_tree=null;
		CommonTree CHAR_LITERAL140_tree=null;
		CommonTree BYTE_LITERAL141_tree=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:674:3: ( LONG_LITERAL | integer_literal | SHORT_LITERAL | CHAR_LITERAL | BYTE_LITERAL )
			int alt23=5;
			switch ( input.LA(1) ) {
			case LONG_LITERAL:
				{
				alt23=1;
				}
				break;
			case NEGATIVE_INTEGER_LITERAL:
			case POSITIVE_INTEGER_LITERAL:
				{
				alt23=2;
				}
				break;
			case SHORT_LITERAL:
				{
				alt23=3;
				}
				break;
			case CHAR_LITERAL:
				{
				alt23=4;
				}
				break;
			case BYTE_LITERAL:
				{
				alt23=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 23, 0, input);
				throw nvae;
			}
			switch (alt23) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:674:5: LONG_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					LONG_LITERAL137=(Token)match(input,LONG_LITERAL,FOLLOW_LONG_LITERAL_in_integral_literal2762);
					LONG_LITERAL137_tree = (CommonTree)adaptor.create(LONG_LITERAL137);
					adaptor.addChild(root_0, LONG_LITERAL137_tree);

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:675:5: integer_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_integer_literal_in_integral_literal2768);
					integer_literal138=integer_literal();
					state._fsp--;

					adaptor.addChild(root_0, integer_literal138.getTree());

					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:676:5: SHORT_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					SHORT_LITERAL139=(Token)match(input,SHORT_LITERAL,FOLLOW_SHORT_LITERAL_in_integral_literal2774);
					SHORT_LITERAL139_tree = (CommonTree)adaptor.create(SHORT_LITERAL139);
					adaptor.addChild(root_0, SHORT_LITERAL139_tree);

					}
					break;
				case 4 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:677:5: CHAR_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					CHAR_LITERAL140=(Token)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_integral_literal2780);
					CHAR_LITERAL140_tree = (CommonTree)adaptor.create(CHAR_LITERAL140);
					adaptor.addChild(root_0, CHAR_LITERAL140_tree);

					}
					break;
				case 5 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:678:5: BYTE_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					BYTE_LITERAL141=(Token)match(input,BYTE_LITERAL,FOLLOW_BYTE_LITERAL_in_integral_literal2786);
					BYTE_LITERAL141_tree = (CommonTree)adaptor.create(BYTE_LITERAL141);
					adaptor.addChild(root_0, BYTE_LITERAL141_tree);

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "integral_literal"


	public static class fixed_32bit_literal_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "fixed_32bit_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:680:1: fixed_32bit_literal : ( LONG_LITERAL | integer_literal | SHORT_LITERAL | BYTE_LITERAL | float_literal | CHAR_LITERAL | BOOL_LITERAL );
	public final smaliParser.fixed_32bit_literal_return fixed_32bit_literal() throws RecognitionException {
		smaliParser.fixed_32bit_literal_return retval = new smaliParser.fixed_32bit_literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token LONG_LITERAL142=null;
		Token SHORT_LITERAL144=null;
		Token BYTE_LITERAL145=null;
		Token CHAR_LITERAL147=null;
		Token BOOL_LITERAL148=null;
		ParserRuleReturnScope integer_literal143 =null;
		ParserRuleReturnScope float_literal146 =null;

		CommonTree LONG_LITERAL142_tree=null;
		CommonTree SHORT_LITERAL144_tree=null;
		CommonTree BYTE_LITERAL145_tree=null;
		CommonTree CHAR_LITERAL147_tree=null;
		CommonTree BOOL_LITERAL148_tree=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:681:3: ( LONG_LITERAL | integer_literal | SHORT_LITERAL | BYTE_LITERAL | float_literal | CHAR_LITERAL | BOOL_LITERAL )
			int alt24=7;
			switch ( input.LA(1) ) {
			case LONG_LITERAL:
				{
				alt24=1;
				}
				break;
			case NEGATIVE_INTEGER_LITERAL:
			case POSITIVE_INTEGER_LITERAL:
				{
				alt24=2;
				}
				break;
			case SHORT_LITERAL:
				{
				alt24=3;
				}
				break;
			case BYTE_LITERAL:
				{
				alt24=4;
				}
				break;
			case FLOAT_LITERAL:
			case FLOAT_LITERAL_OR_ID:
				{
				alt24=5;
				}
				break;
			case CHAR_LITERAL:
				{
				alt24=6;
				}
				break;
			case BOOL_LITERAL:
				{
				alt24=7;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 24, 0, input);
				throw nvae;
			}
			switch (alt24) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:681:5: LONG_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					LONG_LITERAL142=(Token)match(input,LONG_LITERAL,FOLLOW_LONG_LITERAL_in_fixed_32bit_literal2796);
					LONG_LITERAL142_tree = (CommonTree)adaptor.create(LONG_LITERAL142);
					adaptor.addChild(root_0, LONG_LITERAL142_tree);

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:682:5: integer_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_integer_literal_in_fixed_32bit_literal2802);
					integer_literal143=integer_literal();
					state._fsp--;

					adaptor.addChild(root_0, integer_literal143.getTree());

					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:683:5: SHORT_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					SHORT_LITERAL144=(Token)match(input,SHORT_LITERAL,FOLLOW_SHORT_LITERAL_in_fixed_32bit_literal2808);
					SHORT_LITERAL144_tree = (CommonTree)adaptor.create(SHORT_LITERAL144);
					adaptor.addChild(root_0, SHORT_LITERAL144_tree);

					}
					break;
				case 4 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:684:5: BYTE_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					BYTE_LITERAL145=(Token)match(input,BYTE_LITERAL,FOLLOW_BYTE_LITERAL_in_fixed_32bit_literal2814);
					BYTE_LITERAL145_tree = (CommonTree)adaptor.create(BYTE_LITERAL145);
					adaptor.addChild(root_0, BYTE_LITERAL145_tree);

					}
					break;
				case 5 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:685:5: float_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_float_literal_in_fixed_32bit_literal2820);
					float_literal146=float_literal();
					state._fsp--;

					adaptor.addChild(root_0, float_literal146.getTree());

					}
					break;
				case 6 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:686:5: CHAR_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					CHAR_LITERAL147=(Token)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_fixed_32bit_literal2826);
					CHAR_LITERAL147_tree = (CommonTree)adaptor.create(CHAR_LITERAL147);
					adaptor.addChild(root_0, CHAR_LITERAL147_tree);

					}
					break;
				case 7 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:687:5: BOOL_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					BOOL_LITERAL148=(Token)match(input,BOOL_LITERAL,FOLLOW_BOOL_LITERAL_in_fixed_32bit_literal2832);
					BOOL_LITERAL148_tree = (CommonTree)adaptor.create(BOOL_LITERAL148);
					adaptor.addChild(root_0, BOOL_LITERAL148_tree);

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "fixed_32bit_literal"


	public static class fixed_literal_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "fixed_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:689:1: fixed_literal : ( integer_literal | LONG_LITERAL | SHORT_LITERAL | BYTE_LITERAL | float_literal | double_literal | CHAR_LITERAL | BOOL_LITERAL );
	public final smaliParser.fixed_literal_return fixed_literal() throws RecognitionException {
		smaliParser.fixed_literal_return retval = new smaliParser.fixed_literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token LONG_LITERAL150=null;
		Token SHORT_LITERAL151=null;
		Token BYTE_LITERAL152=null;
		Token CHAR_LITERAL155=null;
		Token BOOL_LITERAL156=null;
		ParserRuleReturnScope integer_literal149 =null;
		ParserRuleReturnScope float_literal153 =null;
		ParserRuleReturnScope double_literal154 =null;

		CommonTree LONG_LITERAL150_tree=null;
		CommonTree SHORT_LITERAL151_tree=null;
		CommonTree BYTE_LITERAL152_tree=null;
		CommonTree CHAR_LITERAL155_tree=null;
		CommonTree BOOL_LITERAL156_tree=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:690:3: ( integer_literal | LONG_LITERAL | SHORT_LITERAL | BYTE_LITERAL | float_literal | double_literal | CHAR_LITERAL | BOOL_LITERAL )
			int alt25=8;
			switch ( input.LA(1) ) {
			case NEGATIVE_INTEGER_LITERAL:
			case POSITIVE_INTEGER_LITERAL:
				{
				alt25=1;
				}
				break;
			case LONG_LITERAL:
				{
				alt25=2;
				}
				break;
			case SHORT_LITERAL:
				{
				alt25=3;
				}
				break;
			case BYTE_LITERAL:
				{
				alt25=4;
				}
				break;
			case FLOAT_LITERAL:
			case FLOAT_LITERAL_OR_ID:
				{
				alt25=5;
				}
				break;
			case DOUBLE_LITERAL:
			case DOUBLE_LITERAL_OR_ID:
				{
				alt25=6;
				}
				break;
			case CHAR_LITERAL:
				{
				alt25=7;
				}
				break;
			case BOOL_LITERAL:
				{
				alt25=8;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 25, 0, input);
				throw nvae;
			}
			switch (alt25) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:690:5: integer_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_integer_literal_in_fixed_literal2842);
					integer_literal149=integer_literal();
					state._fsp--;

					adaptor.addChild(root_0, integer_literal149.getTree());

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:691:5: LONG_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					LONG_LITERAL150=(Token)match(input,LONG_LITERAL,FOLLOW_LONG_LITERAL_in_fixed_literal2848);
					LONG_LITERAL150_tree = (CommonTree)adaptor.create(LONG_LITERAL150);
					adaptor.addChild(root_0, LONG_LITERAL150_tree);

					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:692:5: SHORT_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					SHORT_LITERAL151=(Token)match(input,SHORT_LITERAL,FOLLOW_SHORT_LITERAL_in_fixed_literal2854);
					SHORT_LITERAL151_tree = (CommonTree)adaptor.create(SHORT_LITERAL151);
					adaptor.addChild(root_0, SHORT_LITERAL151_tree);

					}
					break;
				case 4 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:693:5: BYTE_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					BYTE_LITERAL152=(Token)match(input,BYTE_LITERAL,FOLLOW_BYTE_LITERAL_in_fixed_literal2860);
					BYTE_LITERAL152_tree = (CommonTree)adaptor.create(BYTE_LITERAL152);
					adaptor.addChild(root_0, BYTE_LITERAL152_tree);

					}
					break;
				case 5 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:694:5: float_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_float_literal_in_fixed_literal2866);
					float_literal153=float_literal();
					state._fsp--;

					adaptor.addChild(root_0, float_literal153.getTree());

					}
					break;
				case 6 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:695:5: double_literal
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_double_literal_in_fixed_literal2872);
					double_literal154=double_literal();
					state._fsp--;

					adaptor.addChild(root_0, double_literal154.getTree());

					}
					break;
				case 7 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:696:5: CHAR_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					CHAR_LITERAL155=(Token)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_fixed_literal2878);
					CHAR_LITERAL155_tree = (CommonTree)adaptor.create(CHAR_LITERAL155);
					adaptor.addChild(root_0, CHAR_LITERAL155_tree);

					}
					break;
				case 8 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:697:5: BOOL_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					BOOL_LITERAL156=(Token)match(input,BOOL_LITERAL,FOLLOW_BOOL_LITERAL_in_fixed_literal2884);
					BOOL_LITERAL156_tree = (CommonTree)adaptor.create(BOOL_LITERAL156);
					adaptor.addChild(root_0, BOOL_LITERAL156_tree);

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "fixed_literal"


	public static class array_literal_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "array_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:699:1: array_literal : OPEN_BRACE ( literal ( COMMA literal )* |) CLOSE_BRACE -> ^( I_ENCODED_ARRAY[$start, \"I_ENCODED_ARRAY\"] ( literal )* ) ;
	public final smaliParser.array_literal_return array_literal() throws RecognitionException {
		smaliParser.array_literal_return retval = new smaliParser.array_literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token OPEN_BRACE157=null;
		Token COMMA159=null;
		Token CLOSE_BRACE161=null;
		ParserRuleReturnScope literal158 =null;
		ParserRuleReturnScope literal160 =null;

		CommonTree OPEN_BRACE157_tree=null;
		CommonTree COMMA159_tree=null;
		CommonTree CLOSE_BRACE161_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_OPEN_BRACE=new RewriteRuleTokenStream(adaptor,"token OPEN_BRACE");
		RewriteRuleTokenStream stream_CLOSE_BRACE=new RewriteRuleTokenStream(adaptor,"token CLOSE_BRACE");
		RewriteRuleSubtreeStream stream_literal=new RewriteRuleSubtreeStream(adaptor,"rule literal");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:700:3: ( OPEN_BRACE ( literal ( COMMA literal )* |) CLOSE_BRACE -> ^( I_ENCODED_ARRAY[$start, \"I_ENCODED_ARRAY\"] ( literal )* ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:700:5: OPEN_BRACE ( literal ( COMMA literal )* |) CLOSE_BRACE
			{
			OPEN_BRACE157=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_array_literal2894);
			stream_OPEN_BRACE.add(OPEN_BRACE157);

			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:700:16: ( literal ( COMMA literal )* |)
			int alt27=2;
			int LA27_0 = input.LA(1);
			if ( (LA27_0==ACCESS_SPEC||LA27_0==ANNOTATION_VISIBILITY||LA27_0==ARRAY_TYPE_PREFIX||(LA27_0 >= BOOL_LITERAL && LA27_0 <= BYTE_LITERAL)||(LA27_0 >= CHAR_LITERAL && LA27_0 <= CLASS_DESCRIPTOR)||(LA27_0 >= DOUBLE_LITERAL && LA27_0 <= DOUBLE_LITERAL_OR_ID)||LA27_0==ENUM_DIRECTIVE||(LA27_0 >= FLOAT_LITERAL && LA27_0 <= HIDDENAPI_RESTRICTION)||(LA27_0 >= INSTRUCTION_FORMAT10t && LA27_0 <= INSTRUCTION_FORMAT10x_ODEX)||LA27_0==INSTRUCTION_FORMAT11x||LA27_0==INSTRUCTION_FORMAT12x_OR_ID||(LA27_0 >= INSTRUCTION_FORMAT21c_FIELD && LA27_0 <= INSTRUCTION_FORMAT21c_TYPE)||LA27_0==INSTRUCTION_FORMAT21t||(LA27_0 >= INSTRUCTION_FORMAT22c_FIELD && LA27_0 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA27_0 >= INSTRUCTION_FORMAT22s_OR_ID && LA27_0 <= INSTRUCTION_FORMAT22t)||LA27_0==INSTRUCTION_FORMAT23x||(LA27_0 >= INSTRUCTION_FORMAT31i_OR_ID && LA27_0 <= INSTRUCTION_FORMAT31t)||(LA27_0 >= INSTRUCTION_FORMAT35c_CALL_SITE && LA27_0 <= INSTRUCTION_FORMAT35ms_METHOD)||(LA27_0 >= INSTRUCTION_FORMAT45cc_METHOD && LA27_0 <= INSTRUCTION_FORMAT51l)||(LA27_0 >= LONG_LITERAL && LA27_0 <= MEMBER_NAME)||(LA27_0 >= METHOD_HANDLE_TYPE_FIELD && LA27_0 <= OPEN_PAREN)||(LA27_0 >= PARAM_LIST_OR_ID_PRIMITIVE_TYPE && LA27_0 <= PRIMITIVE_TYPE)||LA27_0==REGISTER||(LA27_0 >= SHORT_LITERAL && LA27_0 <= SIMPLE_NAME)||(LA27_0 >= STRING_LITERAL && LA27_0 <= SUBANNOTATION_DIRECTIVE)||(LA27_0 >= VERIFICATION_ERROR_TYPE && LA27_0 <= VOID_TYPE)) ) {
				alt27=1;
			}
			else if ( (LA27_0==CLOSE_BRACE) ) {
				alt27=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 27, 0, input);
				throw nvae;
			}

			switch (alt27) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:700:17: literal ( COMMA literal )*
					{
					pushFollow(FOLLOW_literal_in_array_literal2897);
					literal158=literal();
					state._fsp--;

					stream_literal.add(literal158.getTree());
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:700:25: ( COMMA literal )*
					loop26:
					while (true) {
						int alt26=2;
						int LA26_0 = input.LA(1);
						if ( (LA26_0==COMMA) ) {
							alt26=1;
						}

						switch (alt26) {
						case 1 :
							// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:700:26: COMMA literal
							{
							COMMA159=(Token)match(input,COMMA,FOLLOW_COMMA_in_array_literal2900);
							stream_COMMA.add(COMMA159);

							pushFollow(FOLLOW_literal_in_array_literal2902);
							literal160=literal();
							state._fsp--;

							stream_literal.add(literal160.getTree());
							}
							break;

						default :
							break loop26;
						}
					}

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:700:44:
					{
					}
					break;

			}

			CLOSE_BRACE161=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_array_literal2910);
			stream_CLOSE_BRACE.add(CLOSE_BRACE161);

			// AST REWRITE
			// elements: literal
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 701:5: -> ^( I_ENCODED_ARRAY[$start, \"I_ENCODED_ARRAY\"] ( literal )* )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:701:8: ^( I_ENCODED_ARRAY[$start, \"I_ENCODED_ARRAY\"] ( literal )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ENCODED_ARRAY, (retval.start), "I_ENCODED_ARRAY"), root_1);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:701:53: ( literal )*
				while ( stream_literal.hasNext() ) {
					adaptor.addChild(root_1, stream_literal.nextTree());
				}
				stream_literal.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "array_literal"


	public static class annotation_element_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "annotation_element"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:703:1: annotation_element : simple_name EQUAL literal -> ^( I_ANNOTATION_ELEMENT[$start, \"I_ANNOTATION_ELEMENT\"] simple_name literal ) ;
	public final smaliParser.annotation_element_return annotation_element() throws RecognitionException {
		smaliParser.annotation_element_return retval = new smaliParser.annotation_element_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token EQUAL163=null;
		ParserRuleReturnScope simple_name162 =null;
		ParserRuleReturnScope literal164 =null;

		CommonTree EQUAL163_tree=null;
		RewriteRuleTokenStream stream_EQUAL=new RewriteRuleTokenStream(adaptor,"token EQUAL");
		RewriteRuleSubtreeStream stream_simple_name=new RewriteRuleSubtreeStream(adaptor,"rule simple_name");
		RewriteRuleSubtreeStream stream_literal=new RewriteRuleSubtreeStream(adaptor,"rule literal");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:704:3: ( simple_name EQUAL literal -> ^( I_ANNOTATION_ELEMENT[$start, \"I_ANNOTATION_ELEMENT\"] simple_name literal ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:704:5: simple_name EQUAL literal
			{
			pushFollow(FOLLOW_simple_name_in_annotation_element2934);
			simple_name162=simple_name();
			state._fsp--;

			stream_simple_name.add(simple_name162.getTree());
			EQUAL163=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_annotation_element2936);
			stream_EQUAL.add(EQUAL163);

			pushFollow(FOLLOW_literal_in_annotation_element2938);
			literal164=literal();
			state._fsp--;

			stream_literal.add(literal164.getTree());
			// AST REWRITE
			// elements: literal, simple_name
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 705:5: -> ^( I_ANNOTATION_ELEMENT[$start, \"I_ANNOTATION_ELEMENT\"] simple_name literal )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:705:8: ^( I_ANNOTATION_ELEMENT[$start, \"I_ANNOTATION_ELEMENT\"] simple_name literal )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ANNOTATION_ELEMENT, (retval.start), "I_ANNOTATION_ELEMENT"), root_1);
				adaptor.addChild(root_1, stream_simple_name.nextTree());
				adaptor.addChild(root_1, stream_literal.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "annotation_element"


	public static class annotation_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "annotation"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:707:1: annotation : ANNOTATION_DIRECTIVE ANNOTATION_VISIBILITY CLASS_DESCRIPTOR ( annotation_element )* END_ANNOTATION_DIRECTIVE -> ^( I_ANNOTATION[$start, \"I_ANNOTATION\"] ANNOTATION_VISIBILITY ^( I_SUBANNOTATION[$start, \"I_SUBANNOTATION\"] CLASS_DESCRIPTOR ( annotation_element )* ) ) ;
	public final smaliParser.annotation_return annotation() throws RecognitionException {
		smaliParser.annotation_return retval = new smaliParser.annotation_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ANNOTATION_DIRECTIVE165=null;
		Token ANNOTATION_VISIBILITY166=null;
		Token CLASS_DESCRIPTOR167=null;
		Token END_ANNOTATION_DIRECTIVE169=null;
		ParserRuleReturnScope annotation_element168 =null;

		CommonTree ANNOTATION_DIRECTIVE165_tree=null;
		CommonTree ANNOTATION_VISIBILITY166_tree=null;
		CommonTree CLASS_DESCRIPTOR167_tree=null;
		CommonTree END_ANNOTATION_DIRECTIVE169_tree=null;
		RewriteRuleTokenStream stream_ANNOTATION_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token ANNOTATION_DIRECTIVE");
		RewriteRuleTokenStream stream_ANNOTATION_VISIBILITY=new RewriteRuleTokenStream(adaptor,"token ANNOTATION_VISIBILITY");
		RewriteRuleTokenStream stream_CLASS_DESCRIPTOR=new RewriteRuleTokenStream(adaptor,"token CLASS_DESCRIPTOR");
		RewriteRuleTokenStream stream_END_ANNOTATION_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token END_ANNOTATION_DIRECTIVE");
		RewriteRuleSubtreeStream stream_annotation_element=new RewriteRuleSubtreeStream(adaptor,"rule annotation_element");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:708:3: ( ANNOTATION_DIRECTIVE ANNOTATION_VISIBILITY CLASS_DESCRIPTOR ( annotation_element )* END_ANNOTATION_DIRECTIVE -> ^( I_ANNOTATION[$start, \"I_ANNOTATION\"] ANNOTATION_VISIBILITY ^( I_SUBANNOTATION[$start, \"I_SUBANNOTATION\"] CLASS_DESCRIPTOR ( annotation_element )* ) ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:708:5: ANNOTATION_DIRECTIVE ANNOTATION_VISIBILITY CLASS_DESCRIPTOR ( annotation_element )* END_ANNOTATION_DIRECTIVE
			{
			ANNOTATION_DIRECTIVE165=(Token)match(input,ANNOTATION_DIRECTIVE,FOLLOW_ANNOTATION_DIRECTIVE_in_annotation2963);
			stream_ANNOTATION_DIRECTIVE.add(ANNOTATION_DIRECTIVE165);

			ANNOTATION_VISIBILITY166=(Token)match(input,ANNOTATION_VISIBILITY,FOLLOW_ANNOTATION_VISIBILITY_in_annotation2965);
			stream_ANNOTATION_VISIBILITY.add(ANNOTATION_VISIBILITY166);

			CLASS_DESCRIPTOR167=(Token)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_annotation2967);
			stream_CLASS_DESCRIPTOR.add(CLASS_DESCRIPTOR167);

			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:709:5: ( annotation_element )*
			loop28:
			while (true) {
				int alt28=2;
				int LA28_0 = input.LA(1);
				if ( (LA28_0==ACCESS_SPEC||LA28_0==ANNOTATION_VISIBILITY||LA28_0==BOOL_LITERAL||LA28_0==DOUBLE_LITERAL_OR_ID||(LA28_0 >= FLOAT_LITERAL_OR_ID && LA28_0 <= HIDDENAPI_RESTRICTION)||(LA28_0 >= INSTRUCTION_FORMAT10t && LA28_0 <= INSTRUCTION_FORMAT10x_ODEX)||LA28_0==INSTRUCTION_FORMAT11x||LA28_0==INSTRUCTION_FORMAT12x_OR_ID||(LA28_0 >= INSTRUCTION_FORMAT21c_FIELD && LA28_0 <= INSTRUCTION_FORMAT21c_TYPE)||LA28_0==INSTRUCTION_FORMAT21t||(LA28_0 >= INSTRUCTION_FORMAT22c_FIELD && LA28_0 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA28_0 >= INSTRUCTION_FORMAT22s_OR_ID && LA28_0 <= INSTRUCTION_FORMAT22t)||LA28_0==INSTRUCTION_FORMAT23x||(LA28_0 >= INSTRUCTION_FORMAT31i_OR_ID && LA28_0 <= INSTRUCTION_FORMAT31t)||(LA28_0 >= INSTRUCTION_FORMAT35c_CALL_SITE && LA28_0 <= INSTRUCTION_FORMAT35ms_METHOD)||(LA28_0 >= INSTRUCTION_FORMAT45cc_METHOD && LA28_0 <= INSTRUCTION_FORMAT51l)||(LA28_0 >= METHOD_HANDLE_TYPE_FIELD && LA28_0 <= NULL_LITERAL)||(LA28_0 >= PARAM_LIST_OR_ID_PRIMITIVE_TYPE && LA28_0 <= PRIMITIVE_TYPE)||LA28_0==REGISTER||LA28_0==SIMPLE_NAME||(LA28_0 >= VERIFICATION_ERROR_TYPE && LA28_0 <= VOID_TYPE)) ) {
					alt28=1;
				}

				switch (alt28) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:709:5: annotation_element
					{
					pushFollow(FOLLOW_annotation_element_in_annotation2973);
					annotation_element168=annotation_element();
					state._fsp--;

					stream_annotation_element.add(annotation_element168.getTree());
					}
					break;

				default :
					break loop28;
				}
			}

			END_ANNOTATION_DIRECTIVE169=(Token)match(input,END_ANNOTATION_DIRECTIVE,FOLLOW_END_ANNOTATION_DIRECTIVE_in_annotation2976);
			stream_END_ANNOTATION_DIRECTIVE.add(END_ANNOTATION_DIRECTIVE169);

			// AST REWRITE
			// elements: ANNOTATION_VISIBILITY, annotation_element, CLASS_DESCRIPTOR
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 710:5: -> ^( I_ANNOTATION[$start, \"I_ANNOTATION\"] ANNOTATION_VISIBILITY ^( I_SUBANNOTATION[$start, \"I_SUBANNOTATION\"] CLASS_DESCRIPTOR ( annotation_element )* ) )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:710:8: ^( I_ANNOTATION[$start, \"I_ANNOTATION\"] ANNOTATION_VISIBILITY ^( I_SUBANNOTATION[$start, \"I_SUBANNOTATION\"] CLASS_DESCRIPTOR ( annotation_element )* ) )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ANNOTATION, (retval.start), "I_ANNOTATION"), root_1);
				adaptor.addChild(root_1, stream_ANNOTATION_VISIBILITY.nextNode());
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:710:69: ^( I_SUBANNOTATION[$start, \"I_SUBANNOTATION\"] CLASS_DESCRIPTOR ( annotation_element )* )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_SUBANNOTATION, (retval.start), "I_SUBANNOTATION"), root_2);
				adaptor.addChild(root_2, stream_CLASS_DESCRIPTOR.nextNode());
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:710:131: ( annotation_element )*
				while ( stream_annotation_element.hasNext() ) {
					adaptor.addChild(root_2, stream_annotation_element.nextTree());
				}
				stream_annotation_element.reset();

				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "annotation"


	public static class subannotation_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "subannotation"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:712:1: subannotation : SUBANNOTATION_DIRECTIVE CLASS_DESCRIPTOR ( annotation_element )* END_SUBANNOTATION_DIRECTIVE -> ^( I_SUBANNOTATION[$start, \"I_SUBANNOTATION\"] CLASS_DESCRIPTOR ( annotation_element )* ) ;
	public final smaliParser.subannotation_return subannotation() throws RecognitionException {
		smaliParser.subannotation_return retval = new smaliParser.subannotation_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token SUBANNOTATION_DIRECTIVE170=null;
		Token CLASS_DESCRIPTOR171=null;
		Token END_SUBANNOTATION_DIRECTIVE173=null;
		ParserRuleReturnScope annotation_element172 =null;

		CommonTree SUBANNOTATION_DIRECTIVE170_tree=null;
		CommonTree CLASS_DESCRIPTOR171_tree=null;
		CommonTree END_SUBANNOTATION_DIRECTIVE173_tree=null;
		RewriteRuleTokenStream stream_SUBANNOTATION_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token SUBANNOTATION_DIRECTIVE");
		RewriteRuleTokenStream stream_CLASS_DESCRIPTOR=new RewriteRuleTokenStream(adaptor,"token CLASS_DESCRIPTOR");
		RewriteRuleTokenStream stream_END_SUBANNOTATION_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token END_SUBANNOTATION_DIRECTIVE");
		RewriteRuleSubtreeStream stream_annotation_element=new RewriteRuleSubtreeStream(adaptor,"rule annotation_element");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:713:3: ( SUBANNOTATION_DIRECTIVE CLASS_DESCRIPTOR ( annotation_element )* END_SUBANNOTATION_DIRECTIVE -> ^( I_SUBANNOTATION[$start, \"I_SUBANNOTATION\"] CLASS_DESCRIPTOR ( annotation_element )* ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:713:5: SUBANNOTATION_DIRECTIVE CLASS_DESCRIPTOR ( annotation_element )* END_SUBANNOTATION_DIRECTIVE
			{
			SUBANNOTATION_DIRECTIVE170=(Token)match(input,SUBANNOTATION_DIRECTIVE,FOLLOW_SUBANNOTATION_DIRECTIVE_in_subannotation3009);
			stream_SUBANNOTATION_DIRECTIVE.add(SUBANNOTATION_DIRECTIVE170);

			CLASS_DESCRIPTOR171=(Token)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_subannotation3011);
			stream_CLASS_DESCRIPTOR.add(CLASS_DESCRIPTOR171);

			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:713:46: ( annotation_element )*
			loop29:
			while (true) {
				int alt29=2;
				int LA29_0 = input.LA(1);
				if ( (LA29_0==ACCESS_SPEC||LA29_0==ANNOTATION_VISIBILITY||LA29_0==BOOL_LITERAL||LA29_0==DOUBLE_LITERAL_OR_ID||(LA29_0 >= FLOAT_LITERAL_OR_ID && LA29_0 <= HIDDENAPI_RESTRICTION)||(LA29_0 >= INSTRUCTION_FORMAT10t && LA29_0 <= INSTRUCTION_FORMAT10x_ODEX)||LA29_0==INSTRUCTION_FORMAT11x||LA29_0==INSTRUCTION_FORMAT12x_OR_ID||(LA29_0 >= INSTRUCTION_FORMAT21c_FIELD && LA29_0 <= INSTRUCTION_FORMAT21c_TYPE)||LA29_0==INSTRUCTION_FORMAT21t||(LA29_0 >= INSTRUCTION_FORMAT22c_FIELD && LA29_0 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA29_0 >= INSTRUCTION_FORMAT22s_OR_ID && LA29_0 <= INSTRUCTION_FORMAT22t)||LA29_0==INSTRUCTION_FORMAT23x||(LA29_0 >= INSTRUCTION_FORMAT31i_OR_ID && LA29_0 <= INSTRUCTION_FORMAT31t)||(LA29_0 >= INSTRUCTION_FORMAT35c_CALL_SITE && LA29_0 <= INSTRUCTION_FORMAT35ms_METHOD)||(LA29_0 >= INSTRUCTION_FORMAT45cc_METHOD && LA29_0 <= INSTRUCTION_FORMAT51l)||(LA29_0 >= METHOD_HANDLE_TYPE_FIELD && LA29_0 <= NULL_LITERAL)||(LA29_0 >= PARAM_LIST_OR_ID_PRIMITIVE_TYPE && LA29_0 <= PRIMITIVE_TYPE)||LA29_0==REGISTER||LA29_0==SIMPLE_NAME||(LA29_0 >= VERIFICATION_ERROR_TYPE && LA29_0 <= VOID_TYPE)) ) {
					alt29=1;
				}

				switch (alt29) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:713:46: annotation_element
					{
					pushFollow(FOLLOW_annotation_element_in_subannotation3013);
					annotation_element172=annotation_element();
					state._fsp--;

					stream_annotation_element.add(annotation_element172.getTree());
					}
					break;

				default :
					break loop29;
				}
			}

			END_SUBANNOTATION_DIRECTIVE173=(Token)match(input,END_SUBANNOTATION_DIRECTIVE,FOLLOW_END_SUBANNOTATION_DIRECTIVE_in_subannotation3016);
			stream_END_SUBANNOTATION_DIRECTIVE.add(END_SUBANNOTATION_DIRECTIVE173);

			// AST REWRITE
			// elements: CLASS_DESCRIPTOR, annotation_element
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 714:5: -> ^( I_SUBANNOTATION[$start, \"I_SUBANNOTATION\"] CLASS_DESCRIPTOR ( annotation_element )* )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:714:8: ^( I_SUBANNOTATION[$start, \"I_SUBANNOTATION\"] CLASS_DESCRIPTOR ( annotation_element )* )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_SUBANNOTATION, (retval.start), "I_SUBANNOTATION"), root_1);
				adaptor.addChild(root_1, stream_CLASS_DESCRIPTOR.nextNode());
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:714:70: ( annotation_element )*
				while ( stream_annotation_element.hasNext() ) {
					adaptor.addChild(root_1, stream_annotation_element.nextTree());
				}
				stream_annotation_element.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "subannotation"


	public static class enum_literal_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "enum_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:717:1: enum_literal : ENUM_DIRECTIVE field_reference -> ^( I_ENCODED_ENUM field_reference ) ;
	public final smaliParser.enum_literal_return enum_literal() throws RecognitionException {
		smaliParser.enum_literal_return retval = new smaliParser.enum_literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ENUM_DIRECTIVE174=null;
		ParserRuleReturnScope field_reference175 =null;

		CommonTree ENUM_DIRECTIVE174_tree=null;
		RewriteRuleTokenStream stream_ENUM_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token ENUM_DIRECTIVE");
		RewriteRuleSubtreeStream stream_field_reference=new RewriteRuleSubtreeStream(adaptor,"rule field_reference");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:718:3: ( ENUM_DIRECTIVE field_reference -> ^( I_ENCODED_ENUM field_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:718:5: ENUM_DIRECTIVE field_reference
			{
			ENUM_DIRECTIVE174=(Token)match(input,ENUM_DIRECTIVE,FOLLOW_ENUM_DIRECTIVE_in_enum_literal3043);
			stream_ENUM_DIRECTIVE.add(ENUM_DIRECTIVE174);

			pushFollow(FOLLOW_field_reference_in_enum_literal3045);
			field_reference175=field_reference();
			state._fsp--;

			stream_field_reference.add(field_reference175.getTree());
			// AST REWRITE
			// elements: field_reference
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 719:3: -> ^( I_ENCODED_ENUM field_reference )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:719:6: ^( I_ENCODED_ENUM field_reference )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ENCODED_ENUM, "I_ENCODED_ENUM"), root_1);
				adaptor.addChild(root_1, stream_field_reference.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "enum_literal"


	public static class type_field_method_literal_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "type_field_method_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:721:1: type_field_method_literal : ( reference_type_descriptor | ( ( reference_type_descriptor ARROW )? ( member_name COLON nonvoid_type_descriptor -> ^( I_ENCODED_FIELD ( reference_type_descriptor )? member_name nonvoid_type_descriptor ) | member_name method_prototype -> ^( I_ENCODED_METHOD ( reference_type_descriptor )? member_name method_prototype ) ) ) | PRIMITIVE_TYPE | VOID_TYPE );
	public final smaliParser.type_field_method_literal_return type_field_method_literal() throws RecognitionException {
		smaliParser.type_field_method_literal_return retval = new smaliParser.type_field_method_literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ARROW178=null;
		Token COLON180=null;
		Token PRIMITIVE_TYPE184=null;
		Token VOID_TYPE185=null;
		ParserRuleReturnScope reference_type_descriptor176 =null;
		ParserRuleReturnScope reference_type_descriptor177 =null;
		ParserRuleReturnScope member_name179 =null;
		ParserRuleReturnScope nonvoid_type_descriptor181 =null;
		ParserRuleReturnScope member_name182 =null;
		ParserRuleReturnScope method_prototype183 =null;

		CommonTree ARROW178_tree=null;
		CommonTree COLON180_tree=null;
		CommonTree PRIMITIVE_TYPE184_tree=null;
		CommonTree VOID_TYPE185_tree=null;
		RewriteRuleTokenStream stream_ARROW=new RewriteRuleTokenStream(adaptor,"token ARROW");
		RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
		RewriteRuleSubtreeStream stream_method_prototype=new RewriteRuleSubtreeStream(adaptor,"rule method_prototype");
		RewriteRuleSubtreeStream stream_nonvoid_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule nonvoid_type_descriptor");
		RewriteRuleSubtreeStream stream_member_name=new RewriteRuleSubtreeStream(adaptor,"rule member_name");
		RewriteRuleSubtreeStream stream_reference_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule reference_type_descriptor");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:722:3: ( reference_type_descriptor | ( ( reference_type_descriptor ARROW )? ( member_name COLON nonvoid_type_descriptor -> ^( I_ENCODED_FIELD ( reference_type_descriptor )? member_name nonvoid_type_descriptor ) | member_name method_prototype -> ^( I_ENCODED_METHOD ( reference_type_descriptor )? member_name method_prototype ) ) ) | PRIMITIVE_TYPE | VOID_TYPE )
			int alt32=4;
			switch ( input.LA(1) ) {
			case CLASS_DESCRIPTOR:
				{
				int LA32_1 = input.LA(2);
				if ( (LA32_1==EOF||(LA32_1 >= ACCESS_SPEC && LA32_1 <= ANNOTATION_VISIBILITY)||LA32_1==BOOL_LITERAL||(LA32_1 >= CLASS_DIRECTIVE && LA32_1 <= CLOSE_PAREN)||LA32_1==COMMA||(LA32_1 >= DOUBLE_LITERAL_OR_ID && LA32_1 <= END_ANNOTATION_DIRECTIVE)||LA32_1==END_FIELD_DIRECTIVE||LA32_1==END_SUBANNOTATION_DIRECTIVE||LA32_1==FIELD_DIRECTIVE||(LA32_1 >= FLOAT_LITERAL_OR_ID && LA32_1 <= IMPLEMENTS_DIRECTIVE)||(LA32_1 >= INSTRUCTION_FORMAT10t && LA32_1 <= INSTRUCTION_FORMAT10x_ODEX)||LA32_1==INSTRUCTION_FORMAT11x||LA32_1==INSTRUCTION_FORMAT12x_OR_ID||(LA32_1 >= INSTRUCTION_FORMAT21c_FIELD && LA32_1 <= INSTRUCTION_FORMAT21c_TYPE)||LA32_1==INSTRUCTION_FORMAT21t||(LA32_1 >= INSTRUCTION_FORMAT22c_FIELD && LA32_1 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA32_1 >= INSTRUCTION_FORMAT22s_OR_ID && LA32_1 <= INSTRUCTION_FORMAT22t)||LA32_1==INSTRUCTION_FORMAT23x||(LA32_1 >= INSTRUCTION_FORMAT31i_OR_ID && LA32_1 <= INSTRUCTION_FORMAT31t)||(LA32_1 >= INSTRUCTION_FORMAT35c_CALL_SITE && LA32_1 <= INSTRUCTION_FORMAT35ms_METHOD)||(LA32_1 >= INSTRUCTION_FORMAT45cc_METHOD && LA32_1 <= INSTRUCTION_FORMAT51l)||(LA32_1 >= METHOD_DIRECTIVE && LA32_1 <= NULL_LITERAL)||(LA32_1 >= PARAM_LIST_OR_ID_PRIMITIVE_TYPE && LA32_1 <= PRIMITIVE_TYPE)||LA32_1==REGISTER||(LA32_1 >= SIMPLE_NAME && LA32_1 <= SOURCE_DIRECTIVE)||(LA32_1 >= SUPER_DIRECTIVE && LA32_1 <= VOID_TYPE)) ) {
					alt32=1;
				}
				else if ( (LA32_1==ARROW) ) {
					alt32=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 32, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case ARRAY_TYPE_PREFIX:
				{
				int LA32_2 = input.LA(2);
				if ( (LA32_2==CLASS_DESCRIPTOR||LA32_2==PRIMITIVE_TYPE) ) {
					int LA32_7 = input.LA(3);
					if ( (LA32_7==EOF||(LA32_7 >= ACCESS_SPEC && LA32_7 <= ANNOTATION_VISIBILITY)||LA32_7==BOOL_LITERAL||(LA32_7 >= CLASS_DIRECTIVE && LA32_7 <= CLOSE_PAREN)||LA32_7==COMMA||(LA32_7 >= DOUBLE_LITERAL_OR_ID && LA32_7 <= END_ANNOTATION_DIRECTIVE)||LA32_7==END_FIELD_DIRECTIVE||LA32_7==END_SUBANNOTATION_DIRECTIVE||LA32_7==FIELD_DIRECTIVE||(LA32_7 >= FLOAT_LITERAL_OR_ID && LA32_7 <= IMPLEMENTS_DIRECTIVE)||(LA32_7 >= INSTRUCTION_FORMAT10t && LA32_7 <= INSTRUCTION_FORMAT10x_ODEX)||LA32_7==INSTRUCTION_FORMAT11x||LA32_7==INSTRUCTION_FORMAT12x_OR_ID||(LA32_7 >= INSTRUCTION_FORMAT21c_FIELD && LA32_7 <= INSTRUCTION_FORMAT21c_TYPE)||LA32_7==INSTRUCTION_FORMAT21t||(LA32_7 >= INSTRUCTION_FORMAT22c_FIELD && LA32_7 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA32_7 >= INSTRUCTION_FORMAT22s_OR_ID && LA32_7 <= INSTRUCTION_FORMAT22t)||LA32_7==INSTRUCTION_FORMAT23x||(LA32_7 >= INSTRUCTION_FORMAT31i_OR_ID && LA32_7 <= INSTRUCTION_FORMAT31t)||(LA32_7 >= INSTRUCTION_FORMAT35c_CALL_SITE && LA32_7 <= INSTRUCTION_FORMAT35ms_METHOD)||(LA32_7 >= INSTRUCTION_FORMAT45cc_METHOD && LA32_7 <= INSTRUCTION_FORMAT51l)||(LA32_7 >= METHOD_DIRECTIVE && LA32_7 <= NULL_LITERAL)||(LA32_7 >= PARAM_LIST_OR_ID_PRIMITIVE_TYPE && LA32_7 <= PRIMITIVE_TYPE)||LA32_7==REGISTER||(LA32_7 >= SIMPLE_NAME && LA32_7 <= SOURCE_DIRECTIVE)||(LA32_7 >= SUPER_DIRECTIVE && LA32_7 <= VOID_TYPE)) ) {
						alt32=1;
					}
					else if ( (LA32_7==ARROW) ) {
						alt32=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 32, 7, input);
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
							new NoViableAltException("", 32, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case ACCESS_SPEC:
			case ANNOTATION_VISIBILITY:
			case BOOL_LITERAL:
			case DOUBLE_LITERAL_OR_ID:
			case FLOAT_LITERAL_OR_ID:
			case HIDDENAPI_RESTRICTION:
			case INSTRUCTION_FORMAT10t:
			case INSTRUCTION_FORMAT10x:
			case INSTRUCTION_FORMAT10x_ODEX:
			case INSTRUCTION_FORMAT11x:
			case INSTRUCTION_FORMAT12x_OR_ID:
			case INSTRUCTION_FORMAT21c_FIELD:
			case INSTRUCTION_FORMAT21c_FIELD_ODEX:
			case INSTRUCTION_FORMAT21c_METHOD_HANDLE:
			case INSTRUCTION_FORMAT21c_METHOD_TYPE:
			case INSTRUCTION_FORMAT21c_STRING:
			case INSTRUCTION_FORMAT21c_TYPE:
			case INSTRUCTION_FORMAT21t:
			case INSTRUCTION_FORMAT22c_FIELD:
			case INSTRUCTION_FORMAT22c_FIELD_ODEX:
			case INSTRUCTION_FORMAT22c_TYPE:
			case INSTRUCTION_FORMAT22cs_FIELD:
			case INSTRUCTION_FORMAT22s_OR_ID:
			case INSTRUCTION_FORMAT22t:
			case INSTRUCTION_FORMAT23x:
			case INSTRUCTION_FORMAT31i_OR_ID:
			case INSTRUCTION_FORMAT31t:
			case INSTRUCTION_FORMAT35c_CALL_SITE:
			case INSTRUCTION_FORMAT35c_METHOD:
			case INSTRUCTION_FORMAT35c_METHOD_ODEX:
			case INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE:
			case INSTRUCTION_FORMAT35c_TYPE:
			case INSTRUCTION_FORMAT35mi_METHOD:
			case INSTRUCTION_FORMAT35ms_METHOD:
			case INSTRUCTION_FORMAT45cc_METHOD:
			case INSTRUCTION_FORMAT4rcc_METHOD:
			case INSTRUCTION_FORMAT51l:
			case MEMBER_NAME:
			case METHOD_HANDLE_TYPE_FIELD:
			case METHOD_HANDLE_TYPE_METHOD:
			case NEGATIVE_INTEGER_LITERAL:
			case NULL_LITERAL:
			case PARAM_LIST_OR_ID_PRIMITIVE_TYPE:
			case POSITIVE_INTEGER_LITERAL:
			case REGISTER:
			case SIMPLE_NAME:
			case VERIFICATION_ERROR_TYPE:
				{
				alt32=2;
				}
				break;
			case PRIMITIVE_TYPE:
				{
				int LA32_4 = input.LA(2);
				if ( (LA32_4==COLON||LA32_4==OPEN_PAREN) ) {
					alt32=2;
				}
				else if ( (LA32_4==EOF||(LA32_4 >= ACCESS_SPEC && LA32_4 <= ANNOTATION_VISIBILITY)||LA32_4==BOOL_LITERAL||(LA32_4 >= CLASS_DIRECTIVE && LA32_4 <= CLOSE_PAREN)||LA32_4==COMMA||(LA32_4 >= DOUBLE_LITERAL_OR_ID && LA32_4 <= END_ANNOTATION_DIRECTIVE)||LA32_4==END_FIELD_DIRECTIVE||LA32_4==END_SUBANNOTATION_DIRECTIVE||LA32_4==FIELD_DIRECTIVE||(LA32_4 >= FLOAT_LITERAL_OR_ID && LA32_4 <= IMPLEMENTS_DIRECTIVE)||(LA32_4 >= INSTRUCTION_FORMAT10t && LA32_4 <= INSTRUCTION_FORMAT10x_ODEX)||LA32_4==INSTRUCTION_FORMAT11x||LA32_4==INSTRUCTION_FORMAT12x_OR_ID||(LA32_4 >= INSTRUCTION_FORMAT21c_FIELD && LA32_4 <= INSTRUCTION_FORMAT21c_TYPE)||LA32_4==INSTRUCTION_FORMAT21t||(LA32_4 >= INSTRUCTION_FORMAT22c_FIELD && LA32_4 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA32_4 >= INSTRUCTION_FORMAT22s_OR_ID && LA32_4 <= INSTRUCTION_FORMAT22t)||LA32_4==INSTRUCTION_FORMAT23x||(LA32_4 >= INSTRUCTION_FORMAT31i_OR_ID && LA32_4 <= INSTRUCTION_FORMAT31t)||(LA32_4 >= INSTRUCTION_FORMAT35c_CALL_SITE && LA32_4 <= INSTRUCTION_FORMAT35ms_METHOD)||(LA32_4 >= INSTRUCTION_FORMAT45cc_METHOD && LA32_4 <= INSTRUCTION_FORMAT51l)||(LA32_4 >= METHOD_DIRECTIVE && LA32_4 <= NULL_LITERAL)||(LA32_4 >= PARAM_LIST_OR_ID_PRIMITIVE_TYPE && LA32_4 <= PRIMITIVE_TYPE)||LA32_4==REGISTER||(LA32_4 >= SIMPLE_NAME && LA32_4 <= SOURCE_DIRECTIVE)||(LA32_4 >= SUPER_DIRECTIVE && LA32_4 <= VOID_TYPE)) ) {
					alt32=3;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 32, 4, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case VOID_TYPE:
				{
				int LA32_5 = input.LA(2);
				if ( (LA32_5==COLON||LA32_5==OPEN_PAREN) ) {
					alt32=2;
				}
				else if ( (LA32_5==EOF||(LA32_5 >= ACCESS_SPEC && LA32_5 <= ANNOTATION_VISIBILITY)||LA32_5==BOOL_LITERAL||(LA32_5 >= CLASS_DIRECTIVE && LA32_5 <= CLOSE_PAREN)||LA32_5==COMMA||(LA32_5 >= DOUBLE_LITERAL_OR_ID && LA32_5 <= END_ANNOTATION_DIRECTIVE)||LA32_5==END_FIELD_DIRECTIVE||LA32_5==END_SUBANNOTATION_DIRECTIVE||LA32_5==FIELD_DIRECTIVE||(LA32_5 >= FLOAT_LITERAL_OR_ID && LA32_5 <= IMPLEMENTS_DIRECTIVE)||(LA32_5 >= INSTRUCTION_FORMAT10t && LA32_5 <= INSTRUCTION_FORMAT10x_ODEX)||LA32_5==INSTRUCTION_FORMAT11x||LA32_5==INSTRUCTION_FORMAT12x_OR_ID||(LA32_5 >= INSTRUCTION_FORMAT21c_FIELD && LA32_5 <= INSTRUCTION_FORMAT21c_TYPE)||LA32_5==INSTRUCTION_FORMAT21t||(LA32_5 >= INSTRUCTION_FORMAT22c_FIELD && LA32_5 <= INSTRUCTION_FORMAT22cs_FIELD)||(LA32_5 >= INSTRUCTION_FORMAT22s_OR_ID && LA32_5 <= INSTRUCTION_FORMAT22t)||LA32_5==INSTRUCTION_FORMAT23x||(LA32_5 >= INSTRUCTION_FORMAT31i_OR_ID && LA32_5 <= INSTRUCTION_FORMAT31t)||(LA32_5 >= INSTRUCTION_FORMAT35c_CALL_SITE && LA32_5 <= INSTRUCTION_FORMAT35ms_METHOD)||(LA32_5 >= INSTRUCTION_FORMAT45cc_METHOD && LA32_5 <= INSTRUCTION_FORMAT51l)||(LA32_5 >= METHOD_DIRECTIVE && LA32_5 <= NULL_LITERAL)||(LA32_5 >= PARAM_LIST_OR_ID_PRIMITIVE_TYPE && LA32_5 <= PRIMITIVE_TYPE)||LA32_5==REGISTER||(LA32_5 >= SIMPLE_NAME && LA32_5 <= SOURCE_DIRECTIVE)||(LA32_5 >= SUPER_DIRECTIVE && LA32_5 <= VOID_TYPE)) ) {
					alt32=4;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 32, 5, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 32, 0, input);
				throw nvae;
			}
			switch (alt32) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:722:5: reference_type_descriptor
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_reference_type_descriptor_in_type_field_method_literal3065);
					reference_type_descriptor176=reference_type_descriptor();
					state._fsp--;

					adaptor.addChild(root_0, reference_type_descriptor176.getTree());

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:723:5: ( ( reference_type_descriptor ARROW )? ( member_name COLON nonvoid_type_descriptor -> ^( I_ENCODED_FIELD ( reference_type_descriptor )? member_name nonvoid_type_descriptor ) | member_name method_prototype -> ^( I_ENCODED_METHOD ( reference_type_descriptor )? member_name method_prototype ) ) )
					{
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:723:5: ( ( reference_type_descriptor ARROW )? ( member_name COLON nonvoid_type_descriptor -> ^( I_ENCODED_FIELD ( reference_type_descriptor )? member_name nonvoid_type_descriptor ) | member_name method_prototype -> ^( I_ENCODED_METHOD ( reference_type_descriptor )? member_name method_prototype ) ) )
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:723:7: ( reference_type_descriptor ARROW )? ( member_name COLON nonvoid_type_descriptor -> ^( I_ENCODED_FIELD ( reference_type_descriptor )? member_name nonvoid_type_descriptor ) | member_name method_prototype -> ^( I_ENCODED_METHOD ( reference_type_descriptor )? member_name method_prototype ) )
					{
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:723:7: ( reference_type_descriptor ARROW )?
					int alt30=2;
					int LA30_0 = input.LA(1);
					if ( (LA30_0==ARRAY_TYPE_PREFIX||LA30_0==CLASS_DESCRIPTOR) ) {
						alt30=1;
					}
					switch (alt30) {
						case 1 :
							// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:723:8: reference_type_descriptor ARROW
							{
							pushFollow(FOLLOW_reference_type_descriptor_in_type_field_method_literal3074);
							reference_type_descriptor177=reference_type_descriptor();
							state._fsp--;

							stream_reference_type_descriptor.add(reference_type_descriptor177.getTree());
							ARROW178=(Token)match(input,ARROW,FOLLOW_ARROW_in_type_field_method_literal3076);
							stream_ARROW.add(ARROW178);

							}
							break;

					}

					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:724:7: ( member_name COLON nonvoid_type_descriptor -> ^( I_ENCODED_FIELD ( reference_type_descriptor )? member_name nonvoid_type_descriptor ) | member_name method_prototype -> ^( I_ENCODED_METHOD ( reference_type_descriptor )? member_name method_prototype ) )
					int alt31=2;
					alt31 = dfa31.predict(input);
					switch (alt31) {
						case 1 :
							// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:724:9: member_name COLON nonvoid_type_descriptor
							{
							pushFollow(FOLLOW_member_name_in_type_field_method_literal3088);
							member_name179=member_name();
							state._fsp--;

							stream_member_name.add(member_name179.getTree());
							COLON180=(Token)match(input,COLON,FOLLOW_COLON_in_type_field_method_literal3090);
							stream_COLON.add(COLON180);

							pushFollow(FOLLOW_nonvoid_type_descriptor_in_type_field_method_literal3092);
							nonvoid_type_descriptor181=nonvoid_type_descriptor();
							state._fsp--;

							stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor181.getTree());
							// AST REWRITE
							// elements: nonvoid_type_descriptor, member_name, reference_type_descriptor
							// token labels:
							// rule labels: retval
							// token list labels:
							// rule list labels:
							// wildcard labels:
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 724:51: -> ^( I_ENCODED_FIELD ( reference_type_descriptor )? member_name nonvoid_type_descriptor )
							{
								// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:724:54: ^( I_ENCODED_FIELD ( reference_type_descriptor )? member_name nonvoid_type_descriptor )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ENCODED_FIELD, "I_ENCODED_FIELD"), root_1);
								// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:724:72: ( reference_type_descriptor )?
								if ( stream_reference_type_descriptor.hasNext() ) {
									adaptor.addChild(root_1, stream_reference_type_descriptor.nextTree());
								}
								stream_reference_type_descriptor.reset();

								adaptor.addChild(root_1, stream_member_name.nextTree());
								adaptor.addChild(root_1, stream_nonvoid_type_descriptor.nextTree());
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;

							}
							break;
						case 2 :
							// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:725:9: member_name method_prototype
							{
							pushFollow(FOLLOW_member_name_in_type_field_method_literal3115);
							member_name182=member_name();
							state._fsp--;

							stream_member_name.add(member_name182.getTree());
							pushFollow(FOLLOW_method_prototype_in_type_field_method_literal3117);
							method_prototype183=method_prototype();
							state._fsp--;

							stream_method_prototype.add(method_prototype183.getTree());
							// AST REWRITE
							// elements: member_name, method_prototype, reference_type_descriptor
							// token labels:
							// rule labels: retval
							// token list labels:
							// rule list labels:
							// wildcard labels:
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (CommonTree)adaptor.nil();
							// 725:38: -> ^( I_ENCODED_METHOD ( reference_type_descriptor )? member_name method_prototype )
							{
								// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:725:41: ^( I_ENCODED_METHOD ( reference_type_descriptor )? member_name method_prototype )
								{
								CommonTree root_1 = (CommonTree)adaptor.nil();
								root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ENCODED_METHOD, "I_ENCODED_METHOD"), root_1);
								// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:725:60: ( reference_type_descriptor )?
								if ( stream_reference_type_descriptor.hasNext() ) {
									adaptor.addChild(root_1, stream_reference_type_descriptor.nextTree());
								}
								stream_reference_type_descriptor.reset();

								adaptor.addChild(root_1, stream_member_name.nextTree());
								adaptor.addChild(root_1, stream_method_prototype.nextTree());
								adaptor.addChild(root_0, root_1);
								}

							}


							retval.tree = root_0;

							}
							break;

					}

					}

					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:728:5: PRIMITIVE_TYPE
					{
					root_0 = (CommonTree)adaptor.nil();


					PRIMITIVE_TYPE184=(Token)match(input,PRIMITIVE_TYPE,FOLLOW_PRIMITIVE_TYPE_in_type_field_method_literal3150);
					PRIMITIVE_TYPE184_tree = (CommonTree)adaptor.create(PRIMITIVE_TYPE184);
					adaptor.addChild(root_0, PRIMITIVE_TYPE184_tree);

					}
					break;
				case 4 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:729:5: VOID_TYPE
					{
					root_0 = (CommonTree)adaptor.nil();


					VOID_TYPE185=(Token)match(input,VOID_TYPE,FOLLOW_VOID_TYPE_in_type_field_method_literal3156);
					VOID_TYPE185_tree = (CommonTree)adaptor.create(VOID_TYPE185);
					adaptor.addChild(root_0, VOID_TYPE185_tree);

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "type_field_method_literal"


	public static class call_site_reference_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "call_site_reference"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:731:1: call_site_reference : simple_name OPEN_PAREN STRING_LITERAL COMMA method_prototype ( COMMA literal )* CLOSE_PAREN AT method_reference -> ^( I_CALL_SITE_REFERENCE simple_name STRING_LITERAL method_prototype ^( I_CALL_SITE_EXTRA_ARGUMENTS ( literal )* ) method_reference ) ;
	public final smaliParser.call_site_reference_return call_site_reference() throws RecognitionException {
		smaliParser.call_site_reference_return retval = new smaliParser.call_site_reference_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token OPEN_PAREN187=null;
		Token STRING_LITERAL188=null;
		Token COMMA189=null;
		Token COMMA191=null;
		Token CLOSE_PAREN193=null;
		Token AT194=null;
		ParserRuleReturnScope simple_name186 =null;
		ParserRuleReturnScope method_prototype190 =null;
		ParserRuleReturnScope literal192 =null;
		ParserRuleReturnScope method_reference195 =null;

		CommonTree OPEN_PAREN187_tree=null;
		CommonTree STRING_LITERAL188_tree=null;
		CommonTree COMMA189_tree=null;
		CommonTree COMMA191_tree=null;
		CommonTree CLOSE_PAREN193_tree=null;
		CommonTree AT194_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_OPEN_PAREN=new RewriteRuleTokenStream(adaptor,"token OPEN_PAREN");
		RewriteRuleTokenStream stream_AT=new RewriteRuleTokenStream(adaptor,"token AT");
		RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
		RewriteRuleTokenStream stream_CLOSE_PAREN=new RewriteRuleTokenStream(adaptor,"token CLOSE_PAREN");
		RewriteRuleSubtreeStream stream_method_reference=new RewriteRuleSubtreeStream(adaptor,"rule method_reference");
		RewriteRuleSubtreeStream stream_simple_name=new RewriteRuleSubtreeStream(adaptor,"rule simple_name");
		RewriteRuleSubtreeStream stream_method_prototype=new RewriteRuleSubtreeStream(adaptor,"rule method_prototype");
		RewriteRuleSubtreeStream stream_literal=new RewriteRuleSubtreeStream(adaptor,"rule literal");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:732:3: ( simple_name OPEN_PAREN STRING_LITERAL COMMA method_prototype ( COMMA literal )* CLOSE_PAREN AT method_reference -> ^( I_CALL_SITE_REFERENCE simple_name STRING_LITERAL method_prototype ^( I_CALL_SITE_EXTRA_ARGUMENTS ( literal )* ) method_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:732:5: simple_name OPEN_PAREN STRING_LITERAL COMMA method_prototype ( COMMA literal )* CLOSE_PAREN AT method_reference
			{
			pushFollow(FOLLOW_simple_name_in_call_site_reference3166);
			simple_name186=simple_name();
			state._fsp--;

			stream_simple_name.add(simple_name186.getTree());
			OPEN_PAREN187=(Token)match(input,OPEN_PAREN,FOLLOW_OPEN_PAREN_in_call_site_reference3168);
			stream_OPEN_PAREN.add(OPEN_PAREN187);

			STRING_LITERAL188=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_call_site_reference3170);
			stream_STRING_LITERAL.add(STRING_LITERAL188);

			COMMA189=(Token)match(input,COMMA,FOLLOW_COMMA_in_call_site_reference3172);
			stream_COMMA.add(COMMA189);

			pushFollow(FOLLOW_method_prototype_in_call_site_reference3174);
			method_prototype190=method_prototype();
			state._fsp--;

			stream_method_prototype.add(method_prototype190.getTree());
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:732:66: ( COMMA literal )*
			loop33:
			while (true) {
				int alt33=2;
				int LA33_0 = input.LA(1);
				if ( (LA33_0==COMMA) ) {
					alt33=1;
				}

				switch (alt33) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:732:67: COMMA literal
					{
					COMMA191=(Token)match(input,COMMA,FOLLOW_COMMA_in_call_site_reference3177);
					stream_COMMA.add(COMMA191);

					pushFollow(FOLLOW_literal_in_call_site_reference3179);
					literal192=literal();
					state._fsp--;

					stream_literal.add(literal192.getTree());
					}
					break;

				default :
					break loop33;
				}
			}

			CLOSE_PAREN193=(Token)match(input,CLOSE_PAREN,FOLLOW_CLOSE_PAREN_in_call_site_reference3183);
			stream_CLOSE_PAREN.add(CLOSE_PAREN193);

			AT194=(Token)match(input,AT,FOLLOW_AT_in_call_site_reference3185);
			stream_AT.add(AT194);

			pushFollow(FOLLOW_method_reference_in_call_site_reference3187);
			method_reference195=method_reference();
			state._fsp--;

			stream_method_reference.add(method_reference195.getTree());
			// AST REWRITE
			// elements: STRING_LITERAL, method_prototype, literal, simple_name, method_reference
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 733:5: -> ^( I_CALL_SITE_REFERENCE simple_name STRING_LITERAL method_prototype ^( I_CALL_SITE_EXTRA_ARGUMENTS ( literal )* ) method_reference )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:733:8: ^( I_CALL_SITE_REFERENCE simple_name STRING_LITERAL method_prototype ^( I_CALL_SITE_EXTRA_ARGUMENTS ( literal )* ) method_reference )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_CALL_SITE_REFERENCE, "I_CALL_SITE_REFERENCE"), root_1);
				adaptor.addChild(root_1, stream_simple_name.nextTree());
				adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());
				adaptor.addChild(root_1, stream_method_prototype.nextTree());
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:733:76: ^( I_CALL_SITE_EXTRA_ARGUMENTS ( literal )* )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_CALL_SITE_EXTRA_ARGUMENTS, "I_CALL_SITE_EXTRA_ARGUMENTS"), root_2);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:733:106: ( literal )*
				while ( stream_literal.hasNext() ) {
					adaptor.addChild(root_2, stream_literal.nextTree());
				}
				stream_literal.reset();

				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_1, stream_method_reference.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "call_site_reference"


	public static class method_handle_reference_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "method_handle_reference"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:736:1: method_handle_reference : ( METHOD_HANDLE_TYPE_FIELD AT field_reference -> METHOD_HANDLE_TYPE_FIELD field_reference | METHOD_HANDLE_TYPE_METHOD AT method_reference -> METHOD_HANDLE_TYPE_METHOD method_reference | INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE AT method_reference -> INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE method_reference );
	public final smaliParser.method_handle_reference_return method_handle_reference() throws RecognitionException {
		smaliParser.method_handle_reference_return retval = new smaliParser.method_handle_reference_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token METHOD_HANDLE_TYPE_FIELD196=null;
		Token AT197=null;
		Token METHOD_HANDLE_TYPE_METHOD199=null;
		Token AT200=null;
		Token INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE202=null;
		Token AT203=null;
		ParserRuleReturnScope field_reference198 =null;
		ParserRuleReturnScope method_reference201 =null;
		ParserRuleReturnScope method_reference204 =null;

		CommonTree METHOD_HANDLE_TYPE_FIELD196_tree=null;
		CommonTree AT197_tree=null;
		CommonTree METHOD_HANDLE_TYPE_METHOD199_tree=null;
		CommonTree AT200_tree=null;
		CommonTree INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE202_tree=null;
		CommonTree AT203_tree=null;
		RewriteRuleTokenStream stream_AT=new RewriteRuleTokenStream(adaptor,"token AT");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE");
		RewriteRuleTokenStream stream_METHOD_HANDLE_TYPE_FIELD=new RewriteRuleTokenStream(adaptor,"token METHOD_HANDLE_TYPE_FIELD");
		RewriteRuleTokenStream stream_METHOD_HANDLE_TYPE_METHOD=new RewriteRuleTokenStream(adaptor,"token METHOD_HANDLE_TYPE_METHOD");
		RewriteRuleSubtreeStream stream_method_reference=new RewriteRuleSubtreeStream(adaptor,"rule method_reference");
		RewriteRuleSubtreeStream stream_field_reference=new RewriteRuleSubtreeStream(adaptor,"rule field_reference");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:737:3: ( METHOD_HANDLE_TYPE_FIELD AT field_reference -> METHOD_HANDLE_TYPE_FIELD field_reference | METHOD_HANDLE_TYPE_METHOD AT method_reference -> METHOD_HANDLE_TYPE_METHOD method_reference | INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE AT method_reference -> INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE method_reference )
			int alt34=3;
			switch ( input.LA(1) ) {
			case METHOD_HANDLE_TYPE_FIELD:
				{
				alt34=1;
				}
				break;
			case METHOD_HANDLE_TYPE_METHOD:
				{
				alt34=2;
				}
				break;
			case INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE:
				{
				alt34=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 34, 0, input);
				throw nvae;
			}
			switch (alt34) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:737:5: METHOD_HANDLE_TYPE_FIELD AT field_reference
					{
					METHOD_HANDLE_TYPE_FIELD196=(Token)match(input,METHOD_HANDLE_TYPE_FIELD,FOLLOW_METHOD_HANDLE_TYPE_FIELD_in_method_handle_reference3231);
					stream_METHOD_HANDLE_TYPE_FIELD.add(METHOD_HANDLE_TYPE_FIELD196);

					AT197=(Token)match(input,AT,FOLLOW_AT_in_method_handle_reference3233);
					stream_AT.add(AT197);

					pushFollow(FOLLOW_field_reference_in_method_handle_reference3235);
					field_reference198=field_reference();
					state._fsp--;

					stream_field_reference.add(field_reference198.getTree());
					// AST REWRITE
					// elements: METHOD_HANDLE_TYPE_FIELD, field_reference
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 737:49: -> METHOD_HANDLE_TYPE_FIELD field_reference
					{
						adaptor.addChild(root_0, stream_METHOD_HANDLE_TYPE_FIELD.nextNode());
						adaptor.addChild(root_0, stream_field_reference.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:738:5: METHOD_HANDLE_TYPE_METHOD AT method_reference
					{
					METHOD_HANDLE_TYPE_METHOD199=(Token)match(input,METHOD_HANDLE_TYPE_METHOD,FOLLOW_METHOD_HANDLE_TYPE_METHOD_in_method_handle_reference3247);
					stream_METHOD_HANDLE_TYPE_METHOD.add(METHOD_HANDLE_TYPE_METHOD199);

					AT200=(Token)match(input,AT,FOLLOW_AT_in_method_handle_reference3249);
					stream_AT.add(AT200);

					pushFollow(FOLLOW_method_reference_in_method_handle_reference3251);
					method_reference201=method_reference();
					state._fsp--;

					stream_method_reference.add(method_reference201.getTree());
					// AST REWRITE
					// elements: method_reference, METHOD_HANDLE_TYPE_METHOD
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 738:51: -> METHOD_HANDLE_TYPE_METHOD method_reference
					{
						adaptor.addChild(root_0, stream_METHOD_HANDLE_TYPE_METHOD.nextNode());
						adaptor.addChild(root_0, stream_method_reference.nextTree());
					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:739:5: INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE AT method_reference
					{
					INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE202=(Token)match(input,INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE,FOLLOW_INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE_in_method_handle_reference3263);
					stream_INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE.add(INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE202);

					AT203=(Token)match(input,AT,FOLLOW_AT_in_method_handle_reference3265);
					stream_AT.add(AT203);

					pushFollow(FOLLOW_method_reference_in_method_handle_reference3267);
					method_reference204=method_reference();
					state._fsp--;

					stream_method_reference.add(method_reference204.getTree());
					// AST REWRITE
					// elements: method_reference, INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 739:76: -> INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE method_reference
					{
						adaptor.addChild(root_0, stream_INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE.nextNode());
						adaptor.addChild(root_0, stream_method_reference.nextTree());
					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "method_handle_reference"


	public static class method_handle_literal_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "method_handle_literal"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:741:1: method_handle_literal : method_handle_reference -> ^( I_ENCODED_METHOD_HANDLE method_handle_reference ) ;
	public final smaliParser.method_handle_literal_return method_handle_literal() throws RecognitionException {
		smaliParser.method_handle_literal_return retval = new smaliParser.method_handle_literal_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope method_handle_reference205 =null;

		RewriteRuleSubtreeStream stream_method_handle_reference=new RewriteRuleSubtreeStream(adaptor,"rule method_handle_reference");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:742:3: ( method_handle_reference -> ^( I_ENCODED_METHOD_HANDLE method_handle_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:742:5: method_handle_reference
			{
			pushFollow(FOLLOW_method_handle_reference_in_method_handle_literal3283);
			method_handle_reference205=method_handle_reference();
			state._fsp--;

			stream_method_handle_reference.add(method_handle_reference205.getTree());
			// AST REWRITE
			// elements: method_handle_reference
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 743:3: -> ^( I_ENCODED_METHOD_HANDLE method_handle_reference )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:743:6: ^( I_ENCODED_METHOD_HANDLE method_handle_reference )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ENCODED_METHOD_HANDLE, "I_ENCODED_METHOD_HANDLE"), root_1);
				adaptor.addChild(root_1, stream_method_handle_reference.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "method_handle_literal"


	public static class method_reference_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "method_reference"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:745:1: method_reference : ( reference_type_descriptor ARROW )? member_name method_prototype -> ( reference_type_descriptor )? member_name method_prototype ;
	public final smaliParser.method_reference_return method_reference() throws RecognitionException {
		smaliParser.method_reference_return retval = new smaliParser.method_reference_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ARROW207=null;
		ParserRuleReturnScope reference_type_descriptor206 =null;
		ParserRuleReturnScope member_name208 =null;
		ParserRuleReturnScope method_prototype209 =null;

		CommonTree ARROW207_tree=null;
		RewriteRuleTokenStream stream_ARROW=new RewriteRuleTokenStream(adaptor,"token ARROW");
		RewriteRuleSubtreeStream stream_method_prototype=new RewriteRuleSubtreeStream(adaptor,"rule method_prototype");
		RewriteRuleSubtreeStream stream_member_name=new RewriteRuleSubtreeStream(adaptor,"rule member_name");
		RewriteRuleSubtreeStream stream_reference_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule reference_type_descriptor");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:746:3: ( ( reference_type_descriptor ARROW )? member_name method_prototype -> ( reference_type_descriptor )? member_name method_prototype )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:746:5: ( reference_type_descriptor ARROW )? member_name method_prototype
			{
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:746:5: ( reference_type_descriptor ARROW )?
			int alt35=2;
			int LA35_0 = input.LA(1);
			if ( (LA35_0==ARRAY_TYPE_PREFIX||LA35_0==CLASS_DESCRIPTOR) ) {
				alt35=1;
			}
			switch (alt35) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:746:6: reference_type_descriptor ARROW
					{
					pushFollow(FOLLOW_reference_type_descriptor_in_method_reference3304);
					reference_type_descriptor206=reference_type_descriptor();
					state._fsp--;

					stream_reference_type_descriptor.add(reference_type_descriptor206.getTree());
					ARROW207=(Token)match(input,ARROW,FOLLOW_ARROW_in_method_reference3306);
					stream_ARROW.add(ARROW207);

					}
					break;

			}

			pushFollow(FOLLOW_member_name_in_method_reference3310);
			member_name208=member_name();
			state._fsp--;

			stream_member_name.add(member_name208.getTree());
			pushFollow(FOLLOW_method_prototype_in_method_reference3312);
			method_prototype209=method_prototype();
			state._fsp--;

			stream_method_prototype.add(method_prototype209.getTree());
			// AST REWRITE
			// elements: reference_type_descriptor, member_name, method_prototype
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 747:3: -> ( reference_type_descriptor )? member_name method_prototype
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:747:6: ( reference_type_descriptor )?
				if ( stream_reference_type_descriptor.hasNext() ) {
					adaptor.addChild(root_0, stream_reference_type_descriptor.nextTree());
				}
				stream_reference_type_descriptor.reset();

				adaptor.addChild(root_0, stream_member_name.nextTree());
				adaptor.addChild(root_0, stream_method_prototype.nextTree());
			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "method_reference"


	public static class field_reference_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "field_reference"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:749:1: field_reference : ( reference_type_descriptor ARROW )? member_name COLON nonvoid_type_descriptor -> ( reference_type_descriptor )? member_name nonvoid_type_descriptor ;
	public final smaliParser.field_reference_return field_reference() throws RecognitionException {
		smaliParser.field_reference_return retval = new smaliParser.field_reference_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ARROW211=null;
		Token COLON213=null;
		ParserRuleReturnScope reference_type_descriptor210 =null;
		ParserRuleReturnScope member_name212 =null;
		ParserRuleReturnScope nonvoid_type_descriptor214 =null;

		CommonTree ARROW211_tree=null;
		CommonTree COLON213_tree=null;
		RewriteRuleTokenStream stream_ARROW=new RewriteRuleTokenStream(adaptor,"token ARROW");
		RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
		RewriteRuleSubtreeStream stream_nonvoid_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule nonvoid_type_descriptor");
		RewriteRuleSubtreeStream stream_member_name=new RewriteRuleSubtreeStream(adaptor,"rule member_name");
		RewriteRuleSubtreeStream stream_reference_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule reference_type_descriptor");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:750:3: ( ( reference_type_descriptor ARROW )? member_name COLON nonvoid_type_descriptor -> ( reference_type_descriptor )? member_name nonvoid_type_descriptor )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:750:5: ( reference_type_descriptor ARROW )? member_name COLON nonvoid_type_descriptor
			{
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:750:5: ( reference_type_descriptor ARROW )?
			int alt36=2;
			int LA36_0 = input.LA(1);
			if ( (LA36_0==ARRAY_TYPE_PREFIX||LA36_0==CLASS_DESCRIPTOR) ) {
				alt36=1;
			}
			switch (alt36) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:750:6: reference_type_descriptor ARROW
					{
					pushFollow(FOLLOW_reference_type_descriptor_in_field_reference3334);
					reference_type_descriptor210=reference_type_descriptor();
					state._fsp--;

					stream_reference_type_descriptor.add(reference_type_descriptor210.getTree());
					ARROW211=(Token)match(input,ARROW,FOLLOW_ARROW_in_field_reference3336);
					stream_ARROW.add(ARROW211);

					}
					break;

			}

			pushFollow(FOLLOW_member_name_in_field_reference3340);
			member_name212=member_name();
			state._fsp--;

			stream_member_name.add(member_name212.getTree());
			COLON213=(Token)match(input,COLON,FOLLOW_COLON_in_field_reference3342);
			stream_COLON.add(COLON213);

			pushFollow(FOLLOW_nonvoid_type_descriptor_in_field_reference3344);
			nonvoid_type_descriptor214=nonvoid_type_descriptor();
			state._fsp--;

			stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor214.getTree());
			// AST REWRITE
			// elements: nonvoid_type_descriptor, reference_type_descriptor, member_name
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 751:3: -> ( reference_type_descriptor )? member_name nonvoid_type_descriptor
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:751:6: ( reference_type_descriptor )?
				if ( stream_reference_type_descriptor.hasNext() ) {
					adaptor.addChild(root_0, stream_reference_type_descriptor.nextTree());
				}
				stream_reference_type_descriptor.reset();

				adaptor.addChild(root_0, stream_member_name.nextTree());
				adaptor.addChild(root_0, stream_nonvoid_type_descriptor.nextTree());
			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "field_reference"


	public static class label_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "label"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:753:1: label : COLON simple_name -> ^( I_LABEL[$COLON, \"I_LABEL\"] simple_name ) ;
	public final smaliParser.label_return label() throws RecognitionException {
		smaliParser.label_return retval = new smaliParser.label_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token COLON215=null;
		ParserRuleReturnScope simple_name216 =null;

		CommonTree COLON215_tree=null;
		RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
		RewriteRuleSubtreeStream stream_simple_name=new RewriteRuleSubtreeStream(adaptor,"rule simple_name");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:754:3: ( COLON simple_name -> ^( I_LABEL[$COLON, \"I_LABEL\"] simple_name ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:754:5: COLON simple_name
			{
			COLON215=(Token)match(input,COLON,FOLLOW_COLON_in_label3365);
			stream_COLON.add(COLON215);

			pushFollow(FOLLOW_simple_name_in_label3367);
			simple_name216=simple_name();
			state._fsp--;

			stream_simple_name.add(simple_name216.getTree());
			// AST REWRITE
			// elements: simple_name
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 754:23: -> ^( I_LABEL[$COLON, \"I_LABEL\"] simple_name )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:754:26: ^( I_LABEL[$COLON, \"I_LABEL\"] simple_name )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_LABEL, COLON215, "I_LABEL"), root_1);
				adaptor.addChild(root_1, stream_simple_name.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "label"


	public static class label_ref_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "label_ref"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:756:1: label_ref : COLON simple_name -> simple_name ;
	public final smaliParser.label_ref_return label_ref() throws RecognitionException {
		smaliParser.label_ref_return retval = new smaliParser.label_ref_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token COLON217=null;
		ParserRuleReturnScope simple_name218 =null;

		CommonTree COLON217_tree=null;
		RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
		RewriteRuleSubtreeStream stream_simple_name=new RewriteRuleSubtreeStream(adaptor,"rule simple_name");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:757:3: ( COLON simple_name -> simple_name )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:757:5: COLON simple_name
			{
			COLON217=(Token)match(input,COLON,FOLLOW_COLON_in_label_ref3386);
			stream_COLON.add(COLON217);

			pushFollow(FOLLOW_simple_name_in_label_ref3388);
			simple_name218=simple_name();
			state._fsp--;

			stream_simple_name.add(simple_name218.getTree());
			// AST REWRITE
			// elements: simple_name
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 757:23: -> simple_name
			{
				adaptor.addChild(root_0, stream_simple_name.nextTree());
			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "label_ref"


	public static class register_list_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "register_list"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:759:1: register_list : ( REGISTER ( COMMA REGISTER )* -> ^( I_REGISTER_LIST[$start, \"I_REGISTER_LIST\"] ( REGISTER )* ) | -> ^( I_REGISTER_LIST[$start, \"I_REGISTER_LIST\"] ) );
	public final smaliParser.register_list_return register_list() throws RecognitionException {
		smaliParser.register_list_return retval = new smaliParser.register_list_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token REGISTER219=null;
		Token COMMA220=null;
		Token REGISTER221=null;

		CommonTree REGISTER219_tree=null;
		CommonTree COMMA220_tree=null;
		CommonTree REGISTER221_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:760:3: ( REGISTER ( COMMA REGISTER )* -> ^( I_REGISTER_LIST[$start, \"I_REGISTER_LIST\"] ( REGISTER )* ) | -> ^( I_REGISTER_LIST[$start, \"I_REGISTER_LIST\"] ) )
			int alt38=2;
			int LA38_0 = input.LA(1);
			if ( (LA38_0==REGISTER) ) {
				alt38=1;
			}
			else if ( (LA38_0==CLOSE_BRACE) ) {
				alt38=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 38, 0, input);
				throw nvae;
			}

			switch (alt38) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:760:5: REGISTER ( COMMA REGISTER )*
					{
					REGISTER219=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_register_list3402);
					stream_REGISTER.add(REGISTER219);

					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:760:14: ( COMMA REGISTER )*
					loop37:
					while (true) {
						int alt37=2;
						int LA37_0 = input.LA(1);
						if ( (LA37_0==COMMA) ) {
							alt37=1;
						}

						switch (alt37) {
						case 1 :
							// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:760:15: COMMA REGISTER
							{
							COMMA220=(Token)match(input,COMMA,FOLLOW_COMMA_in_register_list3405);
							stream_COMMA.add(COMMA220);

							REGISTER221=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_register_list3407);
							stream_REGISTER.add(REGISTER221);

							}
							break;

						default :
							break loop37;
						}
					}

					// AST REWRITE
					// elements: REGISTER
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 760:32: -> ^( I_REGISTER_LIST[$start, \"I_REGISTER_LIST\"] ( REGISTER )* )
					{
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:760:35: ^( I_REGISTER_LIST[$start, \"I_REGISTER_LIST\"] ( REGISTER )* )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_REGISTER_LIST, (retval.start), "I_REGISTER_LIST"), root_1);
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:760:80: ( REGISTER )*
						while ( stream_REGISTER.hasNext() ) {
							adaptor.addChild(root_1, stream_REGISTER.nextNode());
						}
						stream_REGISTER.reset();

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:761:5:
					{
					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 761:5: -> ^( I_REGISTER_LIST[$start, \"I_REGISTER_LIST\"] )
					{
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:761:7: ^( I_REGISTER_LIST[$start, \"I_REGISTER_LIST\"] )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_REGISTER_LIST, (retval.start), "I_REGISTER_LIST"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "register_list"


	public static class register_range_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "register_range"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:763:1: register_range : (startreg= REGISTER ( DOTDOT endreg= REGISTER )? )? -> ^( I_REGISTER_RANGE[$start, \"I_REGISTER_RANGE\"] ( $startreg)? ( $endreg)? ) ;
	public final smaliParser.register_range_return register_range() throws RecognitionException {
		smaliParser.register_range_return retval = new smaliParser.register_range_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token startreg=null;
		Token endreg=null;
		Token DOTDOT222=null;

		CommonTree startreg_tree=null;
		CommonTree endreg_tree=null;
		CommonTree DOTDOT222_tree=null;
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_DOTDOT=new RewriteRuleTokenStream(adaptor,"token DOTDOT");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:764:3: ( (startreg= REGISTER ( DOTDOT endreg= REGISTER )? )? -> ^( I_REGISTER_RANGE[$start, \"I_REGISTER_RANGE\"] ( $startreg)? ( $endreg)? ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:764:5: (startreg= REGISTER ( DOTDOT endreg= REGISTER )? )?
			{
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:764:5: (startreg= REGISTER ( DOTDOT endreg= REGISTER )? )?
			int alt40=2;
			int LA40_0 = input.LA(1);
			if ( (LA40_0==REGISTER) ) {
				alt40=1;
			}
			switch (alt40) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:764:6: startreg= REGISTER ( DOTDOT endreg= REGISTER )?
					{
					startreg=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_register_range3442);
					stream_REGISTER.add(startreg);

					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:764:24: ( DOTDOT endreg= REGISTER )?
					int alt39=2;
					int LA39_0 = input.LA(1);
					if ( (LA39_0==DOTDOT) ) {
						alt39=1;
					}
					switch (alt39) {
						case 1 :
							// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:764:25: DOTDOT endreg= REGISTER
							{
							DOTDOT222=(Token)match(input,DOTDOT,FOLLOW_DOTDOT_in_register_range3445);
							stream_DOTDOT.add(DOTDOT222);

							endreg=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_register_range3449);
							stream_REGISTER.add(endreg);

							}
							break;

					}

					}
					break;

			}

			// AST REWRITE
			// elements: endreg, startreg
			// token labels: endreg, startreg
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleTokenStream stream_endreg=new RewriteRuleTokenStream(adaptor,"token endreg",endreg);
			RewriteRuleTokenStream stream_startreg=new RewriteRuleTokenStream(adaptor,"token startreg",startreg);
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 764:52: -> ^( I_REGISTER_RANGE[$start, \"I_REGISTER_RANGE\"] ( $startreg)? ( $endreg)? )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:764:55: ^( I_REGISTER_RANGE[$start, \"I_REGISTER_RANGE\"] ( $startreg)? ( $endreg)? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_REGISTER_RANGE, (retval.start), "I_REGISTER_RANGE"), root_1);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:764:103: ( $startreg)?
				if ( stream_startreg.hasNext() ) {
					adaptor.addChild(root_1, stream_startreg.nextNode());
				}
				stream_startreg.reset();

				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:764:114: ( $endreg)?
				if ( stream_endreg.hasNext() ) {
					adaptor.addChild(root_1, stream_endreg.nextNode());
				}
				stream_endreg.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "register_range"


	public static class verification_error_reference_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "verification_error_reference"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:766:1: verification_error_reference : ( CLASS_DESCRIPTOR | field_reference | method_reference );
	public final smaliParser.verification_error_reference_return verification_error_reference() throws RecognitionException {
		smaliParser.verification_error_reference_return retval = new smaliParser.verification_error_reference_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token CLASS_DESCRIPTOR223=null;
		ParserRuleReturnScope field_reference224 =null;
		ParserRuleReturnScope method_reference225 =null;

		CommonTree CLASS_DESCRIPTOR223_tree=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:767:3: ( CLASS_DESCRIPTOR | field_reference | method_reference )
			int alt41=3;
			alt41 = dfa41.predict(input);
			switch (alt41) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:767:5: CLASS_DESCRIPTOR
					{
					root_0 = (CommonTree)adaptor.nil();


					CLASS_DESCRIPTOR223=(Token)match(input,CLASS_DESCRIPTOR,FOLLOW_CLASS_DESCRIPTOR_in_verification_error_reference3478);
					CLASS_DESCRIPTOR223_tree = (CommonTree)adaptor.create(CLASS_DESCRIPTOR223);
					adaptor.addChild(root_0, CLASS_DESCRIPTOR223_tree);

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:767:24: field_reference
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_field_reference_in_verification_error_reference3482);
					field_reference224=field_reference();
					state._fsp--;

					adaptor.addChild(root_0, field_reference224.getTree());

					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:767:42: method_reference
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_method_reference_in_verification_error_reference3486);
					method_reference225=method_reference();
					state._fsp--;

					adaptor.addChild(root_0, method_reference225.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "verification_error_reference"


	public static class catch_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "catch_directive"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:769:1: catch_directive : CATCH_DIRECTIVE nonvoid_type_descriptor OPEN_BRACE from= label_ref DOTDOT to= label_ref CLOSE_BRACE using= label_ref -> ^( I_CATCH[$start, \"I_CATCH\"] nonvoid_type_descriptor $from $to $using) ;
	public final smaliParser.catch_directive_return catch_directive() throws RecognitionException {
		smaliParser.catch_directive_return retval = new smaliParser.catch_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token CATCH_DIRECTIVE226=null;
		Token OPEN_BRACE228=null;
		Token DOTDOT229=null;
		Token CLOSE_BRACE230=null;
		ParserRuleReturnScope from =null;
		ParserRuleReturnScope to =null;
		ParserRuleReturnScope using =null;
		ParserRuleReturnScope nonvoid_type_descriptor227 =null;

		CommonTree CATCH_DIRECTIVE226_tree=null;
		CommonTree OPEN_BRACE228_tree=null;
		CommonTree DOTDOT229_tree=null;
		CommonTree CLOSE_BRACE230_tree=null;
		RewriteRuleTokenStream stream_OPEN_BRACE=new RewriteRuleTokenStream(adaptor,"token OPEN_BRACE");
		RewriteRuleTokenStream stream_CLOSE_BRACE=new RewriteRuleTokenStream(adaptor,"token CLOSE_BRACE");
		RewriteRuleTokenStream stream_DOTDOT=new RewriteRuleTokenStream(adaptor,"token DOTDOT");
		RewriteRuleTokenStream stream_CATCH_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token CATCH_DIRECTIVE");
		RewriteRuleSubtreeStream stream_label_ref=new RewriteRuleSubtreeStream(adaptor,"rule label_ref");
		RewriteRuleSubtreeStream stream_nonvoid_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule nonvoid_type_descriptor");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:770:3: ( CATCH_DIRECTIVE nonvoid_type_descriptor OPEN_BRACE from= label_ref DOTDOT to= label_ref CLOSE_BRACE using= label_ref -> ^( I_CATCH[$start, \"I_CATCH\"] nonvoid_type_descriptor $from $to $using) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:770:5: CATCH_DIRECTIVE nonvoid_type_descriptor OPEN_BRACE from= label_ref DOTDOT to= label_ref CLOSE_BRACE using= label_ref
			{
			CATCH_DIRECTIVE226=(Token)match(input,CATCH_DIRECTIVE,FOLLOW_CATCH_DIRECTIVE_in_catch_directive3496);
			stream_CATCH_DIRECTIVE.add(CATCH_DIRECTIVE226);

			pushFollow(FOLLOW_nonvoid_type_descriptor_in_catch_directive3498);
			nonvoid_type_descriptor227=nonvoid_type_descriptor();
			state._fsp--;

			stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor227.getTree());
			OPEN_BRACE228=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_catch_directive3500);
			stream_OPEN_BRACE.add(OPEN_BRACE228);

			pushFollow(FOLLOW_label_ref_in_catch_directive3504);
			from=label_ref();
			state._fsp--;

			stream_label_ref.add(from.getTree());
			DOTDOT229=(Token)match(input,DOTDOT,FOLLOW_DOTDOT_in_catch_directive3506);
			stream_DOTDOT.add(DOTDOT229);

			pushFollow(FOLLOW_label_ref_in_catch_directive3510);
			to=label_ref();
			state._fsp--;

			stream_label_ref.add(to.getTree());
			CLOSE_BRACE230=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_catch_directive3512);
			stream_CLOSE_BRACE.add(CLOSE_BRACE230);

			pushFollow(FOLLOW_label_ref_in_catch_directive3516);
			using=label_ref();
			state._fsp--;

			stream_label_ref.add(using.getTree());
			// AST REWRITE
			// elements: from, using, nonvoid_type_descriptor, to
			// token labels:
			// rule labels: using, from, to, retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_using=new RewriteRuleSubtreeStream(adaptor,"rule using",using!=null?using.getTree():null);
			RewriteRuleSubtreeStream stream_from=new RewriteRuleSubtreeStream(adaptor,"rule from",from!=null?from.getTree():null);
			RewriteRuleSubtreeStream stream_to=new RewriteRuleSubtreeStream(adaptor,"rule to",to!=null?to.getTree():null);
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 771:5: -> ^( I_CATCH[$start, \"I_CATCH\"] nonvoid_type_descriptor $from $to $using)
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:771:8: ^( I_CATCH[$start, \"I_CATCH\"] nonvoid_type_descriptor $from $to $using)
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_CATCH, (retval.start), "I_CATCH"), root_1);
				adaptor.addChild(root_1, stream_nonvoid_type_descriptor.nextTree());
				adaptor.addChild(root_1, stream_from.nextTree());
				adaptor.addChild(root_1, stream_to.nextTree());
				adaptor.addChild(root_1, stream_using.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "catch_directive"


	public static class catchall_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "catchall_directive"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:773:1: catchall_directive : CATCHALL_DIRECTIVE OPEN_BRACE from= label_ref DOTDOT to= label_ref CLOSE_BRACE using= label_ref -> ^( I_CATCHALL[$start, \"I_CATCHALL\"] $from $to $using) ;
	public final smaliParser.catchall_directive_return catchall_directive() throws RecognitionException {
		smaliParser.catchall_directive_return retval = new smaliParser.catchall_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token CATCHALL_DIRECTIVE231=null;
		Token OPEN_BRACE232=null;
		Token DOTDOT233=null;
		Token CLOSE_BRACE234=null;
		ParserRuleReturnScope from =null;
		ParserRuleReturnScope to =null;
		ParserRuleReturnScope using =null;

		CommonTree CATCHALL_DIRECTIVE231_tree=null;
		CommonTree OPEN_BRACE232_tree=null;
		CommonTree DOTDOT233_tree=null;
		CommonTree CLOSE_BRACE234_tree=null;
		RewriteRuleTokenStream stream_OPEN_BRACE=new RewriteRuleTokenStream(adaptor,"token OPEN_BRACE");
		RewriteRuleTokenStream stream_CLOSE_BRACE=new RewriteRuleTokenStream(adaptor,"token CLOSE_BRACE");
		RewriteRuleTokenStream stream_DOTDOT=new RewriteRuleTokenStream(adaptor,"token DOTDOT");
		RewriteRuleTokenStream stream_CATCHALL_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token CATCHALL_DIRECTIVE");
		RewriteRuleSubtreeStream stream_label_ref=new RewriteRuleSubtreeStream(adaptor,"rule label_ref");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:774:3: ( CATCHALL_DIRECTIVE OPEN_BRACE from= label_ref DOTDOT to= label_ref CLOSE_BRACE using= label_ref -> ^( I_CATCHALL[$start, \"I_CATCHALL\"] $from $to $using) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:774:5: CATCHALL_DIRECTIVE OPEN_BRACE from= label_ref DOTDOT to= label_ref CLOSE_BRACE using= label_ref
			{
			CATCHALL_DIRECTIVE231=(Token)match(input,CATCHALL_DIRECTIVE,FOLLOW_CATCHALL_DIRECTIVE_in_catchall_directive3548);
			stream_CATCHALL_DIRECTIVE.add(CATCHALL_DIRECTIVE231);

			OPEN_BRACE232=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_catchall_directive3550);
			stream_OPEN_BRACE.add(OPEN_BRACE232);

			pushFollow(FOLLOW_label_ref_in_catchall_directive3554);
			from=label_ref();
			state._fsp--;

			stream_label_ref.add(from.getTree());
			DOTDOT233=(Token)match(input,DOTDOT,FOLLOW_DOTDOT_in_catchall_directive3556);
			stream_DOTDOT.add(DOTDOT233);

			pushFollow(FOLLOW_label_ref_in_catchall_directive3560);
			to=label_ref();
			state._fsp--;

			stream_label_ref.add(to.getTree());
			CLOSE_BRACE234=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_catchall_directive3562);
			stream_CLOSE_BRACE.add(CLOSE_BRACE234);

			pushFollow(FOLLOW_label_ref_in_catchall_directive3566);
			using=label_ref();
			state._fsp--;

			stream_label_ref.add(using.getTree());
			// AST REWRITE
			// elements: to, using, from
			// token labels:
			// rule labels: using, from, to, retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_using=new RewriteRuleSubtreeStream(adaptor,"rule using",using!=null?using.getTree():null);
			RewriteRuleSubtreeStream stream_from=new RewriteRuleSubtreeStream(adaptor,"rule from",from!=null?from.getTree():null);
			RewriteRuleSubtreeStream stream_to=new RewriteRuleSubtreeStream(adaptor,"rule to",to!=null?to.getTree():null);
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 775:5: -> ^( I_CATCHALL[$start, \"I_CATCHALL\"] $from $to $using)
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:775:8: ^( I_CATCHALL[$start, \"I_CATCHALL\"] $from $to $using)
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_CATCHALL, (retval.start), "I_CATCHALL"), root_1);
				adaptor.addChild(root_1, stream_from.nextTree());
				adaptor.addChild(root_1, stream_to.nextTree());
				adaptor.addChild(root_1, stream_using.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "catchall_directive"


	public static class parameter_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "parameter_directive"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:781:1: parameter_directive : PARAMETER_DIRECTIVE REGISTER ( COMMA STRING_LITERAL )? ({...}? annotation )* ( END_PARAMETER_DIRECTIVE -> ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ( annotation )* ) ) | -> ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ) ) ) ;
	public final smaliParser.parameter_directive_return parameter_directive() throws RecognitionException {
		smaliParser.parameter_directive_return retval = new smaliParser.parameter_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token PARAMETER_DIRECTIVE235=null;
		Token REGISTER236=null;
		Token COMMA237=null;
		Token STRING_LITERAL238=null;
		Token END_PARAMETER_DIRECTIVE240=null;
		ParserRuleReturnScope annotation239 =null;

		CommonTree PARAMETER_DIRECTIVE235_tree=null;
		CommonTree REGISTER236_tree=null;
		CommonTree COMMA237_tree=null;
		CommonTree STRING_LITERAL238_tree=null;
		CommonTree END_PARAMETER_DIRECTIVE240_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_PARAMETER_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token PARAMETER_DIRECTIVE");
		RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
		RewriteRuleTokenStream stream_END_PARAMETER_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token END_PARAMETER_DIRECTIVE");
		RewriteRuleSubtreeStream stream_annotation=new RewriteRuleSubtreeStream(adaptor,"rule annotation");

		List<CommonTree> annotations = new ArrayList<CommonTree>();
		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:783:3: ( PARAMETER_DIRECTIVE REGISTER ( COMMA STRING_LITERAL )? ({...}? annotation )* ( END_PARAMETER_DIRECTIVE -> ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ( annotation )* ) ) | -> ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ) ) ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:783:5: PARAMETER_DIRECTIVE REGISTER ( COMMA STRING_LITERAL )? ({...}? annotation )* ( END_PARAMETER_DIRECTIVE -> ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ( annotation )* ) ) | -> ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ) ) )
			{
			PARAMETER_DIRECTIVE235=(Token)match(input,PARAMETER_DIRECTIVE,FOLLOW_PARAMETER_DIRECTIVE_in_parameter_directive3605);
			stream_PARAMETER_DIRECTIVE.add(PARAMETER_DIRECTIVE235);

			REGISTER236=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_parameter_directive3607);
			stream_REGISTER.add(REGISTER236);

			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:783:34: ( COMMA STRING_LITERAL )?
			int alt42=2;
			int LA42_0 = input.LA(1);
			if ( (LA42_0==COMMA) ) {
				alt42=1;
			}
			switch (alt42) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:783:35: COMMA STRING_LITERAL
					{
					COMMA237=(Token)match(input,COMMA,FOLLOW_COMMA_in_parameter_directive3610);
					stream_COMMA.add(COMMA237);

					STRING_LITERAL238=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_parameter_directive3612);
					stream_STRING_LITERAL.add(STRING_LITERAL238);

					}
					break;

			}

			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:784:5: ({...}? annotation )*
			loop43:
			while (true) {
				int alt43=2;
				alt43 = dfa43.predict(input);
				switch (alt43) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:784:6: {...}? annotation
					{
					if ( !((input.LA(1) == ANNOTATION_DIRECTIVE)) ) {
						throw new FailedPredicateException(input, "parameter_directive", "input.LA(1) == ANNOTATION_DIRECTIVE");
					}
					pushFollow(FOLLOW_annotation_in_parameter_directive3623);
					annotation239=annotation();
					state._fsp--;

					stream_annotation.add(annotation239.getTree());
					annotations.add((annotation239!=null?((CommonTree)annotation239.getTree()):null));
					}
					break;

				default :
					break loop43;
				}
			}

			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:786:5: ( END_PARAMETER_DIRECTIVE -> ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ( annotation )* ) ) | -> ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ) ) )
			int alt44=2;
			int LA44_0 = input.LA(1);
			if ( (LA44_0==END_PARAMETER_DIRECTIVE) ) {
				alt44=1;
			}
			else if ( (LA44_0==ANNOTATION_DIRECTIVE||LA44_0==ARRAY_DATA_DIRECTIVE||(LA44_0 >= CATCHALL_DIRECTIVE && LA44_0 <= CATCH_DIRECTIVE)||LA44_0==COLON||(LA44_0 >= END_LOCAL_DIRECTIVE && LA44_0 <= END_METHOD_DIRECTIVE)||LA44_0==EPILOGUE_DIRECTIVE||(LA44_0 >= INSTRUCTION_FORMAT10t && LA44_0 <= INSTRUCTION_FORMAT51l)||(LA44_0 >= LINE_DIRECTIVE && LA44_0 <= LOCAL_DIRECTIVE)||(LA44_0 >= PACKED_SWITCH_DIRECTIVE && LA44_0 <= PARAMETER_DIRECTIVE)||LA44_0==PROLOGUE_DIRECTIVE||(LA44_0 >= REGISTERS_DIRECTIVE && LA44_0 <= RESTART_LOCAL_DIRECTIVE)||(LA44_0 >= SOURCE_DIRECTIVE && LA44_0 <= SPARSE_SWITCH_DIRECTIVE)) ) {
				alt44=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 44, 0, input);
				throw nvae;
			}

			switch (alt44) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:786:7: END_PARAMETER_DIRECTIVE
					{
					END_PARAMETER_DIRECTIVE240=(Token)match(input,END_PARAMETER_DIRECTIVE,FOLLOW_END_PARAMETER_DIRECTIVE_in_parameter_directive3636);
					stream_END_PARAMETER_DIRECTIVE.add(END_PARAMETER_DIRECTIVE240);

					// AST REWRITE
					// elements: REGISTER, annotation, STRING_LITERAL
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 787:7: -> ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ( annotation )* ) )
					{
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:787:10: ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ( annotation )* ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_PARAMETER, (retval.start), "I_PARAMETER"), root_1);
						adaptor.addChild(root_1, stream_REGISTER.nextNode());
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:787:56: ( STRING_LITERAL )?
						if ( stream_STRING_LITERAL.hasNext() ) {
							adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());
						}
						stream_STRING_LITERAL.reset();

						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:787:72: ^( I_ANNOTATIONS ( annotation )* )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ANNOTATIONS, "I_ANNOTATIONS"), root_2);
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:787:88: ( annotation )*
						while ( stream_annotation.hasNext() ) {
							adaptor.addChild(root_2, stream_annotation.nextTree());
						}
						stream_annotation.reset();

						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:788:19:
					{
					statements_and_directives_stack.peek().methodAnnotations.addAll(annotations);
					// AST REWRITE
					// elements: REGISTER, STRING_LITERAL
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 789:7: -> ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ) )
					{
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:789:10: ^( I_PARAMETER[$start, \"I_PARAMETER\"] REGISTER ( STRING_LITERAL )? ^( I_ANNOTATIONS ) )
						{
						CommonTree root_1 = (CommonTree)adaptor.nil();
						root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_PARAMETER, (retval.start), "I_PARAMETER"), root_1);
						adaptor.addChild(root_1, stream_REGISTER.nextNode());
						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:789:56: ( STRING_LITERAL )?
						if ( stream_STRING_LITERAL.hasNext() ) {
							adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());
						}
						stream_STRING_LITERAL.reset();

						// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:789:72: ^( I_ANNOTATIONS )
						{
						CommonTree root_2 = (CommonTree)adaptor.nil();
						root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ANNOTATIONS, "I_ANNOTATIONS"), root_2);
						adaptor.addChild(root_1, root_2);
						}

						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "parameter_directive"


	public static class debug_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "debug_directive"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:792:1: debug_directive : ( line_directive | local_directive | end_local_directive | restart_local_directive | prologue_directive | epilogue_directive | source_directive );
	public final smaliParser.debug_directive_return debug_directive() throws RecognitionException {
		smaliParser.debug_directive_return retval = new smaliParser.debug_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope line_directive241 =null;
		ParserRuleReturnScope local_directive242 =null;
		ParserRuleReturnScope end_local_directive243 =null;
		ParserRuleReturnScope restart_local_directive244 =null;
		ParserRuleReturnScope prologue_directive245 =null;
		ParserRuleReturnScope epilogue_directive246 =null;
		ParserRuleReturnScope source_directive247 =null;


		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:793:3: ( line_directive | local_directive | end_local_directive | restart_local_directive | prologue_directive | epilogue_directive | source_directive )
			int alt45=7;
			switch ( input.LA(1) ) {
			case LINE_DIRECTIVE:
				{
				alt45=1;
				}
				break;
			case LOCAL_DIRECTIVE:
				{
				alt45=2;
				}
				break;
			case END_LOCAL_DIRECTIVE:
				{
				alt45=3;
				}
				break;
			case RESTART_LOCAL_DIRECTIVE:
				{
				alt45=4;
				}
				break;
			case PROLOGUE_DIRECTIVE:
				{
				alt45=5;
				}
				break;
			case EPILOGUE_DIRECTIVE:
				{
				alt45=6;
				}
				break;
			case SOURCE_DIRECTIVE:
				{
				alt45=7;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 45, 0, input);
				throw nvae;
			}
			switch (alt45) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:793:5: line_directive
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_line_directive_in_debug_directive3709);
					line_directive241=line_directive();
					state._fsp--;

					adaptor.addChild(root_0, line_directive241.getTree());

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:794:5: local_directive
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_local_directive_in_debug_directive3715);
					local_directive242=local_directive();
					state._fsp--;

					adaptor.addChild(root_0, local_directive242.getTree());

					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:795:5: end_local_directive
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_end_local_directive_in_debug_directive3721);
					end_local_directive243=end_local_directive();
					state._fsp--;

					adaptor.addChild(root_0, end_local_directive243.getTree());

					}
					break;
				case 4 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:796:5: restart_local_directive
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_restart_local_directive_in_debug_directive3727);
					restart_local_directive244=restart_local_directive();
					state._fsp--;

					adaptor.addChild(root_0, restart_local_directive244.getTree());

					}
					break;
				case 5 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:797:5: prologue_directive
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_prologue_directive_in_debug_directive3733);
					prologue_directive245=prologue_directive();
					state._fsp--;

					adaptor.addChild(root_0, prologue_directive245.getTree());

					}
					break;
				case 6 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:798:5: epilogue_directive
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_epilogue_directive_in_debug_directive3739);
					epilogue_directive246=epilogue_directive();
					state._fsp--;

					adaptor.addChild(root_0, epilogue_directive246.getTree());

					}
					break;
				case 7 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:799:5: source_directive
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_source_directive_in_debug_directive3745);
					source_directive247=source_directive();
					state._fsp--;

					adaptor.addChild(root_0, source_directive247.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "debug_directive"


	public static class line_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "line_directive"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:801:1: line_directive : LINE_DIRECTIVE integral_literal -> ^( I_LINE[$start, \"I_LINE\"] integral_literal ) ;
	public final smaliParser.line_directive_return line_directive() throws RecognitionException {
		smaliParser.line_directive_return retval = new smaliParser.line_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token LINE_DIRECTIVE248=null;
		ParserRuleReturnScope integral_literal249 =null;

		CommonTree LINE_DIRECTIVE248_tree=null;
		RewriteRuleTokenStream stream_LINE_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token LINE_DIRECTIVE");
		RewriteRuleSubtreeStream stream_integral_literal=new RewriteRuleSubtreeStream(adaptor,"rule integral_literal");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:802:3: ( LINE_DIRECTIVE integral_literal -> ^( I_LINE[$start, \"I_LINE\"] integral_literal ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:802:5: LINE_DIRECTIVE integral_literal
			{
			LINE_DIRECTIVE248=(Token)match(input,LINE_DIRECTIVE,FOLLOW_LINE_DIRECTIVE_in_line_directive3755);
			stream_LINE_DIRECTIVE.add(LINE_DIRECTIVE248);

			pushFollow(FOLLOW_integral_literal_in_line_directive3757);
			integral_literal249=integral_literal();
			state._fsp--;

			stream_integral_literal.add(integral_literal249.getTree());
			// AST REWRITE
			// elements: integral_literal
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 803:5: -> ^( I_LINE[$start, \"I_LINE\"] integral_literal )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:803:8: ^( I_LINE[$start, \"I_LINE\"] integral_literal )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_LINE, (retval.start), "I_LINE"), root_1);
				adaptor.addChild(root_1, stream_integral_literal.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "line_directive"


	public static class local_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "local_directive"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:805:1: local_directive : LOCAL_DIRECTIVE REGISTER ( COMMA ( NULL_LITERAL |name= STRING_LITERAL ) COLON ( VOID_TYPE | nonvoid_type_descriptor ) ( COMMA signature= STRING_LITERAL )? )? -> ^( I_LOCAL[$start, \"I_LOCAL\"] REGISTER ( NULL_LITERAL )? ( $name)? ( nonvoid_type_descriptor )? ( $signature)? ) ;
	public final smaliParser.local_directive_return local_directive() throws RecognitionException {
		smaliParser.local_directive_return retval = new smaliParser.local_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token name=null;
		Token signature=null;
		Token LOCAL_DIRECTIVE250=null;
		Token REGISTER251=null;
		Token COMMA252=null;
		Token NULL_LITERAL253=null;
		Token COLON254=null;
		Token VOID_TYPE255=null;
		Token COMMA257=null;
		ParserRuleReturnScope nonvoid_type_descriptor256 =null;

		CommonTree name_tree=null;
		CommonTree signature_tree=null;
		CommonTree LOCAL_DIRECTIVE250_tree=null;
		CommonTree REGISTER251_tree=null;
		CommonTree COMMA252_tree=null;
		CommonTree NULL_LITERAL253_tree=null;
		CommonTree COLON254_tree=null;
		CommonTree VOID_TYPE255_tree=null;
		CommonTree COMMA257_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_LOCAL_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token LOCAL_DIRECTIVE");
		RewriteRuleTokenStream stream_VOID_TYPE=new RewriteRuleTokenStream(adaptor,"token VOID_TYPE");
		RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
		RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
		RewriteRuleTokenStream stream_NULL_LITERAL=new RewriteRuleTokenStream(adaptor,"token NULL_LITERAL");
		RewriteRuleSubtreeStream stream_nonvoid_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule nonvoid_type_descriptor");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:806:3: ( LOCAL_DIRECTIVE REGISTER ( COMMA ( NULL_LITERAL |name= STRING_LITERAL ) COLON ( VOID_TYPE | nonvoid_type_descriptor ) ( COMMA signature= STRING_LITERAL )? )? -> ^( I_LOCAL[$start, \"I_LOCAL\"] REGISTER ( NULL_LITERAL )? ( $name)? ( nonvoid_type_descriptor )? ( $signature)? ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:806:5: LOCAL_DIRECTIVE REGISTER ( COMMA ( NULL_LITERAL |name= STRING_LITERAL ) COLON ( VOID_TYPE | nonvoid_type_descriptor ) ( COMMA signature= STRING_LITERAL )? )?
			{
			LOCAL_DIRECTIVE250=(Token)match(input,LOCAL_DIRECTIVE,FOLLOW_LOCAL_DIRECTIVE_in_local_directive3780);
			stream_LOCAL_DIRECTIVE.add(LOCAL_DIRECTIVE250);

			REGISTER251=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_local_directive3782);
			stream_REGISTER.add(REGISTER251);

			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:806:30: ( COMMA ( NULL_LITERAL |name= STRING_LITERAL ) COLON ( VOID_TYPE | nonvoid_type_descriptor ) ( COMMA signature= STRING_LITERAL )? )?
			int alt49=2;
			int LA49_0 = input.LA(1);
			if ( (LA49_0==COMMA) ) {
				alt49=1;
			}
			switch (alt49) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:806:31: COMMA ( NULL_LITERAL |name= STRING_LITERAL ) COLON ( VOID_TYPE | nonvoid_type_descriptor ) ( COMMA signature= STRING_LITERAL )?
					{
					COMMA252=(Token)match(input,COMMA,FOLLOW_COMMA_in_local_directive3785);
					stream_COMMA.add(COMMA252);

					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:806:37: ( NULL_LITERAL |name= STRING_LITERAL )
					int alt46=2;
					int LA46_0 = input.LA(1);
					if ( (LA46_0==NULL_LITERAL) ) {
						alt46=1;
					}
					else if ( (LA46_0==STRING_LITERAL) ) {
						alt46=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 46, 0, input);
						throw nvae;
					}

					switch (alt46) {
						case 1 :
							// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:806:38: NULL_LITERAL
							{
							NULL_LITERAL253=(Token)match(input,NULL_LITERAL,FOLLOW_NULL_LITERAL_in_local_directive3788);
							stream_NULL_LITERAL.add(NULL_LITERAL253);

							}
							break;
						case 2 :
							// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:806:53: name= STRING_LITERAL
							{
							name=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_local_directive3794);
							stream_STRING_LITERAL.add(name);

							}
							break;

					}

					COLON254=(Token)match(input,COLON,FOLLOW_COLON_in_local_directive3797);
					stream_COLON.add(COLON254);

					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:806:80: ( VOID_TYPE | nonvoid_type_descriptor )
					int alt47=2;
					int LA47_0 = input.LA(1);
					if ( (LA47_0==VOID_TYPE) ) {
						alt47=1;
					}
					else if ( (LA47_0==ARRAY_TYPE_PREFIX||LA47_0==CLASS_DESCRIPTOR||LA47_0==PRIMITIVE_TYPE) ) {
						alt47=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 47, 0, input);
						throw nvae;
					}

					switch (alt47) {
						case 1 :
							// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:806:81: VOID_TYPE
							{
							VOID_TYPE255=(Token)match(input,VOID_TYPE,FOLLOW_VOID_TYPE_in_local_directive3800);
							stream_VOID_TYPE.add(VOID_TYPE255);

							}
							break;
						case 2 :
							// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:806:93: nonvoid_type_descriptor
							{
							pushFollow(FOLLOW_nonvoid_type_descriptor_in_local_directive3804);
							nonvoid_type_descriptor256=nonvoid_type_descriptor();
							state._fsp--;

							stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor256.getTree());
							}
							break;

					}

					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:807:31: ( COMMA signature= STRING_LITERAL )?
					int alt48=2;
					int LA48_0 = input.LA(1);
					if ( (LA48_0==COMMA) ) {
						alt48=1;
					}
					switch (alt48) {
						case 1 :
							// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:807:32: COMMA signature= STRING_LITERAL
							{
							COMMA257=(Token)match(input,COMMA,FOLLOW_COMMA_in_local_directive3838);
							stream_COMMA.add(COMMA257);

							signature=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_local_directive3842);
							stream_STRING_LITERAL.add(signature);

							}
							break;

					}

					}
					break;

			}

			// AST REWRITE
			// elements: nonvoid_type_descriptor, signature, REGISTER, NULL_LITERAL, name
			// token labels: signature, name
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleTokenStream stream_signature=new RewriteRuleTokenStream(adaptor,"token signature",signature);
			RewriteRuleTokenStream stream_name=new RewriteRuleTokenStream(adaptor,"token name",name);
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 808:5: -> ^( I_LOCAL[$start, \"I_LOCAL\"] REGISTER ( NULL_LITERAL )? ( $name)? ( nonvoid_type_descriptor )? ( $signature)? )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:808:8: ^( I_LOCAL[$start, \"I_LOCAL\"] REGISTER ( NULL_LITERAL )? ( $name)? ( nonvoid_type_descriptor )? ( $signature)? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_LOCAL, (retval.start), "I_LOCAL"), root_1);
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:808:46: ( NULL_LITERAL )?
				if ( stream_NULL_LITERAL.hasNext() ) {
					adaptor.addChild(root_1, stream_NULL_LITERAL.nextNode());
				}
				stream_NULL_LITERAL.reset();

				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:808:61: ( $name)?
				if ( stream_name.hasNext() ) {
					adaptor.addChild(root_1, stream_name.nextNode());
				}
				stream_name.reset();

				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:808:67: ( nonvoid_type_descriptor )?
				if ( stream_nonvoid_type_descriptor.hasNext() ) {
					adaptor.addChild(root_1, stream_nonvoid_type_descriptor.nextTree());
				}
				stream_nonvoid_type_descriptor.reset();

				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:808:93: ( $signature)?
				if ( stream_signature.hasNext() ) {
					adaptor.addChild(root_1, stream_signature.nextNode());
				}
				stream_signature.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "local_directive"


	public static class end_local_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "end_local_directive"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:810:1: end_local_directive : END_LOCAL_DIRECTIVE REGISTER -> ^( I_END_LOCAL[$start, \"I_END_LOCAL\"] REGISTER ) ;
	public final smaliParser.end_local_directive_return end_local_directive() throws RecognitionException {
		smaliParser.end_local_directive_return retval = new smaliParser.end_local_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token END_LOCAL_DIRECTIVE258=null;
		Token REGISTER259=null;

		CommonTree END_LOCAL_DIRECTIVE258_tree=null;
		CommonTree REGISTER259_tree=null;
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_END_LOCAL_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token END_LOCAL_DIRECTIVE");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:811:3: ( END_LOCAL_DIRECTIVE REGISTER -> ^( I_END_LOCAL[$start, \"I_END_LOCAL\"] REGISTER ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:811:5: END_LOCAL_DIRECTIVE REGISTER
			{
			END_LOCAL_DIRECTIVE258=(Token)match(input,END_LOCAL_DIRECTIVE,FOLLOW_END_LOCAL_DIRECTIVE_in_end_local_directive3884);
			stream_END_LOCAL_DIRECTIVE.add(END_LOCAL_DIRECTIVE258);

			REGISTER259=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_end_local_directive3886);
			stream_REGISTER.add(REGISTER259);

			// AST REWRITE
			// elements: REGISTER
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 812:5: -> ^( I_END_LOCAL[$start, \"I_END_LOCAL\"] REGISTER )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:812:8: ^( I_END_LOCAL[$start, \"I_END_LOCAL\"] REGISTER )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_END_LOCAL, (retval.start), "I_END_LOCAL"), root_1);
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "end_local_directive"


	public static class restart_local_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "restart_local_directive"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:814:1: restart_local_directive : RESTART_LOCAL_DIRECTIVE REGISTER -> ^( I_RESTART_LOCAL[$start, \"I_RESTART_LOCAL\"] REGISTER ) ;
	public final smaliParser.restart_local_directive_return restart_local_directive() throws RecognitionException {
		smaliParser.restart_local_directive_return retval = new smaliParser.restart_local_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token RESTART_LOCAL_DIRECTIVE260=null;
		Token REGISTER261=null;

		CommonTree RESTART_LOCAL_DIRECTIVE260_tree=null;
		CommonTree REGISTER261_tree=null;
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_RESTART_LOCAL_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token RESTART_LOCAL_DIRECTIVE");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:815:3: ( RESTART_LOCAL_DIRECTIVE REGISTER -> ^( I_RESTART_LOCAL[$start, \"I_RESTART_LOCAL\"] REGISTER ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:815:5: RESTART_LOCAL_DIRECTIVE REGISTER
			{
			RESTART_LOCAL_DIRECTIVE260=(Token)match(input,RESTART_LOCAL_DIRECTIVE,FOLLOW_RESTART_LOCAL_DIRECTIVE_in_restart_local_directive3909);
			stream_RESTART_LOCAL_DIRECTIVE.add(RESTART_LOCAL_DIRECTIVE260);

			REGISTER261=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_restart_local_directive3911);
			stream_REGISTER.add(REGISTER261);

			// AST REWRITE
			// elements: REGISTER
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 816:5: -> ^( I_RESTART_LOCAL[$start, \"I_RESTART_LOCAL\"] REGISTER )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:816:8: ^( I_RESTART_LOCAL[$start, \"I_RESTART_LOCAL\"] REGISTER )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_RESTART_LOCAL, (retval.start), "I_RESTART_LOCAL"), root_1);
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "restart_local_directive"


	public static class prologue_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "prologue_directive"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:818:1: prologue_directive : PROLOGUE_DIRECTIVE -> ^( I_PROLOGUE[$start, \"I_PROLOGUE\"] ) ;
	public final smaliParser.prologue_directive_return prologue_directive() throws RecognitionException {
		smaliParser.prologue_directive_return retval = new smaliParser.prologue_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token PROLOGUE_DIRECTIVE262=null;

		CommonTree PROLOGUE_DIRECTIVE262_tree=null;
		RewriteRuleTokenStream stream_PROLOGUE_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token PROLOGUE_DIRECTIVE");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:819:3: ( PROLOGUE_DIRECTIVE -> ^( I_PROLOGUE[$start, \"I_PROLOGUE\"] ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:819:5: PROLOGUE_DIRECTIVE
			{
			PROLOGUE_DIRECTIVE262=(Token)match(input,PROLOGUE_DIRECTIVE,FOLLOW_PROLOGUE_DIRECTIVE_in_prologue_directive3934);
			stream_PROLOGUE_DIRECTIVE.add(PROLOGUE_DIRECTIVE262);

			// AST REWRITE
			// elements:
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 820:5: -> ^( I_PROLOGUE[$start, \"I_PROLOGUE\"] )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:820:8: ^( I_PROLOGUE[$start, \"I_PROLOGUE\"] )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_PROLOGUE, (retval.start), "I_PROLOGUE"), root_1);
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "prologue_directive"


	public static class epilogue_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "epilogue_directive"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:822:1: epilogue_directive : EPILOGUE_DIRECTIVE -> ^( I_EPILOGUE[$start, \"I_EPILOGUE\"] ) ;
	public final smaliParser.epilogue_directive_return epilogue_directive() throws RecognitionException {
		smaliParser.epilogue_directive_return retval = new smaliParser.epilogue_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token EPILOGUE_DIRECTIVE263=null;

		CommonTree EPILOGUE_DIRECTIVE263_tree=null;
		RewriteRuleTokenStream stream_EPILOGUE_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token EPILOGUE_DIRECTIVE");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:823:3: ( EPILOGUE_DIRECTIVE -> ^( I_EPILOGUE[$start, \"I_EPILOGUE\"] ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:823:5: EPILOGUE_DIRECTIVE
			{
			EPILOGUE_DIRECTIVE263=(Token)match(input,EPILOGUE_DIRECTIVE,FOLLOW_EPILOGUE_DIRECTIVE_in_epilogue_directive3955);
			stream_EPILOGUE_DIRECTIVE.add(EPILOGUE_DIRECTIVE263);

			// AST REWRITE
			// elements:
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 824:5: -> ^( I_EPILOGUE[$start, \"I_EPILOGUE\"] )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:824:8: ^( I_EPILOGUE[$start, \"I_EPILOGUE\"] )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_EPILOGUE, (retval.start), "I_EPILOGUE"), root_1);
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "epilogue_directive"


	public static class source_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "source_directive"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:826:1: source_directive : SOURCE_DIRECTIVE ( STRING_LITERAL )? -> ^( I_SOURCE[$start, \"I_SOURCE\"] ( STRING_LITERAL )? ) ;
	public final smaliParser.source_directive_return source_directive() throws RecognitionException {
		smaliParser.source_directive_return retval = new smaliParser.source_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token SOURCE_DIRECTIVE264=null;
		Token STRING_LITERAL265=null;

		CommonTree SOURCE_DIRECTIVE264_tree=null;
		CommonTree STRING_LITERAL265_tree=null;
		RewriteRuleTokenStream stream_SOURCE_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token SOURCE_DIRECTIVE");
		RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:827:3: ( SOURCE_DIRECTIVE ( STRING_LITERAL )? -> ^( I_SOURCE[$start, \"I_SOURCE\"] ( STRING_LITERAL )? ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:827:5: SOURCE_DIRECTIVE ( STRING_LITERAL )?
			{
			SOURCE_DIRECTIVE264=(Token)match(input,SOURCE_DIRECTIVE,FOLLOW_SOURCE_DIRECTIVE_in_source_directive3976);
			stream_SOURCE_DIRECTIVE.add(SOURCE_DIRECTIVE264);

			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:827:22: ( STRING_LITERAL )?
			int alt50=2;
			int LA50_0 = input.LA(1);
			if ( (LA50_0==STRING_LITERAL) ) {
				alt50=1;
			}
			switch (alt50) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:827:22: STRING_LITERAL
					{
					STRING_LITERAL265=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_source_directive3978);
					stream_STRING_LITERAL.add(STRING_LITERAL265);

					}
					break;

			}

			// AST REWRITE
			// elements: STRING_LITERAL
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 828:5: -> ^( I_SOURCE[$start, \"I_SOURCE\"] ( STRING_LITERAL )? )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:828:8: ^( I_SOURCE[$start, \"I_SOURCE\"] ( STRING_LITERAL )? )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_SOURCE, (retval.start), "I_SOURCE"), root_1);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:828:39: ( STRING_LITERAL )?
				if ( stream_STRING_LITERAL.hasNext() ) {
					adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());
				}
				stream_STRING_LITERAL.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "source_directive"


	public static class instruction_format12x_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "instruction_format12x"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:830:1: instruction_format12x : ( INSTRUCTION_FORMAT12x | INSTRUCTION_FORMAT12x_OR_ID -> INSTRUCTION_FORMAT12x[$INSTRUCTION_FORMAT12x_OR_ID] );
	public final smaliParser.instruction_format12x_return instruction_format12x() throws RecognitionException {
		smaliParser.instruction_format12x_return retval = new smaliParser.instruction_format12x_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT12x266=null;
		Token INSTRUCTION_FORMAT12x_OR_ID267=null;

		CommonTree INSTRUCTION_FORMAT12x266_tree=null;
		CommonTree INSTRUCTION_FORMAT12x_OR_ID267_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT12x_OR_ID=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT12x_OR_ID");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:831:3: ( INSTRUCTION_FORMAT12x | INSTRUCTION_FORMAT12x_OR_ID -> INSTRUCTION_FORMAT12x[$INSTRUCTION_FORMAT12x_OR_ID] )
			int alt51=2;
			int LA51_0 = input.LA(1);
			if ( (LA51_0==INSTRUCTION_FORMAT12x) ) {
				alt51=1;
			}
			else if ( (LA51_0==INSTRUCTION_FORMAT12x_OR_ID) ) {
				alt51=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 51, 0, input);
				throw nvae;
			}

			switch (alt51) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:831:5: INSTRUCTION_FORMAT12x
					{
					root_0 = (CommonTree)adaptor.nil();


					INSTRUCTION_FORMAT12x266=(Token)match(input,INSTRUCTION_FORMAT12x,FOLLOW_INSTRUCTION_FORMAT12x_in_instruction_format12x4003);
					INSTRUCTION_FORMAT12x266_tree = (CommonTree)adaptor.create(INSTRUCTION_FORMAT12x266);
					adaptor.addChild(root_0, INSTRUCTION_FORMAT12x266_tree);

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:832:5: INSTRUCTION_FORMAT12x_OR_ID
					{
					INSTRUCTION_FORMAT12x_OR_ID267=(Token)match(input,INSTRUCTION_FORMAT12x_OR_ID,FOLLOW_INSTRUCTION_FORMAT12x_OR_ID_in_instruction_format12x4009);
					stream_INSTRUCTION_FORMAT12x_OR_ID.add(INSTRUCTION_FORMAT12x_OR_ID267);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 832:33: -> INSTRUCTION_FORMAT12x[$INSTRUCTION_FORMAT12x_OR_ID]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(INSTRUCTION_FORMAT12x, INSTRUCTION_FORMAT12x_OR_ID267));
					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "instruction_format12x"


	public static class instruction_format22s_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "instruction_format22s"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:834:1: instruction_format22s : ( INSTRUCTION_FORMAT22s | INSTRUCTION_FORMAT22s_OR_ID -> INSTRUCTION_FORMAT22s[$INSTRUCTION_FORMAT22s_OR_ID] );
	public final smaliParser.instruction_format22s_return instruction_format22s() throws RecognitionException {
		smaliParser.instruction_format22s_return retval = new smaliParser.instruction_format22s_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT22s268=null;
		Token INSTRUCTION_FORMAT22s_OR_ID269=null;

		CommonTree INSTRUCTION_FORMAT22s268_tree=null;
		CommonTree INSTRUCTION_FORMAT22s_OR_ID269_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22s_OR_ID=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22s_OR_ID");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:835:3: ( INSTRUCTION_FORMAT22s | INSTRUCTION_FORMAT22s_OR_ID -> INSTRUCTION_FORMAT22s[$INSTRUCTION_FORMAT22s_OR_ID] )
			int alt52=2;
			int LA52_0 = input.LA(1);
			if ( (LA52_0==INSTRUCTION_FORMAT22s) ) {
				alt52=1;
			}
			else if ( (LA52_0==INSTRUCTION_FORMAT22s_OR_ID) ) {
				alt52=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 52, 0, input);
				throw nvae;
			}

			switch (alt52) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:835:5: INSTRUCTION_FORMAT22s
					{
					root_0 = (CommonTree)adaptor.nil();


					INSTRUCTION_FORMAT22s268=(Token)match(input,INSTRUCTION_FORMAT22s,FOLLOW_INSTRUCTION_FORMAT22s_in_instruction_format22s4024);
					INSTRUCTION_FORMAT22s268_tree = (CommonTree)adaptor.create(INSTRUCTION_FORMAT22s268);
					adaptor.addChild(root_0, INSTRUCTION_FORMAT22s268_tree);

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:836:5: INSTRUCTION_FORMAT22s_OR_ID
					{
					INSTRUCTION_FORMAT22s_OR_ID269=(Token)match(input,INSTRUCTION_FORMAT22s_OR_ID,FOLLOW_INSTRUCTION_FORMAT22s_OR_ID_in_instruction_format22s4030);
					stream_INSTRUCTION_FORMAT22s_OR_ID.add(INSTRUCTION_FORMAT22s_OR_ID269);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 836:33: -> INSTRUCTION_FORMAT22s[$INSTRUCTION_FORMAT22s_OR_ID]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(INSTRUCTION_FORMAT22s, INSTRUCTION_FORMAT22s_OR_ID269));
					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "instruction_format22s"


	public static class instruction_format31i_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "instruction_format31i"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:838:1: instruction_format31i : ( INSTRUCTION_FORMAT31i | INSTRUCTION_FORMAT31i_OR_ID -> INSTRUCTION_FORMAT31i[$INSTRUCTION_FORMAT31i_OR_ID] );
	public final smaliParser.instruction_format31i_return instruction_format31i() throws RecognitionException {
		smaliParser.instruction_format31i_return retval = new smaliParser.instruction_format31i_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT31i270=null;
		Token INSTRUCTION_FORMAT31i_OR_ID271=null;

		CommonTree INSTRUCTION_FORMAT31i270_tree=null;
		CommonTree INSTRUCTION_FORMAT31i_OR_ID271_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT31i_OR_ID=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT31i_OR_ID");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:839:3: ( INSTRUCTION_FORMAT31i | INSTRUCTION_FORMAT31i_OR_ID -> INSTRUCTION_FORMAT31i[$INSTRUCTION_FORMAT31i_OR_ID] )
			int alt53=2;
			int LA53_0 = input.LA(1);
			if ( (LA53_0==INSTRUCTION_FORMAT31i) ) {
				alt53=1;
			}
			else if ( (LA53_0==INSTRUCTION_FORMAT31i_OR_ID) ) {
				alt53=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 53, 0, input);
				throw nvae;
			}

			switch (alt53) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:839:5: INSTRUCTION_FORMAT31i
					{
					root_0 = (CommonTree)adaptor.nil();


					INSTRUCTION_FORMAT31i270=(Token)match(input,INSTRUCTION_FORMAT31i,FOLLOW_INSTRUCTION_FORMAT31i_in_instruction_format31i4045);
					INSTRUCTION_FORMAT31i270_tree = (CommonTree)adaptor.create(INSTRUCTION_FORMAT31i270);
					adaptor.addChild(root_0, INSTRUCTION_FORMAT31i270_tree);

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:840:5: INSTRUCTION_FORMAT31i_OR_ID
					{
					INSTRUCTION_FORMAT31i_OR_ID271=(Token)match(input,INSTRUCTION_FORMAT31i_OR_ID,FOLLOW_INSTRUCTION_FORMAT31i_OR_ID_in_instruction_format31i4051);
					stream_INSTRUCTION_FORMAT31i_OR_ID.add(INSTRUCTION_FORMAT31i_OR_ID271);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 840:33: -> INSTRUCTION_FORMAT31i[$INSTRUCTION_FORMAT31i_OR_ID]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(INSTRUCTION_FORMAT31i, INSTRUCTION_FORMAT31i_OR_ID271));
					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "instruction_format31i"


	public static class instruction_format35c_method_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "instruction_format35c_method"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:842:1: instruction_format35c_method : ( INSTRUCTION_FORMAT35c_METHOD | INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE -> INSTRUCTION_FORMAT35c_METHOD[$INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE] );
	public final smaliParser.instruction_format35c_method_return instruction_format35c_method() throws RecognitionException {
		smaliParser.instruction_format35c_method_return retval = new smaliParser.instruction_format35c_method_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT35c_METHOD272=null;
		Token INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE273=null;

		CommonTree INSTRUCTION_FORMAT35c_METHOD272_tree=null;
		CommonTree INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE273_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:843:3: ( INSTRUCTION_FORMAT35c_METHOD | INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE -> INSTRUCTION_FORMAT35c_METHOD[$INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE] )
			int alt54=2;
			int LA54_0 = input.LA(1);
			if ( (LA54_0==INSTRUCTION_FORMAT35c_METHOD) ) {
				alt54=1;
			}
			else if ( (LA54_0==INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE) ) {
				alt54=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 54, 0, input);
				throw nvae;
			}

			switch (alt54) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:843:5: INSTRUCTION_FORMAT35c_METHOD
					{
					root_0 = (CommonTree)adaptor.nil();


					INSTRUCTION_FORMAT35c_METHOD272=(Token)match(input,INSTRUCTION_FORMAT35c_METHOD,FOLLOW_INSTRUCTION_FORMAT35c_METHOD_in_instruction_format35c_method4068);
					INSTRUCTION_FORMAT35c_METHOD272_tree = (CommonTree)adaptor.create(INSTRUCTION_FORMAT35c_METHOD272);
					adaptor.addChild(root_0, INSTRUCTION_FORMAT35c_METHOD272_tree);

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:844:5: INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE
					{
					INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE273=(Token)match(input,INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE,FOLLOW_INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE_in_instruction_format35c_method4074);
					stream_INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE.add(INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE273);

					// AST REWRITE
					// elements:
					// token labels:
					// rule labels: retval
					// token list labels:
					// rule list labels:
					// wildcard labels:
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (CommonTree)adaptor.nil();
					// 844:56: -> INSTRUCTION_FORMAT35c_METHOD[$INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE]
					{
						adaptor.addChild(root_0, (CommonTree)adaptor.create(INSTRUCTION_FORMAT35c_METHOD, INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE273));
					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "instruction_format35c_method"


	public static class instruction_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "instruction"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:846:1: instruction : ( insn_format10t | insn_format10x | insn_format10x_odex | insn_format11n | insn_format11x | insn_format12x | insn_format20bc | insn_format20t | insn_format21c_field | insn_format21c_field_odex | insn_format21c_method_handle | insn_format21c_method_type | insn_format21c_string | insn_format21c_type | insn_format21ih | insn_format21lh | insn_format21s | insn_format21t | insn_format22b | insn_format22c_field | insn_format22c_field_odex | insn_format22c_type | insn_format22cs_field | insn_format22s | insn_format22t | insn_format22x | insn_format23x | insn_format30t | insn_format31c | insn_format31i | insn_format31t | insn_format32x | insn_format35c_call_site | insn_format35c_method | insn_format35c_type | insn_format35c_method_odex | insn_format35mi_method | insn_format35ms_method | insn_format3rc_call_site | insn_format3rc_method | insn_format3rc_method_odex | insn_format3rc_type | insn_format3rmi_method | insn_format3rms_method | insn_format45cc_method | insn_format4rcc_method | insn_format51l | insn_array_data_directive | insn_packed_switch_directive | insn_sparse_switch_directive );
	public final smaliParser.instruction_return instruction() throws RecognitionException {
		smaliParser.instruction_return retval = new smaliParser.instruction_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		ParserRuleReturnScope insn_format10t274 =null;
		ParserRuleReturnScope insn_format10x275 =null;
		ParserRuleReturnScope insn_format10x_odex276 =null;
		ParserRuleReturnScope insn_format11n277 =null;
		ParserRuleReturnScope insn_format11x278 =null;
		ParserRuleReturnScope insn_format12x279 =null;
		ParserRuleReturnScope insn_format20bc280 =null;
		ParserRuleReturnScope insn_format20t281 =null;
		ParserRuleReturnScope insn_format21c_field282 =null;
		ParserRuleReturnScope insn_format21c_field_odex283 =null;
		ParserRuleReturnScope insn_format21c_method_handle284 =null;
		ParserRuleReturnScope insn_format21c_method_type285 =null;
		ParserRuleReturnScope insn_format21c_string286 =null;
		ParserRuleReturnScope insn_format21c_type287 =null;
		ParserRuleReturnScope insn_format21ih288 =null;
		ParserRuleReturnScope insn_format21lh289 =null;
		ParserRuleReturnScope insn_format21s290 =null;
		ParserRuleReturnScope insn_format21t291 =null;
		ParserRuleReturnScope insn_format22b292 =null;
		ParserRuleReturnScope insn_format22c_field293 =null;
		ParserRuleReturnScope insn_format22c_field_odex294 =null;
		ParserRuleReturnScope insn_format22c_type295 =null;
		ParserRuleReturnScope insn_format22cs_field296 =null;
		ParserRuleReturnScope insn_format22s297 =null;
		ParserRuleReturnScope insn_format22t298 =null;
		ParserRuleReturnScope insn_format22x299 =null;
		ParserRuleReturnScope insn_format23x300 =null;
		ParserRuleReturnScope insn_format30t301 =null;
		ParserRuleReturnScope insn_format31c302 =null;
		ParserRuleReturnScope insn_format31i303 =null;
		ParserRuleReturnScope insn_format31t304 =null;
		ParserRuleReturnScope insn_format32x305 =null;
		ParserRuleReturnScope insn_format35c_call_site306 =null;
		ParserRuleReturnScope insn_format35c_method307 =null;
		ParserRuleReturnScope insn_format35c_type308 =null;
		ParserRuleReturnScope insn_format35c_method_odex309 =null;
		ParserRuleReturnScope insn_format35mi_method310 =null;
		ParserRuleReturnScope insn_format35ms_method311 =null;
		ParserRuleReturnScope insn_format3rc_call_site312 =null;
		ParserRuleReturnScope insn_format3rc_method313 =null;
		ParserRuleReturnScope insn_format3rc_method_odex314 =null;
		ParserRuleReturnScope insn_format3rc_type315 =null;
		ParserRuleReturnScope insn_format3rmi_method316 =null;
		ParserRuleReturnScope insn_format3rms_method317 =null;
		ParserRuleReturnScope insn_format45cc_method318 =null;
		ParserRuleReturnScope insn_format4rcc_method319 =null;
		ParserRuleReturnScope insn_format51l320 =null;
		ParserRuleReturnScope insn_array_data_directive321 =null;
		ParserRuleReturnScope insn_packed_switch_directive322 =null;
		ParserRuleReturnScope insn_sparse_switch_directive323 =null;


		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:847:3: ( insn_format10t | insn_format10x | insn_format10x_odex | insn_format11n | insn_format11x | insn_format12x | insn_format20bc | insn_format20t | insn_format21c_field | insn_format21c_field_odex | insn_format21c_method_handle | insn_format21c_method_type | insn_format21c_string | insn_format21c_type | insn_format21ih | insn_format21lh | insn_format21s | insn_format21t | insn_format22b | insn_format22c_field | insn_format22c_field_odex | insn_format22c_type | insn_format22cs_field | insn_format22s | insn_format22t | insn_format22x | insn_format23x | insn_format30t | insn_format31c | insn_format31i | insn_format31t | insn_format32x | insn_format35c_call_site | insn_format35c_method | insn_format35c_type | insn_format35c_method_odex | insn_format35mi_method | insn_format35ms_method | insn_format3rc_call_site | insn_format3rc_method | insn_format3rc_method_odex | insn_format3rc_type | insn_format3rmi_method | insn_format3rms_method | insn_format45cc_method | insn_format4rcc_method | insn_format51l | insn_array_data_directive | insn_packed_switch_directive | insn_sparse_switch_directive )
			int alt55=50;
			switch ( input.LA(1) ) {
			case INSTRUCTION_FORMAT10t:
				{
				alt55=1;
				}
				break;
			case INSTRUCTION_FORMAT10x:
				{
				alt55=2;
				}
				break;
			case INSTRUCTION_FORMAT10x_ODEX:
				{
				alt55=3;
				}
				break;
			case INSTRUCTION_FORMAT11n:
				{
				alt55=4;
				}
				break;
			case INSTRUCTION_FORMAT11x:
				{
				alt55=5;
				}
				break;
			case INSTRUCTION_FORMAT12x:
			case INSTRUCTION_FORMAT12x_OR_ID:
				{
				alt55=6;
				}
				break;
			case INSTRUCTION_FORMAT20bc:
				{
				alt55=7;
				}
				break;
			case INSTRUCTION_FORMAT20t:
				{
				alt55=8;
				}
				break;
			case INSTRUCTION_FORMAT21c_FIELD:
				{
				alt55=9;
				}
				break;
			case INSTRUCTION_FORMAT21c_FIELD_ODEX:
				{
				alt55=10;
				}
				break;
			case INSTRUCTION_FORMAT21c_METHOD_HANDLE:
				{
				alt55=11;
				}
				break;
			case INSTRUCTION_FORMAT21c_METHOD_TYPE:
				{
				alt55=12;
				}
				break;
			case INSTRUCTION_FORMAT21c_STRING:
				{
				alt55=13;
				}
				break;
			case INSTRUCTION_FORMAT21c_TYPE:
				{
				alt55=14;
				}
				break;
			case INSTRUCTION_FORMAT21ih:
				{
				alt55=15;
				}
				break;
			case INSTRUCTION_FORMAT21lh:
				{
				alt55=16;
				}
				break;
			case INSTRUCTION_FORMAT21s:
				{
				alt55=17;
				}
				break;
			case INSTRUCTION_FORMAT21t:
				{
				alt55=18;
				}
				break;
			case INSTRUCTION_FORMAT22b:
				{
				alt55=19;
				}
				break;
			case INSTRUCTION_FORMAT22c_FIELD:
				{
				alt55=20;
				}
				break;
			case INSTRUCTION_FORMAT22c_FIELD_ODEX:
				{
				alt55=21;
				}
				break;
			case INSTRUCTION_FORMAT22c_TYPE:
				{
				alt55=22;
				}
				break;
			case INSTRUCTION_FORMAT22cs_FIELD:
				{
				alt55=23;
				}
				break;
			case INSTRUCTION_FORMAT22s:
			case INSTRUCTION_FORMAT22s_OR_ID:
				{
				alt55=24;
				}
				break;
			case INSTRUCTION_FORMAT22t:
				{
				alt55=25;
				}
				break;
			case INSTRUCTION_FORMAT22x:
				{
				alt55=26;
				}
				break;
			case INSTRUCTION_FORMAT23x:
				{
				alt55=27;
				}
				break;
			case INSTRUCTION_FORMAT30t:
				{
				alt55=28;
				}
				break;
			case INSTRUCTION_FORMAT31c:
				{
				alt55=29;
				}
				break;
			case INSTRUCTION_FORMAT31i:
			case INSTRUCTION_FORMAT31i_OR_ID:
				{
				alt55=30;
				}
				break;
			case INSTRUCTION_FORMAT31t:
				{
				alt55=31;
				}
				break;
			case INSTRUCTION_FORMAT32x:
				{
				alt55=32;
				}
				break;
			case INSTRUCTION_FORMAT35c_CALL_SITE:
				{
				alt55=33;
				}
				break;
			case INSTRUCTION_FORMAT35c_METHOD:
			case INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE:
				{
				alt55=34;
				}
				break;
			case INSTRUCTION_FORMAT35c_TYPE:
				{
				alt55=35;
				}
				break;
			case INSTRUCTION_FORMAT35c_METHOD_ODEX:
				{
				alt55=36;
				}
				break;
			case INSTRUCTION_FORMAT35mi_METHOD:
				{
				alt55=37;
				}
				break;
			case INSTRUCTION_FORMAT35ms_METHOD:
				{
				alt55=38;
				}
				break;
			case INSTRUCTION_FORMAT3rc_CALL_SITE:
				{
				alt55=39;
				}
				break;
			case INSTRUCTION_FORMAT3rc_METHOD:
				{
				alt55=40;
				}
				break;
			case INSTRUCTION_FORMAT3rc_METHOD_ODEX:
				{
				alt55=41;
				}
				break;
			case INSTRUCTION_FORMAT3rc_TYPE:
				{
				alt55=42;
				}
				break;
			case INSTRUCTION_FORMAT3rmi_METHOD:
				{
				alt55=43;
				}
				break;
			case INSTRUCTION_FORMAT3rms_METHOD:
				{
				alt55=44;
				}
				break;
			case INSTRUCTION_FORMAT45cc_METHOD:
				{
				alt55=45;
				}
				break;
			case INSTRUCTION_FORMAT4rcc_METHOD:
				{
				alt55=46;
				}
				break;
			case INSTRUCTION_FORMAT51l:
				{
				alt55=47;
				}
				break;
			case ARRAY_DATA_DIRECTIVE:
				{
				alt55=48;
				}
				break;
			case PACKED_SWITCH_DIRECTIVE:
				{
				alt55=49;
				}
				break;
			case SPARSE_SWITCH_DIRECTIVE:
				{
				alt55=50;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 55, 0, input);
				throw nvae;
			}
			switch (alt55) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:847:5: insn_format10t
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format10t_in_instruction4089);
					insn_format10t274=insn_format10t();
					state._fsp--;

					adaptor.addChild(root_0, insn_format10t274.getTree());

					}
					break;
				case 2 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:848:5: insn_format10x
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format10x_in_instruction4095);
					insn_format10x275=insn_format10x();
					state._fsp--;

					adaptor.addChild(root_0, insn_format10x275.getTree());

					}
					break;
				case 3 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:849:5: insn_format10x_odex
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format10x_odex_in_instruction4101);
					insn_format10x_odex276=insn_format10x_odex();
					state._fsp--;

					adaptor.addChild(root_0, insn_format10x_odex276.getTree());

					}
					break;
				case 4 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:850:5: insn_format11n
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format11n_in_instruction4107);
					insn_format11n277=insn_format11n();
					state._fsp--;

					adaptor.addChild(root_0, insn_format11n277.getTree());

					}
					break;
				case 5 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:851:5: insn_format11x
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format11x_in_instruction4113);
					insn_format11x278=insn_format11x();
					state._fsp--;

					adaptor.addChild(root_0, insn_format11x278.getTree());

					}
					break;
				case 6 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:852:5: insn_format12x
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format12x_in_instruction4119);
					insn_format12x279=insn_format12x();
					state._fsp--;

					adaptor.addChild(root_0, insn_format12x279.getTree());

					}
					break;
				case 7 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:853:5: insn_format20bc
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format20bc_in_instruction4125);
					insn_format20bc280=insn_format20bc();
					state._fsp--;

					adaptor.addChild(root_0, insn_format20bc280.getTree());

					}
					break;
				case 8 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:854:5: insn_format20t
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format20t_in_instruction4131);
					insn_format20t281=insn_format20t();
					state._fsp--;

					adaptor.addChild(root_0, insn_format20t281.getTree());

					}
					break;
				case 9 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:855:5: insn_format21c_field
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format21c_field_in_instruction4137);
					insn_format21c_field282=insn_format21c_field();
					state._fsp--;

					adaptor.addChild(root_0, insn_format21c_field282.getTree());

					}
					break;
				case 10 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:856:5: insn_format21c_field_odex
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format21c_field_odex_in_instruction4143);
					insn_format21c_field_odex283=insn_format21c_field_odex();
					state._fsp--;

					adaptor.addChild(root_0, insn_format21c_field_odex283.getTree());

					}
					break;
				case 11 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:857:5: insn_format21c_method_handle
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format21c_method_handle_in_instruction4149);
					insn_format21c_method_handle284=insn_format21c_method_handle();
					state._fsp--;

					adaptor.addChild(root_0, insn_format21c_method_handle284.getTree());

					}
					break;
				case 12 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:858:5: insn_format21c_method_type
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format21c_method_type_in_instruction4155);
					insn_format21c_method_type285=insn_format21c_method_type();
					state._fsp--;

					adaptor.addChild(root_0, insn_format21c_method_type285.getTree());

					}
					break;
				case 13 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:859:5: insn_format21c_string
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format21c_string_in_instruction4161);
					insn_format21c_string286=insn_format21c_string();
					state._fsp--;

					adaptor.addChild(root_0, insn_format21c_string286.getTree());

					}
					break;
				case 14 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:860:5: insn_format21c_type
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format21c_type_in_instruction4167);
					insn_format21c_type287=insn_format21c_type();
					state._fsp--;

					adaptor.addChild(root_0, insn_format21c_type287.getTree());

					}
					break;
				case 15 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:861:5: insn_format21ih
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format21ih_in_instruction4173);
					insn_format21ih288=insn_format21ih();
					state._fsp--;

					adaptor.addChild(root_0, insn_format21ih288.getTree());

					}
					break;
				case 16 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:862:5: insn_format21lh
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format21lh_in_instruction4179);
					insn_format21lh289=insn_format21lh();
					state._fsp--;

					adaptor.addChild(root_0, insn_format21lh289.getTree());

					}
					break;
				case 17 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:863:5: insn_format21s
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format21s_in_instruction4185);
					insn_format21s290=insn_format21s();
					state._fsp--;

					adaptor.addChild(root_0, insn_format21s290.getTree());

					}
					break;
				case 18 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:864:5: insn_format21t
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format21t_in_instruction4191);
					insn_format21t291=insn_format21t();
					state._fsp--;

					adaptor.addChild(root_0, insn_format21t291.getTree());

					}
					break;
				case 19 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:865:5: insn_format22b
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format22b_in_instruction4197);
					insn_format22b292=insn_format22b();
					state._fsp--;

					adaptor.addChild(root_0, insn_format22b292.getTree());

					}
					break;
				case 20 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:866:5: insn_format22c_field
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format22c_field_in_instruction4203);
					insn_format22c_field293=insn_format22c_field();
					state._fsp--;

					adaptor.addChild(root_0, insn_format22c_field293.getTree());

					}
					break;
				case 21 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:867:5: insn_format22c_field_odex
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format22c_field_odex_in_instruction4209);
					insn_format22c_field_odex294=insn_format22c_field_odex();
					state._fsp--;

					adaptor.addChild(root_0, insn_format22c_field_odex294.getTree());

					}
					break;
				case 22 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:868:5: insn_format22c_type
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format22c_type_in_instruction4215);
					insn_format22c_type295=insn_format22c_type();
					state._fsp--;

					adaptor.addChild(root_0, insn_format22c_type295.getTree());

					}
					break;
				case 23 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:869:5: insn_format22cs_field
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format22cs_field_in_instruction4221);
					insn_format22cs_field296=insn_format22cs_field();
					state._fsp--;

					adaptor.addChild(root_0, insn_format22cs_field296.getTree());

					}
					break;
				case 24 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:870:5: insn_format22s
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format22s_in_instruction4227);
					insn_format22s297=insn_format22s();
					state._fsp--;

					adaptor.addChild(root_0, insn_format22s297.getTree());

					}
					break;
				case 25 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:871:5: insn_format22t
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format22t_in_instruction4233);
					insn_format22t298=insn_format22t();
					state._fsp--;

					adaptor.addChild(root_0, insn_format22t298.getTree());

					}
					break;
				case 26 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:872:5: insn_format22x
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format22x_in_instruction4239);
					insn_format22x299=insn_format22x();
					state._fsp--;

					adaptor.addChild(root_0, insn_format22x299.getTree());

					}
					break;
				case 27 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:873:5: insn_format23x
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format23x_in_instruction4245);
					insn_format23x300=insn_format23x();
					state._fsp--;

					adaptor.addChild(root_0, insn_format23x300.getTree());

					}
					break;
				case 28 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:874:5: insn_format30t
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format30t_in_instruction4251);
					insn_format30t301=insn_format30t();
					state._fsp--;

					adaptor.addChild(root_0, insn_format30t301.getTree());

					}
					break;
				case 29 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:875:5: insn_format31c
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format31c_in_instruction4257);
					insn_format31c302=insn_format31c();
					state._fsp--;

					adaptor.addChild(root_0, insn_format31c302.getTree());

					}
					break;
				case 30 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:876:5: insn_format31i
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format31i_in_instruction4263);
					insn_format31i303=insn_format31i();
					state._fsp--;

					adaptor.addChild(root_0, insn_format31i303.getTree());

					}
					break;
				case 31 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:877:5: insn_format31t
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format31t_in_instruction4269);
					insn_format31t304=insn_format31t();
					state._fsp--;

					adaptor.addChild(root_0, insn_format31t304.getTree());

					}
					break;
				case 32 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:878:5: insn_format32x
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format32x_in_instruction4275);
					insn_format32x305=insn_format32x();
					state._fsp--;

					adaptor.addChild(root_0, insn_format32x305.getTree());

					}
					break;
				case 33 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:879:5: insn_format35c_call_site
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format35c_call_site_in_instruction4281);
					insn_format35c_call_site306=insn_format35c_call_site();
					state._fsp--;

					adaptor.addChild(root_0, insn_format35c_call_site306.getTree());

					}
					break;
				case 34 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:880:5: insn_format35c_method
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format35c_method_in_instruction4287);
					insn_format35c_method307=insn_format35c_method();
					state._fsp--;

					adaptor.addChild(root_0, insn_format35c_method307.getTree());

					}
					break;
				case 35 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:881:5: insn_format35c_type
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format35c_type_in_instruction4293);
					insn_format35c_type308=insn_format35c_type();
					state._fsp--;

					adaptor.addChild(root_0, insn_format35c_type308.getTree());

					}
					break;
				case 36 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:882:5: insn_format35c_method_odex
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format35c_method_odex_in_instruction4299);
					insn_format35c_method_odex309=insn_format35c_method_odex();
					state._fsp--;

					adaptor.addChild(root_0, insn_format35c_method_odex309.getTree());

					}
					break;
				case 37 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:883:5: insn_format35mi_method
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format35mi_method_in_instruction4305);
					insn_format35mi_method310=insn_format35mi_method();
					state._fsp--;

					adaptor.addChild(root_0, insn_format35mi_method310.getTree());

					}
					break;
				case 38 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:884:5: insn_format35ms_method
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format35ms_method_in_instruction4311);
					insn_format35ms_method311=insn_format35ms_method();
					state._fsp--;

					adaptor.addChild(root_0, insn_format35ms_method311.getTree());

					}
					break;
				case 39 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:885:5: insn_format3rc_call_site
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format3rc_call_site_in_instruction4317);
					insn_format3rc_call_site312=insn_format3rc_call_site();
					state._fsp--;

					adaptor.addChild(root_0, insn_format3rc_call_site312.getTree());

					}
					break;
				case 40 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:886:5: insn_format3rc_method
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format3rc_method_in_instruction4323);
					insn_format3rc_method313=insn_format3rc_method();
					state._fsp--;

					adaptor.addChild(root_0, insn_format3rc_method313.getTree());

					}
					break;
				case 41 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:887:5: insn_format3rc_method_odex
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format3rc_method_odex_in_instruction4329);
					insn_format3rc_method_odex314=insn_format3rc_method_odex();
					state._fsp--;

					adaptor.addChild(root_0, insn_format3rc_method_odex314.getTree());

					}
					break;
				case 42 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:888:5: insn_format3rc_type
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format3rc_type_in_instruction4335);
					insn_format3rc_type315=insn_format3rc_type();
					state._fsp--;

					adaptor.addChild(root_0, insn_format3rc_type315.getTree());

					}
					break;
				case 43 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:889:5: insn_format3rmi_method
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format3rmi_method_in_instruction4341);
					insn_format3rmi_method316=insn_format3rmi_method();
					state._fsp--;

					adaptor.addChild(root_0, insn_format3rmi_method316.getTree());

					}
					break;
				case 44 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:890:5: insn_format3rms_method
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format3rms_method_in_instruction4347);
					insn_format3rms_method317=insn_format3rms_method();
					state._fsp--;

					adaptor.addChild(root_0, insn_format3rms_method317.getTree());

					}
					break;
				case 45 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:891:5: insn_format45cc_method
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format45cc_method_in_instruction4353);
					insn_format45cc_method318=insn_format45cc_method();
					state._fsp--;

					adaptor.addChild(root_0, insn_format45cc_method318.getTree());

					}
					break;
				case 46 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:892:5: insn_format4rcc_method
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format4rcc_method_in_instruction4359);
					insn_format4rcc_method319=insn_format4rcc_method();
					state._fsp--;

					adaptor.addChild(root_0, insn_format4rcc_method319.getTree());

					}
					break;
				case 47 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:893:5: insn_format51l
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_format51l_in_instruction4365);
					insn_format51l320=insn_format51l();
					state._fsp--;

					adaptor.addChild(root_0, insn_format51l320.getTree());

					}
					break;
				case 48 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:894:5: insn_array_data_directive
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_array_data_directive_in_instruction4371);
					insn_array_data_directive321=insn_array_data_directive();
					state._fsp--;

					adaptor.addChild(root_0, insn_array_data_directive321.getTree());

					}
					break;
				case 49 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:895:5: insn_packed_switch_directive
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_packed_switch_directive_in_instruction4377);
					insn_packed_switch_directive322=insn_packed_switch_directive();
					state._fsp--;

					adaptor.addChild(root_0, insn_packed_switch_directive322.getTree());

					}
					break;
				case 50 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:896:5: insn_sparse_switch_directive
					{
					root_0 = (CommonTree)adaptor.nil();


					pushFollow(FOLLOW_insn_sparse_switch_directive_in_instruction4383);
					insn_sparse_switch_directive323=insn_sparse_switch_directive();
					state._fsp--;

					adaptor.addChild(root_0, insn_sparse_switch_directive323.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "instruction"


	public static class insn_format10t_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format10t"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:898:1: insn_format10t : INSTRUCTION_FORMAT10t label_ref -> ^( I_STATEMENT_FORMAT10t[$start, \"I_STATEMENT_FORMAT10t\"] INSTRUCTION_FORMAT10t label_ref ) ;
	public final smaliParser.insn_format10t_return insn_format10t() throws RecognitionException {
		smaliParser.insn_format10t_return retval = new smaliParser.insn_format10t_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT10t324=null;
		ParserRuleReturnScope label_ref325 =null;

		CommonTree INSTRUCTION_FORMAT10t324_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT10t=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT10t");
		RewriteRuleSubtreeStream stream_label_ref=new RewriteRuleSubtreeStream(adaptor,"rule label_ref");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:899:3: ( INSTRUCTION_FORMAT10t label_ref -> ^( I_STATEMENT_FORMAT10t[$start, \"I_STATEMENT_FORMAT10t\"] INSTRUCTION_FORMAT10t label_ref ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:901:5: INSTRUCTION_FORMAT10t label_ref
			{
			INSTRUCTION_FORMAT10t324=(Token)match(input,INSTRUCTION_FORMAT10t,FOLLOW_INSTRUCTION_FORMAT10t_in_insn_format10t4403);
			stream_INSTRUCTION_FORMAT10t.add(INSTRUCTION_FORMAT10t324);

			pushFollow(FOLLOW_label_ref_in_insn_format10t4405);
			label_ref325=label_ref();
			state._fsp--;

			stream_label_ref.add(label_ref325.getTree());
			// AST REWRITE
			// elements: label_ref, INSTRUCTION_FORMAT10t
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 902:5: -> ^( I_STATEMENT_FORMAT10t[$start, \"I_STATEMENT_FORMAT10t\"] INSTRUCTION_FORMAT10t label_ref )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:902:8: ^( I_STATEMENT_FORMAT10t[$start, \"I_STATEMENT_FORMAT10t\"] INSTRUCTION_FORMAT10t label_ref )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT10t, (retval.start), "I_STATEMENT_FORMAT10t"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT10t.nextNode());
				adaptor.addChild(root_1, stream_label_ref.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format10t"


	public static class insn_format10x_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format10x"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:904:1: insn_format10x : INSTRUCTION_FORMAT10x -> ^( I_STATEMENT_FORMAT10x[$start, \"I_STATEMENT_FORMAT10x\"] INSTRUCTION_FORMAT10x ) ;
	public final smaliParser.insn_format10x_return insn_format10x() throws RecognitionException {
		smaliParser.insn_format10x_return retval = new smaliParser.insn_format10x_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT10x326=null;

		CommonTree INSTRUCTION_FORMAT10x326_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT10x=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT10x");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:905:3: ( INSTRUCTION_FORMAT10x -> ^( I_STATEMENT_FORMAT10x[$start, \"I_STATEMENT_FORMAT10x\"] INSTRUCTION_FORMAT10x ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:906:5: INSTRUCTION_FORMAT10x
			{
			INSTRUCTION_FORMAT10x326=(Token)match(input,INSTRUCTION_FORMAT10x,FOLLOW_INSTRUCTION_FORMAT10x_in_insn_format10x4435);
			stream_INSTRUCTION_FORMAT10x.add(INSTRUCTION_FORMAT10x326);

			// AST REWRITE
			// elements: INSTRUCTION_FORMAT10x
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 907:5: -> ^( I_STATEMENT_FORMAT10x[$start, \"I_STATEMENT_FORMAT10x\"] INSTRUCTION_FORMAT10x )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:907:8: ^( I_STATEMENT_FORMAT10x[$start, \"I_STATEMENT_FORMAT10x\"] INSTRUCTION_FORMAT10x )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT10x, (retval.start), "I_STATEMENT_FORMAT10x"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT10x.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format10x"


	public static class insn_format10x_odex_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format10x_odex"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:909:1: insn_format10x_odex : INSTRUCTION_FORMAT10x_ODEX ;
	public final smaliParser.insn_format10x_odex_return insn_format10x_odex() throws RecognitionException {
		smaliParser.insn_format10x_odex_return retval = new smaliParser.insn_format10x_odex_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT10x_ODEX327=null;

		CommonTree INSTRUCTION_FORMAT10x_ODEX327_tree=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:910:3: ( INSTRUCTION_FORMAT10x_ODEX )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:911:5: INSTRUCTION_FORMAT10x_ODEX
			{
			root_0 = (CommonTree)adaptor.nil();


			INSTRUCTION_FORMAT10x_ODEX327=(Token)match(input,INSTRUCTION_FORMAT10x_ODEX,FOLLOW_INSTRUCTION_FORMAT10x_ODEX_in_insn_format10x_odex4463);
			INSTRUCTION_FORMAT10x_ODEX327_tree = (CommonTree)adaptor.create(INSTRUCTION_FORMAT10x_ODEX327);
			adaptor.addChild(root_0, INSTRUCTION_FORMAT10x_ODEX327_tree);


			      throwOdexedInstructionException(input, (INSTRUCTION_FORMAT10x_ODEX327!=null?INSTRUCTION_FORMAT10x_ODEX327.getText():null));
			
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format10x_odex"


	public static class insn_format11n_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format11n"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:916:1: insn_format11n : INSTRUCTION_FORMAT11n REGISTER COMMA integral_literal -> ^( I_STATEMENT_FORMAT11n[$start, \"I_STATEMENT_FORMAT11n\"] INSTRUCTION_FORMAT11n REGISTER integral_literal ) ;
	public final smaliParser.insn_format11n_return insn_format11n() throws RecognitionException {
		smaliParser.insn_format11n_return retval = new smaliParser.insn_format11n_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT11n328=null;
		Token REGISTER329=null;
		Token COMMA330=null;
		ParserRuleReturnScope integral_literal331 =null;

		CommonTree INSTRUCTION_FORMAT11n328_tree=null;
		CommonTree REGISTER329_tree=null;
		CommonTree COMMA330_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT11n=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT11n");
		RewriteRuleSubtreeStream stream_integral_literal=new RewriteRuleSubtreeStream(adaptor,"rule integral_literal");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:917:3: ( INSTRUCTION_FORMAT11n REGISTER COMMA integral_literal -> ^( I_STATEMENT_FORMAT11n[$start, \"I_STATEMENT_FORMAT11n\"] INSTRUCTION_FORMAT11n REGISTER integral_literal ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:918:5: INSTRUCTION_FORMAT11n REGISTER COMMA integral_literal
			{
			INSTRUCTION_FORMAT11n328=(Token)match(input,INSTRUCTION_FORMAT11n,FOLLOW_INSTRUCTION_FORMAT11n_in_insn_format11n4484);
			stream_INSTRUCTION_FORMAT11n.add(INSTRUCTION_FORMAT11n328);

			REGISTER329=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format11n4486);
			stream_REGISTER.add(REGISTER329);

			COMMA330=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format11n4488);
			stream_COMMA.add(COMMA330);

			pushFollow(FOLLOW_integral_literal_in_insn_format11n4490);
			integral_literal331=integral_literal();
			state._fsp--;

			stream_integral_literal.add(integral_literal331.getTree());
			// AST REWRITE
			// elements: REGISTER, INSTRUCTION_FORMAT11n, integral_literal
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 919:5: -> ^( I_STATEMENT_FORMAT11n[$start, \"I_STATEMENT_FORMAT11n\"] INSTRUCTION_FORMAT11n REGISTER integral_literal )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:919:8: ^( I_STATEMENT_FORMAT11n[$start, \"I_STATEMENT_FORMAT11n\"] INSTRUCTION_FORMAT11n REGISTER integral_literal )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT11n, (retval.start), "I_STATEMENT_FORMAT11n"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT11n.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_integral_literal.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format11n"


	public static class insn_format11x_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format11x"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:921:1: insn_format11x : INSTRUCTION_FORMAT11x REGISTER -> ^( I_STATEMENT_FORMAT11x[$start, \"I_STATEMENT_FORMAT11x\"] INSTRUCTION_FORMAT11x REGISTER ) ;
	public final smaliParser.insn_format11x_return insn_format11x() throws RecognitionException {
		smaliParser.insn_format11x_return retval = new smaliParser.insn_format11x_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT11x332=null;
		Token REGISTER333=null;

		CommonTree INSTRUCTION_FORMAT11x332_tree=null;
		CommonTree REGISTER333_tree=null;
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT11x=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT11x");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:922:3: ( INSTRUCTION_FORMAT11x REGISTER -> ^( I_STATEMENT_FORMAT11x[$start, \"I_STATEMENT_FORMAT11x\"] INSTRUCTION_FORMAT11x REGISTER ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:923:5: INSTRUCTION_FORMAT11x REGISTER
			{
			INSTRUCTION_FORMAT11x332=(Token)match(input,INSTRUCTION_FORMAT11x,FOLLOW_INSTRUCTION_FORMAT11x_in_insn_format11x4522);
			stream_INSTRUCTION_FORMAT11x.add(INSTRUCTION_FORMAT11x332);

			REGISTER333=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format11x4524);
			stream_REGISTER.add(REGISTER333);

			// AST REWRITE
			// elements: REGISTER, INSTRUCTION_FORMAT11x
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 924:5: -> ^( I_STATEMENT_FORMAT11x[$start, \"I_STATEMENT_FORMAT11x\"] INSTRUCTION_FORMAT11x REGISTER )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:924:8: ^( I_STATEMENT_FORMAT11x[$start, \"I_STATEMENT_FORMAT11x\"] INSTRUCTION_FORMAT11x REGISTER )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT11x, (retval.start), "I_STATEMENT_FORMAT11x"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT11x.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format11x"


	public static class insn_format12x_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format12x"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:926:1: insn_format12x : instruction_format12x REGISTER COMMA REGISTER -> ^( I_STATEMENT_FORMAT12x[$start, \"I_STATEMENT_FORMAT12x\"] instruction_format12x REGISTER REGISTER ) ;
	public final smaliParser.insn_format12x_return insn_format12x() throws RecognitionException {
		smaliParser.insn_format12x_return retval = new smaliParser.insn_format12x_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token REGISTER335=null;
		Token COMMA336=null;
		Token REGISTER337=null;
		ParserRuleReturnScope instruction_format12x334 =null;

		CommonTree REGISTER335_tree=null;
		CommonTree COMMA336_tree=null;
		CommonTree REGISTER337_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleSubtreeStream stream_instruction_format12x=new RewriteRuleSubtreeStream(adaptor,"rule instruction_format12x");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:927:3: ( instruction_format12x REGISTER COMMA REGISTER -> ^( I_STATEMENT_FORMAT12x[$start, \"I_STATEMENT_FORMAT12x\"] instruction_format12x REGISTER REGISTER ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:928:5: instruction_format12x REGISTER COMMA REGISTER
			{
			pushFollow(FOLLOW_instruction_format12x_in_insn_format12x4554);
			instruction_format12x334=instruction_format12x();
			state._fsp--;

			stream_instruction_format12x.add(instruction_format12x334.getTree());
			REGISTER335=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format12x4556);
			stream_REGISTER.add(REGISTER335);

			COMMA336=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format12x4558);
			stream_COMMA.add(COMMA336);

			REGISTER337=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format12x4560);
			stream_REGISTER.add(REGISTER337);

			// AST REWRITE
			// elements: REGISTER, REGISTER, instruction_format12x
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 929:5: -> ^( I_STATEMENT_FORMAT12x[$start, \"I_STATEMENT_FORMAT12x\"] instruction_format12x REGISTER REGISTER )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:929:8: ^( I_STATEMENT_FORMAT12x[$start, \"I_STATEMENT_FORMAT12x\"] instruction_format12x REGISTER REGISTER )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT12x, (retval.start), "I_STATEMENT_FORMAT12x"), root_1);
				adaptor.addChild(root_1, stream_instruction_format12x.nextTree());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format12x"


	public static class insn_format20bc_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format20bc"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:931:1: insn_format20bc : INSTRUCTION_FORMAT20bc VERIFICATION_ERROR_TYPE COMMA verification_error_reference -> ^( I_STATEMENT_FORMAT20bc INSTRUCTION_FORMAT20bc VERIFICATION_ERROR_TYPE verification_error_reference ) ;
	public final smaliParser.insn_format20bc_return insn_format20bc() throws RecognitionException {
		smaliParser.insn_format20bc_return retval = new smaliParser.insn_format20bc_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT20bc338=null;
		Token VERIFICATION_ERROR_TYPE339=null;
		Token COMMA340=null;
		ParserRuleReturnScope verification_error_reference341 =null;

		CommonTree INSTRUCTION_FORMAT20bc338_tree=null;
		CommonTree VERIFICATION_ERROR_TYPE339_tree=null;
		CommonTree COMMA340_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_VERIFICATION_ERROR_TYPE=new RewriteRuleTokenStream(adaptor,"token VERIFICATION_ERROR_TYPE");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT20bc=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT20bc");
		RewriteRuleSubtreeStream stream_verification_error_reference=new RewriteRuleSubtreeStream(adaptor,"rule verification_error_reference");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:932:3: ( INSTRUCTION_FORMAT20bc VERIFICATION_ERROR_TYPE COMMA verification_error_reference -> ^( I_STATEMENT_FORMAT20bc INSTRUCTION_FORMAT20bc VERIFICATION_ERROR_TYPE verification_error_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:933:5: INSTRUCTION_FORMAT20bc VERIFICATION_ERROR_TYPE COMMA verification_error_reference
			{
			INSTRUCTION_FORMAT20bc338=(Token)match(input,INSTRUCTION_FORMAT20bc,FOLLOW_INSTRUCTION_FORMAT20bc_in_insn_format20bc4592);
			stream_INSTRUCTION_FORMAT20bc.add(INSTRUCTION_FORMAT20bc338);

			VERIFICATION_ERROR_TYPE339=(Token)match(input,VERIFICATION_ERROR_TYPE,FOLLOW_VERIFICATION_ERROR_TYPE_in_insn_format20bc4594);
			stream_VERIFICATION_ERROR_TYPE.add(VERIFICATION_ERROR_TYPE339);

			COMMA340=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format20bc4596);
			stream_COMMA.add(COMMA340);

			pushFollow(FOLLOW_verification_error_reference_in_insn_format20bc4598);
			verification_error_reference341=verification_error_reference();
			state._fsp--;

			stream_verification_error_reference.add(verification_error_reference341.getTree());

			      if (!allowOdex || opcodes.getOpcodeByName((INSTRUCTION_FORMAT20bc338!=null?INSTRUCTION_FORMAT20bc338.getText():null)) == null || apiLevel >= 14) {
			        throwOdexedInstructionException(input, (INSTRUCTION_FORMAT20bc338!=null?INSTRUCTION_FORMAT20bc338.getText():null));
			      }
			
			// AST REWRITE
			// elements: INSTRUCTION_FORMAT20bc, verification_error_reference, VERIFICATION_ERROR_TYPE
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 939:5: -> ^( I_STATEMENT_FORMAT20bc INSTRUCTION_FORMAT20bc VERIFICATION_ERROR_TYPE verification_error_reference )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:939:8: ^( I_STATEMENT_FORMAT20bc INSTRUCTION_FORMAT20bc VERIFICATION_ERROR_TYPE verification_error_reference )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT20bc, "I_STATEMENT_FORMAT20bc"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT20bc.nextNode());
				adaptor.addChild(root_1, stream_VERIFICATION_ERROR_TYPE.nextNode());
				adaptor.addChild(root_1, stream_verification_error_reference.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format20bc"


	public static class insn_format20t_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format20t"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:941:1: insn_format20t : INSTRUCTION_FORMAT20t label_ref -> ^( I_STATEMENT_FORMAT20t[$start, \"I_STATEMENT_FORMAT20t\"] INSTRUCTION_FORMAT20t label_ref ) ;
	public final smaliParser.insn_format20t_return insn_format20t() throws RecognitionException {
		smaliParser.insn_format20t_return retval = new smaliParser.insn_format20t_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT20t342=null;
		ParserRuleReturnScope label_ref343 =null;

		CommonTree INSTRUCTION_FORMAT20t342_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT20t=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT20t");
		RewriteRuleSubtreeStream stream_label_ref=new RewriteRuleSubtreeStream(adaptor,"rule label_ref");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:942:3: ( INSTRUCTION_FORMAT20t label_ref -> ^( I_STATEMENT_FORMAT20t[$start, \"I_STATEMENT_FORMAT20t\"] INSTRUCTION_FORMAT20t label_ref ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:943:5: INSTRUCTION_FORMAT20t label_ref
			{
			INSTRUCTION_FORMAT20t342=(Token)match(input,INSTRUCTION_FORMAT20t,FOLLOW_INSTRUCTION_FORMAT20t_in_insn_format20t4635);
			stream_INSTRUCTION_FORMAT20t.add(INSTRUCTION_FORMAT20t342);

			pushFollow(FOLLOW_label_ref_in_insn_format20t4637);
			label_ref343=label_ref();
			state._fsp--;

			stream_label_ref.add(label_ref343.getTree());
			// AST REWRITE
			// elements: label_ref, INSTRUCTION_FORMAT20t
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 944:5: -> ^( I_STATEMENT_FORMAT20t[$start, \"I_STATEMENT_FORMAT20t\"] INSTRUCTION_FORMAT20t label_ref )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:944:8: ^( I_STATEMENT_FORMAT20t[$start, \"I_STATEMENT_FORMAT20t\"] INSTRUCTION_FORMAT20t label_ref )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT20t, (retval.start), "I_STATEMENT_FORMAT20t"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT20t.nextNode());
				adaptor.addChild(root_1, stream_label_ref.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format20t"


	public static class insn_format21c_field_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format21c_field"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:946:1: insn_format21c_field : INSTRUCTION_FORMAT21c_FIELD REGISTER COMMA field_reference -> ^( I_STATEMENT_FORMAT21c_FIELD[$start, \"I_STATEMENT_FORMAT21c_FIELD\"] INSTRUCTION_FORMAT21c_FIELD REGISTER field_reference ) ;
	public final smaliParser.insn_format21c_field_return insn_format21c_field() throws RecognitionException {
		smaliParser.insn_format21c_field_return retval = new smaliParser.insn_format21c_field_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT21c_FIELD344=null;
		Token REGISTER345=null;
		Token COMMA346=null;
		ParserRuleReturnScope field_reference347 =null;

		CommonTree INSTRUCTION_FORMAT21c_FIELD344_tree=null;
		CommonTree REGISTER345_tree=null;
		CommonTree COMMA346_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_FIELD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_FIELD");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleSubtreeStream stream_field_reference=new RewriteRuleSubtreeStream(adaptor,"rule field_reference");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:947:3: ( INSTRUCTION_FORMAT21c_FIELD REGISTER COMMA field_reference -> ^( I_STATEMENT_FORMAT21c_FIELD[$start, \"I_STATEMENT_FORMAT21c_FIELD\"] INSTRUCTION_FORMAT21c_FIELD REGISTER field_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:948:5: INSTRUCTION_FORMAT21c_FIELD REGISTER COMMA field_reference
			{
			INSTRUCTION_FORMAT21c_FIELD344=(Token)match(input,INSTRUCTION_FORMAT21c_FIELD,FOLLOW_INSTRUCTION_FORMAT21c_FIELD_in_insn_format21c_field4667);
			stream_INSTRUCTION_FORMAT21c_FIELD.add(INSTRUCTION_FORMAT21c_FIELD344);

			REGISTER345=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21c_field4669);
			stream_REGISTER.add(REGISTER345);

			COMMA346=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format21c_field4671);
			stream_COMMA.add(COMMA346);

			pushFollow(FOLLOW_field_reference_in_insn_format21c_field4673);
			field_reference347=field_reference();
			state._fsp--;

			stream_field_reference.add(field_reference347.getTree());
			// AST REWRITE
			// elements: REGISTER, field_reference, INSTRUCTION_FORMAT21c_FIELD
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 949:5: -> ^( I_STATEMENT_FORMAT21c_FIELD[$start, \"I_STATEMENT_FORMAT21c_FIELD\"] INSTRUCTION_FORMAT21c_FIELD REGISTER field_reference )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:949:8: ^( I_STATEMENT_FORMAT21c_FIELD[$start, \"I_STATEMENT_FORMAT21c_FIELD\"] INSTRUCTION_FORMAT21c_FIELD REGISTER field_reference )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT21c_FIELD, (retval.start), "I_STATEMENT_FORMAT21c_FIELD"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21c_FIELD.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_field_reference.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format21c_field"


	public static class insn_format21c_field_odex_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format21c_field_odex"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:951:1: insn_format21c_field_odex : INSTRUCTION_FORMAT21c_FIELD_ODEX REGISTER COMMA field_reference -> ^( I_STATEMENT_FORMAT21c_FIELD[$start, \"I_STATEMENT_FORMAT21c_FIELD\"] INSTRUCTION_FORMAT21c_FIELD_ODEX REGISTER field_reference ) ;
	public final smaliParser.insn_format21c_field_odex_return insn_format21c_field_odex() throws RecognitionException {
		smaliParser.insn_format21c_field_odex_return retval = new smaliParser.insn_format21c_field_odex_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT21c_FIELD_ODEX348=null;
		Token REGISTER349=null;
		Token COMMA350=null;
		ParserRuleReturnScope field_reference351 =null;

		CommonTree INSTRUCTION_FORMAT21c_FIELD_ODEX348_tree=null;
		CommonTree REGISTER349_tree=null;
		CommonTree COMMA350_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_FIELD_ODEX=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_FIELD_ODEX");
		RewriteRuleSubtreeStream stream_field_reference=new RewriteRuleSubtreeStream(adaptor,"rule field_reference");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:952:3: ( INSTRUCTION_FORMAT21c_FIELD_ODEX REGISTER COMMA field_reference -> ^( I_STATEMENT_FORMAT21c_FIELD[$start, \"I_STATEMENT_FORMAT21c_FIELD\"] INSTRUCTION_FORMAT21c_FIELD_ODEX REGISTER field_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:953:5: INSTRUCTION_FORMAT21c_FIELD_ODEX REGISTER COMMA field_reference
			{
			INSTRUCTION_FORMAT21c_FIELD_ODEX348=(Token)match(input,INSTRUCTION_FORMAT21c_FIELD_ODEX,FOLLOW_INSTRUCTION_FORMAT21c_FIELD_ODEX_in_insn_format21c_field_odex4705);
			stream_INSTRUCTION_FORMAT21c_FIELD_ODEX.add(INSTRUCTION_FORMAT21c_FIELD_ODEX348);

			REGISTER349=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21c_field_odex4707);
			stream_REGISTER.add(REGISTER349);

			COMMA350=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format21c_field_odex4709);
			stream_COMMA.add(COMMA350);

			pushFollow(FOLLOW_field_reference_in_insn_format21c_field_odex4711);
			field_reference351=field_reference();
			state._fsp--;

			stream_field_reference.add(field_reference351.getTree());

			      if (!allowOdex || opcodes.getOpcodeByName((INSTRUCTION_FORMAT21c_FIELD_ODEX348!=null?INSTRUCTION_FORMAT21c_FIELD_ODEX348.getText():null)) == null || apiLevel >= 14) {
			        throwOdexedInstructionException(input, (INSTRUCTION_FORMAT21c_FIELD_ODEX348!=null?INSTRUCTION_FORMAT21c_FIELD_ODEX348.getText():null));
			      }
			
			// AST REWRITE
			// elements: INSTRUCTION_FORMAT21c_FIELD_ODEX, REGISTER, field_reference
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 959:5: -> ^( I_STATEMENT_FORMAT21c_FIELD[$start, \"I_STATEMENT_FORMAT21c_FIELD\"] INSTRUCTION_FORMAT21c_FIELD_ODEX REGISTER field_reference )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:959:8: ^( I_STATEMENT_FORMAT21c_FIELD[$start, \"I_STATEMENT_FORMAT21c_FIELD\"] INSTRUCTION_FORMAT21c_FIELD_ODEX REGISTER field_reference )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT21c_FIELD, (retval.start), "I_STATEMENT_FORMAT21c_FIELD"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21c_FIELD_ODEX.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_field_reference.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format21c_field_odex"


	public static class insn_format21c_method_handle_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format21c_method_handle"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:961:1: insn_format21c_method_handle : INSTRUCTION_FORMAT21c_METHOD_HANDLE REGISTER COMMA method_handle_reference -> ^( I_STATEMENT_FORMAT21c_METHOD_HANDLE[$start, \"I_STATEMENT_FORMAT21c_METHOD_HANDLE\"] INSTRUCTION_FORMAT21c_METHOD_HANDLE REGISTER method_handle_reference ) ;
	public final smaliParser.insn_format21c_method_handle_return insn_format21c_method_handle() throws RecognitionException {
		smaliParser.insn_format21c_method_handle_return retval = new smaliParser.insn_format21c_method_handle_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT21c_METHOD_HANDLE352=null;
		Token REGISTER353=null;
		Token COMMA354=null;
		ParserRuleReturnScope method_handle_reference355 =null;

		CommonTree INSTRUCTION_FORMAT21c_METHOD_HANDLE352_tree=null;
		CommonTree REGISTER353_tree=null;
		CommonTree COMMA354_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_METHOD_HANDLE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_METHOD_HANDLE");
		RewriteRuleSubtreeStream stream_method_handle_reference=new RewriteRuleSubtreeStream(adaptor,"rule method_handle_reference");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:962:3: ( INSTRUCTION_FORMAT21c_METHOD_HANDLE REGISTER COMMA method_handle_reference -> ^( I_STATEMENT_FORMAT21c_METHOD_HANDLE[$start, \"I_STATEMENT_FORMAT21c_METHOD_HANDLE\"] INSTRUCTION_FORMAT21c_METHOD_HANDLE REGISTER method_handle_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:963:5: INSTRUCTION_FORMAT21c_METHOD_HANDLE REGISTER COMMA method_handle_reference
			{
			INSTRUCTION_FORMAT21c_METHOD_HANDLE352=(Token)match(input,INSTRUCTION_FORMAT21c_METHOD_HANDLE,FOLLOW_INSTRUCTION_FORMAT21c_METHOD_HANDLE_in_insn_format21c_method_handle4749);
			stream_INSTRUCTION_FORMAT21c_METHOD_HANDLE.add(INSTRUCTION_FORMAT21c_METHOD_HANDLE352);

			REGISTER353=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21c_method_handle4751);
			stream_REGISTER.add(REGISTER353);

			COMMA354=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format21c_method_handle4753);
			stream_COMMA.add(COMMA354);

			pushFollow(FOLLOW_method_handle_reference_in_insn_format21c_method_handle4755);
			method_handle_reference355=method_handle_reference();
			state._fsp--;

			stream_method_handle_reference.add(method_handle_reference355.getTree());
			// AST REWRITE
			// elements: REGISTER, INSTRUCTION_FORMAT21c_METHOD_HANDLE, method_handle_reference
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 964:5: -> ^( I_STATEMENT_FORMAT21c_METHOD_HANDLE[$start, \"I_STATEMENT_FORMAT21c_METHOD_HANDLE\"] INSTRUCTION_FORMAT21c_METHOD_HANDLE REGISTER method_handle_reference )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:964:8: ^( I_STATEMENT_FORMAT21c_METHOD_HANDLE[$start, \"I_STATEMENT_FORMAT21c_METHOD_HANDLE\"] INSTRUCTION_FORMAT21c_METHOD_HANDLE REGISTER method_handle_reference )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT21c_METHOD_HANDLE, (retval.start), "I_STATEMENT_FORMAT21c_METHOD_HANDLE"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21c_METHOD_HANDLE.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_method_handle_reference.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format21c_method_handle"


	public static class insn_format21c_method_type_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format21c_method_type"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:967:1: insn_format21c_method_type : INSTRUCTION_FORMAT21c_METHOD_TYPE REGISTER COMMA method_prototype -> ^( I_STATEMENT_FORMAT21c_METHOD_TYPE[$start, \"I_STATEMENT_FORMAT21c_METHOD_TYPE\"] INSTRUCTION_FORMAT21c_METHOD_TYPE REGISTER method_prototype ) ;
	public final smaliParser.insn_format21c_method_type_return insn_format21c_method_type() throws RecognitionException {
		smaliParser.insn_format21c_method_type_return retval = new smaliParser.insn_format21c_method_type_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT21c_METHOD_TYPE356=null;
		Token REGISTER357=null;
		Token COMMA358=null;
		ParserRuleReturnScope method_prototype359 =null;

		CommonTree INSTRUCTION_FORMAT21c_METHOD_TYPE356_tree=null;
		CommonTree REGISTER357_tree=null;
		CommonTree COMMA358_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_METHOD_TYPE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_METHOD_TYPE");
		RewriteRuleSubtreeStream stream_method_prototype=new RewriteRuleSubtreeStream(adaptor,"rule method_prototype");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:968:5: ( INSTRUCTION_FORMAT21c_METHOD_TYPE REGISTER COMMA method_prototype -> ^( I_STATEMENT_FORMAT21c_METHOD_TYPE[$start, \"I_STATEMENT_FORMAT21c_METHOD_TYPE\"] INSTRUCTION_FORMAT21c_METHOD_TYPE REGISTER method_prototype ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:969:5: INSTRUCTION_FORMAT21c_METHOD_TYPE REGISTER COMMA method_prototype
			{
			INSTRUCTION_FORMAT21c_METHOD_TYPE356=(Token)match(input,INSTRUCTION_FORMAT21c_METHOD_TYPE,FOLLOW_INSTRUCTION_FORMAT21c_METHOD_TYPE_in_insn_format21c_method_type4801);
			stream_INSTRUCTION_FORMAT21c_METHOD_TYPE.add(INSTRUCTION_FORMAT21c_METHOD_TYPE356);

			REGISTER357=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21c_method_type4803);
			stream_REGISTER.add(REGISTER357);

			COMMA358=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format21c_method_type4805);
			stream_COMMA.add(COMMA358);

			pushFollow(FOLLOW_method_prototype_in_insn_format21c_method_type4807);
			method_prototype359=method_prototype();
			state._fsp--;

			stream_method_prototype.add(method_prototype359.getTree());
			// AST REWRITE
			// elements: INSTRUCTION_FORMAT21c_METHOD_TYPE, REGISTER, method_prototype
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 970:5: -> ^( I_STATEMENT_FORMAT21c_METHOD_TYPE[$start, \"I_STATEMENT_FORMAT21c_METHOD_TYPE\"] INSTRUCTION_FORMAT21c_METHOD_TYPE REGISTER method_prototype )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:970:8: ^( I_STATEMENT_FORMAT21c_METHOD_TYPE[$start, \"I_STATEMENT_FORMAT21c_METHOD_TYPE\"] INSTRUCTION_FORMAT21c_METHOD_TYPE REGISTER method_prototype )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT21c_METHOD_TYPE, (retval.start), "I_STATEMENT_FORMAT21c_METHOD_TYPE"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21c_METHOD_TYPE.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_method_prototype.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format21c_method_type"


	public static class insn_format21c_string_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format21c_string"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:973:1: insn_format21c_string : INSTRUCTION_FORMAT21c_STRING REGISTER COMMA STRING_LITERAL -> ^( I_STATEMENT_FORMAT21c_STRING[$start, \"I_STATEMENT_FORMAT21c_STRING\"] INSTRUCTION_FORMAT21c_STRING REGISTER STRING_LITERAL ) ;
	public final smaliParser.insn_format21c_string_return insn_format21c_string() throws RecognitionException {
		smaliParser.insn_format21c_string_return retval = new smaliParser.insn_format21c_string_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT21c_STRING360=null;
		Token REGISTER361=null;
		Token COMMA362=null;
		Token STRING_LITERAL363=null;

		CommonTree INSTRUCTION_FORMAT21c_STRING360_tree=null;
		CommonTree REGISTER361_tree=null;
		CommonTree COMMA362_tree=null;
		CommonTree STRING_LITERAL363_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_STRING=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_STRING");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:974:3: ( INSTRUCTION_FORMAT21c_STRING REGISTER COMMA STRING_LITERAL -> ^( I_STATEMENT_FORMAT21c_STRING[$start, \"I_STATEMENT_FORMAT21c_STRING\"] INSTRUCTION_FORMAT21c_STRING REGISTER STRING_LITERAL ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:975:5: INSTRUCTION_FORMAT21c_STRING REGISTER COMMA STRING_LITERAL
			{
			INSTRUCTION_FORMAT21c_STRING360=(Token)match(input,INSTRUCTION_FORMAT21c_STRING,FOLLOW_INSTRUCTION_FORMAT21c_STRING_in_insn_format21c_string4851);
			stream_INSTRUCTION_FORMAT21c_STRING.add(INSTRUCTION_FORMAT21c_STRING360);

			REGISTER361=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21c_string4853);
			stream_REGISTER.add(REGISTER361);

			COMMA362=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format21c_string4855);
			stream_COMMA.add(COMMA362);

			STRING_LITERAL363=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_insn_format21c_string4857);
			stream_STRING_LITERAL.add(STRING_LITERAL363);

			// AST REWRITE
			// elements: INSTRUCTION_FORMAT21c_STRING, REGISTER, STRING_LITERAL
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 976:5: -> ^( I_STATEMENT_FORMAT21c_STRING[$start, \"I_STATEMENT_FORMAT21c_STRING\"] INSTRUCTION_FORMAT21c_STRING REGISTER STRING_LITERAL )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:976:8: ^( I_STATEMENT_FORMAT21c_STRING[$start, \"I_STATEMENT_FORMAT21c_STRING\"] INSTRUCTION_FORMAT21c_STRING REGISTER STRING_LITERAL )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT21c_STRING, (retval.start), "I_STATEMENT_FORMAT21c_STRING"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21c_STRING.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format21c_string"


	public static class insn_format21c_type_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format21c_type"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:978:1: insn_format21c_type : INSTRUCTION_FORMAT21c_TYPE REGISTER COMMA nonvoid_type_descriptor -> ^( I_STATEMENT_FORMAT21c_TYPE[$start, \"I_STATEMENT_FORMAT21c\"] INSTRUCTION_FORMAT21c_TYPE REGISTER nonvoid_type_descriptor ) ;
	public final smaliParser.insn_format21c_type_return insn_format21c_type() throws RecognitionException {
		smaliParser.insn_format21c_type_return retval = new smaliParser.insn_format21c_type_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT21c_TYPE364=null;
		Token REGISTER365=null;
		Token COMMA366=null;
		ParserRuleReturnScope nonvoid_type_descriptor367 =null;

		CommonTree INSTRUCTION_FORMAT21c_TYPE364_tree=null;
		CommonTree REGISTER365_tree=null;
		CommonTree COMMA366_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21c_TYPE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21c_TYPE");
		RewriteRuleSubtreeStream stream_nonvoid_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule nonvoid_type_descriptor");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:979:3: ( INSTRUCTION_FORMAT21c_TYPE REGISTER COMMA nonvoid_type_descriptor -> ^( I_STATEMENT_FORMAT21c_TYPE[$start, \"I_STATEMENT_FORMAT21c\"] INSTRUCTION_FORMAT21c_TYPE REGISTER nonvoid_type_descriptor ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:980:5: INSTRUCTION_FORMAT21c_TYPE REGISTER COMMA nonvoid_type_descriptor
			{
			INSTRUCTION_FORMAT21c_TYPE364=(Token)match(input,INSTRUCTION_FORMAT21c_TYPE,FOLLOW_INSTRUCTION_FORMAT21c_TYPE_in_insn_format21c_type4889);
			stream_INSTRUCTION_FORMAT21c_TYPE.add(INSTRUCTION_FORMAT21c_TYPE364);

			REGISTER365=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21c_type4891);
			stream_REGISTER.add(REGISTER365);

			COMMA366=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format21c_type4893);
			stream_COMMA.add(COMMA366);

			pushFollow(FOLLOW_nonvoid_type_descriptor_in_insn_format21c_type4895);
			nonvoid_type_descriptor367=nonvoid_type_descriptor();
			state._fsp--;

			stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor367.getTree());
			// AST REWRITE
			// elements: nonvoid_type_descriptor, REGISTER, INSTRUCTION_FORMAT21c_TYPE
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 981:5: -> ^( I_STATEMENT_FORMAT21c_TYPE[$start, \"I_STATEMENT_FORMAT21c\"] INSTRUCTION_FORMAT21c_TYPE REGISTER nonvoid_type_descriptor )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:981:8: ^( I_STATEMENT_FORMAT21c_TYPE[$start, \"I_STATEMENT_FORMAT21c\"] INSTRUCTION_FORMAT21c_TYPE REGISTER nonvoid_type_descriptor )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT21c_TYPE, (retval.start), "I_STATEMENT_FORMAT21c"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21c_TYPE.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_nonvoid_type_descriptor.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format21c_type"


	public static class insn_format21ih_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format21ih"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:983:1: insn_format21ih : INSTRUCTION_FORMAT21ih REGISTER COMMA fixed_32bit_literal -> ^( I_STATEMENT_FORMAT21ih[$start, \"I_STATEMENT_FORMAT21ih\"] INSTRUCTION_FORMAT21ih REGISTER fixed_32bit_literal ) ;
	public final smaliParser.insn_format21ih_return insn_format21ih() throws RecognitionException {
		smaliParser.insn_format21ih_return retval = new smaliParser.insn_format21ih_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT21ih368=null;
		Token REGISTER369=null;
		Token COMMA370=null;
		ParserRuleReturnScope fixed_32bit_literal371 =null;

		CommonTree INSTRUCTION_FORMAT21ih368_tree=null;
		CommonTree REGISTER369_tree=null;
		CommonTree COMMA370_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21ih=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21ih");
		RewriteRuleSubtreeStream stream_fixed_32bit_literal=new RewriteRuleSubtreeStream(adaptor,"rule fixed_32bit_literal");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:984:3: ( INSTRUCTION_FORMAT21ih REGISTER COMMA fixed_32bit_literal -> ^( I_STATEMENT_FORMAT21ih[$start, \"I_STATEMENT_FORMAT21ih\"] INSTRUCTION_FORMAT21ih REGISTER fixed_32bit_literal ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:985:5: INSTRUCTION_FORMAT21ih REGISTER COMMA fixed_32bit_literal
			{
			INSTRUCTION_FORMAT21ih368=(Token)match(input,INSTRUCTION_FORMAT21ih,FOLLOW_INSTRUCTION_FORMAT21ih_in_insn_format21ih4927);
			stream_INSTRUCTION_FORMAT21ih.add(INSTRUCTION_FORMAT21ih368);

			REGISTER369=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21ih4929);
			stream_REGISTER.add(REGISTER369);

			COMMA370=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format21ih4931);
			stream_COMMA.add(COMMA370);

			pushFollow(FOLLOW_fixed_32bit_literal_in_insn_format21ih4933);
			fixed_32bit_literal371=fixed_32bit_literal();
			state._fsp--;

			stream_fixed_32bit_literal.add(fixed_32bit_literal371.getTree());
			// AST REWRITE
			// elements: INSTRUCTION_FORMAT21ih, REGISTER, fixed_32bit_literal
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 986:5: -> ^( I_STATEMENT_FORMAT21ih[$start, \"I_STATEMENT_FORMAT21ih\"] INSTRUCTION_FORMAT21ih REGISTER fixed_32bit_literal )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:986:8: ^( I_STATEMENT_FORMAT21ih[$start, \"I_STATEMENT_FORMAT21ih\"] INSTRUCTION_FORMAT21ih REGISTER fixed_32bit_literal )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT21ih, (retval.start), "I_STATEMENT_FORMAT21ih"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21ih.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_fixed_32bit_literal.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format21ih"


	public static class insn_format21lh_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format21lh"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:988:1: insn_format21lh : INSTRUCTION_FORMAT21lh REGISTER COMMA fixed_32bit_literal -> ^( I_STATEMENT_FORMAT21lh[$start, \"I_STATEMENT_FORMAT21lh\"] INSTRUCTION_FORMAT21lh REGISTER fixed_32bit_literal ) ;
	public final smaliParser.insn_format21lh_return insn_format21lh() throws RecognitionException {
		smaliParser.insn_format21lh_return retval = new smaliParser.insn_format21lh_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT21lh372=null;
		Token REGISTER373=null;
		Token COMMA374=null;
		ParserRuleReturnScope fixed_32bit_literal375 =null;

		CommonTree INSTRUCTION_FORMAT21lh372_tree=null;
		CommonTree REGISTER373_tree=null;
		CommonTree COMMA374_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21lh=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21lh");
		RewriteRuleSubtreeStream stream_fixed_32bit_literal=new RewriteRuleSubtreeStream(adaptor,"rule fixed_32bit_literal");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:989:3: ( INSTRUCTION_FORMAT21lh REGISTER COMMA fixed_32bit_literal -> ^( I_STATEMENT_FORMAT21lh[$start, \"I_STATEMENT_FORMAT21lh\"] INSTRUCTION_FORMAT21lh REGISTER fixed_32bit_literal ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:990:5: INSTRUCTION_FORMAT21lh REGISTER COMMA fixed_32bit_literal
			{
			INSTRUCTION_FORMAT21lh372=(Token)match(input,INSTRUCTION_FORMAT21lh,FOLLOW_INSTRUCTION_FORMAT21lh_in_insn_format21lh4965);
			stream_INSTRUCTION_FORMAT21lh.add(INSTRUCTION_FORMAT21lh372);

			REGISTER373=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21lh4967);
			stream_REGISTER.add(REGISTER373);

			COMMA374=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format21lh4969);
			stream_COMMA.add(COMMA374);

			pushFollow(FOLLOW_fixed_32bit_literal_in_insn_format21lh4971);
			fixed_32bit_literal375=fixed_32bit_literal();
			state._fsp--;

			stream_fixed_32bit_literal.add(fixed_32bit_literal375.getTree());
			// AST REWRITE
			// elements: INSTRUCTION_FORMAT21lh, fixed_32bit_literal, REGISTER
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 991:5: -> ^( I_STATEMENT_FORMAT21lh[$start, \"I_STATEMENT_FORMAT21lh\"] INSTRUCTION_FORMAT21lh REGISTER fixed_32bit_literal )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:991:8: ^( I_STATEMENT_FORMAT21lh[$start, \"I_STATEMENT_FORMAT21lh\"] INSTRUCTION_FORMAT21lh REGISTER fixed_32bit_literal )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT21lh, (retval.start), "I_STATEMENT_FORMAT21lh"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21lh.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_fixed_32bit_literal.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format21lh"


	public static class insn_format21s_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format21s"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:993:1: insn_format21s : INSTRUCTION_FORMAT21s REGISTER COMMA integral_literal -> ^( I_STATEMENT_FORMAT21s[$start, \"I_STATEMENT_FORMAT21s\"] INSTRUCTION_FORMAT21s REGISTER integral_literal ) ;
	public final smaliParser.insn_format21s_return insn_format21s() throws RecognitionException {
		smaliParser.insn_format21s_return retval = new smaliParser.insn_format21s_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT21s376=null;
		Token REGISTER377=null;
		Token COMMA378=null;
		ParserRuleReturnScope integral_literal379 =null;

		CommonTree INSTRUCTION_FORMAT21s376_tree=null;
		CommonTree REGISTER377_tree=null;
		CommonTree COMMA378_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21s=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21s");
		RewriteRuleSubtreeStream stream_integral_literal=new RewriteRuleSubtreeStream(adaptor,"rule integral_literal");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:994:3: ( INSTRUCTION_FORMAT21s REGISTER COMMA integral_literal -> ^( I_STATEMENT_FORMAT21s[$start, \"I_STATEMENT_FORMAT21s\"] INSTRUCTION_FORMAT21s REGISTER integral_literal ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:995:5: INSTRUCTION_FORMAT21s REGISTER COMMA integral_literal
			{
			INSTRUCTION_FORMAT21s376=(Token)match(input,INSTRUCTION_FORMAT21s,FOLLOW_INSTRUCTION_FORMAT21s_in_insn_format21s5003);
			stream_INSTRUCTION_FORMAT21s.add(INSTRUCTION_FORMAT21s376);

			REGISTER377=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21s5005);
			stream_REGISTER.add(REGISTER377);

			COMMA378=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format21s5007);
			stream_COMMA.add(COMMA378);

			pushFollow(FOLLOW_integral_literal_in_insn_format21s5009);
			integral_literal379=integral_literal();
			state._fsp--;

			stream_integral_literal.add(integral_literal379.getTree());
			// AST REWRITE
			// elements: INSTRUCTION_FORMAT21s, integral_literal, REGISTER
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 996:5: -> ^( I_STATEMENT_FORMAT21s[$start, \"I_STATEMENT_FORMAT21s\"] INSTRUCTION_FORMAT21s REGISTER integral_literal )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:996:8: ^( I_STATEMENT_FORMAT21s[$start, \"I_STATEMENT_FORMAT21s\"] INSTRUCTION_FORMAT21s REGISTER integral_literal )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT21s, (retval.start), "I_STATEMENT_FORMAT21s"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21s.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_integral_literal.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format21s"


	public static class insn_format21t_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format21t"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:998:1: insn_format21t : INSTRUCTION_FORMAT21t REGISTER COMMA label_ref -> ^( I_STATEMENT_FORMAT21t[$start, \"I_STATEMENT_FORMAT21t\"] INSTRUCTION_FORMAT21t REGISTER label_ref ) ;
	public final smaliParser.insn_format21t_return insn_format21t() throws RecognitionException {
		smaliParser.insn_format21t_return retval = new smaliParser.insn_format21t_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT21t380=null;
		Token REGISTER381=null;
		Token COMMA382=null;
		ParserRuleReturnScope label_ref383 =null;

		CommonTree INSTRUCTION_FORMAT21t380_tree=null;
		CommonTree REGISTER381_tree=null;
		CommonTree COMMA382_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT21t=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT21t");
		RewriteRuleSubtreeStream stream_label_ref=new RewriteRuleSubtreeStream(adaptor,"rule label_ref");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:999:3: ( INSTRUCTION_FORMAT21t REGISTER COMMA label_ref -> ^( I_STATEMENT_FORMAT21t[$start, \"I_STATEMENT_FORMAT21t\"] INSTRUCTION_FORMAT21t REGISTER label_ref ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1000:5: INSTRUCTION_FORMAT21t REGISTER COMMA label_ref
			{
			INSTRUCTION_FORMAT21t380=(Token)match(input,INSTRUCTION_FORMAT21t,FOLLOW_INSTRUCTION_FORMAT21t_in_insn_format21t5041);
			stream_INSTRUCTION_FORMAT21t.add(INSTRUCTION_FORMAT21t380);

			REGISTER381=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format21t5043);
			stream_REGISTER.add(REGISTER381);

			COMMA382=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format21t5045);
			stream_COMMA.add(COMMA382);

			pushFollow(FOLLOW_label_ref_in_insn_format21t5047);
			label_ref383=label_ref();
			state._fsp--;

			stream_label_ref.add(label_ref383.getTree());
			// AST REWRITE
			// elements: INSTRUCTION_FORMAT21t, label_ref, REGISTER
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1001:5: -> ^( I_STATEMENT_FORMAT21t[$start, \"I_STATEMENT_FORMAT21t\"] INSTRUCTION_FORMAT21t REGISTER label_ref )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1001:8: ^( I_STATEMENT_FORMAT21t[$start, \"I_STATEMENT_FORMAT21t\"] INSTRUCTION_FORMAT21t REGISTER label_ref )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT21t, (retval.start), "I_STATEMENT_FORMAT21t"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT21t.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_label_ref.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format21t"


	public static class insn_format22b_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format22b"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1003:1: insn_format22b : INSTRUCTION_FORMAT22b REGISTER COMMA REGISTER COMMA integral_literal -> ^( I_STATEMENT_FORMAT22b[$start, \"I_STATEMENT_FORMAT22b\"] INSTRUCTION_FORMAT22b REGISTER REGISTER integral_literal ) ;
	public final smaliParser.insn_format22b_return insn_format22b() throws RecognitionException {
		smaliParser.insn_format22b_return retval = new smaliParser.insn_format22b_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT22b384=null;
		Token REGISTER385=null;
		Token COMMA386=null;
		Token REGISTER387=null;
		Token COMMA388=null;
		ParserRuleReturnScope integral_literal389 =null;

		CommonTree INSTRUCTION_FORMAT22b384_tree=null;
		CommonTree REGISTER385_tree=null;
		CommonTree COMMA386_tree=null;
		CommonTree REGISTER387_tree=null;
		CommonTree COMMA388_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22b=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22b");
		RewriteRuleSubtreeStream stream_integral_literal=new RewriteRuleSubtreeStream(adaptor,"rule integral_literal");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1004:3: ( INSTRUCTION_FORMAT22b REGISTER COMMA REGISTER COMMA integral_literal -> ^( I_STATEMENT_FORMAT22b[$start, \"I_STATEMENT_FORMAT22b\"] INSTRUCTION_FORMAT22b REGISTER REGISTER integral_literal ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1005:5: INSTRUCTION_FORMAT22b REGISTER COMMA REGISTER COMMA integral_literal
			{
			INSTRUCTION_FORMAT22b384=(Token)match(input,INSTRUCTION_FORMAT22b,FOLLOW_INSTRUCTION_FORMAT22b_in_insn_format22b5079);
			stream_INSTRUCTION_FORMAT22b.add(INSTRUCTION_FORMAT22b384);

			REGISTER385=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22b5081);
			stream_REGISTER.add(REGISTER385);

			COMMA386=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22b5083);
			stream_COMMA.add(COMMA386);

			REGISTER387=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22b5085);
			stream_REGISTER.add(REGISTER387);

			COMMA388=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22b5087);
			stream_COMMA.add(COMMA388);

			pushFollow(FOLLOW_integral_literal_in_insn_format22b5089);
			integral_literal389=integral_literal();
			state._fsp--;

			stream_integral_literal.add(integral_literal389.getTree());
			// AST REWRITE
			// elements: integral_literal, REGISTER, INSTRUCTION_FORMAT22b, REGISTER
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1006:5: -> ^( I_STATEMENT_FORMAT22b[$start, \"I_STATEMENT_FORMAT22b\"] INSTRUCTION_FORMAT22b REGISTER REGISTER integral_literal )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1006:8: ^( I_STATEMENT_FORMAT22b[$start, \"I_STATEMENT_FORMAT22b\"] INSTRUCTION_FORMAT22b REGISTER REGISTER integral_literal )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT22b, (retval.start), "I_STATEMENT_FORMAT22b"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT22b.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_integral_literal.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format22b"


	public static class insn_format22c_field_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format22c_field"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1008:1: insn_format22c_field : INSTRUCTION_FORMAT22c_FIELD REGISTER COMMA REGISTER COMMA field_reference -> ^( I_STATEMENT_FORMAT22c_FIELD[$start, \"I_STATEMENT_FORMAT22c_FIELD\"] INSTRUCTION_FORMAT22c_FIELD REGISTER REGISTER field_reference ) ;
	public final smaliParser.insn_format22c_field_return insn_format22c_field() throws RecognitionException {
		smaliParser.insn_format22c_field_return retval = new smaliParser.insn_format22c_field_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT22c_FIELD390=null;
		Token REGISTER391=null;
		Token COMMA392=null;
		Token REGISTER393=null;
		Token COMMA394=null;
		ParserRuleReturnScope field_reference395 =null;

		CommonTree INSTRUCTION_FORMAT22c_FIELD390_tree=null;
		CommonTree REGISTER391_tree=null;
		CommonTree COMMA392_tree=null;
		CommonTree REGISTER393_tree=null;
		CommonTree COMMA394_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22c_FIELD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22c_FIELD");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleSubtreeStream stream_field_reference=new RewriteRuleSubtreeStream(adaptor,"rule field_reference");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1009:3: ( INSTRUCTION_FORMAT22c_FIELD REGISTER COMMA REGISTER COMMA field_reference -> ^( I_STATEMENT_FORMAT22c_FIELD[$start, \"I_STATEMENT_FORMAT22c_FIELD\"] INSTRUCTION_FORMAT22c_FIELD REGISTER REGISTER field_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1010:5: INSTRUCTION_FORMAT22c_FIELD REGISTER COMMA REGISTER COMMA field_reference
			{
			INSTRUCTION_FORMAT22c_FIELD390=(Token)match(input,INSTRUCTION_FORMAT22c_FIELD,FOLLOW_INSTRUCTION_FORMAT22c_FIELD_in_insn_format22c_field5123);
			stream_INSTRUCTION_FORMAT22c_FIELD.add(INSTRUCTION_FORMAT22c_FIELD390);

			REGISTER391=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_field5125);
			stream_REGISTER.add(REGISTER391);

			COMMA392=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22c_field5127);
			stream_COMMA.add(COMMA392);

			REGISTER393=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_field5129);
			stream_REGISTER.add(REGISTER393);

			COMMA394=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22c_field5131);
			stream_COMMA.add(COMMA394);

			pushFollow(FOLLOW_field_reference_in_insn_format22c_field5133);
			field_reference395=field_reference();
			state._fsp--;

			stream_field_reference.add(field_reference395.getTree());
			// AST REWRITE
			// elements: field_reference, INSTRUCTION_FORMAT22c_FIELD, REGISTER, REGISTER
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1011:5: -> ^( I_STATEMENT_FORMAT22c_FIELD[$start, \"I_STATEMENT_FORMAT22c_FIELD\"] INSTRUCTION_FORMAT22c_FIELD REGISTER REGISTER field_reference )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1011:8: ^( I_STATEMENT_FORMAT22c_FIELD[$start, \"I_STATEMENT_FORMAT22c_FIELD\"] INSTRUCTION_FORMAT22c_FIELD REGISTER REGISTER field_reference )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT22c_FIELD, (retval.start), "I_STATEMENT_FORMAT22c_FIELD"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT22c_FIELD.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_field_reference.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format22c_field"


	public static class insn_format22c_field_odex_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format22c_field_odex"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1013:1: insn_format22c_field_odex : INSTRUCTION_FORMAT22c_FIELD_ODEX REGISTER COMMA REGISTER COMMA field_reference -> ^( I_STATEMENT_FORMAT22c_FIELD[$start, \"I_STATEMENT_FORMAT22c_FIELD\"] INSTRUCTION_FORMAT22c_FIELD_ODEX REGISTER REGISTER field_reference ) ;
	public final smaliParser.insn_format22c_field_odex_return insn_format22c_field_odex() throws RecognitionException {
		smaliParser.insn_format22c_field_odex_return retval = new smaliParser.insn_format22c_field_odex_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT22c_FIELD_ODEX396=null;
		Token REGISTER397=null;
		Token COMMA398=null;
		Token REGISTER399=null;
		Token COMMA400=null;
		ParserRuleReturnScope field_reference401 =null;

		CommonTree INSTRUCTION_FORMAT22c_FIELD_ODEX396_tree=null;
		CommonTree REGISTER397_tree=null;
		CommonTree COMMA398_tree=null;
		CommonTree REGISTER399_tree=null;
		CommonTree COMMA400_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22c_FIELD_ODEX=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22c_FIELD_ODEX");
		RewriteRuleSubtreeStream stream_field_reference=new RewriteRuleSubtreeStream(adaptor,"rule field_reference");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1014:3: ( INSTRUCTION_FORMAT22c_FIELD_ODEX REGISTER COMMA REGISTER COMMA field_reference -> ^( I_STATEMENT_FORMAT22c_FIELD[$start, \"I_STATEMENT_FORMAT22c_FIELD\"] INSTRUCTION_FORMAT22c_FIELD_ODEX REGISTER REGISTER field_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1015:5: INSTRUCTION_FORMAT22c_FIELD_ODEX REGISTER COMMA REGISTER COMMA field_reference
			{
			INSTRUCTION_FORMAT22c_FIELD_ODEX396=(Token)match(input,INSTRUCTION_FORMAT22c_FIELD_ODEX,FOLLOW_INSTRUCTION_FORMAT22c_FIELD_ODEX_in_insn_format22c_field_odex5167);
			stream_INSTRUCTION_FORMAT22c_FIELD_ODEX.add(INSTRUCTION_FORMAT22c_FIELD_ODEX396);

			REGISTER397=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_field_odex5169);
			stream_REGISTER.add(REGISTER397);

			COMMA398=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22c_field_odex5171);
			stream_COMMA.add(COMMA398);

			REGISTER399=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_field_odex5173);
			stream_REGISTER.add(REGISTER399);

			COMMA400=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22c_field_odex5175);
			stream_COMMA.add(COMMA400);

			pushFollow(FOLLOW_field_reference_in_insn_format22c_field_odex5177);
			field_reference401=field_reference();
			state._fsp--;

			stream_field_reference.add(field_reference401.getTree());

			      if (!allowOdex || opcodes.getOpcodeByName((INSTRUCTION_FORMAT22c_FIELD_ODEX396!=null?INSTRUCTION_FORMAT22c_FIELD_ODEX396.getText():null)) == null || apiLevel >= 14) {
			        throwOdexedInstructionException(input, (INSTRUCTION_FORMAT22c_FIELD_ODEX396!=null?INSTRUCTION_FORMAT22c_FIELD_ODEX396.getText():null));
			      }
			
			// AST REWRITE
			// elements: INSTRUCTION_FORMAT22c_FIELD_ODEX, field_reference, REGISTER, REGISTER
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1021:5: -> ^( I_STATEMENT_FORMAT22c_FIELD[$start, \"I_STATEMENT_FORMAT22c_FIELD\"] INSTRUCTION_FORMAT22c_FIELD_ODEX REGISTER REGISTER field_reference )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1021:8: ^( I_STATEMENT_FORMAT22c_FIELD[$start, \"I_STATEMENT_FORMAT22c_FIELD\"] INSTRUCTION_FORMAT22c_FIELD_ODEX REGISTER REGISTER field_reference )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT22c_FIELD, (retval.start), "I_STATEMENT_FORMAT22c_FIELD"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT22c_FIELD_ODEX.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_field_reference.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format22c_field_odex"


	public static class insn_format22c_type_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format22c_type"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1023:1: insn_format22c_type : INSTRUCTION_FORMAT22c_TYPE REGISTER COMMA REGISTER COMMA nonvoid_type_descriptor -> ^( I_STATEMENT_FORMAT22c_TYPE[$start, \"I_STATEMENT_FORMAT22c_TYPE\"] INSTRUCTION_FORMAT22c_TYPE REGISTER REGISTER nonvoid_type_descriptor ) ;
	public final smaliParser.insn_format22c_type_return insn_format22c_type() throws RecognitionException {
		smaliParser.insn_format22c_type_return retval = new smaliParser.insn_format22c_type_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT22c_TYPE402=null;
		Token REGISTER403=null;
		Token COMMA404=null;
		Token REGISTER405=null;
		Token COMMA406=null;
		ParserRuleReturnScope nonvoid_type_descriptor407 =null;

		CommonTree INSTRUCTION_FORMAT22c_TYPE402_tree=null;
		CommonTree REGISTER403_tree=null;
		CommonTree COMMA404_tree=null;
		CommonTree REGISTER405_tree=null;
		CommonTree COMMA406_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22c_TYPE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22c_TYPE");
		RewriteRuleSubtreeStream stream_nonvoid_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule nonvoid_type_descriptor");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1024:3: ( INSTRUCTION_FORMAT22c_TYPE REGISTER COMMA REGISTER COMMA nonvoid_type_descriptor -> ^( I_STATEMENT_FORMAT22c_TYPE[$start, \"I_STATEMENT_FORMAT22c_TYPE\"] INSTRUCTION_FORMAT22c_TYPE REGISTER REGISTER nonvoid_type_descriptor ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1025:5: INSTRUCTION_FORMAT22c_TYPE REGISTER COMMA REGISTER COMMA nonvoid_type_descriptor
			{
			INSTRUCTION_FORMAT22c_TYPE402=(Token)match(input,INSTRUCTION_FORMAT22c_TYPE,FOLLOW_INSTRUCTION_FORMAT22c_TYPE_in_insn_format22c_type5217);
			stream_INSTRUCTION_FORMAT22c_TYPE.add(INSTRUCTION_FORMAT22c_TYPE402);

			REGISTER403=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_type5219);
			stream_REGISTER.add(REGISTER403);

			COMMA404=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22c_type5221);
			stream_COMMA.add(COMMA404);

			REGISTER405=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22c_type5223);
			stream_REGISTER.add(REGISTER405);

			COMMA406=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22c_type5225);
			stream_COMMA.add(COMMA406);

			pushFollow(FOLLOW_nonvoid_type_descriptor_in_insn_format22c_type5227);
			nonvoid_type_descriptor407=nonvoid_type_descriptor();
			state._fsp--;

			stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor407.getTree());
			// AST REWRITE
			// elements: INSTRUCTION_FORMAT22c_TYPE, REGISTER, REGISTER, nonvoid_type_descriptor
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1026:5: -> ^( I_STATEMENT_FORMAT22c_TYPE[$start, \"I_STATEMENT_FORMAT22c_TYPE\"] INSTRUCTION_FORMAT22c_TYPE REGISTER REGISTER nonvoid_type_descriptor )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1026:8: ^( I_STATEMENT_FORMAT22c_TYPE[$start, \"I_STATEMENT_FORMAT22c_TYPE\"] INSTRUCTION_FORMAT22c_TYPE REGISTER REGISTER nonvoid_type_descriptor )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT22c_TYPE, (retval.start), "I_STATEMENT_FORMAT22c_TYPE"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT22c_TYPE.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_nonvoid_type_descriptor.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format22c_type"


	public static class insn_format22cs_field_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format22cs_field"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1028:1: insn_format22cs_field : INSTRUCTION_FORMAT22cs_FIELD REGISTER COMMA REGISTER COMMA FIELD_OFFSET ;
	public final smaliParser.insn_format22cs_field_return insn_format22cs_field() throws RecognitionException {
		smaliParser.insn_format22cs_field_return retval = new smaliParser.insn_format22cs_field_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT22cs_FIELD408=null;
		Token REGISTER409=null;
		Token COMMA410=null;
		Token REGISTER411=null;
		Token COMMA412=null;
		Token FIELD_OFFSET413=null;

		CommonTree INSTRUCTION_FORMAT22cs_FIELD408_tree=null;
		CommonTree REGISTER409_tree=null;
		CommonTree COMMA410_tree=null;
		CommonTree REGISTER411_tree=null;
		CommonTree COMMA412_tree=null;
		CommonTree FIELD_OFFSET413_tree=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1029:3: ( INSTRUCTION_FORMAT22cs_FIELD REGISTER COMMA REGISTER COMMA FIELD_OFFSET )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1030:5: INSTRUCTION_FORMAT22cs_FIELD REGISTER COMMA REGISTER COMMA FIELD_OFFSET
			{
			root_0 = (CommonTree)adaptor.nil();


			INSTRUCTION_FORMAT22cs_FIELD408=(Token)match(input,INSTRUCTION_FORMAT22cs_FIELD,FOLLOW_INSTRUCTION_FORMAT22cs_FIELD_in_insn_format22cs_field5261);
			INSTRUCTION_FORMAT22cs_FIELD408_tree = (CommonTree)adaptor.create(INSTRUCTION_FORMAT22cs_FIELD408);
			adaptor.addChild(root_0, INSTRUCTION_FORMAT22cs_FIELD408_tree);

			REGISTER409=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22cs_field5263);
			REGISTER409_tree = (CommonTree)adaptor.create(REGISTER409);
			adaptor.addChild(root_0, REGISTER409_tree);

			COMMA410=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22cs_field5265);
			COMMA410_tree = (CommonTree)adaptor.create(COMMA410);
			adaptor.addChild(root_0, COMMA410_tree);

			REGISTER411=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22cs_field5267);
			REGISTER411_tree = (CommonTree)adaptor.create(REGISTER411);
			adaptor.addChild(root_0, REGISTER411_tree);

			COMMA412=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22cs_field5269);
			COMMA412_tree = (CommonTree)adaptor.create(COMMA412);
			adaptor.addChild(root_0, COMMA412_tree);

			FIELD_OFFSET413=(Token)match(input,FIELD_OFFSET,FOLLOW_FIELD_OFFSET_in_insn_format22cs_field5271);
			FIELD_OFFSET413_tree = (CommonTree)adaptor.create(FIELD_OFFSET413);
			adaptor.addChild(root_0, FIELD_OFFSET413_tree);


			      throwOdexedInstructionException(input, (INSTRUCTION_FORMAT22cs_FIELD408!=null?INSTRUCTION_FORMAT22cs_FIELD408.getText():null));
			
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format22cs_field"


	public static class insn_format22s_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format22s"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1035:1: insn_format22s : instruction_format22s REGISTER COMMA REGISTER COMMA integral_literal -> ^( I_STATEMENT_FORMAT22s[$start, \"I_STATEMENT_FORMAT22s\"] instruction_format22s REGISTER REGISTER integral_literal ) ;
	public final smaliParser.insn_format22s_return insn_format22s() throws RecognitionException {
		smaliParser.insn_format22s_return retval = new smaliParser.insn_format22s_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token REGISTER415=null;
		Token COMMA416=null;
		Token REGISTER417=null;
		Token COMMA418=null;
		ParserRuleReturnScope instruction_format22s414 =null;
		ParserRuleReturnScope integral_literal419 =null;

		CommonTree REGISTER415_tree=null;
		CommonTree COMMA416_tree=null;
		CommonTree REGISTER417_tree=null;
		CommonTree COMMA418_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleSubtreeStream stream_instruction_format22s=new RewriteRuleSubtreeStream(adaptor,"rule instruction_format22s");
		RewriteRuleSubtreeStream stream_integral_literal=new RewriteRuleSubtreeStream(adaptor,"rule integral_literal");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1036:3: ( instruction_format22s REGISTER COMMA REGISTER COMMA integral_literal -> ^( I_STATEMENT_FORMAT22s[$start, \"I_STATEMENT_FORMAT22s\"] instruction_format22s REGISTER REGISTER integral_literal ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1037:5: instruction_format22s REGISTER COMMA REGISTER COMMA integral_literal
			{
			pushFollow(FOLLOW_instruction_format22s_in_insn_format22s5292);
			instruction_format22s414=instruction_format22s();
			state._fsp--;

			stream_instruction_format22s.add(instruction_format22s414.getTree());
			REGISTER415=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22s5294);
			stream_REGISTER.add(REGISTER415);

			COMMA416=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22s5296);
			stream_COMMA.add(COMMA416);

			REGISTER417=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22s5298);
			stream_REGISTER.add(REGISTER417);

			COMMA418=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22s5300);
			stream_COMMA.add(COMMA418);

			pushFollow(FOLLOW_integral_literal_in_insn_format22s5302);
			integral_literal419=integral_literal();
			state._fsp--;

			stream_integral_literal.add(integral_literal419.getTree());
			// AST REWRITE
			// elements: REGISTER, instruction_format22s, REGISTER, integral_literal
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1038:5: -> ^( I_STATEMENT_FORMAT22s[$start, \"I_STATEMENT_FORMAT22s\"] instruction_format22s REGISTER REGISTER integral_literal )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1038:8: ^( I_STATEMENT_FORMAT22s[$start, \"I_STATEMENT_FORMAT22s\"] instruction_format22s REGISTER REGISTER integral_literal )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT22s, (retval.start), "I_STATEMENT_FORMAT22s"), root_1);
				adaptor.addChild(root_1, stream_instruction_format22s.nextTree());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_integral_literal.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format22s"


	public static class insn_format22t_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format22t"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1040:1: insn_format22t : INSTRUCTION_FORMAT22t REGISTER COMMA REGISTER COMMA label_ref -> ^( I_STATEMENT_FORMAT22t[$start, \"I_STATEMENT_FFORMAT22t\"] INSTRUCTION_FORMAT22t REGISTER REGISTER label_ref ) ;
	public final smaliParser.insn_format22t_return insn_format22t() throws RecognitionException {
		smaliParser.insn_format22t_return retval = new smaliParser.insn_format22t_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT22t420=null;
		Token REGISTER421=null;
		Token COMMA422=null;
		Token REGISTER423=null;
		Token COMMA424=null;
		ParserRuleReturnScope label_ref425 =null;

		CommonTree INSTRUCTION_FORMAT22t420_tree=null;
		CommonTree REGISTER421_tree=null;
		CommonTree COMMA422_tree=null;
		CommonTree REGISTER423_tree=null;
		CommonTree COMMA424_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22t=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22t");
		RewriteRuleSubtreeStream stream_label_ref=new RewriteRuleSubtreeStream(adaptor,"rule label_ref");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1041:3: ( INSTRUCTION_FORMAT22t REGISTER COMMA REGISTER COMMA label_ref -> ^( I_STATEMENT_FORMAT22t[$start, \"I_STATEMENT_FFORMAT22t\"] INSTRUCTION_FORMAT22t REGISTER REGISTER label_ref ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1042:5: INSTRUCTION_FORMAT22t REGISTER COMMA REGISTER COMMA label_ref
			{
			INSTRUCTION_FORMAT22t420=(Token)match(input,INSTRUCTION_FORMAT22t,FOLLOW_INSTRUCTION_FORMAT22t_in_insn_format22t5336);
			stream_INSTRUCTION_FORMAT22t.add(INSTRUCTION_FORMAT22t420);

			REGISTER421=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22t5338);
			stream_REGISTER.add(REGISTER421);

			COMMA422=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22t5340);
			stream_COMMA.add(COMMA422);

			REGISTER423=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22t5342);
			stream_REGISTER.add(REGISTER423);

			COMMA424=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22t5344);
			stream_COMMA.add(COMMA424);

			pushFollow(FOLLOW_label_ref_in_insn_format22t5346);
			label_ref425=label_ref();
			state._fsp--;

			stream_label_ref.add(label_ref425.getTree());
			// AST REWRITE
			// elements: label_ref, REGISTER, REGISTER, INSTRUCTION_FORMAT22t
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1043:5: -> ^( I_STATEMENT_FORMAT22t[$start, \"I_STATEMENT_FFORMAT22t\"] INSTRUCTION_FORMAT22t REGISTER REGISTER label_ref )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1043:8: ^( I_STATEMENT_FORMAT22t[$start, \"I_STATEMENT_FFORMAT22t\"] INSTRUCTION_FORMAT22t REGISTER REGISTER label_ref )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT22t, (retval.start), "I_STATEMENT_FFORMAT22t"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT22t.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_label_ref.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format22t"


	public static class insn_format22x_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format22x"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1045:1: insn_format22x : INSTRUCTION_FORMAT22x REGISTER COMMA REGISTER -> ^( I_STATEMENT_FORMAT22x[$start, \"I_STATEMENT_FORMAT22x\"] INSTRUCTION_FORMAT22x REGISTER REGISTER ) ;
	public final smaliParser.insn_format22x_return insn_format22x() throws RecognitionException {
		smaliParser.insn_format22x_return retval = new smaliParser.insn_format22x_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT22x426=null;
		Token REGISTER427=null;
		Token COMMA428=null;
		Token REGISTER429=null;

		CommonTree INSTRUCTION_FORMAT22x426_tree=null;
		CommonTree REGISTER427_tree=null;
		CommonTree COMMA428_tree=null;
		CommonTree REGISTER429_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT22x=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT22x");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1046:3: ( INSTRUCTION_FORMAT22x REGISTER COMMA REGISTER -> ^( I_STATEMENT_FORMAT22x[$start, \"I_STATEMENT_FORMAT22x\"] INSTRUCTION_FORMAT22x REGISTER REGISTER ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1047:5: INSTRUCTION_FORMAT22x REGISTER COMMA REGISTER
			{
			INSTRUCTION_FORMAT22x426=(Token)match(input,INSTRUCTION_FORMAT22x,FOLLOW_INSTRUCTION_FORMAT22x_in_insn_format22x5380);
			stream_INSTRUCTION_FORMAT22x.add(INSTRUCTION_FORMAT22x426);

			REGISTER427=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22x5382);
			stream_REGISTER.add(REGISTER427);

			COMMA428=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format22x5384);
			stream_COMMA.add(COMMA428);

			REGISTER429=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format22x5386);
			stream_REGISTER.add(REGISTER429);

			// AST REWRITE
			// elements: INSTRUCTION_FORMAT22x, REGISTER, REGISTER
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1048:5: -> ^( I_STATEMENT_FORMAT22x[$start, \"I_STATEMENT_FORMAT22x\"] INSTRUCTION_FORMAT22x REGISTER REGISTER )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1048:8: ^( I_STATEMENT_FORMAT22x[$start, \"I_STATEMENT_FORMAT22x\"] INSTRUCTION_FORMAT22x REGISTER REGISTER )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT22x, (retval.start), "I_STATEMENT_FORMAT22x"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT22x.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format22x"


	public static class insn_format23x_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format23x"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1050:1: insn_format23x : INSTRUCTION_FORMAT23x REGISTER COMMA REGISTER COMMA REGISTER -> ^( I_STATEMENT_FORMAT23x[$start, \"I_STATEMENT_FORMAT23x\"] INSTRUCTION_FORMAT23x REGISTER REGISTER REGISTER ) ;
	public final smaliParser.insn_format23x_return insn_format23x() throws RecognitionException {
		smaliParser.insn_format23x_return retval = new smaliParser.insn_format23x_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT23x430=null;
		Token REGISTER431=null;
		Token COMMA432=null;
		Token REGISTER433=null;
		Token COMMA434=null;
		Token REGISTER435=null;

		CommonTree INSTRUCTION_FORMAT23x430_tree=null;
		CommonTree REGISTER431_tree=null;
		CommonTree COMMA432_tree=null;
		CommonTree REGISTER433_tree=null;
		CommonTree COMMA434_tree=null;
		CommonTree REGISTER435_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT23x=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT23x");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1051:3: ( INSTRUCTION_FORMAT23x REGISTER COMMA REGISTER COMMA REGISTER -> ^( I_STATEMENT_FORMAT23x[$start, \"I_STATEMENT_FORMAT23x\"] INSTRUCTION_FORMAT23x REGISTER REGISTER REGISTER ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1052:5: INSTRUCTION_FORMAT23x REGISTER COMMA REGISTER COMMA REGISTER
			{
			INSTRUCTION_FORMAT23x430=(Token)match(input,INSTRUCTION_FORMAT23x,FOLLOW_INSTRUCTION_FORMAT23x_in_insn_format23x5418);
			stream_INSTRUCTION_FORMAT23x.add(INSTRUCTION_FORMAT23x430);

			REGISTER431=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format23x5420);
			stream_REGISTER.add(REGISTER431);

			COMMA432=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format23x5422);
			stream_COMMA.add(COMMA432);

			REGISTER433=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format23x5424);
			stream_REGISTER.add(REGISTER433);

			COMMA434=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format23x5426);
			stream_COMMA.add(COMMA434);

			REGISTER435=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format23x5428);
			stream_REGISTER.add(REGISTER435);

			// AST REWRITE
			// elements: REGISTER, INSTRUCTION_FORMAT23x, REGISTER, REGISTER
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1053:5: -> ^( I_STATEMENT_FORMAT23x[$start, \"I_STATEMENT_FORMAT23x\"] INSTRUCTION_FORMAT23x REGISTER REGISTER REGISTER )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1053:8: ^( I_STATEMENT_FORMAT23x[$start, \"I_STATEMENT_FORMAT23x\"] INSTRUCTION_FORMAT23x REGISTER REGISTER REGISTER )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT23x, (retval.start), "I_STATEMENT_FORMAT23x"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT23x.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format23x"


	public static class insn_format30t_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format30t"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1055:1: insn_format30t : INSTRUCTION_FORMAT30t label_ref -> ^( I_STATEMENT_FORMAT30t[$start, \"I_STATEMENT_FORMAT30t\"] INSTRUCTION_FORMAT30t label_ref ) ;
	public final smaliParser.insn_format30t_return insn_format30t() throws RecognitionException {
		smaliParser.insn_format30t_return retval = new smaliParser.insn_format30t_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT30t436=null;
		ParserRuleReturnScope label_ref437 =null;

		CommonTree INSTRUCTION_FORMAT30t436_tree=null;
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT30t=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT30t");
		RewriteRuleSubtreeStream stream_label_ref=new RewriteRuleSubtreeStream(adaptor,"rule label_ref");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1056:3: ( INSTRUCTION_FORMAT30t label_ref -> ^( I_STATEMENT_FORMAT30t[$start, \"I_STATEMENT_FORMAT30t\"] INSTRUCTION_FORMAT30t label_ref ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1057:5: INSTRUCTION_FORMAT30t label_ref
			{
			INSTRUCTION_FORMAT30t436=(Token)match(input,INSTRUCTION_FORMAT30t,FOLLOW_INSTRUCTION_FORMAT30t_in_insn_format30t5462);
			stream_INSTRUCTION_FORMAT30t.add(INSTRUCTION_FORMAT30t436);

			pushFollow(FOLLOW_label_ref_in_insn_format30t5464);
			label_ref437=label_ref();
			state._fsp--;

			stream_label_ref.add(label_ref437.getTree());
			// AST REWRITE
			// elements: INSTRUCTION_FORMAT30t, label_ref
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1058:5: -> ^( I_STATEMENT_FORMAT30t[$start, \"I_STATEMENT_FORMAT30t\"] INSTRUCTION_FORMAT30t label_ref )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1058:8: ^( I_STATEMENT_FORMAT30t[$start, \"I_STATEMENT_FORMAT30t\"] INSTRUCTION_FORMAT30t label_ref )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT30t, (retval.start), "I_STATEMENT_FORMAT30t"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT30t.nextNode());
				adaptor.addChild(root_1, stream_label_ref.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format30t"


	public static class insn_format31c_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format31c"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1060:1: insn_format31c : INSTRUCTION_FORMAT31c REGISTER COMMA STRING_LITERAL -> ^( I_STATEMENT_FORMAT31c[$start, \"I_STATEMENT_FORMAT31c\"] INSTRUCTION_FORMAT31c REGISTER STRING_LITERAL ) ;
	public final smaliParser.insn_format31c_return insn_format31c() throws RecognitionException {
		smaliParser.insn_format31c_return retval = new smaliParser.insn_format31c_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT31c438=null;
		Token REGISTER439=null;
		Token COMMA440=null;
		Token STRING_LITERAL441=null;

		CommonTree INSTRUCTION_FORMAT31c438_tree=null;
		CommonTree REGISTER439_tree=null;
		CommonTree COMMA440_tree=null;
		CommonTree STRING_LITERAL441_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT31c=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT31c");
		RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1061:3: ( INSTRUCTION_FORMAT31c REGISTER COMMA STRING_LITERAL -> ^( I_STATEMENT_FORMAT31c[$start, \"I_STATEMENT_FORMAT31c\"] INSTRUCTION_FORMAT31c REGISTER STRING_LITERAL ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1062:5: INSTRUCTION_FORMAT31c REGISTER COMMA STRING_LITERAL
			{
			INSTRUCTION_FORMAT31c438=(Token)match(input,INSTRUCTION_FORMAT31c,FOLLOW_INSTRUCTION_FORMAT31c_in_insn_format31c5494);
			stream_INSTRUCTION_FORMAT31c.add(INSTRUCTION_FORMAT31c438);

			REGISTER439=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format31c5496);
			stream_REGISTER.add(REGISTER439);

			COMMA440=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format31c5498);
			stream_COMMA.add(COMMA440);

			STRING_LITERAL441=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_insn_format31c5500);
			stream_STRING_LITERAL.add(STRING_LITERAL441);

			// AST REWRITE
			// elements: REGISTER, STRING_LITERAL, INSTRUCTION_FORMAT31c
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1063:5: -> ^( I_STATEMENT_FORMAT31c[$start, \"I_STATEMENT_FORMAT31c\"] INSTRUCTION_FORMAT31c REGISTER STRING_LITERAL )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1063:7: ^( I_STATEMENT_FORMAT31c[$start, \"I_STATEMENT_FORMAT31c\"] INSTRUCTION_FORMAT31c REGISTER STRING_LITERAL )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT31c, (retval.start), "I_STATEMENT_FORMAT31c"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT31c.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_STRING_LITERAL.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format31c"


	public static class insn_format31i_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format31i"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1065:1: insn_format31i : instruction_format31i REGISTER COMMA fixed_32bit_literal -> ^( I_STATEMENT_FORMAT31i[$start, \"I_STATEMENT_FORMAT31i\"] instruction_format31i REGISTER fixed_32bit_literal ) ;
	public final smaliParser.insn_format31i_return insn_format31i() throws RecognitionException {
		smaliParser.insn_format31i_return retval = new smaliParser.insn_format31i_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token REGISTER443=null;
		Token COMMA444=null;
		ParserRuleReturnScope instruction_format31i442 =null;
		ParserRuleReturnScope fixed_32bit_literal445 =null;

		CommonTree REGISTER443_tree=null;
		CommonTree COMMA444_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleSubtreeStream stream_fixed_32bit_literal=new RewriteRuleSubtreeStream(adaptor,"rule fixed_32bit_literal");
		RewriteRuleSubtreeStream stream_instruction_format31i=new RewriteRuleSubtreeStream(adaptor,"rule instruction_format31i");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1066:3: ( instruction_format31i REGISTER COMMA fixed_32bit_literal -> ^( I_STATEMENT_FORMAT31i[$start, \"I_STATEMENT_FORMAT31i\"] instruction_format31i REGISTER fixed_32bit_literal ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1067:5: instruction_format31i REGISTER COMMA fixed_32bit_literal
			{
			pushFollow(FOLLOW_instruction_format31i_in_insn_format31i5531);
			instruction_format31i442=instruction_format31i();
			state._fsp--;

			stream_instruction_format31i.add(instruction_format31i442.getTree());
			REGISTER443=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format31i5533);
			stream_REGISTER.add(REGISTER443);

			COMMA444=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format31i5535);
			stream_COMMA.add(COMMA444);

			pushFollow(FOLLOW_fixed_32bit_literal_in_insn_format31i5537);
			fixed_32bit_literal445=fixed_32bit_literal();
			state._fsp--;

			stream_fixed_32bit_literal.add(fixed_32bit_literal445.getTree());
			// AST REWRITE
			// elements: fixed_32bit_literal, instruction_format31i, REGISTER
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1068:5: -> ^( I_STATEMENT_FORMAT31i[$start, \"I_STATEMENT_FORMAT31i\"] instruction_format31i REGISTER fixed_32bit_literal )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1068:8: ^( I_STATEMENT_FORMAT31i[$start, \"I_STATEMENT_FORMAT31i\"] instruction_format31i REGISTER fixed_32bit_literal )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT31i, (retval.start), "I_STATEMENT_FORMAT31i"), root_1);
				adaptor.addChild(root_1, stream_instruction_format31i.nextTree());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_fixed_32bit_literal.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format31i"


	public static class insn_format31t_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format31t"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1070:1: insn_format31t : INSTRUCTION_FORMAT31t REGISTER COMMA label_ref -> ^( I_STATEMENT_FORMAT31t[$start, \"I_STATEMENT_FORMAT31t\"] INSTRUCTION_FORMAT31t REGISTER label_ref ) ;
	public final smaliParser.insn_format31t_return insn_format31t() throws RecognitionException {
		smaliParser.insn_format31t_return retval = new smaliParser.insn_format31t_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT31t446=null;
		Token REGISTER447=null;
		Token COMMA448=null;
		ParserRuleReturnScope label_ref449 =null;

		CommonTree INSTRUCTION_FORMAT31t446_tree=null;
		CommonTree REGISTER447_tree=null;
		CommonTree COMMA448_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT31t=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT31t");
		RewriteRuleSubtreeStream stream_label_ref=new RewriteRuleSubtreeStream(adaptor,"rule label_ref");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1071:3: ( INSTRUCTION_FORMAT31t REGISTER COMMA label_ref -> ^( I_STATEMENT_FORMAT31t[$start, \"I_STATEMENT_FORMAT31t\"] INSTRUCTION_FORMAT31t REGISTER label_ref ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1072:5: INSTRUCTION_FORMAT31t REGISTER COMMA label_ref
			{
			INSTRUCTION_FORMAT31t446=(Token)match(input,INSTRUCTION_FORMAT31t,FOLLOW_INSTRUCTION_FORMAT31t_in_insn_format31t5569);
			stream_INSTRUCTION_FORMAT31t.add(INSTRUCTION_FORMAT31t446);

			REGISTER447=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format31t5571);
			stream_REGISTER.add(REGISTER447);

			COMMA448=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format31t5573);
			stream_COMMA.add(COMMA448);

			pushFollow(FOLLOW_label_ref_in_insn_format31t5575);
			label_ref449=label_ref();
			state._fsp--;

			stream_label_ref.add(label_ref449.getTree());
			// AST REWRITE
			// elements: label_ref, INSTRUCTION_FORMAT31t, REGISTER
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1073:5: -> ^( I_STATEMENT_FORMAT31t[$start, \"I_STATEMENT_FORMAT31t\"] INSTRUCTION_FORMAT31t REGISTER label_ref )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1073:8: ^( I_STATEMENT_FORMAT31t[$start, \"I_STATEMENT_FORMAT31t\"] INSTRUCTION_FORMAT31t REGISTER label_ref )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT31t, (retval.start), "I_STATEMENT_FORMAT31t"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT31t.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_label_ref.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format31t"


	public static class insn_format32x_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format32x"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1075:1: insn_format32x : INSTRUCTION_FORMAT32x REGISTER COMMA REGISTER -> ^( I_STATEMENT_FORMAT32x[$start, \"I_STATEMENT_FORMAT32x\"] INSTRUCTION_FORMAT32x REGISTER REGISTER ) ;
	public final smaliParser.insn_format32x_return insn_format32x() throws RecognitionException {
		smaliParser.insn_format32x_return retval = new smaliParser.insn_format32x_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT32x450=null;
		Token REGISTER451=null;
		Token COMMA452=null;
		Token REGISTER453=null;

		CommonTree INSTRUCTION_FORMAT32x450_tree=null;
		CommonTree REGISTER451_tree=null;
		CommonTree COMMA452_tree=null;
		CommonTree REGISTER453_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT32x=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT32x");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1076:3: ( INSTRUCTION_FORMAT32x REGISTER COMMA REGISTER -> ^( I_STATEMENT_FORMAT32x[$start, \"I_STATEMENT_FORMAT32x\"] INSTRUCTION_FORMAT32x REGISTER REGISTER ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1077:5: INSTRUCTION_FORMAT32x REGISTER COMMA REGISTER
			{
			INSTRUCTION_FORMAT32x450=(Token)match(input,INSTRUCTION_FORMAT32x,FOLLOW_INSTRUCTION_FORMAT32x_in_insn_format32x5607);
			stream_INSTRUCTION_FORMAT32x.add(INSTRUCTION_FORMAT32x450);

			REGISTER451=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format32x5609);
			stream_REGISTER.add(REGISTER451);

			COMMA452=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format32x5611);
			stream_COMMA.add(COMMA452);

			REGISTER453=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format32x5613);
			stream_REGISTER.add(REGISTER453);

			// AST REWRITE
			// elements: REGISTER, INSTRUCTION_FORMAT32x, REGISTER
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1078:5: -> ^( I_STATEMENT_FORMAT32x[$start, \"I_STATEMENT_FORMAT32x\"] INSTRUCTION_FORMAT32x REGISTER REGISTER )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1078:8: ^( I_STATEMENT_FORMAT32x[$start, \"I_STATEMENT_FORMAT32x\"] INSTRUCTION_FORMAT32x REGISTER REGISTER )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT32x, (retval.start), "I_STATEMENT_FORMAT32x"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT32x.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format32x"


	public static class insn_format35c_call_site_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format35c_call_site"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1080:1: insn_format35c_call_site : INSTRUCTION_FORMAT35c_CALL_SITE OPEN_BRACE register_list CLOSE_BRACE COMMA call_site_reference -> ^( I_STATEMENT_FORMAT35c_CALL_SITE[$start, \"I_STATEMENT_FORMAT35c_CALL_SITE\"] INSTRUCTION_FORMAT35c_CALL_SITE register_list call_site_reference ) ;
	public final smaliParser.insn_format35c_call_site_return insn_format35c_call_site() throws RecognitionException {
		smaliParser.insn_format35c_call_site_return retval = new smaliParser.insn_format35c_call_site_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT35c_CALL_SITE454=null;
		Token OPEN_BRACE455=null;
		Token CLOSE_BRACE457=null;
		Token COMMA458=null;
		ParserRuleReturnScope register_list456 =null;
		ParserRuleReturnScope call_site_reference459 =null;

		CommonTree INSTRUCTION_FORMAT35c_CALL_SITE454_tree=null;
		CommonTree OPEN_BRACE455_tree=null;
		CommonTree CLOSE_BRACE457_tree=null;
		CommonTree COMMA458_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_OPEN_BRACE=new RewriteRuleTokenStream(adaptor,"token OPEN_BRACE");
		RewriteRuleTokenStream stream_CLOSE_BRACE=new RewriteRuleTokenStream(adaptor,"token CLOSE_BRACE");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35c_CALL_SITE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT35c_CALL_SITE");
		RewriteRuleSubtreeStream stream_register_list=new RewriteRuleSubtreeStream(adaptor,"rule register_list");
		RewriteRuleSubtreeStream stream_call_site_reference=new RewriteRuleSubtreeStream(adaptor,"rule call_site_reference");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1081:3: ( INSTRUCTION_FORMAT35c_CALL_SITE OPEN_BRACE register_list CLOSE_BRACE COMMA call_site_reference -> ^( I_STATEMENT_FORMAT35c_CALL_SITE[$start, \"I_STATEMENT_FORMAT35c_CALL_SITE\"] INSTRUCTION_FORMAT35c_CALL_SITE register_list call_site_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1083:5: INSTRUCTION_FORMAT35c_CALL_SITE OPEN_BRACE register_list CLOSE_BRACE COMMA call_site_reference
			{
			INSTRUCTION_FORMAT35c_CALL_SITE454=(Token)match(input,INSTRUCTION_FORMAT35c_CALL_SITE,FOLLOW_INSTRUCTION_FORMAT35c_CALL_SITE_in_insn_format35c_call_site5650);
			stream_INSTRUCTION_FORMAT35c_CALL_SITE.add(INSTRUCTION_FORMAT35c_CALL_SITE454);

			OPEN_BRACE455=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format35c_call_site5652);
			stream_OPEN_BRACE.add(OPEN_BRACE455);

			pushFollow(FOLLOW_register_list_in_insn_format35c_call_site5654);
			register_list456=register_list();
			state._fsp--;

			stream_register_list.add(register_list456.getTree());
			CLOSE_BRACE457=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format35c_call_site5656);
			stream_CLOSE_BRACE.add(CLOSE_BRACE457);

			COMMA458=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format35c_call_site5658);
			stream_COMMA.add(COMMA458);

			pushFollow(FOLLOW_call_site_reference_in_insn_format35c_call_site5660);
			call_site_reference459=call_site_reference();
			state._fsp--;

			stream_call_site_reference.add(call_site_reference459.getTree());
			// AST REWRITE
			// elements: call_site_reference, INSTRUCTION_FORMAT35c_CALL_SITE, register_list
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1084:5: -> ^( I_STATEMENT_FORMAT35c_CALL_SITE[$start, \"I_STATEMENT_FORMAT35c_CALL_SITE\"] INSTRUCTION_FORMAT35c_CALL_SITE register_list call_site_reference )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1084:8: ^( I_STATEMENT_FORMAT35c_CALL_SITE[$start, \"I_STATEMENT_FORMAT35c_CALL_SITE\"] INSTRUCTION_FORMAT35c_CALL_SITE register_list call_site_reference )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT35c_CALL_SITE, (retval.start), "I_STATEMENT_FORMAT35c_CALL_SITE"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT35c_CALL_SITE.nextNode());
				adaptor.addChild(root_1, stream_register_list.nextTree());
				adaptor.addChild(root_1, stream_call_site_reference.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format35c_call_site"


	public static class insn_format35c_method_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format35c_method"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1086:1: insn_format35c_method : instruction_format35c_method OPEN_BRACE register_list CLOSE_BRACE COMMA method_reference -> ^( I_STATEMENT_FORMAT35c_METHOD[$start, \"I_STATEMENT_FORMAT35c_METHOD\"] instruction_format35c_method register_list method_reference ) ;
	public final smaliParser.insn_format35c_method_return insn_format35c_method() throws RecognitionException {
		smaliParser.insn_format35c_method_return retval = new smaliParser.insn_format35c_method_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token OPEN_BRACE461=null;
		Token CLOSE_BRACE463=null;
		Token COMMA464=null;
		ParserRuleReturnScope instruction_format35c_method460 =null;
		ParserRuleReturnScope register_list462 =null;
		ParserRuleReturnScope method_reference465 =null;

		CommonTree OPEN_BRACE461_tree=null;
		CommonTree CLOSE_BRACE463_tree=null;
		CommonTree COMMA464_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_OPEN_BRACE=new RewriteRuleTokenStream(adaptor,"token OPEN_BRACE");
		RewriteRuleTokenStream stream_CLOSE_BRACE=new RewriteRuleTokenStream(adaptor,"token CLOSE_BRACE");
		RewriteRuleSubtreeStream stream_instruction_format35c_method=new RewriteRuleSubtreeStream(adaptor,"rule instruction_format35c_method");
		RewriteRuleSubtreeStream stream_method_reference=new RewriteRuleSubtreeStream(adaptor,"rule method_reference");
		RewriteRuleSubtreeStream stream_register_list=new RewriteRuleSubtreeStream(adaptor,"rule register_list");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1087:3: ( instruction_format35c_method OPEN_BRACE register_list CLOSE_BRACE COMMA method_reference -> ^( I_STATEMENT_FORMAT35c_METHOD[$start, \"I_STATEMENT_FORMAT35c_METHOD\"] instruction_format35c_method register_list method_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1088:5: instruction_format35c_method OPEN_BRACE register_list CLOSE_BRACE COMMA method_reference
			{
			pushFollow(FOLLOW_instruction_format35c_method_in_insn_format35c_method5692);
			instruction_format35c_method460=instruction_format35c_method();
			state._fsp--;

			stream_instruction_format35c_method.add(instruction_format35c_method460.getTree());
			OPEN_BRACE461=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format35c_method5694);
			stream_OPEN_BRACE.add(OPEN_BRACE461);

			pushFollow(FOLLOW_register_list_in_insn_format35c_method5696);
			register_list462=register_list();
			state._fsp--;

			stream_register_list.add(register_list462.getTree());
			CLOSE_BRACE463=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format35c_method5698);
			stream_CLOSE_BRACE.add(CLOSE_BRACE463);

			COMMA464=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format35c_method5700);
			stream_COMMA.add(COMMA464);

			pushFollow(FOLLOW_method_reference_in_insn_format35c_method5702);
			method_reference465=method_reference();
			state._fsp--;

			stream_method_reference.add(method_reference465.getTree());
			// AST REWRITE
			// elements: instruction_format35c_method, register_list, method_reference
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1089:5: -> ^( I_STATEMENT_FORMAT35c_METHOD[$start, \"I_STATEMENT_FORMAT35c_METHOD\"] instruction_format35c_method register_list method_reference )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1089:8: ^( I_STATEMENT_FORMAT35c_METHOD[$start, \"I_STATEMENT_FORMAT35c_METHOD\"] instruction_format35c_method register_list method_reference )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT35c_METHOD, (retval.start), "I_STATEMENT_FORMAT35c_METHOD"), root_1);
				adaptor.addChild(root_1, stream_instruction_format35c_method.nextTree());
				adaptor.addChild(root_1, stream_register_list.nextTree());
				adaptor.addChild(root_1, stream_method_reference.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format35c_method"


	public static class insn_format35c_type_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format35c_type"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1091:1: insn_format35c_type : INSTRUCTION_FORMAT35c_TYPE OPEN_BRACE register_list CLOSE_BRACE COMMA nonvoid_type_descriptor -> ^( I_STATEMENT_FORMAT35c_TYPE[$start, \"I_STATEMENT_FORMAT35c_TYPE\"] INSTRUCTION_FORMAT35c_TYPE register_list nonvoid_type_descriptor ) ;
	public final smaliParser.insn_format35c_type_return insn_format35c_type() throws RecognitionException {
		smaliParser.insn_format35c_type_return retval = new smaliParser.insn_format35c_type_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT35c_TYPE466=null;
		Token OPEN_BRACE467=null;
		Token CLOSE_BRACE469=null;
		Token COMMA470=null;
		ParserRuleReturnScope register_list468 =null;
		ParserRuleReturnScope nonvoid_type_descriptor471 =null;

		CommonTree INSTRUCTION_FORMAT35c_TYPE466_tree=null;
		CommonTree OPEN_BRACE467_tree=null;
		CommonTree CLOSE_BRACE469_tree=null;
		CommonTree COMMA470_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT35c_TYPE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT35c_TYPE");
		RewriteRuleTokenStream stream_OPEN_BRACE=new RewriteRuleTokenStream(adaptor,"token OPEN_BRACE");
		RewriteRuleTokenStream stream_CLOSE_BRACE=new RewriteRuleTokenStream(adaptor,"token CLOSE_BRACE");
		RewriteRuleSubtreeStream stream_register_list=new RewriteRuleSubtreeStream(adaptor,"rule register_list");
		RewriteRuleSubtreeStream stream_nonvoid_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule nonvoid_type_descriptor");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1092:3: ( INSTRUCTION_FORMAT35c_TYPE OPEN_BRACE register_list CLOSE_BRACE COMMA nonvoid_type_descriptor -> ^( I_STATEMENT_FORMAT35c_TYPE[$start, \"I_STATEMENT_FORMAT35c_TYPE\"] INSTRUCTION_FORMAT35c_TYPE register_list nonvoid_type_descriptor ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1093:5: INSTRUCTION_FORMAT35c_TYPE OPEN_BRACE register_list CLOSE_BRACE COMMA nonvoid_type_descriptor
			{
			INSTRUCTION_FORMAT35c_TYPE466=(Token)match(input,INSTRUCTION_FORMAT35c_TYPE,FOLLOW_INSTRUCTION_FORMAT35c_TYPE_in_insn_format35c_type5734);
			stream_INSTRUCTION_FORMAT35c_TYPE.add(INSTRUCTION_FORMAT35c_TYPE466);

			OPEN_BRACE467=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format35c_type5736);
			stream_OPEN_BRACE.add(OPEN_BRACE467);

			pushFollow(FOLLOW_register_list_in_insn_format35c_type5738);
			register_list468=register_list();
			state._fsp--;

			stream_register_list.add(register_list468.getTree());
			CLOSE_BRACE469=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format35c_type5740);
			stream_CLOSE_BRACE.add(CLOSE_BRACE469);

			COMMA470=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format35c_type5742);
			stream_COMMA.add(COMMA470);

			pushFollow(FOLLOW_nonvoid_type_descriptor_in_insn_format35c_type5744);
			nonvoid_type_descriptor471=nonvoid_type_descriptor();
			state._fsp--;

			stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor471.getTree());
			// AST REWRITE
			// elements: nonvoid_type_descriptor, register_list, INSTRUCTION_FORMAT35c_TYPE
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1094:5: -> ^( I_STATEMENT_FORMAT35c_TYPE[$start, \"I_STATEMENT_FORMAT35c_TYPE\"] INSTRUCTION_FORMAT35c_TYPE register_list nonvoid_type_descriptor )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1094:8: ^( I_STATEMENT_FORMAT35c_TYPE[$start, \"I_STATEMENT_FORMAT35c_TYPE\"] INSTRUCTION_FORMAT35c_TYPE register_list nonvoid_type_descriptor )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT35c_TYPE, (retval.start), "I_STATEMENT_FORMAT35c_TYPE"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT35c_TYPE.nextNode());
				adaptor.addChild(root_1, stream_register_list.nextTree());
				adaptor.addChild(root_1, stream_nonvoid_type_descriptor.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format35c_type"


	public static class insn_format35c_method_odex_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format35c_method_odex"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1096:1: insn_format35c_method_odex : INSTRUCTION_FORMAT35c_METHOD_ODEX OPEN_BRACE register_list CLOSE_BRACE COMMA method_reference ;
	public final smaliParser.insn_format35c_method_odex_return insn_format35c_method_odex() throws RecognitionException {
		smaliParser.insn_format35c_method_odex_return retval = new smaliParser.insn_format35c_method_odex_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT35c_METHOD_ODEX472=null;
		Token OPEN_BRACE473=null;
		Token CLOSE_BRACE475=null;
		Token COMMA476=null;
		ParserRuleReturnScope register_list474 =null;
		ParserRuleReturnScope method_reference477 =null;

		CommonTree INSTRUCTION_FORMAT35c_METHOD_ODEX472_tree=null;
		CommonTree OPEN_BRACE473_tree=null;
		CommonTree CLOSE_BRACE475_tree=null;
		CommonTree COMMA476_tree=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1097:3: ( INSTRUCTION_FORMAT35c_METHOD_ODEX OPEN_BRACE register_list CLOSE_BRACE COMMA method_reference )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1098:5: INSTRUCTION_FORMAT35c_METHOD_ODEX OPEN_BRACE register_list CLOSE_BRACE COMMA method_reference
			{
			root_0 = (CommonTree)adaptor.nil();


			INSTRUCTION_FORMAT35c_METHOD_ODEX472=(Token)match(input,INSTRUCTION_FORMAT35c_METHOD_ODEX,FOLLOW_INSTRUCTION_FORMAT35c_METHOD_ODEX_in_insn_format35c_method_odex5776);
			INSTRUCTION_FORMAT35c_METHOD_ODEX472_tree = (CommonTree)adaptor.create(INSTRUCTION_FORMAT35c_METHOD_ODEX472);
			adaptor.addChild(root_0, INSTRUCTION_FORMAT35c_METHOD_ODEX472_tree);

			OPEN_BRACE473=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format35c_method_odex5778);
			OPEN_BRACE473_tree = (CommonTree)adaptor.create(OPEN_BRACE473);
			adaptor.addChild(root_0, OPEN_BRACE473_tree);

			pushFollow(FOLLOW_register_list_in_insn_format35c_method_odex5780);
			register_list474=register_list();
			state._fsp--;

			adaptor.addChild(root_0, register_list474.getTree());

			CLOSE_BRACE475=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format35c_method_odex5782);
			CLOSE_BRACE475_tree = (CommonTree)adaptor.create(CLOSE_BRACE475);
			adaptor.addChild(root_0, CLOSE_BRACE475_tree);

			COMMA476=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format35c_method_odex5784);
			COMMA476_tree = (CommonTree)adaptor.create(COMMA476);
			adaptor.addChild(root_0, COMMA476_tree);

			pushFollow(FOLLOW_method_reference_in_insn_format35c_method_odex5786);
			method_reference477=method_reference();
			state._fsp--;

			adaptor.addChild(root_0, method_reference477.getTree());


			      throwOdexedInstructionException(input, (INSTRUCTION_FORMAT35c_METHOD_ODEX472!=null?INSTRUCTION_FORMAT35c_METHOD_ODEX472.getText():null));
			
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format35c_method_odex"


	public static class insn_format35mi_method_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format35mi_method"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1103:1: insn_format35mi_method : INSTRUCTION_FORMAT35mi_METHOD OPEN_BRACE register_list CLOSE_BRACE COMMA INLINE_INDEX ;
	public final smaliParser.insn_format35mi_method_return insn_format35mi_method() throws RecognitionException {
		smaliParser.insn_format35mi_method_return retval = new smaliParser.insn_format35mi_method_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT35mi_METHOD478=null;
		Token OPEN_BRACE479=null;
		Token CLOSE_BRACE481=null;
		Token COMMA482=null;
		Token INLINE_INDEX483=null;
		ParserRuleReturnScope register_list480 =null;

		CommonTree INSTRUCTION_FORMAT35mi_METHOD478_tree=null;
		CommonTree OPEN_BRACE479_tree=null;
		CommonTree CLOSE_BRACE481_tree=null;
		CommonTree COMMA482_tree=null;
		CommonTree INLINE_INDEX483_tree=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1104:3: ( INSTRUCTION_FORMAT35mi_METHOD OPEN_BRACE register_list CLOSE_BRACE COMMA INLINE_INDEX )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1105:5: INSTRUCTION_FORMAT35mi_METHOD OPEN_BRACE register_list CLOSE_BRACE COMMA INLINE_INDEX
			{
			root_0 = (CommonTree)adaptor.nil();


			INSTRUCTION_FORMAT35mi_METHOD478=(Token)match(input,INSTRUCTION_FORMAT35mi_METHOD,FOLLOW_INSTRUCTION_FORMAT35mi_METHOD_in_insn_format35mi_method5807);
			INSTRUCTION_FORMAT35mi_METHOD478_tree = (CommonTree)adaptor.create(INSTRUCTION_FORMAT35mi_METHOD478);
			adaptor.addChild(root_0, INSTRUCTION_FORMAT35mi_METHOD478_tree);

			OPEN_BRACE479=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format35mi_method5809);
			OPEN_BRACE479_tree = (CommonTree)adaptor.create(OPEN_BRACE479);
			adaptor.addChild(root_0, OPEN_BRACE479_tree);

			pushFollow(FOLLOW_register_list_in_insn_format35mi_method5811);
			register_list480=register_list();
			state._fsp--;

			adaptor.addChild(root_0, register_list480.getTree());

			CLOSE_BRACE481=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format35mi_method5813);
			CLOSE_BRACE481_tree = (CommonTree)adaptor.create(CLOSE_BRACE481);
			adaptor.addChild(root_0, CLOSE_BRACE481_tree);

			COMMA482=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format35mi_method5815);
			COMMA482_tree = (CommonTree)adaptor.create(COMMA482);
			adaptor.addChild(root_0, COMMA482_tree);

			INLINE_INDEX483=(Token)match(input,INLINE_INDEX,FOLLOW_INLINE_INDEX_in_insn_format35mi_method5817);
			INLINE_INDEX483_tree = (CommonTree)adaptor.create(INLINE_INDEX483);
			adaptor.addChild(root_0, INLINE_INDEX483_tree);


			      throwOdexedInstructionException(input, (INSTRUCTION_FORMAT35mi_METHOD478!=null?INSTRUCTION_FORMAT35mi_METHOD478.getText():null));
			
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format35mi_method"


	public static class insn_format35ms_method_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format35ms_method"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1110:1: insn_format35ms_method : INSTRUCTION_FORMAT35ms_METHOD OPEN_BRACE register_list CLOSE_BRACE COMMA VTABLE_INDEX ;
	public final smaliParser.insn_format35ms_method_return insn_format35ms_method() throws RecognitionException {
		smaliParser.insn_format35ms_method_return retval = new smaliParser.insn_format35ms_method_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT35ms_METHOD484=null;
		Token OPEN_BRACE485=null;
		Token CLOSE_BRACE487=null;
		Token COMMA488=null;
		Token VTABLE_INDEX489=null;
		ParserRuleReturnScope register_list486 =null;

		CommonTree INSTRUCTION_FORMAT35ms_METHOD484_tree=null;
		CommonTree OPEN_BRACE485_tree=null;
		CommonTree CLOSE_BRACE487_tree=null;
		CommonTree COMMA488_tree=null;
		CommonTree VTABLE_INDEX489_tree=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1111:3: ( INSTRUCTION_FORMAT35ms_METHOD OPEN_BRACE register_list CLOSE_BRACE COMMA VTABLE_INDEX )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1112:5: INSTRUCTION_FORMAT35ms_METHOD OPEN_BRACE register_list CLOSE_BRACE COMMA VTABLE_INDEX
			{
			root_0 = (CommonTree)adaptor.nil();


			INSTRUCTION_FORMAT35ms_METHOD484=(Token)match(input,INSTRUCTION_FORMAT35ms_METHOD,FOLLOW_INSTRUCTION_FORMAT35ms_METHOD_in_insn_format35ms_method5838);
			INSTRUCTION_FORMAT35ms_METHOD484_tree = (CommonTree)adaptor.create(INSTRUCTION_FORMAT35ms_METHOD484);
			adaptor.addChild(root_0, INSTRUCTION_FORMAT35ms_METHOD484_tree);

			OPEN_BRACE485=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format35ms_method5840);
			OPEN_BRACE485_tree = (CommonTree)adaptor.create(OPEN_BRACE485);
			adaptor.addChild(root_0, OPEN_BRACE485_tree);

			pushFollow(FOLLOW_register_list_in_insn_format35ms_method5842);
			register_list486=register_list();
			state._fsp--;

			adaptor.addChild(root_0, register_list486.getTree());

			CLOSE_BRACE487=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format35ms_method5844);
			CLOSE_BRACE487_tree = (CommonTree)adaptor.create(CLOSE_BRACE487);
			adaptor.addChild(root_0, CLOSE_BRACE487_tree);

			COMMA488=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format35ms_method5846);
			COMMA488_tree = (CommonTree)adaptor.create(COMMA488);
			adaptor.addChild(root_0, COMMA488_tree);

			VTABLE_INDEX489=(Token)match(input,VTABLE_INDEX,FOLLOW_VTABLE_INDEX_in_insn_format35ms_method5848);
			VTABLE_INDEX489_tree = (CommonTree)adaptor.create(VTABLE_INDEX489);
			adaptor.addChild(root_0, VTABLE_INDEX489_tree);


			      throwOdexedInstructionException(input, (INSTRUCTION_FORMAT35ms_METHOD484!=null?INSTRUCTION_FORMAT35ms_METHOD484.getText():null));
			
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format35ms_method"


	public static class insn_format3rc_call_site_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format3rc_call_site"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1117:1: insn_format3rc_call_site : INSTRUCTION_FORMAT3rc_CALL_SITE OPEN_BRACE register_range CLOSE_BRACE COMMA call_site_reference -> ^( I_STATEMENT_FORMAT3rc_CALL_SITE[$start, \"I_STATEMENT_FORMAT3rc_CALL_SITE\"] INSTRUCTION_FORMAT3rc_CALL_SITE register_range call_site_reference ) ;
	public final smaliParser.insn_format3rc_call_site_return insn_format3rc_call_site() throws RecognitionException {
		smaliParser.insn_format3rc_call_site_return retval = new smaliParser.insn_format3rc_call_site_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT3rc_CALL_SITE490=null;
		Token OPEN_BRACE491=null;
		Token CLOSE_BRACE493=null;
		Token COMMA494=null;
		ParserRuleReturnScope register_range492 =null;
		ParserRuleReturnScope call_site_reference495 =null;

		CommonTree INSTRUCTION_FORMAT3rc_CALL_SITE490_tree=null;
		CommonTree OPEN_BRACE491_tree=null;
		CommonTree CLOSE_BRACE493_tree=null;
		CommonTree COMMA494_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_OPEN_BRACE=new RewriteRuleTokenStream(adaptor,"token OPEN_BRACE");
		RewriteRuleTokenStream stream_CLOSE_BRACE=new RewriteRuleTokenStream(adaptor,"token CLOSE_BRACE");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT3rc_CALL_SITE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT3rc_CALL_SITE");
		RewriteRuleSubtreeStream stream_register_range=new RewriteRuleSubtreeStream(adaptor,"rule register_range");
		RewriteRuleSubtreeStream stream_call_site_reference=new RewriteRuleSubtreeStream(adaptor,"rule call_site_reference");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1118:3: ( INSTRUCTION_FORMAT3rc_CALL_SITE OPEN_BRACE register_range CLOSE_BRACE COMMA call_site_reference -> ^( I_STATEMENT_FORMAT3rc_CALL_SITE[$start, \"I_STATEMENT_FORMAT3rc_CALL_SITE\"] INSTRUCTION_FORMAT3rc_CALL_SITE register_range call_site_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1120:5: INSTRUCTION_FORMAT3rc_CALL_SITE OPEN_BRACE register_range CLOSE_BRACE COMMA call_site_reference
			{
			INSTRUCTION_FORMAT3rc_CALL_SITE490=(Token)match(input,INSTRUCTION_FORMAT3rc_CALL_SITE,FOLLOW_INSTRUCTION_FORMAT3rc_CALL_SITE_in_insn_format3rc_call_site5874);
			stream_INSTRUCTION_FORMAT3rc_CALL_SITE.add(INSTRUCTION_FORMAT3rc_CALL_SITE490);

			OPEN_BRACE491=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format3rc_call_site5876);
			stream_OPEN_BRACE.add(OPEN_BRACE491);

			pushFollow(FOLLOW_register_range_in_insn_format3rc_call_site5878);
			register_range492=register_range();
			state._fsp--;

			stream_register_range.add(register_range492.getTree());
			CLOSE_BRACE493=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format3rc_call_site5880);
			stream_CLOSE_BRACE.add(CLOSE_BRACE493);

			COMMA494=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format3rc_call_site5882);
			stream_COMMA.add(COMMA494);

			pushFollow(FOLLOW_call_site_reference_in_insn_format3rc_call_site5884);
			call_site_reference495=call_site_reference();
			state._fsp--;

			stream_call_site_reference.add(call_site_reference495.getTree());
			// AST REWRITE
			// elements: call_site_reference, register_range, INSTRUCTION_FORMAT3rc_CALL_SITE
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1121:5: -> ^( I_STATEMENT_FORMAT3rc_CALL_SITE[$start, \"I_STATEMENT_FORMAT3rc_CALL_SITE\"] INSTRUCTION_FORMAT3rc_CALL_SITE register_range call_site_reference )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1121:8: ^( I_STATEMENT_FORMAT3rc_CALL_SITE[$start, \"I_STATEMENT_FORMAT3rc_CALL_SITE\"] INSTRUCTION_FORMAT3rc_CALL_SITE register_range call_site_reference )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT3rc_CALL_SITE, (retval.start), "I_STATEMENT_FORMAT3rc_CALL_SITE"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT3rc_CALL_SITE.nextNode());
				adaptor.addChild(root_1, stream_register_range.nextTree());
				adaptor.addChild(root_1, stream_call_site_reference.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format3rc_call_site"


	public static class insn_format3rc_method_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format3rc_method"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1123:1: insn_format3rc_method : INSTRUCTION_FORMAT3rc_METHOD OPEN_BRACE register_range CLOSE_BRACE COMMA method_reference -> ^( I_STATEMENT_FORMAT3rc_METHOD[$start, \"I_STATEMENT_FORMAT3rc_METHOD\"] INSTRUCTION_FORMAT3rc_METHOD register_range method_reference ) ;
	public final smaliParser.insn_format3rc_method_return insn_format3rc_method() throws RecognitionException {
		smaliParser.insn_format3rc_method_return retval = new smaliParser.insn_format3rc_method_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT3rc_METHOD496=null;
		Token OPEN_BRACE497=null;
		Token CLOSE_BRACE499=null;
		Token COMMA500=null;
		ParserRuleReturnScope register_range498 =null;
		ParserRuleReturnScope method_reference501 =null;

		CommonTree INSTRUCTION_FORMAT3rc_METHOD496_tree=null;
		CommonTree OPEN_BRACE497_tree=null;
		CommonTree CLOSE_BRACE499_tree=null;
		CommonTree COMMA500_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT3rc_METHOD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT3rc_METHOD");
		RewriteRuleTokenStream stream_OPEN_BRACE=new RewriteRuleTokenStream(adaptor,"token OPEN_BRACE");
		RewriteRuleTokenStream stream_CLOSE_BRACE=new RewriteRuleTokenStream(adaptor,"token CLOSE_BRACE");
		RewriteRuleSubtreeStream stream_method_reference=new RewriteRuleSubtreeStream(adaptor,"rule method_reference");
		RewriteRuleSubtreeStream stream_register_range=new RewriteRuleSubtreeStream(adaptor,"rule register_range");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1124:3: ( INSTRUCTION_FORMAT3rc_METHOD OPEN_BRACE register_range CLOSE_BRACE COMMA method_reference -> ^( I_STATEMENT_FORMAT3rc_METHOD[$start, \"I_STATEMENT_FORMAT3rc_METHOD\"] INSTRUCTION_FORMAT3rc_METHOD register_range method_reference ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1125:5: INSTRUCTION_FORMAT3rc_METHOD OPEN_BRACE register_range CLOSE_BRACE COMMA method_reference
			{
			INSTRUCTION_FORMAT3rc_METHOD496=(Token)match(input,INSTRUCTION_FORMAT3rc_METHOD,FOLLOW_INSTRUCTION_FORMAT3rc_METHOD_in_insn_format3rc_method5916);
			stream_INSTRUCTION_FORMAT3rc_METHOD.add(INSTRUCTION_FORMAT3rc_METHOD496);

			OPEN_BRACE497=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format3rc_method5918);
			stream_OPEN_BRACE.add(OPEN_BRACE497);

			pushFollow(FOLLOW_register_range_in_insn_format3rc_method5920);
			register_range498=register_range();
			state._fsp--;

			stream_register_range.add(register_range498.getTree());
			CLOSE_BRACE499=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format3rc_method5922);
			stream_CLOSE_BRACE.add(CLOSE_BRACE499);

			COMMA500=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format3rc_method5924);
			stream_COMMA.add(COMMA500);

			pushFollow(FOLLOW_method_reference_in_insn_format3rc_method5926);
			method_reference501=method_reference();
			state._fsp--;

			stream_method_reference.add(method_reference501.getTree());
			// AST REWRITE
			// elements: register_range, method_reference, INSTRUCTION_FORMAT3rc_METHOD
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1126:5: -> ^( I_STATEMENT_FORMAT3rc_METHOD[$start, \"I_STATEMENT_FORMAT3rc_METHOD\"] INSTRUCTION_FORMAT3rc_METHOD register_range method_reference )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1126:8: ^( I_STATEMENT_FORMAT3rc_METHOD[$start, \"I_STATEMENT_FORMAT3rc_METHOD\"] INSTRUCTION_FORMAT3rc_METHOD register_range method_reference )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT3rc_METHOD, (retval.start), "I_STATEMENT_FORMAT3rc_METHOD"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT3rc_METHOD.nextNode());
				adaptor.addChild(root_1, stream_register_range.nextTree());
				adaptor.addChild(root_1, stream_method_reference.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format3rc_method"


	public static class insn_format3rc_method_odex_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format3rc_method_odex"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1128:1: insn_format3rc_method_odex : INSTRUCTION_FORMAT3rc_METHOD_ODEX OPEN_BRACE register_list CLOSE_BRACE COMMA method_reference ;
	public final smaliParser.insn_format3rc_method_odex_return insn_format3rc_method_odex() throws RecognitionException {
		smaliParser.insn_format3rc_method_odex_return retval = new smaliParser.insn_format3rc_method_odex_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT3rc_METHOD_ODEX502=null;
		Token OPEN_BRACE503=null;
		Token CLOSE_BRACE505=null;
		Token COMMA506=null;
		ParserRuleReturnScope register_list504 =null;
		ParserRuleReturnScope method_reference507 =null;

		CommonTree INSTRUCTION_FORMAT3rc_METHOD_ODEX502_tree=null;
		CommonTree OPEN_BRACE503_tree=null;
		CommonTree CLOSE_BRACE505_tree=null;
		CommonTree COMMA506_tree=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1129:3: ( INSTRUCTION_FORMAT3rc_METHOD_ODEX OPEN_BRACE register_list CLOSE_BRACE COMMA method_reference )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1130:5: INSTRUCTION_FORMAT3rc_METHOD_ODEX OPEN_BRACE register_list CLOSE_BRACE COMMA method_reference
			{
			root_0 = (CommonTree)adaptor.nil();


			INSTRUCTION_FORMAT3rc_METHOD_ODEX502=(Token)match(input,INSTRUCTION_FORMAT3rc_METHOD_ODEX,FOLLOW_INSTRUCTION_FORMAT3rc_METHOD_ODEX_in_insn_format3rc_method_odex5958);
			INSTRUCTION_FORMAT3rc_METHOD_ODEX502_tree = (CommonTree)adaptor.create(INSTRUCTION_FORMAT3rc_METHOD_ODEX502);
			adaptor.addChild(root_0, INSTRUCTION_FORMAT3rc_METHOD_ODEX502_tree);

			OPEN_BRACE503=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format3rc_method_odex5960);
			OPEN_BRACE503_tree = (CommonTree)adaptor.create(OPEN_BRACE503);
			adaptor.addChild(root_0, OPEN_BRACE503_tree);

			pushFollow(FOLLOW_register_list_in_insn_format3rc_method_odex5962);
			register_list504=register_list();
			state._fsp--;

			adaptor.addChild(root_0, register_list504.getTree());

			CLOSE_BRACE505=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format3rc_method_odex5964);
			CLOSE_BRACE505_tree = (CommonTree)adaptor.create(CLOSE_BRACE505);
			adaptor.addChild(root_0, CLOSE_BRACE505_tree);

			COMMA506=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format3rc_method_odex5966);
			COMMA506_tree = (CommonTree)adaptor.create(COMMA506);
			adaptor.addChild(root_0, COMMA506_tree);

			pushFollow(FOLLOW_method_reference_in_insn_format3rc_method_odex5968);
			method_reference507=method_reference();
			state._fsp--;

			adaptor.addChild(root_0, method_reference507.getTree());


			      throwOdexedInstructionException(input, (INSTRUCTION_FORMAT3rc_METHOD_ODEX502!=null?INSTRUCTION_FORMAT3rc_METHOD_ODEX502.getText():null));
			
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format3rc_method_odex"


	public static class insn_format3rc_type_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format3rc_type"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1135:1: insn_format3rc_type : INSTRUCTION_FORMAT3rc_TYPE OPEN_BRACE register_range CLOSE_BRACE COMMA nonvoid_type_descriptor -> ^( I_STATEMENT_FORMAT3rc_TYPE[$start, \"I_STATEMENT_FORMAT3rc_TYPE\"] INSTRUCTION_FORMAT3rc_TYPE register_range nonvoid_type_descriptor ) ;
	public final smaliParser.insn_format3rc_type_return insn_format3rc_type() throws RecognitionException {
		smaliParser.insn_format3rc_type_return retval = new smaliParser.insn_format3rc_type_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT3rc_TYPE508=null;
		Token OPEN_BRACE509=null;
		Token CLOSE_BRACE511=null;
		Token COMMA512=null;
		ParserRuleReturnScope register_range510 =null;
		ParserRuleReturnScope nonvoid_type_descriptor513 =null;

		CommonTree INSTRUCTION_FORMAT3rc_TYPE508_tree=null;
		CommonTree OPEN_BRACE509_tree=null;
		CommonTree CLOSE_BRACE511_tree=null;
		CommonTree COMMA512_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_OPEN_BRACE=new RewriteRuleTokenStream(adaptor,"token OPEN_BRACE");
		RewriteRuleTokenStream stream_CLOSE_BRACE=new RewriteRuleTokenStream(adaptor,"token CLOSE_BRACE");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT3rc_TYPE=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT3rc_TYPE");
		RewriteRuleSubtreeStream stream_nonvoid_type_descriptor=new RewriteRuleSubtreeStream(adaptor,"rule nonvoid_type_descriptor");
		RewriteRuleSubtreeStream stream_register_range=new RewriteRuleSubtreeStream(adaptor,"rule register_range");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1136:3: ( INSTRUCTION_FORMAT3rc_TYPE OPEN_BRACE register_range CLOSE_BRACE COMMA nonvoid_type_descriptor -> ^( I_STATEMENT_FORMAT3rc_TYPE[$start, \"I_STATEMENT_FORMAT3rc_TYPE\"] INSTRUCTION_FORMAT3rc_TYPE register_range nonvoid_type_descriptor ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1137:5: INSTRUCTION_FORMAT3rc_TYPE OPEN_BRACE register_range CLOSE_BRACE COMMA nonvoid_type_descriptor
			{
			INSTRUCTION_FORMAT3rc_TYPE508=(Token)match(input,INSTRUCTION_FORMAT3rc_TYPE,FOLLOW_INSTRUCTION_FORMAT3rc_TYPE_in_insn_format3rc_type5989);
			stream_INSTRUCTION_FORMAT3rc_TYPE.add(INSTRUCTION_FORMAT3rc_TYPE508);

			OPEN_BRACE509=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format3rc_type5991);
			stream_OPEN_BRACE.add(OPEN_BRACE509);

			pushFollow(FOLLOW_register_range_in_insn_format3rc_type5993);
			register_range510=register_range();
			state._fsp--;

			stream_register_range.add(register_range510.getTree());
			CLOSE_BRACE511=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format3rc_type5995);
			stream_CLOSE_BRACE.add(CLOSE_BRACE511);

			COMMA512=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format3rc_type5997);
			stream_COMMA.add(COMMA512);

			pushFollow(FOLLOW_nonvoid_type_descriptor_in_insn_format3rc_type5999);
			nonvoid_type_descriptor513=nonvoid_type_descriptor();
			state._fsp--;

			stream_nonvoid_type_descriptor.add(nonvoid_type_descriptor513.getTree());
			// AST REWRITE
			// elements: INSTRUCTION_FORMAT3rc_TYPE, nonvoid_type_descriptor, register_range
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1138:5: -> ^( I_STATEMENT_FORMAT3rc_TYPE[$start, \"I_STATEMENT_FORMAT3rc_TYPE\"] INSTRUCTION_FORMAT3rc_TYPE register_range nonvoid_type_descriptor )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1138:8: ^( I_STATEMENT_FORMAT3rc_TYPE[$start, \"I_STATEMENT_FORMAT3rc_TYPE\"] INSTRUCTION_FORMAT3rc_TYPE register_range nonvoid_type_descriptor )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT3rc_TYPE, (retval.start), "I_STATEMENT_FORMAT3rc_TYPE"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT3rc_TYPE.nextNode());
				adaptor.addChild(root_1, stream_register_range.nextTree());
				adaptor.addChild(root_1, stream_nonvoid_type_descriptor.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format3rc_type"


	public static class insn_format3rmi_method_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format3rmi_method"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1140:1: insn_format3rmi_method : INSTRUCTION_FORMAT3rmi_METHOD OPEN_BRACE register_range CLOSE_BRACE COMMA INLINE_INDEX ;
	public final smaliParser.insn_format3rmi_method_return insn_format3rmi_method() throws RecognitionException {
		smaliParser.insn_format3rmi_method_return retval = new smaliParser.insn_format3rmi_method_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT3rmi_METHOD514=null;
		Token OPEN_BRACE515=null;
		Token CLOSE_BRACE517=null;
		Token COMMA518=null;
		Token INLINE_INDEX519=null;
		ParserRuleReturnScope register_range516 =null;

		CommonTree INSTRUCTION_FORMAT3rmi_METHOD514_tree=null;
		CommonTree OPEN_BRACE515_tree=null;
		CommonTree CLOSE_BRACE517_tree=null;
		CommonTree COMMA518_tree=null;
		CommonTree INLINE_INDEX519_tree=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1141:3: ( INSTRUCTION_FORMAT3rmi_METHOD OPEN_BRACE register_range CLOSE_BRACE COMMA INLINE_INDEX )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1142:5: INSTRUCTION_FORMAT3rmi_METHOD OPEN_BRACE register_range CLOSE_BRACE COMMA INLINE_INDEX
			{
			root_0 = (CommonTree)adaptor.nil();


			INSTRUCTION_FORMAT3rmi_METHOD514=(Token)match(input,INSTRUCTION_FORMAT3rmi_METHOD,FOLLOW_INSTRUCTION_FORMAT3rmi_METHOD_in_insn_format3rmi_method6031);
			INSTRUCTION_FORMAT3rmi_METHOD514_tree = (CommonTree)adaptor.create(INSTRUCTION_FORMAT3rmi_METHOD514);
			adaptor.addChild(root_0, INSTRUCTION_FORMAT3rmi_METHOD514_tree);

			OPEN_BRACE515=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format3rmi_method6033);
			OPEN_BRACE515_tree = (CommonTree)adaptor.create(OPEN_BRACE515);
			adaptor.addChild(root_0, OPEN_BRACE515_tree);

			pushFollow(FOLLOW_register_range_in_insn_format3rmi_method6035);
			register_range516=register_range();
			state._fsp--;

			adaptor.addChild(root_0, register_range516.getTree());

			CLOSE_BRACE517=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format3rmi_method6037);
			CLOSE_BRACE517_tree = (CommonTree)adaptor.create(CLOSE_BRACE517);
			adaptor.addChild(root_0, CLOSE_BRACE517_tree);

			COMMA518=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format3rmi_method6039);
			COMMA518_tree = (CommonTree)adaptor.create(COMMA518);
			adaptor.addChild(root_0, COMMA518_tree);

			INLINE_INDEX519=(Token)match(input,INLINE_INDEX,FOLLOW_INLINE_INDEX_in_insn_format3rmi_method6041);
			INLINE_INDEX519_tree = (CommonTree)adaptor.create(INLINE_INDEX519);
			adaptor.addChild(root_0, INLINE_INDEX519_tree);


			      throwOdexedInstructionException(input, (INSTRUCTION_FORMAT3rmi_METHOD514!=null?INSTRUCTION_FORMAT3rmi_METHOD514.getText():null));
			
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format3rmi_method"


	public static class insn_format3rms_method_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format3rms_method"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1147:1: insn_format3rms_method : INSTRUCTION_FORMAT3rms_METHOD OPEN_BRACE register_range CLOSE_BRACE COMMA VTABLE_INDEX ;
	public final smaliParser.insn_format3rms_method_return insn_format3rms_method() throws RecognitionException {
		smaliParser.insn_format3rms_method_return retval = new smaliParser.insn_format3rms_method_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT3rms_METHOD520=null;
		Token OPEN_BRACE521=null;
		Token CLOSE_BRACE523=null;
		Token COMMA524=null;
		Token VTABLE_INDEX525=null;
		ParserRuleReturnScope register_range522 =null;

		CommonTree INSTRUCTION_FORMAT3rms_METHOD520_tree=null;
		CommonTree OPEN_BRACE521_tree=null;
		CommonTree CLOSE_BRACE523_tree=null;
		CommonTree COMMA524_tree=null;
		CommonTree VTABLE_INDEX525_tree=null;

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1148:3: ( INSTRUCTION_FORMAT3rms_METHOD OPEN_BRACE register_range CLOSE_BRACE COMMA VTABLE_INDEX )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1149:5: INSTRUCTION_FORMAT3rms_METHOD OPEN_BRACE register_range CLOSE_BRACE COMMA VTABLE_INDEX
			{
			root_0 = (CommonTree)adaptor.nil();


			INSTRUCTION_FORMAT3rms_METHOD520=(Token)match(input,INSTRUCTION_FORMAT3rms_METHOD,FOLLOW_INSTRUCTION_FORMAT3rms_METHOD_in_insn_format3rms_method6062);
			INSTRUCTION_FORMAT3rms_METHOD520_tree = (CommonTree)adaptor.create(INSTRUCTION_FORMAT3rms_METHOD520);
			adaptor.addChild(root_0, INSTRUCTION_FORMAT3rms_METHOD520_tree);

			OPEN_BRACE521=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format3rms_method6064);
			OPEN_BRACE521_tree = (CommonTree)adaptor.create(OPEN_BRACE521);
			adaptor.addChild(root_0, OPEN_BRACE521_tree);

			pushFollow(FOLLOW_register_range_in_insn_format3rms_method6066);
			register_range522=register_range();
			state._fsp--;

			adaptor.addChild(root_0, register_range522.getTree());

			CLOSE_BRACE523=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format3rms_method6068);
			CLOSE_BRACE523_tree = (CommonTree)adaptor.create(CLOSE_BRACE523);
			adaptor.addChild(root_0, CLOSE_BRACE523_tree);

			COMMA524=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format3rms_method6070);
			COMMA524_tree = (CommonTree)adaptor.create(COMMA524);
			adaptor.addChild(root_0, COMMA524_tree);

			VTABLE_INDEX525=(Token)match(input,VTABLE_INDEX,FOLLOW_VTABLE_INDEX_in_insn_format3rms_method6072);
			VTABLE_INDEX525_tree = (CommonTree)adaptor.create(VTABLE_INDEX525);
			adaptor.addChild(root_0, VTABLE_INDEX525_tree);


			      throwOdexedInstructionException(input, (INSTRUCTION_FORMAT3rms_METHOD520!=null?INSTRUCTION_FORMAT3rms_METHOD520.getText():null));
			
			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format3rms_method"


	public static class insn_format45cc_method_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format45cc_method"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1154:1: insn_format45cc_method : INSTRUCTION_FORMAT45cc_METHOD OPEN_BRACE register_list CLOSE_BRACE COMMA method_reference COMMA method_prototype -> ^( I_STATEMENT_FORMAT45cc_METHOD[$start, \"I_STATEMENT_FORMAT45cc_METHOD\"] INSTRUCTION_FORMAT45cc_METHOD register_list method_reference method_prototype ) ;
	public final smaliParser.insn_format45cc_method_return insn_format45cc_method() throws RecognitionException {
		smaliParser.insn_format45cc_method_return retval = new smaliParser.insn_format45cc_method_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT45cc_METHOD526=null;
		Token OPEN_BRACE527=null;
		Token CLOSE_BRACE529=null;
		Token COMMA530=null;
		Token COMMA532=null;
		ParserRuleReturnScope register_list528 =null;
		ParserRuleReturnScope method_reference531 =null;
		ParserRuleReturnScope method_prototype533 =null;

		CommonTree INSTRUCTION_FORMAT45cc_METHOD526_tree=null;
		CommonTree OPEN_BRACE527_tree=null;
		CommonTree CLOSE_BRACE529_tree=null;
		CommonTree COMMA530_tree=null;
		CommonTree COMMA532_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT45cc_METHOD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT45cc_METHOD");
		RewriteRuleTokenStream stream_OPEN_BRACE=new RewriteRuleTokenStream(adaptor,"token OPEN_BRACE");
		RewriteRuleTokenStream stream_CLOSE_BRACE=new RewriteRuleTokenStream(adaptor,"token CLOSE_BRACE");
		RewriteRuleSubtreeStream stream_method_reference=new RewriteRuleSubtreeStream(adaptor,"rule method_reference");
		RewriteRuleSubtreeStream stream_method_prototype=new RewriteRuleSubtreeStream(adaptor,"rule method_prototype");
		RewriteRuleSubtreeStream stream_register_list=new RewriteRuleSubtreeStream(adaptor,"rule register_list");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1155:3: ( INSTRUCTION_FORMAT45cc_METHOD OPEN_BRACE register_list CLOSE_BRACE COMMA method_reference COMMA method_prototype -> ^( I_STATEMENT_FORMAT45cc_METHOD[$start, \"I_STATEMENT_FORMAT45cc_METHOD\"] INSTRUCTION_FORMAT45cc_METHOD register_list method_reference method_prototype ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1156:5: INSTRUCTION_FORMAT45cc_METHOD OPEN_BRACE register_list CLOSE_BRACE COMMA method_reference COMMA method_prototype
			{
			INSTRUCTION_FORMAT45cc_METHOD526=(Token)match(input,INSTRUCTION_FORMAT45cc_METHOD,FOLLOW_INSTRUCTION_FORMAT45cc_METHOD_in_insn_format45cc_method6093);
			stream_INSTRUCTION_FORMAT45cc_METHOD.add(INSTRUCTION_FORMAT45cc_METHOD526);

			OPEN_BRACE527=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format45cc_method6095);
			stream_OPEN_BRACE.add(OPEN_BRACE527);

			pushFollow(FOLLOW_register_list_in_insn_format45cc_method6097);
			register_list528=register_list();
			state._fsp--;

			stream_register_list.add(register_list528.getTree());
			CLOSE_BRACE529=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format45cc_method6099);
			stream_CLOSE_BRACE.add(CLOSE_BRACE529);

			COMMA530=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format45cc_method6101);
			stream_COMMA.add(COMMA530);

			pushFollow(FOLLOW_method_reference_in_insn_format45cc_method6103);
			method_reference531=method_reference();
			state._fsp--;

			stream_method_reference.add(method_reference531.getTree());
			COMMA532=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format45cc_method6105);
			stream_COMMA.add(COMMA532);

			pushFollow(FOLLOW_method_prototype_in_insn_format45cc_method6107);
			method_prototype533=method_prototype();
			state._fsp--;

			stream_method_prototype.add(method_prototype533.getTree());
			// AST REWRITE
			// elements: INSTRUCTION_FORMAT45cc_METHOD, method_reference, method_prototype, register_list
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1157:5: -> ^( I_STATEMENT_FORMAT45cc_METHOD[$start, \"I_STATEMENT_FORMAT45cc_METHOD\"] INSTRUCTION_FORMAT45cc_METHOD register_list method_reference method_prototype )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1157:8: ^( I_STATEMENT_FORMAT45cc_METHOD[$start, \"I_STATEMENT_FORMAT45cc_METHOD\"] INSTRUCTION_FORMAT45cc_METHOD register_list method_reference method_prototype )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT45cc_METHOD, (retval.start), "I_STATEMENT_FORMAT45cc_METHOD"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT45cc_METHOD.nextNode());
				adaptor.addChild(root_1, stream_register_list.nextTree());
				adaptor.addChild(root_1, stream_method_reference.nextTree());
				adaptor.addChild(root_1, stream_method_prototype.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format45cc_method"


	public static class insn_format4rcc_method_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format4rcc_method"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1159:1: insn_format4rcc_method : INSTRUCTION_FORMAT4rcc_METHOD OPEN_BRACE register_range CLOSE_BRACE COMMA method_reference COMMA method_prototype -> ^( I_STATEMENT_FORMAT4rcc_METHOD[$start, \"I_STATEMENT_FORMAT4rcc_METHOD\"] INSTRUCTION_FORMAT4rcc_METHOD register_range method_reference method_prototype ) ;
	public final smaliParser.insn_format4rcc_method_return insn_format4rcc_method() throws RecognitionException {
		smaliParser.insn_format4rcc_method_return retval = new smaliParser.insn_format4rcc_method_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT4rcc_METHOD534=null;
		Token OPEN_BRACE535=null;
		Token CLOSE_BRACE537=null;
		Token COMMA538=null;
		Token COMMA540=null;
		ParserRuleReturnScope register_range536 =null;
		ParserRuleReturnScope method_reference539 =null;
		ParserRuleReturnScope method_prototype541 =null;

		CommonTree INSTRUCTION_FORMAT4rcc_METHOD534_tree=null;
		CommonTree OPEN_BRACE535_tree=null;
		CommonTree CLOSE_BRACE537_tree=null;
		CommonTree COMMA538_tree=null;
		CommonTree COMMA540_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_OPEN_BRACE=new RewriteRuleTokenStream(adaptor,"token OPEN_BRACE");
		RewriteRuleTokenStream stream_CLOSE_BRACE=new RewriteRuleTokenStream(adaptor,"token CLOSE_BRACE");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT4rcc_METHOD=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT4rcc_METHOD");
		RewriteRuleSubtreeStream stream_method_reference=new RewriteRuleSubtreeStream(adaptor,"rule method_reference");
		RewriteRuleSubtreeStream stream_method_prototype=new RewriteRuleSubtreeStream(adaptor,"rule method_prototype");
		RewriteRuleSubtreeStream stream_register_range=new RewriteRuleSubtreeStream(adaptor,"rule register_range");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1160:3: ( INSTRUCTION_FORMAT4rcc_METHOD OPEN_BRACE register_range CLOSE_BRACE COMMA method_reference COMMA method_prototype -> ^( I_STATEMENT_FORMAT4rcc_METHOD[$start, \"I_STATEMENT_FORMAT4rcc_METHOD\"] INSTRUCTION_FORMAT4rcc_METHOD register_range method_reference method_prototype ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1161:5: INSTRUCTION_FORMAT4rcc_METHOD OPEN_BRACE register_range CLOSE_BRACE COMMA method_reference COMMA method_prototype
			{
			INSTRUCTION_FORMAT4rcc_METHOD534=(Token)match(input,INSTRUCTION_FORMAT4rcc_METHOD,FOLLOW_INSTRUCTION_FORMAT4rcc_METHOD_in_insn_format4rcc_method6141);
			stream_INSTRUCTION_FORMAT4rcc_METHOD.add(INSTRUCTION_FORMAT4rcc_METHOD534);

			OPEN_BRACE535=(Token)match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_insn_format4rcc_method6143);
			stream_OPEN_BRACE.add(OPEN_BRACE535);

			pushFollow(FOLLOW_register_range_in_insn_format4rcc_method6145);
			register_range536=register_range();
			state._fsp--;

			stream_register_range.add(register_range536.getTree());
			CLOSE_BRACE537=(Token)match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_insn_format4rcc_method6147);
			stream_CLOSE_BRACE.add(CLOSE_BRACE537);

			COMMA538=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format4rcc_method6149);
			stream_COMMA.add(COMMA538);

			pushFollow(FOLLOW_method_reference_in_insn_format4rcc_method6151);
			method_reference539=method_reference();
			state._fsp--;

			stream_method_reference.add(method_reference539.getTree());
			COMMA540=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format4rcc_method6153);
			stream_COMMA.add(COMMA540);

			pushFollow(FOLLOW_method_prototype_in_insn_format4rcc_method6155);
			method_prototype541=method_prototype();
			state._fsp--;

			stream_method_prototype.add(method_prototype541.getTree());
			// AST REWRITE
			// elements: INSTRUCTION_FORMAT4rcc_METHOD, method_reference, method_prototype, register_range
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1162:5: -> ^( I_STATEMENT_FORMAT4rcc_METHOD[$start, \"I_STATEMENT_FORMAT4rcc_METHOD\"] INSTRUCTION_FORMAT4rcc_METHOD register_range method_reference method_prototype )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1162:8: ^( I_STATEMENT_FORMAT4rcc_METHOD[$start, \"I_STATEMENT_FORMAT4rcc_METHOD\"] INSTRUCTION_FORMAT4rcc_METHOD register_range method_reference method_prototype )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT4rcc_METHOD, (retval.start), "I_STATEMENT_FORMAT4rcc_METHOD"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT4rcc_METHOD.nextNode());
				adaptor.addChild(root_1, stream_register_range.nextTree());
				adaptor.addChild(root_1, stream_method_reference.nextTree());
				adaptor.addChild(root_1, stream_method_prototype.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format4rcc_method"


	public static class insn_format51l_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_format51l"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1164:1: insn_format51l : INSTRUCTION_FORMAT51l REGISTER COMMA fixed_literal -> ^( I_STATEMENT_FORMAT51l[$start, \"I_STATEMENT_FORMAT51l\"] INSTRUCTION_FORMAT51l REGISTER fixed_literal ) ;
	public final smaliParser.insn_format51l_return insn_format51l() throws RecognitionException {
		smaliParser.insn_format51l_return retval = new smaliParser.insn_format51l_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token INSTRUCTION_FORMAT51l542=null;
		Token REGISTER543=null;
		Token COMMA544=null;
		ParserRuleReturnScope fixed_literal545 =null;

		CommonTree INSTRUCTION_FORMAT51l542_tree=null;
		CommonTree REGISTER543_tree=null;
		CommonTree COMMA544_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleTokenStream stream_REGISTER=new RewriteRuleTokenStream(adaptor,"token REGISTER");
		RewriteRuleTokenStream stream_INSTRUCTION_FORMAT51l=new RewriteRuleTokenStream(adaptor,"token INSTRUCTION_FORMAT51l");
		RewriteRuleSubtreeStream stream_fixed_literal=new RewriteRuleSubtreeStream(adaptor,"rule fixed_literal");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1165:3: ( INSTRUCTION_FORMAT51l REGISTER COMMA fixed_literal -> ^( I_STATEMENT_FORMAT51l[$start, \"I_STATEMENT_FORMAT51l\"] INSTRUCTION_FORMAT51l REGISTER fixed_literal ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1166:5: INSTRUCTION_FORMAT51l REGISTER COMMA fixed_literal
			{
			INSTRUCTION_FORMAT51l542=(Token)match(input,INSTRUCTION_FORMAT51l,FOLLOW_INSTRUCTION_FORMAT51l_in_insn_format51l6189);
			stream_INSTRUCTION_FORMAT51l.add(INSTRUCTION_FORMAT51l542);

			REGISTER543=(Token)match(input,REGISTER,FOLLOW_REGISTER_in_insn_format51l6191);
			stream_REGISTER.add(REGISTER543);

			COMMA544=(Token)match(input,COMMA,FOLLOW_COMMA_in_insn_format51l6193);
			stream_COMMA.add(COMMA544);

			pushFollow(FOLLOW_fixed_literal_in_insn_format51l6195);
			fixed_literal545=fixed_literal();
			state._fsp--;

			stream_fixed_literal.add(fixed_literal545.getTree());
			// AST REWRITE
			// elements: INSTRUCTION_FORMAT51l, fixed_literal, REGISTER
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1167:5: -> ^( I_STATEMENT_FORMAT51l[$start, \"I_STATEMENT_FORMAT51l\"] INSTRUCTION_FORMAT51l REGISTER fixed_literal )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1167:8: ^( I_STATEMENT_FORMAT51l[$start, \"I_STATEMENT_FORMAT51l\"] INSTRUCTION_FORMAT51l REGISTER fixed_literal )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_FORMAT51l, (retval.start), "I_STATEMENT_FORMAT51l"), root_1);
				adaptor.addChild(root_1, stream_INSTRUCTION_FORMAT51l.nextNode());
				adaptor.addChild(root_1, stream_REGISTER.nextNode());
				adaptor.addChild(root_1, stream_fixed_literal.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_format51l"


	public static class insn_array_data_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_array_data_directive"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1169:1: insn_array_data_directive : ARRAY_DATA_DIRECTIVE parsed_integer_literal ( fixed_literal )* END_ARRAY_DATA_DIRECTIVE -> ^( I_STATEMENT_ARRAY_DATA[$start, \"I_STATEMENT_ARRAY_DATA\"] ^( I_ARRAY_ELEMENT_SIZE parsed_integer_literal ) ^( I_ARRAY_ELEMENTS ( fixed_literal )* ) ) ;
	public final smaliParser.insn_array_data_directive_return insn_array_data_directive() throws RecognitionException {
		smaliParser.insn_array_data_directive_return retval = new smaliParser.insn_array_data_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token ARRAY_DATA_DIRECTIVE546=null;
		Token END_ARRAY_DATA_DIRECTIVE549=null;
		ParserRuleReturnScope parsed_integer_literal547 =null;
		ParserRuleReturnScope fixed_literal548 =null;

		CommonTree ARRAY_DATA_DIRECTIVE546_tree=null;
		CommonTree END_ARRAY_DATA_DIRECTIVE549_tree=null;
		RewriteRuleTokenStream stream_END_ARRAY_DATA_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token END_ARRAY_DATA_DIRECTIVE");
		RewriteRuleTokenStream stream_ARRAY_DATA_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token ARRAY_DATA_DIRECTIVE");
		RewriteRuleSubtreeStream stream_parsed_integer_literal=new RewriteRuleSubtreeStream(adaptor,"rule parsed_integer_literal");
		RewriteRuleSubtreeStream stream_fixed_literal=new RewriteRuleSubtreeStream(adaptor,"rule fixed_literal");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1170:3: ( ARRAY_DATA_DIRECTIVE parsed_integer_literal ( fixed_literal )* END_ARRAY_DATA_DIRECTIVE -> ^( I_STATEMENT_ARRAY_DATA[$start, \"I_STATEMENT_ARRAY_DATA\"] ^( I_ARRAY_ELEMENT_SIZE parsed_integer_literal ) ^( I_ARRAY_ELEMENTS ( fixed_literal )* ) ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1170:5: ARRAY_DATA_DIRECTIVE parsed_integer_literal ( fixed_literal )* END_ARRAY_DATA_DIRECTIVE
			{
			ARRAY_DATA_DIRECTIVE546=(Token)match(input,ARRAY_DATA_DIRECTIVE,FOLLOW_ARRAY_DATA_DIRECTIVE_in_insn_array_data_directive6222);
			stream_ARRAY_DATA_DIRECTIVE.add(ARRAY_DATA_DIRECTIVE546);

			pushFollow(FOLLOW_parsed_integer_literal_in_insn_array_data_directive6228);
			parsed_integer_literal547=parsed_integer_literal();
			state._fsp--;

			stream_parsed_integer_literal.add(parsed_integer_literal547.getTree());

			        int elementWidth = (parsed_integer_literal547!=null?((smaliParser.parsed_integer_literal_return)parsed_integer_literal547).value:0);
			        if (elementWidth != 4 && elementWidth != 8 && elementWidth != 1 && elementWidth != 2) {
			            throw new SemanticException(input, (retval.start), "Invalid element width: %d. Must be 1, 2, 4 or 8", elementWidth);
			        }
			
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1178:5: ( fixed_literal )*
			loop56:
			while (true) {
				int alt56=2;
				int LA56_0 = input.LA(1);
				if ( ((LA56_0 >= BOOL_LITERAL && LA56_0 <= BYTE_LITERAL)||LA56_0==CHAR_LITERAL||(LA56_0 >= DOUBLE_LITERAL && LA56_0 <= DOUBLE_LITERAL_OR_ID)||(LA56_0 >= FLOAT_LITERAL && LA56_0 <= FLOAT_LITERAL_OR_ID)||LA56_0==LONG_LITERAL||LA56_0==NEGATIVE_INTEGER_LITERAL||LA56_0==POSITIVE_INTEGER_LITERAL||LA56_0==SHORT_LITERAL) ) {
					alt56=1;
				}

				switch (alt56) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1178:5: fixed_literal
					{
					pushFollow(FOLLOW_fixed_literal_in_insn_array_data_directive6240);
					fixed_literal548=fixed_literal();
					state._fsp--;

					stream_fixed_literal.add(fixed_literal548.getTree());
					}
					break;

				default :
					break loop56;
				}
			}

			END_ARRAY_DATA_DIRECTIVE549=(Token)match(input,END_ARRAY_DATA_DIRECTIVE,FOLLOW_END_ARRAY_DATA_DIRECTIVE_in_insn_array_data_directive6243);
			stream_END_ARRAY_DATA_DIRECTIVE.add(END_ARRAY_DATA_DIRECTIVE549);

			// AST REWRITE
			// elements: fixed_literal, parsed_integer_literal
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1180:5: -> ^( I_STATEMENT_ARRAY_DATA[$start, \"I_STATEMENT_ARRAY_DATA\"] ^( I_ARRAY_ELEMENT_SIZE parsed_integer_literal ) ^( I_ARRAY_ELEMENTS ( fixed_literal )* ) )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1180:8: ^( I_STATEMENT_ARRAY_DATA[$start, \"I_STATEMENT_ARRAY_DATA\"] ^( I_ARRAY_ELEMENT_SIZE parsed_integer_literal ) ^( I_ARRAY_ELEMENTS ( fixed_literal )* ) )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_ARRAY_DATA, (retval.start), "I_STATEMENT_ARRAY_DATA"), root_1);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1180:67: ^( I_ARRAY_ELEMENT_SIZE parsed_integer_literal )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ARRAY_ELEMENT_SIZE, "I_ARRAY_ELEMENT_SIZE"), root_2);
				adaptor.addChild(root_2, stream_parsed_integer_literal.nextTree());
				adaptor.addChild(root_1, root_2);
				}

				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1181:8: ^( I_ARRAY_ELEMENTS ( fixed_literal )* )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_ARRAY_ELEMENTS, "I_ARRAY_ELEMENTS"), root_2);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1181:27: ( fixed_literal )*
				while ( stream_fixed_literal.hasNext() ) {
					adaptor.addChild(root_2, stream_fixed_literal.nextTree());
				}
				stream_fixed_literal.reset();

				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_array_data_directive"


	public static class insn_packed_switch_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_packed_switch_directive"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1183:1: insn_packed_switch_directive : PACKED_SWITCH_DIRECTIVE fixed_32bit_literal ( label_ref )* END_PACKED_SWITCH_DIRECTIVE -> ^( I_STATEMENT_PACKED_SWITCH[$start, \"I_STATEMENT_PACKED_SWITCH\"] ^( I_PACKED_SWITCH_START_KEY[$start, \"I_PACKED_SWITCH_START_KEY\"] fixed_32bit_literal ) ^( I_PACKED_SWITCH_ELEMENTS[$start, \"I_PACKED_SWITCH_ELEMENTS\"] ( label_ref )* ) ) ;
	public final smaliParser.insn_packed_switch_directive_return insn_packed_switch_directive() throws RecognitionException {
		smaliParser.insn_packed_switch_directive_return retval = new smaliParser.insn_packed_switch_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token PACKED_SWITCH_DIRECTIVE550=null;
		Token END_PACKED_SWITCH_DIRECTIVE553=null;
		ParserRuleReturnScope fixed_32bit_literal551 =null;
		ParserRuleReturnScope label_ref552 =null;

		CommonTree PACKED_SWITCH_DIRECTIVE550_tree=null;
		CommonTree END_PACKED_SWITCH_DIRECTIVE553_tree=null;
		RewriteRuleTokenStream stream_END_PACKED_SWITCH_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token END_PACKED_SWITCH_DIRECTIVE");
		RewriteRuleTokenStream stream_PACKED_SWITCH_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token PACKED_SWITCH_DIRECTIVE");
		RewriteRuleSubtreeStream stream_fixed_32bit_literal=new RewriteRuleSubtreeStream(adaptor,"rule fixed_32bit_literal");
		RewriteRuleSubtreeStream stream_label_ref=new RewriteRuleSubtreeStream(adaptor,"rule label_ref");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1184:5: ( PACKED_SWITCH_DIRECTIVE fixed_32bit_literal ( label_ref )* END_PACKED_SWITCH_DIRECTIVE -> ^( I_STATEMENT_PACKED_SWITCH[$start, \"I_STATEMENT_PACKED_SWITCH\"] ^( I_PACKED_SWITCH_START_KEY[$start, \"I_PACKED_SWITCH_START_KEY\"] fixed_32bit_literal ) ^( I_PACKED_SWITCH_ELEMENTS[$start, \"I_PACKED_SWITCH_ELEMENTS\"] ( label_ref )* ) ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1184:9: PACKED_SWITCH_DIRECTIVE fixed_32bit_literal ( label_ref )* END_PACKED_SWITCH_DIRECTIVE
			{
			PACKED_SWITCH_DIRECTIVE550=(Token)match(input,PACKED_SWITCH_DIRECTIVE,FOLLOW_PACKED_SWITCH_DIRECTIVE_in_insn_packed_switch_directive6289);
			stream_PACKED_SWITCH_DIRECTIVE.add(PACKED_SWITCH_DIRECTIVE550);

			pushFollow(FOLLOW_fixed_32bit_literal_in_insn_packed_switch_directive6295);
			fixed_32bit_literal551=fixed_32bit_literal();
			state._fsp--;

			stream_fixed_32bit_literal.add(fixed_32bit_literal551.getTree());
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1186:5: ( label_ref )*
			loop57:
			while (true) {
				int alt57=2;
				int LA57_0 = input.LA(1);
				if ( (LA57_0==COLON) ) {
					alt57=1;
				}

				switch (alt57) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1186:5: label_ref
					{
					pushFollow(FOLLOW_label_ref_in_insn_packed_switch_directive6301);
					label_ref552=label_ref();
					state._fsp--;

					stream_label_ref.add(label_ref552.getTree());
					}
					break;

				default :
					break loop57;
				}
			}

			END_PACKED_SWITCH_DIRECTIVE553=(Token)match(input,END_PACKED_SWITCH_DIRECTIVE,FOLLOW_END_PACKED_SWITCH_DIRECTIVE_in_insn_packed_switch_directive6308);
			stream_END_PACKED_SWITCH_DIRECTIVE.add(END_PACKED_SWITCH_DIRECTIVE553);

			// AST REWRITE
			// elements: fixed_32bit_literal, label_ref
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1188:5: -> ^( I_STATEMENT_PACKED_SWITCH[$start, \"I_STATEMENT_PACKED_SWITCH\"] ^( I_PACKED_SWITCH_START_KEY[$start, \"I_PACKED_SWITCH_START_KEY\"] fixed_32bit_literal ) ^( I_PACKED_SWITCH_ELEMENTS[$start, \"I_PACKED_SWITCH_ELEMENTS\"] ( label_ref )* ) )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1188:8: ^( I_STATEMENT_PACKED_SWITCH[$start, \"I_STATEMENT_PACKED_SWITCH\"] ^( I_PACKED_SWITCH_START_KEY[$start, \"I_PACKED_SWITCH_START_KEY\"] fixed_32bit_literal ) ^( I_PACKED_SWITCH_ELEMENTS[$start, \"I_PACKED_SWITCH_ELEMENTS\"] ( label_ref )* ) )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_PACKED_SWITCH, (retval.start), "I_STATEMENT_PACKED_SWITCH"), root_1);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1189:10: ^( I_PACKED_SWITCH_START_KEY[$start, \"I_PACKED_SWITCH_START_KEY\"] fixed_32bit_literal )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_PACKED_SWITCH_START_KEY, (retval.start), "I_PACKED_SWITCH_START_KEY"), root_2);
				adaptor.addChild(root_2, stream_fixed_32bit_literal.nextTree());
				adaptor.addChild(root_1, root_2);
				}

				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1190:10: ^( I_PACKED_SWITCH_ELEMENTS[$start, \"I_PACKED_SWITCH_ELEMENTS\"] ( label_ref )* )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_PACKED_SWITCH_ELEMENTS, (retval.start), "I_PACKED_SWITCH_ELEMENTS"), root_2);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1191:11: ( label_ref )*
				while ( stream_label_ref.hasNext() ) {
					adaptor.addChild(root_2, stream_label_ref.nextTree());
				}
				stream_label_ref.reset();

				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_packed_switch_directive"


	public static class insn_sparse_switch_directive_return extends ParserRuleReturnScope {
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insn_sparse_switch_directive"
	// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1194:1: insn_sparse_switch_directive : SPARSE_SWITCH_DIRECTIVE ( fixed_32bit_literal ARROW label_ref )* END_SPARSE_SWITCH_DIRECTIVE -> ^( I_STATEMENT_SPARSE_SWITCH[$start, \"I_STATEMENT_SPARSE_SWITCH\"] ^( I_SPARSE_SWITCH_ELEMENTS[$start, \"I_SPARSE_SWITCH_ELEMENTS\"] ( fixed_32bit_literal label_ref )* ) ) ;
	public final smaliParser.insn_sparse_switch_directive_return insn_sparse_switch_directive() throws RecognitionException {
		smaliParser.insn_sparse_switch_directive_return retval = new smaliParser.insn_sparse_switch_directive_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		Token SPARSE_SWITCH_DIRECTIVE554=null;
		Token ARROW556=null;
		Token END_SPARSE_SWITCH_DIRECTIVE558=null;
		ParserRuleReturnScope fixed_32bit_literal555 =null;
		ParserRuleReturnScope label_ref557 =null;

		CommonTree SPARSE_SWITCH_DIRECTIVE554_tree=null;
		CommonTree ARROW556_tree=null;
		CommonTree END_SPARSE_SWITCH_DIRECTIVE558_tree=null;
		RewriteRuleTokenStream stream_ARROW=new RewriteRuleTokenStream(adaptor,"token ARROW");
		RewriteRuleTokenStream stream_SPARSE_SWITCH_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token SPARSE_SWITCH_DIRECTIVE");
		RewriteRuleTokenStream stream_END_SPARSE_SWITCH_DIRECTIVE=new RewriteRuleTokenStream(adaptor,"token END_SPARSE_SWITCH_DIRECTIVE");
		RewriteRuleSubtreeStream stream_fixed_32bit_literal=new RewriteRuleSubtreeStream(adaptor,"rule fixed_32bit_literal");
		RewriteRuleSubtreeStream stream_label_ref=new RewriteRuleSubtreeStream(adaptor,"rule label_ref");

		try {
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1195:3: ( SPARSE_SWITCH_DIRECTIVE ( fixed_32bit_literal ARROW label_ref )* END_SPARSE_SWITCH_DIRECTIVE -> ^( I_STATEMENT_SPARSE_SWITCH[$start, \"I_STATEMENT_SPARSE_SWITCH\"] ^( I_SPARSE_SWITCH_ELEMENTS[$start, \"I_SPARSE_SWITCH_ELEMENTS\"] ( fixed_32bit_literal label_ref )* ) ) )
			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1195:7: SPARSE_SWITCH_DIRECTIVE ( fixed_32bit_literal ARROW label_ref )* END_SPARSE_SWITCH_DIRECTIVE
			{
			SPARSE_SWITCH_DIRECTIVE554=(Token)match(input,SPARSE_SWITCH_DIRECTIVE,FOLLOW_SPARSE_SWITCH_DIRECTIVE_in_insn_sparse_switch_directive6382);
			stream_SPARSE_SWITCH_DIRECTIVE.add(SPARSE_SWITCH_DIRECTIVE554);

			// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1196:5: ( fixed_32bit_literal ARROW label_ref )*
			loop58:
			while (true) {
				int alt58=2;
				int LA58_0 = input.LA(1);
				if ( ((LA58_0 >= BOOL_LITERAL && LA58_0 <= BYTE_LITERAL)||LA58_0==CHAR_LITERAL||(LA58_0 >= FLOAT_LITERAL && LA58_0 <= FLOAT_LITERAL_OR_ID)||LA58_0==LONG_LITERAL||LA58_0==NEGATIVE_INTEGER_LITERAL||LA58_0==POSITIVE_INTEGER_LITERAL||LA58_0==SHORT_LITERAL) ) {
					alt58=1;
				}

				switch (alt58) {
				case 1 :
					// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1196:6: fixed_32bit_literal ARROW label_ref
					{
					pushFollow(FOLLOW_fixed_32bit_literal_in_insn_sparse_switch_directive6389);
					fixed_32bit_literal555=fixed_32bit_literal();
					state._fsp--;

					stream_fixed_32bit_literal.add(fixed_32bit_literal555.getTree());
					ARROW556=(Token)match(input,ARROW,FOLLOW_ARROW_in_insn_sparse_switch_directive6391);
					stream_ARROW.add(ARROW556);

					pushFollow(FOLLOW_label_ref_in_insn_sparse_switch_directive6393);
					label_ref557=label_ref();
					state._fsp--;

					stream_label_ref.add(label_ref557.getTree());
					}
					break;

				default :
					break loop58;
				}
			}

			END_SPARSE_SWITCH_DIRECTIVE558=(Token)match(input,END_SPARSE_SWITCH_DIRECTIVE,FOLLOW_END_SPARSE_SWITCH_DIRECTIVE_in_insn_sparse_switch_directive6401);
			stream_END_SPARSE_SWITCH_DIRECTIVE.add(END_SPARSE_SWITCH_DIRECTIVE558);

			// AST REWRITE
			// elements: label_ref, fixed_32bit_literal
			// token labels:
			// rule labels: retval
			// token list labels:
			// rule list labels:
			// wildcard labels:
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (CommonTree)adaptor.nil();
			// 1198:5: -> ^( I_STATEMENT_SPARSE_SWITCH[$start, \"I_STATEMENT_SPARSE_SWITCH\"] ^( I_SPARSE_SWITCH_ELEMENTS[$start, \"I_SPARSE_SWITCH_ELEMENTS\"] ( fixed_32bit_literal label_ref )* ) )
			{
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1198:8: ^( I_STATEMENT_SPARSE_SWITCH[$start, \"I_STATEMENT_SPARSE_SWITCH\"] ^( I_SPARSE_SWITCH_ELEMENTS[$start, \"I_SPARSE_SWITCH_ELEMENTS\"] ( fixed_32bit_literal label_ref )* ) )
				{
				CommonTree root_1 = (CommonTree)adaptor.nil();
				root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_STATEMENT_SPARSE_SWITCH, (retval.start), "I_STATEMENT_SPARSE_SWITCH"), root_1);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1199:8: ^( I_SPARSE_SWITCH_ELEMENTS[$start, \"I_SPARSE_SWITCH_ELEMENTS\"] ( fixed_32bit_literal label_ref )* )
				{
				CommonTree root_2 = (CommonTree)adaptor.nil();
				root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(I_SPARSE_SWITCH_ELEMENTS, (retval.start), "I_SPARSE_SWITCH_ELEMENTS"), root_2);
				// /usr/local/google/home/melisacz/extra/aosp-master-with-phones/external/google-smali/smali/src/main/antlr/smaliParser.g:1199:71: ( fixed_32bit_literal label_ref )*
				while ( stream_label_ref.hasNext()||stream_fixed_32bit_literal.hasNext() ) {
					adaptor.addChild(root_2, stream_fixed_32bit_literal.nextTree());
					adaptor.addChild(root_2, stream_label_ref.nextTree());
				}
				stream_label_ref.reset();
				stream_fixed_32bit_literal.reset();

				adaptor.addChild(root_1, root_2);
				}

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insn_sparse_switch_directive"

	// Delegated rules


	protected DFA31 dfa31 = new DFA31(this);
	protected DFA41 dfa41 = new DFA41(this);
	protected DFA43 dfa43 = new DFA43(this);
	static final String DFA31_eotS =
		"\64\uffff";
	static final String DFA31_eofS =
		"\64\uffff";
	static final String DFA31_minS =
		"\1\4\61\24\2\uffff";
	static final String DFA31_maxS =
		"\1\u00d7\13\u00c5\1\u00c8\45\u00c5\2\uffff";
	static final String DFA31_acceptS =
		"\62\uffff\1\1\1\2";
	static final String DFA31_specialS =
		"\64\uffff}>";
	static final String[] DFA31_transitionS = {
			"\1\2\1\uffff\1\17\4\uffff\1\11\14\uffff\1\10\17\uffff\1\7\1\3\2\uffff"+
			"\1\22\1\23\1\24\1\uffff\1\25\1\uffff\1\26\2\uffff\1\27\1\30\1\31\1\32"+
			"\1\33\1\34\3\uffff\1\35\1\uffff\1\36\1\37\1\40\1\41\1\uffff\1\42\1\43"+
			"\1\uffff\1\44\3\uffff\1\45\1\46\1\uffff\1\47\1\50\1\51\1\52\1\53\1\54"+
			"\1\55\6\uffff\1\56\1\57\1\60\137\uffff\1\61\1\uffff\1\20\1\21\1\6\1\12"+
			"\4\uffff\1\14\1\5\1\15\1\uffff\1\13\3\uffff\1\1\5\uffff\1\4\1\16",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63\2\uffff\1\14",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"\1\62\u00b0\uffff\1\63",
			"",
			""
	};

	static final short[] DFA31_eot = DFA.unpackEncodedString(DFA31_eotS);
	static final short[] DFA31_eof = DFA.unpackEncodedString(DFA31_eofS);
	static final char[] DFA31_min = DFA.unpackEncodedStringToUnsignedChars(DFA31_minS);
	static final char[] DFA31_max = DFA.unpackEncodedStringToUnsignedChars(DFA31_maxS);
	static final short[] DFA31_accept = DFA.unpackEncodedString(DFA31_acceptS);
	static final short[] DFA31_special = DFA.unpackEncodedString(DFA31_specialS);
	static final short[][] DFA31_transition;

	static {
		int numStates = DFA31_transitionS.length;
		DFA31_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA31_transition[i] = DFA.unpackEncodedString(DFA31_transitionS[i]);
		}
	}

	protected class DFA31 extends DFA {

		public DFA31(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 31;
			this.eot = DFA31_eot;
			this.eof = DFA31_eof;
			this.min = DFA31_min;
			this.max = DFA31_max;
			this.accept = DFA31_accept;
			this.special = DFA31_special;
			this.transition = DFA31_transition;
		}
		@Override
		public String getDescription() {
			return "724:7: ( member_name COLON nonvoid_type_descriptor -> ^( I_ENCODED_FIELD ( reference_type_descriptor )? member_name nonvoid_type_descriptor ) | member_name method_prototype -> ^( I_ENCODED_METHOD ( reference_type_descriptor )? member_name method_prototype ) )";
		}
	}

	static final String DFA41_eotS =
		"\71\uffff";
	static final String DFA41_eofS =
		"\71\uffff";
	static final String DFA41_minS =
		"\1\4\1\5\1\20\61\24\1\uffff\1\4\1\11\2\uffff";
	static final String DFA41_maxS =
		"\1\u00d7\1\u00d2\1\u00ca\13\u00c5\1\u00c8\45\u00c5\1\uffff\1\u00d7\1\11"+
		"\2\uffff";
	static final String DFA41_acceptS =
		"\64\uffff\1\1\2\uffff\1\2\1\3";
	static final String DFA41_specialS =
		"\71\uffff}>";
	static final String[] DFA41_transitionS = {
			"\1\4\1\uffff\1\21\1\uffff\1\2\2\uffff\1\13\4\uffff\1\1\7\uffff\1\12\17"+
			"\uffff\1\11\1\5\2\uffff\1\24\1\25\1\26\1\uffff\1\27\1\uffff\1\30\2\uffff"+
			"\1\31\1\32\1\33\1\34\1\35\1\36\3\uffff\1\37\1\uffff\1\40\1\41\1\42\1"+
			"\43\1\uffff\1\44\1\45\1\uffff\1\46\3\uffff\1\47\1\50\1\uffff\1\51\1\52"+
			"\1\53\1\54\1\55\1\56\1\57\6\uffff\1\60\1\61\1\62\137\uffff\1\63\1\uffff"+
			"\1\22\1\23\1\10\1\14\4\uffff\1\16\1\7\1\17\1\uffff\1\15\3\uffff\1\3\5"+
			"\uffff\1\6\1\20",
			"\1\64\1\uffff\1\64\1\uffff\1\65\3\uffff\2\64\5\uffff\1\64\7\uffff\2"+
			"\64\5\uffff\1\64\10\uffff\63\64\133\uffff\3\64\11\uffff\2\64\3\uffff"+
			"\1\64\1\uffff\2\64\2\uffff\2\64",
			"\1\66\u00b9\uffff\1\66",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70\2\uffff\1\16",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"\1\67\u00b0\uffff\1\70",
			"",
			"\1\4\1\uffff\1\21\4\uffff\1\13\14\uffff\1\12\17\uffff\1\11\1\5\2\uffff"+
			"\1\24\1\25\1\26\1\uffff\1\27\1\uffff\1\30\2\uffff\1\31\1\32\1\33\1\34"+
			"\1\35\1\36\3\uffff\1\37\1\uffff\1\40\1\41\1\42\1\43\1\uffff\1\44\1\45"+
			"\1\uffff\1\46\3\uffff\1\47\1\50\1\uffff\1\51\1\52\1\53\1\54\1\55\1\56"+
			"\1\57\6\uffff\1\60\1\61\1\62\137\uffff\1\63\1\uffff\1\22\1\23\1\10\1"+
			"\14\4\uffff\1\16\1\7\1\17\1\uffff\1\15\3\uffff\1\3\5\uffff\1\6\1\20",
			"\1\65",
			"",
			""
	};

	static final short[] DFA41_eot = DFA.unpackEncodedString(DFA41_eotS);
	static final short[] DFA41_eof = DFA.unpackEncodedString(DFA41_eofS);
	static final char[] DFA41_min = DFA.unpackEncodedStringToUnsignedChars(DFA41_minS);
	static final char[] DFA41_max = DFA.unpackEncodedStringToUnsignedChars(DFA41_maxS);
	static final short[] DFA41_accept = DFA.unpackEncodedString(DFA41_acceptS);
	static final short[] DFA41_special = DFA.unpackEncodedString(DFA41_specialS);
	static final short[][] DFA41_transition;

	static {
		int numStates = DFA41_transitionS.length;
		DFA41_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA41_transition[i] = DFA.unpackEncodedString(DFA41_transitionS[i]);
		}
	}

	protected class DFA41 extends DFA {

		public DFA41(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 41;
			this.eot = DFA41_eot;
			this.eof = DFA41_eof;
			this.min = DFA41_min;
			this.max = DFA41_max;
			this.accept = DFA41_accept;
			this.special = DFA41_special;
			this.transition = DFA41_transition;
		}
		@Override
		public String getDescription() {
			return "766:1: verification_error_reference : ( CLASS_DESCRIPTOR | field_reference | method_reference );";
		}
	}

	static final String DFA43_eotS =
		"\110\uffff";
	static final String DFA43_eofS =
		"\110\uffff";
	static final String DFA43_minS =
		"\1\5\105\uffff\1\0\1\uffff";
	static final String DFA43_maxS =
		"\1\u00d2\105\uffff\1\0\1\uffff";
	static final String DFA43_acceptS =
		"\1\uffff\1\2\105\uffff\1\1";
	static final String DFA43_specialS =
		"\106\uffff\1\0\1\uffff}>";
	static final String[] DFA43_transitionS = {
			"\1\106\1\uffff\1\1\5\uffff\2\1\5\uffff\1\1\7\uffff\2\1\1\uffff\1\1\3"+
			"\uffff\1\1\10\uffff\63\1\133\uffff\3\1\11\uffff\2\1\3\uffff\1\1\1\uffff"+
			"\2\1\2\uffff\2\1",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\uffff",
			""
	};

	static final short[] DFA43_eot = DFA.unpackEncodedString(DFA43_eotS);
	static final short[] DFA43_eof = DFA.unpackEncodedString(DFA43_eofS);
	static final char[] DFA43_min = DFA.unpackEncodedStringToUnsignedChars(DFA43_minS);
	static final char[] DFA43_max = DFA.unpackEncodedStringToUnsignedChars(DFA43_maxS);
	static final short[] DFA43_accept = DFA.unpackEncodedString(DFA43_acceptS);
	static final short[] DFA43_special = DFA.unpackEncodedString(DFA43_specialS);
	static final short[][] DFA43_transition;

	static {
		int numStates = DFA43_transitionS.length;
		DFA43_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA43_transition[i] = DFA.unpackEncodedString(DFA43_transitionS[i]);
		}
	}

	protected class DFA43 extends DFA {

		public DFA43(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 43;
			this.eot = DFA43_eot;
			this.eof = DFA43_eof;
			this.min = DFA43_min;
			this.max = DFA43_max;
			this.accept = DFA43_accept;
			this.special = DFA43_special;
			this.transition = DFA43_transition;
		}
		@Override
		public String getDescription() {
			return "()* loopback of 784:5: ({...}? annotation )*";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			TokenStream input = (TokenStream)_input;
			int _s = s;
			switch ( s ) {
					case 0 :
						int LA43_70 = input.LA(1);
						
						int index43_70 = input.index();
						input.rewind();
						s = -1;
						if ( ((input.LA(1) == ANNOTATION_DIRECTIVE)) ) {s = 71;}
						else if ( (true) ) {s = 1;}
						
						input.seek(index43_70);
						if ( s>=0 ) return s;
						break;
			}
			NoViableAltException nvae =
				new NoViableAltException(getDescription(), 43, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	public static final BitSet FOLLOW_class_spec_in_smali_file1150 = new BitSet(new long[]{0x0000042000020020L,0x0000000000000000L,0x8000000000000000L,0x0000000000220000L});
	public static final BitSet FOLLOW_super_spec_in_smali_file1161 = new BitSet(new long[]{0x0000042000020020L,0x0000000000000000L,0x8000000000000000L,0x0000000000220000L});
	public static final BitSet FOLLOW_implements_spec_in_smali_file1169 = new BitSet(new long[]{0x0000042000020020L,0x0000000000000000L,0x8000000000000000L,0x0000000000220000L});
	public static final BitSet FOLLOW_source_spec_in_smali_file1178 = new BitSet(new long[]{0x0000042000020020L,0x0000000000000000L,0x8000000000000000L,0x0000000000220000L});
	public static final BitSet FOLLOW_method_in_smali_file1186 = new BitSet(new long[]{0x0000042000020020L,0x0000000000000000L,0x8000000000000000L,0x0000000000220000L});
	public static final BitSet FOLLOW_field_in_smali_file1192 = new BitSet(new long[]{0x0000042000020020L,0x0000000000000000L,0x8000000000000000L,0x0000000000220000L});
	public static final BitSet FOLLOW_annotation_in_smali_file1198 = new BitSet(new long[]{0x0000042000020020L,0x0000000000000000L,0x8000000000000000L,0x0000000000220000L});
	public static final BitSet FOLLOW_EOF_in_smali_file1209 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CLASS_DIRECTIVE_in_class_spec1296 = new BitSet(new long[]{0x0000000000010010L});
	public static final BitSet FOLLOW_access_list_in_class_spec1298 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_class_spec1300 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SUPER_DIRECTIVE_in_super_spec1318 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_super_spec1320 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IMPLEMENTS_DIRECTIVE_in_implements_spec1339 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_implements_spec1341 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SOURCE_DIRECTIVE_in_source_spec1360 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_source_spec1362 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ACCESS_SPEC_in_access_list1381 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_access_or_restriction_in_access_or_restriction_list1416 = new BitSet(new long[]{0x0000020000000012L});
	public static final BitSet FOLLOW_FIELD_DIRECTIVE_in_field1449 = new BitSet(new long[]{0x47E5730001000850L,0x00000000703FB16FL,0x4000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_access_or_restriction_list_in_field1451 = new BitSet(new long[]{0x47E5730001000850L,0x00000000703FB16FL,0x4000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_member_name_in_field1453 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COLON_in_field1455 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_field1457 = new BitSet(new long[]{0x0000001008000022L});
	public static final BitSet FOLLOW_EQUAL_in_field1460 = new BitSet(new long[]{0x47E5738401819950L,0x00000000703FB16FL,0x6000000000000000L,0x0000000000D9973FL});
	public static final BitSet FOLLOW_literal_in_field1462 = new BitSet(new long[]{0x0000000008000022L});
	public static final BitSet FOLLOW_annotation_in_field1475 = new BitSet(new long[]{0x0000000008000022L});
	public static final BitSet FOLLOW_END_FIELD_DIRECTIVE_in_field1489 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_METHOD_DIRECTIVE_in_method1600 = new BitSet(new long[]{0x47E5730001000850L,0x00000000703FB16FL,0x4000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_access_or_restriction_list_in_method1602 = new BitSet(new long[]{0x47E5730001000850L,0x00000000703FB16FL,0x4000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_member_name_in_method1604 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_method_prototype_in_method1606 = new BitSet(new long[]{0xFFFFF008301060A0L,0x000000007FFFFFFFL,0x1C00000000000000L,0x00000000000668C0L});
	public static final BitSet FOLLOW_statements_and_directives_in_method1608 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_END_METHOD_DIRECTIVE_in_method1614 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ordered_method_item_in_statements_and_directives1659 = new BitSet(new long[]{0xFFFFF008101060A2L,0x000000007FFFFFFFL,0x1C00000000000000L,0x00000000000668C0L});
	public static final BitSet FOLLOW_registers_directive_in_statements_and_directives1667 = new BitSet(new long[]{0xFFFFF008101060A2L,0x000000007FFFFFFFL,0x1C00000000000000L,0x00000000000668C0L});
	public static final BitSet FOLLOW_catch_directive_in_statements_and_directives1675 = new BitSet(new long[]{0xFFFFF008101060A2L,0x000000007FFFFFFFL,0x1C00000000000000L,0x00000000000668C0L});
	public static final BitSet FOLLOW_catchall_directive_in_statements_and_directives1683 = new BitSet(new long[]{0xFFFFF008101060A2L,0x000000007FFFFFFFL,0x1C00000000000000L,0x00000000000668C0L});
	public static final BitSet FOLLOW_parameter_directive_in_statements_and_directives1691 = new BitSet(new long[]{0xFFFFF008101060A2L,0x000000007FFFFFFFL,0x1C00000000000000L,0x00000000000668C0L});
	public static final BitSet FOLLOW_annotation_in_statements_and_directives1699 = new BitSet(new long[]{0xFFFFF008101060A2L,0x000000007FFFFFFFL,0x1C00000000000000L,0x00000000000668C0L});
	public static final BitSet FOLLOW_label_in_ordered_method_item1784 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_instruction_in_ordered_method_item1790 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_debug_directive_in_ordered_method_item1796 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_REGISTERS_DIRECTIVE_in_registers_directive1816 = new BitSet(new long[]{0x0000000000009000L,0x0000000000000000L,0x2000000000000000L,0x0000000000008204L});
	public static final BitSet FOLLOW_integral_literal_in_registers_directive1820 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LOCALS_DIRECTIVE_in_registers_directive1840 = new BitSet(new long[]{0x0000000000009000L,0x0000000000000000L,0x2000000000000000L,0x0000000000008204L});
	public static final BitSet FOLLOW_integral_literal_in_registers_directive1844 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PARAM_LIST_OR_ID_PRIMITIVE_TYPE_in_param_list_or_id1876 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_SIMPLE_NAME_in_simple_name1889 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ACCESS_SPEC_in_simple_name1895 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_HIDDENAPI_RESTRICTION_in_simple_name1906 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VERIFICATION_ERROR_TYPE_in_simple_name1917 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_POSITIVE_INTEGER_LITERAL_in_simple_name1928 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEGATIVE_INTEGER_LITERAL_in_simple_name1939 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FLOAT_LITERAL_OR_ID_in_simple_name1950 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLE_LITERAL_OR_ID_in_simple_name1961 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BOOL_LITERAL_in_simple_name1972 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NULL_LITERAL_in_simple_name1983 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_REGISTER_in_simple_name1994 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_param_list_or_id_in_simple_name2005 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRIMITIVE_TYPE_in_simple_name2015 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VOID_TYPE_in_simple_name2026 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ANNOTATION_VISIBILITY_in_simple_name2037 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_METHOD_HANDLE_TYPE_FIELD_in_simple_name2048 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_METHOD_HANDLE_TYPE_METHOD_in_simple_name2054 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT10t_in_simple_name2060 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT10x_in_simple_name2071 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT10x_ODEX_in_simple_name2082 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT11x_in_simple_name2093 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT12x_OR_ID_in_simple_name2104 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_FIELD_in_simple_name2115 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_FIELD_ODEX_in_simple_name2126 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_METHOD_HANDLE_in_simple_name2137 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_METHOD_TYPE_in_simple_name2148 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_STRING_in_simple_name2159 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_TYPE_in_simple_name2170 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21t_in_simple_name2181 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_FIELD_in_simple_name2192 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_FIELD_ODEX_in_simple_name2203 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_TYPE_in_simple_name2214 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22cs_FIELD_in_simple_name2225 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22s_OR_ID_in_simple_name2236 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22t_in_simple_name2247 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT23x_in_simple_name2258 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT31i_OR_ID_in_simple_name2269 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT31t_in_simple_name2280 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_CALL_SITE_in_simple_name2291 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_METHOD_in_simple_name2302 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_METHOD_ODEX_in_simple_name2313 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE_in_simple_name2324 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_TYPE_in_simple_name2335 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35mi_METHOD_in_simple_name2346 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35ms_METHOD_in_simple_name2357 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT45cc_METHOD_in_simple_name2368 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT4rcc_METHOD_in_simple_name2379 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT51l_in_simple_name2390 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_simple_name_in_member_name2405 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MEMBER_NAME_in_member_name2411 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPEN_PAREN_in_method_prototype2426 = new BitSet(new long[]{0x0000000000090100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000500L});
	public static final BitSet FOLLOW_param_list_in_method_prototype2428 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_CLOSE_PAREN_in_method_prototype2430 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000800400L});
	public static final BitSet FOLLOW_type_descriptor_in_method_prototype2432 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PARAM_LIST_OR_ID_PRIMITIVE_TYPE_in_param_list_or_id_primitive_type2462 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_param_list_or_id_primitive_type_in_param_list2477 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_param_list2484 = new BitSet(new long[]{0x0000000000010102L,0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_ARRAY_TYPE_PREFIX_in_array_descriptor2495 = new BitSet(new long[]{0x0000000000010000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_set_in_array_descriptor2497 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VOID_TYPE_in_type_descriptor2513 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRIMITIVE_TYPE_in_type_descriptor2519 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_type_descriptor2525 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_array_descriptor_in_type_descriptor2531 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRIMITIVE_TYPE_in_nonvoid_type_descriptor2541 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_nonvoid_type_descriptor2547 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_array_descriptor_in_nonvoid_type_descriptor2553 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_reference_type_descriptor2563 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_array_descriptor_in_reference_type_descriptor2569 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_POSITIVE_INTEGER_LITERAL_in_integer_literal2579 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEGATIVE_INTEGER_LITERAL_in_integer_literal2590 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FLOAT_LITERAL_OR_ID_in_float_literal2605 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FLOAT_LITERAL_in_float_literal2616 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLE_LITERAL_OR_ID_in_double_literal2626 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLE_LITERAL_in_double_literal2637 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LONG_LITERAL_in_literal2647 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_integer_literal_in_literal2653 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SHORT_LITERAL_in_literal2659 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BYTE_LITERAL_in_literal2665 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_float_literal_in_literal2671 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_double_literal_in_literal2677 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CHAR_LITERAL_in_literal2683 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_literal2689 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BOOL_LITERAL_in_literal2695 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NULL_LITERAL_in_literal2701 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_array_literal_in_literal2707 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_subannotation_in_literal2713 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_type_field_method_literal_in_literal2719 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_enum_literal_in_literal2725 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_method_handle_literal_in_literal2731 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_method_prototype_in_literal2737 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_integer_literal_in_parsed_integer_literal2750 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LONG_LITERAL_in_integral_literal2762 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_integer_literal_in_integral_literal2768 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SHORT_LITERAL_in_integral_literal2774 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CHAR_LITERAL_in_integral_literal2780 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BYTE_LITERAL_in_integral_literal2786 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LONG_LITERAL_in_fixed_32bit_literal2796 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_integer_literal_in_fixed_32bit_literal2802 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SHORT_LITERAL_in_fixed_32bit_literal2808 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BYTE_LITERAL_in_fixed_32bit_literal2814 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_float_literal_in_fixed_32bit_literal2820 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CHAR_LITERAL_in_fixed_32bit_literal2826 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BOOL_LITERAL_in_fixed_32bit_literal2832 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_integer_literal_in_fixed_literal2842 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LONG_LITERAL_in_fixed_literal2848 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SHORT_LITERAL_in_fixed_literal2854 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BYTE_LITERAL_in_fixed_literal2860 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_float_literal_in_fixed_literal2866 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_double_literal_in_fixed_literal2872 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CHAR_LITERAL_in_fixed_literal2878 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BOOL_LITERAL_in_fixed_literal2884 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_array_literal2894 = new BitSet(new long[]{0x47E5738401859950L,0x00000000703FB16FL,0x6000000000000000L,0x0000000000D9973FL});
	public static final BitSet FOLLOW_literal_in_array_literal2897 = new BitSet(new long[]{0x0000000000240000L});
	public static final BitSet FOLLOW_COMMA_in_array_literal2900 = new BitSet(new long[]{0x47E5738401819950L,0x00000000703FB16FL,0x6000000000000000L,0x0000000000D9973FL});
	public static final BitSet FOLLOW_literal_in_array_literal2902 = new BitSet(new long[]{0x0000000000240000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_array_literal2910 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_simple_name_in_annotation_element2934 = new BitSet(new long[]{0x0000001000000000L});
	public static final BitSet FOLLOW_EQUAL_in_annotation_element2936 = new BitSet(new long[]{0x47E5738401819950L,0x00000000703FB16FL,0x6000000000000000L,0x0000000000D9973FL});
	public static final BitSet FOLLOW_literal_in_annotation_element2938 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ANNOTATION_DIRECTIVE_in_annotation2963 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_ANNOTATION_VISIBILITY_in_annotation2965 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_annotation2967 = new BitSet(new long[]{0x47E5730003000850L,0x00000000703FB16FL,0x0000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_annotation_element_in_annotation2973 = new BitSet(new long[]{0x47E5730003000850L,0x00000000703FB16FL,0x0000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_END_ANNOTATION_DIRECTIVE_in_annotation2976 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SUBANNOTATION_DIRECTIVE_in_subannotation3009 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_subannotation3011 = new BitSet(new long[]{0x47E5730201000850L,0x00000000703FB16FL,0x0000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_annotation_element_in_subannotation3013 = new BitSet(new long[]{0x47E5730201000850L,0x00000000703FB16FL,0x0000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_END_SUBANNOTATION_DIRECTIVE_in_subannotation3016 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ENUM_DIRECTIVE_in_enum_literal3043 = new BitSet(new long[]{0x47E5730001010950L,0x00000000703FB16FL,0x4000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_field_reference_in_enum_literal3045 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_reference_type_descriptor_in_type_field_method_literal3065 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_reference_type_descriptor_in_type_field_method_literal3074 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_ARROW_in_type_field_method_literal3076 = new BitSet(new long[]{0x47E5730001000850L,0x00000000703FB16FL,0x4000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_member_name_in_type_field_method_literal3088 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COLON_in_type_field_method_literal3090 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_type_field_method_literal3092 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_member_name_in_type_field_method_literal3115 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_method_prototype_in_type_field_method_literal3117 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRIMITIVE_TYPE_in_type_field_method_literal3150 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VOID_TYPE_in_type_field_method_literal3156 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_simple_name_in_call_site_reference3166 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_OPEN_PAREN_in_call_site_reference3168 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_call_site_reference3170 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_call_site_reference3172 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_method_prototype_in_call_site_reference3174 = new BitSet(new long[]{0x0000000000280000L});
	public static final BitSet FOLLOW_COMMA_in_call_site_reference3177 = new BitSet(new long[]{0x47E5738401819950L,0x00000000703FB16FL,0x6000000000000000L,0x0000000000D9973FL});
	public static final BitSet FOLLOW_literal_in_call_site_reference3179 = new BitSet(new long[]{0x0000000000280000L});
	public static final BitSet FOLLOW_CLOSE_PAREN_in_call_site_reference3183 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_AT_in_call_site_reference3185 = new BitSet(new long[]{0x47E5730001010950L,0x00000000703FB16FL,0x4000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_method_reference_in_call_site_reference3187 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_METHOD_HANDLE_TYPE_FIELD_in_method_handle_reference3231 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_AT_in_method_handle_reference3233 = new BitSet(new long[]{0x47E5730001010950L,0x00000000703FB16FL,0x4000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_field_reference_in_method_handle_reference3235 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_METHOD_HANDLE_TYPE_METHOD_in_method_handle_reference3247 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_AT_in_method_handle_reference3249 = new BitSet(new long[]{0x47E5730001010950L,0x00000000703FB16FL,0x4000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_method_reference_in_method_handle_reference3251 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE_in_method_handle_reference3263 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_AT_in_method_handle_reference3265 = new BitSet(new long[]{0x47E5730001010950L,0x00000000703FB16FL,0x4000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_method_reference_in_method_handle_reference3267 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_method_handle_reference_in_method_handle_literal3283 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_reference_type_descriptor_in_method_reference3304 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_ARROW_in_method_reference3306 = new BitSet(new long[]{0x47E5730001000850L,0x00000000703FB16FL,0x4000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_member_name_in_method_reference3310 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_method_prototype_in_method_reference3312 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_reference_type_descriptor_in_field_reference3334 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_ARROW_in_field_reference3336 = new BitSet(new long[]{0x47E5730001000850L,0x00000000703FB16FL,0x4000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_member_name_in_field_reference3340 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COLON_in_field_reference3342 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_field_reference3344 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COLON_in_label3365 = new BitSet(new long[]{0x47E5730001000850L,0x00000000703FB16FL,0x0000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_simple_name_in_label3367 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COLON_in_label_ref3386 = new BitSet(new long[]{0x47E5730001000850L,0x00000000703FB16FL,0x0000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_simple_name_in_label_ref3388 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_REGISTER_in_register_list3402 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_COMMA_in_register_list3405 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_register_list3407 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_REGISTER_in_register_range3442 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_DOTDOT_in_register_range3445 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_register_range3449 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CLASS_DESCRIPTOR_in_verification_error_reference3478 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_field_reference_in_verification_error_reference3482 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_method_reference_in_verification_error_reference3486 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CATCH_DIRECTIVE_in_catch_directive3496 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_catch_directive3498 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_catch_directive3500 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_label_ref_in_catch_directive3504 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_DOTDOT_in_catch_directive3506 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_label_ref_in_catch_directive3510 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_catch_directive3512 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_label_ref_in_catch_directive3516 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CATCHALL_DIRECTIVE_in_catchall_directive3548 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_catchall_directive3550 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_label_ref_in_catchall_directive3554 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_DOTDOT_in_catchall_directive3556 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_label_ref_in_catchall_directive3560 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_catchall_directive3562 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_label_ref_in_catchall_directive3566 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PARAMETER_DIRECTIVE_in_parameter_directive3605 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_parameter_directive3607 = new BitSet(new long[]{0x0000000080200022L});
	public static final BitSet FOLLOW_COMMA_in_parameter_directive3610 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_parameter_directive3612 = new BitSet(new long[]{0x0000000080000022L});
	public static final BitSet FOLLOW_annotation_in_parameter_directive3623 = new BitSet(new long[]{0x0000000080000022L});
	public static final BitSet FOLLOW_END_PARAMETER_DIRECTIVE_in_parameter_directive3636 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_line_directive_in_debug_directive3709 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_local_directive_in_debug_directive3715 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_end_local_directive_in_debug_directive3721 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_restart_local_directive_in_debug_directive3727 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_prologue_directive_in_debug_directive3733 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_epilogue_directive_in_debug_directive3739 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_source_directive_in_debug_directive3745 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LINE_DIRECTIVE_in_line_directive3755 = new BitSet(new long[]{0x0000000000009000L,0x0000000000000000L,0x2000000000000000L,0x0000000000008204L});
	public static final BitSet FOLLOW_integral_literal_in_line_directive3757 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LOCAL_DIRECTIVE_in_local_directive3780 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_local_directive3782 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_COMMA_in_local_directive3785 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000080008L});
	public static final BitSet FOLLOW_NULL_LITERAL_in_local_directive3788 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_local_directive3794 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_COLON_in_local_directive3797 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000800400L});
	public static final BitSet FOLLOW_VOID_TYPE_in_local_directive3800 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_local_directive3804 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_COMMA_in_local_directive3838 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_local_directive3842 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_END_LOCAL_DIRECTIVE_in_end_local_directive3884 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_end_local_directive3886 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_RESTART_LOCAL_DIRECTIVE_in_restart_local_directive3909 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_restart_local_directive3911 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PROLOGUE_DIRECTIVE_in_prologue_directive3934 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_EPILOGUE_DIRECTIVE_in_epilogue_directive3955 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SOURCE_DIRECTIVE_in_source_directive3976 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_source_directive3978 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT12x_in_instruction_format12x4003 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT12x_OR_ID_in_instruction_format12x4009 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22s_in_instruction_format22s4024 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22s_OR_ID_in_instruction_format22s4030 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT31i_in_instruction_format31i4045 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT31i_OR_ID_in_instruction_format31i4051 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_METHOD_in_instruction_format35c_method4068 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_METHOD_OR_METHOD_HANDLE_TYPE_in_instruction_format35c_method4074 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format10t_in_instruction4089 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format10x_in_instruction4095 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format10x_odex_in_instruction4101 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format11n_in_instruction4107 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format11x_in_instruction4113 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format12x_in_instruction4119 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format20bc_in_instruction4125 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format20t_in_instruction4131 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21c_field_in_instruction4137 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21c_field_odex_in_instruction4143 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21c_method_handle_in_instruction4149 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21c_method_type_in_instruction4155 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21c_string_in_instruction4161 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21c_type_in_instruction4167 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21ih_in_instruction4173 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21lh_in_instruction4179 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21s_in_instruction4185 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format21t_in_instruction4191 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22b_in_instruction4197 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22c_field_in_instruction4203 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22c_field_odex_in_instruction4209 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22c_type_in_instruction4215 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22cs_field_in_instruction4221 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22s_in_instruction4227 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22t_in_instruction4233 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format22x_in_instruction4239 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format23x_in_instruction4245 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format30t_in_instruction4251 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format31c_in_instruction4257 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format31i_in_instruction4263 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format31t_in_instruction4269 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format32x_in_instruction4275 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format35c_call_site_in_instruction4281 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format35c_method_in_instruction4287 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format35c_type_in_instruction4293 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format35c_method_odex_in_instruction4299 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format35mi_method_in_instruction4305 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format35ms_method_in_instruction4311 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format3rc_call_site_in_instruction4317 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format3rc_method_in_instruction4323 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format3rc_method_odex_in_instruction4329 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format3rc_type_in_instruction4335 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format3rmi_method_in_instruction4341 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format3rms_method_in_instruction4347 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format45cc_method_in_instruction4353 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format4rcc_method_in_instruction4359 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_format51l_in_instruction4365 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_array_data_directive_in_instruction4371 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_packed_switch_directive_in_instruction4377 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insn_sparse_switch_directive_in_instruction4383 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT10t_in_insn_format10t4403 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_label_ref_in_insn_format10t4405 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT10x_in_insn_format10x4435 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT10x_ODEX_in_insn_format10x_odex4463 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT11n_in_insn_format11n4484 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format11n4486 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format11n4488 = new BitSet(new long[]{0x0000000000009000L,0x0000000000000000L,0x2000000000000000L,0x0000000000008204L});
	public static final BitSet FOLLOW_integral_literal_in_insn_format11n4490 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT11x_in_insn_format11x4522 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format11x4524 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_instruction_format12x_in_insn_format12x4554 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format12x4556 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format12x4558 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format12x4560 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT20bc_in_insn_format20bc4592 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000400000L});
	public static final BitSet FOLLOW_VERIFICATION_ERROR_TYPE_in_insn_format20bc4594 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format20bc4596 = new BitSet(new long[]{0x47E5730001010950L,0x00000000703FB16FL,0x4000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_verification_error_reference_in_insn_format20bc4598 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT20t_in_insn_format20t4635 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_label_ref_in_insn_format20t4637 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_FIELD_in_insn_format21c_field4667 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21c_field4669 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format21c_field4671 = new BitSet(new long[]{0x47E5730001010950L,0x00000000703FB16FL,0x4000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_field_reference_in_insn_format21c_field4673 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_FIELD_ODEX_in_insn_format21c_field_odex4705 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21c_field_odex4707 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format21c_field_odex4709 = new BitSet(new long[]{0x47E5730001010950L,0x00000000703FB16FL,0x4000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_field_reference_in_insn_format21c_field_odex4711 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_METHOD_HANDLE_in_insn_format21c_method_handle4749 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21c_method_handle4751 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format21c_method_handle4753 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L,0x0000000000000000L,0x0000000000000003L});
	public static final BitSet FOLLOW_method_handle_reference_in_insn_format21c_method_handle4755 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_METHOD_TYPE_in_insn_format21c_method_type4801 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21c_method_type4803 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format21c_method_type4805 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_method_prototype_in_insn_format21c_method_type4807 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_STRING_in_insn_format21c_string4851 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21c_string4853 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format21c_string4855 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_insn_format21c_string4857 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21c_TYPE_in_insn_format21c_type4889 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21c_type4891 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format21c_type4893 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_insn_format21c_type4895 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21ih_in_insn_format21ih4927 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21ih4929 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format21ih4931 = new BitSet(new long[]{0x0000018000009800L,0x0000000000000000L,0x2000000000000000L,0x0000000000008204L});
	public static final BitSet FOLLOW_fixed_32bit_literal_in_insn_format21ih4933 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21lh_in_insn_format21lh4965 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21lh4967 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format21lh4969 = new BitSet(new long[]{0x0000018000009800L,0x0000000000000000L,0x2000000000000000L,0x0000000000008204L});
	public static final BitSet FOLLOW_fixed_32bit_literal_in_insn_format21lh4971 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21s_in_insn_format21s5003 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21s5005 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format21s5007 = new BitSet(new long[]{0x0000000000009000L,0x0000000000000000L,0x2000000000000000L,0x0000000000008204L});
	public static final BitSet FOLLOW_integral_literal_in_insn_format21s5009 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT21t_in_insn_format21t5041 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format21t5043 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format21t5045 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_label_ref_in_insn_format21t5047 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22b_in_insn_format22b5079 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22b5081 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22b5083 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22b5085 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22b5087 = new BitSet(new long[]{0x0000000000009000L,0x0000000000000000L,0x2000000000000000L,0x0000000000008204L});
	public static final BitSet FOLLOW_integral_literal_in_insn_format22b5089 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_FIELD_in_insn_format22c_field5123 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_field5125 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22c_field5127 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_field5129 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22c_field5131 = new BitSet(new long[]{0x47E5730001010950L,0x00000000703FB16FL,0x4000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_field_reference_in_insn_format22c_field5133 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_FIELD_ODEX_in_insn_format22c_field_odex5167 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_field_odex5169 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22c_field_odex5171 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_field_odex5173 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22c_field_odex5175 = new BitSet(new long[]{0x47E5730001010950L,0x00000000703FB16FL,0x4000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_field_reference_in_insn_format22c_field_odex5177 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22c_TYPE_in_insn_format22c_type5217 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_type5219 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22c_type5221 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22c_type5223 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22c_type5225 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_insn_format22c_type5227 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22cs_FIELD_in_insn_format22cs_field5261 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22cs_field5263 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22cs_field5265 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22cs_field5267 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22cs_field5269 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_FIELD_OFFSET_in_insn_format22cs_field5271 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_instruction_format22s_in_insn_format22s5292 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22s5294 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22s5296 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22s5298 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22s5300 = new BitSet(new long[]{0x0000000000009000L,0x0000000000000000L,0x2000000000000000L,0x0000000000008204L});
	public static final BitSet FOLLOW_integral_literal_in_insn_format22s5302 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22t_in_insn_format22t5336 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22t5338 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22t5340 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22t5342 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22t5344 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_label_ref_in_insn_format22t5346 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT22x_in_insn_format22x5380 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22x5382 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format22x5384 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format22x5386 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT23x_in_insn_format23x5418 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format23x5420 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format23x5422 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format23x5424 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format23x5426 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format23x5428 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT30t_in_insn_format30t5462 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_label_ref_in_insn_format30t5464 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT31c_in_insn_format31c5494 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format31c5496 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format31c5498 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_insn_format31c5500 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_instruction_format31i_in_insn_format31i5531 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format31i5533 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format31i5535 = new BitSet(new long[]{0x0000018000009800L,0x0000000000000000L,0x2000000000000000L,0x0000000000008204L});
	public static final BitSet FOLLOW_fixed_32bit_literal_in_insn_format31i5537 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT31t_in_insn_format31t5569 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format31t5571 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format31t5573 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_label_ref_in_insn_format31t5575 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT32x_in_insn_format32x5607 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format32x5609 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format32x5611 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format32x5613 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_CALL_SITE_in_insn_format35c_call_site5650 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format35c_call_site5652 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_register_list_in_insn_format35c_call_site5654 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format35c_call_site5656 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format35c_call_site5658 = new BitSet(new long[]{0x47E5730001000850L,0x00000000703FB16FL,0x0000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_call_site_reference_in_insn_format35c_call_site5660 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_instruction_format35c_method_in_insn_format35c_method5692 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format35c_method5694 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_register_list_in_insn_format35c_method5696 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format35c_method5698 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format35c_method5700 = new BitSet(new long[]{0x47E5730001010950L,0x00000000703FB16FL,0x4000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_method_reference_in_insn_format35c_method5702 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_TYPE_in_insn_format35c_type5734 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format35c_type5736 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_register_list_in_insn_format35c_type5738 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format35c_type5740 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format35c_type5742 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_insn_format35c_type5744 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35c_METHOD_ODEX_in_insn_format35c_method_odex5776 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format35c_method_odex5778 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_register_list_in_insn_format35c_method_odex5780 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format35c_method_odex5782 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format35c_method_odex5784 = new BitSet(new long[]{0x47E5730001010950L,0x00000000703FB16FL,0x4000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_method_reference_in_insn_format35c_method_odex5786 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35mi_METHOD_in_insn_format35mi_method5807 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format35mi_method5809 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_register_list_in_insn_format35mi_method5811 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format35mi_method5813 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format35mi_method5815 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_INLINE_INDEX_in_insn_format35mi_method5817 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT35ms_METHOD_in_insn_format35ms_method5838 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format35ms_method5840 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_register_list_in_insn_format35ms_method5842 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format35ms_method5844 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format35ms_method5846 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_VTABLE_INDEX_in_insn_format35ms_method5848 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT3rc_CALL_SITE_in_insn_format3rc_call_site5874 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format3rc_call_site5876 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_register_range_in_insn_format3rc_call_site5878 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format3rc_call_site5880 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format3rc_call_site5882 = new BitSet(new long[]{0x47E5730001000850L,0x00000000703FB16FL,0x0000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_call_site_reference_in_insn_format3rc_call_site5884 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT3rc_METHOD_in_insn_format3rc_method5916 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format3rc_method5918 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_register_range_in_insn_format3rc_method5920 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format3rc_method5922 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format3rc_method5924 = new BitSet(new long[]{0x47E5730001010950L,0x00000000703FB16FL,0x4000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_method_reference_in_insn_format3rc_method5926 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT3rc_METHOD_ODEX_in_insn_format3rc_method_odex5958 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format3rc_method_odex5960 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_register_list_in_insn_format3rc_method_odex5962 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format3rc_method_odex5964 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format3rc_method_odex5966 = new BitSet(new long[]{0x47E5730001010950L,0x00000000703FB16FL,0x4000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_method_reference_in_insn_format3rc_method_odex5968 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT3rc_TYPE_in_insn_format3rc_type5989 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format3rc_type5991 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_register_range_in_insn_format3rc_type5993 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format3rc_type5995 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format3rc_type5997 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000000L,0x0000000000000000L,0x0000000000000400L});
	public static final BitSet FOLLOW_nonvoid_type_descriptor_in_insn_format3rc_type5999 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT3rmi_METHOD_in_insn_format3rmi_method6031 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format3rmi_method6033 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_register_range_in_insn_format3rmi_method6035 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format3rmi_method6037 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format3rmi_method6039 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_INLINE_INDEX_in_insn_format3rmi_method6041 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT3rms_METHOD_in_insn_format3rms_method6062 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format3rms_method6064 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_register_range_in_insn_format3rms_method6066 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format3rms_method6068 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format3rms_method6070 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000001000000L});
	public static final BitSet FOLLOW_VTABLE_INDEX_in_insn_format3rms_method6072 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT45cc_METHOD_in_insn_format45cc_method6093 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format45cc_method6095 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_register_list_in_insn_format45cc_method6097 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format45cc_method6099 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format45cc_method6101 = new BitSet(new long[]{0x47E5730001010950L,0x00000000703FB16FL,0x4000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_method_reference_in_insn_format45cc_method6103 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format45cc_method6105 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_method_prototype_in_insn_format45cc_method6107 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT4rcc_METHOD_in_insn_format4rcc_method6141 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000010L});
	public static final BitSet FOLLOW_OPEN_BRACE_in_insn_format4rcc_method6143 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_register_range_in_insn_format4rcc_method6145 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_CLOSE_BRACE_in_insn_format4rcc_method6147 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format4rcc_method6149 = new BitSet(new long[]{0x47E5730001010950L,0x00000000703FB16FL,0x4000000000000000L,0x0000000000C1170FL});
	public static final BitSet FOLLOW_method_reference_in_insn_format4rcc_method6151 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format4rcc_method6153 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_method_prototype_in_insn_format4rcc_method6155 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSTRUCTION_FORMAT51l_in_insn_format51l6189 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000001000L});
	public static final BitSet FOLLOW_REGISTER_in_insn_format51l6191 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COMMA_in_insn_format51l6193 = new BitSet(new long[]{0x0000018001809800L,0x0000000000000000L,0x2000000000000000L,0x0000000000008204L});
	public static final BitSet FOLLOW_fixed_literal_in_insn_format51l6195 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ARRAY_DATA_DIRECTIVE_in_insn_array_data_directive6222 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000000L,0x0000000000000204L});
	public static final BitSet FOLLOW_parsed_integer_literal_in_insn_array_data_directive6228 = new BitSet(new long[]{0x0000018005809800L,0x0000000000000000L,0x2000000000000000L,0x0000000000008204L});
	public static final BitSet FOLLOW_fixed_literal_in_insn_array_data_directive6240 = new BitSet(new long[]{0x0000018005809800L,0x0000000000000000L,0x2000000000000000L,0x0000000000008204L});
	public static final BitSet FOLLOW_END_ARRAY_DATA_DIRECTIVE_in_insn_array_data_directive6243 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PACKED_SWITCH_DIRECTIVE_in_insn_packed_switch_directive6289 = new BitSet(new long[]{0x0000018000009800L,0x0000000000000000L,0x2000000000000000L,0x0000000000008204L});
	public static final BitSet FOLLOW_fixed_32bit_literal_in_insn_packed_switch_directive6295 = new BitSet(new long[]{0x0000000040100000L});
	public static final BitSet FOLLOW_label_ref_in_insn_packed_switch_directive6301 = new BitSet(new long[]{0x0000000040100000L});
	public static final BitSet FOLLOW_END_PACKED_SWITCH_DIRECTIVE_in_insn_packed_switch_directive6308 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SPARSE_SWITCH_DIRECTIVE_in_insn_sparse_switch_directive6382 = new BitSet(new long[]{0x0000018100009800L,0x0000000000000000L,0x2000000000000000L,0x0000000000008204L});
	public static final BitSet FOLLOW_fixed_32bit_literal_in_insn_sparse_switch_directive6389 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_ARROW_in_insn_sparse_switch_directive6391 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_label_ref_in_insn_sparse_switch_directive6393 = new BitSet(new long[]{0x0000018100009800L,0x0000000000000000L,0x2000000000000000L,0x0000000000008204L});
	public static final BitSet FOLLOW_END_SPARSE_SWITCH_DIRECTIVE_in_insn_sparse_switch_directive6401 = new BitSet(new long[]{0x0000000000000002L});
}
