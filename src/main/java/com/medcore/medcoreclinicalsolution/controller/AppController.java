package com.medcore.medcoreclinicalsolution.controller;

import com.medcore.medcoreclinicalsolution.dto.ContactDTO;
import com.medcore.medcoreclinicalsolution.model.Contact;
import com.medcore.medcoreclinicalsolution.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class AppController {

    private final ContactService contactService;

    // Home page
    @GetMapping({"/", "/index"})
    public String home(Model model) {
        model.addAttribute("contact", new ContactDTO());
        return "/index";
    }

    // Public login page
    @GetMapping("/login")
    public String loginPage() {
        return "/login";
    }

    // Access denied page
    @GetMapping("/admin/access-denied")
    public String accessDenied() {
        return "access-denied";
    }

    // Admin dashboard
    @GetMapping("/admin")
    public String dashboard(Model model) {

        model.addAttribute("contacts", contactService.findAll());
        model.addAttribute("totalMessages", contactService.countAll());
        model.addAttribute("newMessages", contactService.countByStatus(false));
        model.addAttribute("replied", contactService.countByStatus(true));
        model.addAttribute("pending", contactService.countByStatus(false));
        model.addAttribute("adminName", "Admin");

        return "/admin";
    }

    // Create contact
    @PostMapping("/contacts")
    public String createContact(@Valid @ModelAttribute("contact") ContactDTO contactDTO,
                                BindingResult result,
                                Model model, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("contact", contactDTO);
            return "redirect:/";
        }

        contactService.createContact(contactDTO);

        model.addAttribute("contact", new ContactDTO());
        redirectAttributes.addFlashAttribute("success", "Message sent successfully!");

        return "redirect:/#contact";
    }

    // Get contact
    @GetMapping("/contacts/{id}")
    @ResponseBody
    public Contact getContact(@PathVariable UUID id) {
        return contactService.findById(id);
    }

    // Update contact
    @PatchMapping("/contacts/{id}")
    @ResponseBody
    public Contact updateContact(@PathVariable UUID id, @RequestBody ContactDTO dto) {
        return contactService.updateContact(id, dto);
    }

    // Delete contact
    @DeleteMapping("/contacts/{id}")
    @ResponseBody
    public void deleteContact(@PathVariable UUID id) {
        contactService.deleteContact(id);
    }
}