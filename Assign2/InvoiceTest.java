/**********************************************************
 *                                                        *
 *  CSCI 470/502       Assignment 2       Summer 2022     *
 *                                                        *
 *  Developer(s): Seraphina Nelson, Noah Flores           *
 *                                                        *
 *  Due Date: 06/24/2022                                  *
 *                                                        *
 *  Purpose:  This class tests the invoice class.         *
 *                                                        *
 **********************************************************/

import java.util.ArrayList;
import java.text.DecimalFormat;

public class InvoiceTest {
    public static void main(String[] args) {
        // set number format for invoice amount
        String pattern = "$##,###,###.00";
        DecimalFormat invoiceAmount = new DecimalFormat(pattern);

        // declare arraylist to hold invoices
        ArrayList<Invoice> invoiceList = new ArrayList<>();

        // create five invoices
        Invoice myInvoice1 = new Invoice("AB-23-4312", "Cordless Drill", 10, 189.08);
        invoiceList.add(myInvoice1);

        Invoice myInvoice2 = new Invoice("AB-23-4313", "Hammer", 15, 95.00);
        invoiceList.add(myInvoice2);

        Invoice myInvoice3 = new Invoice("AB-23-4314", "Screwdriver", 20, 150.37);
        invoiceList.add(myInvoice3);

        Invoice myInvoice4 = new Invoice("AB-23-4315", "Light Switch", 30, 200.72);
        invoiceList.add(myInvoice4);

        Invoice myInvoice5= new Invoice("AB-23-4316", "Carpenter's Square", 45, 50.49);
        invoiceList.add(myInvoice5);

        // Print each invoice in the array list
        for (int i = 0; i < 5; i++) {
            System.out.printf("Invoice #%d%n%n", i + 1);
            System.out.printf("        Part No.:  %s%n%n", invoiceList.get(i).getPartNumber());
            System.out.printf("      Item Desc.:  %s%n%n",invoiceList.get(i).getPartDescription());
            System.out.printf("        Quantity:  %s%n%n",invoiceList.get(i).getQuantity());
            System.out.printf("      Item Price:  %.2f%n%n",invoiceList.get(i).getItemPrice());
            System.out.printf("Invoice Subtotal:            %s%n%n",invoiceAmount.format(invoiceList.get(i).getInvoiceAmount()));
            System.out.println("**************************************\n");
        }
    }
}
