package elementaryCellularAutomata;
import java.util.Random;

public class RuleSet {

    // creating private variables for the rule, rules, and probabilities (if using probabilistic rules)
    private int rule;
    private boolean[] rules;
    private double[] probabilities;

    /**
     * Constructor for deterministic rules.
     * 
     * @param rule An integer representing the deterministic rule
     */
    public RuleSet(int rule) {
        this.rule = rule;
        this.probabilities = null;
        this.rules = new boolean[8];
        for (int i = 0; i < 8; i++) {
            int lastBit = rule & 1;
            this.rules[i] = lastBit == 1;
            rule = rule >> 1;
        }
        
    }

    /**
     * Constructor for deterministic rules.
     * 
     * @param rules An array of boolean values representing the rules for each configuration
     */
    public RuleSet(boolean[] rules) {
        this.rules = rules;
        this.probabilities = null;
    }

    /**
     * Constructor for probabilistic rules.
     * 
     * @param probabilities An array of probabilities for each possible configuration
     */
    public RuleSet(double[] probabilities) {
        this.probabilities = probabilities;
        this.rules = null;
    }

    /**
     * Determines the next state of a cell based on its neighbors and the ruleset.
     * 
     * @param left The state of the left neighbor cell
     * @param center The state of the current cell
     * @param right The state of the right neighbor cell
     * @param random A Random object for probabilistic rules
     * @return The next state of the cell
     */


    public boolean getNext(boolean left, boolean center, boolean right, Random random) {
        // calculate the configuration index based on the states of the three cells
        int configuration = (left ? 4 : 0) + (center ? 2 : 0) + (right ? 1 : 0);

        if (probabilities != null) {
            // if using probabilistic rules
            double ruleProbability = probabilities[configuration];

            
            if (ruleProbability == 0) {
                // if the probability is 0, return false
                return false;
            } else if (ruleProbability == 1) {
                // if the probability is 1, return true
                return true;
            } else {
                // if the probability is not 0 or 1, return a random boolean value based on the probability
                return random.nextDouble() < ruleProbability;
            }
        } else {
            // if using deterministic rules
            return rules[configuration];
        }
    }
}
