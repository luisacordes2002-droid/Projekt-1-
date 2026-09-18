# B2 – Batch

> **Status:** Arbeitsentwurf vom 30.08.2026. Die Prüfung durch das Projektteam steht aus.  
> **Anwendbarkeit:** Nicht anwendbar im festgelegten MVP.

## 1. Einordnung

Dieser Baustein betrachtet fachliche Stapelverarbeitung: Mehrere Datensätze werden
in einem gemeinsamen Verarbeitungslauf bearbeitet, ohne jeden Datensatz einzeln
über einen Dialog zu erfassen oder zu bestätigen.

Für Reportify ist im MVP keine solche Funktion vorgesehen. Reports werden einzeln
über das Reportformular erfasst und nach Prüfung der Eingaben gespeichert.
Sammelimporte, Massenänderungen und eine zeitgesteuerte Erstellung von Reports
gehören nicht zum festgelegten Funktionsumfang.

Das Anzeigen und Sortieren mehrerer Reports in der Historie ist Bestandteil von
`UC-06` und `AF-07`. Diese interaktive Anzeige begründet keine zusätzliche
fachliche Batchfunktion.

## 2. Begründung und Abgrenzung

Der MVP unterstützt die unmittelbare Dokumentation und das Einsehen einer
Schichtübergabe. Dafür sind die beschriebenen interaktiven Anwendungsfälle ausreichend.
Es wird daher kein eigener Batchablauf spezifiziert oder für die Abnahme gefordert.

Diese Einordnung trifft keine Festlegung über interne technische Verarbeitungsweisen.
Eine spätere fachliche Batchfunktion müsste zuerst als Erweiterung des Umfangs
abgestimmt und in Spezifikation, Architektur und Tests aufgenommen werden.

## 3. Bezug

- [P1 – Ziele und Rahmenbedingungen](P1-ziele-rahmenbedingungen.md)
- [F2 – Anwendungsfälle](F2-anwendungsfaelle.md)
- [F3 – Anwendungsfunktionen](F3-anwendungsfunktionen.md)