package de.thm.reportify.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import de.thm.reportify.user.Nutzer;
import de.thm.reportify.user.NutzerRepository;
import de.thm.reportify.user.PasswortRegeln;
import de.thm.reportify.user.Rolle;

@Configuration
@Profile("dev")
public class EntwicklungsdatenKonfiguration {

    private static final String DEMO_PASSWORT = "Reportify!2026";

    @Bean
    public ApplicationRunner entwicklungsdaten(
            NutzerRepository nutzerRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            PasswortRegeln.pruefe(DEMO_PASSWORT);

            legeNutzerAnWennNichtVorhanden(
                    nutzerRepository,
                    passwordEncoder,
                    "mitarbeiter",
                    "Test Mitarbeiter",
                    Rolle.MITARBEITER);

            legeNutzerAnWennNichtVorhanden(
                    nutzerRepository,
                    passwordEncoder,
                    "schichtleitung",
                    "Test Schichtleitung",
                    Rolle.SCHICHTLEITUNG);
        };
    }

    private void legeNutzerAnWennNichtVorhanden(
            NutzerRepository nutzerRepository,
            PasswordEncoder passwordEncoder,
            String benutzername,
            String anzeigename,
            Rolle rolle) {
        if (nutzerRepository.existsByBenutzernameIgnoreCase(
                benutzername)) {
            return;
        }

        String passwortNachweis =
                passwordEncoder.encode(DEMO_PASSWORT);

        Nutzer nutzer = new Nutzer(
                benutzername,
                anzeigename,
                passwortNachweis,
                rolle,
                true);

        nutzerRepository.save(nutzer);
    }
}