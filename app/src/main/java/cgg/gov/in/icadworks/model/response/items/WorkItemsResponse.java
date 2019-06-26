package cgg.gov.in.icadworks.model.response.items;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class WorkItemsResponse implements Parcelable {

    @SerializedName("data")
    @Expose
    private List<WorkItemData> data = null;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;

    public List<WorkItemData> getData() {
        return data;
    }

    public void setData(List<WorkItemData> data) {
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
        dest.writeList(this.data);
        dest.writeValue(this.statusCode);
    }

    public WorkItemsResponse() {
    }

    protected WorkItemsResponse(Parcel in) {
        this.data = new ArrayList<WorkItemData>();
        in.readList(this.data, WorkItemData.class.getClassLoader());
        this.statusCode = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<WorkItemsResponse> CREATOR = new Parcelable.Creator<WorkItemsResponse>() {
        @Override
        public WorkItemsResponse createFromParcel(Parcel source) {
            return new WorkItemsResponse(source);
        }

        @Override
        public WorkItemsResponse[] newArray(int size) {
            return new WorkItemsResponse[size];
        }
    };
}
