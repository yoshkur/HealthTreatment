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
import javax.persistence.Lob;
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
    @NamedQuery(name = "Treatment.findAll", query = "SELECT t FROM Treatment t"),
    @NamedQuery(name = "Treatment.findById", query = "SELECT t FROM Treatment t WHERE t.id = :id"),
    @NamedQuery(name = "Treatment.findByRecorddate", query = "SELECT t FROM Treatment t WHERE t.recorddate = :recorddate"),
    @NamedQuery(name = "Treatment.findByRecordprogram", query = "SELECT t FROM Treatment t WHERE t.recordprogram = :recordprogram"),
    @NamedQuery(name = "Treatment.findByRecorduserid", query = "SELECT t FROM Treatment t WHERE t.recorduserid = :recorduserid"),
    @NamedQuery(name = "Treatment.findByRecordvalid", query = "SELECT t FROM Treatment t WHERE t.recordvalid = :recordvalid"),
    @NamedQuery(name = "Treatment.findByCustomerid", query = "SELECT t FROM Treatment t WHERE t.customerid = :customerid"),
    @NamedQuery(name = "Treatment.findByTreatmentdate", query = "SELECT t FROM Treatment t WHERE t.treatmentdate = :treatmentdate"),
    @NamedQuery(name = "Treatment.findByChiefcomplaint", query = "SELECT t FROM Treatment t WHERE t.chiefcomplaint = :chiefcomplaint"),
    @NamedQuery(name = "Treatment.findByExamination", query = "SELECT t FROM Treatment t WHERE t.examination = :examination"),
    @NamedQuery(name = "Treatment.findByTreatment", query = "SELECT t FROM Treatment t WHERE t.treatment = :treatment"),
    @NamedQuery(name = "Treatment.findByTreatmentresult", query = "SELECT t FROM Treatment t WHERE t.treatmentresult = :treatmentresult"),
    @NamedQuery(name = "Treatment.findByAttachedfile", query = "SELECT t FROM Treatment t WHERE t.attachedfile = :attachedfile"),
    @NamedQuery(name = "Treatment.findByAttachedfilename", query = "SELECT t FROM Treatment t WHERE t.attachedfilename = :attachedfilename")})
@SuppressWarnings("serial")
public class Treatment implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    private int customerid;
    @Temporal(TemporalType.TIMESTAMP)
    private Date treatmentdate;
    @Size(max = 2147483647)
    private String chiefcomplaint;
    @Size(max = 2147483647)
    private String examination;
    @Size(max = 2147483647)
    private String treatment;
    @Size(max = 2147483647)
    private String treatmentresult;
    private Boolean attachedfile;
    @Size(max = 2147483647)
    private String attachedfilename;
//    @Lob
//    private byte[] attachedfiledata;

    public Treatment() {
    }

    public Treatment(Integer id) {
        this.id = id;
    }

    public Treatment(Integer id, Date recorddate, String recordprogram, String recorduserid, boolean recordvalid, int customerid) {
        this.id = id;
        this.recorddate = recorddate;
        this.recordprogram = recordprogram;
        this.recorduserid = recorduserid;
        this.recordvalid = recordvalid;
        this.customerid = customerid;
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

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public Date getTreatmentdate() {
        return treatmentdate;
    }

    public void setTreatmentdate(Date treatmentdate) {
        this.treatmentdate = treatmentdate;
    }

    public String getChiefcomplaint() {
        return chiefcomplaint;
    }

    public void setChiefcomplaint(String chiefcomplaint) {
        this.chiefcomplaint = chiefcomplaint;
    }

    public String getExamination() {
        return examination;
    }

    public void setExamination(String examination) {
        this.examination = examination;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getTreatmentresult() {
        return treatmentresult;
    }

    public void setTreatmentresult(String treatmentresult) {
        this.treatmentresult = treatmentresult;
    }

    public Boolean getAttachedfile() {
        return attachedfile;
    }

    public void setAttachedfile(Boolean attachedfile) {
        this.attachedfile = attachedfile;
    }

    public String getAttachedfilename() {
        return attachedfilename;
    }

    public void setAttachedfilename(String attachedfilename) {
        this.attachedfilename = attachedfilename;
    }

//    public byte[] getAttachedfiledata() {
//        return attachedfiledata;
//    }
//
//    public void setAttachedfiledata(byte[] attachedfiledata) {
//        this.attachedfiledata = attachedfiledata;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Treatment)) {
            return false;
        }
        Treatment other = (Treatment) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "net.jp.kurata.healthtreatment.entity.Treatment[ id=" + id + " ]";
    }

}
