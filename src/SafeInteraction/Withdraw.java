/*
 * Author: Matt Molloy
 * ID: 21135070
 */
package SafeInteraction;

import Contents.HashMapContainer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Withdraw extends SafeInteraction implements IWithdraw {

// sadly, Withdraw is not working properly and I don't have time to fix it.
// the plan was/is to copy "Sample Text.txt" into a new file while skipping
// the unwanted line, then remaking HashMapContainer with this new and improved
// file. Root of the problem is in requestWithdraw().
    
    String toRemove;
    String fileName;
    File original = new File("Sample Text.txt");
    File replacement = new File("New.txt");

    public Withdraw(String toRemove, String fileName) {
        this.toRemove = toRemove;
        this.fileName = fileName;
    }

    @Override
    public Map execute() {
        HashMapContainer hm = new HashMapContainer(fileName);
        return hm.update();
    }

    @Override
    public boolean requestWithdraw() {
        boolean successful = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader(original));
            BufferedWriter bw = new BufferedWriter(new FileWriter(replacement));
            String s;

            while ((s = br.readLine()) != null) {
                String trimmed = s.trim();
                if (trimmed.equals(toRemove)) {
                    continue;
                }
                bw.write(s + System.getProperty("line.separator"));
            }

            br.close();
            bw.close();
            // swap file names around for next iteration
            //successful = replacement.renameTo(original);
            //original.renameTo(new File("New.txt"));
        } catch (IOException ex) {
            Logger.getLogger(Withdraw.class.getName()).log(Level.SEVERE, null, ex);
        }

        //return successful;
        return false;
    }
}
