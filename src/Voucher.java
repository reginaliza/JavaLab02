/*
Insert a Promo in the Database called “Piso Pizza”
It will run from Feb 01 2021 10:00 am - June 30, 2021 23:59
User needs to send an SMS to the short code 1234555
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public enum Voucher {

    PROMO("PROMO", "Piso Pizza Promo", "1234555", LocalDateTime.of(2021,02,01,10,00, 00),
            LocalDateTime.of(2021,06,30,23,59, 00)),

    FREESF("FREESF","Free Shipping Voucher","98765",LocalDateTime.of(2021,02,14,02,00, 00),
            LocalDateTime.of(2021,02,14,07,59, 00)),

    B1T1("B1T1","Buy 1 Take 1 Pizza","87000",LocalDateTime.of(2021,02,01,10,00, 00),
            LocalDateTime.of(2021,02,01,23,59, 00)
    );

    private String promoCode;
    private String details;
    private String shortCode;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    Voucher(String promoCode, String details, String shortCode, LocalDateTime startDate, LocalDateTime endDate) {
        this.promoCode = promoCode;
        this.details = details;
        this.shortCode = shortCode;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getFormattedStartDate(){
        return this.startDate.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getFormattedEndDate(){
        return this.endDate.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


}
