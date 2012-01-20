/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.wmi.daut.re;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NondeterministicAutomatonByThompsonApproach;

/**
 *  Testy klasy OctSingleCharacterOperator.
 * 
 */
public class TestOctSingleCharacterOperator extends TestCase {

    /**
     * Test konstruktora.
     */
    public void testOctSingleCharacterOperator() {

        OctSingleCharacterOperator operator = new OctSingleCharacterOperator(120);

        assertNotNull(operator);
        assertEquals(operator.getOctValue(), 120);
        assertFalse(operator.getOctValue() == 80);
        try {
            operator = new OctSingleCharacterOperator(200);
        } catch (Exception ex) {
            assertTrue(ex instanceof RuntimeException);
        }

    }

    /**
     * Test CreateFixedAutomaton.
     * @throws Exception
     */
    public final void testCreateFixedAutomaton() throws Exception {

        OctSingleCharacterOperator operator = new OctSingleCharacterOperator(120);
        AutomatonSpecification automaton = operator.createFixedAutomaton();
        NondeterministicAutomatonByThompsonApproach result =
                new NondeterministicAutomatonByThompsonApproach(automaton);

        assertFalse(automaton.isEmpty());

        assertTrue(result.accepts("P"));
        assertFalse(result.accepts("PP"));
        assertFalse(result.accepts(" P"));
        assertFalse(result.accepts("cc"));
        assertFalse(result.accepts("a"));

        assertEquals(120, operator.getOctValue());
    }

    /**
     * Test fabryki.
     */
    public final void testFactory() {

        OctSingleCharacterOperator.Factory factory = new OctSingleCharacterOperator.Factory();
        assertNotNull(factory);
        List<String> list = new ArrayList<String>();
        assertNotNull(list);
        list.add("141");
        OctSingleCharacterOperator operator =
                (OctSingleCharacterOperator) factory.doCreateOperator(list);
        assertNotNull(operator);
        assertEquals(1, factory.numberOfParams());
        assertEquals(141, operator.getOctValue());
        assertFalse(142 == operator.getOctValue());
        assertEquals('a', operator.getCharacter());
        assertFalse(operator.getCharacter() == 'Z');
        assertEquals("a".charAt(0), operator.getCharacter());
        list.clear();
        list.add("132");
        operator = (OctSingleCharacterOperator) factory.createOperator(list);
        assertNotNull(operator);
        assertEquals(1, factory.numberOfParams());
        assertEquals(132, operator.getOctValue());
        assertFalse(142 == operator.getOctValue());
        assertEquals('Z', operator.getCharacter());
        assertFalse(operator.getCharacter() == 'a');
        assertEquals("Z".charAt(0), operator.getCharacter());
        list.clear();
        list.add("999");
        operator = (OctSingleCharacterOperator) factory.createOperator(list);
        assertNull(operator);
    }
}
