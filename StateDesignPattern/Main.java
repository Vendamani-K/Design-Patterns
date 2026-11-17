package StateDesignPattern;


// 1. STATE INTERFACE
interface TrafficLightState {
    void changeSignal(TrafficLight trafficLight);
}

// 2. GREEN STATE
class GreenState implements TrafficLightState {

    @Override
    public void changeSignal(TrafficLight trafficLight) {
        System.out.println("GREEN: Vehicles can GO.");
        System.out.println("Changing signal from GREEN to YELLOW.");

        trafficLight.setState(new YellowState());
    }
}

// 3. YELLOW STATE
class YellowState implements TrafficLightState {

    @Override
    public void changeSignal(TrafficLight trafficLight) {
        System.out.println("YELLOW: Vehicles should SLOW DOWN.");
        System.out.println("Changing signal from YELLOW to RED.");

        trafficLight.setState(new RedState());
    }
}

// 4. RED STATE
class RedState implements TrafficLightState {

    @Override
    public void changeSignal(TrafficLight trafficLight) {
        System.out.println("RED: Vehicles must STOP.");
        System.out.println("Changing signal from RED to GREEN.");

        trafficLight.setState(new GreenState());
    }
}

// 5. CONTEXT
class TrafficLight {

    private TrafficLightState currentState;

    // Initial state is GREEN
    public TrafficLight() {
        currentState = new GreenState();
    }

    public void setState(TrafficLightState state) {
        currentState = state;
    }

    // Delegate behavior to the current state
    public void changeSignal() {
        currentState.changeSignal(this);
    }

    public void displayCurrentState() {
        System.out.println("Current Traffic Light State: "
                + currentState.getClass().getSimpleName());
    }
}

// 6. MAIN CLASS
public class Main {

    public static void main(String[] args) {

        // Create traffic light
        TrafficLight trafficLight = new TrafficLight();

        System.out.println("===== TRAFFIC LIGHT SYSTEM =====");

        // Display initial state
        trafficLight.displayCurrentState();

        // Change signal 1
        System.out.println("\n--- Signal Change 1 ---");
        trafficLight.changeSignal();
        trafficLight.displayCurrentState();

        // Change signal 2
        System.out.println("\n--- Signal Change 2 ---");
        trafficLight.changeSignal();
        trafficLight.displayCurrentState();

        // Change signal 3
        System.out.println("\n--- Signal Change 3 ---");
        trafficLight.changeSignal();
        trafficLight.displayCurrentState();

        // Change signal 4
        System.out.println("\n--- Signal Change 4 ---");
        trafficLight.changeSignal();
        trafficLight.displayCurrentState();
    }
}
