package cgg.gov.in.icadworks.model.response.checkdam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckDamItemStatusData {

    @SerializedName("tank_id")
    @Expose
    private Integer tankId;
    @SerializedName("irr_work_id")
    @Expose
    private Integer irrWorkId;
    @SerializedName("status_id")
    @Expose
    private Integer statusId;
    @SerializedName("irr_work_name")
    @Expose
    private String irrWorkName;
    @SerializedName("status_name")
    @Expose
    private String statusName;

    public Integer getTankId() {
        return tankId;
    }

    public void setTankId(Integer tankId) {
        this.tankId = tankId;
    }

    public Integer getIrrWorkId() {
        return irrWorkId;
    }

    public void setIrrWorkId(Integer irrWorkId) {
        this.irrWorkId = irrWorkId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getIrrWorkName() {
        return irrWorkName;
    }

    public void setIrrWorkName(String irrWorkName) {
        this.irrWorkName = irrWorkName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

}
