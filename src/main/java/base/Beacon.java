package base;
import java.util.*;
import java.lang.*;

public class Beacon extends Tiles{ 

  //A beacon on a square indicates that from that square, the golden square can be reached in m squares in any vertical or horizontal direction, where m < n, without ever falling into a pit. The value of m is not returned by the action scan.

  public Beacon(int x,int y,String icon,int distance)
  {
    super(x,y,icon,distance);
  }
 
 //need pit array
  public int Distance(Golden Gold, ArrayList<Pit> pits)
  {
    int n=0;
    int X = this.x;
    int Y = this.y; 

    if(this.allignRow(X,Y,Gold, pits) == true && this.allignCol(X,Y,Gold, pits) == true)
      n = Math.abs(this.x - Gold.getX()) + Math.abs(this.y - Gold.getY());
    else if(this.allignCol(X,Y,Gold,pits) == true && this.allignRow(X,Y,Gold,pits) == true)
      n = Math.abs(this.x - Gold.getX()) + Math.abs(this.y - Gold.getY());

    else if (this.forceX(X,Y,Gold,pits) == true && this.forceY(X,Y,Gold,pits) == true){
      n = Math.abs(this.x - Gold.getX()) + Math.abs(this.y - Gold.getY());
    }
    else if (this.forceY(X,Y,Gold,pits) == true && this.forceX(X,Y,Gold,pits) == true){
      n = Math.abs(this.x - Gold.getX()) + Math.abs(this.y - Gold.getY());
    }
    return n;
  }
  
  private boolean forceX(int X, int Y, Golden Gold, ArrayList<Pit> pits){
    boolean minus = false;
    boolean add = false;
    boolean detour = false;
    do{
      minus = false;
      add = false;
      detour = false;
      if (X > Gold.getX())
      {
        minus = true;
        X--;
      }
      else
      {
        add = true;
        X++;
      }

      for (int i = 0 ; i < pits.size(); i++){
        if (pits.get(i).getX() == X && pits.get(i).getY() == Y){
            if(add == true)
            {
              X--;
              break;
            }
            else if( minus == true)
            {
              X++;
              break;
            }
            detour = true;
        }
      }
      if (detour == true){
        if (Y > Gold.getY()){
          Y--;
        }
        else if (Y < Gold.getY()){
          Y++;
        }
        for (int i = 0 ; i < pits.size(); i++){
          if (pits.get(i).getX() == X && pits.get(i).getY() == Y){
              return false;
          }
        } 
      }
      
    }while(X != Gold.getX());
    return true;
  }
 private boolean forceY(int X, int Y, Golden Gold, ArrayList<Pit> pits){
    boolean minus = false;
    boolean add = false;
    boolean detour = false;
    do{
      minus = false;
      add = false;
      detour = false;
      if (Y > Gold.getY())
      {
        minus = true;
        Y--;
      }
      else
      {
        add = true;
        Y++;
      }

      for (int i = 0 ; i < pits.size(); i++){
        if (pits.get(i).getX() == X && pits.get(i).getY() == Y){
            if(add == true)
            {
              Y--;
              break;
            }
            else if( minus == true)
            {
              Y++;
              break;
            }
            detour = true;
        }
      }
      if (detour == true){
        if (X > Gold.getX()){
          Y--;
        }
        else if (X < Gold.getX()){
          Y++;
        }
        for (int i = 0 ; i < pits.size(); i++){
          if (pits.get(i).getX() == X && pits.get(i).getY() == Y){
              return false;
          }
        } 
      }
      
    }while(Y != Gold.getY());
    return true;
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