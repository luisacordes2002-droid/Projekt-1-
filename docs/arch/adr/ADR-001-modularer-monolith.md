# ADR-001: Modular strukturierter Spring-Boot-Monolith

## Status

Accepted

## Kontext

Reportify wird als webbasierte Anwendung zur Unterstützung der digitalen Dokumentation im Arbeitsalltag entwickelt. Die Anwendung soll unter anderem Berichte, Aufgaben und Informationen für Schichtübergaben verwalten.

Für die technische Umsetzung muss entschieden werden, wie die Anwendung strukturiert wird. Dabei soll die Architektur einerseits eine klare Trennung verschiedener Verantwortlichkeiten ermöglichen und andererseits für den Umfang des Projekts überschaubar bleiben.

Das Projekt wird von einem kleinen Entwicklungsteam im Rahmen eines Hochschulprojekts umgesetzt. Eine verteilte Architektur würde zusätzliche technische und organisatorische Komplexität verursachen, beispielsweise durch mehrere Deployments, Netzwerkkommunikation zwischen Diensten und aufwendigere Fehleranalyse.

## Betrachtete Alternativen

### Klassischer Monolith ohne klare interne Struktur

Die gesamte Anwendung könnte ohne festgelegte interne Schichten oder Module entwickelt werden.

**Vorteile:**
- geringer anfänglicher Strukturierungsaufwand
- einfache Ausführung und Bereitstellung

**Nachteile:**
- Verantwortlichkeiten können sich schnell vermischen
- steigende Wartungs- und Erweiterungskosten
- schlechtere Nachvollziehbarkeit der Architektur im Quellcode

### Microservice-Architektur

Fachliche Bereiche könnten als eigenständige Services entwickelt und unabhängig voneinander ausgeführt werden.

**Vorteile:**
- starke technische Trennung einzelner Bereiche
- unabhängige Deployments und Skalierung grundsätzlich möglich

**Nachteile:**
- deutlich höhere technische Komplexität
- zusätzliche Netzwerkkommunikation
- aufwendigeres Deployment
- für Umfang und Teamgröße des Projekts nicht erforderlich

### Modular strukturierter Monolith

Reportify wird als eine gemeinsam deploybare Spring-Boot-Anwendung entwickelt. Innerhalb der Anwendung werden Verantwortlichkeiten jedoch klar getrennt.

Vorgesehen sind insbesondere Bereiche für:

- Controller
- Services
- Repositories
- Domänenmodell
- Konfiguration
- Thymeleaf-Templates

## Entscheidung

Reportify wird als **modular strukturierter Spring-Boot-Monolith** umgesetzt.

Die Anwendung wird als eine gemeinsame Spring-Boot-Anwendung gebaut und ausgeführt. Innerhalb des Projekts werden die technischen Verantwortlichkeiten durch eine definierte Paket- und Schichtenstruktur voneinander getrennt.

Die grundlegende Abhängigkeitsrichtung lautet:

```text
Controller
    |
    v
Service
    |
    v
Repository
    |
    v
Persistenz# ADR-001: Modular strukturierter Spring-Boot-Monolith

## Status

Accepted

## Kontext

Reportify wird als webbasierte Anwendung zur Unterstützung der digitalen Dokumentation im Arbeitsalltag entwickelt. Die Anwendung soll unter anderem Berichte, Aufgaben und Informationen für Schichtübergaben verwalten.

Für die technische Umsetzung muss entschieden werden, wie die Anwendung strukturiert wird. Dabei soll die Architektur einerseits eine klare Trennung verschiedener Verantwortlichkeiten ermöglichen und andererseits für den Umfang des Projekts überschaubar bleiben.

Das Projekt wird von einem kleinen Entwicklungsteam im Rahmen eines Hochschulprojekts umgesetzt. Eine verteilte Architektur würde zusätzliche technische und organisatorische Komplexität verursachen, beispielsweise durch mehrere Deployments, Netzwerkkommunikation zwischen Diensten und aufwendigere Fehleranalyse.

## Betrachtete Alternativen

### Klassischer Monolith ohne klare interne Struktur

Die gesamte Anwendung könnte ohne festgelegte interne Schichten oder Module entwickelt werden.

**Vorteile:**
- geringer anfänglicher Strukturierungsaufwand
- einfache Ausführung und Bereitstellung

**Nachteile:**
- Verantwortlichkeiten können sich schnell vermischen
- steigende Wartungs- und Erweiterungskosten
- schlechtere Nachvollziehbarkeit der Architektur im Quellcode

### Microservice-Architektur

Fachliche Bereiche könnten als eigenständige Services entwickelt und unabhängig voneinander ausgeführt werden.

**Vorteile:**
- starke technische Trennung einzelner Bereiche
- unabhängige Deployments und Skalierung grundsätzlich möglich

**Nachteile:**
- deutlich höhere technische Komplexität
- zusätzliche Netzwerkkommunikation
- aufwendigeres Deployment
- für Umfang und Teamgröße des Projekts nicht erforderlich

### Modular strukturierter Monolith

Reportify wird als eine gemeinsam deploybare Spring-Boot-Anwendung entwickelt. Innerhalb der Anwendung werden Verantwortlichkeiten jedoch klar getrennt.

Vorgesehen sind insbesondere Bereiche für:

- Controller
- Services
- Repositories
- Domänenmodell
- Konfiguration
- Thymeleaf-Templates

## Entscheidung

Reportify wird als **modular strukturierter Spring-Boot-Monolith** umgesetzt.

Die Anwendung wird als eine gemeinsame Spring-Boot-Anwendung gebaut und ausgeführt. Innerhalb des Projekts werden die technischen Verantwortlichkeiten durch eine definierte Paket- und Schichtenstruktur voneinander getrennt.

Die grundlegende Abhängigkeitsrichtung lautet:

```text
Controller
    |
    v
Service
    |
    v
Repository
    |
    v
Persistenz