package cgg.gov.in.icadworks.view;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.model.response.ot.AbstractReport;

public class StructureMasterOTPieActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    PieChart pieChart;
    PieData pieData;
    PieDataSet dataSet;
    ArrayList<PieEntry> pieEntries;
    private List<LegendEntry> legendEntries;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.structure_master_abstract_pie_activity);


        try {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle("Abstract Report");
                ColorDrawable colorDrawable = new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimaryDark));
                getSupportActionBar().setBackgroundDrawable(colorDrawable);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pieChart = findViewById(R.id.pieChart);

        sharedPreferences = getSharedPreferences("APP_PREF", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String pieChartData = sharedPreferences.getString("OT_ABSTRACT_PIE_DATA", "");
        Type listType = new TypeToken<ArrayList<AbstractReport>>() {
        }.getType();
        List<AbstractReport> abstractReports = new Gson().fromJson(pieChartData, listType);

        if (abstractReports != null && abstractReports.size() > 0) {
            setPieData(abstractReports);
        } else {
            Toast.makeText(this, getString(R.string.something), Toast.LENGTH_SHORT).show();
        }


    }

    private void setPieData(List<AbstractReport> abstractReports) {
        pieEntries = new ArrayList<>();
        int index = 0;
        for (AbstractReport abstractReport : abstractReports) {
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

        final int[] MY_COLORS = {Color.parseColor("#ee3738"), Color.parseColor("#FF7F00"), Color.parseColor("#008000")};

        dataSet = new PieDataSet(pieEntries, "Abstract Report");
        dataSet.setColors(MY_COLORS);
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
        pieChart.animateXY(2000, 2000);
        pieChart.setOnChartValueSelectedListener(this);
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

    private void clearData(String label){
        editor.putString("SEL_STAGE", label);
        editor.commit();
        finish();
    }
}