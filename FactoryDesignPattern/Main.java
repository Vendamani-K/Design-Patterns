package FactoryDesignPattern;

interface Vehicle {
    String getType();
    int getNumberOfWheels();
    String getCapacity();
}

class Car implements Vehicle {
    public String getType() {
        return "Car";
    }

    public int getNumberOfWheels() {
        return 4;
    }

    public String getCapacity() {
        return "5 passengers";
    }
}

class Bike implements Vehicle {
    public String getType() {
        return "Bike";
    }

    public int getNumberOfWheels() {
        return 2;
    }

    public String getCapacity() {
        return "2 people";
    }
}

class Truck implements Vehicle {
    public String getType() {
        return "Truck";
    }

    public int getNumberOfWheels() {
        return 6;
    }

    public String getCapacity() {
        return "10 tons of cargo";
    }
}

class VehicleFactory {
    public static Vehicle createVehicle(String type) {
        if (type.equalsIgnoreCase("car")) {
            return new Car();
        } else if (type.equalsIgnoreCase("bike")) {
            return new Bike();
        } else if (type.equalsIgnoreCase("truck")) {
            return new Truck();
        } else {
            throw new IllegalArgumentException(
                "Invalid vehicle type: " + type
            );
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle car = VehicleFactory.createVehicle("car");
        Vehicle bike = VehicleFactory.createVehicle("bike");
        Vehicle truck = VehicleFactory.createVehicle("truck");

        System.out.println("Vehicle: " + car.getType());
        System.out.println("Wheels: " + car.getNumberOfWheels());
        System.out.println("Capacity: " + car.getCapacity());

        System.out.println();

        System.out.println("Vehicle: " + bike.getType());
        System.out.println("Wheels: " + bike.getNumberOfWheels());
        System.out.println("Capacity: " + bike.getCapacity());

        System.out.println();

        System.out.println("Vehicle: " + truck.getType());
        System.out.println("Wheels: " + truck.getNumberOfWheels());
        System.out.println("Capacity: " + truck.getCapacity());
    }
}
