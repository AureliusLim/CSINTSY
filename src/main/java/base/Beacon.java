package base;
import java.util.*;

public class Beacon extends Tiles{ 

  //A beacon on a square indicates that from that square, the golden square can be reached in m squares in any vertical or horizontal direction, where m < n, without ever falling into a pit. The value of m is not returned by the action scan.

  public Beacon(int x,int y,String icon,int distance)
  {
    super(x,y,icon,distance);
  }
 
 //need pit array
  public int Distance(Golden Gold, ArrayList<Pit> pits,int n)
  {
    int X = this.x;
    int Y = this.y; 

    if(this.allignRow(X,Y,Gold, pits) == true && this.allignCol(X,Y,Gold, pits) == true)
      return (Math.abs(this.x - Gold.getX()) + Math.abs(this.y - Gold.getY()));
    
    X = this.x;
    Y = this.y; 

    if(this.allignCol(X,Y,Gold,pits) == true && this.allignRow(X,Y,Gold,pits) == true)
      return (Math.abs(this.x - Gold.getX()) + Math.abs(this.y - Gold.getY()));


    for(int i=1;i<n;i++)
    {
      X = this.x+i;
      Y = this.y; 

      if(this.allignRow(X,Y,Gold, pits) == true && this.allignCol(X,Y,Gold, pits) == true)
        return (Math.abs(this.x - Gold.getX()) + Math.abs(this.y - Gold.getY()))+i;
      
      X = this.x;
      Y = this.y+i; 

      if(this.allignCol(X,Y,Gold,pits) == true && this.allignRow(X,Y,Gold,pits) == true)
        return (Math.abs(this.x - Gold.getX()) + Math.abs(this.y - Gold.getY()))+i;

      X = this.x-i;
      Y = this.y; 

      if(this.allignRow(X,Y,Gold, pits) == true && this.allignCol(X,Y,Gold, pits) == true)
        return (Math.abs(this.x - Gold.getX()) + Math.abs(this.y - Gold.getY()))+i;
      
      X = this.x;
      Y = this.y-i; 

      if(this.allignCol(X,Y,Gold,pits) == true && this.allignRow(X,Y,Gold,pits) == true)
        return (Math.abs(this.x - Gold.getX()) + Math.abs(this.y - Gold.getY()))+i;
    }

    return 0;
  }
  

  private boolean allignRow(int X,int Y,Golden Gold, ArrayList<Pit> pits)
  {      
      do{   
        if(X>Gold.getX())
          X--;
        else if (X < Gold.getX())
          X++;

        for (int i = 0; i < pits.size(); i++)
        {
          if (pits.get(i).getX() == X && pits.get(i).getY() == Y)
            return false;
        }

    }while(X!=Gold.getX());

    return true;
  }

  private boolean allignCol(int X,int Y,Golden Gold, ArrayList<Pit> pits)
  {      
    do{
    
      if(Y>Gold.getY())
        Y--;
      else if (Y<Gold.getY())
        Y++;

      for (int i = 0; i < pits.size(); i++)
      {
        if (pits.get(i).getX() == X && pits.get(i).getY() == Y)
          return false;
      }

    }while(Y!=Gold.getY());

    return true;
  }
}