package de.thm.reportify.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import jakarta.servlet.RequestDispatcher;

@SpringBootTest(properties = {
    "spring.datasource.url=jdbc:h2:mem:errorpagetest;DB_CLOSE_DELAY=-1",
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.h2.console.enabled=false"
})
class ErrorPageIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void zeigtVerstaendlicheSeiteBeiNichtGefundenerSeite()
            throws Exception {
        mockMvc.perform(get("/error")
                        .accept(MediaType.TEXT_HTML)
                        .with(user("mitarbeiter")
                                .roles("MITARBEITER"))
                        .requestAttr(
                                RequestDispatcher.ERROR_STATUS_CODE,
                                404)
                        .requestAttr(
                                RequestDispatcher.ERROR_REQUEST_URI,
                                "/nicht-vorhanden"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString(
                        "Seite nicht gefunden")))
                .andExpect(content().string(containsString(
                        "Zur Reportübersicht")));
    }

    @Test
    void zeigtKeineInternenDetailsBeiServerfehler()
            throws Exception {
        mockMvc.perform(get("/error")
                        .accept(MediaType.TEXT_HTML)
                        .with(user("mitarbeiter")
                                .roles("MITARBEITER"))
                        .requestAttr(
                                RequestDispatcher.ERROR_STATUS_CODE,
                                500)
                        .requestAttr(
                                RequestDispatcher.ERROR_REQUEST_URI,
                                "/reports")
                        .requestAttr(
                                RequestDispatcher.ERROR_MESSAGE,
                                "Interner Datenbankfehler"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString(
                        "Ein Fehler ist aufgetreten")))
                .andExpect(content().string(org.hamcrest.Matchers.not(
                        containsString("Interner Datenbankfehler"))));
    }
}
