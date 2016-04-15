package com.hillygeeks.tictac;

/**
 * Created by Max Thierry on 13/4/2016.
 * represent a slot that can be part of/comply an object with a collection of more than one slot (empty/filled spaces)
 */
public class slot {
    //a slot can either be empty or taken. when the occupant field is null that  means the slot is emptyi,
    // if the occupant field is set means the slot is taken
    private Integer Occupant;
    private int position;
    private static int counter = 0;


    /**
     * constructor
     */
    public slot() {
        this.Occupant = null;
        this.position = counter;
        if (this.counter == 8) {
            this.counter = 0;
        } else {
            this.counter++;
        }
    }


    /**
     * constructor that receives the occupant identification
     */
    public slot(Integer occupant) {
        this.Occupant = new Integer(occupant);

        this.position = counter;
        if (this.counter == 8) {
            this.counter = 0;
        } else {
            this.counter++;
        }
    }


    /**
     * @return if the slot is empty or not
     */
    public boolean isEmpty() {

        return this.Occupant == null;
    }

    /**
     * @return the identification of the occupant
     */
    public Integer getOccupant() {
        return Occupant;
    }


    /**
     * sets the identification of the occupant
     */
    public void setOccupant(Integer occupant) {
        this.Occupant = occupant;
    }


    /**
     * get position
     *
     * @return
     */
    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "slot{" + "Occupant=" + Occupant + ", position=" + position +'}';
    }


}
