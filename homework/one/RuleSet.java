import java.util.Random;

public class RuleSet {
    private int rule;
    private double[] rules;

    public RuleSet(int rule) {
        this.rule = rule;
        this.rules = new double[8];
    }

    public RuleSet(double[] rules) {
        this.rules = rules;
    }

    public boolean getNext(boolean left, boolean center, boolean right, Random random) {
        int configuration = (left ? 4 : 0) + (center ? 2 : 0) + (right ? 1 : 0);
        if (rules == null) {
            int bit = (rule >> configuration) & 1;
            return bit == 1;
        }
        else {
            double probability = rules[configuration];
            if (probability >= 1.0) {
                return true;
            }
            else if (probability <= 0.0) {
                return false;
            }
            else {
                return random.nextDouble() < probability;
            }
        }
    }
}
