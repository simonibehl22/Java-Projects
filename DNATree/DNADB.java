//-------------------------------------------------------------------------

/**
 * The database implementation for this project.
 * This manages the commands for the DNA tree.
 *
 * @author CS3114/5040 Staff
 * @version Spring 2026
 *
 */
public class DNADB implements DNA {
    
    private DNATreeNode root;

    // ----------------------------------------------------------
    /**
     * Create a new DNADB object.
     */
    public DNADB()
    {
        this.root = DNATreeEmpty.getInstance();
    }


    // ----------------------------------------------------------
    /**
     * Insert a DNA string into the database
     * @param sequence The sequence to insert
     * @return The outcomes message string
     */
    public String insert(String sequence) {
        
        if (sequence == null) {
            return "Bad input: Sequence may not be null\r\n";
        }
        
        if (sequence.isEmpty()) {
            return "Bad input: Sequence may not be empty\r\n";
        }
        
        if (!isValid(sequence, false)) {
            return "Bad Input sequence |" + sequence + "|\r\n";
        }
        
        if (root.exists(sequence, 0)) {
            return "Sequence |" + sequence + "| already exists";
        }
        
        root = root.insert(sequence, 0);
        return "Sequence |" + sequence + "| inserted";
    }


    // ----------------------------------------------------------
    /**
     * Remove a DNA string into the database
     * @param sequence The sequence to remove
     * @return The outcomes message string
     */
    public String remove(String sequence) {
        if (sequence == null) {
            return "Bad input: Sequence may not be null\r\n";
        }
        
        if (sequence.isEmpty()) {
            return "Bad input: Sequence may not be empty\r\n";
        }
        
        if (!isValid(sequence, false)) {
            return "Bad Input sequence |" + sequence + "|\r\n";
        }

        if (!root.exists(sequence, 0)) {
            return "Sequence |" + sequence + "| does not exist";
        }
        
        root = root.remove(sequence, 0);
        return "Sequence |" + sequence + "| removed";
    }


    // ----------------------------------------------------------
    /**
     * Print the tree
     * @return the print string
     */
    public String print() {
        StringBuilder str = new StringBuilder();
        str.append("tree dump:\r\n");
        root.display(0, 0, str);
        return str.toString();
    }


    // ----------------------------------------------------------
    /**
     * Print the lengths
     * @return the print string
     */
    public String printLengths() {
        StringBuilder str = new StringBuilder();
        str.append("tree dump with lengths:\r\n");
        root.display(0, 1, str);
        return str.toString();
    }


    // ----------------------------------------------------------
    /**
     * Print the stats
     * @return the print string
     */
    public String printStats() {
        StringBuilder str = new StringBuilder();
        str.append("tree dump with stats:\r\n");
        root.display(0, 2, str);
        return str.toString();
    }


    // ----------------------------------------------------------
    /**
     * Search for a given string
     * @param sequence The sequence to search for
     * @return the print string
     */
    public String search(String sequence) {
        if (sequence == null) {
            return "Bad input: Sequence may not be null\r\n";
        }
        
        if (!isValid(sequence, true)) {
            return "Bad Input sequence |" + sequence + "|\r\n";
        }
        
        int[] visitCount = {0};
        StringBuilder str = new StringBuilder();

        root.search(sequence, 0, visitCount, str);
        
        if (str.length() == 0) {
            str.append("No sequence found\r\n");
        }
        
        return str.toString() + "# of nodes visited: " + visitCount[0];
    }
    
    /**
     * Tests to see if a sequence is valid, follows the appropriate rules
     * @param sequence The sequence being checked for validity
     * @param isSearch If it is trying to be searched or another method
     * @return true if it is valid, false if not
     */
    private boolean isValid(String sequence, boolean isSearch) {
        for (int i = 0; i < sequence.length(); i++) {
            char nucleotide = sequence.charAt(i);
            
            // if argument includes $, but isn't searching
            // or has a sequence following the terminator
            if (nucleotide == '$') {
                if (!isSearch || i != sequence.length() - 1) {
                    return false;
                }
            }
            
            // checks valid nucleotide values
            else if (nucleotide != 'A' && nucleotide != 'C'
                && nucleotide != 'G' && nucleotide != 'T') {
                return false;
            }
        }
        return true;
    }
}
