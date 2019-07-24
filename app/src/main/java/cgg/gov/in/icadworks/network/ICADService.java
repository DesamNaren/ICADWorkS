package cgg.gov.in.icadworks.network;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import cgg.gov.in.icadworks.model.request.OTUpdateRequest;
import cgg.gov.in.icadworks.model.response.checkdam.CheckDamResponse;
import cgg.gov.in.icadworks.model.response.checkdam.office.CheckDamOfficeResponse;
import cgg.gov.in.icadworks.model.response.ot.OTUpdateResponse;
import cgg.gov.in.icadworks.model.response.itemStatus.ItemStatusResponse;
import cgg.gov.in.icadworks.model.response.items.WorkItemsResponse;
import cgg.gov.in.icadworks.model.response.login.EmployeeDetailss;
import cgg.gov.in.icadworks.model.response.ot.OTResponse;
import cgg.gov.in.icadworks.model.response.report.ReportResponse;
import cgg.gov.in.icadworks.model.response.version.VersionResponse;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ICADService {

    class Factory {
        public static ICADService create(Context contextOfApplication, int val) {
            String BASE_URL;
            Converter.Factory factory;
            if (val == 1) {
                BASE_URL = ICADURL.ICAD_BASE_URL;
                factory = SimpleXmlConverterFactory.create();
            } else {
                BASE_URL = ICADURL.ICAD_REST_BASE_URL;
                factory = GsonConverterFactory.create();
            }

            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(factory)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
            return retrofit.create(ICADService.class);
        }
    }

    @GET("validateEmployeeIrrApp")
    Observable<EmployeeDetailss> getLoginRes(@Query("userName") String username,
                                             @Query("password") String password);

    @GET("getItemStatusData")
    Observable<ItemStatusResponse> getItemStatusRes(@Query("user") String username,
                                               @Query("password") String password);

    @GET("getWorkItemsData")
    Observable<WorkItemsResponse> getWorkItemRes(@Query("user") String username,
                                                 @Query("password") String password);

    @GET("getStructureMasterData")
    Observable<OTResponse> getOTRes(@Query("secid") String secID,
                                    @Query("subdivid") String subDivID,
                                    @Query("divid") String divID,
                                    @Query("circid") String cirID,
                                    @Query("unitid") String unitID,
                                    @Query("strtype") String strType,
                                    @Query("user") String user,
                                    @Query("password") String password);

    @GET("getCheckDamsData")
    Observable<CheckDamResponse> getCDRes(@Query("secid") String secID,
                                          @Query("subdivid") String subDivID,
                                          @Query("divid") String divID,
                                          @Query("circid") String cirID,
                                          @Query("unitid") String unitID,
                                          @Query("user") String user,
                                          @Query("password") String password);

    @GET("getCheckDamsOfficeData")
    Observable<CheckDamOfficeResponse> getCDOfficeRes(@Query("secid") String secID,
                                                      @Query("subdivid") String subDivID,
                                                      @Query("divid") String divID,
                                                      @Query("circid") String cirID,
                                                      @Query("unitid") String unitID,
                                                      @Query("user") String user,
                                                      @Query("password") String password);

    @POST("updateItemsStatus")
    Observable<OTUpdateResponse> submitOTData(@Body OTUpdateRequest otUpdateRequest, @Header("Content-Type") String content_type);


    @GET("getAppVersion")
    Observable<VersionResponse> getAppVersion(@Query("user") String user,
                                         @Query("password") String pwd);

    @GET("getDashboardReport")
    Observable<ReportResponse> getDashboardReportRes(@Query("secid") String secID,
                                                     @Query("subdivid") String subDivID,
                                                     @Query("divid") String divID,
                                                     @Query("circid") String cirID,
                                                     @Query("unitid") String unitID,
                                                     @Query("user") String user,
                                                     @Query("password") String password);
}



