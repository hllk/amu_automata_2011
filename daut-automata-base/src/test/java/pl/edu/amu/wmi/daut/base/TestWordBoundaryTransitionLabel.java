package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
* Klasa testujaca klase WordBoundaryTransitionLabel.
*/
public class TestWordBoundaryTransitionLabel extends TestCase {

/**
* Test metody doCheckContext.
*/
    public final void testDoCheckContext() {
        WordBoundaryTransitionLabel trans = new WordBoundaryTransitionLabel();

        assertTrue(trans.doCheckContext("baobab", 6));
        assertTrue(trans.doCheckContext("snob", 4));
        assertTrue(trans.doCheckContext("bob bab", 3));
        assertTrue(trans.doCheckContext("kara\nsara", 4));
        assertTrue(trans.doCheckContext("Do\tdomu", 2));

        assertFalse(trans.doCheckContext("gorczyca", 4));
        assertFalse(trans.doCheckContext("baobab", 3));

        try {
            trans.doCheckContext("gorczyca", 10);
            fail();
        } catch (PositionOutOfStringBordersException e) {
            assertTrue(true);
        }

        try {
            trans.doCheckContext("robot", 6);
            fail();
        } catch (PositionOutOfStringBordersException e) {
            assertTrue(true);
        }

/**
* Test atrybutów.
*/

        assertEquals(trans.toString(), "WordBoundary");

        assertFalse(trans.isEmpty());
        assertFalse(trans.canAcceptCharacter('d'));
        assertTrue(trans.canBeEpsilon());

    }

}
