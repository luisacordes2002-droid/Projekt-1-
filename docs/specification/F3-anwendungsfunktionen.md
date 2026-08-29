# F3 – Anwendungsfunktionen

> **Status:** Arbeitsentwurf vom 29.08.2026.  
> Die Inhalte müssen vom Projektteam fachlich geprüft und bei Änderungen an den
> Anwendungsfällen aktualisiert werden.

## 1. Zweck

Dieses Dokument beschreibt die fachlichen Funktionen von Reportify. Die Funktionen
realisieren die in [F2 – Anwendungsfälle](F2-anwendungsfaelle.md) beschriebenen
Interaktionen.

Die Beschreibung bleibt unabhängig von der technischen Implementierung. Konkrete
Frameworks, Klassen und Datenbanktabellen werden in der Architekturdokumentation
beziehungsweise im Quellcode beschrieben.

## 2. Funktionsübersicht

| ID | Anwendungsfunktion | Zugehörige Anwendungsfälle |
|---|---|---|
| AF-01 | Zugangsdaten prüfen | UC-01 |
| AF-02 | Sitzung verwalten | UC-01, UC-02 |
| AF-03 | Schichten bereitstellen | UC-03 |
| AF-04 | Report-Eingaben validieren | UC-04 |
| AF-05 | Report speichern | UC-04 |
| AF-06 | Aktuelle Übergabe bestimmen | UC-05 |
| AF-07 | Report-Historie bereitstellen | UC-06 |
| AF-08 | Report-Details bereitstellen | UC-05, UC-06 |

## 3. Funktionsbeschreibungen

### AF-01 – Zugangsdaten prüfen

**Zweck:**  
Feststellen, ob sich eine berechtigte Person mit gültigen Zugangsdaten anmeldet.

**Eingaben:**

- Benutzername
- Passwort

**Verarbeitung:**

1. Das System prüft, ob die Zugangsdaten zu einem vorhandenen Benutzerkonto gehören.
2. Das eingegebene Passwort wird sicher mit den gespeicherten Zugangsdaten verglichen.
3. Bei ungültigen Zugangsdaten wird die Anmeldung abgelehnt.
4. Die Fehlermeldung verrät nicht, ob der Benutzername oder das Passwort falsch war.

**Ergebnis:**

- erfolgreiche Authentifizierung oder
- abgelehnte Anmeldung mit verständlicher Fehlermeldung

### AF-02 – Sitzung verwalten

**Zweck:**  
Den Anmeldestatus einer Person während der Nutzung von Reportify verwalten.

**Eingaben:**

- Ergebnis der erfolgreichen Anmeldung
- Abmeldeanforderung

**Verarbeitung:**

1. Nach erfolgreicher Anmeldung erstellt das System eine gültige Sitzung.
2. Geschützte Funktionen sind nur innerhalb einer gültigen Sitzung erreichbar.
3. Bei der Abmeldung beendet das System die Sitzung.
4. Nach der Abmeldung dürfen geschützte Seiten nicht mehr aufgerufen werden.

**Ergebnis:**

- aktive Sitzung nach der Anmeldung oder
- beendete Sitzung nach der Abmeldung

### AF-03 – Schichten bereitstellen

**Zweck:**  
Die auswählbaren Arbeitsschichten für einen Report bereitstellen.

**Auswahlwerte:**

- Frühschicht
- Spätschicht
- Nachtschicht

**Verarbeitung:**

1. Das System stellt die verfügbaren Schichten zur Auswahl.
2. Die ausgewählte Schicht wird für die weitere Reporterstellung übernommen.
3. Ohne gültige Schichtauswahl kann kein Report gespeichert werden.

**Ergebnis:**

- eine eindeutig ausgewählte Schicht

### AF-04 – Report-Eingaben validieren

**Zweck:**  
Prüfen, ob ein Report fachlich gültig und speicherbar ist.

**Eingaben:**

- ausgewählte Schicht
- erledigte Aufgaben
- offene Aufgaben
- Probleme oder Incidents
- Priorität
- wichtige Hinweise

**Validierungsregeln:**

