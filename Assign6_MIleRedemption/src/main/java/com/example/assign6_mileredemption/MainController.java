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
 *  Purpose: This is the main controller for the GUI.     *
 *  Contains methods to add function to the GUI           *
 *                                                        *
 *                                                        *
 **********************************************************/
package com.example.assign6_mileredemption;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainController {
    Scanner sc; // Stores scanner for selected file
    MileRedeemer mileRedeemer; // stores class object
    ArrayList<Destination> destinationList = new ArrayList<Destination>(); // stores the destination array
    enum Months{Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sept,Oct,Nov,Dec}; // enum that stores months

    @FXML
    private TextField normalMilesTextfield;
    @FXML
    private TextField superSaverMilesTextfield;
    @FXML
    private TextField upgradeMilesTextfield;
    @FXML
    private TextField superSaverDateTextfieldl;
    @FXML
    private TextField mileInputTextField;
    @FXML
    private TextField remainingMilesTextField;
    @FXML
    private Button redeemButton;
    @FXML
    private ComboBox<String> monthComboBox;
    @FXML
    private ListView<String> tripListview;
    @FXML
    private ListView<String> cityListView;
    //constructor for the main controller
    public MainController(Scanner scanner,MileRedeemer mileRedeemer){
        this.sc = scanner; //sets scanner
        this.mileRedeemer = mileRedeemer; // sets class object of mileRedeemer
    }
    //Used to show all the details of a selected city name on the cityListView list
    public void showDestinationDetails(MouseEvent e) throws InterruptedException{
        int temp; // temp int
        int firstMonth; //stores first month of the off season
        int lastMonth; //stores last month of the off season
        String tempString;
        String dateRange;

        destinationList = mileRedeemer.getDestinationList(); //get the destination list from mileRedeemer
        //Gets the index of normal miles of the selected city based off index of the city
        temp = destinationList.get(cityListView.getSelectionModel().getSelectedIndex()).getNormalMiles();
        tempString = Integer.toString(temp); //converts temp into a string
        normalMilesTextfield.setText(tempString); //sets tempstring to the normal miles text field
        //Gets the index of Supersavers miles of the selected city based off index of the city
        temp = destinationList.get(cityListView.getSelectionModel().getSelectedIndex()).getSuperSaverMileage();
        tempString = Integer.toString(temp);//converts temp into a string
        superSaverMilesTextfield.setText(tempString); //sets tempstring to the supersaver miles text field
        //Gets the index of upgrade miles of the selected city based off index of the city
        temp = destinationList.get(cityListView.getSelectionModel().getSelectedIndex()).getAdditionalMileage();
        tempString = Integer.toString(temp);//converts temp into a string
        upgradeMilesTextfield.setText(tempString); //sets tempstring to the upgrade miles text field
        //gets the first month of the off season for the selected city
        firstMonth = destinationList.get(cityListView.getSelectionModel().getSelectedIndex()).getOffSeasonlow();
        //gets the last month of the off season for the selected city
        lastMonth = destinationList.get(cityListView.getSelectionModel().getSelectedIndex()).getOffSeasonHigh();
        //format the string to show date range
        dateRange = Months.values()[firstMonth-1].toString() + "-" + Months.values()[lastMonth-1].toString();
        superSaverDateTextfieldl.setText(dateRange); //set formatted string to supersaver date text field
    }

    //initialize the combobox and city list when gui starts
    @FXML
    public void initialize() throws IOException{
        //pass the scanner to the read destination method
        mileRedeemer.readDestinations(this.sc);
        //loop through and set fill the month combo box
        for(int i = 0; i < 12; i++)
        {
            monthComboBox.getItems().add(Months.values()[i].toString());
        }
        monthComboBox.getSelectionModel().select(0);
        String[] cityNames = new String[12];
        //get city names
        cityNames = mileRedeemer.getCityNames();
        //loop through and populate city list on the GUI
        for(int i = 0; i < cityNames.length; i++)
        {
            //array index is null then break
            if(cityNames[i] == null) {
                break;
            }

            cityListView.getItems().add(cityNames[i]);
        }


    }
    public void redeemMileButtonClick() {
        //clear out trip view if anything is in it
        tripListview.getItems().clear();
        //clear out remaining mile field
        remainingMilesTextField.clear();
        //If the user mile input field is not null
        if (mileInputTextField.getText() != null) {
            try {

                int miles = Integer.parseInt(mileInputTextField.getText()); // get the user inputted miles
                //get all trips based off selected month and miles entered
                String[] tripResults = mileRedeemer.redeemMiles(miles, monthComboBox.getSelectionModel().getSelectedIndex() - 1);
                //if trips array is empty
                if (tripResults[0] == null) {
                    tripListview.getItems().add("Your client has not accumulated enough Frequent Flyer Miles");
                } else {
                    tripListview.getItems().add("Your client's Frequent Flyer Miles can be used to redeem the following tickets:");
                    //else loop and add all the trips to the trip list
                    for (String trip : tripResults) {
                        if (trip != null)
                            tripListview.getItems().add(trip);
                    }
                    //get the remaining miles
                    miles = mileRedeemer.getRemainingMiles();
                }
                //set remaining miles
                remainingMilesTextField.setText(Integer.toString(miles));
            }
            catch (NumberFormatException exception) // if user puts in bad input for the miles
            {
                System.out.println("Bad Input by user" + exception.getMessage());
                return;
            }
        }
    }

}