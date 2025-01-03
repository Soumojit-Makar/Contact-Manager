package com.cm.cm2.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cm.cm2.entities.Contact;
import com.cm.cm2.forms.ContactForm;
import com.cm.cm2.helper.AppCon;
import com.cm.cm2.helper.Helper;
import com.cm.cm2.helper.Message;
import com.cm.cm2.helper.MessageType;
import com.cm.cm2.services.ContactService;
import com.cm.cm2.services.ImageService;
import com.cm.cm2.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private final ContactService contactService;
    private final UserService userService;
    private final ImageService imageService;

    public ContactController(ContactService contactService, UserService userService, ImageService imageService) {
        this.contactService = contactService;
        this.userService = userService;
        this.imageService = imageService;
    }

    @GetMapping("/add_contact")
    public String addContact(Model model) {
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);

        return "user/add_contact";
    }

    @PostMapping("/add_contact")
    public String save(@Valid @ModelAttribute ContactForm contactForm, BindingResult bindingResult, Authentication authentication, HttpSession session) {
        if (bindingResult.hasErrors()) {
            session.setAttribute("message",new Message("Please correct the following errors. ",
                    MessageType.red));
            return "user/add_contact";
        }
        String username = Helper.getEmailOfLoginUser(authentication);

        Contact contact = new Contact();
        BeanUtils.copyProperties(contactForm, contact);
        contact.setUser(userService.getUserByEmail(username));
        if (contactForm.getContactImage() != null && !contactForm.getContactImage().isEmpty()) {
            contact.setPic(imageService.uploadImage(contactForm.getContactImage()));
        }

        contactService.saveContact(contact);
        System.out.println(contactForm);

        session.setAttribute("message",new Message("You has been successfully added a new contact. ",MessageType.green));
        return "redirect:/user/contacts/add_contact";
    }

    @RequestMapping
    public String viewContact(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = AppCon.Page_Size + "") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Model model, Authentication authentication) {
        Page<Contact> PageContact = contactService.getByUser(userService.getUserByEmail(Helper.getEmailOfLoginUser(authentication)), page, size, sortBy, direction);
        model.addAttribute("contacts", PageContact);
        model.addAttribute("pagesize", AppCon.Page_Size);
        return "user/contacts";
    }

    @GetMapping("/search")
    public String searchHandaler(
            @RequestParam("field") String field,
            @RequestParam("keyword") String value,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = AppCon.Page_Size + "") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Authentication authentication,
            Model model) {
        Page<Contact> pageContacts = null;
        if (field.equalsIgnoreCase("all")) {
            pageContacts = contactService.getByUser(userService.getUserByEmail(Helper.getEmailOfLoginUser(authentication)), page, size, sortBy, direction);

        } else if (field.equalsIgnoreCase("name")) {
            pageContacts = contactService.searchByName(value, page, size, sortBy, direction, userService.getUserByEmail(Helper.getEmailOfLoginUser(authentication)));
        } else if (field.equalsIgnoreCase("phone")) {
            pageContacts = contactService.searchByPhoneNumber(value, page, size, sortBy, direction, userService.getUserByEmail(Helper.getEmailOfLoginUser(authentication)));
        } else if (field.equalsIgnoreCase("email")) {
            pageContacts = contactService.searchByEmail(value, page, size, sortBy, direction, userService.getUserByEmail(Helper.getEmailOfLoginUser(authentication)));
        }
        model.addAttribute("contacts", pageContacts);
        model.addAttribute("pagesize", AppCon.Page_Size);
        model.addAttribute("valuefield", field);
        model.addAttribute("type", value);
        model.addAttribute("pagesize", AppCon.Page_Size);
        return "user/search";
    }

    @GetMapping("/view/{id}")
    public String updateContactView(
            @PathVariable("id") String id,
            Model model) {
        Contact contact = contactService.getContactById(id);
        ContactForm contactForm = new ContactForm();
        contactForm.setName(contact.getName());
        contactForm.setEmail(contact.getEmail());
        contactForm.setPhoneNumber(contact.getPhoneNumber());
        contactForm.setAddress(contact.getAddress());
        contactForm.setDescription(contact.getDescription());
        contactForm.setFavorite(contact.isFavorite());
        contactForm.setWebsiteLink(contact.getWebsiteLink());
        contactForm.setLinkedInLink(contact.getLinkedInLink());
        contactForm.setPicture(contact.getPic());
        model.addAttribute("contactForm", contactForm);
        model.addAttribute("contactId", id);
        return "user/updata_contact";
    }

    @PostMapping("/update/{contactId}")
    public String UpdateContact(@Valid @PathVariable("contactId") String contactId, @ModelAttribute ContactForm contactForm, BindingResult bindingResult, HttpSession session) {

        var contact = contactService.getContactById(contactId);
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getPhoneNumber());
        contact.setDescription(contactForm.getDescription());
        contact.setFavorite(contactForm.isFavorite());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setLinkedInLink(contactForm.getLinkedInLink());

        if (contactForm.getContactImage() != null && !contactForm.getContactImage().isEmpty()) {
            contact.setPic(imageService.uploadImage(contactForm.getContactImage()));
        }
        contactService.updateContact(contact);
        session.setAttribute("message", new Message
                ("Contact Updated... ",
                MessageType.green));

        return "redirect:/user/contacts/view/" + contactId;
    }

}
