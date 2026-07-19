// Virginia Tech Honor Code Pledge:

// Project 3 Spring 2025

// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those 
// who do.

// -- Simoni Behl (simonib)

package towerofhanoi;

import student.TestCase;

/**
 *  Tests the Tower class and makes sure the position is correctly returned
 *  and disks are added on the tower based on their size
 * 
 *  @author Simoni Behl
 *  @version Mar 24, 2025
 */
public class TowerTest extends TestCase {
    
    private Tower tower;
    private Disk disk1;
    private Disk disk2;
    
    /**
     * Sets up the tower class to test the methods on
     */
    public void setUp() {
        tower = new Tower(Position.LEFT);
        disk1 = new Disk(5);
        disk2 = new Disk(1);
    }
    
    /**
     * Tests that the position of the tower is returned correctly
     */
    public void testPosition() {
        assertEquals(Position.LEFT, tower.position());
    }
    
    /**
     * Tests that the disks are appropriately added onto the tower and
     * the smaller disks are on top of larger ones
     */
    public void testPush() {
        Exception thrown = null;
        try {
            tower.push(null);
        } 
        catch (IllegalArgumentException e) {
            thrown = e;
        }
        assertNotNull(thrown);
        
        tower.push(disk1);
        assertEquals(1, tower.size());
        assertEquals(disk1, tower.peek());
        
        Exception thrown1 = null;
        try {
            tower.push(new Disk(8)); 
        } 
        catch (IllegalStateException e) {
            thrown1 = e;
        }
        assertNotNull(thrown1);
  
        tower.push(disk2);
        assertEquals(2, tower.size());
        assertEquals(disk2, tower.peek());
    }
}
