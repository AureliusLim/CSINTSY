package base;
import java.util.LinkedList;
import java.util.ArrayList;
import java.lang.Math; 

public class DFSnode      //stack
{
  private LinkedList<Node> node = new LinkedList<Node>();
  private int depth;
  private Node top; 
  private Player player;
  private ArrayList<State> goalState = new ArrayList<State>();

  public DFSnode(Player player,Golden gold)
  {
    this.player= player;
    this.depth=0;
    node.addFirst(new Node(player,null,null,0,depth));
    
    top = node.getFirst();

    this.goalState.add(new State(gold.getX(),gold.getY(),1));
    this.goalState.add(new State(gold.getX(),gold.getY(),2));
    this.goalState.add(new State(gold.getX(),gold.getY(),3));
    this.goalState.add(new State(gold.getX(),gold.getY(),4));
  }

  public void decision(String action, int n)
  {
    this.depth++;
    if(action=="Move")
    {
      node.add(new Node(player,top,"Scan",1,depth));
      node.add(new Node(player,top,"Rotate",1,depth));
      this.player.move(this.player.getDirection(), n);
      node.add(new Node(player,top,"Move",1,depth));
      top=node.getLast();
    }

    else if(action=="Scan")
    {
      node.add(new Node(player,top,"Move",1,depth));
      node.add(new Node(player,top,"Rotate",1,depth));
      node.add(new Node(player,top,"Scan",1,depth));
      top=node.getLast();
    }

    else if(action=="Rotate")
    {
      node.add(new Node(player,top,"Move",1,depth));
      node.add(new Node(player,top,"Scan",1,depth));
      this.player.rotate();
      node.add(new Node(player,top,"Rotate",1,depth));
      top=node.getLast();
    }

  }

  public boolean goal()
  {
    for (int i = 0; i < this.goalState.size(); i++){
      if(this.goalState.get(i) == top.getState())
      return true;
    }
    return false;
  }
  public LinkedList<Node> getNodes(){
    return this.node;
  }
  public Node getTop() {
    return this.top;
  }
}