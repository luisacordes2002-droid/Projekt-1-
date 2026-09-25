# N2 – Querschnittskonzepte

> **Status:** Aktualisierter Stand vom 25.09.2026.
> Dieses Kapitel fasst fachliche Regeln zusammen, die mehrere Funktionen und
> Dialoge von Reportify betreffen. Detaillierte Definitionen verbleiben in den
> jeweils zuständigen Spezifikationskapiteln.

## 1. Zweck

Querschnittskonzepte beschreiben Regeln, die nicht nur für einen einzelnen
Anwendungsfall gelten, sondern an mehreren Stellen von Reportify berücksichtigt
werden müssen.

Für die erste Version betrifft dies insbesondere:

- Anmeldung und Zugriff,
- Rollen und Berechtigungen,
- Validierung von Eingaben,
- Fehler- und Rückmeldungen,
- Umgang mit gespeicherten Reports,
- Nachvollziehbarkeit von Änderungen.

Dieses Kapitel führt die übergreifenden Regeln zusammen. Es ersetzt nicht die
detaillierten Anforderungen in den fachlichen Spezifikationskapiteln und nicht
die technische Umsetzung in der Architekturdokumentation.

## 2. Anmeldung und Zugriff

Die fachlichen Funktionen von Reportify stehen nur angemeldeten Nutzer:innen
zur Verfügung.

Für die erste Version werden Benutzerkonten vorbereitet bereitgestellt. Eine
Selbstregistrierung ist nicht vorgesehen.

Die Anmeldung erfolgt mit den für das jeweilige Benutzerkonto vorgesehenen
Anmeldedaten. Nach erfolgreicher Anmeldung kann die Person die für ihre Rolle
freigegebenen Funktionen verwenden.

Nicht angemeldete Personen dürfen nicht auf geschützte Funktionen oder
Report-Daten zugreifen.

Die konkreten Abläufe für Anmeldung und Abmeldung werden in
[F2 – Anwendungsfälle](F2-anwendungsfaelle.md) beschrieben.

## 3. Rollen und Berechtigungen

Reportify unterscheidet in der ersten Version die Rollen:

- `MITARBEITER`
- `SCHICHTLEITUNG`

Beide Rollen dürfen die allgemeinen Funktionen der digitalen Schichtübergabe
verwenden. Dazu gehören insbesondere das Erstellen und Anzeigen von Reports
sowie die Nutzung der Report-Historie.

Gespeicherte Reports dürfen entsprechend den festgelegten fachlichen Regeln
bearbeitet werden.

Das Löschen gespeicherter Reports ist ausschließlich Nutzer:innen mit der
Rolle `SCHICHTLEITUNG` erlaubt.

Versucht eine Person ohne diese Berechtigung, einen Report zu löschen, führt
Reportify die Löschung nicht durch. Der Report bleibt unverändert gespeichert.

Eine weitergehende oder komplexere Rollenverwaltung ist für die erste Version
nicht vorgesehen.

Die fachlichen Rollen und Geschäftsregeln werden insbesondere in
[P1 – Ziele und Rahmenbedingungen](P1-ziele-rahmenbedingungen.md) und
[F1 – Geschäftsprozesse](F1-geschaeftsprozesse.md) konkretisiert.

## 4. Validierung von Eingaben

Eingaben werden vor einer fachlich wirksamen Speicherung geprüft.

Für Reports gelten die in den fachlichen Datenregeln festgelegten Pflichtfelder,
zulässigen Werte und Längenbeschränkungen.

Insbesondere gelten folgende übergreifende Regeln:

- die Schicht muss angegeben werden,
- die erledigten Aufgaben müssen angegeben werden,
- bei einem erfassten Problem beziehungsweise Incident muss eine Priorität
  angegeben werden,
- jedes Report-Textfeld darf maximal 4.000 Zeichen enthalten,
- unzulässige oder unvollständige Eingaben dürfen nicht als gültiger Report
  gespeichert werden.

Die detaillierten Datentypen, Wertebereiche und Validierungsregeln werden in
[D2 – Datentypenverzeichnis](D2-datentypen.md) festgelegt.

Die Validierungsregeln gelten sowohl beim erstmaligen Erstellen als auch bei
der Bearbeitung eines bereits gespeicherten Reports.

## 5. Rückmeldungen bei ungültigen Eingaben

Kann eine Eingabe aufgrund einer fachlichen Validierungsregel nicht verarbeitet
werden, erhält die nutzende Person eine verständliche Rückmeldung.

Die Rückmeldung soll erkennen lassen, welche Eingabe fehlt oder nicht den
fachlichen Regeln entspricht.

Bereits eingegebene fachlich verwendbare Werte sollen bei einem
Validierungsfehler soweit möglich erhalten bleiben, damit die Eingabe
korrigiert werden kann.

Ein Validierungsfehler darf nicht dazu führen, dass ein ungültiger Report als
gültiger Report gespeichert wird.

Die dialogbezogene Darstellung der Rückmeldungen wird in
[B1 – Dialogspezifikation](B1-dialogspezifikationen.md) konkretisiert.

## 6. Fehlerbehandlung

Reportify unterscheidet fachlich zwischen erwartbaren Zuständen und Fehlern.

Ein erwartbarer Zustand liegt beispielsweise vor, wenn noch kein Report
gespeichert wurde oder eine Report-Historie noch leer ist. In diesem Fall soll
die Anwendung eine verständliche Information anzeigen und darf nicht den
Eindruck eines technischen Fehlers vermitteln.

