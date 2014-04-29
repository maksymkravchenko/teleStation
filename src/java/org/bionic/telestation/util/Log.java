package org.bionic.telestation.util;

/**
 *
 * @author MAX
 */
import java.io.IOException;
import java.util.logging.Level;
import org.apache.log4j.FileAppender;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.Logger;

public class Log {

    private static Log instance;
    public Logger logger; 

    private Log() {
        FileAppender fileappender;
        try {
            logger = Logger.getLogger("Log");
            fileappender = new FileAppender(new PatternLayout(), "logger.txt");
            logger.addAppender(fileappender);
            logger.info("Start logging");
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }
}
