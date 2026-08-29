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

## 3. UC-02 – Abmelden

### Ziel

Eine angemeldete Person beendet ihre Sitzung, sodass niemand über dasselbe
Gerät weiterhin auf geschützte Informationen zugreifen kann.

### Vorbedingungen

- Die Person ist angemeldet.

### Standardablauf

1. Die Person wählt „Abmelden“.
2. Das System beendet die aktive Sitzung.
3. Das System zeigt die Anmeldeseite mit einer Abmeldebestätigung an.

### Nachbedingungen

- Es besteht keine angemeldete Sitzung mehr.
- Geschützte Seiten können erst nach einer erneuten Anmeldung aufgerufen werden.

### Akzeptanzkriterien

| ID | Gegeben | Wenn | Dann |
|---|---|---|---|
| AK-06 | Eine Person ist angemeldet | sie wählt „Abmelden“ | die Sitzung wird beendet und die Anmeldeseite erscheint |
| AK-07 | Eine Person hat sich abgemeldet | sie ruft eine geschützte Seite auf | sie wird erneut zur Anmeldung aufgefordert |

## 4. UC-03 – Schicht auswählen

### Ziel

Eine angemeldete Person wählt die Schicht aus, für die sie Informationen
einsehen beziehungsweise einen Report erstellen möchte.

### Primäre Akteur:innen

- Mitarbeiter:in
- Schichtleitung

### Vorbedingungen

- Die Person ist angemeldet.
- Die auswählbaren Schichten sind im System vorhanden.

### Standardablauf

1. Das System zeigt die verfügbaren Schichten an.
2. Die Person wählt Frühschicht, Spätschicht oder Nachtschicht.
3. Die Person bestätigt ihre Auswahl.
4. Das System übernimmt die gewählte Schicht als aktuellen Arbeitskontext.
5. Das System zeigt die zugehörige Übersichtsseite an.

### Alternativablauf – Keine Schicht ausgewählt

1. Die Person bestätigt das Formular ohne Auswahl.
2. Das System übernimmt keine Schicht.
3. Das System fordert zur Auswahl einer Schicht auf.

### Nachbedingungen bei Erfolg

- Für die aktuelle Sitzung ist eine Schicht ausgewählt.
- Ein neu erstellter Report kann dieser Schicht zugeordnet werden.

### Akzeptanzkriterien

| ID | Gegeben | Wenn | Dann |
|---|---|---|---|
| AK-08 | Die Person ist angemeldet | eine gültige Schicht wird ausgewählt | die Schicht wird als aktueller Arbeitskontext übernommen |
| AK-09 | Keine Schicht wurde ausgewählt | die Auswahl wird bestätigt | das System fordert zur Auswahl einer Schicht auf |
| AK-10 | Eine Schicht wurde ausgewählt | die Report-Erfassung wird geöffnet | die ausgewählte Schicht ist dem neuen Report zugeordnet |

## 5. UC-04 – Report erstellen

### Ziel

Eine angemeldete Person dokumentiert den Arbeitsstand ihrer Schicht in einem
strukturierten Report.

### Primäre Akteur:innen

- Mitarbeiter:in
- Schichtleitung

### Vorbedingungen

- Die Person ist angemeldet.
- Eine Schicht ist ausgewählt.

### Standardablauf

1. Die Person wählt „Report erstellen“.
2. Das System zeigt das Report-Formular an.
3. Die Person erfasst erledigte Aufgaben.
4. Die Person erfasst offene Aufgaben.
5. Die Person erfasst vorhandene Probleme oder Incidents und deren Priorität.
6. Die Person ergänzt bei Bedarf wichtige Hinweise.
7. Die Person bestätigt die Speicherung.
8. Das System prüft die Eingaben.
9. Das System ergänzt automatisch Schicht, Autor:in und Erstellungszeitpunkt.
10. Das System speichert den Report.
11. Das System zeigt eine Erfolgsbestätigung beziehungsweise den gespeicherten Report an.

### Vorläufige Validierungsregel

Mindestens eines der fachlichen Eingabefelder muss einen Inhalt besitzen.
Diese Regel muss beim Teamtermin bestätigt werden. -> 31.08.2026

### Alternativabläufe und Fehlerfälle

#### A1 – Kein fachlicher Inhalt

1. Alle fachlichen Eingabefelder sind leer.
2. Das System speichert den Report nicht.
3. Das System fordert zur Eingabe mindestens eines Inhalts auf.

#### A2 – Problem ohne gültige Priorität

1. Die Person erfasst ein Problem, aber keine gültige Priorität.
2. Das System speichert den Report nicht.
3. Das System fordert zur Auswahl einer Priorität auf.

#### A3 – Technischer Speicherfehler

1. Der Report kann technisch nicht gespeichert werden.
2. Das System zeigt eine verständliche Fehlermeldung an.
3. Das System zeigt keine erfolgreiche Speicherung an.

