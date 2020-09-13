package org.openjfx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.openjfx.bike.BikeManager;
import org.openjfx.components.Component;
import org.openjfx.components.ComponentManager;

import java.io.IOException;

// This class controls FXML I/O in the home screen of the application
public class StartController {

    // Switch to about display
    @FXML
    public void displayAbout() throws IOException {
        try {
            ParkShopApp.scene = new Scene(ParkShopApp.loadFXML("About"));
            ParkShopApp.window = new Stage();
            ParkShopApp.window.setTitle("About");
            ParkShopApp.window.setResizable(false);
            ParkShopApp.window.setScene(ParkShopApp.scene);
            ParkShopApp.window.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error in showing About scene");
        }
    }

    // Switch to settings display
    @FXML
    public void displaySettings() throws IOException {
        try {
            ParkShopApp.scene = new Scene(ParkShopApp.loadFXML("Settings"));
            ParkShopApp.window = new Stage();
            ParkShopApp.window.setTitle("Settings");
            ParkShopApp.window.setResizable(false);
            ParkShopApp.window.setScene(ParkShopApp.scene);
            ParkShopApp.window.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error is showing Settings scene");
        }
    }

    @FXML
    public void loadState(ActionEvent event) throws IOException {
        try {
            ((Stage)ParkShopApp.scene.getWindow()).close();
            ParkShopApp.scene = new Scene(ParkShopApp.loadFXML("ComponentView"));
            ParkShopApp.window = new Stage();
            ParkShopApp.window.setTitle("Park Shop");
            ParkShopApp.window.setResizable(true);
            ParkShopApp.window.setScene(ParkShopApp.scene);
            ParkShopApp.window.show();

            ParkShopApp.window.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    // Run exit protocol
                    Platform.exit();
                    System.exit(0);
                }
            });
        } catch(IOException e) {
            e.printStackTrace();
            System.err.println("Error in Load access to Component Scene");
        }

        // Initialize load gamestate
        ParkShopApp.cmpManager = new ComponentManager("ComponentManager");
        ParkShopApp.bkManager = new BikeManager("BikeManager");
    }

    // Initialize new managers, setup data, switch scene
    @FXML
    public void startNew(ActionEvent event) throws IOException {
        try {
            ((Stage)ParkShopApp.scene.getWindow()).close();
            ParkShopApp.scene = new Scene(ParkShopApp.loadFXML("ComponentView"));
            ParkShopApp.window = new Stage();
            ParkShopApp.window.setTitle("Park Shop");
            ParkShopApp.window.setResizable(true);
            ParkShopApp.window.setScene(ParkShopApp.scene);
            ParkShopApp.window.show();

            ParkShopApp.window.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    // Run exit protocol
                    Platform.exit();
                    System.exit(0);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error in New access to Component Scene");
        }

        // This is where we should split between new and load gamestate
        ParkShopApp.cmpManager = new ComponentManager();
        ParkShopApp.bkManager = new BikeManager();
    }

    // Return to Start screen from About or Settings
    @FXML void returnStart(ActionEvent event) throws IOException {
        try {
            ((Stage)ParkShopApp.scene.getWindow()).close();
            ParkShopApp.scene = new Scene(ParkShopApp.loadFXML("Login"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
