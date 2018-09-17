package com.phantom.pickup.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface LogMapper {

    void createLog(Map map);



    List<Map> getLogs(Map map);



    void attendLog(Map map);



    void defyLog(Map map);

}
