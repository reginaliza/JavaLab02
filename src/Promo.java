import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Promo {

    private String promoCode;
    private String details;
    private String shortCode;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Promo(String promoCode, String details, String shortCode, LocalDateTime startDate, LocalDateTime endDate) {
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
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(this.startDate);
    }

    public String getFormattedEndDate(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(this.endDate);
    }

    @Override
    public String toString() {
        return "Promo" +
                "\npromoCode='" + promoCode + '\'' +
                "\n details='" + details + '\'' +
                "\n shortCode='" + shortCode + '\'' +
                "\n startDate=" + startDate +
                "\n endDate=" + endDate +
                '}';
    }
}
