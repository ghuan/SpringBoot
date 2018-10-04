package com.bsoft.service;

import com.bsoft.entity.Emr_Brda;
import com.bsoft.entity.Emr_Brjzsy;
import com.bsoft.entity.Sys_Personnel;

import java.util.List;

/**
 * Created by Administrator on 2018/9/19.
 */
public interface JzjlService {
    public List<Emr_Brjzsy> findJzjl(int patientIndex);
    public String getUserName(int patientIndex);
    public Emr_Brda getBrda(int patientIndex);
}
