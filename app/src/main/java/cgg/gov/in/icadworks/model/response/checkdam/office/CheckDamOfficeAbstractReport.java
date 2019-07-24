package cgg.gov.in.icadworks.model.response.checkdam.office;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckDamOfficeAbstractReport {
    @SerializedName("billssent")
    @Expose
    private Integer billssent;
    @SerializedName("bills_paid_amount")
    @Expose
    private Integer billsPaidAmount;
    @SerializedName("bills_sent_amt")
    @Expose
    private Integer billsSentAmt;
    @SerializedName("bills_paid")
    @Expose
    private Integer billsPaid;
    @SerializedName("check_dams")
    @Expose
    private Integer checkDams;
    @SerializedName("agreements")
    @Expose
    private Integer agreements;
    @SerializedName("single_bill_notsent")
    @Expose
    private Integer singleBillNotsent;
    @SerializedName("commenced")
    @Expose
    private Integer commenced;
    @SerializedName("admin_amount")
    @Expose
    private Double adminAmount;
    @SerializedName("final_sent")
    @Expose
    private Integer finalSent;
    @SerializedName("agmt_amt")
    @Expose
    private Integer agmtAmt;
    @SerializedName("tech_amount")
    @Expose
    private Integer techAmount;
    @SerializedName("final_paid")
    @Expose
    private Integer finalPaid;
    @SerializedName("tenders_award")
    @Expose
    private Integer tendersAward;
    @SerializedName("tenders_publish")
    @Expose
    private Integer tendersPublish;
    @SerializedName("tech_sanctions")
    @Expose
    private Integer techSanctions;
    @SerializedName("admin_sanctions")
    @Expose
    private Integer adminSanctions;

    public Integer getBillssent() {
        return billssent;
    }

    public void setBillssent(Integer billssent) {
        this.billssent = billssent;
    }

    public Integer getBillsPaidAmount() {
        return billsPaidAmount;
    }

    public void setBillsPaidAmount(Integer billsPaidAmount) {
        this.billsPaidAmount = billsPaidAmount;
    }

    public Integer getBillsSentAmt() {
        return billsSentAmt;
    }

    public void setBillsSentAmt(Integer billsSentAmt) {
        this.billsSentAmt = billsSentAmt;
    }

    public Integer getBillsPaid() {
        return billsPaid;
    }

    public void setBillsPaid(Integer billsPaid) {
        this.billsPaid = billsPaid;
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

    public Integer getSingleBillNotsent() {
        return singleBillNotsent;
    }

    public void setSingleBillNotsent(Integer singleBillNotsent) {
        this.singleBillNotsent = singleBillNotsent;
    }

    public Integer getCommenced() {
        return commenced;
    }

    public void setCommenced(Integer commenced) {
        this.commenced = commenced;
    }

    public Double getAdminAmount() {
        return adminAmount;
    }

    public void setAdminAmount(Double adminAmount) {
        this.adminAmount = adminAmount;
    }

    public Integer getFinalSent() {
        return finalSent;
    }

    public void setFinalSent(Integer finalSent) {
        this.finalSent = finalSent;
    }

    public Integer getAgmtAmt() {
        return agmtAmt;
    }

    public void setAgmtAmt(Integer agmtAmt) {
        this.agmtAmt = agmtAmt;
    }

    public Integer getTechAmount() {
        return techAmount;
    }

    public void setTechAmount(Integer techAmount) {
        this.techAmount = techAmount;
    }

    public Integer getFinalPaid() {
        return finalPaid;
    }

    public void setFinalPaid(Integer finalPaid) {
        this.finalPaid = finalPaid;
    }

    public Integer getTendersAward() {
        return tendersAward;
    }

    public void setTendersAward(Integer tendersAward) {
        this.tendersAward = tendersAward;
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

    public Integer getAdminSanctions() {
        return adminSanctions;
    }

    public void setAdminSanctions(Integer adminSanctions) {
        this.adminSanctions = adminSanctions;
    }
}
