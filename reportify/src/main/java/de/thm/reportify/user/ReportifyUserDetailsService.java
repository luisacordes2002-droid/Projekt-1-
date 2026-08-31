package de.thm.reportify.user;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ReportifyUserDetailsService implements UserDetailsService {

    private final NutzerRepository nutzerRepository;

    public ReportifyUserDetailsService(
            NutzerRepository nutzerRepository) {
        this.nutzerRepository = nutzerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String benutzername)
            throws UsernameNotFoundException {
        String normalisierterBenutzername = benutzername.trim();

        Nutzer nutzer = nutzerRepository
                .findByBenutzernameIgnoreCaseAndAktivTrue(
                        normalisierterBenutzername)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Benutzerkonto nicht gefunden."));

        return User.withUsername(nutzer.getBenutzername())
                .password(nutzer.getPasswortNachweis())
                .roles(nutzer.getRolle().name())
                .build();
    }
}