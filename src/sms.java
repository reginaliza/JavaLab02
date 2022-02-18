/*
MSISDN
RECIPIENT
SENDER
SHORT CODE
TRANSACTION ID (contains number and characters)
TIMESTAMP
 */

import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class sms {

    protected String msisdn;
    protected String recipient;
    protected String sender;
    protected String shortCode;
    protected String transactionID;

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    protected Date timestamp;

// ------------------------------------------
    /*
    Create a function that will accept a map with exactly 3 items.
The first item is the mobile number.
The second item is the sms
The third is the short code that will send the sms

     */

    final static Logger logger = Logger.getLogger(sms.class.getName());

    public static void main(String[] args){
            // Create a HashMap object called smsChecker
            HashMap<String, String> smsChecker = new HashMap<String, String>();

            // Add keys and values
            smsChecker.put("\nMobile Number", "09202819125");
            smsChecker.put("\nMessage", "Promo Code Accepted");
            smsChecker.put("\nShortcode", "1234");
            logger.log(Level.INFO, "SMS: " + smsChecker);


        }
    }
