// Virginia Tech Honor Code Pledge:
//
// This is a submission for project 2 (Spring 2025)
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of 
// those who do.
// -- Simoni Behl (simonib)

package game;

/**
 *  Runs the project so that the window is created and displays
 *  the correct shapes from the bag in the window
 * 
 *  @author Simoni Behl
 *  @version Feb 24, 2025
 */
public class ProjectRunner {
    
    /**
     *  Creates a new WhackAShape object using one of the two constructors
     *  and creates a window displaying the shapes from the bag created
     *  
     *  @param  args An array of Strings
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            new WhackAShape(args);
        }
            
        new WhackAShape();
       
    }

}
