package cgg.gov.in.icadworks.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cgg.gov.in.icadworks.R;
import cgg.gov.in.icadworks.interfaces.ReportView;
import cgg.gov.in.icadworks.model.response.login.EmployeeDetailss;
import cgg.gov.in.icadworks.model.response.report.ReportResponse;
import cgg.gov.in.icadworks.presenter.ReportPresenter;
import cgg.gov.in.icadworks.util.ConnectionDetector;
import cgg.gov.in.icadworks.util.Utilities;

import static android.content.Context.MODE_PRIVATE;

public class ReportFragment extends Fragment implements ReportView {
    SharedPreferences sharedPreferences;

    @BindView(R.id.progress)
    ProgressBar progress;
    Unbinder unbinder;

    private ReportPresenter reportPresenter;
    private String defUsername, defUserPwd;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.report_fragment, container, false);
        progress.setVisibility(View.VISIBLE);
        unbinder = ButterKnife.bind(this, view);

        reportPresenter = new ReportPresenter();
        reportPresenter.attachView(this);

        EmployeeDetailss employeeDetailss = null;
        try {
            Gson gson = new Gson();
            sharedPreferences = getActivity().getSharedPreferences("APP_PREF", MODE_PRIVATE);
            String string = sharedPreferences.getString("LOGIN_DATA", "");
            defUsername = sharedPreferences.getString("DEFAULT_USER_NAME", "");
            defUserPwd = sharedPreferences.getString("DEFAULT_USER_PWD", "");
            employeeDetailss = gson.fromJson(string, EmployeeDetailss.class);
            if (employeeDetailss == null) {
                Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.something), false);
            }
        } catch (Exception e) {
            Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.something), false);
            e.printStackTrace();
        }

        if (ConnectionDetector.isConnectedToInternet(getActivity())) {
            if (employeeDetailss != null) {
                progress.setVisibility(View.VISIBLE);
//                reportPresenter.getDashboardReport(employeeDetailss.getEmployeeDetail().getSectionId(),
//                        employeeDetailss.getEmployeeDetail().getSubdivisionId(),
//                        employeeDetailss.getEmployeeDetail().getDivisionId(),
//                        employeeDetailss.getEmployeeDetail().getCircleId(),
//                        employeeDetailss.getEmployeeDetail().getUnitId(),
//                        defUsername,
//                        defUserPwd);
            } else {
                Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.something), false);
            }
        } else {
            Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.please_check_internet), false);
        }

        return view;
    }


    @Override
    public void getReportResponse(ReportResponse reportResponse) {
        progress.setVisibility(View.GONE);
        try {
            if (reportResponse != null) {
                if (reportResponse.getStatusCode() == 200 && reportResponse.getData() != null && reportResponse.getData().size() > 0) {

                }else if(reportResponse.getStatus()!=null && reportResponse.getStatus()==404){
                    Utilities.showCustomNetworkAlert(getActivity(), reportResponse.getTag(), false);
                }else {
                    Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.server), false);
                }
            } else {
                Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.server), false);
            }
        } catch (Exception e) {
            Utilities.showCustomNetworkAlert(getActivity(), getResources().getString(R.string.something), false);
            e.printStackTrace();
        }

    }

    @Override
    public void showMessage(int stringId) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showProgressIndicator(Boolean show) {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
