package fxfiles;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Mainscene {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchTorand(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("randomscene.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchTosmart(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("smartscene.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
