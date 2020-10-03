package cgg.gov.in.icadworks.model.response.ot;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OTData implements Parcelable {

    private long statusId;

    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    @SerializedName("structure_no")
    @Expose
    private String structureNo;
    @SerializedName("projectname")
    @Expose
    private String projectname;
    @SerializedName("vent_dia_in_mm")
    @Expose
    private String ventDiaInMm;
    @SerializedName("assemblyname")
    @Expose
    private String assemblyname;
    @SerializedName("tanks_to_be_fed_count")
    @Expose
    private String tanksToBeFedCount;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("canalname")
    @Expose
    private String canalname;
    @SerializedName("structure_id")
    @Expose
    private String structureId;
    @SerializedName("mandalname")
    @Expose
    private String mandalname;
    @SerializedName("mandalid")
    @Expose
    private String mandalid;
    @SerializedName("getItemStatusData")
    @Expose
    private List<OTStatusData> getItemStatusData = null;
    @SerializedName("districtid")
    @Expose
    private String districtid;
    @SerializedName("districtname")
    @Expose
    private String districtname;
    @SerializedName("structure_name")
    @Expose
    private String structurename;
    @SerializedName("reservoirname")
    @Expose
    private String reservoirname;
    @SerializedName("cumulative_capacity_of_tank_in_mcft")
    @Expose
    private String cumulativeCapacityOfTankInMcft;
    @SerializedName("assemblycode")
    @Expose
    private String assemblycode;
    @SerializedName("canalid")
    @Expose
    private String canalid;
    @SerializedName("villageid")
    @Expose
    private String villageid;
    @SerializedName("projectid")
    @Expose
    private String projectid;
    @SerializedName("villagename")
    @Expose
    private String villagename;
    @SerializedName("discharge_in_cusecs")
    @Expose
    private String dischargeInCusecs;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("reservoirid")
    @Expose
    private String reservoirid;
    @SerializedName("photo_path")
    @Expose
    private String photoPath;

    @SerializedName("agmt_msg")
    @Expose
    private String agmtMsg;

    @SerializedName("agmt_status")
    @Expose
    private String agmtStatus;

    @SerializedName("radius_msg")
    @Expose
    private String radiusMsg;

    @SerializedName("agmt_number")
    @Expose
    private String agmtNumber;

    @SerializedName("ts_number")
    @Expose
    private String tsNumber;

    public OTData() {
    }

    public String getAgmtNumber() {
        return agmtNumber;
    }

    public void setAgmtNumber(String agmtNumber) {
        this.agmtNumber = agmtNumber;
    }

    public String getTsNumber() {
        return tsNumber;
    }

    public void setTsNumber(String tsNumber) {
        this.tsNumber = tsNumber;
    }

    public String getStructureNo() {
        return structureNo;
    }

    public void setStructureNo(String structureNo) {
        this.structureNo = structureNo;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getVentDiaInMm() {
        return ventDiaInMm;
    }

    public void setVentDiaInMm(String ventDiaInMm) {
        this.ventDiaInMm = ventDiaInMm;
    }

    public String getAssemblyname() {
        return assemblyname;
    }

    public void setAssemblyname(String assemblyname) {
        this.assemblyname = assemblyname;
    }

    public String getTanksToBeFedCount() {
        return tanksToBeFedCount;
    }

    public void setTanksToBeFedCount(String tanksToBeFedCount) {
        this.tanksToBeFedCount = tanksToBeFedCount;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCanalname() {
        return canalname;
    }

    public void setCanalname(String canalname) {
        this.canalname = canalname;
    }

    public String getStructureId() {
        return structureId;
    }

    public void setStructureId(String structureId) {
        this.structureId = structureId;
    }

    public String getMandalname() {
        return mandalname;
    }

    public void setMandalname(String mandalname) {
        this.mandalname = mandalname;
    }

    public String getMandalid() {
        return mandalid;
    }

    public void setMandalid(String mandalid) {
        this.mandalid = mandalid;
    }

    public List<OTStatusData> getGetItemStatusData() {
        return getItemStatusData;
    }

    public void setGetItemStatusData(List<OTStatusData> getItemStatusData) {
        this.getItemStatusData = getItemStatusData;
    }

    public String getDistrictid() {
        return districtid;
    }

    public void setDistrictid(String districtid) {
        this.districtid = districtid;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    public String getStructurename() {
        return structurename;
    }

    public void setStructurename(String structurename) {
        this.structurename = structurename;
    }

    public String getReservoirname() {
        return reservoirname;
    }

    public void setReservoirname(String reservoirname) {
        this.reservoirname = reservoirname;
    }

    public String getCumulativeCapacityOfTankInMcft() {
        return cumulativeCapacityOfTankInMcft;
    }

    public void setCumulativeCapacityOfTankInMcft(String cumulativeCapacityOfTankInMcft) {
        this.cumulativeCapacityOfTankInMcft = cumulativeCapacityOfTankInMcft;
    }

    public String getAssemblycode() {
        return assemblycode;
    }

    public void setAssemblycode(String assemblycode) {
        this.assemblycode = assemblycode;
    }

    public String getCanalid() {
        return canalid;
    }

    public void setCanalid(String canalid) {
        this.canalid = canalid;
    }

    public String getVillageid() {
        return villageid;
    }

    public void setVillageid(String villageid) {
        this.villageid = villageid;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getVillagename() {
        return villagename;
    }

    public void setVillagename(String villagename) {
        this.villagename = villagename;
    }

    public String getDischargeInCusecs() {
        return dischargeInCusecs;
    }

    public void setDischargeInCusecs(String dischargeInCusecs) {
        this.dischargeInCusecs = dischargeInCusecs;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getReservoirid() {
        return reservoirid;
    }

    public void setReservoirid(String reservoirid) {
        this.reservoirid = reservoirid;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getAgmtMsg() {
        return agmtMsg;
    }

    public void setAgmtMsg(String agmtMsg) {
        this.agmtMsg = agmtMsg;
    }

    public String getAgmtStatus() {
        return agmtStatus;
    }

    public void setAgmtStatus(String agmtStatus) {
        this.agmtStatus = agmtStatus;
    }

    public String getRadiusMsg() {
        return radiusMsg;
    }

    public void setRadiusMsg(String radiusMsg) {
        this.radiusMsg = radiusMsg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.structureNo);
        dest.writeString(this.projectname);
        dest.writeString(this.ventDiaInMm);
        dest.writeString(this.assemblyname);
        dest.writeString(this.tanksToBeFedCount);
        dest.writeString(this.latitude);
        dest.writeString(this.canalname);
        dest.writeString(this.structureId);
        dest.writeString(this.mandalname);
        dest.writeString(this.mandalid);
        dest.writeTypedList(this.getItemStatusData);
        dest.writeString(this.districtid);
        dest.writeString(this.districtname);
        dest.writeString(this.structurename);
        dest.writeString(this.reservoirname);
        dest.writeString(this.cumulativeCapacityOfTankInMcft);
        dest.writeString(this.assemblycode);
        dest.writeString(this.canalid);
        dest.writeString(this.villageid);
        dest.writeString(this.projectid);
        dest.writeString(this.villagename);
        dest.writeString(this.dischargeInCusecs);
        dest.writeString(this.longitude);
        dest.writeString(this.reservoirid);
        dest.writeString(this.photoPath);
        dest.writeString(this.agmtMsg);
        dest.writeString(this.agmtStatus);
        dest.writeString(this.radiusMsg);
        dest.writeString(this.agmtNumber);
        dest.writeString(this.tsNumber);
    }

    protected OTData(Parcel in) {
        this.structureNo = in.readString();
        this.projectname = in.readString();
        this.ventDiaInMm = in.readString();
        this.assemblyname = in.readString();
        this.tanksToBeFedCount = in.readString();
        this.latitude = in.readString();
        this.canalname = in.readString();
        this.structureId = in.readString();
        this.mandalname = in.readString();
        this.mandalid = in.readString();
        this.getItemStatusData = in.createTypedArrayList(OTStatusData.CREATOR);
        this.districtid = in.readString();
        this.districtname = in.readString();
        this.structurename = in.readString();
        this.reservoirname = in.readString();
        this.cumulativeCapacityOfTankInMcft = in.readString();
        this.assemblycode = in.readString();
        this.canalid = in.readString();
        this.villageid = in.readString();
        this.projectid = in.readString();
        this.villagename = in.readString();
        this.dischargeInCusecs = in.readString();
        this.longitude = in.readString();
        this.reservoirid = in.readString();
        this.photoPath = in.readString();
        this.agmtMsg = in.readString();
        this.agmtStatus = in.readString();
        this.radiusMsg = in.readString();
        this.agmtNumber = in.readString();
        this.tsNumber = in.readString();
    }

    public static final Creator<OTData> CREATOR = new Creator<OTData>() {
        @Override
        public OTData createFromParcel(Parcel source) {
            return new OTData(source);
        }

        @Override
        public OTData[] newArray(int size) {
            return new OTData[size];
        }
    };
}
