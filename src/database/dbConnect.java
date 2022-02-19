package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dbConnect {
    final private static Logger logger = Logger.getLogger(dbConnect.class.getName());
    private static Connection con = null;

    public static Connection connect(Connection con) {
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
}
