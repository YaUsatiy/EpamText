package by.epam.training.chain;

import by.epam.training.composite.Symbol;
import by.epam.training.composite.TextComponent;

import static by.epam.training.composite.ComponentType.LETTER;

public class WordParser extends DataParser {

    public WordParser() {}

    @Override
    public void parseText(TextComponent component, String input) {
        char[] characters = input.toCharArray();
        for (char ch : characters) {
            component.add(new Symbol(ch, LETTER));
        }
    }
}
