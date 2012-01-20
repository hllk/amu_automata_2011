package pl.edu.amu.wmi.daut.re;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

/**
 * Test klasy GrammarRule.
 */
public class TestGrammarRule extends TestCase {

    /**
     * Test reguły z pustą prawą stroną.
     */
    public final void testRuleWithEmptyRightSide() {
        GrammarNonterminalSymbol nonterminal = new GrammarNonterminalSymbol('T');
        GrammarRule rule = new GrammarRule(nonterminal);

        assertEquals(0, rule.getArity());

        assertSame(nonterminal, rule.getLhsSymbol());
        assertEquals(0, rule.getRhsSymbols().size());
        assertEquals("T -> ", rule.toString());

        boolean thrown = false;
        try {
            rule.getRhsFirstSymbol();
        } catch (RhsEmptyException e) {
            thrown = true;
        }

        assertTrue(thrown);

        try {
            rule.getRhsSecondSymbol();
        } catch (RhsEmptyException e) {
            thrown = false;
        }

        assertFalse(thrown);
    }

    /**
     * Test reguły unarnej.
     */
    public final void testUnaryRule() {
        GrammarTerminalSymbol terminal = new GrammarTerminalSymbol('a');
        GrammarNonterminalSymbol nonterminal = new GrammarNonterminalSymbol('T');
        GrammarRule rule = new GrammarRule(nonterminal, terminal);

        assertSame(terminal, rule.getRhsFirstSymbol());

        boolean thrown = false;

        try {
            rule.getRhsSecondSymbol();
        } catch (OutOfRhsBordersException e) {
            thrown = true;
        }

        assertTrue(thrown);

        assertEquals(1, rule.getRhsSymbols().size());

        assertEquals(1, rule.getArity());

        assertEquals("T -> a", rule.toString());

    }

    /**
     * Test reguły binarnej.
     */
    public final void testBinaryRule() {
        GrammarNonterminalSymbol nonterminal = new GrammarNonterminalSymbol('T');
        GrammarTerminalSymbol terminal = new GrammarTerminalSymbol('a');
        GrammarRule rule = new GrammarRule(nonterminal, terminal, nonterminal);

        assertSame(nonterminal, rule.getRhsSecondSymbol());

    }

    /**
     * Test reguły z dowolną liczbą symboli terminalny
     * i nieterminalnych po prawej stronie.
     */
    public final void testRuleWithAnyNumberOfSymbols() {
        GrammarTerminalSymbol terminalA = new GrammarTerminalSymbol('a');
        GrammarTerminalSymbol terminalB = new GrammarTerminalSymbol('b');
        GrammarNonterminalSymbol nonterminal = new GrammarNonterminalSymbol('T');
        List<GrammarSymbol> listOfSymbols = new ArrayList<GrammarSymbol>();

        listOfSymbols.add(terminalA);
        listOfSymbols.add(terminalB);
        listOfSymbols.add(nonterminal);

        GrammarRule rule = new GrammarRule(nonterminal, listOfSymbols);

        assertEquals(listOfSymbols, rule.getRhsSymbols());
    }

}
