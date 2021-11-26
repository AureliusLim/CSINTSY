package base;

public class Tiles{

  protected int x;
  protected int y;
  protected String icon;
  protected int distance;

  public Tiles(int x,int y,String icon,int distance)
  {
    this.x = x;
    this.y = y;
    this.icon = icon;
    this.distance = distance;
  }

  public String getIcon(){
    return this.icon;
  }

  public int getX(){
    return this.x;
  }

  public int getY(){
    return this.y;
  }
}