package com.jsrathore.wordgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class Game extends AppCompatActivity implements View.OnClickListener {
    private Button btn11 , btn22, btn33, btn44, button11,button22,button33,button44;
    private Button bthome,btend,btexit;
    private SharedPreferences prefs;
    private Random random=new Random();
    private TextView yourScore,bestScore;
    private ArrayList<String> wordList;
    public ArrayList<String> userWord;
    public String[] str1=new String[5];

    public char c1,c2,c3,c4;
    public int temp,i,v1,v2,v3,v4,chance=0,flag=0,score=0,best;
    public String str2,s1,s2,s3,s4,a1="",a2="",a3="",a4="";
    public boolean b1,b2,b3,b4;
    private TrieNode root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        btn11=(Button)findViewById(R.id.btn1);
        btn22=(Button)findViewById(R.id.btn2);
        btn33=(Button)findViewById(R.id.btn3);
        btn44=(Button)findViewById(R.id.btn4);
        button11=(Button)findViewById(R.id.button1);
        button22=(Button)findViewById(R.id.button2);
        button33=(Button)findViewById(R.id.button3);
        button44=(Button)findViewById(R.id.button4);
        bthome =(Button)findViewById(R.id.home);
        btend = (Button)findViewById(R.id.endGame);
        btexit = (Button)findViewById(R.id.exit);
        yourScore=(TextView)findViewById(R.id.yourScore);
        bestScore=(TextView)findViewById(R.id.bestScore);
        prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        best = prefs.getInt("score", 0);
        bestScore.setText(Integer.toString(best));



        wordList=new ArrayList<>();
        userWord=new ArrayList<>(5);
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("file.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            root = new TrieNode();
            while((line = in.readLine()) != null) {
                String word = line.trim();
                wordList.add(word);
                root.add(word);
            }

            int num=random.nextInt(1600);
            str2=wordList.get(num);

            List<Character> characters = new ArrayList<Character>();
            for(char c:str2.toCharArray()){
                characters.add(c);
            }
            StringBuilder str = new StringBuilder(str2.length());
            while(characters.size()!=0){
                int randPicker = (int)(Math.random()*characters.size());
                str.append(characters.remove(randPicker));
            }



            temp=0;
            i=0;
            b1=false;
            b2=false;
            b3=false;
            b4=false;
            /*Log.d(str, "upload String............");*/

            c1=str.charAt(0);
            c2=str.charAt(1);
            c3=str.charAt(2);
            c4=str.charAt(3);
            s1= String.valueOf(c1);
            s2= String.valueOf(c2);
            s3= String.valueOf(c3);
            s4= String.valueOf(c4);
            button11.setText(s1);
            button22.setText(s2);
            button33.setText(s3);
            button44.setText(s4);


        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        }
        button11.setOnClickListener(this);
        button22.setOnClickListener(this);
        button33.setOnClickListener(this);
        button44.setOnClickListener(this);
        bthome.setOnClickListener(this);
        btend.setOnClickListener(this);
        btexit.setOnClickListener(this);
    }

    public boolean isWord(String word) {
        return root.isWord(word);
    }
    public void checkString(){
        if (b1==true && b2==true && b3==true && b4==true){

            String s=str1[0]+str1[1]+str1[2]+str1[3];
            Log.d(s, "String.............................");
            if(isWord(s)){
                btn11.setTextColor(Color.GREEN);
                btn22.setTextColor(Color.GREEN);
                btn33.setTextColor(Color.GREEN);
                btn44.setTextColor(Color.GREEN);
                score+=5;
                yourScore.setText(Integer.toString(score));
                best = prefs.getInt("score", 0);
                if (best > score) {
                    bestScore.setText(Integer.toString(best));
                } else {
                    best = score;
                    bestScore.setText(Integer.toString(best));
                    prefs.edit().putInt("score", best).apply();
                }
                nextword();
                flag=1;

            }  else {
                btn11.setTextColor(Color.RED);
                btn22.setTextColor(Color.RED);
                btn33.setTextColor(Color.RED);
                btn44.setTextColor(Color.RED);
            }

        }
    }
    public void setWordOnButton(int i,String ss){
        switch (i){
            case 1:
                btn11.setText(ss);
                break;
            case 2:
                btn22.setText(ss);
                break;
            case 3:
                btn33.setText(ss);
                break;
            case 4:
                btn44.setText(ss);
                break;
        }
    }
    public void EraseWordOnButton(int i,String ss){
        switch (i){
            case 1:
                //button11.setText(ss);
                btn11.setText("");
                break;
            case 2:
                //button22.setText(ss);
                btn22.setText("");
                break;
            case 3:
                //button33.setText(ss);
                btn33.setText("");
                break;
            case 4:
                //button44.setText(ss);
                btn44.setText("");
                break;
        }
    }

    public void nextword(){
        int num=random.nextInt(1600);
        str2=wordList.get(num);

        List<Character> characters = new ArrayList<Character>();
        for(char c:str2.toCharArray()){
            characters.add(c);
        }
        StringBuilder str = new StringBuilder(str2.length());
        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            str.append(characters.remove(randPicker));
        }
        temp=0;
        i=0;
        b1=false;
        b2=false;
        b3=false;
        b4=false;
        c1=str.charAt(0);
        c2=str.charAt(1);
        c3=str.charAt(2);
        c4=str.charAt(3);
        s1= String.valueOf(c1);
        s2= String.valueOf(c2);
        s3= String.valueOf(c3);
        s4= String.valueOf(c4);
        button11.setText(s1);
        button22.setText(s2);
        button33.setText(s3);
        button44.setText(s4);

    }



    @Override
    public void onClick(View v) {
        if(flag==1){
            flag=0;
            btn11.setText("");
            btn22.setText("");
            btn33.setText("");
            btn44.setText("");
            btn11.setTextColor(Color.BLACK);
            btn22.setTextColor(Color.BLACK);
            btn33.setTextColor(Color.BLACK);
            btn44.setTextColor(Color.BLACK);


        }
        switch (v.getId()){
            case R.id.exit:
                    Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                    homeIntent.addCategory( Intent.CATEGORY_HOME );
                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(homeIntent);
                finish();
                break;
            case R.id.home:
                Intent k=new Intent(Game.this ,MainActivity.class);
                startActivity(k);
                finish();
                break;
            case R.id.endGame:
                Intent j=new Intent(Game.this ,Result.class);
                startActivity(j);
                finish();
                break;
            case R.id.button1:

                if (b1==false) {
                    button11.setText("");
                    //btn11.setText(s1);
                    b1=true;
                    temp++;
                    v1=temp;
                    //userWord.add(1,s1);
                    //a1=s1;
                    str1[i++]=s1;
                    //temp_val++;
                    setWordOnButton(temp,s1);
                    checkString();
                }else {
                    button11.setText(s1);
                    //btn11.setText("");
                    b1=false;
                    temp--;
                    //userWord.remove(1);
                    //a1="";
                    str1[i--]="";
                    EraseWordOnButton(v1,s1);
                    //temp_val--;
                }
                break;
            case R.id.button2:

                if (b2==false) {
                    button22.setText("");
                    // btn22.setText(s2);
                    b2=true;
                    temp++;
                    v2=temp;
                    //userWord.add(2,s2);
                    //a2=s2;
                    str1[i++]=s2;
                    setWordOnButton(temp,s2);
                    checkString();
                    //temp_val++;
                }else {
                    button22.setText(s2);
                    //  btn22.setText("");
                    b2=false;
                    temp--;
                    //userWord.remove(2);
                    //a2="";
                    str1[i--]="";
                    EraseWordOnButton(v2,s2);
                    //temp_val--;
                }
                break;
            case R.id.button3:

                if (b3==false) {
                    button33.setText("");
                    // btn33.setText(s3);
                    b3=true;
                    temp++;
                    v3=temp;
                    // userWord.add(3,s3);
                    //a3=s3;
                    str1[i++]=s3;
                    setWordOnButton(temp,s3);
                    checkString();
                    //temp_val++;
                }else {
                    button33.setText(s3);
                    //btn33.setText("");
                    b3=false;
                    temp--;
                    // userWord.remove(3);
                    //a3="";
                    str1[i--]="";
                    EraseWordOnButton(v3,s3);
                    //temp_val--;
                }
                break;
            case R.id.button4:
                if (b4==false) {
                    button44.setText("");
                    //btn44.setText(s4);
                    b4=true;
                    temp++;
                    v4=temp;
                    // userWord.add(4,s4);
                    //a4=s4;
                    str1[i++]=s4;
                    setWordOnButton(temp,s4);
                    checkString();
                    //temp_val++;
                }else {
                    button44.setText(s4);
                    //btn44.setText("");
                    b4=false;
                    temp--;
                    //userWord.remove(4);
                    //a4="";
                    str1[i--]="";
                    EraseWordOnButton(v4,s4);
                    //temp_val--;
                }
                break;


        }

    }
}
