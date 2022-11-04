/*
 * Author: Matt Molloy
 * ID: 21135070
 */
package Safe;

import Contents.EmbeddedDB;
import SafeInteraction.Deposit;
import SafeInteraction.Withdraw;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class UnlockedSafe extends Safe {

    private boolean open = true;
    public static EmbeddedDB safe;
    Connection conn;
    Statement stat;

    public UnlockedSafe() {
        safe = new EmbeddedDB();
        createTable();
    }

    @Override
    public void open() {
        open = true;
    }

    @Override
    public void close() {
        open = false;
    }

    @Override
    public int store(String toAppend) {
        if (open) {
            Deposit d = new Deposit(toAppend);
            if (d.requestDeposit()) {
                d.execute();
                return 0;
            } else {
                System.out.println("Cannot deposit item in safe.");
                return -1;
            }
        } else {
            System.out.println("Safe is closed, cannot store items.");
            return -1;
        }
    }

    @Override
    public int remove(String toRemove) {
        if (open) {
            Withdraw w = new Withdraw(toRemove);
            if (w.requestWithdraw()) {
                //safe = w.execute();
                return 0;
            } else {
                System.out.println("Cannot remove query from file.");
                return -1;
            }
        } else {
            System.out.println("Safe is closed, cannot remove items.");
        }
        return -1;
    }

    @Override
    public void check() {
        System.out.println(safe.toString());
    }

    @Override
    public void printContents() {
        System.out.println(safe.toString());
    }

    private void createTable() {
        this.conn = safe.getConnection();
        try {
            stat = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            stat.addBatch("CREATE  TABLE SAFECONTENTS  (OBJECT   VARCHAR(50))");
            stat.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        safe.closeConnections();
    }

}
