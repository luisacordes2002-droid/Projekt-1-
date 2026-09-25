# ADR-003: H2-Datenbank für Version 1

## Status

Accepted

## Kontext

Reportify benötigt eine relationale Datenbank, um Anwendungsdaten wie Berichte, Aufgaben und weitere fachliche Informationen persistent verwalten zu können.

Für die erste Version des Systems soll die Datenhaltung möglichst einfach in die bestehende Spring-Boot-Anwendung integriert werden können. Gleichzeitig soll der Entwicklungs- und Konfigurationsaufwand für das Hochschulprojekt überschaubar bleiben.

Im aktuellen Projekt sind Spring Data JPA und H2 bereits als Dependencies eingebunden. H2 ist in `application.properties` als dateibasierte Persistenz konfiguriert. Die Konfiguration wurde mit dem aktuellen Spring-Boot-Build erfolgreich getestet. Diese Architekturentscheidung legt H2 als Datenbanktechnologie für Version 1 fest.

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
```

## Begründung

H2 erfüllt den für Version 1 benötigten Funktionsumfang und ermöglicht eine
reproduzierbare lokale Inbetriebnahme ohne externen Datenbankserver. Durch JPA
bleibt die Persistenzlogik von der konkreten Datenbank weitgehend getrennt. Der
geringe Betriebsaufwand ist für die lokale Demonstrationsanwendung wichtiger
als Skalierung und Mehrinstanzbetrieb.

## Konsequenzen

**Positiv:**

- Das Projekt kann mit JDK und Maven Wrapper lokal gestartet werden.
- Daten bleiben in einer lokalen Datei über Anwendungsneustarts hinweg erhalten.
- Für Tests kann weiterhin eine isolierte H2-Datenbank verwendet werden.

**Negativ:**

- H2 ist nicht als Produktionsdatenbank für mehrere Anwendungsinstanzen vorgesehen.
- Ein späterer Wechsel zu PostgreSQL oder MariaDB erfordert eigene Migrations-
  und Kompatibilitätstests.
- Backups und gleichzeitige externe Zugriffe werden in Version 1 nicht automatisiert.

## Auswirkungen auf die Implementierung

- Entities werden mit JPA annotiert und über Spring-Data-Repositories gespeichert.
- Die dateibasierte Datenbank liegt lokal unter `reportify/data` und wird nicht
  in Git eingecheckt.
- Datenbankspezifische Logik wird außerhalb der Konfiguration vermieden.
