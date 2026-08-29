# Offene Teamentscheidungen – Reportify

> **Stand:** 30.08.2026  
> Diese Datei ist die zentrale Übersicht für fachliche, technische und
> organisatorische Entscheidungen, die das Team noch abstimmen muss.

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
| TD-001 | Report-Pflichtfelder | Welche Report-Felder müssen ausgefüllt werden? | Schicht ist Pflicht; zusätzlich mindestens ein fachliches Textfeld | F2, F3, D1, B1 | `OFFEN` |
| TD-002 | Priorität | Gilt die Priorität für den gesamten Report oder nur für Probleme und Incidents? | Priorität ist nur bei Problemen oder Incidents verpflichtend | F3, D1, B1 | `OFFEN` |
| TD-003 | Bearbeitung | Dürfen bereits gespeicherte Reports bearbeitet werden? | In Version 1 nicht vorgesehen | P1, F1, F2, F3, D1 | `OFFEN` |
| TD-004 | Löschung | Dürfen Reports gelöscht werden? | In Version 1 nicht vorgesehen | P1, F1, F2, F3, D1 | `OFFEN` |
| TD-005 | Aktuelle Übergabe | Wie wird der aktuelle Übergabereport bestimmt? | Der zuletzt gespeicherte Report wird angezeigt | F1, F3, D1 | `OFFEN` |
| TD-006 | Berechtigungen | Erhält die Schichtleitung zusätzliche Berechtigungen? | Beide Rollen besitzen in Version 1 dieselben Kernrechte | P1, F2, D1, N1 | `OFFEN` |
| TD-007 | Aufbewahrung | Wie lange werden Reports gespeichert? | Während der ersten Version keine automatische Löschung | D1, N1, S3 | `OFFEN` |
| TD-008 | Benutzerkonten | Wie werden Benutzerkonten angelegt und verwaltet? | Vorbereitete Benutzerkonten; keine Selbstregistrierung | F2, D1, B1, Architektur | `OFFEN` |
| TD-009 | Datenbank | Welche Datenbank wird für die finale Version eingesetzt? | H2 für Entwicklung; endgültige Entscheidung in der Architektur | TEAMINFO, Architektur, Inbetriebnahme | `OFFEN` |
| TD-010 | Projektrollen | Wer übernimmt dauerhaft welche Projektrolle? | Bestehende TEAMINFO gilt bis zur gemeinsamen Abstimmung | TEAMINFO | `OFFEN` |
| TD-011 | Sprache | Welche Sprache verwendet die Benutzeroberfläche? | Deutsche Benutzeroberfläche | B1, N1 | `OFFEN` |
| TD-012 | Git-Arbeitsweise | Wie gelangen Feature- und Dokumentationsbranches nach `main`? | Branch, Prüfung, Pull Request und anschließend Merge | README, Teamarbeitsweise | `OFFEN` |
| TD-013 | Textlänge | Welche maximale Länge darf ein Report-Textfeld besitzen? | Höchstens 4.000 Zeichen je Textfeld | D2, B1, N1 | `OFFEN` |
| TD-014 | Passwortregel | Welche Mindest- und Höchstlänge gilt für Passwörter? | Mindestens 8 und höchstens 128 Zeichen | F2, D2, B1, N1, Architektur | `OFFEN` |


## 4. Priorität für das nächste Teamtreffen

Folgende Entscheidungen müssen zuerst getroffen werden, weil sie die weitere
Implementierung direkt beeinflussen:

1. `TD-001` – Report-Pflichtfelder
2. `TD-002` – Bedeutung der Priorität
3. `TD-003` – Bearbeitung gespeicherter Reports
4. `TD-005` – Bestimmung der aktuellen Übergabe
5. `TD-006` – Berechtigungen der Rollen
6. `TD-008` – Anlage der Benutzerkonten
7. `TD-009` – endgültige Datenbank
8. `TD-010` – Projektrollen
9. `TD-013` – maximale Länge der Report-Textfelder
10. `TD-014` – Passwortregel

**Zieltermin für diese Entscheidungen:** 01.09.2026

## 5. Entscheidungshistorie

| ID | Entscheidung | Kurze Begründung | Datum | Beteiligte |
|---|---|---|---|---|
| – | Noch keine Teamentscheidung eingetragen | – | – | – |

## 6. Neue Entscheidung aufnehmen

Neue offene Fragen erhalten die nächste freie Kennung `TD-015`, `TD-016` usw.

Jeder neue Eintrag benötigt:

- eine konkrete Entscheidungsfrage,
- eine vorläufige Arbeitsannahme,
- die betroffenen Dokumente,
- den Status `OFFEN`.

Architekturentscheidungen mit technischen Alternativen werden zusätzlich als
Architecture Decision Record mit einer Kennung wie `ADR-001` dokumentiert.