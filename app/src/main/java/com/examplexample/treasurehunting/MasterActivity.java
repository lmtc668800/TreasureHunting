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

public class MasterActivity extends AppCompatActivity {

    int unlockedGame = 1;
    int coins = 0;
    String username;
    int demoType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        Bundle bundle = this.getIntent().getExtras();
        coins = bundle.getInt("coins");
        unlockedGame = bundle.getInt("unlockedGame");
        username = bundle.getString("username");
        demoType = bundle.getInt("DemoType");

        final TextView coinText= (TextView) findViewById(R.id.coinsText);
        coinText.setText("COINS: " + coins);



        Button button1 = (Button) findViewById(R.id.waseda);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MasterActivity.this, MainActivity.class);

                Bundle bundle=new Bundle();
                bundle.putInt("coins",coins);
                bundle.putInt("unlockedGame",unlockedGame);
                bundle.putInt("stage",0);
                bundle.putString("username",username);
                bundle.putInt("DemoType",demoType);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        final Button button2 = (Button) findViewById(R.id.shinjuku);
        if (unlockedGame<2){
            button2.setBackgroundColor(Color.parseColor("#70BFBFBF"));
        }else{
            button2.setText("Shinjuku");
            button2.setBackgroundColor(Color.parseColor("#b0FFAA00"));
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
                    bundle.putInt("stage",1);
                    bundle.putString("username",username);
                    bundle.putInt("DemoType",demoType);
                    intent.putExtras(bundle);

                    startActivity(intent);
                }else{
                    new AlertDialog.Builder(MasterActivity.this).setTitle("Locked Stage")
                            .setMessage("This stage is locked. Do you want to unlock it?(require 50 coins)")
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (coins >= 50){
                                        coins = coins -50;
                                        unlockedGame++;
//                                        button3.setVisibility(View.INVISIBLE);
                                        coinText.setText("COINS: " + coins);
                                        button2.setText("Shinjuku");
                                        button2.setBackgroundColor(Color.parseColor("#b0FFAA00"));
                                    }else {
                                        Toast.makeText(getApplicationContext(), "Failed. Insufficient coins.", Toast.LENGTH_LONG).show();
                                    }

                                }

                            }).setNegativeButton("No",new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }

                    }).show();
                }
            }
        });


        Button button3 = (Button) findViewById(R.id.help);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new  AlertDialog.Builder(MasterActivity.this)
//                        .setTitle("HELP" )
//                        .setMessage("In the stage, there will be several spots that you need to find out. One of them is the treasure.\n\n" +
//                                "When you find a spot, you may click 'Destination' to search for treasure, or 'Get Hint' to receive bonus.\n\n" +
//                                "There are three types of bonus:\n\nCOINS: Buy extra hint with it.\nHINT: Describing about treasure.\nSMALL HINT: Only show in spot description, leading you to other spots.\n\n" +
//                                "On the map, there are two circle on the Map.\n You need to start 'NEW GAME' inside the small circle. All of the spots will be located inside the large circle." )
//                        .setPositiveButton("OK" ,  null )
//                        .show();
                Intent intent = new Intent();
                intent.setClass(MasterActivity.this, LogInActivity.class);

                Bundle bundle=new Bundle();
                bundle.putInt("coins",coins);
                bundle.putInt("unlockedGame",unlockedGame);
                bundle.putInt("stage",0);
                intent.putExtras(bundle);

                startActivity(intent);

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
