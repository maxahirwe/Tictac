package com.hillygeeks.tictac;

/**
 * Created by User on 14/4/2016.
 */
public class SimulatedTicTac3Board extends TicTac3Board {

    //keep the class
    public int score;
    public int playedslot;

    /**
     * constructor that calls the super class
     */

    public SimulatedTicTac3Board() {
        super();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public int getPlayedslot() {
        return playedslot;
    }

    public void setPlayedslot(int playedslot) {
        this.playedslot = playedslot;
    }


}
