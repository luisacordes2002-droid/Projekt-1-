# S3 – Inbetriebnahme

> **Status:** Arbeitsentwurf.  
> Dieses Kapitel beschreibt die fachlichen Voraussetzungen und den vorgesehenen
> Ausgangszustand für die erstmalige Inbetriebnahme von Reportify.

## 1. Zweck

Dieses Kapitel beschreibt die Bedingungen, die erfüllt sein müssen, damit
Reportify erstmals verwendet werden kann.

Im Mittelpunkt stehen die fachlichen Voraussetzungen für den ersten Einsatz.
Konkrete technische Installationsschritte, Befehle und Konfigurationsdetails
werden nicht in diesem Kapitel festgelegt, sondern in einer ausführbaren
Installationsanleitung beziehungsweise in der Architekturdokumentation
beschrieben.

## 2. Voraussetzungen für den ersten Einsatz

Vor der erstmaligen fachlichen Nutzung muss Reportify erfolgreich eingerichtet
und gestartet worden sein.

Für den ersten Einsatz müssen insbesondere folgende Voraussetzungen erfüllt
sein:

- die Anwendung ist über einen unterstützten Webbrowser erreichbar,
- die für die Nutzung vorgesehenen Benutzerkonten sind vorbereitet,
- jedem Benutzerkonto ist eine gültige Rolle zugeordnet,
- eine Anmeldung mit einem vorbereiteten Benutzerkonto ist möglich,
- die für Reports benötigte Datenhaltung ist verfügbar,
- die vorgesehenen Schichten können bei der Reporterstellung ausgewählt werden.

Eine Selbstregistrierung durch Nutzer:innen ist für die erste Version nicht
vorgesehen.

## 3. Benutzerkonten und Rollen

Die für den ersten Einsatz benötigten Benutzerkonten werden vorbereitet
bereitgestellt.

Reportify unterscheidet die Rollen:

- `MITARBEITER`
- `SCHICHTLEITUNG`

Beide Rollen können die allgemeinen Funktionen zur digitalen Schichtübergabe
verwenden.

Die Rolle `SCHICHTLEITUNG` besitzt zusätzlich die Berechtigung, gespeicherte
Reports zu löschen. Nutzer:innen ohne diese Rolle dürfen keine Reports löschen.

Die erstmalige Bereitstellung der Benutzerkonten ist keine Datenmigration.
Die entsprechende Abgrenzung wird in
[S2 – Datenmigration](S2-datenmigration.md) beschrieben.

## 4. Ausgangszustand der Report-Daten

Für die erstmalige Inbetriebnahme müssen keine bestehenden Reports vorhanden
sein.

Reportify übernimmt keine historischen Reports aus einem Altsystem. Die
Report-Historie darf beim ersten Start daher leer sein.

Solange noch kein Report gespeichert wurde, muss die Anwendung bei Funktionen,
die einen vorhandenen Report voraussetzen, einen verständlichen Zustand
beziehungsweise eine entsprechende Rückmeldung anzeigen.

Nach dem Speichern des ersten gültigen Reports steht dieser für die
vorgesehenen Anzeige- und Historienfunktionen zur Verfügung.

Der zuletzt gespeicherte Report wird als aktuelle Übergabe angezeigt.

## 5. Erster fachlicher Einsatz

Nach erfolgreicher Einrichtung kann der erste fachliche Einsatz grundsätzlich
folgendermaßen erfolgen:

1. Eine Nutzerin oder ein Nutzer öffnet Reportify im Webbrowser.
2. Die Person meldet sich mit einem vorbereiteten Benutzerkonto an.
3. Bei der Reporterstellung wird die zugehörige Schicht ausgewählt.
4. Die erforderlichen Report-Daten werden erfasst.
5. Der Report wird gespeichert.
6. Der gespeicherte Report steht anschließend als aktuelle Übergabe sowie in der Report-Historie zur Verfügung.

Für das Speichern gelten dieselben fachlichen Validierungsregeln wie im späteren regulären Betrieb.

## 6. Prüfung der Inbetriebnahme

Die Inbetriebnahme gilt aus fachlicher Sicht als erfolgreich, wenn mindestens
folgende grundlegende Abläufe möglich sind:

- Reportify kann im vorgesehenen Webbrowser aufgerufen werden,
- die Anmeldung mit einem vorbereiteten Benutzerkonto funktioniert,
- die vorgesehene Rolle des angemeldeten Benutzerkontos wird berücksichtigt,
- ein gültiger Report kann erstellt und gespeichert werden,
- der gespeicherte Report kann anschließend angezeigt werden,
- die Report-Historie kann aufgerufen werden.

Bei einem Benutzerkonto mit der Rolle `SCHICHTLEITUNG` muss zusätzlich die
vorgesehene Löschberechtigung berücksichtigt werden. Nutzer:innen ohne diese
Rolle dürfen keine Reports löschen.

Die vollständige Prüfung der einzelnen Funktionen erfolgt anhand der
Anwendungsfälle, Akzeptanzkriterien und Tests.

## 7. Technische Installationsanleitung

S3 ersetzt keine technische Installationsanleitung.

Die Installationsanleitung muss die konkreten Schritte enthalten, mit denen
eine andere Person Reportify in der vorgesehenen Umgebung selbstständig
einrichten und starten kann.

Dazu gehören abhängig von der endgültigen technischen Umsetzung insbesondere
die benötigten Softwarevoraussetzungen, der Bezug des Projekts, notwendige
Konfigurationen, der Start der Anwendung und der Aufruf im Webbrowser.

Technische Details zur Datenbank, zu Entwicklungswerkzeugen oder zu
Startbefehlen werden in der Installations- beziehungsweise
Architekturdokumentation beschrieben und sollen in S3 nicht doppelt gepflegt
werden. Für Version 1 ist H2 als relationale Datenbank festgelegt.

## 8. Abgrenzung

Nicht Bestandteil dieses Kapitels sind:

- die Migration bestehender Fachdaten; siehe
  [S2 – Datenmigration](S2-datenmigration.md),
- externe Fachsysteme und Schnittstellen; siehe
  [S1 – Nachbarsysteme](S1-nachbarsysteme.md),
- die detaillierte Beschreibung einzelner Benutzerabläufe; siehe
  [F2 – Anwendungsfälle](F2-anwendungsfaelle.md),
- technische Implementierungs- und Architekturentscheidungen.

Dieses Kapitel beschreibt ausschließlich die fachlichen Bedingungen und den
erwarteten Ausgangszustand für die erstmalige Nutzung.

## 9. Ergebnis

S3 ist für Reportify anwendbar, da die Anwendung vor dem ersten Einsatz
eingerichtet und in einen definierten fachlichen Ausgangszustand versetzt
werden muss.

Für die erste Version sind insbesondere vorbereitete Benutzerkonten, gültige
Rollenzuordnungen, eine verfügbare Datenhaltung und eine erreichbare
Webanwendung erforderlich.

Eine Übernahme bestehender Reports ist dagegen nicht notwendig. Die
ausführbaren technischen Installationsschritte werden außerhalb dieses
Bausteins in der Installations- beziehungsweise Architekturdokumentation
konkretisiert.
