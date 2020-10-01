package cgg.gov.in.icadworks.model.response.works;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkDetailsData {

    @SerializedName("agreement_amount")
    @Expose
    private Double agreementAmount;

    @SerializedName("agency_name")
    @Expose
    private String agencyName;

    @SerializedName("agreement_number")
    @Expose
    private String agreementNumber;

    @SerializedName("ts_str_id")
    @Expose
    private Long tsStrId;

    @SerializedName("paid_amount")
    @Expose
    private Double paidAmount;

    @SerializedName("technical_sanction_amount")
    @Expose
    private Long technicalSanctionAmount;

    @SerializedName("ot_count")
    @Expose
    private Long otCount;

    @SerializedName("technical_sanction_number")
    @Expose
    private String technicalSanctionNumber;

    @SerializedName("bill_status")
    @Expose
    private String billStatus;

    @SerializedName("str_work_id")
    @Expose
    private Long strWorkId;

    @SerializedName("sent_amount")
    @Expose
    private Double sentAmount;

    public Double getAgreementAmount() {
        return agreementAmount;
    }

    public void setAgreementAmount(Double agreementAmount) {
        this.agreementAmount = agreementAmount;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgreementNumber() {
        return agreementNumber;
    }

    public void setAgreementNumber(String agreementNumber) {
        this.agreementNumber = agreementNumber;
    }

    public Long getTsStrId() {
        return tsStrId;
    }

    public void setTsStrId(Long tsStrId) {
        this.tsStrId = tsStrId;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Long getTechnicalSanctionAmount() {
        return technicalSanctionAmount;
    }

    public void setTechnicalSanctionAmount(Long technicalSanctionAmount) {
        this.technicalSanctionAmount = technicalSanctionAmount;
    }

    public Long getOtCount() {
        return otCount;
    }

    public void setOtCount(Long otCount) {
        this.otCount = otCount;
    }

    public String getTechnicalSanctionNumber() {
        return technicalSanctionNumber;
    }

    public void setTechnicalSanctionNumber(String technicalSanctionNumber) {
        this.technicalSanctionNumber = technicalSanctionNumber;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public Long getStrWorkId() {
        return strWorkId;
    }

    public void setStrWorkId(Long strWorkId) {
        this.strWorkId = strWorkId;
    }

    public Double getSentAmount() {
        return sentAmount;
    }

    public void setSentAmount(Double sentAmount) {
        this.sentAmount = sentAmount;
    }
}
