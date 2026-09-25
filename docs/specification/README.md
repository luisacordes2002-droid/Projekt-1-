# Reportify – Spezifikation

> **Status:** Mit Implementierung und Tests abgeglichener Stand vom 25.09.2026.  
> Eine formale Freigabe durch das gesamte Projektteam steht noch aus.

Reportify unterstützt digitale Schichtübergaben. Mitarbeitende dokumentieren
erledigte und offene Aufgaben, Probleme beziehungsweise Incidents, Prioritäten
und wichtige Hinweise. Die nachfolgende Schicht kann die aktuelle Übergabe und
ältere Reports einsehen.

## 1. Leseanleitung – E1

Die Leseanleitung wird als Baustein E1 direkt in diesem README geführt, damit
Einstieg, Navigation und Bearbeitungsstand an einer Stelle stehen.

Für den Einstieg empfiehlt sich folgende Reihenfolge:

1. **P1 und P2:** Ziele, Umfang, Systemgrenze und geplanter fachlicher Aufbau.
2. **S1 bis S3:** Abgrenzung zu Nachbarsystemen und Datenmigration sowie Voraussetzungen für die Inbetriebnahme.
3. **F1 und F2:** Geschäftsprozess und Abläufe aus Sicht der nutzenden Personen.
4. **F3, D1 und D2:** Systemfunktionen, benötigte Daten und fachliche Datenregeln.
5. **B1, N1 und N2:** Dialoge, Rückmeldungen, Qualitätsanforderungen und übergreifende fachliche Regeln.
6. **E2:** Fachbegriffe bei Bedarf nachschlagen.

Die Kapitel beschreiben Anforderungen und den umgesetzten Umfang der Version 1.
Der technische Stand wird zusätzlich durch Quellcode, automatisierte Tests und den
[Test- und Abnahmenachweis](../ABNAHME.md) belegt. P2 fasst die Architektur nur
fachlich zusammen; die technische Beschreibung steht unter `docs/arch`.

## 2. Vorhandene Kapitel

Die folgenden Kapitel wurden mit dem Stand der Version 1 abgeglichen. Noch nicht
formal freigegebene Punkte sind ausdrücklich gekennzeichnet.

| Baustein | Dokument | Inhalt |
|---|---|---|
| P1 | [Ziele und Rahmenbedingungen](P1-ziele-rahmenbedingungen.md) | Problem, Zielgruppen, Ziele und Umfang |
| P2 | [Architekturüberblick](P2-architekturueberblick.md) | Systemgrenze und geplante fachliche Verantwortungsbereiche |
| F1 | [Geschäftsprozesse](F1-geschaeftsprozesse.md) | Schichtübergabe und Geschäftsregeln |
| F2 | [Anwendungsfälle](F2-anwendungsfaelle.md) | Nutzerabläufe, Alternativen und Akzeptanzkriterien |
| F3 | [Anwendungsfunktionen](F3-anwendungsfunktionen.md) | Fachliche Leistungen des Systems |
| D1 | [Datenmodell](D1-datenmodell.md) | Nutzer:in, Report, Beziehungen und Datenregeln |
| D2 | [Datentypenverzeichnis](D2-datentypen.md) | Zulässige Werte, Formate und Validierungsregeln |
| B1 | [Dialogspezifikation](B1-dialogspezifikationen.md) | Seiten, Eingaben, Navigation und Rückmeldungen |
| B2 | [Batch](B2-batch.md) | Nicht anwendbar im MVP; Begründung und Abgrenzung |
| B3 | [Druckausgaben](B3-druckausgaben.md) | Nicht anwendbar im MVP; Abgrenzung zu Browserdruck und Export |
| N1 | [Nichtfunktionale Anforderungen](N1-nichtfunktional.md) | Qualitätsziele und überprüfbare Akzeptanzkriterien |
| N2 | [Querschnittskonzepte](N2%20%E2%80%93%20querschnittskonzepte.md) | Übergreifende Regeln zu Zugriff, Berechtigungen, Validierung und Fehlerbehandlung |
| E2 | [Glossar](E2-glossar.md) | Gemeinsame Fachbegriffe und verwendete Kennungen |
| S1 | [Nachbarsysteme](S1-nachbarsysteme.md) | Abgrenzung gegenüber externen Fachsystemen und Schnittstellen |
| S2 – Datenmigration | [Datenmigration](S2%20%E2%80%93%20datenmigration.md) | Keine Altdatenmigration; Abgrenzung zur erstmaligen Bereitstellung vorbereiteter Benutzerkonten |
| S3 | [Inbetriebnahme](S3%20%E2%80%93%20inbetriebnahme.md) | Fachliche Voraussetzungen und Ausgangszustand für den ersten Einsatz |




