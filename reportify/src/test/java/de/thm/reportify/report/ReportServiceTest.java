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
                null,
                "mitarbeiter");

        assertEquals("Übergabe Frühschicht", createdReport.getTitle());
        assertEquals("Maschine kontrollieren", createdReport.getContent());
        assertEquals(Priority.MITTEL, createdReport.getPriority());
        verify(reportRepository).save(createdReport);
    }

    @Test
    void createRejectsBlankTitle() {
        assertThrows(
                IllegalArgumentException.class,
                () -> reportService.create(
                        "   ",
                        "Inhalt",
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