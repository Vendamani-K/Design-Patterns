
class Logger {

    // Only one Logger object will be created
    private static final Logger instance = new Logger();

    // Private constructor
    private Logger() {
    }

    // Returns the same Logger object every time
    public static Logger getInstance() {
        return instance;
    }

    public void info(String message) {
        System.out.println("[INFO] " + message);
    }

    public void warn(String message) {
        System.out.println("[WARN] " + message);
    }

    public void error(String message) {
        System.out.println("[ERROR] " + message);
    }

    public void debug(String message) {
        System.out.println("[DEBUG] " + message);
    }
}

public class Main {

    public static void main(String[] args) {

        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.info("Application started.");
        logger1.warn("Low disk space.");
        logger1.error("An error occurred.");
        logger1.debug("Checking application status.");

        System.out.println(
            "Are both loggers the same? " + (logger1 == logger2)
        );
    }
}
