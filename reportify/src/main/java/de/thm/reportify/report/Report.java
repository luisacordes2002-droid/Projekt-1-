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

    @Column(nullable = false, length = 120)
    private String title;

    @Column(nullable = false, length = 4000)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 20,
            columnDefinition = "varchar(20) default 'FRUEHSCHICHT'")
    private Shift shift = Shift.FRUEHSCHICHT;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Priority priority = Priority.MITTEL;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status = Status.OFFEN;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false, length = 100)
    private String createdBy;

    protected Report() {
    }

    public Report(String title,String content,Shift shift,Priority priority,String createdBy) {
        this.title = title;
        this.content = content;
        this.shift = shift;
        this.priority = priority;
        this.createdBy = createdBy;
    }

    @PrePersist
    void prepareForInsert() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }

        if (shift == null) {
            shift = Shift.FRUEHSCHICHT;
        }

        if (priority == null) {
            priority = Priority.MITTEL;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

}