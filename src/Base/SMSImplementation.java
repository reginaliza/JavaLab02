/* INSTRUCTIONS:
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SMSImplementation implements SMSManager {

    @Override
    public void sendSMS(SMS sms) {

    }

    @Override
    public ArrayList retrieveSMS() {
        return null;
    }

    @Override
    public ArrayList retrieveByStartEndDate(LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }

    @Override
    public ArrayList retrieveByPromoCode(String promoCode) {
        return null;
    }

    @Override
    public ArrayList retrieveBymsisdn(String msisdn) {
        return null;
    }
}
