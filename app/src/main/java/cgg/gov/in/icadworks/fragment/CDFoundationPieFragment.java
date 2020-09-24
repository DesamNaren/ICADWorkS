package cgg.gov.in.icadworks.fragment;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.model.response.checkdam.CheckDamData;
import cgg.gov.in.icadworks.model.response.checkdam.CheckDamResponse;
import cgg.gov.in.icadworks.model.response.ot.OTData;
import cgg.gov.in.icadworks.model.response.ot.OTResponse;
import cgg.gov.in.icadworks.util.Utilities;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by lenovo on 03-06-2019.
 */

public class CDFoundationPieFragment extends Fragment implements OnChartValueSelectedListener {

    @BindView(R.id.pieChart)
    PieChart pieChartIv;
    private Unbinder unbinder;
    private SharedPreferences sharedPreferences;
    PieChart pieChart;
    PieDataSet dataSet;
    ArrayList<PieEntry> pieEntries;
    private List<LegendEntry> legendEntries;
    private SharedPreferences.Editor editor;
    private List<CheckDamData> notStrOtData, inProOtData, comOtData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.foundation_pie_fragment, container, false);
        setHasOptionsMenu(true);
        unbinder = ButterKnife.bind(this, view);
        pieChart = view.findViewById(R.id.pieChart);
        try {
            if (getActivity() != null) {
                sharedPreferences = getActivity().getSharedPreferences("APP_PREF", MODE_PRIVATE);
                editor = sharedPreferences.edit();
                String pieChartData = sharedPreferences.getString("CD_PIE_DATA", "");
                CheckDamResponse checkDamResponse = new Gson().fromJson(pieChartData, CheckDamResponse.class);

                if (checkDamResponse != null && checkDamResponse.getData().size() > 0) {
                    setPieChartData(checkDamResponse);
                } else {
                    Toast.makeText(getActivity(), getString(R.string.something), Toast.LENGTH_SHORT).show();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    private void setPieChartData(CheckDamResponse checkDamResponse) {
        try {
            pieEntries = new ArrayList<>();

            LegendEntry legendEntryNs = new LegendEntry();
            LegendEntry legendEntryIp = new LegendEntry();
            LegendEntry legendEntryC = new LegendEntry();
            legendEntries = new ArrayList<>();

            notStrOtData = new ArrayList<>();
            inProOtData = new ArrayList<>();
            comOtData = new ArrayList<>();

            for (int x = 0; x < checkDamResponse.getData().size(); x++) {

                if (checkDamResponse.getData().get(x).getGetItemStatusData().get(0).getIrrWorkId().equalsIgnoreCase("1")
                        && checkDamResponse.getData().get(x).getGetItemStatusData().get(0).getStatusId().contains("1")) {
                    notStrOtData.add(checkDamResponse.getData().get(x));
                }

                if (checkDamResponse.getData().get(x).getGetItemStatusData().get(0).getIrrWorkId().equalsIgnoreCase("1")
                        && checkDamResponse.getData().get(x).getGetItemStatusData().get(0).getStatusId().contains("2")) {
                    inProOtData.add(checkDamResponse.getData().get(x));
                }

                if (checkDamResponse.getData().get(x).getGetItemStatusData().get(0).getIrrWorkId().equalsIgnoreCase("1")
                        && checkDamResponse.getData().get(x).getGetItemStatusData().get(0).getStatusId().contains("3")) {
                    comOtData.add(checkDamResponse.getData().get(x));
                }

            }

            int index = 0;
            if (notStrOtData.size() > 0) {
                pieEntries.add(new PieEntry(notStrOtData.size(), index++));

                legendEntryNs.label = "Not Started";
                legendEntryNs.formSize = 18f;
                legendEntryNs.formColor = Color.parseColor("#EE3738");
                legendEntries.add(legendEntryNs);

            }

            if (inProOtData.size() > 0) {
                pieEntries.add(new PieEntry(inProOtData.size(), index++));

                legendEntryIp.label = "In Progress";
                legendEntryIp.formSize = 18f;
                legendEntryIp.formColor = Color.parseColor("#FF7F00");
                legendEntries.add(legendEntryIp);
            }

            if (comOtData.size() > 0) {
                pieEntries.add(new PieEntry(comOtData.size(), index++));

                legendEntryC.label = "Completed";
                legendEntryC.formSize = 18f;
                legendEntryC.formColor = Color.parseColor("#008000");
                legendEntries.add(legendEntryC);
            }

            Legend legend = pieChart.getLegend();
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

            legend.setCustom(legendEntries);


            ArrayList<Integer> arrayList = new ArrayList<>();
            if (notStrOtData.size() > 0) {
                arrayList.add(Color.parseColor("#EE3738"));
            }
            if (inProOtData.size() > 0) {
                arrayList.add(Color.parseColor("#FF7F00"));
            }
            if (comOtData.size() > 0) {
                arrayList.add(Color.parseColor("#008000"));
            }


            dataSet = new PieDataSet(pieEntries, "Foundation Report");
            int[] col = Utilities.convertIntegers(arrayList);
            dataSet.setColors(col);
            PieData pieData = new PieData(dataSet);
            pieData.setValueTextSize(18f);
            pieData.setValueTextColor(Color.WHITE);
            pieChart.setData(pieData);
            pieChart.setHoleColor(android.R.color.transparent);
            pieChart.setDrawCenterText(true);
            pieChart.setCenterText("Total: " + checkDamResponse.getAbstractReport().get(0).getTotal());
            pieChart.setCenterTextColor(Color.WHITE);
            pieChart.setCenterTextSize(18f);
            pieChart.getDescription().setEnabled(false);
            pieChart.getLegend().setTextColor(Color.WHITE);
            //pieChart.setTransparentCircleRadius(10);
            pieChart.animateXY(1000, 1000);
            pieChart.setOnChartValueSelectedListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
