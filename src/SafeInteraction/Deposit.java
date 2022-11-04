/*
 * Author: Matt Molloy
 * ID: 21135070
 */
package SafeInteraction;

import Safe.UnlockedSafe;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Deposit extends SafeInteraction implements IDeposit {

    String toAppend;
    Connection conn;
    Statement stat;

    public Deposit(String toAdd) {
        this.toAppend = toAdd;
        conn = UnlockedSafe.safe.getConnection();
        try {
            stat = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void execute() {
        try {
            stat.executeUpdate("INSERT INTO SAFECONTENTS VALUES (" + toAppend + ")");
        } catch (SQLException ex) {
            Logger.getLogger(Deposit.class.getName()).log(Level.SEVERE, null, ex);
        }
        UnlockedSafe.safe.closeConnections();
    }

    @Override
    // redundant due to emeddedDB implementation, relevant in other
    // implementations
    public boolean requestDeposit() {
        return true;
    }
}
