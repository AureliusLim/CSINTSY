package base;
public class State
{
  private int x; 
  private int y;  
  private int rotation; 

  public State(int x, int y, int rotation)
  {
    this.x = x;
    this.y = y;
    this.rotation = rotation;
  }

  public int getX()
  {
    return this.x;
  }

  public int getY()
  {
    return this.y;
  }

};