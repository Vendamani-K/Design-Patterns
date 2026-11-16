package ObserverDesignPattern;


import java.util.ArrayList;
import java.util.List;

// 1. OBSERVER INTERFACE
interface Observer {
    void update(float temperature, float humidity, float pressure);
}

// 2. SUBJECT INTERFACE
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// 3. WEATHER STATION - CONCRETE SUBJECT
class WeatherStation implements Subject {

    private List<Observer> observers = new ArrayList<>();

    private float temperature;
    private float humidity;
    private float pressure;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
        System.out.println("Display registered.");
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
        System.out.println("Display removed.");
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    // Called whenever new weather data is received
    public void setMeasurements(float temperature,
                                float humidity,
                                float pressure) {

        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;

        System.out.println("\nWeather conditions changed!");
        notifyObservers();
    }
}

// 4. CURRENT CONDITIONS DISPLAY
class CurrentConditionsDisplay implements Observer {

    @Override
    public void update(float temperature, float humidity, float pressure) {
        System.out.println("\nCurrent Conditions Display");
        System.out.println("Temperature: " + temperature + " °C");
        System.out.println("Humidity: " + humidity + " %");
        System.out.println("Pressure: " + pressure + " hPa");
    }
}

// 5. STATISTICS DISPLAY
class StatisticsDisplay implements Observer {

    private float maxTemperature = Float.MIN_VALUE;
    private float minTemperature = Float.MAX_VALUE;
    private float totalTemperature = 0;
    private int readings = 0;

    @Override
    public void update(float temperature, float humidity, float pressure) {

        totalTemperature += temperature;
        readings++;

        if (temperature > maxTemperature) {
            maxTemperature = temperature;
        }

        if (temperature < minTemperature) {
            minTemperature = temperature;
        }

        System.out.println("\nStatistics Display");
        System.out.println("Maximum Temperature: " + maxTemperature + " °C");
        System.out.println("Minimum Temperature: " + minTemperature + " °C");
        System.out.println("Average Temperature: " + (totalTemperature / readings) + " °C");
    }
}

// 6. FORECAST DISPLAY
class ForecastDisplay implements Observer {

    private float lastPressure = 0;

    @Override
    public void update(float temperature, float humidity, float pressure) {

        System.out.println("\nForecast Display");

        if (lastPressure == 0) {
            System.out.println("Forecast: Collecting weather data.");
        } else if (pressure > lastPressure) {
            System.out.println("Forecast: Improving weather.");
        } else if (pressure < lastPressure) {
            System.out.println("Forecast: Rainy weather expected.");
        } else {
            System.out.println("Forecast: Weather conditions unchanged.");
        }

        lastPressure = pressure;
    }
}

// 7. MAIN CLASS
public class Main {

    public static void main(String[] args) {

        // Create Weather Station
        WeatherStation weatherStation = new WeatherStation();

        // Create display units
        CurrentConditionsDisplay currentDisplay =
                new CurrentConditionsDisplay();

        StatisticsDisplay statisticsDisplay =
                new StatisticsDisplay();

        ForecastDisplay forecastDisplay =
                new ForecastDisplay();

        // Register all displays
        weatherStation.registerObserver(currentDisplay);
        weatherStation.registerObserver(statisticsDisplay);
        weatherStation.registerObserver(forecastDisplay);

        // First weather update
        System.out.println("\n========== FIRST WEATHER UPDATE ==========");
        weatherStation.setMeasurements(30.0f, 65.0f, 1012.0f);

        // Second weather update
        System.out.println("\n========== SECOND WEATHER UPDATE ==========");
        weatherStation.setMeasurements(32.0f, 70.0f, 1008.0f);

        // Remove Statistics Display
        System.out.println("\n========== REMOVING STATISTICS DISPLAY ==========");
        weatherStation.removeObserver(statisticsDisplay);

        // Third weather update
        // Statistics Display will not receive this update
        System.out.println("\n========== THIRD WEATHER UPDATE ==========");
        weatherStation.setMeasurements(28.0f, 60.0f, 1015.0f);

    }
}
