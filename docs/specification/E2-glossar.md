# E2 – Glossar

> **Status:** Arbeitsentwurf vom 30.08.2026.  
> Die Begriffe sind mit dem vorliegenden Spezifikationsentwurf abgeglichen.
> Die fachliche Prüfung und Freigabe durch das Projektteam stehen noch aus.

## 1. Zweck und Verwendung

Dieses Glossar erklärt die in Reportify verwendeten Fachbegriffe. Es dient als
gemeinsame Grundlage für Spezifikation, Benutzeroberfläche, Architektur, Code und
Tests und unterstützt damit Ziel `Z-08` aus [P1](P1-ziele-rahmenbedingungen.md).

Die Verweise führen zu den Beschreibungen, aus denen die Begriffe abgeleitet sind.
Feldgrenzen und vollständige Abläufe werden dort gepflegt, nicht im Glossar
wiederholt. Technische Umsetzungsentscheidungen gehören in die Architektur.

Offene Regeln bleiben Arbeitsannahmen. Dieses Glossar ersetzt keine
Teamentscheidung; deren Status wird zentral in
[TEAM-ENTSCHEIDUNGEN.md](../TEAM-ENTSCHEIDUNGEN.md) geführt.

## 2. Schichtübergabe und Reports

### Reportify

Webanwendung zur Dokumentation und Anzeige digitaler Schichtübergaben.
Mitarbeitende erfassen den Arbeitsstand; die nachfolgende Schicht kann die
aktuelle Übergabe und frühere Reports einsehen.

Bezug: [P1 – Produktvision](P1-ziele-rahmenbedingungen.md).

### Schicht

Arbeitsschicht, auf die sich ein Report bezieht. Im vorliegenden Entwurf sind
Frühschicht, Spätschicht und Nachtschicht vorgesehen. Jeder Report ist genau einer
Schicht zugeordnet. Die Schichtbezeichnung allein legt weder ein Datum noch
konkrete Beginn- und Endzeiten fest.

Bezug: [D1 – Fachliche Wertetypen](D1-datenmodell.md),
[D2 – DT-09](D2-datentypen.md).

### Schichtübergabe

Geschäftsprozess, bei dem eine ausgehende Schicht ihren Arbeitsstand für die
nachfolgende Schicht dokumentiert. Reportify unterstützt die Weitergabe dieser
Informationen. Die Ausführung der betrieblichen Aufgaben findet außerhalb der
Anwendung statt.

Bezug: [F1 – Geschäftsprozesse](F1-geschaeftsprozesse.md).

### Report / Übergabereport

Gespeicherte Dokumentation des Arbeitsstands einer Schicht. Beide Bezeichnungen
meinen in Reportify dasselbe Datenobjekt. Ein Report enthält eine Schichtzuordnung,
fachliche Inhalte, eine erstellende Person und einen Erstellungszeitpunkt.

Noch nicht gespeicherte Formulareingaben sind kein gespeicherter Report. Ein
dauerhaft gespeicherter Entwurfsstatus ist im aktuellen Datenmodell nicht vorgesehen.

Bezug: [D1 – Report und Lebenszyklus](D1-datenmodell.md),
[F3 – AF-05](F3-anwendungsfunktionen.md).

### Fachliche Textfelder

Die vier Inhaltsbereiche eines Reports:

- **Erledigte Aufgaben:** während der Schicht abgeschlossene Aufgaben.
- **Offene Aufgaben:** noch nicht abgeschlossene Aufgaben, über die die
  nachfolgende Schicht informiert werden soll.
- **Probleme/Incidents:** Störungen oder Probleme, die für die Übergabe relevant sind.
- **Wichtige Hinweise:** weitere für die nachfolgende Schicht benötigte Informationen.

Aufgaben und Probleme werden im aktuellen Datenmodell als Report-Texte erfasst,
nicht als eigenständig verwaltete Aufgaben- oder Incidentobjekte.

