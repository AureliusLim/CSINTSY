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
        node.addFirst(new Node(player,null,null,depth));

        top = node.getFirst();

        this.goalState.add(new State(gold.getX(),gold.getY(),1));
        this.goalState.add(new State(gold.getX(),gold.getY(),2));
        this.goalState.add(new State(gold.getX(),gold.getY(),3));
        this.goalState.add(new State(gold.getX(),gold.getY(),4));
    }

    // public void children()
    // {
    //   this.depth++;
    //   node.add(new Node(player,top,"Move",depth));
    //   node.add(new Node(player,top,"Scan",depth));
    //   node.add(new Node(player,top,"Rotate",depth));

    // }

    public void decision(String action, int n)
    {
        if(action=="Move")
        {
            node.add(new Node(player,top,"Scan",depth));
            node.add(new Node(player,top,"Rotate",depth));
            this.player.move(this.player.getDirection(), n);
            node.add(new Node(player,top,"Move",depth));
            top=node.getLast();
        }

        else if(action=="Scan")
        {
            node.add(new Node(player,top,"Move",depth));
            node.add(new Node(player,top,"Rotate",depth));
            node.add(new Node(player,top,"Scan",depth));
            top=node.getLast();
        }

        else if(action=="Rotate")
        {
            node.add(new Node(player,top,"Move",depth));
            node.add(new Node(player,top,"Scan",depth));
            this.player.rotate();
            node.add(new Node(player,top,"Rotate",depth));
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

}
