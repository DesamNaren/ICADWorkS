package cgg.gov.in.icadworks.model.response.checkdam.office;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import cgg.gov.in.icadworks.model.response.ot.AbstractReport;

public class CheckDamOfficeResponse {
    @SerializedName("data")
    @Expose
    private List<CheckDamOfficeDataItem> data = null;
    @SerializedName("abstractReport")
    @Expose
    private List<CheckDamOfficeAbstractReport> abstractReport = null;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;

    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("status")
    @Expose
    private Integer status;

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTag() {
        return tag;
    }

    public List<CheckDamOfficeDataItem> getData() {
        return data;
    }

    public void setData(List<CheckDamOfficeDataItem> data) {
        this.data = data;
    }

    public List<CheckDamOfficeAbstractReport> getAbstractReport() {
        return abstractReport;
    }

    public void setAbstractReport(List<CheckDamOfficeAbstractReport> abstractReport) {
        this.abstractReport = abstractReport;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

}
