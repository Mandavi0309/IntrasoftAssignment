package com.android.intrasoft.newsapplication.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.example.ankitaga.newsapplication.R;

public class NewsDetailActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportActionBar().hide();

    setContentView(R.layout.activity_news_detail);
    WebView webView = (WebView) findViewById(R.id.webview);

    WebSettings webSettings = webView.getSettings();
    webSettings.setJavaScriptEnabled(true);
    final ProgressDialog progressBar = ProgressDialog
        .show(NewsDetailActivity.this, "", "Loading...");
    progressBar.setCancelable(true);
    webView.setWebViewClient(new WebViewClient() {
      public boolean shouldOverrideUrlLoading(WebView view, String url) {

        view.loadUrl(url);
        return true;
      }

      public void onPageFinished(WebView view, String url) {

        if (progressBar.isShowing()) {
          progressBar.dismiss();
        }
      }


    });
    webView.loadUrl(getIntent().getStringExtra("URL"));
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
