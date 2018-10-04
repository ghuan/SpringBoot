package com.bsoft.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/9/19.
 */
public class Emr_Brda implements Serializable {


    private int jlxh;
    private String patientIndex;
    private String brxm;
    private int brxb;
    private String sfzh;
    private Date csny;
    private String mz;
    private String dhhm;
    private int hyzk;
    private String zy;
    private String ybkh;
    private String dwdz;
    private String xzz;
    private String bzxx;

    public int getJlxh() {
        return jlxh;
    }

    public void setJlxh(int jlxh) {
        this.jlxh = jlxh;
    }

    public String getPatientIndex() {
        return patientIndex;
    }

    public void setPatientIndex(String patientIndex) {
        this.patientIndex = patientIndex;
    }

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public int getBrxb() {
        return brxb;
    }

    public void setBrxb(int brxb) {
        this.brxb = brxb;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public Date getCsny() {
        return csny;
    }

    public void setCsny(Date csny) {
        this.csny = csny;
    }

    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    public String getDhhm() {
        return dhhm;
    }

    public void setDhhm(String dhhm) {
        this.dhhm = dhhm;
    }

    public int getHyzk() {
        return hyzk;
    }

    public void setHyzk(int hyzk) {
        this.hyzk = hyzk;
    }

    public String getZy() {
        return zy;
    }

    public void setZy(String zy) {
        this.zy = zy;
    }

    public String getYbkh() {
        return ybkh;
    }

    public void setYbkh(String ybkh) {
        this.ybkh = ybkh;
    }

    public String getDwdz() {
        return dwdz;
    }

    public void setDwdz(String dwdz) {
        this.dwdz = dwdz;
    }

    public String getXzz() {
        return xzz;
    }

    public void setXzz(String xzz) {
        this.xzz = xzz;
    }

    public String getBzxx() {
        return bzxx;
    }

    public void setBzxx(String bzxx) {
        this.bzxx = bzxx;
    }
}