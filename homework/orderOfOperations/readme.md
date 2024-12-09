# Order of Operations (Assignment 3a)
**November 13, 2024**  
*Data Structures (CSCI-UA 102)*

## How does it work?
This program interprets mathematical expressions and builds an expression tree to evaluate them. It uses stacks to manage operators and operands, allowing it to handle complex expressions with proper precedence and associativity rules.

## Runbook
This program can be compiled by executing the following command while in the `orderOfOperations` directory:

`javac *.java`

This program can be run by executing the following command while in the `orderOfOperations` directory:
`java ExpressionInterpreter [expression]`

, where `[expression]` is the mathematical expression to be evaluated.

> Examples:   
>`java ExpressionInterpreter "3 * x + 5 + 2"`  
>`java ExpressionInterpreter "1 + 1"`  
>`java ExpressionInterpreter "m * x + b"`

## Time Spent
This project took around 8 hours to complete.

## Notes
There was some difficulty initially with understanding how to properly implement the `solveAsMuchAsPossible` method. Since the tree is left heavy, the recursive calls would always go to the left child first, and so it would be possible that the right child's unbound variable would not be detected.

## Resources and Acknowledgements
Similar to the previous project, the code in this project was submitted through ChatGPT to identify any exceptions or bugs that would have caused problems, particularly looking at any minor syntax or formatting errors. Additionally, I consulted my notes from CSCI-UA 101 to review some basic syntax.