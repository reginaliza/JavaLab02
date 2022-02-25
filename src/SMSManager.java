/*
Create an interface for managing SMS then implement these in a class
These are the required functions:
-Insert SMS
-Retrieve SMS given a start date and end date
-Retrieve SMS given a promo code
-Retrieve SMS given an msisdn
-Retrieve SMS sent by the system
-Retrieve SMS receive by the system
-Retrieve SMS given several msisdn
 */

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
