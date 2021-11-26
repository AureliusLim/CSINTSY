package base;
public class Node
{
  private State state;
  private Node parent;
  private String action;
  private int cost;
  private int depth;

  public Node(Player player,Node parent,String action, int cost, int depth)
  {
    this.state = new State(player.getX(),player.getY(),player.getDirection());
    this.parent = parent;
    this.action = action;
    this.cost=1;
    this.depth=depth;
    
  }

  public State getState(){
    return this.state;
  }

  public String getAction()
  {
    return this.action;
  }
}