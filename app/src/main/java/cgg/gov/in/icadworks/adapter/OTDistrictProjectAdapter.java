package cgg.gov.in.icadworks.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.model.response.report.ReportData;
import cgg.gov.in.icadworks.util.Utilities;

public class OTDistrictProjectAdapter extends
        RecyclerView.Adapter<OTDistrictProjectAdapter.ItemViewHolder>
        implements Filterable {

    private ArrayList<ReportData> otResponse;
    private ArrayList<ReportData> mFilteredList;
    private Context context;
    private Activity activity;
    ArrayList<PieEntry> pieEntries;
    private List<LegendEntry> legendEntries;
    private PieDataSet dataSet;
    private static int currentPosition;

    public OTDistrictProjectAdapter(ArrayList<ReportData> otResponse, Context context, Activity activity) {
        this.otResponse = otResponse;
        mFilteredList = otResponse;
        this.context = context;
        this.activity = activity;
        currentPosition = 0;
        pieEntries = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ot_unit_project_pie_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder itemViewHolder, final int position) {
        try {
            itemViewHolder.title.setText(mFilteredList.get(position).getProjectName());
            itemViewHolder.absrtractLl.setVisibility(View.GONE);

            if (currentPosition == position) {
                //creating an animation
                Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.nav_default_enter_anim);

                //toggling visibility
                itemViewHolder.absrtractLl.setVisibility(View.VISIBLE);

                //adding sliding effect
                itemViewHolder.absrtractLl.startAnimation(slideDown);
            }
            itemViewHolder.ll_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //getting the position of the item to expand it
                    currentPosition = position;

                    //reloding the list
                    notifyDataSetChanged();
                }

            });

            setPieData(mFilteredList.get(position).getNotStarted().intValue(),
                    mFilteredList.get(position).getInProgress().intValue(),
                    mFilteredList.get(position).getCompleted().intValue(),
                    mFilteredList.get(position).getTotal().intValue(), itemViewHolder);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setPieData(int notStarted, int inProgress, int completed, int total, ItemViewHolder itemViewHolder) {
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

        Legend legend = itemViewHolder.pieChartIv.getLegend();
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
        itemViewHolder.pieChartIv.setData(pieData);
        itemViewHolder.pieChartIv.setHoleColor(android.R.color.transparent);
        itemViewHolder.pieChartIv.setDrawCenterText(true);
        itemViewHolder.pieChartIv.setCenterText("Total: " + total);
        itemViewHolder.pieChartIv.setCenterTextColor(Color.WHITE);
        itemViewHolder.pieChartIv.setCenterTextSize(18f);
        itemViewHolder.pieChartIv.getDescription().setEnabled(false);
        itemViewHolder.pieChartIv.getLegend().setTextColor(Color.WHITE);
        //pieChart.setTransparentCircleRadius(10);
        itemViewHolder.pieChartIv.animateXY(1000, 1000);

        //viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilteredList = otResponse;
                } else {
                    try {
                        ArrayList<ReportData> filteredList = new ArrayList<>();
                        for (ReportData detailsData : otResponse) {
                            if (!TextUtils.isEmpty(detailsData.getProjectName())
                                    && detailsData.getProjectName().toLowerCase()
                                    .contains(charString.toLowerCase())) {
                                filteredList.add(detailsData);
                            }
                        }
                        mFilteredList = filteredList;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<ReportData>) filterResults.values;
                notifyDataSetChanged();

                getFilteredData();
            }
        };
    }

    public List<ReportData> getFilteredData() {
        return mFilteredList;
    }


    @Override
    public int getItemCount() {
        return mFilteredList == null ? 0 : mFilteredList.size();
    }

    @OnClick(R.id.cardItem)
    public void onViewClicked() {

    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        CustomFontTextView title;
        @BindView(R.id.absrtract_ll)
        CardView absrtractLl;
        @BindView(R.id.ll_title)
        LinearLayout ll_title;
        @BindView(R.id.pieChart)
        PieChart pieChartIv;

        ItemViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
