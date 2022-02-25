import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;

public class Main {

    public static void main(String[] args) {

        createPromos();
        scenarios();

        DatabaseConnect.connect();
        DatabaseConnect.retrieveData();
    }

    private static void scenarios() {
        //DATA POPULATION -----------------------

        // INPUT 1: PromoCode: PROMO; Should be SUCCESS_SMS
        SMSSender.getInstance()
                .sendSMS(new SMS(
                        "09202819125",
                        "System",
                        "Regina Diloy",
                        "1234555",
                        null,
                        "PROMO",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                00,
                                00))
                );

        //Users send “REGISTER” to the shortcode to be able to avail of the promo.
        SMSSender.getInstance()
                .sendSMS(new SMS(
                        "09202819125",
                        "System",
                        "Regina Diloy",
                        "1234555",
                        null,
                        "REGISTER",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                00,
                                00))
                );
// The system will reply To complete the promo registration, please send Lastname, Firstname to shortCode
        SMSSender.getInstance()
                .sendSMS(new SMS(
                        "09202819125",
                        "System",
                        "Regina Diloy",
                        "1234555",
                        null,
                        "Diloy, Regina",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                00,
                                00))
                );
        // END OF INPUT 1

        // INPUT 2: PromoCode: FREESF; Should be SUCCESS_SMS
        SMSSender.getInstance()
                .sendSMS(new SMS(
                        "09171234567",
                        "System",
                        "Regina Diloy",
                        "98765",
                        null,
                        "FREESF",
                        LocalDateTime.of(
                                2021,
                                02,
                                14,
                                05,
                                30,
                                00))
                );

        //Users send “REGISTER” to the shortcode to be able to avail of the promo.
        SMSSender.getInstance()
                .sendSMS(new SMS(
                        "09171234567",
                        "System",
                        "Regina Diloy",
                        "98765",
                        null,
                        "REGISTER",
                        LocalDateTime.of(
                                2021,
                                02,
                                14,
                                05,
                                30,
                                00))
                );
// The system will reply To complete the promo registration, please send Lastname, Firstname to shortCode
        SMSSender.getInstance()
                .sendSMS(new SMS(
                        "09171234567",
                        "System",
                        "Regina Diloy",
                        "98765",
                        null,
                        "Diloy, Regina",
                        LocalDateTime.of(
                                2021,
                                02,
                                14,
                                05,
                                30,
                                00))
                );

        // INPUT 3: PromoCode: B1T1; Should be SUCCESS_SMS
        SMSSender.getInstance()
                .sendSMS(new SMS(
                        "09459876543",
                        "System",
                        "Regina Diloy",
                        "87000",
                        null,
                        "B1T1",
                        LocalDateTime.of(
                                2021,
                                02,
                                01,
                                11,
                                00,
                                00))
                );

        //Users send “REGISTER” to the shortcode to be able to avail of the promo.
        SMSSender.getInstance()
                .sendSMS(new SMS(
                        "09459876543",
                        "System",
                        "Regina Diloy",
                        "87000",
                        null,
                        "REGISTER",
                        LocalDateTime.of(
                                2021,
                                02,
                                01,
                                11,
                                00,
                                00))
                );

// The system will reply To complete the promo registration, please send Lastname, Firstname to shortCode
        SMSSender.getInstance()
                .sendSMS(new SMS(
                        "09171234567",
                        "System",
                        "Regina Diloy",
                        "87000",
                        null,
                        "Diloy, Regina",
                        LocalDateTime.of(
                                2021,
                                02,
                                01,
                                11,
                                00,
                                00))
                );
    }

    /*
    Insert a Promo in the Database called “Piso Pizza”
    It will run from Feb 01 2021 10:00 am - June 30, 2021 23:59
    User needs to send an SMS to the short code 1234555
     */
    private static void createPromos() {
        //CREATE PROMOS -----------------------
        Promo promo = new Promo(
                "PROMO",
                "Piso Pizza Promo",
                "1234555",
                LocalDateTime.of(
                        2021,
                        02,
                        01,
                        10,
                        00,
                        00),
                LocalDateTime.of(
                        2021,
                        06,
                        30,
                        23,
                        59,
                        00)
        );
        SMSSender.getInstance().createPromo(promo);


        // ADD 2 OTHER PROMOS: FREESF and B1T1

        Promo promo2 = new Promo(
                "FREESF",
                "Free Shipping Voucher",
                "98765",
                LocalDateTime.of(
                        2022,
                        02,
                        14,
                        02,
                        00,
                        00),
                LocalDateTime.of(
                        2022,
                        02,
                        14,
                        07,
                        59,
                        00)
        );
        SMSSender.getInstance().createPromo(promo2);

        Promo promo3 = new Promo(
                "B1T1",
                "Buy 1 Take 1 Pizza",
                "87000",
                LocalDateTime.of(
                        2021,
                        02,
                        01,
                        10,
                        00,
                        00),
                LocalDateTime.of(
                        2021,
                        02,
                        01,
                        23,
                        59,
                        00)
        );
        SMSSender.getInstance().createPromo(promo3);
    }
}
