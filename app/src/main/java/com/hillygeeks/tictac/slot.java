package com.hillygeeks.tictac;

/**
 * Created by Max Thierry on 13/4/2016.
 * represent a slot that can be part of/comply an object with a collection of more than one slot (empty/filled spaces)
 */
public class slot {
    //a slot can either be empty or taken. when the occupant field is null that  means the slot is emptyi,
    // if the occupant field is set means the slot is taken
    private Integer Occupant;

    /**
     * constructor
     */
    public slot() {
        this.Occupant = null;
    }


    /**
     * constructor that receives the occupant identification
     */
    public slot(Integer occupant) {
        this.Occupant = new Integer(occupant);
    }


    /**
     * @return if the slot is occupied or not
     */
    public boolean isOccupied() {

        if (this.Occupant == null) {
            return false;
        } else {
            return true;
        }
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


}
