package com.phantom.pickup.model;

import com.phantom.pickup.util.FacebookToken;
import org.springframework.web.multipart.MultipartFile;

public class UserModel {

    private String name;
    private String phone;
    private String email;
    private byte[] photo;


    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        try {
            this.photo = photo.getBytes();
        } catch (Exception exception) {
            this.photo = null;
        }
    }

    public void photoAvailable(String id) {
        if (photo == null) {
            this.photo = new FacebookToken().getDefaultPhoto(id);
        }
    }

}
