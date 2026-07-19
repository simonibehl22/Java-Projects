// Virginia Tech Honor Code Pledge:

// Project 3 Spring 2025

// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those 
// who do.

// -- Simoni Behl (simonib)

package towerofhanoi;

import student.TestCase;

/**
 *  Tests the LinkedStack methods by creating a stack that is cleared properly,
 *  makes sure it can be checked if it is empty, ensures the right objects are 
 *  added, removed, and returned in the right order, the size is updated
 *  correctly, and the stack can be turned into and returned as a string.
 * 
 *  @author Simoni Behl
 *  @version Mar 18, 2025
 */
public class LinkedStackTest extends TestCase {
    
    private LinkedStack<String> stack;
    
    /**
     * Sets up a stack that can test the methods
     */
    public void setUp() {
        stack = new LinkedStack<>();
        
    }
    
    /**
     * Tests that a stack is properly cleared
     */
    public void testClear() {
        stack.push("blueberry");
        stack.push("orange");
        stack.clear();
        
        assertTrue(stack.isEmpty());
    }
    
    /**
     * Tests that a stack is properly checked if it is empty or not
     */
    public void testIsEmpty() {
        assertTrue(stack.isEmpty());
    }
    
    /**
     * Tests that the last item in the stack is correctly returned, 
     * without changing the stacks contents
     */
    public void testPeek() {
        Exception thrown = null;
        try {
            stack.peek();
        }
        catch (Exception EmptyStackException) {
            thrown = EmptyStackException;
        }
        assertNotNull(thrown);
        
        stack.push("blueberry");
        stack.push("apple");
     
        assertEquals("apple", stack.peek());
        assertEquals(2, stack.size());
        
        stack.push("orange");
        
        assertEquals("orange", stack.peek());
        assertEquals(3, stack.size());
        
    }
    
    /**
     * Tests that the last item is removed from the stack and the size is
     * updated
     */
    public void testPop() {
        Exception thrown = null;
        try {
            stack.pop();
        }
        catch (Exception EmptyStackException) {
            thrown = EmptyStackException;
        }
        assertNotNull(thrown);
        
        stack.push("blueberry");
        stack.push("apple");
        stack.push("orange");
        
        assertEquals("orange", stack.pop());
        assertEquals(2, stack.size());
    }
    
    /**
     * Tests that an item is added to the end of the stack and the size is
     * updated
     */
    public void testPush() {
        stack.push("apple");
        stack.push("orange");

        assertEquals(2, stack.size());
        assertEquals("orange", stack.peek());
        
        stack.push("blueberry");
        stack.push("banana");
        
        assertEquals(4, stack.size());
        assertEquals("banana", stack.peek());
    }
    
    /**
     * Tests that the size is correctly updated
     */
    public void testSize() {
        assertEquals(0, stack.size());
        
        stack.push("blueberry");
        stack.push("apple");
        stack.push("orange");
        
        assertEquals(3, stack.size());
    }
    
    /**
     * Tests that the stack is correctly converted and returned as a string
     */
    public void testToString() {
        stack.push("blueberry");
        stack.push("apple");
        stack.push("orange");
        
        assertEquals("[orange, apple, blueberry]", stack.toString());
    }
}
