# Test- und Abnahmenachweis

> **Stand:** 25.09.2026

Dieses Dokument trennt automatisierte Tests von manuellen Oberflächenprüfungen.
Es dient als nachvollziehbarer Nachweis für den geprüften Stand der Version 1.

## 1. Automatisierte Tests

Ausführung im Verzeichnis `reportify`:

```bash
./mvnw test
```

Der geprüfte Stand umfasst **46 erfolgreiche Tests** und endet mit
`BUILD SUCCESS`. Abgedeckt sind insbesondere:

- Start des Spring-Kontexts,
- Login, Logout und Schutz nicht öffentlicher Seiten,
- erzwungener Passwortwechsel bei der ersten Anmeldung,
- Passwortregeln von 8 bis 128 Zeichen,
- Laden aktiver Benutzer und Abbildung ihrer Rollen,
- Anzeige, Erstellung, Bearbeitung, Statuswechsel und Löschung von Reports,
- fachliche Validierung einschließlich bedingter Priorität,
- Löschberechtigung ausschließlich für die Schichtleitung,
- CSRF-Schutz bei schreibenden Anfragen,
- verständliche Fehlerseiten ohne interne technische Details.

## 2. Manuelle Oberflächenprüfung

Die folgenden Fälle wurden am 25.09.2026 lokal in Safari und ergänzend in
Google Chrome geprüft:

| Prüffall | Ergebnis |
|---|---|
| Anmeldung als Mitarbeiter:in | Erfolgreich |
| Anmeldung als Schichtleitung | Erfolgreich |
| Persönliches Passwort bei Erstanmeldung festlegen | Erfolgreich |
| Dashboard, Kennzahlen und aktuelle Übergabe anzeigen | Erfolgreich |
| Report mit Schicht, Texten und Priorität erstellen | Erfolgreich |
| Reportdetails anzeigen und Report bearbeiten | Erfolgreich |
| Report als erledigt kennzeichnen | Erfolgreich |
| Historie nach Reporttext durchsuchen | Erfolgreich |
| Historie nach Schicht filtern und Filter zurücksetzen | Erfolgreich |
| Meldung bei leerem Suchergebnis | Erfolgreich |
| Mitarbeiter:in kann keinen Report löschen | Erfolgreich |
| Schichtleitung erhält Löschaktion mit Sicherheitsabfrage | Erfolgreich |
| Report drucken und über Safari als PDF sichern | Erfolgreich |
| Dashboard-Auswertung nach Status, Schicht und Priorität prüfen | Erfolgreich |
| Kernablauf in einer aktuellen Chrome-Version prüfen | Erfolgreich |
| Dashboard und mobiles Menü bei 360 Pixel Breite prüfen | Erfolgreich |
| Reportformular bei 360 Pixel Breite prüfen | Erfolgreich |
| Reportdetail und Aktionsbuttons bei 360 Pixel Breite prüfen | Erfolgreich |
| Login, Dashboard, Formular und Reportdetails per Tastatur bedienen | Erfolgreich |
| Löschdialog per Tastatur öffnen und ohne Löschung schließen | Erfolgreich |
| Abmelden | Erfolgreich |

## 3. Empfohlene zusätzliche Qualitätsprüfungen

Über den dokumentierten Abgabestand hinaus werden empfohlen:

- automatisierte Browsertests für Suche, Schichtfilter, Zeichenzähler,
  mobiles Menü und Löschdialog,
- Lastmessung, bevor konkrete Leistungsgrenzen zugesichert werden.

Diese zusätzlichen Prüfungen betreffen weiterführende Qualitätssicherung. Die in
Abschnitt 1 genannten automatisierten Tests und die in Abschnitt 2 genannten
Kernabläufe waren im geprüften Stand erfolgreich.
