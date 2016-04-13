package com.hillygeeks.tictac;

/**
 * Created by Max Ahirwe on 12/4/2016.
 * this class represents a tic tac game of 3*3
 */
public class TicTacGame {


    private static TicTac3Board Board;
    //receives a boolean that specify who plays first between the human player and the human player

    public TicTacGame(boolean PC_Plays_First) {

        Board = new TicTac3Board();
        //if 1 means the computer plays firsts,0 means the human makes the first move
        if (PC_Plays_First) {
            Board.MakeFirstPCRandomMove();
        }

    }

    /**
     * make moves accross on the board
     */
    public boolean makemove(int slotnumber, int playerID) {
        return Board.FillSlot(slotnumber, playerID);
    }

}
