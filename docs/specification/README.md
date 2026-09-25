# Reportify â€“ Spezifikation

> **Status:** Aktualisierter Stand vom 25.09.2026.
> Die vorhandenen Kapitel beschreiben den geplanten MVP. Die Spezifikation ist
> noch nicht vollstÃ¤ndig und noch nicht vom gesamten Projektteam freigegeben.

Reportify unterstÃ¼tzt digitale SchichtÃ¼bergaben. Mitarbeitende dokumentieren
erledigte und offene Aufgaben, Probleme beziehungsweise Incidents, PrioritÃ¤ten
und wichtige Hinweise. Die nachfolgende Schicht kann die aktuelle Ãœbergabe und
Ã¤ltere Reports einsehen.

## 1. Leseanleitung â€“ E1

Die Leseanleitung wird als Baustein E1 direkt in diesem README gefÃ¼hrt, damit
Einstieg, Navigation und Bearbeitungsstand an einer Stelle stehen.

FÃ¼r den Einstieg empfiehlt sich folgende Reihenfolge:

1. **P1 und P2:** Ziele, Umfang, Systemgrenze und geplanter fachlicher Aufbau.
2. **S1 bis S3:** Abgrenzung zu Nachbarsystemen und Datenmigration sowie Voraussetzungen fÃ¼r die Inbetriebnahme.
3. **F1 und F2:** GeschÃ¤ftsprozess und AblÃ¤ufe aus Sicht der nutzenden Personen.
4. **F3, D1 und D2:** Systemfunktionen, benÃ¶tigte Daten und fachliche Datenregeln.
5. **B1, N1 und N2:** Dialoge, RÃ¼ckmeldungen, QualitÃ¤tsanforderungen und Ã¼bergreifende fachliche Regeln.
6. **E2:** Fachbegriffe bei Bedarf nachschlagen.

Die Kapitel beschreiben Anforderungen und Planungen. Eine vorhandene Beschreibung
ist kein Nachweis einer bereits implementierten oder erfolgreich getesteten Funktion.
P2 ersetzt nicht die spÃ¤tere detaillierte Architekturdokumentation.

## 2. Vorhandene Kapitel

Alle folgenden Kapitel liegen als ArbeitsentwÃ¼rfe vor. Der jeweilige Inhalt ist
zusammen mit den offenen Teamentscheidungen zu lesen.

| Baustein | Dokument | Inhalt |
|---|---|---|
| P1 | [Ziele und Rahmenbedingungen](P1-ziele-rahmenbedingungen.md) | Problem, Zielgruppen, Ziele und Umfang |
| P2 | [ArchitekturÃ¼berblick](P2-architekturueberblick.md) | Systemgrenze und geplante fachliche Verantwortungsbereiche |
| F1 | [GeschÃ¤ftsprozesse](F1-geschaeftsprozesse.md) | SchichtÃ¼bergabe und GeschÃ¤ftsregeln |
| F2 | [AnwendungsfÃ¤lle](F2-anwendungsfaelle.md) | NutzerablÃ¤ufe, Alternativen und Akzeptanzkriterien |
| F3 | [Anwendungsfunktionen](F3-anwendungsfunktionen.md) | Fachliche Leistungen des Systems |
| D1 | [Datenmodell](D1-datenmodell.md) | Nutzer:in, Report, Beziehungen und Datenregeln |
| D2 | [Datentypenverzeichnis](D2-datentypen.md) | ZulÃ¤ssige Werte, Formate und Validierungsregeln |
| B1 | [Dialogspezifikation](B1-dialogspezifikationen.md) | Seiten, Eingaben, Navigation und RÃ¼ckmeldungen |
| B2 | [Batch](B2-batch.md) | Nicht anwendbar im MVP; BegrÃ¼ndung und Abgrenzung |
| B3 | [Druckausgaben](B3-druckausgaben.md) | Nicht anwendbar im MVP; Abgrenzung zu Browserdruck und Export |
| N1 | [Nichtfunktionale Anforderungen](N1-nichtfunktional.md) | QualitÃ¤tsziele und Ã¼berprÃ¼fbare Akzeptanzkriterien |
| N2 | [Querschnittskonzepte](N2-querschnittskonzepte.md) | Ãœbergreifende Regeln zu Zugriff, Berechtigungen, Validierung und Fehlerbehandlung |
| E2 | [Glossar](E2-glossar.md) | Gemeinsame Fachbegriffe und verwendete Kennungen |
| S1 | [Nachbarsysteme](S1-nachbarsysteme.md) | Abgrenzung gegenÃ¼ber externen Fachsystemen und Schnittstellen |
| S2 â€“ Datenmigration | [Datenmigration](S2-datenmigration.md) | Keine Altdatenmigration; Abgrenzung zur erstmaligen Bereitstellung vorbereiteter Benutzerkonten |
| S3 | [Inbetriebnahme](S3-inbetriebnahme.md) | Fachliche Voraussetzungen und Ausgangszustand fÃ¼r den ersten Einsatz |




