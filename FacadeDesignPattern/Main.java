package FacadeDesignPattern;

    
class Lighting {
    public void turnOn() {
        System.out.println("Lights are ON");
    }

    public void turnOff() {
        System.out.println("Lights are OFF");
    }
}

class Heating {
    public void setTemperature(int temperature) {
        System.out.println("Temperature set to " + temperature + " degrees");
    }

    public void turnOff() {
        System.out.println("Heating is OFF");
    }
}

class Security {
    public void activateAlarm() {
        System.out.println("Security alarm activated");
    }

    public void deactivateAlarm() {
        System.out.println("Security alarm deactivated");
    }
}

// Facade class
class HomeAutomationFacade {
    private Lighting lighting;
    private Heating heating;
    private Security security;

    public HomeAutomationFacade() {
        lighting = new Lighting();
        heating = new Heating();
        security = new Security();
    }

    public void arriveHome() {
        System.out.println("--- Welcome Home ---");
        lighting.turnOn();
        heating.setTemperature(24);
        security.deactivateAlarm();
    }

    public void leaveHome() {
        System.out.println("--- Leaving Home ---");
        lighting.turnOff();
        heating.turnOff();
        security.activateAlarm();
    }
}

public class Main {
    public static void main(String[] args) {
        HomeAutomationFacade home =
                new HomeAutomationFacade();

        home.arriveHome();

        System.out.println();

        home.leaveHome();
    }
}

