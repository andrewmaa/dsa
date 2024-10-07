package homework.one;
import java.util.Random;

public class RuleSet {
    private int rule;
    private boolean[] rules;

    // rules: [True, False, False, False ....]

    public RuleSet(int rule) {
        this.rule = rule; // 21
        this.rules = new boolean[8];
        for (int i=0; i<8; i++) {
            int lastBit = rule & 1; // bit = 0 or 1
            this.rules[i] = lastBit == 1;
            rule = rule >> 1;
        }
    }

    public RuleSet(boolean[] rules) {
        this.rules = rules;
    }

    public boolean getNext(boolean left, boolean center, boolean right, Random random) {
        int configuration = (left ? 4 : 0) + (center ? 2 : 0) + (right ? 1 : 0);
        // if (rules == null) {
        //     int bit = (rule >> configuration) & 1;
        //     return bit == 1;
        // }
        
        return rules[configuration];
        
        // else {
        //     double probability = rules[configuration];
        //     if (probability >= 1.0) {
        //         return true;
        //     }
        //     else if (probability <= 0.0) {
        //         return false;
        //     }
        //     else {
        //         return random.nextDouble() < probability;
        //     }
        // }
    }
}
