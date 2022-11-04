/*
 * Author: Matt Molloy
 * ID: 21135070
 */
package SafeInteraction;

public class Withdraw extends SafeInteraction implements IWithdraw {
    
    String toRemove;

    public Withdraw(String toRemove) {
        this.toRemove = toRemove;
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean requestWithdraw() {
        return true;
    }
}
