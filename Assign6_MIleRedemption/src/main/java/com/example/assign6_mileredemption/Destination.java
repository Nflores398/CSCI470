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
 *  Purpose: This Class file creates a destination the    *
 *  user can go to. It stores Name, miles, and off season *
 *  The class file contains methods to get all its        *
 *  data vales                                            *
 *                                                        *
 *                                                        *
 **********************************************************/
package com.example.assign6_mileredemption;
public class Destination {
    String cityName;
    int normalMileage;
    int superSaverMileage;
    int additionalMileage;
    int offSeasonlow;
    int offSeasonHigh;
    // Destination constructor that sets city name, normal miles, super saver miles, upgrade miles, and off season
    //months.
    Destination(String name, int mileage, int superSaver, int additionalMileage, int offSeasonlower,
            int offSeasonHigh) {
        this.cityName = name;
        this.normalMileage = mileage;
        this.superSaverMileage = superSaver;
        this.additionalMileage = additionalMileage;
        this.offSeasonlow = offSeasonlower;
        this.offSeasonHigh = offSeasonHigh;
    }

    //returns city name
    public String getCityName()
    {
        return this.cityName;
    }
    //returns the normal miles
    public int getNormalMiles() {
        return this.normalMileage;
    }
    //returns the super saver miles
    public int getSuperSaverMileage() {
        return this.superSaverMileage;
    }
    //returns the upgrade miles
    public int getAdditionalMileage() {
        return this.additionalMileage;
    }
    //returns the first month of the off season
    public int getOffSeasonlow() {
        return this.offSeasonlow;
    }
    //retunrs the last month of the off season
    public int getOffSeasonHigh() {
        return this.offSeasonHigh;
    }
}
