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
    void listShowsCurrentReportAndHistory() throws Exception {
    Report currentReport = mock(Report.class);
    Report olderReportOne = mock(Report.class);
    Report olderReportTwo = mock(Report.class);

    List<Report> reports = List.of(
            currentReport,
            olderReportOne,
            olderReportTwo);

    List<Report> historyReports = List.of(
            olderReportOne,
            olderReportTwo);

    when(reportService.findAll()).thenReturn(reports);

    mockMvc.perform(get("/reports"))
            .andExpect(status().isOk())
            .andExpect(view().name("reports/list"))
            .andExpect(model().attribute(
                    "reports",
                    reports))
            .andExpect(model().attribute(
                    "currentReport",
                    currentReport))
            .andExpect(model().attribute(
                    "historyReports",
                    historyReports));

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
            "Maschine kontrolliert",
            "Dokumentation ergänzen",
            "Sensor ausgefallen",
            "Ersatzteil bestellt",
            Shift.FRUEHSCHICHT,
            Priority.HOCH,
            "mitarbeiter"))
            .thenReturn(report);

    mockMvc.perform(post("/reports")
                    .principal(() -> "mitarbeiter")
                    .param(
                            "completedTasks",
                            "Maschine kontrolliert")
                    .param(
                            "openTasks",
                            "Dokumentation ergänzen")
                    .param(
                            "problemsIncidents",
                            "Sensor ausgefallen")
                    .param(
                            "importantNotes",
                            "Ersatzteil bestellt")
                    .param("shift", "FRUEHSCHICHT")
                    .param("priority", "HOCH"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/reports/7"));

    verify(reportService).create(
            "Maschine kontrolliert",
            "Dokumentation ergänzen",
            "Sensor ausgefallen",
            "Ersatzteil bestellt",
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
void showEditFormDisplaysExistingReport()
        throws Exception {

    Report report = mock(Report.class);

    when(report.getId()).thenReturn(7L);
    when(report.getCompletedTasks())
            .thenReturn("Maschine kontrolliert");
    when(report.getOpenTasks())
            .thenReturn("Dokumentation ergänzen");
    when(report.getProblemsIncidents())
            .thenReturn("Sensor ausgefallen");
    when(report.getImportantNotes())
            .thenReturn("Ersatzteil bestellt");
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
                    "completedTasks",
                    "Maschine kontrolliert"))
            .andExpect(model().attribute(
                    "openTasks",
                    "Dokumentation ergänzen"))
            .andExpect(model().attribute(
                    "problemsIncidents",
                    "Sensor ausgefallen"))
            .andExpect(model().attribute(
                    "importantNotes",
                    "Ersatzteil bestellt"))
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
void updateRedirectsToEditedReport()
        throws Exception {

    mockMvc.perform(post("/reports/7/edit")
                    .param(
                            "completedTasks",
                            "Maschine kontrolliert")
                    .param(
                            "openTasks",
                            "Dokumentation ergänzen")
                    .param(
                            "problemsIncidents",
                            "Sensor ausgefallen")
                    .param(
                            "importantNotes",
                            "Ersatzteil bestellt")
                    .param("shift", "SPAETSCHICHT")
                    .param("priority", "HOCH"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/reports/7"))
            .andExpect(flash().attribute(
                    "successMessage",
                    "Der Report wurde erfolgreich bearbeitet."));

    verify(reportService).update(
            7L,
            "Maschine kontrolliert",
            "Dokumentation ergänzen",
            "Sensor ausgefallen",
            "Ersatzteil bestellt",
            Shift.SPAETSCHICHT,
            Priority.HOCH);
}

}