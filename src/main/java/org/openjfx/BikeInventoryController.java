package org.openjfx;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.openjfx.bike.BikeManager;
import org.openjfx.bike.BikeObj;

import java.io.IOException;

public class BikeInventoryController {

    public BikeManager myBK;

    @FXML private VBox contentPane;

    // Draw the box for each bike
    @FXML private VBox getBikeBox(BikeObj contents) throws IOException {
        VBox temp = new VBox();
        temp.setPrefWidth(300);
        temp.setPrefHeight(300);
        temp.setSpacing(25);

        Text title = new Text(contents.getBikeName());
        title.setWrappingWidth(275);
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(new Font("System", 24));
        ImageView pic = new ImageView(contents.bikePic);
        pic.setFitWidth(285);
        pic.setFitHeight(285-title.getLayoutBounds().getHeight());

        temp.getChildren().add(title);
        temp.getChildren().add(pic);

        temp.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                // Do Something
                System.out.println("Register click");
            }
        });

        return temp;
    }

    // panes will be 300 x 300
    public void doLayout() throws IOException {
        int index = 0;
        contentPane.getChildren().clear();

        contentPane.setSpacing(25);
        for(int i=0; i<Math.ceil(myBK.myBKList.size()/(contentPane.getWidth()/325)); i++) {
            HBox lvl = new HBox();
            lvl.setPrefHeight(300);
            lvl.setPrefWidth((int) contentPane.getWidth());
            lvl.setSpacing(25);
            for(int k=0; k<contentPane.getWidth()/325; k++) {
                lvl.getChildren().add(getBikeBox(this.myBK.myBKList.get(index)));
            }
        }
    }
}
