package com.examplexample.treasurehunting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.content.Context;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class InformationActivity extends AppCompatActivity{

    int currentHints = 0;
    List<TextView> list = new ArrayList<TextView>();
    List<String> hintContents = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Bundle bundle = this.getIntent().getExtras();
        int hintNumber = bundle.getInt("hintNumber");

        setTextview();
        setHintContents();




        if (hintNumber!=currentHints){
            for (int i=0;i<hintNumber;i++){
                list.get(i).setText(getHintContents(i));
            }
            currentHints = hintNumber;
        }

    }


    private void setTextview(){
        TextView textView1 = (TextView) findViewById(R.id.hint1);
        list.add(textView1);
        TextView textView2 = (TextView) findViewById(R.id.hint2);
        list.add(textView2);
        TextView textView3 = (TextView) findViewById(R.id.hint3);
        list.add(textView3);
        TextView textView4 = (TextView) findViewById(R.id.hint4);
        list.add(textView4);
        TextView textView5 = (TextView) findViewById(R.id.hint5);
        list.add(textView5);
    }


    private String getHintContents(int h){
        return hintContents.get(h);

    }

    private void setHintContents(){
        String content1 = "Near to one of the gate";
        String content2 = "Usually closed on holiday";
        String content3 = "Coffee shop";
        String content4 = "hint 4";
        String content5 = "hint 5";

        hintContents.add(content1);
        hintContents.add(content2);
        hintContents.add(content3);
        hintContents.add(content4);
        hintContents.add(content5);
    }



    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
