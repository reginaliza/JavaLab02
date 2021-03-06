/*
Create a class sms with the following fields.
MSISDN
RECIPIENT
SENDER
SHORT CODE
TRANSACTION ID (contains number and characters)
TIMESTAMP
Note: TRANSACTION ID is auto generate by another system and is not generated.
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

// Contains: Variables, Constructors for User SMS and System SMS, Getters & Setters, toString (for output)
// Map. Scenario: msisdn should be 11
        // user should send "PROMO"
        // then, user should send "REGISTER" to avail the promo. This time, system will check if they received "REGISTER"
        //System will send "To complete the promo registration, please send Lastname, Firstname to" + promoCode
        //Finally, User will send <lastname>, <firstname>
        //SUCCESS_SMS :)

public class SMS {
    protected String msisdn;
    protected String recipient;
    protected String sender;
    protected String shortCode;
    protected String transactionID;
    protected String register;
    protected LocalDateTime timestamp;
    protected Status status;
    protected Voucher voucherCode;

    // USER
    public SMS(String msisdn, String recipient, String sender, String shortCode, String transactionID, String register, LocalDateTime timestamp) {
        this.msisdn = msisdn;
        this.recipient = recipient;
        this.sender = sender;
        this.shortCode = shortCode;
        this.transactionID = transactionID;
        this.register = register;
        this.timestamp = timestamp;
    }

    // SYSTEM
    public SMS(String msisdn, String recipient, String sender, String shortCode, String transactionID, String register, LocalDateTime timestamp, Status status, Voucher voucherCode) {
        this.msisdn = msisdn;
        this.recipient = recipient;
        this.sender = sender;
        this.shortCode = shortCode;
        this.transactionID = transactionID;
        this.register = register;
        this.timestamp = timestamp;
        this.status = status;
        this.voucherCode = voucherCode;
    }


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

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // I ADDED A TIMESTAMP FORMATTER
    public String getFormattedTimestamp(){
        return this.timestamp.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Voucher getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(Voucher voucherCode) {
        this.voucherCode = voucherCode;
    }

    /*
    Create a function that will accept a map with exactly 3 items.
    The first item is the mobile number.
    The second item is the sms
    The third is the short code that will send the sms
     */
    public Map<String, String> getSmsMap() {
        Map<String, String> smsMap = new HashMap<>();
        smsMap.put("msisdn", this.getMsisdn());
        smsMap.put("message", this.getRegister());
        smsMap.put("shortCode", this.getShortCode());
        return smsMap;
    }


    public void checkSMS(Map<String, String> smsMap){
        this.voucherCode = getVoucherCode(smsMap.get("message"), smsMap.get("shortCode"));

        if (checkMsisdn(smsMap.get("msisdn"))
                && checkMessage(smsMap.get("message"), this.voucherCode)
                && checkDate(this.timestamp, this.voucherCode)){
            this.status = Status.SUCCESS_SMS;

        } else {
            this.status = Status.FAILED_SMS;
        }
    }

    private boolean checkMsisdn(String msisdn){
        return msisdn.length() == 11;
        // FORMAT OF MSISDN = 0XXXXXXXXXX not +639
    }

    private boolean checkMessage(String message, Voucher voucherCode){
        return voucherCode.getPromoCode().equals(message)
                || message.equals("REGISTER")
                || message.contains(", ");
    }

    private boolean checkDate(LocalDateTime timestamp, Voucher voucher){
        return voucher.getStartDate().isBefore(timestamp)
                && voucher.getEndDate().isAfter(timestamp);
    }

    private Voucher getVoucherCode(String message, String shortCode){
        Voucher promo = null;

        for (Voucher voucher : Voucher.values()){
            if (voucher.getPromoCode().equals(message)
                    || voucher.getShortCode().equals(shortCode)){
                promo = voucher;
            }
        }

        return promo;
    }

    @Override
    public String toString() {
        return
                "\n msisdn: " + msisdn +
                "\n recipient: " + recipient +
                "\n sender: " + sender +
                "\n shortCode: " + shortCode +
                "\n transactionID: " + transactionID +
                "\n register: " + register +
                "\n timestamp: " + timestamp +
                "\n status: " + status +
                "\n voucherCode: " + voucherCode.getPromoCode() +
                " \n==================== ";
    }
}
