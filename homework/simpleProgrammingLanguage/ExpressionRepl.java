package simpleProgrammingLanguage;
import java.io.*;

public class ExpressionRepl {
    public static void main(String[] args) throws IOException {
        ExpressionRepl repl = new ExpressionRepl();

        while (true) {
            // Read
            System.out.print("> ");
            String input = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
            
            // Eval
            String response = repl.evaluateInput(input);

            // Print
            System.out.println(response);
        }
    }

    private MapInterface<String, Double> variableBindings = new MapImplemented<String, Double>(); 

    public String evaluateInput(String input) {
        Tokenizer.TokenList tokenList = Tokenizer.tokenize(input);
        ExpressionInterpreter interpreter = new ExpressionInterpreter();
        Node root = interpreter.buildExpressionTree(tokenList.list);
        String variableName = tokenList.variableName;
        Double solved = solveIfPossible(root);

        if (solved == null) {
            return "Not solved.";
        }

        if (variableName == null) {
            return String.valueOf(solved);
        }
        
        variableBindings.put(variableName, solved);
        return variableName + " = " + solved;
    }

    private Double solveIfPossible(Node root) {
        // Implement This!

        return null;
    }

    public ExpressionRepl() { }
}