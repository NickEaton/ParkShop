package org.openjfx;

import javafx.fxml.FXML;

import java.io.IOException;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        ParkShopApp.setRoot("secondary");
    }

    //public void
}
