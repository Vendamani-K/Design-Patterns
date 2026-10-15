interface Button {
    void render();
}

interface TextField {
    void render();
}

class DarkButton implements Button {
    public void render() {
        System.out.println("Displaying a dark button.");
    }
}

class LightButton implements Button {
    public void render() {
        System.out.println("Displaying a light button.");
    }
}

class DarkTextField implements TextField {
    public void render() {
        System.out.println("Displaying a dark text field.");
    }
}

class LightTextField implements TextField {
    public void render() {
        System.out.println("Displaying a light text field.");
    }
}

interface GUIFactory {
    Button createButton();
    TextField createTextField();
}

class DarkThemeFactory implements GUIFactory {
    public Button createButton() {
        return new DarkButton();
    }

    public TextField createTextField() {
        return new DarkTextField();
    }
}

class LightThemeFactory implements GUIFactory {
    public Button createButton() {
        return new LightButton();
    }

    public TextField createTextField() {
        return new LightTextField();
    }
}

public class Main {
    public static void main(String[] args) {
        String theme = "dark";

        GUIFactory factory;

        if (theme.equalsIgnoreCase("dark")) {
            factory = new DarkThemeFactory();
        } else {
            factory = new LightThemeFactory();
        }

        Button button = factory.createButton();
        TextField textField = factory.createTextField();

        System.out.println("Selected theme: " + theme);
        button.render();
        textField.render();
    }
}
