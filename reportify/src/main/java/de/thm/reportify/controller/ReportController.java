package de.thm.reportify.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.thm.reportify.report.Report;
import de.thm.reportify.report.Report.Priority;
import de.thm.reportify.report.Report.Shift;
import de.thm.reportify.report.ReportService;

@Controller
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

   @GetMapping
public String list(Model model) {
    var reports = reportService.findAll();

    model.addAttribute("reports", reports);
    model.addAttribute(
            "currentReport",
            reports.isEmpty() ? null : reports.get(0));
    model.addAttribute(
            "historyReports",
            reports.subList(
                    Math.min(1, reports.size()),
                    reports.size()));

    return "reports/list";
}

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("priorities", Priority.values());
        model.addAttribute("shifts", Shift.values());
        return "reports/form";
    }

   @PostMapping
public String create(
        @RequestParam String completedTasks,
        @RequestParam(defaultValue = "")
        String openTasks,
        @RequestParam(defaultValue = "")
        String problemsIncidents,
        @RequestParam(defaultValue = "")
        String importantNotes,
        @RequestParam Shift shift,
        @RequestParam(required = false)
        Priority priority,
        Principal principal,
        Model model,
        RedirectAttributes redirectAttributes) {

    try {
        String username = principal == null
                ? "unbekannt"
                : principal.getName();

        Report report = reportService.create(
                completedTasks,
                openTasks,
                problemsIncidents,
                importantNotes,
                shift,
                priority,
                username);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Der Report wurde erfolgreich erstellt.");

        return "redirect:/reports/" + report.getId();
    } catch (IllegalArgumentException exception) {
        model.addAttribute(
                "errorMessage",
                exception.getMessage());
        model.addAttribute(
                "completedTasks",
                completedTasks);
        model.addAttribute("openTasks", openTasks);
        model.addAttribute(
                "problemsIncidents",
                problemsIncidents);
        model.addAttribute(
                "importantNotes",
                importantNotes);
        model.addAttribute("selectedShift", shift);
        model.addAttribute(
                "selectedPriority",
                priority);
        model.addAttribute(
                "priorities",
                Priority.values());
        model.addAttribute("shifts", Shift.values());

        return "reports/form";
    }
}
@GetMapping("/{id}")
    public String detail(
            @PathVariable Long id,
            Model model,
            Authentication authentication,
            RedirectAttributes redirectAttributes) {

        return reportService.findById(id)
                .map(report -> {
                    model.addAttribute("report", report);
                    model.addAttribute(
                            "canDelete",
                            authentication != null &&
                            authentication.getAuthorities().stream()
                                    .anyMatch(authority ->
                                            authority.getAuthority()
                                                    .equals("ROLE_SCHICHTLEITUNG")));
                    return "reports/detail";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute(
                            "errorMessage",
                            "Der Report wurde nicht gefunden.");
                    return "redirect:/reports";
                });
    }

   @GetMapping("/{id}/edit")
public String showEditForm(
        @PathVariable Long id,
        Model model,
        RedirectAttributes redirectAttributes) {

    return reportService.findById(id)
            .map(report -> {
                model.addAttribute(
                        "reportId",
                        report.getId());
                model.addAttribute(
                        "completedTasks",
                        report.getCompletedTasks());
                model.addAttribute(
                        "openTasks",
                        report.getOpenTasks());
                model.addAttribute(
                        "problemsIncidents",
                        report.getProblemsIncidents());
                model.addAttribute(
                        "importantNotes",
                        report.getImportantNotes());
                model.addAttribute(
                        "selectedShift",
                        report.getShift());
                model.addAttribute(
                        "selectedPriority",
                        report.getPriority());
                model.addAttribute(
                        "shifts",
                        Shift.values());
                model.addAttribute(
                        "priorities",
                        Priority.values());
                model.addAttribute("editMode", true);

                return "reports/form";
            })
            .orElseGet(() -> {
                redirectAttributes.addFlashAttribute(
                        "errorMessage",
                        "Der Report wurde nicht gefunden.");

                return "redirect:/reports";
            });
}

@PostMapping("/{id}/edit")
public String update(
        @PathVariable Long id,
        @RequestParam String completedTasks,
        @RequestParam(defaultValue = "")
        String openTasks,
        @RequestParam(defaultValue = "")
        String problemsIncidents,
        @RequestParam(defaultValue = "")
        String importantNotes,
        @RequestParam Shift shift,
        @RequestParam(required = false)
        Priority priority,
        Principal principal,
        Model model,
        RedirectAttributes redirectAttributes) {

    try {
        String username = principal == null
        ? "unbekannt"
        : principal.getName();
        reportService.update(
                id,
                completedTasks,
                openTasks,
                problemsIncidents,
                importantNotes,
                shift,
                priority,
                username);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Der Report wurde erfolgreich bearbeitet.");

        return "redirect:/reports/" + id;
    } catch (IllegalArgumentException exception) {
        model.addAttribute(
                "errorMessage",
                exception.getMessage());
        model.addAttribute("reportId", id);
        model.addAttribute(
                "completedTasks",
                completedTasks);
        model.addAttribute("openTasks", openTasks);
        model.addAttribute(
                "problemsIncidents",
                problemsIncidents);
        model.addAttribute(
                "importantNotes",
                importantNotes);
        model.addAttribute("selectedShift", shift);
        model.addAttribute(
                "selectedPriority",
                priority);
        model.addAttribute("shifts", Shift.values());
        model.addAttribute(
                "priorities",
                Priority.values());
        model.addAttribute("editMode", true);

        return "reports/form";
    }
}

    @PostMapping("/{id}/complete")
    public String markAsCompleted(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        try {
            reportService.markAsCompleted(id);
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Der Report wurde als erledigt markiert.");
        } catch (IllegalArgumentException exception) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    exception.getMessage());
        }

        return "redirect:/reports/" + id;
    }
    @PostMapping("/{id}/delete")
    public String delete(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        try {
            reportService.delete(id);
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Der Report wurde gelöscht.");
            return "redirect:/reports";
        } catch (IllegalArgumentException exception) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    exception.getMessage());
            return "redirect:/reports/" + id;
        }
    }
}