# Installation und Inbetriebnahme

Diese Anleitung beschreibt, wie Reportify lokal eingerichtet, getestet und gestartet wird.

## 1. Voraussetzungen

Benötigt werden:

- Java Development Kit (JDK) 21
- Git
- ein aktueller Webbrowser
- eine Internetverbindung beim ersten Maven-Start

Eine separate Maven-Installation ist nicht erforderlich. Das Repository enthält den Maven Wrapper.

Die installierte Java-Version kann mit folgendem Befehl geprüft werden:

```powershell
java -version
```

In der Ausgabe muss Java 21 angezeigt werden.

## 2. Repository herunterladen

Das Repository wird mit Git geklont:

```powershell
git clone https://github.com/luisacordes2002-droid/Projekt-1-.git
cd Projekt-1-
cd reportify
```

Alternativ kann ein bereits vorhandener lokaler Klon verwendet werden.

## 3. Tests ausführen

### Windows PowerShell

```powershell
.\mvnw.cmd test
```

### Linux und macOS

```bash
chmod +x mvnw
./mvnw test
```

Ein erfolgreicher Testlauf endet mit:

```text
BUILD SUCCESS
```

## 4. Anwendung lokal starten

Für die lokale Entwicklung wird das Spring-Profil `dev` verwendet. Dieses Profil legt die benötigten Testbenutzer an.

### Windows PowerShell

```powershell
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=dev"
```

### Linux und macOS

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

Die Anwendung ist vollständig gestartet, sobald folgende Meldung erscheint:

```text
Started ReportifyApplication
```

## 5. Anwendung aufrufen

Nach dem Start ist Reportify unter folgender Adresse erreichbar:

<http://localhost:8080>

Nicht angemeldete Personen werden automatisch auf die Login-Seite weitergeleitet.

## 6. Entwicklungszugänge

Das Profil `dev` stellt folgende lokale Testbenutzer bereit:

| Benutzername | Passwort | Rolle |
|---|---|---|
| `mitarbeiter` | `Reportify!2026` | `MITARBEITER` |
| `schichtleitung` | `Reportify!2026` | `SCHICHTLEITUNG` |

Diese Zugangsdaten sind ausschließlich für die lokale Entwicklung und Demonstration vorgesehen.

## 7. Datenbank

Reportify verwendet eine lokale H2-Dateidatenbank. Die Daten werden im Verzeichnis `reportify/data` gespeichert.

Die Datenbanktabellen werden beim Start automatisch anhand der vorhandenen Java-Entities angelegt beziehungsweise aktualisiert. Lokale Datenbankdateien werden durch `.gitignore` vom Repository ausgeschlossen.

## 8. Anwendung beenden

Die laufende Anwendung wird im Terminal mit folgender Tastenkombination beendet:

```text
Strg + C
```

Falls Windows anschließend fragt:

```text
Batchvorgang abbrechen (J/N)?
```

wird die Frage mit `J` bestätigt.

## 9. Häufige Probleme

### Port 8080 ist bereits belegt

Möglicherweise läuft Reportify noch in einem anderen Terminal. Die dort laufende Anwendung muss zunächst mit `Strg + C` beendet werden.

### Anmeldung schlägt fehl

Die Anwendung muss für die lokalen Testbenutzer mit dem Profil `dev` gestartet werden. Der entsprechende Startbefehl steht in Abschnitt 4.

Außerdem müssen Groß- und Kleinschreibung sowie das Ausrufezeichen im Passwort beachtet werden.

### Falsche Java-Version

Mit folgendem Befehl wird die aktive Java-Version geprüft:

```powershell
java -version
```

Für dieses Projekt wird Java 21 benötigt.

### Abhängigkeiten können nicht geladen werden

Beim ersten Start benötigt Maven eine Internetverbindung, um die Projektabhängigkeiten herunterzuladen. Danach können bereits heruntergeladene Abhängigkeiten aus dem lokalen Maven-Cache verwendet werden.

## 10. Erfolgreiche Inbetriebnahme prüfen

Die lokale Inbetriebnahme ist erfolgreich, wenn:

1. die automatischen Tests mit `BUILD SUCCESS` abgeschlossen werden,
2. die Anwendung ohne Fehlermeldung startet,
3. <http://localhost:8080> erreichbar ist,
4. die eigene Login-Seite angezeigt wird,
5. die Anmeldung mit einem Entwicklungsbenutzer funktioniert und
6. anschließend die geschützte Startseite erscheint.