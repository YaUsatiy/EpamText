package by.epam.training.action;

import by.epam.training.chain.*;
import by.epam.training.composite.ComponentType;
import by.epam.training.composite.Symbol;
import by.epam.training.composite.TextComponent;
import by.epam.training.composite.TextComposite;
import by.epam.training.exception.CustomException;
import by.epam.training.reader.TextReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static by.epam.training.composite.ComponentType.TEXT;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class SortActionTest {
    private static final String FILEPATH = "/data/data.txt";
    private static TextReader reader;
    private static SortAction sortAction;
    private static TextComposite mainComposite;
    private static DataParser wordParser;
    private static DataParser lexemeParser;
    private static DataParser sentenceParser;
    private static DataParser paragraphParser;
    private static DataParser textParser;
    private static String content;

    @BeforeClass
    public static void setUp() throws CustomException {
        sortAction = new SortAction();
        reader = new TextReader();
        content = reader.readContent(FILEPATH);
        wordParser = new WordParser();
        lexemeParser = new LexemeParser(wordParser);
        sentenceParser = new SentenceParser(lexemeParser);
        paragraphParser = new ParagraphParser(sentenceParser);
        textParser = new TextParser(paragraphParser);
        mainComposite = new TextComposite(TEXT);
        textParser.parseText(mainComposite, content);
    }

    @Test
    public void wrongParameterTest() {
        java.util.Optional<TextComposite> composite = sortAction.sortParagraphBySentences(mainComposite.getTextComponents().get().get(0));
        boolean actual = composite.isPresent();
        assertFalse(actual);
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void wrongOperationTest() {
        TextComponent component = new Symbol('a', ComponentType.LETTER);
        component.add(new TextComposite(ComponentType.SENTENCE));
    }

    @Test
    public void countLexemesTest() {
        int actual = mainComposite.getTextComponents().get().get(2).getTextComponents().get().get(0).getAmount(); //first sentence of the third paragraph
        int expected = 19;
        assertEquals(actual, expected);
    }

    @Test
    public void sortByCountOfSymbolTest() throws CustomException {
        TextComponent paragraph = mainComposite.getTextComponents().get().get(1);
        TextComponent sentence = paragraph.getTextComponents().get().get(0); //first sentence of the second paragraph
        sentence = sortAction.sortByCountOfSymbol(sentence, 'a').get();
        String actualValue = sentence.write();
        String expectedValue = reader.readContent("/data/sortedLexemes.txt");
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void sortParagraphBySentences() throws CustomException {
        TextComponent text = sortAction.sortParagraphBySentences(mainComposite).get();
        String actualValue = text.write();
        String expectedValue = reader.readContent("/data/sortedParagraphs.txt");
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void sortWordsByLength() throws CustomException {
        TextComponent paragraph = mainComposite.getTextComponents().get().get(1);
        TextComponent sentence = paragraph.getTextComponents().get().get(0); //first sentence of the second paragraph
        sentence = sortAction.sortWordsByLength(sentence).get();
        String actualValue = sentence.write();
        String expectedValue = reader.readContent("/data/sortedWords.txt");
        assertEquals(actualValue, expectedValue);
    }
}