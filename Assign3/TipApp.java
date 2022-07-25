
/**********************************************************
 *                                                        *
 *  CSCI 470/502       Assignment 3       Summer 2022     *
 *                                                        *
 * Class   Name: Programming in Java                      *
 *                                                        *
 * Developer(s): Seraphina Nelson, Noah Flores            *
 *                                                        *
 * Due Date: 07/01/2022                                   *
 *                                                        *
 *  Purpose:  This program will allow users to enter a    *
 * bill amount, tip percent, and party size. Then the     *
 * program wil calc the total bill cost and the even spit *
 * for the party.                                         *
 *                                                        *
 *                                                        *
 **********************************************************/
import java.util.Scanner;
import java.text.DecimalFormat;

public class TipApp {
    public static void main(String[] args) {
        // Used for reading in keyboard input
        Scanner sc = new Scanner(System.in);
        // creates a tipApp object
        TipApp tipApp = new TipApp();
        // creates a stirng object.
        String input = new String();
        // loop until the user decides to end the program when prompted
        while (true) {
            // call funcation that will run the calc tip routine
            tipApp.calculateTips(sc, input);
            // loop until user enters a correct input
            while (true) {
                System.out.print("\nAnother bill? (y/n): ");
                // read input from user
                input = sc.next();
                // if user wants to quit program
                if (input.equals("n") || input.equals("N")) {
                    System.out.println("\nGoodbye!\n");
                    sc.close();
                    return;
                    // if user wants to do another tip calc
                } else if (input.equals("y") || input.equals("Y")) {
                    break;
                    // user enters a bad input
                } else {
                    System.out.println("\nBad input!");
                }
            }
        }
    }

    /**********************************************************************
     * *
     * calculateTips(Scanner,String) takes a scanner object and a string. *
     * The funcation will take user input and print out the total *
     * bill and the spit bill for everyone in the party *
     * *
     *********************************************************************/
    public void calculateTips(Scanner sc, String input) {
        Double billAmount; // stores bill amount
        Integer tipPercentage, partySize; // stores tip perecent and party size
        String pattern = "$##,###,###.00"; // formats the output for the bill
        DecimalFormat billFormat = new DecimalFormat(pattern); // creates a decimal format object
        TipCalculator tipCalulcator = new TipCalculator(); // creates a class object from TipCalculator

        System.out.println("*** Tip Calculator ***\n");
        // loop until user enters correct value
        while (true) {

            System.out.print("Enter the bill amount: ");
            // get user input
            input = sc.next();
            try {
                // sets user input to bill amount
                billAmount = Double.parseDouble(input);
                // if amount is a negative number then throw exception
                if (billAmount < 0) {
                    throw new NumberFormatException();
                }
                tipCalulcator.setBillAmount(billAmount);
                // break out of loop to move to next step
                break;
                // If user enters a bad input
            } catch (NumberFormatException nfe) {
                System.out.println("Bill amount invailed.");
            }
        }
        // loop until user enters correct value
        while (true) {
            System.out.print("Enter your desired tip percentage (20 equals 20%): ");
            // get user input
            input = sc.next();
            try {
                // sets user input to tip percentage
                tipPercentage = Integer.parseInt(input);
                // if the tip is a negative number then throw exception
                if (tipPercentage < 0) {
                    throw new NumberFormatException();
                }
                tipCalulcator.setTipPercentage(tipPercentage);
                // break out of loop to move to next step
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Tip amount invailed.");
            }
        }
        // loop until user enters correct value
        while (true) {
            System.out.print("Enter the size of your party: ");
            // get user input
            input = sc.next();
            try {
                // sets user input to party size
                partySize = Integer.parseInt(input);
                // if party size is less than or equal to zero then throw exception
                if (partySize <= 0) {
                    throw new NumberFormatException();
                }
                tipCalulcator.setPartySize(partySize);
                // break out of loop to move to next step
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Party amount invailed.");
            }
        }
        // print out bill
        System.out.println("\n*** Your Bill ***\n");
        System.out.println("Bill Amount: " + billFormat.format(tipCalulcator.getBillAmount()));
        System.out.println("Tip Percentage: " + tipCalulcator.getTipPercentage() + "%");
        System.out.println("Party Size: " + tipCalulcator.getPartySize());
        System.out.printf("\nTotal Bill (with Tip):            %s%n",
                billFormat.format(tipCalulcator.getTotalBill()));
        System.out.printf("Share for Each Individual:        %s%n",
                billFormat.format(tipCalulcator.getIndividualShare()));
        // exit function
        return;
    }
}
