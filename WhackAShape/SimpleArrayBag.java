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

/**
 * // -------------------------------------------------------------------------
/**
 *  The SimpleArrayBag class implements the SimpleBagInterface to create a 
 *  randomly generated bag using arrays
 *  
 *  @param <T>
 * 
 *  @author Simoni Behl
 *  @version Feb 24, 2025
 */
public class SimpleArrayBag<T> implements SimpleBagInterface<T> {

    private T[] bag;
    private static final int MAX = 18;
    private int numberOfEntries;
    
    /**
     * Constructor used to initialize the fields
     */
    public SimpleArrayBag() {
        @SuppressWarnings("unchecked")
        T[] tempbag = (T[]) new Object[MAX];
        bag = tempbag; 
    }
        
    @Override
    public boolean add(T anEntry) {
        if (anEntry == null) {
            return false;
        }
        
        if (numberOfEntries < MAX) {
            bag[numberOfEntries] = anEntry;
            numberOfEntries++;
            return true;
        }
        return false;
        
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
        if (numberOfEntries == 0) {
            return null;
        }
        
        TestableRandom generator = new TestableRandom(); 
        int index = generator.nextInt(numberOfEntries);
        return bag[index];
    }

 
    private int getIndexOf(T anEntry) {
        for (int i = 0; i < numberOfEntries; i++) {
            if (bag[i].equals(anEntry)) {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public boolean remove(T anEntry) {
        int index = getIndexOf(anEntry);
        if (index == -1) {
            return false;
        }
        
        bag[index] = bag[numberOfEntries - 1];
        bag[numberOfEntries - 1] = null;
        numberOfEntries--;
        return true;
    }
    
   
    
}
