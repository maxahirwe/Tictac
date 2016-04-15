package com.hillygeeks.tictac;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Max on 13/4/2016.
 */
public class TicTac3Board {

    //represents the 9 slots that comply a 3*3 Tic Tac Toe game
    TicTac3ArrayList<slot> slots;
    //0 done ,1 not done
    public boolean winmove;
    public String Winmsg;
    public winset Winset;
    static public int gamestatus;
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
        //Instantiate the game slots
        slots = new TicTac3ArrayList<slot>();
        //fill the TicTac3ArrayList with empty slots
        for (int i = 0; i < 9; i++) {
            slot newslot = new slot();
            slots.add(newslot);
            Log.v("slotdata", "Slot(" + (i + 1) + ") " + newslot.toString());
        }
        Log.v("Slots size:", String.valueOf(slots.size()));

        winmove = false;
    }

    /**
     * fill the TicTacToe board slots
     *
     * @param slotnumber
     * @return true if the game is Finished or False if its still continuous
     */

    public boolean FillSlot(int slotnumber, int player) {
        boolean winingmove;
        String playername = player == 1 ? "Computer" : "Human";
        //reduce 1 since arraylist indexing start from 0
        slotnumber -= 1;
        //first get the requested slo
        slot slot = slots.get(slotnumber);

        //checks if slot is occupied or not then make move
        //checks if the user is allow to make this move by making sure he doesn't perform two consecutive moves
        //if(!slot.isOccupied() && this.Lastmoveplayer!=player){

        //if slot is not occupied and no player has won yet
        if (slot.isEmpty() && !this.winmove) {
            slot.setOccupant(new Integer(player));
            this.Lastmoveplayer = player;

            Log.v("move", "Player:" + playername + " move to slot(" + (slotnumber + 1) + ") Succeeded");
            Log.v("slotdata", "Slot(" + (slotnumber + 1) + ") " + slot.toString());

            //Wining checking and update board status
            IsItaWinMove(slotnumber);

            return true;
        } else {

            Log.v("move", "Player:" + playername + " move to slot(" + (slotnumber + 1) + ") Failed");
            Log.v("slotdata", "Slot(" + (slotnumber + 1) + ") " + slot.toString());
            Log.v("lastmoveplayer", String.valueOf(this.Lastmoveplayer));
            return false;
        }
    }

    /**
     * method to be called after each slot is filled to asses if its a winning move
     * Possible wins(considering a 3 way vice versa side switch)
     * -------------
     * n*     Orientation   num    combinations
     * 1       horizontal    1        123
     * 2       horizontal    2        456
     * 3       horizontal    3        789
     * 4       vertical      1        147
     * 5       vertical      2        258
     * 6       vertical      3        369
     * 7       diagonal      1        159
     * 8       diagonal      2        357
     */

    public boolean IsItaWinMove(int index) {

        //Possinle wins/
        //1 horizontal 1  123
        //2 horizantal 2  456
        //3 horizantal 2  789
        //4 vertical 1    147
        //5 vertical 2    258
        //6 vertical 3    369
        //7 diagonal 1    159
        //8 diagonal 2    357

        ArrayList<winset> winsets = new ArrayList<winset>();
        winsets.add(new winset(0, 1, 2));
        winsets.add(new winset(3, 4, 5));
        winsets.add(new winset(6, 7, 8));
        winsets.add(new winset(0, 3, 6));
        winsets.add(new winset(1, 4, 7));
        winsets.add(new winset(2, 5, 8));
        winsets.add(new winset(0, 4, 8));
        winsets.add(new winset(2, 4, 6));

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

                            winset toCompare1 = new winset(i, x, index);
                            winset toCompare2 = new winset(x, i, index);
                            winset toCompare3 = new winset(index, x, i);
                            winset toCompare4 = new winset(index, i, x);
                            winset toCompare5 = new winset(i, index, x);
                            winset toCompare6 = new winset(x, index, i);


                            Log.v("Gamestatus", "Player:" + player);
                            Log.v("Gamestatus", "combination found (" + toCompare1.toString() + ") (" + toCompare2.toString() + ") (" + toCompare3.toString() + ")");
                            Log.v("Gamestatus", "combination found (" + toCompare4.toString() + ") (" + toCompare5.toString() + ") (" + toCompare6.toString() + ")");
                            //check if its a wining set
                            if ((winingset.equals(toCompare1)) | (winingset.equals(toCompare2)) | (winingset.equals(toCompare3)) |
                                    (winingset.equals(toCompare4)) | (winingset.equals(toCompare5)) | (winingset.equals(toCompare6))
                                    ) {
                                Log.v("Gamestatus", "Game won by (" + player + ") on slot (" + (index + 1) + ")");
                                String Playername = player == 1 ? "Me" : "You";

                                //set win msg and the winset for later use
                                this.Winmsg = Playername + " Won!";
                                this.Winset = toCompare1;
                                this.winmove = true;
                                this.gamestatus = player;
                                this.Lastmoveplayer = 0;
                                return true;


                            }
                        }
                    }
                }
                //Log.v("Gamestatus","Game not won by ("+player+") on slot"+(index + 1) +" Keep going...");
            }
        }
        this.gamestatus = -1;
        return false;

    }


    /**
     * checks if a move will result in a win
     */

    public boolean IsItaWinMove(int index, int player) {

        //Possinle wins/
        //1 horizontal 1  12
        //2 horizantal 2  45
        //3 horizantal 2  78
        //4 vertical 1    147
        //5 vertical 2    258
        //6 vertical 3    369
        //7 diagonal 1    159
        //8 diagonal 2    357

        Log.v("isitawinmove", "index" + index + " Player:" + player);

        ArrayList<winset> winsets = new ArrayList<winset>();
        winsets.add(new winset(0, 1, 2));
        winsets.add(new winset(3, 4, 5));
        winsets.add(new winset(6, 7, 8));
        winsets.add(new winset(0, 3, 6));
        winsets.add(new winset(1, 4, 7));
        winsets.add(new winset(2, 5, 8));
        winsets.add(new winset(0, 4, 8));
        winsets.add(new winset(2, 4, 6));

        ///loop trough all the winsets and identify a match
        for (winset winingset : winsets) {
            //Log.v("winchecking", "Slot(" + (index + 1) + ") " + "player " + player);
            //for the i value
            for (int i = 0; i < 9; i++) {
                //for the x value
                for (int x = 0; x < 9; x++) {
                    //check if the combination is filled by the same player
                    if (this.IsslotfilledByplayer(i, player) && this.IsslotfilledByplayer(x, player)
                            && i != index && x != index && x != i) {

                        winset toCompare1 = new winset(i, x, index);
                        winset toCompare2 = new winset(x, i, index);
                        winset toCompare3 = new winset(index, x, i);
                        winset toCompare4 = new winset(index, i, x);
                        winset toCompare5 = new winset(i, index, x);
                        winset toCompare6 = new winset(x, index, i);

                        Log.v("Gamestatus", "Player:" + player);
                        Log.v("Gamestatus", "combination found (" + toCompare1.toString() + ") (" + toCompare2.toString() + ") (" + toCompare3.toString() + ")");
                        Log.v("Gamestatus", "combination found (" + toCompare4.toString() + ") (" + toCompare5.toString() + ") (" + toCompare6.toString() + ")");
                        //check if its a wining set
                        if ((winingset.equals(toCompare1)) | (winingset.equals(toCompare2)) | (winingset.equals(toCompare3)) |
                                (winingset.equals(toCompare4)) | (winingset.equals(toCompare5)) | (winingset.equals(toCompare6))
                                ) {
                            Log.v("Gamestatus", "combination to win found");
                            //set win msg and the winset for later use
                            return true;
                        }
                    }
                }
            }
        }

        return false;

    }


    /**
     * checks whether a slot is filled by a certain player at a specific slot
     */


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
     * returns empty slots
     */
    public ArrayList<slot> getEmptySlots() {

        ArrayList<slot> EmptySlots = new ArrayList<slot>();
        for (slot s : this.slots) {
            if (s.isEmpty()) {
                EmptySlots.add(s);
            }
        }
        return EmptySlots;
    }


    /**
     * @return true id board is full and false if it's not
     */
    public boolean isBoardFull() {

        return this.getEmptySlots().size() == 0;

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
            if (this.getSlot(0) == toCompare.getSlot(0) && this.getSlot(1) == toCompare.getSlot(1) && this.getSlot(2) == toCompare.getSlot(2)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "winset{" + "slotset=" + slotset + '}';
    }
}
