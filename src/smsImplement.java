import java.time.LocalDateTime;

public class smsImplement implements smsManager {

    @Override
    public boolean insertSMS() {
        return false;
    }

    @Override
    public String retrieveByDate(LocalDateTime dateTime) {
        return null;
    }

    @Override
    public String retrieveByPromoCode() {
        return null;
    }

    @Override
    public String retrieveBymsisdn(String msisdn) {
        return null;
    }

    @Override
    public String retrieveBySystemSent() {
        return null;
    }

    @Override
    public String retrieveBySystemRecieve() {
        return null;
    }

    @Override
    public String retriveByManymsisdn(String[] msisdn) {
        return null;
    }
}
