package homework.one;
import java.util.Arrays;



public class EcaParams {
    private int rule;
    private double[] probabilities;
    private AnsiColor offColor = new AnsiColor("black");
    private AnsiColor onColor = new AnsiColor("white");
    private long seed = 0;
    private int size = 100;
    private double init = 0.0;
    private int iterations = 1000;

    public EcaParams(String[] args) {
        parseArgs(args);
    }

    private void parseArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String flag = args[i];
            switch (flag) {
                case "-rules":
                    // if (i+1 < args.length && isInteger(args[i+1])) {
                    //     rule = 
                    // }
                    if (i + 1 < args.length && isInteger(args[i + 1])) {
                        rule = Integer.parseInt(args[i + 1]);
                        probabilities = null;
                        i++;
                    } else if (i + 8 < args.length) {
                        probabilities = new double[8];
                        for (int j = 0; j < 8; j++) {
                            probabilities[j] = Double.parseDouble(args[i + 1 + j]);
                        }
                        i += 8;
                    } else {
                        throw new IllegalArgumentException("Invalid number of arguments for -rules");
                    }
                    break;
                case "-off-color":
                    if (i + 1 < args.length) {
                        offColor = new AnsiColor(args[i + 1]);
                        i++;
                    } else {
                        throw new IllegalArgumentException("Off color must be provided");
                    }
                    break;
                case "-on-color":
                    if (i + 1 < args.length) {
                        onColor = new AnsiColor(args[i + 1]);
                        i++;
                    } else {
                        throw new IllegalArgumentException("On color must be provided");
                    }
                    break;
                case "-random-seed":
                    if (i + 1 < args.length) {
                        seed = Long.parseLong(args[i + 1]);
                        i++;
                    } else {
                        throw new IllegalArgumentException("Random seed must be provided");
                    }
                    break;
                case "-size":
                    if (i + 1 < args.length) {
                        int parsedSize = Integer.parseInt(args[i + 1]);
                        if (parsedSize <= 0) {
                            throw new IllegalArgumentException("Size must be positive");
                        }
                        size = parsedSize;
                        i++;
                    } else {
                        throw new IllegalArgumentException("Size must be provided");
                    }
                    break;
                case "-init":
                    if (i + 1 < args.length) {
                        double parsedInit = Double.parseDouble(args[i + 1]);
                        if (parsedInit < 0 || parsedInit > 1) {
                            throw new IllegalArgumentException("Initial value must be between 0 and 1");
                        }
                        init = parsedInit;
                        i++;
                    } else {
                        throw new IllegalArgumentException("Initial value must be provided");
                    }
                    break;
                case "-iter":
                    if (i + 1 < args.length) {
                        int parsedIter = Integer.parseInt(args[i + 1]);
                        if (parsedIter <= 0) {
                            throw new IllegalArgumentException("Iterations must be positive");
                        }
                        iterations = parsedIter;
                        i++;
                    } else {
                        throw new IllegalArgumentException("Iterations must be provided");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown flag: " + flag);
            }
        }
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Getter methods
    public int getRule() { return rule; }
    public double[] getProbabilities() { return probabilities; }
    public AnsiColor getOffColor() { return offColor; }
    public AnsiColor getOnColor() { return onColor; }
    public long getSeed() { return seed; }
    public int getSize() { return size; }
    public double getInit() { return init; }
    public int getIterations() { return iterations; }

    @Override
    public String toString() {
        return "EcaParams{" +
                "rule=" + rule +
                ", probabilities=" + Arrays.toString(probabilities) +
                ", offColor='" + offColor.toString() + '\'' +
                ", onColor='" + onColor.toString() + '\'' +
                ", seed=" + seed +
                ", size=" + size +
                ", init=" + init +
                ", iterations=" + iterations +
                '}';
    }
}
