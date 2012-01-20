package pl.edu.amu.wmi.daut.re;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

class RhsEmptyException extends RuntimeException {
    private static final long serialVersionUID = 1L;
}

class OutOfRhsBordersException extends RuntimeException {
    private static final long serialVersionUID = 1L;
}

/**
 * Klasa reprezentująca regułę przepisywania gramatyki generatywnej.
 */
public class GrammarRule {

    private GrammarNonterminalSymbol lhsSymbol;
    private List<GrammarSymbol> rhsSymbols;

    /**
     * Reguła z pustą prawą stroną.
     * @param lhsSymbol - Symbol nieterminalny obecny z lewej strony zasady przepisywania.
     */
    public GrammarRule(GrammarNonterminalSymbol lhsSymbol) {
        this.lhsSymbol = lhsSymbol;
    };

    /**
     * Reguła unarna (z jednym symbolem po prawej stronie).
     * @param lhsSymbol - Symbol nieterminalny obecny z lewej strony reguły przepisywania.
     * @param rhsSymbol - Symbol obecny z prawej strony reguły przepisywania.
     */
    public GrammarRule(GrammarNonterminalSymbol lhsSymbol, GrammarSymbol rhsSymbol) {
        this.lhsSymbol = lhsSymbol;
        rhsSymbols = new Vector<GrammarSymbol>();
        rhsSymbols.add(rhsSymbol);
    };

    /**
     * Reguła binarna (z dwoma symbolami po prawej stronie).
     * @param lhsSymbol - Symbol nieterminalny obecny z lewej strony reguły przepisywania.
     * @param rhsFirstSymbol - Pierwszy symbol obecny z prawej strony reguły przepisywania.
     * @param rhsSecondSymbol - Drugi symbol obecny z prawej strony reguły przepisywania.
     */
    GrammarRule(GrammarNonterminalSymbol lhsSymbol, GrammarSymbol rhsFirstSymbol,
            GrammarSymbol rhsSecondSymbol) {
        this.lhsSymbol = lhsSymbol;
        rhsSymbols = new Vector<GrammarSymbol>();
        rhsSymbols.add(rhsFirstSymbol);
        rhsSymbols.add(rhsSecondSymbol);
    };

    /**
     * Reguła z dowolną liczbą symboli po prawej stronie.
     * @param lhsSymbol - Symbol nieterminalny obecny z lewej strony reguły przepisywania.
     * @param rhsSymbols - Lista symboli obecnych z prawej strony reguły przepisywania.
     *                     W kolejności występowania od lewej strony!
     */
    GrammarRule(GrammarNonterminalSymbol lhsSymbol, List<GrammarSymbol> rhsSymbols) {
        this.lhsSymbol = lhsSymbol;
        this.rhsSymbols = new Vector<GrammarSymbol>();
        for (GrammarSymbol symbol : rhsSymbols) {
            this.rhsSymbols.add(symbol);
        }
    };

    /**
     * Zwraca symbol po lewej stronie.
     */
    GrammarNonterminalSymbol getLhsSymbol() {
        return lhsSymbol;
    };

    /**
     * Zwraca pierwszy symbol po prawej stronie, jeśli nie ma takiego symbolu,
     * powinien być wyrzucany wyjątek.
     */
    GrammarSymbol getRhsFirstSymbol() {
        if (rhsSymbols == null) {
            throw new RhsEmptyException();
        } else {
            return rhsSymbols.get(0);
        }
    };

    /**
     * Zwraca drugi symbol po prawej stronie, jeśli nie ma takiego symbolu,
     * powinien być wyrzucany wyjątek.
     */
    GrammarSymbol getRhsSecondSymbol() {
        if (rhsSymbols == null) {
            throw new RhsEmptyException();
        } else if (rhsSymbols.size() == 1) {
            throw new OutOfRhsBordersException();
        } else {
            return rhsSymbols.get(1);
        }
    };

    /**
     * Zwraca listę symboli po prawej stronie.
     */
    List<GrammarSymbol> getRhsSymbols() {
        if (rhsSymbols == null) {
            return new Vector<GrammarSymbol>();
        } else {
            return Collections.unmodifiableList(rhsSymbols);
        }
    };

    /**
     * Zwraca arność (liczbę symboli po prawej stronie).
     */
    int getArity() {
        if (rhsSymbols == null) {
            return 0;
        } else {
            return rhsSymbols.size();
        }
    };

    /**
     * @return String przedstawiający czytelny dla człowieka zapis tej reguły przepisywania.
     */
    @Override
    public String toString() {

        StringBuffer readable = new StringBuffer(lhsSymbol.toString() + " -> ");

        if (!((rhsSymbols == null) || rhsSymbols.isEmpty())) {
            for (GrammarSymbol g : rhsSymbols) {
                readable.append(g.toString());
            }
        }

        return readable.toString();
    };
}
