package cgg.gov.in.icadworks.fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.adapter.CheckDamAdapter;
import cgg.gov.in.icadworks.adapter.DashboardAdapter;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.interfaces.OTView;
import cgg.gov.in.icadworks.model.response.checkdam.CheckDamData;
import cgg.gov.in.icadworks.model.response.checkdam.CheckDamResponse;
import cgg.gov.in.icadworks.model.response.login.EmployeeDetailss;
import cgg.gov.in.icadworks.model.response.ot.OTData;
import cgg.gov.in.icadworks.model.response.ot.OTResponse;
import cgg.gov.in.icadworks.model.response.report.ReportResponse;
import cgg.gov.in.icadworks.presenter.OTPresenter;
import cgg.gov.in.icadworks.util.ConnectionDetector;
import cgg.gov.in.icadworks.util.Utilities;
import cgg.gov.in.icadworks.view.DashboardActivity;
import cgg.gov.in.icadworks.view.MapsActivity;

import static android.content.Context.MODE_PRIVATE;

public class CheckDamFragment extends Fragment implements OTView {

    CustomFontTextView empNameTV;
    CustomFontTextView empDesTV;
    LinearLayout headerLl;
    RecyclerView dashboardRV;
    CustomFontTextView emptyTV;
    CustomFontTextView totalCount;
    CustomFontTextView notStCount;
    CustomFontTextView inProCount;
    CustomFontTextView comCount;

    CustomFontTextView tanksCount;
    CustomFontTextView tsCount;
    CustomFontTextView tenCount;
    CustomFontTextView aggCount;


    private OTPresenter otPresenter;
    private String flagSwitch;

    private String userName, userPwd;
    SharedPreferences sharedPreferences;

