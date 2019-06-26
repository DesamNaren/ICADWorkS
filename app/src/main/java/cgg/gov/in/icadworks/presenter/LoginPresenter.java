package cgg.gov.in.icadworks.presenter;


import cgg.gov.in.icadworks.application.ICADApplication;
import cgg.gov.in.icadworks.base.BasePresenter;
import cgg.gov.in.icadworks.interfaces.LoginView;
import cgg.gov.in.icadworks.model.response.itemStatus.ItemStatusResponse;
import cgg.gov.in.icadworks.model.response.items.WorkItemsResponse;
import cgg.gov.in.icadworks.model.response.login.EmployeeDetailss;
import cgg.gov.in.icadworks.network.ICADService;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;


public class LoginPresenter implements BasePresenter<LoginView> {

    private LoginView loginView;
    private Subscription subscription;
    EmployeeDetailss employeeDetailss;
    ItemStatusResponse itemStatusResponse;
    WorkItemsResponse workItemsResponse;

    @Override
    public void attachView(LoginView view) {
        this.loginView = view;
    }

    @Override
    public void detachView() {
        this.loginView = null;
        if (subscription != null) subscription.unsubscribe();
    }

    public void submitLogin(String userName, String password) {
        try {
            ICADApplication application = ICADApplication.get(loginView.getContext());
            ICADService gitHubService = application.getDBService(1);

            subscription = gitHubService.getLoginRes(userName, password)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(application.defaultSubscribeScheduler())
                    .subscribe(new Subscriber<EmployeeDetailss>() {
                        @Override
                        public void onCompleted() {
                            loginView.getLoginResponseSuccess(employeeDetailss);
                        }

                        @Override
                        public void onError(Throwable error) {
                            loginView.getLoginResponseSuccess(employeeDetailss);
                        }

                        @Override
                        public void onNext(EmployeeDetailss employeeDetailss) {
                            LoginPresenter.this.employeeDetailss = employeeDetailss;
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
            loginView.getLoginResponseSuccess(employeeDetailss);
        }
    }

    public void getItemStatus(String userName, String password) {
        try {
//            loginView.showProgressIndicator(true);
            ICADApplication application = ICADApplication.get(loginView.getContext());
            ICADService gitHubService = application.getDBService(2);

            subscription = gitHubService.getItemStatusRes(userName, password)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(application.defaultSubscribeScheduler())
                    .subscribe(new Subscriber<ItemStatusResponse>() {
                        @Override
                        public void onCompleted() {
//                            loginView.showProgressIndicator(false);
                            loginView.getItemStatusResponse(itemStatusResponse);
                        }

                        @Override
                        public void onError(Throwable error) {
//                            loginView.showProgressIndicator(false);
                            loginView.getItemStatusResponse(itemStatusResponse);
                        }

                        @Override
                        public void onNext(ItemStatusResponse itemStatusResponse) {
                            LoginPresenter.this.itemStatusResponse = itemStatusResponse;
                        }
                    });

        } catch (Exception e) {
//            loginView.showProgressIndicator(false);
            e.printStackTrace();
            loginView.getLoginResponseSuccess(employeeDetailss);
        }
    }

    public void getWorkItems(String userName, String password) {
        try {
            loginView.showProgressIndicator(true);
            ICADApplication application = ICADApplication.get(loginView.getContext());
            ICADService gitHubService = application.getDBService(2);

            subscription = gitHubService.getWorkItemRes(userName, password)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(application.defaultSubscribeScheduler())
                    .subscribe(new Subscriber<WorkItemsResponse>() {
                        @Override
                        public void onCompleted() {
                            loginView.showProgressIndicator(false);
                            loginView.getWorkItemsResponse(workItemsResponse);
                        }

                        @Override
                        public void onError(Throwable error) {
                            loginView.showProgressIndicator(false);
                            loginView.getWorkItemsResponse(workItemsResponse);
                        }

                        @Override
                        public void onNext(WorkItemsResponse workItemsResponse) {
                            LoginPresenter.this.workItemsResponse = workItemsResponse;
                        }
                    });

        } catch (Exception e) {
            loginView.showProgressIndicator(false);
            e.printStackTrace();
            loginView.getLoginResponseSuccess(employeeDetailss);
        }
    }
}
