package base;
import java.util.*;
import java.lang.Math;   
import java.util.Collections;

public class GameManager
{
  private ArrayList<Tiles> tiles;
  private Generate generate;
  private Player player;
  private int n;
  private DFSnode randomTree;
  private Heuristic smartTree;
  
  private String lastScan;

  private int moves,rotates,scans;

  public GameManager(int n)
  {
    this.tiles = new ArrayList<Tiles>();
    this.n = n;
    generate=new Generate(this.n, tiles);
    player = new Player(0,0);
    randomTree = new DFSnode(this.player,generate.getGold());
    smartTree = new Heuristic(this.player,generate.getGold(),n, this.tiles);

    lastScan = null;

    this.moves=0;
    this.rotates=0;
    this.scans=0;
  }


  public int GameStart(int behavior)
  {
    int x=0,i,start=1;

    this.drawBoard();
    
    do
    {
      System.out.printf("Moves: %d\nRotates: %d\nScans: %d\n",moves,rotates,scans);

      if(behavior == 1)
        this.random();
        
      else if(behavior == 2)
      {
        if(start==1)
        {
          this.lastScan = this.player.scan(this.n,this);
          this.scans++;
          start=0;
          smartTree.decision(0,null);
        }
        this.smart();
      }
        
      this.drawBoard();

      x = SpecialTile();

      if(x==1)
      {
        for(i=0;i<generate.getBeacons().size();i++)   //beacon
        {       
          if(generate.getBeacons().get(i).getX() == this.player.getX() &&generate.getBeacons().get(i).getY()==this.player.getY())
          {
            System.out.printf("Distance from G = %d\n", generate.getBeacons().get(i).Distance(generate.getGold(),this.generate.getPits(),this.n));
          }
        }

        lastScan= this.player.scan(this.n,this);
        smartTree.decision(0,null);
      }

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }while(x!=2 && x!=3);
    
    if(behavior==1)
    {
      randomTree.decision("NULL",this.n);
      if(randomTree.goal()==true)
        System.out.printf("Search Successful\n");
    }
    else if(behavior==2)
    {
      smartTree.decision(-1,null);
      if(smartTree.goal()==true)
        System.out.printf("Search Successful\n");
    }

    return x;         //return 1 if lose, 2 = win
  }

  public int SpecialTile()     //Player coords
  {
    int i;

    for(i=0;i<generate.getPits().size();i++){       //pit
      if(generate.getPits().get(i).getX() == this.player.getX() && generate.getPits().get(i).getY()==this.player.getY()){
        return 3;
      }
    }
    
    if(generate.getGold().getX()==this.player.getX() && 
      generate.getGold().getY()==this.player.getY() ){    //gold
      return 2;
    }

    for(i=0;i<generate.getBeacons().size();i++){       //beacon
      if(generate.getBeacons().get(i).getX() == this.player.getX() &&       
        generate.getBeacons().get(i).getY()==this.player.getY()){
        return 1;
      }
    }

    return 0;           //no tile
  }

  public Tiles getTile(int x, int y){
    int i;
    for(i=0;i<tiles.size();i++){
      if(tiles.get(i).getX()==x && tiles.get(i).getY()==y){
        return tiles.get(i);
      }
    }
    return null;
  }
    public ArrayList<Tiles> getTile(){
    return this.tiles;
  }
  public Player getPlayer(){
    return this.player;
  }
  public Generate getGenerate(){
    return this.generate;
  }
  public void drawBoard()
  {
    System.out.println();
    for (int rows = 0; rows < this.n; rows++)
    {
      for (int cols = 0; cols < this.n; cols++)
      {
        for (int i = 0; i < tiles.size(); i++)
        {
          if (rows == this.player.getX() && cols == this.player.getY())
          {
            System.out.print("M ");break;
          }
          else if (tiles.get(i).getX() == rows && tiles.get(i).getY() == cols)
          {
            System.out.print(tiles.get(i).getIcon()+" ");
            break;
          }
          if (i == tiles.size() - 1){
            System.out.println("- "); break;
          }
        }
      }
      System.out.println();
    }
  }

  public void random()
  {
    int r;  
    boolean valid;

    valid = validCheck(this.n);

    if(valid==true)
      r = (int)(Math.random()* (3-1+1)+1);
    else
      r=2;
    
    if(r==1)    //move
    {
      randomTree.decision("Move", this.n);
      this.moves++;
      System.out.printf("Move\n");
      System.out.printf("New pos = %d, %d",this.player.getX(),this.player.getY()); 
    }

    if(r==2)    //rotate
    {
      randomTree.decision("Rotate", this.n);
      this.rotates++;
      System.out.printf("Rotated direction = %d",this.player.getDirection());
    }

    if(r==3)    //scan
    {
      randomTree.decision("Scan", this.n);
      this.scans++;
      System.out.printf("Scan = %s",this.player.scan(n, this));     
    }
  }

