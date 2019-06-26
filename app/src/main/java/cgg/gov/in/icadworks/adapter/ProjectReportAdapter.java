package cgg.gov.in.icadworks.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.model.ProjectReportData;
import cgg.gov.in.icadworks.model.response.ot.OTData;
import cgg.gov.in.icadworks.model.response.report.ReportData;
import cgg.gov.in.icadworks.util.Utilities;

public class ProjectReportAdapter extends RecyclerView.Adapter<ProjectReportAdapter.ItemViewHolder> implements Filterable {


    private ArrayList<ProjectReportData> projectReportData;
    private ArrayList<ProjectReportData> mFilteredList;
    private Context context;
    private Activity activity;

    public ProjectReportAdapter(ArrayList<ProjectReportData> projectReportData, Context context, Activity activity) {
        this.projectReportData = projectReportData;
        mFilteredList = projectReportData;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pro_report_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder itemViewHolder, final int position) {
        try {
            itemViewHolder.title.setText(mFilteredList.get(position).getProjectName());
            itemViewHolder.tanksCount.setText(String.valueOf(mFilteredList.get(position).getTanks() +"/" + mFilteredList.get(position).getTanksTobeFed()));
            itemViewHolder.tsCount.setText(String.valueOf(mFilteredList.get(position).getTechsanctions())
                    +
                    " [ "
                    + String.valueOf(mFilteredList.get(position).getTechsancots())
                    + " ]");
            itemViewHolder.tenCount.setText(String.valueOf(mFilteredList.get(position).getTenders())
                    +" [ " + mFilteredList.get(position).getNominations() + " ]");
            itemViewHolder.aggCount.setText(String.valueOf(mFilteredList.get(position).getAgreements()));
            itemViewHolder.notStCount.setText(String.valueOf(mFilteredList.get(position).getNotStarted()));
            itemViewHolder.inProCount.setText(String.valueOf(mFilteredList.get(position).getInProgress()));
            itemViewHolder.comCount.setText(String.valueOf(mFilteredList.get(position).getCompleted()));
            itemViewHolder.totalCount.setText(String.valueOf(mFilteredList.get(position).getTotal()));

            itemViewHolder.shareIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LinearLayout abstractView =itemViewHolder.dataLl;
                    Utilities.takeSCImage(activity, abstractView ,
                            mFilteredList.get(position).getProjectName() + "_Project Data");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilteredList = projectReportData;
                } else {
                    ArrayList<ProjectReportData> filteredList = new ArrayList<>();
                    for (ProjectReportData otData : projectReportData) {
                        if (otData.getProjectName().toLowerCase().contains(charString.toLowerCase())) {
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
        @BindView(R.id.shareIV)
        ImageView shareIV;

        ItemViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
