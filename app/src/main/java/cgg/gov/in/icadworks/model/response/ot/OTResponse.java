package cgg.gov.in.icadworks.model.response.ot;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OTResponse implements Parcelable {

    @SerializedName("data")
    @Expose
    private ArrayList<OTData> data = null;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;

    @SerializedName("tag")
    @Expose
    private String tag;

    @SerializedName("abstractReport")
    @Expose
    private List<AbstractReport> abstractReport = null;

    public OTResponse() {
    }


    public ArrayList<OTData> getData() {
        return data;
    }

    public void setData(ArrayList<OTData> data) {
        this.data = data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<AbstractReport> getAbstractReport() {
        return abstractReport;
    }

    public void setAbstractReport(List<AbstractReport> abstractReport) {
        this.abstractReport = abstractReport;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.data);
        dest.writeValue(this.statusCode);
        dest.writeString(this.tag);
        dest.writeList(this.abstractReport);
    }

    protected OTResponse(Parcel in) {
        this.data = in.createTypedArrayList(OTData.CREATOR);
        this.statusCode = (Integer) in.readValue(Integer.class.getClassLoader());
        this.tag = in.readString();
        this.abstractReport = new ArrayList<AbstractReport>();
        in.readList(this.abstractReport, AbstractReport.class.getClassLoader());
    }

    public static final Creator<OTResponse> CREATOR = new Creator<OTResponse>() {
        @Override
        public OTResponse createFromParcel(Parcel source) {
            return new OTResponse(source);
        }

        @Override
        public OTResponse[] newArray(int size) {
            return new OTResponse[size];
        }
    };
}