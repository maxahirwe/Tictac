package com.hillygeeks.tictac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {


    private TicTacGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    /**
     * function called everytime a slot on the tic tac board game is clicked
     *
     * @param SlotButton
     */

    public void Slotclick(View SlotButton) {

        //initialize game if new
        if (game == null) {
            game = new TicTacGame(false);
        }

        //get the tag(string) of the clicked button
        String Buttontag = (String) (SlotButton.getTag());
        //convert into an int
        int slot = Integer.valueOf(Buttontag);
        Log.v("slotclicking", " slot cliked:" + slot);

        //Try making the move
        if (game.makemove(slot, 1)) {
            ImageButton Btn = (ImageButton) SlotButton;
            Btn.setImageResource(R.drawable.circle);
        }

    }
}
