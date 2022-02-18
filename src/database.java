import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class database {

    final private static Logger logger = Logger.getLogger(database.class.getName());
    private static Connection con = null;

    public static void main(String[] args) {
        database.connect();
        database.insertPromo("Piso Pizza");
        database.disconnect();
    }

    public static void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/java102_db?useTimezone=true&serverTimezone=UTC", "root", "reginaliza");
            logger.info("Connected");
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Not Connected", e);
        }
    }

    public static void disconnect() {
        try {
            if (con != null){
                con.close();
                logger.info("Disconnected");
            }
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Not Connected", e);
        }
    }

    //public static void insertPromo(String promoCode, String details, String shortCodePromo, Date startDate, Date endDate) {
public static void insertPromo(String args) {

        }
}
