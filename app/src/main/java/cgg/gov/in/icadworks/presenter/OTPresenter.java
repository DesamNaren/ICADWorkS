package cgg.gov.in.icadworks.presenter;


import cgg.gov.in.icadworks.application.ICADApplication;
import cgg.gov.in.icadworks.base.BasePresenter;
import cgg.gov.in.icadworks.interfaces.OTView;
import cgg.gov.in.icadworks.model.response.checkdam.CheckDamResponse;
import cgg.gov.in.icadworks.model.response.itemStatus.ItemStatusData;
import cgg.gov.in.icadworks.model.response.itemStatus.ItemStatusResponse;
import cgg.gov.in.icadworks.model.response.ot.OTData;
import cgg.gov.in.icadworks.model.response.ot.OTResponse;
import cgg.gov.in.icadworks.model.response.ot.OTStatusData;
import cgg.gov.in.icadworks.model.response.report.ReportResponse;
import cgg.gov.in.icadworks.network.ICADService;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;


public class OTPresenter implements BasePresenter<OTView> {

    private OTView otView;
    private Subscription subscription;
    OTResponse otResponse;
    CheckDamResponse checkDamResponse;
    ReportResponse reportResponse;

    @Override
    public void attachView(OTView view) {
        this.otView = view;
    }

    @Override
    public void detachView() {
        this.otView = null;
        if (subscription != null) subscription.unsubscribe();
    }

    public void getOTData(String sectionID, String subDivID, String divID, String cirID,
                          String unitID, String strType, String userName, String userPwd) {
        try {
            ICADApplication application = ICADApplication.get(otView.getContext());
            ICADService gitHubService = application.getDBService(2);

            subscription = gitHubService.getOTRes(sectionID,subDivID,divID,cirID,unitID,strType,userName,userPwd)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(application.defaultSubscribeScheduler())
                    .subscribe(new Subscriber<OTResponse>() {
                        @Override
                        public void onCompleted() {
                            otView.getOTResponse(otResponse);
                        }

                        @Override
                        public void onError(Throwable error) {
                            otView.getOTResponse(otResponse);
                        }

                        @Override
                        public void onNext(OTResponse otResponse) {
                            OTPresenter.this.otResponse = otResponse;
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
            otView.getOTResponse(otResponse);
        }
    }

    public void getCDData(String sectionID, String subDivID, String divID, String cirID,
                          String unitID, String userName, String userPwd) {
        try {
            ICADApplication application = ICADApplication.get(otView.getContext());
            ICADService gitHubService = application.getDBService(2);

            subscription = gitHubService.getCDRes(sectionID,subDivID,divID,cirID,unitID,userName,userPwd)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(application.defaultSubscribeScheduler())
                    .subscribe(new Subscriber<CheckDamResponse>() {
                        @Override
                        public void onCompleted() {
                            otView.getCheckDamResponse(checkDamResponse);
                        }

                        @Override
                        public void onError(Throwable error) {
                            otView.getCheckDamResponse(checkDamResponse);
                        }

                        @Override
                        public void onNext(CheckDamResponse checkDamResponse) {
                            OTPresenter.this.checkDamResponse = checkDamResponse;
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
            otView.getOTResponse(otResponse);
        }
    }

    public void getDashboardReport(String sectionID, String subDivID, String divID, String cirID,
                                   String unitID, String userName, String userPwd) {
        try {
            ICADApplication application = ICADApplication.get(otView.getContext());
            ICADService gitHubService = application.getDBService(2);

            subscription = gitHubService.getDashboardReportRes(sectionID,subDivID,divID,cirID,unitID,userName,userPwd)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(application.defaultSubscribeScheduler())
                    .subscribe(new Subscriber<ReportResponse>() {
                        @Override
                        public void onCompleted() {
                            otView.getReportResponse(reportResponse);
                        }

                        @Override
                        public void onError(Throwable error) {
                            otView.getReportResponse(reportResponse);
                        }

                        @Override
                        public void onNext(ReportResponse reportResponse) {
                            OTPresenter.this.reportResponse = reportResponse;
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
            otView.getReportResponse(reportResponse);
        }
    }
}
