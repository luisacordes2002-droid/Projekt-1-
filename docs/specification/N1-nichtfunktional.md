# N1 – Nichtfunktionale Anforderungen

> **Status:** Arbeitsentwurf vom 30.08.2026.  
> Die Anforderungen sind auf die erste Reportify-Version begrenzt und müssen vom
> Projektteam geprüft werden.

## 1. Zweck

Dieses Dokument beschreibt die Qualitätsanforderungen an Reportify. Jede
Anforderung erhält eine stabile Kennung und ein überprüfbares Akzeptanzkriterium.

Die Anforderungen ergänzen die funktionalen Beschreibungen in F2 und F3. Sie
beschreiben nicht, welche fachliche Funktion vorhanden ist, sondern wie gut,
sicher und zuverlässig diese Funktion ausgeführt werden muss.

## 2. Prioritäten

| Priorität | Bedeutung |
|---|---|
| A | Muss erfüllt werden; beeinflusst die Architektur oder Sicherheit wesentlich |
| B | Muss für die finale Version erfüllt werden |
| C | Wünschenswert, aber bei Zeitmangel nachrangig |

## 3. Übersicht

| ID | Thema | Priorität |
|---|---|---|
| NFR-10a-01 | Responsive Darstellung | A |
| NFR-10a-02 | Einheitliche Gestaltung | B |
| NFR-11a-01 | Schnelle Reporterstellung | A |
| NFR-11a-02 | Verständliche Rückmeldungen | B |
| NFR-11d-01 | Tastaturbedienung und Beschriftungen | B |
| NFR-12a-01 | Antwortzeiten | B |
| NFR-12d-01 | Verlässliche Speicherung | A |
| NFR-12d-02 | Keine Teilspeicherung | A |
| NFR-12e-01 | Datenmenge | C |
| NFR-13a-01 | Browser-Kompatibilität | B |
| NFR-13b-01 | Reproduzierbarer Build | A |
| NFR-13d-01 | Nachvollziehbare Inbetriebnahme | A |
| NFR-14a-01 | Verständliche Codegliederung | B |
| NFR-14b-01 | Automatisierte Tests | A |
| NFR-14c-01 | Austauschbare Persistenz | C |
| NFR-15a-01 | Schutz nichtöffentlicher Seiten | A |
| NFR-15a-02 | Sichere Anmeldung | A |
| NFR-15a-03 | Sichere Abmeldung | A |
| NFR-15b-01 | Sichere Passwortspeicherung | A |
| NFR-15b-02 | Schutz vor manipulierten Eingaben | A |
| NFR-15c-01 | Datensparsamkeit | B |
| NFR-15c-02 | Schutz von Protokolldaten | B |
| NFR-15d-01 | Nachvollziehbare Reporterstellung | A |
| NFR-16a-01 | Einheitliche Sprache | B |
| NFR-17b-01 | Nachvollziehbare Git-Historie | B |

## 10. Erscheinungsbild

### NFR-10a-01 – Responsive Darstellung

**Anforderung:**  
Alle Dialoge müssen auf Desktop- und Mobilbildschirmen ohne Verlust der
Kernfunktionen nutzbar sein.

**Akzeptanzkriterien:**

- Die Dialoge sind bei einer Breite von 360 Pixeln vollständig bedienbar.
- Es entsteht kein horizontaler Seitenlauf für die Hauptinhalte.
- Formularelemente und Schaltflächen bleiben erreichbar.
- Die Report-Historie darf auf kleinen Bildschirmen als Karten dargestellt werden.

**Priorität:** A

### NFR-10a-02 – Einheitliche Gestaltung

**Anforderung:**  
Navigation, Formulare, Meldungen und Schaltflächen müssen in allen Dialogen
einheitlich gestaltet und bezeichnet sein.

**Akzeptanzkriterien:**

- Gleiche Aktionen besitzen auf allen Seiten dieselbe Bezeichnung.
- Erfolgsmeldungen und Fehlermeldungen sind visuell unterscheidbar.
- Die Navigation verwendet in DLG-02 bis DLG-06 dieselben Hauptpunkte.
- Die in B1 festgelegten Feldbezeichnungen werden verwendet.