Bezug: [D1 – Report-Attribute](D1-datenmodell.md),
[D2 – DT-11](D2-datentypen.md), [B1 – DLG-03](B1-dialogspezifikationen.md).

### Incident

Eine für die Schichtübergabe relevante Störung beziehungsweise ein Vorfall im
Arbeitsablauf. Probleme und Incidents werden im MVP gemeinsam im Feld
„Probleme/Incidents“ beschrieben. Eine getrennte fachliche Klassifikation wird
im vorliegenden Entwurf nicht festgelegt.

Bezug: [D1 – Report-Attribute](D1-datenmodell.md),
[B1 – DLG-03](B1-dialogspezifikationen.md).

### Priorität

Kennzeichnung der Dringlichkeit mit den Werten Niedrig, Mittel oder Hoch.
**Arbeitsannahme nach TD-002:** Die Priorität bezieht sich auf die im Report
beschriebenen Probleme oder Incidents und ist bei einem solchen Eintrag
verpflichtend. Ob sie stattdessen für den gesamten Report gelten soll, ist offen.

Diese Priorität ist von der Gewichtung einer Anforderung zu unterscheiden:
Die Kategorien A, B und C in N1 bewerten Anforderungen an Reportify, keine Reports.

Bezug: [D2 – DT-10](D2-datentypen.md), [N1 – Prioritäten](N1-nichtfunktional.md),
[TD-002](../TEAM-ENTSCHEIDUNGEN.md).

### Aktuelle Übergabe / letzte Übergabe

Der Report, den Reportify beim Aufruf der Übergabeansicht als aktuellen
Informationsstand bereitstellt. **Arbeitsannahme nach TD-005:** Maßgeblich ist
der zuletzt gespeicherte Report anhand seines Erstellungszeitpunkts.
Ob zusätzlich die Reihenfolge der Schichten berücksichtigt werden soll, ist offen.

Die aktuelle Übergabe ist eine Ansicht eines Reports, kein zusätzliches Datenobjekt.
Existiert noch kein Report, wird ein erklärender Hinweis angezeigt.

Bezug: [F3 – AF-06](F3-anwendungsfunktionen.md),
[B1 – DLG-04](B1-dialogspezifikationen.md), [TD-005](../TEAM-ENTSCHEIDUNGEN.md).

### Report-Historie

Übersicht der gespeicherten Reports in absteigender Reihenfolge ihres
Erstellungszeitpunkts. Sie enthält auch den neuesten Report. Aus der Übersicht
kann ein einzelner Report zur vollständigen Anzeige ausgewählt werden.

Bezug: [F2 – UC-06](F2-anwendungsfaelle.md),
[F3 – AF-07](F3-anwendungsfunktionen.md), [B1 – DLG-05](B1-dialogspezifikationen.md).

### Report-Details / Detailansicht

Vollständige Anzeige eines bestimmten gespeicherten Reports. Anders als die
aktuelle Übergabe kann sie auch einen älteren Report zeigen. Das Öffnen der
Detailansicht verändert den Report nicht. In F2 ist der Aufruf aus der Historie
bereits Bestandteil von `UC-06`.

Bezug: [F2 – UC-06](F2-anwendungsfaelle.md),
[F3 – AF-08](F3-anwendungsfunktionen.md), [B1 – DLG-06](B1-dialogspezifikationen.md).

### Ersteller:in / Autor:in

Die angemeldete Person, der Reportify einen Report beim Speichern zuordnet.
Beide Bezeichnungen meinen dieselbe Verantwortung für die Erstellung. In der
Benutzeroberfläche wird ihr Anzeigename verwendet.

Bezug: [F1 – GR-03](F1-geschaeftsprozesse.md), [D1 – Report](D1-datenmodell.md).

### Erstellungszeitpunkt

Datum und Uhrzeit der Speicherung eines Reports. Reportify vergibt diesen
Zeitpunkt automatisch. Er bezeichnet weder den Beginn der Schicht noch den
Zeitpunkt, zu dem eine beschriebene Aufgabe ausgeführt wurde.