Die Leseanleitung E1 steht in Abschnitt 1 dieses README. Die zentrale
[Übersicht offener Teamentscheidungen](../TEAM-ENTSCHEIDUNGEN.md) ergänzt die Kapitel.

## 3. Umfang der ersten Version

Der MVP umfasst:

1. Anmelden und Abmelden – `UC-01`, `UC-02`.
2. Schicht auswählen – `UC-03`.
3. Report erstellen – `UC-04`.
4. Aktuelle Übergabe anzeigen – `UC-05`.
5. Report-Historie anzeigen – `UC-06`.
6. Report-Details anzeigen – Bestandteil von `UC-06`, konkretisiert durch
   `AF-08` und `DLG-06`.
7. Gespeicherte Reports bearbeiten.
8. Gespeicherte Reports durch die Schichtleitung löschen.
9. Bei der ersten Anmeldung ein persönliches Passwort festlegen – `UC-09`.
10. Reports als erledigt kennzeichnen – `UC-10`.
11. Die Historie nach Reporttext durchsuchen und nach Schicht filtern.

Als optionale Erweiterung außerhalb des ursprünglichen Minimalumfangs ist
außerdem das Drucken beziehungsweise Speichern eines geöffneten Reports als PDF
über den nativen Browserdialog umgesetzt (`UC-11`, `AF-13`, B3).

Das Dashboard ergänzt den Überblick durch einfache Anzahlen nach Status, Schicht
und Priorität (`AF-14`). Diese Kennzahlen stellen keine umfangreiche statistische
Analyse dar.

Die unterschiedliche Gruppierung der Funktionen und Anwendungsfälle begründet
keinen zusätzlichen Anwendungsfall: Der Detailaufruf ist bereits in `UC-06`
enthalten.

Der zuletzt gespeicherte Report wird als aktuelle Übergabe angezeigt.
Gespeicherte Reports werden in der ersten Version nicht automatisch gelöscht.

Für die Löschung gilt eine rollenabhängige Berechtigung: Nur Nutzer:innen mit
der Rolle `SCHICHTLEITUNG` dürfen gespeicherte Reports löschen.
Mitarbeiter:innen besitzen diese Berechtigung nicht.

Nicht zum MVP gehören eine native Mobile-App, Chat, Benachrichtigungen,
Dateianhänge, externe APIs, Statistiken, Selbstregistrierung und eine komplexe
Rollenverwaltung. Benutzerkonten werden für die erste Version vorbereitet
bereitgestellt.

## 4. Bearbeitungsstand und ausstehende Arbeiten

Die für die Spezifikation vorgesehenen Bausteine sind als eigene Kapitel
beziehungsweise als Bestandteil dieses README angelegt.

Die Bausteine S1 bis S3 und N2 ergänzen die bisherige Spezifikation:

- S1 grenzt Reportify gegenüber externen Fachsystemen und Schnittstellen ab.
- S2 dokumentiert, dass keine Altdatenmigration vorgesehen ist, und grenzt
  diese von der erstmaligen Bereitstellung vorbereiteter Benutzerkonten ab.
- S3 beschreibt die fachlichen Voraussetzungen für die erstmalige
  Inbetriebnahme.
- N2 führt übergreifende fachliche Regeln zu Zugriff, Berechtigungen,
  Validierung und Fehlerbehandlung zusammen.

