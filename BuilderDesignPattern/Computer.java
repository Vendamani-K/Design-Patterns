package BuilderDesignPattern;


public class Computer {

    
    private String CPU;
    private String RAM;
    private String Storage;
    private String graphicsCard;
    private String operatingSystem;

    
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.Storage = builder.Storage;
        this.graphicsCard = builder.graphicsCard;
        this.operatingSystem = builder.operatingSystem;
    }

    
    public static class Builder {

        private String CPU;
        private String RAM;
        private String Storage;
        private String graphicsCard;
        private String operatingSystem;

        
        public Builder setCPU(String CPU) {
            this.CPU = CPU;
            return this;
        }

        
        public Builder setRAM(String RAM) {
            this.RAM = RAM;
            return this;
        }

       
        public Builder setStorage(String Storage) {
            this.Storage = Storage;
            return this;
        }

        
        public Builder setGraphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        
        public Builder setOperatingSystem(String operatingSystem) {
            this.operatingSystem = operatingSystem;
            return this;
        }

       
        public Computer build() {
            return new Computer(this);
        }
    }

    
    public void display() {
        System.out.println("CPU: " + CPU);
        System.out.println("RAM: " + RAM);
        System.out.println("Storage: " + Storage);
        System.out.println("Graphics Card: " + graphicsCard);
        System.out.println("Operating System: " + operatingSystem);
    }
}
