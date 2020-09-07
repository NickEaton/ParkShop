package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
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
import org.openjfx.entity.Player;
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
    @FXML ArrayList<ComponentScrollView> scrollContent;

    //TODO: Manage text objects on RHS

    @FXML private static VBox scrollContentFinal = new VBox();
    @FXML private ScrollPane compBar;
    @FXML private Pane compExpandedView;
    @FXML private GridPane gridPane;
    @FXML private VBox gridTitle;
    @FXML private Text title_I;
    @FXML private Text title_II;
    @FXML private Text fitPart;
    @FXML private Text mat;
    @FXML private Text price;
    @FXML private Text profitability;
    @FXML private ProgressBar climbingBar;
    @FXML private ProgressBar enduranceBar;
    @FXML private ProgressBar downhillBar;
    @FXML private ProgressBar wearBar;
    @FXML private ProgressBar timeBar;
    @FXML private Pane playerOverview;

    private int scrollHeight;

    // Dynamic update using a static method??
    //@FXML @Override static void rebuildScrollBox()
    @FXML
    public void refreshScrollBox(ActionEvent event) throws IOException {
        scrollContentFinal = new VBox();
        scrollContent = new ArrayList<ComponentScrollView>();
        scrollHeight = 0;

        for(Component _cmp : ParkShopApp.player.componentManager.getShopList()) {
            this.addCompDisplay(_cmp);
            System.out.println("added:\n"+_cmp);
        }
        for(HBox box : scrollContent) {
            scrollHeight += box.getHeight();
        }
        scrollContentFinal.setMinHeight(scrollHeight);
        scrollContentFinal.getChildren().addAll(scrollContent);
        compBar.setContent(scrollContentFinal);
        System.out.println("Made it");
    }

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
        addCompDisplay(ParkShopApp.cmpManager.addCatalogComponent(ParkShopApp.cmpManager.getNewRandComponent("chain", ComponentManager.Part.FORK)));
    }

    // This creates a new instance of a visible component, and adds it to listVisible and listDetail;
    @FXML
    public void addCompDisplay(Component _comp) throws IOException {
        if(scrollContentFinal == null)
            scrollContentFinal = new VBox();
        if(scrollContent == null)
            scrollContent = new ArrayList<ComponentScrollView>();
        listVisible.add(_comp);

        ComponentScrollView temp = new ComponentScrollView(_comp);
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
        //title.setTranslateX((190-title.getLayoutBounds().getWidth())/2);

        VBox titleBox = new VBox();
        titleBox.getChildren().add(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPrefWidth(150d);
        VBox viewBox = new VBox();
        viewBox.getChildren().add(view);
        viewBox.setAlignment(Pos.CENTER);
        viewBox.setPrefWidth(150d);

        temp.getChildren().addAll(viewBox, titleBox);
        temp.setVisible(true);
        scrollContent.add(temp);      // IOException on missing container could happen lol
        //this.rebuildScrollBox();
    }

    // Draw the display with appropriate component
    @FXML
    public void displayComponentExpanded(Component _comp) {
        compExpandedView.setVisible(true);

    }

    // Toggled on clicking the purchase button on expanded component view
    // TODO: Create Player class
    @FXML
    public void purchaseSelectedComponent(ActionEvent e) {

    }
}
