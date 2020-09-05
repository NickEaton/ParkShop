package org.openjfx.graphics;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
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
    @FXML private Text title;
    private Component myComponent;

    public ComponentScrollView(Component _comp) throws IOException {
        super(10);

        Path pathToComp = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString()+
                                    "\\src\\main\\resources\\org\\images\\"+_comp.getCompName()+".png");

        try (InputStream in = new BufferedInputStream(new FileInputStream(pathToComp.toString()))){
            view = new ImageView(new Image(in));
            title = new Text(_comp.getCompName());
        } catch(IOException e) {
            e.printStackTrace();
        }

        this.myComponent = _comp;
        //this.getChildren().addAll(view, title);
    }

    // Mutators
    @FXML public void setImage(ImageView _view) { this.view = _view; }
    @FXML public void setTitle(Text _title) { this.title = _title; }
    @FXML public ImageView getView() { return this.view; }
    @FXML public Text getTitle() { return this.title; }
}
