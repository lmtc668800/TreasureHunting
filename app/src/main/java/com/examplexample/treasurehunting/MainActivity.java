package com.examplexample.treasurehunting;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SlidingDrawer;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
//import java.util.Set;
//import java.util.function.ToDoubleBiFunction;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.util.TypedValue;

import org.w3c.dom.Text;

import com.examplexample.treasurehunting.DataLoader;
import com.examplexample.treasurehunting.SpotData;


public class MainActivity extends AppCompatActivity
        implements  OnMapReadyCallback,OnMyLocationButtonClickListener,SensorEventListener{

    private String provider;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private GoogleMap mMap;
    double latitude;
    double longitude;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean mPermissionDenied = false;


    GPSTracker gps;
    LocationCalculator loC;


    Marker spot1;
    Marker spot2;
    Marker spot3;
    Marker spot4;
    Marker spot5;
    Marker spot6;
    Marker spot7;
    Marker spot8;
    Marker spot9;
    Marker spot10;
    Marker spot11;
    Marker spot12;
    Marker spot13;
    Marker spot14;
    Marker spot15;
    Marker spot16;
    Marker spot17;
    Marker spot18;
    Marker spot19;
    Marker spot20;
    Marker spot21;
    Marker spot22;
    Marker spot23;
    Marker spot24;
    Marker spot25;
    Marker spot26;
    Marker spot27;
    Marker spot28;
    Marker spot29;
    Marker spot30;
    Marker spot31;
    Marker spot32;
    Marker spot33;
    Marker spot34;
    Marker spot35;
    Marker spot36;
    Marker spot37;
    Marker spot38;
    Marker spot39;
    Marker spot40;
    Marker spot41;
    Marker spot42;
    Marker spot43;
//    Marker spot44;
//    Marker spot45;
//    Marker spot46;
//    Marker spot47;
//    Marker spot48;
//    Marker spot49;
//    Marker spot50;




    List checked = new ArrayList();
    static double scan_range=0.060;
//    Marker treasure;


    private SensorManager mSensorManager;
    private Sensor mStepDetectorSensor;
    private Sensor mStepCounterSensor;
    int currentSteps = 0;
    int stepSetZero = 0;

    int hintNumber = 0;
    int gameStarted =0;
    int leftChance =0;

    int unlockedGame = 0;
    int coins=0;
    int stage=0;
    int destination = 0;

    List<SpotData> gameData;
    List<String> hintData;

    TextView coinText;
    TextView steps;
    TextView chance;

    LatLng startPoint;

//    Circle circle3;


    List<Marker> spots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = this.getIntent().getExtras();
        coins = bundle.getInt("coins");
        unlockedGame = bundle.getInt("unlockedGame");
        stage = bundle.getInt("stage");

        switch (stage){
            case 0:
                destination = 9;
                break;

            case 1:
                destination =21;
                break;

            default:
                destination =0;
                break;
        }

        coinText= (TextView) findViewById(R.id.coins_text_main);
        coinText.setText("Coins: " + coins);
        steps=(TextView) this.findViewById(R.id.steps);
        steps.setText( "0 steps");
        chance = (TextView) findViewById(R.id.left_chance);
        chance.setText("Chance: "+leftChance);


        DataLoader dataLoader = new DataLoader();
        gameData = dataLoader.LoadIn(stage);
        hintData = dataLoader.LoadHints(stage);

        gps = new GPSTracker(MainActivity.this);
        latitude = gps.getLatitude();
        longitude = gps.getLongitude();

        switch (stage){
            case 0:
                startPoint = new LatLng(35.707012,139.704780);
                break;

            default:
                startPoint = new LatLng(35.692164,139.701101);
        }



        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);










