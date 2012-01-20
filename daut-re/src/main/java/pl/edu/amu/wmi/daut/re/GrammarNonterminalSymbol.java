package pl.edu.amu.wmi.daut.re;

import java.util.Random;

/**
 * Klasa opisująca symbol nieterminalny gramatyki generatywnej.
 */
public class GrammarNonterminalSymbol implements GrammarSymbol {

    private static final int ALLCHARRANGE = 26;
    private static final int FIRSTCAPITALLETTERNR = 8;
    private Character myChar;

    /**
     * Konstruktor dobierający symbolowi LOSOWĄ(być może powtarzającą się) dużą literę,
     * aby móc potem ten symbol dać do metody toString().
     */
    public GrammarNonterminalSymbol() {
        int randomUppercaseCharInt = (new Random()).nextInt(ALLCHARRANGE) + FIRSTCAPITALLETTERNR;
        char[] tempTab = Character.toChars(randomUppercaseCharInt);
        myChar = tempTab[0];
    }

    /**
     * Konstruktor przyporządkowujący symbolowi znak viewSymbol
     * jako znak zwracany dla metody toString().
     * @param viewSymbol
     */
    public GrammarNonterminalSymbol(char viewSymbol) {
        myChar = viewSymbol;
    }

    @Override
    public boolean isTerminalSymbol() {
        return false;
    }

    @Override
    public String toString() {
        return myChar.toString();
    }

}
