import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SMSLogs {

    final private static Logger logger = Logger.getLogger(SMSLogs.class.getName());

    public void showResult(String message){
        logger.info("LOG: " + message);
    }

    public void showResult(String message, Logger logger){
        logger.info("LOG: " + message);
    }

    public void showSMSResult(ArrayList<SMS> smsList, Logger logger){

        logger.log(Level.INFO, "------------");
        for (SMS sms : smsList){
            logger.info("LOG: " + sms.toString());
        }
        logger.log(Level.INFO, "------------");

    }

    public void showPersonsResult(ArrayList<String> persons, Logger logger){
        logger.log(Level.INFO, "------------");
        for (String person : persons){
            logger.info("VIEW: " + person);
        }
        logger.log(Level.INFO, "------------");
    }
}
