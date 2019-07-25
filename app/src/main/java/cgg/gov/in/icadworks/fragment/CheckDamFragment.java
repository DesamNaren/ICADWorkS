package cgg.gov.in.icadworks.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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
import android.text.TextUtils;
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
import java.util.List;

import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.adapter.CheckDamAdapter;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.interfaces.OTView;
import cgg.gov.in.icadworks.model.response.checkdam.CheckDamData;
import cgg.gov.in.icadworks.model.response.checkdam.CheckDamResponse;
import cgg.gov.in.icadworks.model.response.checkdam.office.CDOfficeResponse;
import cgg.gov.in.icadworks.model.response.login.EmployeeDetailss;
import cgg.gov.in.icadworks.model.response.ot.OTResponse;
import cgg.gov.in.icadworks.model.response.report.ReportResponse;
import cgg.gov.in.icadworks.presenter.OTPresenter;
import cgg.gov.in.icadworks.util.ConnectionDetector;
import cgg.gov.in.icadworks.util.Utilities;
import cgg.gov.in.icadworks.view.CDMapsActivity;
import cgg.gov.in.icadworks.view.DashboardActivity;

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
    private LinearLayout notStartedL, inProL, comL, totalLayout;

    CustomFontTextView cdsCount;
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

        cdsCount = view.findViewById(R.id.cdsCount);
        tsCount = view.findViewById(R.id.tsCount);
        tenCount = view.findViewById(R.id.tenCount);
        aggCount = view.findViewById(R.id.aggCount);
        progressBar = view.findViewById(R.id.progress);
        mainRL = view.findViewById(R.id.mainRL);
        shareIV = view.findViewById(R.id.shareIV);

        data_ll = view.findViewById(R.id.data_ll);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.simpleSwipeRefreshLayout);


        notStartedL = view.findViewById(R.id.notStartedLayout);
        inProL = view.findViewById(R.id.inProLayout);
        comL = view.findViewById(R.id.comLayout);
        totalLayout = view.findViewById(R.id.totalLayout);

        switchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sharedPreferences = getActivity().getSharedPreferences("APP_PREF", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    List<CheckDamData> checkDamData = checkDamAdapter.getFilteredData();
                    CheckDamResponse checkDamResponse = new CheckDamResponse();
                    checkDamResponse.setData(checkDamData);
                    editor.putString("CD_DATA", gson.toJson(checkDamResponse));
                    editor.putString("FROM_CLASS", "DASH");
                    editor.commit();

                    startActivity(new Intent(getContext(), CDMapsActivity.class));
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


        notStartedL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    notStartedL.setBackgroundColor(getResources().getColor(R.color.color_gray));
                    totalLayout.setBackgroundColor(Color.TRANSPARENT);
                    inProL.setBackgroundColor(Color.TRANSPARENT);
                    comL.setBackgroundColor(Color.TRANSPARENT);

                    if(!TextUtils.isEmpty(notStCount.getText().toString()) && Integer.valueOf(notStCount.getText().toString())>0){
                        clearRV();

                        getFilterData();
                    }else {
                        emptyTV.setVisibility(View.VISIBLE);
                        switchView.setVisibility(View.GONE);
                        checkDamAdapter.setFilteredData(null);
                        dashboardRV.setAdapter(null);
                    }
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        inProL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    inProL.setBackgroundColor(getResources().getColor(R.color.color_gray));
                    notStartedL.setBackgroundColor(Color.TRANSPARENT);
                    comL.setBackgroundColor(Color.TRANSPARENT);
                    totalLayout.setBackgroundColor(Color.TRANSPARENT);
                    if(!TextUtils.isEmpty(inProCount.getText().toString()) && Integer.valueOf(inProCount.getText().toString())>0){
                        clearRV();

                        getFilterDataInPro();
                    }else {
                        emptyTV.setVisibility(View.VISIBLE);
                        switchView.setVisibility(View.GONE);
                        checkDamAdapter.setFilteredData(null);
                        dashboardRV.setAdapter(null);
                    }
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        comL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    comL.setBackgroundColor(getResources().getColor(R.color.color_gray));
                    notStartedL.setBackgroundColor(Color.TRANSPARENT);
                    inProL.setBackgroundColor(Color.TRANSPARENT);
                    totalLayout.setBackgroundColor(Color.TRANSPARENT);
                    if(!TextUtils.isEmpty(comCount.getText().toString()) && Integer.valueOf(comCount.getText().toString())>0){
                        clearRV();
                        getFilterDataCom();
                    }else {
                        emptyTV.setVisibility(View.VISIBLE);
                        switchView.setVisibility(View.GONE);
                        checkDamAdapter.setFilteredData(null);
                        dashboardRV.setAdapter(null);
                    }
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        totalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    totalLayout.setBackgroundColor(getResources().getColor(R.color.color_gray));
                    notStartedL.setBackgroundColor(Color.TRANSPARENT);
                    inProL.setBackgroundColor(Color.TRANSPARENT);
                    comL.setBackgroundColor(Color.TRANSPARENT);
                    if(!TextUtils.isEmpty(totalCount.getText().toString()) && Integer.valueOf(totalCount.getText().toString())>0){
                        clearRV();
                        setDataAdapter(checkDamResponse);
                    }else {
                        emptyTV.setVisibility(View.VISIBLE);
                        dashboardRV.setAdapter(null);
                        switchView.setVisibility(View.GONE);
                    }
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    private void clearRV() {
        emptyTV.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        dashboardRV.setAdapter(null);
        dashboardRV.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                dashboardRV.setVisibility(View.VISIBLE);
                switchView.setVisibility(View.VISIBLE);

            }

        }, 2000);
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
                        List<CheckDamData> checkDamData = checkDamAdapter.getFilteredData();
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
                otPresenter.getCDOfficeData(employeeDetailss.getEmployeeDetail().get(defSelection).getSectionId(),
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

    }

    CheckDamResponse checkDamResponse;

    @Override
    public void getCheckDamResponse(CheckDamResponse checkDamResponse) {
        progressBar.setVisibility(View.GONE);
        try {
            if (checkDamResponse != null) {
                if (checkDamResponse.getStatusCode() != null && checkDamResponse.getStatusCode() == 200) {
                    if (checkDamResponse.getAbstractReport() != null && checkDamResponse.getAbstractReport().size() > 0) {
                        totalCount.setText(String.valueOf(checkDamResponse.getAbstractReport().get(0).getTotal()));
                        notStCount.setText(String.valueOf(checkDamResponse.getAbstractReport().get(0).getNotStarted()));
                        inProCount.setText(String.valueOf(checkDamResponse.getAbstractReport().get(0).getInProgress()));
                        comCount.setText(String.valueOf(checkDamResponse.getAbstractReport().get(0).getCompleted()));
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

    @Override
    public void getCheckDamOfficeResponse(CDOfficeResponse cdOfficeResponse) {
        progressBar.setVisibility(View.GONE);
        try {
            if (cdOfficeResponse != null) {
                if (cdOfficeResponse.getStatusCode() == 200 && cdOfficeResponse.getCdOfficeData() != null && cdOfficeResponse.getCdOfficeData().size() > 0) {
                    cdsCount.setText(String.valueOf(cdOfficeResponse.getAbstractReport().get(0).getCheckDams()));
                    tsCount.setText(String.valueOf(cdOfficeResponse.getAbstractReport().get(0).getTechSanctions()));
                    tenCount.setText(String.valueOf(cdOfficeResponse.getAbstractReport().get(0).getTendersPublish()));
                    aggCount.setText(String.valueOf(cdOfficeResponse.getAbstractReport().get(0).getAgreements()));
                } else if (cdOfficeResponse.getStatus() != null && cdOfficeResponse.getStatus() == 404) {
                    Utilities.showCustomNetworkAlert(getActivity(), cdOfficeResponse.getTag(), false);
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
        inflater.inflate(R.menu.cd_search_menu, menu);
        mMenu = menu;

        mMenu.findItem(R.id.action_view).setVisible(false);
        mMenu.findItem(R.id.action_logout).setVisible(false);
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


        cdsCount.setText("");
        tsCount.setText("");
        tenCount.setText("");
        aggCount.setText("");

        switchView.setVisibility(View.GONE);
    }


    private void getFilterData() {
        ArrayList<CheckDamData> checkDamData = new ArrayList<>(checkDamResponse.getData());
        ArrayList<CheckDamData> tempData = new ArrayList<>();


        if(checkDamData.size()>0){
            dashboardRV.setAdapter(null);
            for(int z=0;z<checkDamData.size();z++){
                int statusId=0;
                for(int y=0;y<checkDamData.get(z).getGetItemStatusData().size();y++){
                    statusId += Integer.valueOf(checkDamData.get(z).getGetItemStatusData().get(y).getStatusId());
                }

                if(statusId>0 && statusId==3){
                    tempData.add(checkDamData.get(z));
                }
            }
        }
        if(tempData.size()>0){
            setDataAdapterOT(tempData);
        }

    }

    private void getFilterDataInPro() {
        ArrayList<CheckDamData> checkDamData = new ArrayList<>(checkDamResponse.getData());
        ArrayList<CheckDamData> tempData = new ArrayList<>();
        if(checkDamData.size()>0){
            dashboardRV.setAdapter(null);
            for(int z=0;z<checkDamData.size();z++){
                int statusId=0;
                for(int y=0;y<checkDamData.get(z).getGetItemStatusData().size();y++){
                    statusId += Integer.valueOf(checkDamData.get(z).getGetItemStatusData().get(y).getStatusId());
                }

                if(statusId>3 && statusId<=8){
                    tempData.add(checkDamData.get(z));
                }
            }
        }
        if(tempData.size()>0) {
            setDataAdapterOT(tempData);
        }
    }

    private void getFilterDataCom() {
        ArrayList<CheckDamData> checkDamData = new ArrayList<>(checkDamResponse.getData());
        ArrayList<CheckDamData> tempData = new ArrayList<>();
        if(checkDamData.size()>0){
            dashboardRV.setAdapter(null);
            for(int z=0;z<checkDamData.size();z++){
                int statusId=0;
                for(int y=0;y<checkDamData.get(z).getGetItemStatusData().size();y++){
                    statusId += Integer.valueOf(checkDamData.get(z).getGetItemStatusData().get(y).getStatusId());
                }

                if(statusId==9){
                    tempData.add(checkDamData.get(z));
                }
            }
        }
        if(tempData.size()>0){
            setDataAdapterOT(tempData);
        }
    }

    void setDataAdapterOT(ArrayList<CheckDamData> checkDamData) {
        searchView.setQuery("", false);
        searchView.clearFocus();
        searchView.setIconified(true);
        flagSwitch = "list";
        checkDamAdapter = new CheckDamAdapter(checkDamData, getActivity());
        dashboardRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        dashboardRV.setAdapter(checkDamAdapter);
        checkDamAdapter.setFilteredData(checkDamData);
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            try {
//                getFragmentManager().beginTransaction().detach(this).attach(this).commit();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

}
