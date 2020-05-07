package cgg.gov.in.icadworks.model.response.checkdam;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckDamAbstractReport implements Parcelable {
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("in_progress")
    @Expose
    private Integer inProgress;
    @SerializedName("completed")
    @Expose
    private Integer completed;
    @SerializedName("not_started")
    @Expose
    private Integer notStarted;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getInProgress() {
        return inProgress;
    }

    public void setInProgress(Integer inProgress) {
        this.inProgress = inProgress;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public Integer getNotStarted() {
        return notStarted;
    }

    public void setNotStarted(Integer notStarted) {
        this.notStarted = notStarted;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.total);
        dest.writeValue(this.inProgress);
        dest.writeValue(this.completed);
        dest.writeValue(this.notStarted);
    }

    public CheckDamAbstractReport() {
    }

    protected CheckDamAbstractReport(Parcel in) {
        this.total = (Integer) in.readValue(Integer.class.getClassLoader());
        this.inProgress = (Integer) in.readValue(Integer.class.getClassLoader());
        this.completed = (Integer) in.readValue(Integer.class.getClassLoader());
        this.notStarted = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<CheckDamAbstractReport> CREATOR = new Parcelable.Creator<CheckDamAbstractReport>() {
        @Override
        public CheckDamAbstractReport createFromParcel(Parcel source) {
            return new CheckDamAbstractReport(source);
        }

        @Override
        public CheckDamAbstractReport[] newArray(int size) {
            return new CheckDamAbstractReport[size];
        }
    };
}
