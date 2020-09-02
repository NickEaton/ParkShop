package org.openjfx;

import javafx.fxml.FXML;

import java.io.IOException;

// This class controls FXML I/O in the home screen of the application
public class StartController {

    @FXML
    public void displayAbout() throws IOException {
        System.out.println("This method will display a new scene with a short description about this program!");
    }

    @FXML
    public void displaySettings() throws IOException {
        System.out.println("This method will allow the user to change some basic settings/configurations");
    }

    @FXML
    public void loadState() throws IOException {
        System.out.println("This method will context-switch to main runtime threads and use load Contructors to recreate prior gamestate");
    }

    // Initialize new managers, setup data, switch scene
    @FXML
    public void startNew() throws IOException {
        // System.out.println("This method will context-switch to main runtime threads and start a new game instance from main-default");
        ParkShopApp.loadNew();
        ParkShopApp.setRoot("PrimaryController");
    }

}
