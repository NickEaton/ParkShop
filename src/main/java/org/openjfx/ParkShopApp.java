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

import java.io.IOException;
import java.util.Random;

public class ParkShopApp extends Application {

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
        window = stage;
        cmpManager = new ComponentManager();
        bkManager = new BikeManager();
        player = new Player();
        rand = new Random();

        //TimerService ts = new TimerService(cmpManager);
        Timeline timerService = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cmpManager.addShopComponent(cmpManager.getNewRandComponent(ComponentManager.Part.values()[rand.nextInt(19)].toString().toLowerCase()));
            }
        }));
        timerService.setCycleCount(Timeline.INDEFINITE);
        timerService.play();

        try {
            scene = new Scene(loadFXML("Login"));
            stage.setTitle("Park Shop v0.0.1");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
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
