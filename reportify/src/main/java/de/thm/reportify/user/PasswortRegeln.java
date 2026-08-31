package de.thm.reportify.user;

public final class PasswortRegeln {

    public static final int MINDESTLAENGE = 8;
    public static final int MAXIMALLAENGE = 128;

    private PasswortRegeln() {
        // Diese Klasse soll nicht als Objekt erzeugt werden.
    }

    public static boolean istGueltig(String passwort) {
        if (passwort == null) {
            return false;
        }

        int anzahlZeichen =
                passwort.codePointCount(0, passwort.length());

        return anzahlZeichen >= MINDESTLAENGE
                && anzahlZeichen <= MAXIMALLAENGE;
    }

    public static void pruefe(String passwort) {
        if (!istGueltig(passwort)) {
            throw new IllegalArgumentException(
                    "Das Passwort muss zwischen 8 und 128 Zeichen lang sein.");
        }
    }
}