/**********************************************************
 *                                                        *
 *  CSCI 470/502       Assignment 6       Summer 2022     *
 *                                                        *
 * Class   Name: Programming in Java                      *
 *                                                        *
 * Developer(s): Seraphina Nelson, Noah Flores            *
 *                                                        *
 * Due Date: 08/04/2022                                   *
 *                                                        *
 *  Purpose: This class file contains methods to allow    *
 *  the program to read the input file, get all           *
 *  city names, and get all destinations a user can go    *
 *  based off entered miles, and month.                   *
 *                                                        *
 *                                                        *
 **********************************************************/
package com.example.assign6_mileredemption;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.lang.Math.*;


public class MileRedeemer {
    //An arraylist to store destinations
    ArrayList<Destination> destinationList = new ArrayList<Destination>();
    //array to store destinations
    Destination[] destinationArray;
    //stores the remaining miles
    int milesRemaining;
    //returns the destination list
    public ArrayList<Destination> getDestinationList()
    {
        return this.destinationList;
    }
    //Reads destination files and put the data into destinations
    public void readDestinations(Scanner fileScanner) {
        String input; // Stores lines from the files
        String[] splitString; //Stores the string when split
        String[] offSeason; // stores off season date range.
        try {
            //Loop until end of file
            while (fileScanner.hasNextLine()) {
                //read file
                input = fileScanner.nextLine();
                //split by ;
                splitString = input.split(";");
                //then split by - for off season
                offSeason = splitString[4].split("-");
                //create new destination object
                Destination destination = new Destination(splitString[0], Integer.parseInt(splitString[1]),
                        Integer.parseInt(splitString[2]), Integer.parseInt(splitString[3]), Integer.parseInt(offSeason[0]),
                        Integer.parseInt(offSeason[1]));
                //add to object to a list
                destinationList.add(destination);
            }
            MileageComparator mileageComparator = new MileageComparator();
            //sort and store destination objects in array
            destinationArray = (Destination[]) destinationList
                    .toArray(new Destination[destinationList.size()]);
            Arrays.sort(destinationArray, mileageComparator);

            return;
        }
        catch (ArrayIndexOutOfBoundsException exception) //If the text file isnt formatted correctly
        {
            System.err.println("Something went wrong reading the selected file!\n" +
                    "Please check that the correct file was selected!\n" +
                    "Program exiting....");
            System.exit(-1);
        }
    }
    //get the city names and return it
    public String[] getCityNames() {
        //create an array to store the city names
        String[] cityNames = new String[9];
        //loop through the destination array and get the city names
        for (int i = 0; i < destinationArray.length; i++) {
            cityNames[i] = destinationArray[i].getCityName();
        }
        Arrays.sort(cityNames); // sort names and return it
        return cityNames;
    }

    public int getRemainingMiles() {
        return this.milesRemaining;
    } //returns remaining miles

    public String[] redeemMiles(int miles, int month) {
        String[] trips = new String[20]; // used to store all place the user can go to
        int j = 0; // Used to keep track of size & current index of the trips array.
        ArrayList<Destination> firstClassList = new ArrayList<Destination>(); // used to store locations destination
                                                                              // to see if we can upgrade
        Boolean inList = false; // a bool to check if times the destination was already redeemed

        this.milesRemaining = miles; // set starting miles to a new var
        // While remaining miles is greater than the smallest supersaver mileage
        // location in the destinationArray
        while (milesRemaining >= destinationArray[destinationArray.length - 1].getSuperSaverMileage()) {
            // loop through the destinationArray.
            for (int i = 0; i < destinationArray.length; i++) {
                // if selected month falls in the range of a destination.
                if (month >= destinationArray[i].getOffSeasonlow() && month <= destinationArray[i].getOffSeasonHigh()) {
                    // check if the remaing miles is greater than or equal to the superSavermileage
                    // of current destination
                    if (milesRemaining >= destinationArray[i].getSuperSaverMileage()) {
                        // subtract the supersaver mileage from the remaing miles

                            milesRemaining -= destinationArray[i].getSuperSaverMileage();
                            // add destination to trips array
                            trips[j] = "* A trip to " + destinationArray[i].getCityName() + " in Economy Class ";
                            // add to firstClassList so we can check for any upgrades.
                            firstClassList.add(destinationArray[i]);
                            j++;


                    }
                }
            }
            // loop through destinationArray
            for (int i = 0; i < destinationArray.length; i++) {
                // if miles remaining is greater than the normalMiles
                if (milesRemaining >= destinationArray[i].getNormalMiles()) {
                    // then subtract the normal miles from miles remaing
                    for(Destination firstClassCheck : firstClassList)
                    {   //checks if the city name is already in the destination array
                        if(firstClassCheck.getCityName().equals(destinationArray[i].getCityName()))
                        {
                            inList = true;
                        }
                    }
                    if(inList == false) {
                        milesRemaining -= destinationArray[i].getNormalMiles();
                        // add it to the trips array
                        trips[j] = "* A trip to " + destinationArray[i].getCityName() + " in Economy Class ";
                        // add to firstClassList so we can check for any upgrades.
                        firstClassList.add(destinationArray[i]);
                        j++;
                    }
                    inList = false;
                }
            }
            // loop through the firstClassList
            for (int i = 0; i < firstClassList.size(); i++) {
                // if the remaining miles is greater than the upgrade cost for a already
                // selected destination
                if (milesRemaining >= firstClassList.get(i).getAdditionalMileage()) {
                    // subtract the milage
                    milesRemaining -= firstClassList.get(i).getAdditionalMileage();
                    // Replace its current entry in the trips array
                    trips[i] = "* A trip to " + firstClassList.get(i).getCityName() + " in First Class ";
                }
            }

            break; // break out of loop
        }
        return trips; // return array
    }
}
