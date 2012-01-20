package pl.edu.amu.wmi.daut.re;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Klasa reprezentujaca.
 */
public class RegexpOperatorTree {

    private static class ArityException extends RuntimeException {

        public ArityException() {
        }
    }
    private RegexpOperator root;
    private List<RegexpOperatorTree> subtrees;

    /**
     * Konstruuje drzewo z korzeniem operator i poddrzewami subtrees.
     *
     * Jeśli liczba poddrzew nie zgadza się z arnością operatora, powinien być wyrzucany wyjątek.
     */
    RegexpOperatorTree(RegexpOperator operator, List<RegexpOperatorTree> subtrees) {

        if (operator.arity() == subtrees.size()) {
            this.root = operator;
            this.subtrees = subtrees;
        } else {
            throw new ArityException();
        }
    }

    /**
     * Zwraca korzeń.
     */
    RegexpOperator getRoot() {
        return root;
    }

    /**
     * Zwraca listę poddrzew.
     */
    List<RegexpOperatorTree> getSubtrees() {
        return subtrees;
    }

    /**
     * Zwraca drzewo w formie bardziej czytelnej,
     * np. dla wyrażenia (ab)*|c wypisze:
     * ALTERNATIVE
     * |_KLEENE_STAR
     * |  |_CONCATENATION
     * |     |_SINGLE_CHAR_a
     * |     |_SINGLE_CHAR_b
     * |_SINGLE_CHAR_c
     */
    String getHumanReadableFormat() {
        StringBuffer toString = new StringBuffer();
        Stack<RegexpOperatorTree> stack = new Stack<RegexpOperatorTree>();
        Stack<RegexpOperatorTree> path = new Stack<RegexpOperatorTree>();
        List<RegexpOperatorTree> drawn = new ArrayList<RegexpOperatorTree>();
        List<RegexpOperatorTree> tempList = new ArrayList<RegexpOperatorTree>();
        int level = 0;

        RegexpOperatorTree tempTree = null;
        path.push(this);

        tempList.addAll(getSubtrees());
        for (int i = tempList.size(); i >= 0; --i)
            stack.push(tempList.get(i));

        tempList.clear();

        toString.append(getRoot().toString() + "\n");
        drawn.add(this);

        while (!stack.empty()) {

            tempTree = stack.pop();

            if (level != 0) {
                toString.append("|");
                for (int i = 0; i < level; i++)
                    toString.append("  ");
            }

            tempList.addAll(path.peek().getSubtrees());
            while (!tempList.contains(tempTree)) {
                tempList.clear();
                level--;
                path.pop();
                tempList.addAll(path.peek().getSubtrees());
            }
            tempList.clear();

            toString.append("|_" + tempTree.getRoot().toString() + "\n");
            drawn.add(tempTree);

            if (!tempTree.getSubtrees().isEmpty()) {
                tempList.addAll(tempTree.getSubtrees());
                for (int i = tempList.size(); i >= 0; --i)
                    stack.push(tempList.get(i));
                path.push(tempTree);
                tempList.clear();
                level++;
            } else {
                List<RegexpOperatorTree> listOfTrees = new ArrayList<RegexpOperatorTree>();
                boolean thereIsSomethingToDraw = false;

                RegexpOperatorTree temporaryTree = path.pop();
                listOfTrees.addAll(temporaryTree.getSubtrees());

                for (RegexpOperatorTree tree : listOfTrees)
                if (!drawn.contains(tree))
                    thereIsSomethingToDraw = true;

                if (thereIsSomethingToDraw) {
                    path.push(temporaryTree);
                } else {
                    level--;
                }

            }

        }

        return toString.toString();
    }
}

