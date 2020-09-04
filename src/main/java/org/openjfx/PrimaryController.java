package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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

    @FXML private VBox scrollContentFinal;
    @FXML private ScrollPane compBar;
    @FXML private Pane compExpandedView;
    @FXML private GridPane gridPane;
    @FXML private VBox gridTitle;

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
    public void toggleCompExtendedDisplay() {
        compExpandedView.setVisible(!compExpandedView.isVisible());
    }

    @FXML
    public void addTestCMP(ActionEvent e) throws Exception {
       addCompDisplay(ParkShopApp.cmpManager.addCatalogComponent(ParkShopApp.cmpManager.getNewRandComponent("fork1", ComponentManager.Part.FORK)));
    }

    // This creates a new instance of a visible component, and adds it to listVisible and listDetail;
    @FXML
    public void addCompDisplay(Component _comp) throws IOException {
        if(scrollContentFinal == null)
            scrollContentFinal = new VBox();
        if(scrollContent == null)
            scrollContent = new ArrayList<HBox>();
        listVisible.add(_comp);

        HBox temp = new HBox(0);
        temp.setPrefHeight(100);
        ImageView view = new ImageView();
        Text title = new Text();
        Path pathToComp = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString()+
                                    "\\src\\main\\resources\\org\\images\\"+_comp.getCompName()+".png");

        try (InputStream in = new BufferedInputStream(new FileInputStream(pathToComp.toString()))){
            view = new ImageView(new Image(in));
            title = new Text(_comp.getCompName());
            title.setFont(new Font(32));
        } catch(IOException e) {
            e.printStackTrace();
        }

        temp.setAlignment(Pos.CENTER);
        title.setTranslateX((190-title.getLayoutBounds().getWidth())/2);
        temp.getChildren().addAll(view, title);
        temp.setVisible(true);
        scrollContent.add(temp);      // IOException on missing container could happen lol
        this.rebuildScrollBox();
    }

    @FXML
    public void displayComponentExpanded(Component _comp) {

    }
}
