package org.openjfx.graphics;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.openjfx.components.Component;

import java.io.IOException;

// HBox extention to simplify 'dynamic draw' of component view
public class ComponentScrollView extends HBox {

    @FXML private ImageView view;
    @FXML private Text title;
    @FXML private VBox viewBox;
    @FXML private VBox titleBox;
    private Component myComponent;

    public ComponentScrollView(Component _comp) throws IOException {
        super(0);
        this.myComponent = _comp;
    }

    // Mutators
    @FXML public void setImage(ImageView _view) { this.view = _view; }
    @FXML public void setTitle(Text _title) { this.title = _title; }
    @FXML public void setTitleBox(VBox _tBox) { this.titleBox = _tBox; }
    @FXML public void setViewBox(VBox _vBox) { this.viewBox = _vBox; }
    @FXML public ImageView getView() { return this.view; }
    @FXML public Text getTitle() { return this.title; }
    @FXML public VBox getViewBox() { return this.viewBox; }
    @FXML public VBox getTitleBox() { return this.titleBox; }
}
