package com.bsoft.service.imp;

import com.bsoft.entity.Sys_Personnel;
import com.bsoft.mapper.UserMapper;
import com.bsoft.service.Sys_PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/9/19.
 */

@Service
public class Sys_PersonnelServiceImpl implements Sys_PersonnelService {

    @Autowired
    private UserMapper userMapper;

    public List<Sys_Personnel> getUserInfo(){
        return userMapper.findUserInfo();
    }
}
