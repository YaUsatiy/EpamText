package by.epam.training.reader;

import by.epam.training.exception.CustomException;
import by.epam.training.util.Separator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TextReaderTest {
    private static TextReader reader;

    @BeforeClass
    public void setUp(){
        reader = new TextReader();
    }

    @Test(expectedExceptions = CustomException.class)
    public void testNegativeRead() throws CustomException {
        reader.readContent("/data/nonexistent.txt");
    }

    @Test
    public void testPositiveRead() throws CustomException {
        String actualContent = reader.readContent("/data/simpleData.txt");
        String expectedContent = "Hello from"+ Separator.LINES_SEPARATOR + "file!!" + Separator.LINES_SEPARATOR  + ":)";
        assertEquals(actualContent, expectedContent);
    }
}