package pl.edu.amu.wmi.daut.re;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * Klasa reprezentująca gramatykę generatywną.
 */
public class Grammar {

    private GrammarNonterminalSymbol startSymbol;
    private List<GrammarRule> rules;

    /**
     * Konstruktor - rules to lista reguł, startSymbol - symbol początkowy.
     */
    public Grammar(List<GrammarRule> rules, GrammarNonterminalSymbol startSymbol) {
        this.startSymbol = startSymbol;
        for (GrammarRule rule : rules) {
            this.rules.add(rule);
        }
    }

    /**
     * Konstruktor, startSymbol - symbol początkowy, zaczyna z pustą listą reguł.
     */
    public Grammar(GrammarNonterminalSymbol startSymbol) {
        this.startSymbol = startSymbol;
        rules = new Vector<GrammarRule>();
    }

    /**
     * Dodaje regułę do gramatyki.
     */
    public void addRule(GrammarRule rule) {
        rules.add(rule);
    }

    /**
     * Zwraca listę wszystkich reguł.
     */
    public List<GrammarRule> allRules() {
        return Collections.unmodifiableList(rules);
    }

    /**
     * Zwraca symbol początkowy.
     */
    GrammarNonterminalSymbol getStartSymbol() {
        return startSymbol;
    }
}
