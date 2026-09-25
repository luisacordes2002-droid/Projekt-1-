# D2 – Datentypenverzeichnis

> **Status:** Aktualisierter Stand vom 25.09.2026.

> Dieses Dokument beschreibt die für die erste Reportify-Version festgelegten
> fachlichen Datentypen, Werte und Validierungsregeln.

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
| DT-12 | `ZeitpunktDT` | Erstellungs- und Änderungszeitpunkte eines Reports |
| DT-13 | `ReportStatusDT` | Bearbeitungsstand eines Reports |

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
- `Report.geaendertVon`

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

- Das persönliche Passwort wird von der nutzenden Person selbst festgelegt.
- Bei der ersten Anmeldung muss die Person ein eigenes Passwort festlegen.
- Das Passwort muss mindestens 8 Zeichen lang sein.
- Das Passwort darf höchstens 128 Zeichen lang sein.
- Das Passwort wird bei der Eingabe nicht sichtbar angezeigt.
- Leerzeichen innerhalb eines Passworts sind zulässig.
- Das Passwort darf weder protokolliert noch als Klartext gespeichert werden.
- Bei einer fehlgeschlagenen Anmeldung wird das eingegebene Passwort verworfen.

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
- Maximallänge: 4.000 Zeichen je Feld.
- Zeilenumbrüche sind zulässig.
- HTML- oder Skriptcode darf nicht als ausführbarer Inhalt interpretiert werden.
- Passwörter und andere Zugangsdaten dürfen nicht in Report-Texten erfasst werden.
- Das Feld „Erledigte Aufgaben“ muss Inhalt besitzen; die übrigen fachlichen Report-Felder sind optional.

## 14. DT-12 – ZeitpunktDT

### Bedeutung

Datum und Uhrzeit eines fachlich relevanten Zeitpunkts eines Reports, insbesondere des Erstellungs- oder letzten
Änderungszeitpunkts

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
## 15. DT-13 – ReportStatusDT

### Bedeutung

Kennzeichnet den aktuellen Bearbeitungsstand eines gespeicherten Reports.

### Zulässige Werte

| Schlüssel | Anzeige | Bedeutung |
|---|---|---|
| `OFFEN` | Offen | Der Report wurde noch nicht als erledigt gekennzeichnet |
| `ERLEDIGT` | Erledigt | Der Report wurde als erledigt gekennzeichnet |

### Regeln

- Ein neu gespeicherter Report erhält den Status `OFFEN`.
- Eine angemeldete Person darf einen offenen Report als `ERLEDIGT` kennzeichnen.
- Ein erledigter Report bleibt vollständig gespeichert und in der Historie sichtbar.
- Freie Statuswerte sind nicht zulässig.

## 16. Allgemeine Validierungsregeln

1. Pflichtwerte dürfen nicht fehlen.
2. Nicht erlaubte Auswahlwerte werden zurückgewiesen.
3. Texte werden vor der fachlichen Prüfung von äußeren Leerzeichen bereinigt.
4. Fehlermeldungen benennen das betroffene Feld und die verletzte Regel.
5. Ungültige Eingaben werden nicht dauerhaft gespeichert.
6. Interne Kennungen werden nicht durch frei eingegebene Werte ersetzt.
7. Geheime Werte werden weder angezeigt noch protokolliert.

## 17. Datenschutzklassifikation

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

## 18. Relevante Teamentscheidungen

Die für die Datentypen relevanten fachlichen und technischen Entscheidungen werden zentral in
[`TEAM-ENTSCHEIDUNGEN.md`](../TEAM-ENTSCHEIDUNGEN.md) dokumentiert.

Für D2 sind insbesondere folgende Entscheidungen relevant:

- `TD-001` – Pflichtfelder eines Reports
- `TD-002` – Bedeutung der Priorität
- `TD-006` – Berechtigungen der Rollen
- `TD-007` – Aufbewahrungsdauer
- `TD-008` – Anlage der Benutzerkonten
- `TD-013` – Maximale Länge der Report-Textfelder

Die maximale Länge der Report-Textfelder ist gemäß `TD-013` auf 4.000 Zeichen je Feld festgelegt.

## 19. Nachverfolgbarkeit

Die Datentypen konkretisieren:

- [D1 – Datenmodell](D1-datenmodell.md)
- [F2 – Anwendungsfälle](F2-anwendungsfaelle.md)
- [F3 – Anwendungsfunktionen](F3-anwendungsfunktionen.md)

Die Kennungen `DT-01` bis `DT-13` bleiben bei späteren Überarbeitungen stabil.
