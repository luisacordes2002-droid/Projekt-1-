package de.thm.reportify.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import de.thm.reportify.report.Report;
import de.thm.reportify.report.Report.Priority;
import de.thm.reportify.report.Report.Shift;
import de.thm.reportify.report.ReportService;

class ReportControllerTest {

    private ReportService reportService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        reportService = mock(ReportService.class);
        ReportController reportController =
                new ReportController(reportService);

        mockMvc = MockMvcBuilders
                .standaloneSetup(reportController)
                .build();
    }

    @Test
    void listShowsAllReports() throws Exception {
        Report report = mock(Report.class);
        List<Report> reports = List.of(report);

        when(reportService.findAll()).thenReturn(reports);

        mockMvc.perform(get("/reports"))
                .andExpect(status().isOk())
                .andExpect(view().name("reports/list"))
                .andExpect(model().attribute("reports", reports));

        verify(reportService).findAll();
    }

    @Test
    void newReportShowsFormAndPriorities() throws Exception {
        mockMvc.perform(get("/reports/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("reports/form"))
                .andExpect(model().attributeExists("priorities"));
    }

    @Test
    void detailShowsSelectedReport() throws Exception {
        Report report = mock(Report.class);

        when(reportService.findById(7L))
                .thenReturn(Optional.of(report));

        mockMvc.perform(get("/reports/7"))
                .andExpect(status().isOk())
                .andExpect(view().name("reports/detail"))
                .andExpect(model().attribute("report", report));

        verify(reportService).findById(7L);
    }

    @Test
    void createRedirectsToCreatedReport() throws Exception {
        Report report = mock(Report.class);

        when(report.getId()).thenReturn(7L);
        when(reportService.create(
                "Übergabe",
                "Maschine kontrollieren",
                Shift.FRUEHSCHICHT,
                Priority.HOCH,
                "mitarbeiter"))
                .thenReturn(report);

        mockMvc.perform(post("/reports")
                        .principal(() -> "mitarbeiter")
                        .param("title", "Übergabe")
                        .param("content", "Maschine kontrollieren")
                        .param("shift", "FRUEHSCHICHT")
                        .param("priority", "HOCH"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/reports/7"));

        verify(reportService).create(
                "Übergabe",
                "Maschine kontrollieren",
                Shift.FRUEHSCHICHT,
                Priority.HOCH,
                "mitarbeiter");
    }

    @Test
    void deleteRedirectsToListAndShowsSuccessMessage()
        throws Exception {

         mockMvc.perform(post("/reports/7/delete"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/reports"))
            .andExpect(flash().attribute(
                    "successMessage",
                    "Der Report wurde gelöscht."));

        verify(reportService).delete(7L);
}

@Test
void deleteRedirectsToDetailAndShowsErrorMessage()
        throws Exception {

    doThrow(new IllegalArgumentException(
            "Report mit ID 7 wurde nicht gefunden."))
            .when(reportService)
            .delete(7L);

    mockMvc.perform(post("/reports/7/delete"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/reports/7"))
            .andExpect(flash().attribute(
                    "errorMessage",
                    "Report mit ID 7 wurde nicht gefunden."));
}

@Test
void showEditFormDisplaysExistingReport() throws Exception {
    Report report = mock(Report.class);

    when(report.getId()).thenReturn(7L);
    when(report.getTitle()).thenReturn("Übergabe");
    when(report.getContent())
            .thenReturn("Maschine kontrollieren");
    when(report.getShift())
            .thenReturn(Shift.FRUEHSCHICHT);
    when(report.getPriority())
            .thenReturn(Priority.HOCH);

    when(reportService.findById(7L))
            .thenReturn(Optional.of(report));

    mockMvc.perform(get("/reports/7/edit"))
            .andExpect(status().isOk())
            .andExpect(view().name("reports/form"))
            .andExpect(model().attribute(
                    "reportId",
                    7L))
            .andExpect(model().attribute(
                    "title",
                    "Übergabe"))
            .andExpect(model().attribute(
                    "content",
                    "Maschine kontrollieren"))
            .andExpect(model().attribute(
                    "selectedShift",
                    Shift.FRUEHSCHICHT))
            .andExpect(model().attribute(
                    "selectedPriority",
                    Priority.HOCH))
            .andExpect(model().attribute(
                    "editMode",
                    true));

    verify(reportService).findById(7L);
}

@Test
void updateRedirectsToEditedReport() throws Exception {
    mockMvc.perform(post("/reports/7/edit")
                    .param("title", "Neue Übergabe")
                    .param(
                            "content",
                            "Maschine wurde kontrolliert")
                    .param("shift", "SPAETSCHICHT")
                    .param("priority", "HOCH"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/reports/7"))
            .andExpect(flash().attribute(
                    "successMessage",
                    "Der Report wurde erfolgreich bearbeitet."));

    verify(reportService).update(
            7L,
            "Neue Übergabe",
            "Maschine wurde kontrolliert",
            Shift.SPAETSCHICHT,
            Priority.HOCH);
}

}