// Virginia Tech Honor Code Pledge:

// Project 3 Spring 2025

// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those 
// who do.

// -- Simoni Behl (simonib)

package towerofhanoi;

/**
 *  Runs the project so that the window is created and displays
 *  the towers and disks properly and updates the window as well.
 * 
 *  @author Simoni Behl
 *  @version Mar 24, 2025
 */
public class ProjectRunner {
    
    /**
     * Creates a window displaying the Hanoi Solver puzzle, with its 
     * towers and disks
     * 
     * @param args An array of Strings
     */
    public static void main(String[] args) {
        int disks = 5;
        
        if (args.length == 1) {
            disks = Integer.parseInt(args[0]);
        }
        HanoiSolver solver = new HanoiSolver(disks);
        PuzzleWindow window = new PuzzleWindow(solver);
    }
}
