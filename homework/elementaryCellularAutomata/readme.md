# Simulating Elementary Cellular Automata (Assignment 1)
**October 7, 2024**  
*Data Structures (CSCI-UA 102)*

## How does it work?
This program simulates an elementary cellular automaton based on a specified rule. The user can define the rule, the colors for alive and dead cells, the random seed, the size of the automaton, the initialization density, and the number of iterations to run.

## Runbook
This program can be compiled by executing the following command while in the `homework` directory:

`javac *.java`

This program can be run by executing the following command while in the `homework` directory:
`java elementaryCellularAutomata/ElementaryCellularAutomata [parameters]`

, where `[parameters]` include:
- `rule`: the rule to use for the elementary cellular automaton
- `off-color`: the color to use for a dead cell
- `on-color`: the color to use for an alive cell
- `random-seed`: the seed to use for the random number generator
- `size`: the number of cells to use for the automaton
- `init`: the initialization density of the automaton
- `iter`: the number of iterations to run for the automaton

> 🚧  **Warning:**  
> Ensure that all parameters are correctly specified to avoid runtime errors.

> Examples:   
>`java elementaryCellularAutomata/ElementaryCellularAutomata 30 black white 42 100 0.5 50`  
> `java elementaryCellularAutomata/ElementaryCellularAutomata 110 red blue 123 200 0.3 100`

## Time Spent
This project took around 4 hours to complete.

## Notes
There was some difficulty initially with setting up the different components of the program, such as the RuleSet class, where I was confused on how to properly apply the rules to the cells.

## Resources and Acknowledgements
Similar to the previous project, the code in this project was submitted through ChatGPT to identify any exceptions or bugs that would have caused problems, particularly looking at any minor syntax or formatting errors. Additionally, I consulted my notes from CSCI-UA 101 to review some basic Object-Oriented Programming concepts.