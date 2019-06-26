package cgg.gov.in.icadworks.fragment;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.model.response.login.EmployeeDetailss;
import cgg.gov.in.icadworks.util.Utilities;
import cgg.gov.in.icadworks.view.DashboardActivity;

import static android.content.Context.MODE_PRIVATE;

public class InfoFragment extends Fragment {

    @BindView(R.id.empNameTV)
    CustomFontTextView empNameTV;
    @BindView(R.id.desTV)
    CustomFontTextView desTV;
    Unbinder unbinder;
    private int defSelection;
    private SharedPreferences sharedPreferences;
    private EmployeeDetailss employeeDetailss = null;
    private int pos=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment, container, false);
        setHasOptionsMenu(true);
        unbinder = ButterKnife.bind(this, view);
        setProfileData();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            ((DashboardActivity)getActivity()).getSupportActionBar().setTitle("Info");
            ((DashboardActivity)getActivity()).getSupportActionBar().setSubtitle("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setProfileData() {
        try {
            Gson gson = new Gson();
            if (getActivity() != null) {
                sharedPreferences = getActivity().getSharedPreferences("APP_PREF", MODE_PRIVATE);
                String string = sharedPreferences.getString("LOGIN_DATA", "");
                defSelection = sharedPreferences.getInt("DEFAULT_SELECTION", -1);
                employeeDetailss = gson.fromJson(string, EmployeeDetailss.class);
                if (defSelection>=0 && employeeDetailss != null) {
                    empNameTV.setText(employeeDetailss.getEmployeeDetail().get(defSelection).getEmpName());
                    desTV.setText(employeeDetailss.getEmployeeDetail().get(defSelection).getDesignation());
                } else {
                    Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.something), false);
                }
            }
        } catch (Exception e) {
            Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.something), false);
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onPrepareOptionsMenu(menu);
        inflater.inflate(R.menu.dashboard, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_manual:
                try {
                    Fragment fragment = new UserManualFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.action_logout:
                Utilities.showCustomNetworkAlertLogout(getActivity(), "Do you want logout from app?");
                return true;
            default:
                break;
        }

        return false;
    }
}
