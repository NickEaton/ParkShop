package org.openjfx;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.LinkedList;

// Let the user select an image for the bike they just constructed
public class BKImageController {

    @FXML private ImageView selectedImg;
    @FXML private LinkedList<Node> styleList;

    @FXML private Button confirm;
    @FXML private Button clear;
    @FXML private Button quit;

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
    @FXML
    public void doConfirm() throws IOException {

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
    @FXML
    public void doQuit() throws IOException {

    }

    public BKImageController() {
        styleList = new LinkedList<Node>();
    }
}
