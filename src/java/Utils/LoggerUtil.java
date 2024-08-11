package Utils;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerUtil {
    private static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());

    static {
        // Create a console handler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL); // Set the level of logging
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.ALL); // Set the level of logging for the logger
    }

    public static Logger getLogger() {
        return logger;
    }
}
