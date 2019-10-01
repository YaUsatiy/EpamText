package by.epam.training.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static by.epam.training.util.Separator.PARAGRAPHS_SEPARATOR;
import static by.epam.training.util.Separator.SPACE_SEPARATOR;

public class TextComposite implements TextComponent {
    private List<TextComponent> textComponents = new ArrayList<>();
    private ComponentType componentType;

    public TextComposite(ComponentType componentType) {
        this.componentType = componentType;
    }

    @Override
    public String write() {
        textComponents.removeIf(Objects::isNull);  //Removes all of the elements of this collection that satisfy the given predicate.
        StringBuilder stringBuilder = new StringBuilder();
        switch (componentType){
            case TEXT:
                for(TextComponent textComponent : textComponents){
                    stringBuilder.append(textComponent.write()).append(PARAGRAPHS_SEPARATOR);
                }
                break;
            case PARAGRAPH:
                for(TextComponent textComponent : textComponents){
                    stringBuilder.append(textComponent.write());
                }
                break;
            case SENTENCE:
                for(TextComponent textComponent : textComponents){
                    stringBuilder.append(textComponent.write()).append(SPACE_SEPARATOR);
                }
                break;
            case LEXEME:
                for(TextComponent textComponent : textComponents){
                    stringBuilder.append(textComponent.write());
                }
                break;
            case WORD:
                for(TextComponent textComponent : textComponents){
                    stringBuilder.append(textComponent.write());
                }
                break;
        }
        return stringBuilder.toString().trim();
    }

    @Override
    public boolean add(TextComponent component) {
        return textComponents.add(component);
    }

    @Override
    public boolean remove(TextComponent component) {
        return textComponents.remove(component);
    }

    @Override
    public int getAmount() {
        return textComponents.size();
    }

    @Override
    public Optional<List<TextComponent>> getTextComponents() {
        return Optional.of(textComponents);
    }

    @Override
    public ComponentType getComponentType() {
        return componentType;
    }

    @Override
    public Character getCharacter() {
        throw new UnsupportedOperationException();
    }
}
