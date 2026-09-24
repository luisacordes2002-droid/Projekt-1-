package de.thm.reportify.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.thm.reportify.report.Report.Priority;
import de.thm.reportify.report.Report.Shift;
import de.thm.reportify.report.Report.Status;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @Mock
    private ReportRepository reportRepository;

    private ReportService reportService;

    @BeforeEach
    void setUp() {
        reportService = new ReportService(reportRepository);
    }

    @Test
    void findAllReturnsReportsInRepositoryOrder() {
        Report firstReport = mock(Report.class);
        Report secondReport = mock(Report.class);
        List<Report> expectedReports = List.of(firstReport, secondReport);

        when(reportRepository.findAllByOrderByCreatedAtDesc())
                .thenReturn(expectedReports);

        List<Report> actualReports = reportService.findAll();

        assertSame(expectedReports, actualReports);
        verify(reportRepository).findAllByOrderByCreatedAtDesc();
    }

   @Test
    void createTrimsFieldsAndAllowsEmptyOptionalValues() {
        when(reportRepository.save(any(Report.class)))
            .thenAnswer(invocation ->
                invocation.getArgument(0));

    Report createdReport = reportService.create(
            "  Maschine kontrolliert  ",
            "   ",
            null,
            "  Werkzeug prüfen  ",
            Shift.FRUEHSCHICHT,
            null,
            "mitarbeiter");

    assertEquals(
            "Maschine kontrolliert",
            createdReport.getCompletedTasks());
    assertNull(createdReport.getOpenTasks());
    assertNull(createdReport.getProblemsIncidents());
    assertEquals(
            "Werkzeug prüfen",
            createdReport.getImportantNotes());
    assertNull(createdReport.getPriority());
    assertEquals(
            Shift.FRUEHSCHICHT,
            createdReport.getShift());

    verify(reportRepository).save(createdReport);
}

    @Test
    void updateChangesStructuredReportData() {
        Report existingReport = new Report(
            "Alte erledigte Aufgabe",
            "Alte offene Aufgabe",
            null,
            null,
            Shift.FRUEHSCHICHT,
            null,
            "mitarbeiter");

    when(reportRepository.findById(7L))
            .thenReturn(Optional.of(existingReport));

    when(reportRepository.save(any(Report.class)))
            .thenAnswer(invocation ->
                    invocation.getArgument(0));

        Report updatedReport = reportService.update(
            7L,
            "  Maschine kontrolliert  ",
            "  Dokumentation ergänzen  ",
            "  Sensor ausgefallen  ",
            "  Ersatzteil ist bestellt  ",
            Shift.SPAETSCHICHT,
                Priority.HOCH,
        "schichtleitung");

    assertEquals(
            "Maschine kontrolliert",
            updatedReport.getCompletedTasks());
    assertEquals(
            "Dokumentation ergänzen",
            updatedReport.getOpenTasks());
    assertEquals(
            "Sensor ausgefallen",
            updatedReport.getProblemsIncidents());
    assertEquals(
            "Ersatzteil ist bestellt",
            updatedReport.getImportantNotes());
    assertEquals(
            Shift.SPAETSCHICHT,
            updatedReport.getShift());
    assertEquals(
            Priority.HOCH,
            updatedReport.getPriority());
            assertEquals(
        "schichtleitung",
        updatedReport.getUpdatedBy());
assertNotNull(updatedReport.getUpdatedAt());

    verify(reportRepository).findById(7L);
    verify(reportRepository).save(existingReport);
}

    @Test
    void createRejectsMissingShift() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> reportService.create(
                    "Maschine kontrolliert",
                    "",
                    "",
                    "",
                    null,
                    null,
                    "mitarbeiter"));

    assertEquals(
            "Bitte wählen Sie eine Schicht aus.",
            exception.getMessage());

    verifyNoInteractions(reportRepository);
}

    @Test
    void createRejectsBlankCompletedTasks() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> reportService.create(
                    "   ",
                    "",
                    "",
                    "",
                    Shift.FRUEHSCHICHT,
                    null,
                    "mitarbeiter"));

    assertEquals(
            "Erledigte Aufgaben dürfen nicht leer sein.",
            exception.getMessage());

    verifyNoInteractions(reportRepository);
}

    @Test
    void createRequiresPriorityForProblems() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
                () -> reportService.create(
                    "Maschine kontrolliert",
                    "",
                    "Sensor ausgefallen",
                    "",
                    Shift.FRUEHSCHICHT,
                    null,
                    "mitarbeiter"));

            assertEquals(
                "Bitte wählen Sie für Probleme oder Incidents "
                    + "eine Priorität aus.",
                exception.getMessage());

            verifyNoInteractions(reportRepository);
}

    @Test
    void markAsCompletedChangesStatus() {
        Report report = mock(Report.class);

        when(reportRepository.findById(1L))
                .thenReturn(Optional.of(report));

        reportService.markAsCompleted(1L);

        verify(report).setStatus(Status.ERLEDIGT);
    }

    @Test
void deleteRemovesExistingReport() {
    Report report = mock(Report.class);

    when(reportRepository.findById(1L))
            .thenReturn(Optional.of(report));

    reportService.delete(1L);

    verify(reportRepository).delete(report);
}

@Test
void deleteRejectsUnknownReport() {
    when(reportRepository.findById(99L))
            .thenReturn(Optional.empty());

    IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> reportService.delete(99L));

    assertEquals(
            "Report mit ID 99 wurde nicht gefunden.",
            exception.getMessage());
}

}