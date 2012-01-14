package pl.edu.amu.wmi.daut.re;

import junit.framework.TestCase;
import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.CharTransitionLabel;
import pl.edu.amu.wmi.daut.base.State;
import pl.edu.amu.wmi.daut.re.RangeNumberOfOccurrencesOperator.Factory;
import pl.edu.amu.wmi.daut.base.NondeterministicAutomatonByThompsonApproach;



/**
 *
 * test klasy RangeNumberOfOccurrencesOperator
 */
public class TestRangeNumberOfOccurrencesOperator extends TestCase {
    
    public final void testCreateAutomatonFromOneAutomaton1 (){
        
        AutomatonSpecification aut = new NaiveAutomatonSpecification();
        
        State q0 = aut.addState();
        State q1 = aut.addState();
        aut.addTransition(q0, q1, new CharTransitionLabel('a'));
        aut.addTransition(q1, q0, new CharTransitionLabel('a'));
        aut.addLoop(q0, new CharTransitionLabel('b'));
        aut.addLoop(q1, new CharTransitionLabel('b'));
        
        aut.markAsInitial(q0);
        aut.markAsFinal(q1);
        
        RangeNumberOfOccurrencesOperator testaut1 = new RangeNumberOfOccurrencesOperator(2,5);
        NondeterministicAutomatonByThompsonApproach result1 =
        new NondeterministicAutomatonByThompsonApproach(
                testaut1.createAutomatonFromTwoAutomatOneAutomaton(aut));
        
        assertTrue(result1.accepts("aa"));
        assertTrue(result1.accepts("abbbabbbabbb"));
        assertTrue(result1.accepts("aaaaabbbbbbbbb"));
        assertTrue(result1.accepts("bbbbbaa"));
        assertFalse(result1.accepts("a"));
        assertFalse(result1.accepts("bbabaabaaa"));
        assertFalse(result1.accepts(""));
        assertFalse(result1.accepts("bbbbb"));
        assertFalse(result1.accepts("asasasa"));
        assertFalse(result1.accepts("..."));
        
        
        RangeNumberOfOccurrencesOperator testaut2 = new RangeNumberOfOccurrencesOperator(0,3);
        NondeterministicAutomatonByThompsonApproach result2 =
        new NondeterministicAutomatonByThompsonApproach(
                testaut2.createAutomatonFromTwoAutomatOneAutomaton(aut));
        
        assertTrue(result2.accepts(""));
        assertTrue(result2.accepts("aaa"));
        assertTrue(result2.accepts("bbbabbb"));
        assertTrue(result2.accepts("bbbbb"));
        assertFalse(result2.accepts("abaabbaaa"));
        assertFalse(result2.accepts("aaaa"));
        assertFalse(result2.accepts("asasas"));
        assertFalse(result2.accepts("kefir"));
        
        
        RangeNumberOfOccurrencesOperator testaut3 = new RangeNumberOfOccurrencesOperator(4,4);
        NondeterministicAutomatonByThompsonApproach result3 =
        new NondeterministicAutomatonByThompsonApproach(
                testaut3.createAutomatonFromTwoAutomatOneAutomaton(aut));
        
        assertTrue(result3.accepts("aaaa"));
        assertTrue(result3.accepts("bbbbbabababba"));
        
        assertFalse(result3.accepts(""));
        assertFalse(result3.accepts("ab"));
        assertFalse(result3.accepts("bbbb"));
        assertFalse(result3.accepts("bbababbababba"));
        assertFalse(result3.accepts("asasasa"));
        assertFalse(result3.accepts("jogurt"));
    }
    
}
