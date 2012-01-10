package pl.edu.amu.wmi.daut.re;

import java.util.ArrayList;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import junit.framework.TestCase;
import pl.edu.amu.wmi.daut.base.AutomatonByRecursion;
import pl.edu.amu.wmi.daut.re.TabOperator.Factory;

/**
 * Testy klasy TabOperator.
 */
public class TestTabOperator extends TestCase {

    /**
     * Konstruuje automat, a nastepnie go testuje.
     */
    public final void testAutomaton() {

        TabOperator operator = new TabOperator();
        AutomatonSpecification spec = operator.createFixedAutomaton();
        assertFalse(spec.isEmpty());
        assertFalse(spec.acceptEmptyWord());

        AutomatonByRecursion automaton = new AutomatonByRecursion(spec);
        assertFalse(automaton.accepts("\n"));
        assertFalse(automaton.accepts("a"));
        assertFalse(automaton.accepts("b"));
        assertFalse(automaton.accepts("%"));
        assertFalse(automaton.accepts(" "));
        assertFalse(automaton.accepts(""));
        assertFalse(automaton.accepts("\r"));
        assertTrue(automaton.accepts("\t"));
        assertTrue(automaton.accepts(Character.toString((char) 9)));
    }

    /**
     * Test fabryki.
     */
    public final void testFactory() {

        Factory factory = new Factory();
        RegexpOperator operator2 = factory.createOperator(new ArrayList<String>());
        int arity = operator2.arity();
        assertEquals(arity, 0);

    }
}
