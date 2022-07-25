/**********************************************************
 *                                                        *
 *  CSCI 470/502       Assignment 2       Summer 2022     *
 *                                                        *
 * Class   Name: Programming in Java                      *
 *                                                        *
 * Developer(s): Seraphina Nelson, Noah Flores            *
 *                                                        *
 *                                                        *
 *  Purpose:  This class models an invoice, storing       *
 *            the part number, description, quantity, and *
 *            the price per unit. It can also compute the *
 *            invoice amount.                             *
 **********************************************************/

public class Invoice {
    private String partNumber;
    private String partDescription;
    private int quantity;
    private double itemPrice;

    /**************************************************************
     * *
     * Invoice sets the part number, itemprice, quantity and *
     * description when created. *
     * *
     ***************************************************************/
    Invoice(String partNumber, String partDescription, int quantity, double itemPrice) {
        setPartNumber(partNumber);
        setItemPrice(itemPrice);
        setQuantity(quantity);
        setPartDescription(partDescription);
    }

    /**************************************************************
     * *
     * setPartNumber sets the part number on invoice. *
     * *
     ***************************************************************/

    public void setPartNumber(String partNumber) {

        this.partNumber = partNumber;
    }

    /**************************************************************
     * *
     * getPartNumber returns the part number on the invoice. *
     * *
     ***************************************************************/
    public String getPartNumber() {
        return partNumber;
    }

    /**************************************************************
     * *
     * setPartDescription sets description on invoice. *
     * *
     ***************************************************************/
    public void setPartDescription(String partDescription) {

        this.partDescription = partDescription;
    }

    /**************************************************************
     * *
     * getPartDescription returns description of item on the invoice.*
     * *
     ***************************************************************/
    public String getPartDescription() {

        return partDescription;
    }

    /**************************************************************
     * *
     * setQuantity sets the quantity of item on the invoice. *
     * *
     ***************************************************************/
    public void setQuantity(int quantity) {
        // if quantity is less than 0
        if (quantity < 0) {
            // set quantity to 0
            this.quantity = 0;
        } else {
            // else set quantity
            this.quantity = quantity;
        }
    }

    /**************************************************************
     * *
     * getQuantity returns the quantity of the item on the invoice. *
     * *
     ***************************************************************/
    public int getQuantity() {
        return quantity;
    }

    /**************************************************************
     * *
     * setItemPrice sets price of item on the invoice. *
     * *
     ***************************************************************/
    public void setItemPrice(double itemPrice) {
        // if item price is less than zero then
        if (itemPrice < 0.0) {
            // set it to 0
            this.itemPrice = 0.0;
        } else {
            // else set price
            this.itemPrice = itemPrice;
        }
    }

    /**************************************************************
     * *
     * getItemPrice gets the price of the item on the invoice. *
     * *
     ***************************************************************/
    public Double getItemPrice() {

        return itemPrice;
    }

    /**************************************************************
     * *
     * getInvoiceAmount returns the total invoice amount. *
     * *
     ***************************************************************/
    public Double getInvoiceAmount() {
        // Invoice total = itemprice * quantity of item
        return itemPrice * quantity;
    }

}