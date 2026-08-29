# P1 – Ziele und Rahmenbedingungen

> **Status:** Entwurf vom 29. August 2026. Noch nicht vom Team abschließend
> geprüft und freigegeben.

## 1. Ausgangssituation

Bei einem Schichtwechsel müssen Informationen über erledigte und offene
Aufgaben, aktuelle Probleme, Prioritäten und wichtige Hinweise an die
nachfolgende Schicht übergeben werden. Erfolgt diese Übergabe nur mündlich
oder über unterschiedliche Kommunikationswege, können Informationen
unvollständig, verspätet oder gar nicht weitergegeben werden.

Reportify soll diesen Übergabeprozess digital und einheitlich unterstützen.

## 2. Produktvision

Reportify ist eine Webanwendung, mit der Mitarbeitende während oder am Ende
ihrer Schicht einen strukturierten Übergabereport erstellen können. Die
nachfolgende Schicht erhält dadurch unmittelbar einen verständlichen
Überblick über den aktuellen Arbeitsstand.

Die Anwendung verbindet Schichtübergabe, Aufgabenübersicht und
Protokollierung in einem gemeinsamen System.

## 3. Zielgruppen und Stakeholder

| Stakeholder | Interesse beziehungsweise Aufgabe |
|---|---|
| Mitarbeiter:innen | Reports erstellen und Informationen der vorherigen Schicht einsehen |
| Schichtleitung | Übergaben überblicken und dringende Probleme erkennen |
| Nachfolgende Schicht | Offene Aufgaben, Probleme und Hinweise zuverlässig übernehmen |
| Projektteam | Reportify spezifizieren, entwickeln, testen und dokumentieren |
| Betreuer | Spezifikation, Architektur und Implementierung bewerten |

## 4. Projektziele

| ID | Ziel |
|---|---|
| Z-01 | Schichtinformationen werden vollständig und strukturiert erfasst. |
| Z-02 | Die nachfolgende Schicht kann den letzten Übergabereport unmittelbar einsehen. |
| Z-03 | Offene und erledigte Aufgaben sind eindeutig unterscheidbar. |
| Z-04 | Probleme und Incidents können mit einer Priorität gekennzeichnet werden. |
| Z-05 | Vergangene Reports können über eine Historie nachvollzogen werden. |
| Z-06 | Nur angemeldete Nutzer:innen können auf die geschützten Funktionen zugreifen. |
| Z-07 | Die Anwendung ist ohne besondere Schulung verständlich bedienbar. |
| Z-08 | Spezifikation, Architektur, Code und Tests verwenden dieselben Fachbegriffe. |

## 5. Geplanter Funktionsumfang

Der verbindliche Minimalumfang umfasst:

1. Anmelden und Abmelden
2. Schicht auswählen
3. Report erstellen
4. Letzte Übergabe einsehen
5. Report-Historie anzeigen

Zusätzliche Funktionen werden nur umgesetzt, wenn der vollständige
Minimalumfang funktioniert, getestet und dokumentiert ist.

## 6. Abgrenzung

Folgende Funktionen gehören nicht zum verbindlichen Umfang:

- native Mobile-App
- E-Mail-, Push- oder SMS-Benachrichtigungen
- externe Schnittstellen und Fremdsysteme
- komplexes Berechtigungssystem mit mehr als zwei Rollen
- Datei- und Bildanhänge
- Echtzeit-Chat
- umfangreiche statistische Auswertungen
- produktiver Betrieb in einem realen Unternehmen

Diese Punkte können als mögliche Weiterentwicklung dokumentiert werden.

## 7. Fachliche Rahmenbedingungen

- Es gibt die Rollen Mitarbeiter:in und Schichtleitung.
- Eine Übergabe gehört zu einer bestimmten Schicht.
- Reports enthalten erledigte Aufgaben, offene Aufgaben, Probleme
  beziehungsweise Incidents sowie wichtige Hinweise.
- Probleme können die Priorität niedrig, mittel oder hoch besitzen.
- Ein Report enthält Erstellungszeitpunkt und verantwortliche Person.
- Die Historie zeigt vergangene Reports in zeitlicher Reihenfolge.

## 8. Technische Rahmenbedingungen

- Webanwendung
- Java 21
- Spring Boot
- Thymeleaf für die Benutzeroberfläche
- Spring Data JPA für den Datenzugriff
- H2 als Datenbank
- Maven als Build-Werkzeug
- Git und GitHub zur Versionsverwaltung
- responsive Darstellung für Desktop und mobile Browser

## 9. Organisatorische Rahmenbedingungen

- Projektteam mit fünf Mitgliedern
- Dokumentation und Quellcode werden im gemeinsamen GitHub-Repository verwaltet.
- Änderungen werden über nachvollziehbare Git-Commits dokumentiert.
- Die finale Abgabe erfolgt spätestens am 25. September 2026.
- Die Anwendung muss lokal anhand einer Installationsanleitung gestartet werden können.
- KI-generierte Inhalte werden durch das Team geprüft und verstanden.

## 10. Erfolgskriterien

Reportify gilt im vereinbarten Umfang als erfolgreich umgesetzt, wenn:

- alle fünf Funktionen des Minimalumfangs Ende-zu-Ende funktionieren,
- die wichtigsten Abläufe durch automatisierte oder dokumentierte Tests geprüft sind,
- keine kritischen Fehler den Kernprozess der Schichtübergabe verhindern,
- eine fremde Person die Anwendung mithilfe der Installationsanleitung starten kann,
- Spezifikation, Architektur und Implementierung inhaltlich übereinstimmen,
- jedes Teammitglied die zentralen Komponenten im Code-Walkthrough erklären kann.
