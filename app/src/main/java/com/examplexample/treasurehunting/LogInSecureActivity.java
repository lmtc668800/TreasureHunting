package com.examplexample.treasurehunting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.models.nosql.UserDataStorageDO;
import com.amazonaws.models.nosql.UserPoolDO;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

public class LogInSecureActivity extends AppCompatActivity {

    int demoType;
    Runnable signIn;
    String username;
    String password;
    int exist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        Bundle bundle = this.getIntent().getExtras();
        demoType = bundle.getInt("DemoType");

        final TextView textView = (TextView) findViewById(R.id.username);
        final TextView textView2 = (TextView) findViewById(R.id.password);


        ClientConfiguration clientConfiguration = new ClientConfiguration();
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
        signIn = new Runnable() {
            public void run() {
                UserPoolDO user = mapper.load(UserPoolDO.class,username);
                if (user!=null) {
                    password = user.getPassword();
                    exist =1;
                }else{
                    exist = 0;
                }
            };
        };




        Button button1 = (Button) findViewById(R.id.signIn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = textView.getText().toString();
               String inputPassword = textView2.getText().toString();

//                Toast.makeText(getApplicationContext(), textView.getText(), Toast.LENGTH_LONG).show();

                if (username.matches("")) {

                    Toast.makeText(getApplicationContext(), "You did not enter a username", Toast.LENGTH_LONG).show();

                }else {
                    Thread mythread = new Thread(signIn);
                    mythread.start();

                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }

                    if (exist == 1) {
                        if (inputPassword.equals(password)) {
                            Toast.makeText(getApplicationContext(), "Signed in.", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent();
                            intent.setClass(LogInSecureActivity.this, MasterActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("coins", 50);
                            bundle.putInt("unlockedGame", 1);
                            bundle.putString("username", username);
                            bundle.putInt("DemoType", demoType);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "password incorrect! Please try again.", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Player does not exist.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });




    }
}
