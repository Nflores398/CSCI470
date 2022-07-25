/**********************************************************
 *                                                        *
 *  CSCI 470/502       Assignment 4       Summer 2022     *
 *                                                        *
 * Class   Name: Programming in Java                      *
 *                                                        *
 * Developer(s): Seraphina Nelson, Noah Flores            *
 *                                                        *
 * Due Date: 07/08/2022                                   *
 *                                                        *
 *  Purpose:                                              *
 *                                                        *
 *                                                        *
 **********************************************************/
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class MileRedeemer {
    ArrayList<Destination> destinationList = new ArrayList<Destination>();
    Destination[] destinationArray;
    int milesRemaining;

    public void readDestinations(Scanner fileScanner) {
        String input;
        String[] splitString;
        String[] offSeason;
        while (fileScanner.hasNextLine()) {
            input = fileScanner.nextLine();

            splitString = input.split(";");
            offSeason = splitString[4].split("-");
            Destination destination = new Destination(splitString[0], Integer.parseInt(splitString[1]),
                    Integer.parseInt(splitString[2]), Integer.parseInt(splitString[3]), Integer.parseInt(offSeason[0]),
                    Integer.parseInt(offSeason[1]));

            destinationList.add(destination);
        }
        MileageComparator mileageComparator = new MileageComparator();
        destinationArray = (Destination[]) destinationList
                .toArray(new Destination[destinationList.size()]);
        Arrays.sort(destinationArray, mileageComparator);
        return;
    }

    public String[] getCityNames() {
        String[] cityNames = new String[9];
        for (int i = 0; i < destinationArray.length; i++) {
            cityNames[i] = destinationArray[i].getCityName();
        }
        Arrays.sort(cityNames);
        return cityNames;
    }

    public int getRemainingMiles() {
        return this.milesRemaining;
    }

    public String[] redeemMiles(int miles, int month) {
        String[] trips = new String[20]; // used to store all place the user can go to
        int j = 0; // Used to keep track of size & current index of the trips array.
        ArrayList<Destination> firstClassList = new ArrayList<Destination>(); // used to store locations destination
                                                                              // to see if we can upgrade

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
                    milesRemaining -= destinationArray[i].getNormalMiles();
                    // add it to the trips array
                    trips[j] = "* A trip to " + destinationArray[i].getCityName() + " in Economy Class ";
                    // add to firstClassList so we can check for any upgrades.
                    firstClassList.add(destinationArray[i]);
                    j++;
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
