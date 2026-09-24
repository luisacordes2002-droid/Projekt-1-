package de.thm.reportify.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import de.thm.reportify.user.PasswortService;

class PasswortControllerTest {

    private PasswortService passwortService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        passwortService = mock(PasswortService.class);
        PasswortController controller =
                new PasswortController(passwortService);
        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    void zeigtFormularBeiErforderlichemPasswortwechsel()
            throws Exception {
        when(passwortService.istPasswortwechselErforderlich(
                "mitarbeiter"))
                .thenReturn(true);

        mockMvc.perform(get("/passwort-aendern")
                        .principal(() -> "mitarbeiter"))
                .andExpect(status().isOk())
                .andExpect(view().name("passwort-aendern"));
    }

    @Test
    void leitetOhneErforderlichenPasswortwechselWeiter()
            throws Exception {
        when(passwortService.istPasswortwechselErforderlich(
                "mitarbeiter"))
                .thenReturn(false);

        mockMvc.perform(get("/passwort-aendern")
                        .principal(() -> "mitarbeiter"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/reports"));
    }

    @Test
    void speichertPersoenlichesPasswortUndLeitetWeiter()
            throws Exception {
        mockMvc.perform(post("/passwort-aendern")
                        .principal(() -> "mitarbeiter")
                        .param(
                                "neuesPasswort",
                                "NeuesPasswort2026")
                        .param(
                                "passwortBestaetigung",
                                "NeuesPasswort2026"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/reports"))
                .andExpect(flash().attribute(
                        "successMessage",
                        "Das persönliche Passwort wurde erfolgreich festgelegt."));

        verify(passwortService).legePersoenlichesPasswortFest(
                "mitarbeiter",
                "NeuesPasswort2026",
                "NeuesPasswort2026");
    }

    @Test
    void zeigtValidierungsfehlerImFormular()
            throws Exception {
        org.mockito.Mockito.doThrow(
                new IllegalArgumentException(
                        "Die eingegebenen Passwörter stimmen nicht überein."))
                .when(passwortService)
                .legePersoenlichesPasswortFest(
                        "mitarbeiter",
                        "NeuesPasswort2026",
                        "AnderesPasswort2026");

        mockMvc.perform(post("/passwort-aendern")
                        .principal(() -> "mitarbeiter")
                        .param(
                                "neuesPasswort",
                                "NeuesPasswort2026")
                        .param(
                                "passwortBestaetigung",
                                "AnderesPasswort2026"))
                .andExpect(status().isOk())
                .andExpect(view().name("passwort-aendern"))
                .andExpect(model().attribute(
                        "errorMessage",
                        "Die eingegebenen Passwörter stimmen nicht überein."));
    }
}
