/*
 * Author: Matt Molloy
 * ID: 21135070
 */
package Display;

import Safe.LockedSafe;
import Safe.Safe;
import Safe.UnlockedSafe;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SafeRunnable {

    public static void main(String[] args) {
        
        // program creates a hashmap using a txt file. When adding to or 
        // withdrawing from the bank, I'm rewriting the text file, then 
        // remaking the map using the altered file.
        // nothing is broken, and everything is working except for Withdraw
        // and the password interation with the locked safe. It is clear to 
        // me how to fix these problems. The withdraw requires subtle changes 
        // to its copying process, and the password system just needs to pass
        // some conditional statments based on user input.

        boolean running = true;
        Safe safe;
        int safeChoice = -1;
        int forbiddenOption = 0;
        int selected = -1;
        String query = "";
        Scanner scan = new Scanner(System.in);

        System.out.println("""
                           Welcome to your personal safe! Here you can store, retrieve, or look at your belongings!
                           """);
        do {
            try {
                System.out.println("""
                                   Firstly, would you like to use your lockable safe, or permanently unlocked safe?
                                   Enter 0 for locked, 1 for unlocked.""");
                safeChoice = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
            }
            scan.nextLine();
        } while (safeChoice != 0 && safeChoice != 1);

        if (safeChoice == 0) {
            safe = new LockedSafe();
        } else {
            safe = new UnlockedSafe();
        }

        while (running) {
            do {
                System.out.println("""
                               Your options sir/madam:
                               0: Exit Process
                               1: Check Safe Contents
                               2: Deposit
                               3: Withdraw
                               4: Open Safe
                               5: Close Safe""");
                if (safeChoice == 0) {
                    System.out.println("""
                                   6: Set Password
                                   7: Enter Password""");
                }
                try {
                    selected = scan.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input.");
                }

                scan.nextLine();
            } while (selected != 0 && selected != 1 && selected != 2
                    && selected != 3 && selected != 4 && selected != 5
                    && selected != 6 && selected != 7); // must be better way

            switch (selected) {
                case 0:
                    running = false;
                    stop();
                    break; // necessary? lol
                case 1:
                    safe.check();
                    break;
                case 2:
                    query = stringCheck();
                    if (safe.store(query) == 0) {
                        System.out.println(query + "  successfully stored!");
                    }
                    break;
                case 3:
                    System.out.println("Enter name of item you wish to remove");
                    query = scan.next();
                    if (safe.remove(query) == 0) {
                        System.out.println(query + "  successfully removed!");
                    }
                    break;
                case 4:
                    safe.open();
                    System.out.println("Safe now open!");
                    break;
                case 5:
                    safe.close();
                    System.out.println("Safe now closed!");
                    break;
                case 6:
                    if (safeChoice == 1) {
                        System.out.println("Illegal option for unlocked safe.");
                        continue;
                    }
                    System.out.println("Ran out of time, feature not supported.");
                    break;
                case 7:
                    if (safeChoice == 1) {
                        System.out.println("Illegal option for unlocked safe.");
                        continue;
                    }
                    System.out.println("Ran out of time, feature not supported.");
                    break;
            }
        }

        stop();

    }

    public static void stop() {
        System.out.println("Thanks for stopping by! See you later");
        System.exit(0);
    }

    public static String stringCheck() {
        Scanner scan = new Scanner(System.in);
        String out = "";
        String in = "";
        boolean acceptable = false;
        do {
            System.out.println("Enter what you'd like to deposit using the "
                    + "following format:\n"
                    + "\"name, quantity:\"  e.g\n"
                    + "cash, 100:");
            in += scan.next();
            in += scan.next();
            scan.nextLine();
            if (in.contains(",") && in.contains(":")) {
                out = in;
                acceptable = true;
            } else {
                in = "";
                System.out.println("Incompatible formatting, please try again.");
            }
        } while (!acceptable);
        return out;
    }
}
