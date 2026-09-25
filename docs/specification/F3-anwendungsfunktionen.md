# F3 – Anwendungsfunktionen

> **Status:** Mit Implementierung und Tests abgeglichener Stand vom 25.09.2026.  
> Die formale Teamfreigabe steht noch aus.

## 1. Zweck

Dieses Dokument beschreibt die fachlichen Funktionen von Reportify. Die Funktionen
realisieren die in [F2 – Anwendungsfälle](F2-anwendungsfaelle.md) beschriebenen
Interaktionen.

Die Beschreibung bleibt unabhängig von der technischen Implementierung. Konkrete
Frameworks, Klassen und Datenbanktabellen werden in der Architekturdokumentation
beziehungsweise im Quellcode beschrieben.

## 2. Funktionsübersicht

| ID | Anwendungsfunktion | Zugehörige Anwendungsfälle |
|---|---|---|
| AF-01 | Zugangsdaten prüfen | UC-01 |
| AF-02 | Sitzung verwalten | UC-01, UC-02 |
| AF-03 | Schichten bereitstellen | UC-03 |
| AF-04 | Report-Eingaben validieren | UC-04 |
| AF-05 | Report speichern | UC-04 |
| AF-06 | Aktuelle Übergabe bestimmen | UC-05 |
| AF-07 | Report-Historie bereitstellen | UC-06 |
| AF-08 | Report-Details bereitstellen | UC-05, UC-06 |
| AF-09 | Gespeicherten Report bearbeiten | UC-07 |
| AF-10 | Gespeicherten Report löschen | UC-08 |
| AF-11 | Persönliches Passwort erstmalig festlegen | UC-09 |
| AF-12 | Report als erledigt kennzeichnen | Report-Lebenszyklus |
| AF-13 | Druckansicht bereitstellen | UC-11 |


## 3. Funktionsbeschreibungen

### AF-01 – Zugangsdaten prüfen

**Zweck:**  
Feststellen, ob sich eine berechtigte Person mit gültigen Zugangsdaten anmeldet.

**Eingaben:**

- Benutzername
- Passwort

**Verarbeitung:**

1. Das System prüft, ob die Zugangsdaten zu einem vorhandenen Benutzerkonto gehören.
2. Das eingegebene Passwort wird sicher mit den gespeicherten Zugangsdaten verglichen.
3. Bei ungültigen Zugangsdaten wird die Anmeldung abgelehnt.
4. Die Fehlermeldung verrät nicht, ob der Benutzername oder das Passwort falsch war.

**Ergebnis:**

- erfolgreiche Authentifizierung oder
- abgelehnte Anmeldung mit verständlicher Fehlermeldung

### AF-02 – Sitzung verwalten

**Zweck:**  
Den Anmeldestatus einer Person während der Nutzung von Reportify verwalten.

**Eingaben:**

- Ergebnis der erfolgreichen Anmeldung
- Abmeldeanforderung

**Verarbeitung:**

1. Nach erfolgreicher Anmeldung erstellt das System eine gültige Sitzung.
2. Geschützte Funktionen sind nur innerhalb einer gültigen Sitzung erreichbar.
3. Bei der Abmeldung beendet das System die Sitzung.
4. Nach der Abmeldung dürfen geschützte Seiten nicht mehr aufgerufen werden.

**Ergebnis:**

- aktive Sitzung nach der Anmeldung oder
- beendete Sitzung nach der Abmeldung

### AF-03 – Schichten bereitstellen

**Zweck:**  
Die auswählbaren Arbeitsschichten für einen Report bereitstellen.

**Auswahlwerte:**

- Frühschicht
- Spätschicht
- Nachtschicht

**Verarbeitung:**

1. Das System stellt die verfügbaren Schichten direkt im Reportformular zur Auswahl.
2. Die Person wählt die Schicht aus, der der neue Report zugeordnet werden soll.
3. Die ausgewählte Schicht wird dem Report zugeordnet.
4. Ohne gültige Schichtauswahl kann der Report nicht gespeichert werden.

**Ergebnis:**

- eine eindeutig ausgewählte Schicht

### AF-04 – Report-Eingaben validieren

**Zweck:**  
Prüfen, ob ein Report fachlich gültig und speicherbar ist.

**Eingaben:**

- ausgewählte Schicht
- erledigte Aufgaben
- offene Aufgaben
- Probleme oder Incidents
- Priorität
- wichtige Hinweise

**Validierungsregeln:**

