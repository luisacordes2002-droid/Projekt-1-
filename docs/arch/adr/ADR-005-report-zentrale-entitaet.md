# ADR-005: Report als zentrale fachliche Entität

## Status

Proposed

## Kontext

Reportify soll die digitale Dokumentation und Weitergabe von Informationen im Arbeitsalltag unterstützen. Ein wesentlicher Bestandteil der Anwendung sind Berichte, in denen relevante Informationen einer Schicht erfasst und für andere Mitarbeitende beziehungsweise nachfolgende Schichten verfügbar gemacht werden können.

Für die weitere Entwicklung muss festgelegt werden, welches fachliche Konzept den zentralen Ausgangspunkt des Domänenmodells bildet.

Nach dem derzeitigen fachlichen Verständnis bietet sich der Report als zentrale Entität an. Andere fachliche Konzepte, beispielsweise Aufgaben oder Informationen für Schichtübergaben, stehen in engem Zusammenhang mit den erfassten Berichten.

Das endgültige Datenmodell der Spezifikation ist derzeit jedoch noch nicht vollständig ausgearbeitet. Ebenso existiert im aktuellen Quellcode noch keine implementierte `Report`-Entität. Aus diesem Grund wird diese Architekturentscheidung zunächst als `Proposed` dokumentiert.

## Betrachtete Alternativen

### Task als zentrale Entität

Die Anwendung könnte hauptsächlich um Aufgaben und deren Bearbeitungsstatus aufgebaut werden.

**Vorteile:**

- Aufgabenverwaltung würde unmittelbar im Mittelpunkt stehen
- Statusänderungen könnten einfach abgebildet werden
- geeignet für eine hauptsächlich auf Task-Management ausgerichtete Anwendung

**Nachteile:**

- allgemeine Informationen einer Schicht lassen sich nicht immer sinnvoll als Aufgabe darstellen
- der Dokumentationscharakter von Reportify würde weniger deutlich abgebildet
- Berichte und Schichtinformationen müssten zusätzlich modelliert werden

### Shift als zentrale Entität

Alternativ könnte eine Arbeitsschicht den zentralen Ausgangspunkt des Datenmodells bilden.

**Vorteile:**

- Informationen könnten unmittelbar einer Schicht zugeordnet werden
- Schichtübergaben ließen sich fachlich direkt darstellen

**Nachteile:**

- stärkere Abhängigkeit von einer konkreten Schichtplanung
- Berichte wären nur ein untergeordnetes Element
- zusätzliche Modellierung von Schichten, Zeiträumen und Zuordnungen wäre notwendig

### Report als zentrale Entität

Ein Report bildet eine dokumentierte Information beziehungsweise Zusammenfassung aus dem Arbeitskontext ab.

**Vorteile:**

- entspricht dem grundlegenden Zweck von Reportify
- Informationen können strukturiert dokumentiert werden
- Berichte können für Schichtübergaben verwendet werden
- weitere fachliche Konzepte können mit Reports verbunden werden
- bietet einen verständlichen Ausgangspunkt für das Domänenmodell

**Nachteile:**

- Beziehungen zu Aufgaben, Benutzern und gegebenenfalls Schichten müssen eindeutig definiert werden
- die genaue Struktur eines Reports hängt von der noch zu vervollständigenden fachlichen Spezifikation ab
- eine zu starke Zentralisierung könnte später zu einer überladenen Entität führen

## Entscheidung

Für das weitere Architektur- und Datenmodelldesign soll **Report als zentrale fachliche Entität von Reportify** verwendet werden.

Ein Report repräsentiert dabei eine dokumentierte Information aus dem Arbeitskontext und bildet einen zentralen Bestandteil für die Informationsweitergabe innerhalb der Anwendung.

Die endgültigen Attribute und Beziehungen der Entität werden erst gemeinsam mit dem Datenmodell und den konkretisierten Anwendungsfällen festgelegt.

Eine mögliche fachliche Struktur ist:

```text
User
  |
  | erstellt
  v
Report
  |
  | kann relevante Informationen enthalten oder referenzieren
  v
Task