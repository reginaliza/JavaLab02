import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnect implements SMSManager {

    final private static Logger logger = Logger.getLogger(DatabaseConnect.class.getName());

    private static DatabaseConnect instance = null;
    private static Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private String query;
    private SMS sms = null;

    //Singleton
    public static DatabaseConnect getInstance(){
        if (instance == null){
            instance = new DatabaseConnect();
        }
        connect();
        return instance;
    }


    static void connect() {
        try {
            Properties properties = getProperties();

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password"));
            logger.log(Level.INFO,"CONNECTED TO DATABASE");

        } catch (Exception e){
            logger.log(Level.SEVERE, "CONNECTION FAILED: ", e);
        }
    }


    public void disconnect(){
        try{
            if (connection!= null){
                connection.close();
                logger.info("DISCONNECTED TO DATABASE");
            }
        } catch (Exception e){
            logger.log(Level.SEVERE, "ERROR!! ", e);
        }
    }


    private static Properties getProperties(){
        Properties properties = new Properties();
        try {
            properties.load(
                    new FileInputStream("/Users/reginaliza.diloy/IdeaProjects/java_diloy/src/config.properties"));
        } catch (IOException e){
            logger.log(Level.SEVERE, "IOException: ", e);
        }
        return properties;
    }

    // ========== END OF CONNECTION TO DATABASE CODE ==========

    //inserts promo to the database
    public String insertPromo(Promo promo){
        query = "INSERT INTO java_diloy_db.promo_db " +
                "(promoCode, details, shortCode, startDate, endDate) " +
                "values (" +
                "\n'" + promo.getPromoCode() + "', " +
                "\n'" + promo.getDetails() + "', " +
                "\n'" + promo.getShortCode() + "', " +
                "\n'" + promo.getStartDate() + "', " +
                "\n'" + promo.getEndDate() + "' )";

        try {
            statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e){
            logger.log(Level.SEVERE, "SQLException: ", e);
        } finally {
            try {
                if (statement != null){
                    statement.close();
                }

            } catch (Exception e){
                logger.log(Level.SEVERE, "ERROR IN CLOSING: ", e);
            }
        }

        logger.log(Level.INFO, "ADDED TO DATABASE" + promo.toString());
        //disconnect();
        return "Created Promo: " + promo.getDetails();
    }

    // ==================================================================

    //inserts SMS to the database
    @Override
    public void insertSMS(SMS sms) {
        query = "INSERT INTO java_diloy_db.sms_db " +
                "(msisdn, recipient, sender, shortCode, " +
                "register, promo, status, timestamp) " +
                "values ( " +

                "\n'" + sms.getMsisdn() + "', " +
                "\n'" + sms.getRecipient() + "', " +
                "\n'" + sms.getSender() + "', " +
                "\n'" + sms.getShortCode() + "', " +
                "\n'" + sms.getRegister() + "', " +
                "\n'" + sms.getVoucherCode().getPromoCode() + "', " +
                "\n'" + sms.getStatus().name() + "', " +
                "\n'" + sms.getFormattedTimestamp() + "' )" ;

        try {
            statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e){
            logger.log(Level.SEVERE, "SQLException: ", e);
        } finally {
            try {
                if (statement != null){
                    statement.close();
                }

            } catch (Exception e){
                logger.log(Level.SEVERE, "ERROR IN CLOSING: ", e);
            }
        }

        logger.log(Level.INFO, "Added to Database " + sms.toString());
        //disconnect();
    }
// ===================================
    // ================== DO NOT MODIFY ABOVE CODE, ALREADY WORKING ===============
//====================================

    public void generateTransactionID(Map<String, Object> transactionId){
        Integer id = (Integer) transactionId.get("id");
        String promo = (String) transactionId.get("promo");
        query = "UPDATE java_diloy_db.sms_db " +
                "SET transactionID = '" + id + "-" + promo + "' " +
                "WHERE id = " + id;

        try {
            statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e){
            logger.log(Level.SEVERE, "SQLException: ", e);
        } finally {
            try {
                if (statement != null){
                    statement.close();
                }

            } catch (Exception e){
                logger.log(Level.SEVERE, "ERROR IN CLOSING: ", e);
            }
        }

        logger.log(Level.INFO, "UPDATED DATA: TransactionID = " + promo + id);
        disconnect();
    }

    public Map<String, Object> getIdPromo() {
        query = "SELECT s.id, s.promo " +
                "FROM java_diloy_db.sms_db AS s " +
                "ORDER BY s.id DESC ";
        Map<String, Object> idPromo = new HashMap<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();

            idPromo.put("id", resultSet.getInt("id"));
            idPromo.put("promo", resultSet.getString("promo"));

           logger.log(Level.INFO, "READ DATA: " + idPromo.toString());

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException: ", e);
        } finally {
            try {
                if (statement != null){
                    statement.close();
                }

            } catch (Exception e){
                logger.log(Level.SEVERE, "ERROR IN CLOSING: ", e);
            }
        }

        disconnect();
        return idPromo;
    }

    public static ArrayList<String> retrieveData() {

        //Retrieve SMS given a start date and end date
        String selectQuery = "Select * from java_diloy_db.sms_db where timestamp '2021-02-01 10:00:00' and '2021-02-01 29:59:00'";

        //Retrieve SMS given a promo code
        //String selectQuery = "Select * from java_diloy_db.sms_db where promo = 'FREESF'";

        //Retrieve SMS given an msisdn
        //String selectQuery = "Select * from java_diloy_db.sms_db where msisdn = '09202819125'";

        //Retrieve SMS sent by the system
        //String selectQuery = "Select * from java_diloy_db.sms_db where sender = 'system'";

        //Retrieve SMS receive by the system
        //String selectQuery = "Select * from java_diloy_db.sms_db where sender = 'Regina Diloy'";

        //Retrieve SMS given several msisdn
        //String selectQuery = "Select * from java_diloy_db.sms_db where msisdn = '09202819125' and '09171234567'";

        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> result = new ArrayList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectQuery);

            while(resultSet.next()) {
                logger.log(Level.INFO, "ROW IN DATABASE: " + resultSet.getString(1)
                        + "\nMobile Number: " + resultSet.getString(2)
                        + "\nRecipient: " + resultSet.getString(3)
                        + "\nSender:" + resultSet.getString(4)
                        + "\nShort Code: " + resultSet.getString(5)
                        + "\nTime Stamp: " + resultSet.getString(6)
                        + "\nTransaction ID: " + resultSet.getString(7)
                        + "\nStatus: " + resultSet.getString(8)
                        + "\nPromo Code: " + resultSet.getString(9)
                        + "\n ==================== ");
            }
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    statement.close();
                }
            }
            catch (Exception e) {
                logger.log(Level.SEVERE, "ERROR IN CLOSING", e);
            }
        }

        //logger.log(Level.INFO, "Retrieved: {0}", result);
        logger.log(Level.INFO, "===== Retrieved Data =====");
        return result;
    }


