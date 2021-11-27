package fxfiles;
import base.*;
import javafx.animation.AnimationTimer;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.util.ArrayList;

public class smartscene {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private GameManager g;
    private ImageView minerimage;
    @FXML
    TextField boardsize;
    @FXML
    AnchorPane anchor;
    @FXML
    GridPane grid;
    @FXML
    Text moves;
    @FXML
    Text rotates;
    @FXML
    Text scans;
    @FXML
    Text modes;
    @FXML
    AnchorPane inneranchor;
    @FXML
    ScrollPane scroll;
    public void switchTomain(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void moveMiner(ActionEvent e) throws IOException {
        int size;
        size = Integer.parseInt(boardsize.getText());

        GameManager g = new GameManager(size);
        this.g = g;
        ArrayList<Tiles> tiles = g.getTile();
        Player player = g.getPlayer();
        //GridPane grid = new GridPane();

        System.out.println("GOLDPOS:"+g.getGenerate().getGold().getX() + " " + g.getGenerate().getGold().getY());
        grid.setGridLinesVisible(true);
        grid.setPrefHeight(500);
        grid.setPrefWidth(500);
        grid.setMinHeight(500);
        grid.setMinWidth(500);
        grid.setMaxWidth(500);
        grid.setMaxHeight(500);

        Image miner = new Image("file:src/miner-pic.jpg");
        Image gold = new Image("file:src/gold.png");
        Image grass = new Image("file:src/grass.png");
        Image beacon = new Image("file:src/beacon.png");
        Image pit = new Image("file:src/pit.jpg");
        ImageView minerimage = new ImageView(miner);
        ImageView goldimage = new ImageView(gold);

        minerimage.setFitHeight(50);
        minerimage.setFitWidth(50);

        goldimage.setFitHeight(50);
        goldimage.setFitWidth(50);

        while(grid.getRowConstraints().size() > 0){
            grid.getRowConstraints().remove(0);
        }

        while(grid.getColumnConstraints().size() > 0){
            grid.getColumnConstraints().remove(0);
        }


        for (int rows = 0; rows < size; rows++) {
            for (int cols = 0; cols < size; cols++) {
                for (int i = 0; i < tiles.size(); i++) {
                    if (rows == player.getX() && cols == player.getY()) {
                        grid.add(minerimage, cols,rows);
                        this.minerimage = minerimage;
                        break;
                    } else if (tiles.get(i).getX() == rows && tiles.get(i).getY() == cols) {
                        if (tiles.get(i).getIcon() == "B") {
                            ImageView beaconimage = new ImageView(beacon);
                            beaconimage.setFitWidth(50);
                            beaconimage.setFitHeight(50);
                            grid.add(beaconimage, cols,rows);
                        } else if (tiles.get(i).getIcon() == "G") {
                            grid.add(goldimage, cols,rows);
                        } else if (tiles.get(i).getIcon() == "P") {
                            ImageView pitimage = new ImageView(pit);
                            pitimage.setFitHeight(50);
                            pitimage.setFitWidth(50);
                            grid.add(pitimage, cols,rows);
                        } else if (tiles.get(i).getIcon() == "-") {
                            ImageView grassimage = new ImageView(grass);
                            grassimage.setFitHeight(50);
                            grassimage.setFitWidth(50);
                            grid.add(grassimage, cols,rows);
                            break;
                        }

                        break;
                    }

                }
            }
        }
        grid.setAlignment(Pos.BASELINE_LEFT);
        inneranchor.setPrefWidth(50 * size + 25);
        inneranchor.setPrefHeight(50 * size + 25);
        scroll.setPrefWidth(800);
        scroll.setPrefHeight(500);
        if (!modes.getText().equals("Slow") && !modes.getText().equals("Fast")){
            modes.setText("Slow");
        }

        int x=0,i;
        AnimationTimer timer = new TimerMethod();
        timer.start();


    }
    public void slowDown(ActionEvent event){
        modes.setText("Slow");
    }
    public void speedUp(ActionEvent event){
        modes.setText("Fast");
    }
    private class TimerMethod extends AnimationTimer {
        //define the handle method
        @Override
        public void handle(long now) {

            handlee();
        }

        private void handlee() {
            int x = 0, i;

            g.smart();
            int newdirect;
            newdirect  = g.getPlayer().getDirection();
            if (newdirect == 1){
                minerimage.setRotate(90);
            }
            if(newdirect == 2){
                minerimage.setRotate(180);
            }
            if(newdirect == 3){
                minerimage.setRotate(270);
            }
            if(newdirect == 4){
                minerimage.setRotate(0);
            }
            //minerimage.setRotate(90);
            if (modes.getText().equals("Slow")){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    // TODO Auto-generated catch block
                    ex.printStackTrace();
                }
            }
            else if (modes.getText().equals("Fast")){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    // TODO Auto-generated catch block
                    ex.printStackTrace();
                }
            }

            grid.getChildren().remove(minerimage);
            grid.add(minerimage,g.getPlayer().getY(), g.getPlayer().getX());
            moves.setText(Integer.toString(g.getmoveCount()));
            rotates.setText(Integer.toString(g.getrotateCount()));
            scans.setText(Integer.toString(g.getscanCount()));



            x = g.SpecialTile();

            if(x==1)
            {
                for(i=0;i<g.getGenerate().getBeacons().size();i++)
                {       //beacon
                    if(g.getGenerate().getBeacons().get(i).getX() == g.getPlayer().getX() &&g.getGenerate().getBeacons().get(i).getY()==g.getPlayer().getY())
                    {
                        System.out.printf("Distance from G = %d", g.getGenerate().getBeacons().get(i).Distance(g.getGenerate().getGold(),g.getGenerate().getPits()));
                    }
                }
            }

            if (x == 3)
            {
                stop();

                System.out.println("Animation stops here:"+ x);
            }
            else if (x == 2){
                stop();
                System.out.println("Animation stops here:"+ x);
            }
        }
    }
}
