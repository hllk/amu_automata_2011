package pl.edu.amu.wmi.daut.re;

import java.util.ArrayList;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import junit.framework.TestCase;
import pl.edu.amu.wmi.daut.base.AutomatonByRecursion;
import pl.edu.amu.wmi.daut.re.DigitOperator.Factory;

/**
 *
 * Test klasy DigitOperator.
 */
public class TestDigitOperator extends TestCase {

    /**
     * Test metody createFixedAutomaton.
     */
    public void testCreateFixedAutomaton() {

        DigitOperator operator = new DigitOperator();
        AutomatonSpecification spec = operator.createFixedAutomaton();
        assertFalse(spec.isEmpty());
        assertFalse(spec.acceptEmptyWord());

        AutomatonByRecursion automaton = new AutomatonByRecursion(spec);
        assertTrue(automaton.accepts("0"));
        assertTrue(automaton.accepts("9"));
        assertTrue(automaton.accepts("1"));
        assertTrue(automaton.accepts("7"));
        assertTrue(automaton.accepts("5"));
        assertFalse(automaton.accepts(""));
        assertFalse(automaton.accepts("a"));
        assertFalse(automaton.accepts("b"));
        assertFalse(automaton.accepts("93"));
        assertFalse(automaton.accepts("100"));
        assertFalse(automaton.accepts("207"));

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
