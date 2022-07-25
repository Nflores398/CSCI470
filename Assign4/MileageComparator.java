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
import java.util.Comparator;

public class MileageComparator implements Comparator<Destination> {
    @Override
    public int compare(Destination d1, Destination d2) {
        return (d2.getNormalMiles() - d1.getNormalMiles());
    }

}
