package com.bsoft.controller;

import com.bsoft.entity.Emr_Brjzsy;
import com.bsoft.entity.Sys_Personnel;
import com.bsoft.service.JzjlService;
import com.bsoft.service.Sys_PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/9/11.
 */
@RestController
public class MainController {

    @Autowired
    private JzjlService jzjlService;

    @RequestMapping("/getMedicalRecords")
    public Map<String,Object> getMedicalRecords(@RequestParam int patientIndex){
        Map<String,Object> map = new HashMap<String,Object>();

        List<Emr_Brjzsy> jzjl = jzjlService.findJzjl(patientIndex);
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int i=0;i<jzjl.size();i++){
            Map<String,Object> map1 = new HashMap<String,Object>();
            map1.put("xh",i+1);
            map1.put("lb",(jzjl.get(i).getPatientType()+"").equals("1") ? "门诊" : "住院");
            map1.put("gsyy",jzjl.get(i).getJgmc());
            map1.put("zd",jzjl.get(i).getDiagnosisName());
            map1.put("rq",jzjl.get(i).getClinicTime());
            map1.put("visited",jzjl.get(i).getVisited());
            list.add(map1);
        }
        map.put("records",list);
        return map;
    }


}
