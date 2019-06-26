package cgg.gov.in.icadworks.base;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;

import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.custom.CustomFontTextView;

public class BaseActivity extends AppCompatActivity {

    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
    }

//    public void setUpToolBar(String title, Boolean showHome) {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        if (toolbar != null) {
//            CustomFontTextView titleView = (CustomFontTextView) toolbar.findViewById(R.id.toolbar_title);
//            titleView.setText(title);
//        }
//        setSupportActionBar(toolbar);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            ActionBar actionBar = getSupportActionBar();
//            if (actionBar != null) {
//                actionBar.setDisplayHomeAsUpEnabled(showHome);
//                actionBar.setDisplayShowTitleEnabled(false);
////                final Drawable upArrow = getResources().getDrawable(android.R.drawable.abc_ic_ab_back_mtrl_am_alpha);
////                upArrow.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
//            }
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
//        }
//    }
//
//    public void setUpToolBar(String title, Boolean showHome, int homeRes) {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        if (toolbar != null) {
//            CustomFontTextView titleView = (CustomFontTextView) toolbar.findViewById(R.id.toolbar_title);
//            titleView.setText(title);
//        }
//        setSupportActionBar(toolbar);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            ActionBar actionBar = getSupportActionBar();
//            if (actionBar != null) {
//                actionBar.setDisplayHomeAsUpEnabled(showHome);
//                actionBar.setHomeAsUpIndicator(homeRes);
//                actionBar.setDisplayShowTitleEnabled(false);
//            }
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    /**
     * show or dismiss progress
     *
     * @param show show
     */
    public void showProgressIndicator(Boolean show) {
        try {
            if (progressDialog != null) {
                if (show) {
                    progressDialog.show();
                } else {
                    progressDialog.dismiss();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * show with custom message
     *
     * @param show    show
     * @param message message to display
     */
    public void showProgressIndicator(Boolean show, String message) {
        if (progressDialog != null) {
            progressDialog.setMessage(message);
            showProgressIndicator(show);
        }
    }

    /**
     * show with cancelable
     *
     * @param show       show
     * @param cancelable cancelable
     */
    public void showProgressIndicator(Boolean show, Boolean cancelable) {
        if (progressDialog != null) {
            progressDialog.setCancelable(cancelable);
            showProgressIndicator(show);
        }
    }

    /**
     * show with custom message and cancelable
     *
     * @param show       show
     * @param message    message
     * @param cancelable cancelable
     */
    public void showProgressIndicator(Boolean show, String message, Boolean cancelable) {
        if (progressDialog != null) {
            progressDialog.setCancelable(cancelable);
            showProgressIndicator(show, message);
        }
    }
}
