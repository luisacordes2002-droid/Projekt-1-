package de.thm.reportify.report;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "reports")
public class Report {

        private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public enum Priority {
        NIEDRIG,
        MITTEL,
        HOCH
    }

    public enum Shift {
        FRUEHSCHICHT,
        SPAETSCHICHT,
        NACHTSCHICHT
    }

    public enum Status {
        OFFEN,
        ERLEDIGT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Column(nullable = false, length = 4000)
        private String completedTasks;

@Column(length = 4000)
        private String openTasks;

@Column(length = 4000)
        private String problemsIncidents;

@Column(length = 4000)
        private String importantNotes;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 20,
            columnDefinition = "varchar(20) default 'FRUEHSCHICHT'")
    private Shift shift;
@Enumerated(EnumType.STRING)
    @Column(length = 20)
        private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status = Status.OFFEN;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false, length = 100)
    private String createdBy;
@Column
private LocalDateTime updatedAt;

@Column(length = 100)
private String updatedBy;

    protected Report() {
    }

    public Report(
            String completedTasks,
            String openTasks,
            String problemsIncidents,
            String importantNotes,
            Shift shift,
            Priority priority,
            String createdBy) {

        this.completedTasks = completedTasks;
        this.openTasks = openTasks;
        this.problemsIncidents = problemsIncidents;
        this.importantNotes = importantNotes;
        this.shift = shift;
        this.priority = priority;
        this.createdBy = createdBy;
}

    @PrePersist
    void prepareForInsert() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }

        if (status == null) {
            status = Status.OFFEN;
        }

        if (createdBy == null || createdBy.isBlank()) {
            createdBy = "unbekannt";
        }
    }

    public Long getId() {
        return id;
    }

   public String getCompletedTasks() {
        return completedTasks;
}

public void setCompletedTasks(String completedTasks) {
    this.completedTasks = completedTasks;
}

public String getOpenTasks() {
    return openTasks;
}

public void setOpenTasks(String openTasks) {
    this.openTasks = openTasks;
}

public String getProblemsIncidents() {
    return problemsIncidents;
}

public void setProblemsIncidents(String problemsIncidents) {
    this.problemsIncidents = problemsIncidents;
}

public String getImportantNotes() {
    return importantNotes;
}

public void setImportantNotes(String importantNotes) {
    this.importantNotes = importantNotes;
}

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }
    public String getFormattedCreatedAt() {
        return createdAt == null ? "" : createdAt.format(DISPLAY_FORMAT);
    }
public void recordUpdate(String updatedBy) {
    this.updatedAt = LocalDateTime.now();
    this.updatedBy =
            updatedBy == null || updatedBy.isBlank()
                    ? "unbekannt"
                    : updatedBy;
}

public LocalDateTime getUpdatedAt() {
    return updatedAt;
}

public String getUpdatedBy() {
    return updatedBy;
}

public String getFormattedUpdatedAt() {
    return updatedAt == null
            ? ""
            : updatedAt.format(DISPLAY_FORMAT);
}
}