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
import java.util.Collections;
import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.model.response.checkdam.office.CDOfficeData;
import cgg.gov.in.icadworks.model.response.checkdam.office.CDOfficeResponse;
import cgg.gov.in.icadworks.model.response.report.ReportData;
import cgg.gov.in.icadworks.util.Utilities;

public class CDDistrictReportAdapter extends RecyclerView.Adapter<CDDistrictReportAdapter.ItemViewHolder> implements Filterable {

    private ArrayList<CDOfficeData> cdOfficeDataArrayList;
    private CDOfficeResponse cdOfficeResponse;
    private ArrayList<CDOfficeData> mFilteredList;
    private Context context;
    ArrayList<ReportData> subReportData;
    Activity activity;

    public CDDistrictReportAdapter(CDOfficeResponse cdOfficeResponse, ArrayList<CDOfficeData> cdOfficeDataArrayList, Context context, Activity activity) {
        this.cdOfficeResponse = cdOfficeResponse;
        this.cdOfficeDataArrayList = cdOfficeDataArrayList;
        mFilteredList = cdOfficeDataArrayList;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cd_sub_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder itemViewHolder, final int position) {
        try {
            itemViewHolder.extradetailsRv.setVisibility(View.GONE);
            itemViewHolder.title.setText(mFilteredList.get(position).getDname());
            itemViewHolder.tanksCount.setText(String.valueOf(mFilteredList.get(position).getCheckDams()));
            itemViewHolder.tsCount.setText(String.valueOf(mFilteredList.get(position).getTechSanctions()));
            itemViewHolder.tenCount.setText(String.valueOf(mFilteredList.get(position).getTendersAward()));
            itemViewHolder.aggCount.setText(String.valueOf(mFilteredList.get(position).getAgreements()));
            itemViewHolder.notStCount.setText(String.valueOf(mFilteredList.get(position).getNot_started()));
            itemViewHolder.inProCount.setText(String.valueOf(mFilteredList.get(position).getIn_progress()));
            itemViewHolder.comCount.setText(String.valueOf(mFilteredList.get(position).getCompleted()));
            itemViewHolder.totalCount.setText(String.valueOf(mFilteredList.get(position).getTotal_cds()));

            itemViewHolder.shareIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LinearLayout abstractView =itemViewHolder.dataLl;
                    Utilities.takeSCImage(activity, abstractView ,
                            mFilteredList.get(position).getOfficeName() + "_CD Unit Data");
                }
            });
//            itemViewHolder.title.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    try {
//
//
//                        if (checkDamResponse.getData().size() > 0) {
//
//                            ArrayList<CheckDamData> projectReportData = new ArrayList<>();
//                            subReportData = new ArrayList<>();
//                            CheckDamData checkDamData = null;
//
//                            for (int z = 0; z < checkDamResponse.getData().size(); z++) {
//                                if (mFilteredList.get(position).getUnitId().equals(checkDamResponse.getData().get(z).getUnit())){
//                                    checkDamData = checkDamResponse.getData().get(z);
//                                    projectReportData.add(checkDamData);
//                                }
//                            }
//
//
//                            if (projectReportData.size() > 0) {
//
//                                Set<Integer> hashSet = new HashSet<>();
//                                for (int x = 0; x < projectReportData.size(); x++) {
//                                    hashSet.add(projectReportData.get(x).getCircle());
//                                }
//
//                                Iterator<Integer> iterator = hashSet.iterator();
//
//                                while (iterator.hasNext()) {
//                                    int tanks = 0,  tanksTobeFed = 0, tsCnt = 0, techSanOts = 0, tenders = 0, agreements = 0, nomination=0;
//                                    int notSta = 0, inPro = 0, completed = 0, total = 0;
//                                    int cirId = iterator.next();
//                                    checkDamData = new CheckDamData();
//
//
//                                    for (int z = 0; z < projectReportData.size(); z++) {
//                                        if (cirId == projectReportData.get(z).getCircle()) {
//                                            tanks = tanks + projectReportData.get(z).getTanks();
//                                            tanksTobeFed = tanksTobeFed + projectReportData.get(z).getTanks_to_be_fed();
//                                            tsCnt = tsCnt + projectReportData.get(z).getTechsanctions();
//                                            techSanOts = techSanOts + projectReportData.get(z).getTechsancots();
//                                            tenders = tenders + projectReportData.get(z).getTenders();
//                                            nomination = nomination + projectReportData.get(z).getNominations();
//                                            agreements = agreements + projectReportData.get(z).getAgreements();
//
//                                            notSta = notSta + projectReportData.get(z).getNotStarted();
//                                            inPro = inPro + projectReportData.get(z).getInProgress();
//                                            completed = completed + projectReportData.get(z).getCompleted();
//
//                                            total = total + projectReportData.get(z).getOts();
//                                            reportData.setUnitId(projectReportData.get(z).getUnitId());
//                                            reportData.setUnitName(projectReportData.get(z).getUnitName());
//                                            reportData.setProjectId(projectReportData.get(z).getProjectId());
//                                            reportData.setProjectName(projectReportData.get(z).getProjectName());
//
//                                        }
//
//                                        reportData.setTanks(tanks);
//                                        reportData.setTanks_to_be_fed(tanksTobeFed);
//                                        reportData.setTechsanctions(tsCnt);
//                                        reportData.setTechsancots(techSanOts);
//                                        reportData.setTenders(tenders);
//                                        reportData.setAgreements(agreements);
//                                        reportData.setNotStarted(notSta);
//                                        reportData.setInProgress(inPro);
//                                        reportData.setCompleted(completed);
//                                        reportData.setTotal(total);
//                                        reportData.setNominations(nomination);
//
//                                    }
//
//                                    subReportData.add(reportData);
//
//
//                                    if (subReportData.size() > 0) {
//
//                                        sortData(subReportData);
//
//                                        OTExpandedAdapter dashboardSubAdapter = new OTExpandedAdapter(subReportData, context, activity);
//                                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//                                        itemViewHolder.extradetailsRv.setLayoutManager(mLayoutManager);
//                                        itemViewHolder.extradetailsRv.setAdapter(dashboardSubAdapter);
//                                    }
//
//                                }
//                            }
//                        }
//
//                        OTExpandedAdapter dashboardSubAdapter = new OTExpandedAdapter(subReportData, context, activity);
//                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//                        itemViewHolder.extradetailsRv.setLayoutManager(mLayoutManager);
//                        itemViewHolder.extradetailsRv.setAdapter(dashboardSubAdapter);
//
//                        if (itemViewHolder.extradetailsRv.getVisibility() == View.GONE) {
//                            itemViewHolder.extradetailsRv.setVisibility(View.VISIBLE);
//                            itemViewHolder.title.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.up_arrow, 0);
//                        } else {
//                            itemViewHolder.extradetailsRv.setVisibility(View.GONE);
//                            itemViewHolder.title.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_arrow, 0);
//                        }
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
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
                    mFilteredList = cdOfficeDataArrayList;
                } else {
                    ArrayList<CDOfficeData> filteredList = new ArrayList<>();
                    for (CDOfficeData cdOfficeData : cdOfficeDataArrayList) {
                        if (cdOfficeData.getDname().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(cdOfficeData);
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
                mFilteredList = (ArrayList<CDOfficeData>) filterResults.values;
                notifyDataSetChanged();
                getFilteredData();
            }
        };
    }

    public ArrayList<CDOfficeData> getFilteredData() {
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