Die Leseanleitung E1 steht in Abschnitt 1 dieses README. Die zentrale
[Ãœbersicht offener Teamentscheidungen](../TEAM-ENTSCHEIDUNGEN.md) ergÃ¤nzt die Kapitel.

## 3. Umfang der ersten Version

Der MVP umfasst:

1. Anmelden und Abmelden â€“ `UC-01`, `UC-02`.
2. Schicht auswÃ¤hlen â€“ `UC-03`.
3. Report erstellen â€“ `UC-04`.
4. Aktuelle Ãœbergabe anzeigen â€“ `UC-05`.
5. Report-Historie anzeigen â€“ `UC-06`.
6. Report-Details anzeigen â€“ Bestandteil von `UC-06`, konkretisiert durch
   `AF-08` und `DLG-06`.
7. Gespeicherte Reports bearbeiten.
8. Gespeicherte Reports durch die Schichtleitung lÃ¶schen.

Die unterschiedliche Gruppierung der Funktionen und AnwendungsfÃ¤lle begrÃ¼ndet
keinen zusÃ¤tzlichen Anwendungsfall: Der Detailaufruf ist bereits in UC-06 enthalten.

Die unterschiedliche Gruppierung der Funktionen und AnwendungsfÃ¤lle begrÃ¼ndet
keinen zusÃ¤tzlichen Anwendungsfall: Der Detailaufruf ist bereits in `UC-06`
enthalten.

Der zuletzt gespeicherte Report wird als aktuelle Ãœbergabe angezeigt.
Gespeicherte Reports werden in der ersten Version nicht automatisch gelÃ¶scht.

FÃ¼r die LÃ¶schung gilt eine rollenabhÃ¤ngige Berechtigung: Nur Nutzer:innen mit
der Rolle `SCHICHTLEITUNG` dÃ¼rfen gespeicherte Reports lÃ¶schen.
Mitarbeiter:innen besitzen diese Berechtigung nicht.

Nicht zum MVP gehÃ¶ren eine native Mobile-App, Chat, Benachrichtigungen,
DateianhÃ¤nge, externe APIs, Statistiken, Selbstregistrierung und eine komplexe
Rollenverwaltung. Benutzerkonten werden fÃ¼r die erste Version vorbereitet
bereitgestellt.

## 4. Bearbeitungsstand und ausstehende Arbeiten

Die fÃ¼r die Spezifikation vorgesehenen Bausteine sind als eigene Kapitel
beziehungsweise als Bestandteil dieses README angelegt.

Die Bausteine S1 bis S3 und N2 ergÃ¤nzen die bisherige Spezifikation:

- S1 grenzt Reportify gegenÃ¼ber externen Fachsystemen und Schnittstellen ab.
- S2 dokumentiert, dass keine Altdatenmigration vorgesehen ist, und grenzt
  diese von der erstmaligen Bereitstellung vorbereiteter Benutzerkonten ab.
- S3 beschreibt die fachlichen Voraussetzungen fÃ¼r die erstmalige
  Inbetriebnahme.
- N2 fÃ¼hrt Ã¼bergreifende fachliche Regeln zu Zugriff, Berechtigungen,
  Validierung und Fehlerbehandlung zusammen.

Bausteine, die fÃ¼r Reportify nicht benÃ¶tigt werden, werden weiterhin
ausdrÃ¼cklich als nicht anwendbar gekennzeichnet und projektbezogen begrÃ¼ndet.
Dies betrifft insbesondere Bausteine, deren vorgesehene Funktion im Umfang
der ersten Version nicht benÃ¶tigt wird.

