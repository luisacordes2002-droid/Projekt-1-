# Reportify – Spezifikation

> **Status:** Arbeitsentwurf vom 30.08.2026.  
> Die vorhandenen Kapitel beschreiben den geplanten MVP. Die Spezifikation ist
> noch nicht vollständig und noch nicht vom gesamten Projektteam freigegeben.

Reportify unterstützt digitale Schichtübergaben. Mitarbeitende dokumentieren
erledigte und offene Aufgaben, Probleme beziehungsweise Incidents, Prioritäten
und wichtige Hinweise. Die nachfolgende Schicht kann die aktuelle Übergabe und
ältere Reports einsehen.

## 1. Leseanleitung – E1

Die Leseanleitung wird als Baustein E1 direkt in diesem README geführt, damit
Einstieg, Navigation und Bearbeitungsstand an einer Stelle stehen.

Für den Einstieg empfiehlt sich folgende Reihenfolge:

1. **P1 und P2:** Ziele, Umfang, Systemgrenze und geplanter fachlicher Aufbau.
2. **F1 und F2:** Geschäftsprozess und Abläufe aus Sicht der nutzenden Personen.
3. **F3, D1 und D2:** Systemfunktionen, benötigte Daten und fachliche Datenregeln.
4. **B1 und N1:** Dialoge, Rückmeldungen und überprüfbare Qualitätsanforderungen.
5. **E2:** Fachbegriffe bei Bedarf nachschlagen.

Die Kapitel beschreiben Anforderungen und Planungen. Eine vorhandene Beschreibung
ist kein Nachweis einer bereits implementierten oder erfolgreich getesteten Funktion.
P2 ersetzt nicht die spätere detaillierte Architekturdokumentation.

## 2. Vorhandene Kapitel

Alle folgenden Kapitel liegen als Arbeitsentwürfe vor. Der jeweilige Inhalt ist
zusammen mit den offenen Teamentscheidungen zu lesen.

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
| N1 | [Nichtfunktionale Anforderungen](N1-nichtfunktional.md) | Qualitätsziele und überprüfbare Akzeptanzkriterien |
| E2 | [Glossar](E2-glossar.md) | Gemeinsame Fachbegriffe und verwendete Kennungen |

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

Die unterschiedliche Gruppierung der Funktionen und Anwendungsfälle begründet
keinen zusätzlichen Anwendungsfall: Der Detailaufruf ist bereits in UC-06 enthalten.

Nicht zum MVP gehören eine native Mobile-App, Chat, Benachrichtigungen,
Dateianhänge, externe APIs, Statistiken und eine komplexe Rollenverwaltung.
Gespeicherte Reports werden im aktuellen Umfang weder bearbeitet noch gelöscht.
Eine spätere Einführung von Bearbeitung oder Löschung muss über `TD-003` beziehungsweise
`TD-004` abgestimmt und in den betroffenen Dokumenten nachvollzogen werden.

## 4. Noch auszuarbeitende Bausteine

Die folgenden Bausteine der im Kurs empfohlenen Struktur sind noch nicht als
eigene Kapitel ausgearbeitet. Diese Übersicht ist keine Vollständigkeitsfreigabe.

| Baustein | Noch zu dokumentieren |
|---|---|
| B2 – Batch | Einordnen und begründen, dass im bisherigen MVP keine fachlichen Stapelverarbeitungsabläufe vorgesehen sind. |
| B3 – Druckausgaben | Festhalten, dass im bisherigen MVP keine anwendungseigenen Druck- oder Exportfunktionen vorgesehen sind. |
| S1 – Nachbarsysteme | Die Abgrenzung gegenüber externen Fachsystemen und Schnittstellen dokumentieren. |
| S2 – Datenmigration | Eine Übernahme von Altdaten abgrenzen und von der erstmaligen Bereitstellung von Benutzerkonten unterscheiden. |
| S3 – Inbetriebnahme | Fachliche Bedingungen für Einrichtung und ersten Einsatz beschreiben und mit der späteren ausführbaren Installationsanleitung verbinden. |
| N2 – Querschnittskonzepte | Übergreifende fachliche Regeln zu Zugriff, Validierung und Fehlerbehandlung zusammenführen und auf bestehende Definitionen verweisen. |

Nicht benötigte Bausteine werden ausdrücklich als nicht anwendbar gekennzeichnet
und projektbezogen begründet. Die technische Umsetzung der Querschnittskonzepte
wird in der Architektur beschrieben.

Zusätzlich stehen die Teamprüfung, die Abstimmung offener Regeln, die in B1
vorgesehenen Darstellungen und der Abgleich mit Architektur, Code und Tests aus.

## 5. Entscheidungen und Nachverfolgbarkeit

Offene fachliche, technische und organisatorische Fragen werden zentral in
[TEAM-ENTSCHEIDUNGEN.md](../TEAM-ENTSCHEIDUNGEN.md) geführt. Eine Arbeitsannahme
ist keine bestätigte Teamentscheidung. Maßgeblich ist der dort dokumentierte Status.

Insbesondere muss `TD-017` die unterschiedliche Beschreibung der Schichtauswahl
in F2 und B1 klären. Die vorläufigen Leistungs- und Mengenziele sowie Browser und
Bildschirmbreite werden über `TD-015` und `TD-016` abgestimmt.

Die Kennungen ermöglichen Verweise zwischen den Kapiteln. Beispielsweise wird
die Reporterstellung aus `UC-04` durch `AF-03` bis `AF-05` konkretisiert.
Die betroffenen Daten stehen in D1 und D2, der Erfassungsdialog ist `DLG-03`.
`NFR-12d-01` und `NFR-12d-02` ergänzen Anforderungen an die Speicherung.
Diese Bezüge müssen später in Architektur, Implementierung und Tests fortgeführt werden.

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
Die fachliche Freigabe durch das gesamte Team sowie die Prüfung gegen Architektur,
Quellcode und Tests stehen noch aus.

Weitere tatsächlich eingesetzte KI-Werkzeuge und Nutzungszwecke werden ergänzt.
KI-Vorschläge ersetzen weder eine Teamentscheidung noch das eigene Verständnis
der dokumentierten Anforderungen.

## 7. Quellen und Orientierung

- [Kurs WK_1106 – Anforderungen und empfohlene Dokumentstruktur](https://github.com/carstenlucke/thm_wkb_wk-1106)
- [Herold – Beispielprojekt des Professors](https://github.com/carstenlucke/herold)
- [arc42 – Übersicht für die spätere Architekturdokumentation](https://arc42.org/overview/)

Die Kursvorgaben bestimmen die Anforderungen an die Abgabe. Herold dient als
Orientierung; projektspezifische Inhalte werden nicht auf Reportify übertragen.
