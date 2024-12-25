package com.cm.cm2.forms;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;


public class ContactForm {

    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Minimum 3 characters are required")
    private String name;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email Address")
    private String email;
    @NotBlank(message = "Number is required")
    @Size(min = 10, max = 10, message = "10 Number required")
    private String phoneNumber;
    @NotBlank(message = "Address is required")
    private String address;
    private String description;
    private boolean favorite;
    private String websiteLink;
    private String LinkedInLink;
    private MultipartFile ContactImage;
    private String picture;
    public ContactForm() {
    }
    public ContactForm(String name,String email,String phoneNumber,String address,String description,boolean favorite,String websiteLink,String linkedInLink,MultipartFile ContactImage,String picture) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.description = description;
        this.favorite = favorite;
        this.websiteLink = websiteLink;
        this.LinkedInLink = linkedInLink;
        this.ContactImage = ContactImage;
        this.picture = picture;

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
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isFavorite() {
        return favorite;
    }
    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
    public String getWebsiteLink() {
        return websiteLink;
    }
    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }
    public String getLinkedInLink() {
        return LinkedInLink;

    }
    public void setLinkedInLink(String linkedInLink) {
        LinkedInLink = linkedInLink;
    }
    public MultipartFile getContactImage() {
        return ContactImage;
    }
    public void setContactImage(MultipartFile ContactImage) {
        this.ContactImage = ContactImage;
    }
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    @Override
    public String toString() {
        return "ContactForm [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber;
    }

}
