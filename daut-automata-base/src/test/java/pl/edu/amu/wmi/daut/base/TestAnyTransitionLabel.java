package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;
/**
 * Klasa testujaca AnyTransitionLabel.
 */
public class TestAnyTransitionLabel extends TestCase {
    /**
     * Metoda testujaca AnyTransitionLabel.
     */
    public final void testAnyTransitionLabel() {
        AutomatonSpecification test = new NaiveAutomatonSpecification();
        State koniec = test.addState();
        State pierwszy = test.addState();
        State drugi = test.addState();
        State trzeci = test.addState();
        State czwarty = test.addState();
        test.markAsInitial(pierwszy);
        test.markAsFinal(drugi);
        test.addTransition(pierwszy, koniec, new AnyTransitionLabel());
        test.addTransition(drugi, koniec, new CharTransitionLabel('a'));
        test.addTransition(trzeci, koniec, new AnyTransitionLabel());
        test.addTransition(czwarty, koniec, new EmptyTransitionLabel());
        TransitionLabel testowany
        = test.allOutgoingTransitions(pierwszy).get(0).getTransitionLabel();
        TransitionLabel char1 = test.allOutgoingTransitions(drugi).get(0).getTransitionLabel();
        TransitionLabel any = test.allOutgoingTransitions(trzeci).get(0).getTransitionLabel();
        TransitionLabel empty = test.allOutgoingTransitions(czwarty).get(0).getTransitionLabel();
        assertEquals("ANY", testowany.toString());
        assertFalse(testowany.canBeEpsilon());
        assertFalse(testowany.isEmpty());
        assertTrue(testowany.canAcceptCharacter(' '));
        assertTrue(testowany.canAcceptCharacter('a'));
        assertTrue(testowany.canAcceptCharacter('b'));
        assertFalse((testowany.intersect(testowany)).canBeEpsilon());
        assertFalse((testowany.intersect(char1)).isEmpty());
        assertTrue((testowany.intersect(empty)).isEmpty());
        assertFalse((testowany.intersect(any)).isEmpty());
        assertEquals("ANY", (testowany.intersect(testowany)).toString());
        assertFalse((testowany.intersectWith(testowany)).canBeEpsilon());
        assertFalse((testowany.intersectWith(char1)).isEmpty());
        assertEquals("ANY", (testowany.intersectWith(testowany)).toString());
        assertFalse((testowany.intersectWith(testowany)).canBeEpsilon());
        assertFalse((testowany.intersectWith(char1)).isEmpty());
        assertTrue((testowany.intersectWith(empty)).isEmpty());
        assertFalse((testowany.intersectWith(any)).isEmpty());
    }
}
