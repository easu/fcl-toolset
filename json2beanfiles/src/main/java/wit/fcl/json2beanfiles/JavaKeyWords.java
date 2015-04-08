package wit.fcl.json2beanfiles;

import java.util.HashSet;
import java.util.Set;

public class JavaKeyWords {
	private static Set<String> keyset = new HashSet<String>();
	private static String[] keys = { "private", "protected", "public",
			"abstract", "class", "extends", "final", "implements", "interface",
			"native", "new", "static", "strictfp", "synchronized", "transient",
			"volatile", "break", "continue", "return", "do", "while", "if",
			"else", "for", "instanceof", "switch", "case", "default", "catch",
			"finally", "throw", "throws", "try", "import", "package",
			"boolean", "byte", "char", "double", "float", "int", "long",
			"short", "null", "true", "false", "super", "this", "void" };
	static {
		for (int i = 0; i < keys.length; i++) {
			keyset.add(keys[i]);
		}
	}

	public static boolean containsJavaKey(String key) {
		return keyset.contains(key);
	}
}
