package cgg.gov.in.icadworks.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import cgg.gov.in.icadworks.BuildConfig;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.util.ConnectionDetector;
import cgg.gov.in.icadworks.util.Utilities;
import cgg.gov.in.icadworks.view.DashboardActivity;

public class UserManualFragment extends Fragment {

    ProgressBar progressBar;
    WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_manual_fragment, container, false);
        setHasOptionsMenu(true);
        webView = view.findViewById(R.id.webview);
        progressBar = view.findViewById(R.id.progress);
        if (ConnectionDetector.isConnectedToInternet(getActivity())) {
            progressBar.setVisibility(View.VISIBLE);
            Utilities.loadWebView(getActivity(), progressBar, webView, BuildConfig.USER_MANUAL_URL);
        }else {
            Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.no_internet), false);
        }
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onPrepareOptionsMenu(menu);
        inflater.inflate(R.menu.search_menu, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                Utilities.showCustomNetworkAlertLogout(getActivity(), "Do you want logout from app?");
                return true;
            default:
                break;
        }

        return false;
    }


    @Override
    public void onResume() {
        super.onResume();
        try {
            ((DashboardActivity)getActivity()).getSupportActionBar().setTitle("User Manual");
            ((DashboardActivity)getActivity()).getSupportActionBar().setSubtitle("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
