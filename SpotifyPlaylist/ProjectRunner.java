// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Simoni Behl (simonib)

package dailymixes;

import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 *  Runs the project by determining the source of the input files
 * 
 *  @author Simoni Behl
 *  @version Apr 15, 2025
 */
public class ProjectRunner {
    /**
     * Constructs the project by initializing the playlist reader with filenames
     * 
     * @param args The string input for the filenames
     * 
     * @throws
     * @throws
     * @throws
     */
    public static void main(String[] args)
        throws ParseException, DailyMixDataException, FileNotFoundException {

        if (args.length == 2) {
            PlaylistReader reader = new PlaylistReader(args[0], args[1]);
        }
        else {
            PlaylistReader reader = new PlaylistReader
                ("input.txt", "playlists.txt");
        }
    }
}
