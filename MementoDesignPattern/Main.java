package MementoDesignPattern;


import java.util.ArrayList;
import java.util.List;

// 1. MEMENTO
// Stores a saved state of the canvas
class Memento {
    private final List<String> shapes;

    public Memento(List<String> shapes) {
        this.shapes = new ArrayList<>(shapes);
    }

    public List<String> getSavedShapes() {
        return new ArrayList<>(shapes);
    }
}

// 2. ORIGINATOR
// Canvas creates and restores saved states
class Canvas {
    private List<String> shapes = new ArrayList<>();

    public void addShape(String shape) {
        shapes.add(shape);
        System.out.println("Added: " + shape);
    }

    public void display() {
        System.out.println("Canvas: " + shapes);
    }

    // Save current canvas state
    public Memento save() {
        return new Memento(shapes);
    }

    // Restore a previously saved state
    public void restore(Memento memento) {
        shapes = memento.getSavedShapes();
        System.out.println("Canvas state restored.");
    }
}

// 3. CARETAKER
// Manages undo and redo history
class Caretaker {
    private List<Memento> history = new ArrayList<>();
    private List<Memento> redoHistory = new ArrayList<>();

    public void saveState(Canvas canvas) {
        history.add(canvas.save());

        // A new change clears the old redo history
        redoHistory.clear();
    }

    public void undo(Canvas canvas) {
        if (history.size() <= 1) {
            System.out.println("Nothing to undo!");
            return;
        }

        // Save current state for redo
        redoHistory.add(history.remove(history.size() - 1));

        // Restore previous state
        canvas.restore(history.get(history.size() - 1));
    }

    public void redo(Canvas canvas) {
        if (redoHistory.isEmpty()) {
            System.out.println("Nothing to redo!");
            return;
        }

        // Get the state to restore
        Memento memento = redoHistory.remove(redoHistory.size() - 1);

        // Restore it and add it back to undo history
        history.add(memento);
        canvas.restore(memento);
    }
}

// 4. MAIN CLASS
public class Main {
    public static void main(String[] args) {

        Canvas canvas = new Canvas();
        Caretaker caretaker = new Caretaker();

        // Save the initial empty canvas
        caretaker.saveState(canvas);

        // Add Circle
        canvas.addShape("Circle");
        caretaker.saveState(canvas);
        canvas.display();

        // Add Square
        canvas.addShape("Square");
        caretaker.saveState(canvas);
        canvas.display();

        // Undo Square
        System.out.println("\n--- Undo ---");
        caretaker.undo(canvas);
        canvas.display();

        // Redo Square
        System.out.println("\n--- Redo ---");
        caretaker.redo(canvas);
        canvas.display();

        // Add Triangle
        System.out.println("\n--- Add Triangle ---");
        canvas.addShape("Triangle");
        caretaker.saveState(canvas);
        canvas.display();

        // Undo Triangle
        System.out.println("\n--- Undo ---");
        caretaker.undo(canvas);
        canvas.display();

        // Redo Triangle
        System.out.println("\n--- Redo ---");
        caretaker.redo(canvas);
        canvas.display();
    }
}
