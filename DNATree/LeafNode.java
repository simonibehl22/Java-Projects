/**
 * Class that handles a leaf node, properly inserting, removing,
 * printing, and searching items accordingly
 * 
 * @author Simoni Behl
 * @version Spring 2026
 */

public class LeafNode implements DNATreeNode {
    
    private String seq;

    /**
     * Constructs a leaf node
     * @param sequence the sequence making a leaf node
     */
    public LeafNode(String sequence) {
        this.seq = sequence;
    }

    /**
     * Inserts a sequence into the tree,
     * creating a new leaf node
     * 
     * @param newSequence The sequence being inserted
     * @param level The current depth of the tree
     * @return DNATreeNode the node inserted
     */
    @Override
    public DNATreeNode insert(String newSequence, int level) { 
        InternalNode newNode = new InternalNode();
        newNode.insert(seq, level);
        // uses recursion to insert the new sequence
        return newNode.insert(newSequence, level);
    }

    /**
     * Removes a sequence from the tree,
     * turns into an empty tree, when leaf is removed
     * 
     * @param sequence The sequence being removed
     * @param level The current depth of the tree
     * @return DNATreeNode the node removed
     */
    @Override
    public DNATreeNode remove(String sequence, int level) {
        return DNATreeEmpty.getInstance();
    }
    /**
     * The sequence being searched for in the tree,
     * but only one node is in the tree to be checked
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
        
        if (sequence.endsWith("$")) {
            String newSequence = sequence.substring(0, sequence.length() - 1);
            if (seq.equals(newSequence)) {
                str.append(seq).append("\r\n");
            }
        }
        else if (seq.startsWith(sequence)) {
            str.append(seq).append("\r\n");
        }
    }
    
    /**
     * The display of the tree details
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
        
        if (mode == 1) {
            str.append(seq).append(" ").append(seq.length());
        }
        
        else if (mode == 2) {
            str.append(seq).append(" ").append(calculateStats(seq));
        }
        else {
            str.append(seq);
        }
        
        str.append("\r\n");
    }
    
    /**
     * Calculates the statistics of each nucleotide based on the 
     * sequence
     * @param sequence The sequence of which the stats are being
     * calculated
     * @return The String providing the statistics
     */
    private String calculateStats(String sequence) {
        double a = 0;
        double c = 0;
        double g = 0;
        double t = 0;
        double length = sequence.length();
        
        for (int i = 0; i < sequence.length(); i++) {
            char nucleotide = sequence.charAt(i);
            
            if (nucleotide == 'A') {
                a++;
            }
            
            else if (nucleotide == 'C') {
                c++;
            }
            
            else if (nucleotide == 'G') {
                g++;
            }
            
            else if (nucleotide == 'T') {
                t++;
            }
        }
        
        return String.format("A:%.2f C:%.2f G:%.2f T:%.2f", 
            (a / length) * 100.00,
            (c / length) * 100.00, 
            (g / length) * 100.00, 
            (t / length) * 100.00);
    }
    

    /**
     * Checks if a sequence exists in the tree,
     * except there is only one sequence to check
     * 
     * @param sequence The sequence we want to check
     * @param level The current depth of the tree
     * @return true if it exists, false if not
     */
    public boolean exists(String sequence, int level) {
        return seq.equals(sequence);
    }
}
