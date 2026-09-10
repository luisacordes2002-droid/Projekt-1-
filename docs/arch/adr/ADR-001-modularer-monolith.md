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
Persistenz
```

Die Benutzeroberfläche wird über Thymeleaf-Templates bereitgestellt.

## Begründung

Ein modular strukturierter Monolith bietet für Reportify einen geeigneten Kompromiss zwischen einer klaren Softwarearchitektur und einer für das Projekt angemessenen technischen Komplexität.

Im Gegensatz zu einem unstrukturierten Monolithen können Verantwortlichkeiten klar getrennt werden. Gleichzeitig entstehen nicht die zusätzlichen Betriebs- und Kommunikationsprobleme einer Microservice-Architektur.

Spring Boot unterstützt diese Struktur gut und ist bereits die technische Grundlage des bestehenden Projekts.

## Konsequenzen

### Positive Konsequenzen

- einfache Entwicklung und Ausführung als eine Anwendung
- überschaubares Deployment
- klare Trennung technischer Verantwortlichkeiten
- geringerer Infrastrukturaufwand als bei Microservices
- Architektur kann im Quellcode durch Pakete nachvollzogen werden
- für die Größe des Projekts angemessen

### Negative Konsequenzen

- alle Bestandteile werden gemeinsam deployt
- einzelne Bereiche können nicht unabhängig voneinander skaliert werden
- eine saubere interne Struktur muss während der Entwicklung konsequent eingehalten werden
- mit wachsendem Projektumfang können stärkere fachliche Modulgrenzen notwendig werden

## Auswirkungen auf die Implementierung

Die geplante Zielstruktur des Java-Codes orientiert sich an folgenden Paketen:

```text
de.thm.reportify
├── controller
├── service
├── repository
├── model
├── config
└── ReportifyApplication
```

Der vorhandene `StartseiteController` wurde entsprechend dieser Architekturentscheidung in das Package `de.thm.reportify.controller` verschoben.

Weitere Controller sowie zukünftige Service-, Repository-, Modell- und Konfigurationsklassen sollen entsprechend ihrer jeweiligen Verantwortung in den vorgesehenen Packages abgelegt werden.

Die Paketstruktur ist damit teilweise umgesetzt. Weitere Bestandteile werden mit der fortschreitenden Implementierung ergänzt. Architektur und tatsächliche Implementierung müssen dabei weiterhin synchron gehalten werden.