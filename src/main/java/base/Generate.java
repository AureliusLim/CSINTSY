package base;
import java.util.*;

public class Generate{
  private Golden gold;
  private ArrayList<Beacon> beacons;
  private ArrayList<Pit> pits;

  public Generate(int n, ArrayList<Tiles> tiles)// instantiate whole board and store in specific arraylists and the general tile arraylist
  {
    int distance;
    this.determineGold(n);
    tiles.add(gold);
    this.determineBeacon(n,tiles);
    for (int i = 0 ; i < beacons.size();i++){
      tiles.add(beacons.get(i));
    }
    this.determinePit(n,tiles);
    
    for (int i = 0; i < pits.size();i++){
      tiles.add(pits.get(i));
    }
    int row = 0;
    int col = 0;
    while(tiles.size() < n * n) // fill up rest of board with normal tile
    {
      if (col == n){
        col = 0;
        row++;
      }
      else{
        if(!this.occupied(row,col,tiles)){
          distance = (Math.abs(this.gold.getX() - row) + Math.abs(this.gold.getY() - col)); 
          tiles.add(new Tiles(row,col,"-",distance));
        }
      col++;
      }
    }
  }

  public boolean occupied(int x, int y, ArrayList<Tiles> tiles)
  {
    for (int i = 0; i < tiles.size(); i++)
    {
      if (tiles.get(i).getX() == x && tiles.get(i).getY() == y) // if x and y is equal to a tile pos, then it is occupied
      {
        return true;
      }
    }
    return false;
  }

  public void determineGold(int n)
  {
    Random rand = new Random();
    int xGold = 0;
    int yGold = 0;
    while (xGold == 0 && yGold == 0) // gold cannot be on miner
    {
      xGold = rand.nextInt(n);
      yGold = rand.nextInt(n);
    }

    this.gold = new Golden(xGold,yGold,"G",0);
   
  }
  
  public void determineBeacon(int n,ArrayList<Tiles> tiles)
  {
    Random rand = new Random(new Date().getTime());
    this.beacons = new ArrayList<Beacon>();
    
    int max;
    int x,y;
    int distance;
    if ((int)(n * 0.1) == 0) // always need atleast 1 beacon
      max = 1;
    else
      max = (int)(n * 0.1); 
  
    while (this.beacons.size() < max){ 
      x = rand.nextInt(n);
      y = rand.nextInt(n);
      distance = (Math.abs(this.gold.getX() - x) + Math.abs(this.gold.getY() - y)); 
      if(distance < n && !this.occupied(x,y,tiles) && x != 0 && y != 0){ // if distance is less than n then instantiate beacon
        this.beacons.add(new Beacon(x,y,"B",distance));
      }
    }
  }

  public void determinePit(int n, ArrayList<Tiles> tiles)
  {
    Random rand = new Random(new Date().getTime());
    int max;
    int x,y;
    int distance;   
    this.pits = new ArrayList<Pit>();
    max = (int)(n * 0.25);
    
    while(this.pits.size()< max){
      x = rand.nextInt(n);
      y = rand.nextInt(n);
      distance = (Math.abs(this.gold.getX() - x) + Math.abs(this.gold.getY() - y)); 
      if (!this.occupied(x, y, tiles) && x != 0 && y != 0){
        this.pits.add(new Pit(x,y,"P",distance));
      }
    }
  }

  public ArrayList<Beacon> getBeacons(){
    return beacons;
  }
  
  public ArrayList<Pit> getPits(){
    return pits;
  }
  
  public Golden getGold(){
    return gold;
  }
}