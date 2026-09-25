# Teamentscheidungen – Reportify

> **Stand:** 25.09.2026  
> Diese Datei ist die zentrale Übersicht über fachliche, technische und
> organisatorische Entscheidungen und deren Einarbeitungsstatus.

## 1. Verwendung

Vor jedem Teamtreffen werden alle Einträge mit dem Status `OFFEN` besprochen.

Nach einer Entscheidung wird:

1. das Ergebnis in der Entscheidungshistorie eingetragen,
2. die Entscheidung in den betroffenen Dokumenten eingearbeitet,
3. der Status auf `EINGEARBEITET` gesetzt.

Diese Datei dient als Übersicht. Die jeweils betroffene Spezifikations- oder
Architekturdatei enthält anschließend die verbindliche Beschreibung.

## 2. Status

| Status | Bedeutung |
|---|---|
| `OFFEN` | Das Team muss den Punkt noch besprechen. |
| `ENTSCHIEDEN` | Das Team hat entschieden, die Dokumente wurden aber noch nicht angepasst. |
| `EINGEARBEITET` | Die Entscheidung wurde in allen betroffenen Dokumenten übernommen. |

## 3. Entscheidungsübersicht

| ID | Thema | Zu entscheidende Frage | Aktuelle Arbeitsannahme | Betroffene Dokumente | Status |
|---|---|---|---|---|---|
| TD-001 | Report-Pflichtfelder | Welche Report-Felder müssen ausgefüllt werden? | Schicht ist und erledigte Aufgaben sind Pflicht | F2, F3, D1, B1 | `EINGEARBEITET` |
| TD-002 | Priorität | Gilt die Priorität für den gesamten Report oder nur für Probleme und Incidents? | Priorität ist nur bei Problemen oder Incidents verpflichtend | F3, D1, B1 | `EINGEARBEITET` |
| TD-003 | Bearbeitung | Dürfen bereits gespeicherte Reports bearbeitet werden? | Bereits gespeicherte Reports dürfen bearbeitet werden | P1, F1, F2, F3, D1, B1 | `EINGEARBEITET` |
| TD-004 | Löschung | Dürfen Reports gelöscht werden? | Reports dürfen nur von der Schichtleitung gelöscht werden | P1, F1, F2, F3, D1 | `EINGEARBEITET` |
| TD-005 | Aktuelle Übergabe | Wie wird der aktuelle Übergabereport bestimmt? | Der zuletzt gespeicherte Report wird angezeigt | F1, F3, D1 | `EINGEARBEITET` |
| TD-006 | Berechtigungen | Erhält die Schichtleitung zusätzliche Berechtigungen? | Die Schichtleitung ist dazu berechtigt, Reports zu löschen | P1, F2, D1, N1 | `EINGEARBEITET` |
| TD-007 | Aufbewahrung | Wie lange werden Reports gespeichert? | Während der ersten Version keine automatische Löschung | D1, N1, S3 | `EINGEARBEITET` |
| TD-008 | Benutzerkonten | Wie werden Benutzerkonten angelegt und verwaltet? | Vorbereitete Benutzerkonten; keine Selbstregistrierung | F2, D1, B1, Architektur | `EINGEARBEITET` |
| TD-009 | Datenbank | Welche Datenbank wird für die finale Version eingesetzt? | Für Version 1 wird H2 als relationale Datenbank verwendet. Die Datenbankentscheidung ist in ADR-003 der Architekturdokumentation festgelegt. | TEAMINFO, Architektur, Inbetriebnahme | `EINGEARBEITET` |
| TD-010 | Projektrollen | Wer übernimmt dauerhaft welche Projektrolle? | Bestehende TEAMINFO gilt bis zur gemeinsamen Abstimmung | TEAMINFO | `ENTSCHIEDEN` |
| TD-011 | Sprache | Welche Sprache verwendet die Benutzeroberfläche? | Die Benutzeroberfläche von Version 1 ist vollständig deutsch; Mehrsprachigkeit ist als spätere Erweiterung möglich | B1, N1, N2 | `EINGEARBEITET` |
| TD-012 | Git-Arbeitsweise | Wie gelangen Feature- und Dokumentationsbranches nach `main`? | Branch, Prüfung, Pull Request und anschließend Merge | README, Teamarbeitsweise | `EINGEARBEITET` |
| TD-013 | Textlänge | Welche maximale Länge darf ein Report-Textfeld besitzen? | Höchstens 4.000 Zeichen je Textfeld | D2, B1, N1 | `EINGEARBEITET` |
| TD-014 | Passwortregel | Welche Mindest- und Höchstlänge gilt für Passwörter? | Benutzerkonten werden vorab bereitgestellt. Bei der ersten Anmeldung legt die nutzende Person ihr eigenes Passwort fest. Das Passwort muss mindestens 8 und darf höchstens 128 Zeichen lang sein. | F2, D2, B1, N1, Architektur | `EINGEARBEITET` |
| TD-015 | Leistungs- und Mengenziele | Welche Antwortzeit-, Mengen- und Testbedingungen sollen für die erste Version verbindlich gelten? | Mindestens 95 Prozent von 20 Aufrufen innerhalb von zwei Sekunden bei 1.000 Testreports; Mengenziele von mindestens 50 Benutzerkonten und 10.000 Reports | N1, Architektur, Tests | `EINGEARBEITET` |
| TD-016 | Browser und Bildschirmbreite | Welche Browser und Versionen werden unterstützt und welche kleinste Bildschirmbreite muss bedienbar sein? | Prüfung mit aktuellen Versionen von Chrome und Safari; Bedienbarkeit ab 360 Pixeln Breite | B1, N1, Architektur, Tests | `EINGEARBEITET` |
| TD-017 | Schichtauswahl | Die Schicht wird direkt im Reportformular ausgewählt | Die Schicht wird direkt im Reportformular ausgewählt | F1, F2, F3, B1, Architektur | `EINGEARBEITET` |
| TD-018 | Änderungsnachverfolgung | Soll bei der Bearbeitung eines Reports gespeichert werden, wann und von wem er zuletzt geändert wurde? | Änderungszeitpunkt und ändernde Person werden gespeichert | D1, F2, F3, B1, Architektur | `EINGEARBEITET`|
| TD-019 | Report-Status | Sollen gespeicherte Reports als erledigt gekennzeichnet werden können? | Angemeldete Nutzer:innen dürfen Reports als erledigt kennzeichnen; der Status wird gespeichert und angezeigt. | P1, F1, F2, F3, D1, D2, B1, N1, Architektur | `EINGEARBEITET` |



