// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Simoni Behl (simonib)

package dailymixes;
import list.AList;
import java.util.Arrays;

/**
 *  The PlaylistCalculator class handles the major calculations for the program,
 *  as well as the logic with adding and rejecting a song, it also contains the 
 *  queue of songs and the playlist objects
 * 
 *  @author Simoni Behl
 *  @version Apr 9, 2025
 */
public class PlaylistCalculator {
    private Playlist[] playlists;
    /**
     * The number of playlists to be used in the playlist calculator
     */
    public static final int NUM_PLAYLISTS = 3;
    /**
     * The minimum percent composition in a playlist
     */
    public static final int MIN_PERCENT = 0;
    /**
     * The maximum percent composition in a playlist
     */
    public static final int MAX_PERCENT = 100;
    private AList<Song> rejectedTracks;
    private ArrayQueue<Song> songQueue;

    /**
     * Constructs a playlist calculator object by initializing the fields
     * 
     * @param songQueue The queue of songs
     * @param playlists The array of playlists
     */
    public PlaylistCalculator(ArrayQueue<Song> songQueue, Playlist[] playlists)
    {
        if (songQueue == null) {
            throw new IllegalArgumentException();
        }
        this.songQueue = songQueue;
        this.playlists = playlists;
        this.rejectedTracks = new AList<>();
        
    }
    
    /**
     * Removes a song from the queue if it is rejected and adds it
     * to the rejected tracks
     */
    public void reject() {
        Song rejected = songQueue.dequeue();
        getRejectedTracks().add(rejected);
    }
    
    private Playlist getPlaylistWithMaximumCapacity(Song aSong) {
        Playlist[] sorted = Arrays.copyOf(getPlaylists(), playlists.length);
        Arrays.sort(sorted);
        
        for (int i = sorted.length - 1; i >= 0 ; i--) {
            if (canAccept(sorted[i], aSong)) {
                return sorted[i]; 
            }
        }
        return null;
    }
    
    /**
     * Adds a song to its suggested playlist and removes it from the queue
     * accordingly
     * 
     * @return boolean True if the song is added successfully, false if not
     */
    public boolean addSongToPlaylist() {
        if (songQueue.isEmpty()) {
            return false;
        }
        Song nextSong = getQueue().getFront();
        Playlist suggestedPlaylist = getPlaylistForSong(nextSong);
        if (suggestedPlaylist != null) {
            suggestedPlaylist.addSong(nextSong);
            songQueue.dequeue();
            return true;
        }
        return false;
    }
    
    /**
     * Determines the next songs suggested playlist and if it can be added
     * to the playlist, if there is no suggested playlist for the song, then 
     * it finds the playlist with the most capacity
     * 
     * @param nextSong The song whose playlist is being found
     * @return Playlist The playlist the song will be added to
     */
    public Playlist getPlaylistForSong(Song nextSong) {
        if (nextSong == null) {
            return null;
        }
        String suggestedName = nextSong.getPlaylistName();
        if (suggestedName != null) {
            for (int i = 0; i < playlists.length; i++) {
                Playlist playlist = playlists[i];
                if (playlist.getName().equals(suggestedName) &&
                    !playlist.isFull() && playlist.isQualified(nextSong)) {
                    return playlist;
                }
            }
        }
        return getPlaylistWithMaximumCapacity(nextSong); 
    }
    
    /**
     * Gets the sonq queue
     * 
     * @return ArrayQueue<Song> The song queue
     */
    public ArrayQueue<Song> getQueue() {
        return songQueue;
    }
    
    private boolean canAccept(Playlist playlist, Song song) {
        return !playlist.isFull() 
            && playlist.isQualified(song);
    }
    
    /**
     * Gets the int representation of the string name of the playlist
     * 
     * @param playlist The playlist whose int representation is beign given
     * @return int The index value, or -1
     */
    public int getPlaylistIndex(String playlist) {
        for (int i = 0; i < NUM_PLAYLISTS; i++) {
            if (playlists[i].getName().equals(playlist)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Gets the playlists
     * 
     * @return Playlist[] the playlists in the array
     */
    public Playlist[] getPlaylists() {
        return playlists;
    }
    
    /**
     * Gets the list of rejected songs
     * 
     * @return AList<Song> The songs that were rejected
     */
    public AList<Song> getRejectedTracks() {
        return rejectedTracks;
    }

}
