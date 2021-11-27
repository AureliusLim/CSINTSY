package base;
import java.util.*;

public class Heuristic{
  private ArrayList<Tiles> tiles;
  private LinkedList<Node> node = new LinkedList<Node>();
  private int depth;
  private Node top; 
  private Player player;
  private ArrayList<State> goalState = new ArrayList<State>();
  private int N;

  public Heuristic(Player player,Golden gold, int N, ArrayList<Tiles> tiles)
  {
    this.player= player;
    this.depth=0;
    node.addFirst(new Node(player,null,null,0,depth));
    top = node.getFirst();
    this.N=N;
    this.tiles=tiles;


    this.goalState.add(new State(gold.getX(),gold.getY(),1));
    this.goalState.add(new State(gold.getX(),gold.getY(),2));
    this.goalState.add(new State(gold.getX(),gold.getY(),3));
    this.goalState.add(new State(gold.getX(),gold.getY(),4));

    // this.goalState = new State(gold.getX(),gold.getY(),0);
  }

  public void pitAvoid(int rotate,int cost)
  {
    this.depth++;

    if(rotate==1)
    {
      node.add(new Node(player,top,"South",cost,depth));
      node.add(new Node(player,top,"West",cost,depth));
      node.add(new Node(player,top,"North",cost,depth));
      node.add(new Node(player,top,"East",cost,depth));
      top=node.getLast();
    }

    else if(rotate==2)
    {
      node.add(new Node(player,top,"North",cost,depth));
      node.add(new Node(player,top,"West",cost,depth));
      node.add(new Node(player,top,"East",cost,depth));
      node.add(new Node(player,top,"South",cost,depth));
      top=node.getLast();
    }

    else if(rotate==3)
    {
      node.add(new Node(player,top,"North",cost,depth));
      node.add(new Node(player,top,"East",cost,depth));
      node.add(new Node(player,top,"South",cost,depth));
      node.add(new Node(player,top,"West",cost,depth));
      top=node.getLast();
    }

    else if(rotate==4)
    {
      node.add(new Node(player,top,"East",cost,depth));
      node.add(new Node(player,top,"South",cost,depth));
      node.add(new Node(player,top,"West",cost,depth));
      node.add(new Node(player,top,"North",cost,depth));
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
      top=node.getLast();
    }

    else if(action==2)
    {
      node.add(new Node(player,top,"North",dirs.get(3).getTotal(),depth));
      node.add(new Node(player,top,"East",dirs.get(0).getTotal(),depth));
      node.add(new Node(player,top,"West",dirs.get(2).getTotal(),depth));
      node.add(new Node(player,top,"South",dirs.get(1).getTotal(),depth));
      top=node.getLast();
    }

    else if(action==3)
    {
      node.add(new Node(player,top,"North",dirs.get(3).getTotal(),depth));
      node.add(new Node(player,top,"East",dirs.get(0).getTotal(),depth));
      node.add(new Node(player,top,"South",dirs.get(1).getTotal(),depth));
      node.add(new Node(player,top,"West",dirs.get(2).getTotal(),depth));
      top=node.getLast();
    }

    else if(action==4)
    {
      node.add(new Node(player,top,"South",dirs.get(1).getTotal(),depth));
      node.add(new Node(player,top,"East",dirs.get(0).getTotal(),depth));
      node.add(new Node(player,top,"West",dirs.get(2).getTotal(),depth));
      node.add(new Node(player,top,"North",dirs.get(3).getTotal(),depth));
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

  public boolean goal()
  {
    for (int i = 0; i < this.goalState.size(); i++){

      // System.out.printf("goal: %d %d %d",);
      if(this.goalState.get(i).getX() == this.player.getX() && this.goalState.get(i).getY() == this.player.getY())
      return true;
    }
    return false;
  }
}