package com.phantom.pickup.controller;

import com.phantom.pickup.model.UserModel;
import com.phantom.pickup.service.SignService;
import com.phantom.pickup.util.FacebookToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class SignController {

    @Autowired
    private FacebookToken facebookToken;

    @Autowired
    private SignService signService;



    /**
     * Join via Facebook access token
     *
     * @param facebook
     * @param userModel
     */
    @RequestMapping(value = "/sign/up", method = RequestMethod.POST)
    public void signUp(@RequestHeader(value = "facebook") String facebook,
                       @RequestBody UserModel userModel) {
        userModel.photoAvailable(facebookToken.isUsable(facebook));
        HashMap map = new HashMap();
        map.put("id", facebookToken.isUsable(facebook));
        map.put("userModel", userModel);

        signService.signUp(map);
    }



    /**
     * Login via Facebook Access Token
     *
     * @param facebook
     */
    @RequestMapping(value = "/sign/in", method = RequestMethod.GET)
    public void signIn(@RequestHeader("facebook") String facebook) {
        HashMap map = new HashMap();
        map.put("id", facebookToken.isUsable(facebook));

        signService.signIn(map);
    }

}
