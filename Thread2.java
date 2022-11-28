public class Thread2 implements Runnable {

    private String threadText;

    public Thread2(String threadText) {
        this.threadText = threadText;
    }

    public Thread2() {
        this("Do some threaded printing...");
    }

    // TODO: when this thread is run, print threadText

    public static void main(String[] args) {
        Thread2 t1; // TODO: instantiate
        Thread2 t2; // TODO: instantiate
        // TODO: start both threads
        System.out.println("This is printing from the main process.");
    }

}
