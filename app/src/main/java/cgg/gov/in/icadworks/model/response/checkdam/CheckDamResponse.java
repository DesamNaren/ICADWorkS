package cgg.gov.in.icadworks.model.response.checkdam;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckDamResponse {

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

}
