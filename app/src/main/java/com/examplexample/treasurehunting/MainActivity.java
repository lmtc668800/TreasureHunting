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




    List checked = new ArrayList();
    final static double scan_range=13.080;
//    Marker treasure;


    private SensorManager mSensorManager;
    private Sensor mStepDetectorSensor;
    private Sensor mStepCounterSensor;
    int currentSteps = 0;
    int stepSetZero = 0;

    int hintNumber = 0;
    int gameStarted =0;

    int unlockedGame = 0;
    int coins=0;

    List<SpotData> gameData;
    List<String> hintData;

    TextView coinText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = this.getIntent().getExtras();
        coins = bundle.getInt("coins");
        unlockedGame = bundle.getInt("unlockedGame");

        coinText= (TextView) findViewById(R.id.coins_text_main);
        coinText.setText("Coins: " + coins);


        DataLoader dataLoader = new DataLoader();
        gameData = dataLoader.LoadIn();
        hintData = dataLoader.LoadHints();




//        FragmentManager fragmentManager = getSupportFragmentManager();
//        SupportMapFragment mapFragment = SupportMapFragment.newInstance();


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
                        startNewGame();
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

                        slidingDrawer.close();

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
                                        coins++;
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







//        fragmentManager.beginTransaction().replace(R.id.flContent, mapFragment).commit();

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Synchronize current location", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                onMyLocationButtonClick();
//            }
//        });
//
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);


        //SOLUTION1:failed
//        openGPSSettings();
//        getLocation();



        mSensorManager =  (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);

        //センサマネージャから TYPE_STEP_DETECTOR についての情報を取得する
        mStepDetectorSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        //センサマネージャから TYPE_STEP_COUNTER についての情報を取得する
        mStepCounterSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
