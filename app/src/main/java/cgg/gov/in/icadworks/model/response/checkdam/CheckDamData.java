package cgg.gov.in.icadworks.model.response.checkdam;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckDamData {

    @SerializedName("tank_name")
    @Expose
    private String tankName;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("tank_id")
    @Expose
    private Integer tankId;
    @SerializedName("acode")
    @Expose
    private String acode;
    @SerializedName("bundlength")
    @Expose
    private Integer bundlength;
    @SerializedName("vcode")
    @Expose
    private String vcode;
    @SerializedName("geo_id")
    @Expose
    private Integer geoId;
    @SerializedName("division")
    @Expose
    private Integer division;
    @SerializedName("getItemStatusData")
    @Expose
    private List<CheckDamItemStatusData> getItemStatusData = null;
    @SerializedName("section_id")
    @Expose
    private Integer sectionId;
    @SerializedName("assembly")
    @Expose
    private String assembly;
    @SerializedName("preworkcapacity")
    @Expose
    private Integer preworkcapacity;
    @SerializedName("village")
    @Expose
    private String village;
    @SerializedName("circle_name")
    @Expose
    private String circleName;
    @SerializedName("sub_division")
    @Expose
    private Integer subDivision;
    @SerializedName("postworkcapacity")
    @Expose
    private Integer postworkcapacity;
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
    private Integer ayacut;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("unit_name")
    @Expose
    private String unitName;
    @SerializedName("unit")
    @Expose
    private Integer unit;
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
    private Integer circle;
    @SerializedName("hcode")
    @Expose
    private String hcode;
    @SerializedName("dcode")
    @Expose
    private String dcode;
    @SerializedName("mandal")
    @Expose
    private String mandal;

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

    public Integer getTankId() {
        return tankId;
    }

    public void setTankId(Integer tankId) {
        this.tankId = tankId;
    }

    public String getAcode() {
        return acode;
    }

    public void setAcode(String acode) {
        this.acode = acode;
    }

    public Integer getBundlength() {
        return bundlength;
    }

    public void setBundlength(Integer bundlength) {
        this.bundlength = bundlength;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public Integer getGeoId() {
        return geoId;
    }

    public void setGeoId(Integer geoId) {
        this.geoId = geoId;
    }

    public Integer getDivision() {
        return division;
    }

    public void setDivision(Integer division) {
        this.division = division;
    }

    public List<CheckDamItemStatusData> getGetItemStatusData() {
        return getItemStatusData;
    }

    public void setGetItemStatusData(List<CheckDamItemStatusData> getItemStatusData) {
        this.getItemStatusData = getItemStatusData;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getAssembly() {
        return assembly;
    }

    public void setAssembly(String assembly) {
        this.assembly = assembly;
    }

    public Integer getPreworkcapacity() {
        return preworkcapacity;
    }

    public void setPreworkcapacity(Integer preworkcapacity) {
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

    public Integer getSubDivision() {
        return subDivision;
    }

    public void setSubDivision(Integer subDivision) {
        this.subDivision = subDivision;
    }

    public Integer getPostworkcapacity() {
        return postworkcapacity;
    }

    public void setPostworkcapacity(Integer postworkcapacity) {
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

    public Integer getAyacut() {
        return ayacut;
    }

    public void setAyacut(Integer ayacut) {
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

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
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

    public Integer getCircle() {
        return circle;
    }

    public void setCircle(Integer circle) {
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

}
