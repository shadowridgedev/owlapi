/* Generated By:JavaCC: Do not edit this line. TokenMgrException.java Version 7.0 */
/* JavaCCOptions: */
package org.coode.owlapi.obo12.parser;

@SuppressWarnings("all")
class TokenMgrException extends org.semanticweb.owlapi.io.OWLParserException {

    private static final long serialVersionUID = 1L;

    public static final int LEXICAL_ERROR = 0;

    public static final int STATIC_LEXER_ERROR = 1;

    public static final int INVALID_LEXICAL_STATE = 2;

    public static final int LOOP_DETECTED = 3;

    int errorCode;

    protected static final String addEscapes(String str) {
        StringBuffer retval = new StringBuffer();
        char ch;
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case '\b':
                    retval.append("\\b");
                    continue;
                case '\t':
                    retval.append("\\t");
                    continue;
                case '\n':
                    retval.append("\\n");
                    continue;
                case '\f':
                    retval.append("\\f");
                    continue;
                case '\r':
                    retval.append("\\r");
                    continue;
                case '\"':
                    retval.append("\\\"");
                    continue;
                case '\'':
                    retval.append("\\\'");
                    continue;
                case '\\':
                    retval.append("\\\\");
                    continue;
                default:
                    if ((ch = str.charAt(i)) < 0x20 || ch > 0x7e) {
                        String s = "0000" + Integer.toString(ch, 16);
                        retval.append("\\u" + s.substring(s.length() - 4, s.length()));
                    } else {
                        retval.append(ch);
                    }
                    continue;
            }
        }
        return retval.toString();
    }

    protected static String LexicalErr(boolean EOFSeen, int lexState, int errorLine,
                    int errorColumn, String errorAfter, int curChar) {
        char curChar1 = (char) curChar;
        return ("Lexical error at line " + errorLine + ", column " + errorColumn
                        + ".  Encountered: "
                        + (EOFSeen ? "<EOF> "
                                        : ("\"" + addEscapes(String.valueOf(curChar1)) + "\"")
                                                        + " (" + (int) curChar + "), ")
                        + "after : \"" + addEscapes(errorAfter) + "\"");
    }

    public String getMessage() {
        return super.getMessage();
    }

    public TokenMgrException() {}

    public TokenMgrException(String message, int reason) {
        super(message);
        errorCode = reason;
    }

    public TokenMgrException(boolean EOFSeen, int lexState, int errorLine, int errorColumn,
                    String errorAfter, int curChar, int reason) {
        this(LexicalErr(EOFSeen, lexState, errorLine, errorColumn, errorAfter, curChar), reason);
    }
}
/* JavaCC - OriginalChecksum=4cb75ec0b3b91e2164de3b1ccd0a9301 (do not edit this line) */
