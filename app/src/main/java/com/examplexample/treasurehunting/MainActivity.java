package com.examplexample.treasurehunting;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
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
    List checked = new ArrayList();
    final static double scan_range=3.080;
//    Marker treasure;


    private SensorManager mSensorManager;
    private Sensor mStepDetectorSensor;
    private Sensor mStepCounterSensor;
    int currentSteps = 0;
    int stepSetZero = 0;

    int hintNumber = 0;
    int gameStarted =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, InformationActivity.class);

                        Bundle bundle=new Bundle();
                        bundle.putInt("hintNumber",hintNumber);
                        intent.putExtras(bundle);

                        startActivity(intent);
                        slidingDrawer.close();
                    }
                });

                Button button3 = (Button) findViewById(R.id.button3);
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String hint1 = "Locked";
                        String hint2 = "Locked";
                        String hint3 = "Locked";
                        String hint4 = "Locked";
                        String hint5 = "Locked";
                        new AlertDialog.Builder(MainActivity.this).setTitle("HINTS").setItems(
                                new String[] { hint1, hint2,hint3,hint4,hint5 }, null).setNegativeButton(
                                "OKay", null).show();
                        slidingDrawer.close();

                    }
                });


                Button button4 = (Button) findViewById(R.id.button4);
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDetailInfo("button4");
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

        LatLng tullys = new LatLng(35.706003, 139.708591);
        LatLng bld51 = new LatLng(35.705997, 139.706732);
        LatLng westgate = new LatLng(35.706406, 139.704828);

        spot1 = map.addMarker(new MarkerOptions()
                .title("Tullys coffee")
                .snippet("Is the treasure here?")
                .position(tullys));

        spot2 =  map.addMarker(new MarkerOptions()
                .title("Building 51")
                .position(bld51));

        spot3 = map.addMarker(new MarkerOptions()
                .title("west gate of the campus")
                .position(westgate));

        spot1.setVisible(false);
        spot2.setVisible(false);
        spot3.setVisible(false);





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
                marker.setVisible(false);
                String inf = marker.getId();
                checked.add(inf);
                if (inf.equals("m0")){
                    Toast.makeText(getApplicationContext(), "Congratulation! You find the treasure in"+ currentSteps+"steps", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "You got a new hint! Check it now!", Toast.LENGTH_LONG).show();
                    showDetailInfo(inf);
                    hintNumber++;
                }
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


        //TODO


        double distance;

        if(gameStarted == 1) {

            if (checked.contains("m0") != true) {
                distance = loC.distance(latitude, longitude, spot1.getPosition().latitude, spot1.getPosition().longitude, "K");
                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot1.setVisible(false);
                } else {
                    spot1.setVisible(true);
                }
            }

            if (checked.contains("m1") != true) {
                distance = loC.distance(latitude, longitude, spot2.getPosition().latitude, spot2.getPosition().longitude, "K");
                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot2.setVisible(false);
                } else {
                    spot2.setVisible(true);
                }
            }

            if (checked.contains("m2") != true) {
                distance = loC.distance(latitude, longitude, spot3.getPosition().latitude, spot3.getPosition().longitude, "K");
                Toast.makeText(getApplicationContext(), "Distance is :" + distance, Toast.LENGTH_LONG).show();
                if (distance >= scan_range) {
                    spot3.setVisible(false);
                } else {
                    spot3.setVisible(true);
                }
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
        spotName.setText(id);
        spotInfo.setText(id + "information");
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




}
