package pl.edu.amu.wmi.daut.re;
import junit.framework.TestCase;

/**
 * Test klasy GrammarNonterminalSymbol.
 */
public class TestGrammarNonterminalSymbol extends TestCase {

    /**
     * Test wszystkich metod klasy.
     */
    public final void testFirst() {
        GrammarNonterminalSymbol symbol = new GrammarNonterminalSymbol('a');
        GrammarNonterminalSymbol symbol2 = new GrammarNonterminalSymbol();

         assertFalse(symbol.isTerminalSymbol());

         assertEquals("a", symbol.toString());
    }

}