Kann eine angeforderte Aktion nicht ausgeführt werden, muss die Anwendung eine
verständliche Rückmeldung geben und darf keine fachlich unzulässige Änderung
durchführen.

Dies gilt insbesondere bei:

- ungültigen oder unvollständigen Eingaben,
- fehlender Berechtigung für eine Aktion,
- nicht vorhandenen oder nicht mehr verfügbaren Daten.

Interne technische Details oder für Nutzer:innen nicht hilfreiche
Fehlerinformationen sind nicht Bestandteil der fachlichen Rückmeldung.

Die technische Behandlung unerwarteter Systemfehler wird in der
Architekturdokumentation konkretisiert.

## 7. Speicherung und aktuelle Übergabe

Ein Report steht für Anzeige- und Historienfunktionen zur Verfügung, nachdem er
gültig gespeichert wurde.

Ältere Reports bleiben beim Speichern eines neuen Reports erhalten.

Der zuletzt gespeicherte Report wird als aktuelle Übergabe angezeigt.

Für die erste Version findet keine automatische Löschung gespeicherter Reports
aufgrund einer Aufbewahrungsfrist statt.

Eine Löschung erfolgt nur durch eine ausdrücklich ausgelöste Löschaktion und
nur, wenn die ausführende Person die dafür erforderliche Rolle
`SCHICHTLEITUNG` besitzt.

Die fachlichen Regeln zum Lebenszyklus eines Reports werden in
[F1 – Geschäftsprozesse](F1-geschaeftsprozesse.md) und
[D1 – Datenmodell](D1-datenmodell.md) konkretisiert.

## 8. Bearbeitung und Nachvollziehbarkeit

Gespeicherte Reports dürfen nachträglich bearbeitet werden.

Für eine Bearbeitung gelten weiterhin die fachlichen Validierungsregeln.
Eine Änderung darf daher nicht dazu führen, dass ein Report anschließend einen
fachlich ungültigen Zustand besitzt.

Für Änderungen an gespeicherten Reports ist eine Nachvollziehbarkeit
vorgesehen. Bei einer erfolgreichen Bearbeitung werden der Zeitpunkt der
letzten Änderung und die Person gespeichert, die die Änderung durchgeführt hat.

Der ursprüngliche Erstellungszeitpunkt und die erstellende Person bleiben
erhalten.

Eine vollständige Versionshistorie aller früheren Bearbeitungsstände ist für
die erste Version nicht vorgesehen.

## 9. Benutzerkonten und Passwörter

Benutzerkonten werden für die erste Version vorbereitet bereitgestellt.
Eine Selbstregistrierung gehört nicht zum vorgesehenen Funktionsumfang.

Bei der ersten Anmeldung legt die nutzende Person für das vorbereitete
Benutzerkonto ein eigenes persönliches Passwort fest. Das Passwort muss
mindestens 8 und darf höchstens 128 Zeichen lang sein.

Die fachlichen Regeln für Benutzerkonten und die zugehörigen Datentypen werden in
[D1 – Datenmodell](D1-datenmodell.md) und
[D2 – Datentypenverzeichnis](D2-datentypen.md) beschrieben.

Technische Sicherheitsmaßnahmen zur Speicherung und Verarbeitung von
Passwörtern sind Bestandteil der technischen Architektur und werden in diesem
fachlichen Kapitel nicht doppelt spezifiziert.

## 10. Sprache und Darstellung

Die Benutzeroberfläche ist in Version 1 vollständig für eine Verwendung in
deutscher Sprache vorgesehen.

Felder, Meldungen und Aktionen werden einheitlich und verständlich auf Deutsch
dargestellt. Mehrsprachigkeit kann in einer späteren Version ergänzt werden.

Die konkreten Dialoge und sichtbaren Rückmeldungen werden in
[B1 – Dialogspezifikation](B1-dialogspezifikationen.md) beschrieben.

Anforderungen an unterstützte Browser und Bildschirmbreiten werden in
[N1 – Nichtfunktionale Anforderungen](N1-nichtfunktional.md) festgelegt.

## 11. Abgrenzung zur technischen Architektur

N2 beschreibt fachliche Querschnittsregeln.

Nicht Bestandteil dieses Kapitels sind konkrete technische
Implementierungsentscheidungen, beispielsweise:

- konkrete Mechanismen zur Sitzungsverwaltung,
- technische Autorisierungsmechanismen,
- Passwort-Hashing und technische Sicherheitskonfiguration,
- konkrete Exception- oder Fehlerklassen,
- Datenbanktransaktionen,
- technische Protokollierung,
- Framework-spezifische Validierungsmechanismen.

Diese Aspekte werden in der Architekturdokumentation beschrieben und müssen die
hier festgelegten fachlichen Regeln technisch umsetzen.

## 12. Ergebnis

N2 ist für Reportify anwendbar, weil mehrere fachliche Regeln über verschiedene
Anwendungsfälle, Dialoge und Datenobjekte hinweg gelten.

Die zentralen Querschnittskonzepte der ersten Version betreffen Anmeldung und
Zugriff, Rollen und Berechtigungen, Validierung, Fehlerbehandlung, Speicherung
und den Umgang mit Änderungen.

Detaillierte fachliche Definitionen verbleiben in den jeweils zuständigen
Spezifikationskapiteln. N2 dient als gemeinsame Übersicht und stellt die
Verbindungen zwischen diesen Anforderungen her.
