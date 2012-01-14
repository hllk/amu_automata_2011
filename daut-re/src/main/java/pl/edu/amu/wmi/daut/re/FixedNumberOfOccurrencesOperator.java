package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.EpsilonTransitionLabel;
import pl.edu.amu.wmi.daut.base.State;
import java.util.List;

/**
* Klasa reprezentującą operator '{n}' z wyrażeń regularnych.
*/
public class FixedNumberOfOccurrencesOperator extends UnaryRegexpOperator {

    private int n;

    /**
     * Konstruktor klasy.
     */
    public FixedNumberOfOccurrencesOperator(int a) {
        this.n = a;
    }

    /**
     * Główna metoda klasy.
     */
    public AutomatonSpecification createAutomatonFromOneAutomaton(
            AutomatonSpecification subautomaton) {

        AutomatonSpecification automatbudowany = subautomaton.clone();

        if (this.n == 0)
            automatbudowany.markAsFinal(automatbudowany.getInitialState());

        if (n > 1) {
            State newState = automatbudowany.addState();

            for (State state : automatbudowany.allStates()) {
                if (automatbudowany.isFinal(state)) {
                    automatbudowany.addTransition(state,
                                newState,
                                new EpsilonTransitionLabel());
                    automatbudowany.unmarkAsFinalState(state);
                }
            }
            automatbudowany.insert(newState, subautomaton);
        }

        for (int i = 1; i < this.n - 1; i++) {
            State newState1 = automatbudowany.addState();

            for (State state : automatbudowany.allStates()) {
                if (automatbudowany.isFinal(state)) {
                    automatbudowany.addTransition(state,
                                newState1,
                                new EpsilonTransitionLabel());
                    automatbudowany.unmarkAsFinalState(state);
                }
            }
            automatbudowany.insert(newState1, subautomaton);
        }
        return automatbudowany;
    }

     /**
     * Fabryka operatora.
     */
    public static class Factory extends UnaryRegexpOperatorFactory {

        @Override
        public int numberOfParams() {
            return 1;
        }

        protected RegexpOperator doCreateOperator(List<String> params) {
            return new FixedNumberOfOccurrencesOperator(Integer.parseInt(params.get(0)));
        }
    }

    /**
     * Metoda toString().
     */
    @Override
    public String toString() {
        return "FIXED_" + n + "_TIMES";
    }

}
