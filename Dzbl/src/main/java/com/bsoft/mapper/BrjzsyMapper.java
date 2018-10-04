package com.bsoft.mapper;

import com.bsoft.entity.Emr_Brjzsy;
import com.bsoft.entity.Sys_Personnel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2018/9/19.
 */
@Mapper
public interface BrjzsyMapper {

    public List<Emr_Brjzsy> findJzjl(int patientIndex);
    public Emr_Brjzsy getUserName(int patientIndex);
}
