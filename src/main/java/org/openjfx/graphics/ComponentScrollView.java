package org.openjfx.graphics;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.openjfx.components.Component;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

// Legacy just in case it is needed later
public class ComponentScrollView extends HBox {

    @FXML private ImageView view;
    @FXML private TextField title;

    public ComponentScrollView(Component _comp) throws IOException {
        super(10);
        Path pathToComp = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString()+"\\src\\main\\resources\\org\\images\\"+_comp.getCompName()+"shock.png");

        try (InputStream in = new BufferedInputStream(new FileInputStream(pathToComp.toString()))){
            view = new ImageView(new Image(in));
            title = new TextField(_comp.getCompName());                       // This ideally should be some sort of 'display name'
        } catch(IOException e) {
            e.printStackTrace();
        }

        this.getChildren().addAll(view, title);
    }
}
