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
import org.openjfx.entity.Employee;
import org.openjfx.entity.EmployeeManager;
import org.openjfx.util.Saveable;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;

public class EmployeeRecruitController implements Saveable {

    @FXML private ScrollPane contentPane;
    private EmployeeManager employeeManager;

    @FXML
    private VBox getEmployeeRecruitBox(Employee target) {
        VBox temp = new VBox();
        temp.setPrefWidth(300);
        temp.setPrefHeight(300);
        temp.setSpacing(25);

        Text name = new Text(target.getName());
        name.setTextAlignment(TextAlignment.CENTER);
        name.setWrappingWidth(275);
        name.setFont(new Font("System", 24));

        HBox starBox = new HBox();
        starBox.setAlignment(Pos.CENTER);
        starBox.setMinWidth(300);
        ImageView starIm = new ImageView();
        Path pathToStar = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString()+
                "\\src\\main\\resources\\org\\images\\star-1.png");
        try (InputStream in = new BufferedInputStream(new FileInputStream(pathToStar.toString()))){
            starIm = new ImageView(new Image(in));
        } catch(IOException e) {
            ParkShopApp.primaryLog.log(Level.SEVERE, "Error Loading Star-1 Image");
            e.printStackTrace();
        }
        for(int i=0; i<target.getLevel(); i++)
            starBox.getChildren().add(new ImageView(starIm.getImage()));

        temp.getChildren().addAll(name, starBox);
        temp.setAlignment(Pos.CENTER);
        temp.getStylesheets().add(ParkShopApp.class.getResource("BKInventory.css").toString());
        temp.getStyleClass().add("bkborder");
        temp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Popup to 'purchase' window
            }
        });

        return temp;
    }

    @FXML
    public void drawPane() throws IOException {
        VBox content = new VBox();
        int index = 0;
        int numPerLine = (int)contentPane.getPrefWidth()/325;
        int HboxCount = (int)Math.ceil((double)employeeManager.getStaffHireList().size()/(double)numPerLine);
        if(HboxCount < 1) HboxCount = 1;

        for(int i=0; i<HboxCount; i++) {
            HBox lvl = new HBox();
            lvl.setAlignment(Pos.CENTER);
            lvl.setPrefHeight(300);
            lvl.setPrefWidth((int) contentPane.getPrefWidth());
            lvl.setSpacing(25);
            for(int k=0; k<numPerLine && index < employeeManager.getStaffHireList().size(); k++) {
                lvl.getChildren().add(getEmployeeRecruitBox(this.employeeManager.getStaffHireList().get(index++)));
                ParkShopApp.primaryLog.log(Level.INFO, "Added to Layout: " + employeeManager.getStaffHireList().get(index-1));
            }
            content.getChildren().add(lvl);
        }
        contentPane.setContent(content);
    }

    @Override
    public void saveToFile() throws IOException {

    }
}
