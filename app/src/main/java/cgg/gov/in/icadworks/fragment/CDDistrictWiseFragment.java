package cgg.gov.in.icadworks.fragment;

import android.content.SharedPreferences;
import android.content.res.Resources;
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
import cgg.gov.in.icadworks.adapter.CDCEReportAdapter;
import cgg.gov.in.icadworks.adapter.CDDistrictReportAdapter;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.model.response.checkdam.office.CDOfficeData;
import cgg.gov.in.icadworks.model.response.checkdam.office.CDOfficeResponse;
import cgg.gov.in.icadworks.model.response.login.EmployeeDetailss;
import cgg.gov.in.icadworks.util.Utilities;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by lenovo on 03-06-2019.
 */

public class CDDistrictWiseFragment extends Fragment {

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
    CDOfficeResponse cdOfficeResponse;
    private int defSelection;
    private EmployeeDetailss employeeDetailss = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cd_abstract_fragment, container, false);
        setHasOptionsMenu(true);
        unbinder = ButterKnife.bind(this, view);


        try {
            Gson gson = new Gson();
            sharedPreferences = getActivity().getSharedPreferences("APP_PREF", MODE_PRIVATE);
            String string = sharedPreferences.getString("LOGIN_DATA", "");
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
            String string = sharedPreferences.getString("CD_REPORT_DATA", "");
            cdOfficeResponse = gson.fromJson(string, CDOfficeResponse.class);

            setCEData(cdOfficeResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }


        shareIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout abstractView = getActivity().getWindow().getDecorView().findViewById(R.id.data_ll);
                Utilities.takeSCImage(getActivity(), abstractView,
                        employeeDetailss.getEmployeeDetail().get(defSelection).getEmpName()
                                + "( " + employeeDetailss.getEmployeeDetail().get(defSelection).getDesignation() + " )" + "_Unit Data");
            }
        });


        return view;
    }


    private void setCEData(CDOfficeResponse cdOfficeResponse) {
        try {
            if (cdOfficeResponse != null) {
                if (cdOfficeResponse.getStatusCode() == 200 && cdOfficeResponse.getCdOfficeData() != null && cdOfficeResponse.getCdOfficeData().size() > 0) {

                    long cds = 0, tsCnt = 0, tenders = 0, agreements = 0;
                    long notSta = 0, inPro = 0, completed = 0, total = 0;

                    for (int x = 0; x < cdOfficeResponse.getCdOfficeData().size(); x++) {
                        cds = cds + cdOfficeResponse.getCdOfficeData().get(x).getCheckDams();
                        tsCnt = tsCnt + cdOfficeResponse.getCdOfficeData().get(x).getTechSanctions();
                        tenders = tenders + cdOfficeResponse.getCdOfficeData().get(x).getTendersAward();
                        agreements = agreements + cdOfficeResponse.getCdOfficeData().get(x).getAgreements();

                        notSta = notSta + cdOfficeResponse.getCdOfficeData().get(x).getNot_started();
                        inPro = inPro + cdOfficeResponse.getCdOfficeData().get(x).getIn_progress();
                        completed = completed + cdOfficeResponse.getCdOfficeData().get(x).getCompleted();
                        total = total + cdOfficeResponse.getCdOfficeData().get(x).getTotal_cds();

                    }

                    totalCount.setText(String.valueOf(total));
                    notStCount.setText(String.valueOf(notSta));
                    inProCount.setText(String.valueOf(inPro));
                    comCount.setText(String.valueOf(completed));

                    tanksCount.setText(String.valueOf(cds));
                    tsCount.setText(String.valueOf(tsCnt));
                    tenCount.setText(String.valueOf(tenders));
                    aggCount.setText(String.valueOf(agreements));


                    prepareAdapter(cdOfficeResponse);


                } else if (cdOfficeResponse.getStatus() != null && cdOfficeResponse.getStatus() == 404) {
                    Utilities.showCustomNetworkAlert(getActivity(), cdOfficeResponse.getTag(), false);
                } else {
                    Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.server), false);
                }
            } else {
                Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.server), false);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    CDDistrictReportAdapter cdDistrictReportAdapter;

    private void prepareAdapter(CDOfficeResponse cdOfficeResponse) {

        try {
            Set<Integer> hashSet = new HashSet<>();
            for (int x = 0; x < cdOfficeResponse.getCdOfficeData().size(); x++) {
                hashSet.add(Integer.valueOf(cdOfficeResponse.getCdOfficeData().get(x).getDcode()));
            }

            if (cdOfficeResponse.getCdOfficeData().size() > 0) {
                ArrayList<CDOfficeData> cdOfficeDataArrayList = new ArrayList<>();
                Iterator<Integer> iterator = hashSet.iterator();

                while (iterator.hasNext()) {
                    long cds = 0, tsCnt = 0, tenders = 0, agreements = 0;
                    long notSta = 0, inPro = 0, completed = 0, total = 0;
                    int dCode = iterator.next();
                    CDOfficeData cdOfficeData = new CDOfficeData();
                    for (int z = 0; z < cdOfficeResponse.getCdOfficeData().size(); z++) {

                        if (dCode == Integer.valueOf(cdOfficeResponse.getCdOfficeData().get(z).getDcode())) {

                            cds = cds + cdOfficeResponse.getCdOfficeData().get(z).getCheckDams();
                            tsCnt = tsCnt + cdOfficeResponse.getCdOfficeData().get(z).getTechSanctions();
                            tenders = tenders + cdOfficeResponse.getCdOfficeData().get(z).getTendersAward();
                            agreements = agreements + cdOfficeResponse.getCdOfficeData().get(z).getAgreements();

                            notSta = notSta + cdOfficeResponse.getCdOfficeData().get(z).getNot_started();
                            inPro = inPro + cdOfficeResponse.getCdOfficeData().get(z).getIn_progress();
                            completed = completed + cdOfficeResponse.getCdOfficeData().get(z).getCompleted();
                            total = total + cdOfficeResponse.getCdOfficeData().get(z).getTotal_cds();

//                            cdOfficeData.Dcode(Integer.valueOf(cdOfficeResponse.getCdOfficeData().get(z).getDcode()));
//                            cdOfficeData.setDname(checkDamOfficeResponse.getData().get(z).getDname());
//                            cdOfficeData.setProjectId(checkDamOfficeResponse.getData().get(z).getProjectId());
//                            cdOfficeData.setProjectName(checkDamOfficeResponse.getData().get(z).getProjectName());


                            cdOfficeData.setDcode(cdOfficeResponse.getCdOfficeData().get(z).getDcode());
                            cdOfficeData.setDname(cdOfficeResponse.getCdOfficeData().get(z).getDname());
                        }

                        cdOfficeData.setCheckDams(cds);
                        cdOfficeData.setTechSanctions(tsCnt);
                        cdOfficeData.setTendersAward(tenders);
                        cdOfficeData.setAgreements(agreements);
                        cdOfficeData.setNot_started(notSta);
                        cdOfficeData.setIn_progress(inPro);
                        cdOfficeData.setCompleted(completed);
                        cdOfficeData.setTotal_cds(total);
                    }
                    cdOfficeDataArrayList.add(cdOfficeData);
                }

                if (cdOfficeDataArrayList.size() > 0) {
                    sortData(cdOfficeDataArrayList);
                    cdDistrictReportAdapter = new CDDistrictReportAdapter(cdOfficeResponse, cdOfficeDataArrayList, getActivity(), getActivity());
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    projectRV.setLayoutManager(mLayoutManager);
                    projectRV.setAdapter(cdDistrictReportAdapter);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


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
                    if (cdDistrictReportAdapter != null) {
                        cdDistrictReportAdapter.getFilter().filter(newText);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
    }

//    @Override
////    public void setUserVisibleHint(boolean isVisibleToUser) {
////        super.setUserVisibleHint(isVisibleToUser);
////        if (isVisibleToUser) {
////            try {
////                getFragmentManager().beginTransaction().detach(this).attach(this).commit();
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////        }
////    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void sortData(ArrayList<CDOfficeData> cdOfficeDataArrayList) {
        Collections.sort(cdOfficeDataArrayList, new Comparator<CDOfficeData>() {
            public int compare(CDOfficeData lhs, CDOfficeData rhs) {
                return (lhs.getDname().compareTo(rhs.getDname()));
            }
        });
    }


}
