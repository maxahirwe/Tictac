package com.hillygeeks.tictac;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Max on 13/4/2016.
 */
public class TicTac3Board {

    //represents the 9 slots that comply a 3*3 Tic Tac Toe game
    TicTac3ArrayList<slot> slots;
    static private Random randomizer = new Random();

    static int PcPlayerID = 1;
    static int HumanPlayerID = 2;
    static int Lastmoveplayer;


    public TicTac3Board() {
        //Instantiate the game slots
        slots = new TicTac3ArrayList<slot>();
        //fill the TicTac3ArrayList with empty slots
        for (int i = 1; i < 10; i++) {
            slots.add(new slot());
            Log.v("slots creation", "slot created" + i);
        }

        Log.v("Slots size=>", String.valueOf(slots.size()));
    }

    /**
     * fill the TicTacToe board slots
     *
     * @param slotnumber
     * @return true if the game is Finished or False if its still continuous
     */

    public boolean FillSlot(int slotnumber, int player) {


        //reduce 1 since arraylist indexing start from 0
        slotnumber -= 1;
        //first get the requested slo
        slot slot = slots.get(slotnumber);

        //checks if slot is occupied or not then make move
        //checks if the user is allow to make this move by making sure he doesn't perform two consecutive moves
        //if(!slot.isOccupied() && this.Lastmoveplayer!=player){

        if (!slot.isOccupied()) {
            slot.setOccupant(new Integer(player));
            Lastmoveplayer = player;

            //if condition when human plays then let artificial intelligence do its job


        return true;
        } else {

            return false;
        }
    }

    /**
     * function make the first pc automated move
     */
    public void MakeFirstPCRandomMove() {
        Integer min = new Integer(1);
        Integer max = new Integer(2);
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int Randomslot = randomizer.nextInt((max - min) + 1) + min;


        //make the move,2 means its the pc playing
        this.FillSlot(Randomslot, 2);



    }

    /**
     * clear the whole 3*3 board
     */
    public void clear() {

        slots.clear();
        for (int i = 1; i < 10; i++) {
            slots.add(new slot());
        }

    }



}


/**
 * create by Max Thierry
 * class extends & limits a Java ArrayList to hold 9 slots only
 */
class TicTac3ArrayList<object> extends ArrayList<object> {


    @Override
    public boolean add(object e) {
        if (this.size() < 10) {
            return super.add(e);
        }
        return false;
    }


}
