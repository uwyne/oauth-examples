package com.clover.android.oauth;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by jwils on 9/3/13.
 */
public class WebViewActivity extends Activity
{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        String url =
                "https://clover.com/oauth/authorize" +
                        "?client_id=" + Config.CLIENT_ID +
                        "&response_type=token" +
                        "&redirect_uri=" + Config.APP_DOMAIN;

        WebView webview = (WebView)findViewById(R.id.webView);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                String accessTokenFragment = "#access_token=";
                String merchantIdFragment = "&merchant_id=";

                int accessTokenStart = url.indexOf(accessTokenFragment);
                int merchantIdStart = url.indexOf(merchantIdFragment);
                if (accessTokenStart > -1) {
                    String accessToken = url.substring(accessTokenStart + accessTokenFragment.length(), merchantIdStart);
                    String merchantId = url.substring(merchantIdStart + merchantIdFragment.length(), url.length());

                    Intent output = new Intent();
                    output.putExtra(MainActivity.ACCESS_TOKEN_KEY, accessToken);
                    output.putExtra(MainActivity.MERCHANT_ID_KEY, merchantId);
                    setResult(RESULT_OK, output);
                    finish();
                }
            }
        });
        webview.loadUrl(url);
    }
}
