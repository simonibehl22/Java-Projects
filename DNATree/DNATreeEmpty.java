/**
 * Interface class for the DNA String Database Project
 *
 * @author CS3114/5040 Staff
 * @version Spring 2026
 *
 */

public class DNATreeEmpty implements DNATreeNode {

    private static final DNATreeEmpty INSTANCE = new DNATreeEmpty();
    
    private DNATreeEmpty() {
        
    }
    
    /**
     * Creates an instance to implement flyweight pattern,
     * so the tree has a shared memory
     * @return the shared DNATreeEmpty instance
     */
    public static DNATreeEmpty getInstance() {
        return INSTANCE;
    }
    
    /**
     * Inserts a sequence into the tree,
     * specifically an empty tree
     * 
     * @param sequence The sequence being inserted
     * @param level The current depth of the tree
     * @return DNATreeNode the node inserted
     */
    @Override
    public DNATreeNode insert(String sequence, int level) {
        return new LeafNode(sequence);
    }
    
    /**
     * Removes a sequence from the tree,
     * which is not possible in an empty tree
     * 
     * @param sequence The sequence being removed
     * @param level The current depth of the tree
     * @return DNATreeNode the node removed
     */
    @Override
    public DNATreeNode remove(String sequence, int level) {
        return this;
    }

    /**
     * The sequence being searched for in the tree,
     * tree is empty, so only one node would be visited
     * with nothing there
     * 
     * @param sequence The sequence being searched for
     * @param level The current depth of the tree
     * @param visitCount Number of nodes visited
     * @param str The string to be returned with appropriate information
     */
    @Override
    public void search(String sequence, int level, int[] visitCount, 
        StringBuilder str) {
        visitCount[0]++; 
    }

    /**
     * The display of the tree details,
     * accounts for empty spots in the tree
     * 
     * @param level The current depth of the tree
     * @param mode The type of details we want to display
     * @param str The string to be returned with appropriate information
     */
    @Override
    public void display(int level, int mode, StringBuilder str) {
        for (int i = 0; i < level; i++) {
            str.append("  ");
        }
        str.append("E\r\n");
    }

    /**
     * Checks if a sequence exists in the tree,
     * specifically when no sequence exists in the empty tree
     * 
     * @param sequence The sequence we want to check
     * @param level The current depth of the tree
     * @return true if it exists, false if not
     */
    @Override
    public boolean exists(String sequence, int level) {
        return false;
    }

}
