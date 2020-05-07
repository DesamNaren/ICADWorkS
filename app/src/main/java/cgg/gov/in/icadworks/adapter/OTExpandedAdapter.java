package cgg.gov.in.icadworks.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.custom.CustomFontTextView;
import cgg.gov.in.icadworks.model.response.report.ReportData;
import cgg.gov.in.icadworks.util.Utilities;

public class OTExpandedAdapter extends RecyclerView.Adapter<OTExpandedAdapter.ItemViewHolder> {

    private ArrayList<ReportData> otResponse;
    private Context context;
    private Activity activity;

    public OTExpandedAdapter(ArrayList<ReportData> otResponse, Context context, Activity activity) {
        this.otResponse = otResponse;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ot_expanded_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder itemViewHolder, final int position) {
        try {
            itemViewHolder.title.setText(otResponse.get(position).getProjectName());
            itemViewHolder.tanksCount.setText(String.valueOf(otResponse.get(position).getTanks()+"/"+otResponse.get(position).getTanks_to_be_fed()));
            itemViewHolder.tsCount.setText(String.valueOf(otResponse.get(position).getTechsanctions())
                    +
                    " [ "
                    + String.valueOf(otResponse.get(position).getTechsancots())
                    + " ]");
            itemViewHolder.tenCount.setText(String.valueOf(otResponse.get(position).getTenders())
                    +" [ " + otResponse.get(position).getNominations() + " ]");
            itemViewHolder.aggCount.setText(String.valueOf(otResponse.get(position).getAgreements()));
            itemViewHolder.notStCount.setText(String.valueOf(otResponse.get(position).getNotStarted()));
            itemViewHolder.inProCount.setText(String.valueOf(otResponse.get(position).getInProgress()));
            itemViewHolder.comCount.setText(String.valueOf(otResponse.get(position).getCompleted()));
            itemViewHolder.totalCount.setText(String.valueOf(otResponse.get(position).getTotal()));

            itemViewHolder.shareIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LinearLayout abstractView =itemViewHolder.dataLl;
                    Utilities.takeSCImage(activity, abstractView ,
                            otResponse.get(position).getProjectName() + "_District Data");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {
        return otResponse == null ? 0 : otResponse.size();
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
