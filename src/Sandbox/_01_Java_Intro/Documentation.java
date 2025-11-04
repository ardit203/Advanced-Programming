package Sandbox._01_Java_Intro;





/**
  We have two main groups of data types in Java:<br>
    • Primitive types – passed by value (not by reference).<br>
    • Reference types – all other types (classes, arrays, wrappers, custom objects)
      are passed by reference, meaning you work with the actual object, not a copy.
*/

class Printer {
    /**
     * Prints the given string followed by a new line.
     *
     * <p>This is a simple utility method that wraps {@link System#out} to
     * standardize printing across the project.</p>
     *
     * @param str the string to be printed
     */
    public static void print(String str) {
        System.out.println(str);
    }



    /**
     * We have two main groups of data types in Java:
     * <ul>
     *   <li><b>Primitive types</b> – value types passed by <i>value</i>, not by reference.</li>
     *   <li><b>Reference types</b> – all other types (classes, arrays, wrapper classes, custom objects),
     *       which are passed by <i>reference</i>. This means you work with the actual object, not a copy.</li>
     * </ul>
     */
    static void main() {
        Printer.print("Hello World!");
    }
}


/**
 * Program to calculate and display the interest earned on a given account balance.
 *
 * <p>This simple program demonstrates the use of constants, variables,
 * and basic arithmetic operations in Java. It calculates the annual
 * interest earned on a fixed balance using a predefined interest rate.</p>
 *
 * <p>Author: Ardit Selmani<br>
 * E-mail: ardit.selmani@finki.ukim.mk<br>
 * Last Updated: November 4, 2025</p>
 *
 * @author Ardit Selmani
 * @version 1.0   (current version)
 * @since 1.0   (initial release)
 */
class ShowInterest {

    /**
     * Constant representing the interest rate (as a percent).
     */
    public static final double INTEREST_RATE = 2.5;

    /**
     * The main method that starts the program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        double balance = 100;
        double interest;

        // Calculate interest as a percentage of the balance
        interest = balance * (INTEREST_RATE / 100.0);

        // Output results
        System.out.println("On a balance of $" + balance);
        System.out.println("You will earn interest of $" + interest);
        System.out.println("All in just one short year.");
    }
}
