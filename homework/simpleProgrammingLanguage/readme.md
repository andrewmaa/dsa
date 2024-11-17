# Simple Programming Language (Assignment 3b)
**November 18, 2024**  
*Data Structures (CSCI-UA 102)*

## How does it work?
This program creates a REPL (Read-Eval-Print Loop) for a simple programming language. The language supports variables, arithmetic operations, and conditional statements. It uses a stack to evaluate expressions and a hash map to store variable values.

## Runbook
This program can be compiled by executing the following command while in the `simpleProgrammingLanguage/src` directory:

`javac *.java`

This program can be run by executing the following command while in the `simpleProgrammingLanguage/src` directory:
`java ExpressionRepl`

> Examples:   
>`> x = 10`  
>`> y = x + 5`  
>`> y`  
>`15.0`

## Time Spent
This project took around 10 hours to complete.

## Notes
There was some difficulty initially with understanding how to properly implement the `get()` and `put()` methods in the `MapImplemented` class. Another challenge was ensuring that the REPL correctly parsed and evaluated expressions in the `solveIfPossible` method, especially when dealing with variable assignments and updates.

## Resources and Acknowledgements
Similar to the previous project, the code in this project was submitted through ChatGPT to identify any exceptions or bugs that would have caused problems, particularly looking at any minor syntax or formatting errors. Additionally, I consulted my notes from CSCI-UA 101 to review some basic syntax.
