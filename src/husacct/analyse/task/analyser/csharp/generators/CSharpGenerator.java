package husacct.analyse.task.analyser.csharp.generators;

import java.util.Arrays;

import husacct.analyse.domain.IModelCreationService;
import husacct.analyse.domain.famix.FamixCreationServiceImpl;

public class CSharpGenerator {
	public CSharpGenerator() {
		Arrays.sort(typeCollection);
		Arrays.sort(accessorCollection);
		Arrays.sort(notPartOfAttribute);
		Arrays.sort(isAPartOfAttribute);
		Arrays.sort(listOfMethodTypes);
	}

	protected static final int IDENTIFIER = 4;
	protected static final int GREATERTHAN = 17;
	protected static final int USING = 18;
	protected static final int SEMICOLON = 25;
	protected static final int BACKWARDBRACKET = 26;
	protected static final int FINALLY = 45;
	protected static final int NAMESPACE = 61;
	protected static final int FORWARDCURLYBRACKET = 62;
	protected static final int BACKWARDCURLYBRACKET = 63;
	protected static final int IS = 66;
	protected static final int NEW	= 68;
	protected static final int PUBLIC = 69;
	protected static final int PROTECTED = 70;
	protected static final int PRIVATE = 71;
	protected static final int ABSTRACT = 74;
	protected static final int STATIC = 76;
	protected static final int VOID = 82;
	protected static final int FORWARDBRACKET = 88;
	protected static final int COMMA = 89;
	protected static final int COLON = 90;
	protected static final int LESSTHAN = 99;
	protected static final int CLASS = 155;
	protected static final int INT = 164;
	protected static final int STRUCT = 169;
	protected static final int INTERFACE = 172;
	protected static final int RETURN = 153;
	protected static final int GET = 156;
	protected static final int SET = 157;
	protected static final int DOT = 14;
	protected static final int EQUALS = 137;
	protected static final int BYTE = 161;
	protected static final int SBYTE = 160;
	protected static final int UINT = 165;
	protected static final int SHORT = 162;
	protected static final int USHORT = 163;
	protected static final int LONG = 166;
	protected static final int ULONG = 167;
	protected static final int FLOAT = 199;
	protected static final int DOUBLE = 198;
	protected static final int CHAR = 168;
	protected static final int BOOL = 196;
	protected static final int OBJECT = 200;
	protected static final int STRING = 201;
	protected static final int DECIMAL = 197;
	protected static final int VAR = 177;
	protected static final int INTERNAL = 72;
	protected static final int THROW = 190;
	protected static final int CATCH = 192;
	protected static final int finalLY = 193;
	protected static final int[] typeCollection = new int[] {BYTE, SBYTE, INT, UINT, SHORT, USHORT, LONG, ULONG, FLOAT, DOUBLE, CHAR, BOOL, OBJECT, STRING, VAR, DECIMAL, IDENTIFIER};
	protected static final int[] accessorCollection = new int[] {PRIVATE, PUBLIC, PROTECTED, INTERNAL};
	protected static final int[] notPartOfAttribute = new int[] { FORWARDCURLYBRACKET, USING, NAMESPACE, CLASS, RETURN, SET, GET, DOT };
	protected static final int[] isAPartOfAttribute = new int[] { FORWARDCURLYBRACKET, SEMICOLON, BACKWARDCURLYBRACKET };
	protected static final int[] listOfMethodTypes = new int[] { FINALLY, PUBLIC, PROTECTED, PRIVATE, ABSTRACT, VOID /* synchronised */};
	protected IModelCreationService modelService = new FamixCreationServiceImpl();	
}