**Priorität:** B

## 11. Benutzbarkeit

### NFR-11a-01 – Schnelle Reporterstellung

**Anforderung:**  
Eine angemeldete Person muss ohne Schulung einen Report erstellen können.

**Akzeptanzkriterien:**

- Das Report-Formular ist von der Startseite mit höchstens einer Aktion erreichbar.
- Alle Report-Felder befinden sich in einem zusammenhängenden Dialog.
- Der Standardablauf benötigt keine technische Eingabe.
- Nach erfolgreicher Speicherung wird der gespeicherte Report angezeigt.

**Priorität:** A

### NFR-11a-02 – Verständliche Rückmeldungen

**Anforderung:**  
Reportify muss Erfolg, Fehler und leere Zustände verständlich erklären.

**Akzeptanzkriterien:**

- Validierungsfehler nennen das betroffene Feld.
- Fehlermeldungen enthalten keine Java-Ausnahmen oder technischen Stacktraces.
- Nach einer erfolgreichen Speicherung erscheint eine Erfolgsmeldung.
- Leere Übergabe und leere Historie besitzen jeweils einen erklärenden Hinweis.
- Wenn möglich, wird eine sinnvolle nächste Aktion angeboten.

**Priorität:** B

### NFR-11d-01 – Tastaturbedienung und Beschriftungen

**Anforderung:**  
Die Kernfunktionen müssen ohne Maus bedienbar sein.

**Akzeptanzkriterien:**

- Alle Eingabefelder und Schaltflächen sind per Tabulatortaste erreichbar.
- Die Fokusreihenfolge folgt der sichtbaren Reihenfolge.
- Jedes Eingabefeld besitzt eine sichtbare Beschriftung.
- Der Tastaturfokus ist erkennbar.
- Formulare können über eine eindeutige Speichern-Schaltfläche abgesendet werden.

**Priorität:** B

## 12. Leistung und Zuverlässigkeit

### NFR-12a-01 – Antwortzeiten

**Anforderung:**  
Lokale Standardaktionen sollen ohne störende Wartezeit ausgeführt werden.

**Akzeptanzkriterien:**

- Startseite, aktuelle Übergabe und Historie werden im lokalen Testbetrieb
  normalerweise innerhalb von zwei Sekunden angezeigt.
- Die Messung erfolgt mit mindestens 1.000 Testreports.
- Mindestens 95 Prozent von 20 aufeinanderfolgenden Aufrufen erfüllen die
  Zwei-Sekunden-Grenze.
- Eine eventuell auftretende längere Verarbeitung wird sichtbar gekennzeichnet.

**Priorität:** B

### NFR-12d-01 – Verlässliche Speicherung

**Anforderung:**  
Ein erfolgreich bestätigter Report darf nicht verloren gehen.

**Akzeptanzkriterien:**

- Nach der Erfolgsmeldung ist der Report in der Historie vorhanden.
- Der Report kann über seine Kennung erneut geöffnet werden.
- Ersteller:in, Schicht und Erstellungszeitpunkt bleiben erhalten.
- Ein Neustart der Anwendung entfernt erfolgreich gespeicherte Reports nicht.

**Priorität:** A

### NFR-12d-02 – Keine Teilspeicherung

**Anforderung:**  
Ein ungültiger oder technisch fehlgeschlagener Speichervorgang darf keinen
unvollständigen Report erzeugen.

**Akzeptanzkriterien:**

- Bei einem Validierungsfehler wird kein Report gespeichert.
- Bei einem Speicherfehler wird kein teilweise gefüllter Report in der Historie
  angezeigt.
- Nutzer:innen erhalten eine verständliche Fehlermeldung.
- Nach Behebung des Fehlers kann die Eingabe erneut gespeichert werden.

**Priorität:** A

### NFR-12e-01 – Datenmenge

**Anforderung:**  
Die erste Version soll eine für den Projektbetrieb ausreichende Datenmenge
verwalten können.

**Akzeptanzkriterien:**

