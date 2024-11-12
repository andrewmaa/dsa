public class ExpressionInterpreter {

    private Stack<Node> expressionStack = new ArrayStack<>();
    private Stack<String> operatorStack = new ArrayStack<>();

    public static void main(String[] args) {
        SinglyLinkedList<String> tokens = Tokenizer.tokenize(args[0]);
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

        // 4) Solve the expression as much as possible, printing out the unbound variables.
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
        SinglyLinkedList.Node<String> current = tokens.head; // Use the correct Node type
        while (current != null) {
            String token = current.getElement();
            if (isOperand(token)) {
                Node operandNode = new Node(token);
                expressionStack.push(operandNode);
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (isOperator(token)) {
                while (!operatorStack.isEmpty() &&
                       precedence(operatorStack.top()) >= precedence(token)) {
                    popOperatorStack();
                }
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.top().equals("(")) {
                    popOperatorStack();
                }
                if (!operatorStack.isEmpty()) {
                    operatorStack.pop(); // Discard the open parenthesis
                }
            }
            current = current.getNext(); // Move to the next node
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
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return 0;
        }
    }

    public static void printPostOrder(Node root) {
        if (root != null) {
            printPostOrder(root.leftChild);
            printPostOrder(root.rightChild);
            System.out.print(root.element + " ");
        }
    }
    
    public static void printInOrder(Node root) {
        if (root != null) {
            if (isOperator(root.element)) {
                System.out.print("( ");
            }
            printInOrder(root.leftChild);
            System.out.print(root.element + " ");
            printInOrder(root.rightChild);
            if (isOperator(root.element)) {
                System.out.print(") ");
            }
        }
    }



    public static Node solveAsMuchAsPossible(Node root) {
        if (root != null) {
            root.leftChild = solveAsMuchAsPossible(root.leftChild);
            root.rightChild = solveAsMuchAsPossible(root.rightChild);

            if (isOperator(root.element)) {
                Node left = root.leftChild;
                Node right = root.rightChild;

                if (left != null && right != null && isOperand(left.element) && isOperand(right.element)) {
                    try {
                        double leftValue = Double.parseDouble(left.element);
                        double rightValue = Double.parseDouble(right.element);
                        double result = 0;

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
                    } catch (NumberFormatException e) {
                        System.out.println("Unbound variable: " + root.element);
                    }
                } else {
                    if (!isOperand(left.element)) {
                        System.out.println("Unbound variable: " + left.element);
                    }
                    if (!isOperand(right.element)) {
                        System.out.println("Unbound variable: " + right.element);
                    }
                }
            }
        }
        return root;
    }


