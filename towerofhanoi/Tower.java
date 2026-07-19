// Virginia Tech Honor Code Pledge:

// Project 3 Spring 2025

// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those 
// who do.

// -- Simoni Behl (simonib)

package towerofhanoi;

/**
 *  Tower class extends the LinkedStack class and stores Disks as stacks
 *  and make sure only smaller disks go on top of bigger ones.
 * 
 *  @author Simoni Behl
 *  @version Mar 18, 2025
 */
public class Tower extends LinkedStack<Disk> {
    
    private Position position;
    
    /**
     * Constructs a stack and initializes the position
     * 
     * @param position The position of the tower
     */
    public Tower(Position position) {
        super();
        this.position = position;
        
    }
    
    /**
     * Provides the position
     * 
     * @return Position The position of the tower
     */
    public Position position() {
        return position;
    }
    
    @Override
    public void push(Disk disk) {
        if (disk == null) {
            throw new IllegalArgumentException();
        }
        
        if (isEmpty() || peek().compareTo(disk) > 0) {
            super.push(disk);
        }
  
        else {
            throw new IllegalStateException();
        }
    }
}
