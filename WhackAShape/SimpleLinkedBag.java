// Virginia Tech Honor Code Pledge:
//
// This is a submission for project 2 (Spring 2025)
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of 
// those who do.
// -- Simoni Behl (simonib)

package game;

import bag.SimpleBagInterface;
import student.TestableRandom;
import bag.Node;

/**
 * // -------------------------------------------------------------------------
/**
 *  The SimpleLinkedBag class implements the SimpleBagInterface to create a 
 *  randomly generated bag using nodes
 *  
 *  @param <T>
 * 
 *  @author Simoni Behl
 *  @version Feb 24, 2025
 */
public class SimpleLinkedBag<T> implements SimpleBagInterface<T> {
    
    private Node<T> firstNode;
    private int numberOfEntries;
    
    /**
     * Constructor used to initialize the instance variables
     */
    public SimpleLinkedBag() {
        firstNode = null;
        numberOfEntries = 0;
    }
    
    @Override
    public boolean add(T anEntry) {
        if (anEntry == null) {
            return false;
        }
        
        Node<T> newNode = new Node<>(anEntry);
        newNode.setNext(firstNode);
        firstNode = newNode;
        numberOfEntries++;
        return true;

    }


    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }


    @Override
    public boolean isEmpty() {
        return (numberOfEntries == 0);
    }

    @Override
    public T pick() {
        if (isEmpty()) {
            return null;
        }
        
        TestableRandom generator = new TestableRandom(); 
        int index = generator.nextInt(numberOfEntries);
        Node<T> currentNode = firstNode;
        
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        
        return currentNode.getData();
    }


    private Node<T> getReferenceTo(T anEntry) {
        boolean found = false;
        Node<T> currentNode = firstNode;
        
        while (currentNode != null) {
            if (currentNode.getData().equals(anEntry)) {
                found = true;
                return currentNode;
            }
            currentNode = currentNode.getNext();
            
        }
        return null;
        
    }
    
    @Override
    public boolean remove(T anEntry) {
        Node<T> removeNode = getReferenceTo(anEntry);
        
        if (getReferenceTo(anEntry) == null) {
            return false;
        }
        
        removeNode.setData(firstNode.getData());
        firstNode = firstNode.getNext();
        numberOfEntries--;

        return true;
    }

}
