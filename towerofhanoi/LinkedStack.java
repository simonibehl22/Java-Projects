// Virginia Tech Honor Code Pledge:

// Project 3 Spring 2025

// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those 
// who do.

// -- Simoni Behl (simonib)

package towerofhanoi;

import stack.StackInterface;
import java.util.EmptyStackException;

/**
 *  The LinkedStack Class implements the StackInterface to create linked nodes
 *  
 *  @param <T>
 * 
 *  @author Simoni Behl
 *  @version Mar 18, 2025
 */
public class LinkedStack<T> implements StackInterface<T> {
    private int size;
    private Node topNode;
    
    /**
     * Constructor used to initialize the instance variables
     */
    public LinkedStack() {
        topNode = null;
        size = 0;
    }

    @Override
    public void clear() {
        size = 0;
        topNode = null;
    }


    @Override
    public boolean isEmpty() {
        return (size == 0);
    }


    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        
        return topNode.getData();
    }


    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        
        T data = topNode.getData();
        topNode = topNode.getNext();
        size--;
        return data;
    }


    @Override
    public void push(T anEntry) {
        topNode = new Node(anEntry, topNode);
        size++;

    }
    
    /**
     * Returns the size of linked stack
     * 
     * @return size The size of the stack
     */
    public int size() {
        return size;
    }
    
    /**
     * Returns the string version of the disk widths on each tower
     * 
     * @return String the string version of the disk widths
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        Node curr = topNode;
        while (curr != null) {
            sb.append(curr.getData());
            if (curr.getNext() != null) {
                sb.append(", ");
            }
            curr = curr.getNext();
        }
        
        sb.append("]");
        return sb.toString();
        
    }
    
    private class Node {
        
        private T data;
        private Node next;
        
        public Node(T entry, Node node) {
            this(entry); 
            this.setNextNode(node);
        }
        
        public Node(T data) {
            this.data = data; 
        }
        
        public T getData() {
            return data;
        }
        
        public Node getNext() {
            return next;
        }
        
        public void setNextNode(Node nextNode) {
            this.next = nextNode;
        }
    }

}
