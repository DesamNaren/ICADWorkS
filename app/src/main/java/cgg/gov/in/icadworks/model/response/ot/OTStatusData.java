package cgg.gov.in.icadworks.model.response.ot;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OTStatusData implements Parcelable {
    @SerializedName("irr_work_id")
    @Expose
    private String irrWorkId;
    @SerializedName("status_id")
    @Expose
    private String statusId;
    @SerializedName("status_name")
    @Expose
    private String statusName;
    @SerializedName("irr_work_name")
    @Expose
    private String irrWorkName;

    protected OTStatusData(Parcel in) {
        irrWorkId = in.readString();
        statusId = in.readString();
        statusName = in.readString();
        irrWorkName = in.readString();
    }

    public static final Creator<OTStatusData> CREATOR = new Creator<OTStatusData>() {
        @Override
        public OTStatusData createFromParcel(Parcel in) {
            return new OTStatusData(in);
        }

        @Override
        public OTStatusData[] newArray(int size) {
            return new OTStatusData[size];
        }
    };

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

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getIrrWorkName() {
        return irrWorkName;
    }

    public void setIrrWorkName(String irrWorkName) {
        this.irrWorkName = irrWorkName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(irrWorkId);
        parcel.writeString(statusId);
        parcel.writeString(statusName);
        parcel.writeString(irrWorkName);
    }
}
