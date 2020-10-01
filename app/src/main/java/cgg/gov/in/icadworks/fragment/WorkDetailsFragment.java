package cgg.gov.in.icadworks.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.adapter.WorkDetailsAdapter;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.interfaces.WorkDetailsView;
import cgg.gov.in.icadworks.model.response.login.EmployeeDetailss;
import cgg.gov.in.icadworks.model.response.works.WorkDetailsData;
import cgg.gov.in.icadworks.model.response.works.WorkDetailsResponse;
import cgg.gov.in.icadworks.presenter.WorkDetailsPresenter;
import cgg.gov.in.icadworks.util.ConnectionDetector;
import cgg.gov.in.icadworks.util.Utilities;

import static android.content.Context.MODE_PRIVATE;

public class WorkDetailsFragment extends Fragment implements WorkDetailsView {

    private RecyclerView workRv;
    private CustomFontTextView emptyTV;
    private ProgressBar progressBar;
    private Menu mMenu;
    EmployeeDetailss employeeDetailss = null;
    private int defSelection;
    private String defUsername, defUserPwd;
    private int pos;
    private WorkDetailsAdapter workDetailsAdapter;
    private RelativeLayout mainRL;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.work_details_fragment, container, false);
        setHasOptionsMenu(true);

        workRv = view.findViewById(R.id.workRv);
        emptyTV = view.findViewById(R.id.emptyTV);
        progressBar = view.findViewById(R.id.progress);
        mainRL = view.findViewById(R.id.mainRL);

        try {
            Gson gson = new Gson();
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("APP_PREF", MODE_PRIVATE);
            String string = sharedPreferences.getString("LOGIN_DATA", "");
            String userName = sharedPreferences.getString("USERNAME", "");
            String userPwd = sharedPreferences.getString("PWD", "");
            defUsername = sharedPreferences.getString("DEFAULT_USER_NAME", "");
            defUserPwd = sharedPreferences.getString("DEFAULT_USER_PWD", "");
            defSelection = sharedPreferences.getInt("DEFAULT_SELECTION", -1);
            employeeDetailss = gson.fromJson(string, EmployeeDetailss.class);
        } catch (Exception e) {
            Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.something), false);
            e.printStackTrace();
        }


        WorkDetailsPresenter workDetailsPresenter = new WorkDetailsPresenter();
        workDetailsPresenter.attachView(this);
        if (ConnectionDetector.isConnectedToInternet(getActivity())) {
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
                Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.something), false);
            }
        } else {
            Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.no_internet), false);
        }

        return view;
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
                        List<WorkDetailsData> checkDamData = workDetailsAdapter.getFilteredData();
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
                    Utilities.showCustomNetworkAlert(getActivity(), "No records found", false);
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
    public void showProgressIndicator(Boolean show) {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    void setDataAdapter(WorkDetailsResponse workDetailsResponse) {
        workDetailsAdapter = new WorkDetailsAdapter(workDetailsResponse.getData(), getActivity());
        workRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        workRv.setAdapter(workDetailsAdapter);
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
        menu.findItem(R.id.action_view).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            Utilities.showCustomNetworkAlertLogout(getActivity(), "Do you want logout from app?");
            return true;
        }

        return false;
    }

}
