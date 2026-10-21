
interface Shape {
    Shape clone();

    void draw();
}

// Concrete Prototype: Circle
class Circle implements Shape {
    private int x;
    private int y;
    private int radius;
    private String color;

    public Circle(int x, int y, int radius, String color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public Shape clone() {
        return new Circle(x, y, radius, color);
    }

    @Override
    public void draw() {
        System.out.println(
            "Circle: position=(" + x + ", " + y +
            "), radius=" + radius + ", color=" + color
        );
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

// Concrete Prototype: Rectangle
class Rectangle implements Shape {
    private int x;
    private int y;
    private int width;
    private int height;
    private String color;

    public Rectangle(int x, int y, int width,
                     int height, String color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public Shape clone() {
        return new Rectangle(x, y, width, height, color);
    }

    @Override
    public void draw() {
        System.out.println(
            "Rectangle: position=(" + x + ", " + y +
            "), width=" + width + ", height=" + height +
            ", color=" + color
        );
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

// Main class
public class Main {
    public static void main(String[] args) {

        // Create original shapes
        Circle originalCircle =
            new Circle(10, 20, 15, "Red");

        Rectangle originalRectangle =
            new Rectangle(30, 40, 100, 50, "Blue");

        System.out.println("Original Shapes:");
        originalCircle.draw();
        originalRectangle.draw();

        // Clone the existing shapes
        Circle clonedCircle =
            (Circle) originalCircle.clone();

        Rectangle clonedRectangle =
            (Rectangle) originalRectangle.clone();

        // Move the cloned shapes
        clonedCircle.setPosition(50, 60);
        clonedRectangle.setPosition(80, 90);

        System.out.println("\nCloned Shapes:");
        clonedCircle.draw();
        clonedRectangle.draw();

        // Check whether they are separate objects
        System.out.println(
            "\nAre the circles the same object? " +
            (originalCircle == clonedCircle)
        );
    }
}
