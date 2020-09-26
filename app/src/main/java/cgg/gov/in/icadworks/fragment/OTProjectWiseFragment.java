package cgg.gov.in.icadworks.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.CardView;
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

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.adapter.OTProjectReportAdapter;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.model.ProjectReportData;
import cgg.gov.in.icadworks.model.response.login.EmployeeDetailss;
import cgg.gov.in.icadworks.model.response.report.ReportResponse;
import cgg.gov.in.icadworks.util.Utilities;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by lenovo on 03-06-2019.
 */

public class OTProjectWiseFragment extends Fragment {

    @BindView(R.id.projectRV)
    RecyclerView projectRV;
    //    @BindView(R.id.simpleSwipeRefreshLayout)
//    SwipeRefreshLayout simpleSwipeRefreshLayout;
    @BindView(R.id.emptyTV)
    CustomFontTextView emptyTV;
    @BindView(R.id.switchView)
    FloatingActionButton switchView;
    @BindView(R.id.progress)
    ProgressBar progress;
    Unbinder unbinder;
    @BindView(R.id.totalCount)
    CustomFontTextView totalCount;
    @BindView(R.id.notStCount)
    CustomFontTextView notStCount;
    @BindView(R.id.inProCount)
    CustomFontTextView inProCount;
    @BindView(R.id.comCount)
    CustomFontTextView comCount;
    @BindView(R.id.absrtract_ll)
    CardView absrtractLl;
    @BindView(R.id.tanksCount)
    CustomFontTextView tanksCount;
    @BindView(R.id.tsCount)
    CustomFontTextView tsCount;
    @BindView(R.id.tenCount)
    CustomFontTextView tenCount;
    @BindView(R.id.aggCount)
    CustomFontTextView aggCount;
    @BindView(R.id.absrtract_ll2)
    CardView absrtractLl2;
    @BindView(R.id.data_ll)
    LinearLayout dataLl;
    @BindView(R.id.rlView)
    RelativeLayout rlView;
    @BindView(R.id.shareIV)
    ImageView shareIV;

