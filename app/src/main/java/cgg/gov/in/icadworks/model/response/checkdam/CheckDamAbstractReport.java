package cgg.gov.in.icadworks.model.response.checkdam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckDamAbstractReport {
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

}
