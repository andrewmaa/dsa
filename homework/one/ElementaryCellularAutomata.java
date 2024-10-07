package homework.one;
// import one.EcaParams;
// import one.AnsiColor;
// import one.RuleSet;

import java.util.Random;

class ElementaryCellularAutomata {

    private boolean[] cells;
    private RuleSet ruleSet;
    private Random random;
    private int iterations;
    private AnsiColor offColor;
    private AnsiColor onColor;

    public ElementaryCellularAutomata(EcaParams params) {
        cells = new boolean[params.getSize()];
        ruleSet = new RuleSet(params.getRule());
        random = new Random(params.getSeed());
        iterations = params.getIterations();
        offColor = params.getOffColor();
        onColor = params.getOnColor();
        
        double init = params.getInit();
        for (int i = 0; i < cells.length; i++) {
            cells[i] = random.nextDouble() < init;
        }
    }

    public void iterate() {
        for (int i = 0; i < iterations; i++) {
            printState();
            updateState();
        }
    }

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