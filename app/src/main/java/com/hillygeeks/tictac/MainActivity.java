package com.hillygeeks.tictac;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private TicTacGame Game;

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

        //initialize game if new,
        if (Game != null) {
            Log.v("Gamestatus:", "On goin..");
            //get the tag(string) of the clicked button
            String Buttontag = (String) (SlotButton.getTag());
            //convert into an int
            int slot = Integer.valueOf(Buttontag);
            Log.v("slotclicking", " slot cliked:" + slot);

            //Try making the move if it goes through then show the move on screen
            if (Game.Board.Lastmoveplayer != 2 && Game.Board.FillSlot(slot, 2)) {
                ImageButton Btn = (ImageButton) SlotButton;
                Btn.setImageResource(R.drawable.circle);
                //trigger the pc to play
                int pcmove = Game.Board.PcFillslot();
                ButtonImageSet(pcmove, R.drawable.close);


            }

        } else {
            Log.v("Gamestatus:", "Not started");
            Toast.makeText(this, "Hey,Chill..First Select who plays firsts from the buttons at the bottom", Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * function for dealing with the creation of a new game either its the pc or the user that starts first
     *
     * @param PlayButton
     */
    public void Playclick(View PlayButton) {
        //clear out the view
        this.clearlayout();
        ImageButton Button = (ImageButton) PlayButton;

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.btn_user: {
                        //if the user choose to start first
                        Log.v("1stPlayer:", "USER");
                        Game = new TicTacGame();
                        clearlayout();
                        break;
                    }

                    case R.id.btn_phone: {
                        //if the user choose the pc to start first
                        Log.v("1stPlayer:", "COMPUTER");
                        Game = new TicTacGame();
                        clearlayout();
                        //play the first move and mark the slot on the GUI
                        int btn_slotid = Game.Board.MakeFirstPCRandomMove();
                        ButtonImageSet(btn_slotid, R.drawable.close);
                        break;
                    }
                }

            }
        });

    }


    /**
     * set ButtonImage with passed id to a passed in  Drawable image
     */

    public ImageButton ButtonImageSet(int viewid, int drawable) {

        String ViewID = "btn_slot" + viewid;
        int id = getResources().getIdentifier(ViewID, "id", this.getPackageName());
        ImageButton Button = (ImageButton) findViewById(id);
        Button.setImageResource(drawable);
        return Button;

    }


    /**
     * clear out all buttons from icons
     */
    public void clearlayout() {
        for (int i = 1; i < 10; i++) {

            ButtonImageSet(i, R.drawable.empty);

        }
    }


}
