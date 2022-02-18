/*
Promo Code
Details
Short Code
Start Date
End Date

 */

import java.util.Date;

public class promo {
    protected String promoCode;
    protected String details;
    protected String shortCodePromo;
    protected Date startDate;
    protected Date endDate;

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

    public String getShortCodePromo() {
        return shortCodePromo;
    }

    public void setShortCodePromo(String shortCodePromo) {
        this.shortCodePromo = shortCodePromo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
