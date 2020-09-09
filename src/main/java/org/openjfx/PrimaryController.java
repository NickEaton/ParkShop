package org.openjfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.openjfx.components.Component;
import org.openjfx.components.ComponentManager;
import org.openjfx.entity.Player;
import org.openjfx.graphics.ComponentScrollView;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;

// This class will handle most of the I/O in the main program
public class PrimaryController {

    ArrayList<Component> listVisible;
    @FXML ArrayList<ComponentScrollView> scrollContent;
    @FXML private ComponentScrollView selectedComponent;

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

        for(Component _cmp : ParkShopApp.cmpManager.getShopList()) {
            this.addCompDisplay(_cmp);
        }
        for(HBox box : scrollContent) {
            scrollHeight += box.getHeight();
        }
        scrollContentFinal.setMinHeight(scrollHeight);
        scrollContentFinal.getChildren().addAll(scrollContent);
        compBar.setContent(scrollContentFinal);
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

        // Read image data
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

        VBox titleBox = new VBox();                                     // Title and it's properties
        titleBox.getChildren().add(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPrefWidth(200d);
        VBox viewBox = new VBox();                                      // Image and it's properties
        view.setFitWidth(200);
        view.setFitHeight(200);
        viewBox.getChildren().add(view);
        viewBox.setAlignment(Pos.CENTER);
        viewBox.setPrefWidth(200d);

        temp.getChildren().addAll(viewBox, titleBox);                   // Add the image and title to the box
        temp.setStyle("-fx-border-color: black");                       // Apply css styling via string input

        temp.setOnMouseClicked(new EventHandler<MouseEvent>() {         // Write to the UI on mouse click event
            @Override
            public void handle(MouseEvent event) {
                displayComponentExpanded(temp.getComponent());
            }
        });
        temp.setVisible(true);                                          // Draw the component
        scrollContent.add(temp);                                        // IOException on missing container
    }

    // Draw the display with appropriate component
    @FXML
    public void displayComponentExpanded(Component _cmp) {
        DecimalFormat format = new DecimalFormat();
        format.setRoundingMode(RoundingMode.FLOOR);
        format.setMaximumFractionDigits(2);

        compExpandedView.setVisible(true);
        this.climbingBar.setProgress(_cmp.getFitXC()/100);
        this.enduranceBar.setProgress(_cmp.getFitEND()/100);
        this.downhillBar.setProgress(_cmp.getFitDH()/100);
        this.wearBar.setProgress(_cmp.getWearPercent()/100);
        this.timeBar.setProgress(_cmp.getTimeModifier()/100);
        this.mat.setText(_cmp.getMaterial().toString());
        this.fitPart.setText(_cmp.getPart().toString());
        this.price.setText("$"+format.format(_cmp.getCostUSD()*getPriceLookup(_cmp.getPart())));
        this.profitability.setText("$"+format.format(_cmp.getMarginUSD()));

        String[] x = (_cmp.getDisplayName().split("_"));
        String z = "";
        for(int i=0; i<x.length-1; i++)
            z += x[i] + " ";
        z += x[x.length-1];

        this.title_I.setText(z);
        this.title_I.setWrappingWidth(400);
        this.title_I.setTextAlignment(TextAlignment.CENTER);
        this.title_II.setText(_cmp.getCompID());
    }

    // Will modify this at a later point
    public int getPriceLookup(ComponentManager.Part _part) {
        switch(_part) {
            case FORK:
                return 75;
            case SEAT:
                return 4;
            case TIRE:
                return 10;
            case BRAKE:
                return 4;
            case CHAIN:
                return 1;
            case FRAME:
                return 100;
            case GRIPS:
                return 2;
            case ROTOR:
                return 3;
            case SHOCK:
                return 50;
            case WHEEL:
                return 10;
            case CRANKS:
                return 4;
            case PEDALS:
                return 4;
            case SHIFTER:
                return 6;
            case CASSETTE:
                return 18;
            case SEATPOST:
                return 5;
            case CHAINRING:
                return 5;
            case HANDLEBAR:
                return 8;
            case DERAILLEUR:
                return 12;
            case BRAKE_LEVER:
                return 6;
            default:
                return 1;
        }
    }

    // Toggled on clicking the purchase button on expanded component view
    // TODO: Create Player class
    @FXML
    public void purchaseSelectedComponent(ActionEvent e) {

    }
}