    private String defUsername, defUserPwd;
    FloatingActionButton switchView;
    private CheckDamAdapter checkDamAdapter;
    private ProgressBar progressBar;
    private Menu mMenu;
    EmployeeDetailss employeeDetailss = null;
    private LinearLayout data_ll;
    private int defSelection;
    private int pos;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RelativeLayout mainRL;
    private LinearLayout liner_ll;
    private ImageView shareIV;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkdam, container, false);
        pos = 0;
        setHasOptionsMenu(true);

        switchView = view.findViewById(R.id.switchView);
        empNameTV = view.findViewById(R.id.empNameTV);
        empDesTV = view.findViewById(R.id.empDesTV);
        headerLl = view.findViewById(R.id.header_ll);
        dashboardRV = view.findViewById(R.id.dashboardRV);
        emptyTV = view.findViewById(R.id.emptyTV);
        totalCount = view.findViewById(R.id.totalCount);
        notStCount = view.findViewById(R.id.notStCount);
        inProCount = view.findViewById(R.id.inProCount);
        comCount = view.findViewById(R.id.comCount);
        liner_ll = view.findViewById(R.id.liner_ll);

        tanksCount = view.findViewById(R.id.tanksCount);
        tsCount = view.findViewById(R.id.tsCount);
        tenCount = view.findViewById(R.id.tenCount);
        aggCount = view.findViewById(R.id.aggCount);
        progressBar = view.findViewById(R.id.progress);
        mainRL = view.findViewById(R.id.mainRL);
        shareIV = view.findViewById(R.id.shareIV);

        data_ll = view.findViewById(R.id.data_ll);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.simpleSwipeRefreshLayout);


        switchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sharedPreferences = getActivity().getSharedPreferences("APP_PREF", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    ArrayList<CheckDamData> checkDamData = checkDamAdapter.getFilteredData();
                    CheckDamResponse checkDamResponse = new CheckDamResponse();
                    checkDamResponse.setData(checkDamData);
                    editor.putString("OT_DATA", gson.toJson(checkDamResponse));
                    editor.putString("FROM_CLASS", "DASH");
                    editor.commit();

                    startActivity(new Intent(getContext(), MapsActivity.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        try {
            Gson gson = new Gson();
            sharedPreferences = getActivity().getSharedPreferences("APP_PREF", MODE_PRIVATE);
            String string = sharedPreferences.getString("LOGIN_DATA", "");
            userName = sharedPreferences.getString("USERNAME", "");
            userPwd = sharedPreferences.getString("PWD", "");
            defUsername = sharedPreferences.getString("DEFAULT_USER_NAME", "");
            defUserPwd = sharedPreferences.getString("DEFAULT_USER_PWD", "");
            defSelection = sharedPreferences.getInt("DEFAULT_SELECTION", -1);
            employeeDetailss = gson.fromJson(string, EmployeeDetailss.class);
            if (defSelection >= 0 && employeeDetailss != null && employeeDetailss.getEmployeeDetail() != null) {
                empNameTV.setText(employeeDetailss.getEmployeeDetail().get(defSelection).getEmpName());
                empDesTV.setText(employeeDetailss.getEmployeeDetail().get(defSelection).getDesignation());
            } else {
                Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.something), false);
            }
        } catch (Exception e) {
            Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.something), false);
            e.printStackTrace();
        }


        otPresenter = new OTPresenter();
        otPresenter.attachView(this);
        if (ConnectionDetector.isConnectedToInternet(getActivity())) {
            if (employeeDetailss != null) {
                progressBar.setVisibility(View.VISIBLE);
                otPresenter.getCDData(employeeDetailss.getEmployeeDetail().get(defSelection).getSectionId(),
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
            Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.no_internet), false);
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchView.setQuery("", false);
                searchView.clearFocus();
                searchView.setIconified(true);
                swipeRefreshLayout.setRefreshing(false);
                clearData();
                if (ConnectionDetector.isConnectedToInternet(getActivity())) {
                    if (employeeDetailss != null) {
                        progressBar.setVisibility(View.VISIBLE);
                        otPresenter.getCDData(employeeDetailss.getEmployeeDetail().get(defSelection).getSectionId(),
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
                    Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.no_internet), false);
                }
            }
        });


        shareIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout abstractView = getActivity().getWindow().getDecorView().findViewById(R.id.data_ll);
                Utilities.takeSCImage(getActivity(), abstractView,
                        employeeDetailss.getEmployeeDetail().get(defSelection).getEmpName()
                                + "( " + employeeDetailss.getEmployeeDetail().get(defSelection).getDesignation() + " )" + "_Abstract Data");
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            ((DashboardActivity) getActivity()).getSupportActionBar()
                    .setTitle(empNameTV.getText().toString());
            ((DashboardActivity) getActivity()).getSupportActionBar()
                    .setSubtitle(empDesTV.getText().toString());

        } catch (Exception e) {
            e.printStackTrace();
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
                    if (checkDamAdapter != null) {
                        checkDamAdapter.getFilter().filter(newText);
                        ArrayList<CheckDamData> checkDamData = checkDamAdapter.getFilteredData();
                        showItemCount(checkDamData.size());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
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


    @Override
    public void getOTResponse(OTResponse otResponse) {

    }

    private void callReportData() {
        if (ConnectionDetector.isConnectedToInternet(getActivity())) {
            progressBar.setVisibility(View.VISIBLE);
            if (employeeDetailss != null) {
                otPresenter.getDashboardReport(employeeDetailss.getEmployeeDetail().get(defSelection).getSectionId(),
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
            Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.no_internet), false);
        }
    }

    @Override
    public void getReportResponse(ReportResponse reportResponse) {
        progressBar.setVisibility(View.GONE);
        try {
            if (reportResponse != null) {
                if (reportResponse.getStatusCode() == 200 && reportResponse.getData() != null && reportResponse.getData().size() > 0) {
                    int tanks = 0, tanksTobeFed = 0, tsCnt = 0, techSanOts = 0, tenders = 0, agreements = 0, nominations = 0;
                    for (int x = 0; x < reportResponse.getData().size(); x++) {
                        tanks = tanks + reportResponse.getData().get(x).getTanks();
                        tanksTobeFed = tanksTobeFed + reportResponse.getData().get(x).getTanks_to_be_fed();
                        tsCnt = tsCnt + reportResponse.getData().get(x).getTechsanctions();
                        techSanOts = techSanOts + reportResponse.getData().get(x).getTechsancots();
                        tenders = tenders + reportResponse.getData().get(x).getTenders();
                        nominations = nominations + reportResponse.getData().get(x).getNominations();
                        agreements = agreements + reportResponse.getData().get(x).getAgreements();
                    }

                    tanksCount.setText(String.valueOf(tanks) + "/" + String.valueOf(tanksTobeFed));
                    tsCount.setText(String.valueOf(tsCnt) +
                            " [ " + String.valueOf(techSanOts) + " ]");
                    tenCount.setText(String.valueOf(tenders) + " [ " + String.valueOf(nominations) + " ]");
                    aggCount.setText(String.valueOf(agreements));


                } else if (reportResponse.getStatus() != null && reportResponse.getStatus() == 404) {
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

    CheckDamResponse checkDamResponse;

    @Override
    public void getCheckDamResponse(CheckDamResponse checkDamResponse) {
        progressBar.setVisibility(View.GONE);
        try {
            if (checkDamResponse != null) {
                if (checkDamResponse.getStatusCode() != null && checkDamResponse.getStatusCode() == 200) {
                    if (checkDamResponse.getAbstractReport() != null && checkDamResponse.getAbstractReport().size() > 0) {
                        totalCount.setText(String.valueOf(checkDamResponse.getAbstractReport().get(0).getTotalCheckdams()));
//                        notStCount.setText(checkDamResponse.getAbstractReport().get(0).getNotStarted());
//                        inProCount.setText(checkDamResponse.getAbstractReport().get(0).getInProgress());
//                        comCount.setText(checkDamResponse.getAbstractReport().get(0).getCompleted());
                    }
                    this.checkDamResponse = checkDamResponse;
                    emptyTV.setVisibility(View.GONE);
                    switchView.setVisibility(View.VISIBLE);
                    shareIV.setVisibility(View.VISIBLE);
                    if (mMenu != null)
                        mMenu.findItem(R.id.action_search).setVisible(true);
                    setDataAdapter(checkDamResponse);
                    callReportData();
                } else {
                    dashboardRV.setAdapter(null);
                    emptyTV.setVisibility(View.VISIBLE);
                    emptyTV.setText(checkDamResponse.getTag());
                    data_ll.setVisibility(View.GONE);
                    if (mMenu != null)
                        mMenu.findItem(R.id.action_search).setVisible(false);
                    switchView.setVisibility(View.GONE);
                    shareIV.setVisibility(View.GONE);
                    Utilities.showCustomNetworkAlert(getActivity(), checkDamResponse.getTag(), false);
                }
            } else {
                Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.server), false);
            }
        } catch (Exception e) {
            Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.something), false);
            e.printStackTrace();
        }
    }

    private void showItemCount(int count) {
        Snackbar snackbar;
        snackbar = Snackbar.make(mainRL, "Found " + count + " Records", Snackbar.LENGTH_SHORT);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        TextView textView = snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(getResources().getColor(R.color.white_new));
        snackbar.show();

    }

    void setDataAdapter(CheckDamResponse checkDamResponse) {
        flagSwitch = "list";
        checkDamAdapter = new CheckDamAdapter(checkDamResponse.getData(), getActivity());
        dashboardRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        dashboardRV.setAdapter(checkDamAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private SearchView searchView = null;


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onPrepareOptionsMenu(menu);
        inflater.inflate(R.menu.search_menu, menu);
        mMenu = menu;

        MenuItem menuItem = mMenu.getItem(1);
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
            desArrayList.add(employeeDetailss.getEmployeeDetail().get(z).getDesignation());
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
                        editor.commit();

                        empDesTV.setText(employeeDetailss.getEmployeeDetail().get(defSelection).getDesignation());
                        ((DashboardActivity) getActivity()).getSupportActionBar()
                                .setSubtitle(empDesTV.getText().toString());
                        if (ConnectionDetector.isConnectedToInternet(getActivity())) {

                            clearData();


                            progressBar.setVisibility(View.VISIBLE);
                            otPresenter.getOTData(employeeDetailss.getEmployeeDetail().get(defSelection).getSectionId(),
                                    employeeDetailss.getEmployeeDetail().get(defSelection).getSubdivisionId(),
                                    employeeDetailss.getEmployeeDetail().get(defSelection).getDivisionId(),
                                    employeeDetailss.getEmployeeDetail().get(defSelection).getCircleId(),
                                    employeeDetailss.getEmployeeDetail().get(defSelection).getUnitId(),
                                    "1",
                                    defUsername,
                                    defUserPwd);
                        } else {
                            Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.no_internet), false);
                        }
                    }
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    private void clearData() {
        dashboardRV.setAdapter(null);
        totalCount.setText("");
        notStCount.setText("");
        inProCount.setText("");
        comCount.setText("");


        tanksCount.setText("");
        tsCount.setText("");
        tenCount.setText("");
        aggCount.setText("");

        switchView.setVisibility(View.GONE);
    }

}
