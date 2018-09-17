package com.phantom.pickup.service;

import com.phantom.pickup.mapper.LogMapper;
import com.phantom.pickup.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private LogMapper logMapper;



    public Map getInfo(Map map) {
        return userMapper.getInfo(map);
    }



    public Map getPhoto(Map map) {
        return userMapper.getPhoto(map);
    }



    public void putInfo(Map map) {
        userMapper.putInfo(map);
    }



    public List<Map> getLogs(Map map) {
        return logMapper.getLogs(map);
    }

}
