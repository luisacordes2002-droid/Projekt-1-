# D2 – Datentypenverzeichnis

> **Status:** Arbeitsentwurf vom 30.08.2026.  
> Die Werte und Grenzen sind Arbeitsannahmen und müssen vom Projektteam geprüft
> werden.

## 1. Zweck

Dieses Dokument definiert die fachlichen Datentypen von Reportify. Es ergänzt das
[D1-Datenmodell](D1-datenmodell.md) um zulässige Werte, Formate und
Validierungsregeln.

Die Definitionen sind unabhängig von Java-Datentypen, Datenbankspalten und
Frameworks. Technische Abbildungen werden in der Architektur beschrieben.

## 2. Typübersicht

| ID | Datentyp | Verwendung |
|---|---|---|
| DT-01 | `NutzerIdDT` | Eindeutige Kennung eines Benutzerkontos |
| DT-02 | `ReportIdDT` | Eindeutige Kennung eines Reports |
| DT-03 | `BenutzernameDT` | Anmeldung und eindeutige Zuordnung |
| DT-04 | `AnzeigenameDT` | Darstellung der erstellenden Person |
| DT-05 | `PasswortDT` | Eingabe eines Passworts |
| DT-06 | `PasswortNachweisDT` | Sicherer Nachweis eines Passworts |
| DT-07 | `RolleDT` | Rolle einer Person |
| DT-08 | `AktivDT` | Aktivierungszustand eines Benutzerkontos |
| DT-09 | `SchichtDT` | Zuordnung eines Reports zu einer Schicht |
| DT-10 | `PrioritaetDT` | Dringlichkeit eines Problems oder Incidents |
| DT-11 | `ReportTextDT` | Fachliche Textinhalte eines Reports |
| DT-12 | `ZeitpunktDT` | Erstellungszeitpunkt eines Reports |

## 3. DT-01 – NutzerIdDT

### Bedeutung

Eindeutige und unveränderliche Kennung eines Benutzerkontos.

### Regeln

- Jeder Nutzer besitzt genau eine Kennung.
- Zwei Nutzer dürfen niemals dieselbe Kennung besitzen.
- Die Kennung bleibt bei Änderungen des Benutzer- oder Anzeigenamens unverändert.
- Die Kennung enthält keine fachliche Bedeutung.
- Die konkrete technische Erzeugung wird in der Architektur festgelegt.

### Verwendung

- `Nutzer.nutzerId`
- `Report.erstelltVon`

## 4. DT-02 – ReportIdDT

### Bedeutung

Eindeutige und unveränderliche Kennung eines gespeicherten Reports.

### Regeln

- Jeder gespeicherte Report besitzt genau eine Kennung.
- Die Kennung wird beim Speichern erzeugt.
- Zwei Reports dürfen niemals dieselbe Kennung besitzen.
- Die Kennung darf nach der Erstellung nicht geändert werden.
- Aus der Kennung dürfen keine fachlichen Informationen abgeleitet werden.

### Verwendung

- `Report.reportId`
- Auswahl eines Reports in der Historie
- Aufruf der Report-Detailansicht

## 5. DT-03 – BenutzernameDT

### Bedeutung

Name, mit dem sich eine Person bei Reportify anmeldet.

### Regeln

- Länge: mindestens 3 und höchstens 50 Zeichen.
- Zulässig sind Buchstaben, Ziffern, Punkt, Bindestrich und Unterstrich.
- Leerzeichen sind nicht zulässig.
- Der Benutzername muss eindeutig sein.
- Groß- und Kleinschreibung sollen bei der Eindeutigkeitsprüfung nicht zu zwei
  unterschiedlichen Konten führen.
- Vor und nach der Eingabe stehende Leerzeichen werden entfernt.

### Gültige Beispiele

- `souhaib`
- `luisa.cordes`
- `schichtleitung_1`

### Ungültige Beispiele

- `ab`
- `schicht leitung`
- ein bereits vergebener Benutzername

## 6. DT-04 – AnzeigenameDT

### Bedeutung

Menschenlesbarer Name einer Person, der in Reports und in der Historie angezeigt
wird.

### Regeln

- Länge: mindestens 1 und höchstens 100 Zeichen.
- Vor und nach dem Namen stehende Leerzeichen werden entfernt.
- Der Anzeigename muss nicht eindeutig sein.
- Der Anzeigename darf nicht für die Anmeldung verwendet werden.

