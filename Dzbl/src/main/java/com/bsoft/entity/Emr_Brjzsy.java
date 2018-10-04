package com.bsoft.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/9/19.
 */
public class Emr_Brjzsy implements Serializable {


    private String patientIndex;
    private String patientName;
    private Date clinicTime;
    private int jgid;
    private String jgmc;
    private String icd10;
    private String diagnosisName;
    private int patientType;
    private String visited;
    private String bzxx;

    public String getJgmc() {
        return jgmc;
    }

    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }

    public void setPatientIndex(String patientIndex) {
        this.patientIndex = patientIndex;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setClinicTime(Date clinicTime) {
        this.clinicTime = clinicTime;
    }

    public void setJgid(int jgid) {
        this.jgid = jgid;
    }

    public void setIcd10(String icd10) {
        this.icd10 = icd10;
    }

    public void setDiagnosisName(String diagnosisName) {
        this.diagnosisName = diagnosisName;
    }

    public void setPatientType(int patientType) {
        this.patientType = patientType;
    }

    public void setVisited(String visited) {
        this.visited = visited;
    }

    public void setBzxx(String bzxx) {
        this.bzxx = bzxx;
    }

    public String getPatientIndex() {
        return patientIndex;
    }

    public String getPatientName() {
        return patientName;
    }

    public Date getClinicTime() {
        return clinicTime;
    }

    public int getJgid() {
        return jgid;
    }

    public String getIcd10() {
        return icd10;
    }

    public String getDiagnosisName() {
        return diagnosisName;
    }

    public int getPatientType() {
        return patientType;
    }

    public String getVisited() {
        return visited;
    }

    public String getBzxx() {
        return bzxx;
    }
}