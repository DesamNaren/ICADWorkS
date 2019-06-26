package cgg.gov.in.icadworks.model.response.items;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkItemData implements Parcelable {

    @SerializedName("irr_work_id")
    @Expose
    private String irrWorkId;
    @SerializedName("irr_work_name")
    @Expose
    private String irrWorkName;

    public String getIrrWorkId() {
        return irrWorkId;
    }

    public void setIrrWorkId(String irrWorkId) {
        this.irrWorkId = irrWorkId;
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
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.irrWorkId);
        dest.writeString(this.irrWorkName);
    }

    public WorkItemData() {
    }

    protected WorkItemData(Parcel in) {
        this.irrWorkId = in.readString();
        this.irrWorkName = in.readString();
    }

    public static final Parcelable.Creator<WorkItemData> CREATOR = new Parcelable.Creator<WorkItemData>() {
        @Override
        public WorkItemData createFromParcel(Parcel source) {
            return new WorkItemData(source);
        }

        @Override
        public WorkItemData[] newArray(int size) {
            return new WorkItemData[size];
        }
    };
}