1. Eine gültige Schicht muss ausgewählt sein.
2. Das Feld "Erledigte Aufgaben" muss einen Inhalt besitzen.
3. Reine Leerzeichen im Feld "Erledigte Aufgaben" gelten nicht als Inhalt.
4. Wenn ein Problem oder Incident eingetragen wurde, muss eine Priorität angegeben sein.
5. Die zulässigen Prioritäten sind `NIEDRIG`, `MITTEL` und `HOCH`.
6. Jedes Report-Textfeld darf maximal 4.000 Zeichen enthalten.
7. Überschreitet ein Report-Textfeld die maximale Länge, ist die Eingabe
   ungültig und darf nicht gespeichert werden.
8. Fehlerhafte Felder werden verständlich gekennzeichnet.

**Ergebnis:**

- gültige Report-Daten oder
- konkrete Validierungsfehler


### AF-05 – Report speichern

**Zweck:**  
Einen fachlich gültigen Report dauerhaft sichern.

**Vorbedingung:**

- AF-04 hat die Report-Daten erfolgreich validiert.

**Verarbeitung:**

1. Das System übernimmt die eingegebenen Report-Daten.
2. Das System ergänzt die erstellende Person.
3. Das System ergänzt Datum und Uhrzeit der Erstellung.
4. Der Report wird dauerhaft gespeichert.
5. Der gespeicherte Report steht anschließend für Übergabe und Historie zur Verfügung.

**Ergebnis:**

- ein eindeutig gespeicherter Report mit Erstellungszeitpunkt und Ersteller:in

### AF-06 – Aktuelle Übergabe bestimmen

**Zweck:**  
Den für die nächste Schicht aktuellsten Übergabereport bereitstellen.

**Eingaben:**

- gespeicherte Reports

**Verarbeitung:**

1. Das System ermittelt den zuletzt gespeicherten Report.
2. Der vollständige Inhalt dieses Reports wird als aktuelle Übergabe bereitgestellt.
3. Wenn noch kein Report existiert, wird ein verständlicher Leerzustand angezeigt.

**Ergebnis:**

- aktuellster Report oder
- Hinweis, dass noch keine Übergabe vorhanden ist

> **Fachliche Regel:** Als aktuelle Übergabe wird der zuletzt gespeicherte Report verwendet. Eine zusätzliche Schichtreihenfolge wird für diese Bestimmung nicht berücksichtigt.

### AF-07 – Report-Historie bereitstellen

**Zweck:**  
Alle gespeicherten Reports nachvollziehbar anzeigen.

**Eingaben:**

- gespeicherte Reports

**Verarbeitung:**

1. Das System lädt alle vorhandenen Reports.
2. Die Reports werden absteigend nach ihrem Erstellungszeitpunkt sortiert.
3. Der neueste Report steht als aktuelle Übergabe an erster Stelle.
4. Die übrigen Reports bilden die Report-Historie.
5. Die historischen Reports können anhand ihres Textinhalts durchsucht werden.
6. Die historischen Reports können nach Früh-, Spät- oder Nachtschicht gefiltert werden.
7. Textsuche und Schichtfilter können miteinander kombiniert werden.
8. Die aktuelle Übergabe wird durch Suche und Schichtfilter nicht ausgeblendet.
9. Jeder Eintrag zeigt mindestens Schicht, Zeitpunkt, Ersteller:in und Priorität.
10. Wenn keine historischen Reports den gewählten Kriterien entsprechen, wird ein verständlicher Hinweis angezeigt.

**Ergebnis:**

- aktuelle Übergabe und chronologisch sortierte Report-Historie,
- entsprechend Suchbegriff und/oder Schicht gefilterte historische Reports oder
- verständlicher Hinweis, wenn keine historischen Reports den gewählten Kriterien entsprechen

### AF-08 – Report-Details bereitstellen

**Zweck:**  
Den vollständigen Inhalt eines ausgewählten Reports anzeigen.

**Eingaben:**

- eindeutige Kennung des ausgewählten Reports

**Verarbeitung:**

1. Das System sucht den ausgewählten Report.
2. Bei vorhandenem Report werden alle fachlichen Inhalte angezeigt.
3. Existiert der Report nicht, wird eine verständliche Fehlermeldung angezeigt.

**Ergebnis:**

- vollständige Report-Details oder
- Meldung, dass der Report nicht gefunden wurde

### AF-09 - Gespeicherten Report bearbeiten 

**Zweck:** 
Einen bereits gespeicherten Report nachträglich ändern.

**Eingaben:**

- Kennung des Reports
- geänderte Report-Daten

**Verarbeitung:**

