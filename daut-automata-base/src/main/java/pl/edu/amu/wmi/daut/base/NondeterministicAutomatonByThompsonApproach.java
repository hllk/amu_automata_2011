package pl.edu.amu.wmi.daut.base;

import java.util.List;
import java.util.LinkedList;

class NondeterministicAutomatonByThompsonApproach implements Acceptor {

    NondeterministicAutomatonByThompsonApproach(final AutomatonSpecification specification) {
        automaton = specification;
    }

    @Override
    public boolean accepts(final String text) {
        accept = false;
        int i = 0;
        int limit = text.length();
        boolean added;
        currentStates = new LinkedList<State>();
        List<State> temporaryStates = new LinkedList<State>();
        List<State> pStates = new LinkedList<State>();

        currentStates.add(automaton.getInitialState());

        do {

            do {
                added = false;

                for (State someState : currentStates) {
                    List<State> epsilonStates =
                            new LinkedList<State>(epsilonClosure(someState));

                    for (State eState : epsilonStates) {
                        if (!currentStates.contains(eState)
                                && !pStates.contains(eState)) {
                            pStates.add(eState);
                            added = true;
                        }
                    }
                }
                currentStates.addAll(pStates);
                pStates.clear();
            } while (added);


            if (limit != 0 && i != limit) {
                for (State someState : currentStates) {
                    List<OutgoingTransition> someStateTransitions = new
                            LinkedList<OutgoingTransition>(
                            automaton.allOutgoingTransitions(someState));

                    for (OutgoingTransition transition : someStateTransitions) {
                        if (transition.getTransitionLabel().canAcceptCharacter(text.charAt(i))
                                && !temporaryStates.contains(transition.getTargetState())) {
                            temporaryStates.add(transition.getTargetState());
                        }
                    }
                }

                currentStates.clear();
                currentStates.addAll(temporaryStates);
                temporaryStates.clear();
            }

            i++;

        } while (i <= limit);

        for (State someState : currentStates) {
            if (automaton.isFinal(someState)) {
                accept = true;
            }
        }

        return accept;
    }

    private List<State> epsilonClosure(State state) {
        List<State> epsilonStates = new LinkedList<State>();
        List<State> temporaryStates = new LinkedList<State>();
        List<State> pStates = new LinkedList<State>();
        boolean added;

        epsilonStates.add(state);

        do {
            added = false;

            for (State someState : epsilonStates) {
                List<OutgoingTransition> someStateTransitions = new
                        LinkedList<OutgoingTransition>(
                        automaton.allOutgoingTransitions(someState));

                for (OutgoingTransition transition : someStateTransitions) {
                    if (transition.getTransitionLabel().canBeEpsilon()
                            && !temporaryStates.contains(transition.getTargetState())) {
                        temporaryStates.add(transition.getTargetState());
                    }
                }

                for (State tState : temporaryStates) {
                    if (!epsilonStates.contains(tState)
                            && !pStates.contains(tState)) {
                        pStates.add(tState);
                        added = true;
                    }
                }
                temporaryStates.clear();
            }

            epsilonStates.addAll(pStates);
            pStates.clear();

        } while (added);

        return epsilonStates;
    }
    private List<State> currentStates;
    private final AutomatonSpecification automaton;
    private boolean accept;
};
