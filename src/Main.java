import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        //createPromos();

        scenarioPisoPizza();

        //dataPopulation();

        //loopDataPopulation();

// =========== FOR RETRIEVALS

        //DatabaseConnect.connect();

        //DatabaseConnect.retrieveData();

        //DatabaseConnect.generateReport();

        //DatabaseConnect.totalReceived();

        //DatabaseConnect.totalSent();

        DatabaseConnect.disconnect(); // Always Disconnect from DB
    }

    private static void scenarioPisoPizza() {
        // INPUT 1: PromoCode: PROMO; Should be SUCCESS_SMS
        SMSSender.getInstance()
                .sendRegister(new SMS(
                        "01234567890",
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
                .sendRegister(new SMS(
                        "01234567890",
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
                .sendRegister(new SMS(
                        "01234567890",
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

        // ===== END PISO PIZZA SCENARIO =====
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

    // DATA POPULATION ======
    private static void dataPopulation() {
        // INPUT 1: FAILED_SMS since msidsn is only 4
        SMSSender.getInstance()
                .sendRegister(new SMS(
                        "0920",
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

        // Data Population 2: PromoCode: FREESF; Should be SUCCESS_SMS
        SMSSender.getInstance()
                .sendRegister(new SMS(
                        "09222222222",
                        "System",
                        "Ethan Batumbakal",
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
                .sendRegister(new SMS(
                        "09222222222",
                        "System",
                        "Ethan Batumbakal",
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
                .sendRegister(new SMS(
                        "0922222222",
                        "System",
                        "Ethan Batumbakal",
                        "98765",
                        null,
                        "Batumbakal, Ethan",
                        LocalDateTime.of(
                                2021,
                                02,
                                14,
                                05,
                                30,
                                00))
                );

        // Data poulation 3: PromoCode: B1T1; Should be SUCCESS_SMS
        SMSSender.getInstance()
                .sendRegister(new SMS(
                        "09777777777",
                        "System",
                        "Jake Sim",
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
                .sendRegister(new SMS(
                        "09777777777",
                        "System",
                        "Jake Sim",
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
                .sendRegister(new SMS(
                        "09777777777",
                        "System",
                        "Jake Sim",
                        "87000",
                        null,
                        "Sim, Jake",
                        LocalDateTime.of(
                                2021,
                                02,
                                01,
                                11,
                                00,
                                00))
                );
    }

    public static void loopDataPopulation() {

        // ========== PROMO - ALL SUCESS_SMS - 10 INPUTS ==========
        for (int cnt = 0; cnt < 10; cnt++) {

            SMSSender.getInstance()
                    .sendRegister(new SMS(
                            "09202819125",
                            "System",
                            "Reg Diloy",
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
                    .sendRegister(new SMS(
                            "09202819125",
                            "System",
                            "Reg Diloy",
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
                    .sendRegister(new SMS(
                            "09202819125",
                            "System",
                            "Reg Diloy",
                            "1234555",
                            null,
                            "Diloy, Reg",
                            LocalDateTime.of(
                                    2021,
                                    03,
                                    02,
                                    11,
                                    00,
                                    00))
                    );
        }  // ========== END OF PROMO 10 DATA POPULATION LOOP ==========

        // ========== FREESF - ALL SUCESS_SMS - 10 INPUTS ==========
        for (int cnt = 0; cnt < 10; cnt++) {
            SMSSender.getInstance()
                    .sendRegister(new SMS(
                            "09171234567",
                            "System",
                            "Juan Dela Cruz",
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
                    .sendRegister(new SMS(
                            "09171234567",
                            "System",
                            "Juan Dela Cruz",
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
                    .sendRegister(new SMS(
                            "09171234567",
                            "System",
                            "Juan Dela Cruz",
                            "98765",
                            null,
                            "Dela Cruz, Juan",
                            LocalDateTime.of(
                                    2021,
                                    02,
                                    14,
                                    05,
                                    30,
                                    00))
                    );
        }  // ========== END OF FREE SF 10 LOOP DATA POPULATION ==========

        // ========== B1T1 - ALL SUCESS_SMS - 10 INPUTS ==========
        for (int cnt = 0; cnt < 10; cnt++) {
        SMSSender.getInstance()
                .sendRegister(new SMS(
                        "09459876543",
                        "System",
                        "GCash Globe",
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
                .sendRegister(new SMS(
                        "09459876543",
                        "System",
                        "GCash Globe",
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
                .sendRegister(new SMS(
                        "09171234567",
                        "System",
                        "GCash Globe",
                        "87000",
                        null,
                        "Globe, GCash",
                        LocalDateTime.of(
                                2021,
                                02,
                                01,
                                11,
                                00,
                                00))
                );
        }  // ========== END OF B1T1 LOOP 10 DATA POPULATION ==========

// ====================================================================================================================
// ============= DATA POPULATION FAILED SMS ===============
// ====================================================================================================================

// ========== PROMO - ALL FAILED_SMS - 10 INPUTS ==========
        for (int cnt = 0; cnt < 10; cnt++) {

            SMSSender.getInstance()
                    .sendRegister(new SMS(
                            "09123456789",
                            "System",
                            "GCash Apper",
                            "1234555",
                            null,
                            "PRO",
                            LocalDateTime.of(
                                    2021,
                                    03,
                                    02,
                                    11,
                                    00,
                                    00))
                    );
        }  // ========== END OF PROMO 10 DATA POPULATION LOOP ==========

        // ========== FREESF - ALL FAILED_SMS - 10 INPUTS ==========
        for (int cnt = 0; cnt < 10; cnt++) {
            SMSSender.getInstance()
                    .sendRegister(new SMS(
                            "0917",
                            "System",
                            "Liza Diloy",
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
        }  // ========== END OF FREE SF 10 LOOP DATA POPULATION ==========

        // ========== B1T1 - ALL FAILED_SMS - 10 INPUTS ==========
        for (int cnt = 0; cnt < 10; cnt++) {
            SMSSender.getInstance()
                    .sendRegister(new SMS(
                            "09987654321",
                            "System",
                            "Admin Secret",
                            "87000",
                            null,
                            "BUY1TAKE1",
                            LocalDateTime.of(
                                    2021,
                                    02,
                                    01,
                                    11,
                                    00,
                                    00))
                    );
        }  // ========== END OF B1T1 LOOP 10 DATA POPULATION ==========

// ====================================================================================================================
// ============= DATA POPULATION MULTIPLE MSISDN AND SENDER ===============
// ====================================================================================================================
        // ========== PROMO - ALL SUCESS_SMS - 10 INPUTS ==========
        for (int cnt = 0; cnt < 10; cnt++) {

            SMSSender.getInstance()
                    .sendRegister(new SMS(
                            "09170000000",
                            "System",
                            "Regina Liza",
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
                    .sendRegister(new SMS(
                            "09170000000",
                            "System",
                            "Regina Liza",
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
                    .sendRegister(new SMS(
                            "09170000000",
                            "System",
                            "Regina Liza",
                            "1234555",
                            null,
                            "Liza, Regina",
                            LocalDateTime.of(
                                    2021,
                                    03,
                                    02,
                                    11,
                                    00,
                                    00))
                    );
        }  // ========== END OF PROMO 10 DATA POPULATION LOOP ==========

        // ========== FREESF - ALL SUCESS_SMS - 10 INPUTS ==========
        for (int cnt = 0; cnt < 10; cnt++) {
            SMSSender.getInstance()
                    .sendRegister(new SMS(
                            "09451111111",
                            "System",
                            "Jimin Park",
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
                    .sendRegister(new SMS(
                            "09451111111",
                            "System",
                            "Jimin Park",
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
                    .sendRegister(new SMS(
                            "09451111111",
                            "System",
                            "Jimin Park",
                            "98765",
                            null,
                            "Park, Jimin",
                            LocalDateTime.of(
                                    2021,
                                    02,
                                    14,
                                    05,
                                    30,
                                    00))
                    );
        }  // ========== END OF FREE SF 10 LOOP DATA POPULATION ==========

        // ========== B1T1 - ALL SUCESS_SMS - 10 INPUTS ==========
        for (int cnt = 0; cnt < 10; cnt++) {
            SMSSender.getInstance()
                    .sendRegister(new SMS(
                            "09179999999",
                            "System",
                            "Jay Park",
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
                    .sendRegister(new SMS(
                            "09179999999",
                            "System",
                            "Jay Park",
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
                    .sendRegister(new SMS(
                            "09179999999",
                            "System",
                            "Jay Park",
                            "87000",
                            null,
                            "Park, Jay",
                            LocalDateTime.of(
                                    2021,
                                    02,
                                    01,
                                    11,
                                    00,
                                    00))
                    );
        }  // ========== END OF B1T1 LOOP 10 DATA POPULATION ==========
    }
}
