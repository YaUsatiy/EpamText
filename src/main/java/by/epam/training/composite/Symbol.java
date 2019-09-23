package by.epam.training.composite;

import java.util.List;
import java.util.Optional;

public class Symbol implements TextComponent {
    private Character character;
    private ComponentType componentType;

    public Symbol(Character character, ComponentType componentType) {
        this.character = character;
        this.componentType = componentType;
    }

    @Override
    public Character getCharacter() {
        return character;
    }

    @Override
    public String write() {
        return character.toString();
    }

    @Override
    public boolean add(TextComponent component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(TextComponent component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getAmount() {
        return 1;
    }

    @Override
    public Optional<List<TextComponent>> getTextComponents() {
        return Optional.empty();
    }

    @Override
    public ComponentType getComponentType() {
        return componentType;
    }
}
