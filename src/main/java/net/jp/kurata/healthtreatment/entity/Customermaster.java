/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jp.kurata.healthtreatment.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author yosh
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customermaster.findAll", query = "SELECT c FROM Customermaster c"),
    @NamedQuery(name = "Customermaster.findById", query = "SELECT c FROM Customermaster c WHERE c.id = :id"),
    @NamedQuery(name = "Customermaster.findByRecorddate", query = "SELECT c FROM Customermaster c WHERE c.recorddate = :recorddate"),
    @NamedQuery(name = "Customermaster.findByRecordprogram", query = "SELECT c FROM Customermaster c WHERE c.recordprogram = :recordprogram"),
    @NamedQuery(name = "Customermaster.findByRecorduserid", query = "SELECT c FROM Customermaster c WHERE c.recorduserid = :recorduserid"),
    @NamedQuery(name = "Customermaster.findByRecordvalid", query = "SELECT c FROM Customermaster c WHERE c.recordvalid = :recordvalid"),
    @NamedQuery(name = "Customermaster.findByFullname", query = "SELECT c FROM Customermaster c WHERE c.fullname = :fullname"),
    @NamedQuery(name = "Customermaster.findByRuby", query = "SELECT c FROM Customermaster c WHERE c.ruby = :ruby"),
    @NamedQuery(name = "Customermaster.findBySex", query = "SELECT c FROM Customermaster c WHERE c.sex = :sex"),
    @NamedQuery(name = "Customermaster.findByZip", query = "SELECT c FROM Customermaster c WHERE c.zip = :zip"),
    @NamedQuery(name = "Customermaster.findByAddress", query = "SELECT c FROM Customermaster c WHERE c.address = :address"),
    @NamedQuery(name = "Customermaster.findByPhonenumber1", query = "SELECT c FROM Customermaster c WHERE c.phonenumber1 = :phonenumber1"),
    @NamedQuery(name = "Customermaster.findByPhonenumber2", query = "SELECT c FROM Customermaster c WHERE c.phonenumber2 = :phonenumber2"),
    @NamedQuery(name = "Customermaster.findByBirthday", query = "SELECT c FROM Customermaster c WHERE c.birthday = :birthday"),
    @NamedQuery(name = "Customermaster.findByCustomerinformationid", query = "SELECT c FROM Customermaster c WHERE c.customerinformationid = :customerinformationid")})
@SuppressWarnings("serial")
public class Customermaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date recorddate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String recordprogram;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String recorduserid;
    @Basic(optional = false)
    @NotNull
    private boolean recordvalid;
    @Size(max = 2147483647)
    private String fullname;
    @Size(max = 2147483647)
    private String ruby;
    @Size(max = 2147483647)
    private String sex;
    @Size(max = 2147483647)
    private String zip;
    @Size(max = 2147483647)
    private String address;
    @Size(max = 2147483647)
    private String phonenumber1;
    @Size(max = 2147483647)
    private String phonenumber2;
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    private Integer customerinformationid;

    public Customermaster() {
    }

    public Customermaster(Integer id) {
        this.id = id;
    }

    public Customermaster(Integer id, Date recorddate, String recordprogram, String recorduserid, boolean recordvalid) {
        this.id = id;
        this.recorddate = recorddate;
        this.recordprogram = recordprogram;
        this.recorduserid = recorduserid;
        this.recordvalid = recordvalid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getRecorddate() {
        return recorddate;
    }

    public void setRecorddate(Date recorddate) {
        this.recorddate = recorddate;
    }

    public String getRecordprogram() {
        return recordprogram;
    }

    public void setRecordprogram(String recordprogram) {
        this.recordprogram = recordprogram;
    }

    public String getRecorduserid() {
        return recorduserid;
    }

    public void setRecorduserid(String recorduserid) {
        this.recorduserid = recorduserid;
    }

    public boolean getRecordvalid() {
        return recordvalid;
    }

    public void setRecordvalid(boolean recordvalid) {
        this.recordvalid = recordvalid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRuby() {
        return ruby;
    }

    public void setRuby(String ruby) {
        this.ruby = ruby;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber1() {
        return phonenumber1;
    }

    public void setPhonenumber1(String phonenumber1) {
        this.phonenumber1 = phonenumber1;
    }

    public String getPhonenumber2() {
        return phonenumber2;
    }

    public void setPhonenumber2(String phonenumber2) {
        this.phonenumber2 = phonenumber2;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getCustomerinformationid() {
        return customerinformationid;
    }

    public void setCustomerinformationid(Integer customerinformationid) {
        this.customerinformationid = customerinformationid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customermaster)) {
            return false;
        }
        Customermaster other = (Customermaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.jp.kurata.healthtreatment.entity.Customermaster[ id=" + id + " ]";
    }

}
