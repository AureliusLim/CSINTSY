package fxfiles;
import base.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.Grid;

import java.io.IOException;
import java.util.ArrayList;

public class randscene {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    TextField boardsize;
    @FXML
    AnchorPane anchor;
    public void switchTomain(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void moveMiner(ActionEvent e) throws IOException{
        int size;
        size = Integer.parseInt(boardsize.getText());
        GameManager g = new GameManager(size);
        ArrayList<Tiles> tiles = g.getTile();
        Player player = g.getPlayer();
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setGridLinesVisible(true);
        grid.setPrefHeight(1000);
        grid.setPrefWidth(1000);
        Image miner = new Image("C:\\Users\\Aurelius Justin Lim\\IdeaProjects\\project1\\src\\main\\java\\miner-pic.jpg");
        Image grass = new Image("C:\\Users\\Aurelius Justin Lim\\IdeaProjects\\project1\\src\\main\\java\\grass.png");
        Image pit = new Image("C:\\Users\\Aurelius Justin Lim\\IdeaProjects\\project1\\src\\main\\java\\pit.jpg");
        Image gold = new Image("C:\\Users\\Aurelius Justin Lim\\IdeaProjects\\project1\\src\\main\\java\\gold.png");
        Image beacon = new Image("C:\\Users\\Aurelius Justin Lim\\IdeaProjects\\project1\\src\\main\\java\\beacon.png");
        ImageView minerimage = new ImageView(miner);
        ImageView goldimage = new ImageView(gold);

        minerimage.setFitHeight(50);
        minerimage.setFitWidth(50);

        goldimage.setFitHeight(50);
        goldimage.setFitWidth(50);

        RowConstraints rc = new RowConstraints(50);
        ColumnConstraints cc = new ColumnConstraints(50);
        for (int i = 0; i < size; i ++){
            grid.getRowConstraints().add(rc);
        }
        for (int i = 0; i < size; i ++){
            grid.getColumnConstraints().add(cc);
        }

        //boardpane.setMinHeight(500);
        //boardpane.setMinWidth(500);
        //boardpane.setMaxWidth(500);
        //boardpane.setMaxHeight(500);
        System.out.println(tiles.size());
        for (int rows = 0; rows < size; rows++)
        {
            for (int cols = 0; cols < size; cols++) {
                for (int i = 0; i < tiles.size(); i++) {
                    if (rows == player.getX() && cols == player.getY()) {
                        grid.add(minerimage, cols, rows);
                        break;
                    } else if (tiles.get(i).getX() == rows && tiles.get(i).getY() == cols) {
                        if (tiles.get(i).getIcon() == "B") {
                            ImageView beaconimage = new ImageView(beacon);
                            beaconimage.setFitWidth(50);
                            beaconimage.setFitHeight(50);
                            grid.add(beaconimage, cols, rows);
                        } else if (tiles.get(i).getIcon() == "G") {
                            grid.add(goldimage, cols, rows);
                        } else if (tiles.get(i).getIcon() == "P") {
                            ImageView pitimage = new ImageView(pit);
                            pitimage.setFitHeight(50);
                            pitimage.setFitWidth(50);
                            grid.add(pitimage, cols, rows);
                        } else if (tiles.get(i).getIcon() == "-") {
                            ImageView grassimage = new ImageView(grass);
                            grassimage.setFitHeight(50);
                            grassimage.setFitWidth(50);
                            grid.add(grassimage, cols, rows);
                            break;
                        }

                        break;
                    }

                }
            }
        }
        grid.setAlignment(Pos.CENTER);
        anchor.getChildren().add(grid);

    }
}
