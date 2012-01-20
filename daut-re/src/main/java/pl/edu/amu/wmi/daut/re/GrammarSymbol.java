package pl.edu.amu.wmi.daut.re;

/**
 * Interface opisujący symbol gramatyki generatywnej.
 */
public interface GrammarSymbol {

    /**
     * Metoda sprawdzająca czy symbol gramatyki jest symbolem terminalnym.
     * @return true lub false
     */
    boolean isTerminalSymbol();
}
