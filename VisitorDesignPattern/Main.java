package VisitorDesignPattern;


import java.util.ArrayList;
import java.util.List;

// 1. ELEMENT INTERFACE
interface FileSystemElement {
    void accept(FileSystemVisitor visitor);
}

// 2. VISITOR INTERFACE
interface FileSystemVisitor {
    void visitFile(File file);
    void visitDirectory(Directory directory);
}

// 3. CONCRETE ELEMENT - FILE
class File implements FileSystemElement {

    private String name;
    private double size;

    public File(String name, double size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public double getSize() {
        return size;
    }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visitFile(this);
    }
}

// 4. CONCRETE ELEMENT - DIRECTORY
class Directory implements FileSystemElement {

    private String name;
    private List<FileSystemElement> elements = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<FileSystemElement> getElements() {
        return elements;
    }

    public void addElement(FileSystemElement element) {
        elements.add(element);
    }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visitDirectory(this);
    }
}

// 5. CONCRETE VISITOR - PRINT STRUCTURE
class PrintStructureVisitor implements FileSystemVisitor {

    private int level = 0;

    @Override
    public void visitFile(File file) {
        printIndent();
        System.out.println("- " + file.getName() + " (" + file.getSize() + " KB)");
    }

    @Override
    public void visitDirectory(Directory directory) {
        printIndent();
        System.out.println("+ " + directory.getName() + "/");

        level++;

        for (FileSystemElement element : directory.getElements()) {
            element.accept(this);
        }

        level--;
    }

    private void printIndent() {
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
    }
}

// 6. CONCRETE VISITOR - CALCULATE TOTAL SIZE
class CalculateTotalSizeVisitor implements FileSystemVisitor {

    private double totalSize = 0;

    @Override
    public void visitFile(File file) {
        totalSize += file.getSize();
    }

    @Override
    public void visitDirectory(Directory directory) {
        for (FileSystemElement element : directory.getElements()) {
            element.accept(this);
        }
    }

    public double getTotalSize() {
        return totalSize;
    }
}

// 7. CONCRETE VISITOR - FIND FILE
class FindFileVisitor implements FileSystemVisitor {

    private String targetName;
    private boolean found = false;

    public FindFileVisitor(String targetName) {
        this.targetName = targetName;
    }

    @Override
    public void visitFile(File file) {
        if (file.getName().equalsIgnoreCase(targetName)) {
            System.out.println("File found: " + file.getName()
                    + " (" + file.getSize() + " KB)");
            found = true;
        }
    }

    @Override
    public void visitDirectory(Directory directory) {
        for (FileSystemElement element : directory.getElements()) {
            element.accept(this);
        }
    }

    public boolean isFound() {
        return found;
    }
}

// 8. MAIN CLASS
public class Main {

    public static void main(String[] args) {

        // Create root directory
        Directory root = new Directory("Root");

        // Create subdirectories
        Directory documents = new Directory("Documents");
        Directory pictures = new Directory("Pictures");

        // Create files
        File file1 = new File("Resume.pdf", 120);
        File file2 = new File("Notes.txt", 50);
        File file3 = new File("Photo.jpg", 500);
        File file4 = new File("Report.docx", 200);

        // Add files to directories
        documents.addElement(file1);
        documents.addElement(file2);
        documents.addElement(file4);

        pictures.addElement(file3);

        // Add directories to root
        root.addElement(documents);
        root.addElement(pictures);

        // VISITOR 1: PRINT FILE STRUCTURE
        System.out.println("===== FILE STRUCTURE =====");

        PrintStructureVisitor printVisitor =
                new PrintStructureVisitor();

        root.accept(printVisitor);

        // VISITOR 2: CALCULATE TOTAL SIZE
        System.out.println("\n===== TOTAL SIZE =====");

        CalculateTotalSizeVisitor sizeVisitor =
                new CalculateTotalSizeVisitor();

        root.accept(sizeVisitor);

        System.out.println("Total Size: "
                + sizeVisitor.getTotalSize() + " KB");

        // VISITOR 3: FIND FILE
        System.out.println("\n===== FIND FILE =====");

        FindFileVisitor findVisitor =
                new FindFileVisitor("Photo.jpg");

        root.accept(findVisitor);

        if (!findVisitor.isFound()) {
            System.out.println("File not found.");
        }
    }
}
