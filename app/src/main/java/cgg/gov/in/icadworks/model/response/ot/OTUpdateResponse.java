package cgg.gov.in.icadworks.model.response.ot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OTUpdateResponse {

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

}
