package CompositeDesignPattern;
    
import java.util.ArrayList;
import java.util.List;

// 1. Component interface
interface FileSystemComponent {
    void display();
}

// 2. Leaf: File
class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void display() {
        System.out.println("File: " + name);
    }
}

// 3. Composite: Folder
class Folder implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components =
            new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(FileSystemComponent component) {
        components.add(component);
    }

    public void remove(FileSystemComponent component) {
        components.remove(component);
    }

    @Override
    public void display() {
        System.out.println("Folder: " + name);

        for (FileSystemComponent component : components) {
            component.display();
        }
    }
}

// 4. Main class
public class Main {
    public static void main(String[] args) {
        File file1 = new File("report.txt");
        File file2 = new File("Main.java");
        File file3 = new File("Test.java");

        Folder projects = new Folder("Projects");
        projects.add(file2);
        projects.add(file3);

        Folder documents = new Folder("Documents");
        documents.add(file1);
        documents.add(projects);

        documents.display();
    }
}

    
