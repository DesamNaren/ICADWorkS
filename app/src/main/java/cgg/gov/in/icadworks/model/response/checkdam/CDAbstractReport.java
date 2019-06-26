package cgg.gov.in.icadworks.model.response.checkdam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CDAbstractReport {

    @SerializedName("total_checkdams")
    @Expose
    private Integer totalCheckdams;

    public Integer getTotalCheckdams() {
        return totalCheckdams;
    }

    public void setTotalCheckdams(Integer totalCheckdams) {
        this.totalCheckdams = totalCheckdams;
    }
}
