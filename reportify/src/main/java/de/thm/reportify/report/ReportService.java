package de.thm.reportify.report;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.thm.reportify.report.Report.Priority;
import de.thm.reportify.report.Report.Shift;
import de.thm.reportify.report.Report.Status;

@Service
@Transactional(readOnly = true)
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<Report> findAll() {
        return reportRepository.findAllByOrderByCreatedAtDesc();
    }

    public Optional<Report> findById(Long id) {
        return reportRepository.findById(id);
    }

    @Transactional
public Report create(
        String completedTasks,
        String openTasks,
        String problemsIncidents,
        String importantNotes,
        Shift shift,
        Priority priority,
        String createdBy) {

    String cleanCompletedTasks = requireText(
            completedTasks,
            "Erledigte Aufgaben dürfen nicht leer sein.");

    String cleanOpenTasks = optionalText(openTasks);
    String cleanProblemsIncidents =
            optionalText(problemsIncidents);
    String cleanImportantNotes =
            optionalText(importantNotes);

    if (shift == null) {
        throw new IllegalArgumentException(
                "Bitte wählen Sie eine Schicht aus.");
    }

    validateReportText(
            cleanCompletedTasks,
            "Erledigte Aufgaben");
    validateReportText(
            cleanOpenTasks,
            "Offene Aufgaben");
    validateReportText(
            cleanProblemsIncidents,
            "Probleme und Incidents");
    validateReportText(
            cleanImportantNotes,
            "Wichtige Hinweise");

    if (cleanProblemsIncidents != null
            && priority == null) {
        throw new IllegalArgumentException(
                "Bitte wählen Sie für Probleme oder Incidents "
                        + "eine Priorität aus.");
    }

    Report report = new Report(
            cleanCompletedTasks,
            cleanOpenTasks,
            cleanProblemsIncidents,
            cleanImportantNotes,
            shift,
            priority,
            createdBy);

    return reportRepository.save(report);
}

    @Transactional
public Report update(
        Long id,
        String completedTasks,
        String openTasks,
        String problemsIncidents,
        String importantNotes,
        Shift shift,
        Priority priority) {

    Report report = reportRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(
                    "Der Report wurde nicht gefunden."));

    String cleanCompletedTasks = requireText(
            completedTasks,
            "Erledigte Aufgaben dürfen nicht leer sein.");

    String cleanOpenTasks = optionalText(openTasks);
    String cleanProblemsIncidents =
            optionalText(problemsIncidents);
    String cleanImportantNotes =
            optionalText(importantNotes);

    if (shift == null) {
        throw new IllegalArgumentException(
                "Bitte wählen Sie eine Schicht aus.");
    }

    validateReportText(
            cleanCompletedTasks,
            "Erledigte Aufgaben");
    validateReportText(
            cleanOpenTasks,
            "Offene Aufgaben");
    validateReportText(
            cleanProblemsIncidents,
            "Probleme und Incidents");
    validateReportText(
            cleanImportantNotes,
            "Wichtige Hinweise");

    if (cleanProblemsIncidents != null
            && priority == null) {
        throw new IllegalArgumentException(
                "Bitte wählen Sie für Probleme oder Incidents "
                        + "eine Priorität aus.");
    }

    report.setCompletedTasks(cleanCompletedTasks);
    report.setOpenTasks(cleanOpenTasks);
    report.setProblemsIncidents(cleanProblemsIncidents);
    report.setImportantNotes(cleanImportantNotes);
    report.setShift(shift);
    report.setPriority(priority);

    return reportRepository.save(report);
}

    @Transactional
    public Report markAsCompleted(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Der Report wurde nicht gefunden."));

        report.setStatus(Status.ERLEDIGT);
        return report;
    }
        @Transactional
    public void delete(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Report mit ID " + id + " wurde nicht gefunden."));

        reportRepository.delete(report);
    }

    private String optionalText(String value) {
    if (value == null || value.isBlank()) {
        return null;
    }

    return value.trim();
}

private void validateReportText(
        String value,
        String fieldName) {

    if (value != null && value.length() > 4000) {
        throw new IllegalArgumentException(
                fieldName
                        + " dürfen höchstens 4000 Zeichen enthalten.");
    }
}
    private String requireText(String value, String errorMessage) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(errorMessage);
        }

        return value.trim();
    }
}