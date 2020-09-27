package org.openjfx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.openjfx.bike.BikeManager;
import org.openjfx.components.ComponentManager;
import org.openjfx.entity.Player;
import org.openjfx.util.Saveable;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParkShopApp extends Application implements Saveable {

    public static final Logger primaryLog = Logger.getLogger(ParkShopApp.class.getName());

    static Player player;

    static Scene scene;
    static Stage window;
    public static ComponentManager cmpManager;
    public static BikeManager bkManager;
    public Random rand;

    // This may be a really bad idea
    // static ScheduledExecutorService newComponentTimer;
    // static ScheduledExecutorService newEmployeeTimer;
    // static ArrayList<ScheduledExecutorService> buildUpTimers;

    @Override
    public void start(Stage stage) throws Exception {

        primaryLog.log(Level.INFO, "Launching Application");

        window = stage;
        player = new Player();
        rand = new Random();

        // Timer for new part generation
        // The longer the timer goes on, the better components should arise, so count iterations and build based off that.
        Timeline timerService = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cmpManager.addShopComponent(cmpManager.getNewRandComponent(ComponentManager.Part.values()[rand.nextInt(19)].toString().toLowerCase()));
            }
        }));
        timerService.setCycleCount(Timeline.INDEFINITE);
        timerService.play();

        try {
            primaryLog.log(Level.INFO, "Loading Login.fxml");
            scene = new Scene(loadFXML("Login"));
            stage.setTitle("Park Shop v0.0.1");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Crucial Error loading Login.fxml");
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

    @Override
    public void saveToFile() throws IOException {

    }
}
