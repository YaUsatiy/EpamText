package by.epam.training.reader;

import by.epam.training.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class TextReader {
    private static final Logger log = LogManager.getLogger(TextReader.class);

    public String readInfo(String filepath) throws CustomException {
        FileReader fileReader;
        try{
            fileReader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Stream<String> stream = bufferedReader.lines();
            StringBuilder builder = new StringBuilder();
            stream.forEach(o -> builder.append(" " + o));
            return builder.toString().trim();

        }catch (IOException e){
            log.error("Wrong filepath: " + filepath, e);
            throw new CustomException(e);
        }
    }
}
