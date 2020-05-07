package cgg.gov.in.icadworks.model.response.checkdam.office;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CDAbstractReport implements Parcelable {

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
    @SerializedName("check_dams")
    @Expose
    private Integer checkDams;
    @SerializedName("agreements")
    @Expose
    private Integer agreements;
    @SerializedName("single_bill_notsent")
    @Expose
    private Double singleBillNotsent;
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
    @SerializedName("tenders_publish")
    @Expose
    private Integer tendersPublish;
    @SerializedName("tech_sanctions")
    @Expose
    private Integer techSanctions;
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

    public Double getSingleBillNotsent() {
        return singleBillNotsent;
    }

    public void setSingleBillNotsent(Double singleBillNotsent) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.billssent);
        dest.writeValue(this.billsPaidAmount);
        dest.writeValue(this.billsSentAmt);
        dest.writeValue(this.billsPaid);
        dest.writeValue(this.checkDams);
        dest.writeValue(this.agreements);
        dest.writeValue(this.singleBillNotsent);
        dest.writeValue(this.commenced);
        dest.writeValue(this.adminAmount);
        dest.writeValue(this.finalSent);
        dest.writeValue(this.agmtAmt);
        dest.writeValue(this.techAmount);
        dest.writeValue(this.finalPaid);
        dest.writeValue(this.tendersAward);
        dest.writeValue(this.tendersPublish);
        dest.writeValue(this.techSanctions);
        dest.writeValue(this.adminSanctions);
    }

    public CDAbstractReport() {
    }

    protected CDAbstractReport(Parcel in) {
        this.billssent = (Double) in.readValue(Double.class.getClassLoader());
        this.billsPaidAmount = (Double) in.readValue(Double.class.getClassLoader());
        this.billsSentAmt = (Double) in.readValue(Double.class.getClassLoader());
        this.billsPaid = (Double) in.readValue(Double.class.getClassLoader());
        this.checkDams = (Integer) in.readValue(Integer.class.getClassLoader());
        this.agreements = (Integer) in.readValue(Integer.class.getClassLoader());
        this.singleBillNotsent = (Double) in.readValue(Double.class.getClassLoader());
        this.commenced = (Integer) in.readValue(Integer.class.getClassLoader());
        this.adminAmount = (Double) in.readValue(Double.class.getClassLoader());
        this.finalSent = (Integer) in.readValue(Integer.class.getClassLoader());
        this.agmtAmt = (Double) in.readValue(Double.class.getClassLoader());
        this.techAmount = (Double) in.readValue(Double.class.getClassLoader());
        this.finalPaid = (Double) in.readValue(Double.class.getClassLoader());
        this.tendersAward = (Integer) in.readValue(Integer.class.getClassLoader());
        this.tendersPublish = (Integer) in.readValue(Integer.class.getClassLoader());
        this.techSanctions = (Integer) in.readValue(Integer.class.getClassLoader());
        this.adminSanctions = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<CDAbstractReport> CREATOR = new Parcelable.Creator<CDAbstractReport>() {
        @Override
        public CDAbstractReport createFromParcel(Parcel source) {
            return new CDAbstractReport(source);
        }

        @Override
        public CDAbstractReport[] newArray(int size) {
            return new CDAbstractReport[size];
        }
    };
}
