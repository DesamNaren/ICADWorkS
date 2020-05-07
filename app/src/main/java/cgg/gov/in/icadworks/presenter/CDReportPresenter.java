package cgg.gov.in.icadworks.presenter;


import cgg.gov.in.icadworks.application.ICADApplication;
import cgg.gov.in.icadworks.base.BasePresenter;
import cgg.gov.in.icadworks.interfaces.CDReportView;
import cgg.gov.in.icadworks.model.response.checkdam.office.CDOfficeResponse;
import cgg.gov.in.icadworks.network.ICADService;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class CDReportPresenter implements BasePresenter<CDReportView> {

    private CDReportView reportView;
    private Subscription subscription;
    CDOfficeResponse cdOfficeResponse;

    @Override
    public void attachView(CDReportView view) {
        this.reportView = view;
    }

    @Override
    public void detachView() {
        this.reportView = null;
        if (subscription != null) subscription.unsubscribe();
    }

    public void getCDOfficeReportResponse(String sectionID, String subDivID, String divID, String cirID,
                                   String unitID, String userName, String userPwd) {
        try {
            ICADApplication application = ICADApplication.get(reportView.getContext());
            ICADService gitHubService = application.getDBService(2);

            subscription = gitHubService.getCDOfficeRes(sectionID,subDivID,divID,cirID,unitID,userName,userPwd)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(application.defaultSubscribeScheduler())
                    .subscribe(new Subscriber<CDOfficeResponse>() {
                        @Override
                        public void onCompleted() {
                            reportView.getCDOfficeReportResponse(cdOfficeResponse);
                        }

                        @Override
                        public void onError(Throwable error) {
                            reportView.getCDOfficeReportResponse(cdOfficeResponse);
                        }

                        @Override
                        public void onNext(CDOfficeResponse cdOfficeResponse) {
                            CDReportPresenter.this.cdOfficeResponse = cdOfficeResponse;
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
            reportView.getCDOfficeReportResponse(cdOfficeResponse);
        }
    }
}
