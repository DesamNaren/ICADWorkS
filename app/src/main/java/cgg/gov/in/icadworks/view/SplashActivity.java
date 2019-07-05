package cgg.gov.in.icadworks.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.base.BaseActivity;
import cgg.gov.in.icadworks.interfaces.SplashView;
import cgg.gov.in.icadworks.model.response.version.VersionResponse;
import cgg.gov.in.icadworks.presenter.SplashPresenter;
import cgg.gov.in.icadworks.util.ConnectionDetector;
import cgg.gov.in.icadworks.util.CustomProgressDialog;
import cgg.gov.in.icadworks.util.Utilities;

public class SplashActivity extends BaseActivity implements SplashView {

    @BindView(R.id.progress)
    ProgressBar progress;
    private SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);


        try {
            getSupportActionBar().hide();
        } catch (Exception e) {
            e.printStackTrace();

        }

        splashPresenter = new SplashPresenter();
        splashPresenter.attachView(this);

        if (ConnectionDetector.isConnectedToInternet(this)) {

            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP){
                progress.getIndeterminateDrawable()
                        .setColorFilter(ContextCompat.getColor(this, R.color.orange), PorterDuff.Mode.SRC_IN );
            }

            progress.setVisibility(View.VISIBLE);
            splashPresenter.getAppVersion("irrigationts", "irrigationts");
        } else {
            Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.please_check_internet), true);
        }


    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showMessage(int stringId) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void getAppVersionResponse(VersionResponse versionResponse) {
        progress.setVisibility(View.GONE);
        try {
            if (versionResponse != null) {
                if (versionResponse.getStatusCode() == 200) {

                    if (versionResponse.getAppversion().equalsIgnoreCase(Utilities.getAppVersion(this))) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                SharedPreferences sharedPreferences = getSharedPreferences("APP_PREF", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("DEFAULT_USER_NAME", "irrigationts");
                                editor.putString("DEFAULT_USER_PWD", "irrigationts");
                                editor.commit();

                                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                                finish();
                            }
                        }, 1000);
                    } else {
                        Utilities.showCustomPlayAlertLogout(this, getResources().getString(R.string.update));

                    }
                } else {
                    Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.something), true);
                }
            } else {
                Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.server), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.something), true);
        }
    }



    @Override
    public void onBackPressed() {
        if(progress.getVisibility()!=View.VISIBLE){
            super.onBackPressed();
        }
    }
}
