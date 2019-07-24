package cgg.gov.in.icadworks.model.request;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class OTUpdateRequestOffline {


//    private int id;
//    private String ots;
//    private String empId, des, post;
//
//    public String getEmpId() {
//        return empId;
//    }
//
//    public void setEmpId(String empId) {
//        this.empId = empId;
//    }
//
//    public String getDes() {
//        return des;
//    }
//
//    public void setDes(String des) {
//        this.des = des;
//    }
//
//    public String getPost() {
//        return post;
//    }
//
//    public void setPost(String post) {
//        this.post = post;
//    }
//
//    public OTUpdateRequest() {
//
//    }
//
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getOts() {
//        return ots;
//    }
//
//    public void setOts(String ots) {
//        this.ots = ots;
//    }

//    public static final String TABLE_NAME = "OTs";
//
//    public static final String COLUMN_ID = "id";
//    public static final String COLUMN_OTS = "ot_items";
//    public static final String COLUMN_STR_ID = "str_id";
//    public static final String COLUMN_STR_NO = "str_no";
//    public static final String COLUMN_Photo = "str_photo";
//    public static final String COLUMN_User = "str_user";
//    public static final String COLUMN_Pass = "str_pass";
//    public static final String COLUMN_EMPID = "str_empid";
//    public static final String COLUMN_DESI = "str_des";
//    public static final String COLUMN_POSTTYPE = "str_posttype";



//    // Create table SQL query
//    public static final String CREATE_TABLE =
//            "CREATE TABLE " + TABLE_NAME + "("
//                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                    + COLUMN_OTS + " TEXT,"
//                    + COLUMN_STR_ID + " TEXT,"
//                    + COLUMN_STR_NO + " TEXT,"
//                    + COLUMN_Photo + " TEXT,"
//                    + COLUMN_User + " TEXT,"
//                    + COLUMN_Pass + " TEXT,"
//                    + COLUMN_EMPID + " TEXT,"
//                    + COLUMN_DESI + " TEXT,"
//                    + COLUMN_POSTTYPE + " TEXT"
//                    + ")";


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

//    public OTUpdateRequest(int id, String ots, String structureId,
//                           String structureNo, String photo, String user,
//                           String password, String empid, String des, String post) {
//        this.id = id;
//        this.ots = ots;
//        this.structureId = structureId;
//        this.structureNo = structureNo;
//        this.photo = photo;
//        this.user = user;
//        this.password = password;
//        this.empId = empid;
//        this.des = des;
//        this.post = post;
//    }

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