## 4. Noch zu klären

Die fachlichen und technischen Entscheidungen `TD-001` bis `TD-019` sind in die
betroffenen Dokumente eingearbeitet. Nur `TD-010` benötigt noch eine formale
Bestätigung der dauerhaften Projektrollen in `TEAMINFO.md`.

Die zu `TD-015` und `TD-016` vereinbarten Qualitätsziele sind dokumentiert, aber
noch nicht vollständig durch Last- und browserübergreifende Tests nachgewiesen.
Diese fehlenden Nachweise werden im Test- und Abnahmenachweis transparent geführt.

## 5. Entscheidungshistorie

| ID | Entscheidung | Kurze Begründung | Datum | Beteiligte |
|---|---|---|---|---|
| TD-018 | Bei der Bearbeitung werden Änderungszeitpunkt und ändernde Person gespeichert. | Änderungen bleiben nachvollziehbar. | 24.09.2026 | Souhaib Boujemaoui |
| TD-019 | Gespeicherte Reports können als erledigt gekennzeichnet werden. | Der Bearbeitungsstand bleibt für nachfolgende Schichten sichtbar. | 24.09.2026 | Souhaib Boujemaoui |

## 6. Neue Entscheidung aufnehmen

Neue offene Fragen erhalten die nächste freie Kennung `TD-020`, `TD-021` usw.

Jeder neue Eintrag benötigt:

- eine konkrete Entscheidungsfrage,
- eine vorläufige Arbeitsannahme,
- die betroffenen Dokumente,
- den Status `OFFEN`.

Architekturentscheidungen mit technischen Alternativen werden zusätzlich als
Architecture Decision Record mit einer Kennung wie `ADR-001` dokumentiert.
