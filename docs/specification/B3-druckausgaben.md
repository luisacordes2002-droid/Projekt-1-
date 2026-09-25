# B3 – Druckausgaben

> **Status:** Als optionale Erweiterung umgesetzt und mit der Benutzeroberfläche
> abgeglichen am 25.09.2026.  
> **Anwendbarkeit:** Zusatzfunktion außerhalb des ursprünglichen Minimalumfangs.

## 1. Einordnung

Reportify stellt Schichtinformationen innerhalb der Webanwendung bereit.
Der Begriff Report bezeichnet weiterhin die gespeicherte Schichtübergabe. Die
Druckfunktion erzeugt keine zusätzliche fachliche Report-Entity und verändert
keine gespeicherten Daten.

Die vorgesehenen Ausgaben sind die aktuelle Übergabe, die Report-Historie und die
Report-Details. Sie werden in `DLG-04`, `DLG-05` und `DLG-06` beschrieben.

## 2. Druck- und PDF-Funktion

Auf der Detailseite eines Reports steht die Aktion „Drucken / als PDF speichern“
zur Verfügung. Sie öffnet den nativen Druckdialog des Browsers. Abhängig vom
Betriebssystem und Browser kann dort ein physischer Drucker gewählt oder die
Ausgabe als PDF gespeichert werden.

Das Drucklayout:

- enthält Titel, Status, Priorität, Metadaten und alle vorhandenen Reporttexte,
- blendet Navigation, Meldungen und Bedienaktionen aus,
- ist auf A4 mit druckgeeigneten Abständen und Kontrasten ausgelegt,
- vermeidet nach Möglichkeit Seitenumbrüche innerhalb einzelner Inhaltsbereiche.

## 3. Abgrenzung

- Reportify erzeugt keine PDF-Datei auf dem Server.
- Es wird keine zusätzliche Datei in der Datenbank gespeichert.
- Dateiname, Druckerwahl und PDF-Ziel werden durch den Browser beziehungsweise
  das Betriebssystem bestimmt.
- Die Funktion exportiert immer genau den aktuell geöffneten Report.

## 4. Akzeptanzkriterien

| ID | Kriterium |
|---|---|
| B3-AK-01 | Die Druckaktion ist auf der Detailseite eines Reports erreichbar. |
| B3-AK-02 | Die Aktion öffnet den nativen Druckdialog des Browsers. |
| B3-AK-03 | Navigation und Aktionsschaltflächen erscheinen nicht in der Druckausgabe. |
| B3-AK-04 | Alle vorhandenen fachlichen Reportfelder und Metadaten werden ausgegeben. |
| B3-AK-05 | Die Ausgabe kann über den Browser als PDF gespeichert werden. |

## 5. Bezug

- `UC-11` – Report drucken oder als PDF speichern
- `AF-13` – Druckansicht bereitstellen
- [B1 – Dialogspezifikation](B1-dialogspezifikationen.md)
- [E2 – Glossar](E2-glossar.md)
