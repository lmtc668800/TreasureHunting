package com.examplexample.treasurehunting;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MasterActivity extends AppCompatActivity {

    int unlockedGame = 1;
    int coins = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        Bundle bundle = this.getIntent().getExtras();
        coins = bundle.getInt("coins");
        unlockedGame = bundle.getInt("unlockedGame");

        final TextView coinText= (TextView) findViewById(R.id.coinsText);
        coinText.setText("Coins: " + coins);



        Button button1 = (Button) findViewById(R.id.waseda);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MasterActivity.this, MainActivity.class);

                Bundle bundle=new Bundle();
                bundle.putInt("coins",coins);
                bundle.putInt("unlockedGame",unlockedGame);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        final Button button2 = (Button) findViewById(R.id.shinjuku);
        if (unlockedGame<2){
            button2.setBackgroundColor(Color.GRAY);
        }
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (unlockedGame>=2) {
                    Intent intent = new Intent();
                    intent.setClass(MasterActivity.this, MainActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putInt("coins",coins);
                    bundle.putInt("unlockedGame", unlockedGame);
                    intent.putExtras(bundle);

                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "You need to unlock this stage", Toast.LENGTH_LONG).show();
                }
            }
        });

        final Button button3 = (Button) findViewById(R.id.unlock);
        if (unlockedGame>=2){
            button3.setVisibility(View.INVISIBLE);
        }
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(MasterActivity.this).setTitle("Unclock new stage")
                        .setMessage("Do you want to unlock a new stage?(require 50 coins)")
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (coins >= 50){
                                    coins = coins -50;
                                    unlockedGame++;
                                    button3.setVisibility(View.INVISIBLE);
                                    coinText.setText("Coins: " + coins);
                                }else {
                                    Toast.makeText(getApplicationContext(), "Failed. Insufficient coins", Toast.LENGTH_LONG).show();
                                }
                                button2.setBackgroundColor(Color.argb(255,255,69,0));

                            }

                        }).setNegativeButton("No",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }

                }).show();
            }
        });





    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            return  true;
        }
        return  super.onKeyDown(keyCode, event);
    }


}
