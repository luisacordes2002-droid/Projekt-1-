package de.thm.reportify;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartseiteController {

    @GetMapping("/")
    public String startseite() {
        return "startseite";
    }
}