package com.phantom.pickup.service;

import com.phantom.pickup.mapper.SignMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Service
@Transactional
public class SignService {

    @Resource
    private SignMapper signMapper;



    public void signUp(Map map) {
        signMapper.signUp(map);
    }



    public boolean signIn(Map map) {
        return signMapper.signIn(map).get("id") == null ? false : true;
    }

}
