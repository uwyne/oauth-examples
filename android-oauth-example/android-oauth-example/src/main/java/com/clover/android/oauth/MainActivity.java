package com.clover.android.oauth;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final int OAUTH_REQUEST_CODE = 0;

    public static final String ACCESS_TOKEN_KEY = "access_token";
    public static final String MERCHANT_ID_KEY = "merchant_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                startActivityForResult(intent, OAUTH_REQUEST_CODE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (requestCode == OAUTH_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String token = data.getStringExtra(ACCESS_TOKEN_KEY);
            String merchantId = data.getStringExtra(MERCHANT_ID_KEY);
            Toast.makeText(MainActivity.this, token, Toast.LENGTH_LONG).show();

            Button btn = (Button)findViewById(R.id.button);
            btn.setVisibility(View.GONE);

            TextView txtView = (TextView)findViewById(R.id.textView);
            txtView.setText("Access Token = " + token + "\nMerchant Id =" + merchantId);

        }
    }
}
