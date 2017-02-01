package com.examplexample.treasurehunting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        Intent intent = new Intent();
        intent.setClass(LogoActivity.this, MasterActivity.class);

        Bundle bundle=new Bundle();
        bundle.putInt("coins",50);
        bundle.putInt("unlockedGame",1);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
