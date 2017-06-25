package com.examplexample.treasurehunting;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.models.nosql.UserDataStorageDO;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

public class CloudSecureActivity extends AppCompatActivity {



    TextView editView;
    private Runnable runnable;
    private Runnable runnable2;
    private Runnable checkAutho;
    String username;
    Double latitude;
    Double longitude;
    int currentSteps;
    int coins;
    int hintNumber;

    String username2;
    Double latitude2;
    Double longitude2;
    int currentSteps2;
    int coins2;
    int hintNumber2;

    String latitude3;
    String longitude3;
    String currentSteps3;
    String coins3;
    String hintNumber3;


    int exist = 0;
    int authorization = 0;
    int right;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud2);


        editView= (TextView) findViewById(R.id.editText);

        Bundle bundle = this.getIntent().getExtras();
        username = bundle.getString("username");
        latitude = bundle.getDouble("latitude");
        longitude = bundle.getDouble("longitude");
        currentSteps = bundle.getInt("stepNumber");
        coins = bundle.getInt("coins");
        hintNumber = bundle.getInt("unlockedHint");





        Button button1 = (Button) findViewById(R.id.cloudUploadBottom);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread mythread = new Thread(runnable);
                mythread.start();
            }
        });

        Button button2 = (Button) findViewById(R.id.cloudCheckButton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editView.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "You did not enter a username", Toast.LENGTH_LONG).show();

                } else {
                    right = checkRight(username, editView.getText().toString());
                    if (right == 1) {
                        Thread mythread = new Thread(runnable2);
                        mythread.start();

                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                        }

                        latitude3 = "LatestLat = " + latitude2;
                        longitude3 = "LatestLon = " + longitude2;
                        currentSteps3 = "Steps: " + String.valueOf(currentSteps2);
                        coins3 = "Coins: " + String.valueOf(coins2);
                        hintNumber3 = "Hints: " + String.valueOf(hintNumber2);

                        if (exist == 1) {
                            new AlertDialog.Builder(CloudSecureActivity.this).setTitle(username2).setItems(
                                    new String[]{latitude3, longitude3, currentSteps3, coins3, hintNumber3}, null).setNegativeButton(
                                    "OK", null).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Player does not exist.", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "No access right!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });




        // Initialize the Amazon Cognito credentials provider
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "ap-northeast-1:a4ef65bd-f3d1-416a-9d74-9c49a3d0dc61", // Identity Pool ID
                Regions.AP_NORTHEAST_1 // Region
        );


        AmazonDynamoDBClient ddbClient = new AmazonDynamoDBClient(credentialsProvider);

        // デフォルトではUS-EASTがリージョンで指定されてしまうため意図的にAP_NORTHEASTにしています
        Region apNortheast1 = Region.getRegion(Regions.AP_NORTHEAST_1);
        ddbClient.setRegion(apNortheast1);

        final DynamoDBMapper mapper = new DynamoDBMapper(ddbClient);

        // インターネットのアクセスを行うためにスレッドを作成し、その中でインサート処理を定義します
        runnable = new Runnable() {
            public void run() {
                UserDataStorageDO userdata = new UserDataStorageDO();
                userdata.setUserName(username);
                userdata.setLatestLatitude(latitude);
                userdata.setLatestLongitude(longitude);
                userdata.setStepNumber(currentSteps);
                userdata.setCoins(coins);
                userdata.setUnlockHintNumber(hintNumber);

                mapper.save(userdata);
            };
        };

        runnable2 = new Runnable() {
            public void run() {

                UserDataStorageDO loadUserdata = mapper.load(UserDataStorageDO.class,editView.getText().toString());
                if (loadUserdata != null) {
                    username2 = loadUserdata.getUserName();
                    latitude2 = loadUserdata.getLatestLatitude();
                    longitude2 = loadUserdata.getLatestLongitude();
                    currentSteps2 = loadUserdata.getStepNumber();
                    coins2 = loadUserdata.getCoins();
                    hintNumber2 = loadUserdata.getUnlockHintNumber();
                    exist = 1;
                }else{
                    exist = 0;
                }



            };
        };

        checkAutho = new Runnable() {
            public void run() {
                UserDataStorageDO loadUserdata = mapper.load(UserDataStorageDO.class,username);
                authorization = loadUserdata.getAuthorization();

            };
        };

        Thread mythread = new Thread(checkAutho);
        mythread.start();

    }


    private int checkRight(String name, String checkedName){
        if (authorization == 1||name.equals(checkedName)){
            return 1;
        }else {
            return 0;
        }
    }
}
