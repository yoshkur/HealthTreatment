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
    @NamedQuery(name = "Customerinformation.findAll", query = "SELECT c FROM Customerinformation c"),
    @NamedQuery(name = "Customerinformation.findById", query = "SELECT c FROM Customerinformation c WHERE c.id = :id"),
    @NamedQuery(name = "Customerinformation.findByRecorddate", query = "SELECT c FROM Customerinformation c WHERE c.recorddate = :recorddate"),
    @NamedQuery(name = "Customerinformation.findByRecordprogram", query = "SELECT c FROM Customerinformation c WHERE c.recordprogram = :recordprogram"),
    @NamedQuery(name = "Customerinformation.findByRecorduserid", query = "SELECT c FROM Customerinformation c WHERE c.recorduserid = :recorduserid"),
    @NamedQuery(name = "Customerinformation.findByRecordvalid", query = "SELECT c FROM Customerinformation c WHERE c.recordvalid = :recordvalid"),
    @NamedQuery(name = "Customerinformation.findByCustomerid", query = "SELECT c FROM Customerinformation c WHERE c.customerid = :customerid"),
    @NamedQuery(name = "Customerinformation.findByFirstvisitdate", query = "SELECT c FROM Customerinformation c WHERE c.firstvisitdate = :firstvisitdate"),
    @NamedQuery(name = "Customerinformation.findByIntroducer", query = "SELECT c FROM Customerinformation c WHERE c.introducer = :introducer"),
    @NamedQuery(name = "Customerinformation.findByRelationship", query = "SELECT c FROM Customerinformation c WHERE c.relationship = :relationship"),
    @NamedQuery(name = "Customerinformation.findByAnamnesis", query = "SELECT c FROM Customerinformation c WHERE c.anamnesis = :anamnesis"),
    @NamedQuery(name = "Customerinformation.findByFamilymember", query = "SELECT c FROM Customerinformation c WHERE c.familymember = :familymember"),
    @NamedQuery(name = "Customerinformation.findByFamilymedicalhistory", query = "SELECT c FROM Customerinformation c WHERE c.familymedicalhistory = :familymedicalhistory"),
    @NamedQuery(name = "Customerinformation.findByJob", query = "SELECT c FROM Customerinformation c WHERE c.job = :job"),
    @NamedQuery(name = "Customerinformation.findByEating", query = "SELECT c FROM Customerinformation c WHERE c.eating = :eating"),
    @NamedQuery(name = "Customerinformation.findBySleeping", query = "SELECT c FROM Customerinformation c WHERE c.sleeping = :sleeping"),
    @NamedQuery(name = "Customerinformation.findByExercise", query = "SELECT c FROM Customerinformation c WHERE c.exercise = :exercise"),
    @NamedQuery(name = "Customerinformation.findBySmokingdrinking", query = "SELECT c FROM Customerinformation c WHERE c.smokingdrinking = :smokingdrinking")})
@SuppressWarnings("serial")
public class Customerinformation implements Serializable {

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
    private Date firstvisitdate;
    @Size(max = 2147483647)
    private String introducer;
    @Size(max = 2147483647)
    private String relationship;
    @Size(max = 2147483647)
    private String anamnesis;
    @Size(max = 2147483647)
    private String familymember;
    @Size(max = 2147483647)
    private String familymedicalhistory;
    @Size(max = 2147483647)
    private String job;
    @Size(max = 2147483647)
    private String eating;
    @Size(max = 2147483647)
    private String sleeping;
    @Size(max = 2147483647)
    private String exercise;
    @Size(max = 2147483647)
    private String smokingdrinking;

    public Customerinformation() {
    }

    public Customerinformation(Integer id) {
        this.id = id;
    }

    public Customerinformation(Integer id, Date recorddate, String recordprogram, String recorduserid, boolean recordvalid, int customerid) {
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

    public Date getFirstvisitdate() {
        return firstvisitdate;
    }

    public void setFirstvisitdate(Date firstvisitdate) {
        this.firstvisitdate = firstvisitdate;
    }

    public String getIntroducer() {
        return introducer;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getAnamnesis() {
        return anamnesis;
    }

    public void setAnamnesis(String anamnesis) {
        this.anamnesis = anamnesis;
    }

    public String getFamilymember() {
        return familymember;
    }

    public void setFamilymember(String familymember) {
        this.familymember = familymember;
    }

    public String getFamilymedicalhistory() {
        return familymedicalhistory;
    }

    public void setFamilymedicalhistory(String familymedicalhistory) {
        this.familymedicalhistory = familymedicalhistory;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEating() {
        return eating;
    }

    public void setEating(String eating) {
        this.eating = eating;
    }

    public String getSleeping() {
        return sleeping;
    }

    public void setSleeping(String sleeping) {
        this.sleeping = sleeping;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String getSmokingdrinking() {
        return smokingdrinking;
    }

    public void setSmokingdrinking(String smokingdrinking) {
        this.smokingdrinking = smokingdrinking;
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
        if (!(object instanceof Customerinformation)) {
            return false;
        }
        Customerinformation other = (Customerinformation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.jp.kurata.healthtreatment.entity.Customerinformation[ id=" + id + " ]";
    }

}
