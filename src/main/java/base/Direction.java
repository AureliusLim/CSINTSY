package base;
public class Direction
{
  private int gN,hN;
  private int dir;
  
  public Direction(int gN,int hN,int dir){
    this.gN=gN;
    this.hN=hN;
    this.dir=dir;
  }

  public int gethN()
  {
    return this.hN;
  }

  public int getgN()
  {
    return this.gN;
  }

  public int getTotal()
  {
    return this.gN + this.hN;
  }

  public void sethN(int n)
  {
    this.hN=n;
  }

  public void setgN(int n)
  {
    this.gN=n;
  }
    
  public int getDir()
  {
    return dir;
  }
}
