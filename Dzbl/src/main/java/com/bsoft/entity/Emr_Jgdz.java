package com.bsoft.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/19.
 */
public class Emr_Jgdz implements Serializable {


    private int jgid;
    private String jgmc;
    private String ipUrl;
    private String databaseName;
    private String userId;
    private String pwd;
    private String bzxx;

    public void setJgid(int jgid) {
        jgid = jgid;
    }

    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }

    public void setIpUrl(String ipUrl) {
        this.ipUrl = ipUrl;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setBzxx(String bzxx) {
        this.bzxx = bzxx;
    }

    public int getJgid() {
        return jgid;
    }

    public String getJgmc() {
        return jgmc;
    }

    public String getIpUrl() {
        return ipUrl;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getUserId() {
        return userId;
    }

    public String getPwd() {
        return pwd;
    }

    public String getBzxx() {
        return bzxx;
    }
}