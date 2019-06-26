package cgg.gov.in.icadworks.model.response.report;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportResponse {

    @SerializedName("data")
    @Expose
    private List<ReportData> data = null;
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

    public List<ReportData> getData() {
        return data;
    }

    public void setData(List<ReportData> data) {
        this.data = data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

}