//        final SlidingDrawer slidingDrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
        final WrappingSlidingDrawer slidingDrawer = (WrappingSlidingDrawer) findViewById(R.id.slidingDrawer1);
        slidingDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener(){
            @Override
            public void onDrawerOpened() {

                Button button1 = (Button) findViewById(R.id.button1);
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(stage == 1){
                            double distance;
                            distance = loC.distance(latitude, longitude, startPoint.latitude, startPoint.longitude, "K");
//                            Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                            if (distance<= 0.040){
                                startNewGame();
                            }else{
                                Toast.makeText(getApplicationContext(), "Please start at the circle area in the map.", Toast.LENGTH_LONG).show();
                            }
                        }else {
                            startNewGame();
                        }
                        slidingDrawer.close();

                    }
                });

                Button button2 = (Button) findViewById(R.id.button2);
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        List<String> hints = new ArrayList<String>();
                        for (int i=0;i<5;i++){
                            if (i<hintNumber){
                                hints.add(hintData.get(i));
                            }else{
                                hints.add("Locked");
                            }
                        }
                        new AlertDialog.Builder(MainActivity.this).setTitle("HINTS").setItems(
                                new String[] { hints.get(0), hints.get(1),hints.get(2),hints.get(3),hints.get(4) }, null).setNegativeButton(
                                "OKay", null).show();
                    }
                });

                Button button3 = (Button) findViewById(R.id.button3);
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (gameStarted ==1) {
                            new AlertDialog.Builder(MainActivity.this).setTitle("SHOP").setItems(
                                    new String[]{"Unlock a hint(10 coins)", "Master mode on", "Master mode off"}, new DialogInterface.OnClickListener() {
//                                    new String[]{"Unlock a hint(10 coins)"}, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int which) {

                                            if (which == 0) {
                                                if (coins >= 10) {
                                                    coins = coins - 10;
                                                    hintNumber++;
                                                    coinText.setText("Coins: " + coins);
                                                    Toast.makeText(getApplicationContext(), "A hint unlocked! Check it now!", Toast.LENGTH_LONG).show();
                                                } else {
                                                    Toast.makeText(getApplicationContext(), "Failed. Insufficient coins", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                            else if (which == 1) {
                                                scan_range = scan_range + 10;
                                            } else if (which == 2){
                                                scan_range = 0.060;
                                            }

                                        }
                                    }).setNegativeButton(
                                    "Cancel", null).show();

                            slidingDrawer.close();
                        }else {
                            Toast.makeText(getApplicationContext(), "You can only visit the shop after game started.", Toast.LENGTH_LONG).show();
                        }

                    }
                });


                Button button4 = (Button) findViewById(R.id.button4);
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new AlertDialog.Builder(MainActivity.this).setTitle("EXIT")
                                .setMessage("Confirm to exit?")
                                .setPositiveButton("Exit",new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //                        showDetailInfo("button4");
                                        Intent intent = new Intent();
                                        intent.setClass(MainActivity.this, MasterActivity.class);

                                        Bundle bundle=new Bundle();
                                        bundle.putInt("coins",coins);
                                        bundle.putInt("unlockedGame", unlockedGame);
                                        intent.putExtras(bundle);

                                        startActivity(intent);

                                    }

                                }).setNegativeButton("Continue",new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

                        slidingDrawer.close();

                    }
                });

            }
        });









        mSensorManager =  (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);

        //センサマネージャから TYPE_STEP_DETECTOR についての情報を取得する
        mStepDetectorSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        //センサマネージャから TYPE_STEP_COUNTER についての情報を取得する
        mStepCounterSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
    }




    @Override
    public void onMapReady(GoogleMap map) {

        mMap = map;
        mMap.setOnMyLocationButtonClickListener(this);
        enableMyLocation();







        spot1 = map.addMarker(new MarkerOptions()
                .title(gameData.get(0).name)
                .position(new LatLng(gameData.get(0).latitude,gameData.get(0).longitude)));
        spot2 = map.addMarker(new MarkerOptions()
                .title(gameData.get(1).name)
                .position(new LatLng(gameData.get(1).latitude,gameData.get(1).longitude)));
        spot3 = map.addMarker(new MarkerOptions()
                .title(gameData.get(2).name)
                .position(new LatLng(gameData.get(2).latitude,gameData.get(2).longitude)));
        spot4 = map.addMarker(new MarkerOptions()
                .title(gameData.get(3).name)
                .position(new LatLng(gameData.get(3).latitude,gameData.get(3).longitude)));
        spot5 = map.addMarker(new MarkerOptions()
                .title(gameData.get(4).name)
                .position(new LatLng(gameData.get(4).latitude,gameData.get(4).longitude)));
        spot6 = map.addMarker(new MarkerOptions()
                .title(gameData.get(5).name)
                .position(new LatLng(gameData.get(5).latitude,gameData.get(5).longitude)));
        spot7 = map.addMarker(new MarkerOptions()
                .title(gameData.get(6).name)
                .position(new LatLng(gameData.get(6).latitude,gameData.get(6).longitude)));
        spot8 = map.addMarker(new MarkerOptions()
                .title(gameData.get(7).name)
                .position(new LatLng(gameData.get(7).latitude,gameData.get(7).longitude)));
        spot9 = map.addMarker(new MarkerOptions()
                .title(gameData.get(8).name)
                .position(new LatLng(gameData.get(8).latitude,gameData.get(8).longitude)));
        spot10 = map.addMarker(new MarkerOptions()
                .title(gameData.get(9).name)
                .position(new LatLng(gameData.get(9).latitude,gameData.get(9).longitude)));

        if(stage==1) {
            spot11 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(10).name)
                    .position(new LatLng(gameData.get(10).latitude, gameData.get(10).longitude)));
            spot12 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(11).name)
                    .position(new LatLng(gameData.get(11).latitude, gameData.get(11).longitude)));
            spot13 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(12).name)
                    .position(new LatLng(gameData.get(12).latitude, gameData.get(12).longitude)));
            spot14 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(13).name)
                    .position(new LatLng(gameData.get(13).latitude, gameData.get(13).longitude)));
            spot15 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(14).name)
                    .position(new LatLng(gameData.get(14).latitude, gameData.get(14).longitude)));
            spot16 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(15).name)
                    .position(new LatLng(gameData.get(15).latitude, gameData.get(15).longitude)));
            spot17 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(16).name)
                    .position(new LatLng(gameData.get(16).latitude, gameData.get(16).longitude)));
            spot18 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(17).name)
                    .position(new LatLng(gameData.get(17).latitude, gameData.get(17).longitude)));
            spot19 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(18).name)
                    .position(new LatLng(gameData.get(18).latitude, gameData.get(18).longitude)));
            spot20 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(19).name)
                    .position(new LatLng(gameData.get(19).latitude, gameData.get(19).longitude)));
            spot21 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(20).name)
                    .position(new LatLng(gameData.get(20).latitude, gameData.get(20).longitude)));
            spot22 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(21).name)
                    .position(new LatLng(gameData.get(21).latitude, gameData.get(21).longitude)));
            spot23 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(22).name)
                    .position(new LatLng(gameData.get(22).latitude, gameData.get(22).longitude)));
            spot24 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(23).name)
                    .position(new LatLng(gameData.get(23).latitude, gameData.get(23).longitude)));
            spot25 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(24).name)
                    .position(new LatLng(gameData.get(24).latitude, gameData.get(24).longitude)));


            spot26 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(25).name)
                    .position(new LatLng(gameData.get(25).latitude, gameData.get(25).longitude)));
            spot27 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(26).name)
                    .position(new LatLng(gameData.get(26).latitude, gameData.get(26).longitude)));
            spot28 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(27).name)
                    .position(new LatLng(gameData.get(27).latitude, gameData.get(27).longitude)));
            spot29 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(28).name)
                    .position(new LatLng(gameData.get(28).latitude, gameData.get(28).longitude)));
            spot30 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(29).name)
                    .position(new LatLng(gameData.get(29).latitude, gameData.get(29).longitude)));
            spot31 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(30).name)
                    .position(new LatLng(gameData.get(30).latitude, gameData.get(30).longitude)));
            spot32 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(31).name)
                    .position(new LatLng(gameData.get(31).latitude, gameData.get(31).longitude)));
            spot33 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(32).name)
                    .position(new LatLng(gameData.get(32).latitude, gameData.get(32).longitude)));
            spot34 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(33).name)
                    .position(new LatLng(gameData.get(33).latitude, gameData.get(33).longitude)));
            spot35 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(34).name)
                    .position(new LatLng(gameData.get(34).latitude, gameData.get(34).longitude)));
            spot36 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(35).name)
                    .position(new LatLng(gameData.get(35).latitude, gameData.get(35).longitude)));
            spot37 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(36).name)
                    .position(new LatLng(gameData.get(36).latitude, gameData.get(36).longitude)));
            spot38 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(37).name)
                    .position(new LatLng(gameData.get(37).latitude, gameData.get(37).longitude)));
            spot39 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(38).name)
                    .position(new LatLng(gameData.get(38).latitude, gameData.get(38).longitude)));
            spot40 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(39).name)
                    .position(new LatLng(gameData.get(39).latitude, gameData.get(39).longitude)));
            spot41 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(40).name)
                    .position(new LatLng(gameData.get(40).latitude, gameData.get(40).longitude)));
            spot42 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(41).name)
                    .position(new LatLng(gameData.get(41).latitude, gameData.get(41).longitude)));
            spot43 = map.addMarker(new MarkerOptions()
                    .title(gameData.get(42).name)
                    .position(new LatLng(gameData.get(42).latitude, gameData.get(42).longitude)));
        }



        spot1.setVisible(false);
        spot2.setVisible(false);
        spot3.setVisible(false);
        spot4.setVisible(false);
        spot5.setVisible(false);
        spot6.setVisible(false);
        spot7.setVisible(false);
        spot8.setVisible(false);
        spot9.setVisible(false);
        spot10.setVisible(false);

        if (stage ==1) {
            spot11.setVisible(false);
            spot12.setVisible(false);
            spot13.setVisible(false);
            spot14.setVisible(false);
            spot15.setVisible(false);
            spot16.setVisible(false);
            spot17.setVisible(false);
            spot18.setVisible(false);
            spot19.setVisible(false);
            spot20.setVisible(false);
            spot21.setVisible(false);
            spot22.setVisible(false);
            spot23.setVisible(false);
            spot24.setVisible(false);
            spot25.setVisible(false);

            spot26.setVisible(false);
            spot27.setVisible(false);
            spot28.setVisible(false);
            spot29.setVisible(false);
            spot30.setVisible(false);
            spot31.setVisible(false);
            spot32.setVisible(false);
            spot33.setVisible(false);
            spot34.setVisible(false);
            spot35.setVisible(false);
            spot36.setVisible(false);
            spot37.setVisible(false);
            spot38.setVisible(false);
            spot39.setVisible(false);
            spot40.setVisible(false);
            spot41.setVisible(false);
            spot42.setVisible(false);
            spot43.setVisible(false);
        }




