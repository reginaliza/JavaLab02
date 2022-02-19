
import java.time.LocalDateTime;

public class sms {

    protected String msisdn;
    protected String recipient;
    protected String sender;
    protected String shortCode;
    protected String transactionID;
    protected LocalDateTime timestamp;



    public sms(String msisdn, String recipient, String sender, String shortCode, String transactionID) {
        this.msisdn = msisdn;
        this.recipient = recipient;
        this.sender = sender;
        this.shortCode = shortCode;
        this.transactionID = transactionID;
        this.timestamp = LocalDateTime.now();
    }

    public String getMsisdn() {
        return msisdn;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSender() {
        return sender;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
