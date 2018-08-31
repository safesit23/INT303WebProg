/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.int303.first.jpa.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author INT303
 */
@Entity
@Table(name = "OFFICE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Office.findAll", query = "SELECT o FROM Office o")
    , @NamedQuery(name = "Office.findByOfficecode", query = "SELECT o FROM Office o WHERE o.officecode = :officecode")
    , @NamedQuery(name = "Office.findByCity", query = "SELECT o FROM Office o WHERE o.city = :city")
    , @NamedQuery(name = "Office.findByPhone", query = "SELECT o FROM Office o WHERE o.phone = :phone")
    , @NamedQuery(name = "Office.findByAddressline1", query = "SELECT o FROM Office o WHERE o.addressline1 = :addressline1")
    , @NamedQuery(name = "Office.findByAddressline2", query = "SELECT o FROM Office o WHERE o.addressline2 = :addressline2")
    , @NamedQuery(name = "Office.findByState", query = "SELECT o FROM Office o WHERE o.state = :state")
    , @NamedQuery(name = "Office.findByCountry", query = "SELECT o FROM Office o WHERE o.country = :country")
    , @NamedQuery(name = "Office.findByPostalcode", query = "SELECT o FROM Office o WHERE o.postalcode = :postalcode")
    , @NamedQuery(name = "Office.findByTerritory", query = "SELECT o FROM Office o WHERE o.territory = :territory")})
public class Office implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "OFFICECODE")
    private String officecode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CITY")
    private String city;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PHONE")
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ADDRESSLINE1")
    private String addressline1;
    @Size(max = 50)
    @Column(name = "ADDRESSLINE2")
    private String addressline2;
    @Size(max = 50)
    @Column(name = "STATE")
    private String state;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "COUNTRY")
    private String country;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "POSTALCODE")
    private String postalcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "TERRITORY")
    private String territory;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "officecode")
    private List<Employee> employeeList;

    public Office() {
    }

    public Office(String officecode) {
        this.officecode = officecode;
    }

    public Office(String officecode, String city, String phone, String addressline1, String country, String postalcode, String territory) {
        this.officecode = officecode;
        this.city = city;
        this.phone = phone;
        this.addressline1 = addressline1;
        this.country = country;
        this.postalcode = postalcode;
        this.territory = territory;
    }

    public String getOfficecode() {
        return officecode;
    }

    public void setOfficecode(String officecode) {
        this.officecode = officecode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    @XmlTransient
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (officecode != null ? officecode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Office)) {
            return false;
        }
        Office other = (Office) object;
        if ((this.officecode == null && other.officecode != null) || (this.officecode != null && !this.officecode.equals(other.officecode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sit.int303.first.jpa.model.Office[ officecode=" + officecode + " ]";
    }
    
}
