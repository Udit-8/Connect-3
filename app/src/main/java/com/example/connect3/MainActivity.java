package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int currentPlayer = 0;
    int[] gameState ={2,2,2,2,2,2,2,2,2};
    int[][] winningState ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean isGameOver = false;
    public void dropIn(View view)
    {
        // 0 == blue,1 == red

        ImageView pic = (ImageView)view;
        int currPic = Integer.parseInt(pic.getTag().toString());
        if(gameState[currPic] == 2 && !isGameOver) {
            pic.setTranslationY(-1000f);
            pic.animate().translationYBy(1000f).rotation(360).setDuration(500);
            gameState[currPic] = currentPlayer;
            if (currentPlayer == 1) {
                pic.setImageResource(R.drawable.red);
                currentPlayer = 0;
            }
            else {
                pic.setImageResource(R.drawable.blue);
                currentPlayer = 1;
            }
        }

            boolean isGameDraw = true;
            for(int i : gameState)
            {
                if(i == 2)
                    isGameDraw = false;
            }
            if(isGameDraw)
            {
                LinearLayout winnerlayout = findViewById(R.id.winnerLayout);
                TextView winnerText = findViewById(R.id.winnerText);
                winnerText.setText("Game is drawn");
                winnerlayout.setVisibility(winnerlayout.VISIBLE);
                isGameOver =true;
            }
        for(int[] win : winningState)
        {
            if(gameState[win[0]] == gameState[win[1]] && gameState[win[0]] == gameState[win[2]]&& gameState[win[0]] != 2)
            {
                LinearLayout winnerlayout = findViewById(R.id.winnerLayout);
                TextView winnerText = findViewById(R.id.winnerText);
                String winner ="";
                if(gameState[win[0]] == 0)
                    winner = "Blue";
                else
                    winner = "Red";
                winnerText.setText(winner+" is the winner");
                winnerlayout.setVisibility(winnerlayout.VISIBLE);
                isGameOver =true;
            }
        }
    }
    public void reset(View view)
    {
        isGameOver = false;
        LinearLayout winnerlayout = findViewById(R.id.winnerLayout);
        TextView winnerText = findViewById(R.id.winnerText);
        winnerlayout.setVisibility(winnerlayout.INVISIBLE);
        androidx.gridlayout.widget.GridLayout  grid = (androidx.gridlayout.widget.GridLayout)findViewById(R.id.gridLayout);
        currentPlayer = 0;
        for(int i= 0;i < gameState.length;i++)
        {
            gameState[i] = 2;
        }
        for(int i = 0; i < grid.getChildCount();i++)
        {
            ((ImageView)grid.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
