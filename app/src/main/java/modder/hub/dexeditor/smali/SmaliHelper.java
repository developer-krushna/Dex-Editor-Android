package modder.hub.dexeditor.smali;

/**
 * SmaliHelper: Utility methods for parsing and manipulating Smali signatures and text.
 */
public class SmaliHelper {

    /**
     * Converts a Smali type signature (Lcom/pkg/Class;) or a dotted class name to slash format.
     */
    public static String smali2OnlySlash(String smaliName) {
        if (smaliName.startsWith("L") && smaliName.endsWith(";")) {
            return smaliName.substring(1, smaliName.length() - 1);
        }
        return smaliName.replace('.', '/');
    }

    /**
     * Extracts the class name without package (e.g., com/pkg/Main -> Main, or Lcom/pkg/Main; -> Main).
     */
    public static String extractSimpleName(String str) {
        if (str == null || str.isEmpty()) return "";
        int lastSlashIndex = str.lastIndexOf('/');
        int start = lastSlashIndex + 1;
        int end = str.length();
        if (str.endsWith(";")) {
            end--;
        }
        return str.substring(start, end);
    }

    /**
     * Finds the positions of the outermost double quotes in a string.
     * Useful for highlighting string literals in Smali.
     */
    public static int[] getOuterQuotePositions(String input) {
        int startQuote = -1;
        int endQuote = -1;
        boolean insideString = false;
        boolean escapeNext = false;
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (currentChar == '\\') {
                escapeNext = true;
            } else if (currentChar == '"' && !escapeNext) {
                if (!insideString) {
                    startQuote = i;
                    insideString = true;
                } else {
                    endQuote = i;
                    break;
                }
            } else {
                escapeNext = false;
            }
        }
        return new int[]{startQuote, endQuote};
    }
}
