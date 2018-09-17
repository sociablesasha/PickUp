package com.phantom.pickup.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface SignMapper {

    void signUp(Map map);



    Map signIn(Map map);

}
