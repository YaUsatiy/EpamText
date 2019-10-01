package by.epam.training.reader;

import by.epam.training.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static by.epam.training.util.Separator.LINES_SEPARATOR;

public class TextReader {
    private static final Logger log = LogManager.getLogger(TextReader.class);

    public String readContent(String pathToFile) throws CustomException {
        if (pathToFile == null) {
            log.error("Null path to file in read method!");
            throw new CustomException("Null path to file in read method!");
        }
        FileReader fileReader;
        try {
            Path path = Paths.get(getClass().getResource(pathToFile).toURI());
            fileReader = new FileReader(String.valueOf(path));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Stream<String> stream = bufferedReader.lines();
            StringBuilder builder = new StringBuilder();
            stream.forEach(o -> builder.append(o).append(LINES_SEPARATOR));
            return builder.toString().trim();

        } catch (IOException | URISyntaxException e){
            log.error("Wrong filepath: " + pathToFile);
            throw new CustomException(e);
        } catch (Exception e) {
            throw new CustomException("Unexpected error with path: " + pathToFile, e);
        }
    }
}
