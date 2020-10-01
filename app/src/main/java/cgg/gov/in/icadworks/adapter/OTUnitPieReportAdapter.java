package cgg.gov.in.icadworks.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.model.ProjectReportData;
import cgg.gov.in.icadworks.model.response.report.ReportData;
import cgg.gov.in.icadworks.model.response.report.ReportResponse;
import cgg.gov.in.icadworks.view.OTUnitProjectPieActivity;

public class OTUnitPieReportAdapter extends RecyclerView.Adapter<OTUnitPieReportAdapter.ItemViewHolder> implements Filterable {


    private ArrayList<ProjectReportData> t_projectReportData;
    private ReportResponse reportResponse;
    private ArrayList<ProjectReportData> mFilteredList;
    private Context context;
    ArrayList<ReportData> subReportData;
    Activity activity;

    public OTUnitPieReportAdapter(ReportResponse reportResponse,
                                  ArrayList<ProjectReportData> t_projectReportData, Context context, Activity activity) {
        this.reportResponse = reportResponse;
        this.t_projectReportData = t_projectReportData;
        mFilteredList = t_projectReportData;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ot_unit_pie_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder itemViewHolder, final int position) {
        try {

            itemViewHolder.title.setText(mFilteredList.get(position).getUnitName());

            itemViewHolder.abstractLL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    context.startActivity(new Intent(context, OTUnitProjectPieActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            .putExtra("UNIT_ID", mFilteredList.get(position).getUnitId())
                            .putExtra("UNIT_NAME", mFilteredList.get(position).getUnitName())
                            .putExtra("NS", mFilteredList.get(position).getNotStarted())
                            .putExtra("IP", mFilteredList.get(position).getInProgress())
                            .putExtra("CO", mFilteredList.get(position).getCompleted())
                            .putExtra("TO", mFilteredList.get(position).getTotal()));
                }
            });

        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    private void sortData(ArrayList<ReportData> reportData) {
        Collections.sort(reportData, new Comparator<ReportData>() {
            public int compare(ReportData lhs, ReportData rhs) {
                return (lhs.getProjectName().compareTo(rhs.getProjectName()));
            }
        });
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilteredList = t_projectReportData;
                } else {
                    ArrayList<ProjectReportData> filteredList = new ArrayList<>();
                    for (ProjectReportData otData : t_projectReportData) {
                        if (otData.getUnitName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(otData);
                        }
                    }
                    mFilteredList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<ProjectReportData>) filterResults.values;
                notifyDataSetChanged();
                getFilteredData();
            }
        };
    }

    public ArrayList<ProjectReportData> getFilteredData() {
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
        CardView abstractLL;

        ItemViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
