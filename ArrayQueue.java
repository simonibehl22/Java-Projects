// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Simoni Behl (simonib)

package dailymixes;

import queue.QueueInterface;
import queue.EmptyQueueException;

/**
 *  The ArrayQueue class implements the QueueInterface to use circular
 *  array implementation and provide default queue behavior, besides that this
 *  class can clear an array queue, tell us if its empty or full, and check the 
 *  array queue length, as well as provide a string and array representation 
 *  and compare two array queues and tell us if they are equal or not
 *  
 *  @param <T>
 * 
 *  @author Simoni Behl
 *  @version Apr 8, 2025
 */
public class ArrayQueue<T> implements QueueInterface<T> {
    
    /**
     * The default capacity applied to the array when none is given
     */
    public static final int DEFAULT_CAPACITY = 20;
    private T[] queue;
    private int dequeueIndex;
    private int size;
    private int enqueueIndex;
    
    /**
     * Constructs and array queue by initializing the fields
     * 
     * @param capacity The capacity of the array queue
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        queue = (T[]) new Object[capacity + 1];
        dequeueIndex = 0;
        enqueueIndex = 0;
        size = 0;
    }
    
    /**
     * Second constructor used when no capacity is given, so sets the array
     * queue to the default capacity
     */
    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }
    
    /**
     * Clears the array queue and resets the fields to their default values
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        queue = (T[]) new Object[DEFAULT_CAPACITY + 1];
        dequeueIndex = 0;
        enqueueIndex = 0;
        size = 0;
    }
    
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        for ( int i = 0; i < size; i++) {
            sb.append(queue[(dequeueIndex + i) % queue.length]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        
        sb.append("]");
        
        return sb.toString();

    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        ArrayQueue<?> other = (ArrayQueue<?>) obj;
        
        if (this.size != other.size) {
            return false;
        }
        
        for (int i = 0; i < size; i++) {
            T element = this.queue[(this.dequeueIndex + i) % this.queue.length];
            Object otherElement = other.queue[(other.dequeueIndex + i) 
                                              % other.queue.length];
            if (!element.equals(otherElement)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Tells us if the array queue is empty or not
     * 
     * @return boolean True if the array queue is empty and false if not
     */
    public boolean isEmpty() {
        return (size == 0);
    }
    
    /**
     * Removes an item from the front of the array queue
     * 
     * @return T the item that is removed
     * @throws EmptyQueueException if the array queue is empty
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        
        T item = queue[dequeueIndex];
        queue[dequeueIndex] = null;
        dequeueIndex = (dequeueIndex + 1) % queue.length;
        size--;
        
        return item;
    }
    
    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        int newCapacity = (queue.length * 2) - 1;
       
        T[] newQueue = (T[]) new Object[newCapacity];
        
        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[(dequeueIndex + i) % queue.length];
        }
        
        queue = newQueue;
        dequeueIndex = 0;
        enqueueIndex = size;
    }
    
    /**
     * Adds an item to the end of the array queue and expands capacity if the
     * array queue is already full
     * 
     * @param queueItem The item that is being added
     */
    public void enqueue(T queueItem) {
        if (isFull()) {
            ensureCapacity();
        }
        queue[enqueueIndex] = queueItem;
        enqueueIndex = (enqueueIndex + 1) % queue.length;
        size++;
    }
    
    /**
     * Gets the length of the underlying array
     * 
     * @return int The length of the underlying array
     */
    public int getLengthOfUnderlyingArray() {
        return queue.length;
    }
    
    /**
     * Gets the front value of array queue
     * 
     * @return T The value at the front of the array queue
     * @throws EmptyQueueException if the array queue is empty
     */
    public T getFront() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        
        T item = queue[dequeueIndex];
        return item;
    }
    
    /**
     * Gets the size of the array queue
     * 
     * @return int The size of the array queue
     */
    public int getSize() {
        return size;
    }
    
    private boolean isFull() {
        return size == queue.length - 1;
    }
    
    /**
     * Converts the array queue to an array
     * 
     * @return Object[] The array representation of the array queue
     */
    public Object[] toArray() {
        Object[] result = new Object[size];
        
        for (int i = 0; i < size; i++) {
            result[i] = queue[(dequeueIndex + i) % queue.length];
        }

        return result;
    }
}
