public class StackTraceExamples {
    public static void main(String[] args) {
        System.out.println("I'm in main");

        mercer();
    }

    public static void mercer() {
        System.out.println("I'm in mercer");

        green();
    }

    public static void green() {
        System.out.println("I'm in green");

        wooster();
    }

    public static void wooster() {
        System.out.println("I'm in wooster");


        main(null);
        //Thread.dumpStack();
    }
}
