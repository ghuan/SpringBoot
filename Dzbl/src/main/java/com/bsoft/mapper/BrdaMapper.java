package com.bsoft.mapper;

import com.bsoft.entity.Emr_Brda;
import com.bsoft.entity.Emr_Brjzsy;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2018/9/19.
 */
@Mapper
public interface BrdaMapper {

    public Emr_Brda getBrda(int patientIndex);
}
