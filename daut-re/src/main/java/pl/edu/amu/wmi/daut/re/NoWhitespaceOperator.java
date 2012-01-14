package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.State;
import pl.edu.amu.wmi.daut.base.ComplementCharClassTransitionLabel;
import java.util.List;

 /**
  * Klasa reprezentująca dowolny znak niebędący białym znakiem.
  */
public class NoWhitespaceOperator extends NullaryRegexpOperator {
    private static final String WHITESPACE_CHARS = "\t\n\f\r \u000B";

    /**
     * Główna metoda klasy.
     */
    @Override
    public AutomatonSpecification createFixedAutomaton() {
        AutomatonSpecification noWhitespaceAutomaton = new NaiveAutomatonSpecification();
        State state1 = noWhitespaceAutomaton.addState();
        State state2 = noWhitespaceAutomaton.addState();
        noWhitespaceAutomaton.addTransition(state1, state2,
              new ComplementCharClassTransitionLabel(WHITESPACE_CHARS));
        noWhitespaceAutomaton.markAsInitial(state1);
        noWhitespaceAutomaton.markAsFinal(state2);
        return noWhitespaceAutomaton;
    }

    /**
     * Fabryka operatora.
     */
    public static class Factory extends NullaryRegexpOperatorFactory {
        @Override
        public int numberOfParams() {
            return 0;
        }
        @Override
        protected RegexpOperator doCreateOperator(List<String> params) {
        return new NoWhitespaceOperator();
        }
    }

}
