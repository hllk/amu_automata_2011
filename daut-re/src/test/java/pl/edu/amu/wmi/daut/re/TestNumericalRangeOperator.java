package pl.edu.amu.wmi.daut.re;

import java.util.ArrayList;
import junit.framework.TestCase;
import pl.edu.amu.wmi.daut.base.NondeterministicAutomatonByThompsonApproach;
import pl.edu.amu.wmi.daut.re.NumericalRangeOperator.Factory;

/**
 * Test klasy NumericalRangeOperator.
 */
public class TestNumericalRangeOperator extends TestCase {

    /**
     * Test konstruktorow NumericalRangeOperator.
     */
    public void testNumericalRangeOperator() {
        NumericalRangeOperator operator = new NumericalRangeOperator(4, 7);

        try {
            NumericalRangeOperator operator2 = new NumericalRangeOperator(100, 1);
            NumericalRangeOperator operator3 = new NumericalRangeOperator(-1, 4);
            NumericalRangeOperator operator4 = new NumericalRangeOperator(5, -10);
            fail();
        } catch (InvalidValueException e) {
            assertTrue(true);
        }
    }

    /**
     * Test najprostszego automatu.
     */
    public void testcreateSimpleFixedAutomaton() {

        NumericalRangeOperator spec = new NumericalRangeOperator(101, 101);
        NondeterministicAutomatonByThompsonApproach automaton =
                new NondeterministicAutomatonByThompsonApproach(spec.createFixedAutomaton());

        assertTrue(automaton.accepts("101"));
        assertFalse(automaton.accepts(""));
        assertFalse(automaton.accepts("1"));
        assertFalse(automaton.accepts("100"));
        assertFalse(automaton.accepts("102"));
        assertFalse(automaton.accepts("a101b"));
        assertFalse(automaton.accepts("   101  "));
    }

    /**
     * Test ciekawszego automatu.
     */
    public final void testcreateFixedAutomaton() {
 
        NumericalRangeOperator spec = new NumericalRangeOperator(3, 88);
        NondeterministicAutomatonByThompsonApproach automaton =
                new NondeterministicAutomatonByThompsonApproach(spec.createFixedAutomaton());
 
        assertTrue(automaton.accepts("3"));
        assertTrue(automaton.accepts("4"));
        assertTrue(automaton.accepts("88"));
        assertTrue(automaton.accepts("87"));
        assertTrue(automaton.accepts("60"));
        assertTrue(automaton.accepts("44"));
        assertTrue(automaton.accepts("29"));
        assertFalse(automaton.accepts(""));
        assertFalse(automaton.accepts("2"));
        assertFalse(automaton.accepts("89"));
        assertFalse(automaton.accepts("100"));
        assertFalse(automaton.accepts("0"));
    }

    /**
     * Test automatu od 0 do 13.
     */
    public final void testcreateFixedAutomatonFrom0() {
 
        NumericalRangeOperator spec = new NumericalRangeOperator(0, 13);
        NondeterministicAutomatonByThompsonApproach automaton =
                new NondeterministicAutomatonByThompsonApproach(spec.createFixedAutomaton());
 
        assertTrue(automaton.accepts("0"));
        assertTrue(automaton.accepts("1"));
        assertTrue(automaton.accepts("13"));
        assertTrue(automaton.accepts("12"));
        assertTrue(automaton.accepts("10"));
        assertTrue(automaton.accepts("5"));
        assertTrue(automaton.accepts("7"));
        assertFalse(automaton.accepts(""));
        assertFalse(automaton.accepts("14"));
        assertFalse(automaton.accepts("100"));
        assertFalse(automaton.accepts("113"));
        assertFalse(automaton.accepts("  0  "));
    }

    /**
     * Test zakresu od 10 do 11.
     */
    public final void testcreateFixedAutomatonFrom10To11() {
 
        NumericalRangeOperator spec = new NumericalRangeOperator(10, 11);
        NondeterministicAutomatonByThompsonApproach automaton =
                new NondeterministicAutomatonByThompsonApproach(spec.createFixedAutomaton());
 
        assertTrue(automaton.accepts("10"));
        assertTrue(automaton.accepts("11"));
        assertFalse(automaton.accepts(""));
        assertFalse(automaton.accepts("9"));
        assertFalse(automaton.accepts("12"));
        assertFalse(automaton.accepts("0"));
        assertFalse(automaton.accepts("  10"));
    }

    /**
     * Test fabryki.
     */
    public final void testFactory() {
        Factory factory = new Factory();
        assertEquals(factory.numberOfParams(), 2);
        ArrayList<String> params = new ArrayList<String>();
        params.add(0, "100");
        params.add(1, "200");
        assertEquals(factory.createOperator(params).getClass(),
                new NumericalRangeOperator(100, 200).getClass());
    }

    /**
     * Test metody toString.
     */
    public final void testtoString() {
        NumericalRangeOperator spec = new NumericalRangeOperator(0, 10);
        String string = spec.toString();
        assertEquals(string, "NUMERICAL_FROM_0_TO_10");
    }
}
