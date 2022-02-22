import Base.Promo;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    final private static Logger logger = Logger.getLogger(Main.class.getName());
    private static Connection con = null;

    public static void main(String[] args) {
        Main.connect();
        //Main.insertPromo();
        //Main.populate();
        Main.retrieveData();
        Main.disconnect();
    }

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/RegLab02?useTimezone=true&serverTimezone=UTC", "root", "reginaliza");
            logger.info("Connected");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Not Connected", e);
        }
        return null;
    }

    public static void disconnect() {
        try {
            if (con != null) {
                con.close();
                logger.info("Disconnected");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Not Connected", e);
        }
    }
    // SMS
    public static void populate() {
        String insertQuery = "Insert into SMS_DB (transactionID, promoCode, register, msisdn, firstName, lastName, shortCode, timestamp)" +
                "values ('3', 'PROMO', 'REGISTER', '09202819125', 'Regina', 'Diloy', '1234555', '2021-02-01 10:00:00')";

        Statement statement = null;
        int result = 0;
        try {
            statement = con.createStatement();
            result = statement.executeUpdate(insertQuery);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "ERROR IN CLOSING", e);
            }
        }
        logger.log(Level.INFO, "Inserted : {0}", result);
    }

    // WORKING INSERT PROMO
    public static void insertPromo() {
        String insertQuery = "Insert into PROMO_DB (promoCode, details, shortCode, startDate, endDate) " +
                "values ('PROMO', 'Piso Pizza', '1234555', '2021-02-01 10:00:00', '2021-06-30 23:59:00'), " +
                "('FREESF', 'Free Shipping', '1234555', '2022-02-02 10:00:00', '2022-03-30 23:59:00'), " +
                "('B1T1', 'Buy One Take One', '1234555', '2020-01-01 10:00:00', '2020-02-02 23:59:00')";

        Statement statement = null;
        int result = 0;
        try {
            statement = con.createStatement();
            result = statement.executeUpdate(insertQuery);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "ERROR IN CLOSING", e);
            }
        }
        logger.log(Level.INFO, "Inserted : {0}", result);
    }
    private static ArrayList<String> retrieveData() {
        String selectQuery = "Select * from RegLab02.SMS_DB where msisdn = '09202819125' ";
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> result = new ArrayList<>();

        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery(selectQuery);

            while(resultSet.next()) {
                logger.log(Level.INFO, resultSet.getString(1) + " : " + resultSet.getString(2));
                result.add(resultSet.getString(1) + " : " + resultSet.getString(2));
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

        logger.log(Level.INFO, "Retrieved: {0}", result);
        return result;
    }
}
