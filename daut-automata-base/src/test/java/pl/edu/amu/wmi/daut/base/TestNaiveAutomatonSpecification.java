package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;
import java.util.List;

/**
 * Przykładowe testy przykładowej klasy NaiveAutomatonSpecification.
 */
public class TestNaiveAutomatonSpecification extends TestCase {

    /**
     * Test prostego automatu o trzech stanach.
     */
    public final void testSimpleAutomaton() {
        NaiveAutomatonSpecification spec = new NaiveAutomatonSpecification();

        // budowanie

        State s0 = spec.addState();
        State s1 = spec.addState();
        spec.addTransition(s0, s1, new CharTransitionLabel('a'));
        State s2 = spec.addState();
        spec.addTransition(s0, s2, new CharTransitionLabel('b'));
        spec.addTransition(s1, s2, new CharTransitionLabel('c'));

        spec.markAsInitial(s0);
        spec.markAsFinal(s2);

        // testowanie

        State r0 = spec.getInitialState();

        List<OutgoingTransition> r0Outs = spec.allOutgoingTransitions(r0);

        // w ten sposób w JUnicie wyrażamy oczekiwanie, że liczba
        // przejść wychodzących z początkowego stanu powinna być równa 2
        assertEquals(r0Outs.size(), 2);
        assertFalse(spec.isFinal(r0));

        State r1;
        State r2;

        if (((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).getChar() == 'a') {
            r1 = r0Outs.get(0).getTargetState();
            r2 = r0Outs.get(1).getTargetState();
            assertEquals(((CharTransitionLabel) r0Outs.get(1).getTransitionLabel()).getChar(), 'b');
            assertTrue(
                ((CharTransitionLabel) r0Outs.get(1).getTransitionLabel()).canAcceptCharacter('b'));
            assertFalse(
                ((CharTransitionLabel) r0Outs.get(1).getTransitionLabel()).canAcceptCharacter('c'));
            assertFalse(((CharTransitionLabel) r0Outs.get(1).getTransitionLabel()).canBeEpsilon());
        } else {
            // kolejność może być odwrócona
            r1 = r0Outs.get(1).getTargetState();
            r2 = r0Outs.get(0).getTargetState();
            assertEquals(((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).getChar(), 'b');
        }

        assertFalse(spec.isFinal(r1));
        assertTrue(spec.isFinal(r2));
        assertSame(r0, spec.getInitialState());
        assertNotSame(r0, r1);
        assertNotSame(r0, r2);
        assertNotSame(r1, r2);

        List<State> states = spec.allStates();

        assertEquals(states.size(), 3);
    }

    /**
     * Prosty test wyznaczania przecięcia.
     */
    public final void testIntersections() {
        CharTransitionLabel tA1 = new CharTransitionLabel('a');
        CharTransitionLabel tA2 = new CharTransitionLabel('a');
        CharTransitionLabel tB = new CharTransitionLabel('b');
        EmptyTransitionLabel emptyTransition = new EmptyTransitionLabel();

        TransitionLabel intersectedA = tA1.intersect(tA2);
        assertFalse(intersectedA.isEmpty());
        assertTrue(intersectedA.canAcceptCharacter('a'));
        assertFalse(intersectedA.canAcceptCharacter('b'));

        assertTrue(tA1.intersect(tB).isEmpty());
        assertTrue(tB.intersect(tA1).isEmpty());
        assertTrue(emptyTransition.intersect(tA1).isEmpty());
        assertTrue(tA1.intersect(emptyTransition).isEmpty());
        assertTrue(emptyTransition.intersect(emptyTransition).isEmpty());
    }

    /**
     * Test metody dodającej pętlę.
     */
    public final void testAddLoop() {
        NaiveAutomatonSpecification spec = new NaiveAutomatonSpecification();

        //budowanie

        State s0 = spec.addState();
        spec.addLoop(s0, new CharTransitionLabel('a'));
        spec.markAsInitial(s0);
        spec.markAsFinal(s0);

        //testowanie

        State r0 = spec.getInitialState();

        List<OutgoingTransition> r0Outs = spec.allOutgoingTransitions(r0);

        assertEquals(r0Outs.size(), 1);
        assertTrue(spec.isFinal(r0));

        if (((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).getChar() == 'a') {
            assertEquals(
                ((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).getChar(), 'a');
            assertTrue(
                ((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).canAcceptCharacter('a'));
            assertFalse(
                ((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).canBeEpsilon());
        }

        assertTrue(spec.isFinal(r0));
        assertSame(r0, spec.getInitialState());

        List<State> states = spec.allStates();

        assertEquals(states.size(), 1);

    }

    /**
     * Test metody tworzącej prosty automat.
     */
    public final void testmakeOneLoopAutomaton(char c) {
        NaiveAutomatonSpecification spec = new NaiveAutomatonSpecification();

        //budowanie

        State s0 = spec.addState();
        spec.addLoop(s0, new CharTransitionLabel('c'));
        spec.markAsInitial(s0);
        spec.markAsFinal(s0);

        //testowanie

        State r0 = spec.getInitialState();

        List<OutgoingTransition> r0Outs = spec.allOutgoingTransitions(r0);

        assertEquals(r0Outs.size(), 1);
        assertTrue(spec.isFinal(r0));

        State r1;

        if (((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).getChar() == 'c') {
            r1 = r0Outs.get(0).getTargetState();
            assertEquals(
                ((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).getChar(), 'c');
            assertTrue(
                ((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).canAcceptCharacter('c'));
            assertFalse(
                ((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).canBeEpsilon());
        }

        assertTrue(spec.isFinal(r0));
        assertSame(r0, spec.getInitialState());

        List<State> states = spec.allStates();

        assertEquals(states.size(), 1);

    }

    /**
     * Test metody dopełniającej automat.
     */
    public final void testMakeFull() {
        NaiveAutomatonSpecification spec = new NaiveAutomatonSpecification();
        NaiveAutomatonSpecification spec2 = new NaiveAutomatonSpecification();
        NaiveAutomatonSpecification spec3 = new NaiveAutomatonSpecification();

        spec.makeFull("abc");
        assertTrue(spec.isFull("abc"));

        State s = spec2.addState();

        spec2.makeFull("abc");
        assertTrue(spec2.isFull("abc"));

        State s0 = spec3.addState();
        State s1 = spec3.addState();
        State s2 = spec3.addState();

        spec3.addTransition(s0, s1, new CharTransitionLabel('a'));

        spec3.makeFull("abc");
        assertTrue(spec3.isFull("abc"));
    }

    /**
     * Test metody tworzącej prosty automat.
     */
    public final void testmakeOneTransitionAutomaton(char c) {
        AutomatonSpecification spec = new NaiveAutomatonSpecification();

        spec = spec.makeOneTransitionAutomaton(c);

        //testowanie

        State r0 = spec.getInitialState();

        List<OutgoingTransition> r0Outs = spec.allOutgoingTransitions(r0);

        assertEquals(r0Outs.size(), 1);
        assertFalse(spec.isFinal(r0));

        State r1 = r0Outs.get(0).getTargetState();

        if (((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).getChar() == 'c') {
            assertEquals(
                ((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).getChar(), 'c');
            assertTrue(
                ((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).canAcceptCharacter('c'));
            assertFalse(
                ((CharTransitionLabel) r0Outs.get(0).getTransitionLabel()).canBeEpsilon());
        }

        assertTrue(spec.isFinal(r1));
        assertSame(r0, spec.getInitialState());

        List<State> states = spec.allStates();

        assertEquals(states.size(), 2);
    }

    /**
     * Test metody tworzącej automat akceptujący wszystkie napisy nad zadanym językiem.
     */
    public final void testMakeAllStringsAutomaton() {
        final AutomatonSpecification spec = new NaiveAutomatonSpecification();
        spec.makeAllStringsAutomaton("abc");
        assertTrue(spec.acceptEmptyWord());
        AutomatonByRecursion automaton = new AutomatonByRecursion(spec);
        assertFalse(automaton.accepts("defffadegbc"));
        assertFalse(automaton.accepts("abecadlo"));
        assertTrue(automaton.accepts("abcbcabbbaaa"));
        assertTrue(automaton.accepts("bbccaabcbabab"));
        assertTrue(automaton.accepts("cacacacbbccccc"));
    }

    /**
     * Testuje działanie metody makeAllNonEmptyStringsAutomaton().
     */
    public final void testMakeAllNonEmptyStringsAutomaton() {

        //Buduję automat na 2 stanach korzystając z testowanej metody

        final AutomatonSpecification spec = new NaiveAutomatonSpecification();
        spec.makeAllNonEmptyStringsAutomaton("ab");
        AutomatonByRecursion automaton = new AutomatonByRecursion(spec);

        //Sprawdzam czy automat akceptuje losowe słowa i czy odrzuca słowo puste

        assertFalse(spec.acceptEmptyWord());
        assertTrue(automaton.accepts("abbabbabbabbaaa"));
        assertFalse(automaton.accepts("caba"));
        assertTrue(automaton.accepts("a"));
        assertTrue(automaton.accepts("b"));
        assertTrue(automaton.accepts("aaaa"));
        assertTrue(automaton.accepts("bbbb"));
    }

    /**
     * Test metody sprawdzającej, czy akceptowany język jest nieskończony.
     */
    public final void testInfinite1() {
        NaiveAutomatonSpecification automat = new NaiveAutomatonSpecification();
        State s0 = automat.addState();
        State s1 = automat.addState();
        State s2 = automat.addState();
        State s3 = automat.addState();
        State s4 = automat.addState();
        automat.addTransition(s0, s1, new CharTransitionLabel('a'));
        automat.addLoop(s1, new CharTransitionLabel('b'));
        automat.addLoop(s2, new CharTransitionLabel('b'));
        automat.addTransition(s1, s2, new CharTransitionLabel('a'));
        automat.addTransition(s2, s1, new CharTransitionLabel('a'));
        automat.addTransition(s2, s0, new CharTransitionLabel('c'));
        automat.addTransition(s2, s3, new CharTransitionLabel('c'));
        automat.addTransition(s3, s4, new CharTransitionLabel('a'));
        automat.addLoop(s4, new CharTransitionLabel('b'));
        automat.markAsFinal(s2);
        automat.markAsFinal(s1);
        automat.markAsInitial(s0);
        automat.markAsFinal(s4);
        assertTrue(automat.isInfinite());
    }

    /**
     * Test metody sprawdzającej, czy akceptowany język jest nieskończony.
     */
    public final void testInfinite2() {
        NaiveAutomatonSpecification automat = new NaiveAutomatonSpecification();
        State s0 = automat.addState();
        State s1 = automat.addState();
        State s2 = automat.addState();
        State s3 = automat.addState();
        automat.addTransition(s0, s1, new CharTransitionLabel('a'));
        automat.addTransition(s1, s2, new CharTransitionLabel('a'));
        automat.addTransition(s2, s3, new CharTransitionLabel('a'));
        automat.addTransition(s3, s1, new CharTransitionLabel('a'));
        automat.markAsInitial(s0);
        automat.markAsFinal(s2);
        automat.markAsFinal(s1);
        assertTrue(automat.isInfinite());
    }

    /**
     * Test metody sprawdzającej, czy akceptowany język jest nieskończony.
     */
    public final void testInfinite3() {
        NaiveAutomatonSpecification automat = new NaiveAutomatonSpecification();
        State s0 = automat.addState();
        State s1 = automat.addState();
        State s2 = automat.addState();
        State s3 = automat.addState();
        State s4 = automat.addState();
        automat.addTransition(s0, s1, new CharTransitionLabel('a'));
        automat.addTransition(s1, s2, new CharTransitionLabel('a'));
        automat.addTransition(s2, s3, new CharTransitionLabel('a'));
        automat.addTransition(s3, s1, new CharTransitionLabel('a'));
        automat.addTransition(s3, s4, new CharTransitionLabel('b'));
        automat.markAsInitial(s0);
        automat.markAsFinal(s2);
        automat.markAsFinal(s1);
        automat.markAsFinal(s4);
        assertTrue(automat.isInfinite());
    }

    /**
     * Test metody sprawdzającej, czy akceptowany język jest nieskończony.
     */
    public final void testInfinite4() {
        NaiveAutomatonSpecification automat = new NaiveAutomatonSpecification();
        State s0 = automat.addState();
        State s1 = automat.addState();
        State s2 = automat.addState();
        State s3 = automat.addState();
        State s4 = automat.addState();
        State s5 = automat.addState();
        automat.addTransition(s0, s1, new CharTransitionLabel('a'));
        automat.addTransition(s1, s2, new CharTransitionLabel('a'));
        automat.addTransition(s2, s3, new CharTransitionLabel('a'));
        automat.addTransition(s3, s2, new CharTransitionLabel('b'));
        automat.addTransition(s3, s1, new CharTransitionLabel('a'));
        automat.addTransition(s3, s4, new CharTransitionLabel('b'));
        automat.addTransition(s4, s5, new CharTransitionLabel('a'));
        automat.addTransition(s5, s4, new CharTransitionLabel('a'));
        automat.markAsInitial(s0);
        automat.markAsFinal(s1);
        assertTrue(automat.isInfinite());
    }

    /**
     * Test metody sprawdzającej, czy akceptowany język jest nieskończony.
     */
    public final void testInfinite5() {
        NaiveAutomatonSpecification automat = new NaiveAutomatonSpecification();
        State s0 = automat.addState();
        State s1 = automat.addState();
        State s2 = automat.addState();
        State s3 = automat.addState();
        State s4 = automat.addState();
        automat.addTransition(s0, s1, new CharTransitionLabel('a'));
        automat.addTransition(s1, s2, new CharTransitionLabel('a'));
        automat.addTransition(s2, s3, new CharTransitionLabel('a'));
        automat.addTransition(s3, s1, new CharTransitionLabel('a'));
        automat.addTransition(s3, s4, new CharTransitionLabel('b'));
        automat.markAsInitial(s0);
        automat.markAsFinal(s4);
        assertFalse(automat.isInfinite());
    }

    /**
     * Test metody sprawdzającej, czy akceptowany język jest nieskończony.
     */
    public final void testInfinite6() {
        NaiveAutomatonSpecification automat = new NaiveAutomatonSpecification();
        State s0 = automat.addState();
        State s1 = automat.addState();
        State s2 = automat.addState();
        State s3 = automat.addState();
        State s4 = automat.addState();
        automat.addTransition(s0, s1, new CharTransitionLabel('a'));
        automat.addTransition(s1, s2, new CharTransitionLabel('a'));
        automat.addTransition(s2, s3, new CharTransitionLabel('a'));
        automat.addTransition(s3, s4, new CharTransitionLabel('a'));
        automat.markAsInitial(s0);
        automat.markAsFinal(s3);
        assertFalse(automat.isInfinite());
    }

    /**
     * Test metody sprawdzającej, czy akceptowany język jest nieskończony.
     */
    public final void testInfinite7() {
        NaiveAutomatonSpecification automat = new NaiveAutomatonSpecification();
        State s0 = automat.addState();
        State s1 = automat.addState();
        State s2 = automat.addState();
        State s3 = automat.addState();
        automat.addTransition(s1, s2, new CharTransitionLabel('a'));
        automat.addTransition(s2, s3, new CharTransitionLabel('a'));
        automat.addTransition(s3, s1, new CharTransitionLabel('a'));
        automat.markAsInitial(s0);
        automat.markAsFinal(s2);
        automat.markAsFinal(s1);
        assertFalse(automat.isInfinite());
    }

    /**
     * Test metody sprawdzającej, czy akceptowany język jest nieskończony.
     */
    public final void testInfinite8() {
        NaiveAutomatonSpecification automat = new NaiveAutomatonSpecification();
        State s0 = automat.addState();
        automat.addLoop(s0, new CharTransitionLabel('a'));
        automat.markAsInitial(s0);
        automat.markAsFinal(s0);
        assertTrue(automat.isInfinite());
    }

    /**
     * Test metody sprawdzającej, czy akceptowany język jest nieskończony.
     */
    public final void testInfinite9() {
        NaiveAutomatonSpecification automat = new NaiveAutomatonSpecification();
        State s0 = automat.addState();
        State s1 = automat.addState();
        automat.addTransition(s0, s1, new CharTransitionLabel('a'));
        automat.addTransition(s1, s0, new CharTransitionLabel('a'));
        automat.markAsInitial(s0);
        automat.markAsFinal(s0);
        assertTrue(automat.isInfinite());
    }
}
