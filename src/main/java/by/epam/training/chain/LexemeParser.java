package by.epam.training.chain;

import by.epam.training.composite.ComponentType;
import by.epam.training.composite.Symbol;
import by.epam.training.composite.TextComponent;
import by.epam.training.composite.TextComposite;

import static java.lang.Character.isLetterOrDigit;

public class LexemeParser extends DataParser{

    public LexemeParser(DataParser nextParser) {
        super(nextParser);
    }

    @Override
    public void parseText(TextComponent component, String input) {
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int startIndex = i;
            if(isLetterOrDigit(characters[i])){
                do {
                    i++;
                } while (i < characters.length && isLetterOrDigit(characters[i]));
                TextComposite current = new TextComposite(ComponentType.WORD);
                component.add(current);
                this.getNext().parseText(current, input.substring(startIndex,i));
                i--;
            }
            else{
                do{
                    component.add(new Symbol(characters[i],ComponentType.SIGN));
                    i++;
                }while (i < characters.length && !isLetterOrDigit(characters[i]));
                i--;
            }
        }
    }
}
