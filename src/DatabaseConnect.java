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
            logger.info("CONNECTED TO DATABASE");

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

    // ================== DO NOT MODIFY ABOVE CODE ==========


    //retrieves SMS list given start date and end date
    @Override
    public ArrayList<SMS> retrieveSMSByDate(String startDate, String endDate) {
        ArrayList<SMS> smsList = new ArrayList<>();

        query = "SELECT * FROM sms_db.tbl_sms " +
                "WHERE timestamp BETWEEN '" + startDate +
                "' AND '" + endDate + "' ";

        return retrieveSMSData(smsList, query);
    }

    //retrieves SMS list given a msisdn
    @Override
    public ArrayList<SMS> retrieveSMSByMsisdn(String msisdn) {
        ArrayList<SMS> smsList = new ArrayList<>();

        query = "SELECT * FROM sms_db.tbl_sms " +
                "WHERE msisdn = '" + msisdn + "' ";

        return retrieveSMSData(smsList, query);
    }

    //retrieves SMS list given multiple msisdn
    @Override
    public ArrayList<SMS> retrieveSMSByMsisdnList(String[] msisdnList) {
        ArrayList<SMS> smsList = new ArrayList<>();

        for (String msisdn : msisdnList){
            query = "SELECT * FROM sms_db.tbl_sms " +
                    "WHERE msisdn = '" + msisdn + "' ";

            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);

                while (resultSet.next()){
                    sms = new SMS(
                            resultSet.getString("msisdn"),
                            resultSet.getString("recipient"),
                            resultSet.getString("sender"),
                            resultSet.getString("shortCode"),
                            resultSet.getString("transactionID"),
                            resultSet.getString("register"),
                            LocalDateTime.parse(
                                    resultSet.getString("timestamp"),
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                            Status.valueOf(resultSet.getString("status")),
                            Voucher.valueOf(resultSet.getString("promo"))
                    );
                    smsList.add(sms);

                    logger.log(Level.INFO, sms.toString());
                }

            } catch (SQLException e) {
                logger.log(Level.SEVERE, "SQLException: ", e);

            } finally {
                try {
                    if (statement != null){
                        statement.close();
                    }
                    if (resultSet != null){
                        resultSet.close();
                    }
                } catch (Exception e){
                    logger.log(Level.SEVERE, "ERROR IN CLOSING: ", e);
                }
            }
            logger.log(Level.INFO, "Retrieved : {0}", smsList);

        }

        disconnect();
        return smsList;
    }

    //retrieves SMS list given a promo code
    @Override
    public ArrayList<SMS> retrieveSMSbyPromoCode(String promoCode) {
        ArrayList<SMS> smsList = new ArrayList<>();

        query = "SELECT * FROM java_diloy_db.sms_db AS s " +
                "INNER JOIN java_diloy_db.promo_db AS p " +
                "ON s.shortCode = p.shortCode " +
                "WHERE p.promoCode = '" + promoCode + "' ";

        return retrieveSMSData(smsList, query);
    }

    //retrieves SMS list sent by the system
    @Override
    public ArrayList<SMS> retrieveSMSfromSystem(String sender) {
        ArrayList<SMS> smsList = new ArrayList<>();

        query = "SELECT * FROM java_diloy_db.sms_db " +
                "WHERE sender = '" + sender + "' ";

        return retrieveSMSData(smsList, query);
    }

    //retrieves SMS list received by the system
    @Override
    public ArrayList<SMS> retrieveSMSToSystem(String recipient) {
        ArrayList<SMS> smsList = new ArrayList<>();

        query = "SELECT * FROM java_diloy_db.sms_db " +
                "WHERE recipient = '" + recipient + "' ";

        return retrieveSMSData(smsList, query);
    }



    //improvised function that updates the transaction ID of the
    // recent added SMS because varchar fields cannot be autogenerated
    public void generateTransactionID(Map<String, Object> transactionId){
        Integer id = (Integer) transactionId.get("id");
        String promo = (String) transactionId.get("promo");

        //transaction ID = promo + autogenerated id
        query = "UPDATE java_diloy_db.sms_db " +
                "SET transactionID = '" + promo + id + "' " +
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

    //gets the id and promo of the recent added SMS
    //used for supplying the parameters required by the updateTransactionID
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

    //main shared function for retrieving SMS transactions
    private ArrayList<SMS> retrieveSMSData(ArrayList<SMS> smsList, String query) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                sms = new SMS(
                        resultSet.getString("msisdn"),
                        resultSet.getString("recipient"),
                        resultSet.getString("sender"),
                        resultSet.getString("shortCode"),
                        resultSet.getString("transactionID"),
                        resultSet.getString("register"),
                        LocalDateTime.parse(
                                resultSet.getString("timestamp"),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        Status.valueOf(resultSet.getString("status")),
                        Voucher.valueOf(resultSet.getString("promo"))
                );
                smsList.add(sms);

                logger.log(Level.INFO, sms.toString());
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException: ", e);

        } finally {
            try {
                if (statement != null){
                    statement.close();
                }
                if (resultSet != null){
                    resultSet.close();
                }
            } catch (Exception e){
                logger.log(Level.SEVERE, "ERROR IN CLOSING: ", e);
            }
        }
        logger.log(Level.INFO, "Retrieved : {0}", smsList);

        disconnect();
        return smsList;
    }

    // ==============

    public static ArrayList<String> retrieveData() {
        String selectQuery = "Select * from java_diloy_db.sms_db where sender = 'Pizza Hut'";
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> result = new ArrayList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectQuery);

            while(resultSet.next()) {
                logger.log(Level.INFO, "ROW: " + resultSet.getString(1)
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
}
