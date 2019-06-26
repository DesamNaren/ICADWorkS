package cgg.gov.in.icadworks.model.response.checkdam;


import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckDamResponse {

    @SerializedName("data")
    @Expose
    private ArrayList<CheckDamData> data = null;
    @SerializedName("abstractReport")
    @Expose
    private ArrayList<CDAbstractReport> abstractReport = null;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;


    @SerializedName("tag")
    @Expose
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public ArrayList<CheckDamData> getData() {
        return data;
    }

    public void setData(ArrayList<CheckDamData> data) {
        this.data = data;
    }

    public List<CDAbstractReport> getAbstractReport() {
        return abstractReport;
    }

    public void setAbstractReport(ArrayList<CDAbstractReport> abstractReport) {
        this.abstractReport = abstractReport;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

}