package pl.edu.amu.wmi.daut.re;

import junit.framework.TestCase;
import static org.junit.Assert.*;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.AutomatonByRecursion;
import pl.edu.amu.wmi.daut.re.NoWhitespaceOperator.Factory;
import java.util.ArrayList;;

/**
 * @author cole1911
 * Test klasy NoWhitespaceOperator.
 */
public class TestNoWhitespaceOperator extends TestCase {
    /**
     * Test createFixedAutomaton.
     */
    public void testcreateFixedAutomaton() {
         NoWhitespaceOperator operator = new NoWhitespaceOperator();
         AutomatonSpecification spec = operator.createFixedAutomaton();
         assertFalse(spec.isEmpty());
         assertFalse(spec.acceptEmptyWord());
         AutomatonByRecursion automaton = new AutomatonByRecursion(spec);

         assertFalse(automaton.accepts(" "));
         assertFalse(automaton.accepts("\n"));
         assertFalse(automaton.accepts("\t"));
         assertFalse(automaton.accepts("\f"));
         assertFalse(automaton.accepts("\u000B"));
         assertFalse(automaton.accepts("\r"));
         assertTrue(automaton.accepts("_"));
         assertTrue(automaton.accepts("a"));
         assertTrue(automaton.accepts("*"));
         assertTrue(automaton.accepts("9"));
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
