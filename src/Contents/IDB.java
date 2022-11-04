/*
 * Author: Matt Molloy
 * ID: 21135070
 */
package Contents;

/**
 *
 * @author omgth
 */
import java.sql.Connection;

public interface IDB {
    
    public Connection getConnection();
    public void establishConnection();
    public void closeConnections();
}
