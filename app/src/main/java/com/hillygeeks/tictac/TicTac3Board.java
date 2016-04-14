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

    // virtual slots for different states
    //each state has a possibility either win/draw/lose
    //ArrayList<TicTac3ArrayList<Simulatedslot>> states;

    //loop through all states and set marks
    //loop through all states and pick the one with the highest marks
    //In order to achieve this we will subtract the depth, that is the number of turns, or recursions,
    // from the end game score, the more turns the lower the score, the fewer turns the higher the score


    static private Random randomizer = new Random();

    static public int PcPlayerID = 1;
    static public int HumanPlayerID = 2;
    static public int Lastmoveplayer = 0;


    public TicTac3Board() {
        winset set1 = new winset(4, 2, 3);
        winset set2 = new winset(4, 2, 6);

        Log.v("setchecking:", "true:set1=set2 ?" + String.valueOf(set1.equals(set2)));
        set2 = new winset(4, 2, 3);
        Log.v("setchecking:", "false:equal set1=set2 ?" + String.valueOf(set1.equals(set2)));


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

            //Wining checking

            IsItaWinMove(slotnumber);
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
     *
     * funtion to be called after each slot is filled to asses if its a winning move
     */
    public boolean IsItaWinMove(int index) {

        //1 horizontal 1  123
        //2 horizantal 2  456
        //3 horizantal 2  789
        //4 vertical 1    147
        //5 vertical 2    258
        //6 vertical 3    369
        //7 diagonal 1    159
        //8 diagonal 2    357


        ArrayList<winset> winsets = new ArrayList<winset>();

        for (int i = 0; i < 9; i++) {
            winsets.add(new winset(1, 2, 3));
            winsets.add(new winset(4, 5, 6));
            winsets.add(new winset(7, 8, 9));
            winsets.add(new winset(1, 4, 7));
            winsets.add(new winset(2, 5, 8));
            winsets.add(new winset(3, 6, 9));
            winsets.add(new winset(1, 5, 9));
            winsets.add(new winset(3, 5, 7));
        }

        ///loop trough all the winsets and identify a match
        for (winset winingset : winsets) {
            //loop for both players
            for (int player = 1; player <= 2; player++) {
                //Log.v("winchecking", "Slot(" + (index + 1) + ") " + "player " + player);
                //for the i value
                for (int i = 0; i < 9; i++) {
                    //for the x value
                    for (int x = 0; x < 9; x++) {
                        //check if the combination is filled by the same player
                        if (this.IsslotfilledByplayer(i, player) && this.IsslotfilledByplayer(x, player)
                                && this.IsslotfilledByplayer(index, player) && i != index && x != index && x != i) {

                            //add one since stored winsets data starting from 1

                            winset toCompare1 = new winset(i + 1, x + 1, index + 1);
                            winset toCompare2 = new winset(x + 1, i + 1, index + 1);
                            winset toCompare3 = new winset(index + 1, x + 1, i + 1);

                            //Log.v("Gamestatus", "combination found ("+toCompare1.toString() +") ("+toCompare2.toString()+") ("+toCompare3.toString() +")  player" +player);
                            //check if its a wining set
                            if (winingset.equals(toCompare1) || winingset.equals(toCompare2) || winingset.equals(toCompare3)) {
                                Log.v("Gamestatus", "Game won by (" + player + ") on slot (" + (index + 1) + ")");

                                return true;

                            }
                        }
                    }
                }
                //Log.v("Gamestatus","Game not won by ("+player+") on slot"+(index + 1) +" Keep going...");
            }
        }

        return false;

    }


    public boolean IsslotfilledByplayer(int index, int player) {
        //check if the passed in player is occupying the slot of the provided index

        if (slots.get(index).getOccupant() == null) {

            return false;

        }

        if (slots.get(index).getOccupant() == player) {

            return true;


        }

        return false;
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

/**
 * hols the combination of 3 slots ,candidating for a game win
 */
class winset {

    ArrayList<Integer> slotset = new ArrayList<Integer>();

    public winset(int a, int b, int c) {
        slotset.add(a);
        slotset.add(b);
        slotset.add(c);
    }


    public ArrayList<Integer> getSlotset() {
        return slotset;
    }

    public void setSlotset(ArrayList<Integer> slotset) {
        this.slotset = slotset;
    }

    public int getSlot(int index) {
        return slotset.get(index);
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof winset) {
            winset toCompare = (winset) o;
            if (this.getSlot(0) == toCompare.getSlot(0) && this.getSlot(1) == toCompare.getSlot(1) && this.getSlot(2) == toCompare.getSlot(2))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "winset{" + "slotset=" + slotset + '}';
    }
}
