package pl.edu.amu.wmi.daut.base;

import junit.framework.TestCase;

/**
 * Klasa testujaca EpsilonTransitionLabel.
 */
public class TestMakeAutomatonFromString extends TestCase {

    /**
     * Test metody fromString() tworzacy pusty automat.
     */
    public final void testFromString0EmptyLanguage() {
        AutomatonSpecification pustyOjciec = new NaiveAutomatonSpecification();

        String slowo = "Automaton:\n-States: q0\n-Transitions:\n"
                + "-Initial state: q0\n-Final states:";

        try {
            pustyOjciec.fromString(slowo);
        } catch (Exception e) {
            fail("fromString() zwrocil wyjatek dla poprawnego Stringa!");
        }

        AutomatonByRecursion pusteDziecko = new AutomatonByRecursion(pustyOjciec);

        assertFalse(pusteDziecko.accepts("aaa"));
        assertFalse(pusteDziecko.accepts("baba"));
        assertFalse(pusteDziecko.accepts(""));
    }

    /**
     * Test metody fromString() tworzący automat akceptujacy slowo puste.
     */
    public final void testFromString1EmptyStringAutomaton() {
        AutomatonSpecification dziwny = new NaiveAutomatonSpecification();

        String slowo = "Automaton:\n-States: q0\n-Transitions:\n"
                + "-Initial state: q0\n-Final states: q0";

        try {
            dziwny.fromString(slowo);
        } catch (Exception e) {
            fail("fromString() zwrocil wyjatek dla poprawnego Stringa!");
        }

        AutomatonByRecursion wynik = new AutomatonByRecursion(dziwny);

        assertFalse(wynik.accepts("aaa"));
        assertFalse(wynik.accepts("baba"));
        assertTrue(wynik.accepts(""));
    }

    /**
     * Test metody fromString() tworzący automat akceptujacy nieparzyste
     * potegi 'a'.
     */
    public final void testFromString2OddPowersOfA() {
        AutomatonSpecification masakra = new NaiveAutomatonSpecification();

        String slowo = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -a-> q1\nq1 -a-> q0\n-Initial state: q0\n-Final states: q1";

        try {
            masakra.fromString(slowo);
        } catch (Exception e) {
            fail("fromString() zwrocil wyjatek dla poprawnego Stringa!");
        }

        AutomatonByRecursion angle = new AutomatonByRecursion(masakra);

        assertFalse(angle.accepts("aab"));
        assertFalse(angle.accepts(""));
        assertTrue(angle.accepts("aaa"));
    }

    /**
     * Test metody fromString() tworzący automat z epsilonem.
     */
    public final void testFromString3AutomatonWithEpsilonTransition() {
        AutomatonSpecification epsilon = new NaiveAutomatonSpecification();

        String slowo = "Automaton:\n-States: q0 q1 q2 q3\n-Transitions:\n"
                + "q0 -epsilon-> q1\nq1 -a-> q2\n-Initial state: q0\n"
                + "-Final states: q3";

        try {
            epsilon.fromString(slowo);
        } catch (Exception e) {
            fail("fromString() zwrocil wyjatek dla automatu z Epsilonem!");
        }

        boolean potworek = false;
        for (int i = 0; i < 3; i++) {
            if (!epsilon.allOutgoingTransitions(epsilon.allStates().get(i)).isEmpty()) {
                if (epsilon.allOutgoingTransitions(epsilon.allStates().get(i))
                        .get(0).getTransitionLabel().canBeEpsilon()) {
                    potworek = true;
                    break;
                }
            }
        }
        assertTrue(potworek);
//  Wykomentowane do czasu obsługiwania przez jakikolwiek automat przejść epsilonowych.
//        AutomatonByRecursion ep = new AutomatonByRecursion(epsilon);
//
//        assertTrue(ep.accepts("a"));
//        assertTrue(ep.accepts("b"));
//        assertFalse(ep.accepts(""));
//        assertFalse(ep.accepts("ε"));
//        assertFalse(ep.accepts("aa"));
//        assertFalse(ep.accepts("ab"));
//        assertFalse(ep.accepts("ba"));
//        assertFalse(ep.accepts("bb"));
    }

