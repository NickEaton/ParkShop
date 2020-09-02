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

    private static Scene scene;
    private ComponentManager usrCompManage;
    private BikeManager usrBikeManage;

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML("Login"));
        stage.setTitle("Park Shop v0.0.1 Pre-Alpha");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ParkShopApp.class.getResource(fxml+".fxml"));
        return fxmlLoader.load();
    }

    // Try to read from manager .prop files
    public static void loadFromState() {

    }

    // Create new game state
    public static void loadNew() {

    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
