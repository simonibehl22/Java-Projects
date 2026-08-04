// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Simoni Behl (simonib)

package dailymixes;

import student.TestCase;

/**
 *  Tests the methods of PlaylistCalculator to make sure the songs
 *  are appropriately able to be rejected or added into a playlist, 
 *  it checks what playlist they belong in as well
 * 
 *  @author Simoni Behl
 *  @version Apr 15, 2025
 */
public class PlaylistCalculatorTest extends TestCase {
    
    private PlaylistCalculator calculator;
    private Playlist[] playlists;
    private ArrayQueue<Song> songQueue;
    private Song song1;
    private Song song2;
    private Song song3;
    private Song song4;
    private Song song5;
    private Song song6;
    private Song song7;
    private Song song8;
    private Song song9;
    
    /**
     * Sets up songs to create a songQueue and an array of playlists
     * to create a playlist calculator to test the methods on
     */
    public void setUp() {
        songQueue = new ArrayQueue<>();
 
        song1 = new Song("Baby", 6, 20, 31, "Daily-Mix");
        song2 = new Song("Dynamite", 2, 3, 1, "Favorites");
        song3 = new Song("24K magic", 2, 3, 1, null);
        song4 = new Song("Grenade", 7, 22, 38, "Daily-Mix");
        song5 = new Song("Roar", 29, 11, 32, "Daily-Mix");
        song6 = new Song("Bless your heart", 15, 21, 25, "country mix");
        song7 = new Song("Our song", 15, 21, 25, "A");
        song8 = new Song("Maroon", 15, 21, 25, "color");
        song9 = new Song("Let it Happen", 15, 21, 25, "Sad");
        
        playlists = new Playlist[PlaylistCalculator.NUM_PLAYLISTS];
        playlists[0] = new Playlist("Favorites", 10 , 15, 20,
            25, 30, 35, 2);
        playlists[1] = new Playlist("Daily-Mix", 5 , 10, 20,
            30, 35, 40, 2);
        playlists[2] = new Playlist("Todays Hits", 8 , 10, 20,
            30, 35, 40, 2);
        
        calculator = new PlaylistCalculator(songQueue, playlists);
       
    }
    
    /**
     * Tests the reject method to ensure if songs are rejected,
     * they get removed from the queue and into the rejected tracks
     */
    public void testReject() {
        calculator.getQueue().enqueue(song2);
        calculator.reject();
        assertEquals(0, songQueue.getSize());
        assertEquals(1, calculator.getRejectedTracks().getLength());
        
    }
    
    /**
     * Tests if a song can be added to any of the playlists available
     *  and throws an exception if the song queue is null
     */
    public void testAddSongToPlaylist() {
        PlaylistCalculator calculator1;
        Exception thrown = null;
        try {
            calculator1 = new PlaylistCalculator(null, playlists);
            calculator1.addSongToPlaylist();
        }
        catch (IllegalArgumentException e) {
            thrown = e;
        }
        assertNotNull(thrown);

        assertFalse(calculator.addSongToPlaylist());
        
        calculator.getQueue().enqueue(song1);
        calculator.getQueue().enqueue(song2);
        calculator.getQueue().enqueue(song4);
        calculator.getQueue().enqueue(song5);
        assertTrue(calculator.addSongToPlaylist());
        assertFalse(calculator.addSongToPlaylist());
        
        PlaylistCalculator calculator2;
        calculator.getQueue().enqueue(song6);

        Playlist[] playlists2 = new Playlist[2];
        playlists2[0] = new Playlist("Fun", 10 , 15, 20,
            25, 30, 35, 2);
        playlists2[1] = new Playlist("Car ride", 5 , 10, 20,
            30, 35, 40, 2);
        
        calculator2 = new PlaylistCalculator(songQueue, playlists2);
        
        calculator2.addSongToPlaylist();
        calculator2.getPlaylistForSong(song7);
        
    }
    /**
     * Tests that a playlist is given for the songs if there is one 
     * that the song fits into and not given if the requirements are not met
     */
    public void testGetPlaylistForSong() {
        Playlist playlist = calculator.getPlaylistForSong(song1);
        assertNotNull(playlist);
        assertEquals("Daily-Mix", playlist.getName());
        assertNull(calculator.getPlaylistForSong(null));
        assertNull(calculator.getPlaylistForSong(song3));
        
        songQueue.enqueue(song1);
        songQueue.enqueue(song4);
        calculator.addSongToPlaylist();
        calculator.addSongToPlaylist();
        assertNotNull(calculator.getPlaylistForSong(song5));
       
        PlaylistCalculator calculator2;
        calculator.getQueue().enqueue(song6);
        calculator.getQueue().enqueue(song7);
        calculator.getQueue().enqueue(song8);
        calculator.getQueue().enqueue(song9);
        
        Playlist[] playlists2 = new Playlist[2];
        playlists2[0] = new Playlist("Fun", 10 , 15, 20,
            25, 30, 35, 2);
        playlists2[1] = new Playlist("Car ride", 5 , 10, 20,
            30, 35, 40, 2);
        
        calculator2 = new PlaylistCalculator(songQueue, playlists2);
        calculator2.addSongToPlaylist();
        calculator2.addSongToPlaylist();
        calculator2.addSongToPlaylist();
        calculator2.addSongToPlaylist();
        calculator2.getPlaylistForSong(song4);
    }
    
    /**
     * Tests that the right int representation, its index, of a playlist is 
     * given from the array of playlists
     */
    public void testGetPlaylistIndex() {
        assertEquals(2, calculator.getPlaylistIndex("Todays Hits"));
        assertEquals(-1, calculator.getPlaylistIndex("Fun"));
    }

}
