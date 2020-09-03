package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.openjfx.components.Component;
import org.openjfx.components.ComponentManager;
import org.openjfx.graphics.ComponentScrollView;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

// This class will handle most of the I/O in the main program
public class PrimaryController {


    ArrayList<Component> listVisible;
    ArrayList<HBox> scrollContent;
    VBox scrollContentFinal;

    @FXML
    private ScrollPane compBar;

    private int scrollHeight;

    // Whenever a component is added/removed, update the scroll list
    @FXML
    private void rebuildScrollBox() {
        scrollContentFinal = new VBox();

        scrollHeight = 0;
        for(HBox c : scrollContent)
            scrollHeight += c.getHeight();
        scrollContentFinal.setMinHeight(scrollHeight);
        scrollContentFinal.getChildren().addAll(scrollContent);
        compBar.setContent(scrollContentFinal);
    }

    // May need another constructor for files, but that is for later
    public PrimaryController() {
        listVisible = new ArrayList<Component>();
    }

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

    @FXML
    public void addTestCMP(ActionEvent e) throws Exception {
       addCompDisplay(ParkShopApp.cmpManager.addCatalogComponent(ParkShopApp.cmpManager.getNewRandComponent("fork1", ComponentManager.Part.FORK)));
    }

    // Ideally this will create a new instance of a visible component, and add it to listVisible and listDetail;
    @FXML
    public void addCompDisplay(Component _comp) throws IOException {
        if(scrollContentFinal == null)
            scrollContentFinal = new VBox();
        if(scrollContent == null)
            scrollContent = new ArrayList<HBox>();
        listVisible.add(_comp);

        HBox temp = new HBox(10);
        temp.setPrefHeight(100);
        ImageView view = new ImageView();
        TextField title = new TextField();
        Path pathToComp = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString()+"\\src\\main\\resources\\org\\images\\"+_comp.getCompName()+".png");

        try (InputStream in = new BufferedInputStream(new FileInputStream(pathToComp.toString()))){
            view = new ImageView(new Image(in));
            title = new TextField(_comp.getCompName());                       // This ideally should be some sort of 'display name'
        } catch(IOException e) {
            e.printStackTrace();
        }

        temp.getChildren().addAll(view, title);
        temp.setVisible(true);
        scrollContent.add(temp);      // This is where the IOException could happen lol
        rebuildScrollBox();
    }
}
