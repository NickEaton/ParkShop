package org.openjfx;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.openjfx.entity.Rider;
import org.openjfx.entity.RiderManager;
import org.openjfx.util.Saveable;

import java.io.IOException;
import java.util.logging.Level;

public class RiderRecruitController implements Saveable {

    @FXML private ScrollPane contentPane;
    private RiderManager riderManager;

    @FXML private VBox getRiderRecruitBox(Rider subject) {
        VBox temp = new VBox();
        temp.setPrefHeight(300);
        temp.setPrefWidth(300);
        temp.setSpacing(25);

        Text name = new Text(subject.getRiderID());
        name.setWrappingWidth(275);
        name.setTextAlignment(TextAlignment.CENTER);
        name.setFont(new Font("System", 24));

        HBox XCBox = new HBox();
        HBox ENDBox = new HBox();
        HBox DHBox = new HBox();
        Text XCB = new Text("Cross: ");
        Text ENDB = new Text("Enduro: ");
        Text DHB = new Text("Downhill: ");
        ProgressBar XCBar = new ProgressBar();
        ProgressBar ENDBar = new ProgressBar();
        ProgressBar DHBar = new ProgressBar();
        XCBar.setProgress(subject.getFitness_XC());
        ENDBar.setProgress(subject.getFitness_END());
        DHBar.setProgress(subject.getFitness_DH());

        XCBox.getChildren().addAll(XCB, XCBar);
        ENDBox.getChildren().addAll(ENDB, ENDBar);
        DHBox.getChildren().addAll(DHB, DHBar);
        temp.getChildren().addAll(name, XCBox, ENDBox, DHBox);
        temp.setAlignment(Pos.CENTER);
        temp.getStylesheets().add(ParkShopApp.class.getResource("BKInventory.css").toString());
        temp.getStyleClass().add("bkborder");

        temp.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                // Popup to 'purchase' window
            }
        });

        return temp;
    }

    @FXML
    public void drawPane() throws IOException {
        VBox content = new VBox();
        int index = 0;
        int numPerLine = (int)contentPane.getPrefWidth()/325;
        int HboxCount = (int)Math.ceil((double)riderManager.getRiderList().size()/(double)numPerLine);
        if(HboxCount < 1) HboxCount = 1;

        for(int i=0; i<HboxCount; i++) {
            HBox lvl = new HBox();
            lvl.setAlignment(Pos.CENTER);
            lvl.setPrefHeight(300);
            lvl.setPrefWidth((int) contentPane.getPrefWidth());
            lvl.setSpacing(25);
            for(int k=0; k<numPerLine && index < riderManager.getRiderList().size(); k++) {
                lvl.getChildren().add(getRiderRecruitBox(this.riderManager.getRiderList().get(index++)));
                ParkShopApp.primaryLog.log(Level.INFO, "Added to Layout: " + riderManager.getRiderList().get(index-1));
            }
            content.getChildren().add(lvl);
        }
        contentPane.setContent(content);
    }
    
    @Override
    public void saveToFile() throws IOException {

    }
}
