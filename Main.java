
class Logger {
    private static final Logger instance = new Logger();

    private Logger() {
    }

    public static Logger getInstance() {
        return instance;
    }

    public void info(String message) {
        System.out.println("[INFO] " + message);
    }

    public void warn(String message) {
        System.out.println("[WARNING] " + message);
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
        Logger logger = Logger.getInstance();

        logger.info("Application started");
        logger.debug("Checking application configuration");
        logger.warn("Memory usage is high");
        logger.error("Unable to connect to the database");

        Logger logger2 = Logger.getInstance();

        System.out.println("Same instance: " + (logger == logger2));
    }
}
