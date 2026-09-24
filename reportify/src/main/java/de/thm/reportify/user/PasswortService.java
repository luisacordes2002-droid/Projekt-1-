package de.thm.reportify.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PasswortService {

    private final NutzerRepository nutzerRepository;
    private final PasswordEncoder passwordEncoder;

    public PasswortService(
            NutzerRepository nutzerRepository,
            PasswordEncoder passwordEncoder) {
        this.nutzerRepository = nutzerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean istPasswortwechselErforderlich(
            String benutzername) {
        return findeAktivenNutzer(benutzername)
                .isPasswortwechselErforderlich();
    }

    @Transactional
    public void legePersoenlichesPasswortFest(
            String benutzername,
            String neuesPasswort,
            String passwortBestaetigung) {
        PasswortRegeln.pruefe(neuesPasswort);

        if (!neuesPasswort.equals(passwortBestaetigung)) {
            throw new IllegalArgumentException(
                    "Die eingegebenen Passwörter stimmen nicht überein.");
        }

        Nutzer nutzer = findeAktivenNutzer(benutzername);

        if (!nutzer.isPasswortwechselErforderlich()) {
            throw new IllegalArgumentException(
                    "Die erstmalige Passwortvergabe ist bereits abgeschlossen.");
        }

        nutzer.legePersoenlichesPasswortFest(
                passwordEncoder.encode(neuesPasswort));
        nutzerRepository.save(nutzer);
    }

    private Nutzer findeAktivenNutzer(String benutzername) {
        if (benutzername == null || benutzername.isBlank()) {
            throw new IllegalArgumentException(
                    "Benutzerkonto nicht gefunden.");
        }

        return nutzerRepository
                .findByBenutzernameIgnoreCaseAndAktivTrue(
                        benutzername.trim())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Benutzerkonto nicht gefunden."));
    }
}
