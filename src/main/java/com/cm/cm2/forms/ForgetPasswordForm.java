package com.cm.cm2.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@ToString

public class ForgetPasswordForm {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email Address")
    private String email;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public ForgetPasswordForm() {
    }
    public ForgetPasswordForm(String email) {
        this.email = email;
    }
}
