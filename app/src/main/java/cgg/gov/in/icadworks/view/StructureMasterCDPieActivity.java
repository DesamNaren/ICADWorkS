package cgg.gov.in.icadworks.view;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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
import java.util.List;

import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.fragment.CDFoundationPieFragment;
import cgg.gov.in.icadworks.fragment.CDProtectionPieFragment;
import cgg.gov.in.icadworks.fragment.CDSuperStrPieFragment;
import cgg.gov.in.icadworks.model.response.checkdam.CheckDamAbstractReport;
import cgg.gov.in.icadworks.model.response.checkdam.CheckDamResponse;
import cgg.gov.in.icadworks.util.Utilities;

public class StructureMasterCDPieActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    PieChart pieChart;
    PieDataSet dataSet;
    ArrayList<PieEntry> pieEntries;
    private List<LegendEntry> legendEntries;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.structure_master_abstract_pie_activity);


        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle("CD Abstract Report");
                ColorDrawable colorDrawable = new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimaryDark));
                getSupportActionBar().setBackgroundDrawable(colorDrawable);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pieChart = findViewById(R.id.pieChart);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
        tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));

        sharedPreferences = getSharedPreferences("APP_PREF", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String pieChartData = sharedPreferences.getString("CD_PIE_DATA", "");
        CheckDamResponse checkDamResponse = new Gson().fromJson(pieChartData, CheckDamResponse.class);

        if (checkDamResponse != null && checkDamResponse.getAbstractReport().size() > 0) {
            setPieData(checkDamResponse.getAbstractReport());
        } else {
            Toast.makeText(this, getString(R.string.something), Toast.LENGTH_SHORT).show();
        }
    }

    private void setPieData(List<CheckDamAbstractReport> abstractReports) {
        pieEntries = new ArrayList<>();
        int index = 0;
        for (CheckDamAbstractReport abstractReport : abstractReports) {
            LegendEntry legendEntryNs = new LegendEntry();
            LegendEntry legendEntryIp = new LegendEntry();
            LegendEntry legendEntryC = new LegendEntry();
            legendEntries = new ArrayList<>();

            if (Float.valueOf(abstractReport.getNotStarted()) > 0) {
                pieEntries.add(new PieEntry(Float.valueOf(abstractReport.getNotStarted()), index++));

                legendEntryNs.label = "Not Started";
                legendEntryNs.formSize = 18f;
                legendEntryNs.formColor = Color.parseColor("#EE3738");
                legendEntries.add(legendEntryNs);

            }
            if (Float.valueOf(abstractReport.getInProgress()) > 0) {
                pieEntries.add(new PieEntry(Float.valueOf(abstractReport.getInProgress()), index++));

                legendEntryIp.label = "In Progress";
                legendEntryIp.formSize = 18f;
                legendEntryIp.formColor = Color.parseColor("#FF7F00");
                legendEntries.add(legendEntryIp);
            }

            if (Float.valueOf(abstractReport.getCompleted()) > 0) {
                pieEntries.add(new PieEntry(Float.valueOf(abstractReport.getCompleted()), index++));

                legendEntryC.label = "Completed";
                legendEntryC.formSize = 18f;
                legendEntryC.formColor = Color.parseColor("#008000");
                legendEntries.add(legendEntryC);
            }

            Legend legend = pieChart.getLegend();
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

            legend.setCustom(legendEntries);

        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        if (Long.valueOf(abstractReports.get(0).getNotStarted()) > 0) {
            arrayList.add(Color.parseColor("#EE3738"));
        }
        if (Long.valueOf(abstractReports.get(0).getInProgress()) > 0) {
            arrayList.add(Color.parseColor("#FF7F00"));
        }
        if (Long.valueOf(abstractReports.get(0).getCompleted()) > 0) {
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
        pieChart.setCenterText("Total: " + abstractReports.get(0).getTotal());
        pieChart.setCenterTextColor(Color.WHITE);
        pieChart.setCenterTextSize(18f);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setTextColor(Color.WHITE);
        //pieChart.setTransparentCircleRadius(10);
        pieChart.animateXY(1000, 1000);
        pieChart.setOnChartValueSelectedListener(this);

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new CDFoundationPieFragment();
                case 1:
                    return new CDSuperStrPieFragment();
                case 2:
                    return new CDProtectionPieFragment();
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
                    return "Protection Works";
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