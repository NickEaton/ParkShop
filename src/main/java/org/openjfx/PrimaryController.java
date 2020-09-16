package org.openjfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
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
import java.util.LinkedList;

// This class will handle most of the I/O in the main program
public class PrimaryController {

    ArrayList<Component> listVisible;
    @FXML ArrayList<ComponentScrollView> scrollContent;
    @FXML private ComponentScrollView selectedComponent;

    //TODO: Manage text objects on RHS

    // Components of Scroll Bar
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
    @FXML private Button purchaseButton;

    // Components of Player Stats Bar
    // counts
    @FXML private Text wallet;
    @FXML private Text shopName;
    @FXML private Text shopLevel;
    @FXML private Text frameCount;
    @FXML private Text forkCount;
    @FXML private Text shockCount;
    @FXML private Text wheelCount;
    @FXML private Text brakeCount;
    @FXML private Text rotorCount;
    @FXML private Text cassetteCount;
    @FXML private Text derailleurCount;
    @FXML private Text chainringCount;
    @FXML private Text chainCount;
    @FXML private Text crankCount;
    @FXML private Text pedalCount;
    @FXML private Text seatpostCount;
    @FXML private Text seatCount;
    @FXML private Text handlebarCount;
    @FXML private Text brakeleverCount;
    @FXML private Text shifterCount;
    @FXML private Text gripCount;
    @FXML private Text tireCount;

    // toggle views for bike construction
    @FXML private ImageView frameIn;
    @FXML private ImageView forkIn;
    @FXML private ImageView shockIn;
    @FXML private ImageView wheelFIn;;
    @FXML private ImageView wheelRIn;
    @FXML private ImageView tireFIn;
    @FXML private ImageView tireRIn;
    @FXML private ImageView rotorFIn;
    @FXML private ImageView rotorRIn;
    @FXML private ImageView cassetteIn;
    @FXML private ImageView chainringIn;
    @FXML private ImageView brakeFIn;
    @FXML private ImageView brakeRIn;
    @FXML private ImageView derailleurIn;
    @FXML private ImageView chainIn;
    @FXML private ImageView cranksIn;
    @FXML private ImageView pedalsIn;
    @FXML private ImageView seatpostIn;
    @FXML private ImageView seatIn;
    @FXML private ImageView handlebarIn;
    @FXML private ImageView shifterIn;
    @FXML private ImageView gripsIn;

    @FXML private AnchorPane bikePane;

    private int scrollHeight;

    // Dynamic update using a static method??
    //@FXML @Override static void rebuildScrollBox()
    @FXML
    public void refreshScrollBox(ActionEvent event) throws IOException {
        scrollContentFinal = new VBox();
        scrollContent = new ArrayList<ComponentScrollView>();
        scrollHeight = 0;

        for(LinkedList<Component> list : ParkShopApp.cmpManager.getShopList().values()) {
            for (Component _cmp : list) {
                this.addCompDisplay(_cmp);
            }
        }
        for(HBox box : scrollContent) {
            scrollHeight += box.getHeight();
        }
        scrollContentFinal.setMinHeight(scrollHeight);
        scrollContentFinal.getChildren().addAll(scrollContent);
        compBar.setContent(scrollContentFinal);
        this.rebuildExpandedView();
        purchaseButton.setVisible(true);
    }

    // Renull the box
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

    // Renull expanded component view
    @FXML
    private void rebuildExpandedView() {
        title_I.setText("");
        title_II.setText("");
        fitPart.setText("");
        mat.setText("");
        price.setText("");
        profitability.setText("");
        climbingBar.setProgress(0);
        enduranceBar.setProgress(0);
        downhillBar.setProgress(0);
        wearBar.setProgress(0);
        timeBar.setProgress(0);
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
                selectedComponent = temp;
                redrawPlayerStats();
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
        if(this.selectedComponent == null) {
            System.out.println("Error no Component Selected");
            return;
        }

        if(ParkShopApp.player.getWallet() > this.selectedComponent.getComponent().getCostUSD()*getPriceLookup(this.selectedComponent.getComponent().getPart())) {
            ParkShopApp.player.addComponent(this.selectedComponent.getComponent());
            ParkShopApp.cmpManager.getShopList().get(this.selectedComponent.getComponent().getPart()).remove(this.selectedComponent.getComponent());
            ParkShopApp.cmpManager.getPlayerList().get(this.selectedComponent.getComponent().getPart()).add(this.selectedComponent.getComponent());
            ParkShopApp.player.spend(this.selectedComponent.getComponent().getCostUSD()*getPriceLookup(this.selectedComponent.getComponent().getPart()));
            scrollContent.remove(this.selectedComponent);
            this.selectedComponent = null;
            this.redrawPlayerStats();
            this.rebuildScrollBox();
            this.rebuildExpandedView();
        }
    }