////        Fragment fragment = null;
////        Class fragmentClass;
//
////        if (id == R.id.map_page) {
//        // Handle the map action
////            fragmentClass = SupportMapFragment.class;
////            FragmentManager fragmentManager = getSupportFragmentManager();
////            fragmentManager.popBackStack();
////            super.onResume();
////
////        } else {
//        if (id == R.id.step_counter) {
//            Intent intent = new Intent();
//            intent.setClass(MainActivity.this, InformationActivity.class);
//            startActivity(intent);
//        } else if (id == R.id.information) {
//
//            //TODO: Hint page
//            Intent intent = new Intent();
//            intent.setClass(MainActivity.this, InformationActivity.class);
//            startActivity(intent);
//        } else {
//
//        }
//
////            try {
////
////                fragment = (Fragment) fragmentClass.newInstance();
////
////            } catch (Exception e) {
////
////                e.printStackTrace();
////
////            }
//
//
//        // Insert the fragment by replacing any existing fragment
////            FragmentManager fragmentManager = getSupportFragmentManager();
////            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
//
////            FragmentTransaction transaction = fragmentManager.beginTransaction();
////            transaction.replace(R.id.flContent, fragment);
////            transaction.addToBackStack("name");
////            transaction.commit();
////            super.onPause();
//
//
////        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }



    @Override
    public void onMapReady(GoogleMap map) {

        mMap = map;
        mMap.setOnMyLocationButtonClickListener(this);
        enableMyLocation();

//        LatLng tullys = new LatLng(35.706003, 139.708591);


        LatLng bld51 = new LatLng(35.705997, 139.706732);
        LatLng westgate = new LatLng(35.706406, 139.704828);

/*        spot1 = map.addMarker(new MarkerOptions()
                .title(gameData.get(0).name)
//                .snippet("Is the treasure here?")
                .position(new LatLng(gameData.get(0).latitude,gameData.get(0).longitude)));

        spot2 =  map.addMarker(new MarkerOptions()
                .title("Building 51")
                .position(bld51));

        spot3 = map.addMarker(new MarkerOptions()
                .title("west gate of the campus")
                .position(westgate));*/




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
        spot11 = map.addMarker(new MarkerOptions()
                .title(gameData.get(10).name)
                .position(new LatLng(gameData.get(10).latitude,gameData.get(10).longitude)));
        spot12 = map.addMarker(new MarkerOptions()
                .title(gameData.get(11).name)
                .position(new LatLng(gameData.get(11).latitude,gameData.get(11).longitude)));
        spot13 = map.addMarker(new MarkerOptions()
                .title(gameData.get(12).name)
                .position(new LatLng(gameData.get(12).latitude,gameData.get(12).longitude)));
        spot14 = map.addMarker(new MarkerOptions()
                .title(gameData.get(13).name)
                .position(new LatLng(gameData.get(13).latitude,gameData.get(13).longitude)));
        spot15 = map.addMarker(new MarkerOptions()
                .title(gameData.get(14).name)
                .position(new LatLng(gameData.get(14).latitude,gameData.get(14).longitude)));
        spot16 = map.addMarker(new MarkerOptions()
                .title(gameData.get(15).name)
                .position(new LatLng(gameData.get(15).latitude,gameData.get(15).longitude)));
        spot17 = map.addMarker(new MarkerOptions()
                .title(gameData.get(16).name)
                .position(new LatLng(gameData.get(16).latitude,gameData.get(16).longitude)));
        spot18 = map.addMarker(new MarkerOptions()
                .title(gameData.get(17).name)
                .position(new LatLng(gameData.get(17).latitude,gameData.get(17).longitude)));
        spot19 = map.addMarker(new MarkerOptions()
                .title(gameData.get(18).name)
                .position(new LatLng(gameData.get(18).latitude,gameData.get(18).longitude)));
        spot20 = map.addMarker(new MarkerOptions()
                .title(gameData.get(19).name)
                .position(new LatLng(gameData.get(19).latitude,gameData.get(19).longitude)));
        spot21 = map.addMarker(new MarkerOptions()
                .title(gameData.get(20).name)
                .position(new LatLng(gameData.get(20).latitude,gameData.get(20).longitude)));
        spot22 = map.addMarker(new MarkerOptions()
                .title(gameData.get(21).name)
                .position(new LatLng(gameData.get(21).latitude,gameData.get(21).longitude)));
        spot23 = map.addMarker(new MarkerOptions()
                .title(gameData.get(22).name)
                .position(new LatLng(gameData.get(22).latitude,gameData.get(22).longitude)));
        spot24 = map.addMarker(new MarkerOptions()
                .title(gameData.get(23).name)
                .position(new LatLng(gameData.get(23).latitude,gameData.get(23).longitude)));
        spot25 = map.addMarker(new MarkerOptions()
                .title(gameData.get(24).name)
                .position(new LatLng(gameData.get(24).latitude,gameData.get(24).longitude)));


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




//        map.setMyLocationEnabled(true);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(westgate, 15));




//        CircleOptions circleOptions = new CircleOptions()
//                .center(bld51)
//                .radius(50)
//                .strokeWidth(5);

// Get back the mutable Circle
//        Circle circle = map.addCircle(circleOptions);

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                String inf = marker.getId();
                showDetailInfo(inf);


//                if (inf.equals("m0")){
//                    Toast.makeText(getApplicationContext(), "Congratulation! You find the treasure in"+ currentSteps+"steps", Toast.LENGTH_LONG).show();
//                }else{
//                    Toast.makeText(getApplicationContext(), "You got a new hint! Check it now!", Toast.LENGTH_LONG).show();
//                    showDetailInfo(inf);
////                    hintNumber++;
//                }
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
        gps = new GPSTracker(MainActivity.this);
        latitude = gps.getLatitude();
        longitude = gps.getLongitude();


        double distance;

        if(gameStarted == 1) {

                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spot1.setVisible(false);
                } else {
                    spot1.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot2.getPosition().latitude, spot2.getPosition().longitude, "K");
                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot2.setVisible(false);
                } else {
                    spot2.setVisible(true);
                }



                distance = loC.distance(latitude, longitude, spot3.getPosition().latitude, spot3.getPosition().longitude, "K");
                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot3.setVisible(false);
                } else {
                    spot3.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot4.setVisible(false);
                } else {
                    spot4.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot5.setVisible(false);
                } else {
                    spot5.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot6.setVisible(false);
                } else {
                    spot6.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot7.setVisible(false);
                } else {
                    spot7.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot8.setVisible(false);
                } else {
                    spot8.setVisible(true);
                }

                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot9.setVisible(false);
                } else {
                    spot9.setVisible(true);
                }

                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot10.setVisible(false);
                } else {
                    spot10.setVisible(true);
                }

                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot11.setVisible(false);
                } else {
                    spot11.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot12.setVisible(false);
                } else {
                    spot12.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot13.setVisible(false);
                } else {
                    spot13.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot14.setVisible(false);
                } else {
                    spot14.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot15.setVisible(false);
                } else {
                    spot15.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot16.setVisible(false);
                } else {
                    spot16.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot17.setVisible(false);
                } else {
                    spot17.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot18.setVisible(false);
                } else {
                    spot18.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot19.setVisible(false);
                } else {
                    spot19.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot20.setVisible(false);
                } else {
                    spot20.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot21.setVisible(false);
                } else {
                    spot21.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot22.setVisible(false);
                } else {
                    spot22.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot23.setVisible(false);
                } else {
                    spot23.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot24.setVisible(false);
                } else {
                    spot24.setVisible(true);
                }


                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
//                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot25.setVisible(false);
                } else {
                    spot25.setVisible(true);
                }
        }


        //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
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
        TextView steps=(TextView) this.findViewById(R.id.steps);
        steps.setText("0 steps");
        stepSetZero = stepSetZero + currentSteps;
        currentSteps = 0;
        hintNumber =1;
        gameStarted =1;
        checked.clear();
    }

    private void showDetailInfo(String id){
        final PopupWindow mPopupWindow = new PopupWindow(MainActivity.this);

        // レイアウト設定
        View popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);

        TextView spotName = (TextView) popupView.findViewById(R.id.spot_name);
        TextView spotInfo = (TextView) popupView.findViewById(R.id.spot_info);

        int idToInt;

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
            default:
                idToInt = 0;
                break;
        }

        if (checked.contains(id) != true && gameStarted==1) {
            switch (gameData.get(idToInt).bonusType) {
                case 0:
                    coins = coins + 5;
                    coinText.setText("Coins: " + coins);
                    break;
                case 1:
                    hintNumber++;
                    break;
                case 2:
                    break;
                case 3:
                    Toast.makeText(getApplicationContext(), "Congratulation! You find the treasure in" + currentSteps + "steps", Toast.LENGTH_LONG).show();
                    gameStarted = 0;
                    break;
                default:
                    break;
            }
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

            // 画面中央に表示
            mPopupWindow.showAtLocation(findViewById(R.id.map), Gravity.CENTER, 0, 0);

            checked.add(id);



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
            TextView steps=(TextView) this.findViewById(R.id.steps);
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
