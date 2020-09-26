package cgg.gov.in.icadworks.model.response.checkdam;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class  CheckDamData implements Parcelable {

    @SerializedName("tank_name")
    @Expose
    private String tankName;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("tank_id")
    @Expose
    private Long tankId;
    @SerializedName("acode")
    @Expose
    private String acode;
    @SerializedName("agmt_msg")
    @Expose
    private String agmtMsg;
    @SerializedName("bundlength")
    @Expose
    private Long bundlength;
    @SerializedName("vcode")
    @Expose
    private String vcode;
    @SerializedName("geo_id")
    @Expose
    private Long geoId;
    @SerializedName("division")
    @Expose
    private Long division;
    @SerializedName("getItemStatusData")
    @Expose
    private List<CheckDamItemStatusData> getItemStatusData = null;
    @SerializedName("section_id")
    @Expose
    private Long sectionId;
    @SerializedName("assembly")
    @Expose
    private String assembly;
    @SerializedName("preworkcapacity")
    @Expose
    private Double preworkcapacity;
    @SerializedName("village")
    @Expose
    private String village;
    @SerializedName("circle_name")
    @Expose
    private String circleName;
    @SerializedName("sub_division")
    @Expose
    private Long subDivision;
    @SerializedName("postworkcapacity")
    @Expose
    private Double postworkcapacity;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("tank_code")
    @Expose
    private long tankCode;
    @SerializedName("habitation")
    @Expose
    private String habitation;
    @SerializedName("ayacut")
    @Expose
    private Long ayacut;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("unit_name")
    @Expose
    private String unitName;
    @SerializedName("unit")
    @Expose
    private Long unit;
    @SerializedName("mcode")
    @Expose
    private String mcode;
    @SerializedName("sub_division_name")
    @Expose
    private String subDivisionName;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("division_name")
    @Expose
    private String divisionName;
    @SerializedName("circle")
    @Expose
    private Long circle;
    @SerializedName("hcode")
    @Expose
    private String hcode;
    @SerializedName("dcode")
    @Expose
    private String dcode;
    @SerializedName("mandal")
    @Expose
    private String mandal;

    @SerializedName("agmt_status")
    @Expose
    private String agmt_status;

    @SerializedName("radius_msg")
    @Expose
    private String radius_msg;

    @SerializedName("image_path")
    @Expose
    private String image_path;

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getAgmtMsg() {
        return agmtMsg;
    }

    public void setAgmtMsg(String agmtMsg) {
        this.agmtMsg = agmtMsg;
    }

    public String getAgmt_status() {
        return agmt_status;
    }

    public void setAgmt_status(String agmt_status) {
        this.agmt_status = agmt_status;
    }

    public String getRadius_msg() {
        return radius_msg;
    }

    public void setRadius_msg(String radius_msg) {
        this.radius_msg = radius_msg;
    }

    public String getTankName() {
        return tankName;
    }

    public void setTankName(String tankName) {
        this.tankName = tankName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Long getTankId() {
        return tankId;
    }

    public void setTankId(Long tankId) {
        this.tankId = tankId;
    }

    public String getAcode() {
        return acode;
    }

    public void setAcode(String acode) {
        this.acode = acode;
    }

    public Long getBundlength() {
        return bundlength;
    }

    public void setBundlength(Long bundlength) {
        this.bundlength = bundlength;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public Long getGeoId() {
        return geoId;
    }

    public void setGeoId(Long geoId) {
        this.geoId = geoId;
    }

    public Long getDivision() {
        return division;
    }

    public void setDivision(Long division) {
        this.division = division;
    }

    public List<CheckDamItemStatusData> getGetItemStatusData() {
        return getItemStatusData;
    }

    public void setGetItemStatusData(List<CheckDamItemStatusData> getItemStatusData) {
        this.getItemStatusData = getItemStatusData;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public String getAssembly() {
        return assembly;
    }

    public void setAssembly(String assembly) {
        this.assembly = assembly;
    }

    public Double getPreworkcapacity() {
        return preworkcapacity;
    }

    public void setPreworkcapacity(Double preworkcapacity) {
        this.preworkcapacity = preworkcapacity;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public Long getSubDivision() {
        return subDivision;
    }

    public void setSubDivision(Long subDivision) {
        this.subDivision = subDivision;
    }

    public Double getPostworkcapacity() {
        return postworkcapacity;
    }

    public void setPostworkcapacity(Double postworkcapacity) {
        this.postworkcapacity = postworkcapacity;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public long getTankCode() {
        return tankCode;
    }

    public void setTankCode(long tankCode) {
        this.tankCode = tankCode;
    }

    public String getHabitation() {
        return habitation;
    }

    public void setHabitation(String habitation) {
        this.habitation = habitation;
    }

    public Long getAyacut() {
        return ayacut;
    }

    public void setAyacut(Long ayacut) {
        this.ayacut = ayacut;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Long getUnit() {
        return unit;
    }

    public void setUnit(Long unit) {
        this.unit = unit;
    }

    public String getMcode() {
        return mcode;
    }

    public void setMcode(String mcode) {
        this.mcode = mcode;
    }

    public String getSubDivisionName() {
        return subDivisionName;
    }

    public void setSubDivisionName(String subDivisionName) {
        this.subDivisionName = subDivisionName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public Long getCircle() {
        return circle;
    }

    public void setCircle(Long circle) {
        this.circle = circle;
    }

    public String getHcode() {
        return hcode;
    }

    public void setHcode(String hcode) {
        this.hcode = hcode;
    }

    public String getDcode() {
        return dcode;
    }

    public void setDcode(String dcode) {
        this.dcode = dcode;
    }

    public String getMandal() {
        return mandal;
    }

    public void setMandal(String mandal) {
        this.mandal = mandal;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tankName);
        dest.writeString(this.latitude);
        dest.writeValue(this.tankId);
        dest.writeString(this.acode);
        dest.writeString(this.agmtMsg);
        dest.writeValue(this.bundlength);
        dest.writeString(this.vcode);
        dest.writeValue(this.geoId);
        dest.writeValue(this.division);
        dest.writeTypedList(this.getItemStatusData);
        dest.writeValue(this.sectionId);
        dest.writeString(this.assembly);
        dest.writeValue(this.preworkcapacity);
        dest.writeString(this.village);
        dest.writeString(this.circleName);
        dest.writeValue(this.subDivision);
        dest.writeValue(this.postworkcapacity);
        dest.writeString(this.longitude);
        dest.writeLong(this.tankCode);
        dest.writeString(this.habitation);
        dest.writeValue(this.ayacut);
        dest.writeString(this.sectionName);
        dest.writeString(this.unitName);
        dest.writeValue(this.unit);
        dest.writeString(this.mcode);
        dest.writeString(this.subDivisionName);
        dest.writeString(this.district);
        dest.writeString(this.divisionName);
        dest.writeValue(this.circle);
        dest.writeString(this.hcode);
        dest.writeString(this.dcode);
        dest.writeString(this.mandal);
        dest.writeString(this.agmt_status);
        dest.writeString(this.radius_msg);
        dest.writeString(this.image_path);
    }

    public CheckDamData() {
    }

    protected CheckDamData(Parcel in) {
        this.tankName = in.readString();
        this.latitude = in.readString();
        this.tankId = (Long) in.readValue(Long.class.getClassLoader());
        this.acode = in.readString();
        this.agmtMsg = in.readString();
        this.bundlength = (Long) in.readValue(Long.class.getClassLoader());
        this.vcode = in.readString();
        this.geoId = (Long) in.readValue(Long.class.getClassLoader());
        this.division = (Long) in.readValue(Long.class.getClassLoader());
        this.getItemStatusData = in.createTypedArrayList(CheckDamItemStatusData.CREATOR);
        this.sectionId = (Long) in.readValue(Long.class.getClassLoader());
        this.assembly = in.readString();
        this.preworkcapacity = (Double) in.readValue(Long.class.getClassLoader());
        this.village = in.readString();
        this.circleName = in.readString();
        this.subDivision = (Long) in.readValue(Long.class.getClassLoader());
        this.postworkcapacity = (Double) in.readValue(Long.class.getClassLoader());
        this.longitude = in.readString();
        this.tankCode = in.readLong();
        this.habitation = in.readString();
        this.ayacut = (Long) in.readValue(Long.class.getClassLoader());
        this.sectionName = in.readString();
        this.unitName = in.readString();
        this.unit = (Long) in.readValue(Long.class.getClassLoader());
        this.mcode = in.readString();
        this.subDivisionName = in.readString();
        this.district = in.readString();
        this.divisionName = in.readString();
        this.circle = (Long) in.readValue(Long.class.getClassLoader());
        this.hcode = in.readString();
        this.dcode = in.readString();
        this.mandal = in.readString();
        this.agmt_status = in.readString();
        this.radius_msg = in.readString();
        this.image_path = in.readString();
    }

    public static final Parcelable.Creator<CheckDamData> CREATOR = new Parcelable.Creator<CheckDamData>() {
        @Override
        public CheckDamData createFromParcel(Parcel source) {
            return new CheckDamData(source);
        }

        @Override
        public CheckDamData[] newArray(int size) {
            return new CheckDamData[size];
        }
    };
}
