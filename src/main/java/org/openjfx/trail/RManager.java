package org.openjfx.trail;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import org.openjfx.entity.Rider;
import org.openjfx.util.Saveable;

import java.io.IOException;
import java.util.ArrayList;

public class RManager implements Saveable {

    private ArrayList<Rider> riderArrayList;
    @FXML private Scene displayWindow;

    @Override
    public void saveToFile() throws IOException {

    }
}
