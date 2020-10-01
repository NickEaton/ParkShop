package org.openjfx;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.openjfx.trail.RManager;
import org.openjfx.trail.Trail;

import java.io.IOException;
import java.util.logging.Level;

public class RaceSelectTrackController {

    public RManager myRaceManager;
    private Trail selection;
    @FXML private ScrollPane scrollContent;

    @FXML
    public VBox generateOption(Trail track) {
        VBox temp = new VBox();
        temp.setPrefWidth(394);
        temp.setPrefHeight(300);
        temp.setAlignment(Pos.CENTER);

        Text tName = new Text(track.getName());
        tName.setFont(new Font("System", 24));
        tName.setTextAlignment(TextAlignment.CENTER);

        Text difficulty = new Text(track.getRating().toString());
        difficulty.setFont(new Font("System", 24));
        difficulty.setTextAlignment(TextAlignment.CENTER);

        temp.getChildren().addAll(tName, difficulty);
        temp.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                clearStyle();
                temp.getStylesheets().add(ParkShopApp.class.getResource("Trail-Style.css").toString());
                temp.getStyleClass().add("insanityBorder");
                selection = track;
            }
        });

        return temp;
    }

    @FXML
    public void doLayout() {    // TODO: refine
        scrollContent.setContent(generateOption(myRaceManager.getTrack().get(0)));
    }

    public void clearStyle() { // TODO: refine
        for(Node xBox : ((VBox)scrollContent.getContent()).getChildren()) {
            xBox.getStyleClass().clear();
        }
    }

    // Hand off to BKImageSelect controller
    @FXML
    public void handoffRaceSelectRB() throws IOException {
        try {
            FXMLLoader fxload = new FXMLLoader(ParkShopApp.class.getResource("RaceSelectRB.fxml"));
            Parent root = fxload.load();
            RaceSelectRBController rbx = fxload.getController();
            this.myRaceManager.addTrack(selection);
            rbx.myRaceManager = this.myRaceManager;
            rbx.doLayout();

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
