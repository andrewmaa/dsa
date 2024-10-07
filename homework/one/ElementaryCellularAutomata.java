package homework.one;
// import one.EcaParams;
// import one.AnsiColor;
// import one.RuleSet;

import java.util.Random;

class ElementaryCellularAutomata {

    // create boolean array to store the state of each cell
    private boolean[] cells;

    // create private variables for cells, the set of rules, 
    // the random number generator, the number of iterations, the color for a dead cell, and the color for an alive cell
    private RuleSet ruleSet;
    private Random random;
    private int iterations;
    private AnsiColor offColor;
    private AnsiColor onColor;

    /**
     * Constructor for ElementaryCellularAutomata.
     * 
     * @param params The parameters for the ECA simulation
     */
    public ElementaryCellularAutomata(EcaParams params) {
        cells = new boolean[params.getSize()];
        ruleSet = new RuleSet(params.getRule());
        random = new Random(params.getSeed());
        iterations = params.getIterations();
        offColor = params.getOffColor();
        onColor = params.getOnColor();
        
        double init = params.getInit();

        // initialize the cells array with random values based on the '-init' parameter
        for (int i = 0; i < cells.length; i++) {
            cells[i] = random.nextDouble() < init;
        }
    }

    /**
     * Iterates through the cells, printing and updating the state of each cell.
     */
    public void iterate() {
        for (int i = 0; i < iterations; i++) {
            printState();
            updateState();
        }
    }

    /**
     * Prints the current state of the cells.
     */
    private void printState() {
        for (boolean cell : cells) {
            if (cell) {
                onColor.printBlock();
            }
            else {
                offColor.printBlock();
            }
        }
        System.out.println();
    }

    /**
     * Updates the state of the cells based on the ruleset.
     * 
     */
    private void updateState() {
        boolean[] newCells = new boolean[cells.length];
        for (int i = 0; i < cells.length; i++) {
            // using modulo to find indices (circular array)
            newCells[i] = ruleSet.getNext(cells[(i - 1 + cells.length) % cells.length], cells[i], cells[(i + 1) % cells.length], random);
        }
        cells = newCells;
    }


    public static void main(String[] args) {
        EcaParams params = new EcaParams(args);
        ElementaryCellularAutomata ECA = new ElementaryCellularAutomata(params);
        ECA.iterate();

    }
}