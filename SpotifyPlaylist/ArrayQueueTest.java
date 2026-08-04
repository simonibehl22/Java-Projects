// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Simoni Behl (simonib)

package dailymixes;

import student.TestCase;
import queue.EmptyQueueException;

/**
 *  Tests the ArrayQueue methods by adding and removing items and checking its 
 *  size, capacity, front value, as well as correctly converting to a
 *  string and array representations, and seeing if two array queues are equal
 * 
 *  @author Simoni Behl
 *  @version Apr 8, 2025
 */
public class ArrayQueueTest extends TestCase {
    
    private ArrayQueue<String> queue;
    private ArrayQueue<Song> queue1;
    private ArrayQueue<String> queue2;

    /**
     * Sets up array queues to test the methods on
     */
    public void setUp() {
        queue = new ArrayQueue<>(3);
        queue1 = new ArrayQueue<>(3);
        queue2 = new ArrayQueue<>();
    }
    
    /**
     * Tests the clear method to check that the array queue is correctly 
     * cleared and reverted to its default form 
     */
    public void testClear() {
        queue.enqueue("dynamite");
        queue.enqueue("24k");
        queue.enqueue("perfect");
        queue.clear();
        
        assertEquals(0, queue.getSize());
    }
    
    /**
     * Tests the toString method to see if the array queue items are correctly 
     * given as a string
     */
    public void testToString() {
        assertEquals("[]", queue.toString());
        
        queue.enqueue("dynamite");
        queue.enqueue("24k");
        queue.enqueue("perfect");
        assertEquals("[dynamite, 24k, perfect]", queue.toString());
        
        queue1.enqueue(new Song("The Final Countdown", 30, 45, 3, "p1"));
        queue1.enqueue(new Song("Our Song", 24, 14, 50, ""));
        String expected = "[The Final Countdown Pop:30 Rock:45 Country:3 "
            + "Suggested: p1, No-Playlist Our Song Pop:24 Rock:14 Country:50]";
        
        assertEquals(expected, queue1.toString());
        
    }
    
    /**
     * Tests the equals method to see if two array queues are equal based on 
     * its size and order of the elements
     */
    public void testEquals() {
        queue.enqueue("dynamite");
        queue.enqueue("24k");
        queue.enqueue("perfect");
        assertTrue(queue.equals(queue));
        
        queue2.enqueue("dynamite");
        queue2.enqueue("24k");
        queue2.enqueue("perfect");
        assertTrue(queue.equals(queue2));
        
        queue2.dequeue();
        assertFalse(queue.equals(queue2));
        
        queue2.enqueue("dynamite");
        assertFalse(queue.equals(queue2));
        assertFalse(queue.equals(null));
        assertFalse(queue2.equals("playlist"));
    }
    
    /**
     * Tests the isEmpty method to see if the array queue is empty or not
     */
    public void testIsEmpty() {
        queue.enqueue("dynamite");
        queue.enqueue("24k");
        queue.enqueue("perfect");
        assertFalse(queue.isEmpty());
        
        queue.clear();
        assertTrue(queue.isEmpty());
    }
    
    /**
     * Tests the dequeue method to see the exception is thrown if the array 
     * queue is empty and if not, then checks if the item is properly removed
     */
    public void testDequeue() {
        Exception thrown = null;
        try {
            queue.dequeue();
        }
        catch (EmptyQueueException e) {
            thrown = e;
        }
        assertNotNull(thrown);
        
        queue.enqueue("dynamite");
        queue.enqueue("24k");
        queue.enqueue("perfect");
        assertEquals("dynamite", queue.dequeue());
        assertEquals(2, queue.getSize());
    }
    
    /**
     * Tests the enqueue method to check that an item is correctly added to the 
     * array queue
     */
    public void testEnqueue() {
        queue.enqueue("dynamite");
        queue.enqueue("24k");
        assertEquals(2, queue.getSize());
        
        queue.enqueue("perfect");
        queue.enqueue("firework");
        assertEquals(4, queue.getSize());

    }
    
    /**
     * Tests that the length of underlying array is correct and the expanded
     * when needed based on capacity
     */
    public void testGetLengthOfUnderlyingArray() {
        queue.enqueue("dynamite");
        queue.enqueue("24k");
        assertEquals(4, queue.getLengthOfUnderlyingArray());
        
        queue.enqueue("perfect");
        assertEquals(4, queue.getLengthOfUnderlyingArray());
        
        queue.enqueue("firework");
        assertEquals(7, queue.getLengthOfUnderlyingArray());
    }
    
    /**
     * Tests the getFront method to check that an exception is thrown if the 
     * array queue is empty and if it isn't then the correct front value is 
     * returned
     */
    public void testGetFront() {
        Exception thrown = null;
        try {
            queue.getFront();
        }
        catch (EmptyQueueException e) {
            thrown = e;
        }
        assertNotNull(thrown);
        
        queue.enqueue("dynamite");
        queue.enqueue("24k");
        queue.enqueue("perfect");
        assertEquals("dynamite", queue.getFront());
        assertEquals(3, queue.getSize());
    }
    
    /**
     * Tests the getSize method to ensure that the correct size is returned
     */
    public void testGetSize() {
        queue.enqueue("dynamite");
        queue.enqueue("24k");
        queue.enqueue("perfect");
        assertEquals(3, queue.getSize());
    }
    
    /**
     * Tests the toArray method to check the array queue is properly converted
     * to an array
     */
    public void testToArray() {
        queue.enqueue("dynamite");
        queue.enqueue("24k");
        queue.enqueue("perfect");
        String[] expected = {"dynamite", "24k", "perfect"};

        assertEquals(expected[0], queue.toArray()[0]);
        assertEquals(expected[1], queue.toArray()[1]);
        assertEquals(expected[2], queue.toArray()[2]);
        
    }
}
