package cgg.gov.in.icadworks.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.custom.CustomFontTextView;


/**
 * Created by lenovo on 03-06-2019.
 */

public class ConWiseFragment extends Fragment {

    @BindView(R.id.projectRV)
    RecyclerView projectRV;
    @BindView(R.id.simpleSwipeRefreshLayout)
    SwipeRefreshLayout simpleSwipeRefreshLayout;
    @BindView(R.id.emptyTV)
    CustomFontTextView emptyTV;
    @BindView(R.id.switchView)
    FloatingActionButton switchView;
    @BindView(R.id.progress)
    ProgressBar progress;
    Unbinder unbinder;
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
    @BindView(R.id.ff)
    CustomFontTextView ff;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_con_wise, container, false);
        unbinder = ButterKnife.bind(this, view);

        Toast.makeText(getActivity(), "Con Wise", Toast.LENGTH_SHORT).show();

        ff.setText("Con COUNT");

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
