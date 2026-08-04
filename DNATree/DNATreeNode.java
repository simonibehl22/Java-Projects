/**
 * Interface class for the different types of nodes of the tree
 * 
 * @author Simoni Behl
 * @version Spring 2026
 */

public interface DNATreeNode {

    /**
     * Inserts a sequence into the tree
     * 
     * @param sequence The sequence being inserted
     * @param level The current depth of the tree
     * @return DNATreeNode the node inserted
     */
    public DNATreeNode insert(String sequence, int level);
    
    /**
     * Removes a sequence from the tree
     * 
     * @param sequence The sequence being removed
     * @param level The current depth of the tree
     * @return DNATreeNode the node removed
     */
    public DNATreeNode remove(String sequence, int level);
    
    /**
     * The sequence being searched for in the tree
     * 
     * @param sequence The sequence being searched for
     * @param level The current depth of the tree
     * @param visitCount Number of nodes visited
     * @param str The string to be returned with appropriate information
     */
    public void search(String sequence, int level, 
        int[] visitCount, StringBuilder str);
    
    /**
     * The display of the tree details
     * 
     * @param level The current depth of the tree
     * @param mode The type of details we want to display
     * @param str The string to be returned with appropriate information
     */
    public void display(int level, int mode, StringBuilder str);

    /**
     * Checks if a sequence exists in the tree
     * 
     * @param sequence The sequence we want to check
     * @param level The current depth of the tree
     * @return true if it exists, false if not
     */
    public boolean exists(String sequence, int level);
}
