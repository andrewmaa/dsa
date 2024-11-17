package simpleProgrammingLanguage;
public class ExpressionReplTest {
    private static int totalTests = 0;
    private static int testsPassed = 0;

    public static void main(String[] args) {
        testInputList(
            "exponent",
            "1.0",
            "1^0"
        );

        testInputList(
            "evaluate simple expression",
            "8.0",
            "3 + 5"
        );

        testInputList(
            "store and retrieve variable",
            "14.0",
            "x = 10.0", "(x + x) / 4 * 3 - 1"
        );
        
        testInputList(
            "update variable",
            "4.0",
            "a = 0", "a = a + 1", "a = a + 1", "a = a + 1", "a = a + 1", "a"
        );

        // Uncomment this once you wrote a better MapImplemented
        testLargeVariableListThreeLetters();
        testLargeVariableListFourLetters();

        System.out.println("Tests passed: " + testsPassed + "/" + totalTests);
    }

    public static void testInputList(String name, String expectedOutput, String... inputs) {
        totalTests += 1;

        ExpressionRepl repl = new ExpressionRepl();
        String output = null;
        
        // Place all the commands in the repl
        for (String input : inputs) output = repl.evaluateInput(input);

        if (expectedOutput.equals(output)) {
            System.out.println(name + " passed.");
            testsPassed++;
        } else {
            System.out.println(name + " failed.");
            System.out.println(name + " Failed - Expected: " + expectedOutput + ", got: " + output);
        }
    }

    public static void testLargeVariableListThreeLetters() {
        String name = "large variable list three letters";
        totalTests += 1;

        ExpressionRepl repl = new ExpressionRepl();
        String previousVar = null;

        long startTime = System.currentTimeMillis();

        // Loop through all 4-letter combinations
        for (char first = 'a'; first <= 'z'; first++) {
            for (char second = 'a'; second <= 'z'; second++) {
                for (char third = 'a'; third <= 'z'; third++) {
                    String varName = "" + first + second + third;
                    String input = (previousVar == null)? varName + " = 0": varName + " = " + previousVar + " + 1";
                    repl.evaluateInput(input);
                    previousVar = varName;
                }
            }
        }

        String output = repl.evaluateInput("zzz");
        String expectedOutput = "17575.0";

        long endTime = System.currentTimeMillis();

        if (expectedOutput.equals(output)) {
            System.out.println(name + " passed.");
            testsPassed++;
        } else {
            System.out.println(name + " failed.");
            System.out.println(name + " Failed - Expected: " + expectedOutput + ", got: " + output);
        }

        long time = endTime - startTime;
        System.out.println("time: " + time);
    }

    public static void testLargeVariableListFourLetters() {
        String name = "large variable list four letters";
        totalTests += 1;

        ExpressionRepl repl = new ExpressionRepl();
        String previousVar = null;

        long startTime = System.currentTimeMillis();

        // Loop through all 4-letter combinations
        for (char first = 'a'; first <= 'z'; first++) {

            for (char second = 'a'; second <= 'z'; second++) {
                for (char third = 'a'; third <= 'z'; third++) {
                    for (char fourth = 'a'; fourth <= 'z'; fourth++) {
                        String varName = "" + first + second + third + fourth;

                        String input = (previousVar == null)? varName + " = 0": varName + " = " + previousVar + " + 1";
                        
                        repl.evaluateInput(input);
                        previousVar = varName;
                    }
                }
            }
        }

        String output = repl.evaluateInput("zzzz");
        String expectedOutput = "456975.0";

        long endTime = System.currentTimeMillis();

        if (expectedOutput.equals(output)) {
            System.out.println(name + " passed.");
            testsPassed++;
        } else {
            System.out.println(name + " failed.");
            System.out.println(name + " Failed - Expected: " + expectedOutput + ", got: " + output);
        }

        long time = endTime - startTime;
        System.out.println("time: " + time);
    }
}
