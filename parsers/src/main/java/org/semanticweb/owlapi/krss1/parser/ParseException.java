/* Generated By:JavaCC: Do not edit this line. ParseException.java Version 7.0 */
/* JavaCCOptions:KEEP_LINE_COLUMN=true */
package org.semanticweb.owlapi.krss1.parser;

@SuppressWarnings("all")
public class ParseException extends org.semanticweb.owlapi.io.OWLParserException {

    private static final long serialVersionUID = 1L;

    private static final String INDENT = "    ";

    protected static String EOL = "\n";


    public ParseException(Token currentTokenVal, int[][] expectedTokenSequencesVal,
                    String[] tokenImageVal) {
        this(currentTokenVal, expectedTokenSequencesVal, tokenImageVal, null);
    }


    public ParseException(Token currentTokenVal, int[][] expectedTokenSequencesVal,
                    String[] tokenImageVal, String lexicalStateName) {
        super(initialise(currentTokenVal, expectedTokenSequencesVal, tokenImageVal,
                        lexicalStateName));
        currentToken = currentTokenVal;
        expectedTokenSequences = expectedTokenSequencesVal;
        tokenImage = tokenImageVal;
    }

    public ParseException() {
        super();
    }

    public ParseException(String message) {
        super(message);
    }


    public Token currentToken;

    public int[][] expectedTokenSequences;

    public String[] tokenImage;

    private static String initialise(Token currentToken, int[][] expectedTokenSequences,
                    String[] tokenImage, String lexicalStateName) {
        StringBuilder sb = new StringBuilder();
        StringBuffer expected = new StringBuffer();

        int maxSize = 0;
        java.util.TreeSet<String> sortedOptions = new java.util.TreeSet<String>();
        for (int i = 0; i < expectedTokenSequences.length; i++) {
            if (maxSize < expectedTokenSequences[i].length) {
                maxSize = expectedTokenSequences[i].length;
            }
            for (int j = 0; j < expectedTokenSequences[i].length; j++) {
                sortedOptions.add(tokenImage[expectedTokenSequences[i][j]]);
            }
        }

        for (String option : sortedOptions) {
            expected.append(INDENT).append(option).append(EOL);
        }

        sb.append("Encountered unexpected token:");

        Token tok = currentToken.next;
        for (int i = 0; i < maxSize; i++) {
            String tokenText = tok.image;
            String escapedTokenText = add_escapes(tokenText);
            if (i != 0) {
                sb.append(" ");
            }
            if (tok.kind == 0) {
                sb.append(tokenImage[0]);
                break;
            }
            sb.append(" \"");
            sb.append(escapedTokenText);
            sb.append("\"");
            sb.append(" " + tokenImage[tok.kind]);
            tok = tok.next;
        }
        sb.append(EOL).append(INDENT).append("at line " + currentToken.next.beginLine + ", column "
                        + currentToken.next.beginColumn);
        sb.append(".").append(EOL);

        if (expectedTokenSequences.length == 0) {
            // Nothing to add here
        } else {
            int numExpectedTokens = expectedTokenSequences.length;
            sb.append(EOL).append("Was expecting" + (numExpectedTokens == 1 ? ":" : " one of:")
                            + EOL + EOL);
            sb.append(expected.toString());
        }
        return sb.toString();
    }


    static String add_escapes(String str) {
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

}
/* JavaCC - OriginalChecksum=27e34fe1ea7e10264da98c6f957c70b8 (do not edit this line) */
