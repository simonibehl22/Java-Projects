import student.TestCase;
import student.testingsupport.annotations.ScoringWeight;

/**
 * @author CS3114/5040 staff
 * @version Spring 2026
 */
public class DNAProjTest extends TestCase {
    private DNA it;


    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        it = new DNADB();
    }


    /**
     * Test output formatting
     */
    public void testSampleInput() {
        assertFuzzyEquals(
            "Sequence |ACGT| inserted",
            it.insert("ACGT"));
        assertFuzzyEquals(
            "Sequence |ACGT| already exists",
            it.insert("ACGT"));
        assertFuzzyEquals(
            "Sequence |ACGT| removed",
            it.remove("ACGT"));
        assertFuzzyEquals(
            "Sequence |AAAA| inserted",
            it.insert("AAAA"));
        assertFuzzyEquals(
            "Sequence |AA| inserted",
            it.insert("AA"));
        assertFuzzyEquals(
            "Sequence |ACG| does not exist",
            it.remove("ACG"));
        assertFuzzyEquals(
            "tree dump:\r\n"
            + "I\r\n"
            + "  I\r\n"
            + "    I\r\n"
            + "      AAAA\r\n"
            + "      E\r\n"
            + "      E\r\n"
            + "      E\r\n"
            + "      AA\r\n"
            + "    E\r\n"
            + "    E\r\n"
            + "    E\r\n"
            + "    E\r\n"
            + "  E\r\n"
            + "  E\r\n"
            + "  E\r\n"
            + "  E",
                it.print());
        assertFuzzyEquals(
            "tree dump with lengths:\r\n"
            + "I\r\n"
            + "  I\r\n"
            + "    I\r\n"
            + "      AAAA 4\r\n"
            + "      E\r\n"
            + "      E\r\n"
            + "      E\r\n"
            + "      AA 2\r\n"
            + "    E\r\n"
            + "    E\r\n"
            + "    E\r\n"
            + "    E\r\n"
            + "  E\r\n"
            + "  E\r\n"
            + "  E\r\n"
            + "  E",
                it.printLengths());
        assertFuzzyEquals(
            "tree dump with stats:\r\n"
            + "I\r\n"
            + "  I\r\n"
            + "    I\r\n"
            + "      AAAA A:100.00 C:0.00 G:0.00 T:0.00\r\n"
            + "      E\r\n"
            + "      E\r\n"
            + "      E\r\n"
            + "      AA A:100.00 C:0.00 G:0.00 T:0.00\r\n"
            + "    E\r\n"
            + "    E\r\n"
            + "    E\r\n"
            + "    E\r\n"
            + "  E\r\n"
            + "  E\r\n"
            + "  E\r\n"
            + "  E",
                it.printStats());
        assertFuzzyEquals(
            "AAAA\r\n"
            + "# of nodes visited: 4",
                it.search("AAAA$"));
        assertFuzzyEquals(
            "AAAA\r\n"
            + "AA\r\n"
            + "# of nodes visited: 8",
                it.search("AA"));
        assertFuzzyEquals(
            "No sequence found\r\n"
                + "# of nodes visited: 3",
                it.search("ACGT$"));
    }

    /**
     * Example tests for bad input error formatting
     */
    @ScoringWeight(10.0)
    public void testBadInput() {
        assertFuzzyEquals(
            "Bad input: Sequence may not be null\r\n",
            it.insert(null));
        assertFuzzyEquals(
            "Bad input: Sequence may not be empty\r\n",
            it.insert(""));
        assertFuzzyEquals(
            "Bad Input Sequence |AXA|\r\n",
            it.insert("AXA"));
        assertFuzzyEquals(
            "Bad Input Sequence |A A|\r\n", 
            it.insert("A A"));
        assertFuzzyEquals(
            "Bad Input Sequence |A |\r\n", 
            it.insert("A "));
        assertFuzzyEquals(
            "Bad Input Sequence |A$|\r\n", 
            it.insert("A$"));
        assertFuzzyEquals(
            "Bad Input: Sequence may not be null\r\n",
            it.search(null));
        assertFuzzyEquals(
            "No sequence found\r\n"
            + "# of nodes visited: 1",
            it.search(""));
        assertFuzzyEquals(
            "Bad Input Sequence |A$A|\r\n", 
            it.search("A$A"));
    }
    
    /**
     * Tests that the inserting a prefix of an 
     * existing sequence triggers a split and is
     * placed in the tree correctly
     */
    public void testSplit() {
        it.insert("AAAA");
        it.insert("AA");
        
        String result = it.print();
        assertFuzzyEquals("tree dump:\r\n"
            + "I\r\n"
            + "I\r\n"
            + "I\r\n"
            + "AAAA\r\n"
            + "E\r\n"
            + "E\r\n"
            + "E\r\n"
            + "AA\r\n"
            + "E\r\n"
            + "E\r\n"
            + "E\r\n"
            + "E\r\n"
            + "E\r\n"
            + "E\r\n"
            + "E\r\n"
            + "E\r\n", result);
    }
    
    /**
     * Tests that the basic statistics are calculated and printed 
     * correctly for each nucleotide
     */
    public void testBasicStats() {
        it.insert("ACGT");
        assertFuzzyEquals("tree dump with stats:\r\n"
            + "ACGT A:25.00 C:25.00 G:25.00 T:25.00", it.printStats());  
        
        it.insert("TGCA");
        assertFuzzyEquals("tree dump with stats:\r\n"
            + "I\r\n"
            + "ACGT A:25.00 C:25.00 G:25.00 T:25.00\r\n"
            + "E\r\n"
            + "E\r\n"
            + "TGCA A:25.00 C:25.00 G:25.00 T:25.00\r\n"
            + "E", it.printStats());
    }
    
    /**
     * Tests that the statistics are calculated and printed correctly
     * for multiple inserts and varying nucleotide values
     */
    public void testMoreStats() {
        it.insert("ACGT");
        it.insert("TGCA");
        it.insert("CC");
        it.insert("GGG");
        it.insert("TT");
        
        assertFuzzyEquals("tree dump with stats:\r\n"
            + "I\r\n"
            + "ACGT A:25.00 C:25.00 G:25.00 T:25.00\r\n"
            + "CC A:0.00 C:100.00 G:0.00 T:0.00\r\n"
            + "GGG A:0.00 C:0.00 G:100.00 T:0.00\r\n"
            + "I\r\n"
            + "E\r\n"
            + "E\r\n"
            + "TGCA A:25.00 C:25.00 G:25.00 T:25.00\r\n"
            + "TT A:0.00 C:0.00 G:0.00 T:100.00\r\n"
            + "E\r\n"
            + "E\r\n", it.printStats());
    }
    
    /**
     * Tests that only the exact search is found
     * even if terminator is included
     */
    public void testExactSearch() {
        it.insert("CCCC");
        assertFuzzyEquals("CCCC\r\n"
            + "# of nodes visited: 1", it.search("CCCC$"));
    }
    
    /**
     * Tests that removing from an internal node is
     * handled properly
     */
    public void testRemoveInternal() {
        it.insert("AGCT");
        it.insert("CGCG");
        it.insert("AC");
        
        String result = it.print();
        assertTrue(result.contains("\r\n  E\r\n"));
        assertFuzzyEquals("Sequence |CGCG| removed", it.remove("CGCG"));
        
        it.remove("AC");
        assertFuzzyEquals("tree dump: \r\nAGCT", it.print());

        it.remove("AGCT");
        assertFuzzyEquals("tree dump: \r\nE", it.print());
        
        assertFuzzyEquals("Sequence |A| does not exist", it.remove("A"));
    }
    
    /**
     * Tests removing sequences from different depths/levels
     */
    public void testRemoveLevels() {
        it.insert("AAAA");
        it.insert("AAAC");
        
        it.remove("AAAA");
        assertFuzzyEquals("tree dump: \r\nAAAC", it.print());
        
        it.insert("AAAA");
        it.insert("AATG");
        assertFuzzyEquals("Sequence |AAAC| removed", it.remove("AAAC"));
        
    }
    
    /**
     *  Tests that the output is displayed correctly,
     *  with correct spacing and sequences
     */
    public void testDisplay() {
        it.insert("AAAA");
        it.insert("AAAC");
        
        assertFuzzyEquals("tree dump:\r\n  "
            + "I\r\n "
            + "  I\r\n"
            + "    I\r\n"
            + "      I\r\n"
            + "        AAAA\r\n"
            + "        AAAC\r\n"
            + "        E\r\n"
            + "        E\r\n"
            + "      E\r\n"
            + "      E\r\n"
            + "      E\r\n"
            + "      E\r\n"
            + "  E\r\n"
            + "  E\r\n"
            + "  E\r\n"
            + "  E\r\n"
            + "E\r\n"
            + "E\r\n"
            + "E\r\n"
            + "E\r\n"
            + "E", it.print());
        
        String result = it.print();
        assertTrue(result.contains("  I\r\n"));
        assertTrue(result.contains("        AAAA"));
        assertTrue(result.contains("        AAAC"));
    }
    
    /**
     * Tests that the right levels and number of nodes are
     * visited when searching for a specific sequence
     * that has the same prefix as other sequences
     */
    public void testPrefixSearch() {
        it.insert("ACGT");
        String result = it.search("AAA");
    
        assertFalse(result.contains("ACGT"));
        it.remove("ACGT");
        
        it.insert("AAAA");
        it.insert("AAAC");
        it.insert("AATG");

        String result2 = it.search("AA");

        assertTrue(result2.contains("AAAA"));
        assertTrue(result2.contains("AAAC"));
        assertTrue(result2.contains("# of nodes visited: 13"));
    }
    
    /**
     * Tests bad inputs for remove
     */
    public void testBadRemove() {
        assertFuzzyEquals(
            "Bad input: Sequence may not be null\r\n",
            it.remove(null));
        assertFuzzyEquals(
            "Bad input: Sequence may not be empty\r\n",
            it.remove(""));
        assertFuzzyEquals(
            "Bad Input Sequence |AXA|\r\n",
            it.remove("AXA"));
    }
}

