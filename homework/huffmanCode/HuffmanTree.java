import java.io.IOException;

public class HuffmanTree {
  private HuffmanNode root;

  private static class HuffmanNode implements Comparable<HuffmanNode> {
    final public String symbols;
    final public Double frequency;
    final public HuffmanNode left, right;

    public HuffmanNode(String symbol, double frequency) {
      this.symbols = symbol;
      this.frequency = frequency;
      this.left = null;
      this.right = null;
    }
    
    public HuffmanNode(HuffmanNode left, HuffmanNode right) {
      this.symbols = left.symbols + right.symbols;
      this.frequency = left.frequency + right.frequency;
      this.left = left;
      this.right = right;
    }
    public int compareTo(HuffmanNode hn) {
      return this.frequency.compareTo(hn.frequency);
    }

    public String toString() {
      return "<" + symbols + ", " + frequency + ">";
    }
  }

  public HuffmanTree(HuffmanNode root) {
    this.root = root;
  }
  
  public void printLegend() {
    printLegendHelper(this.root, "");
  }

  private void printLegendHelper(HuffmanNode node, String bits) {
    if (node == null) {
      return;
    }
    
    
    printLegendHelper(node.right, bits + "0");
    if (node.symbols.length() == 1) {
      System.out.println(convertSymbolToChar(node.symbols) + "\t" + bits);    
    }
    printLegendHelper(node.left, bits + "1");
    
  }
  
  
  
  public void printTreeSpec() {
    printTreeSpecHelper(this.root, "");
  }

  private void printTreeSpecHelper(HuffmanNode node, String bits) {
    if (node == null) {
      return;
    }
    if (node.left == null && node.right == null) {
      System.out.print(convertSymbolToChar(node.symbols));
    }
    else {
      // Check if this node is on the rightmost path by seeing if it has any right children
      HuffmanNode curr = node;
      boolean onRightmostPath = true;
      while (curr.right != null) {
        curr = curr.right;
        if (curr.left != null) {
          onRightmostPath = false;
          break;
        }
      }
      if (!onRightmostPath) {
        System.out.print("|");
      }
    }
  }

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

  public static HuffmanTree createFromHeap(BinaryHeap<HuffmanNode> b) {
    while (b.getSize() > 1) {
      HuffmanNode left = b.extractMin();
      HuffmanNode right = b.extractMin();
      HuffmanNode parent = new HuffmanNode(left, right);
      b.insert(parent);
    }
    return new HuffmanTree(b.extractMin());
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