package homework.zero;

public class FibonacciTester {
    // setting variable for golden ratio, used in closed form function
    public static final double GoldenRatio = (1 + Math.sqrt(5)) / 2;

    // recursive function
    public static long recFibonacci(int n) {
        
        // if n is 0 or 1, the function will return n
        if ((n == 0) || (n == 1)) {
            return n;
        }

        // function calls itself for n-1 and n-2
        return recFibonacci(n-1) + recFibonacci(n-2);

    }
    
    // dynamic programming
    public static long dynFibonacci(int n) {

        // similar to the recursive function, if n is 0 or 1, the function will return n
        if ((n == 0) || (n == 1)) {
            return n;
        }

        // setting local variables
        long first = 0;
        long second = 1;

        // setting final answer
        long ans = 0;

        // for loop to run until the nth number is reached
        // we start at 2 since we know the values for F(0) and F(1)
        for (int i = 2; i <= n; i++) {

            // answer is n - 1 + n -2
            ans = first+second;

            // updating first and second variables to continue the loop
            first = second;
            second = ans;
        }

        // return final answer
        return ans;
    }

    // closed form
    public static long closeFibonacci(int n) {

        // using Binet's formula and the golden ratio to 
        // find the nth number mathematically
        return Math.round((Math.pow(GoldenRatio, n)-Math.pow(-GoldenRatio, -n))/Math.sqrt(5));
    }


    public static void main(String[] args) {
        // convert arguments into ints
        int maxInput = Integer.parseInt(args[0]);
        int maxTime = Integer.parseInt(args[1]);

        if ((maxInput < 0) || (maxTime < 0)) {
            throw new IllegalArgumentException("maxInput and maxTime cannot be negative");
        }
        // test cases for recursive function
        for (int n = 0; n <= maxInput; n++) {

            // calculate runtime
            long startTime = System.currentTimeMillis();
            long ans = recFibonacci(n);
            long endTime = System.currentTimeMillis();
            long timeToRun = endTime - startTime;

            System.out.println("RECURSIVE\t" + n + "\t" + ans + "\t" + timeToRun);
            
            // stop algorithm if algorithm runtime exceeds maxTime
            if (maxTime < timeToRun) { break; }
        }

        System.out.println();

        // test cases for dynamic programming
        for (int n = 0; n <= maxInput; n++) {

            // calculate runtime
            long startTime = System.currentTimeMillis();
            long ans = dynFibonacci(n);
            long endTime = System.currentTimeMillis();
            long timeToRun = endTime - startTime;

            System.out.println("DYNAMIC\t" + n + "\t" + ans + "\t" + timeToRun);
            
            // stop algorithm if algorithm runtime exceeds maxTime
            if (maxTime < timeToRun) { break; }
        }

        System.out.println();

        // test cases for closed form
        for (int n = 0; n <= maxInput; n++) {

            // calculate runtime
            long startTime = System.currentTimeMillis();
            long ans = closeFibonacci(n);
            long endTime = System.currentTimeMillis();
            long timeToRun = endTime - startTime;

            System.out.println("FORMULA\t" + n + "\t" + ans + "\t" + timeToRun);
            
            // stop algorithm if algorithm runtime exceeds maxTime
            if (maxTime < timeToRun) { break; }
        }
    }
}