package cgg.gov.in.icadworks.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.model.response.works.WorkDetailsData;

public class WorkDetailsAdapter extends
        RecyclerView.Adapter<WorkDetailsAdapter.ItemViewHolder>
        implements Filterable {

    private List<WorkDetailsData> workDetailsData;
    private List<WorkDetailsData> mFilteredList;
    private Context context;

    public WorkDetailsAdapter(List<WorkDetailsData> workDetailsData, Context context) {
        this.workDetailsData = workDetailsData;
        mFilteredList = workDetailsData;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.work_details_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, final int position) {
        try {
            itemViewHolder.agencyNameTv.setText(mFilteredList.get(position).getAgencyName());
            itemViewHolder.tecSanNumTv.setText(mFilteredList.get(position).getTechnicalSanctionNumber());
            itemViewHolder.aggNumTv.setText(mFilteredList.get(position).getAgreementNumber());
            itemViewHolder.aggAmtTv.setText("RS. "+mFilteredList.get(position).getAgreementAmount() + "/-");
            itemViewHolder.otCountTv.setText(String.valueOf(mFilteredList.get(position).getOtCount()));
            itemViewHolder.billStatusTv.setText(mFilteredList.get(position).getBillStatus());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return mFilteredList == null ? 0 : mFilteredList.size();
    }

    @OnClick(R.id.cardItem)
    public void onViewClicked() {

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilteredList = workDetailsData;
                } else {
                    try {
                        ArrayList<WorkDetailsData> filteredList = new ArrayList<>();
                        for (WorkDetailsData detailsData : workDetailsData) {
                            if (!TextUtils.isEmpty(detailsData.getAgencyName()) && detailsData.getAgencyName().toLowerCase().contains(charString.toLowerCase())
                                    ||
                                    !TextUtils.isEmpty(detailsData.getTechnicalSanctionNumber()) && detailsData.getTechnicalSanctionNumber().toLowerCase().contains(charString.toLowerCase())
                                    ||
                                    !TextUtils.isEmpty(detailsData.getAgreementNumber()) && detailsData.getAgreementNumber().toLowerCase().contains(charString.toLowerCase())) {
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
                mFilteredList = (ArrayList<WorkDetailsData>) filterResults.values;
                notifyDataSetChanged();

                getFilteredData();
            }
        };
    }

    public List<WorkDetailsData> getFilteredData() {
        return mFilteredList;
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.agencyNameTv)
        CustomFontTextView agencyNameTv;
        @BindView(R.id.tecSanNumTv)
        CustomFontTextView tecSanNumTv;
        @BindView(R.id.aggNumTv)
        CustomFontTextView aggNumTv;
        @BindView(R.id.aggAmtTv)
        CustomFontTextView aggAmtTv;
        @BindView(R.id.otCountTv)
        CustomFontTextView otCountTv;
        @BindView(R.id.billStatusTv)
        CustomFontTextView billStatusTv;


        ItemViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