### Beispiele

- `Souhaib Boujemaoui`
- `Luisa Cordes`
- `Schichtleitung Produktion`

## 7. DT-05 – PasswortDT

### Bedeutung

Geheime Eingabe, mit der sich eine Person authentifiziert.

### Regeln

- Das Passwort wird bei der Eingabe nicht sichtbar angezeigt.
- Vorläufige Mindestlänge: 8 Zeichen.
- Vorläufige Höchstlänge: 128 Zeichen.
- Leerzeichen innerhalb eines Passworts sind zulässig.
- Das Passwort darf weder protokolliert noch als Klartext gespeichert werden.
- Bei einer fehlgeschlagenen Anmeldung wird das eingegebene Passwort verworfen.

> Die endgültige Passwortregel wird zusammen mit den Sicherheitsanforderungen
> festgelegt.

## 8. DT-06 – PasswortNachweisDT

### Bedeutung

Sicher gespeicherter Nachweis, mit dem ein eingegebenes Passwort überprüft werden
kann.

### Regeln

- Der Nachweis ist kein Klartextpasswort.
- Aus dem Nachweis darf das ursprüngliche Passwort nicht direkt ermittelt werden
  können.
- Der Nachweis darf niemals in der Benutzeroberfläche angezeigt werden.
- Der Nachweis darf nicht in normalen Anwendungsprotokollen erscheinen.
- Das konkrete Sicherheitsverfahren wird in der Architektur festgelegt.

## 9. DT-07 – RolleDT

### Bedeutung

Fachliche Rolle einer Person innerhalb von Reportify.

### Zulässige Werte

| Technischer Schlüssel | Anzeige | Bedeutung |
|---|---|---|
| `MITARBEITER` | Mitarbeiter:in | Erstellt und liest Schichtreports |
| `SCHICHTLEITUNG` | Schichtleitung | Erstellt und liest Schichtreports |

### Regeln

- Jedes Benutzerkonto besitzt genau eine Rolle.
- Weitere Rollen sind in der ersten Version nicht vorgesehen.
- Zusätzliche Rechte der Schichtleitung sind Gegenstand von `TD-006`.

## 10. DT-08 – AktivDT

### Bedeutung

Kennzeichnet, ob ein Benutzerkonto aktuell verwendet werden darf.

### Zulässige Werte

| Wert | Bedeutung |
|---|---|
| `WAHR` | Das Benutzerkonto darf sich anmelden. |
| `FALSCH` | Eine Anmeldung ist nicht erlaubt. |

### Regeln

- Neue, freigegebene Benutzerkonten sind aktiv.
- Ein inaktives Konto darf keine neue Sitzung erhalten.
- Bereits erstellte Reports bleiben bei einer Deaktivierung erhalten.

## 11. DT-09 – SchichtDT

### Bedeutung

Bezeichnet die Arbeitsschicht, auf die sich ein Report bezieht.

### Zulässige Werte

| Schlüssel | Anzeige |
|---|---|
| `FRUEHSCHICHT` | Frühschicht |
| `SPAETSCHICHT` | Spätschicht |
| `NACHTSCHICHT` | Nachtschicht |

### Regeln

- Jeder Report besitzt genau eine Schicht.
- Freie Texteingaben für die Schicht sind nicht erlaubt.
- Die Schicht muss vor dem Speichern ausgewählt werden.
- Die Schlüssel bleiben stabil, auch wenn sich die sichtbare Bezeichnung ändert.

## 12. DT-10 – PrioritaetDT

### Bedeutung

Beschreibt die Dringlichkeit eines Problems oder Incidents.

### Zulässige Werte

| Schlüssel | Anzeige | Fachliche Bedeutung |
|---|---|---|
| `NIEDRIG` | Niedrig | Keine unmittelbare Bearbeitung erforderlich |
| `MITTEL` | Mittel | Zeitnahe Bearbeitung erforderlich |
| `HOCH` | Hoch | Dringende Bearbeitung durch die nächste Schicht |

### Regeln

- Wenn `problemeIncidents` ausgefüllt ist, muss eine Priorität angegeben werden.
- Ohne Problem oder Incident darf die Priorität leer bleiben.
- Freie Prioritätsangaben sind nicht zulässig.
- Die genaue fachliche Zuordnung ist Gegenstand von `TD-002`.

