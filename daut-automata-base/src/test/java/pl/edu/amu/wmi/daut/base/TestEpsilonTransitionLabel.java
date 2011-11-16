package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 * Klasa testujaca EpsilonTransitionLabel.
 */
public class TestEpsilonTransitionLabel extends TestCase {

    /**
     * Metoda testująca EpsilonTransitionLabel.
     */
    public final void testEpsilonTransitionLabel() {
        AutomatonSpecification test = new NaiveAutomatonSpecification();
        State koniec = test.addState();
        State pierwszy = test.addState();
        State drugi = test.addState();
        State trzeci = test.addState();
        State czwarty = test.addState();
        test.markAsInitial(pierwszy);
        test.markAsFinal(drugi);
        test.addTransition(pierwszy, koniec, new EpsilonTransitionLabel());
        test.addTransition(drugi, koniec, new CharTransitionLabel('a'));
        test.addTransition(trzeci, koniec, new CharTransitionLabel('ε'));
        test.addTransition(czwarty, koniec, new EmptyTransitionLabel());
        TransitionLabel testowany =
            test.allOutgoingTransitions(pierwszy).get(0).getTransitionLabel();
        TransitionLabel char1 = test.allOutgoingTransitions(drugi).get(0).getTransitionLabel();
        TransitionLabel char2 = test.allOutgoingTransitions(trzeci).get(0).getTransitionLabel();
        TransitionLabel empty = test.allOutgoingTransitions(czwarty).get(0).getTransitionLabel();
        assertEquals("epsilon", testowany.toString());
        assertTrue(testowany.canBeEpsilon());
        assertFalse(testowany.isEmpty());
        assertFalse(testowany.canAcceptCharacter(' '));
        assertFalse(testowany.canAcceptCharacter('a'));
        assertFalse(testowany.canAcceptCharacter('b'));
        assertFalse(testowany.canAcceptCharacter('ε'));
        assertEquals("epsilon", (testowany.intersect(testowany)).toString());
        assertTrue((testowany.intersect(testowany)).canBeEpsilon());
        assertTrue((testowany.intersect(char1)).isEmpty());
        assertTrue((testowany.intersect(char2)).isEmpty());
        assertTrue((testowany.intersect(empty)).isEmpty());
        assertEquals("epsilon", (testowany.intersectWith(testowany)).toString());
        assertTrue((testowany.intersectWith(testowany)).canBeEpsilon());
        assertTrue((testowany.intersectWith(char1)).isEmpty());
        assertTrue((testowany.intersectWith(char2)).isEmpty());
        assertTrue((testowany.intersectWith(empty)).isEmpty());
    }
}
