package com.examplexample.treasurehunting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.models.nosql.SpotLocationsDO;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.*;

public class CloudActivity extends AppCompatActivity {



    TextView text;
    private SpotLocationsDO tmpSpot;
    private SpotLocationsDO loadSpot;
    private Runnable runnable;
    private Runnable runnable2;
    String content = "null";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud);

        text= (TextView) findViewById(R.id.cloudData);
        text.setText("Origin");

        Button button = (Button) findViewById(R.id.cloudBottom);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                text.setText(content);
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
                SpotLocationsDO tmpSpot = new SpotLocationsDO();
                //never set to 0-42
/*                tmpSpot.setItemId(50);
                tmpSpot.setName("tmp");
                tmpSpot.setLatitude(35.694531);
                tmpSpot.setLongitude(139.700161);
                tmpSpot.setDescription("Find: Rabbit");
                tmpSpot.setBonusType(2);
                tmpSpot.setBonusContent("hint");
                mapper.save(tmpSpot);*/

                //存在响应时间问题
                SpotLocationsDO loadSpot = mapper.load(SpotLocationsDO.class,38);
                content = loadSpot.getName();
            };
        };

        runnable2 = new Runnable() {
            public void run() {
                SpotLocationsDO loadSpot = mapper.load(SpotLocationsDO.class,"花園神社");
                content = loadSpot.getName();
            };
        };

        Thread mythread = new Thread(runnable);
        mythread.start();




    }
}
