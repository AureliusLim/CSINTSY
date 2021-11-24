package fxfiles;
import base.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class randscene {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    TextField boardsize;
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
        GridPane pane = new GridPane();
        ArrayList<Tiles> tiles = g.getTile();
        Player player = g.getPlayer();
        pane.setHgap(5);
        pane.setVgap(5);
        Image miner = new Image("miner-pic.jpg");
        Image grass = new Image("grass.png");
        Image pitimage = new Image("pit.jpg");
        Image gold = new Image("gold.png");
        Image beacon = new Image("beacon.png");
        for (int rows = 0; rows < size; rows++)
        {
            for (int cols = 0; cols < size; cols++)
            {
                for (int i = 0; i < tiles.size(); i++)
                {
                    if (rows == player.getX() && cols == player.getY())
                    {
                        pane.add(new ImageView(miner),cols, rows);break;
                    }
                    else if (tiles.get(i).getX() == rows && tiles.get(i).getY() == cols)
                    {
                       if(tiles.get(i).getIcon() == "B"){
                           pane.add(new ImageView(beacon),cols,rows);
                       }
                       else if (tiles.get(i).getIcon() == "G"){
                           pane.add(new ImageView(gold), cols, rows);
                       }

                        break;
                    }
                    if (i == tiles.size() - 1){
                        pane.add(new ImageView(grass),cols,rows); break;
                    }
                }
            }
            System.out.println();
        }

    }
}
