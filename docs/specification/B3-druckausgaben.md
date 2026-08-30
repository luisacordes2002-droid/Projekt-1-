# B3 – Druckausgaben

> **Status:** Arbeitsentwurf vom 30.08.2026. Die Prüfung durch das Projektteam steht aus.  
> **Anwendbarkeit:** Nicht anwendbar im festgelegten MVP.

## 1. Einordnung

Reportify stellt Schichtinformationen innerhalb der Webanwendung bereit.
Der Begriff Report bezeichnet die gespeicherte Schichtübergabe und nicht ein
eigenständig erzeugtes Druckdokument.

Die vorgesehenen Ausgaben sind die aktuelle Übergabe, die Report-Historie und die
Report-Details. Sie werden in `DLG-04`, `DLG-05` und `DLG-06` beschrieben.

## 2. Begründung und Abgrenzung

Die nachfolgende Schicht soll die Informationen im Browser lesen können.
Für diesen Ablauf sind weder Druckvorlagen noch eine anwendungseigene
Druckfunktion erforderlich. Der Export von Reports gehört gemäß F2 ebenfalls
nicht zum verbindlichen Minimalumfang.

Die allgemeine Druckfunktion des Webbrowsers ist davon zu unterscheiden.
Ihre mögliche Nutzung stellt keine eigene Reportify-Funktion dar.
Ein besonderes Drucklayout oder eine bestimmte Qualität gedruckter Seiten
wird für den MVP nicht zugesichert.

Eine spätere Druck- oder Exportfunktion müsste zuerst als Erweiterung des Umfangs
abgestimmt und mit eigenen Anforderungen und Akzeptanzkriterien beschrieben werden.

## 3. Bezug

- [F2 – Nicht enthaltene Anwendungsfälle](F2-anwendungsfaelle.md)
- [B1 – Dialogspezifikation](B1-dialogspezifikationen.md)
- [E2 – Glossar](E2-glossar.md)