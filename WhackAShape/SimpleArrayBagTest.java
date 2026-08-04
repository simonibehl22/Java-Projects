// Virginia Tech Honor Code Pledge:
//
// This is a submission for project 2 (Spring 2025)
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of 
// those who do.
// -- Simoni Behl (simonib)

package game;

import student.TestCase;
import student.TestableRandom;

/**
 *  Tests the SimpleArrayBag methods and ensures that a bag can correctly add 
 *  and remove Strings and gives the right size of the bag and returns empty
 *  if there's nothing, also checks that it correctly picks items out of the 
 *  bag at randomly generated indexes.
 * 
 *  @author Simoni Behl
 *  @version Feb 24, 2025
 */
public class SimpleArrayBagTest extends TestCase {
    
    private SimpleArrayBag<String> bag;
  
    /**
     * Sets up the SimpleArrayBag by creating a bag to test the methods on
     */
    public void setUp() {
        bag = new SimpleArrayBag<>();
        bag.add("Red");
        bag.add("Blue");
        bag.add("Pink");
        bag.add("Indigo");
        bag.add("Yellow");
        bag.add("Orange");
        bag.add("Green");
        bag.add("Purple");
        
    }
    
    /**
     * Tests that strings are correctly added into the bag, but ensures the
     * bag doesn't exceed its size limit
     */
    public void testAdd() {
        assertTrue(bag.add("Purple"));
        assertFalse(bag.add(null));
        
        bag.add("Light Yellow");
        bag.add("Citrus Orange");
        bag.add("Fire Red");
        bag.add("Lime Green");
        bag.add("Bright Blue");
        bag.add("Lavender");
        bag.add("Hot Pink");
        bag.add("Gray");
        bag.add("White");
        assertFalse(bag.add("Black"));
    }
    
    /**
     * Tests that the correct size of the bag is returned, even when strings are
     * removed
     */
    public void testGetCurrentSize() {
        assertEquals(8, bag.getCurrentSize());
        
        bag.remove("Red");
        bag.remove("Orange");
        assertEquals(6, bag.getCurrentSize());
       
    }
    
    /**
     * Tests that the bag is not empty when it has strings in it and
     * is empty once all strings are removed
     */
    public void testIsEmpty() {
        assertFalse(bag.isEmpty());
        
        bag.remove("Red");
        bag.remove("Blue");
        bag.remove("Pink");
        bag.remove("Indigo");
        bag.remove("Yellow");
        bag.remove("Orange");
        bag.remove("Green");
        bag.remove("Purple");
        assertTrue(bag.isEmpty());
    }
    
    /**
     * Tests that the strings are correctly picked from the bag,
     * using the first in, first out rule, at their
     * respective indexes that would be randomly generated
     */
    public void testPick() {
        TestableRandom.setNextInts(0, 4);
        assertEquals("Red", bag.pick());
        assertEquals("Yellow", bag.pick());
        
        bag.remove("Red");
        bag.remove("Blue");
        bag.remove("Pink");
        bag.remove("Indigo");
        bag.remove("Yellow");
        bag.remove("Orange");
        bag.remove("Green");
        bag.remove("Purple");
        assertNull(bag.pick());
        
    }
    
    /**
     * Tests that the strings are correctly removed from the bag
     */
    public void testRemove() {
        assertTrue(bag.remove("Red"));
        assertTrue(bag.remove("Orange"));
        assertFalse(bag.remove("Light Blue"));
        
        bag.remove("Blue");
        bag.remove("Pink");
        bag.remove("Indigo");
        bag.remove("Yellow");
        bag.remove("Green");
        bag.remove("Purple");
        assertFalse(bag.remove("Pink"));
        
    }
    
}
