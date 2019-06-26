package cgg.gov.in.icadworks.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import cgg.gov.in.icadworks.network.ICADService;
import rx.Scheduler;
import rx.schedulers.Schedulers;


public class ICADApplication extends Application {

    public ICADService dbService;
    //    private Retrofit retrofit;
    private Scheduler defaultSubscribeScheduler;
    public static Context contextOfApplication;

    public static ICADApplication get(Context context) {
        return (ICADApplication) context.getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        contextOfApplication = getApplicationContext();
        MultiDex.install(this);
        // Initialize Realm
//        Realm.init(getApplicationContext());
//        /************************************************
//         *REALM DATABASE
//         ******************************************** */
//        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
//                .name(Constants.DATABASE_NAME)
//                .schemaVersion(DatabaseMigration.VERSION)
//                .migration(new DatabaseMigration())
//                //.deleteRealmIfMigrationNeeded()
//                .build();
//
//        Realm.setDefaultConfiguration(realmConfiguration);

    }

    public static Context getContextOfApplication() {
        return contextOfApplication;
    }

    public ICADService getDBService(int val) {
//        if (dbService == null) {
        dbService = ICADService.Factory.create(getContextOfApplication(), val);
//        }
        return dbService;
    }

    //For setting mocks during testing
    public void setDBService(ICADService dbService) {
        this.dbService = dbService;
    }

    public Scheduler defaultSubscribeScheduler() {
        if (defaultSubscribeScheduler == null) {
            defaultSubscribeScheduler = Schedulers.io();
        }
        return defaultSubscribeScheduler;
    }

    //User to change scheduler from tests
    public void setDefaultSubscribeScheduler(Scheduler scheduler) {
        this.defaultSubscribeScheduler = scheduler;
    }

}
