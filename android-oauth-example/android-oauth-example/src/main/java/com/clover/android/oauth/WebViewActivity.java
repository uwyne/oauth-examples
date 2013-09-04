package com.clover.android.oauth;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by jwils on 9/3/13.
 */
public class WebViewActivity extends Activity
{
    private static final String TAG = "ActivityWebView";

    /**
     * Get these values when you create your app at clover.com
     * The callback url must begin with your domain
     **/
    public static final String CALLBACK_URL = "your callback url";
    public static final String CLIENT_ID = "your client id";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        String url =
                "https://clover.com/oauth/authorize" +
                        "?client_id=" + CLIENT_ID +
                        "&response_type=token" +
                        "&redirect_uri=" + CALLBACK_URL;

        WebView webview = (WebView)findViewById(R.id.webView);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                String accessTokenFragment = "#access_token=";
                String merchantIdFragment = "&merchant_id=";

                int accessTokenStart = url.indexOf(accessTokenFragment);
                int merchantIdStart = url.indexOf(merchantIdFragment);
                if (accessTokenStart > -1) {
                    // You can use the accessToken for api calls now.
                    String accessToken = url.substring(accessTokenStart + accessTokenFragment.length(), merchantIdStart);
                    String merchantId = url.substring(merchantIdStart + merchantIdFragment.length(), url.length());

                    //MAKE an oauth call.
                }
            }
        });
        webview.loadUrl(url);
    }
}
