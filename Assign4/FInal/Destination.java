/**********************************************************
 *                                                        *
 *  CSCI 470/502       Assignment 4       Summer 2022     *
 *                                                        *
 * Class   Name: Programming in Java                      *
 *                                                        *
 * Developer(s): Seraphina Nelson, Noah Flores            *
 *                                                        *
 *                                                        *
 *  Purpose:  This class models destinations and includes *
 *            city, normal miles required, off-season     *
 *            required, upgrade to first class miles and  *
 *            the beginning and ending months of the      *
 *            off-season.                                 *
 *                                                        *
 **********************************************************/

public class Destination {
    // private instance variables
    private String destinationCity;
    private int normalMiles;
    private int superSaver;
    private int upgrade;
    private int startOffSeason;
    private int endOffSeason;

    // constructor
    public Destination(String destinationCity, int normalMiles, int superSaver, int upgrade, int startOffSeason, int endOffSeason){
        this.destinationCity = destinationCity;
        this.normalMiles = normalMiles;
        this.superSaver = superSaver;
        this.startOffSeason = startOffSeason;
        this.endOffSeason = endOffSeason;
        this.upgrade = upgrade;
    }

    // default constructor
    public Destination() { }

    // getters and setters for the Destination object's private instance variables
    public void setDestinationCity(String destinationCity){
        this.destinationCity = destinationCity;
    }

    public String getDestinationCity(){
        return destinationCity;
    }

    public void setNormalMiles(int normalMiles){
        this.normalMiles = normalMiles;
    }

    public int getNormalMiles(){
        return normalMiles;
    }

    public void setSuperSaver(int superSaver){
        this.superSaver = superSaver;
    }

    public int getSuperSaver(){
        return superSaver;
    }

    public void setStartOffSeason(int startOffSeason){
        this.startOffSeason = startOffSeason;
    }

    public int getStartOffSeason(){
        return startOffSeason;
    }

    public void setEndOffSeason(int endOffSeason){
        this.endOffSeason = endOffSeason;
    }

    public int getEndOffSeason(){
        return endOffSeason;
    }

    public void setUpgrade(int upgrade){
        this.upgrade = upgrade;
    }

    public int getUpgrade(){
        return upgrade;
    }
}
