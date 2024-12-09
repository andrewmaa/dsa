Building a Huffman Converter
December 9, 2024
Data Structures (CSCI-UA 102)

RUNBOOK:
This program implements a Huffman coding system that can compress text by building a Huffman tree and generating binary codes for characters based on their frequency. The program can be run in two modes - one that outputs the tree specification and another that outputs the legend mapping characters to their binary codes.

To compile the program, execute the following command in the huffmanConverter directory:
javac *.java

There are three modes of operation:
1. analyze - This mode will analyze the input file and print the tree specification
$ cat sample_legend.txt | java HuffmanConverter analyze
2. encode - This mode will encode the input file and print the encoded message
$ cat sample_legend.txt | java HuffmanConverter encode
3. decode - This mode will decode the input file and print the decoded message
$ cat sample_legend.txt | java HuffmanConverter decode

The output can be redirected to a file by appending "> output.txt" to the end of the command.
Special characters:
- "space" represents a space character
- "\e" represents the end-of-message marker
- Escape sequences are used for special characters like backslash and pipe

TIME SPENT:
This project took approximately 5 hours to complete.

NOTES:
The main challenges in this project were:
1. Printing the legend correctly
2. Handling special characters and escape sequences properly (particularly the "\e" character)
3. Proper implementation of the analyze mode

RESOURCES AND ACKNOWLEDGEMENTS:
Similar to the previous project, the code in this project was submitted through ChatGPT to identify any exceptions
or bugs that would have caused problems, particularly looking at any minor syntax
or formatting errors. Additionally, I consulted my notes from CSCI-UA 101 to 
review some basic syntax.
