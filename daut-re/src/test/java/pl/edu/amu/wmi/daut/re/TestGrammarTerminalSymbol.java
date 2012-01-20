package pl.edu.amu.wmi.daut.re;
import junit.framework.TestCase;

/**
 * Testy klasy GrammarTerminalSymbol.
 */
public class TestGrammarTerminalSymbol extends TestCase {

    /**
     * Prosty test sprawdzający działanie wszystkich metod klasy.
     */
    public final void testGrammarTerminal() {
        GrammarTerminalSymbol terminal = new GrammarTerminalSymbol('a');

        assertEquals('a', terminal.getSymbol());
        assertTrue(terminal.isTerminalSymbol());
        assertEquals("a", terminal.toString());
    }

}
