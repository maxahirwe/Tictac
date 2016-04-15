package com.hillygeeks.tictac;

/**
 * Created by Max Ahirwe on 14/4/2016.
 */
public class virtualslot extends slot {


    public void setPosition(int position) {
        this.position = position;
    }

    public int position;
    public int score;

    public virtualslot(Integer occupant) {
        super(occupant);
    }

    public virtualslot() {

    }

    public int getPosition() {
        return position;
    }

}
