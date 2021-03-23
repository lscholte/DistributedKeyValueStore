package utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A logger that writes messages to a given location.
 * All log messages include the timestamp (in ISO 8601 format) at which the message was generated.
 * @author lscholte
 *
 */
public final class Logger {
  
  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
  private static final Appendable APPENDABLE = System.out;
  
  private Logger() {}
  
  /**
   * Logs an info message.
   * @param message the message to log
   */
  public static void logInfo(String message) {
    log("INFO", message);
  }
  
  /**
   * Logs an error message.
   * @param message the message to log
   */
  public static void logError(String message) {
    log("ERROR", message);
  }
  
  private static void log(String level, String message) {
    String formattedMessage = 
        String.format(
            "%s : %016X : %s : %s",
            DATE_FORMAT.format(new Date()),
            Thread.currentThread().getId(),
            level,
            message)
        + System.lineSeparator();
    
    try {
      APPENDABLE.append(formattedMessage);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

}
