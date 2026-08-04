// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Simoni Behl (simonib)

package dailymixes;

/**
 * The Playlist class creates a playlist giving it a name and adding songs to
 * it,
 * it provides all the information relating to the playlist, such as songs,
 * capacity, space left, if its full, or if a song is qualified to be in the
 * playlist or not, as well as a string representation of the playlist details,
 * compares two playlists to each other, and tells if two playlists are equal
 * 
 * @author Simoni Behl
 * @version Apr 8, 2025
 */
public class Playlist implements Comparable<Playlist> {
    private GenreSet minGenreSet;
    private GenreSet maxGenreSet;
    private Song[] songs;
    private int capacity;
    private int numberOfSongs;
    private String name;

    /**
     * Constructs a playlist by intilializing the fields
     * 
     * @param playlistName
     *            The name of the playlist
     * @param minPop
     *            the percent composition of pop from the minimum genre set
     * @param minRock
     *            the percent composition of rock from the minimum genre set
     * @param minCountry
     *            the percent composition of country from the minimum
     *            genre set
     * @param maxPop
     *            the percent composition of pop from the maximum genre set
     * @param maxRock
     *            the percent composition of rock from the maximum genre set
     * @param maxCountry
     *            the percent composition of country from the maximum
     *            genre set
     * @param playlistCap
     *            the capacity of the playlist
     */
    public Playlist(
        String playlistName,
        int minPop,
        int minRock,
        int minCountry,
        int maxPop,
        int maxRock,
        int maxCountry,
        int playlistCap) {
        this.name = playlistName;
        this.minGenreSet = new GenreSet(minPop, minRock, minCountry);
        this.maxGenreSet = new GenreSet(maxPop, maxRock, maxCountry);
        this.capacity = playlistCap;
        this.numberOfSongs = 0;
        this.songs = new Song[playlistCap];
    }


    /**
     * Gets the minimum genre set
     * 
     * @return GenreSet The minimum genre set
     */
    public GenreSet getMinGenreSet() {
        return minGenreSet;
    }


    /**
     * Gets the maximum genre set
     * 
     * @return GenreSet The maximum genre set
     */
    public GenreSet getMaxGenreSet() {
        return maxGenreSet;
    }


    /**
     * Gets the capacity of the playlist
     * 
     * @return int The capacity
     */
    public int getCapacity() {
        return capacity;
    }


    /**
     * Gets the name of the playlist
     * 
     * @return String The playlist name
     */
    public String getName() {
        return name;
    }


    /**
     * Gets the number of songs in the playlist
     * 
     * @return int The number of songs
     */
    public int getNumberOfSongs() {
        return numberOfSongs;
    }


    /**
     * Gets the songs in the playlist
     * 
     * @return Song[] The songs
     */
    public Song[] getSongs() {
        return songs;
    }


    /**
     * Sets the name of the playlist
     * 
     * @param name
     *            The new name of the palylist
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the number of spaces left in the playlist
     * 
     * @return int The spaces left
     */
    public int getSpacesLeft() {
        return capacity - numberOfSongs;
    }


    /**
     * Compares a playlist to another playlist based on its capacity, or spaces
     * left, or minimum genre set, or maximum genre set, or name
     * 
     * @param other
     *            The other playlist that the playlist is being compared to
     * @return int The number based on which playlist is bigger or smaller
     */
    public int compareTo(Playlist other) {
        if (this.getCapacity() != other.getCapacity()) {
            return this.capacity - other.capacity;
        }

        int spacesLeft = this.capacity - this.numberOfSongs;
        int otherSpacesLeft = other.capacity - other.numberOfSongs;
        if (spacesLeft != otherSpacesLeft) {
            return spacesLeft - otherSpacesLeft;
        }

        int minCompare = this.getMinGenreSet().compareTo(other
            .getMinGenreSet());
        if (minCompare != 0) {
            return minCompare;
        }

        int maxCompare = this.getMaxGenreSet().compareTo(other
            .getMaxGenreSet());
        if (maxCompare != 0) {
            return maxCompare;
        }

        return this.name.compareTo(other.name);
    }


    /**
     * Adds a song if the playlist isn't full and the song is qualified
     * 
     * @param newSong
     *            The new song being added
     * @return boolean True if the song is successfully added and false if not
     */
    public boolean addSong(Song newSong) {
        if (!isFull() && isQualified(newSong)) {
            songs[numberOfSongs] = newSong;
            numberOfSongs++;
            return true;
        }
        return false;
    }


    /**
     * Tells us if the playlist is full
     * 
     * @return boolean True if playlist is full and false if not
     */
    public boolean isFull() {
        return capacity == numberOfSongs;
    }


    /**
     * Tells us whether or not a song is qualified to be added to the playlist
     * based on if its in the range of the minimum and maximum genre sets
     * 
     * @param possibleSong
     *            the song that is being checked
     * @return boolean True if the song is qualified and false if not
     */
    public boolean isQualified(Song possibleSong) {
        return possibleSong.getGenreSet().isWithinRange(minGenreSet,
            maxGenreSet);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Playlist: ").append(name).append(", # of songs: ").append(
            numberOfSongs).append(" (cap: ").append(capacity).append(
                "), Requires: ");

        sb.append("Pop:").append(minGenreSet.getPop()).append("%-").append(
            maxGenreSet.getPop()).append("%, ");

        sb.append("Rock:").append(minGenreSet.getRock()).append("%-").append(
            maxGenreSet.getRock()).append("%, ");

        sb.append("Country:").append(minGenreSet.getCountry()).append("%-")
            .append(maxGenreSet.getCountry()).append("%");

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

        Playlist other = (Playlist)obj;
        if (capacity == other.capacity &&
            numberOfSongs == other.numberOfSongs
            && name.equals(other.name) && minGenreSet.equals(other.minGenreSet)
            && maxGenreSet.equals(other.maxGenreSet)) {
            for (int i = 0; i < numberOfSongs; i++) {
                if (!getSongs()[i].equals(other.getSongs()[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
