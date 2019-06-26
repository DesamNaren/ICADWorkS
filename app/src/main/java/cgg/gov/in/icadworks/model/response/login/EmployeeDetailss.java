package cgg.gov.in.icadworks.model.response.login;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name="employeeDetailss", strict = false)

public class EmployeeDetailss {

    @ElementList(name="employeeDetail", inline = true)

    private ArrayList<EmployeeDetail> employeeDetail;

    public ArrayList<EmployeeDetail> getEmployeeDetail () {
        return employeeDetail;
    }

    public void setEmployeeDetail (ArrayList<EmployeeDetail> employeeDetail) {
        this.employeeDetail = employeeDetail;
    }

    @Override
    public String toString() {
        return "ClassPojo [employeeDetail = "+employeeDetail+"]";
    }
}
