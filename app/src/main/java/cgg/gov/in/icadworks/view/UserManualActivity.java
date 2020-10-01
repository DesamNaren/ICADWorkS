package cgg.gov.in.icadworks.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cgg.gov.in.icadworks.BuildConfig;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.base.BaseActivity;
import cgg.gov.in.icadworks.interfaces.SplashView;
import cgg.gov.in.icadworks.model.response.version.VersionResponse;
import cgg.gov.in.icadworks.presenter.SplashPresenter;
import cgg.gov.in.icadworks.util.ConnectionDetector;
import cgg.gov.in.icadworks.util.Utilities;

public class UserManualActivity extends BaseActivity {

    ProgressBar progressBar;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_manual_fragment);
        try {
            getSupportActionBar().setTitle("User Manual");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().show();
        } catch (Exception e) {
            e.printStackTrace();

        }

        webView = findViewById(R.id.webview);
        progressBar = findViewById(R.id.progress);
        if (ConnectionDetector.isConnectedToInternet(this)) {
            progressBar.setVisibility(View.VISIBLE);
            Utilities.loadWebView(this, progressBar, webView, BuildConfig.USER_MANUAL_URL);
        }else {
            Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.no_internet), true);
        }
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