Die technische Umsetzung der Querschnittskonzepte wird in der
Architekturdokumentation beschrieben.

Vor der fachlichen Freigabe stehen weiterhin die TeamprÃ¼fung, die Abstimmung
noch offener Entscheidungen, die in B1 vorgesehenen Darstellungen sowie der
Abgleich der Spezifikation mit Architektur, Quellcode und Tests aus.

## 5. Entscheidungen und Nachverfolgbarkeit

Offene fachliche, technische und organisatorische Fragen werden zentral in
[TEAM-ENTSCHEIDUNGEN.md](../TEAM-ENTSCHEIDUNGEN.md) gefÃ¼hrt. Eine Arbeitsannahme
ist keine bestÃ¤tigte Teamentscheidung. MaÃŸgeblich ist der dort dokumentierte Status.

Die bereits getroffenen Teamentscheidungen werden in den jeweils betroffenen
Spezifikationskapiteln eingearbeitet. Dazu gehÃ¶ren insbesondere die Regeln zur
Bearbeitung und LÃ¶schung gespeicherter Reports, zur Bestimmung der aktuellen
Ãœbergabe, zu den Rollen und Berechtigungen, zur Aufbewahrung von Reports sowie
zur Bereitstellung vorbereiteter Benutzerkonten.

Teamentscheidungen werden zentral in
[TEAM-ENTSCHEIDUNGEN.md](../TEAM-ENTSCHEIDUNGEN.md) dokumentiert und in den
betroffenen Spezifikationsdokumenten nachvollziehbar berÃ¼cksichtigt.


Die Kennungen ermÃ¶glichen Verweise zwischen den Kapiteln. Beispielsweise wird
die Reporterstellung aus `UC-04` durch `AF-03` bis `AF-05` konkretisiert.
Die betroffenen Daten stehen in D1 und D2, der Erfassungsdialog ist `DLG-03`.
`NFR-12d-01` und `NFR-12d-02` ergÃ¤nzen Anforderungen an die Speicherung.
Diese BezÃ¼ge mÃ¼ssen spÃ¤ter in Architektur, Implementierung und Tests fortgefÃ¼hrt werden.

Vorhandene Kennungen bleiben bei Ãœberarbeitungen erhalten. Die Bedeutung der
Kennungsgruppen wird im [Glossar](E2-glossar.md) erklÃ¤rt.

## 6. Eingesetzte KI-Werkzeuge

ChatGPT/Codex unterstÃ¼tzt die Erstellung und Ãœberarbeitung von
DokumentationsentwÃ¼rfen, den Abgleich von Begriffen, Kennungen und Verweisen
sowie die Formulierung von Git-Befehlen. Dazu gehÃ¶ren unter anderem die EntwÃ¼rfe
fÃ¼r Glossar, ArchitekturÃ¼berblick und dieses README.

Die EntwÃ¼rfe werden mit dem vereinbarten MVP und den vorliegenden
Spezifikationskapiteln abgeglichen. Bei der Ãœbernahme werden DateilÃ¤nge, Dateiende
und Git-Diff kontrolliert; erkannte Fehler werden gesondert korrigiert.
Die fachliche Freigabe durch das gesamte Team sowie die PrÃ¼fung gegen Architektur,
Quellcode und Tests stehen noch aus.

Weitere tatsÃ¤chlich eingesetzte KI-Werkzeuge und Nutzungszwecke werden ergÃ¤nzt.
KI-VorschlÃ¤ge ersetzen weder eine Teamentscheidung noch das eigene VerstÃ¤ndnis
der dokumentierten Anforderungen.

## 7. Quellen und Orientierung

- [Kurs WK_1106 â€“ Anforderungen und empfohlene Dokumentstruktur](https://github.com/carstenlucke/thm_wkb_wk-1106)
- [Herold â€“ Beispielprojekt des Professors](https://github.com/carstenlucke/herold)
- [arc42 â€“ Ãœbersicht fÃ¼r die spÃ¤tere Architekturdokumentation](https://arc42.org/overview/)

Die Kursvorgaben bestimmen die Anforderungen an die Abgabe. Herold dient als
Orientierung; projektspezifische Inhalte werden nicht auf Reportify Ã¼bertragen.