// FOR PISO PIZZA PROMO ONLY
    public static ArrayList<String> generateReport() {

        //List of Failed Transactions
        String selectQuery = "Select * from java_diloy_db.sms_db where status = 'FAILED_SMS'";

        //List of Successful Transactions
        //String selectQuery = "Select * from java_diloy_db.sms_db where status = 'SUCCESS'";

        //List of Persons who joined the Promo
        //String selectQuery = "Select * from java_diloy_db.sms_db where sender = 'Regina Diloy'";

        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> result = new ArrayList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectQuery);

            while(resultSet.next()) {
                logger.log(Level.INFO, "ROW IN DATABASE: " + resultSet.getString(1)
                        + "\nMobile Number: " + resultSet.getString(2)
                        + "\nRecipient: " + resultSet.getString(3)
                        + "\nSender:" + resultSet.getString(4)
                        + "\nShort Code: " + resultSet.getString(5)
                        + "\nTime Stamp: " + resultSet.getString(6)
                        + "\nTransaction ID: " + resultSet.getString(7)
                        + "\nStatus: " + resultSet.getString(8)
                        + "\nPromo Code: " + resultSet.getString(9)
                        + "\n ==================== ");
            }
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    statement.close();
                }
            }
            catch (Exception e) {
                logger.log(Level.SEVERE, "ERROR IN CLOSING", e);
            }
        }

        //logger.log(Level.INFO, "Retrieved: {0}", result);
        logger.log(Level.INFO, "===== Retrieved Data =====");
        return result;
    }

    //Total Count of SMS received
    public static ArrayList<String> totalReceived() {
        String selectQuery = "Select count(*) from java_diloy_db.sms_db where sender != 'System'";
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> result = new ArrayList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectQuery);

            while(resultSet.next()) {
                int count = resultSet.getInt(1);
                logger.log(Level.INFO,"Number of SMS received by system: " +count);
            }
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    statement.close();
                }
            }
            catch (Exception e) {
                logger.log(Level.SEVERE, "ERROR IN CLOSING", e);
            }
        }
        return result;
    }

    //Total Count of SMS Sent
    public static ArrayList<String> totalSent() {
        String selectQuery = "Select count(*) from java_diloy_db.sms_db where sender = 'System'";
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> result = new ArrayList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectQuery);

            while(resultSet.next()) {
                int count = resultSet.getInt(1);
                logger.log(Level.INFO,"Number of SMS sent by system: " +count);
            }
        }
        catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    statement.close();
                }
            }
            catch (Exception e) {
                logger.log(Level.SEVERE, "ERROR IN CLOSING", e);
            }
        }
        return result;
    }
}
