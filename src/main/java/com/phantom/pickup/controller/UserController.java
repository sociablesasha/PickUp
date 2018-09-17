package com.phantom.pickup.controller;

import com.phantom.pickup.model.UserModel;
import com.phantom.pickup.service.UserService;
import com.phantom.pickup.util.FacebookToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FacebookToken facebookToken;



    /**
     * Get user information from Facebook token
     *
     * @param facebook
     * @return User
     */
    @RequestMapping(value = "/users/info", method = RequestMethod.GET)
    public Map getInfo(@RequestHeader("facebook") String facebook) {
        HashMap map = new HashMap();
        map.put("id", facebookToken.isUsable(facebook));

        return userService.getInfo(map);
    }



    /**
     * Edit your Facebook token user information
     *
     * @param facebook
     * @param userModel
     */
    @RequestMapping(value = "/users/info", method = RequestMethod.PUT)
    public void putInfo(@RequestHeader(value = "facebook") String facebook,
                        @RequestBody UserModel userModel) {
        HashMap map = new HashMap();
        map.put("id", facebookToken.isUsable(facebook));
        map.put("userModel", userModel);

        userService.putInfo(map);
    }



    /**
     * Return user picture via Facebook ID
     *
     * @param id
     * @return User-Photo
     */
    @RequestMapping(value = "/users/{id}/photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPhoto(@PathVariable String id) {
        HashMap map = new HashMap();
        map.put("id", id);

        return (byte[]) userService.getPhoto(map).get("photo");
    }



    /**
     * Return user logs with Facebook token
     *
     * @param facebook
     * @return User-Logs
     */
    @RequestMapping(value = "/users/logs", method = RequestMethod.GET)
    public List<Map> getLogs(@RequestHeader(value = "facebook") String facebook) {
        HashMap map = new HashMap();
        map.put("id", facebookToken.isUsable(facebook));

        return userService.getLogs(map);
    }

}
