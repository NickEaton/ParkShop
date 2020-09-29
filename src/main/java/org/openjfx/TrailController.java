package org.openjfx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import org.openjfx.trail.Feature;
import org.openjfx.trail.Trail;
import org.openjfx.trail.TrailManager;

import java.util.ArrayList;
import java.util.List;

public class TrailController {

    public TrailManager tManager;
    private TrailManager.Rating lineRating;

    @FXML public ArrayList<Line> tDrawBuffer;
    @FXML private Canvas map;
    @FXML private GraphicsContext gc;

    @FXML private GridPane difGP;
    @FXML private ArrayList<AnchorPane> difLDraw;
    @FXML private AnchorPane easiest;
    @FXML private AnchorPane easy;
    @FXML private AnchorPane intermediate;
    @FXML private AnchorPane advanced;
    @FXML private AnchorPane expert;
    @FXML private AnchorPane pro;
    @FXML private AnchorPane master;
    @FXML private AnchorPane insanity;

    private int prevx;
    private int prevy;

    private static final String[] colors = {"#9EE8B2", "#30e360", "#1384e8", "#4d4a4b",
                                            "#171616", "#db7c00", "#631c0f", "#c42204"};

    // true when currently drawing a line
    private boolean activeLine;

    @FXML
    private void onMouseClick(MouseEvent e) {
        lineRating = TrailManager.Rating.values()[GridPane.getColumnIndex((AnchorPane)e.getSource())];
        gc.setStroke(Paint.valueOf(colors[GridPane.getColumnIndex((AnchorPane)e.getSource())]));
        refreshDifficultyBar((AnchorPane)e.getSource());
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
        System.out.println(selection.getId()+"Border");
        selection.getStyleClass().add(selection.getId()+"Border");
    }

    private String getColorById() {
        return null;
    }

    // Draw the canvas with only registered segments shown
    private void refreshCanvas() {
        gc.clearRect(0, 0, map.getWidth(), map.getHeight());
        for(Line l : tDrawBuffer)
            gc.strokeLine(l.getStartX(), l.getStartY(), l.getEndX(), l.getEndY());
    }

    // Show the user where their line is going
    private void onMouseMoveNoDrag() {
        map.setOnMouseMoved(event -> {
            if(activeLine) {
                refreshCanvas();
                gc.strokeLine(prevx, prevy, event.getX(), event.getY());
            }
        });
    }

    public void mouseClick(MouseEvent e) {
        int xpos = (int)e.getX();
        int ypos = (int)e.getY();

        if(!activeLine) {
            prevx = xpos;
            prevy = ypos;
            activeLine = true;
            return;
        }

        gc.strokeLine(prevx, prevy, xpos, ypos);
        tDrawBuffer.add(new Line(prevx, prevy, xpos, ypos));
        prevx = xpos;
        prevy = ypos;
        activeLine = true;
    }

    // setup the layout of this trail map
    public void doLayout() {
        gc = map.getGraphicsContext2D();
        tDrawBuffer = new ArrayList<Line>();
        gc.setLineWidth(5);
        gc.setStroke(Paint.valueOf("blue"));
        activeLine = false;
        onMouseMoveNoDrag();
    }

    // Re-Draw pre-existing features if the map is closed and re-opened
    public void preDraw(List<Trail> trailList ) {
        // TODO
    }
}
