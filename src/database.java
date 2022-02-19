import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class database {

    private static Connection con;
    static final private Logger logger = Logger.getLogger(sms.class.getName());

    public static void main (String[] args) {
        database.connect();
        database.insertData();
        database.insertPromo();
        database.disconnect();

    }

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/lab02?useTimezone=true&serverTimezone=UTC", "root", "reginaliza");
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
    // ====== WORKING INSERT DATA TO DB
    public static void insertData() {
        String insertQuery = "Insert into sms_db (msisdn, recipient, sender, shortCode, timestamp) values (123, 'regina', 'diloy', 'Piso Pizza', '021922')";
        Statement statement = null;
        int result = 0;
        try {
            statement = con.createStatement();
            result = statement.executeUpdate(insertQuery);
        } catch (SQLException e){
            logger.log(Level.SEVERE, "SQLException", e);
        } finally {
            try {
                if (statement != null){
                    statement.close();
                }
            }catch (Exception e){
                logger.log(Level.SEVERE, "ERROR IN CLOSING", e);
            }
        }
        logger.log(Level.INFO, "Inserted : {0}", result);
    }

    public static void insertPromo() {
        String insertQuery = "Insert into promo_db (details, shortCode, startDate, endDate) values ('Piso Pizza', '1234555', '20210201 10:00:00 AM', '20210630 11:59:00 PM')";


        Statement statement = null;
        int result = 0;
        try {
            statement = con.createStatement();
            result = statement.executeUpdate(insertQuery);
        } catch (SQLException e){
            logger.log(Level.SEVERE, "SQLException", e);
        } finally {
            try {
                if (statement != null){
                    statement.close();
                }
            }catch (Exception e){
                logger.log(Level.SEVERE, "ERROR IN CLOSING", e);
            }
        }
        logger.log(Level.INFO, "Inserted : {0}", result);
    }

    public static void setCon(Connection con) {
        database.con = con;
    }

}