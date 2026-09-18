package de.thm.reportify.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PasswortRegelnTest {

    @Test
    void lehntSiebenZeichenAb() {
        assertFalse(PasswortRegeln.istGueltig("a".repeat(7)));
    }

    @Test
    void akzeptiertAchtZeichen() {
        assertTrue(PasswortRegeln.istGueltig("a".repeat(8)));
    }

    @Test
    void akzeptiert128Zeichen() {
        assertTrue(PasswortRegeln.istGueltig("a".repeat(128)));
    }

    @Test
    void lehnt129ZeichenAb() {
        assertFalse(PasswortRegeln.istGueltig("a".repeat(129)));
    }

    @Test
    void lehntFehlendesPasswortAb() {
        assertFalse(PasswortRegeln.istGueltig(null));
    }
}