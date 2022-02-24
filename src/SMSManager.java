import java.util.ArrayList;

public interface SMSManager {

    void insertSMS(SMS sms);
    ArrayList<SMS> retrieveSMSByDate(String startDate, String endDate);
    ArrayList<SMS> retrieveSMSByMsisdn(String msisdn);
    ArrayList<SMS> retrieveSMSByMsisdnList(String[] msisdnList);
    ArrayList<SMS> retrieveSMSbyPromoCode(String promoCode);
    ArrayList<SMS> retrieveSMSfromSystem(String sender);
    ArrayList<SMS> retrieveSMSToSystem(String recipient);
}
