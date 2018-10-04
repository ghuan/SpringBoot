package com.bsoft.controller;

import com.bsoft.entity.Emr_Brda;
import com.bsoft.service.JzjlService;
import com.bsoft.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2018/9/11.
 */
@Controller
public class MedicalController {

    @Autowired
    private JzjlService jzjlService;

    @RequestMapping("/showMedicalRecords")
    public String showMedicalRecords(@RequestParam int patientIndex,   Map<String,Object> map){
        Emr_Brda brda = jzjlService.getBrda(patientIndex);
        map.put("brda",brda);
        map.put("brxb",brda.getBrxb() == 1 ? "男" : "女");
        map.put("csny",new SimpleDateFormat("yyyy-MM-dd").format(brda.getCsny()));
        map.put("age", DateUtil.getPersonAge(brda.getCsny(),new Date()).get("ages") + "");
        return "index";
    }
}
