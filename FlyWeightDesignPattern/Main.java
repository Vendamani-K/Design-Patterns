package FlyWeightDesignPattern;
    
import java.util.HashMap;
import java.util.Map;

// 1. Flyweight interface
interface Character {
    void render(int x, int y);
}

// 2. Concrete Flyweight
class CharacterGlyph implements Character {
    private char symbol;

    public CharacterGlyph(char symbol) {
        this.symbol = symbol;
        System.out.println("Creating character: " + symbol);
    }

    @Override
    public void render(int x, int y) {
        System.out.println(
            "Rendering '" + symbol +
            "' at position (" + x + ", " + y + ")"
        );
    }
}

// 3. Flyweight Factory
class CharacterFactory {
    private Map<java.lang.Character, Character> characters =
            new HashMap<>();

    public Character getCharacter(char symbol) {
        if (!characters.containsKey(symbol)) {
            characters.put(symbol, new CharacterGlyph(symbol));
        }

        return characters.get(symbol);
    }
}

// 4. Client
public class Main {
    public static void main(String[] args) {
        CharacterFactory factory = new CharacterFactory();

        Character c1 = factory.getCharacter('A');
        Character c2 = factory.getCharacter('B');
        Character c3 = factory.getCharacter('A');

        c1.render(10, 20);
        c2.render(30, 20);
        c3.render(50, 20);

        System.out.println("Same A object? " + (c1 == c3));
    }
}

    
