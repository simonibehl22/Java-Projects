// Virginia Tech Honor Code Pledge:

// Project 3 Spring 2025

// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those 
// who do.

// -- Simoni Behl (simonib)

package towerofhanoi;

import java.util.Observable;

/**
 *  HanoiSolver represents a Tower of Hanoi puzzle, based on how many disks
 *  are used, makes sure there are only three towers, disks are appropriately 
 *  moved from tower to tower and extends Observable so the display can be 
 *  updated with the proper disks.
 * 
 *  @author Simoni Behl
 *  @version Mar 18, 2025
 */
public class HanoiSolver extends Observable {
    
    private Tower left;
    private Tower center;
    private Tower right;
    private int numDisks;
    
    /**
     * Constructs the puzzle by initializing the fields
     * 
     * @param numDisks The number of disks
     */
    public HanoiSolver(int numDisks) {
        this.numDisks = numDisks;
        left = new Tower(Position.LEFT);
        center = new Tower(Position.CENTER);
        right = new Tower(Position.RIGHT);
    }
    
    /**
     * Provides the number of disks
     * 
     * @return int The number of disks
     */
    public int disks() {
        return numDisks;
    }
    
    /**
     * Provides the position of the towers
     * 
     * @param pos The position the tower is at
     * @return Tower which tower is at that positon
     */
    public Tower getTower(Position pos) {
        switch (pos) {
            case LEFT:
                return left;
            case CENTER:
                return center;
            case RIGHT:
                return right;
            default:
                return center;
        }
    }
    @Override
    public String toString() {
        return left.toString() + center.toString() + right.toString();
    }
    
    /**
     * Executes moving a disk from one tower to another
     * 
     * @param source The tower from which the disk is being moved from
     * @param destination The tower that the disk is being moved onto
     */
    public void move(Tower source, Tower destination) {
        Disk disk = source.pop();
        destination.push(disk);
        setChanged();
        notifyObservers(destination.position());
    }
    
    /**
     * Recursive method that moves the disks around
     * 
     * @param currentDisks The number of disks being moved
     * @param startPole The pole that the disk starts on
     * @param tempPole The pole that the disk temporarily sits on before going
     *          where its supposed to
     * @param endPole the pole that the disk will end on
     */
    public void solveTowers(int currentDisks, Tower startPole, Tower tempPole, 
        Tower endPole) {
        if (currentDisks == 1) {
            move(startPole, endPole);
            return;
        }
        
        solveTowers(currentDisks - 1, startPole, endPole, tempPole);
        move(startPole, endPole);
        solveTowers(currentDisks - 1, tempPole, startPole, endPole);
    }
 
    /**
     * Makes the initial call to the recursive method, solveTowers, 
     * and provides it with the correct parameters
     */
    public void solve() {
        solveTowers(numDisks, left, center, right);
    }
}
