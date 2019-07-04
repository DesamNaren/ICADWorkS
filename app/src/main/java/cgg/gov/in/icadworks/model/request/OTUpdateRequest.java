package cgg.gov.in.icadworks.model.request;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class OTUpdateRequest{

    @SerializedName("itemslist")
    @Expose
    private List<OTItemReq> OTItemReq = null;
    @SerializedName("structure_no")
    @Expose
    private String structureNo;
    @SerializedName("structure_id")
    @Expose
    private String structureId;

    @SerializedName("photo")
    @Expose
    private String photo;

    @SerializedName("user")
    @Expose
    private String user;

    @SerializedName("password")
    @Expose
    private String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<OTItemReq> getOTItemReq() {
        return OTItemReq;
    }

    public void setOTItemReq(List<OTItemReq> OTItemReq) {
        this.OTItemReq = OTItemReq;
    }

    public String getStructureNo() {
        return structureNo;
    }

    public void setStructureNo(String structureNo) {
        this.structureNo = structureNo;
    }

    public String getStructureId() {
        return structureId;
    }

    public void setStructureId(String structureId) {
        this.structureId = structureId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
