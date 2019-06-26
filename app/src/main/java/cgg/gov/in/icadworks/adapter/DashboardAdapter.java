package cgg.gov.in.icadworks.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.model.response.ot.OTData;
import cgg.gov.in.icadworks.view.OTDetailActivityLoc;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ItemViewHolder> implements Filterable {

    private ArrayList<OTData> otResponse;
    private ArrayList<OTData> mFilteredList;
    private Context context;

    public DashboardAdapter(ArrayList<OTData> otResponse, Context context) {
        this.otResponse = otResponse;
        mFilteredList = otResponse;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dashboard_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, final int position) {
        try {
            itemViewHolder.projectData.setText(mFilteredList.get(position).getProjectname());
            itemViewHolder.resData.setText(mFilteredList.get(position).getReservoirname());
            itemViewHolder.canalData.setText(mFilteredList.get(position).getCanalname());
            itemViewHolder.otID.setText(mFilteredList.get(position).getStructureId());
            itemViewHolder.otName.setText(mFilteredList.get(position).getStructurename());
            itemViewHolder.otVillage.setText(mFilteredList.get(position).getVillagename()
                    + "(v),"
                    + mFilteredList.get(position).getMandalname()
                    + "(m),"
                    + mFilteredList.get(position).getDistrictname());

            DashboardSubAdapter dashboardSubAdapter = new DashboardSubAdapter(mFilteredList.get(position), context);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            itemViewHolder.extradetailsRv.setLayoutManager(mLayoutManager);
            itemViewHolder.extradetailsRv.setAdapter(dashboardSubAdapter);

            itemViewHolder.cardItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, OTDetailActivityLoc.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            .putExtra("ITEM_DATA", mFilteredList.get(position)));
                }
            });
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
                    mFilteredList = otResponse;
                } else {
                    try {
                        ArrayList<OTData> filteredList = new ArrayList<>();
                        for (OTData otData : otResponse) {



                            if (!TextUtils.isEmpty(otData.getProjectname()) && otData.getProjectname().toLowerCase().contains(charString.toLowerCase())
                                    ||
                                    !TextUtils.isEmpty(otData.getReservoirname()) && otData.getReservoirname().toLowerCase().contains(charString.toLowerCase())
                                    ||
                                    !TextUtils.isEmpty(otData.getCanalname()) && otData.getCanalname().toLowerCase().contains(charString.toLowerCase())
                                    ||
                                    !TextUtils.isEmpty(otData.getStructureId()) && otData.getStructureId().toLowerCase().contains(charString.toLowerCase())
                                    ||
                                    !TextUtils.isEmpty(otData.getStructurename()) && otData.getStructurename().toLowerCase().contains(charString.toLowerCase())
                                    ||
                                    !TextUtils.isEmpty(otData.getDistrictname()) && otData.getDistrictname().toLowerCase().contains(charString.toLowerCase())
                                    ||
                                    !TextUtils.isEmpty(otData.getMandalname()) && otData.getMandalname().toLowerCase().contains(charString.toLowerCase())
                                    ||
                                    !TextUtils.isEmpty(otData.getVillagename()) && otData.getVillagename().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(otData);
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
                mFilteredList = (ArrayList<OTData>) filterResults.values;
                notifyDataSetChanged();

                getFilteredData();
            }
        };
    }

    public ArrayList<OTData> getFilteredData() {
       return mFilteredList;
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.projectData)
        CustomFontTextView projectData;
        @BindView(R.id.resData)
        CustomFontTextView resData;
        @BindView(R.id.canalData)
        CustomFontTextView canalData;
        @BindView(R.id.otID)
        CustomFontTextView otID;
        @BindView(R.id.otName)
        CustomFontTextView otName;
        @BindView(R.id.otVillage)
        CustomFontTextView otVillage;
        @BindView(R.id.cardItem)
        CardView cardItem;
        @BindView(R.id.extradetails_rv)
        RecyclerView extradetailsRv;

        ItemViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
