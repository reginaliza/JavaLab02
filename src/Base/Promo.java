/* INSTRUCTIONS:
Create a class Promo with the following fields.
Promo Code
Details
Short Code
Start Date
End Date
 */

package Base;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Promo {
    protected static String promoCode = " ";
    protected static String details = " ";
    protected static String shortCode = " ";
    protected static LocalDateTime startDate;
    protected static LocalDateTime endDate;

// ------- CONSTRUCTOR -------

    public Promo(String promoCode, String details, String shortCode, LocalDateTime startDate, LocalDateTime endDate) {
        this.promoCode = promoCode;
        this.details = details;
        this.shortCode = shortCode;
        this.startDate = startDate;
        this.endDate = endDate;
    }
// ------ END OF CONSTRUCTORS

// ------- GETTERS AND SETTERS -------

    public static String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public static String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public static String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public static LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public static LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
// ------- END OF GETTERS AND SETTERS --------------
public String getFormattedStartDate(){
    return this.startDate.format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
}

    public String getFormattedEndDate(){
        return this.endDate.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


    @Override
    public String toString() {
        return "Promo{" +
                "promoCode='" + promoCode + '\'' +
                ", details='" + details + '\'' +
                ", shortCode='" + shortCode + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
