package pl.edu.amu.wmi.daut.base;

import java.util.Stack;
import java.util.List;

/**
 * Klasa sprawdzajaca czy niedeterministyczny automat skonczenie stanowy
 * akceptuje dany napis.
 */
public class AutomatonByStack implements Acceptor {


    /**
    * Konstruktor przyjmujacy obiekt klasy AutomatonSpecification.
     */
    public AutomatonByStack(final AutomatonSpecification specification) {
        automaton = specification;
    }

    private AutomatonSpecification automaton;


    /**
     * Metoda sprawdzajaca czy niedeterministyczny automat skonczenie stanowy
     * akceptuje dany napis.
     */
    public boolean accepts(final String text) {
        TransitionLabel currentLabel;
        Stack stack = new Stack();
        stack.push(automaton.getInitialState());
        stack.push(text);
        while (!stack.empty()) {
            String v = (String) stack.pop();
            State r = (State) stack.pop();

            if (v.length() == 0) {
                if (automaton.isFinal(r)) {
                    return true;
                }
            } else {
                String u = v.substring(1);
                List<OutgoingTransition> allOutTransitions;
                allOutTransitions = automaton.allOutgoingTransitions(r);
                for (OutgoingTransition transition : allOutTransitions) {
                    currentLabel = transition.getTransitionLabel();
                    if (currentLabel.canBeEpsilon()) {
                                      throw new UnsupportedOperationException();
                    }
                    if (currentLabel.canAcceptCharacter(v.charAt(0))) {
                        State p = transition.getTargetState();
                        stack.push(p);
                        stack.push(u);
                    }
                }
              }
        }
        return false;
    }
}
