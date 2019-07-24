package cgg.gov.in.icadworks.model.response.checkdam;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckDamResponse implements Parcelable {

    @SerializedName("data")
    @Expose
    private List<CheckDamData> data = null;
    @SerializedName("abstractReport")
    @Expose
    private List<CheckDamAbstractReport> abstractReport = null;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;

    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("status")
    @Expose
    private Integer status;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<CheckDamData> getData() {
        return data;
    }

    public void setData(List<CheckDamData> data) {
        this.data = data;
    }

    public List<CheckDamAbstractReport> getAbstractReport() {
        return abstractReport;
    }

    public void setAbstractReport(List<CheckDamAbstractReport> abstractReport) {
        this.abstractReport = abstractReport;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.data);
        dest.writeList(this.abstractReport);
        dest.writeValue(this.statusCode);
        dest.writeString(this.tag);
        dest.writeValue(this.status);
    }

    public CheckDamResponse() {
    }

    protected CheckDamResponse(Parcel in) {
        this.data = new ArrayList<CheckDamData>();
        in.readList(this.data, CheckDamData.class.getClassLoader());
        this.abstractReport = new ArrayList<CheckDamAbstractReport>();
        in.readList(this.abstractReport, CheckDamAbstractReport.class.getClassLoader());
        this.statusCode = (Integer) in.readValue(Integer.class.getClassLoader());
        this.tag = in.readString();
        this.status = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<CheckDamResponse> CREATOR = new Parcelable.Creator<CheckDamResponse>() {
        @Override
        public CheckDamResponse createFromParcel(Parcel source) {
            return new CheckDamResponse(source);
        }

        @Override
        public CheckDamResponse[] newArray(int size) {
            return new CheckDamResponse[size];
        }
    };
}
