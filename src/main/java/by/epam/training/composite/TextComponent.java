package by.epam.training.composite;

import java.util.List;
import java.util.Optional;

public interface TextComponent {
    String write();
    boolean add(TextComponent component);
    boolean remove(TextComponent component);
    int getAmount();
    Optional<List<TextComponent>> getTextComponents();
    ComponentType getComponentType();
    Character getCharacter();
}
