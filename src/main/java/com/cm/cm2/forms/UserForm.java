package com.cm.cm2.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


public class UserForm {

    @NotBlank(message = "usernam is required")
    @Size(min = 3, message = "Min 3 characters is required")
    private String name;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Address")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Min 6 Characters is required")
    private String password;
    @NotBlank(message = "About is required")
    private String about;
    @Size(min = 10, max = 10, message = "Invalid Number")
    private String phoneNo;
    public UserForm() {}
    public UserForm(String name, String email, String password, String about, String phoneNo) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
        this.phoneNo = phoneNo;
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
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

}
