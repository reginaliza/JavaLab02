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

public interface SMSManager {

    void insertSMS(SMS sms);

}
