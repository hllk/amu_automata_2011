package pl.edu.amu.wmi.daut.re;

import java.util.ArrayList;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import junit.framework.TestCase;
import pl.edu.amu.wmi.daut.base.AutomatonByRecursion;
import pl.edu.amu.wmi.daut.re.NoDigitOperator.Factory;

/**
 *
 * Test klasy NoDigitOperator.
 */
public class TestNoDigitOperator extends TestCase {

    /**
     * Test metody createFixedAutomaton.
     */
    public void testCreateFixedAutomaton() {

        NoDigitOperator operator = new NoDigitOperator();
        AutomatonSpecification spec = operator.createFixedAutomaton();
        assertFalse(spec.isEmpty());
        assertFalse(spec.acceptEmptyWord());

        AutomatonByRecursion noDigitAutomaton = new AutomatonByRecursion(spec);
        assertFalse(noDigitAutomaton.accepts("0"));
        assertFalse(noDigitAutomaton.accepts("1"));
        assertFalse(noDigitAutomaton.accepts("2"));
        assertFalse(noDigitAutomaton.accepts("3"));
        assertFalse(noDigitAutomaton.accepts("4"));
        assertFalse(noDigitAutomaton.accepts("5"));
        assertFalse(noDigitAutomaton.accepts("6"));
        assertFalse(noDigitAutomaton.accepts("7"));
        assertFalse(noDigitAutomaton.accepts("8"));
        assertFalse(noDigitAutomaton.accepts("9"));
        assertFalse(noDigitAutomaton.accepts("12"));
        assertFalse(noDigitAutomaton.accepts("123"));
        assertFalse(noDigitAutomaton.accepts("abc"));
        assertTrue(noDigitAutomaton.accepts("a"));
        assertTrue(noDigitAutomaton.accepts("b"));
        assertFalse(noDigitAutomaton.accepts(""));

    }

    /**
     * Test fabryki.
     */
    public void testFactory() {

        Factory factory = new Factory();
        assertEquals(factory.numberOfParams(), 0);
        RegexpOperator operator2 = factory.createOperator(new ArrayList<String>());
        int arity = operator2.arity();
        assertEquals(arity, 0);

    }
}
