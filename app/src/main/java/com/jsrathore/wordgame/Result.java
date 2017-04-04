package com.jsrathore.wordgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.jsrathore.wordgame.R.id.bestScore;

public class Result extends AppCompatActivity {
    private Button Exitbt,RePlaybt;
    private int best;
    private TextView yourScore;
    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        yourScore = (TextView)findViewById(R.id.result);
        Exitbt=(Button)findViewById(R.id.exit);
        RePlaybt=(Button)findViewById(R.id.rePlay);

        prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        best = prefs.getInt("score", 0);
        yourScore.setText(Integer.toString(best));



        Exitbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                finish();
            }
        });
        RePlaybt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent i=new Intent(Result.this ,Game.class);
                        startActivity(i);
                        finish();
            }

        });
    }
}
