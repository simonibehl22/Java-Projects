// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Simoni Behl (simonib)
    
package dailymixes;

import student.TestCase;

/**
 *  Tests the Song class methods and checks that toString 
 *  and equals work as expected, and creates varying instances
 *  to test all scenarios
 * 
 *  @author Simoni Behl
 *  @version Apr 8, 2025
 */
public class SongTest extends TestCase {
    
    private Song song;
    private Song song1;
    private Song song2;
    private Song song3;
    private Song song4;
    private Song song5;
    
    /**
     * Sets us the test class, by creating multiple songs to test the methods on
     */
    public void setUp() {
        song = new Song("Baby", 2, 3, 1, "Daily Mix");
        song1 = new Song("Baby", 2, 3, 1, "Daily Mix");
        song2 = new Song("Dynamite", 2, 3, 1, "Daily Mix");
        song3 = new Song("24k magic", 1, 2, 3, "");
        song4 = new Song("Baby", 2, 3, 1, "Fun");
        song5 = new Song("Baby", 2, 3, 6, "Daily Mix");
    }
    
    /**
     * Tests the toString method and ensures the songs details are properly
     * returned in string format
     */
    public void testToString() {
        assertEquals("Baby Pop:2 Rock:3 Country:1 Suggested: Daily Mix",
            song.toString());
        assertEquals("No-Playlist 24k magic Pop:1 Rock:2 Country:3",
            song3.toString());
    }
    
    /**
     * Tests the equals method and makes sure that two songs are equals if
     * they have the same name, suggestedPlaylist, and genreSet
     */
    public void testEquals() {
        assertTrue(song.equals(song));
        assertFalse(song.equals(null));
        assertFalse(song.equals("music"));
        assertTrue(song.equals(song1));
        assertFalse(song.equals(song2));
        assertFalse(song.equals(song4));
        assertFalse(song.equals(song5));
    }
}
