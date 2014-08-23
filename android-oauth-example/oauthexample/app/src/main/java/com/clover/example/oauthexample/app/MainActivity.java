package com.clover.example.oauthexample.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    public static final int OAUTH_REQUEST_CODE = 0;

    public static final String AUTH_CODE = "auth_code";
    public static final String MERCHANT_ID_KEY = "merchant_id";
    public static final String EMPLOYEE_ID_KEY = "employee_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Starts intent to fetch OAuth 2.0 information
                Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                startActivityForResult(intent, OAUTH_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == OAUTH_REQUEST_CODE && resultCode == RESULT_OK && data != null) {

            // Access data from the completed intent
            String token = data.getStringExtra(AUTH_CODE);
            String merchantId = data.getStringExtra(MERCHANT_ID_KEY);
            String employeeId = data.getStringExtra(EMPLOYEE_ID_KEY);
            Toast.makeText(MainActivity.this, token, Toast.LENGTH_LONG).show();

            Button btn = (Button)findViewById(R.id.button);
            btn.setVisibility(View.GONE);

            TextView txtView = (TextView)findViewById(R.id.textView);
            txtView.setText("Auth Token = " + token + "\nMerchant Id = " + merchantId +"\nEmployee Id = " + employeeId);
        }
        else {
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }

}