## 13. DT-11 – ReportTextDT

### Bedeutung

Textinhalt eines fachlichen Report-Feldes.

Dieser Datentyp wird verwendet für:

- erledigte Aufgaben
- offene Aufgaben
- Probleme und Incidents
- wichtige Hinweise

### Regeln

- Vor und nach dem Text stehende Leerzeichen werden entfernt.
- Ein Text, der nur aus Leerzeichen besteht, gilt als leer.
- Vorläufige Maximallänge: 4.000 Zeichen je Feld.
- Zeilenumbrüche sind zulässig.
- HTML- oder Skriptcode darf nicht als ausführbarer Inhalt interpretiert werden.
- Passwörter und andere Zugangsdaten dürfen nicht in Report-Texten erfasst werden.
- Mindestens eines der fachlichen Report-Felder muss Inhalt besitzen.

## 14. DT-12 – ZeitpunktDT

### Bedeutung

Datum und Uhrzeit, zu denen ein Report gespeichert wurde.

### Regeln

- Der Zeitpunkt wird durch Reportify vergeben.
- Nutzer:innen können den Erstellungszeitpunkt nicht frei eingeben.
- Der Zeitpunkt enthält Datum, Uhrzeit und eine eindeutige Zeitzoneninformation.
- In der Benutzeroberfläche wird der Zeitpunkt in verständlicher lokaler Form
  angezeigt.
- Für die Sortierung wird der vollständige Zeitpunkt verwendet.
- Wenn zwei Reports kurz nacheinander erstellt werden, muss ihre Reihenfolge
  weiterhin eindeutig bestimmbar sein.

### Beispielanzeige

```text
30.08.2026, 14:35 Uhr
```

## 15. Allgemeine Validierungsregeln

1. Pflichtwerte dürfen nicht fehlen.
2. Nicht erlaubte Auswahlwerte werden zurückgewiesen.
3. Texte werden vor der fachlichen Prüfung von äußeren Leerzeichen bereinigt.
4. Fehlermeldungen benennen das betroffene Feld und die verletzte Regel.
5. Ungültige Eingaben werden nicht dauerhaft gespeichert.
6. Interne Kennungen werden nicht durch frei eingegebene Werte ersetzt.
7. Geheime Werte werden weder angezeigt noch protokolliert.

## 16. Datenschutzklassifikation

| Datentyp | Klassifikation | Begründung |
|---|---|---|
| `NutzerIdDT` | Intern | Technische Zuordnung eines Kontos |
| `ReportIdDT` | Intern | Technische Zuordnung eines Reports |
| `BenutzernameDT` | Personenbeziehbar | Kann einer Person zugeordnet werden |
| `AnzeigenameDT` | Personenbeziehbar | Wird in der Historie angezeigt |
| `PasswortDT` | Geheim | Authentifizierungsmerkmal |
| `PasswortNachweisDT` | Vertraulich | Sicherheitsrelevanter gespeicherter Wert |
| `ReportTextDT` | Intern/vertraulich | Kann betriebliche Informationen enthalten |
| `ZeitpunktDT` | Intern | Bestandteil der Report-Historie |

## 17. Offene Entscheidungen

Die zu diesem Dokument gehörenden offenen Punkte werden zentral in
[`TEAM-ENTSCHEIDUNGEN.md`](../TEAM-ENTSCHEIDUNGEN.md) verwaltet.

Besonders relevant sind:

- `TD-001` – Pflichtfelder eines Reports
- `TD-002` – Bedeutung der Priorität
- `TD-006` – Berechtigungen der Rollen
- `TD-007` – Aufbewahrungsdauer
- `TD-008` – Anlage der Benutzerkonten

Zusätzlich muss das Team die vorläufige Maximallänge von 4.000 Zeichen und die
Passwort-Mindestlänge bestätigen.

## 18. Nachverfolgbarkeit

Die Datentypen konkretisieren:

- [D1 – Datenmodell](D1-datenmodell.md)
- [F2 – Anwendungsfälle](F2-anwendungsfaelle.md)
- [F3 – Anwendungsfunktionen](F3-anwendungsfunktionen.md)

Die Kennungen `DT-01` bis `DT-12` bleiben bei späteren Überarbeitungen stabil.