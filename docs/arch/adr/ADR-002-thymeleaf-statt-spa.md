# ADR-002: Thymeleaf statt Single-Page-Application

## Status

Accepted

## Kontext

Für Reportify muss entschieden werden, wie die Benutzeroberfläche technisch umgesetzt wird.

Grundsätzlich bestehen zwei naheliegende Möglichkeiten:

1. serverseitige HTML-Erzeugung mit Thymeleaf innerhalb der Spring-Boot-Anwendung,
2. ein separates Frontend als Single-Page-Application, beispielsweise mit React, Angular oder Vue.

Reportify ist eine webbasierte Anwendung, deren zentrale Funktionen vor allem aus Formularen, Übersichten, Berichten, Aufgaben und Informationen für Schichtübergaben bestehen.

Für diese Funktionen ist keine komplexe clientseitige Anwendungslogik erforderlich. Gleichzeitig soll die technische Struktur für das Hochschulprojekt überschaubar bleiben und sich eng an die bestehende Spring-Boot-Anwendung anbinden.

Thymeleaf ist bereits als Dependency im Projekt eingebunden und wird aktuell bereits für die Startseite verwendet.

## Betrachtete Alternativen

### Separate Single-Page-Application

Das Frontend könnte als eigenständige Anwendung mit einem JavaScript-Framework entwickelt werden. Die Kommunikation mit dem Backend würde dann typischerweise über eine REST-Schnittstelle erfolgen.

**Vorteile:**

- klare technische Trennung zwischen Frontend und Backend
- umfangreiche clientseitige Interaktionen möglich
- moderne und dynamische Benutzeroberflächen gut umsetzbar
- Frontend könnte unabhängig vom Backend weiterentwickelt werden

**Nachteile:**

- zusätzliches Framework und zusätzliche Projektstruktur notwendig
- höhere technische Komplexität
- REST-Schnittstellen müssten zusätzlich entwickelt und gepflegt werden
- separates Build- und Deployment-Verfahren möglich
- zusätzlicher Aufwand für Authentifizierung und Zustandsverwaltung
- für die Anforderungen von Reportify derzeit nicht erforderlich

### Serverseitige Darstellung mit Thymeleaf

Die HTML-Seiten werden direkt durch die Spring-Boot-Anwendung erzeugt.

Controller liefern Daten an Thymeleaf-Templates, die daraus die Benutzeroberfläche erzeugen.

**Vorteile:**

- direkte Integration in Spring Boot
- geringe zusätzliche technische Komplexität
- kein separates Frontend-Projekt notwendig
- einfache Verarbeitung klassischer Formulare
- gemeinsames Build und Deployment
- bereits im Projekt vorhanden

**Nachteile:**

- weniger geeignet für sehr komplexe clientseitige Interaktionen
- Seitenwechsel können vollständige HTTP-Anfragen erforderlich machen
- starke Trennung von Frontend und Backend ist geringer als bei einer SPA

## Entscheidung

Die Benutzeroberfläche von Reportify wird mit **Thymeleaf als serverseitiger Template-Engine** umgesetzt.

Eine separate Single-Page-Application wird für Version 1 nicht verwendet.

Die Spring-MVC-Controller verarbeiten HTTP-Anfragen und stellen die für die Darstellung benötigten Daten bereit. Anschließend werden Thymeleaf-Templates verwendet, um die HTML-Seiten serverseitig zu erzeugen.

Die grundlegende Kommunikation sieht damit wie folgt aus:

```text
Webbrowser
    |
    | HTTP-Anfrage
    v
Spring MVC Controller
    |
    | Daten für die Ansicht
    v
Thymeleaf Template
    |
    | HTML
    v
Webbrowser
```

## Begründung


Thymeleaf ist für Reportify geeignet, da die Anwendung überwiegend klassische Weboberflächen mit Formularen und Übersichten benötigt. Durch die serverseitige Darstellung kann die Benutzeroberfläche direkt in die bestehende Spring-Boot-Anwendung integriert werden.

Eine separate Single-Page-Application würde zusätzliche technische Komplexität verursachen, ohne für die derzeit vorgesehenen Anforderungen einen ausreichenden Mehrwert zu bieten. Da Thymeleaf bereits im Projekt eingebunden und für die Startseite verwendet wird, kann außerdem auf der bestehenden technischen Grundlage aufgebaut werden.

## Konsequenzen

### Positive Konsequenzen

- kein separates Frontend-Projekt erforderlich
- gemeinsames Build und Deployment mit der Spring-Boot-Anwendung
- direkte Integration mit Spring MVC
- geringere technische Komplexität
- bestehende Thymeleaf-Struktur kann weiterverwendet werden

### Negative Konsequenzen

- komplexe clientseitige Interaktionen sind schwieriger umzusetzen als mit einer SPA
- Änderungen der dargestellten Inhalte können neue HTTP-Anfragen erfordern
- Frontend und Backend können nicht unabhängig voneinander deployt werden

## Auswirkungen auf die Implementierung

HTML-Seiten werden unter `src/main/resources/templates` als Thymeleaf-Templates abgelegt. Spring-MVC-Controller verarbeiten die HTTP-Anfragen und liefern die für die Darstellung benötigten Daten an die jeweiligen Templates.

Die bestehende Startseite unter `templates/startseite.html` verwendet bereits diesen Ansatz. Weitere Benutzeroberflächen sollen entsprechend derselben Struktur umgesetzt werden.