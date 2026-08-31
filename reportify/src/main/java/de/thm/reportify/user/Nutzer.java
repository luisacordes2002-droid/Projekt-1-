package de.thm.reportify.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "nutzer")
public class Nutzer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nutzerId;

    @Column(nullable = false, unique = true, length = 50)
    private String benutzername;

    @Column(nullable = false, length = 100)
    private String anzeigename;

    @Column(nullable = false, length = 255)
    private String passwortNachweis;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Rolle rolle;

    @Column(nullable = false)
    private boolean aktiv;

    protected Nutzer() {
        // Dieser Konstruktor wird von JPA benötigt.
    }

    public Nutzer(
            String benutzername,
            String anzeigename,
            String passwortNachweis,
            Rolle rolle,
            boolean aktiv) {
        this.benutzername = benutzername;
        this.anzeigename = anzeigename;
        this.passwortNachweis = passwortNachweis;
        this.rolle = rolle;
        this.aktiv = aktiv;
    }

    public Long getNutzerId() {
        return nutzerId;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public String getAnzeigename() {
        return anzeigename;
    }

    public String getPasswortNachweis() {
        return passwortNachweis;
    }

    public Rolle getRolle() {
        return rolle;
    }

    public boolean isAktiv() {
        return aktiv;
    }
}