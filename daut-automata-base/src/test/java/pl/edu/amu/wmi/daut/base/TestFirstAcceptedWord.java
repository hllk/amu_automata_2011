package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 * test metody FirstAcceptedWord.
 */
public class TestFirstAcceptedWord extends TestCase {

    /**
     * automatPusty.
     */
    public final void testFirstAcceptedWordEmpty() {
        AutomatonSpecification a = new NaiveAutomatonSpecification();
        State s0 = a.addState();
        a.markAsInitial(s0);
        a.markAsFinal(s0);
        assertEquals(a.firstAcceptedWord(""), "");
    }

    /**
     * automatA. Nie pusty ale akceptuje slowo puste.
     */
    public final void testFirstAcceptedWordA() {
        AutomatonSpecification a = new NaiveAutomatonSpecification();
        State s0 = a.addState();
        State s1 = a.addState();
        State s2 = a.addState();
        a.markAsInitial(s0);
        a.markAsFinal(s0);
        a.markAsFinal(s1);
        a.markAsFinal(s2);
        a.addTransition(s0, s1, new CharTransitionLabel('a'));
        a.addTransition(s0, s2, new CharTransitionLabel('b'));
        a.addTransition(s1, s2, new CharTransitionLabel('a'));
        a.addTransition(s1, s0, new CharTransitionLabel('b'));
        a.addTransition(s2, s1, new CharTransitionLabel('b'));
        a.addTransition(s2, s0, new CharTransitionLabel('a'));
        assertEquals(a.firstAcceptedWord("ab"), "");
    }

    /**
     * automatB. nic ciekawego.
     */
    public final void testFirstAcceptedWordB() {
        AutomatonSpecification a = new NaiveAutomatonSpecification();
        State s0 = a.addState();
        State s1 = a.addState();
        State s2 = a.addState();
        State s3 = a.addState();
        State s4 = a.addState();
        State s5 = a.addState();
        State s6 = a.addState();
        a.markAsInitial(s0);
        a.markAsFinal(s6);
        a.addTransition(s0, s2, new CharTransitionLabel('a'));
        a.addTransition(s2, s3, new CharTransitionLabel('a'));
        a.addTransition(s3, s4, new CharTransitionLabel('a'));
        a.addTransition(s4, s5, new CharTransitionLabel('a'));
        a.addTransition(s5, s6, new CharTransitionLabel('a'));
        a.addTransition(s0, s1, new CharTransitionLabel('b'));
        assertEquals(a.firstAcceptedWord("ab"), "aaaaa");
    }

    /**
     * automatC. nie ma pierwszego slowa.
     */
    public final void testFirstAcceptedWordC() {
        AutomatonSpecification a = new NaiveAutomatonSpecification();
        State s0 = a.addState();
        State s1 = a.addState();
        State s2 = a.addState();
        State s3 = a.addState();
        a.markAsInitial(s0);
        a.markAsFinal(s1);
        a.addTransition(s0, s1, new CharTransitionLabel('c'));
        a.addTransition(s0, s3, new CharTransitionLabel('a'));
        a.addTransition(s2, s1, new CharTransitionLabel('c'));
        a.addTransition(s2, s3, new CharTransitionLabel('a'));
        a.addTransition(s3, s0, new CharTransitionLabel('b'));
        a.addTransition(s3, s2, new CharTransitionLabel('a'));
        a.addTransition(s1, s0, new CharTransitionLabel('a'));
        a.addTransition(s1, s2, new CharTransitionLabel('a'));
        assertEquals(a.firstAcceptedWord("abc"), "c");
    }

    /**
     * automatD. jezyk akceptowany przezen jest nieskonczony
     */
    public final void testFirstAcceptedWordD() {
        AutomatonSpecification a = new NaiveAutomatonSpecification();
        State s0 = a.addState();
        State s1 = a.addState();
        State s2 = a.addState();
        State s3 = a.addState();
        a.markAsInitial(s0);
        a.markAsFinal(s1);
        a.addTransition(s0, s1, new CharTransitionLabel('b'));
        a.addTransition(s0, s3, new CharTransitionLabel('a'));
        a.addTransition(s2, s1, new CharTransitionLabel('a'));
        a.addTransition(s2, s3, new CharTransitionLabel('b'));
        a.addTransition(s3, s0, new CharTransitionLabel('b'));
        a.addTransition(s3, s2, new CharTransitionLabel('a'));
        a.addTransition(s1, s0, new CharTransitionLabel('b'));
        a.addTransition(s1, s2, new CharTransitionLabel('b'));
        assertEquals(a.firstAcceptedWord("ba"), "b");
    }

    /**
     * automatE. "Tasiemiec".
     */
    public final void testFirstAcceptedWordE() {
        AutomatonSpecification a = new NaiveAutomatonSpecification();
        State s0 = a.addState();
        State s1 = a.addState();
        State s2 = a.addState();
        State s3 = a.addState();
        State s4 = a.addState();
        State s5 = a.addState();
        State s6 = a.addState();
        a.markAsInitial(s0);
        a.markAsFinal(s6);
        a.addTransition(s0, s1, new CharTransitionLabel('s'));
        a.addTransition(s0, s2, new CharTransitionLabel('d'));
        a.addTransition(s1, s2, new CharTransitionLabel('f'));
        a.addTransition(s1, s3, new CharTransitionLabel('g'));
        a.addTransition(s2, s3, new CharTransitionLabel('s'));
        a.addTransition(s2, s4, new CharTransitionLabel('c'));
        a.addTransition(s3, s4, new CharTransitionLabel('z'));
        a.addTransition(s3, s5, new CharTransitionLabel('b'));
        a.addTransition(s4, s5, new CharTransitionLabel('f'));
        a.addTransition(s4, s6, new CharTransitionLabel('v'));
        a.addTransition(s5, s6, new CharTransitionLabel('k'));
        assertEquals(a.firstAcceptedWord("sdfgczbvk"), "dcv");
    }

    /**
     * automatF. tez nieskonczony.
     */
    public final void testFirstAcceptedWordF() {
        AutomatonSpecification a = new NaiveAutomatonSpecification();
        State s0 = a.addState();
        State s1 = a.addState();
        State s2 = a.addState();
        State s3 = a.addState();
        State s4 = a.addState();
        State s5 = a.addState();
        State s6 = a.addState();
        a.markAsInitial(s0);
        a.markAsFinal(s6);
        a.addTransition(s0, s1, new CharTransitionLabel('s'));
        a.addTransition(s1, s0, new CharTransitionLabel('s'));
        a.addTransition(s0, s2, new CharTransitionLabel('d'));
        a.addTransition(s1, s2, new CharTransitionLabel('f'));
        a.addTransition(s1, s3, new CharTransitionLabel('g'));
        a.addTransition(s2, s3, new CharTransitionLabel('s'));
        a.addTransition(s2, s4, new CharTransitionLabel('c'));
        a.addTransition(s3, s4, new CharTransitionLabel('z'));
        a.addTransition(s3, s5, new CharTransitionLabel('b'));
        a.addTransition(s4, s5, new CharTransitionLabel('f'));
        a.addTransition(s4, s6, new CharTransitionLabel('v'));
        a.addTransition(s5, s6, new CharTransitionLabel('k'));
        assertEquals(a.firstAcceptedWord("sdfgczbvk"), "dcv");
    }
}
