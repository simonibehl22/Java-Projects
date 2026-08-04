/**
 * Class that handles an internal node, properly inserting, removing,
 * printing, and searching items accordingly
 * 
 * @author Simoni Behl
 * @version Spring 2026
 */

public class InternalNode implements DNATreeNode {

    private DNATreeNode[] children;
    
    public InternalNode() {
        children = new DNATreeNode[5];
        for (int i = 0; i < 5; i++) {
            children[i] = DNATreeEmpty.getInstance();
        }
    }
    
    /**
     * Inserts a sequence into the tree
     * 
     * @param sequence The sequence being inserted
     * @param level The current depth of the tree
     * @return DNATreeNode the node inserted
     */
    @Override
    public DNATreeNode insert(String sequence, int level) {
        int index = getIndex(sequence, level);
        
        children[index] = children[index].insert(sequence, level + 1);
        return this;
    }

    /**
     * Gets the child branch index of a sequence at a specific depth
     * @param sequence The sequence of which we want the index
     * @param level The current depth of the tree
     * @return The number indicating which index
     */
    private int getIndex(String sequence, int level) {
        if (level >= sequence.length()) {
            return 4;
        }
        char c = sequence.charAt(level);
        switch (c) {
            case 'A' : return 0;
            case 'C' : return 1;
            case 'G' : return 2;
            case 'T' : return 3;
            default : return 4;
        }
    }

    /**
     * Removes a sequence from the tree
     * 
     * @param sequence The sequence being removed
     * @param level The current depth of the tree
     * @return DNATreeNode the node removed
     */
    @Override
    public DNATreeNode remove(String sequence, int level) {
        int index = getIndex(sequence, level);
        
        // uses recursion to remove the sequence
        children[index] = children[index].remove(sequence, level + 1);
        
        int internalCount = 0;
        int leafCount = 0;
        DNATreeNode lastLeaf = null;
        
        for (int i = 0; i < 5; i++) {
            if (children[i] instanceof InternalNode) {
                internalCount++;
            }
            
            else if (children[i] instanceof LeafNode) {
                leafCount++;
                lastLeaf = children[i];
            }
        }
        
        if (internalCount > 0) {
            return this;
        }
        
        // empty node
        if (leafCount == 0) {
            return DNATreeEmpty.getInstance();
        }
        
        // leaf node
        if (leafCount == 1) {
            return lastLeaf;
        }
        
        return this;
    }

    /**
     * The sequence being searched for in the tree
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
        
        if (level < sequence.length()) {
            int index = getIndex(sequence, level);
            children[index].search(sequence, level + 1, visitCount, str);
        }
        else {
            for (int i = 0; i < 5; i++) {
                children[i].search(sequence, level + 1, visitCount, str);
            }
        }
    }

    /**
     * The display of the tree details,
     * based on which information we want to show
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
        
        str.append("I\r\n");
        
        for (int i = 0; i < 5; i++) {
            children[i].display(level + 1, mode, str);
        }
        
    }

    /**
     * Checks if a sequence exists in the tree
     * 
     * @param sequence The sequence we want to check
     * @param level The current depth of the tree
     * @return true if it exists, false if not
     */
    @Override
    public boolean exists(String sequence, int level) {
        int index = getIndex(sequence, level);
        return children[index].exists(sequence, level + 1);
    }

}
