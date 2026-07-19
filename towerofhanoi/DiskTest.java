// Virginia Tech Honor Code Pledge:

// Project 3 Spring 2025

// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those 
// who do.

// -- Simoni Behl (simonib)

package towerofhanoi;

import java.util.EmptyStackException;
import student.TestCase;

/**
 *  Tests the Disk methods makes sure that disks are correctly compared to 
 *  each other and, the width of the disk is correctly converted to a string,
 *  and lastly checks if two disks are equal based on their width.
 * 
 *  @author Simoni Behl
 *  @version Mar 18, 2025
 */
public class DiskTest extends TestCase {
    
    private Disk disk;
    private Disk disk1;
    private Disk disk2;
    
    /**
     * Sets up the Disk class by creating few disks to test the methods on
     */
    public void setUp() {
        disk = new Disk(20);
        disk1 = new Disk(20);
        disk2 = new Disk(30);
    }
    
    /**
     * Tests that the given disk is correctly compared to another disk, by 
     * returning a positive number, negative number, 0, or throwing an exception
     */
    public void testCompareTo() {
        Exception thrown = null;
        try {
            disk.compareTo(null);
        }
        catch (Exception EmptyStackException) {
            thrown = EmptyStackException;
        }
        assertNotNull(thrown);
        
        assertEquals(0, disk.compareTo(disk1));
        assertEquals(-10, disk.compareTo(disk2));
        assertEquals(10, disk2.compareTo(disk1));
        
        
    }
    
    /**
     * Tests that the integer width is correctly converted a string
     */
    public void testToString() {
        assertEquals("20", disk.toString());
        assertEquals("30", disk2.toString());
    }
    
    /**
     * Tests that two disks are equal based on their widths
     */
    public void testEquals() {
        assertTrue(disk.equals(disk));
        assertTrue(disk.equals(disk1));
        assertFalse(disk.equals("disk"));
        assertFalse(disk.equals(null));
        assertFalse(disk.equals(disk2));
    }
}