### Nachbedingungen bei Erfolg

- Der Report ist dauerhaft gespeichert.
- Autor:in, Schicht und Erstellungszeitpunkt sind nachvollziehbar.
- Der Report kann in Übergabe und Historie angezeigt werden.

### Akzeptanzkriterien

| ID | Gegeben | Wenn | Dann |
|---|---|---|---|
| AK-11 | Eine Person ist angemeldet und hat eine Schicht gewählt | ein gültiger Report wird gespeichert | der Report erhält Schicht, Autor:in und Erstellungszeitpunkt |
| AK-12 | Alle fachlichen Felder sind leer | die Speicherung wird bestätigt | der Report wird nicht gespeichert |
| AK-13 | Ein Problem wurde erfasst | keine Priorität wurde gewählt | der Report wird nicht gespeichert und das Feld wird gekennzeichnet |
| AK-14 | Ein gültiger Report wurde gespeichert | die Übergabeansicht wird geöffnet | der Report kann dort angezeigt werden |

## 6. UC-05 – Übergabe einsehen

### Ziel

Eine angemeldete Person sieht den zuletzt gespeicherten Report, um den
aktuellen Arbeitsstand der vorherigen Schicht zu übernehmen.

### Primäre Akteur:innen

- Mitarbeiter:in
- Schichtleitung

### Vorbedingungen

- Die Person ist angemeldet.

### Standardablauf

1. Die Person öffnet die Übergabeansicht.
2. Das System ermittelt den zuletzt gespeicherten Report.
3. Das System zeigt Schicht, Autor:in und Erstellungszeitpunkt an.
4. Das System stellt erledigte Aufgaben, offene Aufgaben, Probleme,
   Prioritäten und Hinweise getrennt dar.

### Alternativablauf – Noch kein Report vorhanden

1. Im System ist noch kein Report gespeichert.
2. Das System zeigt den Hinweis „Noch keine Übergabe vorhanden“ an.
3. Die übrige Navigation bleibt verwendbar.

### Nachbedingungen

- Es werden keine Daten verändert.
- Die Person kennt den zuletzt dokumentierten Arbeitsstand.

### Akzeptanzkriterien

| ID | Gegeben | Wenn | Dann |
|---|---|---|---|
| AK-15 | Mindestens ein Report existiert | die Übergabeansicht wird geöffnet | der zuletzt gespeicherte Report wird angezeigt |
| AK-16 | Ein Report wird angezeigt | er enthält verschiedene Kategorien | die Inhalte werden getrennt und verständlich dargestellt |
| AK-17 | Es existiert kein Report | die Übergabeansicht wird geöffnet | ein verständlicher Hinweis wird angezeigt |

## 7. UC-06 – Report-Historie anzeigen

### Ziel

Eine angemeldete Person kann vergangene Reports nachvollziehen.

### Primäre Akteur:innen

- Mitarbeiter:in
- Schichtleitung

### Vorbedingungen

- Die Person ist angemeldet.

### Standardablauf

1. Die Person öffnet die Report-Historie.
2. Das System lädt die gespeicherten Reports.
3. Das System zeigt die Reports absteigend nach Erstellungszeitpunkt an.
4. Jeder Listeneintrag zeigt mindestens Schicht, Autor:in und Erstellungszeitpunkt.
5. Die Person wählt einen Report aus.
6. Das System zeigt dessen vollständige Inhalte an.

### Alternativablauf – Historie ist leer

1. Es ist noch kein Report gespeichert.
2. Das System zeigt einen verständlichen Hinweis an.

### Nachbedingungen

- Es werden keine Reports verändert.
- Die Person kann einen früheren Arbeitsstand nachvollziehen.

### Akzeptanzkriterien

| ID | Gegeben | Wenn | Dann |
|---|---|---|---|
| AK-18 | Mehrere Reports existieren | die Historie wird geöffnet | die neuesten Reports stehen zuerst |
| AK-19 | Die Historie wird angezeigt | ein Report wird ausgewählt | sämtliche Inhalte des Reports werden angezeigt |
| AK-20 | Es existiert kein Report | die Historie wird geöffnet | ein verständlicher Hinweis wird angezeigt |

## 8. Nicht enthaltene Anwendungsfälle

Folgende Funktionen gehören nicht zum verbindlichen Minimalumfang:

- Reports löschen
- gespeicherte Reports nachträglich bearbeiten
- Benutzerkonten über die Oberfläche verwalten
- Reports exportieren
- Benachrichtigungen versenden
- Reports kommentieren
- statistische Auswertungen erstellen

Änderungen an dieser Abgrenzung müssen vom Team beschlossen und anschließend
in P1, F1, F2, Architektur und Implementierung konsistent übernommen werden.