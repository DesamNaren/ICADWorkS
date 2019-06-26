package cgg.gov.in.icadworks.model.response.report;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportData {

    @SerializedName("agreements")
    @Expose
    private Integer agreements;
    @SerializedName("completed")
    @Expose
    private Integer completed;
    private Integer total;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @SerializedName("project_name")
    @Expose
    private String projectName;

    @SerializedName("unit_name")
    @Expose
    private String unitName;

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @SerializedName("techsancots")
    @Expose
    private Integer techsancots;
    @SerializedName("ots")
    @Expose
    private Integer ots;
    @SerializedName("not_started")
    @Expose
    private Integer notStarted;
    @SerializedName("in_progress")
    @Expose
    private Integer inProgress;
    @SerializedName("project_id")
    @Expose
    private Integer projectId;
    @SerializedName("tenders")
    @Expose
    private Integer tenders;
    @SerializedName("nominations")
    @Expose
    private Integer nominations;
    @SerializedName("tanks")
    @Expose
    private Integer tanks;
    @SerializedName("tanks_to_be_fed")
    @Expose
    private Integer tanks_to_be_fed;

    @SerializedName("techsanctions")
    @Expose
    private Integer techsanctions;
    @SerializedName("dname")
    @Expose
    private String dname;
    @SerializedName("unit_id")
    @Expose
    private Integer unitId;
    @SerializedName("dcode")
    @Expose
    private Integer dcode;

    public Integer getTanks_to_be_fed() {
        return tanks_to_be_fed;
    }

    public void setTanks_to_be_fed(Integer tanks_to_be_fed) {
        this.tanks_to_be_fed = tanks_to_be_fed;
    }

    public Integer getNominations() {
        return nominations;
    }

    public void setNominations(Integer nominations) {
        this.nominations = nominations;
    }

    public Integer getAgreements() {
        return agreements;
    }

    public void setAgreements(Integer agreements) {
        this.agreements = agreements;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getTechsancots() {
        return techsancots;
    }

    public void setTechsancots(Integer techsancots) {
        this.techsancots = techsancots;
    }

    public Integer getOts() {
        return ots;
    }

    public void setOts(Integer ots) {
        this.ots = ots;
    }

    public Integer getNotStarted() {
        return notStarted;
    }

    public void setNotStarted(Integer notStarted) {
        this.notStarted = notStarted;
    }

    public Integer getInProgress() {
        return inProgress;
    }

    public void setInProgress(Integer inProgress) {
        this.inProgress = inProgress;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTenders() {
        return tenders;
    }

    public void setTenders(Integer tenders) {
        this.tenders = tenders;
    }

    public Integer getTanks() {
        return tanks;
    }

    public void setTanks(Integer tanks) {
        this.tanks = tanks;
    }

    public Integer getTechsanctions() {
        return techsanctions;
    }

    public void setTechsanctions(Integer techsanctions) {
        this.techsanctions = techsanctions;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Integer getDcode() {
        return dcode;
    }

    public void setDcode(Integer dcode) {
        this.dcode = dcode;
    }

}
