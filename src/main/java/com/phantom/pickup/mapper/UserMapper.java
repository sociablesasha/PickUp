package com.phantom.pickup.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {

    Map getInfo(Map map);



    Map getPhoto(Map map);



    void putInfo(Map map);

}
