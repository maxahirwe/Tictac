package com.hillygeeks.tictac;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by User on 13/4/2016.
 */
public class TicTac3Board {

    //represents the 9 slots that comply a 3*3 Tic Tac Toe game
    TicTac3ArrayList slots;
    static private Random randomizer = new Random();

    static int PcPlayerID = 1;
    static int HumanPlayerID = 2;
    static int lastmove;


    public TicTac3Board() {
        //Instantiate the game slots
        slots = new TicTac3ArrayList();
        //fill the TicTac3ArrayList with empty slots
        for (int i = 1; i < 10; i++) {
            slots.add(new slot());
        }
    }

    /**
     * The most important function of the whole program that makes helpes players make moves accross the board
     *
     * @param slotnumber
     * @return true if the game is Finished or False if its still continuous
     */

    public boolean makemove(int slotnumber) {

        return true;
    }

    /**
     * function make the first pc automated move
     */
    public static void MakeFirstPCRandomMove() {
        int min = 1;
        int max = 10;
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = randomizer.nextInt((max - min) + 1) + min;
    }
}


/**
 * create by Max Thierry
 * class extends & limits a Java ArrayList to hold 9 slots only
 */
class TicTac3ArrayList<slot> extends ArrayList<slot> {
    @Override
    public boolean add(slot e) {
        if (this.size() < 9) {
            return super.add(e);
        }
        return false;
    }
}
