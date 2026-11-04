package CommandDesignPattern;


import java.util.Stack;

// 1. RECEIVER
class Document {

    private StringBuilder content = new StringBuilder();

    public void type(String text) {
        content.append(text);
    }

    public void delete(int length) {
        content.delete(
            content.length() - length,
            content.length()
        );
    }

    public String getContent() {
        return content.toString();
    }
}

// 2. COMMAND INTERFACE
interface Command {
    void execute();
    void undo();
}

// 3. CONCRETE COMMAND
class TypeCommand implements Command {

    private Document document;
    private String text;

    public TypeCommand(Document document, String text) {
        this.document = document;
        this.text = text;
    }

    @Override
    public void execute() {
        document.type(text);
    }

    @Override
    public void undo() {
        document.delete(text.length());
    }
}

// 4. INVOKER
class TextEditor {

    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();


    public void executeCommand(Command command) {
        command.execute();

        undoStack.push(command);

        // A new operation clears the redo history
        redoStack.clear();
    }
    

    public void undo() {

        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo!");
            return;
        }

        Command command = undoStack.pop();

        command.undo();

        redoStack.push(command);
    }

    public void redo() {

        if (redoStack.isEmpty()) {
            System.out.println("Nothing to redo!");
            return;
        }

        Command command = redoStack.pop();

        command.execute();

        undoStack.push(command);
    }
}

// 5. MAIN CLASS
public class Main {

    public static void main(String[] args) {

        Document document = new Document();

        TextEditor editor = new TextEditor();

        // Type Hello
        Command command1 =
            new TypeCommand(document, "Hello ");

        editor.executeCommand(command1);

        System.out.println("After typing: "
                + document.getContent());

        // Type Java
        Command command2 =
            new TypeCommand(document, "Java");

        editor.executeCommand(command2);

        System.out.println("After typing again: "
                + document.getContent());

        // Undo last operation
        editor.undo();

        System.out.println("After undo: "
                + document.getContent());

        // Redo last undone operation
        editor.redo();

        System.out.println("After redo: "
                + document.getContent());

        // Undo again
        editor.undo();

        System.out.println("After second undo: "
                + document.getContent());
    }
}
