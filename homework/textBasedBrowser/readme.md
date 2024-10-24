# Text Based Browser (Assignment 2)
**October 24, 2024**  
*Data Structures (CSCI-UA 102)*

## How does it work?
This program simulates a text-based browser with a specified number of frames. Using stacks, the program is able to keep track of the pages that have been visited– the user can navigate through the pages in each frame using the `back` and `forward` commands. Additionally, the program is able to display a section of a page as a "frame" of the browser.

## Runbook
This program can be compiled by executing the following command while in the `textBasedBrowser/src` directory:

`javac *.java`

This program can be run by executing the following command while in the `textBasedBrowser/src` directory:
`java textBasedBrowser/Browser [number of frames]`

, where `[number of frames]` is the number of frames that the browser will have.

> 🚧  **Warning:**  
> Ignore the 'unchecked or unsafe operations' warning when compiling this program.

> Examples:   
>`java Browser 10`  
> `java Browser 5`  
> `java Browser 100`

## Time Spent
This project took around 3.5 hours to complete.

## Notes
There was some difficulty initially with understanding how to properly implement the 
different ArrayStacks to keep track of the pages and frames.

## Resources and Acknowledgements
Similar to the previous project, the code in this project was submitted through ChatGPT to identify any exceptions
or bugs that would have caused problems, particularly looking at any minor syntax
or formatting errors. Additionally, I consulted my notes from CSCI-UA 101 to 
review some basic syntax.