package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;

    int activePlayer = 0;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    public static int counter = 0;

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        TextView status = findViewById(R.id.ttt_status_tv);

        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (!gameActive) {
            return;
        }


        if (gameState[tappedImage] == 2) {
            counter++;

            if (counter == 9) {
                gameActive = false;
            }

            gameState[tappedImage] = activePlayer;

            img.setTranslationY(-1000f);


            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;

                status.setText("O's Turn - Tap to play");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;

                status.setText("X's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        int flag = 0;

        if(counter>2){
            for (int[] winPosition : winPositions) {
                if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                        gameState[winPosition[1]] == gameState[winPosition[2]] &&
                        gameState[winPosition[0]] != 2) {
                    flag = 1;

                    String winnerStr;

                    gameActive = false;
                    if (gameState[winPosition[0]] == 0) {
                        winnerStr = "X has won";
                    } else {
                        winnerStr = "O has won";
                    }
                    status.setText(winnerStr);
                }
            }

        }
        if (counter == 9 && flag == 0) {
            status.setText("Match Draw");
        }
    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        counter=0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        ((ImageView) findViewById(R.id.grid_box_1)).setImageResource(0);
        ((ImageView) findViewById(R.id.grid_box_2)).setImageResource(0);
        ((ImageView) findViewById(R.id.grid_box_3)).setImageResource(0);
        ((ImageView) findViewById(R.id.grid_box_4)).setImageResource(0);
        ((ImageView) findViewById(R.id.grid_box_5)).setImageResource(0);
        ((ImageView) findViewById(R.id.grid_box_6)).setImageResource(0);
        ((ImageView) findViewById(R.id.grid_box_7)).setImageResource(0);
        ((ImageView) findViewById(R.id.grid_box_8)).setImageResource(0);
        ((ImageView) findViewById(R.id.grid_box_9)).setImageResource(0);

        TextView status = findViewById(R.id.ttt_status_tv);
        status.setText("X's Turn - Tap to play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.ttt_restart_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameReset(view);
            }
        });
    }
}