  public boolean validCheck(int N)
  {
    boolean valid=true;

    if(this.player.getDirection()==4 && this.player.getX() == 0)
      valid= false;
    if(this.player.getDirection()==1 && this.player.getY() == N-1)
      valid= false;  
    if(this.player.getDirection()==2 && this.player.getX() == N-1)
      valid= false;
    if(this.player.getDirection()==3 && this.player.getY() == 0)
      valid= false;

    return valid;
  }

  public void smart()
  {
    ArrayList<Direction> dirs;
    dirs = new ArrayList<Direction>();

    dirs.add(new Direction(0,n*n,1));
    dirs.add(new Direction(0,n*n,2));
    dirs.add(new Direction(0,n*n,3));
    dirs.add(new Direction(0,n*n,4));
    
    int i;
    int numRotate=0;
    int min;
    int over;
    boolean edge=false; 
    
    min=0;
    this.smartTree.totalCost(dirs);
    
    if(this.lastScan=="g")
    {
      smartTree.decision(this.player.getDirection(),dirs);
      this.player.move(this.n);
      this.moves++;
    }

    else if(this.lastScan=="p")
    {    
    
      if(directionAvoidance()==1)
      {
        smartTree.pitAvoid(this.player.getDirection()+1,dirs,5);

        for(int k=0;k<4;k++)
        {
          this.player.rotate();
          this.rotates++;

          if(k==0){
            this.player.move(this.n);
            this.moves++;
          }           
        }
        smartTree.decision(0,dirs);
        this.lastScan = player.scan(this.n,this);
        this.scans++;
      }
      
      else if(directionAvoidance()==-1)
      {
        over=this.player.getDirection()+3;
        if(over>4)
          over-=4;
        smartTree.pitAvoid(over,dirs,5);

        for(int k=0;k<4;k++)
        {
          if(k==3){
            this.player.move(this.n);
            this.moves++;
          }   

          this.player.rotate();
          this.rotates++;
        }
        smartTree.decision(0,dirs);
        this.lastScan = player.scan(this.n,this);
        this.scans++;
      }
       
    }
    
    else
    {
      for(i=0;i<4;i++)
      {
        for(int j=i+1;j<4;j++)
        {
          if((dirs.get(j).gethN() + dirs.get(j).getgN())<(dirs.get(min).gethN() + 
            dirs.get(min).getgN())){
            min=dirs.get(j).getDir()-1;
          }
        }
      }

      //for tied totalCost
      for(i=0;i<4;i++)
      {
        if(min != i)
        {
          if(dirs.get(min).gethN() + dirs.get(min).getgN() == 
            dirs.get(i).gethN() + dirs.get(i).getgN())
          {
            if(dirs.get(min).gethN() > dirs.get(i).gethN() )
            {
              min=i;
            }
          }
        }
      }

      smartTree.decision(min+1,dirs);
      // System.out.println("GET ACTION:" + smartTree.getTop().getAction());
      // System.out.println("GET DIRECCTION:" + player.getDirection());
      if(((player.getDirection() == 1 && smartTree.getTop().getAction() == "East")||
          (player.getDirection() == 2 && smartTree.getTop().getAction() == "South")||
          (player.getDirection() == 3 && smartTree.getTop().getAction() == "West")||
          (player.getDirection() == 4 && smartTree.getTop().getAction() == "North"))) 
      {
        this.player.move(this.n);
        this.moves++;
      }

      else
      {
        numRotate = Math.abs(player.getDirection()) - min +1 ;
        
        for(int k=0;k<numRotate;k++)
        {
          this.player.rotate();
          this.rotates++;
        }
          
        smartTree.decision(0,dirs);
        this.lastScan = player.scan(this.n,this);
        this.scans++;
      }
    }
  }

  public int directionAvoidance()
  {
    if(this.player.getDirection()==1)
    {
      if(this.player.getX()<=this.n/2)
        return 1;
      else
        return -1;
    }
          
    else if(this.player.getDirection()==2)
    {
      if(this.player.getY()<=this.n/2)
        return -1;
      else
        return 1;
    }

    else if(this.player.getDirection()==3)
    {
      if(this.player.getX()<=this.n/2)
        return -1;
      else
        return 1;
    }

    else if(this.player.getDirection()==4)
    {
      if(this.player.getY()<=this.n/2)
        return 1;
      else
        return -1;
    }
    return 0;
  }

  public DFSnode getrandTree(){
    return this.randomTree;
  }
  public Heuristic getsmartTree(){
    return this.smartTree;
  }
  public int getmoveCount(){
    return this.moves;
  }
  public int getrotateCount(){
    return this.rotates;
  }
  public int getscanCount(){
    return this.scans;
  }
}
