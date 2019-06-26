package cgg.gov.in.icadworks.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.model.ProjectReportData;
import cgg.gov.in.icadworks.model.response.report.ReportData;
import cgg.gov.in.icadworks.model.response.report.ReportResponse;
import cgg.gov.in.icadworks.util.Utilities;

public class CEReportAdapter extends RecyclerView.Adapter<CEReportAdapter.ItemViewHolder> implements Filterable {


    private ArrayList<ProjectReportData> t_projectReportData;
    private ReportResponse reportResponse;
    private ArrayList<ProjectReportData> mFilteredList;
    private Context context;
    ArrayList<ReportData> subReportData;
    Activity activity;

    public CEReportAdapter(ReportResponse reportResponse, ArrayList<ProjectReportData> t_projectReportData, Context context, Activity activity) {
        this.reportResponse = reportResponse;
        this.t_projectReportData = t_projectReportData;
        mFilteredList = t_projectReportData;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ce_report_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder itemViewHolder, final int position) {
        try {

            itemViewHolder.extradetailsRv.setVisibility(View.GONE);

            itemViewHolder.title.setText(mFilteredList.get(position).getUnitName());
            itemViewHolder.tanksCount.setText(String.valueOf(mFilteredList.get(position).getTanks() + "/" + mFilteredList.get(position).getTanksTobeFed()));
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
                            mFilteredList.get(position).getUnitName() + "_Unit Data");
                }
            });


            itemViewHolder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {


                        if (reportResponse.getData().size() > 0) {

                            ArrayList<ReportData> projectReportData = new ArrayList<>();
                            subReportData = new ArrayList<>();
                            ReportData reportData = null;

                            for (int z = 0; z < reportResponse.getData().size(); z++) {
                                if (mFilteredList.get(position).getUnitId().equals(reportResponse.getData().get(z).getUnitId())){
                                    reportData = reportResponse.getData().get(z);
                                    projectReportData.add(reportData);
                                }
                            }


                            if (projectReportData.size() > 0) {

                                Set<Integer> hashSet = new HashSet<>();
                                for (int x = 0; x < projectReportData.size(); x++) {
                                    hashSet.add(projectReportData.get(x).getProjectId());
                                }

                                Iterator<Integer> iterator = hashSet.iterator();

                                while (iterator.hasNext()) {
                                    int tanks = 0,  tanksTobeFed = 0, tsCnt = 0, techSanOts = 0, tenders = 0, agreements = 0, nomination=0;
                                    int notSta = 0, inPro = 0, completed = 0, total = 0;
                                    int projectId = iterator.next();
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

                                    subReportData.add(reportData);


                                    if (subReportData.size() > 0) {

                                        sortData(subReportData);

                                        DistrictSubAdapter dashboardSubAdapter = new DistrictSubAdapter(subReportData, context, activity);
                                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                                        itemViewHolder.extradetailsRv.setLayoutManager(mLayoutManager);
                                        itemViewHolder.extradetailsRv.setAdapter(dashboardSubAdapter);
                                    }

                                }
                            }
                        }

                        DistrictSubAdapter dashboardSubAdapter = new DistrictSubAdapter(subReportData, context, activity);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                        itemViewHolder.extradetailsRv.setLayoutManager(mLayoutManager);
                        itemViewHolder.extradetailsRv.setAdapter(dashboardSubAdapter);

                        if (itemViewHolder.extradetailsRv.getVisibility() == View.GONE) {
                            itemViewHolder.extradetailsRv.setVisibility(View.VISIBLE);
                            itemViewHolder.title.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.up_arrow, 0);
                        } else {
                            itemViewHolder.extradetailsRv.setVisibility(View.GONE);
                            itemViewHolder.title.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_arrow, 0);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
        @BindView(R.id.extradetails_rv)
        RecyclerView extradetailsRv;
        @BindView(R.id.shareIV)
        ImageView shareIV;

        ItemViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
