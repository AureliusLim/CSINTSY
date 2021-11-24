module com.example.project1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens fxfiles to javafx.fxml;
    exports fxfiles;
}