package com.cm.cm2.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

public class UpdateUserFrom {
    @NotBlank(message = "usernam is required")
    @Size(min = 3, message = "Min 3 characters is required")
    private String name;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Address")
    private String email;
    @NotBlank(message = "About is required")
    private String about;
    @Size(min = 10, max = 10, message = "Invalid Number")
    private String phoneNo;
    private MultipartFile prifileImage;
    private String picture;
    public UpdateUserFrom() {}
    public UpdateUserFrom(String name, String email, String about, String phoneNo, MultipartFile prifileImage) {
        this.name = name;
        this.email = email;
        this.about = about;
        this.phoneNo = phoneNo;
        this.prifileImage = prifileImage;

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAbout() {
        return about;
    }
    public void setAbout(String about) {
        this.about = about;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public MultipartFile getPrifileImage() {
        return prifileImage;
    }
    public void setPrifileImage(MultipartFile prifileImage) {
        this.prifileImage = prifileImage;
    }
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
}

