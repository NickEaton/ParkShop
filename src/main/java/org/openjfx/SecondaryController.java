package org.openjfx;

import javafx.fxml.FXML;

import java.io.IOException;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        ParkShopApp.setRoot("primary");
    }
}
