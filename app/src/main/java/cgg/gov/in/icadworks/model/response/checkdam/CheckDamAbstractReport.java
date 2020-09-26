package cgg.gov.in.icadworks.model.response.checkdam;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckDamAbstractReport implements Parcelable {
    @SerializedName("total")
    @Expose
    private Long total;
    @SerializedName("in_progress")
    @Expose
    private Long inProgress;
    @SerializedName("completed")
    @Expose
    private Long completed;
    @SerializedName("not_started")
    @Expose
    private Long notStarted;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getInProgress() {
        return inProgress;
    }

    public void setInProgress(Long inProgress) {
        this.inProgress = inProgress;
    }

    public Long getCompleted() {
        return completed;
    }

    public void setCompleted(Long completed) {
        this.completed = completed;
    }

    public Long getNotStarted() {
        return notStarted;
    }

    public void setNotStarted(Long notStarted) {
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
        this.total = (Long) in.readValue(Long.class.getClassLoader());
        this.inProgress = (Long) in.readValue(Long.class.getClassLoader());
        this.completed = (Long) in.readValue(Long.class.getClassLoader());
        this.notStarted = (Long) in.readValue(Long.class.getClassLoader());
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
