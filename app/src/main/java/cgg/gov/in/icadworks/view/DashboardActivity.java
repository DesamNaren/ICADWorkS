package cgg.gov.in.icadworks.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.gson.Gson;

import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.fragment.DashboardFragment;
import cgg.gov.in.icadworks.fragment.InfoFragment;
import cgg.gov.in.icadworks.fragment.ProfileFragment;
import cgg.gov.in.icadworks.fragment.ReportFragmenNewt;
import cgg.gov.in.icadworks.fragment.ReportFragment;
import cgg.gov.in.icadworks.fragment.UserManualFragment;
import cgg.gov.in.icadworks.fragment.VPDashboardFragment;
import cgg.gov.in.icadworks.model.response.login.EmployeeDetailss;
import cgg.gov.in.icadworks.util.Utilities;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private CustomFontTextView name, des;
    private NavigationView navigationView;
    private int defSelection;
    private EmployeeDetailss employeeDetailss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        selectedFragment = new ReportFragmenNewt();
        fragmentTag = "Home";
        displayFragment(selectedFragment, fragmentTag);

        Gson gson = new Gson();
        SharedPreferences sharedPreferences = getSharedPreferences("APP_PREF", MODE_PRIVATE);
        String string = sharedPreferences.getString("LOGIN_DATA", "");
        defSelection = sharedPreferences.getInt("DEFAULT_SELECTION", -1);
        employeeDetailss = gson.fromJson(string, EmployeeDetailss.class);
        if (defSelection >= 0 && employeeDetailss != null) {

            if (employeeDetailss.getEmployeeDetail().get(defSelection).getUnitId().trim().equalsIgnoreCase("0")) {
                navigationView.getMenu().findItem(R.id.nav_profile).setVisible(false);
            } else {
                navigationView.getMenu().findItem(R.id.nav_profile).setVisible(true);
            }

            View headerView = navigationView.getHeaderView(0);
            name = headerView.findViewById(R.id.nameTVHeader);
            des = headerView.findViewById(R.id.nameDesHeader);
            name.setText(employeeDetailss.getEmployeeDetail().get(defSelection).getEmpName());
            des.setText(employeeDetailss.getEmployeeDetail().get(defSelection).getDesignation());
        } else {
            Utilities.showCustomNetworkAlert(this, getResources().getString(R.string.something), false);
        }
    }

    Fragment selectedFragment = null;

    @Override
    public void onBackPressed() {

        try {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                selectedFragment = new ReportFragmenNewt();
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentTag);
                if (fragment == null) {
                    Utilities.showCustomNetworkAlertLogout(this, "Do you want logout and exit from app?");
                } else if (fragment instanceof ReportFragmenNewt) {
                    Utilities.showCustomNetworkAlertLogout(this, "Do you want logout and exit from app?");
                } else if (fragment instanceof ProfileFragment) {
                    fragmentTag = "Profile";
                    navigationView.setCheckedItem(R.id.nav_home);
                    displayFragment(selectedFragment, fragmentTag);
                } else if (fragment instanceof DashboardFragment) {
                    fragmentTag = "Report";
                    navigationView.setCheckedItem(R.id.nav_home);
                    displayFragment(selectedFragment, fragmentTag);
                } else if (fragment instanceof UserManualFragment) {
                    fragmentTag = "UserManual";
                    navigationView.setCheckedItem(R.id.nav_home);
                    displayFragment(selectedFragment, fragmentTag);
                } else if (fragment instanceof InfoFragment) {
                    fragmentTag = "Info";
                    navigationView.setCheckedItem(R.id.nav_home);
                    displayFragment(selectedFragment, fragmentTag);
                }

            }
        } catch (Exception e) {
            DashboardActivity.this.finish();
        }

    }

    String fragmentTag = null;

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            fragmentTag = "Home";
            selectedFragment = new ReportFragmenNewt();
            displayFragment(selectedFragment, fragmentTag);

        } else if (id == R.id.nav_profile) {
            fragmentTag = "Profile";
            selectedFragment = new ProfileFragment();
            displayFragment(selectedFragment, fragmentTag);

        } else if (id == R.id.nav_reports) {
            fragmentTag = "Reports";
            selectedFragment = new DashboardFragment();
            displayFragment(selectedFragment, fragmentTag);
        } else if (id == R.id.nav_info) {
            fragmentTag = "Info";
            selectedFragment = new InfoFragment();
            displayFragment(selectedFragment, fragmentTag);
        } else if (id == R.id.nav_manual) {
            fragmentTag = "UserManual";
            selectedFragment = new UserManualFragment();
            displayFragment(selectedFragment, fragmentTag);
        } else if (id == R.id.nav_logout) {
            Utilities.showCustomNetworkAlertLogout(this, "Do you want logout from app?");
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void displayFragment(Fragment fragment, String name) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment, name);
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }
}
