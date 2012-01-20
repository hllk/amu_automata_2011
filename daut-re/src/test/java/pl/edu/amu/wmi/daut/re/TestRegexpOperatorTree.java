package pl.edu.amu.wmi.daut.re;

import java.util.ArrayList;
import junit.framework.TestCase;

/**
 * Testy klasy RegexpOperatorTree.
 */
public class TestRegexpOperatorTree extends TestCase {

    /**
     * Jeden liść.
     */
    public final void testLeaf() {
        RegexpOperator nullOperator = (new DigitOperator.Factory()).createOperator(
                new ArrayList<String>());

        RegexpOperatorTree rOT = new RegexpOperatorTree(nullOperator,
                new ArrayList<RegexpOperatorTree>());

        assertTrue(rOT.getRoot().arity() == 0);
        assertTrue(rOT.getSubtrees().isEmpty());
        assertFalse(rOT.getRoot().arity() != rOT.getSubtrees().size());
    }

    /**
     * Trójpoziomowe drzewo operatorów.
     */
    public final void testThreeLayerTree() {
        RegexpOperator nul1 = (new DigitOperator.Factory()).createOperator(
                new ArrayList<String>());
        RegexpOperator nul2 = (new NoDigitOperator.Factory()).createOperator(
                new ArrayList<String>());
        RegexpOperator nul3 = (new WhitespaceOperator.Factory()).createOperator(
                new ArrayList<String>());

        RegexpOperator una1 = (new OptionalityOperator.Factory()).createOperator(
                new ArrayList<String>());

        RegexpOperator bin1 = (new AlternativeOperator.Factory()).createOperator(
                new ArrayList<String>());
        RegexpOperator bin2 = (new AnyOrderOperator.Factory()).createOperator(
                new ArrayList<String>());

        RegexpOperatorTree c3 = new RegexpOperatorTree(nul3, new ArrayList<RegexpOperatorTree>());

        RegexpOperatorTree c2 = new RegexpOperatorTree(nul2, new ArrayList<RegexpOperatorTree>());
        RegexpOperatorTree c1 = new RegexpOperatorTree(nul1, new ArrayList<RegexpOperatorTree>());

        ArrayList<RegexpOperatorTree> c3tree = new ArrayList<RegexpOperatorTree>();
        c3tree.add(c3);
        RegexpOperatorTree b2 = new RegexpOperatorTree(una1, c3tree);

        ArrayList<RegexpOperatorTree> c21tree = new ArrayList<RegexpOperatorTree>();
        c21tree.add(c2);
        c21tree.add(c1);
        RegexpOperatorTree b1 = new RegexpOperatorTree(bin1, c21tree);

        ArrayList<RegexpOperatorTree> b21tree = new ArrayList<RegexpOperatorTree>();
        b21tree.add(b2);
        b21tree.add(b1);
        RegexpOperatorTree theTree = new RegexpOperatorTree(bin2, b21tree);

        assertEquals(theTree.getRoot(), bin2);
        assertTrue(theTree.getRoot().arity() == theTree.getSubtrees().size());
        assertFalse(theTree.getSubtrees().get(0).getRoot().arity() == 0);
        assertFalse(theTree.getSubtrees().get(0).getSubtrees().get(0).getRoot().arity() != 0);
        assertEquals(theTree.getSubtrees().get(1).getSubtrees().get(1).getRoot(), nul1);
        assertTrue(theTree.getSubtrees().get(1).getSubtrees().get(0).getRoot().arity() == 0);

    }

    /**
     * Nieudana budowa drzewa.
     */
    public final void testForceException() {
        RegexpOperator binary = (new ConcatenationOperator.Factory()).createOperator(
                new ArrayList<String>());

        try {
            RegexpOperatorTree thisTree = new RegexpOperatorTree(binary,
                    new ArrayList<RegexpOperatorTree>());
        } catch (RuntimeException e) {
            assertNotNull(e);
        }
    }
}
