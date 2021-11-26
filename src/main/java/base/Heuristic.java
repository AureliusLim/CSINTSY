package base;
import java.util.*;

public class Heuristic{
  private ArrayList<Tiles> tiles;
  private LinkedList<Node> node = new LinkedList<Node>();
  private int depth;
  private Node top; 
  private Player player;
  private State goalState;
  private int N;

  public Heuristic(Player player,Golden gold, int N, ArrayList<Tiles> tiles)
  {
    this.player= player;
    this.depth=0;
    node.addFirst(new Node(player,null,null,0,depth));
    top = node.getFirst();
    this.N=N;
    this.tiles=tiles;

    this.goalState = new State(gold.getX(),gold.getY(),0);
  }

  public void pitAvoid(int rotate)
  {
    this.depth++;
    if(rotate==1)
    {
      node.add(new Node(player,top,"North",6,depth));
      node.add(new Node(player,top,"West",6,depth));
      node.add(new Node(player,top,"East",6,depth));
      node.add(new Node(player,top,"South",6,depth));
      top=node.getLast();
    }

    else if(rotate==2)
    {
      node.add(new Node(player,top,"North",6,depth));
      node.add(new Node(player,top,"East",6,depth));
      node.add(new Node(player,top,"South",6,depth));
      node.add(new Node(player,top,"West",6,depth));
      top=node.getLast();
    }

    else if(rotate==3)
    {
      node.add(new Node(player,top,"East",6,depth));
      node.add(new Node(player,top,"South",6,depth));
      node.add(new Node(player,top,"West",6,depth));
      node.add(new Node(player,top,"North",6,depth));
      top=node.getLast();
    }

    else if(rotate==4)
    {
      node.add(new Node(player,top,"South",6,depth));
      node.add(new Node(player,top,"West",6,depth));
      node.add(new Node(player,top,"North",6,depth));
      node.add(new Node(player,top,"East",6,depth));
      top=node.getLast();
    }
  }

  public void decision(int action,ArrayList<Direction> dirs,int n)
  {
    this.depth++;

    if(action==1)
    {
      node.add(new Node(player,top,"North",dirs.get(3).getTotal(),depth));
      node.add(new Node(player,top,"South",dirs.get(1).getTotal(),depth));
      node.add(new Node(player,top,"West",dirs.get(2).getTotal(),depth));
      node.add(new Node(player,top,"East",dirs.get(0).getTotal(),depth));
      // this.player.rotate();
      top=node.getLast();
    }

    else if(action==2)
    {
      node.add(new Node(player,top,"North",dirs.get(3).getTotal(),depth));
      node.add(new Node(player,top,"East",dirs.get(0).getTotal(),depth));
      node.add(new Node(player,top,"West",dirs.get(2).getTotal(),depth));
      node.add(new Node(player,top,"South",dirs.get(1).getTotal(),depth));
      // this.player.rotate();

      top=node.getLast();
    }

    else if(action==3)
    {
      node.add(new Node(player,top,"North",dirs.get(3).getTotal(),depth));
      node.add(new Node(player,top,"East",dirs.get(0).getTotal(),depth));
      node.add(new Node(player,top,"South",dirs.get(1).getTotal(),depth));
      node.add(new Node(player,top,"West",dirs.get(2).getTotal(),depth));
      // this.player.rotate();
      top=node.getLast();
    }

    else if(action==4)
    {
      node.add(new Node(player,top,"South",dirs.get(1).getTotal(),depth));
      node.add(new Node(player,top,"East",dirs.get(0).getTotal(),depth));
      node.add(new Node(player,top,"West",dirs.get(2).getTotal(),depth));
      node.add(new Node(player,top,"North",dirs.get(3).getTotal(),depth));
      // this.player.move(this.player.getDirection(), n);
      top=node.getLast();
    }
  }

  // public double getDistance(int x, int y){
  //   int i;
  //   double distance;
  //   for(i=0;i<N;i++){
  //     if(tiles.get(i) instanceof Golden){
  //       distance= Math.sqrt(Math.pow((x-tiles.get(i).getX()),2) + 
  //                 Math.pow((y-tiles.get(i).getY()),2));
  //       return distance;
  //     }
  //   }
  // }


  //by tiles
  public int getDistance(int x,int y){
    int i;
    int distance;
    for(i=0;i<N;i++){
      if(tiles.get(i) instanceof Golden){
        distance= Math.abs(x - tiles.get(i).getX()) + 
                  Math.abs(y - tiles.get(i).getY());
        return distance;
      }
    }
    return -1;
  }