    /**
     * Test metody fromString() z bełkotem.
     */
    public final void testFromString4Blubbering() {
        AutomatonSpecification niepoprawny = new NaiveAutomatonSpecification();

        String slowo = "niepoprawnywogolestring";

        try {
            niepoprawny.fromString(slowo);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        slowo = "Magia: dwa trzy cztery piec";

        try {
            niepoprawny.fromString(slowo);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        slowo = "Automaton:\n-Niestates: q0\n-Transitions: magia";

        try {
            niepoprawny.fromString(slowo);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    /**
     * Test metody fromString() z błędnymi opisami automatu.
     * Błędy nazw stanów.
     */
    public final void testFromString5WrongAutomatonStrings0States() {
        AutomatonSpecification bledny = new NaiveAutomatonSpecification();

        String nonCorrectStateName0 = "Automaton:\n-States: q\n-Transitions:\n a";

        try {
            bledny.fromString(nonCorrectStateName0);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectStateName1 = "Automaton:\n-States: a1\n-Transitions:\n a";

        try {
            bledny.fromString(nonCorrectStateName1);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectStateName2 = "Automaton:\n-States: q1.2\n-Transitions:\n a";

        try {
            bledny.fromString(nonCorrectStateName2);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectStateName3 = "Automaton:\n-States: qa\n-Transitions:\n a";

        try {
            bledny.fromString(nonCorrectStateName3);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    /**
     * Test metody fromString() z błędnymi opisami automatu.
     * Błędy nazw przejść.
     */
    public final void testFromString5WrongAutomatonStrings1Transitions() {
        AutomatonSpecification bledny = new NaiveAutomatonSpecification();

        String nonCorrectTransitionName0Label0 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 --> q0";

        try {
            bledny.fromString(nonCorrectTransitionName0Label0);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectTransitionName0Label1 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 .q-> q0";

        try {
            bledny.fromString(nonCorrectTransitionName0Label1);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectTransitionName0Label2 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 --.> q0";

        try {
            bledny.fromString(nonCorrectTransitionName0Label2);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectTransitionName0Label3 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -\t-> q0";

        try {
            bledny.fromString(nonCorrectTransitionName0Label2);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectTransitionName1 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "qa -a-> q0";

        try {
            bledny.fromString(nonCorrectTransitionName1);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectTransitionName2 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -a-> qa";

        try {
            bledny.fromString(nonCorrectTransitionName2);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    /**
     * Test metody fromString() z błędnymi opisami automatu.
     * Błędy nazw stanów specjalnych.
     */
    public final void testFromString5WrongAutomatonStrings2SpecialStates() {
        AutomatonSpecification bledny = new NaiveAutomatonSpecification();

        String nonCorrectSpecialStates0 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -a-> q0\n-Initial error";

        try {
            bledny.fromString(nonCorrectSpecialStates0);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectSpecialStates1 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -a-> q0\n-Initial state: qa";

        try {
            bledny.fromString(nonCorrectSpecialStates1);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectSpecialStates2 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -a-> q0\n-Initial state: q0\nerror";

        try {
            bledny.fromString(nonCorrectSpecialStates2);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectSpecialStates3 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -a-> q0\n-Initial state: q0\n-Final error";

        try {
            bledny.fromString(nonCorrectSpecialStates3);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

        String nonCorrectSpecialStates4 = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -a-> q0\n-Initial state: q0\n-Final states: qk";

        try {
            bledny.fromString(nonCorrectSpecialStates4);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    /**
     * Test metody fromString() tworzący automat z przejściem ANY.
     */
    public final void testFromString6AutomatonWithAnyTransition() {
        AutomatonSpecification anyone = new NaiveAutomatonSpecification();

        String slowo = "Automaton:\n-States: q0 q1 q2\n-Transitions:\n"
                + "q0 -q-> q1\nq1 -ANY-> q2\n-Initial state: q0\n-Final states: q2";

        try {
            anyone.fromString(slowo);
        } catch (Exception e) {
            fail("fromString() zwrocil wyjatek dla automatu z przejściem dowolnym!");
        }

        AutomatonByRecursion ap = new AutomatonByRecursion(anyone);

        assertTrue(ap.accepts("q0"));
        assertTrue(ap.accepts("qε"));
        assertFalse(ap.accepts(""));
        assertFalse(ap.accepts("q"));
        assertFalse(ap.accepts("q10"));
        assertFalse(ap.accepts("aq0"));
        assertFalse(ap.accepts("01"));
    }

    /**
     * Test metody fromString() tworzący automat z przejściem po znaku z dopełnienia
     * sumy przedziałów.
     */
    public final void testFromString7AutomatonWithCCharClassTransition() {
        AutomatonSpecification ccchar = new NaiveAutomatonSpecification();

        String slowo = "Automaton:\n-States: q0 q1\n-Transitions:\n"
                + "q0 -[^ac-e]-> q1\n-Initial state: q0\n-Final states: q1";

        try {
            ccchar.fromString(slowo);
        } catch (Exception e) {
            fail("fromString() zwrocil wyjatek dla automatu z odwrotnością przejścia"
                    + "z sumy przedziałów!");
        }

        AutomatonByRecursion cccp = new AutomatonByRecursion(ccchar);

        assertFalse(cccp.accepts("^"));
        assertFalse(cccp.accepts("a"));
        assertFalse(cccp.accepts("c"));
        assertFalse(cccp.accepts("d"));
        assertFalse(cccp.accepts("e"));
        assertFalse(cccp.accepts(""));
        assertTrue(cccp.accepts("f"));
        assertTrue(cccp.accepts("b"));
    }

    /**
     * Test metody fromString() tworzący automat z przejściem po znaku ze zbioru.
     */
//    public final void testFromString8AutomatonWithCharSetTransition() {
//        AutomatonSpecification charSet = new NaiveAutomatonSpecification();
//
//        String slowo = "Automaton:\n-States: q0 q1\n-Transitions:\n"
//                + "q0 -{a,g,e}-> q1\n-Initial state: q0\n-Final states: q1";
//
//        try {
//            charSet.fromString(slowo);
//        } catch (Exception e) {
//            fail("fromString() zwrocil wyjatek dla automatu z przejściem po znaku ze zbioru!");
//        }
//
//        AutomatonByRecursion csp = new AutomatonByRecursion(charSet);
//
//        assertTrue(csp.accepts("a"));
//        assertTrue(csp.accepts("g"));
//        assertTrue(csp.accepts("e"));
//        assertFalse(csp.accepts(""));
//        assertFalse(csp.accepts("b"));
//        assertFalse(csp.accepts("f"));
//        assertFalse(csp.accepts("A"));
//    }

    /**
     * Test metody fromString() tworzący automat z przejściem po znaku z zakresu.
     */
//    public final void testFromString8AutomatonWithCharSetTransition() {
//        AutomatonSpecification charSet = new NaiveAutomatonSpecification();
//
//        String slowo = "Automaton:\n-States: q0 q1\n-Transitions:\n"
//                + "q0 -(a,m)-> q1\n-Initial state: q0\n-Final states: q1";
//
//        try {
//            charSet.fromString(slowo);
//        } catch (Exception e) {
//            fail("fromString() zwrocil wyjatek dla automatu z przejściem po znaku ze zbioru!");
//        }
//
//        AutomatonByRecursion csp = new AutomatonByRecursion(charSet);
//
//        assertTrue(csp.accepts("a"));
//        assertTrue(csp.accepts("g"));
//        assertTrue(csp.accepts("m"));
//        assertFalse(csp.accepts(""));
//        assertFalse(csp.accepts("n"));
//        assertFalse(csp.accepts("p"));
//        assertFalse(csp.accepts("A"));
//        assertFalse(csp.accepts("."));
//        assertFalse(csp.accepts(","));
//    }
}
