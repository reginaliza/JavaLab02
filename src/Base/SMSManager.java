/* INSTRUCTIONS:
Create an interface for managing SMS
These are the required functions :
-Insert SMS
-Retrieve SMS given a start date and end date
-Retrieve SMS given a promo code
-Retrieve SMS given an msisdn
-Retrieve SMS sent by the system
-Retrieve SMS receive by the system
-Retrieve SMS given several msisdn

*/

package Base;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface SMSManager {
    void sendSMS(SMS sms);
    ArrayList retrieveSMS();
    ArrayList retrieveByStartEndDate(LocalDateTime startDate, LocalDateTime endDate);
    ArrayList retrieveByPromoCode(String promoCode);
    ArrayList retrieveBymsisdn(String msisdn);
}
