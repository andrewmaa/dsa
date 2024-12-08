import java.io.IOException;

public class HuffmanTree {
  private HuffmanNode root;
  private HuffmanNode current;  // For decoding

  private static class HuffmanNode implements Comparable<HuffmanNode> {
    final public String symbols;
    final public Double frequency;
    final public HuffmanNode left, right;

    // Constructor for a leaf node
    public HuffmanNode(String symbol, double frequency) {
      this.symbols = symbol;
      this.frequency = frequency;
      this.left = null;
      this.right = null;
    }
    // Constructor for an internal node
    public HuffmanNode(HuffmanNode left, HuffmanNode right) {
      this.symbols = left.symbols + right.symbols;
      this.frequency = left.frequency + right.frequency;
      this.left = left;
      this.right = right;
    }

    // Compare the frequencies of two nodes
    public int compareTo(HuffmanNode hn) {
      return this.frequency.compareTo(hn.frequency);
    }

    // String representation of a node
    public String toString() {
      return "<" + symbols + ", " + frequency + ">";
    }
  }

  // Constructor for a Huffman tree
  public HuffmanTree(HuffmanNode root) {
    this.root = root;
  }
  
  // Print the legend of the Huffman tree
  public void printLegend() {
    printLegendHelper(this.root, "");
  }

  // Helper function to print the legend of the Huffman tree
  private void printLegendHelper(HuffmanNode node, String bits) {
    if (node == null) {
      return;
    }
    
    // Recursively print the legend of the left and right subtrees
    // Add a "0" to the bits string for the left subtree
    printLegendHelper(node.left, bits + "0");

    // If the node is a leaf node, print the symbol and its corresponding bits
    if (node.symbols.length() == 1) {
      System.out.println(convertSymbolToChar(node.symbols) + "\t" + bits);    
    }

    // Recursively print the legend of the right subtree
    printLegendHelper(node.right, bits + "1");
    
  }
  
  // Print the tree specification of the Huffman tree
  public void printTreeSpec() {
    printTreeSpecHelper(this.root, "");
    if (this.root.symbols.length() > 1) {
      System.out.print("|");
    }
  }

  // Helper function to print the tree specification of the Huffman tree
  private void printTreeSpecHelper(HuffmanNode node, String bits) {
    if (node == null) {
      return;
    } 

    // Recursively print the tree specification of the left and right subtrees
    printTreeSpecHelper(node.left, bits + "0");
    printTreeSpecHelper(node.right, bits + "1");

    // If the node is a leaf node, print the symbol
    if (node.symbols.length() == 1) {
      System.out.print(convertSymbolToChar(node.symbols));
    }
    else {
      if (bits.contains("0")) {
        System.out.print("|");
      }
    }
  }

  // Convert the legend to a heap of Huffman nodes
  public static BinaryHeap freqToHeap(String legend) {
    BinaryHeap<HuffmanNode> heap = new BinaryHeap<>();
    String[] tokens = legend.split(" ");
    for (int i = 0; i < tokens.length; i += 2) {
      String symbol = tokens[i];
      double frequency = Double.parseDouble(tokens[i + 1]);
      heap.insert(new HuffmanNode(symbol, frequency));
    }
    return heap;
  }

  // Create a Huffman tree from a heap of Huffman nodes
  public static HuffmanTree createFromHeap(BinaryHeap<HuffmanNode> b) {
    while (b.getSize() > 1) {
      HuffmanNode right = b.extractMin();
      HuffmanNode left = b.extractMin();
      HuffmanNode parent = new HuffmanNode(left, right);
      b.insert(parent);
    }
    return new HuffmanTree(b.extractMin());
  }

  public static HuffmanTree loadTree(String storedTree) {
    Stack<HuffmanNode> stack = new ArrayStack<>();
    
    for (int i = 0; i < storedTree.length(); i++) {
        char currentChar = storedTree.charAt(i);
        
        // Handle escape sequences
        if (currentChar == '\\') {
            char nextChar = storedTree.charAt(i + 1);
            
            if (nextChar == 'e') {
                // Keep \e as is
                stack.push(new HuffmanNode("eom", 0));
                i++;
            } else if (nextChar == '\\') {
                // Convert \\ to \
                stack.push(new HuffmanNode("\\", 0));
                i++;
            } else if (nextChar == '|') {
                // Convert \| to |
                stack.push(new HuffmanNode("|", 0));
                i++;
            }
        }
        // Handle unescaped pipe - combine nodes
        else if (currentChar == '|') {
            HuffmanNode first = stack.pop();
            HuffmanNode second = stack.pop();
            HuffmanNode parent = new HuffmanNode(second, first);
            stack.push(parent);
        }
        // Handle regular characters
        else {
            stack.push(new HuffmanNode(String.valueOf(currentChar), 0));
        }
    }

    // If there are multiple nodes remaining, combine them
    while (stack.size() > 1) {
        HuffmanNode first = stack.pop();
        HuffmanNode second = stack.pop();
        HuffmanNode parent = new HuffmanNode(second, first);
        stack.push(parent);
    }
    return new HuffmanTree(stack.pop());
  }

  public String[] legendToArray() {
    String[] codes = new String[129];  // 128 ASCII chars + 1 EOM
    // Initialize all entries to null
    for (int i = 0; i < codes.length; i++) {
      codes[i] = null;
    }
    legendToArrayHelper(this.root, "", codes);
    return codes;
  }

  private void legendToArrayHelper(HuffmanNode node, String bits, String[] codes) {
    if (node == null) return;
    
    // Traverse left (add 0)
    legendToArrayHelper(node.left, bits + "0", codes);
    
    // If leaf node, store the code
    if (node.symbols.length() == 1) {
      if (node.symbols.equals("eom")) {
        codes[128] = bits;  // Store EOM code at index 128
      } else if (node.symbols.equals("space")) {
        codes[32] = bits;   // Store space at ASCII 32
      } else {
        char c = node.symbols.charAt(0);
        codes[(int)c] = bits;  // Store character code at its ASCII value
      }
    }
    
    // Traverse right (add 1)
    legendToArrayHelper(node.right, bits + "1", codes);
  }

  public void initializeCurrent() {
    current = root;
  }

  public String advanceCurrent(char bit) {
    if (current == null) {
      initializeCurrent();
      return null;
    }

    // Advance left or right based on bit
    if (bit == '0') {
      current = current.left;
    } else if (bit == '1') {
      current = current.right;
    }

    // If we've reached a leaf node
    if (current != null && current.left == null && current.right == null) {
      String symbol = current.symbols;
      initializeCurrent();
      return symbol;
    }

    return null;
  }

  // Usage from the command line:
  // cat sample_legend.txt | java HuffmanTree 
  // on windows: type sample_legend.txt | java HuffmanTree
  public static void main(String [] args) throws IOException {
    String mode = (args.length == 0)? "spec": args[0];

    String frequencyStr = StdinToString.read();

    BinaryHeap<HuffmanNode> heap = freqToHeap(frequencyStr);

    HuffmanTree tree = createFromHeap(heap);

    if (mode.toLowerCase().equals("legend")) {
      tree.printLegend();
    } 
    
    else {
      tree.printTreeSpec();
    }
    
  }

  public static String convertSymbolToChar(String symbol) {
    if (symbol.equals("space")) return " ";
    if (symbol.equals("eom")) return "\\e";
    if (symbol.equals("|")) return "\\|";
    if (symbol.equals("\\")) return "\\\\";
    return symbol;
  }

}