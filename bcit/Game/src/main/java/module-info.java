module com.example.game {
    requires javafx.controls;
    requires javafx.fxml;

//    requires org.controlsfx.controls;
//    requires net.synedra.validatorfx;
//    requires org.kordamp.bootstrapfx.core;

    opens com.example.game to javafx.fxml;
    exports com.example.game;
}