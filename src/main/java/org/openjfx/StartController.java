package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.openjfx.bike.BikeManager;
import org.openjfx.components.Component;
import org.openjfx.components.ComponentManager;

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
    public void startNew(ActionEvent event) throws IOException {
        try {
            ParkShopApp.scene = new Scene(ParkShopApp.loadFXML("ComponentView"));
            ParkShopApp.window = new Stage();
            ParkShopApp.window.setTitle("Park Shop");
            ParkShopApp.window.setResizable(true);
            ParkShopApp.window.setScene(ParkShopApp.scene);
            ParkShopApp.window.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error Loadidng Component Scene");
        }

        // This is where we should split between new and load gamestate
        ParkShopApp.cmpManager = new ComponentManager();
        ParkShopApp.bkManager = new BikeManager();
    }

}
