Elementary Cellular Automata
October 7, 2024
Data Structures (CSCI-UA 102)

RUNBOOK: 
This program can be compiled by executing the following command while in the /one directory:
javac *.java

This program can be run by executing the following command while up one level from the /one directory:
java one/ElementaryCellularAutomata [parameters]
the parameters are as follows:
- rule: the rule to use for the elementary cellular automata
- off-color: the color to use for a dead cell
- on-color: the color to use for an alive cell
- random-seed: the seed to use for the random number generator
- size: the number of cells to use for the eca
- init: the initialization density of the eca
- iter: the number of iterations to run for the eca

TIME SPENT: 
This project took around 4 hours to complete.

NOTES:
There was some difficulty initially with setting up the 
different components of the program, such as the RuleSet class, where 
I was confused on how to properly apply the rules to the cells.

RESOURCES AND ACKNOWLEDGEMENTS: 
Similar to the previous project, the code in this project was submitted through ChatGPT to identify any exceptions
or bugs that would have caused problems, particularly looking at any minor syntax
or formatting errors. Additionally, I consulted my notes from CSCI-UA 101 to 
review some basic Object-Oriented Programming concepts.