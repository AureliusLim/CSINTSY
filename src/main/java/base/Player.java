package base;
import java.util.*;
public class Player{
  private int x;
  private int y;
  private int direction; 
  
  public Player (int x, int y)
  {
    this.direction = 1;
  } 

                  //is direction needed?
 public void move(int direction, int n)
  {
    if(direction==1 && this.y+1 < n && this.y+1 >= 0)     //right
      setY(this.y+1);

    else if(direction==2 && this.x+1 < n && this.x+1 >= 0)     //down
      setX(this.x+1);

    else if(direction==3 && this.y-1 < n && this.y-1 >= 0)     //left
      setY(this.y-1);

    else if(direction==4 && this.x-1 < n && this.x-1 >= 0)     //up
      setX(this.x-1);
  }
  public void rotate(){     //clockwise rotation

    if(this.direction<4){
      this.direction++;
    }
    else{
      this.direction=1;
    }
  }
  public Tiles occupied(int x, int y, ArrayList<Tiles> tiles)
  {
    for (int i = 0; i < tiles.size(); i++)
    {
      if (tiles.get(i).getX() == x && tiles.get(i).getY() == y) // if x and y is equal to a tile pos, then it is occupied
      {
        return tiles.get(i);
      }
    }
    return null;
  }

  public String getInstance(Tiles tile){
    
    if(tile instanceof Golden){
      return "g";
    }
    else if(tile instanceof Pit){
      return "p";
    }
    else if(tile instanceof Beacon){
      return "b";
    }

    return "NULL";
  }

  public String scan(int N, GameManager gm)
  {
    int row,col;

    if(this.direction==1)     //right
    {
      for(col=this.y+1;col<N;col++)
      {
        if(!this.getInstance(gm.getTile(this.getX(),col)).equals("NULL")){
          return this.getInstance(gm.getTile(this.getX(), col));
        }
      }
    }

    else if(direction==2)     //down
    {
      for(row=this.getX()+1;row<N;row++)
      {
        if(!this.getInstance(gm.getTile(row,this.getY())).equals("NULL")){
          return this.getInstance(gm.getTile(row, this.getY()));
        } 
      }
    }

    else if(direction==3)     //left
    {
      for(col=this.getY()-1;col>=0;col--)
      {
        // if (check for tiles in (x-i,y))
        if(!this.getInstance(gm.getTile(this.getX(),col)).equals("NULL")){
          return this.getInstance(gm.getTile(this.getX(), col));
        } 
      }
    }

    else if(direction==4)     //up
    {
      for(row=this.getX();row>=0;row--)
      {
        // if (check for tiles in (x,y+i))
        if(!this.getInstance(gm.getTile(row,this.getY())).equals("NULL")){
          return this.getInstance(gm.getTile(row,this.getY()));
        }
      }
    }

    return "NULL";
  }

  public int getX(){
    return this.x;
  }

  public int getY(){
    return this.y;
  }

  public void setX(int x)
  {
    this.x = x;
  }

  public void setY(int y)
  {
    this.y = y;
  }

  public int getDirection()
  {
    return this.direction;
  }
  
  public void setDirection(int direction)
  {
    this.direction = direction;
  }
  

}