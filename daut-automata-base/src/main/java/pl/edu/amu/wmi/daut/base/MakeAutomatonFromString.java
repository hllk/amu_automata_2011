package pl.edu.amu.wmi.daut.base;

import java.util.List;

/**
 * 
 * @author tomekd
 * 
 * Klasa odpowiedzialna za zbudowanie poprawnego automatu, po pobraniu
 * informacji z metody toString() wykonanej na wybranym automacie.
 *
 */

public class MakeAutomatonFromString {
    private static final int TRANSITION_PARTS = 3;
    private static final int LABEL_LENGHT = 4;
    private static final int FINAL_STR_INDEX = 3;
    private static final int MINIMUM_TABLE_SIZE = 5;
    private String[] codeTable;
    private List<State> stateList;
    private int transitionPoiner, initialPointer;
    private AutomatonSpecification automaton;

    /**
     * Konstruktor przyjmujący za argumenty automat (powinien być pusty!) i
     * strumień znaków, który pochodzi z metody toString() automatu.
     * @param emptyAutomaton pusty automat, na którym chcemy zbudować wybrany automat
     * @param description opis automatu w formie, jaką zwraca metoda toString()
     */
    public MakeAutomatonFromString(AutomatonSpecification emptyAutomaton, String description) {
        automaton = emptyAutomaton;
        codeTable = description.split("\\s+");
        stateList = automaton.allStates();
    }

    private boolean isCorrectStateName(String name) {
        if (name.length() < 2)
            return false;
        if (!name.startsWith("q"))
            return false;
        try {
            Integer.decode(name.substring(1));
        } catch (NumberFormatException exp) {
            return false;
        }
        return true;
    }

    private boolean isCorrectLabel(String name) {
        if (name.equals("-epsilon->"))
            return true;
        if (name.equals("-ANY->"))
            return true;
        if (name.startsWith("-[") && name.endsWith("]->"))
            return true;
        if (!(name.length() == LABEL_LENGHT))
            return false;
        if (!name.startsWith("-"))
            return false;
        if (!name.endsWith("->"))
            return false;
        return true;
    }

    private void checkStates() throws StructureException {
        for (int i = 2; i < codeTable.length; ++i) {
            if (!isCorrectStateName(codeTable[i])) {
                if (!codeTable[i].equals("-Transitions:")) {
                    throw new StructureException();
                } else {
                    transitionPoiner = i;
                    break;
                }

            }
        }
    }

    private void checkTransitions() throws StructureException {
        for (int i = transitionPoiner + 1; i < codeTable.length; i += TRANSITION_PARTS) {
            if (codeTable[i].equals("-Initial")) {
                initialPointer = i;
                break;
            } else {
                if (!isCorrectStateName(codeTable[i]))
                    throw new StructureException();
                if (!isCorrectLabel(codeTable[i + 1]))
                    throw new StructureException();
                if (!isCorrectStateName(codeTable[i + 2]))
                    throw new StructureException();
            }
        }
    }

    private void isCorrectSpecialState() throws StructureException {
        if (!codeTable[initialPointer + 1].equals("state:"))
            throw new StructureException();
        if (!isCorrectStateName(codeTable[initialPointer + 2]))
            throw new StructureException();
        if (!codeTable[initialPointer + FINAL_STR_INDEX]
                .equals("-Final"))
            throw new StructureException();
        if (!codeTable[initialPointer + FINAL_STR_INDEX + 1]
                .equals("states:"))
            throw new StructureException();
        for (int it = initialPointer + FINAL_STR_INDEX + 2; it < codeTable.length; ++it) {
            if (!isCorrectStateName(codeTable[it]))
                throw new StructureException();
        }
    }

    private void isCorrectInitialWords() throws StructureException {
        if (codeTable.length < MINIMUM_TABLE_SIZE)
            throw new StructureException();
        if (!codeTable[0].equals("Automaton:"))
            throw new StructureException();
        if (!codeTable[1].equals("-States:"))
            throw new StructureException();
    }

    /**
     * Sprawdza czy podany ciag znaków jest poprawny; jeżeli tak
     * to dodaje stany i przejścia zgodnie z danym opisem automatu.
     * @throws StructureException zwracany, gdy podany ciąg znaków jest niepoprawny.
     */
    public void make() throws StructureException {
        isCorrectInitialWords();
        checkStates();
        checkTransitions();
        isCorrectSpecialState();
        constructGraph();
    }

    private int getIndex(String stateName) {
        String indexStr = stateName.substring(1);
        return Integer.decode(indexStr);
    }

    private TransitionLabel getLabel(String name) throws StructureException {
        if (name.length() == LABEL_LENGHT)
            return new CharTransitionLabel(name.charAt(1));
        if (name.equals("-epsilon->"))
            return new EpsilonTransitionLabel();
        if (name.startsWith("-[") && name.endsWith("]->"))
            return new ComplementCharClassTransitionLabel(name
                    .substring(2, name.length() - 2));
        if (name.equals("-ANY->"))
            return new AnyTransitionLabel();
        throw new StructureException();
    }

    private void constructGraph() throws StructureException {
        for (int i = 0; i < transitionPoiner - 2; ++i)
            automaton.addState();
        automaton.markAsInitial(stateList
                .get(getIndex(codeTable[initialPointer + 2])));
        for (int i = initialPointer + FINAL_STR_INDEX + 2; i < codeTable.length; ++i) {
            automaton.markAsFinal(stateList.get(getIndex(codeTable[i])));
        }
        for (int i = transitionPoiner + 1; i < initialPointer; i += TRANSITION_PARTS) {
            automaton.addTransition(stateList.get(getIndex(codeTable[i])),
                    stateList.get(getIndex(codeTable[i + 2])),
                    getLabel(codeTable[i + 1]));

        }

    }

}

