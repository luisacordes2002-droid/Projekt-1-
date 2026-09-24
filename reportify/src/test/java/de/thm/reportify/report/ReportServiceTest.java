package de.thm.reportify.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void createTrimsInputAndUsesMediumAsDefaultPriority() {
        when(reportRepository.save(any(Report.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Report createdReport = reportService.create(
                "  Übergabe Frühschicht  ",
                "  Maschine kontrollieren  ",
                Shift.FRUEHSCHICHT,
                null,
                "mitarbeiter");

        assertEquals("Übergabe Frühschicht", createdReport.getTitle());
        assertEquals("Maschine kontrollieren", createdReport.getContent());
        assertEquals(Priority.MITTEL, createdReport.getPriority());
        assertEquals(Shift.FRUEHSCHICHT, createdReport.getShift());
        verify(reportRepository).save(createdReport);
    }

    @Test
    void updateChangesReportData() {
        Report existingReport = new Report(
            "Alter Titel",
            "Alter Inhalt",
            Shift.FRUEHSCHICHT,
            Priority.NIEDRIG,
            "mitarbeiter");

        when(reportRepository.findById(7L))
            .thenReturn(Optional.of(existingReport));

        when(reportRepository.save(any(Report.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

    Report updatedReport = reportService.update(
            7L,
            "  Neuer Titel  ",
            "  Neuer Inhalt  ",
            Shift.SPAETSCHICHT,
            Priority.HOCH);

    assertEquals("Neuer Titel", updatedReport.getTitle());
    assertEquals("Neuer Inhalt", updatedReport.getContent());
    assertEquals(Shift.SPAETSCHICHT, updatedReport.getShift());
    assertEquals(Priority.HOCH, updatedReport.getPriority());

    verify(reportRepository).findById(7L);
    verify(reportRepository).save(existingReport);
}

    @Test
    void createRejectsMissingShift() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> reportService.create(
                        "Übergabe",
                        "Maschine kontrollieren",
                        null,
                        Priority.MITTEL,
                        "mitarbeiter"));

        assertEquals(
                "Bitte wählen Sie eine Schicht aus.",
                exception.getMessage());
     }

    @Test
    void createRejectsBlankTitle() {
        assertThrows(
                IllegalArgumentException.class,
                () -> reportService.create(
                        "   ",
                        "Inhalt",
                        Shift.FRUEHSCHICHT,
                        Priority.HOCH,
                        "mitarbeiter"));

        verifyNoInteractions(reportRepository);
    }

    @Test
    void createRejectsBlankContent() {
        assertThrows(
                IllegalArgumentException.class,
                () -> reportService.create(
                        "Übergabe",
                        "   ",
                        Shift.FRUEHSCHICHT,
                        Priority.NIEDRIG,
                        "mitarbeiter"));

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