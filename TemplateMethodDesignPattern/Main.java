package TemplateMethodDesignPattern;


 // 1. ABSTRACT BASE CLASS
abstract class DataProcessor {

    // Template Method
    // Defines the sequence of steps
    public final void processFile() {

        System.out.println("\nStarting file processing...");

        readFile();
        parseData();
        processData();
        saveResults();

        System.out.println("File processing completed!");
    }

    // Steps that subclasses must implement
    protected abstract void readFile();

    protected abstract void parseData();

    // Common processing step
    protected void processData() {
        System.out.println("Processing the data...");
    }

    protected abstract void saveResults();
}

// 2. CSV DATA PROCESSOR
class CSVDataProcessor extends DataProcessor {

    @Override
    protected void readFile() {
        System.out.println("Reading CSV file...");
    }

    @Override
    protected void parseData() {
        System.out.println("Parsing CSV data...");
    }

    @Override
    protected void saveResults() {
        System.out.println("Saving CSV results...");
    }
}

// 3. JSON DATA PROCESSOR
class JSONDataProcessor extends DataProcessor {

    @Override
    protected void readFile() {
        System.out.println("Reading JSON file...");
    }

    @Override
    protected void parseData() {
        System.out.println("Parsing JSON data...");
    }

    @Override
    protected void saveResults() {
        System.out.println("Saving JSON results...");
    }
}

// 4. MAIN CLASS
public class Main {

    public static void main(String[] args) {

        // Create CSV processor
        DataProcessor csvProcessor = new CSVDataProcessor();

        System.out.println("===== CSV PROCESSING =====");
        csvProcessor.processFile();

        // Create JSON processor
        DataProcessor jsonProcessor = new JSONDataProcessor();

        System.out.println("\n===== JSON PROCESSING =====");
        jsonProcessor.processFile();
    }
}
