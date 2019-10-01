package by.epam.training.util;

public class Separator {
    public static final String PARAGRAPHS_SEPARATOR = "\n\r\t";
    public static final String LEXEMES_SEPARATOR = "\\p{Blank}+"; //The character class \p{Blank} matches a space or tab character.
    public static final String SENTENCES_REGEXP = "([^\\.{3}!?\\.]+[\\.{3}!?\\.])";
    public static final String SPACE_SEPARATOR = " ";
    public static final String LINES_SEPARATOR = "\n\r";

    private Separator() {}
}
