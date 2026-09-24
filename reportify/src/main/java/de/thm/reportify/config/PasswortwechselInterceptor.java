package de.thm.reportify.config;

import java.security.Principal;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import de.thm.reportify.user.Nutzer;
import de.thm.reportify.user.NutzerRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class PasswortwechselInterceptor
        implements HandlerInterceptor {

    private final NutzerRepository nutzerRepository;

    public PasswortwechselInterceptor(
            NutzerRepository nutzerRepository) {
        this.nutzerRepository = nutzerRepository;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        Principal principal = request.getUserPrincipal();

        if (principal == null || istFreigegebenerPfad(request)) {
            return true;
        }

        boolean passwortwechselErforderlich = nutzerRepository
                .findByBenutzernameIgnoreCaseAndAktivTrue(
                        principal.getName())
                .map(Nutzer::isPasswortwechselErforderlich)
                .orElse(false);

        if (!passwortwechselErforderlich) {
            return true;
        }

        response.sendRedirect(
                request.getContextPath() + "/passwort-aendern");
        return false;
    }

    private boolean istFreigegebenerPfad(
            HttpServletRequest request) {
        String pfad = request.getRequestURI().substring(
                request.getContextPath().length());

        return pfad.equals("/passwort-aendern")
                || pfad.equals("/logout")
                || pfad.equals("/login")
                || pfad.equals("/error")
                || pfad.startsWith("/css/");
    }
}