Bezug: [F1 – GR-04](F1-geschaeftsprozesse.md), [D2 – DT-12](D2-datentypen.md).

## 3. Personen und Zugriff

### Nutzer:in und Benutzerkonto

Eine Nutzerin oder ein Nutzer ist eine zur Verwendung von Reportify berechtigte
Person. Das Benutzerkonto enthält die zur Anmeldung und Zuordnung benötigten
Angaben. Im Datenmodell werden diese als Datenobjekt „Nutzer:in“ beschrieben.

Ein aktives Konto darf sich anmelden; es bedeutet nicht, dass die Person bereits
angemeldet ist. **Arbeitsannahme nach TD-008:** Konten werden vorbereitet, eine
Selbstregistrierung über Reportify ist nicht vorgesehen.

Bezug: [D1 – Nutzer:in](D1-datenmodell.md), [D2 – DT-08](D2-datentypen.md),
[TD-008](../TEAM-ENTSCHEIDUNGEN.md).

### Benutzername und Anzeigename

Der **Benutzername** identifiziert das Konto bei der Anmeldung und muss eindeutig
sein. Der **Anzeigename** ist der lesbare Name, der unter anderem in Reports und
in der Historie erscheint. Anzeigenamen müssen nicht eindeutig sein und werden
nicht zur Anmeldung verwendet.

Bezug: [D2 – DT-03 und DT-04](D2-datentypen.md).

### Fachliche Rolle

Zuordnung eines Benutzerkontos zu Mitarbeiter:in oder Schichtleitung.
**Arbeitsannahme nach TD-006:** Beide Rollen können dieselben Kernfunktionen
verwenden. Zusätzliche Berechtigungen der Schichtleitung sind noch nicht beschlossen.

Die fachliche Rolle ist von einer Projektrolle im Entwicklungsteam zu unterscheiden.
Beispielsweise ist die Projektleitung aus TEAMINFO keine Rolle innerhalb von
Reportify. Die Verteilung der Projektrollen wird über `TD-010` abgestimmt.

Bezug: [D2 – DT-07](D2-datentypen.md),
[TD-006 und TD-010](../TEAM-ENTSCHEIDUNGEN.md).

### Anmeldung / Authentifizierung

Prüfung, ob eingegebener Benutzername und Passwort zu einem berechtigten Konto
gehören. Eine erfolgreiche Anmeldung führt zu einer gültigen Sitzung.
Welche Funktionen anschließend erlaubt sind, ist eine Frage der Berechtigung.

Bezug: [F2 – UC-01](F2-anwendungsfaelle.md),
[F3 – AF-01 und AF-02](F3-anwendungsfunktionen.md).

### Sitzung und Abmeldung

Eine Sitzung hält den Anmeldestatus einer Person während der Nutzung fest.
Geschützte Report-Funktionen benötigen eine gültige Sitzung. Die Abmeldung
beendet diese Sitzung; gespeicherte Reports bleiben unverändert.

Bezug: [F2 – UC-02](F2-anwendungsfaelle.md), [F3 – AF-02](F3-anwendungsfunktionen.md).

### Passwortnachweis

Sicher gespeicherter Wert, mit dem ein eingegebenes Passwort überprüft werden
kann. Der Passwortnachweis ist kein gespeichertes Klartextpasswort und wird
Nutzer:innen nicht angezeigt. Das konkrete Verfahren wird in der Architektur
beschrieben.

Bezug: [D2 – DT-06](D2-datentypen.md), [N1 – NFR-15b-01](N1-nichtfunktional.md).

## 4. Eingaben und Rückmeldungen

### Pflichtangabe / bedingte Pflichtangabe

Eine Pflichtangabe muss für einen gültigen Vorgang vorhanden sein. Eine bedingte
Pflicht hängt von anderen Eingaben ab. **Arbeitsannahme nach TD-001:** Beim
Report sind eine Schicht und Inhalt in mindestens einem der vier fachlichen
Textfelder erforderlich. Nicht jedes Textfeld muss einzeln ausgefüllt sein.
Die bedingte Pflicht einer Priorität gehört zu `TD-002`.

