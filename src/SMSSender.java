import java.util.Map;
import java.util.logging.Logger;

public class SMSSender {

    private static SMSSender controllerInstance = null;
    private static SMSLogs smsLogs;
    final private static Logger logger = Logger.getLogger(SMSSender.class.getName());

    public static SMSSender getInstance(){
        if (controllerInstance == null){
            controllerInstance = new SMSSender();
            smsLogs = new SMSLogs();
        }
        return controllerInstance;
    }

    /*
    Users send “PROMO” to the shortcode to get the Pizza that will cost 1 peso
    Users send “REGISTER” to the shortcode to be able to avail of the promo.
    The system will reply "To complete the promo registration,
        please send Lastname, Firstname to 1234555"
     */

    public void sendSMS(SMS sms){
        sms.checkSMS(sms.getSmsMap());
        insertSMS(sms);

        if (sms.getRegister().equals("REGISTER")){
            receiveReplySMS(sms);
        }
    }

    private void receiveReplySMS(SMS sms) {
        SMS replySMS = new SMS(
                sms.getMsisdn(),
                sms.getSender(),
                sms.getRecipient(),
                sms.getShortCode(),
                null,
                "To complete the promo registration, please send Lastname, Firstname to " +
                        sms.getShortCode(), //shortCode is 1234555, 98765, 87000
                sms.getTimestamp(),
                sms.getStatus(),
                sms.getVoucherCode()
        );

        insertSMS(replySMS);
        displaySMS(sms);
    }


    //logs out the reply to the user
    private void displaySMS(SMS sms){
        smsLogs.showResult(sms.getRegister());
    }

    //requests the database to insert the promo
    public void createPromo(Promo promo){
        smsLogs.showResult(DatabaseConnect.getInstance().insertPromo(promo));
    }

    //requests to insert the SMS, entered by the user, into the database
    private void insertSMS(SMS sms){
        DatabaseConnect.getInstance().insertSMS(sms);
        Map<String, Object> idPromo = DatabaseConnect.getInstance().getIdPromo();
        DatabaseConnect.getInstance().generateTransactionID(idPromo);
    }
}
