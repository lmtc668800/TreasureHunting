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
import android.util.Log;
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

import com.amazonaws.AmazonClientException;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
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

//Cloud contents from here
import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.models.nosql.SpotDataDO;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.models.nosql.SpotLocationsDO;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.*;


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

//cloud
    private SpotLocationsDO tmpSpot;
    private SpotLocationsDO loadSpot;
    private Runnable runnable1;
    private Runnable runnable2;
    String content = "null";

    //Cloud/Database

//    public void insertData() {

//        // Fetch the default configured DynamoDB ObjectMapper
//        final DynamoDBMapper dynamoDBMapper = AWSMobileClient.defaultMobileClient().getDynamoDBMapper();
//        final SpotDataDO tmpSpot = new SpotDataDO(); // Initialize the Notes Object
//
//        // The userId has to be set to user's Cognito Identity Id for private / protected tables.
//        // User's Cognito Identity Id can be fetched by using:
//        tmpSpot.setName("tmpName");
//        tmpSpot.setDescription("tmpDescription");
//        tmpSpot.setBonusType(1.0);
//        tmpSpot.setLatitude(35.706407);
//        tmpSpot.setLongitude(139.704855);
//        tmpSpot.setBonusContent("tmpBonusContent");
//
//        dynamoDBMapper.save(tmpSpot);
//
//    }



//    String tmpName = "花園神社";
//    Double tmpLatitude;
//    Double tmpLongitude;
//    String tmpDescription;
//    int tmpBonusType;
//    String tmpBonusContent;
//    public void getData(String name) {
//        // Fetch the default configured DynamoDB ObjectMapper
//        final DynamoDBMapper dynamoDBMapper = AWSMobileClient.defaultMobileClient().getDynamoDBMapper();
//
//        final SpotDataDO tmpSpot = new SpotDataDO(); // Initialize the Notes Object
//
//        // The userId has to be set to user's Cognito Identity Id for private / protected tables.
//        // User's Cognito Identity Id can be fetched by using:
//        tmpSpot.setName("tmpName");
//        tmpSpot.setDescription("tmpDescription");
//        tmpSpot.setBonusType(1.0);
//        tmpSpot.setLatitude(35.706407);
//        tmpSpot.setLongitude(139.704855);
//        tmpSpot.setBonusContent("tmpBonusContent");
//
//        dynamoDBMapper.save(tmpSpot);
//
//    }

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
    int totalSpotNumber = 0;

    List<SpotData> gameData;
    List<Marker> spotList= new ArrayList<>();
    List<String> hintData;

    TextView coinText;
    TextView steps;
    TextView chance;

    LatLng startPoint;

//    Circle circle3;







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
                totalSpotNumber = 10;
                break;

            case 1:
                destination =21;
                totalSpotNumber = 43;
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
//                          start distance changed
                            if (distance<= 100.040){
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


        //cloud
        // Initialize the Amazon Cognito credentials provider
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "ap-northeast-1:a4ef65bd-f3d1-416a-9d74-9c49a3d0dc61", // Identity Pool ID
                Regions.AP_NORTHEAST_1 // Region
        );


        AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(credentialsProvider);
        final DynamoDBMapper mapper = new DynamoDBMapper(ddbClient);

        // デフォルトではUS-EASTがリージョンで指定されてしまうため意図的にAP_NORTHEASTにしています
        Region apNortheast1 = Region.getRegion(Regions.AP_NORTHEAST_1);
        ddbClient.setRegion(apNortheast1);



