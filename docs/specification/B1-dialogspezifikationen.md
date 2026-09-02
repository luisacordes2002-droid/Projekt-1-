# B1 – Dialogspezifikation

> **Status:** Arbeitsentwurf vom 30.08.2026.  
> Die Dialoge beschreiben die geplante Benutzeroberfläche der ersten
> Reportify-Version. Gestaltung und Feldregeln müssen vom Team geprüft werden.

## 1. Zweck

Dieses Dokument beschreibt die Benutzeroberfläche von Reportify aus fachlicher
Sicht. Es legt fest:

- welche Dialoge und Seiten vorhanden sind,
- welche Informationen angezeigt werden,
- welche Eingaben möglich sind,
- welche Aktionen Nutzer:innen auslösen können,
- wie Validierungsfehler und leere Zustände dargestellt werden.

Technische Controller, HTML-Dateien und Framework-Komponenten werden erst in der
Architektur und Implementierung beschrieben.

## 2. Dialogübersicht

| ID | Dialog | Zweck | Anmeldung erforderlich |
|---|---|---|---|
| DLG-01 | Anmeldung | Zugang zu Reportify erhalten | Nein |
| DLG-02 | Startseite | Überblick und zentrale Navigation | Ja |
| DLG-03 | Report erstellen | Neue Schichtübergabe erfassen | Ja |
| DLG-04 | Aktuelle Übergabe | Neuesten Report anzeigen | Ja |
| DLG-05 | Report-Historie | Gespeicherte Reports durchsuchen | Ja |
| DLG-06 | Report-Details | Vollständigen Report anzeigen | Ja |

## 3. Grundlegende Navigation

Nach erfolgreicher Anmeldung enthält jede geschützte Seite eine einheitliche
Navigation.

| Navigationselement | Ziel |
|---|---|
| Reportify-Logo oder Titel | DLG-02 – Startseite |
| Neue Übergabe | DLG-03 – Report erstellen |
| Aktuelle Übergabe | DLG-04 – Aktuelle Übergabe |
| Historie | DLG-05 – Report-Historie |
| Abmelden | Sitzung beenden und DLG-01 öffnen |

Auf kleinen Bildschirmen darf die Navigation als aufklappbares Menü dargestellt
werden. Die Bezeichnungen und erreichbaren Funktionen bleiben identisch.

## 4. DLG-01 – Anmeldung

### 4.1 Zweck

Eine berechtigte Person meldet sich mit Benutzername und Passwort bei Reportify an.

### 4.2 Aufruf

Der Dialog wird angezeigt, wenn:

- Reportify ohne gültige Sitzung geöffnet wird,
- eine geschützte Seite ohne gültige Sitzung aufgerufen wird,
- eine Person sich erfolgreich abgemeldet hat.

### 4.3 Dialogelemente

| Element | Typ | Pflicht | Beschreibung |
|---|---|---|---|
| Reportify-Titel | Überschrift | – | Zeigt den Namen der Anwendung |
| Benutzername | Eingabefeld | Ja | Eingabe gemäß `BenutzernameDT` |
| Passwort | Passwortfeld | Ja | Eingabe wird verdeckt dargestellt |
| Anmelden | Schaltfläche | – | Prüft die eingegebenen Zugangsdaten |
| Fehlermeldung | Hinweisbereich | – | Zeigt eine allgemeine Anmeldefehlermeldung |

### 4.4 Verhalten

1. Die Person trägt Benutzername und Passwort ein.
2. Die Person betätigt „Anmelden“.
3. Reportify prüft beide Eingaben.
4. Bei erfolgreicher Anmeldung wird DLG-02 geöffnet.
5. Bei ungültigen Zugangsdaten bleibt DLG-01 geöffnet.

### 4.5 Validierung und Fehler

- Leere Pflichtfelder werden direkt gekennzeichnet.
- Bei ungültigen Zugangsdaten erscheint:

  **„Benutzername oder Passwort ist nicht korrekt.“**

- Die Meldung verrät nicht, welche der beiden Eingaben falsch war.
- Das eingegebene Passwort wird nach einer fehlgeschlagenen Anmeldung nicht
  dauerhaft gespeichert.
- Eine Selbstregistrierung ist in der ersten Version nicht vorgesehen.

### 4.6 Bezug

- `UC-01` – Anmelden
- `AF-01` – Zugangsdaten prüfen
- `AF-02` – Sitzung verwalten
- `DT-03` – BenutzernameDT
- `DT-05` – PasswortDT
- `TD-008` – Anlage der Benutzerkonten
- `TD-014` – Passwortregel

## 5. DLG-02 – Startseite

### 5.1 Zweck

