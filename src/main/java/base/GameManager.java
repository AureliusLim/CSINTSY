package base;

import java.util.*;
import java.lang.Math;
public class GameManager
{
    private ArrayList<Tiles> tiles;
    private Generate generate;
    private Player player;
    private int n;
    private DFSnode randomTree;

    public GameManager(int n)
    {
        this.tiles = new ArrayList<Tiles>();
        this.n = n;
        generate=new Generate(this.n, tiles);
        player = new Player(1,1);
        randomTree = new DFSnode(this.player,generate.getGold());
    }

    public int Control(int move)
    {
        int x=0,i;

        if(move==1 && this.player.getY()+1 < n && this.player.getY()+1 >= 0)     //right
            this.player.setY(this.player.getY()+1);

        else if(move==2 && this.player.getX()+1 < n && this.player.getX()+1 >= 0)     //down
            this.player.setX(this.player.getX()+1);

        else if(move==3 && this.player.getY()-1 < n && this.player.getY()-1 >= 0)     //left
            this.player.setY(this.player.getY()-1);

        else if(move==4 && this.player.getX()-1 < n && this.player.getX()-1 >= 0)     //up
            this.player.setX(this.player.getX()-1);


        x = SpecialTile();

        if(x==3)
        {
            for(i=0;i<generate.getBeacons().size();i++)
            {       //beacon
                if(generate.getBeacons().get(i).getX() == this.player.getX() &&generate.getBeacons().get(i).getY()==this.player.getY())
                {
                    System.out.printf("Distance from G = %d", generate.getBeacons().get(i).Distance(generate.getGold(), generate.getPits()));
                }
            }
        }

        this.drawBoard();
        return x;
    }

    public int GameStart()
    {
        int x=0,i;
        this.drawBoard();

        // this.player.Move(x);

        // this.drawBoard();

        // this.player.Rotate();
        // this.drawBoard();
        // this.player.Move(x);
        // this.drawBoard();

        do
        {
            this.random();

            this.drawBoard();

            x = SpecialTile();

            if(x==3)
            {
                for(i=0;i<generate.getBeacons().size();i++)
                {       //beacon
                    if(generate.getBeacons().get(i).getX() == this.player.getX() &&generate.getBeacons().get(i).getY()==this.player.getY())
                    {
                        System.out.printf("Distance from G = %d", generate.getBeacons().get(i).Distance(generate.getGold(),this.generate.getPits()));
                    }
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }while(x!=1 && x!=2);

        // return 2;
        return x;         //return 1 if lose, 2 = win
    }

    public int SpecialTile()     //Player coords
    {
        int i;

        for(i=0;i<generate.getPits().size();i++){       //pit
            if(generate.getPits().get(i).getX() == this.player.getX() && generate.getPits().get(i).getY()==this.player.getY()){
                return 1;
            }
        }

        if(generate.getGold().getX()==this.player.getX() && generate.getGold().getY()==this.player.getY() ){    //gold
            return 2;
        }

        for(i=0;i<generate.getBeacons().size();i++){       //beacon
            if(generate.getBeacons().get(i).getX() == this.player.getX() && generate.getBeacons().get(i).getY()==this.player.getY()){
                return 3;
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
            //if(this.player.getX()>=0 && this.player.getX()<=this.n && this.player.getY()>=0   && this.player.getY()<=this.n)
            System.out.printf("Move\n");
            //System.out.printf("New pos = %d, %d",this.player.getX(),this.player.getY());

        }

        if(r==2)    //rotate
        {
            randomTree.decision("Rotate", this.n);
            System.out.printf("\nRotated direction = %d",this.player.getDirection());

        }

        if(r==3)    //scan
        {
            randomTree.decision("Scan", this.n);
            //System.out.printf("Scan = %s",this.player.scan(n, this));

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

  /*public void smart(int N,int counter,int loop){         //scan first move
    int i;

    Tiles occupiedTile=this.player.occupied();
    String scan = this.player.Scan(n,tiles);
    counter+=1;


    if(scan=="NULL")
    {
      if(this.player.getDirection()==1)
      {
        for(i=this.player.getX();i<N;i++)
        {
          this.player.Move();
          counter+=1;
        }
        player.Rotate();
        counter+=1;
      }

      if(this.player.getDirection()==2)
      {
        for(i=this.player.getY();i<N;i++)
        {
          this.player.Move();
          counter+=1;
        }
        player.Rotate();
        counter+=1;
      }
      if(this.player.getDirection()==3)
      {
        for(i=this.player.getX();i>0;i--)
        {
          this.player.Move();
          counter+=1;
        }
        player.Rotate();
        counter+=1;
      }
      if(this.player.getDirection()==4)
      {
        for(i=this.player.getY();i>0;i--)
        {
          this.player.Move();
          counter+=1;
        }
        player.Rotate();
        counter+=1;
      }
    }

    if(scan == "b")
    {
      while(this.player.getInstance(occupiedTile)!="b"){
        this.player.Move();
        counter+=1;
      }

    }

    else
    {
      player.Rotate();
      this.player.Move();
      counter+=1;
    }




      if(this.player.Scan(n,tiles)!="p"){

        player.Rotate();
        this.player.Move();
        player.Rotate();
        player.Rotate();
        player.Rotate();
        counter+=5;
      }

  }
  */


}
