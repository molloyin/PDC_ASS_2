/*
 * Author: Matt Molloy
 * ID: 21135070
 */
package Contents;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HashMapContainer extends MapContainers {

    Map myHashMap = new HashMap();
    String toMap = "";

    public HashMapContainer(String fileName) {
        // turn text file to string
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader inputStream = new BufferedReader(fr);
            String input;
            
            while((input=inputStream.readLine()) != null) 
                toMap += input;
            inputStream.close();
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(HashMapContainer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HashMapContainer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // string to HashMap
        StringTokenizer st = new StringTokenizer(toMap, ",: ");
        String key = "";
        String value = "";
        while(st.hasMoreTokens()) {
            key = st.nextToken();
            if (st.hasMoreTokens())
                value = st.nextToken();
            myHashMap.put(key, value);
        }
        
    }

    @Override
    public Map update() {
        return this.myHashMap;
    }

}
