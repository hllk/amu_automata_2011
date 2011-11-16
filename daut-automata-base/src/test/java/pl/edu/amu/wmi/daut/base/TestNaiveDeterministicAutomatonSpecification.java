package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;
import java.util.List;

/**
 * Przykładowe testy przykładowej klasy NaiveDeterministicAutomatonSpecification.
 */
public class TestNaiveDeterministicAutomatonSpecification extends TestCase {

    /**
     * Test prostego automatu.
     */
    public final void testNaiveDeterministicAutomaton() {
        NaiveDeterministicAutomatonSpecification automat =
                new NaiveDeterministicAutomatonSpecification();

        State s1 = automat.addState();
        State s2 = automat.addState();
        automat.addTransition(s1, s2, new CharTransitionLabel('a'));
        State s3 = automat.addState();
        automat.addTransition(s2, s3, new CharTransitionLabel('b'));
        State s4 = automat.addState();
        automat.addTransition(s3, s4, new CharTransitionLabel('c'));

        automat.markAsInitial(s1);
        automat.markAsFinal(s4);
        State s0 = automat.getInitialState();

        List<OutgoingTransition> s0Out = automat.allOutgoingTransitions(s0);
        List<State> states = automat.allStates();

        assertEquals(s0Out.size(), 1);
        assertEquals(states.size(), 4);
        assertFalse(automat.isFinal(s0));
        assertEquals(
                ((CharTransitionLabel) s0Out.get(0).getTransitionLabel()).getChar(), 'a');
        assertTrue(
                ((CharTransitionLabel) s0Out.get(0).getTransitionLabel()).canAcceptCharacter('a'));
        assertFalse(
                ((CharTransitionLabel) s0Out.get(0).getTransitionLabel()).canAcceptCharacter('b'));
        State r0 = automat.targetState(s2, 'b');
        assertSame(r0, s3);
        assertNotSame(r0, s1);
    }

    /**
     * Test metody targetState.
     */
    public final void testTargetState() {
        NaiveDeterministicAutomatonSpecification automat =
                new NaiveDeterministicAutomatonSpecification();

        State r1 = automat.addState();
        State r2 = automat.addState();
        automat.addTransition(r1, r2, new CharTransitionLabel('a'));
        State r3 = automat.addState();
        automat.addTransition(r2, r3, new CharTransitionLabel('b'));
        State r4 = automat.addState();
        automat.addTransition(r2, r4, new CharTransitionLabel('c'));
        State r5 = automat.addState();
        automat.addTransition(r2, r5, new CharTransitionLabel('d'));

        State test0 = automat.targetState(r2, 'b');
        State test1 = automat.targetState(r2, 'c');
        State test2 = automat.targetState(r2, 'd');
        State test3 = automat.targetState(r1, 'a');
        State test4 = automat.targetState(r5, 'a');

        assertSame(test0, r3);
        assertSame(test1, r4);
        assertSame(test2, r5);
        assertSame(test3, r2);
        assertNotSame(test0, r4);
        assertNotSame(test1, r5);
        assertNotSame(test4, r1);
    }
}
