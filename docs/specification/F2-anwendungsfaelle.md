# F2 – Anwendungsfälle

> **Status:** Entwurf vom 29. August 2026. Noch nicht vom Team abschließend
> geprüft und freigegeben.

## 1. Übersicht

| ID | Anwendungsfall | Primäre Akteur:innen | Priorität |
|---|---|---|---|
| UC-01 | Anmelden | Mitarbeiter:in, Schichtleitung | Muss |
| UC-02 | Abmelden | Angemeldete Nutzer:innen | Muss |
| UC-03 | Schicht auswählen | Mitarbeiter:in, Schichtleitung | Muss |
| UC-04 | Report erstellen | Mitarbeiter:in, Schichtleitung | Muss |
| UC-05 | Übergabe einsehen | Mitarbeiter:in, Schichtleitung | Muss |
| UC-06 | Report-Historie anzeigen | Mitarbeiter:in, Schichtleitung | Muss |

## 2. UC-01 – Anmelden

### Ziel

Eine berechtigte Person meldet sich mit Benutzername und Passwort bei
Reportify an, um auf die geschützten Funktionen der Anwendung zugreifen zu
können.

### Primäre Akteur:innen

- Mitarbeiter:in
- Schichtleitung

### Auslöser

Eine nicht angemeldete Person öffnet Reportify oder versucht, eine geschützte
Seite aufzurufen.

### Vorbedingungen

- Reportify ist erreichbar.
- Für die Person existiert ein gültiges Benutzerkonto.
- Die Person ist noch nicht angemeldet.

### Standardablauf

1. Die Person öffnet Reportify.
2. Das System zeigt die Anmeldeseite an.
3. Die Person gibt Benutzername und Passwort ein.
4. Die Person bestätigt die Anmeldung.
5. Das System prüft die eingegebenen Zugangsdaten.
6. Das System erstellt bei gültigen Zugangsdaten eine angemeldete Sitzung.
7. Das System leitet die Person zur Startseite weiter.

### Alternativabläufe und Fehlerfälle

#### A1 – Pflichtfeld fehlt

1. Die Person lässt Benutzername oder Passwort leer.
2. Das System führt keine Anmeldung durch.
3. Das System fordert die Person auf, beide Felder auszufüllen.

#### A2 – Zugangsdaten sind ungültig

1. Benutzername oder Passwort sind nicht korrekt.
2. Das System führt keine Anmeldung durch.
3. Das System zeigt eine allgemeine Fehlermeldung an.
4. Das System verrät nicht, ob Benutzername oder Passwort falsch war.
5. Die Person kann die Eingabe erneut versuchen.

### Nachbedingungen bei Erfolg

- Die Person ist eindeutig authentifiziert.
- Eine Sitzung für die angemeldete Person ist aktiv.
- Die Person kann entsprechend ihrer Rolle auf freigegebene Funktionen zugreifen.

### Nachbedingungen bei Misserfolg

- Es wird keine angemeldete Sitzung erstellt.
- Geschützte Funktionen bleiben gesperrt.

### Akzeptanzkriterien

| ID | Gegeben | Wenn | Dann |
|---|---|---|---|
| AK-01 | Ein gültiges Benutzerkonto existiert | korrekte Zugangsdaten werden eingegeben | die Anmeldung ist erfolgreich und die Startseite wird angezeigt |
| AK-02 | Die Person ist nicht angemeldet | eine geschützte Seite wird aufgerufen | die Anmeldeseite wird angezeigt |
| AK-03 | Die Person befindet sich auf der Anmeldeseite | Benutzername oder Passwort fehlen | die Anmeldung wird nicht durchgeführt |
| AK-04 | Die Person befindet sich auf der Anmeldeseite | ungültige Zugangsdaten werden eingegeben | eine allgemeine Fehlermeldung wird angezeigt |
| AK-05 | Die Anmeldung ist fehlgeschlagen | die geschützte Startseite wird aufgerufen | der Zugriff bleibt gesperrt |
