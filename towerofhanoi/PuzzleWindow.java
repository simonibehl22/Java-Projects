// Virginia Tech Honor Code Pledge:

// Project 3 Spring 2025

// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those 
// who do.

// -- Simoni Behl (simonib)

package towerofhanoi;

import cs2.Button;
import cs2.Shape;
import cs2.Window;
import cs2.WindowSide;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * The main front-end work and the view for the Tower of Hanoi puzzle (Fall 2024)
 *
 * @author Simoni Behl
 * @version March 24, 2025
 */
public class PuzzleWindow implements Observer {

    private HanoiSolver game;
    private Shape left;
    private Shape center;
    private Shape right;
    private Window window;
   
    /**
     * A factor in which the width of the disks are multiplied by
     */
    public static final int WIDTH_FACTOR = 12;
    /**
     * The vertical gap between each disk on the tower
     */
    public static final int DISK_GAP = 2;
    /**
     * The height of each disk on the tower
     */
    public static final int DISK_HEIGHT = 15;


    /**
     * Creates a new PuzzleWindow view for a given HanoiSolver game
     *
     * @param g the game to create a view for
     */
    public PuzzleWindow(HanoiSolver g) {
        this.game = g;
        game.addObserver(this);
        
        window = new Window("Tower of Hanoi");
        window.setSize(600, 400);
        
      //The height and Y location of each pole are the same
        int poleHeight = 200;
        int poleY = (window.getGraphPanelHeight() / 2) - (poleHeight / 2);
        left = new Shape((200 - 15 / 2),
            poleY, 15, poleHeight, new Color(0, 0, 0));
        center = new Shape(((window.getGraphPanelWidth() / 2) - 15 / 2),
            poleY, 15, poleHeight, new Color(0, 0, 0));
        right = new Shape(((window.getGraphPanelWidth() - 200) - 15 / 2),
            poleY, 15, poleHeight, new Color(0, 0, 0));
        
        Tower leftTower = game.getTower(Position.LEFT);
        for (int width = (game.disks() + 1) * WIDTH_FACTOR;
            width > WIDTH_FACTOR;
            width -= WIDTH_FACTOR) {
            
            Disk disk = new Disk(width);
            window.addShape(disk);
            leftTower.push(disk);
            moveDisk(Position.LEFT);
            
        }
        window.addShape(left);
        window.addShape(center);
        window.addShape(right);
        
        Button solve = new Button("Solve");
        solve.onClick(this, "clickedSolve");
        window.addButton(solve, WindowSide.SOUTH);
    }
    
    private void moveDisk(Position position) {
        Tower tower = game.getTower(position);
        Disk currentDisk = tower.peek();
        Shape currentPole;
        if (position == Position.LEFT) {
            currentPole = left;
        }
        else if (position == Position.CENTER) {
                currentPole = center;
        }
        else if (position == Position.RIGHT) {
            currentPole = right;
        }
        else {
            currentPole = center;
        }
        
        int newX = currentPole.getX() + (currentPole.getWidth() - 
                        currentDisk.getWidth()) / 2;
        int newY = currentPole.getY() + currentPole.getHeight() - 
                        ((tower.size()) * DISK_HEIGHT) - DISK_GAP;
        
        currentDisk.moveTo(newX, newY);
    }

    /**
     * Updates the view whenever a disk is moved in the back-end
     *
     * @param o   The observable that triggered the update
     * @param arg arguments sent by the game; should be a position
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg.getClass() == Position.class) {
            Position position = (Position)arg;
            moveDisk(position);
            sleep();
        }
    }

    /**
     * Runs when the Solve button is clicked, tells the puzzle to start solving
     *
     * @param button the button that was clicked
     */
    public void clickedSolve(Button button) {
        button.disable();
        new Thread() {
            public void run() {
                game.solve();
            }
        }.start();
    }

    private void sleep() {
        try {
            Thread.sleep(500);
        }
        catch (Exception e) {
        }
    }
}
