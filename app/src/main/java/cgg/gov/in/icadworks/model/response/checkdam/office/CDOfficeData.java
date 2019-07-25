package cgg.gov.in.icadworks.model.response.checkdam.office;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CDOfficeData implements Parcelable {

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
    private Integer commenced;
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
        dest.writeString(this.aname);
        dest.writeValue(this.checkDams);
        dest.writeValue(this.agreements);
        dest.writeString(this.acode);
        dest.writeValue(this.singleBillNotsent);
        dest.writeValue(this.commenced);
        dest.writeValue(this.adminAmount);
        dest.writeValue(this.finalSent);
        dest.writeString(this.officeName);
        dest.writeValue(this.agmtAmt);
        dest.writeValue(this.techAmount);
        dest.writeValue(this.finalPaid);
        dest.writeValue(this.tendersAward);
        dest.writeString(this.dname);
        dest.writeValue(this.tendersPublish);
        dest.writeValue(this.techSanctions);
        dest.writeValue(this.unitId);
        dest.writeString(this.dcode);
        dest.writeValue(this.adminSanctions);
    }

    public CDOfficeData() {
    }

    protected CDOfficeData(Parcel in) {
        this.billssent = (Double) in.readValue(Double.class.getClassLoader());
        this.billsPaidAmount = (Double) in.readValue(Double.class.getClassLoader());
        this.billsSentAmt = (Double) in.readValue(Double.class.getClassLoader());
        this.billsPaid = (Double) in.readValue(Double.class.getClassLoader());
        this.aname = in.readString();
        this.checkDams = (Integer) in.readValue(Integer.class.getClassLoader());
        this.agreements = (Integer) in.readValue(Integer.class.getClassLoader());
        this.acode = in.readString();
        this.singleBillNotsent = (Double) in.readValue(Double.class.getClassLoader());
        this.commenced = (Integer) in.readValue(Integer.class.getClassLoader());
        this.adminAmount = (Double) in.readValue(Double.class.getClassLoader());
        this.finalSent = (Double) in.readValue(Double.class.getClassLoader());
        this.officeName = in.readString();
        this.agmtAmt = (Double) in.readValue(Double.class.getClassLoader());
        this.techAmount = (Double) in.readValue(Double.class.getClassLoader());
        this.finalPaid = (Double) in.readValue(Double.class.getClassLoader());
        this.tendersAward = (Integer) in.readValue(Integer.class.getClassLoader());
        this.dname = in.readString();
        this.tendersPublish = (Integer) in.readValue(Integer.class.getClassLoader());
        this.techSanctions = (Integer) in.readValue(Integer.class.getClassLoader());
        this.unitId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.dcode = in.readString();
        this.adminSanctions = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<CDOfficeData> CREATOR = new Parcelable.Creator<CDOfficeData>() {
        @Override
        public CDOfficeData createFromParcel(Parcel source) {
            return new CDOfficeData(source);
        }

        @Override
        public CDOfficeData[] newArray(int size) {
            return new CDOfficeData[size];
        }
    };
}
