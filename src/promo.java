
import java.util.Date;

public class promo {

    protected String promoCode;
    protected String details;
    protected String shortCode;
    protected Date startDate;
    protected Date endDate;

    public promo(String promoCode, String details, String shortCode, Date startDate, Date endDate) {
        this.promoCode = promoCode;
        this.details = details;
        this.shortCode = shortCode;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public String getDetails() {
        return details;
    }

    public String getShortCode() {
        return shortCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
