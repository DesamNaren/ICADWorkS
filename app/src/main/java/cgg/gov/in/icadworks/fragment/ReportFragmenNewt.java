package cgg.gov.in.icadworks.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.interfaces.ReportView;
import cgg.gov.in.icadworks.model.response.login.EmployeeDetailss;
import cgg.gov.in.icadworks.model.response.report.ReportResponse;
import cgg.gov.in.icadworks.presenter.ReportPresenter;
import cgg.gov.in.icadworks.util.ConnectionDetector;
import cgg.gov.in.icadworks.util.Utilities;
import cgg.gov.in.icadworks.view.DashboardActivity;

import static android.content.Context.MODE_PRIVATE;

public class ReportFragmenNewt extends Fragment implements ReportView {

    SharedPreferences sharedPreferences;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.emptyTV)
    CustomFontTextView emptyTV;
    Unbinder unbinder;

    private String defUsername, defUserPwd;
    private int defSelection;
    private int pos;

    private EmployeeDetailss employeeDetailss = null;
    private ReportPresenter reportPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_fragment, container, false);
        setHasOptionsMenu(true);
        unbinder = ButterKnife.bind(this, view);
        pos = 0;


        reportPresenter = new ReportPresenter();
        reportPresenter.attachView(this);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
        tabLayout.setSelectedTabIndicatorHeight((int) (2 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));

        try {
            Gson gson = new Gson();
            sharedPreferences = getActivity().getSharedPreferences("APP_PREF", MODE_PRIVATE);
            String string = sharedPreferences.getString("LOGIN_DATA", "");
            defUsername = sharedPreferences.getString("DEFAULT_USER_NAME", "");
            defUserPwd = sharedPreferences.getString("DEFAULT_USER_PWD", "");
            defSelection = sharedPreferences.getInt("DEFAULT_SELECTION", -1);
            employeeDetailss = gson.fromJson(string, EmployeeDetailss.class);
            if (employeeDetailss == null) {
                Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.something), false);
            }
        } catch (Exception e) {
            Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.something), false);
            e.printStackTrace();
        }

        if (ConnectionDetector.isConnectedToInternet(getActivity())) {
            if (defSelection >= 0 && employeeDetailss != null) {
                progress.setVisibility(View.VISIBLE);
                reportPresenter.getDashboardReport(employeeDetailss.getEmployeeDetail().get(defSelection).getSectionId(),
                        employeeDetailss.getEmployeeDetail().get(defSelection).getSubdivisionId(),
                        employeeDetailss.getEmployeeDetail().get(defSelection).getDivisionId(),
                        employeeDetailss.getEmployeeDetail().get(defSelection).getCircleId(),
                        employeeDetailss.getEmployeeDetail().get(defSelection).getUnitId(),
                        defUsername,
                        defUserPwd);
            } else {
                Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.something), false);
            }
        } else {
            Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.please_check_internet), false);
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    ReportResponse reportResponse;

    @Override
    public void getReportResponse(ReportResponse reportResponse) {
        try {
            progress.setVisibility(View.GONE);
            if (reportResponse != null) {
                if (reportResponse.getStatusCode()!=null && reportResponse.getStatusCode() == 200 && reportResponse.getData() != null && reportResponse.getData().size() > 0) {
                    this.reportResponse = reportResponse;
                    sharedPreferences = getActivity().getSharedPreferences("APP_PREF", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    editor.putString("REPORT_DATA", gson.toJson(reportResponse));
                    editor.commit();
                    viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));

                } else if (reportResponse.getStatus() != null && reportResponse.getStatus() == 404) {
                    emptyTV.setVisibility(View.VISIBLE);
                    emptyTV.setText(reportResponse.getTag());
                    Utilities.showCustomNetworkAlert(getActivity(), reportResponse.getTag(), false);
                } else {
                    Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.server), false);
                }
            } else {

                Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.server), false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showMessage(int stringId) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showProgressIndicator(Boolean show) {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ProjectWiseFragment();
                case 1:
                    return new CEWiseFragment();
                case 2:
                    return new DistrictWiseFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Project";
                case 1:
                    return "Unit";
                case 2:
                    return "District";
            }
            return null;
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        if (employeeDetailss != null && employeeDetailss.getEmployeeDetail() != null && employeeDetailss.getEmployeeDetail().size() > 1) {
            menu.findItem(R.id.action_view).setVisible(true);
        } else {
            menu.findItem(R.id.action_view).setVisible(false);
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onPrepareOptionsMenu(menu);
        inflater.inflate(R.menu.search_menu, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_view:
                if (employeeDetailss != null && employeeDetailss.getEmployeeDetail() != null
                        && employeeDetailss.getEmployeeDetail().size() > 0)
                    displayMultiSelectDialog(employeeDetailss);
                else
                    Utilities.showCustomNetworkAlert(getActivity(), getActivity().getResources().getString(R.string.something), false);
                return true;
            case R.id.action_logout:
                Utilities.showCustomNetworkAlertLogout(getActivity(), "Do you want logout from app?");
                return true;
            default:
                break;
        }

        return false;
    }


    private void displayMultiSelectDialog(final EmployeeDetailss employeeDetailss) {
        final ArrayList<String> desArrayList = new ArrayList<>();

        for (int z = 0; z < employeeDetailss.getEmployeeDetail().size(); z++) {
            if(employeeDetailss.getEmployeeDetail().get(z).getDesignation().equalsIgnoreCase("CE")){
                desArrayList.add(employeeDetailss.getEmployeeDetail().get(z).getDesignation()
                +" ("
                +employeeDetailss.getEmployeeDetail().get(z).getUnit()
                +")");
            }

            else if(employeeDetailss.getEmployeeDetail().get(z).getDesignation().equalsIgnoreCase("SE")){
                desArrayList.add(employeeDetailss.getEmployeeDetail().get(z).getDesignation()
                        +" ("
                        +employeeDetailss.getEmployeeDetail().get(z).getCircle()
                        +")");
            }

            else if(employeeDetailss.getEmployeeDetail().get(z).getDesignation().equalsIgnoreCase("EE")){
                desArrayList.add(employeeDetailss.getEmployeeDetail().get(z).getDesignation()
                        +" ("
                        +employeeDetailss.getEmployeeDetail().get(z).getDivision()
                        +")");
            }

            else if(employeeDetailss.getEmployeeDetail().get(z).getDesignation().equalsIgnoreCase("DE")
            || employeeDetailss.getEmployeeDetail().get(z).getDesignation().equalsIgnoreCase("DEE")){
                desArrayList.add(employeeDetailss.getEmployeeDetail().get(z).getDesignation()
                        +" ("
                        +employeeDetailss.getEmployeeDetail().get(z).getSubdivision()
                        +")");
            }

            else if(employeeDetailss.getEmployeeDetail().get(z).getDesignation().equalsIgnoreCase("AE")
                    || employeeDetailss.getEmployeeDetail().get(z).getDesignation().equalsIgnoreCase("AEE")){
                desArrayList.add(employeeDetailss.getEmployeeDetail().get(z).getDesignation()
                        +" ("
                        +employeeDetailss.getEmployeeDetail().get(z).getSection()
                        +")");
            }
        }

        if (desArrayList.size() > 0) {

            sharedPreferences = getActivity().getSharedPreferences("APP_PREF", MODE_PRIVATE);
            defSelection = sharedPreferences.getInt("DEFAULT_SELECTION", 0);

            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Switch Role");

            final String[] str = desArrayList.toArray(new String[desArrayList.size()]);
            pos = defSelection;

            builder.setSingleChoiceItems(
                    str,
                    defSelection,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            pos = i;
                            String selectedItem = Arrays.asList(str).get(pos);
                            Snackbar.make(
                                    getView(),
                                    "Selected Role : " + selectedItem,
                                    Snackbar.LENGTH_LONG
                            ).show();
                        }
                    });

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (defSelection != pos && defSelection >= 0) {

                        defSelection = pos;

//                        String selectedItem = Arrays.asList(str).get(pos);
//                        for (int z = 0; z < employeeDetailss.getEmployeeDetail().size(); z++) {
//                            if (selectedItem.equalsIgnoreCase(employeeDetailss.getEmployeeDetail().get(z).getDesignation())) {
//                                defSelection = z;
//                                break;
//                            }
//                        }

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("DEFAULT_SELECTION", defSelection);
                        editor.putString("REPORT_DATA", "");
                        editor.commit();

                        startActivity(new Intent(getActivity(), DashboardActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    }
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            ((DashboardActivity) getActivity()).getSupportActionBar()
                    .setTitle(employeeDetailss.getEmployeeDetail().get(defSelection).getEmpName());
            ((DashboardActivity) getActivity()).getSupportActionBar()
                    .setSubtitle(employeeDetailss.getEmployeeDetail().get(defSelection).getDesignation());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
