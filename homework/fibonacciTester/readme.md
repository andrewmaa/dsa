# Fibonacci Tester (Assignment 0)
**September 20, 2024**  
*Data Structures (CSCI-UA 102)*

## How does it work?
This program tests the efficiency of implementing different algorithms for computing Fibonacci numbers. It allows the user to specify the maximum input value and the maximum time the program should wait for an algorithm to complete. The program uses recursion, dynamic programming, and a closed form to optimize the computation of Fibonacci numbers and handles potential overflow issues for large inputs.

## Runbook
This program can be compiled by executing the following command while in the `fibonacciTester` directory:

`javac FibonacciTester.java`

This program can be run by executing the following command while in the `homework` directory:
`java fibonacciTester/FibonacciTester [maxInput] [maxTime]`

, where `[maxInput]` is the maximum Fibonacci value to test and `[maxTime]` is the maximum time (in milliseconds) the program will wait for an algorithm to finish before canceling a higher input.

> Examples:   
>`java FibonacciTester 50 1000`  
> `java FibonacciTester 100 5000`  
> `java FibonacciTester 200 10000`

## Time Spent
This project took around 2 hours to complete.

## Notes
There was some difficulty initially with setting up the dynamic programming approach. Additionally, one "bug" that appeared throughout the completion of this project was the overflow issue that occurred when inputting a high maxInput, as there is a maximum value for the long data type in Java.

## Resources and Acknowledgements
The code in this project was submitted through ChatGPT to identify any exceptions or bugs that would have caused problems, particularly looking at any minor syntax or formatting errors. Additionally, I consulted my notes from CSCI-UA 101 to review some basic Java concepts.