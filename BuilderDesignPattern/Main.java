package BuilderDesignPattern;

public class Main {

    public static void main(String[] args) {

        Computer gamingComputer = new Computer.Builder()
                .setCPU("Intel i7")
                .setRAM("32GB")
                .setStorage("1TB SSD")
                .setGraphicsCard("NVIDIA RTX 4070")
                .setOperatingSystem("Windows 11")
                .build();

        System.out.println("Gaming Computer:");
        gamingComputer.display();

        System.out.println();

        Computer officeComputer = new Computer.Builder()
                .setCPU("Intel i5")
                .setRAM("8GB")
                .setStorage("512GB SSD")
                .setOperatingSystem("Ubuntu")
                .build();

        System.out.println("Office Computer:");
        officeComputer.display();
    }
}

