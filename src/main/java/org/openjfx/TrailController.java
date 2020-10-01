package org.openjfx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.openjfx.bike.BikeObj;
import org.openjfx.trail.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class TrailController {

    @FXML public Scene myScene;

    RManager raceManager;

    public TrailManager tManager;
    private TrailManager.Rating lineRating;

    @FXML private BorderPane base;

    @FXML public ArrayList<Shape> tDrawBuffer;

    @FXML private GridPane difGP;
    @FXML private AnchorPane easiest;
    @FXML private AnchorPane easy;
    @FXML private AnchorPane intermediate;
    @FXML private AnchorPane advanced;
    @FXML private AnchorPane expert;
    @FXML private AnchorPane pro;
    @FXML private AnchorPane master;
    @FXML private AnchorPane insanity;
    @FXML private ImageView easiestIMG;
    @FXML private ImageView easyIMG;
    @FXML private ImageView intermediateIMG;
    @FXML private ImageView advancedIMG;
    @FXML private ImageView expertIMG;
    @FXML private ImageView proIMG;
    @FXML private ImageView masterIMG;
    @FXML private ImageView insanityIMG;
    @FXML private Text editModeTxt;
    @FXML private Text contDrawModeTxt;

    @FXML private Pane map;
    @FXML private Line tmpLine;

    private int prevx;
    private int prevy;

    private boolean contDraw;
    private boolean editMode;

    private static final String[] colors = {"#9EE8B2", "#30e360", "#1384e8", "#4d4a4b",
                                            "#171616", "#db7c00", "#631c0f", "#c42204"};

    private final ImageView[] rImages = { easiestIMG, easyIMG, intermediateIMG, advancedIMG,
                                    expertIMG, proIMG, masterIMG, insanityIMG};
    private Paint activeColor;

    private boolean activeLine;
    private boolean drawingTrail;
    private Trail cDrawTrail;

    @FXML private TextField cTrailName;
    @FXML private ImageView cTrailRating;
    @FXML private Text cTrailDistTXT;
    private int cTrailDistVal;

    public TrailController() {
        this.raceManager = new RManager();
    }

    @FXML
    private void onMouseClick(MouseEvent e) {
        lineRating = TrailManager.Rating.values()[GridPane.getColumnIndex((AnchorPane)e.getSource())];
        cTrailRating = (rImages[GridPane.getColumnIndex((AnchorPane)e.getSource())]);           // TODO: needs work
        activeColor = Paint.valueOf(colors[GridPane.getColumnIndex((AnchorPane)e.getSource())]);
        refreshDifficultyBar((AnchorPane)e.getSource());
    }

    private void generateNode(MouseEvent event) {
        if(!drawingTrail) {                 // Create new Trail object, put first node in the linked list
            cDrawTrail = new Trail();
            TrailVectorNode startVec = new TrailVectorNode(event.getX(), event.getY(), 15);
            startVec.setLink(tmpLine);
            startVec.setFill(Color.TRANSPARENT);
            cDrawTrail.getTrailPath().add(startVec);
            drawingTrail = true;
        } else {                            // Add node to end, setup line link using tmpLine
            cDrawTrail.getTrailPath().getLast().setLink(this.tmpLine);
            TrailVectorNode newVec = new TrailVectorNode(event.getX(), event.getY(), 15);
            newVec.setLink(tmpLine);
            newVec.setFill(Color.TRANSPARENT);
            cDrawTrail.getTrailPath().add(newVec);
        }
        prevx = (int)event.getX();
        prevy = (int)event.getY();
    }

    @FXML
    private void refreshDifficultyBar(AnchorPane selection) {
        easiest.getStyleClass().clear();
        easy.getStyleClass().clear();
        intermediate.getStyleClass().clear();
        advanced.getStyleClass().clear();
        expert.getStyleClass().clear();
        pro.getStyleClass().clear();
        master.getStyleClass().clear();
        insanity.getStyleClass().clear();

        selection.getStylesheets().add(ParkShopApp.class.getResource("Trail-Style.css").toString());
        selection.getStyleClass().add(selection.getId()+"Border");
    }

    // Draw the pane with only registered segments shown
    private void refreshCanvas() {
        map.getChildren().clear();
        map.getChildren().addAll(tDrawBuffer);
    }

    // Show the user where their line is going
    private void onMouseMoveNoDrag() {
        map.setOnMouseMoved(event ->  {
            if(editMode) {
                refreshCanvas();
                if (activeLine && contDraw) {
                    drawTmpLine((int) event.getX(), (int) event.getY());
                    tmpLine.setStroke(activeColor);
                    tmpLine.setStrokeWidth(4);
                    map.getChildren().add(tmpLine);
                }
            }
        });
    }

    @FXML private void drawTmpLine(int x, int y) {
        tmpLine = new Line(prevx, prevy, x, y);
    }

    @FXML
    private void toggleContiguousDraw() {
        contDraw = !contDraw;
        if(contDraw) {
            contDrawModeTxt.setText("ENABLED");
            contDrawModeTxt.getStyleClass().clear();
            contDrawModeTxt.getStyleClass().add("textEnable");
        } else {
            contDrawModeTxt.setText("DISABLED");
            contDrawModeTxt.getStyleClass().clear();
            contDrawModeTxt.getStyleClass().add("textDisable");
        }
    }

    @FXML
    private void toggleEditMode() {
        editMode = !editMode;
        if(editMode) {
            editModeTxt.setText("ENABLED");
            editModeTxt.getStyleClass().clear();
            editModeTxt.getStyleClass().add("textEnable");
        } else {
            editModeTxt.setText("DISABLED");
            editModeTxt.getStyleClass().clear();
            editModeTxt.getStyleClass().add("textDisable");
        }
    }

    public void mouseClick(MouseEvent e) {
        if(editMode) {                  // Currently do nothing if not in edit mode
            int xpos = (int) e.getX();
            int ypos = (int) e.getY();
            if (!activeLine) {          // If not drawing a segment, create new Trail/Segment
                prevx = xpos;
                prevy = ypos;
                activeLine = true;
            } else {                    // Add to the current list
                map.getChildren().clear();
                drawTmpLine(xpos, ypos);
                map.getChildren().add(tmpLine);
                tmpLine.setStroke(activeColor);
                tmpLine.setStrokeWidth(4);
                tDrawBuffer.add(tmpLine);
                prevx = xpos;
                prevy = ypos;
            }
            if(cDrawTrail == null) cDrawTrail = new Trail();
            generateNode(e);
            tDrawBuffer.add(cDrawTrail.getTrailPath().getLast());

            cDrawTrail.getTrailPath().getLast().setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    //cTrailRating.setImage(cTrailRating.getImage());
                    cTrailName.setText(cDrawTrail.getTrailPath().getLast().getOwner().getName());
                    cTrailDistTXT.setText(cDrawTrail.getTrailPath().getLast().getOwner().getLength());
                }
            });
        }
    }


    // setup the layout of this trail map
    public void doLayout() {
        tManager = new TrailManager();
        contDraw = false;
        editMode = false;
        drawingTrail = false;

        tDrawBuffer = new ArrayList<Shape>();
        activeLine = false;
        onMouseMoveNoDrag();

        activeColor = Paint.valueOf(colors[0]);

        //TODO: map.addEventHandler();

        myScene.setOnKeyPressed(event -> {
            if(event.getText().toLowerCase().equals("c")) {
                toggleContiguousDraw();
            }
            if(event.getText().toLowerCase().equals("e")) {
                toggleEditMode();
            }
        });
    }

    // Re-Draw pre-existing features if the map is closed and re-opened
    public void preDraw(List<Trail> trailList ) {
        for(Trail t : trailList) {
            for(TrailVectorNode tvn : t.getTrailPath()) {
                tDrawBuffer.add(tvn);
                if(tvn.getLink() !=  null) tDrawBuffer.add(tvn.getLink());
            }
        }
        refreshCanvas();
    }

    public void publishTrail() {
        this.cDrawTrail.setRating(lineRating);
        this.cDrawTrail.setName(this.cTrailName.getText());
        ParkShopApp.primaryLog.log(Level.INFO, "Published Trail "+cDrawTrail.getName());
        this.tManager.addTrail(this.cDrawTrail);
        this.raceManager.addTrack(this.cDrawTrail);
        cDrawTrail = new Trail();
        drawingTrail = false;
        activeLine = false;
    }

    // Hand off to BKImageSelect controller
    @FXML
    public void handoffRaceSelectTrack() throws IOException {
        try {
            //raceManager = new RManager();
            FXMLLoader fxload = new FXMLLoader(ParkShopApp.class.getResource("RaceSelectTrack.fxml"));
            Parent root = fxload.load();
            RaceSelectTrackController rtx = fxload.getController();
            rtx.myRaceManager = raceManager;
            rtx.doLayout();

            Scene sub = new Scene(root);
            ParkShopApp.window = new Stage();
            ParkShopApp.window.setScene(sub);
            ParkShopApp.window.setTitle("Select Track");
            ParkShopApp.window.show();
        } catch (Exception e) {
            ParkShopApp.primaryLog.log(Level.SEVERE,"Error in RaceSelectTrack Controller handoff");
            e.printStackTrace();
        }
    }
}
