package cgg.gov.in.icadworks.model.response.itemStatus;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemStatusData implements Parcelable {

    @SerializedName("status_id")
    @Expose
    private String statusId;
    @SerializedName("status_name")
    @Expose
    private String statusName;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.statusId);
        dest.writeString(this.statusName);
    }

    public ItemStatusData() {
    }

    protected ItemStatusData(Parcel in) {
        this.statusId = in.readString();
        this.statusName = in.readString();
    }

    public static final Parcelable.Creator<ItemStatusData> CREATOR = new Parcelable.Creator<ItemStatusData>() {
        @Override
        public ItemStatusData createFromParcel(Parcel source) {
            return new ItemStatusData(source);
        }

        @Override
        public ItemStatusData[] newArray(int size) {
            return new ItemStatusData[size];
        }
    };
}
