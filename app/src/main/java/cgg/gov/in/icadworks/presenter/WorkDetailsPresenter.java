package cgg.gov.in.icadworks.presenter;


import cgg.gov.in.icadworks.application.ICADApplication;
import cgg.gov.in.icadworks.base.BasePresenter;
import cgg.gov.in.icadworks.interfaces.OTView;
import cgg.gov.in.icadworks.interfaces.WorkDetailsView;
import cgg.gov.in.icadworks.model.response.checkdam.CheckDamResponse;
import cgg.gov.in.icadworks.model.response.checkdam.office.CDOfficeResponse;
import cgg.gov.in.icadworks.model.response.ot.OTResponse;
import cgg.gov.in.icadworks.model.response.report.ReportResponse;
import cgg.gov.in.icadworks.model.response.works.WorkDetailsResponse;
import cgg.gov.in.icadworks.network.ICADService;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;


public class WorkDetailsPresenter implements BasePresenter<WorkDetailsView> {

    private WorkDetailsView workDetailsView;
    private Subscription subscription;
    WorkDetailsResponse workDetailsResponse;
    CheckDamResponse checkDamResponse;
    CDOfficeResponse cdOfficeResponse;
    ReportResponse reportResponse;

    @Override
    public void attachView(WorkDetailsView view) {
        this.workDetailsView = view;
    }

    @Override
    public void detachView() {
        this.workDetailsView = null;
        if (subscription != null) subscription.unsubscribe();
    }

    public void getWorkDetails(String sectionID, String subDivID, String divID, String cirID,
                          String unitID, String strType, String userName, String userPwd) {
        try {
            ICADApplication application = ICADApplication.get(workDetailsView.getContext());
            ICADService gitHubService = application.getDBService(2);

            subscription = gitHubService.getWorkDetailsRes
                    (userName,userPwd,sectionID,subDivID,divID,cirID,unitID,strType)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(application.defaultSubscribeScheduler())
                    .subscribe(new Subscriber<WorkDetailsResponse>() {
                        @Override
                        public void onCompleted() {
                            workDetailsView.getWorkDetailsResponse(workDetailsResponse);
                        }

                        @Override
                        public void onError(Throwable error) {
                            workDetailsView.getWorkDetailsResponse(workDetailsResponse);
                        }

                        @Override
                        public void onNext(WorkDetailsResponse workDetailsResponse) {
                            WorkDetailsPresenter.this.workDetailsResponse = workDetailsResponse;
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
            workDetailsView.getWorkDetailsResponse(workDetailsResponse);
        }
    }
}
