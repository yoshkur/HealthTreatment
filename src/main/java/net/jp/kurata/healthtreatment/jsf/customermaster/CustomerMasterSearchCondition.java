/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jp.kurata.healthtreatment.jsf.customermaster;

import java.util.Date;
import net.jp.kurata.healthtreatment.jsf.util.SearchCondition;

/**
 *
 * @author yosh
 */
public class CustomerMasterSearchCondition extends SearchCondition {

    private String fullname;
    private String ruby;
    private String sex;
    private String zip;
    private String address;
    private String phonenumber1;
    private String phonenumber2;
    private Date birthday;
    private Integer customerinformationid;

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
}
