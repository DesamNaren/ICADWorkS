//package cgg.gov.in.icadworks.adapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.CardView;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Filter;
//import android.widget.Filterable;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import cgg.gov.in.icadworks.R;
//import cgg.gov.in.icadworks.custom.CustomFontTextView;
//import cgg.gov.in.icadworks.model.request.OTUpdateRequest;
//import cgg.gov.in.icadworks.view.UploadDetailActivityLoc;
//
//public class LocalDBAdapter extends RecyclerView.Adapter<LocalDBAdapter.ItemViewHolder> implements Filterable {
//
//    private ArrayList<OTUpdateRequest> otResponse;
//    private ArrayList<OTUpdateRequest> mFilteredList;
//    private Context context;
//
//    public LocalDBAdapter(ArrayList<OTUpdateRequest> otResponse, Context context) {
//        this.otResponse = otResponse;
//        mFilteredList = otResponse;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.local_dashboard_item, viewGroup, false);
//        return new ItemViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, final int position) {
//        try {
//            itemViewHolder.chainID.setText(mFilteredList.get(position).getStructureId());
//            itemViewHolder.strNo.setText(mFilteredList.get(position).getStructureNo());
//
//            itemViewHolder.proceed.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    context.startActivity(new Intent(context, UploadDetailActivityLoc.class)
//                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                            .putExtra("LOCAL_DATA", mFilteredList.get(position)));
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return mFilteredList == null ? 0 : mFilteredList.size();
//    }
//
//    @OnClick(R.id.cardItem)
//    public void onViewClicked() {
//
//    }
//
//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//                String charString = charSequence.toString();
//                if (charString.isEmpty()) {
//                    mFilteredList = otResponse;
//                } else {
//                    try {
//                        ArrayList<OTUpdateRequest> filteredList = new ArrayList<>();
//                        for (OTUpdateRequest otData : otResponse) {
//                            if (!TextUtils.isEmpty(otData.getStructureId()) && otData.getStructureId().toLowerCase().contains(charString.toLowerCase())
//                                    ||
//                                    !TextUtils.isEmpty(otData.getStructureNo()) && otData.getStructureNo().toLowerCase().contains(charString.toLowerCase())) {
//                                filteredList.add(otData);
//                            }
//                        }
//                        mFilteredList = filteredList;
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = mFilteredList;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                mFilteredList = (ArrayList<OTUpdateRequest>) filterResults.values;
//                notifyDataSetChanged();
//
//                getFilteredData();
//            }
//        };
//    }
//
//    public ArrayList<OTUpdateRequest> getFilteredData() {
//        return mFilteredList;
//    }
//
//    public void setFilteredData(ArrayList<OTUpdateRequest> otData) {
//        mFilteredList = otData;
//    }
//
//
//    class ItemViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.chainID)
//        CustomFontTextView chainID;
//        @BindView(R.id.strNo)
//        CustomFontTextView strNo;
//        @BindView(R.id.proceed)
//        CustomFontTextView proceed;
//
//        ItemViewHolder(@NonNull View view) {
//            super(view);
//            ButterKnife.bind(this, view);
//        }
//    }
//}