//        map.setMyLocationEnabled(true);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(startPoint, 15));




        CircleOptions circleOptions1 = new CircleOptions()
                .center(startPoint)
                .radius(40)
                .strokeWidth(2)
                .fillColor(Color.parseColor("#a0EAEFEA"));


                CircleOptions circleOptions2 = new CircleOptions();
        switch (stage){
            case 0:
                 circleOptions2 = new CircleOptions()
                        .center(new LatLng(35.706057, 139.706644))
                        .radius(300)
                        .strokeWidth(5)
                        .strokeColor(Color.parseColor("#f0AACC44"));
                break;
            case 1:
                 circleOptions2 = new CircleOptions()
                        .center(startPoint)
                        .radius(600)
                        .strokeWidth(5)
                        .strokeColor(Color.parseColor("#f0AACC44"));
                break;
            default:
                 circleOptions2 = new CircleOptions()
                        .center(startPoint)
                        .radius(600)
                        .strokeWidth(5)
                        .strokeColor(Color.parseColor("#f0AACC44"));
                break;

        }

        Circle circle1 = map.addCircle(circleOptions1);
        Circle circle2 = map.addCircle(circleOptions2);

//        CircleOptions circleOptions3 = new CircleOptions()
//                .center(new LatLng(latitude,longitude))
//                .radius(40)
//                .strokeWidth(3);
//        circle3 = map.addCircle(circleOptions3);



        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(final Marker marker) {
                final String inf = marker.getId();
                showDetailInfo(inf);


            }
        });

    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
