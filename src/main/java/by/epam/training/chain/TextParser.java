package by.epam.training.chain;

import by.epam.training.composite.TextComponent;
import by.epam.training.composite.TextComposite;

import static by.epam.training.composite.ComponentType.PARAGRAPH;
import static by.epam.training.util.Separator.PARAGRAPHS_SEPARATOR;

public class TextParser extends DataParser {

    public TextParser(DataParser nextParser) {
        super(nextParser);
    }

    @Override
    public void parseText(TextComponent component, String input) {
        String[] paragraphs = input.trim().split(PARAGRAPHS_SEPARATOR);
        for (String currentString : paragraphs) {
            TextComposite currentComposite = new TextComposite(PARAGRAPH);
            component.add(currentComposite);
            this.getNext().parseText(currentComposite, currentString);
        }
    }
}
