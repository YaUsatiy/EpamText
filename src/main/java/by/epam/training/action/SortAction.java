package by.epam.training.action;

import by.epam.training.composite.ComponentType;
import by.epam.training.composite.TextComponent;
import by.epam.training.composite.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static by.epam.training.composite.ComponentType.*;

class SortAction {
    private static Logger log = LogManager.getLogger(SortAction.class);

    Optional<TextComposite> sortWordsByLength(TextComponent composite) {
        if (composite.getComponentType() != ComponentType.SENTENCE) {
            log.error("The composite isn't a sentence!");
            return Optional.empty();
        }
        List<TextComponent> bufferWords = new ArrayList<>();
        for (TextComponent lexeme : composite.getTextComponents().get()) {
            for (TextComponent wordOrSymbol : lexeme.getTextComponents().get()) {
                if (wordOrSymbol.getComponentType() == WORD) {
                    bufferWords.add(wordOrSymbol);
                }
            }
        }
        bufferWords.sort(Comparator.comparingInt(TextComponent::getAmount));
        int i = 0;
        TextComposite result = new TextComposite(SENTENCE); //sentence -> lexeme -> word -> length
        for (TextComponent lexeme : composite.getTextComponents().get()) {
            TextComponent newLexeme = new TextComposite(ComponentType.LEXEME);
            for (TextComponent wordOrSymbol : lexeme.getTextComponents().get()) {
                if (wordOrSymbol.getComponentType() == ComponentType.WORD) {
                    newLexeme.add(bufferWords.get(i++));
                } else {
                    newLexeme.add(wordOrSymbol);
                }
            }
            result.add(newLexeme);
        }
        log.info("Words in sentence are sorted by length!");
        return Optional.of(result);
    }

    Optional<TextComposite> sortParagraphBySentences(TextComponent composite) {
        if (composite.getComponentType() != TEXT) {
            log.error("The composite isn't a text!");
            return Optional.empty();
        }
        TextComposite result = new TextComposite(TEXT); //text -> paragraph -> sentence
        List<TextComponent> bufferParagraphs = new ArrayList<>(composite.getTextComponents().get());
        bufferParagraphs.sort(Comparator.comparingInt(TextComponent::getAmount));
        bufferParagraphs.forEach(result::add);
        log.info("Paragraphs in text are sorted by sentences!");
        return Optional.of(result);
    }

    private int countOfSymbol(TextComponent composite, Character ch) {
        int result = 0;
        if (composite.getTextComponents().isPresent()) {
            for (TextComponent component : composite.getTextComponents().get()) {
                result += countOfSymbol(component, ch);
            }
        } else {
            if (composite.getCharacter() == ch) {
                return 1;
            }
        }
        return result;
    }

    Optional<TextComposite> sortByCountOfSymbol(TextComponent composite, Character ch) {
        if (composite.getComponentType() != ComponentType.SENTENCE) {
            log.error("This composite isn't a sentence");
            return Optional.empty();
        }
        SortAction executor = new SortAction();
        TextComposite result = new TextComposite(ComponentType.SENTENCE);
        List<TextComponent> lexemeBuffer = new ArrayList<>(composite.getTextComponents().get());
        lexemeBuffer.sort(((Comparator<TextComponent>)
                (o1, o2) -> Integer.compare(executor.countOfSymbol(o1, ch), executor.countOfSymbol(o2, ch))).
                reversed().thenComparing(o -> o.write().toLowerCase()));
        lexemeBuffer.forEach(result::add);
        log.info("Lexemes in sentence are sorted by count of symbol \"" + ch + "\"");
        return Optional.of(result);
    }
}