1. Das System lädt den zu bearbeitenden Report.
2. Die vorhandenen Report-Daten werden zur Bearbeitung bereitgestellt.
3. Die geänderten Daten werden gemäß AF-04 validiert.
4. Bei gültigen Eingaben werden die Änderungen zusammen mit Änderungszeitpunkt und ändernder Person dauerhaft gespeichert.
5. Bei ungültigen Eingaben wird der Report nicht geändert und die fehlerhaften Felder werden gekennzeichnet.

**Ergebnis:**

- aktualisierter Report mit Änderungszeitpunkt und ändernder Person oder
- konkrete Validierungsfehler

### AF-10 – Gespeicherten Report löschen

**Zweck:**  

Einen gespeicherten Report durch eine berechtigte Schichtleitung löschen.

**Eingaben:**

- Kennung des Reports
- angemeldete Person

**Verarbeitung:**

1. Das System prüft, ob der Report existiert.
2. Das System prüft, ob die angemeldete Person die Rolle `SCHICHTLEITUNG` besitzt.
3. Besitzt die Person nicht die erforderliche Rolle, wird die Löschung abgelehnt.
4. Die Löschung muss durch die Schichtleitung bestätigt werden.
5. Nach erfolgreicher Bestätigung wird der Report gelöscht.

**Ergebnis:**

- erfolgreich gelöschter Report oder
- Ablehnung der Löschung

### AF-11 – Persönliches Passwort erstmalig festlegen

**Zweck:**  
Einer Person ermöglichen, bei der ersten Anmeldung für ihr vorbereitetes
Benutzerkonto ein eigenes persönliches Passwort festzulegen.

**Eingaben:**

- neues Passwort
- Passwortbestätigung

**Verarbeitung:**

1. Das System prüft, ob für das Benutzerkonto eine erstmalige Passwortvergabe
   erforderlich ist.
2. Das System prüft, ob das neue Passwort mindestens 8 und höchstens
   128 Zeichen lang ist.
3. Das System prüft, ob Passwort und Passwortbestätigung übereinstimmen.
4. Bei ungültigen Eingaben wird das Passwort nicht übernommen.
5. Bei gültigen Eingaben wird das Passwort sicher verarbeitet und darf nicht
   als Klartext gespeichert werden.
6. Die erstmalige Passwortvergabe wird anschließend als abgeschlossen
   gekennzeichnet.

**Ergebnis:**

- erfolgreich festgelegtes persönliches Passwort oder
- verständlicher Validierungsfehler
### AF-12 – Report als erledigt kennzeichnen

**Zweck:**
Den Bearbeitungsstand eines gespeicherten Reports von `OFFEN` auf `ERLEDIGT`
setzen.

**Eingaben:**

- eindeutige Kennung des Reports
- angemeldete Person

**Verarbeitung:**

1. Das System sucht den ausgewählten Report.
2. Existiert der Report nicht, wird eine verständliche Fehlermeldung angezeigt.
3. Ein vorhandener offener Report wird als `ERLEDIGT` gekennzeichnet.
4. Der Report bleibt vollständig gespeichert und in der Historie sichtbar.
5. Ein bereits erledigter Report bleibt unverändert erledigt.

**Ergebnis:**

- als erledigt gekennzeichneter Report oder
- Meldung, dass der Report nicht gefunden wurde

## 4. Abgrenzung

### AF-13 – Druckansicht bereitstellen

**Zweck:**  
Die fachlichen Inhalte eines geöffneten Reports druckgeeignet darstellen.

**Eingaben:**

- der aktuell geöffnete Report

**Verarbeitung:**

1. Das System blendet Navigation, Meldungen und Bedienaktionen für den Druck aus.
2. Metadaten, Status, Priorität und vorhandene Reporttexte bleiben sichtbar.
3. Der native Druckdialog des Browsers wird geöffnet.

**Ergebnis:**

- druckoptimierte Ausgabe des Reports

Folgende Funktionen sind für die erste Version nicht vorgesehen:

- Datei- oder Bildanhänge
- automatische Benachrichtigungen
- Chat zwischen Mitarbeitenden
- externe Schnittstellen
- statistische Auswertungen

Änderungen an dieser Abgrenzung müssen zuerst in der Spezifikation dokumentiert
werden, bevor sie implementiert werden.

## 5. Nachverfolgbarkeit

Die Kennungen `AF-01` bis `AF-13` bleiben stabil. Sie werden in folgenden
Dokumenten wiederverwendet:

- Datenmodell
- Dialogspezifikation
- Architekturdokumentation
- Tests und Akzeptanzkriterien
