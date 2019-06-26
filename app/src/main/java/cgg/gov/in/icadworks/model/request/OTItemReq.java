package cgg.gov.in.icadworks.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OTItemReq {

    @SerializedName("irr_work_id")
    @Expose
    private String irrWorkId;
    @SerializedName("status_id")
    @Expose
    private String statusId;

    public String getIrrWorkId() {
        return irrWorkId;
    }

    public void setIrrWorkId(String irrWorkId) {
        this.irrWorkId = irrWorkId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

}
