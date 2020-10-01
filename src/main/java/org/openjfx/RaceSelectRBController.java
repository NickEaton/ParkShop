package org.openjfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.openjfx.trail.RManager;

import java.io.IOException;
import java.util.logging.Level;

public class RaceSelectRBController {

    public RManager myRaceManager;

    // Hand off to BKImageSelect controller
    @FXML
    public void handoffRaceResult() throws IOException {
        try {
            FXMLLoader fxload = new FXMLLoader(ParkShopApp.class.getResource("RaceResults.fxml"));
            Parent root = fxload.load();
            RaceResultController rrx = fxload.getController();
            rrx.myRaceManager = this.myRaceManager;

            Scene sub = new Scene(root);
            ParkShopApp.window = new Stage();
            ParkShopApp.window.setScene(sub);
            ParkShopApp.window.setTitle("Results");
            ParkShopApp.window.show();
        } catch (Exception e) {
            ParkShopApp.primaryLog.log(Level.SEVERE, "Error in RaceResults Controller handoff");
            e.printStackTrace();
        }
    }
}
