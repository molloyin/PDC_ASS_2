/*
 * The programs are designed for PDC paper
 */
package Contents;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class EmbeddedDB implements IDB {

    private static final String USER_NAME = ""; 
    private static final String PASSWORD = ""; 
    private static final String URL = "jdbc:derby:Safe; create=true";  
    static EmbeddedDB db;
    Connection conn;

    public EmbeddedDB() {
        establishConnection();
    }

    public static void main(String[] args) {
        db = new EmbeddedDB();
        System.out.println(db.getConnection());
    }

    @Override
    public Connection getConnection() {
        return this.conn;
    }

    //Establish connection
    @Override
    public void establishConnection() {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println(URL + " Get Connected Successfully ....");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


}
