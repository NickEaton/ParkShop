package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

// This is the FXML controller for BikeDetail.fxml, which is a subwindow showing what parts you have in queue
// on the bike which is currently being constructed
public class DetailController {

    // Boxes which contain the 'star' ratings to simplify how good a given part is
    @FXML private HBox frameStar;
    @FXML private HBox forkStar;
    @FXML private HBox shockStar;
    @FXML private HBox wheelFStar;
    @FXML private HBox wheelRStar;
    @FXML private HBox tireFStar;
    @FXML private HBox tireRStar;
    @FXML private HBox brakeFStar;
    @FXML private HBox brakeRStar;
    @FXML private HBox rotorFStar;
    @FXML private HBox rotorRStar;
    @FXML private HBox cassetteStar;
    @FXML private HBox chainringStar;
    @FXML private HBox chainStar;
    @FXML private HBox derailleurStar;
    @FXML private HBox cranksStar;
    @FXML private HBox pedalsStar;
    @FXML private HBox seatpostStar;
    @FXML private HBox seatStar;
    @FXML private HBox handlebarStar;
    @FXML private HBox shifterStar;
    @FXML private HBox brakeLeverStar;
    @FXML private HBox gripsStar;

    // The names of each part
    @FXML private Text frameName;
    @FXML private Text forkName;
    @FXML private Text shockName;
    @FXML private Text wheelFName;
    @FXML private Text wheelRName;
    @FXML private Text tireFName;
    @FXML private Text tireRName;
    @FXML private Text brakeFName;
    @FXML private Text brakeRName;
    @FXML private Text rotorFName;
    @FXML private Text rotorRName;
    @FXML private Text cassetteName;
    @FXML private Text chainringName;
    @FXML private Text chainName;
    @FXML private Text derailleurName;
    @FXML private Text cranksName;
    @FXML private Text pedalsName;
    @FXML private Text seatpostName;
    @FXML private Text seatName;
    @FXML private Text handlebarName;
    @FXML private Text shifterName;
    @FXML private Text brakeLeverName;
    @FXML private Text gripsName;

    // The name of the ride
    @FXML private Text bikeName;

    @FXML private ImageView dashIm;
    @FXML private ImageView starIm;

    // private method to shallow copy an ImageView, saving width and height
    @FXML
    private ImageView shallowCopy(ImageView target) {
        ImageView result = new ImageView(target.getImage());
        result.setFitWidth(target.getFitWidth());
        result.setFitHeight(target.getFitHeight());
        return result;
    }

    // Regex utility for display names
    public static String regexParse(String target, String regex) {
        String[] temp = target.split(regex);
        String res = new String();
        for(String s : temp)
            res += s + " ";
        return res;
    }

    // Draw current bike name
    @FXML
    public void drawName(TextField nameParse) {
        this.bikeName.setText(nameParse.getText());
    }

