package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import org.openjfx.trail.Feature;
import org.openjfx.trail.Trail;
import org.openjfx.trail.TrailManager;

import java.util.List;

public class TrailController {

    public TrailManager tManager;

    @FXML private Canvas map;
    @FXML private GraphicsContext gc;

    private int prevx;
    private int prevy;

    private boolean dLine;

    public void mouseClick(MouseEvent e) {
        System.out.println("Try arc");
        int xpos = (int)e.getX();
        int ypos = (int)e.getY();

        int width = (int) map.getWidth();
        int height = (int) map.getHeight();

        if(!dLine) {
            prevx = xpos;
            prevy = ypos;
            gc.setLineWidth(5);
            gc.setStroke(Paint.valueOf("blue"));
            dLine = true;
            return;
        }

        gc.strokeLine(prevx, prevy, xpos, ypos);
        prevx = xpos;
        prevy = ypos;
        dLine = false;
        System.out.println("Try arc");
    }

    // setup the layout of this trail map
    public void doLayout() {
        dLine = false;
        gc = map.getGraphicsContext2D();

        //map.setOnMouseClicked(event -> {
        //    System.out.println("["+event.getX()+", "+event.getY()+"]");
        //});
    }

    // Re-Draw pre-existing features if the map is closed and re-opened
    public void preDraw(List<Trail> trailList ) {
        // TODO
    }
}