Bausteine, die für Reportify nicht benötigt werden, werden weiterhin
ausdrücklich als nicht anwendbar gekennzeichnet und projektbezogen begründet.
Dies betrifft insbesondere Bausteine, deren vorgesehene Funktion im Umfang
der ersten Version nicht benötigt wird.

Die technische Umsetzung der Querschnittskonzepte wird in der
Architekturdokumentation beschrieben.

Der Abgleich mit Architektur, Quellcode und Tests wurde am 25.09.2026
durchgeführt. Vor der formalen Freigabe bleiben eine abschließende Teamprüfung
und die Prüfung der Darstellung in einem zweiten Browser offen.

## 5. Entscheidungen und Nachverfolgbarkeit

Offene fachliche, technische und organisatorische Fragen werden zentral in
[TEAM-ENTSCHEIDUNGEN.md](../TEAM-ENTSCHEIDUNGEN.md) geführt. Eine Arbeitsannahme
ist keine bestätigte Teamentscheidung. Maßgeblich ist der dort dokumentierte Status.

Die bereits getroffenen Teamentscheidungen werden in den jeweils betroffenen
Spezifikationskapiteln eingearbeitet. Dazu gehören insbesondere die Regeln zur
Bearbeitung und Löschung gespeicherter Reports, zur Bestimmung der aktuellen
Übergabe, zu den Rollen und Berechtigungen, zur Aufbewahrung von Reports sowie
zur Bereitstellung vorbereiteter Benutzerkonten.

Noch offene Entscheidungen werden weiterhin zentral in
[TEAM-ENTSCHEIDUNGEN.md](../TEAM-ENTSCHEIDUNGEN.md) geführt und nach ihrer
Entscheidung in allen betroffenen Dokumenten nachvollziehbar eingearbeitet.

Die Kennungen ermöglichen Verweise zwischen den Kapiteln. Beispielsweise wird
die Reporterstellung aus `UC-04` durch `AF-03` bis `AF-05` konkretisiert.
Die betroffenen Daten stehen in D1 und D2, der Erfassungsdialog ist `DLG-03`.
`NFR-12d-01` und `NFR-12d-02` ergänzen Anforderungen an die Speicherung.
Diese Bezüge werden in Architektur, Implementierung und Tests fortgeführt.

Vorhandene Kennungen bleiben bei Überarbeitungen erhalten. Die Bedeutung der
Kennungsgruppen wird im [Glossar](E2-glossar.md) erklärt.

## 6. Eingesetzte KI-Werkzeuge

ChatGPT/Codex unterstützt die Erstellung und Überarbeitung von
Dokumentationsentwürfen, den Abgleich von Begriffen, Kennungen und Verweisen
sowie die Formulierung von Git-Befehlen. Dazu gehören unter anderem die Entwürfe
für Glossar, Architekturüberblick und dieses README.

Die Entwürfe werden mit dem vereinbarten MVP und den vorliegenden
Spezifikationskapiteln abgeglichen. Bei der Übernahme werden Dateilänge, Dateiende
und Git-Diff kontrolliert; erkannte Fehler werden gesondert korrigiert.
Die fachliche Freigabe durch das gesamte Team steht noch aus. Der Abgleich gegen
Architektur, Quellcode und Tests wurde am 25.09.2026 durchgeführt.

Weitere tatsächlich eingesetzte KI-Werkzeuge und Nutzungszwecke werden ergänzt.
KI-Vorschläge ersetzen weder eine Teamentscheidung noch das eigene Verständnis
der dokumentierten Anforderungen.

## 7. Quellen und Orientierung

- [Kurs WK_1106 – Anforderungen und empfohlene Dokumentstruktur](https://github.com/carstenlucke/thm_wkb_wk-1106)
- [Herold – Beispielprojekt des Professors](https://github.com/carstenlucke/herold)
- [arc42 – Übersicht für die spätere Architekturdokumentation](https://arc42.org/overview/)

Die Kursvorgaben bestimmen die Anforderungen an die Abgabe. Herold dient als
Orientierung; projektspezifische Inhalte werden nicht auf Reportify übertragen.
