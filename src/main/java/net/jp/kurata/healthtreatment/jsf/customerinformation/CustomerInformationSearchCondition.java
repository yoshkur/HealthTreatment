/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jp.kurata.healthtreatment.jsf.customerinformation;

import java.util.Date;
import net.jp.kurata.healthtreatment.jsf.util.SearchCondition;

/**
 *
 * @author yosh
 */
public class CustomerInformationSearchCondition extends SearchCondition {

    private Date firstvisitdate;
    private String introducer;
    private String relationship;
    private String anamnesis;
    private String familymember;
    private String familymedicalhistory;
    private String job;
    private String eating;
    private String sleeping;
    private String exercise;
    private String smokingdrinking;

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

}
