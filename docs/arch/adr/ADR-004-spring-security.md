# ADR-004: Spring Security für Authentifizierung und Autorisierung

## Status

Proposed

## Kontext

Reportify soll Funktionen bereitstellen, die nicht für beliebige Personen frei zugänglich sein sollen. Mitarbeitende sollen sich gegenüber der Anwendung authentifizieren können, bevor sie auf geschützte Bereiche zugreifen.

Darüber hinaus kann es notwendig werden, Zugriffsrechte abhängig von Benutzerrollen oder Zuständigkeiten zu unterscheiden. Damit entsteht die Anforderung, Authentifizierung und Autorisierung zentral und konsistent umzusetzen.

Für Spring-Boot-Anwendungen bietet sich Spring Security als etabliertes Sicherheitsframework an.

Im aktuellen Projektstand ist Spring Security jedoch noch nicht als Dependency im `pom.xml` eingebunden. Deshalb wird diese Architekturentscheidung zunächst als `Proposed` dokumentiert.

## Betrachtete Alternativen

### Eigene Authentifizierungslogik

Login, Sitzungsverwaltung und Zugriffsprüfungen könnten vollständig selbst implementiert werden.

**Vorteile:**

- vollständige Kontrolle über das Verhalten
- keine zusätzliche Security-Bibliothek notwendig

**Nachteile:**

- hoher Implementierungsaufwand
- erhöhtes Risiko für Sicherheitsfehler
- Authentifizierung und Autorisierung müssten vollständig selbst gepflegt werden
- zentrale Sicherheitsmechanismen müssten eigenständig entwickelt werden

### Keine Authentifizierung

Die Anwendung könnte in der ersten Version ohne Login-Mechanismus betrieben werden.

**Vorteile:**

- geringster technischer Aufwand
- keine zusätzliche Konfiguration erforderlich

**Nachteile:**

- geschützte Daten wären nicht ausreichend abgesichert
- Benutzer könnten nicht eindeutig zugeordnet werden
- rollenabhängige Zugriffsrechte wären nicht möglich
- für eine Anwendung mit benutzerbezogenen Arbeitsinformationen langfristig ungeeignet

### Spring Security

Spring Security wird als zentraler Mechanismus für Authentifizierung und Autorisierung verwendet.

**Vorteile:**

- enge Integration in Spring Boot
- etablierte Sicherheitsmechanismen
- zentrale Konfiguration von Zugriffsregeln
- Unterstützung für Login, Sessions und Rollen
- gute Erweiterbarkeit

**Nachteile:**

- zusätzliche Dependency
- initialer Konfigurationsaufwand
- falsche Konfiguration kann zu unerwartetem Zugriffsverhalten führen
- Sicherheitsregeln müssen mit den fachlichen Rollen abgestimmt werden

## Entscheidung

Für Reportify soll **Spring Security zur Umsetzung von Authentifizierung und Autorisierung verwendet werden**.

Die Entscheidung ist derzeit noch nicht vollständig umgesetzt und trägt deshalb den Status `Proposed`.

Nach der Integration soll Spring Security insbesondere folgende Aufgaben übernehmen:

- Anmeldung von Benutzern,
- Verwaltung authentifizierter Sitzungen,
- Schutz nicht öffentlicher Seiten,
- rollen- beziehungsweise berechtigungsabhängige Zugriffssteuerung,
- zentrale Sicherheitskonfiguration.

## Begründung

Spring Security ist für eine Spring-Boot-Anwendung eine naheliegende und etablierte Lösung zur Absicherung von Webanwendungen.

Eine selbst entwickelte Authentifizierungslogik würde zusätzlichen Aufwand verursachen und das Risiko von Sicherheitsfehlern erhöhen.

Die zentrale Konfiguration von Sicherheitsregeln unterstützt außerdem die angestrebte klare Trennung von Verantwortlichkeiten innerhalb der Architektur.

## Konsequenzen

### Positive Konsequenzen

- zentrale Sicherheitslogik
- etablierte Integration mit Spring Boot
- wiederverwendbare Authentifizierungs- und Autorisierungsmechanismen
- geschützte Bereiche können konsistent abgesichert werden
- spätere Rollenmodelle können ergänzt werden
- geringerer Eigenentwicklungsaufwand für sicherheitskritische Funktionen

### Negative Konsequenzen

- zusätzliche technische Komplexität
- Spring Security muss zunächst in das Projekt integriert werden
- Rollen und Zugriffsregeln müssen fachlich definiert werden
- falsche Konfiguration kann zu unbeabsichtigten Zugriffsrechten führen
- Tests für geschützte und öffentliche Endpunkte werden notwendig

## Auswirkungen auf die Implementierung

Für die Umsetzung muss zunächst eine geeignete Spring-Security-Dependency in das Maven-Projekt aufgenommen werden.

Die Sicherheitskonfiguration soll anschließend in einem eigenen Konfigurationsbereich gekapselt werden, beispielsweise:

```text
de.thm.reportify
└── config
    └── SecurityConfig
    ```