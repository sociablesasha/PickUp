package com.phantom.pickup.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.ws.rs.NotAuthorizedException;
import java.net.URI;
import java.util.Map;

@Component
public class FacebookToken {

    public String isUsable(String facebook) {
        Map<String, Object> map;
        ObjectMapper mapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();

        URI uri = UriComponentsBuilder.newInstance().scheme("https").host("graph.facebook.com")
                .path("/me")
                .queryParam("fields", "id")
                .queryParam("access_token", facebook)
                .build()
                .encode()
                .toUri();

        try {
            map = mapper.readValue(restTemplate.getForObject(uri, String.class), new TypeReference<Map<String, String>>(){});
        } catch (Exception exception) {
            throw new NotAuthorizedException("Invalid token.");
        }

        return (String) map.get("id");
    }



    public byte[] getDefaultPhoto(String id) {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = UriComponentsBuilder.newInstance().scheme("https").host("graph.facebook.com")
                .path("/" + id)
                .path("/picture")
                .queryParam("type", "large")
                .build()
                .encode()
                .toUri();

        return restTemplate.getForObject(uri, byte[].class);
    }

}
