package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive=true;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int activePlayer = 0;
    int[][] winPos = {{0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}};
    public void playerTap(View view){
        ImageView img = (ImageView) view;
        if(!gameActive){
            gameReset(view);
            return;
        }
        TextView textView = findViewById(R.id.status);
        int tapImg = Integer.parseInt(img.getTag().toString());
        if(gameState[tapImg]==2){
            gameState[tapImg]=activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer==0){
                img.setImageResource(R.drawable.x);
                activePlayer=1;
                textView.setText("O's turn to play");
            }
            else{
                img.setImageResource(R.drawable.o);
                activePlayer=0;
                textView.setText("X's turn to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        for(int[] winPosition : winPos){
            if(gameState[winPosition[0]]==gameState[winPosition[1]] && gameState[winPosition[1]]==gameState[winPosition[2]] && gameState[winPosition[0]]!=2){
                if(gameState[winPosition[0]]==0){
                    textView.setText("Congratulations!\n    X has won!");
                }
                else{
                    textView.setText("Congratulations!\n    O has won!");
                }
                gameActive=false;
            }
        }
        int cnt=0;
        for(int gs : gameState){
            if(gs!=2) {
                cnt++;
            }
        }
        if(cnt==9){
            gameActive=false;
            textView.setText("Game is Drawn");
        }
    }
    public void gameReset(View view){
        gameActive = true;
        activePlayer=0;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
        TextView textView = findViewById(R.id.status);
        textView.setText("X's turn to play");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}