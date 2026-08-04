// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Simoni Behl (simonib)

package dailymixes;

import student.TestCase;

/**
 *  Tests the GenreSet methods by creating various genre sets and checking 
 *  if they are within a certain range, comparing them to each other, seeing 
 *  if two are equal or not, and returning the percent composition as a string
 * 
 *  @author Simoni Behl
 *  @version Apr 8, 2025
 */
public class GenreSetTest extends TestCase {
    
    private GenreSet genreSet;
    private GenreSet genreSet1;
    private GenreSet genreSet2;
    private GenreSet genreSet3;
    private GenreSet genreSet4;
    private GenreSet genreSet5;
    private GenreSet genreSet6;
    private GenreSet minGenreSet;
    private GenreSet maxGenreSet;
    
    /**
     * Sets up multiple genre sets to test the methods on
     */
    public void setUp() {
        genreSet = new GenreSet(10, 6, 4);
        genreSet1 = new GenreSet(10, 6, 4);
        genreSet2 = new GenreSet(8, 7, 4);
        genreSet3 = new GenreSet(4, 1, 10);
        genreSet4 = new GenreSet(5, 3, 10);
        genreSet5 = new GenreSet(5, 11, 10);
        genreSet6 = new GenreSet(8, 7, 2);
        
        
        minGenreSet = new GenreSet(5, 2, 1);
        maxGenreSet = new GenreSet(9, 8, 6);
    }
    
    /**
     * Tests to see if a genre set is within a certain range, when given a
     * minimum and maximum genre set
     */
    public void testIsWithinRange() {
        assertTrue(genreSet2.isWithinRange(minGenreSet, maxGenreSet));
        assertFalse(genreSet1.isWithinRange(minGenreSet, maxGenreSet));
        assertFalse(genreSet3.isWithinRange(minGenreSet, maxGenreSet));
        assertFalse(genreSet3.isWithinRange(minGenreSet, maxGenreSet));
        assertFalse(genreSet4.isWithinRange(minGenreSet, maxGenreSet));
        assertFalse(genreSet.isWithinRange(minGenreSet, maxGenreSet));
        assertFalse(genreSet5.isWithinRange(minGenreSet, maxGenreSet));
        
    }
    
    /**
     * Tests to check if two genre sets are equal to each other or not based
     * on each genres percent composition
     */
    public void testEquals() {
        assertTrue(genreSet.equals(genreSet));
        assertFalse(genreSet.equals(null));
        assertTrue(genreSet.equals(genreSet1));
        assertFalse(genreSet.equals(genreSet2));
        assertFalse(genreSet.equals("music"));
        assertFalse(genreSet.equals(genreSet2));
        assertFalse(genreSet4.equals(genreSet5));
        assertFalse(genreSet2.equals(genreSet6));
    }
    
    /**
     * Tests comparing two genre sets to see which one is bigger or smaller
     * than the other
     */
    public void testCompareTo() {
        assertEquals(0, genreSet.compareTo(genreSet1));
        assertEquals(1, genreSet.compareTo(genreSet2));
        assertEquals(-1, genreSet2.compareTo(genreSet));
        assertEquals(5, genreSet.compareTo(genreSet3));
    }
    
    /**
     * Tests the String format of the percent compositions
     */
    public void testToString() {
        assertEquals("Pop:10 Rock:6 Country:4", genreSet.toString());
    }
}
