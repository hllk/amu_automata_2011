package pl.edu.amu.wmi.daut.re;

import java.util.List;
import java.util.ArrayList;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NondeterministicAutomatonByThompsonApproach;
import junit.framework.TestCase;

/**
 * Test klasy RegexpUtilities.
 */
public class TestRegexpUtilities extends TestCase {

    /**
     * Test metody createAutopmatonFromOperatorTree dla wyrażenia regularnego a.
     */
    public final void testTrivialRegexp() throws Exception {

        //Tworzymy drzewo dla wyrażenia regularnego a.
        RegexpOperator root = new SingleCharacterOperator('a');
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees);

        //Tworzymy automat dla tego drzewa.
        AutomatonSpecification automaton =
                RegexpUtilities.createAutomatonFromOperatorTree(tree);
        NondeterministicAutomatonByThompsonApproach result =
                new NondeterministicAutomatonByThompsonApproach(automaton);

        //Testujemy.
        assertTrue(result.accepts("a"));
        assertFalse(result.accepts("b"));
        assertFalse(result.accepts(""));
        assertFalse(result.accepts("aa"));
        assertFalse(result.accepts("testujtest"));

    }

    /**
     * Test metody createAutopmatonFromOperatorTree
     *  dla wyrażenia regularnego a|b.
     */
    public final void testRegexpWithAlternativeOperator() throws Exception {

        //Tworzymy drzewo dla wyrażenia regularnego a|b
        RegexpOperator root1 = new SingleCharacterOperator('a');
        List<RegexpOperatorTree> subtrees1 = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree subtree1 = new RegexpOperatorTree(root1, subtrees1);

        RegexpOperator root2 = new SingleCharacterOperator('b');
        List<RegexpOperatorTree> subtrees2 = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree subtree2 = new RegexpOperatorTree(root2, subtrees2);

        RegexpOperator root = new AlternativeOperator();
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        subtrees.add(subtree1);
        subtrees.add(subtree2);
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees);

        //Tworzymy automat dla tego drzewa.
        AutomatonSpecification automaton =
                RegexpUtilities.createAutomatonFromOperatorTree(tree);
        NondeterministicAutomatonByThompsonApproach result =
                new NondeterministicAutomatonByThompsonApproach(automaton);

        //Testujemy.
        assertTrue(result.accepts("a"));
        assertTrue(result.accepts("b"));
        assertFalse(result.accepts(""));
        assertFalse(result.accepts("ab"));
        assertFalse(result.accepts("aaaaa"));
        assertFalse(result.accepts("testujtest"));
    }

    /**
     * Test metody createAutopmatonFromOperatorTree dla wyrażenia regularnego ab.
     */
    public final void testRegexpWithConcatenationOperator() throws Exception {

        //Tworzymy drzewo dla wyrażenia regularnego ab
        RegexpOperator root1 = new SingleCharacterOperator('a');
        List<RegexpOperatorTree> subtrees1 = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree subtree1 = new RegexpOperatorTree(root1, subtrees1);

        RegexpOperator root2 = new SingleCharacterOperator('b');
        List<RegexpOperatorTree> subtrees2 = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree subtree2 = new RegexpOperatorTree(root2, subtrees2);

        RegexpOperator root = new ConcatenationOperator();
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        subtrees.add(subtree1);
        subtrees.add(subtree2);
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees);

         //Tworzymy automat dla tego drzewa.
        AutomatonSpecification automaton =
                RegexpUtilities.createAutomatonFromOperatorTree(tree);
        NondeterministicAutomatonByThompsonApproach result =
                new NondeterministicAutomatonByThompsonApproach(automaton);

        //Testujemy.
        assertTrue(result.accepts("ab"));
        assertFalse(result.accepts("a"));
        assertFalse(result.accepts("b"));
        assertFalse(result.accepts(""));
        assertFalse(result.accepts("aaaaa"));
        assertFalse(result.accepts("testujtest"));


    }

    /**
     * Test metody createAutopmatonFromOperatorTree
     * dla wyrażenia regularnego a?|b|c*.
     */
    public final void testComplexRegexp() throws Exception {

        //Tworzymy drzewo dla wyrażenia regularnego a?|b|c*
        RegexpOperator root1 = new SingleCharacterOperator('a');
        List<RegexpOperatorTree> subtrees1 = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree subtree1 = new RegexpOperatorTree(root1, subtrees1);

        RegexpOperator root2 = new SingleCharacterOperator('b');
        List<RegexpOperatorTree> subtrees2 = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree subtree2 = new RegexpOperatorTree(root2, subtrees2);

        RegexpOperator root3 = new SingleCharacterOperator('c');
        List<RegexpOperatorTree> subtrees3 = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree subtree3 = new RegexpOperatorTree(root3, subtrees3);

        RegexpOperator root4 = new OptionalityOperator();
        List<RegexpOperatorTree> subtrees4 = new ArrayList<RegexpOperatorTree>();
        subtrees4.add(subtree1);
        RegexpOperatorTree subtree4 = new RegexpOperatorTree(root4, subtrees4);

        RegexpOperator root5 = new KleeneStarOperator();
        List<RegexpOperatorTree> subtrees5 = new ArrayList<RegexpOperatorTree>();
        subtrees5.add(subtree3);
        RegexpOperatorTree subtree5 = new RegexpOperatorTree(root5, subtrees5);

        RegexpOperator root6 = new AlternativeOperator();
        List<RegexpOperatorTree> subtrees6 = new ArrayList<RegexpOperatorTree>();
        subtrees6.add(subtree4);
        subtrees6.add(subtree2);
        RegexpOperatorTree subtree6 = new RegexpOperatorTree(root6, subtrees6);

        RegexpOperator root = new AlternativeOperator();
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        subtrees.add(subtree6);
        subtrees.add(subtree5);
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees);

        //Tworzymy automat dla tego drzewa.
        AutomatonSpecification automaton =
                RegexpUtilities.createAutomatonFromOperatorTree(tree);
        NondeterministicAutomatonByThompsonApproach result =
                new NondeterministicAutomatonByThompsonApproach(automaton);

        //Testujemy.
        assertTrue(result.accepts(""));
        assertTrue(result.accepts("a"));
        assertTrue(result.accepts("b"));
        assertTrue(result.accepts("c"));
        assertTrue(result.accepts("cccccccc"));
        assertFalse(result.accepts("abcccc"));
        assertFalse(result.accepts("abc"));
        assertFalse(result.accepts("aaaaa"));
        assertFalse(result.accepts("testujtest"));
    }

    /**
     * Test metody createAutopmatonFromOperatorTree
     * dla wyrażenia regularnego (a|b)*|c+|de*.
     */
    public final void testMoreComplexRegexp() throws Exception {

        //Tworzymy drzewo dla wyrażenia regularnego (a|b)*|c+|de*
        RegexpOperator root1 = new SingleCharacterOperator('a');
        List<RegexpOperatorTree> subtrees1 = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree subtree1 = new RegexpOperatorTree(root1, subtrees1);

        RegexpOperator root2 = new SingleCharacterOperator('b');
        List<RegexpOperatorTree> subtrees2 = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree subtree2 = new RegexpOperatorTree(root2, subtrees2);

        RegexpOperator root3 = new SingleCharacterOperator('c');
        List<RegexpOperatorTree> subtrees3 = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree subtree3 = new RegexpOperatorTree(root3, subtrees3);

        RegexpOperator root4 = new SingleCharacterOperator('d');
        List<RegexpOperatorTree> subtrees4 = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree subtree4 = new RegexpOperatorTree(root4, subtrees4);

        RegexpOperator root5 = new SingleCharacterOperator('e');
        List<RegexpOperatorTree> subtrees5 = new ArrayList<RegexpOperatorTree>();
        RegexpOperatorTree subtree5 = new RegexpOperatorTree(root5, subtrees5);

        RegexpOperator root6 = new AlternativeOperator();
        List<RegexpOperatorTree> subtrees6 = new ArrayList<RegexpOperatorTree>();
        subtrees6.add(subtree1);
        subtrees6.add(subtree2);
        RegexpOperatorTree subtree6 = new RegexpOperatorTree(root6, subtrees6);

        RegexpOperator root7 = new KleeneStarOperator();
        List<RegexpOperatorTree> subtrees7 = new ArrayList<RegexpOperatorTree>();
        subtrees7.add(subtree6);
        RegexpOperatorTree subtree7 = new RegexpOperatorTree(root7, subtrees7);

        RegexpOperator root8 = new AtLeastOneOperator();
        List<RegexpOperatorTree> subtrees8 = new ArrayList<RegexpOperatorTree>();
        subtrees8.add(subtree3);
        RegexpOperatorTree subtree8 = new RegexpOperatorTree(root8, subtrees8);

        RegexpOperator root9 = new KleeneStarOperator();
        List<RegexpOperatorTree> subtrees9 = new ArrayList<RegexpOperatorTree>();
        subtrees9.add(subtree5);
        RegexpOperatorTree subtree9 = new RegexpOperatorTree(root9, subtrees9);

        RegexpOperator root10 = new AlternativeOperator();
        List<RegexpOperatorTree> subtrees10 = new ArrayList<RegexpOperatorTree>();
        subtrees10.add(subtree7);
        subtrees10.add(subtree8);
        RegexpOperatorTree subtree10 = new RegexpOperatorTree(root10, subtrees10);

        RegexpOperator root11 = new ConcatenationOperator();
        List<RegexpOperatorTree> subtrees11 = new ArrayList<RegexpOperatorTree>();
        subtrees11.add(subtree4);
        subtrees11.add(subtree9);
        RegexpOperatorTree subtree11 = new RegexpOperatorTree(root11, subtrees11);

        RegexpOperator root = new AlternativeOperator();
        List<RegexpOperatorTree> subtrees = new ArrayList<RegexpOperatorTree>();
        subtrees.add(subtree10);
        subtrees.add(subtree11);
        RegexpOperatorTree tree = new RegexpOperatorTree(root, subtrees);

        //Tworzymy automat dla tego drzewa.
        AutomatonSpecification automaton =
                RegexpUtilities.createAutomatonFromOperatorTree(tree);
        NondeterministicAutomatonByThompsonApproach result =
                new NondeterministicAutomatonByThompsonApproach(automaton);

        //Testujemy.
        assertTrue(result.accepts("a"));
        assertTrue(result.accepts("aaaaaaa"));
        assertTrue(result.accepts("b"));
        assertTrue(result.accepts("bbbbb"));
        assertTrue(result.accepts("ab"));
        assertTrue(result.accepts("abaaaabbb"));
        assertTrue(result.accepts(""));
        assertTrue(result.accepts("ccc"));
        assertTrue(result.accepts("de"));
        assertTrue(result.accepts("deeeeeeee"));
        assertTrue(result.accepts("d"));
        assertFalse(result.accepts("abbbbc"));
        assertFalse(result.accepts("dddd"));
        assertFalse(result.accepts("eeeee"));
        assertFalse(result.accepts("testujtest"));
    }

}
