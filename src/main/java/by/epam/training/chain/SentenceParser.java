package by.epam.training.chain;

import by.epam.training.composite.TextComponent;
import by.epam.training.composite.TextComposite;
import by.epam.training.util.Separator;

import static by.epam.training.composite.ComponentType.LEXEME;

public class SentenceParser extends DataParser {

    public SentenceParser(DataParser nextParser) {
        super(nextParser);
    }

    @Override
    public void parseText(TextComponent component, String input) {
        String[] lexemes = input.trim().split(Separator.LEXEMES_SEPARATOR);
        for (String currentString : lexemes) {
            TextComposite currentComposite = new TextComposite(LEXEME);
            component.add(currentComposite);
            this.getNext().parseText(currentComposite, currentString);
        }
    }
}
