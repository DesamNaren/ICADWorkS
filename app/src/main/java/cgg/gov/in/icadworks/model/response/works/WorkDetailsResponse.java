package cgg.gov.in.icadworks.model.response.works;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WorkDetailsResponse {

    @SerializedName("data")
    @Expose
    private List<WorkDetailsData> data = null;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;

    public List<WorkDetailsData> getData() {
        return data;
    }

    public void setData(List<WorkDetailsData> data) {
        this.data = data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

}
