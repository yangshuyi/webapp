package com.echodrama.pdfgen.model;

import com.echodrama.model.Employee;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yangsh
 * Date: 2/28/14
 * Time: 3:30 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "XMLModel")
@XmlType(name = "EmployeeListXMLModel")
public class EmployeeListXMLModel extends XMLModel {


    private List<Employee> employeeList;

    @XmlElementWrapper(name = "Employees")
    @XmlElement(name = "Employee")
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }


}
