import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;

public class Main {

    public static void main(String[] args) {
        DatabaseConnect.connect();
        //createPromos();
        //populateData();
        DatabaseConnect.retrieveData();
    }

    private static void populateData() {
        //DATA POPULATION -----------------------

        SMSSender.getInstance()
                .sendSMS(new SMS(
                        "09202819125",
                        "Pizza Hut",
                        "Regina Diloy",
                        "1234555",
                        null,
                        "PROMO",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSSender.getInstance()
                .sendSMS(new SMS(
                        "09202819125",
                        "Pizza Hut",
                        "Regina Diloy",
                        "1234555",
                        null,
                        "REGISTER",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSSender.getInstance()
                .sendSMS(new SMS(
                        "09202819125",
                        "Pizza Hut",
                        "Regina Diloy",
                        "1234555",
                        null,
                        "Diloy, Regina",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );
    }

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
                        0),
                LocalDateTime.of(
                        2021,
                        06,
                        30,
                        23,
                        59)
        );
        SMSSender.getInstance().createPromo(promo);
    }



}
