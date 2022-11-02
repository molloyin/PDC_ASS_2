/*
 * Author: Matt Molloy
 * ID: 21135070
 */
package Safe;

public abstract class Safe {
    
    public abstract void open();

    public abstract void close();

    public abstract int store(String toAppend);

    public abstract int remove(String toRemove);

    public abstract void check();
    
    public abstract void printContents();
}
