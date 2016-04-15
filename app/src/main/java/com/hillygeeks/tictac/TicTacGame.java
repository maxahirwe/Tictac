package com.hillygeeks.tictac;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Max Ahirwe on 12/4/2016.
 * this class represents a tic tac game of 3*3
 */
public class TicTacGame {


    public static TicTac3Board Board;
    static public int PcPlayerID = 1;
    static public int HumanPlayerID = 2;
    TicTac3Board virtualboard;

    //receives a boolean that specify who plays first between the human player and the human player

    public TicTacGame() {

        Board = new TicTac3Board();

    }

    /**
     * Artificial Intelligence core heart of the game
     */
    public int Pcplay() {
        ArrayList<slot> Emptyslots = Board.getEmptySlots();
        Log.v("status", "Empty slots:" + Board.getEmptySlots());
        if (Emptyslots.size() != 0) {

            Log.v("status", "Empty slots" + Emptyslots.size());

            for (slot s : Emptyslots) {


                //check if i play in that position i will
                //2.check if there's an opportunity to win for pc player
                if (Board.IsItaWinMove(s.getPosition(), PcPlayerID)) {
                    Log.v("blocking", "winning");
                    return s.getPosition() + 1;
                }

                //1.check is opponent any of future positions would allow the human to win ,and play in that position

                if (Board.IsItaWinMove(s.getPosition(), HumanPlayerID)) {
                    Log.v("blocking", "Human combination");
                    return s.getPosition() + 1;
                }

            }

            // pick the middle if it is available

            for (slot s : Emptyslots) {
                if (s.getPosition() == 4) {
                    return 4 + 1;
                }

            }


            //pick a random place to play
            boolean search = true;
            while (search) {
                int random = Board.Randomslot() - 1;
                if (Board.slots.get(random).getOccupant() == null) {
                    search = false;
                    return random + 1;
                }
            }
        }


        //No empty slot means game is finished /Draw
        return -1;

    }


    /**
     * method make the first pc automated move
     */
    public int MakeFirstPCRandomMove() {
        Board.FillSlot(5, 1);
        return 5;
    }


    /**
     * function the computer calls to play using Minimax algorithm
     */
    public int Computerplay() {

        virtualslot bestmove = BestAimove(PcPlayerID);

        //all human moves usually are sent by the screen clicks counting from 1-9 so we + to the move index computed by the computer to be in boundaries of the array
        if (Board.FillSlot(bestmove.position + 1, PcPlayerID)) {
            Log.v("minimax", "Succeed moving To slot" + bestmove.position);
            return bestmove.position + 1;

        } else {
            Log.v("minimax", "failed moving To slot" + bestmove.position);
            return -1;
        }


    }


    /**
     * Artificial Intelligence core heart of the game using Minimax algorithm
     * too be implemented in the next version (couldnt find out why am receiving an IndexOutOfBoundsException
     *
     * @return
     */
    public virtualslot BestAimove(int player) {


        //end state checking
        int winner = Board.gamestatus;
        if (winner == PcPlayerID) {
            virtualslot vs = new virtualslot();
            Log.v("minimax", "+10 scores");
            vs.score = 10;
            return vs;
        } else if (winner == HumanPlayerID) {
            virtualslot vs = new virtualslot();
            vs.score = -10;
            Log.v("minimax", "-10 scores");
            return vs;
        } else if (winner == -1) {
            virtualslot vs = new virtualslot();
            vs.score = -1;
            Log.v("minimax", "0 scores");
            return vs;
        }

        ArrayList<virtualslot> moves = new ArrayList<virtualslot>();

        //
        // loop through all possible combinations of winning slots for both players
        //construct the moves arraylist so later we can check tit for picking the best move
        for (int x = 0; x < 9; x++) {
            //check if this slot is empty
            Log.v("minimax", "slot(" + x + ")" + Board.slots.get(x).getOccupant());
            Log.v("minimax", "occupant" + Board.slots.get(x).getOccupant());

            if (Board.slots.get(x).getOccupant() == null) {
                virtualslot Vslot = new virtualslot(player);
                Vslot.position = x;
                Board.FillSlot(x, player);
                if (player == PcPlayerID) {
                    Vslot.score = BestAimove(HumanPlayerID).score;
                    Log.v("PC minimax score", String.valueOf(Vslot.score));
                } else {
                    Log.v("User minimax score", String.valueOf(Vslot.score));
                    Vslot.score = BestAimove(PcPlayerID).score;
                }
                moves.add(Vslot);
                Log.v("minimax", moves.toString());
                //Board.Emptyslot(x);
            }

        }

        Log.v("minimax", "player:" + player);
        //pick the best move for the current player
        //variable to hold index of the best move
        int bestmoveindex = 0;
        if (player == PcPlayerID) {
            Log.v("minimax", "computer player 1");
            int bestscore = -1000000;
            //loop throuh all moves done and get the best
            for (virtualslot vs : moves) {
                if (vs.score > bestscore) {
                    Log.v("minimax", "score" + vs.score);
                    bestmoveindex = vs.position;
                    bestscore = vs.score;
                }

            }

        } else {
            int bestscore = 1000000;
            //loop throuh all moves done and get the best
            Log.v("minimax", "Human player 2");
            for (virtualslot vs : moves) {
                if (vs.score < bestscore) {
                    bestmoveindex = vs.position;
                    bestscore = vs.score;
                }
            }

        }       //return the best move the Ai can ever do
        Log.v("minimax", "making moves:" + moves);
        return moves.get(bestmoveindex);
    }









}
