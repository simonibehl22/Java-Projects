// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those 
// who do.
// -- Simoni Behl (simonib)

package dailymixes;

/**
 *  The Song class creates a song with a name, a genreSet, and 
 *  its sugguestedPlaylist to be in, the class can return the songs
 *  details in string format and can compare two songs to see if
 *  they are the same
 * 
 *  @author Simoni Behl
 *  @version Apr 8, 2025
 */
public class Song {
    
    private String name;
    private String suggestedPlaylist;
    private GenreSet genreSet;
    
    /**
     * Constructor used to initialize the fields
     * 
     * @param name The name of the song
     * @param pop The percent composition of pop
     * @param rock The percent composition of rock
     * @param country The percent composition of country
     * @param suggestedPlaylist The name of the suggested playlist
     */
    public Song(String name, int pop, int rock, int country, 
        String suggestedPlaylist) {
        this.name = name;
        this.suggestedPlaylist = suggestedPlaylist;
        this.genreSet =  new GenreSet(pop, rock, country);
    }
    
    /**
     * Gets the playlist's name
     * 
     * @return String The name of the suggested playlist
     */
    public String getPlaylistName() {
        return suggestedPlaylist;
    }
    
    /**
     * Gets the song name
     * 
     * @return String The name of the song
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the genre set of the song
     * 
     * @return GenreSet The genre set of the song, in other words,
     * the percent composition of the three genres
     */
    public GenreSet getGenreSet() {
        return genreSet;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (suggestedPlaylist.isEmpty()) {
            sb.append("No-Playlist ");
        }
        sb.append(name).append(" ");
        sb.append(genreSet.toString());
        
        if (suggestedPlaylist.length() > 0) {
            sb.append(" Suggested: ").append(suggestedPlaylist);
        }
        
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Song otherSong = (Song)obj;
        return this.name.equals(otherSong.getName()) && 
            this.suggestedPlaylist.equals(otherSong.getPlaylistName())
            && this.genreSet.equals(otherSong.getGenreSet());
    }
    
}
