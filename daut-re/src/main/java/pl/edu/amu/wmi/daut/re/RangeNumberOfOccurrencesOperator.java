package pl.edu.amu.wmi.daut.re;

import java.util.List;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.EpsilonTransitionLabel;
import pl.edu.amu.wmi.daut.base.State;

/**
 * Klasa reprezentująca operator '{n,m}'.
 */
public class RangeNumberOfOccurrencesOperator extends UnaryRegexpOperator {
   /**
    * Zmienne dla ilosci wystapen wyrazenia.
    */
    private int max;
    private int min;

   /**
    * Konstruktor klasy.
    */
    public RangeNumberOfOccurrencesOperator(int n, int m) {
       this.max = m;
       this.min = n;
   }

   /**
    * Glowna metoda klasy.
    */
    public AutomatonSpecification createAutomatonFromOneAutomaton(
            AutomatonSpecification subautomaton) {
 
        AutomatonSpecification finalAutomaton = subautomaton.clone();

        if (this.min == 0)
            finalAutomaton.markAsFinal(finalAutomaton.getInitialState());

        for (int i = 1; i < this.max; i++) {
            State newState = finalAutomaton.addState();

            for (State state : finalAutomaton.allStates()) {
                if (finalAutomaton.isFinal(state)) {
                    finalAutomaton.addTransition(state, newState, new EpsilonTransitionLabel());
                    if (i < this.min)
                        finalAutomaton.unmarkAsFinalState(state);
                }
            }
            finalAutomaton.insert(newState, subautomaton);
        }
        return finalAutomaton;
    }

     /**
     * Fabryka operatora.
     */
    public static class Factory extends UnaryRegexpOperatorFactory {

        @Override
        public int numberOfParams() {
            return 2;
        }

        protected RegexpOperator doCreateOperator(List<String> params) {
            return new RangeNumberOfOccurrencesOperator(Integer.parseInt(params.get(0)),
                    Integer.parseInt(params.get(1)));
        }
    }

    /**
     * Metoda toString().
     */
    @Override
    public String toString() {
        return "RANGE_NUMBER_OF_OCCURRENCES_FROM_" + min + "_TO_" + max;
    }

};
