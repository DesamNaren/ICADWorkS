package cgg.gov.in.icadworks.model.response.checkdam.office;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CDOfficeResponse implements Parcelable {

    @SerializedName("data")
    @Expose
    private List<CDOfficeData> cdOfficeData = null;
    @SerializedName("abstractReport")
    @Expose
    private List<CDAbstractReport> abstractReport = null;
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

    public List<CDOfficeData> getCdOfficeData() {
        return cdOfficeData;
    }

    public void setCdOfficeData(List<CDOfficeData> cdOfficeData) {
        this.cdOfficeData = cdOfficeData;
    }

    public List<CDAbstractReport> getAbstractReport() {
        return abstractReport;
    }

    public void setAbstractReport(List<CDAbstractReport> abstractReport) {
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
        dest.writeList(this.cdOfficeData);
        dest.writeList(this.abstractReport);
        dest.writeValue(this.statusCode);
        dest.writeString(this.tag);
        dest.writeValue(this.status);
    }

    public CDOfficeResponse() {
    }

    protected CDOfficeResponse(Parcel in) {
        this.cdOfficeData = new ArrayList<CDOfficeData>();
        in.readList(this.cdOfficeData, CDOfficeData.class.getClassLoader());
        this.abstractReport = new ArrayList<CDAbstractReport>();
        in.readList(this.abstractReport, CDAbstractReport.class.getClassLoader());
        this.statusCode = (Integer) in.readValue(Integer.class.getClassLoader());
        this.tag = in.readString();
        this.status = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<CDOfficeResponse> CREATOR = new Parcelable.Creator<CDOfficeResponse>() {
        @Override
        public CDOfficeResponse createFromParcel(Parcel source) {
            return new CDOfficeResponse(source);
        }

        @Override
        public CDOfficeResponse[] newArray(int size) {
            return new CDOfficeResponse[size];
        }
    };
}