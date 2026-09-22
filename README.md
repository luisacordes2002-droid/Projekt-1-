# Reportify

Reportify ist eine Webanwendung für digitale Schichtübergaben. Mitarbeitende sollen während oder am Ende ihrer Schicht strukturierte Reports erstellen können. Die nachfolgende Schicht erhält dadurch einen Überblick über erledigte und offene Aufgaben, Probleme, Prioritäten und wichtige Hinweise.

## Projektstatus

Das Projekt befindet sich in Entwicklung.

Bereits umgesetzt sind:

- Spring-Boot-Grundprojekt
- eigene Login-Seite
- Authentifizierung mit Spring Security
- Benutzer und Rollen
- Passwortregeln
- lokale H2-Datenbank
- automatische Tests für zentrale Authentifizierungsbestandteile
- Reportübersicht und Reportdetailansicht
- Erstellen von Schichtreports mit Priorität
- Kennzeichnen von Reports als erledigt
- Löschen von Reports ausschließlich durch Benutzer mit der Rolle `SCHICHTLEITUNG`
- dauerhafte Speicherung der Reports in der H2-Datenbank
- direkte Weiterleitung zur Reportübersicht nach erfolgreicher Anmeldung
- automatisierte Service- und Controller-Tests für die Reportverwaltung

Die fachlichen Funktionen zur Erstellung, Anzeige und Verwaltung von Schichtreports werden schrittweise ergänzt.

## Technologien

- Java 21
- Spring Boot 4
- Spring MVC
- Spring Security
- Spring Data JPA
- Thymeleaf
- H2 Database
- Maven
- HTML und CSS

## Dokumentation

| Dokument | Inhalt |
|---|---|
| [Teaminfo](TEAMINFO.md) | Projektidee, Team und eingesetzte Technologien |
| [Spezifikation](docs/specification/README.md) | Fachliche Anforderungen nach Siedersleben |
| [Architektur](docs/arch/README.md) | Architekturbeschreibung nach arc42 |
| [Architekturentscheidungen](docs/arch/adr/) | Architecture Decision Records |
| [Installation](INSTALL.md) | Einrichtung, Tests und lokaler Start |

## Schnellstart

Voraussetzung ist ein installiertes JDK 21.

### Windows PowerShell

```powershell
cd reportify
.\mvnw.cmd test
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=dev"
```

### Linux und macOS

```bash
cd reportify
chmod +x mvnw
./mvnw test
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

Anschließend ist Reportify unter folgender Adresse erreichbar:

<http://localhost:8080>

Weitere Informationen stehen in der [Installationsanleitung](INSTALL.md).

## Lokale Entwicklungszugänge

| Benutzername | Passwort | Rolle |
|---|---|---|
| `mitarbeiter` | `Reportify!2026` | `MITARBEITER` |
| `schichtleitung` | `Reportify!2026` | `SCHICHTLEITUNG` |

Die Zugangsdaten sind ausschließlich für die lokale Entwicklung und Demonstration vorgesehen. Sie werden nur beim Start mit dem Profil `dev` bereitgestellt.

## Projektstruktur

```text
Projekt-1-/
├── docs/
│   ├── arch/                 Architekturdokumentation und ADRs
│   └── specification/        Fachliche Spezifikation
├── reportify/
│   ├── src/main/java/        Java-Quellcode
│   ├── src/main/resources/   Konfiguration und Thymeleaf-Templates
│   └── src/test/java/        Automatische Tests
├── INSTALL.md                Installationsanleitung
├── README.md                 Projektübersicht
└── TEAMINFO.md               Team und Projektidee
```

## Tests

Die Tests werden im Ordner `reportify` ausgeführt.

Unter Windows:

```powershell
.\mvnw.cmd test
```

Unter Linux und macOS:

```bash
./mvnw test
```

Ein erfolgreicher Testlauf endet mit `BUILD SUCCESS`.

## Mitarbeit

Für Commit-Nachrichten wird der Conventional-Commits-Standard verwendet, zum Beispiel:

```text
feat(auth): add custom login page
fix(ui): correct login alignment
docs(install): update startup instructions
refactor(web): move controller into controller package
```