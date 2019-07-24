package cgg.gov.in.icadworks.model.response.checkdam.office;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckDamOfficeDataItem {
    @SerializedName("billssent")
    @Expose
    private Double billssent;
    @SerializedName("bills_paid_amount")
    @Expose
    private Double billsPaidAmount;
    @SerializedName("bills_sent_amt")
    @Expose
    private Double billsSentAmt;
    @SerializedName("bills_paid")
    @Expose
    private Double billsPaid;
    @SerializedName("aname")
    @Expose
    private String aname;
    @SerializedName("check_dams")
    @Expose
    private Integer checkDams;
    @SerializedName("agreements")
    @Expose
    private Integer agreements;
    @SerializedName("acode")
    @Expose
    private String acode;
    @SerializedName("single_bill_notsent")
    @Expose
    private Double singleBillNotsent;
    @SerializedName("commenced")
    @Expose
    private Double commenced;
    @SerializedName("admin_amount")
    @Expose
    private Double adminAmount;
    @SerializedName("final_sent")
    @Expose
    private Double finalSent;
    @SerializedName("office_name")
    @Expose
    private String officeName;
    @SerializedName("agmt_amt")
    @Expose
    private Double agmtAmt;
    @SerializedName("tech_amount")
    @Expose
    private Double techAmount;
    @SerializedName("final_paid")
    @Expose
    private Double finalPaid;
    @SerializedName("tenders_award")
    @Expose
    private Integer tendersAward;
    @SerializedName("dname")
    @Expose
    private String dname;
    @SerializedName("tenders_publish")
    @Expose
    private Integer tendersPublish;
    @SerializedName("tech_sanctions")
    @Expose
    private Integer techSanctions;
    @SerializedName("unit_id")
    @Expose
    private Integer unitId;
    @SerializedName("dcode")
    @Expose
    private String dcode;
    @SerializedName("admin_sanctions")
    @Expose
    private Integer adminSanctions;

    public Double getBillssent() {
        return billssent;
    }

    public void setBillssent(Double billssent) {
        this.billssent = billssent;
    }

    public Double getBillsPaidAmount() {
        return billsPaidAmount;
    }

    public void setBillsPaidAmount(Double billsPaidAmount) {
        this.billsPaidAmount = billsPaidAmount;
    }

    public Double getBillsSentAmt() {
        return billsSentAmt;
    }

    public void setBillsSentAmt(Double billsSentAmt) {
        this.billsSentAmt = billsSentAmt;
    }

    public Double getBillsPaid() {
        return billsPaid;
    }

    public void setBillsPaid(Double billsPaid) {
        this.billsPaid = billsPaid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public Integer getCheckDams() {
        return checkDams;
    }

    public void setCheckDams(Integer checkDams) {
        this.checkDams = checkDams;
    }

    public Integer getAgreements() {
        return agreements;
    }

    public void setAgreements(Integer agreements) {
        this.agreements = agreements;
    }

    public String getAcode() {
        return acode;
    }

    public void setAcode(String acode) {
        this.acode = acode;
    }

    public Double getSingleBillNotsent() {
        return singleBillNotsent;
    }

    public void setSingleBillNotsent(Double singleBillNotsent) {
        this.singleBillNotsent = singleBillNotsent;
    }

    public Double getCommenced() {
        return commenced;
    }

    public void setCommenced(Double commenced) {
        this.commenced = commenced;
    }

    public Double getAdminAmount() {
        return adminAmount;
    }

    public void setAdminAmount(Double adminAmount) {
        this.adminAmount = adminAmount;
    }

    public Double getFinalSent() {
        return finalSent;
    }

    public void setFinalSent(Double finalSent) {
        this.finalSent = finalSent;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public Double getAgmtAmt() {
        return agmtAmt;
    }

    public void setAgmtAmt(Double agmtAmt) {
        this.agmtAmt = agmtAmt;
    }

    public Double getTechAmount() {
        return techAmount;
    }

    public void setTechAmount(Double techAmount) {
        this.techAmount = techAmount;
    }

    public Double getFinalPaid() {
        return finalPaid;
    }

    public void setFinalPaid(Double finalPaid) {
        this.finalPaid = finalPaid;
    }

    public Integer getTendersAward() {
        return tendersAward;
    }

    public void setTendersAward(Integer tendersAward) {
        this.tendersAward = tendersAward;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Integer getTendersPublish() {
        return tendersPublish;
    }

    public void setTendersPublish(Integer tendersPublish) {
        this.tendersPublish = tendersPublish;
    }

    public Integer getTechSanctions() {
        return techSanctions;
    }

    public void setTechSanctions(Integer techSanctions) {
        this.techSanctions = techSanctions;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getDcode() {
        return dcode;
    }

    public void setDcode(String dcode) {
        this.dcode = dcode;
    }

    public Integer getAdminSanctions() {
        return adminSanctions;
    }

    public void setAdminSanctions(Integer adminSanctions) {
        this.adminSanctions = adminSanctions;
    }
}
