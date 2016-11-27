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
    @NamedQuery(name = "Treatmentattachedfile.findAll", query = "SELECT t FROM Treatmentattachedfile t"),
    @NamedQuery(name = "Treatmentattachedfile.findById", query = "SELECT t FROM Treatmentattachedfile t WHERE t.id = :id"),
    @NamedQuery(name = "Treatmentattachedfile.findByRecorddate", query = "SELECT t FROM Treatmentattachedfile t WHERE t.recorddate = :recorddate"),
    @NamedQuery(name = "Treatmentattachedfile.findByRecordprogram", query = "SELECT t FROM Treatmentattachedfile t WHERE t.recordprogram = :recordprogram"),
    @NamedQuery(name = "Treatmentattachedfile.findByRecorduserid", query = "SELECT t FROM Treatmentattachedfile t WHERE t.recorduserid = :recorduserid"),
    @NamedQuery(name = "Treatmentattachedfile.findByRecordvalid", query = "SELECT t FROM Treatmentattachedfile t WHERE t.recordvalid = :recordvalid"),
    @NamedQuery(name = "Treatmentattachedfile.findByAttachedfilename", query = "SELECT t FROM Treatmentattachedfile t WHERE t.attachedfilename = :attachedfilename"),
    @NamedQuery(name = "Treatmentattachedfile.findByAttachedfiledata", query = "SELECT t FROM Treatmentattachedfile t WHERE t.attachedfiledata = :attachedfiledata")})
public class Treatmentattachedfile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
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
    private String attachedfilename;
    @Lob
    private byte[] attachedfiledata;

    public Treatmentattachedfile() {
    }

    public Treatmentattachedfile(Integer id) {
        this.id = id;
    }

    public Treatmentattachedfile(Integer id, Date recorddate, String recordprogram, String recorduserid, boolean recordvalid) {
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

    public String getAttachedfilename() {
        return attachedfilename;
    }

    public void setAttachedfilename(String attachedfilename) {
        this.attachedfilename = attachedfilename;
    }

    public byte[] getAttachedfiledata() {
        return attachedfiledata;
    }

    public void setAttachedfiledata(byte[] attachedfiledata) {
        this.attachedfiledata = attachedfiledata;
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
        if (!(object instanceof Treatmentattachedfile)) {
            return false;
        }
        Treatmentattachedfile other = (Treatmentattachedfile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.jp.kurata.healthtreatment.entity.Treatmentattachedfile[ id=" + id + " ]";
    }

}
