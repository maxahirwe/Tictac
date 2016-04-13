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

    static public int PcPlayerID = 1;
    static public int HumanPlayerID = 2;
    static public int Lastmoveplayer = 0;


    public TicTac3Board() {
        //Instantiate the game slots
        slots = new TicTac3ArrayList<slot>();
        //fill the TicTac3ArrayList with empty slots
        for (int i = 0; i < 9; i++) {
            slot newslot = new slot();
            slots.add(newslot);
            Log.v("slotdata", "Slot(" + (i + 1) + ") " + newslot.toString());
        }

        Log.v("Slots size:", String.valueOf(slots.size()));
    }

    /**
     * fill the TicTacToe board slots
     * @param slotnumber
     * @return true if the game is Finished or False if its still continuous
     */

    public boolean FillSlot(int slotnumber, int player) {

        String playername = player == 1 ? "Computer" : "Human";
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

            Log.v("move", "Player:" + playername + " move to slot(" + (slotnumber + 1) + ") Succeeded");
            Log.v("slotdata", "Slot(" + (slotnumber + 1) + ") " + slot.toString());
            return true;

        } else {

            Log.v("move", "Player:" + playername + " move to slot(" + (slotnumber + 1) + ") Failed");
            Log.v("slotdata", "Slot(" + (slotnumber + 1) + ") " + slot.toString());
            return false;
        }


    }

    /**
     * function make the first pc automated move
     */
    public int MakeFirstPCRandomMove() {
        int Randomslot = Randomslot();
        //make the move,2 means its the pc playing Lastmoveplayer=null &&
        if (this.FillSlot(Randomslot, 1)) {
            Lastmoveplayer = 1;
            return Randomslot;

        } else {
            return -1;
        }
    }


    /**
     * Artificial Intelligence core heart of the game
     */
    public int PcFillslot() {
        int Randomslot = Randomslot();
        boolean isBoardfull = isBoardFull();

        //if the game board is not full keep looping until random slot is free ,fill it and quit the loop and return the slot number
        while (!isBoardfull) {
            if (this.FillSlot(Randomslot, 1)) {
                Lastmoveplayer = 1;
                return Randomslot;
            }
            Randomslot = Randomslot();
        }
        return -1;
    }

    /**
     * @return true id board is full and false if it's not
     */
    public boolean isBoardFull() {

        for (slot s : slots) {
            //if one of the slots is empty means there is still an empty slot
            if (!s.isOccupied()) {
                return false;
            }
        }
        return true;

    }

    /**
     * Randomize a number between 1-9
     */
    public int Randomslot() {
        Integer min = new Integer(1);
        Integer max = new Integer(9);
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int RandomSlot = randomizer.nextInt((max - min) + 1) + min;

        return RandomSlot;
    }

    /**
     * clear the whole 3*3 board
     */
    public void clear() {

        slots.clear();
        for (int i = 0; i < 9; i++) {
            slots.add(new slot());
        }

    }


    /**
     * print all slots data
     */
    public void SlotsDataPrint() {

        for (int i = 0; i < 9; i++) {
            slot slot = slots.get(i);
            Log.v("slotdata", "Slot(" + (i + 1) + ") " + slot.toString());
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
