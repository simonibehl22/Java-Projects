// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those 
// who do.
// -- Simoni Behl (simonib)

package dailymixes;

import student.TestCase;

/**
 *  Tests the playlist methods to make sure playlist name can be set, songs can
 *  be added based on if they are qualified and if the playlist is full, along
 *  with checking the string representation and if two playlists are equal
 *  
 * 
 *  @author Simoni Behl
 *  @version Apr 8, 2025
 */
public class PlaylistTest extends TestCase {

    private Playlist playlistA;
    private Playlist playlistB;
    private Playlist playlistC;
    private Playlist playlistD;
    private Playlist playlistE;
    private Playlist playlistF;
    private Playlist playlistG;
    private Playlist playlistH;
    private Song song1;
    private Song song2;
    private Song song3;
    private Song song4;
    
    /**
     * Sets up playlists to test the methods on
     */
    public void setUp() {
        playlistA = new Playlist("Favorites", 10 , 15, 20,
            25, 30, 35, 3);
        playlistB = new Playlist("Favorites", 10 , 15, 20,
            25, 30, 35, 3);
        playlistC = new Playlist("Daily-Mix", 5 , 10, 20,
            30, 35, 40, 2);
        playlistD = new Playlist("Todays Hits", 8 , 10, 20,
            30, 35, 40, 2);
        playlistE = new Playlist("Daily-Mix", 8 , 10, 20,
            30, 35, 38, 2);
        playlistF = new Playlist("Daily-Mix", 5 , 10, 20,
            25, 30, 38, 2);
        playlistG = new Playlist("Daily-Mix", 5 , 10, 20,
            25, 30, 38, 4);
        playlistH = new Playlist("Daily-Mix", 5 , 10, 20,
            25, 32, 38, 4);
        song1 = new Song("Last Night", 11, 22, 33, "");
        song2 = new Song("Firework", 6, 20, 36, "");
        song3 = new Song("Grenade", 10, 25, 30, "");
        song4 = new Song("Our Song", 13, 21, 33, "");
    }
    
    /**
     * Tests the setName method ensures the name is properly changed
     */
    public void testSetName() {
        assertEquals("Favorites", playlistA.getName());
        
        playlistA.setName("Daily-Mix");
        assertEquals("Daily-Mix", playlistA.getName());
    }
    
    /**
     * Tests the getSongs method checks the songs are listed properly when added
     */
    public void testGetSongs() {
        playlistA.addSong(song1);
        playlistA.addSong(song3);
        Song[] songs = playlistA.getSongs();
        
        assertNotNull(songs[0]);
        assertNotNull(songs[1]);
        assertNull(songs[2]);
    }
    
    /**
     * Tests the getSpacesLeft method to see the amount of spaces left
     */
    public void testGetSpacesLeft() {
        assertEquals(3, playlistA.getSpacesLeft());
        
        playlistA.addSong(song1);
        playlistA.addSong(song3);
        assertEquals(2, playlistA.getNumberOfSongs());
        assertEquals(1, playlistA.getSpacesLeft());
    }
    
    /**
     * Tests the compareTo method to compare the playlists
     */
    public void testCompareTo() {
        assertEquals(1, playlistA.compareTo(playlistC));
        
        playlistA.addSong(song1);
        assertEquals(-1, playlistA.compareTo(playlistB));
        assertEquals(-3, playlistC.compareTo(playlistD));
        assertEquals(2, playlistD.compareTo(playlistE));
        assertEquals(3, playlistE.compareTo(playlistF));
        
        playlistB.addSong(song3);
        assertEquals(0, playlistA.compareTo(playlistB));
    }
    
    /**
     * Tests the addSong method to make sure a song is added
     */
    public void testAddSong() {
        assertTrue(playlistA.addSong(song1));
        assertEquals(1, playlistA.getNumberOfSongs());
        assertFalse(playlistA.addSong(song2));
        assertEquals(1, playlistA.getNumberOfSongs());
        
        playlistC.addSong(song1);
        playlistC.addSong(song2);
        assertFalse(playlistC.addSong(song3));
    }
    
    /**
     * Tests the isFull method to check if the playlist is full
     */
    public void testIsFull() {
        assertFalse(playlistC.isFull());
        
        playlistC.addSong(song1);
        playlistC.addSong(song2);
        playlistC.addSong(song3);
        assertTrue(playlistC.isFull());
    }
    
    /**
     * Tests the isQualified method to check if a song is qualified
     */
    public void testIsQualified() {
        assertFalse(playlistA.isQualified(song2));
        assertTrue(playlistC.isQualified(song2));
    }
    
    /**
     * Tests the toString method to see the right string representation of the
     * playlist is given
     */
    public void testToString() {
        String expected = "Playlist: Favorites, # of songs: 0 (cap: 3),"
            + " Requires: Pop:10%-25%, Rock:15%-30%, Country:20%-35%";
        assertEquals(expected, playlistA.toString());
        
        playlistC.addSong(song1);
        playlistC.addSong(song2);
        String expected1 = "Playlist: Daily-Mix, # of songs: 2 (cap: 2),"
            + " Requires: Pop:5%-30%, Rock:10%-35%, Country:20%-40%";
        assertEquals(expected1, playlistC.toString());
    }
    
    /**
     * Tests the equals method to check if two playlists are equal or not
     */
    public void testEquals() {
        assertTrue(playlistA.equals(playlistA));
        
        playlistA.addSong(song1);
        assertFalse(playlistA.equals(playlistB)); 
        
        playlistB.addSong(song1);
        assertTrue(playlistA.equals(playlistB));
        assertFalse(playlistA.equals(null));
        assertFalse(playlistA.equals("playlist"));
        assertFalse(playlistA.equals(playlistC));
        assertFalse(playlistC.equals(playlistD));
        assertFalse(playlistF.equals(playlistG));
        assertFalse(playlistG.equals(playlistH));
        
        playlistC.addSong(song1);
        assertFalse(playlistC.equals(playlistD));
        
        playlistE.addSong(song1);
        assertFalse(playlistC.equals(playlistE));
        
        playlistF.addSong(song1);
        assertFalse(playlistE.equals(playlistF));
        
        playlistA.addSong(song3);
        playlistB.addSong(song4);
        assertFalse(playlistA.equals(playlistB));
        
    }
}
