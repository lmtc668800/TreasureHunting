package com.examplexample.treasurehunting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.examplexample.treasurehunting.R.id.textView;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);



        Button button1 = (Button) findViewById(R.id.button5);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.setClass(DemoActivity.this, LogInActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("DemoType", 1);
                    intent.putExtras(bundle);
                    startActivity(intent);
            }
        });


        Button button2 = (Button) findViewById(R.id.button6);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(DemoActivity.this, LogInSecureActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("DemoType", 2);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
