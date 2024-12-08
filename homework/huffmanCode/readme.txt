Building a Huffman Tree
November 26, 2024
Data Structures (CSCI-UA 102)

RUNBOOK:
This program can be compiled by executing the following command while in the huffmanCode directory:
javac *.java

This program can be run by executing the following command while in the huffmanCode directory:
java HuffmanTree

Example:
$ cat sample_legend.txt | java HuffmanTree
a|b|c|d|e|

The program can also be run with the "legend" argument to print the legend format:
$ cat sample_legend.txt | java HuffmanTree legend
a    000
b    001  
c    01
d    10
e    11

TIME SPENT:
This project took around 8 hours to complete.

NOTES:
There was some difficulty initially with understanding how to properly implement the tree traversal methods, such as printLegendHelper and printTreeSpecHelper. Another challenge was ensuring that the tree was built correctly from the heap of nodes.

RESOURCES AND ACKNOWLEDGEMENTS:
Similar to the previous project, the code in this project was submitted through ChatGPT to identify any exceptions
or bugs that would have caused problems, particularly looking at any minor syntax
or formatting errors. Additionally, I consulted my notes from CSCI-UA 101 to 
review some basic syntax.