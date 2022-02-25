import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SMSLogs {
    final private static Logger logger = Logger.getLogger(SMSLogs.class.getName());

    public void showResult(String message){
        logger.log(Level.INFO, "LOG: " + message);
    }
}
