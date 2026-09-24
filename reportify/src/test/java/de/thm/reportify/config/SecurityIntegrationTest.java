package de.thm.reportify.config;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import de.thm.reportify.user.Nutzer;
import de.thm.reportify.user.NutzerRepository;
import de.thm.reportify.user.Rolle;

@SpringBootTest(properties = {
    "spring.datasource.url=jdbc:h2:mem:securitytest;DB_CLOSE_DELAY=-1",
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.h2.console.enabled=false"
})
class SecurityIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private NutzerRepository nutzerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        nutzerRepository.deleteAll();
    }

    @Test
    void zeigtLoginseiteOhneAnmeldung() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(unauthenticated());
    }

    @Test
    void leitetNichtAngemeldetePersonZurLoginseiteWeiter()
            throws Exception {
        mockMvc.perform(get("/reports"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void zeigtAllgemeineMeldungNachFehlgeschlagenerAnmeldung()
            throws Exception {
        mockMvc.perform(get("/login").param("error", ""))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "Benutzername oder Passwort ist nicht korrekt.")));
    }

    @Test
    void zeigtMeldungNachAbmeldung() throws Exception {
        mockMvc.perform(get("/login").param("logout", ""))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "Sie wurden erfolgreich abgemeldet.")));
    }

    @Test
    void akzeptiertGueltigeErstanmeldung() throws Exception {
        speichereNutzer(
                "mitarbeiter",
                "Reportify!2026",
                Rolle.MITARBEITER);

        mockMvc.perform(post("/login")
                        .with(csrf())
                        .param("username", "mitarbeiter")
                        .param("password", "Reportify!2026"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/passwort-aendern"))
                .andExpect(authenticated()
                        .withUsername("mitarbeiter"));
    }

    @Test
    void lehntUngueltigeAnmeldungAb() throws Exception {
        speichereNutzer(
                "mitarbeiter",
                "Reportify!2026",
                Rolle.MITARBEITER);

        mockMvc.perform(post("/login")
                        .with(csrf())
                        .param("username", "mitarbeiter")
                        .param("password", "falsch"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"))
                .andExpect(unauthenticated());
    }

    @Test
    void sperrtReportsBisZumErstenPasswortwechsel()
            throws Exception {
        speichereNutzer(
                "mitarbeiter",
                "Reportify!2026",
                Rolle.MITARBEITER);

        mockMvc.perform(get("/reports")
                        .with(user("mitarbeiter")
                                .roles("MITARBEITER")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/passwort-aendern"));
    }

    @Test
    void erlaubtAngemeldeterPersonDieReportuebersicht()
            throws Exception {
        mockMvc.perform(get("/reports")
                        .with(user("mitarbeiter")
                                .roles("MITARBEITER")))
                .andExpect(status().isOk());
    }

    @Test
    void verbietetMitarbeitendenDasLoeschen()
            throws Exception {
        mockMvc.perform(post("/reports/99/delete")
                        .with(user("mitarbeiter")
                                .roles("MITARBEITER"))
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    void erlaubtDerSchichtleitungDenLoeschaufruf()
            throws Exception {
        mockMvc.perform(post("/reports/99/delete")
                        .with(user("schichtleitung")
                                .roles("SCHICHTLEITUNG"))
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/reports/99"));
    }

    @Test
    void lehntPostAnfrageOhneCsrfTokenAb()
            throws Exception {
        mockMvc.perform(post("/reports/99/delete")
                        .with(user("schichtleitung")
                                .roles("SCHICHTLEITUNG")))
                .andExpect(status().isForbidden());
    }

    private void speichereNutzer(
            String benutzername,
            String passwort,
            Rolle rolle) {
        Nutzer nutzer = new Nutzer(
                benutzername,
                "Testnutzer",
                passwordEncoder.encode(passwort),
                rolle,
                true);
        nutzerRepository.save(nutzer);
    }
}
