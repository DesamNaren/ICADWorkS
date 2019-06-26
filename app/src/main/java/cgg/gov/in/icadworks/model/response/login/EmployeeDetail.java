package cgg.gov.in.icadworks.model.response.login;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="employeeDetail", strict = false)

public class EmployeeDetail {

    @Element(name = "empId", required = false)
    private String empId;

    @Element(name = "subdivisionId", required = false)
    private String subdivisionId;

    @Element(name = "postType", required = false)
    private String postType;

    @Element(name = "emailId", required = false)
    private String emailId;

    @Element(name = "section", required = false)
    private String section;

    @Element(name = "postId", required = false)
    private String postId;

    @Element(name = "sectionId", required = false)
    private String sectionId;

    @Element(name = "userName", required = false)
    private String userName;

    @Element(name = "division", required = false)
    private String division;

    @Element(name = "subdivision", required = false)
    private String subdivision;

    @Element(name = "unit", required = false)
    private String unit;

    @Element(name = "empName", required = false)
    private String empName;

    @Element(name = "unitId", required = false)
    private String unitId;

    @Element(name = "designation", required = false)
    private String designation;

    @Element(name = "circleId", required = false)
    private String circleId;

    @Element(name = "divisionId", required = false)
    private String divisionId;

    @Element(name = "circle", required = false)
    private String circle;

    @Element(name = "contactNo", required = false)
    private String contactNo;

    public String getEmpId () {
        return empId;
    }

    public void setEmpId (String empId)
    {
        this.empId = empId;
    }

    public String getSubdivisionId ()
    {
        return subdivisionId;
    }

    public void setSubdivisionId (String subdivisionId)
    {
        this.subdivisionId = subdivisionId;
    }

    public String getPostType ()
    {
        return postType;
    }

    public void setPostType (String postType)
    {
        this.postType = postType;
    }

    public String getEmailId ()
    {
        return emailId;
    }

    public void setEmailId (String emailId)
    {
        this.emailId = emailId;
    }

    public String getSection ()
    {
        return section;
    }

    public void setSection (String section)
    {
        this.section = section;
    }

    public String getPostId ()
    {
        return postId;
    }

    public void setPostId (String postId)
    {
        this.postId = postId;
    }

    public String getSectionId ()
    {
        return sectionId;
    }

    public void setSectionId (String sectionId)
    {
        this.sectionId = sectionId;
    }

    public String getUserName ()
    {
        return userName;
    }

    public void setUserName (String userName)
    {
        this.userName = userName;
    }

    public String getDivision ()
    {
        return division;
    }

    public void setDivision (String division)
    {
        this.division = division;
    }

    public String getSubdivision ()
    {
        return subdivision;
    }

    public void setSubdivision (String subdivision)
    {
        this.subdivision = subdivision;
    }

    public String getUnit ()
    {
        return unit;
    }

    public void setUnit (String unit)
    {
        this.unit = unit;
    }

    public String getEmpName ()
    {
        return empName;
    }

    public void setEmpName (String empName)
    {
        this.empName = empName;
    }

    public String getUnitId ()
    {
        return unitId;
    }

    public void setUnitId (String unitId)
    {
        this.unitId = unitId;
    }

    public String getDesignation ()
    {
        return designation;
    }

    public void setDesignation (String designation)
    {
        this.designation = designation;
    }

    public String getCircleId ()
    {
        return circleId;
    }

    public void setCircleId (String circleId)
    {
        this.circleId = circleId;
    }

    public String getDivisionId ()
    {
        return divisionId;
    }

    public void setDivisionId (String divisionId)
    {
        this.divisionId = divisionId;
    }

    public String getCircle ()
    {
        return circle;
    }

    public void setCircle (String circle)
    {
        this.circle = circle;
    }

    public String getContactNo ()
    {
        return contactNo;
    }

    public void setContactNo (String contactNo)
    {
        this.contactNo = contactNo;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [empId = "+empId+", subdivisionId = "+subdivisionId+", postType = "+postType+", emailId = "+emailId+", section = "+section+", postId = "+postId+", sectionId = "+sectionId+", userName = "+userName+", division = "+division+", subdivision = "+subdivision+", unit = "+unit+", empName = "+empName+", unitId = "+unitId+", designation = "+designation+", circleId = "+circleId+", divisionId = "+divisionId+", circle = "+circle+", contactNo = "+contactNo+"]";
    }
}
