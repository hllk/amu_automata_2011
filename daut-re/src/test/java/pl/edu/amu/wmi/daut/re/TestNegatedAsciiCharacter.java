package pl.edu.amu.wmi.daut.re;

import junit.framework.TestCase;
import pl.edu.amu.wmi.daut.base.NondeterministicAutomatonByThompsonApproach;
/**
 *
 * @author adrian
 */
public class TestNegatedAsciiCharacter extends TestCase {

    /**
     * Test klasy znaków alnum.
     */
    public void testAlnumNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("alnum");
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertFalse(automaton.accepts("a"));
       assertFalse(automaton.accepts("Z"));
       assertFalse(automaton.accepts("9"));
       assertFalse(automaton.accepts(""));
    }

    /**
     * Test klasy znaków alpha.
     */
    public void testAlphaNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("alpha");
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertFalse(automaton.accepts("a"));
       assertFalse(automaton.accepts("Z"));
    }

    /**
     * Test klasy znaków blank.
     */
    public void testBlankNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("blank");
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertFalse(automaton.accepts("\t"));
    }

    /**
     * Test klasy znaków cntrl.
     */
    public void testCntrlNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("cntrl");
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertFalse(automaton.accepts("\u0000"));
       assertFalse(automaton.accepts("\u0001"));
    }

    /**
     * Test klasy znaków digit.
     */
    public void testDigitNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("digit");
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertFalse(automaton.accepts("0"));
       assertFalse(automaton.accepts("3"));
       assertFalse(automaton.accepts("4"));
       assertFalse(automaton.accepts("1"));
       assertFalse(automaton.accepts("9"));
    }

    /**
     * Test klasy znaków graph.
     */
    public void testGraphNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("graph");
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertFalse(automaton.accepts("!"));
       assertFalse(automaton.accepts("~"));
       assertFalse(automaton.accepts("-"));
    }

    /**
     * Test klasy znaków lower.
     */
    public void testLowerNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("lower");
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertFalse(automaton.accepts("z"));
       assertFalse(automaton.accepts("a"));
       assertFalse(automaton.accepts("g"));
    }

    /**
     * Test klasy znaków print.
     */
    public void testPrintNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("print");
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertTrue(!automaton.accepts(" "));
       assertTrue(!automaton.accepts("a"));
       assertFalse(automaton.accepts("!"));
       assertFalse(automaton.accepts("*"));
       assertFalse(automaton.accepts("$"));
       assertFalse(automaton.accepts("\""));
       assertFalse(automaton.accepts("("));
       assertFalse(automaton.accepts("0"));
    }

    /**
     * Test klasy znaków punct.
     */
    public void testPunctNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("punct");
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertFalse(automaton.accepts("!"));
       assertFalse(automaton.accepts("/"));
       assertFalse(automaton.accepts("@"));
       assertFalse(automaton.accepts("["));
    }

    /**
     * Test klasy znaków space.
     */
    public void testSpaceNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("space");
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertFalse(automaton.accepts("\u000B"));
       assertFalse(automaton.accepts("\t"));
       assertFalse(automaton.accepts("\n"));
       assertFalse(automaton.accepts("\f"));
    }

    /**
     * Test klasy znaków upper.
     */
    public void testUpperNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("upper");
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertFalse(automaton.accepts("A"));
       assertFalse(automaton.accepts("C"));
       assertFalse(automaton.accepts("G"));
       assertFalse(automaton.accepts("Z"));
    }

    /**
     * Test klasy znaków word.
     */
    public void testWordNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("word");
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertFalse(automaton.accepts("a"));
       assertFalse(automaton.accepts("B"));
       assertFalse(automaton.accepts("_"));
       assertFalse(automaton.accepts("9"));
    }

    /**
     * Test klasy znaków xdigit.
     */
    public void testXdigitNegatedAsciiCHaracter() {
       NegatedAsciiCharacterClass character = new NegatedAsciiCharacterClass("xdigit");
       NondeterministicAutomatonByThompsonApproach automaton =
               new NondeterministicAutomatonByThompsonApproach(character.createFixedAutomaton());
       assertFalse(automaton.accepts("0"));
       assertFalse(automaton.accepts("8"));
       assertFalse(automaton.accepts("A"));
       assertFalse(automaton.accepts("f"));
       assertFalse(automaton.accepts("F"));
       assertFalse(automaton.accepts("a"));
    }
}
