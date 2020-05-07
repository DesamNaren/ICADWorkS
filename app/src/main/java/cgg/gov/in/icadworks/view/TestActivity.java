package cgg.gov.in.icadworks.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.fragment.BaseFragment;
import cgg.gov.in.icadworks.fragment.OTMapFragment;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        Fragment f = new BaseFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, f);
        transaction.commit();
    }
}
