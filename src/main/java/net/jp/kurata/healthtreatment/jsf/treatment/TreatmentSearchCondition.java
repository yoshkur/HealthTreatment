/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jp.kurata.healthtreatment.jsf.treatment;

import java.util.Date;
import net.jp.kurata.healthtreatment.jsf.util.SearchCondition;

/**
 *
 * @author yosh
 */
public class TreatmentSearchCondition extends SearchCondition {

    private Integer customerid;
    private Date treatmentdateStart;
    private Date treatmentdateEnd;
    private String chiefcomplaint;
    private String examination;
    private String treatment;
    private String treatmentresult;
    private Boolean attachedfile;
    private String attachedfilename;

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public Date getTreatmentdateStart() {
        return treatmentdateStart;
    }

    public void setTreatmentdateStart(Date treatmentdateStart) {
        this.treatmentdateStart = treatmentdateStart;
    }

    public Date getTreatmentdateEnd() {
        return treatmentdateEnd;
    }

    public void setTreatmentdateEnd(Date treatmentdateEnd) {
        this.treatmentdateEnd = treatmentdateEnd;
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

}
