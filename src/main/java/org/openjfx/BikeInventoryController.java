package org.openjfx;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
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

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BikeInventoryController {

    public BikeManager myBK;

    @FXML private ScrollPane contentPane;

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

        HBox starBox = new HBox();
        starBox.setAlignment(Pos.CENTER);
        starBox.setMinWidth(300);
        ImageView starIm = new ImageView();
        Path pathToStar = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString()+
                "\\src\\main\\resources\\org\\images\\star-1.png");
        try (InputStream in = new BufferedInputStream(new FileInputStream(pathToStar.toString()))){
            starIm = new ImageView(new Image(in));
        } catch(IOException e) {
            e.printStackTrace();
        }
        for(int a=0; a<contents.getRating(); a++)
            starBox.getChildren().add(new ImageView(starIm.getImage()));

        temp.getChildren().add(title);
        temp.getChildren().add(starBox);
        temp.getChildren().add(pic);
        temp.setAlignment(Pos.CENTER);
        temp.getStylesheets().add(ParkShopApp.class.getResource("BKInventory.css").toString());
        temp.getStyleClass().add("bkborder");

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
    // TODO: Not be dumb :(
    public void doLayout() throws IOException {
        VBox content = new VBox();
        int index = 0;
        int numPerLine = (int)contentPane.getPrefWidth()/325;
        System.out.println(contentPane.getPrefWidth());
        int HBoxCount = (int) Math.ceil((double)myBK.BKList.size()/(double)numPerLine);
        System.out.println(HBoxCount);
        if(HBoxCount < 1) HBoxCount = 1;

        System.out.println(HBoxCount);
        for(int i=0; i<HBoxCount; i++) {
            HBox lvl = new HBox();
            lvl.setAlignment(Pos.CENTER);
            lvl.setPrefHeight(300);
            lvl.setPrefWidth((int) contentPane.getPrefWidth());
            lvl.setSpacing(25);
            for(int k=0; k<numPerLine && index < myBK.BKList.size(); k++) {
                lvl.getChildren().add(getBikeBox(this.myBK.BKList.get(index++)));
                System.out.println("INV: Add bike");
                //index++;
            }
            content.getChildren().add(lvl);
        }
        contentPane.setContent(content);
    }
}