Die Startseite gibt einer angemeldeten Person einen schnellen Überblick und Zugang
zu den wichtigsten Funktionen.

### 5.2 Dialogelemente

| Element | Typ | Beschreibung |
|---|---|---|
| Begrüßung | Text | Zeigt den Anzeigenamen der angemeldeten Person |
| Aktuelle Übergabe | Zusammenfassung | Zeigt Kerndaten des neuesten Reports |
| Neue Übergabe erstellen | Schaltfläche | Öffnet DLG-03 |
| Aktuelle Übergabe ansehen | Schaltfläche | Öffnet DLG-04 |
| Historie öffnen | Schaltfläche | Öffnet DLG-05 |
| Abmelden | Schaltfläche | Beendet die Sitzung |

### 5.3 Zusammenfassung der aktuellen Übergabe

Wenn mindestens ein Report vorhanden ist, werden angezeigt:

- Schicht
- Erstellungszeitpunkt
- Ersteller:in
- Priorität, falls vorhanden
- gekürzte Vorschau der offenen Aufgaben
- gekürzte Vorschau der wichtigen Hinweise

Die vollständigen Inhalte werden erst in DLG-04 angezeigt.

### 5.4 Leerer Zustand

Wenn noch kein Report vorhanden ist, erscheint:

**„Es ist noch keine Übergabe vorhanden.“**

Zusätzlich wird die Schaltfläche „Neue Übergabe erstellen“ angeboten.

### 5.5 Bezug

- `UC-05` – Übergabe einsehen
- `AF-06` – Aktuelle Übergabe bestimmen
- `AF-08` – Report-Details bereitstellen
- `TD-005` – Bestimmung der aktuellen Übergabe

## 6. DLG-03 – Report erstellen

### 6.1 Zweck

Eine angemeldete Person erfasst einen neuen Report für eine Schichtübergabe.

### 6.2 Dialogelemente

| Element | Typ | Pflicht | Beschreibung |
|---|---|---|---|
| Schicht | Auswahlliste | Ja | Früh-, Spät- oder Nachtschicht |
| Erledigte Aufgaben | Mehrzeiliges Textfeld | Ja | Abgeschlossene Aufgaben |
| Offene Aufgaben | Mehrzeiliges Textfeld | Bedingt | Noch ausstehende Aufgaben |
| Probleme/Incidents | Mehrzeiliges Textfeld | Bedingt | Aktuelle Störungen und Probleme |
| Priorität | Auswahlliste | Bedingt | Niedrig, Mittel oder Hoch |
| Wichtige Hinweise | Mehrzeiliges Textfeld | Bedingt | Informationen für die nächste Schicht |
| Report speichern | Schaltfläche | – | Validiert und speichert den Report |
| Abbrechen | Schaltfläche | – | Verlässt den Dialog ohne Speicherung |
| Fehlerbereich | Hinweisbereich | – | Zeigt Validierungsfehler |

### 6.3 Feldregeln

- Eine Schicht muss ausgewählt sein.
- Das Feld "Erledigte Aufgaben" muss ausgefüllt sein.
- Reine Leerzeichen gelten nicht als Inhalt.
- Bei einem Problem oder Incident muss eine Priorität ausgewählt sein.
- Jedes Textfeld besitzt vorläufig eine Maximallänge von 4.000 Zeichen.
- Mehrzeilige Eingaben sind erlaubt.

### 6.4 Speicherung

1. Die Person füllt die benötigten Felder aus.
2. Sie betätigt „Report speichern“.
3. Reportify validiert die Eingaben.
4. Bei gültigen Eingaben wird der Report gespeichert.
5. Eine Erfolgsmeldung wird angezeigt.
6. Anschließend wird der gespeicherte Report vollständig angezeigt.

Vorgesehene Erfolgsmeldung:

**„Der Report wurde erfolgreich gespeichert.“**

### 6.5 Validierungsfehler

Bei ungültigen Eingaben bleibt der Dialog geöffnet. Bereits eingegebene fachliche
Daten bleiben sichtbar.

Mögliche Meldungen:

- **„Bitte wählen Sie eine Schicht aus.“**
- **„Bitte füllen Sie das Pflichtfeld 'Erledigte Aufgaben' aus.“**
- **„Bitte wählen Sie für das Problem eine Priorität aus.“**
- **„Das Feld darf höchstens 4.000 Zeichen enthalten.“**

Die Fehlermeldung wird direkt beim betroffenen Feld und zusätzlich in einem
allgemeinen Fehlerbereich angezeigt.

### 6.6 Abbrechen

Wenn noch keine Eingabe erfolgt ist, führt „Abbrechen“ direkt zur Startseite.

Wenn bereits Daten eingegeben wurden, muss vor dem Verlassen bestätigt werden:

