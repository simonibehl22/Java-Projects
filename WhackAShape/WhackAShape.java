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
import cs2.Window;
import cs2.Button;
import cs2.Shape;
import cs2.CircleShape;
import cs2.SquareShape;
import cs2.WindowSide;
import java.awt.Color;
import cs2.TextShape;

/**
 * // -------------------------------------------------------------------------
/**
 *  The WhackAShape class creates a window with a quit button 
 *  that takes strings from a bag and displays their corresponding shape, 
 *  once the user clicks on the shape it switches to the next shape 
 *  until the bag is empty
 * 
 *  @author Simoni Behl
 *  @version Feb 24, 2025
 */
public class WhackAShape {

    private static final String[] STRINGS = {
        "red circle", "blue circle", "red square", "blue square"
    };
    private SimpleBagInterface<Shape> bag;
    private Window window;
    private TestableRandom randomGenerator;
    private Button quitButton;
    
    private Shape buildShape(String input) {
        this.randomGenerator = new TestableRandom();
        int size = randomGenerator.nextInt(101) + 100;
        
        int x = randomGenerator.nextInt(window.getGraphPanelWidth() - size);
        int y = randomGenerator.nextInt(window.getGraphPanelHeight() - size);
        
        Color color;
        input.toLowerCase();
        
        if (input.contains("red")) {
            color = Color.RED;
        }
        else if (input.contains("blue")) {
            color = Color.BLUE;
        }
        else {
            throw new IllegalArgumentException();
        }
        
        Shape currentShape;
        if (input.contains("circle")) {
            currentShape = new CircleShape(x, y, size, color);
        }
        else if (input.contains("square")) {
            currentShape = new SquareShape(x, y, size, color);
        }
        else {
            throw new IllegalArgumentException();
        }
        currentShape.onClick(this, "clickedShape");
        return currentShape;
    }

    /**
     * The shape displayed on the window is removed from the bag and the window
     * displays the next shape in the bag in the center of the screen
     *
     *@param shape The shape that is clicked on in the window
     */
    public void clickedShape(Shape shape) {
        window.removeShape(shape);
        bag.remove(shape);
        
        Shape nextShape = bag.pick();
        if (nextShape == null) {
            int x = window.getGraphPanelWidth() / 2;
            int y = window.getGraphPanelHeight() / 2;
            
            TextShape textShape = new TextShape(x, y, "You Win!");
            window.addShape(textShape);
        }
        window.addShape(nextShape);
        
    }
    
    /**
     * The method makes it so if the Quit button is clicked
     * the system exits the window.
     * 
     * @param button The button that triggers the quit action
     */
    public void clickedQuit(Button button) {
        System.exit(0);
    }
    
    /**
     * Calls the getBag function found in the BagInterface that is imported.
     *
     *@return bag the Bag created that contains random Strings
     */
    public SimpleBagInterface<Shape> getBag() {
        return bag;
    }
    
    /**
     * Calls the getWindow function.
     *
     *@return window the Window created that contains the shapes from the bag
     */
    public Window getWindow() {
        return window;
    }
    
    /**
     * Default constructor that creates a bag that has a minimum of 6 items and
     * a maximum of 14 items randomly picked from the Strings constant,
     * also constructs the quit button and displays the string in the window
     */
    public WhackAShape() {
        this.window = new Window();
        this.quitButton = new Button("Quit");
        this.quitButton.onClick(this, "clickedQuit");
        this.window.addButton(this.quitButton, WindowSide.SOUTH);
        
        this.randomGenerator = new TestableRandom();
        int bagSize = randomGenerator.nextInt(9) + 6;
        bag = new SimpleArrayBag<>();
        
        for (int i = 0; i < bagSize; i++) {
            String shapeString = STRINGS[randomGenerator
                                         .nextInt(STRINGS.length)];
            Shape shape = buildShape(shapeString);
            bag.add(shape);
        }
        Shape shape = bag.pick();
        if (shape != null) {
            window.addShape(shape);
        }
       
    }
    
    /**
     * Constructs a bag from the string inputs and turns them into shapes,
     * catches errors in the input, and also constructs the quit button 
     * and displays the string in the window
     * 
     * @param inputs an array of Strings that are converted to shapes
     */
    public WhackAShape(String[] inputs) {
        this.window = new Window();
        this.quitButton = new Button("Quit");
        this.window.addButton(this.quitButton, WindowSide.SOUTH);
        bag = new SimpleArrayBag<>();
        
        for (String input : inputs) {
            try {
                Shape shape = buildShape(input);
                bag.add(shape);
            }
            
            catch (Exception e){
                e.printStackTrace();
            }
        }
        
        Shape shape = bag.pick();
        if (shape != null) {
            window.addShape(shape);
        }
    } 
}
