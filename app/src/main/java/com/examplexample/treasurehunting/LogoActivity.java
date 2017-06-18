package com.examplexample.treasurehunting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);



        final TextView textView = (TextView) findViewById(R.id.username);

        Button button1 = (Button) findViewById(R.id.signIn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = textView.getText().toString();

//                Toast.makeText(getApplicationContext(), textView.getText(), Toast.LENGTH_LONG).show();

                if (username.matches("")) {

                    Toast.makeText(getApplicationContext(), "You did not enter a username", Toast.LENGTH_LONG).show();

                }else{
                    Intent intent = new Intent();
                    intent.setClass(LogoActivity.this, MasterActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("coins", 50);
                    bundle.putInt("unlockedGame", 1);
                    bundle.putString("username",username);
                    intent.putExtras(bundle);

                    startActivity(intent);
                }
            }
        });




    }
}
