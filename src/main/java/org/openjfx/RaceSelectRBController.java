package org.openjfx;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.openjfx.bike.BikeObj;
import org.openjfx.entity.Rider;
import org.openjfx.trail.RManager;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;

public class RaceSelectRBController {

    public RManager myRaceManager;
    @FXML private ScrollPane riderScrollContent;
    @FXML private ScrollPane bikeScrollContent;

    private Rider selRider;
    private BikeObj selBike;

    private void clearStyle(ScrollPane target) {
        for(Node n : ((VBox)target.getContent()).getChildren()) {
            n.getStyleClass().clear();
        }
    }

    @FXML
    public VBox genRiderBox(Rider rider) {
        VBox temp = new VBox();
        temp.setPrefHeight(200);
        temp.setPrefWidth(394);
        temp.setAlignment(Pos.CENTER);

        Text name = new Text(rider.getRiderID());
        name.setTextAlignment(TextAlignment.CENTER);
        name.setFont(new Font("System", 24));

        HBox starBox = new HBox();
        starBox.setAlignment(Pos.CENTER);
        starBox.setMinWidth(300);
        ImageView starIm = new ImageView();
        Path pathToStar = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString()+
                "\\src\\main\\resources\\org\\images\\star-1.png");
        try (InputStream in = new BufferedInputStream(new FileInputStream(pathToStar.toString()))){
            starIm = new ImageView(new Image(in));
        } catch(IOException e) {
            ParkShopApp.primaryLog.log(Level.SEVERE, "Error Loading Star-1 Image");
            e.printStackTrace();
        }
        for(int a=0; a<rider.getLevel(); a++)
            starBox.getChildren().add(new ImageView(starIm.getImage()));

        temp.getChildren().addAll(name, starBox);
        temp.setSpacing(20);
        temp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clearStyle(riderScrollContent);
                temp.getStylesheets().add(ParkShopApp.class.getResource("Trail-Style.css").toString());
                temp.getStyleClass().add("insanityBorder");
                selRider = rider;
            }
        });

        return temp;
    }

    @FXML
    public VBox genBikeBox(BikeObj bike) {
        VBox temp = new VBox();
        temp.setPrefHeight(400);
        temp.setPrefWidth(394);
        temp.setAlignment(Pos.CENTER);

        Text name = new Text(bike.getBikeName());
        name.setTextAlignment(TextAlignment.CENTER);
        name.setFont(new Font("System", 24));

        HBox starBox = new HBox();
        starBox.setAlignment(Pos.CENTER);
        starBox.setMinWidth(300);
        ImageView starIm = new ImageView();
        Path pathToStar = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString()+
                "\\src\\main\\resources\\org\\images\\star-1.png");
        try (InputStream in = new BufferedInputStream(new FileInputStream(pathToStar.toString()))){
            starIm = new ImageView(new Image(in));
        } catch(IOException e) {
            ParkShopApp.primaryLog.log(Level.SEVERE, "Error Loading Star-1 Image");
            e.printStackTrace();
        }
        for(int a=0; a<bike.getRating(); a++)
            starBox.getChildren().add(new ImageView(starIm.getImage()));

        ImageView bikeIm = new ImageView();
        bikeIm.setImage(bike.bikePic);

        temp.getChildren().addAll(name, starBox, bikeIm);
        temp.setSpacing(20);
        temp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clearStyle(bikeScrollContent);
                temp.getStylesheets().add(ParkShopApp.class.getResource("Trail-Style.css").toString());
                temp.getStyleClass().add("insanityBorder");
                selBike = bike;
            }
        });

        return temp;
    }

    public void doLayout() {
        VBox rContent = new VBox();
        VBox bContent = new VBox();
        for(Rider r : ParkShopApp.rManager.getRiderList()) {
            rContent.getChildren().add(genRiderBox(r));
        }
        riderScrollContent.setContent(rContent);

        for(BikeObj b : ParkShopApp.bkManager.BKList) {
            bContent.getChildren().add(genBikeBox(b));
        }
        bikeScrollContent.setContent(bContent);
    }

    // Hand off to BKImageSelect controller
    @FXML
    public void handoffRaceResult() throws IOException {
        try {
            FXMLLoader fxload = new FXMLLoader(ParkShopApp.class.getResource("RaceResults.fxml"));
            Parent root = fxload.load();
            RaceResultController rrx = fxload.getController();
            rrx.myRaceManager = this.myRaceManager;
            rrx.doSimpleRace();

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
