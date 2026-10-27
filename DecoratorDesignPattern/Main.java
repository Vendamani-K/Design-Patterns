package DecoratorDesignPattern;

    
interface Coffee {
    double getCost();
    String getDescription();
}

// Concrete component
class SimpleCoffee implements Coffee {
    @Override
    public double getCost() {
        return 50.0;
    }

    @Override
    public String getDescription() {
        return "Simple Coffee";
    }
}

// Base decorator
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
}

// Milk decorator
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 10.0;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }
}

// Sugar decorator
class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 5.0;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }
}

// Caramel decorator
class CaramelDecorator extends CoffeeDecorator {
    public CaramelDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 15.0;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Caramel";
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();

        System.out.println(coffee.getDescription()
                + " = Rs. " + coffee.getCost());

        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);
        coffee = new CaramelDecorator(coffee);

        System.out.println(coffee.getDescription()
                + " = Rs. " + coffee.getCost());
    }
}

