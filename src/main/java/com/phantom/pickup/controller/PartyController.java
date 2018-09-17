package com.phantom.pickup.controller;

import com.phantom.pickup.model.PartyModel;
import com.phantom.pickup.service.PartyService;
import com.phantom.pickup.util.FacebookToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PartyController {

    @Autowired
    private FacebookToken facebookToken;

    @Autowired
    private PartyService partyService;



    /**
     * Create party with Facebook token
     *
     * @param facebook
     * @param partyModel
     */
    @RequestMapping(value = "/parties", method = RequestMethod.POST)
    public void createParty(@RequestHeader("facebook") String facebook,
                            @RequestBody PartyModel partyModel) {
        HashMap map = new HashMap();
        map.put("id", facebookToken.isUsable(facebook));
        map.put("partyModel", partyModel);

        partyService.createParty(map);
    }



    /**
     * Provide party lists only to authenticated users via Facebook token
     *
     * @param facebook
     * @return Parties
     */
    @RequestMapping(value = "/parties", method = RequestMethod.GET)
    public List<Map> getParties(@RequestHeader("facebook") String facebook) {
        HashMap map = new HashMap();
        map.put("id", facebookToken.isUsable(facebook));

        return partyService.getParties();
    }



    /**
     * Provide party only to authenticated users via Facebook token
     *
     * @param facebook
     * @param index
     * @return Party & Members
     */
    @RequestMapping(value = "/parties/{index}", method = RequestMethod.GET)
    public Map getParty(@RequestHeader("facebook") String facebook,
                        @PathVariable("index") String index) {
        HashMap map = new HashMap();
        map.put("id", facebookToken.isUsable(facebook));
        map.put("index", index);

        return partyService.getParty(map);
    }



    /**
     * Join the party through Facebook token
     *
     * @param facebook
     * @param index
     */
    @RequestMapping(value = "/parties/{index}/member", method = RequestMethod.POST)
    public void attendParty(@RequestHeader("facebook") String facebook,
                            @PathVariable("index") String index) {
        HashMap map = new HashMap();
        map.put("id", facebookToken.isUsable(facebook));
        map.put("index", index);

        partyService.attendParty(map);
    }



    /**
     * Cancel to join the party through Facebook token
     *
     * @param facebook
     * @param index
     */
    @RequestMapping(value = "/parties/{index}/member", method = RequestMethod.DELETE)
    public void defyParty(@RequestHeader("facebook") String facebook,
                          @PathVariable("index") String index) {
        HashMap map = new HashMap();
        map.put("id", facebookToken.isUsable(facebook));
        map.put("index", index);

        partyService.defyParty(map);
    }

}
