import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HuffmanConverter {
  // Usage from the command line:
  // cat just_to_say.txt | java HuffmanConverter encode spec.txt
  public static void main(String [] args) throws IOException {
    String mode = args[0].toLowerCase();
    String treeFile = args[1];

    String treeStr = StdinToString.readfile(treeFile);
    // TODO - copy HuffmanTree.java from part A, and implement loadTree()
    HuffmanTree tree = HuffmanTree.loadTree(treeStr);

    String input = StdinToString.read();

    if (mode.equals("decode")) {
      tree.initializeCurrent();
      for (int i = 0; i < input.length(); i++) {
        char bit = input.charAt(i);
        String symbol = tree.advanceCurrent(bit);
        if (symbol != null) {
          if (symbol.equals("eom")) {
            break;  // End of message found
          } else if (symbol.equals("space")) {
            System.out.print(" ");
          } else {
            System.out.print(symbol);
          }
        }
      }
    } else if (mode.equals("analyze")) {
      String[] legend = tree.legendToArray();
      int count = 0;
      // Encode each character in the input
      for (int i = 0; i < input.length(); i++) {
        char c = input.charAt(i);
        if (c == ' ') {
          // Handle space character
          count += legend[32].length();
        } else {
          // Handle regular character
          count += legend[(int)c].length();
        }
      }
      // Print EOM code at the end
      count += legend[128].length();
      System.out.println(count);

    } else if (mode.equals("encode")) {
      String[] legend = tree.legendToArray();
      // Encode each character in the input
      for (int i = 0; i < input.length(); i++) {
        char c = input.charAt(i);
        if (c == ' ') {
          // Handle space character
          System.out.print(legend[32]);
        } else {
          // Handle regular character
          System.out.print(legend[(int)c]);
        }
      }
      // Print EOM code at the end
      System.out.print(legend[128]);
    } else {
      System.out.println("Unknown Mode: " + mode);
    }
  }
}
