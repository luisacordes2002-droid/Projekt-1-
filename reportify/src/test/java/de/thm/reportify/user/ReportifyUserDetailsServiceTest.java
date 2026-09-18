package de.thm.reportify.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

class ReportifyUserDetailsServiceTest {

    @Test
    void laedtAktivenNutzerUndUebersetztDieRolle() {
        NutzerRepository nutzerRepository =
                mock(NutzerRepository.class);

        String passwortNachweis = "pbkdf2-testnachweis";

        Nutzer nutzer = new Nutzer(
                "souhaib",
                "Souhaib Boujemaoui",
                passwortNachweis,
                Rolle.MITARBEITER,
                true);

        when(nutzerRepository
                .findByBenutzernameIgnoreCaseAndAktivTrue("Souhaib"))
                .thenReturn(Optional.of(nutzer));

        ReportifyUserDetailsService service =
                new ReportifyUserDetailsService(nutzerRepository);

        UserDetails userDetails =
                service.loadUserByUsername("  Souhaib  ");

        assertEquals("souhaib", userDetails.getUsername());
        assertEquals(passwortNachweis, userDetails.getPassword());

        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority()
                        .equals("ROLE_MITARBEITER")));

        verify(nutzerRepository)
                .findByBenutzernameIgnoreCaseAndAktivTrue("Souhaib");
    }

    @Test
    void lehntNichtVorhandenenNutzerAb() {
        NutzerRepository nutzerRepository =
                mock(NutzerRepository.class);

        when(nutzerRepository
                .findByBenutzernameIgnoreCaseAndAktivTrue("unbekannt"))
                .thenReturn(Optional.empty());

        ReportifyUserDetailsService service =
                new ReportifyUserDetailsService(nutzerRepository);

        assertThrows(
                UsernameNotFoundException.class,
                () -> service.loadUserByUsername("unbekannt"));
    }
}