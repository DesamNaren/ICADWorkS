package cgg.gov.in.icadworks.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.base.BaseActivity;
import cgg.gov.in.icadworks.custom.CustomFontEditText;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.interfaces.LoginView;
import cgg.gov.in.icadworks.model.response.itemStatus.ItemStatusResponse;
import cgg.gov.in.icadworks.model.response.items.WorkItemsResponse;
import cgg.gov.in.icadworks.model.response.login.EmployeeDetailss;
import cgg.gov.in.icadworks.presenter.LoginPresenter;
import cgg.gov.in.icadworks.util.ConnectionDetector;
import cgg.gov.in.icadworks.util.Utilities;

public class LoginActivity extends LocBaseActivity implements LoginView {

    @BindView(R.id.userNameET)
    CustomFontEditText userNameET;
    @BindView(R.id.userManualTV)
    CustomFontTextView userManualTV;
    @BindView(R.id.pwdET)
    CustomFontEditText pwdET;
    @BindView(R.id.loginBtn)
    CustomFontTextView loginBtn;
    @BindView(R.id.progress)
    ProgressBar progress;
    private LoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        try {
            getSupportActionBar().hide();
        } catch (Exception e) {
            e.printStackTrace();

        }


        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);

        if (ConnectionDetector.isConnectedToInternet(this)) {
            Gson gson = new Gson();
            SharedPreferences sharedPreferences = getSharedPreferences("APP_PREF", MODE_PRIVATE);
            String statusMasterData = sharedPreferences.getString("STATUS_MASTER_DATA", "");
            String defUsername = sharedPreferences.getString("DEFAULT_USER_NAME", "");
            String defUserPwd = sharedPreferences.getString("DEFAULT_USER_PWD", "");
            itemStatusResponse = gson.fromJson(statusMasterData, ItemStatusResponse.class);
            if (itemStatusResponse == null) {
                progress.setVisibility(View.VISIBLE);
                loginPresenter.getItemStatus(defUsername, defUserPwd);
            }
        } else {
            Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.no_internet), false);
        }

        userManualTV.setPaintFlags(userManualTV.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        userManualTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, UserManualActivity.class));
            }
        });
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressIndicator(Boolean show) {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void getLoginResponseSuccess(EmployeeDetailss employeeDetailss) {
        progress.setVisibility(View.GONE);
        try {
            if (employeeDetailss != null) {
                if (employeeDetailss.getEmployeeDetail() != null) {
                    SharedPreferences sharedPreferences = getSharedPreferences("APP_PREF", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    editor.putString("LOGIN_DATA", gson.toJson(employeeDetailss));
                    editor.putString("USERNAME", userNameET.getText().toString().trim());
                    editor.putString("PWD", pwdET.getText().toString().trim());
                    editor.putString("DEFAULT_USER_NAME", "irrigationts");
                    editor.putString("DEFAULT_USER_PWD", "irrigationts");
                    editor.putInt("DEFAULT_SELECTION", 0);

                    editor.commit();
                    startActivity(new Intent(this, DashboardActivity.class));
                    finish();
                } else {
                    Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.something), false);
                }
            } else {
                Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.server), false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.something), false);
        }
    }

    ItemStatusResponse itemStatusResponse;

    @Override
    public void getItemStatusResponse(ItemStatusResponse itemStatusResponse) {
        progress.setVisibility(View.GONE);
        try {
            if (itemStatusResponse != null) {
                if (itemStatusResponse.getData() != null && itemStatusResponse.getStatusCode() == 200 && itemStatusResponse.getData().size() > 0) {
                    this.itemStatusResponse = itemStatusResponse;
                    SharedPreferences sharedPreferences = getSharedPreferences("APP_PREF", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    editor.putString("STATUS_MASTER_DATA", gson.toJson(itemStatusResponse));
                    editor.commit();
//                    if (ConnectionDetector.isConnectedToInternet(this)) {
//                        loginPresenter.getWorkItems("irrigationts","irrigationts");
//                    }else {
//                        Utilities.showFancyErrorAlert(this,getResources().getString(R.string.please_check_internet));
//                    }
                }
            } else {
//                Utilities.showFancyErrorAlert(this, getResources().getString(R.string.server));
                Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.server), false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    WorkItemsResponse workItemsResponse;

    @Override
    public void getWorkItemsResponse(WorkItemsResponse workItemsResponse) {
        progress.setVisibility(View.GONE);
        try {
            if (workItemsResponse != null) {
                if (workItemsResponse.getData() != null && workItemsResponse.getStatusCode() == 200 && workItemsResponse.getData().size() > 0) {
                    this.workItemsResponse = workItemsResponse;
                    SharedPreferences sharedPreferences = getSharedPreferences("APP_PREF", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    editor.putString("WORK_TYPE_MASTER_DATA", gson.toJson(workItemsResponse));
                    editor.commit();
                }
            } else {
                Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.server), false);
//                Utilities.showFancyErrorAlert(this, getResources().getString(R.string.server));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.loginBtn)
    public void onViewClicked() {


        if (Utilities.isFiledEmpty(userNameET.getText().toString().trim())) {
            userNameET.setError(getResources().getString(R.string.user_name_validation));
            userNameET.requestFocus();
            return;
        }

        if (Utilities.isFiledEmpty(pwdET.getText().toString().trim())) {
            pwdET.setError(getResources().getString(R.string.pwd_validation));
            pwdET.requestFocus();
            return;
        }

        String userName = userNameET.getText().toString().trim();
        String md5Pwd = Utilities.md5Hash(pwdET.getText().toString().trim());

        if (itemStatusResponse != null
                && itemStatusResponse.getData() != null && itemStatusResponse.getData().size() > 0) {
            if (ConnectionDetector.isConnectedToInternet(this)) {
                progress.setVisibility(View.VISIBLE);
                loginPresenter.submitLogin(userName, md5Pwd);
            } else {
                Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.please_check_internet), false);
            }
        } else {

            Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.server) + " Failed to download master data", false);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mGpsSwitchStateReceiver, new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION));

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                mCurrentLocation = locationResult.getLastLocation();
            }
        };
    }

    @Override
    public void onBackPressed() {
        if (progress.getVisibility() != View.VISIBLE) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mGpsSwitchStateReceiver);
    }


    private BroadcastReceiver mGpsSwitchStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (intent.getAction().matches("android.location.PROVIDERS_CHANGED")) {
                    callPermissions();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