**„Nicht gespeicherte Eingaben verwerfen?“**

### 6.7 Bezug

- `UC-03` – Schicht auswählen
- `UC-04` – Report erstellen
- `AF-03` – Schichten bereitstellen
- `AF-04` – Report-Eingaben validieren
- `AF-05` – Report speichern
- `DT-09` – SchichtDT
- `DT-10` – PrioritaetDT
- `DT-11` – ReportTextDT
- `TD-001`, `TD-002` und `TD-013`

## 7. DLG-04 – Aktuelle Übergabe

### 7.1 Zweck

Die nachfolgende Schicht sieht den aktuellsten gespeicherten Report vollständig.

### 7.2 Angezeigte Informationen

| Information | Darstellung |
|---|---|
| Schicht | Bezeichnung der ausgewählten Schicht |
| Ersteller:in | Anzeigename |
| Erstellt am | Lokales Datum und Uhrzeit |
| Priorität | Sichtbare Prioritätskennzeichnung, falls vorhanden |
| Erledigte Aufgaben | Vollständiger Text oder Leerhinweis |
| Offene Aufgaben | Vollständiger Text oder Leerhinweis |
| Probleme/Incidents | Vollständiger Text oder Leerhinweis |
| Wichtige Hinweise | Vollständiger Text oder Leerhinweis |

Leere optionale Felder werden nicht als Fehler behandelt. Sie können entweder
ausgeblendet oder mit „Keine Angabe“ dargestellt werden. Das Team muss eine
einheitliche Variante festlegen.

### 7.3 Aktionen

| Aktion | Ergebnis |
|---|---|
| Zur Startseite | Öffnet DLG-02 |
| Historie anzeigen | Öffnet DLG-05 |
| Neue Übergabe | Öffnet DLG-03 |

### 7.4 Leerer Zustand

Wenn noch kein Report vorhanden ist, erscheint:

**„Es ist noch keine Übergabe vorhanden.“**

Zusätzlich wird „Neue Übergabe erstellen“ angeboten.

### 7.5 Bezug

- `UC-05` – Übergabe einsehen
- `AF-06` – Aktuelle Übergabe bestimmen
- `AF-08` – Report-Details bereitstellen
- `TD-005` – Bestimmung der aktuellen Übergabe

## 8. DLG-05 – Report-Historie

### 8.1 Zweck

Gespeicherte Reports werden in nachvollziehbarer Reihenfolge angezeigt.

### 8.2 Sortierung

- Der neueste Report steht an erster Stelle.
- Die Sortierung erfolgt anhand des Erstellungszeitpunkts.
- Reports mit identischem sichtbarem Zeitpunkt besitzen intern weiterhin eine
  eindeutige Reihenfolge.

### 8.3 Listeneintrag

Jeder Listeneintrag zeigt mindestens:

- Schicht
- Erstellungszeitpunkt
- Ersteller:in
- Priorität, falls vorhanden
- kurze Vorschau eines fachlichen Inhalts
- Aktion „Details anzeigen“

Auf kleinen Bildschirmen dürfen Listeneinträge als Karten statt als Tabelle
dargestellt werden.

### 8.4 Aktionen

| Aktion | Ergebnis |
|---|---|
| Details anzeigen | Öffnet DLG-06 für den ausgewählten Report |
| Neue Übergabe | Öffnet DLG-03 |
| Zur Startseite | Öffnet DLG-02 |

### 8.5 Leerer Zustand

Wenn keine Reports existieren, erscheint:

**„Es wurden noch keine Reports erstellt.“**

Zusätzlich wird „Neue Übergabe erstellen“ angeboten.

### 8.6 Bezug

- `UC-06` – Report-Historie anzeigen
- `AF-07` – Report-Historie bereitstellen
- `AF-08` – Report-Details bereitstellen
- `DT-12` – ZeitpunktDT

## 9. DLG-06 – Report-Details

### 9.1 Zweck

Ein in der Historie ausgewählter Report wird vollständig und unverändert angezeigt.

### 9.2 Angezeigte Informationen

DLG-06 zeigt dieselben Report-Informationen wie DLG-04. Zusätzlich muss erkennbar
sein, dass ein historischer Report und nicht zwingend die aktuelle Übergabe
angezeigt wird.

### 9.3 Aktionen

| Aktion | Ergebnis |
|---|---|
| Zurück zur Historie | Öffnet DLG-05 |
| Zur Startseite | Öffnet DLG-02 |
| Neue Übergabe | Öffnet DLG-03 |

Eine Bearbeiten- oder Löschen-Schaltfläche ist in der ersten Version nicht
vorgesehen.

### 9.4 Nicht gefundener Report

