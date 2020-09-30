package cgg.gov.in.icadworks.view;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.adapter.OTUnitPieReportAdapter;
import cgg.gov.in.icadworks.adapter.OTUnitProjectAdapter;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.fragment.FoundationPieFragment;
import cgg.gov.in.icadworks.fragment.ShutterPieFragment;
import cgg.gov.in.icadworks.fragment.SuperStrPieFragment;
import cgg.gov.in.icadworks.model.ProjectReportData;
import cgg.gov.in.icadworks.model.response.report.ReportData;
import cgg.gov.in.icadworks.model.response.report.ReportResponse;
import cgg.gov.in.icadworks.util.Utilities;

public class OTUnitProjectPieActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    PieChart pieChart;
    PieDataSet dataSet;
    ArrayList<PieEntry> pieEntries;
    private List<LegendEntry> legendEntries;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private RecyclerView unitsRecyclerView;
    private ReportResponse reportResponse;
    ArrayList<ReportData> subReportData;
    private Long unitID;
    private String unitName;
    private CustomFontTextView textViewTile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ot_unit_project_pie_activity);

        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle("Unit Abstract Report");
                ColorDrawable colorDrawable = new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimaryDark));
                getSupportActionBar().setBackgroundDrawable(colorDrawable);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pieChart = findViewById(R.id.pieChart);
        unitsRecyclerView = findViewById(R.id.otsRV);
        textViewTile = findViewById(R.id.title);

        sharedPreferences = getSharedPreferences("APP_PREF", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String string = sharedPreferences.getString("REPORT_DATA", "");
        reportResponse = new Gson().fromJson(string, ReportResponse.class);

        try {
            unitID = getIntent().getLongExtra("UNIT_ID", -1);
            unitName = getIntent().getStringExtra("UNIT_NAME");
            textViewTile.setText(unitName);
            if (reportResponse != null && reportResponse.getData().size() > 0 && unitID != -1) {
                setCEProjectData(reportResponse);
            } else {
                Toast.makeText(this, getString(R.string.something), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void setCEProjectData(ReportResponse reportResponse) {
        try {
            if (reportResponse.getData().size() > 0) {

                ArrayList<ReportData> projectReportData = new ArrayList<>();
                subReportData = new ArrayList<>();
                ReportData reportData = null;

                for (int z = 0; z < reportResponse.getData().size(); z++) {
                    if (unitID.equals(reportResponse.getData().get(z).getUnitId())) {
                        reportData = reportResponse.getData().get(z);
                        projectReportData.add(reportData);
                    }
                }


                if (projectReportData.size() > 0) {

                    Set<Long> hashSet = new HashSet<>();
                    for (int x = 0; x < projectReportData.size(); x++) {
                        hashSet.add(projectReportData.get(x).getProjectId());
                    }

                    Iterator<Long> iterator = hashSet.iterator();

                    while (iterator.hasNext()) {
                        long tanks = 0, tanksTobeFed = 0, tsCnt = 0, techSanOts = 0, tenders = 0, agreements = 0, nomination = 0;
                        long notSta = 0, inPro = 0, completed = 0, total = 0;
                        long projectId = iterator.next();
                        reportData = new ReportData();


                        for (int z = 0; z < projectReportData.size(); z++) {
                            if (projectId == projectReportData.get(z).getProjectId()) {
                                tanks = tanks + projectReportData.get(z).getTanks();
                                tanksTobeFed = tanksTobeFed + projectReportData.get(z).getTanks_to_be_fed();
                                tsCnt = tsCnt + projectReportData.get(z).getTechsanctions();
                                techSanOts = techSanOts + projectReportData.get(z).getTechsancots();
                                tenders = tenders + projectReportData.get(z).getTenders();
                                nomination = nomination + projectReportData.get(z).getNominations();
                                agreements = agreements + projectReportData.get(z).getAgreements();

                                notSta = notSta + projectReportData.get(z).getNotStarted();
                                inPro = inPro + projectReportData.get(z).getInProgress();
                                completed = completed + projectReportData.get(z).getCompleted();

                                total = total + projectReportData.get(z).getOts();
                                reportData.setUnitId(projectReportData.get(z).getUnitId());
                                reportData.setUnitName(projectReportData.get(z).getUnitName());
                                reportData.setProjectId(projectReportData.get(z).getProjectId());
                                reportData.setProjectName(projectReportData.get(z).getProjectName());

                            }

                            reportData.setTanks(tanks);
                            reportData.setTanks_to_be_fed(tanksTobeFed);
                            reportData.setTechsanctions(tsCnt);
                            reportData.setTechsancots(techSanOts);
                            reportData.setTenders(tenders);
                            reportData.setAgreements(agreements);
                            reportData.setNotStarted(notSta);
                            reportData.setInProgress(inPro);
                            reportData.setCompleted(completed);
                            reportData.setTotal(total);
                            reportData.setNominations(nomination);

                        }

                        setPieData(notSta, inPro, completed, total);

                        subReportData.add(reportData);


                        if (subReportData.size() > 0) {

                            sortDataUnitProjects(subReportData);

                            OTUnitProjectAdapter dashboardSubAdapter = new OTUnitProjectAdapter(subReportData, this, this);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                            unitsRecyclerView.setLayoutManager(mLayoutManager);
                            unitsRecyclerView.setAdapter(dashboardSubAdapter);
                        }

                    }
                }
            }

//            OTExpandedAdapter dashboardSubAdapter = new OTExpandedAdapter(subReportData, context, activity);
//            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//            itemViewHolder.extradetailsRv.setLayoutManager(mLayoutManager);
//            itemViewHolder.extradetailsRv.setAdapter(dashboardSubAdapter);
//
//            if (itemViewHolder.extradetailsRv.getVisibility() == View.GONE) {
//                itemViewHolder.extradetailsRv.setVisibility(View.VISIBLE);
//                itemViewHolder.title.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.up_arrow, 0);
//            } else {
//                itemViewHolder.extradetailsRv.setVisibility(View.GONE);
//                itemViewHolder.title.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_arrow, 0);
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void sortData(ArrayList<ProjectReportData> projectReportData) {
        Collections.sort(projectReportData, new Comparator<ProjectReportData>() {
            public int compare(ProjectReportData lhs, ProjectReportData rhs) {
                return (lhs.getUnitName().compareTo(rhs.getUnitName()));
            }
        });
    }


    private void sortDataUnitProjects(ArrayList<ReportData> projectReportData) {
        Collections.sort(projectReportData, new Comparator<ReportData>() {
            public int compare(ReportData lhs, ReportData rhs) {
                return (lhs.getUnitName().compareTo(rhs.getUnitName()));
            }
        });
    }

    private void setPieData(long notStarted, long inProgress, long completed, long total) {
        pieEntries = new ArrayList<>();
        int index = 0;
        LegendEntry legendEntryNs = new LegendEntry();
        LegendEntry legendEntryIp = new LegendEntry();
        LegendEntry legendEntryC = new LegendEntry();
        legendEntries = new ArrayList<>();

        if (notStarted > 0) {
            pieEntries.add(new PieEntry(notStarted, index++));

            legendEntryNs.label = "Not Started";
            legendEntryNs.formSize = 18f;
            legendEntryNs.formColor = Color.parseColor("#EE3738");
            legendEntries.add(legendEntryNs);

        }
        if (inProgress > 0) {
            pieEntries.add(new PieEntry(inProgress, index++));

            legendEntryIp.label = "In Progress";
            legendEntryIp.formSize = 18f;
            legendEntryIp.formColor = Color.parseColor("#FF7F00");
            legendEntries.add(legendEntryIp);
        }

        if (completed > 0) {
            pieEntries.add(new PieEntry(completed, index++));

            legendEntryC.label = "Completed";
            legendEntryC.formSize = 18f;
            legendEntryC.formColor = Color.parseColor("#008000");
            legendEntries.add(legendEntryC);
        }

        Legend legend = pieChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        legend.setCustom(legendEntries);


        ArrayList<Integer> arrayList = new ArrayList<>();
        if (notStarted > 0) {
            arrayList.add(Color.parseColor("#EE3738"));
        }
        if (inProgress > 0) {
            arrayList.add(Color.parseColor("#FF7F00"));
        }
        if (completed > 0) {
            arrayList.add(Color.parseColor("#008000"));
        }


        dataSet = new PieDataSet(pieEntries, "Abstract Report");
        int[] col = Utilities.convertIntegers(arrayList);
        dataSet.setColors(col);
        PieData pieData = new PieData(dataSet);
        pieData.setValueTextSize(18f);
        pieData.setValueTextColor(Color.WHITE);
        pieChart.setData(pieData);
        pieChart.setHoleColor(android.R.color.transparent);
        pieChart.setDrawCenterText(true);
        pieChart.setCenterText("Total: " + total);
        pieChart.setCenterTextColor(Color.WHITE);
        pieChart.setCenterTextSize(18f);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setTextColor(Color.WHITE);
        //pieChart.setTransparentCircleRadius(10);
        pieChart.animateXY(1000, 1000);
        pieChart.setOnChartValueSelectedListener(this);

        //viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FoundationPieFragment();
                case 1:
                    return new SuperStrPieFragment();
                case 2:
                    return new ShutterPieFragment();
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
                    return "Foundation";
                case 1:
                    return "Super Structure";
                case 2:
                    return "Shutters";
            }
            return null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            clearData("");
            return true;
        }
        if (item.getItemId() == R.id.item_report) {
            clearData("");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pie_menu, menu);
        return true;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        LegendEntry legendEntry = legendEntries.get((int) h.getX());
        clearData(legendEntry.label);
    }

    @Override
    public void onNothingSelected() {

    }


    @Override
    public void onBackPressed() {
        clearData("");
    }

    private void clearData(String label) {
        editor.putString("SEL_STAGE", label);
        editor.commit();
        finish();
    }
}