/*        runnable2 = new Runnable() {
            public void run() {
                SpotLocationsDO loadSpot;
                for (int l=0; l<10;l++){
                    loadSpot= mapper.load(SpotLocationsDO.class,l);
                    loadSpot.getName();
                }

            };
        };*/


    }




    @Override
    public void onMapReady(GoogleMap map) {

        mMap = map;
        mMap.setOnMyLocationButtonClickListener(this);
        enableMyLocation();

        //cloud
//        Thread mythread = new Thread(runnable2);
//        mythread.start();



        for (int l=0; l<totalSpotNumber; l++){
            Marker marker = map.addMarker(new MarkerOptions()
                    .title(gameData.get(l).name)
                    .position(new LatLng(gameData.get(l).latitude,gameData.get(l).longitude)));
            spotList.add(marker);
            marker.setVisible(false);
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

            for (int l=0;l<totalSpotNumber;l++){
                distance = loC.distance(latitude, longitude, spotList.get(l).getPosition().latitude, spotList.get(l).getPosition().longitude, "K");
                if (distance >= scan_range) {
                    spotList.get(l).setVisible(false);
                } else {
                    spotList.get(l).setVisible(true);
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
                            if (idToInt == destination){
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


    private void CloudLoadIn(int stage){
            List<SpotData> shinjuku = new ArrayList<>();

        // Initialize the Amazon Cognito credentials provider
        final CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "ap-northeast-1:a4ef65bd-f3d1-416a-9d74-9c49a3d0dc61", // Identity Pool ID
                Regions.AP_NORTHEAST_1 // Region
        );

        final AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(credentialsProvider);

        final DynamoDBMapper mapper = new DynamoDBMapper(ddbClient);

        runnable1 = new Runnable() {
            public void run() {
                SpotLocationsDO loadSpot = mapper.load(SpotLocationsDO.class,14);
                content = loadSpot.getName();
            };
        };



//Bonus type:0->gold 1->Hint 2->SmallHint 3->treasure
           /* SpotData spot1 = new SpotData("花園神社", 35.693436, 139.705262, "This shrine was founded in the mid-17th century.", 1, "Inside/beside a Department Store");
            SpotData spot2 = new SpotData("TOHOシネマズ新宿", 35.695100, 139.701690, "You can have a discount on every 1st and 14th day of the month!", 1, "Go to the East.");
            SpotData spot3 = new SpotData("新宿ピカデリー", 35.692676, 139.703616, "Find: Tourist Spot ", 2, "hint");
            SpotData spot4 = new SpotData("歌舞伎町", 35.693769, 139.701344, "Find: Giant Monster Movie", 2, "hint");
            SpotData spot5 = new SpotData("新宿野村ビル", 35.693311, 139.695985, "Welcome..Uh, maybe not", 0, "hint");
            SpotData spot6 = new SpotData("東京モード学園", 35.691529, 139.696872, "Find: Feeling tiring of working?\nLet’s find somewhere quiet, somewhere in the city, but take us out of the city", 2, "hint");
            SpotData spot7 = new SpotData("ヤマダ電機 LABI新宿東口館", 35.693481, 139.700824, "Need any electric goods? Visit here!", 0, "hint");
            SpotData spot8 = new SpotData("伊勢丹 新宿店", 35.691929, 139.704283, "Find: Some where with Flowers.", 2, "hint");
            SpotData spot9 = new SpotData("Kirin City キリンシティプラス新宿東南口", 35.690357, 139.701309, "Find: Here is what you have at night, find somewhere you would like to have in the morning ", 2, "hint");
            SpotData spot10 = new SpotData("小田急百貨店 新宿店", 35.691284, 139.699321, "Find: Let’s explore the Another Area", 2, "hint");
            SpotData spot11 = new SpotData("紀伊国屋書店新宿本店", 35.691911, 139.702804, "Big book Store! Reading book is a good hobby.", 0, "hint");
            SpotData spot12 = new SpotData("新宿プリンスホテル", 35.694531, 139.700161, "Find: Rabbit", 2, "hint");
            SpotData spot13 = new SpotData("スターバックスコーヒー 新宿新南口店", 35.688886, 139.702420, "Find: somewhere is competitor of here", 2, "hint");
            SpotData spot14 = new SpotData("ビームス 新宿", 35.691458, 139.701122, "A clothing brand which is established in 1976", 0, "hint");
            SpotData spot15 = new SpotData("ビックロ ビックカメラ 新宿東口", 35.691399, 139.703069, "I have a uniqlo, I have a bic Camera.Ah~BiQlo", 0, "hint");
            SpotData spot16 = new SpotData("損保ジャパン日本興亜美術館", 35.692450, 139.695846, "Artist Museum. Visit here next time!", 1, "It is a CINEMA ");
            SpotData spot17 = new SpotData("新宿マルイ メン", 35.692506, 139.706005, "Find: Try to find another 'OIOI' department.", 2, "hint");
            SpotData spot18 = new SpotData("京王百貨店 新宿店", 35.690025, 139.698844, "Find: To left or to right? ", 2, "hint");
            SpotData spot19 = new SpotData("三菱東京UFJ銀行 新宿通支店", 35.690688, 139.704508, "Find: You may on the correct 'way'.", 2, "hint");
            SpotData spot20 = new SpotData("ファミリーマート 新宿損保ジャパン店", 35.693045, 139.696550, "Buy some drinks and take a rest here. Don't worry about the steps.", 0, "hint");
            SpotData spot21 = new SpotData("新宿ミロード", 35.689230, 139.699803, "Welcome", 0, "hint");
            SpotData spot22 = new SpotData("新宿バルト9", 35.690269, 139.705781, "1300yen during 17:30～19：55.", 3, "hint");
            SpotData spot23 = new SpotData("新宿区役所 本庁舎", 35.693805, 139.703463, "Don't forget pay for your tax.", 0, "hint");
            SpotData spot24 = new SpotData("K’s　cinema", 35.690143, 139.702769, "Find: Isn’t it close?", 2, "hint");
            SpotData spot25 = new SpotData("ブルーボトルコーヒー 新宿カフェ", 35.688809, 139.702068, "What about have a coffee here?", 1, "Having a very famous cafe nearby.");

            SpotData spot26 = new SpotData("伊勢丹メンズ館", 35.692585, 139.704564, "Find: If you have three wishes, what would they be", 2, "hint");
            SpotData spot27 = new SpotData("新宿 東南口駐輪場", 35.689603, 139.701626, "Find: Somewhere opened in 2016. Be careful when pass the street.", 2, "hint");
            SpotData spot28 = new SpotData("ドン・キホーテ 新宿東口本店", 35.693762, 139.701682, "Find: somewhere founded in 1930s. ", 2, "hint");
            SpotData spot29 = new SpotData("DEAN & DELUCA MARKET STORES 新宿", 35.689478, 139.701332, "Welcome", 0, "hint");
            SpotData spot30 = new SpotData("新宿西口ハルク", 35.692743, 139.698809, "Find: Yes, you can find something in this area.But who knows what is you really want.", 2, "hint");
            SpotData spot31 = new SpotData("スターバックスコーヒー 新宿エルタワー店", 35.692142, 139.697776, "Welcome", 0, "hint");
            SpotData spot32 = new SpotData("モザイク通り", 35.690969, 139.699362, "Find: How about try seeing from another perspective?", 2, "hint");
            SpotData spot33 = new SpotData("新宿駅東口駐車場", 35.691926, 139.701305, "Find: I hpoe you are coming from west.", 2, "hint");
            SpotData spot34 = new SpotData("マクドナルド 新宿スバルビル店", 35.691405, 139.697807, "Welcome", 0, "hint");
            SpotData spot35 = new SpotData("湖南菜館", 35.694311, 139.701032, "Find: Almost there", 2, "hint");
            SpotData spot36 = new SpotData("河合塾新宿校", 35.693516, 139.696838, "Welcome", 0, "hint");
            SpotData spot37 = new SpotData("六歌仙", 35.693408, 139.698893, "Are we going to the EAST? ", 2, "hint");
            SpotData spot38 = new SpotData("FOREVER21 新宿店", 35.690011, 139.704679, "Welcome", 0, "hint");
            SpotData spot39 = new SpotData("新宿高島屋", 35.687451, 139.702663, "Don't spend too much time here.", 0, "hint");
            SpotData spot40 = new SpotData("びゅうプラザ", 35.688236, 139.700641, "How did you find me lol lol lol", 0, "hint");
            SpotData spot41 = new SpotData("Brooklyn Parlor 新宿", 35.690251, 139.705960, "Find: Sometimes we only need a little confidence and bravery.", 2, "It is a CINEMA ");
            SpotData spot42 = new SpotData("世界堂 新宿本店", 35.689996, 139.706475, "Find: Try to find another Marui department.", 2, "hint");
            SpotData spot43 = new SpotData("百果園", 35.692539, 139.700947, "Have some fruits with you!", 0, "hint");


            shinjuku.add(spot1);
            shinjuku.add(spot2);
            shinjuku.add(spot3);
            shinjuku.add(spot4);
            shinjuku.add(spot5);
            shinjuku.add(spot6);
            shinjuku.add(spot7);
            shinjuku.add(spot8);
            shinjuku.add(spot9);
            shinjuku.add(spot10);
            shinjuku.add(spot11);
            shinjuku.add(spot12);
            shinjuku.add(spot13);
            shinjuku.add(spot14);
            shinjuku.add(spot15);
            shinjuku.add(spot16);
            shinjuku.add(spot17);
            shinjuku.add(spot18);
            shinjuku.add(spot19);
            shinjuku.add(spot20);
            shinjuku.add(spot21);
            shinjuku.add(spot22);
            shinjuku.add(spot23);
            shinjuku.add(spot24);
            shinjuku.add(spot25);
            shinjuku.add(spot26);
            shinjuku.add(spot27);
            shinjuku.add(spot28);
            shinjuku.add(spot29);
            shinjuku.add(spot30);
            shinjuku.add(spot31);
            shinjuku.add(spot32);
            shinjuku.add(spot33);
            shinjuku.add(spot34);
            shinjuku.add(spot35);
            shinjuku.add(spot36);
            shinjuku.add(spot37);
            shinjuku.add(spot38);
            shinjuku.add(spot39);
            shinjuku.add(spot40);
            shinjuku.add(spot41);
            shinjuku.add(spot42);
            shinjuku.add(spot43);*/


    }




}