- Mindestens 50 Benutzerkonten können verwaltet werden.
- Mindestens 10.000 Reports können gespeichert werden.
- Die Historie bleibt bei 1.000 Testreports bedienbar.
- Eine Erweiterung auf größere Datenmengen ist nicht Bestandteil der ersten Version.

**Priorität:** C

## 13. Betriebsumgebung

### NFR-13a-01 – Browser-Kompatibilität

**Anforderung:**  
Reportify muss in aktuellen Desktop- und Mobilbrowsern funktionieren.

**Akzeptanzkriterien:**

- Der Kernablauf wird mindestens mit einer aktuellen Version von Chrome und Safari
  geprüft.
- Anmeldung, Reporterstellung, Übergabe und Historie funktionieren in beiden
  getesteten Browsern.
- Es wird kein Browser-Plugin benötigt.
- JavaScript- oder Darstellungsfehler verhindern keine Kernfunktion.

**Priorität:** B

### NFR-13b-01 – Reproduzierbarer Build

**Anforderung:**  
Das Projekt muss mit den im Repository vorhandenen Werkzeugen reproduzierbar gebaut
und getestet werden können.

**Akzeptanzkriterien:**

- Java 21 ist als benötigte Java-Version dokumentiert.
- Der Maven Wrapper ist Bestandteil des Repositorys.
- Der Befehl `./mvnw test` läuft auf einem korrekt eingerichteten System erfolgreich.
- Abhängigkeiten werden durch die Build-Konfiguration eindeutig beschrieben.
- Ein Build erfordert keine geheimen Zugangsdaten.

**Priorität:** A

### NFR-13d-01 – Nachvollziehbare Inbetriebnahme

**Anforderung:**  
Eine nicht am Projekt beteiligte Person muss Reportify anhand einer Anleitung
starten können.

**Akzeptanzkriterien:**

- Benötigte Software und Versionen sind dokumentiert.
- Installation, Test und Start werden mit konkreten Befehlen beschrieben.
- Benötigte Konfigurationen werden erklärt.
- Testzugänge werden sicher und ohne echte persönliche Passwörter bereitgestellt.
- Die Anleitung wird auf einem frischen oder bereinigten System geprüft.

**Priorität:** A

## 14. Wartbarkeit und Testbarkeit

### NFR-14a-01 – Verständliche Codegliederung

**Anforderung:**  
Der Quellcode muss fachlich nachvollziehbar und eindeutig gegliedert sein.

**Akzeptanzkriterien:**

- Klassen und Methoden verwenden verständliche, fachlich passende Namen.
- Zuständigkeiten für Benutzer, Reports und Sicherheit sind erkennbar getrennt.
- Nicht verwendeter Code und nicht verwendete Importe werden entfernt.
- Offensichtliche Logik wird nicht durch bedeutungslose Kommentare wiederholt.
- Die Codegliederung stimmt mit der Bausteinsicht der Architektur überein.

**Priorität:** B

### NFR-14b-01 – Automatisierte Tests

**Anforderung:**  
Die zentralen fachlichen Regeln müssen automatisiert geprüft werden.

**Akzeptanzkriterien:**

Mindestens folgende Fälle besitzen automatisierte Tests:

- Anwendungskontext startet erfolgreich.
- Anmeldung mit gültigen Zugangsdaten funktioniert.
- Anmeldung mit ungültigen Zugangsdaten wird abgelehnt.
- Ungültiger Report wird nicht gespeichert.
- Gültiger Report wird gespeichert.
- Der neueste Report wird als aktuelle Übergabe bestimmt.
- Die Historie ist absteigend nach Zeitpunkt sortiert.
- Geschützte Seiten sind ohne Anmeldung nicht erreichbar.

Der Befehl `./mvnw test` führt alle automatisierten Tests aus.

**Priorität:** A

### NFR-14c-01 – Austauschbare Persistenz

**Anforderung:**  
Die fachliche Verarbeitung soll nicht vollständig von einer konkreten Datenbank
abhängig sein.

**Akzeptanzkriterien:**

- Die Benutzeroberfläche enthält keine Datenbankabfragen.
- Die fachlichen Funktionen verwenden klar abgegrenzte Zugriffe auf gespeicherte
  Nutzer und Reports.
