package com.bsoft.mapper;

import com.bsoft.entity.Sys_Personnel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2018/9/19.
 */
@Mapper
public interface UserMapper {

    public List<Sys_Personnel> findUserInfo();

}
