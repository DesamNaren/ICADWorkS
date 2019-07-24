package cgg.gov.in.icadworks.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
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
import cgg.gov.in.icadworks.model.response.checkdam.CheckDamData;
import cgg.gov.in.icadworks.model.response.checkdam.CheckDamItemStatusData;

public class CheckDamAdapter extends RecyclerView.Adapter<CheckDamAdapter.ItemViewHolder> implements Filterable {

    private List<CheckDamData> checkDamData;
    private List<CheckDamData> mFilteredList;
    private Context context;

    public CheckDamAdapter(List<CheckDamData> checkDamData, Context context) {
        this.checkDamData = checkDamData;
        mFilteredList = checkDamData;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.checkdam_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, final int position) {
        try {
            itemViewHolder.projectData.setText(mFilteredList.get(position).getTankName());
//            itemViewHolder.resData.setText(mFilteredList.get(position).getReservoirname());
//            itemViewHolder.canalData.setText(mFilteredList.get(position).getCanalname());
//            itemViewHolder.otID.setText(mFilteredList.get(position).getStructureId());
//            itemViewHolder.otName.setText(mFilteredList.get(position).getStructurename());
//            itemViewHolder.otVillage.setText(mFilteredList.get(position).getVillagename()
//                    + "(v),"
//                    + mFilteredList.get(position).getMandalname()
//                    + "(m),"
//                    + mFilteredList.get(position).getDistrictname());

//            DashboardSubAdapter dashboardSubAdapter = new DashboardSubAdapter(mFilteredList.get(position), context);
//            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//            itemViewHolder.extradetailsRv.setLayoutManager(mLayoutManager);
//            itemViewHolder.extradetailsRv.setAdapter(dashboardSubAdapter);

//            itemViewHolder.cardItem.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    context.startActivity(new Intent(context, OTDetailActivityLoc.class)
//                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                            .putExtra("ITEM_DATA", mFilteredList.get(position)));
//                }
//            });
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
                    mFilteredList = checkDamData;
                } else {
                    try {
                        ArrayList<CheckDamData> filteredList = new ArrayList<>();
                        for (CheckDamData checkDamData : checkDamData) {
                            if (!TextUtils.isEmpty(checkDamData.getTankName()) && checkDamData.getTankName().toLowerCase().contains(charString.toLowerCase())
                                    ||
                                    !TextUtils.isEmpty(String.valueOf(checkDamData.getTankId())) && String.valueOf(checkDamData.getTankId()).toLowerCase().contains(charString.toLowerCase())
                                    ||
                                    !TextUtils.isEmpty(String.valueOf(checkDamData.getTankCode())) && String.valueOf(checkDamData.getTankCode()).toLowerCase().contains(charString.toLowerCase())
                                    ||
                                    !TextUtils.isEmpty(checkDamData.getCircleName()) && checkDamData.getCircleName().toLowerCase().contains(charString.toLowerCase())
                                    ||
                                    !TextUtils.isEmpty(checkDamData.getSectionName()) && checkDamData.getSectionName().toLowerCase().contains(charString.toLowerCase())
                                    ||
                                    !TextUtils.isEmpty(checkDamData.getUnitName()) && checkDamData.getUnitName().toLowerCase().contains(charString.toLowerCase())
                                    ||
                                    !TextUtils.isEmpty(checkDamData.getVillage()) && checkDamData.getVillage().toLowerCase().contains(charString.toLowerCase())
                                    ||
                                    !TextUtils.isEmpty(checkDamData.getMandal()) && checkDamData.getMandal().toLowerCase().contains(charString.toLowerCase())
                                    ||
                                    !TextUtils.isEmpty(checkDamData.getDistrict()) && checkDamData.getDistrict().toLowerCase().contains(charString.toLowerCase())
                                    ||
                                    !TextUtils.isEmpty(checkDamData.getSubDivisionName()) && checkDamData.getSubDivisionName().toLowerCase().contains(charString.toLowerCase())
                                    ||
                                    !TextUtils.isEmpty(checkDamData.getDivisionName()) && checkDamData.getDivisionName().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(checkDamData);
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
                mFilteredList = (ArrayList<CheckDamData>) filterResults.values;
                notifyDataSetChanged();

                getFilteredData();
            }
        };
    }

    public List<CheckDamData> getFilteredData() {
        return mFilteredList;
    }


    public void  setFilteredData(ArrayList<CheckDamData> checkDamData) {
        mFilteredList = checkDamData;
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
