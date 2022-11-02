/*
 * Author: Matt Molloy
 * ID: 21135070
 */
package Safe;

import Contents.HashMapContainer;
import SafeInteraction.Deposit;
import SafeInteraction.Withdraw;
import java.util.Map;

public class LockedSafe extends Safe implements ILock, IPassword {

    private Map safe;
    private final String READFILE = "Sample Text.txt";
    private boolean open = false;
    private boolean locked = true;

    public LockedSafe() {
        safe = new HashMapContainer(READFILE).update();
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
            Deposit d = new Deposit(toAppend, READFILE);
            if (d.requestDeposit()) {
                safe = d.execute();
                return 0;
            } else {
                System.out.println("Cannot append input string to file.");
                return -1;
            }
        } else {
            System.out.println("Safe is closed, cannot store items.");
            return -1;
        }
    }

    @Override
    public int remove(String toRemove) {
        // ran out of time, see comments in Withdraw.java for plan
        if(open) {
            Withdraw w = new Withdraw(toRemove, READFILE);
            if (w.requestWithdraw()) {
                safe = w.execute();
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

    @Override
    public void lock() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void unlock() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int setPassword() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int enterPassword() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