Bezug: [F3 – AF-04](F3-anwendungsfunktionen.md),
[TD-001 und TD-002](../TEAM-ENTSCHEIDUNGEN.md).

### Validierung / Validierungsfehler

Prüfung der Eingaben auf die festgelegten Feld- und Datenregeln. Ein
Validierungsfehler liegt beispielsweise vor, wenn die erforderliche Schicht fehlt.
Ein ungültiger Report wird nicht gespeichert; die betroffene Eingabe wird
verständlich gekennzeichnet. Die Validierung bestätigt nicht die sachliche
Richtigkeit der beschriebenen betrieblichen Ereignisse.

Bezug: [F3 – AF-04](F3-anwendungsfunktionen.md),
[B1 – DLG-03](B1-dialogspezifikationen.md).

### Leerzustand

Regulärer Anzeigezustand, wenn noch keine Reports für Übergabe oder Historie
vorliegen. Ein verständlicher Hinweis erklärt diesen Zustand. Er ist von einem
technischen Fehler oder dem Aufruf einer nicht vorhandenen Report-Kennung zu
unterscheiden.

Bezug: [F3 – AF-06 bis AF-08](F3-anwendungsfunktionen.md),
[B1 – DLG-04 bis DLG-06](B1-dialogspezifikationen.md).

## 5. Begriffe und Kennungen der Dokumentation

| Begriff / Kennung | Bedeutung und Verwendung in Reportify |
|---|---|
| MVP | Minimum Viable Product; vereinbarter Mindestfunktionsumfang der ersten Version. Report-Details sind über UC-06, AF-08 und DLG-06 abgedeckt. |
| Arbeitsannahme | Vorläufige Grundlage für einen Entwurf; keine bestätigte Teamentscheidung. |
| Z | Projektziel aus P1, beispielsweise Z-08 für einheitliche Fachbegriffe. |
| GR | Geschäftsregel aus F1, beispielsweise GR-02 für die Schichtzuordnung. |
| UC | Use Case; Anwendungsfall aus Sicht einer nutzenden Person. In F2 werden UC-01 bis UC-06 beschrieben. |
| AF | Anwendungsfunktion; fachliche Leistung des Systems zur Umsetzung eines oder mehrerer Anwendungsfälle. In F3 werden AF-01 bis AF-08 beschrieben. |
| AK | Akzeptanzkriterium; überprüfbare Bedingung für die Erfüllung einer Anforderung. Die Kennungen AK-01 bis AK-20 werden in F2 verwendet. |
| DM | Fachliche Datenregel aus D1, beispielsweise DM-02 für die Zuordnung eines Reports zu seiner erstellenden Person. |
| DT | Datentyp; beschreibt zulässige fachliche Werte und Regeln. D2 enthält DT-01 bis DT-12. |
| DLG | Dialog; fachlich beschriebene Seite beziehungsweise Interaktionsansicht. B1 enthält DLG-01 bis DLG-06. |
| NFR | Non-functional Requirement; Qualitätsanforderung aus N1 mit überprüfbaren Akzeptanzkriterien, etwa zur Zuverlässigkeit oder Bedienbarkeit. |
| TD | Teamentscheidung; zentral geführter Abstimmungspunkt. Eine TD-Kennung allein bedeutet nicht, dass bereits entschieden wurde. |
| ADR | Architecture Decision Record; Dokumentation einer Architekturentscheidung mit Alternativen und Begründung. ADR-Kennungen sind für die spätere Architektur vorgesehen. |
| Nachverfolgbarkeit | Verknüpfung von Zielen, Anforderungen, Daten, Dialogen, Architektur, Code und Tests über eindeutige Verweise. |

Bei späteren Ergänzungen bleiben bereits vergebene Kennungen erhalten.
Geänderte Teamentscheidungen werden zuerst zentral dokumentiert und anschließend
in den betroffenen Spezifikationskapiteln und Glossarbegriffen nachgeführt.