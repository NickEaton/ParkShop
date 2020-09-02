package org.openjfx;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

// This class will handle most of the I/O in the main program
public class PrimaryController {

    @FXML
    ScrollPane compBar;

    /*
    @FXML
    private void switchToPrimary() throws IOException {
        ParkShopApp.setRoot("primary");
    }
    */

    // This will allow us to swap between component views
    @FXML
    public void toggleCompDisplay() {
        compBar.setVisible(!compBar.isVisible());
    }

}
