package com.phantom.pickup.service;

import com.phantom.pickup.mapper.LogMapper;
import com.phantom.pickup.mapper.PartyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PartyService {

    @Resource
    private PartyMapper partyMapper;

    @Resource
    private LogMapper logMapper;



    public void createParty(Map map) {
        partyMapper.createParty(map);
        logMapper.createLog(map);
    }



    public List<Map> getParties() {
        return partyMapper.getParties();
    }



    public Map getParty(Map map) {
        map.put("party", partyMapper.getParty(map));
        map.put("members", partyMapper.getMembers(map));

        return map;
    }


    public void attendParty(Map map) {
        logMapper.attendLog(map);
        partyMapper.attendParty(map);
    }



    public void defyParty(Map map) {
        logMapper.defyLog(map);
        partyMapper.defyParty(map);
    }

}
