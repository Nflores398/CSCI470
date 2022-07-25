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


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class MileRedemptionApp {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // create a MileRedeemer object
        MileRedeemer myMileRedeemer = new MileRedeemer();

        // create a Scanner Object for client input.
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the name of the file: ");
        File fileName = new File(input.next());

        // create a Scanner Object for the file
        Scanner sourceFile = new Scanner(fileName);

        // read the available destinations from the file.
        myMileRedeemer.readDestinations(sourceFile);

        sourceFile.close(); // close the file scanner

        // print the App headers
        System.out.println("\n------------------------------------------------------------");
        System.out.println("     WELCOME TO THE JAVA AIRLINES MILES REDEMPTION APP      ");
        System.out.println("------------------------------------------------------------");

        // print the available destinations
        System.out.println("\nList of destination cities your client can travel to:\n");
        for (String name : myMileRedeemer.getCityNames()) {
            System.out.println(name);
        }

        Boolean shouldContinue; // continue flag

        // print the UI for each round of mile redemptions
        do {
            shouldContinue = false;

            System.out.println("\n------------------------------------------------------------");

            // get client's miles and departure month
            System.out.print("\nPlease enter your client's accumulated Frequent Flyer Miles: ");
            int miles = input.nextInt();

            System.out.print("\nPlease enter your client's month of departure(1-12): ");
            int month = input.nextInt();

            // redeem the miles
            String[] result = myMileRedeemer.redeemMiles(miles, month);

            // print the recommended destinations
            if (result.length == 0) {
                System.out.println("\n*** Your client has not accumulated enough Frequent Flyer Miles ***");
            } else {
                System.out.print("\nYour client's Frequent Flyer Miles can be used to redeem the following tickets:\n\n");

                for (int i = 0; i < result.length; i++) {
                    if (result[i] == null) {
                        System.out.println();
                        break;
                    }
                    System.out.println(result[i]);
                }
            }

            // print the remaining miles
            System.out.println("\nYour client's remaining Frequent Flyer Miles: " + myMileRedeemer.getRemainingMiles());

            // ask client if they wish to continue
            System.out.println("\n------------------------------------------------------------");
            System.out.print("\nDo you want to continue (y/n)? ");
            String response = input.next();

            if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes")) {
                shouldContinue = true;
            }
        } while (shouldContinue);

        System.out.println("\n-------------------------------------------------------------------------");
        System.out.println("       THANK YOU FOR USING THE JAVA AIRLINES MILES REDEMPTION APP        ");
        System.out.println("-------------------------------------------------------------------------");

        input.close(); // close the input scanner
    }
}
