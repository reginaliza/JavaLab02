/*
-Insert SMS
-Retrieve SMS given a start date and end date
-Retrieve SMS given a promo code
-Retrieve SMS given an msisdn
-Retrieve SMS sent by the system
-Retrieve SMS receive by the system
-Retrieve SMS given several msisdn

 */

import java.time.LocalDateTime;

public interface smsManager {

    public boolean insertSMS();

    public String retrieveByDate(LocalDateTime dateTime);

    public String retrieveByPromoCode();

    public String retrieveBymsisdn(String msisdn);

    public String retrieveBySystemSent();

    public String retrieveBySystemRecieve();

    public String retriveByManymsisdn(String[] msisdn);

}
