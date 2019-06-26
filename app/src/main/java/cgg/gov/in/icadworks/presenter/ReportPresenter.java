package cgg.gov.in.icadworks.presenter;


import cgg.gov.in.icadworks.application.ICADApplication;
import cgg.gov.in.icadworks.base.BasePresenter;
import cgg.gov.in.icadworks.interfaces.ReportView;
import cgg.gov.in.icadworks.model.response.report.ReportResponse;
import cgg.gov.in.icadworks.network.ICADService;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class ReportPresenter implements BasePresenter<ReportView> {

    private ReportView reportView;
    private Subscription subscription;
    ReportResponse reportResponse;

    @Override
    public void attachView(ReportView view) {
        this.reportView = view;
    }

    @Override
    public void detachView() {
        this.reportView = null;
        if (subscription != null) subscription.unsubscribe();
    }

    public void getDashboardReport(String sectionID, String subDivID, String divID, String cirID,
                                   String unitID, String userName, String userPwd) {
        try {
            ICADApplication application = ICADApplication.get(reportView.getContext());
            ICADService gitHubService = application.getDBService(2);

            subscription = gitHubService.getDashboardReportRes(sectionID,subDivID,divID,cirID,unitID,userName,userPwd)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(application.defaultSubscribeScheduler())
                    .subscribe(new Subscriber<ReportResponse>() {
                        @Override
                        public void onCompleted() {
                            reportView.getReportResponse(reportResponse);
                        }

                        @Override
                        public void onError(Throwable error) {
                            reportView.getReportResponse(reportResponse);
                        }

                        @Override
                        public void onNext(ReportResponse reportResponse) {
                            ReportPresenter.this.reportResponse = reportResponse;
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
            reportView.getReportResponse(reportResponse);
        }
    }
}
