package org.openjfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.openjfx.trail.RManager;

import java.io.IOException;
import java.util.logging.Level;

public class RaceSelectTrackController {

    public RManager myRaceManager;

    @FXML
    public void doLayout() {

    }

    // Hand off to BKImageSelect controller
    @FXML
    public void handoffRaceSelectRB() throws IOException {
        try {
            FXMLLoader fxload = new FXMLLoader(ParkShopApp.class.getResource("RaceSelectRB.fxml"));
            Parent root = fxload.load();
            RaceSelectRBController rbx = fxload.getController();
            rbx.myRaceManager = this.myRaceManager;

            Scene sub = new Scene(root);
            ParkShopApp.window = new Stage();
            ParkShopApp.window.setScene(sub);
            ParkShopApp.window.setTitle("Select Bike and Rider");
            ParkShopApp.window.show();
        } catch (Exception e) {
            ParkShopApp.primaryLog.log(Level.SEVERE, "Error in RaceSelectRB Controller handoff");
            e.printStackTrace();
        }
    }
}
