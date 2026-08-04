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
 * // -------------------------------------------------------------------------
/**
 *  Tests the SimpleLinkedBag methods and ensures that a bag can correctly add 
 *  and remove Strings and gives the right size of the bag and returns empty
 *  if there's nothing, also checks that it correctly picks items out of the 
 *  bag at randomly generated indexes.
 * 
 *  @author Simoni Behl
 *  @version Feb 24, 2025
 */
public class SimpleLinkedBagTest extends TestCase {
    
    private SimpleLinkedBag<String> bag;
    
    /**
     * Sets up the SimpleLinkedBag by creating a bag to test the methods on
     */
    public void setUp() {
        bag = new SimpleLinkedBag<>();
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
     * Tests that strings are correctly added into the bag
     */
    public void testAdd() {
        assertTrue(bag.add("Light Purple"));
        assertFalse(bag.add(null));
    }
    
    /**
     * Tests that the correct size of the bag is returned, even when strings are
     * removed
     */
    public void testGetCurrentSize() {
        assertEquals(8, bag.getCurrentSize());
        
        assertTrue(bag.remove("Red"));
        assertTrue(bag.remove("Yellow"));
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
     * using the last in, first out rule, at their
     * respective indexes that would be randomly generated
     */
    public void testPick() {
        TestableRandom.setNextInts(0, 4);
        assertEquals("Purple", bag.pick());
        assertEquals("Indigo", bag.pick());
        
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
        assertTrue(bag.remove("Yellow"));
        assertFalse(bag.remove("Baby Blue"));
    }
    
}
