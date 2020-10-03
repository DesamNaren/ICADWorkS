package cgg.gov.in.icadworks.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.google.gson.Gson;

import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.adapter.WorkDetailsAdapter;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.interfaces.WorkDetailsView;
import cgg.gov.in.icadworks.model.response.login.EmployeeDetailss;
import cgg.gov.in.icadworks.model.response.works.WorkDetailsResponse;
import cgg.gov.in.icadworks.presenter.WorkDetailsPresenter;
import cgg.gov.in.icadworks.util.ConnectionDetector;
import cgg.gov.in.icadworks.util.Utilities;

public class WorkDetailsActivity extends AppCompatActivity implements WorkDetailsView {

    private RecyclerView workRv;
    private CustomFontTextView emptyTV;
    private CustomFontTextView tsOts;
    private ProgressBar progressBar;
    private Menu mMenu;
    EmployeeDetailss employeeDetailss = null;
    private int defSelection;
    private String defUsername, defUserPwd;
    private int pos;
    private WorkDetailsAdapter workDetailsAdapter;
    private RelativeLayout mainRL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_details_fragment);
        workRv = findViewById(R.id.workRv);
        emptyTV = findViewById(R.id.emptyTV);
        tsOts = findViewById(R.id.tsOts);
        progressBar = findViewById(R.id.progress);
        mainRL = findViewById(R.id.mainRL);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("TS[OTs]");
        }

        try {
            if (getIntent() != null) {
                String tsOtsText = getIntent().getStringExtra("Total_TS");
                tsOts.setText(tsOtsText);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Gson gson = new Gson();
            SharedPreferences sharedPreferences = getSharedPreferences("APP_PREF", MODE_PRIVATE);
            String string = sharedPreferences.getString("LOGIN_DATA", "");
            String userName = sharedPreferences.getString("USERNAME", "");
            String userPwd = sharedPreferences.getString("PWD", "");
            defUsername = sharedPreferences.getString("DEFAULT_USER_NAME", "");
            defUserPwd = sharedPreferences.getString("DEFAULT_USER_PWD", "");
            defSelection = sharedPreferences.getInt("DEFAULT_SELECTION", -1);
            employeeDetailss = gson.fromJson(string, EmployeeDetailss.class);
        } catch (Exception e) {
            Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.something), false);
            e.printStackTrace();
        }


        WorkDetailsPresenter workDetailsPresenter = new WorkDetailsPresenter();
        workDetailsPresenter.attachView(this);
        if (ConnectionDetector.isConnectedToInternet(this)) {
            if (employeeDetailss != null) {
                progressBar.setVisibility(View.VISIBLE);
                workDetailsPresenter.getWorkDetails(employeeDetailss.getEmployeeDetail().get(defSelection).getSectionId(),
                        employeeDetailss.getEmployeeDetail().get(defSelection).getSubdivisionId(),
                        employeeDetailss.getEmployeeDetail().get(defSelection).getDivisionId(),
                        employeeDetailss.getEmployeeDetail().get(defSelection).getCircleId(),
                        employeeDetailss.getEmployeeDetail().get(defSelection).getUnitId(),
                        "1",
                        defUsername,
                        defUserPwd);
            } else {
                Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.something), false);
            }
        } else {
            Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.no_internet), false);
        }

    }

    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    if (workDetailsAdapter != null) {
                        workDetailsAdapter.getFilter().filter(newText);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
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

    }

    @Override
    public void getWorkDetailsResponse(WorkDetailsResponse workDetailsResponse) {
        progressBar.setVisibility(View.GONE);
        try {
            if (workDetailsResponse != null) {
                if (workDetailsResponse.getStatusCode() != null && workDetailsResponse.getStatusCode() == 200) {
                    emptyTV.setVisibility(View.GONE);
                    if (mMenu != null)
                        mMenu.findItem(R.id.action_search).setVisible(true);
                    setDataAdapter(workDetailsResponse);
                } else {
                    workRv.setAdapter(null);
                    emptyTV.setVisibility(View.VISIBLE);
                    if (mMenu != null)
                        mMenu.findItem(R.id.action_search).setVisible(false);
                    Utilities.showCustomNetworkAlert(this, "No records found", false);
                }
            } else {
                Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.server), false);
            }
        } catch (Exception e) {
            Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.something), false);
            e.printStackTrace();
        }
    }

    @Override
    public void showProgressIndicator(Boolean show) {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    void setDataAdapter(WorkDetailsResponse workDetailsResponse) {
        workDetailsAdapter = new WorkDetailsAdapter(workDetailsResponse.getData(), this);
        workRv.setLayoutManager(new LinearLayoutManager(this));
        workRv.setAdapter(workDetailsAdapter);
    }

    private SearchView searchView = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        mMenu = menu;

        mMenu.findItem(R.id.action_view).setVisible(false);
        mMenu.findItem(R.id.action_logout).setVisible(true);
        mMenu.findItem(R.id.action_search).setVisible(true);

        MenuItem menuItem = mMenu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setQueryHint("Search by Chain ID or OT Name or location");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                search(searchView);
                return true;
            }
        });
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            Utilities.showCustomNetworkAlertLogout(this, "Do you want logout from app?");
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return false;
    }

}
