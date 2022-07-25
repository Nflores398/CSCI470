
/***********************************************************
 *                                                         *
 *  CSCI 470/502       Assignment 4       Summer 2022      *
 *                                                         *
 * Class   Name: Programming in Java                       *
 *                                                         *
 * Developer(s): Seraphina Nelson, Noah Flores             *
 *                                                         *
 * Due date:  07/15/2022                                   *
 *  Purpose:  This program generates a list of             *
 *            destinations a client is eligible to travel  *
 *            to and considers off-season discounts during *
 *            off-season months. It also recommends        *
 *            upgrades when their are remaining miles to   *
 *            redeem.                                      *
 *                                                         *
 ***********************************************************/
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MileRedemptionApp {

    public static void main(String[] args) throws IOException {
        // stores cityNames
        String[] cityNames = new String[20];
        String[] trips; // Stores the trips the user can take
        MileRedeemer mileRedeemer = new MileRedeemer(); // Create class object
        Scanner sc2 = new Scanner(System.in); // create scanner object
        String input; // used to store user input
        int miles; // store user entered miles
        int month; // used to store user entered month
        // Open file
        try {

            System.out.print("Please enter the name of the file: ");
            input = sc2.next();
            File myFile = new File(input);

            Scanner sc = new Scanner(myFile);
            mileRedeemer.readDestinations(sc);
            sc.close();
            // if file failed to open
        } catch (FileNotFoundException e) {
            System.out.println("File failed to open");
            sc2.close();
            return;
        }
        System.out.println("----------------------------------------------------------------");
        System.out.println("       WELCOME TO THE JAVA AIRLINES MILES REDEMPTION APP       ");
        System.out.println("----------------------------------------------------------------");
        System.out.println("\nList of destination cities your client can travel to:\n");
        cityNames = mileRedeemer.getCityNames(); // get the city names from file
        // Loop and display all cities
        for (int i = 0; i < cityNames.length; i++) {
            System.out.println(cityNames[i]);
        }
        // loop until user quits program
        while (true) {
            // loops until user enters vailed miles amount
            while (true) {
                System.out.println("\n----------------------------------------------------------------\n");
                while (true) {

                    System.out.print("Please enter your client's accumulated Frequent Flyer Miles: ");
                    // get user input
                    input = sc2.next();
                    System.out.println();
                    try {
                        // sets user input to mile amount
                        miles = Integer.parseInt(input);
                        // if amount is a negative number then throw exception
                        if (miles < 0) {
                            throw new NumberFormatException();
                        }
                        // break out of loop to move to next step
                        break;
                        // If user enters a bad input
                    } catch (NumberFormatException nfe) {
                        System.out.println("Miles amount invailed.");
                    }
                }
                // loop until user enters vailed month
                while (true) {

                    System.out.print("Please enter your client's month of departure (1-12): ");
                    // get user input
                    input = sc2.next();
                    System.out.println();
                    try {
                        // sets user input to bill amount
                        month = Integer.parseInt(input);
                        // if amount is a negative number then throw exception
                        if (month <= 0 || month >= 13) {
                            throw new NumberFormatException();
                        }
                        // break out of loop to move to next step
                        break;
                        // If user enters a bad input
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invailed month.");
                    }
                }
                trips = mileRedeemer.redeemMiles(miles, month); // call mile redeemer and return all trips
                if (trips.length == 0 || trips[0] == null) {
                    System.out.println("*** Your client has not accumulated enough Frequent Flyer Miles ***\n");
                } else {
                    for (int i = 0; i < trips.length; i = i + 2) {
                        System.out.println("* A trip to " + trips[i] + " in " + trips[i + 1]);
                    }
                }
                // Get the remaining miles
                miles = mileRedeemer.getRemainingMiles();
                System.out.println("\nYour client's remaining Frequent Flyer Miles: " + miles + "\n");
                while (true) {
                    System.out.print("\nDo you want to continue (y/n)? ");
                    // read input from user
                    input = sc2.next();
                    // if user wants to quit program
                    if (input.equals("n") || input.equals("N")) {
                        System.out
                                .println("\n-------------------------------------------------------------------------");
                        System.out.println("       THANK YOU FOR USING THE JAVA AIRLINES MILES REDEMPTION APP       ");
                        System.out
                                .println("-------------------------------------------------------------------------\n");
                        sc2.close();
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
    }
}
