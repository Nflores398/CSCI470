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
 *  Purpose: This program lets the user select a file,    *
 *  enter the amount of miles they have, and see what     *
 *  destinations they can go to based off their miles.    *
 *  THis program provides the user with GUI to perform    *
 *  these actions.                                        *
 *                                                        *
 *                                                        *
 **********************************************************/
package com.example.assign6_mileredemption;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MileRedeemerApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FileChooser f = new FileChooser(); //create file chooser
        File MyFile;
            //creates a filters that only allows .txt files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("text", "*.txt");
            f.getExtensionFilters().add(extFilter);
            f.setTitle("Opening the location...");
            MyFile = f.showOpenDialog(stage); // show file chooser
        if(MyFile == null) //if user doesnt enter as file/closes file chooser
        {
            System.err.println("Opening File error has occured.\nExiting....");
            System.exit(-1);
        }
        HBox r = new HBox();
        r.setSpacing(20);
        Scene sc = new Scene(r,350,100);

        stage.setScene(sc);
        stage.setTitle("Sample file chooser");
        stage.show();
        //create a scanner with selected file
        Scanner scanner = new Scanner(MyFile);
        MileRedeemer mileRedeemer = new MileRedeemer(); // call mileRedeemer constructor
        MainController controller = new MainController(scanner,mileRedeemer); // call the the main controller and
        //pass the the scanner and milerRedeemer object

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MileApp.fxml"));
        fxmlLoader.setController(controller);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 978, 481);
        stage.setTitle("Mile Redemption App");
        stage.setScene(scene);

        stage.show(); //show GUI for progream

    }


    public static void main(String[] args) {
        launch();
    }
}