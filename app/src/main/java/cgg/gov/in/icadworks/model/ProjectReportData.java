package cgg.gov.in.icadworks.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProjectReportData {

    @SerializedName("agreements")
    @Expose
    private Long agreements;
    @SerializedName("completed")
    @Expose
    private Long completed;
    @SerializedName("project_name")
    @Expose
    private String projectName;
    @SerializedName("unit_name")
    @Expose
    private String unitName;
    @SerializedName("techsancots")
    @Expose
    private Long techsancots;
    @SerializedName("ots")
    @Expose
    private Long ots;
    @SerializedName("not_started")
    @Expose
    private Long notStarted;
    @SerializedName("in_progress")
    @Expose
    private Long inProgress;
    @SerializedName("project_id")
    @Expose
    private Long projectId;
    @SerializedName("tenders")
    @Expose
    private Long tenders;
    @SerializedName("tanks")
    @Expose
    private Long tanks;
    @SerializedName("tanks_to_be_fed_count")
    @Expose
    private Long tanksTobeFed;
    @SerializedName("total")
    @Expose
    private Long total;
    @SerializedName("nominations")
    @Expose
    private Long nominations;
    @SerializedName("techsanctions")
    @Expose
    private Long techsanctions;
    @SerializedName("dname")
    @Expose
    private String dname;
    @SerializedName("unit_id")
    @Expose
    private Long unitId;
    @SerializedName("dcode")
    @Expose
    private Long dcode;

    public Long getTanksTobeFed() {
        return tanksTobeFed;
    }

    public void setTanksTobeFed(Long tanksTobeFed) {
        this.tanksTobeFed = tanksTobeFed;
    }

    public Long getNominations() {
        return nominations;
    }

    public void setNominations(Long nominations) {
        this.nominations = nominations;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getAgreements() {
        return agreements;
    }

    public void setAgreements(Long agreements) {
        this.agreements = agreements;
    }

    public Long getCompleted() {
        return completed;
    }

    public void setCompleted(Long completed) {
        this.completed = completed;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getTechsancots() {
        return techsancots;
    }

    public void setTechsancots(Long techsancots) {
        this.techsancots = techsancots;
    }

    public Long getOts() {
        return ots;
    }

    public void setOts(Long ots) {
        this.ots = ots;
    }

    public Long getNotStarted() {
        return notStarted;
    }

    public void setNotStarted(Long notStarted) {
        this.notStarted = notStarted;
    }

    public Long getInProgress() {
        return inProgress;
    }

    public void setInProgress(Long inProgress) {
        this.inProgress = inProgress;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getTenders() {
        return tenders;
    }

    public void setTenders(Long tenders) {
        this.tenders = tenders;
    }

    public Long getTanks() {
        return tanks;
    }

    public void setTanks(Long tanks) {
        this.tanks = tanks;
    }

    public Long getTechsanctions() {
        return techsanctions;
    }

    public void setTechsanctions(Long techsanctions) {
        this.techsanctions = techsanctions;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getDcode() {
        return dcode;
    }

    public void setDcode(Long dcode) {
        this.dcode = dcode;
    }

}
