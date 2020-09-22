package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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

public class DetailController {
    // Controls for bike overview
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

    @FXML private Text frameName;
    @FXML private Text forkName = new Text();
    @FXML private Text shockName = new Text();
    @FXML private Text wheelFName = new Text();
    @FXML private Text wheelRName = new Text();
    @FXML private Text tireFName = new Text();
    @FXML private Text tireRName = new Text();
    @FXML private Text brakeFName = new Text();
    @FXML private Text brakeRName = new Text();
    @FXML private Text rotorFName = new Text();
    @FXML private Text rotorRName = new Text();
    @FXML private Text cassetteName = new Text();
    @FXML private Text chainringName = new Text();
    @FXML private Text chainName = new Text();
    @FXML private Text derailleurName = new Text();
    @FXML private Text cranksName = new Text();
    @FXML private Text pedalsName = new Text();
    @FXML private Text seatpostName = new Text();
    @FXML private Text seatName = new Text();
    @FXML private Text handlebarName = new Text();
    @FXML private Text shifterName = new Text();
    @FXML private Text brakeLeverName = new Text();
    @FXML private Text gripsName = new Text();

    @FXML private AnchorPane bikePane;
    @FXML private Text bikeName = new Text();
    //@FXML private HBox topBox = new HBox();

    @FXML private ImageView dashIm;
    @FXML private ImageView starIm;

    @FXML
    private ImageView shallowCopy(ImageView target) {
        ImageView result = new ImageView(target.getImage());
        result.setFitWidth(target.getFitWidth());
        result.setFitHeight(target.getFitHeight());
        return result;
    }

    // private submethod to do drawing for viewBikeDetail
    @FXML
    public void subUpdateBikeDetail() {
        frameName.setText((ParkShopApp.bkManager.activeFrame == null) ? "-" : ParkShopApp.bkManager.activeFrame.getDisplayName());
        forkName.setText((ParkShopApp.bkManager.activeFork == null) ? "-" : ParkShopApp.bkManager.activeFork.getDisplayName());
        shockName.setText((ParkShopApp.bkManager.activeShock == null) ? "-" : ParkShopApp.bkManager.activeShock.getDisplayName());
        wheelFName.setText((ParkShopApp.bkManager.activeWheelF == null) ? "-" : ParkShopApp.bkManager.activeWheelF.getDisplayName());
        wheelRName.setText((ParkShopApp.bkManager.activeWheelR == null) ? "-" : ParkShopApp.bkManager.activeWheelR.getDisplayName());
        tireFName.setText((ParkShopApp.bkManager.activeTireF == null) ? "-" : ParkShopApp.bkManager.activeTireF.getDisplayName());
        tireRName.setText((ParkShopApp.bkManager.activeTireR == null) ? "-" : ParkShopApp.bkManager.activeTireR.getDisplayName());
        brakeFName.setText((ParkShopApp.bkManager.activeBrakeF == null) ? "-" : ParkShopApp.bkManager.activeBrakeF.getDisplayName());
        brakeRName.setText((ParkShopApp.bkManager.activeBrakeR == null) ? "-" : ParkShopApp.bkManager.activeBrakeR.getDisplayName());
        rotorFName.setText((ParkShopApp.bkManager.activeRotorF == null) ? "-" : ParkShopApp.bkManager.activeRotorF.getDisplayName());
        rotorRName.setText((ParkShopApp.bkManager.activeRotorR == null) ? "-" : ParkShopApp.bkManager.activeRotorR.getDisplayName());
        cassetteName.setText((ParkShopApp.bkManager.activeCassette == null) ? "-" : ParkShopApp.bkManager.activeCassette.getDisplayName());
        chainringName.setText((ParkShopApp.bkManager.activeChainring == null) ? "-" : ParkShopApp.bkManager.activeChainring.getDisplayName());
        chainName.setText((ParkShopApp.bkManager.activeChain == null) ? "-" : ParkShopApp.bkManager.activeChain.getDisplayName());
        derailleurName.setText((ParkShopApp.bkManager.activeDerailleur == null) ? "-" : ParkShopApp.bkManager.activeDerailleur.getDisplayName());
        cranksName.setText((ParkShopApp.bkManager.activeCranks == null) ? "-" : ParkShopApp.bkManager.activeCranks.getDisplayName());
        pedalsName.setText((ParkShopApp.bkManager.activePedals == null) ? "-" : ParkShopApp.bkManager.activePedals.getDisplayName());
        seatpostName.setText((ParkShopApp.bkManager.activeSeatpost == null) ? "-" : ParkShopApp.bkManager.activeSeatpost.getDisplayName());
        seatName.setText((ParkShopApp.bkManager.activeSeat == null) ? "-" : ParkShopApp.bkManager.activeSeat.getDisplayName());
        handlebarName.setText((ParkShopApp.bkManager.activeHandlebar == null) ? "-" : ParkShopApp.bkManager.activeHandlebar.getDisplayName());
        shifterName.setText((ParkShopApp.bkManager.activeShifter == null) ? "-" : ParkShopApp.bkManager.activeShifter.getDisplayName());
        brakeLeverName.setText((ParkShopApp.bkManager.activeBrakeLever == null) ? "-" : ParkShopApp.bkManager.activeBrakeLever.getDisplayName());
        gripsName.setText((ParkShopApp.bkManager.activeGrips == null) ? "-" : ParkShopApp.bkManager.activeGrips.getDisplayName());
        System.out.println(gripsName.getText());
    }

    // private submethod  to draw stars for viewBikeDetail
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

    // Show another scene with a full part list
    public DetailController() {
        //subUpdateBikeDetail();
        //subUpdateBikeDetailStars();
    }
}