- Ein Wechsel der Datenbank erfordert keine Änderung der Dialogspezifikation.
- Die konkrete Umsetzung wird in der Architektur beschrieben.

**Priorität:** C

## 15. Sicherheit und Datenschutz

### NFR-15a-01 – Schutz nichtöffentlicher Seiten

**Anforderung:**  
Alle fachlichen Report-Funktionen dürfen nur nach erfolgreicher Anmeldung verwendet
werden.

**Akzeptanzkriterien:**

- Ohne gültige Sitzung sind nur Anmeldung und benötigte öffentliche Ressourcen
  erreichbar.
- Der direkte Aufruf einer geschützten Seite führt zur Anmeldung.
- Es werden keine Report-Inhalte an nicht angemeldete Personen ausgeliefert.
- Der Zugriffsschutz wird automatisiert getestet.

**Priorität:** A

### NFR-15a-02 – Sichere Anmeldung

**Anforderung:**  
Die Anmeldung darf keine unnötigen Informationen über Benutzerkonten preisgeben.

**Akzeptanzkriterien:**

- Bei falschem Benutzernamen und falschem Passwort erscheint dieselbe allgemeine
  Fehlermeldung.
- Passwortfelder werden verdeckt dargestellt.
- Passwörter erscheinen nicht in der URL.
- Die Passwortlänge folgt nach Teamentscheidung `TD-014`.
- Eine Selbstregistrierung ist in der ersten Version nicht verfügbar.

**Priorität:** A

### NFR-15a-03 – Sichere Abmeldung

**Anforderung:**  
Eine Abmeldung muss die bestehende Sitzung wirksam beenden.

**Akzeptanzkriterien:**

- Nach der Abmeldung wird die Anmeldeseite angezeigt.
- Geschützte Seiten können mit der beendeten Sitzung nicht erneut geöffnet werden.
- Eine neue Anmeldung ist für den erneuten Zugriff erforderlich.
- Die Abmeldung verändert keine gespeicherten Reports.

**Priorität:** A

### NFR-15b-01 – Sichere Passwortspeicherung

**Anforderung:**  
Passwörter dürfen niemals im Klartext gespeichert werden.

**Akzeptanzkriterien:**

- Persistierte Benutzerdaten enthalten nur einen sicheren Passwortnachweis.
- Das ursprüngliche Passwort kann nicht aus einer normalen Anzeige ausgelesen
  werden.
- Passwörter erscheinen nicht in Logs oder Fehlermeldungen.
- Das verwendete Verfahren wird in der Architektur dokumentiert.

**Priorität:** A

### NFR-15b-02 – Schutz vor manipulierten Eingaben

**Anforderung:**  
Eingaben dürfen keine fremden Befehle oder ausführbaren Inhalte in Reportify
einschleusen.

**Akzeptanzkriterien:**

- Alle Report-Eingaben werden serverseitig validiert.
- Eingetragener HTML- oder Skripttext wird nicht als ausführbares Skript dargestellt.
- Ungültige Auswahlwerte für Schicht, Rolle und Priorität werden zurückgewiesen.
- Zustandsändernde Anfragen werden gegen unberechtigte Ausführung geschützt.
- Manipulierte Kennungen führen nicht zum Zugriff auf nicht vorhandene Reports.

**Priorität:** A

### NFR-15c-01 – Datensparsamkeit

**Anforderung:**  
Reportify speichert nur für Anmeldung und Schichtübergabe notwendige Daten.

**Akzeptanzkriterien:**

- Es werden keine privaten Telefonnummern oder persönlichen E-Mail-Adressen
  benötigt.
- Benutzerkonten enthalten nur Kennung, Benutzername, Anzeigename,
  Passwortnachweis, Rolle und Aktivierungsstatus.
- Reports enthalten nur die in D1 festgelegten fachlichen Informationen.
- Nicht benötigte personenbezogene Daten werden nicht erhoben.

**Priorität:** B

### NFR-15c-02 – Schutz von Protokolldaten

**Anforderung:**  
Anwendungsprotokolle dürfen keine geheimen oder unnötigen fachlichen Inhalte
enthalten.

