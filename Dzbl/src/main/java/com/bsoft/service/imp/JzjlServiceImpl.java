package com.bsoft.service.imp;

import com.bsoft.entity.Emr_Brda;
import com.bsoft.entity.Emr_Brjzsy;
import com.bsoft.entity.Sys_Personnel;
import com.bsoft.mapper.BrdaMapper;
import com.bsoft.mapper.BrjzsyMapper;
import com.bsoft.mapper.UserMapper;
import com.bsoft.service.JzjlService;
import com.bsoft.service.Sys_PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/9/19.
 */

@Service
public class JzjlServiceImpl implements JzjlService {

    @Autowired
    private BrjzsyMapper brjzsyMapper;
    @Autowired
    private BrdaMapper brdaMapper;

    public List<Emr_Brjzsy> findJzjl(int patientIndex){
        return brjzsyMapper.findJzjl(patientIndex);
    }

    @Override
    public String getUserName(int patientIndex) {
        Emr_Brjzsy entity = brjzsyMapper.getUserName(patientIndex);
        return entity.getPatientName();
    }

    @Override
    public Emr_Brda getBrda(int patientIndex) {
        return brdaMapper.getBrda(patientIndex);
    }
}
