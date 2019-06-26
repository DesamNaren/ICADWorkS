package cgg.gov.in.icadworks.model.response.itemStatus;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ItemStatusResponse implements Parcelable {

    @SerializedName("data")
    @Expose
    private List<ItemStatusData> data = null;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;

    public List<ItemStatusData> getData() {
        return data;
    }

    public void setData(List<ItemStatusData> data) {
        this.data = data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.data);
        dest.writeValue(this.statusCode);
    }

    public ItemStatusResponse() {
    }

    protected ItemStatusResponse(Parcel in) {
        this.data = in.createTypedArrayList(ItemStatusData.CREATOR);
        this.statusCode = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<ItemStatusResponse> CREATOR = new Parcelable.Creator<ItemStatusResponse>() {
        @Override
        public ItemStatusResponse createFromParcel(Parcel source) {
            return new ItemStatusResponse(source);
        }

        @Override
        public ItemStatusResponse[] newArray(int size) {
            return new ItemStatusResponse[size];
        }
    };
}
