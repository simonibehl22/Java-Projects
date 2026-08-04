// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Simoni Behl (simonib)

package dailymixes;

/**
 *  The GenreSet class creates a genre set of pop, rock, and country,
 *  this class implements the Comparable interface to compare two genre sets
 *  to each other and checks if a genre set is in a certain range,
 *  as well as if one is equal to another, and can return a string format
 *  of the percent composition of the genres
 * 
 *  @author simoni
 *  @version Apr 8, 2025
 */
public class GenreSet implements Comparable<GenreSet> {
    
    private int pop;
    private int rock;
    private int country;
    
    /**
     * Constructs a genreSet by initializing the fields
     * 
     * @param pop The percent composition of pop
     * @param rock The percent composition of rock
     * @param country The percent composition of country
     */
    public GenreSet(int pop, int rock, int country) {
        this.pop = pop;
        this.rock = rock;
        this.country = country;
    }
    
    /**
     * Gets the pop percent composition
     * 
     *  @return int The percent composition of pop
     */
    public int getPop() {
        return pop;
    }
    
    /**
     * Gets the rock percent composition
     * 
     *  @return int The percent composition of rock
     */
    public int getRock() {
        return rock;
    }
    
    /**
     * Gets the country percent composition
     * 
     *  @return int The percent composition of country
     */
    public int getCountry() {
        return country;
    }
    
    private boolean isLessThanOrEqualTo(GenreSet other) {
        return this.pop <= other.pop && 
            this.rock <= other.rock &&
            this.country <= other.country;
    }
    
    /**
     * Checks if a genre set is between the range of two genre sets
     * 
     * @param minGenreSet The minimum genre set
     * @param maxGenreSet The maximum genre set
     * @return boolean True if it is within the range and false otherwise
     */
    public boolean isWithinRange(GenreSet minGenreSet, GenreSet maxGenreSet) {
        return minGenreSet.isLessThanOrEqualTo(this) && 
            this.isLessThanOrEqualTo(maxGenreSet);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        GenreSet otherSet = (GenreSet)obj;
        return this.pop == otherSet.getPop() && 
            this.rock == otherSet.getRock() && 
            this.country == otherSet.getCountry();
    }
    /**
     * Compares two genre sets to each other
     * 
     * @param other The other genreSet that a genreSet is being compared to
     * @return int The number based on if the genreSet is bigger, smaller, or 
     * than the other genreSet
     */
    public int compareTo(GenreSet other) {
        int sum = this.pop + this.rock + this.country;
        int otherSum = other.getPop() + other.getRock() + other.getCountry();
        if (sum < otherSum) {
            return sum - otherSum;
        }
        if (sum > otherSum) {
            return sum - otherSum;
        }
        return 0;
    }
    
    @Override
    public String toString() {
        return "Pop:" + pop + " Rock:" + rock 
            + " Country:" + country;
    }
}
