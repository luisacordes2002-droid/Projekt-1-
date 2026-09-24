package de.thm.reportify.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.thm.reportify.user.PasswortService;

@Controller
public class PasswortController {

    private final PasswortService passwortService;

    public PasswortController(PasswortService passwortService) {
        this.passwortService = passwortService;
    }

    @GetMapping("/passwort-aendern")
    public String formular(Principal principal) {
        if (!passwortService.istPasswortwechselErforderlich(
                principal.getName())) {
            return "redirect:/reports";
        }

        return "passwort-aendern";
    }

    @PostMapping("/passwort-aendern")
    public String passwortFestlegen(
            @RequestParam String neuesPasswort,
            @RequestParam String passwortBestaetigung,
            Principal principal,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            passwortService.legePersoenlichesPasswortFest(
                    principal.getName(),
                    neuesPasswort,
                    passwortBestaetigung);

            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Das persönliche Passwort wurde erfolgreich festgelegt.");
            return "redirect:/reports";
        } catch (IllegalArgumentException exception) {
            model.addAttribute(
                    "errorMessage",
                    exception.getMessage());
            return "passwort-aendern";
        }
    }
}
