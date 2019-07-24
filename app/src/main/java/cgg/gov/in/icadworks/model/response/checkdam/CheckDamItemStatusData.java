package cgg.gov.in.icadworks.model.response.checkdam;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckDamItemStatusData implements Parcelable {

    @SerializedName("tank_id")
    @Expose
    private Integer tankId;
    @SerializedName("irr_work_id")
    @Expose
    private String irrWorkId;
    @SerializedName("status_id")
    @Expose
    private String statusId;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.tankId);
        dest.writeString(this.irrWorkId);
        dest.writeString(this.statusId);
        dest.writeString(this.irrWorkName);
        dest.writeString(this.statusName);
    }

    public CheckDamItemStatusData() {
    }

    protected CheckDamItemStatusData(Parcel in) {
        this.tankId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.irrWorkId = in.readString();
        this.statusId = in.readString();
        this.irrWorkName = in.readString();
        this.statusName = in.readString();
    }

    public static final Parcelable.Creator<CheckDamItemStatusData> CREATOR = new Parcelable.Creator<CheckDamItemStatusData>() {
        @Override
        public CheckDamItemStatusData createFromParcel(Parcel source) {
            return new CheckDamItemStatusData(source);
        }

        @Override
        public CheckDamItemStatusData[] newArray(int size) {
            return new CheckDamItemStatusData[size];
        }
    };
}
