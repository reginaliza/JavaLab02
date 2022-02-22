/*
Instructions:
Create a class sms with the following fields.
MSISDN
RECIPIENT
SENDER
SHORT CODE
TRANSACTION ID (contains number and characters)
TIMESTAMP
Note: TRANSACTION ID is auto generate by another system and is not generated.
*/

package Base;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SMS {
    protected String transactionID = " ";
    protected String promoCode = " ";
    protected String register = " ";
    protected String msisdn = " ";
    protected String firstName = " ";
    protected String lastName = " ";
    protected String shortCode = " ";
    protected LocalDateTime timestamp;

// ----------- CONSTRUCTORS --------------

    public SMS(String transactionID, String promoCode, String register, String msisdn, String firstName, String lastName, String shortCode, LocalDateTime timestamp) {
        this.transactionID = transactionID;
        this.promoCode = promoCode;
        this.register = register;
        this.msisdn = msisdn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.shortCode = shortCode;
        this.timestamp = timestamp;
    }

// --------- END OF CONSTRUCTORS -------------

// ---------- GETTERS AND SETTERS --------------------
    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // ------- END OF GETTERS AND SETTERS -------------
public String getFormattedTimestamp(){
    return this.timestamp.format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
}

    @Override
    public String toString() {
        return "SMS{" +
                "transactionID='" + transactionID + '\'' +
                ", promoCode='" + promoCode + '\'' +
                ", register='" + register + '\'' +
                ", msisdn='" + msisdn + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", shortCode='" + shortCode + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}


