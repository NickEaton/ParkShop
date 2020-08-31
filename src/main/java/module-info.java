// README - Dependency lib from local javafx-11.0.2 removed as it was a duplicate of the Maven generated External Libraries;
// Any strange issues with dependencies should start by re-importing lib in structure->modules

module org.openjfx {

    requires javafx.controls;
    requires javafx.fxml;

    opens org.openjfx to javafx.fxml;
    exports org.openjfx;
}