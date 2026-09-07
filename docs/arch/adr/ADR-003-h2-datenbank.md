# ADR-003: H2-Datenbank für Version 1

## Status

Accepted

## Kontext

Reportify benötigt eine relationale Datenbank, um Anwendungsdaten wie Berichte, Aufgaben und weitere fachliche Informationen persistent verwalten zu können.

Für die erste Version des Systems soll die Datenhaltung möglichst einfach in die bestehende Spring-Boot-Anwendung integriert werden können. Gleichzeitig soll der Entwicklungs- und Konfigurationsaufwand für das Hochschulprojekt überschaubar bleiben.

Im aktuellen Projekt sind Spring Data JPA und H2 bereits als Dependencies eingebunden. Dadurch kann H2 ohne zusätzliche externe Datenbankinstallation zusammen mit der Anwendung verwendet werden.

Zum aktuellen Zeitpunkt ist in `application.properties` jedoch noch keine konkrete dateibasierte H2-Persistenz konfiguriert. Diese Architekturentscheidung legt deshalb zunächst H2 als Datenbanktechnologie für Version 1 fest, ohne eine bereits umgesetzte Dateikonfiguration zu behaupten.

## Betrachtete Alternativen

### PostgreSQL

PostgreSQL könnte als eigenständiges relationales Datenbanksystem eingesetzt werden.

**Vorteile:**

- für produktive Anwendungen gut geeignet
- leistungsfähiges relationales Datenbanksystem
- gute Unterstützung für größere Datenmengen
- unabhängige Datenhaltung außerhalb der Anwendung

**Nachteile:**

- zusätzlicher Installations- und Konfigurationsaufwand
- separater Datenbankdienst notwendig
- höherer Aufwand für lokale Entwicklungsumgebungen
- für die erste Version des Hochschulprojekts derzeit nicht erforderlich

### MySQL oder MariaDB

Alternativ könnte eine MySQL- beziehungsweise MariaDB-Datenbank verwendet werden.

**Vorteile:**

- weit verbreitete relationale Datenbanksysteme
- für produktive Anwendungen geeignet
- gute Unterstützung durch Spring Data JPA

**Nachteile:**

- zusätzlicher Datenbankserver erforderlich
- zusätzliche Konfiguration notwendig
- höherer Betriebsaufwand als bei einer eingebetteten Datenbank

### H2

H2 ist eine leichtgewichtige relationale Java-Datenbank, die sich direkt in eine Spring-Boot-Anwendung integrieren lässt.

**Vorteile:**

- einfache Integration in Spring Boot
- kein separater Datenbankserver erforderlich
- geringer Konfigurationsaufwand
- gut für Entwicklung und kleinere Anwendungen geeignet
- Unterstützung durch Spring Data JPA
- bereits als Dependency im Projekt vorhanden

**Nachteile:**

- für größere produktive Systeme nur eingeschränkt geeignet
- unterscheidet sich in einzelnen Bereichen von produktiven Datenbanksystemen wie PostgreSQL
- bei einer späteren Migration können Anpassungen notwendig werden

## Entscheidung

Für **Version 1 von Reportify wird H2 als relationale Datenbank verwendet**.

Der Zugriff auf persistente Daten erfolgt über Spring Data JPA. Repository-Komponenten bilden dabei die Schnittstelle zwischen der Geschäftslogik und der Datenhaltung.

Die grundlegende Struktur ist:

```text
Service
   |
   v
Repository
   |
   v
Spring Data JPA
   |
   v
H2-Datenbank