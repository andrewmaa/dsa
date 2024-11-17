package simpleProgrammingLanguage;
public class ExpressionInterpreter {

    private Stack<Node> expressionStack = new ArrayStack<>();
    private Stack<String> operatorStack = new ArrayStack<>();

    public static void main(String[] args) {
        SinglyLinkedList<String> tokens = Tokenizer.tokenize(args[0]).list;
        ExpressionInterpreter interpreter = new ExpressionInterpreter();

        // 1) Build the expression Tree From the Tokens (requires a Node class)
        Node root = interpreter.buildExpressionTree(tokens);

        System.out.print("Postfix: ");
        // 2) Print the post order travesal (postfix notation)
        printPostOrder(root);
        System.out.print("\n");

        System.out.print("Infix: ");
        // 3) Print the inorder travesal (infix notation) with additional parentheses
        printInOrder(root);
        System.out.print("\n");

        // 4) Solve the expression as much as possible, printing out the unbound
        // variables.
        root = solveAsMuchAsPossible(root);

        System.out.print("Solved: ");
        // 5) Print the solved expression.
        printInOrder(root);
        System.out.print("\n");
    }

    public void popOperatorStack() {
        String operator = operatorStack.pop();
        Node right = expressionStack.pop();
        Node left = expressionStack.pop();
        Node newNode = new Node(operator, left, right);
        expressionStack.push(newNode);
    }

    public Node buildExpressionTree(SinglyLinkedList<String> tokens) {
        // while the list of tokens is not empty
        while (!tokens.isEmpty()) {
            // get the next token (and remove it from the list)
            String token = tokens.removeFirst();
            // if the token is an operand
            if (isOperand(token)) {
                // create a new node with the operand
                Node operandNode = new Node(token);
                // push the node onto the expression stack
                expressionStack.push(operandNode);
            }
            // if the token is an open parenthesis
            else if (token.equals("(")) {
                // push the token onto the operator stack
                operatorStack.push(token);
            }
            // if the token is an operator
            else if (isOperator(token)) {
                // while the operator stack is not empty and the top of the operator stack
                // has greater or equal precedence to the current operator,
                // pop the operator stack
                while (!operatorStack.isEmpty() &&
                       precedence(operatorStack.top()) >= precedence(token)) {
                    popOperatorStack();
                }
                operatorStack.push(token);
            } else if (token.equals(")")) {
                // while the operator stack is not empty and the top of the operator stack
                // is not an open parenthesis, pop the operator stack
                while (!operatorStack.isEmpty() && !operatorStack.top().equals("(")) {
                    popOperatorStack();
                }
                // if the operator stack is not empty, pop the open parenthesis
                if (!operatorStack.isEmpty()) {
                    operatorStack.pop(); // Discard the open parenthesis
                }
            }

        }

        // Finalize the Expression Tree
        while (!operatorStack.isEmpty() && !operatorStack.top().equals("(")) {
            popOperatorStack();
        }

        // Validation and Final Steps
        if (!operatorStack.isEmpty() || expressionStack.size() != 1) {
            throw new IllegalStateException("Mismatched parentheses or malformed expression");
        }

        return expressionStack.pop();
    }

    private static boolean isOperand(String token) {
        // Implement logic to check if the token is an operand
        return !isOperator(token) && !token.equals("(") && !token.equals(")");
    }

    private static boolean isOperator(String token) {
        // Implement logic to check if the token is an operator
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^");
    }

    private int precedence(String operator) {
        // Implement logic to return the precedence of the operator
        switch (operator) {
            case "+":
                return 1;
            case "-":
                return 1;
            case "*":
                return 2;
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return 0;
        }
    }
    // print postorder traversal of expression tree
    public static void printPostOrder(Node root) {
        if (root != null) {
            printPostOrder(root.leftChild);
            printPostOrder(root.rightChild);
            System.out.print(root.element + " ");

        }
    }

    // print inorder traversal of expression tree with additional parentheses
    public static void printInOrder(Node root) {
        if (root != null) {
            if (isOperator(root.element)) {
                System.out.print("(");
            }
            printInOrder(root.leftChild);
            if (isOperator(root.element)) {
                System.out.print(" ");
            }
            System.out.print(root.element);
            if (isOperator(root.element)) {
                System.out.print(" ");
            }
            printInOrder(root.rightChild);
            if (isOperator(root.element)) {
                System.out.print(")");
            }
        }
    }

    // solve the expression as much as possible
    public static Node solveAsMuchAsPossible(Node root) {
        if (root != null) {
            root.leftChild = solveAsMuchAsPossible(root.leftChild);
            root.rightChild = solveAsMuchAsPossible(root.rightChild);

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
                        System.out.println("Unbound variable (left): " + left.element);
                    }
                    try {
                        rightValue = Double.parseDouble(right.element);
                    }
                    catch (NumberFormatException e ) {
                        System.out.println("Unbound variable (right): " + right.element);
                    }

                    // if either the left or right child is not a number, return the current node
                    if (leftValue == null || rightValue == null) {
                        return root;
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
                                return root;
                            }
                            break;
                        case "^":
                            result = Math.pow(leftValue, rightValue);
                            break;
                    }
                    root = new Node(String.valueOf(result));
                }
                // checking if one of the children is an operand and the other is not (a variable)
                else if (isOperand(left.element) && !isOperand(right.element)) {
                    try {
                        leftValue = Double.parseDouble(left.element);
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Unbound variable (left): " + left.element);
                    }                }
                else if (!isOperand(left.element) && isOperand(right.element)) {
                    try {
                        rightValue = Double.parseDouble(right.element);
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Unbound variable (right): " + right.element);
                    }
                }
            }
        }
        // return the current node
        return root;
    }
}