//            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
//                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {


        double distance;
        gps = new GPSTracker(MainActivity.this);
        latitude = gps.getLatitude();
        longitude = gps.getLongitude();

//        circle3.setCenter(new LatLng(latitude,longitude));

        if(gameStarted == 1) {

                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot1.setVisible(false);
                } else {
                    spot1.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot2.getPosition().latitude, spot2.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot2.setVisible(false);
                } else {
                    spot2.setVisible(true);
                }



                distance = loC.distance(latitude, longitude, spot3.getPosition().latitude, spot3.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot3.setVisible(false);
                } else {
                    spot3.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot4.getPosition().latitude, spot4.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot4.setVisible(false);
                } else {
                    spot4.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot5.getPosition().latitude, spot5.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot5.setVisible(false);
                } else {
                    spot5.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot6.getPosition().latitude, spot6.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot6.setVisible(false);
                } else {
                    spot6.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot7.getPosition().latitude, spot7.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot7.setVisible(false);
                } else {
                    spot7.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot8.getPosition().latitude, spot8.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot8.setVisible(false);
                } else {
                    spot8.setVisible(true);
                }

                distance = loC.distance(latitude, longitude, spot9.getPosition().latitude, spot9.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot9.setVisible(false);
                } else {
                    spot9.setVisible(true);
                }

                distance = loC.distance(latitude, longitude, spot10.getPosition().latitude, spot10.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot10.setVisible(false);
                } else {
                    spot10.setVisible(true);
                }


            if (stage ==1) {
                distance = loC.distance(latitude, longitude, spot11.getPosition().latitude, spot11.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot11.setVisible(false);
                } else {
                    spot11.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot12.getPosition().latitude, spot12.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot12.setVisible(false);
                } else {
                    spot12.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot13.getPosition().latitude, spot13.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot13.setVisible(false);
                } else {
                    spot13.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot14.getPosition().latitude, spot14.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot14.setVisible(false);
                } else {
                    spot14.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot15.getPosition().latitude, spot15.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot15.setVisible(false);
                } else {
                    spot15.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot16.getPosition().latitude, spot16.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot16.setVisible(false);
                } else {
                    spot16.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot17.getPosition().latitude, spot17.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot17.setVisible(false);
                } else {
                    spot17.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot18.getPosition().latitude, spot18.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot18.setVisible(false);
                } else {
                    spot18.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot19.getPosition().latitude, spot19.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot19.setVisible(false);
                } else {
                    spot19.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot20.getPosition().latitude, spot20.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot20.setVisible(false);
                } else {
                    spot20.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot21.getPosition().latitude, spot21.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot21.setVisible(false);
                } else {
                    spot21.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot22.getPosition().latitude, spot22.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot22.setVisible(false);
                } else {
                    spot22.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot23.getPosition().latitude, spot23.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot23.setVisible(false);
                } else {
                    spot23.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot24.getPosition().latitude, spot24.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot24.setVisible(false);
                } else {
                    spot24.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot25.getPosition().latitude, spot25.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot25.setVisible(false);
                } else {
                    spot25.setVisible(true);
                }

                distance = loC.distance(latitude, longitude, spot26.getPosition().latitude, spot26.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot26.setVisible(false);
                } else {
                    spot26.setVisible(true);
                }

                distance = loC.distance(latitude, longitude, spot27.getPosition().latitude, spot27.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot27.setVisible(false);
                } else {
                    spot27.setVisible(true);
                }

                distance = loC.distance(latitude, longitude, spot28.getPosition().latitude, spot28.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot28.setVisible(false);
                } else {
                    spot28.setVisible(true);
                }

                distance = loC.distance(latitude, longitude, spot29.getPosition().latitude, spot29.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot29.setVisible(false);
                } else {
                    spot29.setVisible(true);
                }

                distance = loC.distance(latitude, longitude, spot30.getPosition().latitude, spot30.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot30.setVisible(false);
                } else {
                    spot30.setVisible(true);
                }

                distance = loC.distance(latitude, longitude, spot31.getPosition().latitude, spot31.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot31.setVisible(false);
                } else {
                    spot31.setVisible(true);
                }

                distance = loC.distance(latitude, longitude, spot32.getPosition().latitude, spot32.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot32.setVisible(false);
                } else {
                    spot32.setVisible(true);
                }

                distance = loC.distance(latitude, longitude, spot33.getPosition().latitude, spot33.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot33.setVisible(false);
                } else {
                    spot33.setVisible(true);
                }

                distance = loC.distance(latitude, longitude, spot34.getPosition().latitude, spot34.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot34.setVisible(false);
                } else {
                    spot34.setVisible(true);
                }

                distance = loC.distance(latitude, longitude, spot35.getPosition().latitude, spot35.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot35.setVisible(false);
                } else {
                    spot35.setVisible(true);
                }

                distance = loC.distance(latitude, longitude, spot36.getPosition().latitude, spot36.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot36.setVisible(false);
                } else {
                    spot36.setVisible(true);
                }

                distance = loC.distance(latitude, longitude, spot37.getPosition().latitude, spot37.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot37.setVisible(false);
                } else {
                    spot37.setVisible(true);
                }

                distance = loC.distance(latitude, longitude, spot38.getPosition().latitude, spot38.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot38.setVisible(false);
                } else {
                    spot38.setVisible(true);
                }

                distance = loC.distance(latitude, longitude, spot39.getPosition().latitude, spot39.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot39.setVisible(false);
                } else {
                    spot39.setVisible(true);
                }

                distance = loC.distance(latitude, longitude, spot40.getPosition().latitude, spot40.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot40.setVisible(false);
                } else {
                    spot40.setVisible(true);
                }

                distance = loC.distance(latitude, longitude, spot41.getPosition().latitude, spot41.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot41.setVisible(false);
                } else {
                    spot41.setVisible(true);
                }

                distance = loC.distance(latitude, longitude, spot42.getPosition().latitude, spot42.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot42.setVisible(false);
                } else {
                    spot42.setVisible(true);
                }

                distance = loC.distance(latitude, longitude, spot43.getPosition().latitude, spot43.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot43.setVisible(false);
                } else {
                    spot43.setVisible(true);
                }
            }



        }


