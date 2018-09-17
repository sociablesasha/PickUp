package com.phantom.pickup.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PartyMapper {

    void createParty(Map map);



    List<Map> getParties();



    Map getParty(Map map);



    List<Map> getMembers(Map map);



    void attendParty(Map map);



    void defyParty(Map map);

}
