package cgg.gov.in.icadworks.model.response.ot;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AbstractReport {

    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("in_progress")
    @Expose
    private String inProgress;
    @SerializedName("completed")
    @Expose
    private String completed;
    @SerializedName("not_started")
    @Expose
    private String notStarted;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getInProgress() {
        return inProgress;
    }

    public void setInProgress(String inProgress) {
        this.inProgress = inProgress;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public String getNotStarted() {
        return notStarted;
    }

    public void setNotStarted(String notStarted) {
        this.notStarted = notStarted;
    }

}
