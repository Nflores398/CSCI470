/**********************************************************
 *                                                        *
 *  CSCI 470/502       Assignment 3       Summer 2022     *
 *                                                        *
 * Class   Name: Programming in Java                      *
 *                                                        *
 * Developer(s): Seraphina Nelson, Noah Flores            *
 *                                                        *
 *                                                        *
 *  Purpose:  This class calculates the tip and how much  *
 *            each person in a group should pay.          *
 *                                                        *
 *                                                        *
 **********************************************************/


public class TipCalculator {

    private double billAmount = 0.0;
    private int tipPercentage = 20;
    private int partySize = 1;


    // default constructor
    TipCalculator(){}

    // declaring setters and getter for instance variables
    public void setBillAmount( double billAmount ) {
        this.billAmount = billAmount;
    }

    // return bill amount
    public double getBillAmount() {
        return billAmount;
    }

    //set tip percentage
    public void setTipPercentage( int tipPercentage ) {
        this.tipPercentage = tipPercentage;
    }

    // return tip percentage
    public int getTipPercentage() {
        return tipPercentage;
    }

    // set party size
    public void setPartySize( int partySize ) {
        this.partySize = partySize;
    }

    // return party size
    public int getPartySize() {
        return partySize;
    }

    // return total bill
    public double getTotalBill() {
        double tip = billAmount * ( tipPercentage / 100.0 );
        return billAmount + tip;
    }

     // return individual share
    public double getIndividualShare() {
        return this.getTotalBill() / partySize;
    }



}
