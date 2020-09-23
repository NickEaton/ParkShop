package org.openjfx;

import javafx.event.ActionEvent;

public class RFSelectController {

    public PrimaryController primary;

    // Send button press info to primary controller
    public void transmitSelectionF(ActionEvent e) {
        primary.cDelin = 0;
        this.quit();
    }

    public void transmitSelectionR(ActionEvent e) {
        primary.cDelin = 1;
        this.quit();
    }

    // Terminate the subwindow
    public void quit() {
        primary.cDone = true;
        primary.doStage2Add();
        (ParkShopApp.window).close();
    }
}
