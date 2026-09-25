package de.thm.reportify.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

class PasswortServiceTest {

    private NutzerRepository nutzerRepository;
    private PasswordEncoder passwordEncoder;
    private PasswortService passwortService;
    private Nutzer nutzer;

    @BeforeEach
    void setUp() {
        nutzerRepository = mock(NutzerRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        passwortService = new PasswortService(
                nutzerRepository,
                passwordEncoder);

        nutzer = new Nutzer(
                "mitarbeiter",
                "Test Mitarbeiter",
                "alter-nachweis",
                Rolle.MITARBEITER,
                true);

        when(nutzerRepository
                .findByBenutzernameIgnoreCaseAndAktivTrue(
                        "mitarbeiter"))
                .thenReturn(Optional.of(nutzer));
    }

    @Test
    void erkenntErforderlichenPasswortwechsel() {
        assertTrue(passwortService
                .istPasswortwechselErforderlich("mitarbeiter"));
    }

    @Test
    void speichertGueltigesPersoenlichesPasswort() {
        when(passwordEncoder.encode("NeuesPasswort2026"))
                .thenReturn("neuer-nachweis");

        passwortService.legePersoenlichesPasswortFest(
                "mitarbeiter",
                "NeuesPasswort2026",
                "NeuesPasswort2026");

        assertFalse(nutzer.isPasswortwechselErforderlich());
        verify(passwordEncoder).encode("NeuesPasswort2026");
        verify(nutzerRepository).save(nutzer);
    }

    @Test
    void lehntUnterschiedlichePasswoerterAb() {
        assertThrows(
                IllegalArgumentException.class,
                () -> passwortService
                        .legePersoenlichesPasswortFest(
                                "mitarbeiter",
                                "NeuesPasswort2026",
                                "AnderesPasswort2026"));

        verify(passwordEncoder, never()).encode("NeuesPasswort2026");
        verify(nutzerRepository, never()).save(nutzer);
    }

    @Test
    void lehntZuKurzesPasswortAb() {
        assertThrows(
                IllegalArgumentException.class,
                () -> passwortService
                        .legePersoenlichesPasswortFest(
                                "mitarbeiter",
                                "kurz",
                                "kurz"));

        verify(nutzerRepository, never()).save(nutzer);
    }
}
