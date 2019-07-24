package cgg.gov.in.icadworks.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.model.response.checkdam.CheckDamData;
import cgg.gov.in.icadworks.model.response.ot.OTData;

public class CheckDamSubAdapter extends RecyclerView.Adapter<CheckDamSubAdapter.ItemViewHolder> {

    private CheckDamData checkDamData;
    private Context context;

    public CheckDamSubAdapter(CheckDamData checkDamData, Context context) {
        this.checkDamData = checkDamData;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cd_sub_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, final int position) {
        try {

            for(int x=0;x<checkDamData.getGetItemStatusData().size();x++){
                if(checkDamData.getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("1")
                && checkDamData.getGetItemStatusData().get(x).getStatusId().contains("1")){
                    itemViewHolder.foundationTV.setBackgroundColor(Color.parseColor("#ee3738"));
                }

                if(checkDamData.getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("1")
                        && checkDamData.getGetItemStatusData().get(x).getStatusId().contains("2")){
                    itemViewHolder.foundationTV.setBackgroundColor(Color.parseColor("#FF7F00"));
                }


                if(checkDamData.getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("1")
                        && checkDamData.getGetItemStatusData().get(x).getStatusId().contains("3")){
                    itemViewHolder.foundationTV.setBackgroundColor(Color.parseColor("#008000"));
                }

                if(checkDamData.getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("2")
                        && checkDamData.getGetItemStatusData().get(x).getStatusId().contains("1")){
                    itemViewHolder.superTV.setBackgroundColor(Color.parseColor("#ee3738"));
                }

                if(checkDamData.getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("2")
                        && checkDamData.getGetItemStatusData().get(x).getStatusId().contains("2")){
                    itemViewHolder.superTV.setBackgroundColor(Color.parseColor("#FF7F00"));
                }

                if(checkDamData.getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("2")
                        && checkDamData.getGetItemStatusData().get(x).getStatusId().contains("3")){
                    itemViewHolder.superTV.setBackgroundColor(Color.parseColor("#008000"));
                }

                if(checkDamData.getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("3")
                        && checkDamData.getGetItemStatusData().get(x).getStatusId().contains("1")){
                    itemViewHolder.shutterTV.setBackgroundColor(Color.parseColor("#ee3738"));
                }

                if(checkDamData.getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("3")
                        && checkDamData.getGetItemStatusData().get(x).getStatusId().contains("2")){
                    itemViewHolder.shutterTV.setBackgroundColor(Color.parseColor("#FF7F00"));
                }

                if(checkDamData.getGetItemStatusData().get(x).getIrrWorkId().equalsIgnoreCase("3")
                        && checkDamData.getGetItemStatusData().get(x).getStatusId().contains("3")){
                    itemViewHolder.shutterTV.setBackgroundColor(Color.parseColor("#008000"));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return checkDamData == null ? 0 : 1;
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