Wenn die angeforderte Report-Kennung nicht existiert, erscheint:

**„Der angeforderte Report wurde nicht gefunden.“**

Die Person kann anschließend zur Historie oder Startseite wechseln.

### 9.5 Bezug

- `UC-06` – Report-Historie anzeigen
- `AF-08` – Report-Details bereitstellen
- `TD-003` – Bearbeitung gespeicherter Reports
- `TD-004` – Löschen gespeicherter Reports

## 10. Dialogübergreifende Regeln

### 10.1 Zugriffsschutz

- DLG-02 bis DLG-06 benötigen eine gültige Sitzung.
- Ohne gültige Sitzung wird DLG-01 geöffnet.
- Nach der Abmeldung dürfen zuvor geschützte Seiten nicht mehr verwendet werden.

### 10.2 Rückmeldungen

Reportify unterscheidet:

- Erfolgsmeldungen
- Validierungsfehler
- allgemeine Fehler
- leere Zustände

Meldungen werden verständlich formuliert und enthalten, wenn möglich, eine
nächste sinnvolle Aktion.

### 10.3 Formulare

- Jedes Eingabefeld besitzt eine sichtbare Beschriftung.
- Pflichtfelder werden erkennbar gekennzeichnet.
- Eingaben bleiben nach einem Validierungsfehler erhalten.
- Die Bedienung ist mit Tastatur möglich.
- Die Reihenfolge der Eingabefelder ist logisch und vorhersehbar.

### 10.4 Responsive Darstellung

Die Anwendung muss auf aktuellen Desktop- und Mobilbrowsern verwendbar sein.

- Inhalte dürfen nicht horizontal aus dem sichtbaren Bereich laufen.
- Schaltflächen müssen auf Touch-Geräten ausreichend groß sein.
- Tabellen dürfen auf kleinen Bildschirmen durch Karten ersetzt werden.
- Texte und Fehlermeldungen müssen ohne Vergrößerung lesbar sein.

### 10.5 Sprache

Die vorläufige Sprache der Benutzeroberfläche ist Deutsch. Technische Schlüssel wie
`FRUEHSCHICHT` werden Nutzer:innen nicht angezeigt.

Die endgültige Sprache ist Gegenstand von `TD-011`.

## 11. Navigation zwischen den Dialogen

| Ausgang | Aktion | Ziel |
|---|---|---|
| DLG-01 | Anmeldung erfolgreich | DLG-02 |
| DLG-02 | Neue Übergabe erstellen | DLG-03 |
| DLG-02 | Aktuelle Übergabe ansehen | DLG-04 |
| DLG-02 | Historie öffnen | DLG-05 |
| DLG-03 | Report erfolgreich speichern | DLG-06 |
| DLG-04 | Historie anzeigen | DLG-05 |
| DLG-05 | Details anzeigen | DLG-06 |
| DLG-06 | Zurück zur Historie | DLG-05 |
| DLG-02 bis DLG-06 | Abmelden | DLG-01 |

## 12. Noch zu ergänzende Darstellungen

Sobald die ersten Seiten implementiert sind, werden diesem Dokument geprüfte
Screenshots oder Wireframes hinzugefügt für:

- Anmeldung
- Startseite
- Report-Formular
- aktuelle Übergabe
- Historie
- Report-Details
- mobile Darstellung

Die Screenshots müssen mit den beschriebenen Dialogen und dem tatsächlichen
Programmstand übereinstimmen.

## 13. Offene Entscheidungen

Die offenen UI-Fragen werden zentral in
[`TEAM-ENTSCHEIDUNGEN.md`](../TEAM-ENTSCHEIDUNGEN.md) verwaltet.

Für B1 sind besonders relevant:

- `TD-001` – Report-Pflichtfelder
- `TD-002` – Bedeutung der Priorität
- `TD-003` – Bearbeitung gespeicherter Reports
- `TD-004` – Löschen gespeicherter Reports
- `TD-005` – Bestimmung der aktuellen Übergabe
- `TD-006` – zusätzliche Berechtigungen
- `TD-008` – Anlage der Benutzerkonten
- `TD-011` – Sprache der Benutzeroberfläche
- `TD-013` – Textlänge
- `TD-014` – Passwortregel

## 14. Nachverfolgbarkeit

Diese Dialogspezifikation konkretisiert insbesondere:

- [F2 – Anwendungsfälle](F2-anwendungsfaelle.md)
- [F3 – Anwendungsfunktionen](F3-anwendungsfunktionen.md)
- [D1 – Datenmodell](D1-datenmodell.md)
- [D2 – Datentypenverzeichnis](D2-datentypen.md)

Die Kennungen `DLG-01` bis `DLG-06` bleiben bei späteren Änderungen stabil.
