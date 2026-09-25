# S1 - Nachbarsysteme 

> **Status:** Mit dem Umfang der Version 1 abgeglichener Stand vom 25.09.2026.
> Für den aktuellen Umfang von Reportify sind keine externen Fachsysteme oder
> fachlichen Schnittstellen vorgesehen.

## 1. Zweck 

Dieses Kapitel beschreibt die Abgrenzung von Reportify gegenüber externen
Fachsystemen und Schnittstellen.

Für die erste Version sind keine externen Fachsysteme an Reportify angebunden.
Der Baustein dient daher insbesondere dazu, die Systemgrenze und die nicht
vorgesehenen Integrationen ausdrücklich zu dokumentieren.

Die grundsätzliche Systemgrenze wird in
[P2 – Architekturüberblick](P2-architekturueberblick.md) beschrieben.

## 2. Nachbarsysteme im aktuellen Umfang 

Reportify besitzt im aktuellen Umfang keine angebundenen externen
Fachsysteme.

Die für die Schichtübergabe benötigten Informationen werden innerhalb von
Reportify erfasst, gespeichert und angezeigt. Es findet kein automatischer
Datenaustausch mit anderen betrieblichen Anwendungen statt.

Insbesondere sind keine Schnittstellen zu folgenden Systemarten vorgesehen:

- Personal- oder Benutzerverwaltungssystemen,
- Dienstplan- oder Schichtplanungssystemen,
- Aufgaben- oder Ticketsystemen,
- E-Mail-, Push- oder SMS-Diensten,
- externen Datei- oder Dokumentensystemen,
- externen APIs.

Diese Systeme sind für die fachlichen Abläufe der ersten Version weder
Voraussetzung noch Bestandteil von Reportify.

## 3. Benutzerkonten 

Die für die Anmeldung benötigten Benutzerkonten werden für die erste Version
vorbereitet bereitgestellt. Eine Selbstregistrierung ist nicht vorgesehen.

Die Benutzerkonten werden nicht aus einem externen Benutzerverwaltungs-,
Verzeichnis- oder Identitätssystem übernommen. Die Bereitstellung der Konten
stellt daher keine Schnittstelle zu einem Nachbarsystem dar.

Die Rollen `MITARBEITER` und `SCHICHTLEITUNG` werden innerhalb von Reportify
verwendet. Die Schichtleitung besitzt zusätzlich die Berechtigung,
gespeicherte Reports zu löschen.

Die erstmalige Bereitstellung von Benutzerkonten wird von einer möglichen
Datenmigration abgegrenzt und im Baustein S2 behandelt.

## 4. Schichtinformationen

Die Auswahl einer Schicht erfolgt innerhalb von Reportify.

Reportify übernimmt keine Dienst- oder Schichtpläne aus einem externen
Planungssystem und übermittelt keine Schichtinformationen an ein solches
System.

Die tatsächliche Organisation und Planung des Schichtbetriebs liegt außerhalb
der Systemgrenze von Reportify. Die Anwendung dokumentiert ausschließlich die
für die Übergabe benötigten Informationen.

## 5. Report-Daten

Reports werden innerhalb von Reportify erstellt und gespeichert.

Die Anwendung importiert im aktuellen Umfang keine Reports aus anderen
Systemen und exportiert Reports nicht über eine fachliche Schnittstelle an
externe Systeme.

Das Speichern, Anzeigen, Bearbeiten und berechtigte Löschen von Reports sind
interne Funktionen von Reportify.

Gespeicherte Reports werden nicht automatisch aufgrund einer Verbindung zu
einem externen System verändert oder gelöscht. Für die erste Version ist
außerdem keine automatische Löschung aufgrund einer Aufbewahrungsfrist
vorgesehen.

## 6. Benutzer und Webbrowser 

Mitarbeiter:innen und Schichtleitungen greifen über einen Webbrowser auf
Reportify zu.

Der Webbrowser ist dabei der Zugang zur Benutzeroberfläche und kein
fachliches Nachbarsystem. Die fachliche Verarbeitung der Eingaben sowie die
Speicherung und Bereitstellung der Report-Daten erfolgen innerhalb von
Reportify.

Die tatsächlichen betrieblichen Aufgaben, über die in einem Report berichtet
wird, werden außerhalb von Reportify durchgeführt.

## 7. Abgrenzung für spätere Erweiterungen 

Eine spätere Version von Reportify könnte Schnittstellen zu externen
Fachsystemen erhalten, beispielsweise zu einer Schichtplanung oder einem
betrieblichen Aufgaben- beziehungsweise Ticketsystem.

Solche Integrationen gehören nicht zum aktuellen Umfang. Werden sie später
eingeführt, müssen die jeweiligen Nachbarsysteme, ausgetauschten Daten,
Verantwortlichkeiten und Schnittstellen in diesem Kapitel ergänzt und in der
Architektur technisch konkretisiert werden.

## 8. Ergebnis 

Für die erste Version von Reportify bestehen keine fachlichen Nachbarsysteme
und keine externen fachlichen Schnittstellen.

S1 ist deshalb für das Projekt nur als Abgrenzung relevant. Eine weitergehende
Schnittstellenspezifikation ist für den aktuellen Umfang nicht erforderlich.

Diese Festlegung entspricht der Abgrenzung in
[P1 – Ziele und Rahmenbedingungen](P1-ziele-rahmenbedingungen.md) und der
Systemgrenze aus
[P2 – Architekturüberblick](P2-architekturueberblick.md).
