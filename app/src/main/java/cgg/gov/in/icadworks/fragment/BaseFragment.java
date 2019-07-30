package cgg.gov.in.icadworks.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cgg.gov.in.icadworks.R;


public class BaseFragment extends OTMapFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ot_structure_fragment, container, false);
        return view;
    }
}