    // Redraw RHS of ComponentView
    @FXML
    public void redrawPlayerStats() {
        this.wallet.setText("$"+ParkShopApp.player.getWallet());
        this.brakeCount.setText(""+ParkShopApp.player.getOwnedComponents().get(ComponentManager.Part.valueOf("BRAKE")).size());
        this.frameCount.setText(""+ParkShopApp.player.getOwnedComponents().get(ComponentManager.Part.valueOf("FRAME")).size());
        this.forkCount.setText(""+ParkShopApp.player.getOwnedComponents().get(ComponentManager.Part.valueOf("FORK")).size());
        this.shockCount.setText(""+ParkShopApp.player.getOwnedComponents().get(ComponentManager.Part.valueOf("SHOCK")).size());
        this.wheelCount.setText(""+ParkShopApp.player.getOwnedComponents().get(ComponentManager.Part.valueOf("WHEEL")).size());
        this.tireCount.setText(""+ParkShopApp.player.getOwnedComponents().get(ComponentManager.Part.valueOf("TIRE")).size());
        this.rotorCount.setText(""+ParkShopApp.player.getOwnedComponents().get(ComponentManager.Part.valueOf("ROTOR")).size());
        this.cassetteCount.setText(""+ParkShopApp.player.getOwnedComponents().get(ComponentManager.Part.valueOf("CASSETTE")).size());
        this.derailleurCount.setText(""+ParkShopApp.player.getOwnedComponents().get(ComponentManager.Part.valueOf("DERAILLEUR")).size());
        this.chainringCount.setText(""+ParkShopApp.player.getOwnedComponents().get(ComponentManager.Part.valueOf("CHAINRING")).size());
        this.chainCount.setText(""+ParkShopApp.player.getOwnedComponents().get(ComponentManager.Part.valueOf("CHAIN")).size());
        this.crankCount.setText(""+ParkShopApp.player.getOwnedComponents().get(ComponentManager.Part.valueOf("CRANKS")).size());
        this.pedalCount.setText(""+ParkShopApp.player.getOwnedComponents().get(ComponentManager.Part.valueOf("PEDALS")).size());
        this.seatpostCount.setText(""+ParkShopApp.player.getOwnedComponents().get(ComponentManager.Part.valueOf("SEATPOST")).size());
        this.seatCount.setText(""+ParkShopApp.player.getOwnedComponents().get(ComponentManager.Part.valueOf("SEAT")).size());
        this.handlebarCount.setText(""+ParkShopApp.player.getOwnedComponents().get(ComponentManager.Part.valueOf("HANDLEBAR")).size());
        this.brakeleverCount.setText(""+ParkShopApp.player.getOwnedComponents().get(ComponentManager.Part.valueOf("BRAKE_LEVER")).size());
        this.shifterCount.setText(""+ParkShopApp.player.getOwnedComponents().get(ComponentManager.Part.valueOf("SHIFTER")).size());
        this.gripCount.setText(""+ParkShopApp.player.getOwnedComponents().get(ComponentManager.Part.valueOf("GRIPS")).size());
    }

    // Swap to player inventory view
    @FXML
    public void showPlayerInventory() throws IOException {
        scrollContentFinal = new VBox();
        scrollContent = new ArrayList<ComponentScrollView>();
        scrollHeight = 0;

        for(LinkedList<Component> list : ParkShopApp.cmpManager.getPlayerList().values()) {
            for (Component _cmp : list) {
                this.addCompDisplay(_cmp);
            }
        }
        for(HBox box : scrollContent) {
            scrollHeight += box.getHeight();
        }
        scrollContentFinal.setMinHeight(scrollHeight);
        scrollContentFinal.getChildren().addAll(scrollContent);
        compBar.setContent(scrollContentFinal);
        this.rebuildExpandedView();
        purchaseButton.setVisible(false);
    }

    // Swap to bike constructor view
    @FXML
    public void showBuilderView() throws IOException {
        bikePane.setVisible(true);
        this.showPlayerInventory();
        purchaseButton.setVisible(true);
        purchaseButton.setText("Add");

        purchaseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

            }
        });
    }
}
