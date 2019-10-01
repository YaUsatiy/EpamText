package by.epam.training.chain;

import by.epam.training.composite.TextComponent;
import by.epam.training.composite.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.training.composite.ComponentType.SENTENCE;
import static by.epam.training.util.Separator.SENTENCES_REGEXP;

public class ParagraphParser extends DataParser {

    public ParagraphParser(DataParser nextParser) {
        super(nextParser);
    }

    @Override
    public void parseText(TextComponent component, String input) {
        Pattern pattern = Pattern.compile(SENTENCES_REGEXP);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            TextComposite currentComposite = new TextComposite(SENTENCE);
            component.add(currentComposite);
            this.getNext().parseText(currentComposite, matcher.group(1)); //the sentence itself without a punctuation mark
        }
    }
}
