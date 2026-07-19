// Virginia Tech Honor Code Pledge:

// Project 3 Spring 2025

// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those 
// who do.

// -- Simoni Behl (simonib)

package towerofhanoi;

import student.TestCase;

/**
 *  Tests the HanoiSolver class to ensure the disks are being moved around
 *  properly based on size
 * 
 *  @author simoni
 *  @version Mar 24, 2025
 */
public class HanoiSolverTest extends TestCase {

    private HanoiSolver solver;
    
    /** 
     * Sets up the HanoiSolver class with a number of disks to test with
     */
    public void setUp() {
        solver = new HanoiSolver(3);
    }
    
    /**
     * Tests the Disks method to make sure the right number of disks
     * are returned
     */
    public void testDisks() {
        assertEquals(3, solver.disks());
    }
    /**
     * Tests the getTower method to make sure the towers align with their 
     * corresponding positions
     */
    public void testGetTower() {
        assertEquals(Position.LEFT, 
            solver.getTower(Position.LEFT).position());
        assertEquals(Position.CENTER, 
            solver.getTower(Position.CENTER).position());
        assertEquals(Position.RIGHT, 
            solver.getTower(Position.RIGHT).position());
        assertEquals(Position.CENTER, 
            solver.getTower(Position.DEFAULT).position());
    }
    /**
     * Tests the toString method to make sure the disk widths are
     * appropriately converted into strings based on which poles they lie on
     */
    public void testToString() {
        assertEquals("[][][]", solver.toString()); 
       
    }
    /**
     * Tests the move method to make sure the disks are moved from
     * the tower they are on to the one they are supposed to end up on
     */
    public void testMove() {
        Disk disk = new Disk(5);
        Tower source = solver.getTower(Position.LEFT);
        Tower destination = solver.getTower(Position.RIGHT);
        
        source.push(disk);
        
        solver.move(source, destination);
        assertEquals("[][][5]", solver.toString());
        
    }
    /**
     * Tests the solveTowers and solve method by moving multiple disks from
     * one tower to another to solve the puzzle
     */
    public void testSolveTowers() {
        Tower left = solver.getTower(Position.LEFT);
        Tower center = solver.getTower(Position.CENTER);
        Tower right = solver.getTower(Position.RIGHT);
        
        Disk disk = new Disk(6);
        Disk disk1 = new Disk(4);
        Disk disk2 = new Disk(2);
        left.push(disk);
        left.push(disk1);
        left.push(disk2);
        int numDisks = 3;
        
        solver.solve();
        assertEquals(3, right.size());
    }
}