    SharedPreferences sharedPreferences;
    private String defUsername, defUserPwd;
    private int defSelection;
    private EmployeeDetailss employeeDetailss = null;
    private ReportResponse reportResponse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ot_abstract_fragment, container, false);
        setHasOptionsMenu(true);
        unbinder = ButterKnife.bind(this, view);

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

        try {
            Gson gson = new Gson();
            sharedPreferences = getActivity().getSharedPreferences("APP_PREF", MODE_PRIVATE);
            String string = sharedPreferences.getString("REPORT_DATA", "");
            reportResponse = gson.fromJson(string, ReportResponse.class);
            setProjectData(reportResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }


        shareIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout abstractView = getActivity().getWindow().getDecorView().findViewById(R.id.data_ll);
                Utilities.takeSCImage(getActivity(), abstractView ,
                        employeeDetailss.getEmployeeDetail().get(defSelection).getEmpName()
                                + "( " + employeeDetailss.getEmployeeDetail().get(defSelection).getDesignation() + " )" + "_Project Data");
            }
        });

        return view;
    }

    private void setProjectData(ReportResponse reportResponse) {
        try {
            long tanks = 0,tanksTobeFed = 0, tsCnt = 0, techSanOts = 0, tenders = 0, nominations = 0, agreements = 0;
            long notSta = 0, inPro = 0, completed = 0, total = 0;

            for (int x = 0; x < reportResponse.getData().size(); x++) {
                tanks = tanks + reportResponse.getData().get(x).getTanks();
                tanksTobeFed = tanksTobeFed + reportResponse.getData().get(x).getTanks_to_be_fed();
                tsCnt = tsCnt + reportResponse.getData().get(x).getTechsanctions();
                techSanOts = techSanOts + reportResponse.getData().get(x).getTechsancots();
                tenders = tenders + reportResponse.getData().get(x).getTenders();
                nominations = nominations + reportResponse.getData().get(x).getNominations();
                agreements = agreements + reportResponse.getData().get(x).getAgreements();


                notSta = notSta + reportResponse.getData().get(x).getNotStarted();
                inPro = inPro + reportResponse.getData().get(x).getInProgress();
                completed = completed + reportResponse.getData().get(x).getCompleted();

                total = total + reportResponse.getData().get(x).getOts();
            }

            totalCount.setText(String.valueOf(total));
            notStCount.setText(String.valueOf(notSta));
            inProCount.setText(String.valueOf(inPro));
            comCount.setText(String.valueOf(completed));

            tanksCount.setText(String.valueOf(tanks)+"/"+String.valueOf(tanksTobeFed));
            tsCount.setText(String.valueOf(tsCnt) +
                    " [ " + String.valueOf(techSanOts) + " ]");
            tenCount.setText(String.valueOf(tenders) +" [ " + String.valueOf(nominations) + " ]");
            aggCount.setText(String.valueOf(agreements));


            prepareAdapter(reportResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    OTProjectReportAdapter OTProjectReportAdapter;

    private void prepareAdapter(ReportResponse reportResponse) {

        try {
            Set<Long> hashSet = new HashSet<>();
            for (int x = 0; x < reportResponse.getData().size(); x++) {
                hashSet.add(reportResponse.getData().get(x).getProjectId());
            }

            if (reportResponse.getData().size() > 0) {
                ArrayList<ProjectReportData> projectReportData = new ArrayList<>();
                ProjectReportData reportData = null;
                Iterator<Long> iterator = hashSet.iterator();

                while (iterator.hasNext()) {
                    long tanks = 0, tanksTobeFed=0, tsCnt = 0, techSanOts = 0, tenders = 0, nominations =0, agreements = 0;
                    long notSta = 0, inPro = 0, completed = 0, total = 0;
                    long projectId = iterator.next();
                    reportData = new ProjectReportData();

                    for (int z = 0; z < reportResponse.getData().size(); z++) {

                        if (projectId == reportResponse.getData().get(z).getProjectId()) {

                            tanks = tanks + reportResponse.getData().get(z).getTanks();
                            tanksTobeFed = tanksTobeFed + reportResponse.getData().get(z).getTanks_to_be_fed();
                            tsCnt = tsCnt + reportResponse.getData().get(z).getTechsanctions();
                            techSanOts = techSanOts + reportResponse.getData().get(z).getTechsancots();
                            tenders = tenders + reportResponse.getData().get(z).getTenders();
                            nominations = nominations + reportResponse.getData().get(z).getNominations();
                            agreements = agreements + reportResponse.getData().get(z).getAgreements();

                            notSta = notSta + reportResponse.getData().get(z).getNotStarted();
                            inPro = inPro + reportResponse.getData().get(z).getInProgress();
                            completed = completed + reportResponse.getData().get(z).getCompleted();

                            total = total + reportResponse.getData().get(z).getOts();
                            reportData.setProjectId(reportResponse.getData().get(z).getProjectId());
                            reportData.setProjectName(reportResponse.getData().get(z).getProjectName());

                        }


                        reportData.setTanks(tanks);
                        reportData.setTanksTobeFed(tanksTobeFed);
                        reportData.setTechsanctions(tsCnt);
                        reportData.setTechsancots(techSanOts);
                        reportData.setTenders(tenders);
                        reportData.setAgreements(agreements);
                        reportData.setNotStarted(notSta);
                        reportData.setInProgress(inPro);
                        reportData.setCompleted(completed);
                        reportData.setTotal(total);
                        reportData.setNominations(nominations);

                    }
                    projectReportData.add(reportData);
                }

                if (projectReportData.size() > 0) {

                    sortData(projectReportData);

                    OTProjectReportAdapter = new OTProjectReportAdapter(projectReportData, getActivity(), getActivity());
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    projectRV.setLayoutManager(mLayoutManager);
                    projectRV.setAdapter(OTProjectReportAdapter);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sortData(ArrayList<ProjectReportData> projectReportData) {
        Collections.sort(projectReportData, new Comparator<ProjectReportData>() {
            public int compare(ProjectReportData lhs, ProjectReportData rhs) {
                return (lhs.getProjectName().compareTo(rhs.getProjectName()));
            }
        });
    }

    private Menu mMenu;
    private SearchView searchView = null;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onPrepareOptionsMenu(menu);
        inflater.inflate(R.menu.search_menu, menu);
        mMenu = menu;

        mMenu.findItem(R.id.action_view).setVisible(false);
        mMenu.findItem(R.id.action_logout).setVisible(false);
        mMenu.findItem(R.id.action_search).setVisible(true);

        MenuItem menuItem = mMenu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setQueryHint("Search by project");
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

    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    if (OTProjectReportAdapter != null) {
                        OTProjectReportAdapter.getFilter().filter(newText);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
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
