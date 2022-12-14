/*************************************************************
 *                                                           *
 *  CSCI 470/502       Assignment 4       Summer 2022        *
 *                                                           *
 * Class   Name: Programming in Java                         *
 *                                                           *
 * Developer(s): Seraphina Nelson, Noah Flores               *
 *                                                           *
 *  Purpose:  This class models a mile redeemer and provides *
 *            methods to generate arrays of available        *
 *            destinations and determine the best way to     *
 *            redeem a client's miles.                       *
 *                                                           *
 *************************************************************/

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import java.util.Comparator;
import java.util.Collections;


public class MileRedeemer {
    private int remainingMiles;
    private Destination[] destinations;

    // default constructor
    MileRedeemer(){
        remainingMiles = 0;
    }

    // this method returns the remaining miles
    public int getRemainingMiles(){
        return remainingMiles;
    }

    // this method reads destination records from a file and creates a sorted array of destination objects
    public void readDestinations(Scanner fileScanner) throws IOException {
        ArrayList<Destination> destinationList = new ArrayList<Destination>();

        // read each line from source file and split it into values, then create a destination and add it to the array
        while(fileScanner.hasNextLine()){
            String line = fileScanner.nextLine();
            String[] values = line.split(";");
            String[] offSeasonDates = values[values.length - 1].split("-");
            destinationList.add(new Destination(values[0],
                    Integer.parseInt(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3]),
                    Integer.parseInt(offSeasonDates[0]), Integer.parseInt(offSeasonDates[1])));
        }

        // sort destination list and store in the private instance variable
        Collections.sort(destinationList, new MileageComparator());
        this.destinations = (Destination[]) destinationList.toArray(new Destination[destinationList.size()]);
    }

    // used to sort Destination objects in descending order by their normalMiles property
    public class MileageComparator implements Comparator<Destination> {
        @Override
        public int compare(Destination d1, Destination d2) {
            return (d2.getNormalMiles() - d1.getNormalMiles());
        }
    }

    // this method creates an ArrayList of strings to store sorted city names
    public String[] getCityNames(){
        ArrayList<String> cityNamesList = new ArrayList<String>();

        for (Destination d : destinations) {
            cityNamesList.add(d.getDestinationCity());
        }

        // sort the city names in ascending order
        Collections.sort(cityNamesList);

        return (String[]) cityNamesList.toArray(new String[cityNamesList.size()]);
    }

    // this method computes the list of eligible destinations and options for upgrading to first class
    public String[] redeemMiles(int miles, int month){
        this.remainingMiles = miles;
        ArrayList<Destination> travelList = new ArrayList<Destination>();
        ArrayList<String> travelOptions = new ArrayList<String>();

        // for each destination, determine whether the client can redeem their miles for it and if so, add it
        // to the travel list
        for (Destination d : destinations) {
            int applicableMiles = getApplicableMiles(d, month);
            if (remainingMiles >= applicableMiles) {
                travelList.add(d);
                remainingMiles -= applicableMiles;
            }
        }

        // for each destination in the travel list, determine if the client can upgrade to first class based on the remaining miles
        for (Destination d : travelList){
            travelOptions.add(d.getDestinationCity());

            int upgradeMiles = d.getUpgrade();
            if (remainingMiles >= upgradeMiles){
                remainingMiles -= upgradeMiles;
                travelOptions.add("First Class");
            } else {
                travelOptions.add("Economy Class");
            }

        }

        // return the destinations and travel class array
        return (String[]) travelOptions.toArray(new String[travelOptions.size()]);
    }

    // this method determines which mileage to apply based on the travel month and the trip's off-season rate schedule
    public int getApplicableMiles(Destination d, int month){
        if (month >= d.getStartOffSeason() && month <= d.getEndOffSeason()){
            return d.getSuperSaver();
        } else {
            return d.getNormalMiles();
        }
    }
}


