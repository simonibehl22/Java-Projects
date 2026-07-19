// Virginia Tech Honor Code Pledge:

// Project 3 Spring 2025

// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.

// -- Simoni Behl (simonib)

package towerofhanoi;

import student.TestableRandom;
import cs2.Shape;
import java.awt.Color;

/**
 * The Disk class extends the Shape class for proper disk dimensions,
 * so the smaller disks are placed atop the larger ones and it implements
 * the Comparable<Disk> interface to allow for comparing the disks.
 * 
 * @author Simoni Behl
 * @version Mar 14, 2025
 */
public class Disk extends Shape implements Comparable<Disk> {

    private static final int DISK_HEIGHT = 10;

    /**
     * Constructs a disk with a given width, a specific height,
     * and a random background color
     * 
     * @param width
     *            The width of the disk
     */
    public Disk(int width) {
        super(0, 0, width, DISK_HEIGHT);
        TestableRandom randomNum = new TestableRandom();
        int red = randomNum.nextInt(256);
        int green = randomNum.nextInt(256);
        int blue = randomNum.nextInt(256);
        
        Color backgroundColor = new Color(red, green, blue);
        this.setBackgroundColor(backgroundColor);
    }


    /**
     * Compares a disk to other disks
     * 
     * @param otherDisk
     *            The other disk that our disk is compared to
     * @return int A number that is either positive, negative, or 0 based on
     *         if the disk is smaller or larger than the disk its compared to
     */
    public int compareTo(Disk otherDisk) {
        if (otherDisk == null) {
            throw new IllegalArgumentException();
        }

        if (this.getWidth() < otherDisk.getWidth()) {
            return (this.getWidth() - otherDisk.getWidth());
        }
        else if (this.getWidth() > otherDisk.getWidth()) {
            return (this.getWidth() - otherDisk.getWidth());
        }
        else {
            return 0;
        }
    }


    /**
     * Converts the width of the disk into a string
     * 
     * @return String The string version of the width
     */
    public String toString() {
        return "" + getWidth();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Disk otherDisk = (Disk)obj;
        return (this.getWidth() == otherDisk.getWidth());

    }
}
