package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 * Klasa testujaca EpsilonTransitionLabel.
 */
public class TestEndOfTextOrLineTransitionLabel extends TestCase {

    /**
     * Metoda testująca działanie tej etykiety przejścia.
     */
    public final void testEndOfTextOrLineTransitionLabel() {
        TransitionLabel testowana = new EndOfTextOrLineTransitionLabel();

        //Sprawdzenie własności etykiety przejścia.
        assertFalse(testowana.canAcceptCharacter('a'));
        assertTrue(testowana.canBeEpsilon());
        assertFalse(testowana.isEmpty());
        assertTrue(testowana.isContextual());

        //Sprawdzenie poprawności metody checkContext().
        assertTrue(testowana.checkContext("ma\nka", 2));
        assertFalse(testowana.checkContext("ma\nka", 4));
        assertTrue(testowana.checkContext("ma\nka", 5));
        try {
            testowana.checkContext("ma\nka", 7);
            fail();
        } catch (PositionOutOfStringBordersException e) {
            assertTrue(true);
        }
        try {
            testowana.checkContext("ma\nka", -2);
            fail();
        } catch (PositionOutOfStringBordersException e) {
            assertTrue(true);
        }

        //Sprawdzenie metody toString().
        assertEquals(testowana.toString(), "EndOfTextOrLine");
    }
}