    // Draw display names for bike detail construction view
    @FXML
    public void subUpdateBikeDetail() {
        frameName.setText((ParkShopApp.bkManager.activeFrame == null) ? "-" : regexParse(ParkShopApp.bkManager.activeFrame.getDisplayName(), "_"));
        forkName.setText((ParkShopApp.bkManager.activeFork == null) ? "-" : regexParse(ParkShopApp.bkManager.activeFork.getDisplayName(), "_"));
        shockName.setText((ParkShopApp.bkManager.activeShock == null) ? "-" : regexParse(ParkShopApp.bkManager.activeShock.getDisplayName(), "_"));
        wheelFName.setText((ParkShopApp.bkManager.activeWheelF == null) ? "-" : regexParse(ParkShopApp.bkManager.activeWheelF.getDisplayName(), "_"));
        wheelRName.setText((ParkShopApp.bkManager.activeWheelR == null) ? "-" : regexParse(ParkShopApp.bkManager.activeWheelR.getDisplayName(), "_"));
        tireFName.setText((ParkShopApp.bkManager.activeTireF == null) ? "-" : regexParse(ParkShopApp.bkManager.activeTireF.getDisplayName(), "_"));
        tireRName.setText((ParkShopApp.bkManager.activeTireR == null) ? "-" : regexParse(ParkShopApp.bkManager.activeTireR.getDisplayName(), "_"));
        brakeFName.setText((ParkShopApp.bkManager.activeBrakeF == null) ? "-" : regexParse(ParkShopApp.bkManager.activeBrakeF.getDisplayName(), "_"));
        brakeRName.setText((ParkShopApp.bkManager.activeBrakeR == null) ? "-" : regexParse(ParkShopApp.bkManager.activeBrakeR.getDisplayName(), "_"));
        rotorFName.setText((ParkShopApp.bkManager.activeRotorF == null) ? "-" : regexParse(ParkShopApp.bkManager.activeRotorF.getDisplayName(), "_"));
        rotorRName.setText((ParkShopApp.bkManager.activeRotorR == null) ? "-" : regexParse(ParkShopApp.bkManager.activeRotorR.getDisplayName(), "_"));
        cassetteName.setText((ParkShopApp.bkManager.activeCassette == null) ? "-" : regexParse(ParkShopApp.bkManager.activeCassette.getDisplayName(), "_"));
        chainringName.setText((ParkShopApp.bkManager.activeChainring == null) ? "-" : regexParse(ParkShopApp.bkManager.activeChainring.getDisplayName(), "_"));
        chainName.setText((ParkShopApp.bkManager.activeChain == null) ? "-" : regexParse(ParkShopApp.bkManager.activeChain.getDisplayName(), "_"));
        derailleurName.setText((ParkShopApp.bkManager.activeDerailleur == null) ? "-" : regexParse(ParkShopApp.bkManager.activeDerailleur.getDisplayName(), "_"));
        cranksName.setText((ParkShopApp.bkManager.activeCranks == null) ? "-" : regexParse(ParkShopApp.bkManager.activeCranks.getDisplayName(), "_"));
        pedalsName.setText((ParkShopApp.bkManager.activePedals == null) ? "-" : regexParse(ParkShopApp.bkManager.activePedals.getDisplayName(), "_"));
        seatpostName.setText((ParkShopApp.bkManager.activeSeatpost == null) ? "-" : regexParse(ParkShopApp.bkManager.activeSeatpost.getDisplayName(), "_"));
        seatName.setText((ParkShopApp.bkManager.activeSeat == null) ? "-" : regexParse(ParkShopApp.bkManager.activeSeat.getDisplayName(), "_"));
        handlebarName.setText((ParkShopApp.bkManager.activeHandlebar == null) ? "-" : regexParse(ParkShopApp.bkManager.activeHandlebar.getDisplayName(), "_"));
        shifterName.setText((ParkShopApp.bkManager.activeShifter == null) ? "-" : regexParse(ParkShopApp.bkManager.activeShifter.getDisplayName(), "_"));
        brakeLeverName.setText((ParkShopApp.bkManager.activeBrakeLever == null) ? "-" : regexParse(ParkShopApp.bkManager.activeBrakeLever.getDisplayName(), "_"));
        gripsName.setText((ParkShopApp.bkManager.activeGrips == null) ? "-" : regexParse(ParkShopApp.bkManager.activeGrips.getDisplayName(), "_"));
    }

