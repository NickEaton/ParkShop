package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.openjfx.bike.BikeManager;
import org.openjfx.bike.BikeObj;
import org.openjfx.components.ComponentManager;

import java.io.IOException;

public class ParkShopApp extends Application {

    static Scene scene;
    static Stage myStage;
    static ComponentManager cmpManager;
    static BikeManager bkManager;

    @Override
    public void start(Stage stage) throws Exception {
        try {
            scene = new Scene(loadFXML("Login"));
            stage.setTitle("Park Shop v0.0.1");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            myStage = stage;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    // This is for the initial loadup
    static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ParkShopApp.class.getResource(fxml+".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
