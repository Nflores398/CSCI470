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
 *  Purpose: Used to compare two Destination objects      *
 *  to sort the Destination array                         *
 *                                                        *
 *                                                        *
 **********************************************************/
package com.example.assign6_mileredemption;
import java.util.Comparator;
//Used to sort the destinations by normal miles
public class MileageComparator implements Comparator<Destination> {
    @Override
    public int compare(Destination d1, Destination d2) {
        return (d2.getNormalMiles() - d1.getNormalMiles());
    }

}
