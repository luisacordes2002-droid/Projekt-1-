# S2 – Datenmigration

> **Status:** Mit dem Umfang der Version 1 abgeglichener Stand vom 25.09.2026.
> Eine Migration von Daten aus einem bestehenden Altsystem ist für die erste
> Version von Reportify nicht vorgesehen.

## 1. Zweck

Dieses Kapitel beschreibt, ob für die Inbetriebnahme von Reportify bestehende
Daten aus einem Altsystem übernommen werden müssen und grenzt eine solche
Datenmigration von der erstmaligen Bereitstellung benötigter Ausgangsdaten ab.

Für die erste Version von Reportify existiert kein fachliches Altsystem, aus
dem Reports oder andere bestehende Fachdaten übernommen werden müssen.

Eine klassische Datenmigration ist daher für den aktuellen Umfang nicht
anwendbar.

## 2. Keine Übernahme von Altdaten

Reportify wird im Rahmen der ersten Version als neues System bereitgestellt.

Es werden keine bestehenden Report-Daten aus anderen Anwendungen,
Dateien oder Datenbanken übernommen. Insbesondere werden keine historischen
Schichtübergaben, Aufgaben, Probleme, Incidents oder Hinweise importiert.

Die Report-Historie entsteht ausschließlich aus Reports, die innerhalb von
Reportify erstellt und gespeichert wurden.

Beim erstmaligen Einsatz kann die Report-Historie daher leer sein. In diesem
Fall zeigt Reportify einen verständlichen Hinweis, dass noch kein Report
vorhanden ist.

## 3. Abgrenzung zu vorbereiteten Benutzerkonten

Die erstmalige Bereitstellung der für die Anmeldung benötigten
Benutzerkonten ist keine Datenmigration.

Für die erste Version werden Benutzerkonten vorbereitet bereitgestellt.
Eine Selbstregistrierung durch Nutzer:innen ist nicht vorgesehen.

Die vorbereiteten Konten enthalten die für die Anmeldung und
Berechtigungsprüfung benötigten Informationen. Jedem Benutzerkonto wird eine
der vorgesehenen Rollen zugeordnet:

- `MITARBEITER`
- `SCHICHTLEITUNG`

Beide Rollen dürfen die allgemeinen Funktionen von Reportify verwenden.
Die Rolle `SCHICHTLEITUNG` besitzt zusätzlich die Berechtigung,
gespeicherte Reports zu löschen.

Die Benutzerkonten werden nicht aus einem externen Personal-,
Benutzerverwaltungs- oder Identitätssystem migriert. Dies entspricht der
Abgrenzung zu externen Nachbarsystemen in [S1 – Nachbarsysteme](S1-nachbarsysteme.md).

## 4. Ausgangszustand beim ersten Einsatz

Beim erstmaligen Einsatz von Reportify gelten folgende fachliche
Ausgangsbedingungen:

- die für die Nutzung vorgesehenen Benutzerkonten sind vorbereitet,
- den Benutzerkonten ist jeweils eine gültige Rolle zugeordnet,
- eine Selbstregistrierung ist nicht erforderlich und nicht vorgesehen,
- es müssen keine bestehenden Reports übernommen werden,
- die Report-Historie darf zu Beginn leer sein.

Sobald der erste gültige Report in Reportify gespeichert wurde, steht dieser
für die vorgesehenen Anzeige- und Historienfunktionen zur Verfügung.

Der zuletzt gespeicherte Report wird als aktuelle Übergabe angezeigt.

## 5. Keine automatische Erzeugung von Altdaten

Reportify erzeugt bei der erstmaligen Bereitstellung keine künstlichen
historischen Reports, um eine bestehende Historie zu simulieren.

Test- oder Beispieldaten, die ausschließlich für Entwicklung oder Tests
verwendet werden, sind keine fachlich migrierten Produktivdaten und werden
nicht als Bestandteil einer Datenmigration betrachtet.

Gespeicherte Reports werden in der ersten Version außerdem nicht aufgrund
einer Migrations- oder Aufbewahrungsregel automatisch gelöscht.

## 6. Spätere Datenmigration

Soll Reportify zukünftig in einer Umgebung eingesetzt werden, in der bereits
fachlich relevante Daten vorhanden sind, muss eine Datenmigration gesondert
spezifiziert werden.

Dabei wären insbesondere Herkunft, Datenformat, Zuordnung zu den
Reportify-Datentypen, Validierung, Vollständigkeit und der Umgang mit
fehlerhaften oder nicht übernehmbaren Daten festzulegen.

Eine solche Migration gehört nicht zum Umfang der ersten Version.

## 7. Ergebnis

Eine Migration bestehender Fachdaten ist für die erste Version von Reportify
nicht erforderlich.

S2 ist daher hinsichtlich einer klassischen Altdatenmigration nicht
anwendbar. Die erstmalige Bereitstellung vorbereiteter Benutzerkonten ist
davon ausdrücklich zu unterscheiden und stellt keine Migration aus einem
Altsystem dar.

Die fachlichen Eigenschaften der Benutzerkonten und Reports werden in
[D1 – Datenmodell](D1-datenmodell.md) beschrieben. Die Abgrenzung zu externen
Systemen erfolgt in [S1 – Nachbarsysteme](S1-nachbarsysteme.md).