    // Draw the appropriate quantity of stars and dashes next to
    // the corresponding components
    @FXML
    public void subUpdateBikeDetailStars() {
        Path pathToDash = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString()+
                "\\src\\main\\resources\\org\\images\\black-line.png");
        try (InputStream in = new BufferedInputStream(new FileInputStream(pathToDash.toString()))){
            dashIm = new ImageView(new Image(in));
        } catch(IOException e) {
            e.printStackTrace();
        }
        Path pathToStar = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString()+
                "\\src\\main\\resources\\org\\images\\star-1.png");
        try (InputStream in = new BufferedInputStream(new FileInputStream(pathToStar.toString()))){
            starIm = new ImageView(new Image(in));
        } catch(IOException e) {
            e.printStackTrace();
        }
        int i;

        frameStar.getChildren().clear();
        starIm.setFitHeight(25);
        starIm.setFitWidth(25);
        dashIm.setFitWidth(31);
        dashIm.setFitHeight(31);

        if(ParkShopApp.bkManager.activeFrame != null) {
            for(i=0; i<=ParkShopApp.bkManager.activeFrame.getPartLevel(); i++)
                frameStar.getChildren().add(shallowCopy(starIm));
        } else frameStar.getChildren().add(shallowCopy(dashIm));
        shockStar.getChildren().clear();
        if(ParkShopApp.bkManager.activeShock != null) {
            for(i=0; i<ParkShopApp.bkManager.activeShock.getPartLevel(); i++)
                shockStar.getChildren().add(shallowCopy(starIm));
        } else shockStar.getChildren().add(shallowCopy(dashIm));
        forkStar.getChildren().clear();
        if(ParkShopApp.bkManager.activeFork != null) {
            for(i=0; i<ParkShopApp.bkManager.activeFork.getPartLevel(); i++)
                forkStar.getChildren().add(shallowCopy(starIm));
        } else forkStar.getChildren().add(shallowCopy(dashIm));
        wheelFStar.getChildren().clear();
        if(ParkShopApp.bkManager.activeWheelF != null) {
            for(i=0; i<ParkShopApp.bkManager.activeWheelF.getPartLevel(); i++)
                wheelFStar.getChildren().add(shallowCopy(starIm));
        } else wheelFStar.getChildren().add(shallowCopy(dashIm));
        wheelRStar.getChildren().clear();
        if(ParkShopApp.bkManager.activeWheelR != null) {
            for(i=0; i<ParkShopApp.bkManager.activeWheelR.getPartLevel(); i++)
                wheelRStar.getChildren().add(shallowCopy(starIm));
        } else wheelRStar.getChildren().add(shallowCopy(dashIm));
        tireFStar.getChildren().clear();
        if(ParkShopApp.bkManager.activeTireF != null) {
            for(i=0; i<ParkShopApp.bkManager.activeTireF.getPartLevel(); i++)
                tireFStar.getChildren().add(shallowCopy(starIm));
        } else tireFStar.getChildren().add(shallowCopy(dashIm));
        tireRStar.getChildren().clear();
        if(ParkShopApp.bkManager.activeTireR != null) {
            for(i=0; i<ParkShopApp.bkManager.activeTireR.getPartLevel(); i++)
                tireRStar.getChildren().add(shallowCopy(starIm));
        } else tireRStar.getChildren().add(shallowCopy(dashIm));
        brakeFStar.getChildren().clear();
        if(ParkShopApp.bkManager.activeBrakeF != null) {
            for(i=0; i<ParkShopApp.bkManager.activeBrakeF.getPartLevel(); i++)
                brakeFStar.getChildren().add(shallowCopy(starIm));
        } else brakeFStar.getChildren().add(shallowCopy(dashIm));
        brakeRStar.getChildren().clear();
        if(ParkShopApp.bkManager.activeBrakeR != null) {
            for(i=0; i<ParkShopApp.bkManager.activeBrakeR.getPartLevel(); i++)
                brakeRStar.getChildren().add(shallowCopy(starIm));
        } else brakeRStar.getChildren().add(shallowCopy(dashIm));
        rotorFStar.getChildren().clear();
        if(ParkShopApp.bkManager.activeRotorF != null) {
            for(i=0; i<ParkShopApp.bkManager.activeRotorF.getPartLevel(); i++)
                rotorFStar.getChildren().add(shallowCopy(starIm));
        } else rotorFStar.getChildren().add(shallowCopy(dashIm));
        rotorRStar.getChildren().clear();
        if(ParkShopApp.bkManager.activeRotorR != null) {
            for(i=0; i<ParkShopApp.bkManager.activeRotorR.getPartLevel(); i++)
                rotorRStar.getChildren().add(shallowCopy(starIm));
        } else rotorRStar.getChildren().add(shallowCopy(dashIm));
        cassetteStar.getChildren().clear();
        if(ParkShopApp.bkManager.activeCassette != null) {
            for(i=0; i<ParkShopApp.bkManager.activeCassette.getPartLevel(); i++)
                cassetteStar.getChildren().add(shallowCopy(starIm));
        } else cassetteStar.getChildren().add(shallowCopy(dashIm));
        chainringStar.getChildren().clear();
        if(ParkShopApp.bkManager.activeChainring != null) {
            for(i=0; i<ParkShopApp.bkManager.activeChainring.getPartLevel(); i++)
                chainringStar.getChildren().add(shallowCopy(starIm));
        } else chainringStar.getChildren().add(shallowCopy(dashIm));
        chainStar.getChildren().clear();
        if(ParkShopApp.bkManager.activeChain != null) {
            for(i=0; i<ParkShopApp.bkManager.activeChain.getPartLevel(); i++)
                chainStar.getChildren().add(shallowCopy(starIm));
        } else chainStar.getChildren().add(shallowCopy(dashIm));
        derailleurStar.getChildren().clear();
        if(ParkShopApp.bkManager.activeDerailleur != null) {
            for(i=0; i<ParkShopApp.bkManager.activeDerailleur.getPartLevel(); i++)
                derailleurStar.getChildren().add(shallowCopy(starIm));
        } else derailleurStar.getChildren().add(shallowCopy(dashIm));
        cranksStar.getChildren().clear();
        if(ParkShopApp.bkManager.activeCranks != null) {
            for(i=0; i<ParkShopApp.bkManager.activeCranks.getPartLevel(); i++)
                cranksStar.getChildren().add(shallowCopy(starIm));
        } else cranksStar.getChildren().add(shallowCopy(dashIm));
        pedalsStar.getChildren().clear();
        if(ParkShopApp.bkManager.activePedals != null) {
            for(i=0; i<ParkShopApp.bkManager.activePedals.getPartLevel(); i++)
                pedalsStar.getChildren().add(shallowCopy(starIm));
        } else pedalsStar.getChildren().add(shallowCopy(dashIm));
        seatpostStar.getChildren().clear();
        if(ParkShopApp.bkManager.activeSeatpost != null) {
            for(i=0; i<ParkShopApp.bkManager.activeSeatpost.getPartLevel(); i++)
                seatpostStar.getChildren().add(shallowCopy(starIm));
        } else seatpostStar.getChildren().add(shallowCopy(dashIm));
        seatStar.getChildren().clear();
        if(ParkShopApp.bkManager.activeSeat != null) {
            for(i=0; i<ParkShopApp.bkManager.activeSeat.getPartLevel(); i++)
                seatStar.getChildren().add(shallowCopy(starIm));
        } else seatStar.getChildren().add(shallowCopy(dashIm));
        handlebarStar.getChildren().clear();
        if(ParkShopApp.bkManager.activeHandlebar != null) {
            for(i=0; i<ParkShopApp.bkManager.activeHandlebar.getPartLevel(); i++)
                handlebarStar.getChildren().add(shallowCopy(starIm));
        } else handlebarStar.getChildren().add(shallowCopy(dashIm));
        shifterStar.getChildren().clear();
        if(ParkShopApp.bkManager.activeShifter != null) {
            for(i=0; i<ParkShopApp.bkManager.activeShifter.getPartLevel(); i++)
                shifterStar.getChildren().add(shallowCopy(starIm));
        } else shifterStar.getChildren().add(shallowCopy(dashIm));
        brakeLeverStar.getChildren().clear();
        if(ParkShopApp.bkManager.activeBrakeLever != null) {
            for(i=0; i<ParkShopApp.bkManager.activeBrakeLever.getPartLevel(); i++)
                brakeLeverStar.getChildren().add(shallowCopy(starIm));
        } else brakeLeverStar.getChildren().add(shallowCopy(dashIm));
        gripsStar.getChildren().clear();
        if(ParkShopApp.bkManager.activeGrips != null) {
            for(i=0; i<ParkShopApp.bkManager.activeGrips.getPartLevel(); i++)
                gripsStar.getChildren().add(shallowCopy(starIm));
        } else gripsStar.getChildren().add(shallowCopy(dashIm));
    }

    // Default construction
    public DetailController() {

    }
}
