package cgg.gov.in.icadworks.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.model.response.ot.OTData;
import cgg.gov.in.icadworks.model.response.ot.OTStatusData;

public class DashboardSubAdapter extends RecyclerView.Adapter<DashboardSubAdapter.ItemViewHolder> {

    private OTData otResponse;
    private Context context;

    public DashboardSubAdapter(OTData otResponse, Context context) {
        this.otResponse = otResponse;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sub_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, final int position) {
        try {

            for(int x=0;x<otResponse.getGetItemStatusData().size();x++){
                if(otResponse.getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("1")
                && otResponse.getGetItemStatusData().get(x).getStatusId().contains("1")){
                    itemViewHolder.foundationTV.setBackgroundColor(Color.parseColor("#ee3738"));
                }

                if(otResponse.getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("1")
                        && otResponse.getGetItemStatusData().get(x).getStatusId().contains("2")){
                    itemViewHolder.foundationTV.setBackgroundColor(Color.parseColor("#FF7F00"));
                }


                if(otResponse.getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("1")
                        && otResponse.getGetItemStatusData().get(x).getStatusId().contains("3")){
                    itemViewHolder.foundationTV.setBackgroundColor(Color.parseColor("#008000"));
                }

                if(otResponse.getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("2")
                        && otResponse.getGetItemStatusData().get(x).getStatusId().contains("1")){
                    itemViewHolder.superTV.setBackgroundColor(Color.parseColor("#ee3738"));
                }

                if(otResponse.getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("2")
                        && otResponse.getGetItemStatusData().get(x).getStatusId().contains("2")){
                    itemViewHolder.superTV.setBackgroundColor(Color.parseColor("#FF7F00"));
                }

                if(otResponse.getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("2")
                        && otResponse.getGetItemStatusData().get(x).getStatusId().contains("3")){
                    itemViewHolder.superTV.setBackgroundColor(Color.parseColor("#008000"));
                }

                if(otResponse.getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("3")
                        && otResponse.getGetItemStatusData().get(x).getStatusId().contains("1")){
                    itemViewHolder.shutterTV.setBackgroundColor(Color.parseColor("#ee3738"));
                }

                if(otResponse.getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("3")
                        && otResponse.getGetItemStatusData().get(x).getStatusId().contains("2")){
                    itemViewHolder.shutterTV.setBackgroundColor(Color.parseColor("#FF7F00"));
                }

                if(otResponse.getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("3")
                        && otResponse.getGetItemStatusData().get(x).getStatusId().contains("3")){
                    itemViewHolder.shutterTV.setBackgroundColor(Color.parseColor("#008000"));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return otResponse == null ? 0 : 1;
    }

    @OnClick(R.id.cardItem)
    public void onViewClicked() {

    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.foundationTV)
        CustomFontTextView foundationTV;
        @BindView(R.id.superTV)
        CustomFontTextView superTV;
        @BindView(R.id.shutterTV)
        CustomFontTextView shutterTV;

        ItemViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
