package homework.one;

import java.util.Random;

class ElementaryCellularAutomata {

    private boolean[] cells;
    private RuleSet ruleSet;
    private Random random;
    private int iterations;

    public ElementaryCellularAutomata(EcaParams params) {
        cells = new boolean[params.getSize()];
        ruleSet = new RuleSet(params.getRule());
        random = new Random(params.getSeed());
        iterations = params.getIterations();

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
            newCells[i] = ruleSet.getNext(cells[i], cells[(i + 1) % cells.length], cells[(i + 2) % cells.length], random);
        }
        cells = newCells;
    }

    public static void main(String[] args) {
        

    }
}