import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public enum Voucher {

    PROMO("PROMO", "Piso Pizza Promo", "1234555", LocalDateTime.of(2021,01,10,00,00),
            LocalDateTime.of(2021,06,30,23,59)),

    FREESF("FREESF","Free Shipping Voucher","12345",LocalDateTime.of(2022,02,14,20,0),
            LocalDateTime.of(2022,02,14,23,59)),

    B1T1("B1T1","Buy 1 Take 1 Pizza","87000",LocalDateTime.of(2021,02,01,10,0),
            LocalDateTime.of(2021,06,30,23,59)
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
