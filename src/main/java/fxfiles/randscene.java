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
    @FXML
    GridPane boardpane;
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
        System.out.println(size);
        GameManager g = new GameManager(size);
        ArrayList<Tiles> tiles = g.getTile();
        Player player = g.getPlayer();
        boardpane.setHgap(5);
        boardpane.setVgap(5);
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





        boardpane.setGridLinesVisible(true);
        boardpane.setPrefHeight(500);
        boardpane.setPrefWidth(500);
        boardpane.setMinHeight(500);
        boardpane.setMinWidth(500);
        boardpane.setMaxWidth(500);
        boardpane.setMaxHeight(500);
        System.out.println(tiles.size());
        for (int rows = 0; rows < size; rows++)
        {
            for (int cols = 0; cols < size; cols++)
            {
                for (int i = 0; i < tiles.size(); i++)
                {
                    if (rows == player.getX() && cols == player.getY())
                    {
                        boardpane.add(minerimage,cols, rows);break;
                    }
                    else if (tiles.get(i).getX() == rows && tiles.get(i).getY() == cols)
                    {
                       if(tiles.get(i).getIcon() == "B"){
                           ImageView beaconimage = new ImageView(beacon);
                           beaconimage.setFitWidth(50);
                           beaconimage.setFitHeight(50);
                           boardpane.add(beaconimage,cols,rows);
                       }
                       else if (tiles.get(i).getIcon() == "G"){
                           boardpane.add(goldimage, cols, rows);
                       }
                       else if (tiles.get(i).getIcon() == "P"){
                           ImageView pitimage = new ImageView(pit);
                           pitimage.setFitHeight(50);
                           pitimage.setFitWidth(50);
                           boardpane.add(pitimage,cols,rows);
                       }
                       else if (tiles.get(i).getIcon() == "-"){
                           ImageView grassimage = new ImageView(grass);
                           grassimage.setFitHeight(50);
                           grassimage.setFitWidth(50);
                           boardpane.add(grassimage,cols,rows); break;
                       }

                        break;
                    }

                }
            }
            System.out.println();
        }


    }
}
