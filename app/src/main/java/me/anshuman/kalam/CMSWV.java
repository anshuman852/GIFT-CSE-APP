package me.anshuman.kalam;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.net.URI;

public class CMSWV extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String url=getIntent().getStringExtra("url");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmswv);
        WebView myWebView = findViewById(R.id.cmswv);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setSupportZoom(true);
        myWebView.getSettings().setDisplayZoomControls(true);

        System.out.println(url);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                super.onPageStarted(view, url, favicon);
                findViewById(R.id.progressBar2).setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                findViewById(R.id.progressBar2).setVisibility(View.GONE);
            }
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getApplicationContext(), description, Toast.LENGTH_SHORT).show();
            }
            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });
        myWebView.loadUrl(url);

    }
}