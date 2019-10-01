package by.epam.training.chain;

import by.epam.training.composite.TextComponent;

public abstract class DataParser {
    private DataParser next;

    DataParser(){} //no-arg to create WordParser without next chain

    DataParser(DataParser nextParser) {
        this.next = nextParser;
    }

    DataParser getNext() {
        return this.next;
    }

    public abstract void parseText(TextComponent component, String input);
}