  public void adjacent(ArrayList<Direction> dirs)
  {
    int i;
    int playerX = player.getX();
    int playerY = player.getY();

    /*for(i=0;i<N*N;i++){
      if(tiles.get(i).getX()==playerX+1 && tiles.get(i).getY()==playerY){
        System.out.println("DISTANCE:" + getDistance(tiles.get(i).getX(),tiles.get(i).getY()));
        dirs.get(0).sethN(getDistance(tiles.get(i).getX(),tiles.get(i).getY()));
      }
      if(tiles.get(i).getX()==playerX-1 && tiles.get(i).getY()==playerY){
        System.out.println("DISTANCE:" +getDistance(tiles.get(i).getX(),tiles.get(i).getY()));
        dirs.get(2).sethN(getDistance(tiles.get(i).getX(),tiles.get(i).getY()));
      }
      if(tiles.get(i).getX()==playerX && tiles.get(i).getY()==playerY+1){
        System.out.println("DISTANCE:" +getDistance(tiles.get(i).getX(),tiles.get(i).getY()));
        dirs.get(1).sethN(getDistance(tiles.get(i).getX(),tiles.get(i).getY()));
      }
      if(tiles.get(i).getX()==playerX && tiles.get(i).getY()==playerY-1){
        System.out.println("DISTANCE:" +getDistance(tiles.get(i).getX(),tiles.get(i).getY()));
        dirs.get(3).sethN(getDistance(tiles.get(i).getX(),tiles.get(i).getY()));
      }    
    }*/
    if(playerX+1<N){
      
      
      dirs.get(1).sethN(getDistance(playerX+1, playerY));
      
      
    }
  
    
    if(playerX-1>=0){
      dirs.get(3).sethN(getDistance(playerX-1, playerY));
      
      
    }
    if(playerY+1<N){
      dirs.get(0).sethN(getDistance(playerX, playerY+1));
      
      
    }
    if(playerY-1>=0){
      dirs.get(2).sethN(getDistance(playerX, playerY-1));
      
    }
  }

/*
  public void hN(int straight, int side)    //check if end of board checker is good
  {
    int i;
    int playerX = player.getX();
    int playerY = player.getY();
    for(i=0;i<N;i++){
      
      if(!((this.player.getDirection()==1 && this.player.getY()==this.n)|| 
            (this.player.getDirection()==2 && this.player.getX()==this.n)|| 
            (this.player.getDirection()==3 && this.player.getY()==0)|| 
            (this.player.getDirection()==4 && this.player.getX()==0)))
        straight=getDistance(tiles.get(i).getX(),tiles.get(i).getY());
  
      else 
        straight=-1;

      else if(tiles.get(i).getX()==playerX-1 && tiles.get(i).getY()==playerY){
        valLeft=getDistance(tiles.get(i).getX(),tiles.get(i).getY());
      }
      else if(tiles.get(i).getX()==playerX && tiles.get(i).getY()==playerY+1){
        valUp=getDistance(tiles.get(i).getX(),tiles.get(i).getY());
      }
      else if(tiles.get(i).getX()==playerX && tiles.get(i).getY()==playerY-1){
        valDown=getDistance(tiles.get(i).getX(),tiles.get(i).getY());
      }    
    }
  }
*/

  public void pathCost(ArrayList<Direction> dirs)
  {
    if(this.player.getDirection() == 1)
    {
      dirs.get(0).setgN(1);
      dirs.get(1).setgN(2);
      dirs.get(2).setgN(3);
      dirs.get(3).setgN(4);
    }

    else if(this.player.getDirection() == 2)
    {
      dirs.get(0).setgN(4);
      dirs.get(1).setgN(1);
      dirs.get(2).setgN(2);
      dirs.get(3).setgN(3);
    }

    else if(this.player.getDirection() == 3)
    {
      dirs.get(0).setgN(3);
      dirs.get(1).setgN(4);
      dirs.get(2).setgN(1);
      dirs.get(3).setgN(2);
    }

    else if(this.player.getDirection() == 4)
    {
      dirs.get(0).setgN(2);
      dirs.get(1).setgN(3);
      dirs.get(2).setgN(4);
      dirs.get(3).setgN(1);
    }
  }


  public void totalCost(ArrayList<Direction> dirs)
  {
    
    this.adjacent(dirs);
    this.pathCost(dirs);
    
    
  }

  public Node getTop()
  {
    return this.top;
  }

}