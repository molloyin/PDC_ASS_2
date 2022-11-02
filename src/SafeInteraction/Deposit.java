/*
 * Author: Matt Molloy
 * ID: 21135070
 */
package SafeInteraction;

import Contents.HashMapContainer;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Deposit extends SafeInteraction implements IDeposit {
    
    String toAppend;
    String fileName;
    
    public Deposit(String toAppend, String fileName) {
        this.toAppend = toAppend;
        this.fileName = fileName;
    }
    
    @Override
    public Map execute() {
        HashMapContainer hm = new HashMapContainer(fileName);
        return hm.update();
    }

    @Override
    public boolean requestDeposit() {
        try {
            FileOutputStream fos = new FileOutputStream(fileName, true);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(toAppend);
            pw.close();
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Deposit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
