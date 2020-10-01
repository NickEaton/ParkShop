package org.openjfx;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.openjfx.bike.BikeObj;
import org.openjfx.entity.Rider;
import org.openjfx.trail.RManager;
import org.openjfx.util.PSUtilities;

import java.io.IOException;
import java.util.ArrayList;

public class RaceResultController {

    private ArrayList<Rider> placings;
    public RManager myRaceManager;
    @FXML private ScrollPane contentPane;

    @FXML
    public HBox genRBox(int placement, Rider competitor) {
        HBox temp = new HBox();
        temp.setAlignment(Pos.CENTER);
        temp.setSpacing(20);

        Text name = new Text(competitor.getRiderID());
        name.setFont(new Font("System", 18));
        name.setTextAlignment(TextAlignment.CENTER);

        Text place = new Text(""+placement);
        place.setFont(new Font("System", 24));
        place.setTextAlignment(TextAlignment.CENTER);

        temp.getChildren().addAll(place, name);

        return temp;
    }

    @FXML
    public void doLayout() {
        VBox content = new VBox();
        for(int i=0; i<placings.size(); i++) {
            content.getChildren().add(genRBox(i+1, placings.get(i)));
        }
        contentPane.setContent(content);
    }

    public void doSimpleRace() throws IOException {
        for(int i=0; i<5; i++) {
            Rider x = ParkShopApp.rManager.genNewRider(PSUtilities.flopNewName(), 3);
            BikeObj b = new BikeObj(PSUtilities.flopNewName(), x, ParkShopApp.cmpManager.genCompFullSet());
            x.getOwnedBikes().add(b);
            x.setActiveBike(b);
            myRaceManager.addCompetitor(x);
        }

        placings = myRaceManager.doSimpleRace();
        this.doLayout();
    }
}