//        Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }
    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
//            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    private void startNewGame(){
        Toast.makeText(getApplicationContext(), "Start new game", Toast.LENGTH_LONG).show();
        steps.setText("0 steps");
        stepSetZero = stepSetZero + currentSteps;
        currentSteps = 0;
        hintNumber =1;
        gameStarted =1;
        leftChance = 5;
        scan_range=0.060;
        checked.clear();
        chance.setText("Chance: "+leftChance);
    }

    private void showDetailInfo(final String id){
        final PopupWindow mPopupWindow = new PopupWindow(MainActivity.this);

        // レイアウト設定
        View popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);

        TextView spotName = (TextView) popupView.findViewById(R.id.spot_name);
        TextView spotInfo = (TextView) popupView.findViewById(R.id.spot_info);

        final int idToInt;

        switch (id) {
            case "m0":
               idToInt = 0;
                break;
            case "m1":
                idToInt = 1;
                break;
            case "m2":
                idToInt = 2;
                break;
            case "m3":
                idToInt = 3;
                break;
            case "m4":
                idToInt = 4;
                break;
            case "m5":
                idToInt = 5;
                break;
            case "m6":
                idToInt = 6;
                break;
            case "m7":
                idToInt = 7;
                break;
            case "m8":
                idToInt = 8;
                break;
            case "m9":
                idToInt = 9;
                break;
            case "m10":
                idToInt = 10;
                break;
            case "m11":
                idToInt = 11;
                break;
            case "m12":
                idToInt = 12;
                break;
            case "m13":
                idToInt = 13;
                break;
            case "m14":
                idToInt = 14;
                break;
            case "m15":
                idToInt = 15;
                break;
            case "m16":
                idToInt = 16;
                break;
            case "m17":
                idToInt = 17;
                break;
            case "m18":
                idToInt = 18;
                break;
            case "m19":
                idToInt = 19;
                break;
            case "m20":
                idToInt = 20;
                break;
            case "m21":
                idToInt = 21;
                break;
            case "m22":
                idToInt = 22;
                break;
            case "m23":
                idToInt = 23;
                break;
            case "m24":
                idToInt = 24;
                break;
            case "m25":
                idToInt = 25;
                break;
            case "m26":
                idToInt = 26;
                break;
            case "m27":
                idToInt = 27;
                break;
            case "m28":
                idToInt = 28;
                break;
            case "m29":
                idToInt = 29;
                break;
            case "m30":
                idToInt = 30;
                break;
            case "m31":
                idToInt = 31;
                break;
            case "m32":
                idToInt = 32;
                break;
            case "m33":
                idToInt = 33;
                break;
            case "m34":
                idToInt = 34;
                break;
            case "m35":
                idToInt = 35;
                break;
            case "m36":
                idToInt = 36;
                break;
            case "m37":
                idToInt = 37;
                break;
            case "m38":
                idToInt = 38;
                break;
            case "m39":
                idToInt = 39;
                break;
            case "m40":
                idToInt = 40;
                break;
            case "m41":
                idToInt = 41;
                break;
            case "m42":
                idToInt = 42;
                break;
            default:
                idToInt = 0;
                break;
        }


        spotName.setText(gameData.get(idToInt).name);
        spotInfo.setText(gameData.get(idToInt).description);

        popupView.findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
            }
        });
        mPopupWindow.setContentView(popupView);


        // 背景設定
        mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.popup_background));

        // タップ時に他のViewでキャッチされないための設定
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);

        // 表示サイズの設定 今回は幅300dp
        float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics());
        mPopupWindow.setWindowLayoutMode((int) width, WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setWidth((int) width);
        mPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);



        if (checked.contains(id) != true && gameStarted==1) {

               new AlertDialog.Builder(MainActivity.this).setTitle(gameData.get(idToInt).name)
                        .setPositiveButton("Destination!",new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (idToInt != destination){
                                    leftChance --;
                                    chance.setText("Chance: "+leftChance);
                                    if (leftChance == 0){
                                        Toast.makeText(getApplicationContext(), "Game Over. Please try again.", Toast.LENGTH_LONG).show();
                                        gameStarted = 0;
                                    }else {
                                        Toast.makeText(getApplicationContext(), "Ops. It's not a treasure. You have "+leftChance+" chance left.", Toast.LENGTH_LONG).show();
                                    }
                                }else {
                                    Toast.makeText(getApplicationContext(), "Congratulation! You find the treasure in " + currentSteps + " steps", Toast.LENGTH_LONG).show();
                                    gameStarted = 0;
                                    mPopupWindow.showAtLocation(findViewById(R.id.map), Gravity.CENTER, 0, 0);
                                    checked.add(id);
                                }

                            }

                        }).setNegativeButton("Get hint",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (gameData.get(idToInt).bonusType) {
                            case 0:

                                int earn =(int)(2+Math.random()*3);
                                coins = coins + earn;

                                coinText.setText("Coins: " + coins);
                                Toast.makeText(getApplicationContext(), "You got "+earn+ " coins.", Toast.LENGTH_LONG).show();
                                checked.add(id);
                                break;
                            case 1:
                                hintNumber++;
                                Toast.makeText(getApplicationContext(), "You got a new hint! Check it now.", Toast.LENGTH_LONG).show();
                                checked.add(id);
                                break;
                            case 2:
                                checked.add(id);
                                break;
                            case 3:
//                                Toast.makeText(getApplicationContext(), "Congratulation! You find the treasure in" + currentSteps + "steps", Toast.LENGTH_LONG).show();
//                                gameStarted = 0;
//                                break;
                                coins = coins + 3;
                                coinText.setText("Coins: " + coins);
                                Toast.makeText(getApplicationContext(), "You got 3 coins.", Toast.LENGTH_LONG).show();
                                checked.add(id);
                                break;
                            default:
                                checked.add(id);
                                break;
                        }
                        // 画面中央に表示
                        mPopupWindow.showAtLocation(findViewById(R.id.map), Gravity.CENTER, 0, 0);
                    }

                }).show();






        }else if (checked.contains(id) != false && gameStarted==1)
        {
            new AlertDialog.Builder(MainActivity.this).setTitle(gameData.get(idToInt).name)
                    .setPositiveButton("Destination!",new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (idToInt == 21){
                                Toast.makeText(getApplicationContext(), "Congratulation! You find the treasure in" + currentSteps + "steps", Toast.LENGTH_LONG).show();
                                gameStarted = 0;
                                checked.add(id);
                            }else {
                                leftChance --;
                                chance.setText("Chance: "+leftChance);
                                if (leftChance == 0){
                                    Toast.makeText(getApplicationContext(), "Game Over. Please try again.", Toast.LENGTH_LONG).show();
                                    gameStarted = 0;
                                }else {
                                    Toast.makeText(getApplicationContext(), "Ops. It's not a treasure. You have "+leftChance+" chance left.", Toast.LENGTH_LONG).show();
                                }
                            }
                            // 画面中央に表示
                            mPopupWindow.showAtLocation(findViewById(R.id.map), Gravity.CENTER, 0, 0);
                        }

                    }).setNegativeButton("Get hint",new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "You have already checked this spot", Toast.LENGTH_LONG).show();
                    // 画面中央に表示
                    mPopupWindow.showAtLocation(findViewById(R.id.map), Gravity.CENTER, 0, 0);
                }

            }).show();
        }








    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // accuracy に変更があった時の処理
    }

    @Override
    public void onSensorChanged(SensorEvent event) {


        Sensor sensor = event.sensor;
        float[] values = event.values;
        long timestamp = event.timestamp;
        //TYPE_STEP_COUNTER
        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            // sensor からの値を取得するなどの処理を行う
            currentSteps = (int)values[0]-stepSetZero;
                steps.setText(currentSteps + " steps");

        }


    }
    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener (this,
                mStepCounterSensor,
                SensorManager.SENSOR_DELAY_NORMAL);

        mSensorManager.registerListener(this,
                mStepDetectorSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this, mStepCounterSensor);
        mSensorManager.unregisterListener(this,mStepDetectorSensor);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            return  true;
        }
        return  super.onKeyDown(keyCode, event);
    }




}