1. Eine gültige Schicht muss ausgewählt sein.
2. Mindestens eines der fachlichen Textfelder muss einen Inhalt besitzen.
3. Reine Leerzeichen gelten nicht als Inhalt.
4. Wenn ein Problem oder Incident eingetragen wurde, muss eine Priorität angegeben sein.
5. Die zulässigen Prioritäten sind `NIEDRIG`, `MITTEL` und `HOCH`.
6. Fehlerhafte Felder werden verständlich gekennzeichnet.

**Ergebnis:**

- gültige Report-Daten oder
- konkrete Validierungsfehler

> **Vorläufige Regel:** Die Pflichtfelder müssen vom Team noch abschließend bestätigt
> werden.

### AF-05 – Report speichern

**Zweck:**  
Einen fachlich gültigen Report dauerhaft sichern.

**Vorbedingung:**

- AF-04 hat die Report-Daten erfolgreich validiert.

**Verarbeitung:**

1. Das System übernimmt die eingegebenen Report-Daten.
2. Das System ergänzt die erstellende Person.
3. Das System ergänzt Datum und Uhrzeit der Erstellung.
4. Der Report wird dauerhaft gespeichert.
5. Der gespeicherte Report steht anschließend für Übergabe und Historie zur Verfügung.

**Ergebnis:**

- ein eindeutig gespeicherter Report mit Erstellungszeitpunkt und Ersteller:in

### AF-06 – Aktuelle Übergabe bestimmen

**Zweck:**  
Den für die nächste Schicht aktuellsten Übergabereport bereitstellen.

**Eingaben:**

- gespeicherte Reports

**Verarbeitung:**

1. Das System ermittelt den zuletzt gespeicherten Report.
2. Der vollständige Inhalt dieses Reports wird als aktuelle Übergabe bereitgestellt.
3. Wenn noch kein Report existiert, wird ein verständlicher Leerzustand angezeigt.

**Ergebnis:**

- aktuellster Report oder
- Hinweis, dass noch keine Übergabe vorhanden ist

> **Vorläufige Regel:** Der aktuelle Report wird zunächst über den
> Erstellungszeitpunkt bestimmt. Das Team muss noch prüfen, ob zusätzlich die
> Reihenfolge der Schichten berücksichtigt werden soll.

### AF-07 – Report-Historie bereitstellen

**Zweck:**  
Alle gespeicherten Reports nachvollziehbar anzeigen.

**Eingaben:**

- gespeicherte Reports

**Verarbeitung:**

1. Das System lädt alle vorhandenen Reports.
2. Die Reports werden absteigend nach ihrem Erstellungszeitpunkt sortiert.
3. Der neueste Report steht an erster Stelle.
4. Jeder Eintrag zeigt mindestens Schicht, Zeitpunkt, Ersteller:in und Priorität.
5. Wenn keine Reports vorhanden sind, wird ein verständlicher Leerzustand angezeigt.

**Ergebnis:**

- chronologisch sortierte Report-Liste oder
- Hinweis, dass noch keine Reports vorhanden sind

### AF-08 – Report-Details bereitstellen

**Zweck:**  
Den vollständigen Inhalt eines ausgewählten Reports anzeigen.

**Eingaben:**

- eindeutige Kennung des ausgewählten Reports

**Verarbeitung:**

1. Das System sucht den ausgewählten Report.
2. Bei vorhandenem Report werden alle fachlichen Inhalte angezeigt.
3. Existiert der Report nicht, wird eine verständliche Fehlermeldung angezeigt.

**Ergebnis:**

- vollständige Report-Details oder
- Meldung, dass der Report nicht gefunden wurde

## 4. Abgrenzung

Folgende Funktionen sind für die erste Version nicht vorgesehen:

- Löschen gespeicherter Reports
- nachträgliches Bearbeiten gespeicherter Reports
- Datei- oder Bildanhänge
- automatische Benachrichtigungen
- Chat zwischen Mitarbeitenden
- externe Schnittstellen
- statistische Auswertungen

Änderungen an dieser Abgrenzung müssen zuerst in der Spezifikation dokumentiert
werden, bevor sie implementiert werden.

## 5. Nachverfolgbarkeit

Die Kennungen `AF-01` bis `AF-08` bleiben stabil. Sie werden später in folgenden
Dokumenten wiederverwendet:

- Datenmodell
- Dialogspezifikation
- Architekturdokumentation
- Tests und Akzeptanzkriterien
