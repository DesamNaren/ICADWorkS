package cgg.gov.in.icadworks.presenter;


import com.google.gson.Gson;

import cgg.gov.in.icadworks.application.ICADApplication;
import cgg.gov.in.icadworks.base.BasePresenter;
import cgg.gov.in.icadworks.interfaces.UpdateOTView;
import cgg.gov.in.icadworks.model.request.OTUpdateRequest;
import cgg.gov.in.icadworks.model.response.ot.OTUpdateResponse;
import cgg.gov.in.icadworks.network.ICADService;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;


public class UpdateOTPresenter implements BasePresenter<UpdateOTView> {

    private UpdateOTView updateView;
    private Subscription subscription;
    OTUpdateResponse otUpdateResponse;

    @Override
    public void attachView(UpdateOTView view) {
        this.updateView = view;
    }

    @Override
    public void detachView() {
        this.updateView = null;
        if (subscription != null) subscription.unsubscribe();
    }

    public void submitOTData(OTUpdateRequest otUpdateRequest) {
        try {
//            updateView.showProgressIndicator(true);
            ICADApplication application = ICADApplication.get(updateView.getContext());
            ICADService gitHubService = application.getDBService(2);

            String string = new Gson().toJson(otUpdateRequest);

            subscription = gitHubService.submitOTData(otUpdateRequest, "application/json")
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(application.defaultSubscribeScheduler())
                    .subscribe(new Subscriber<OTUpdateResponse>() {
                        @Override
                        public void onCompleted() {
//                            updateView.showProgressIndicator(false);
                            updateView.getOTUpdateResponse(otUpdateResponse);
                        }

                        @Override
                        public void onError(Throwable error) {
//                            updateView.showProgressIndicator(false);
                            updateView.getOTUpdateResponse(otUpdateResponse);
                        }

                        @Override
                        public void onNext(OTUpdateResponse otUpdateResponse) {
                            UpdateOTPresenter.this.otUpdateResponse = otUpdateResponse;
                        }
                    });

        } catch (Exception e) {
//            updateView.showProgressIndicator(false);
            e.printStackTrace();
            updateView.getOTUpdateResponse(otUpdateResponse);
        }
    }
}
