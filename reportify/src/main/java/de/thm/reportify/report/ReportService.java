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
            String title,
            String content,
            Shift shift,
            Priority priority,
            String createdBy) {

        String cleanTitle = requireText(title, "Der Titel darf nicht leer sein.");
        String cleanContent = requireText(content, "Der Inhalt darf nicht leer sein.");
        if (shift == null) {
            throw new IllegalArgumentException(
                "Bitte wählen Sie eine Schicht aus.");
        }

        if (cleanTitle.length() > 120) {
            throw new IllegalArgumentException(
                    "Der Titel darf höchstens 120 Zeichen enthalten.");
        }

        if (cleanContent.length() > 4000) {
            throw new IllegalArgumentException(
                    "Der Inhalt darf höchstens 4000 Zeichen enthalten.");
        }

        Report report = new Report(
                cleanTitle,
                cleanContent,
                shift,
                priority == null ? Priority.MITTEL : priority,
                createdBy);

        return reportRepository.save(report);
    }

    @Transactional
     public Report update(
             Long id,
             String title,
             String content,
             Shift shift,
             Priority priority) {

         Report report = reportRepository.findById(id)
                 .orElseThrow(() -> new IllegalArgumentException(
                    "Der Report wurde nicht gefunden."));
                    String cleanTitle = requireText(
                           title,
                           "Der Titel darf nicht leer sein.");

String cleanContent = requireText(
        content,
        "Der Inhalt darf nicht leer sein.");

if (shift == null) {
    throw new IllegalArgumentException(
            "Bitte wählen Sie eine Schicht aus.");
}

if (cleanTitle.length() > 120) {
    throw new IllegalArgumentException(
            "Der Titel darf höchstens 120 Zeichen enthalten.");
}

if (cleanContent.length() > 4000) {
    throw new IllegalArgumentException(
            "Der Inhalt darf höchstens 4000 Zeichen enthalten.");
}
report.setTitle(cleanTitle);
report.setContent(cleanContent);
report.setShift(shift);
report.setPriority(
        priority == null ? Priority.MITTEL : priority);

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
    private String requireText(String value, String errorMessage) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(errorMessage);
        }

        return value.trim();
    }
}