# Huffman Converter (Assignment 4b)
**December 9, 2024**  
*Data Structures (CSCI-UA 102)*

## How does it work?
This program implements a Huffman coding system that can compress text by building a Huffman tree and generating binary codes for characters based on their frequency. The program can be run in different modes to analyze input, encode messages, or decode compressed data.

## Runbook
This program can be compiled by executing the following command while in the `huffmanConverter` directory:

`javac *.java`

This program can be run by executing the following command while in the `huffmanConverter` directory:
`java HuffmanConverter [mode]`

, where `[mode]` can be one of:
- `analyze` - Analyzes input and prints the tree specification
- `encode` - Encodes the input and prints the encoded message  
- `decode` - Decodes the input and prints the decoded message

> Examples:   
>`cat sample_legend.txt | java HuffmanConverter analyze`  
>`cat sample_legend.txt | java HuffmanConverter encode`  
>`cat sample_legend.txt | java HuffmanConverter decode`

The output can be redirected to a file by appending `> output.txt` to any command.

## Time Spent
This project took approximately 5 hours to complete.

## Notes
The main challenges in this project were:
1. Printing the legend correctly
2. Handling special characters and escape sequences properly (particularly the "\e" character)
3. Proper implementation of the analyze mode

## Resources and Acknowledgements
Similar to the previous project, the code in this project was submitted through ChatGPT to identify any exceptions or bugs that would have caused problems, particularly looking at any minor syntax or formatting errors. Additionally, I consulted my notes from CSCI-UA 101 to review some basic syntax.