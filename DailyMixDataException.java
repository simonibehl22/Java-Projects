// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those 
// who do.
// -- Simoni Behl (simonib)
    
package dailymixes;

/**
 *  DailyMixDataException is an exception that is thrown when 
 *  data is incorrect in the input files
 * 
 *  @author Simoni Behl
 *  @version Apr 9, 2025
 */
public class DailyMixDataException extends Exception {
    
    /**
     * Constructs an exception with a message
     * 
     * @param string The message to describe the exception
     */
    public DailyMixDataException(String string) {
        super(string);
    }
}