**Akzeptanzkriterien:**

- Passwörter und Passwortnachweise erscheinen nicht in Logs.
- Report-Texte werden nicht vollständig in normalen Fehlerlogs ausgegeben.
- Fehler können über technische Kennungen nachvollzogen werden.
- Bei einer Testsuche finden sich keine Testpasswörter in den Protokolldateien.

**Priorität:** B

### NFR-15d-01 – Nachvollziehbare Reporterstellung

**Anforderung:**  
Für jeden Report muss nachvollziehbar sein, wann und durch wen er erstellt wurde.

**Akzeptanzkriterien:**

- Jeder Report besitzt eine erstellende Person.
- Jeder Report besitzt einen vom System vergebenen Erstellungszeitpunkt.
- Beide Informationen werden in Detailansicht und Historie angezeigt.
- Die Angaben werden nach der Speicherung nicht verändert.

**Priorität:** A

## 16. Sprache und kulturelle Anforderungen

### NFR-16a-01 – Einheitliche Sprache

**Anforderung:**  
Die Benutzeroberfläche verwendet eine einheitliche Sprache.

**Akzeptanzkriterien:**

- Beschriftungen, Hinweise und Fehlermeldungen sind in der final gewählten Sprache.
- Innerhalb eines Dialogs werden Deutsch und Englisch nicht ohne Grund gemischt.
- Technische Schlüssel wie `FRUEHSCHICHT` werden nicht direkt angezeigt.
- Die endgültige Sprache wird über `TD-011` festgelegt.

**Priorität:** B

## 17. Projekt- und Dokumentationsanforderungen

### NFR-17b-01 – Nachvollziehbare Git-Historie

**Anforderung:**  
Projektänderungen müssen den Teammitgliedern und der Bewertung nachvollziehbar
zugeordnet werden können.

**Akzeptanzkriterien:**

- Commit-Nachrichten folgen Conventional Commits.
- Commit-Nachrichten sind kurz, konkret und englisch.
- Teammitglieder verwenden eindeutig zuordenbare Git-Identitäten.
- Größere Themen werden in eigenen Branches bearbeitet.
- Die Beiträge mehrerer Teammitglieder sind in der Historie erkennbar.
- KI-Werkzeuge werden entsprechend den Kursanforderungen dokumentiert.

**Priorität:** B

## 18. Bewusst nicht geforderte Eigenschaften

Für die erste Version werden folgende Qualitätsziele nicht zugesichert:

- Betrieb rund um die Uhr
- Hochverfügbarkeit mit mehreren Servern
- Verarbeitung sicherheitskritischer Daten
- Unterstützung sehr großer Benutzerzahlen
- vollständige Barrierefreiheitszertifizierung
- Mehrsprachigkeit
- Offlinebetrieb
- native Mobile-App

Diese Punkte sind bewusst außerhalb des Projektumfangs und nicht versehentlich
vergessen worden.

## 19. Offene Entscheidungen

Die offenen Qualitätsentscheidungen werden in
[`TEAM-ENTSCHEIDUNGEN.md`](../TEAM-ENTSCHEIDUNGEN.md) verwaltet.

Besonders relevant sind:

- `TD-006` – Rollenberechtigungen
- `TD-007` – Aufbewahrungsdauer
- `TD-009` – Datenbank
- `TD-011` – Sprache
- `TD-013` – Textlänge
- `TD-014` – Passwortregel

## 20. Nachverfolgbarkeit

Die Anforderungen dieses Dokuments konkretisieren insbesondere:

- [P1 – Ziele und Rahmenbedingungen](P1-ziele-rahmenbedingungen.md)
- [F2 – Anwendungsfälle](F2-anwendungsfaelle.md)
- [F3 – Anwendungsfunktionen](F3-anwendungsfunktionen.md)
- [D1 – Datenmodell](D1-datenmodell.md)
- [D2 – Datentypenverzeichnis](D2-datentypen.md)
- [B1 – Dialogspezifikation](B1-dialogspezifikationen.md)

Die NFR-Kennungen bleiben stabil und werden später in Architektur und Tests
wiederverwendet.
