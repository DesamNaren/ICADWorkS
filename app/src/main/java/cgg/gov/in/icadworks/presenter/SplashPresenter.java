package cgg.gov.in.icadworks.presenter;


import cgg.gov.in.icadworks.application.ICADApplication;
import cgg.gov.in.icadworks.base.BasePresenter;
import cgg.gov.in.icadworks.interfaces.SplashView;
import cgg.gov.in.icadworks.model.response.ot.OTResponse;
import cgg.gov.in.icadworks.model.response.version.VersionResponse;
import cgg.gov.in.icadworks.network.ICADService;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;


public class SplashPresenter implements BasePresenter<SplashView> {

    private SplashView splashView;
    private Subscription subscription;
    VersionResponse versionResponse;

    @Override
    public void attachView(SplashView view) {
        this.splashView = view;
    }

    @Override
    public void detachView() {
        this.splashView = null;
        if (subscription != null) subscription.unsubscribe();
    }

    public void getAppVersion(String userName, String userPwd) {
        try {
//            splashView.showProgressIndicator(true);
            ICADApplication application = ICADApplication.get(splashView.getContext());
            ICADService gitHubService = application.getDBService(2);

            subscription = gitHubService.getAppVersion(userName,userPwd)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(application.defaultSubscribeScheduler())
                    .subscribe(new Subscriber<VersionResponse>() {
                        @Override
                        public void onCompleted() {
//                            splashView.showProgressIndicator(false);
                            splashView.getAppVersionResponse(versionResponse);
                        }

                        @Override
                        public void onError(Throwable error) {
//                            splashView.showProgressIndicator(false);
                            splashView.getAppVersionResponse(versionResponse);
                        }

                        @Override
                        public void onNext(VersionResponse versionResponse) {
                            SplashPresenter.this.versionResponse = versionResponse;
                        }
                    });

        } catch (Exception e) {
//            splashView.showProgressIndicator(false);
            e.printStackTrace();
            splashView.getAppVersionResponse(versionResponse);
        }
    }
}
