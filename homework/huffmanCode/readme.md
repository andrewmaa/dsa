# Huffman Coding (Assignment 4a)
**November 26, 2024**  
*Data Structures (CSCI-UA 102)*

## How does it work?
This program implements a Huffman coding system that builds a Huffman tree from character frequencies. The tree is used to generate binary codes for characters, with more frequent characters getting shorter codes. The program can output either the tree specification or a legend mapping characters to their binary codes.

## Runbook
This program can be compiled by executing the following command while in the `huffmanCode` directory:

`javac *.java`

This program can be run by executing the following command while in the `huffmanCode` directory:
`java HuffmanTree [mode]`

, where `[mode]` is optional and can be "legend" to print the character-to-code mapping.

> Examples:   
>`cat sample_legend.txt | java HuffmanTree`  
>`cat sample_legend.txt | java HuffmanTree legend`

## Time Spent
This project took around 8 hours to complete.

## Notes
There was some difficulty initially with understanding how to properly implement the tree traversal methods, such as printLegendHelper and printTreeSpecHelper. Another challenge was ensuring that the tree was built correctly from the heap of nodes.

## Resources and Acknowledgements
Similar to the previous project, the code in this project was submitted through ChatGPT to identify any exceptions or bugs that would have caused problems, particularly looking at any minor syntax or formatting errors. Additionally, I consulted my notes from CSCI-UA 101 to review some basic syntax.