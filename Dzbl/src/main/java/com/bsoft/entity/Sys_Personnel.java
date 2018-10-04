package com.bsoft.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/19.
 */
public class Sys_Personnel implements Serializable {


    private String personId;
    private String personName;

    public String getPersonId() {
        return personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}