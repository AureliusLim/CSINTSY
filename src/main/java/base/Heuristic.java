package base;

import java.util.LinkedList;

public class Heuristic{

    private LinkedList<Node> node = new LinkedList<Node>();
    private int depth;
    private Node top;
    private Player player;
    private State goalState;

    public Heuristic(Player player,Golden gold)
    {
        this.player= player;
        this.depth=0;
        node.addFirst(new Node(player,null,null,depth));
        top = node.getFirst();

        this.goalState = new State(gold.getX(),gold.getY(),0);
    }


    public void adjaecent()     //not sure how
    {
        node.add(new Node(player,top,"move",depth));
    }



    // public
    // {
    //   loop until rotation= 1 again

    //   scan

    //   if no pit  move
    //     rotate
    //     repeat

    //   if pit
    //     counter +1
    //     rotate
    //     scan

    //     if counter=4
    //       move one
    //       repeat rotate scanning

    //     if gold or beacon
    //       go straight


    //     //all tiles like beacon
    //     every step gives distance(regardless of pit)
    //     make array to save all possibles tiles

    //     succeeding step will give new distance and compare with array

    //     if same distance
    //       keep
    //     else
    //       trash


    //     scan adjaecent

    // }





}
