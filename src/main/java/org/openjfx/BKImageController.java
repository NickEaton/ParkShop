package org.openjfx;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.openjfx.bike.BikeObj;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.logging.Level;

// Let the user select an image for the bike they just constructed
public class BKImageController {

    @FXML private ImageView selectedImg;
    @FXML private LinkedList<Node> styleList;

    @FXML private Button confirm;
    @FXML private Button clear;
    @FXML private Button quit;

    public BikeObj tmpBK;

    // Pre: only called on clicking an imageview
    @FXML
    public void selectImage(Event event) throws IOException {
        this.selectedImg = (ImageView)event.getSource();
        Node x = ((ImageView) event.getSource()).getParent();
        for(Node _n : this.styleList)
            _n.getStyleClass().clear();
        styleList.add(x);

        ((ImageView) event.getSource()).getParent().getStylesheets().add(ParkShopApp.class.getResource("BKImage.css").toString());
        ((ImageView) event.getSource()).getParent().getStyleClass().add("imageWrap");

        confirm.setDisable(false);
        clear.setDisable(false);
    }

    // Finish by assigning the icon for the current bike, draw a notification window and exit this control flow
    // Use Timeline and KeyFrame to fade out the scene
    @FXML
    public void doConfirm(ActionEvent event) throws IOException {
        try {
            ImageView starIm = new ImageView();
            Path pathToStar = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString()+
                    "\\src\\main\\resources\\org\\images\\star-1.png");
            try (InputStream in = new BufferedInputStream(new FileInputStream(pathToStar.toString()))){
                starIm = new ImageView(new Image(in));
            } catch(IOException e) {
                e.printStackTrace();
            }

            ParkShopApp.primaryLog.log(Level.INFO, "Loading BuildNotification.fxml");
            FXMLLoader fxload = new FXMLLoader(ParkShopApp.class.getResource("BuildNotification.fxml"));
            Parent root = fxload.load();
            BKConCompleteController controller = fxload.getController();

            ParkShopApp.window.close();

            Scene popup = new Scene(root);
            Stage popwin = new Stage();
            popwin.setTitle("Built!");
            popwin.setResizable(false);
            popwin.setScene(popup);
            popwin.show();

            tmpBK.bikePic = this.selectedImg.getImage();
            controller.bkImage.setImage(this.selectedImg.getImage());
            controller.bkName.setText(this.tmpBK.getBikeName());
            controller.starBox.getChildren().clear();
            for(int k=0; k<this.tmpBK.getRating(); k++)
                controller.starBox.getChildren().add(new ImageView(starIm.getImage()));

            controller.fadeAway(popwin, popup);                              // Deuces
        } catch (IOException e) {
            ParkShopApp.primaryLog.log(Level.SEVERE, "Error in fade transition scene construct");
            e.printStackTrace();
        }
    }

    // Clear selection
    @FXML
    public void doClear() {
        confirm.setDisable(true);
        clear.setDisable(true);
        for(Node _n : this.styleList)
            _n.getStyleClass().clear();
    }

    // Quit without construction
    // TODO
    @FXML
    public void doQuit() throws IOException {

    }

    public BKImageController() {
        styleList = new LinkedList<Node>();
    }
}
