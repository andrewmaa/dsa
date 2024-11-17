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
    // > a = 5 * 10  -> tokenList = { variableName: 'a', list: (...) }
    // > 5 * 10      -> tokenList = { variableName: null, list: (5, *, 10)}
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
    private static boolean isOperand(String token) {
        // Implement logic to check if the token is an operand
        return !isOperator(token) && !token.equals("(") && !token.equals(")");
    }

    private static boolean isOperator(String token) {
        // Implement logic to check if the token is an operator
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^");
    }
    /**
     *    ( / )
     * ( 5 )  null
     * 
     * x = 5
     */
    private Node _solveIfPossible(Node root) {
        if (root == null) {
            return null;
        }
        root.leftChild = _solveIfPossible(root.leftChild);
        root.rightChild = _solveIfPossible(root.rightChild);

        // if the current node is an operator
        if (isOperator(root.element)) {
            // get the left and right children
            Node left = root.leftChild;
            Node right = root.rightChild;
            Double leftValue = null;
            Double rightValue = null;
            double result = 0;
            // if the left and right children are operands
            if (left != null && right != null && isOperand(left.element) && isOperand(right.element)) {
                // try to parse the left and right children as doubles
                // (verify that they are numbers)
                
                try {
                    leftValue = Double.parseDouble(left.element);
                }
                // if the left child is not a number, print an unbound variable message
                // (meaning it's a variable that we don't know the value of)
                catch (NumberFormatException e) {
                    leftValue = variableBindings.get(left.element);
                    if (leftValue == null) {
                        System.out.println("Unbound variable: " + left.element);
                    }
                }
                try {
                    rightValue = Double.parseDouble(right.element);
                }
                catch (NumberFormatException e ) {
                    rightValue = variableBindings.get(right.element);
                    if (rightValue == null) {
                        System.out.println("Unbound variable: " + right.element);
                        
                    }
                }

                // if either the left or right child is not a number, return the current node
                if (leftValue == null || rightValue == null) {
                    return null;
                }

                // perform the operation
                switch (root.element) {
                    case "+":
                        result = leftValue + rightValue;
                        break;
                    case "-":
                        result = leftValue - rightValue;
                        break;
                    case "*":
                        result = leftValue * rightValue;
                        break;
                    case "/":
                        if (rightValue != 0) {
                            result = leftValue / rightValue;
                        } else {
                            System.out.println("Division by zero");
                            return null;
                        }
                        break;
                    case "^":
                        result = Math.pow(leftValue, rightValue);
                        break;
                }
                return new Node(String.valueOf(result));
            }
            else {
                return null;
            }
            
        }
        else {
            try {
                Double.parseDouble(root.element);
                return root;
            }
            catch (NumberFormatException e) {
                Double value = variableBindings.get(root.element);
                if (value == null) {
                    System.out.println("Unbound variable (root): " + root.element);
                    return null;
                }
                return new Node(String.valueOf(value));
            }
        }
    }

    
    private Double solveIfPossible(Node root) {
        // Implement This!
        Node solved = _solveIfPossible(root);
        if (solved == null) {
            return null;
        }
        return Double.parseDouble(solved.element);
    }

    public ExpressionRepl() {
        
    }
}